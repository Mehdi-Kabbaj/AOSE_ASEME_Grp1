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
 * Another consulting scenario where we check if two consumers get the table from the marketplace
 */
public class ConsultScenarioTwoConsumers {
	public static void main(String[] args) throws InterruptedException {
		Runtime runtime = Runtime.instance();
		Profile config = new ProfileImpl("localhost", 8884, null);
		config.setParameter("gui", "true");
		AgentContainer mc = runtime.createMainContainer(config);
		try{
			Offer offer1 = new Offer(50,"Producer", true);
			int quota = 1;
			Object argsbis[] = new Object[2];
		    argsbis[0] = offer1;
		    argsbis[1] = quota;
			AgentController mp = mc.createNewAgent("Market", MarketPlaceAgent.class.getName(), null);
			AgentController pa = mc.createNewAgent("Producer", ProducersAgent.class.getName(), argsbis);
			AgentController co1 = mc.createNewAgent("Consumer1", ConsumersAgent.class.getName(), null);
			AgentController co2 = mc.createNewAgent("Consumer2", ConsumersAgent.class.getName(), null);

			mp.start();
			TimeUnit.MILLISECONDS.sleep(1000);
			pa.start();
			TimeUnit.MILLISECONDS.sleep(2000);
			co1.start();
			co2.start();
			MarketPlaceAgentManager o2a_mp = null;
            ProducersAgentManager o2a_pa = null;
            ConsumersAgentManager o2a_co1 = null;
            ConsumersAgentManager o2a_co2 = null;
            try{
                
                o2a_mp = mp.getO2AInterface(MarketPlaceAgentManager.class);
                o2a_pa = pa.getO2AInterface(ProducersAgentManager.class);
                o2a_co1 = co1.getO2AInterface(ConsumersAgentManager.class);
                o2a_co2 = co2.getO2AInterface(ConsumersAgentManager.class);
                
            }catch(StaleProxyException e){e.printStackTrace();}
            TimeUnit.MILLISECONDS.sleep(5000);
            if(o2a_co1.toString().equals("Consumer1 : [table=[Offer [price=50.0, producer=Producer, renewable=true]], chosenOffer=null]")){
            	if(o2a_co2.toString().equals("Consumer2 : [table=[Offer [price=50.0, producer=Producer, renewable=true]], chosenOffer=null]")){
	            	System.out.println(o2a_mp.toString());
	            	System.out.println("CONSULTING WITH TWO OR MORE CONSUMERS SUCESS, END OF THE TEST");
	            	o2a_mp.doDelete();
	            	o2a_pa.doDelete();
	            	o2a_co1.doDelete();
	            	o2a_co2.doDelete();
	            	pa.kill();
	            	mp.kill();
	            	co1.kill();
	            	co2.kill();
	            	Runtime.instance().shutDown();
            	}
			}
        } catch (StaleProxyException e) { e.printStackTrace(); }
    }
}
