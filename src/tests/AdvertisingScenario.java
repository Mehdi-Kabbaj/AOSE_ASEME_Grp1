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
 * Simple Scenario where one producer wants to advertise his offer on the marketplace
 */
public class AdvertisingScenario {
	public static void main(String[] args) throws InterruptedException {
		Runtime runtime = Runtime.instance();
		Profile config = new ProfileImpl("localhost", 8885, null);
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

			mp.start();
			TimeUnit.MILLISECONDS.sleep(1000);
			pa.start();
			TimeUnit.MILLISECONDS.sleep(2000);
			MarketPlaceAgentManager o2a_mp = null;
            ProducersAgentManager o2a_pa = null;
            try{
                
                o2a_mp = mp.getO2AInterface(MarketPlaceAgentManager.class);
                o2a_pa = pa.getO2AInterface(ProducersAgentManager.class);
                
            }catch(StaleProxyException e){e.printStackTrace();}
            System.out.println(o2a_mp.toString());
            if(o2a_mp.toString().equals("MarketTable : [Offer [price=50.0, producer=Producer, renewable=true]]")){
            	System.out.println(o2a_mp.toString());
            	System.out.println("ADVERTISING SUCESS, END OF THE TEST");
            	o2a_mp.doDelete();
            	o2a_pa.doDelete();
            	pa.kill();
            	mp.kill();
            	Runtime.instance().shutDown();
            }
        } catch (StaleProxyException e) { e.printStackTrace(); }
    }
}
