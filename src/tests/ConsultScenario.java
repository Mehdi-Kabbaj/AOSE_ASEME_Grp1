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
 * Simple scenario to see that a consumer gets the market place table
 */
public class ConsultScenario {
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
			AgentController co = mc.createNewAgent("Consumer", ConsumersAgent.class.getName(), null);

			mp.start();
			TimeUnit.MILLISECONDS.sleep(1000);
			pa.start();
			TimeUnit.MILLISECONDS.sleep(2000);
			co.start();
			MarketPlaceAgentManager o2a_mp = null;
            ProducersAgentManager o2a_pa = null;
            ConsumersAgentManager o2a_co = null;
            try{
                
                o2a_mp = mp.getO2AInterface(MarketPlaceAgentManager.class);
                o2a_pa = pa.getO2AInterface(ProducersAgentManager.class);
                o2a_co = co.getO2AInterface(ConsumersAgentManager.class);
                
            }catch(StaleProxyException e){e.printStackTrace();}
            TimeUnit.MILLISECONDS.sleep(5000);
            if(o2a_co.toString().equals("Consumer : [table=[Offer [price=50.0, producer=Producer, renewable=true]], chosenOffer=null]")){
            	System.out.println(o2a_mp.toString());
            	System.out.println("CONSULTING SUCESS, END OF THE TEST");
            	o2a_mp.doDelete();
            	o2a_pa.doDelete();
            	o2a_co.doDelete();
            	pa.kill();
            	mp.kill();
            	co.kill();
            	Runtime.instance().shutDown();
            }
        } catch (StaleProxyException e) { e.printStackTrace(); }
    }
}
