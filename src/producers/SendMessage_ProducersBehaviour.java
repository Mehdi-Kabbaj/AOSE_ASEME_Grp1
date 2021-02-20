
package producers;

import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;


public class SendMessage_ProducersBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1834330215350623042L;
	boolean finished = false;
	ProducersAgent pa;
	public SendMessage_ProducersBehaviour(Agent a) {
		super(a);
		pa = (ProducersAgent) a;
	}
//we send the offer to Market
	public void action() {
		if(pa.isFirsTime()){//the first time at the beginning of the program 
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(new AID("Market", AID.ISLOCALNAME));
			try {
				msg.setContentObject(pa.getOffer());
				pa.setFirsTime(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(pa.getAID().getLocalName()+" : First time Send to Market : " + pa.getOffer().toString());
			myAgent.send(msg);
		}else{
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(new AID("Market", AID.ISLOCALNAME));
			try {
				msg.setContentObject(pa.getOffer());
				System.out.println(pa.getAID().getLocalName()+ " : Send to Market : " + pa.getOffer().toString());
				pa.setUpdated(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myAgent.send(msg);
		}
		finished = true;
	}

	public boolean done() {
		return finished;
	}

}
