package tests;

import java.util.concurrent.TimeUnit;

import consumers.ConsumersAgent;
import interfaces.ConsumersAgentManager;
import interfaces.MarketPlaceAgentManager;
import interfaces.ProducersAgentManager;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import marketPlace.MarketPlaceAgent;
import producers.ProducersAgent;
import structures.Offer;
/*
 * A tricky scenario : we have to check if a given producer, based on his performance of the day, actually updates his price offer.
 * Here the producer1 gets both consumers 1 and 2 as clients but the producer2 gets none, so normally he should lower his price the next day.
 */
public class PriceFluctuationScenario {
	public static void main(String[] args) throws InterruptedException {
		Runtime runtime = Runtime.instance();
		Profile config = new ProfileImpl("localhost", 8884, null);
		config.setParameter("gui", "true");
		AgentContainer mc = runtime.createMainContainer(config);
		try{
			Offer offer1 = new Offer(50,"Producer1", true);
			Offer offer2 = new Offer(40,"Producer2", false);
			int quota1 = 1;
			int quota2 = 3;
			Object args1[] = new Object[2];
		    args1[0] = offer1;
		    args1[1] = quota1;
		    
		    Object args2[] = new Object[2];
		    args2[0] = offer2;
		    args2[1] = quota2;
			AgentController mp = mc.createNewAgent("Market", MarketPlaceAgent.class.getName(), null);
			AgentController pa1 = mc.createNewAgent("Producer1", ProducersAgent.class.getName(), args1);
			AgentController pa2 = mc.createNewAgent("Producer2", ProducersAgent.class.getName(), args2);
			AgentController co1 = mc.createNewAgent("Consumer1", ConsumersAgent.class.getName(), null);
			AgentController co2 = mc.createNewAgent("Consumer2", ConsumersAgent.class.getName(), null);

			mp.start();
			TimeUnit.MILLISECONDS.sleep(1000);
			pa1.start();
			TimeUnit.MILLISECONDS.sleep(1000);
			pa2.start();
			TimeUnit.MILLISECONDS.sleep(2000);
			co1.start();
			co2.start();
			MarketPlaceAgentManager o2a_mp = null;
            ProducersAgentManager o2a_pa1 = null;
            ProducersAgentManager o2a_pa2 = null;
            ConsumersAgentManager o2a_co1 = null;
            ConsumersAgentManager o2a_co2 = null;
            try{
                
                o2a_mp = mp.getO2AInterface(MarketPlaceAgentManager.class);
                o2a_pa1 = pa1.getO2AInterface(ProducersAgentManager.class);
                o2a_pa2 = pa2.getO2AInterface(ProducersAgentManager.class);
                o2a_co1 = co1.getO2AInterface(ConsumersAgentManager.class);
                o2a_co2 = co2.getO2AInterface(ConsumersAgentManager.class);
                
            }catch(StaleProxyException e){e.printStackTrace();}
            TimeUnit.MILLISECONDS.sleep(12000); //Wait enought time 
            if(o2a_pa2.toString().equals("Producer2 : Offer [price=30.0, producer=Producer2, renewable=false] , quota : 0")){
	            System.out.println("PRICE FLUCTUATION WITH TWO PRODUCERS AND TWO CONSUMER SUCCESS, END OF THE TEST");
	            o2a_mp.doDelete();
	            o2a_pa1.doDelete();
	            o2a_pa2.doDelete();
	            o2a_co1.doDelete();
	            o2a_co2.doDelete();
	            pa1.kill();
	            pa2.kill();
	            mp.kill();
	            co1.kill();
	            co2.kill();
	            Runtime.instance().shutDown();
            }
        } catch (StaleProxyException e) { e.printStackTrace(); }
    }
}
