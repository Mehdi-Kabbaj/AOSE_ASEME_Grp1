
package producers;

import java.util.concurrent.TimeUnit;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import structures.Offer;

public class PriceCheckBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1963737156521360755L;
	boolean finished = false;
	ProducersAgent agent;
	
	public PriceCheckBehaviour(Agent a) {
		super(a);
		agent =  (ProducersAgent) a;
	}

	public void action() {
		if(agent.isFirsTime_PriceCheckReview()==false){
			try {
				TimeUnit.MILLISECONDS.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println("Agent Producer Terminated");
			}
			/*Decision Theory*/
			//check the max of consumers, rise price if there are more consumers than the limit
			System.out.println(agent.getLocalName()+ ": modifying prices based on today's performance");
			if(agent.getConsumers().size()> agent.getQuota() && agent.isUpdated()==false){//rise price if there is more consumers than the producer can manages
				System.out.println(agent.getLocalName()+ ": Nb of clients today exeded by : "+ (agent.getConsumers().size() - agent.getQuota()));
				Offer newOffer =  agent.getOffer();
				newOffer.setPrice(newOffer.getPrice()+10);
				agent.setQuota(agent.getQuota()+2);
				agent.setOffer(newOffer);
				agent.setLastTimeChanged(agent.getTime());
				agent.setUpdated(true);
				agent.setQuota(agent.getQuota()+(agent.getConsumers().size()-agent.getQuota()));
			}else{
				if(agent.getConsumers().size()< agent.getQuota() && agent.isUpdated()==false){
					System.out.println(agent.getLocalName()+ ": Nb of clients not as expected by : "+ (agent.getConsumers().size() - agent.getQuota()));
					Offer newOffer =  agent.getOffer();
					newOffer.setPrice(newOffer.getPrice()-10);
					agent.setQuota(agent.getQuota()-2);
					agent.setOffer(newOffer);
					agent.setLastTimeChanged(agent.getTime());
					agent.setUpdated(true);
					agent.setQuota(agent.getQuota()+(agent.getConsumers().size()-agent.getQuota()));
				}
			}
		}else{
			agent.setFirsTime_PriceCheckReview(false);
		}
		finished = true;
	}

	public boolean done() {
		return finished;
	}

}
