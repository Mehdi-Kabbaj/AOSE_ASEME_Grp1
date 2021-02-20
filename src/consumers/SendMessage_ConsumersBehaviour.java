
package consumers;


import jade.core.AID;
import jade.core.behaviours.SimpleBehaviour;

import jade.lang.acl.ACLMessage;

public class SendMessage_ConsumersBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5850971030366552635L;
	private String marketOrProducer;
	boolean finished = false;
	ConsumersAgent a;

	public SendMessage_ConsumersBehaviour(ConsumersAgent a, String market) {
		super(a);
		marketOrProducer = market;
		this.a = a;

	}

	public void action() {
		//System.out.println("send to "+marketOrProducer);
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.setContent("Request");
		if(marketOrProducer.equals("Market")){
			msg.addReceiver(new AID(marketOrProducer, AID.ISLOCALNAME));//Send request to market place and wait for Table
			System.out.println(this.a.getLocalName()+": sending a consulting request to the marketplace");
			myAgent.send(msg);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("Consumer Agent Terminated");
			}
		}else{
			if(a.getChosenOffer()!= null){
				msg.addReceiver(new AID(a.getChosenOffer().getProducer(), AID.ISLOCALNAME));
				System.out.println(this.a.getLocalName()+": sending a reservation offer to " + a.getChosenOffer().getProducer());
				myAgent.send(msg);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					System.out.println("Consumer Agent Terminated");
				}
			}
		}
		finished = true;
	}

	public boolean done() {
		return finished;
	}

}
