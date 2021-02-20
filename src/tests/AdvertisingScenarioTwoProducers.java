package tests;

import java.util.concurrent.TimeUnit;

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
 * Another advertising scenario but with two producers to check that the marketplace adds both offers to his table
 */
public class AdvertisingScenarioTwoProducers {
	public static void main(String[] args) throws InterruptedException {
		Runtime runtime = Runtime.instance();
		Profile config = new ProfileImpl("localhost", 8884, null);
		config.setParameter("gui", "true");
		AgentContainer mc = runtime.createMainContainer(config);
		try{
			Offer offer1 = new Offer(50,"Producer", true);
			Offer offer2 = new Offer(30,"Producer2", true);
			int quota1 = 1;
			int quota2 = 3;
			Object args1[] = new Object[2];
		    args1[0] = offer1;
		    args1[1] = quota1;
		    
		    Object args2[] = new Object[2];
		    args2[0] = offer2;
		    args2[1] = quota2;
			AgentController mp = mc.createNewAgent("Market", MarketPlaceAgent.class.getName(), null);
			AgentController pa1 = mc.createNewAgent("Producer", ProducersAgent.class.getName(), args1);
			AgentController pa2 = mc.createNewAgent("Producer2", ProducersAgent.class.getName(), args2);

			mp.start();
			TimeUnit.MILLISECONDS.sleep(1000);
			pa1.start();
			TimeUnit.MILLISECONDS.sleep(1000);
			pa2.start();
			TimeUnit.MILLISECONDS.sleep(2000);
			MarketPlaceAgentManager o2a_mp = null;
            ProducersAgentManager o2a_pa1 = null;
            ProducersAgentManager o2a_pa2 = null;
            try{
                
                o2a_mp = mp.getO2AInterface(MarketPlaceAgentManager.class);
                o2a_pa1 = pa1.getO2AInterface(ProducersAgentManager.class);
                o2a_pa2 = pa2.getO2AInterface(ProducersAgentManager.class);
                
            }catch(StaleProxyException e){e.printStackTrace();}
            System.out.println(o2a_mp.toString());
            if(o2a_mp.toString().equals("MarketTable : [Offer [price=50.0, producer=Producer, renewable=true], Offer [price=30.0, producer=Producer2, renewable=true]]")){
            	System.out.println(o2a_mp.toString());
            	System.out.println("ADVERTISING WITH TWO PRODUCERS SUCCESS, END OF THE TEST");
            	o2a_mp.doDelete();
            	o2a_pa1.doDelete();
            	o2a_pa2.doDelete();
            	pa1.kill();
            	pa2.kill();
            	mp.kill();
            	Runtime.instance().shutDown();
            }
        } catch (StaleProxyException e) { e.printStackTrace(); }
    }
}
