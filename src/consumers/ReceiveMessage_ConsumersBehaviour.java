
package consumers;

import java.util.ArrayList;
import jade.core.behaviours.SimpleBehaviour;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import structures.Offer;

public class ReceiveMessage_ConsumersBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4428357427004593812L;
	protected MessageTemplate mt = null;
	boolean finished = false;
	private ConsumersAgent agent;

	public ReceiveMessage_ConsumersBehaviour(ConsumersAgent a) {
		super(a);
		agent = a;

	}

	public void action() {
		//System.out.println(agent.getAID().getLocalName()+" is waiting for table");
		mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ACLMessage msg = myAgent.receive(mt);
		if (msg != null) {
			
			//insert message handling code
			try {
				//System.out.println(agent.getAID().getLocalName()+" : table received");
				@SuppressWarnings("unchecked")
				ArrayList<Offer> al = (ArrayList<Offer>)msg.getContentObject();
				agent.setTable(al);
				System.out.println(agent.toString());
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finished = true;
		} else {
			block(10000);
			finished = true;
		}
	}

	public boolean done() {
		return finished;
	}

}
