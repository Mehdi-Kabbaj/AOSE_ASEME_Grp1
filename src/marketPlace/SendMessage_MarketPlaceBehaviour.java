
package marketPlace;

import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import jade.lang.acl.ACLMessage;

public class SendMessage_MarketPlaceBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8117132622745635155L;

	boolean finished = false;

	MarketPlaceAgent mp;
	public SendMessage_MarketPlaceBehaviour(Agent a) {
		super(a);
		mp = (MarketPlaceAgent)a;
	}

	public void action() {
		if(mp.getConsumers().size()>0 && mp.getTable().size()>0){
			ACLMessage msg =  new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(new AID(mp.getConsumers().get(0), AID.ISLOCALNAME));
			System.out.println("Market: sending marketPlace Table to "+ mp.getConsumers().get(0));
			mp.getConsumers().remove(0);
			try {
				msg.setContentObject(mp.getTable());
				mp.send(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		finished = true;
	}

	public boolean done() {
		return finished;
	}

}
