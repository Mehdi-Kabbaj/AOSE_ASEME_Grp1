
package producers;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class receiveMessage_ProducersBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6434888268301933204L;
	protected MessageTemplate mt = null;
	boolean finished = false;
	ProducersAgent agent;
	public receiveMessage_ProducersBehaviour(ProducersAgent a) {
		super(a);
		this.agent = a;
	}

	public void action() {
		ACLMessage msg = this.agent.receive();
	
		if (msg != null) {
			if(this.agent.checkConsumers(msg.getSender().getLocalName())== false){
				if(msg.getContent().toString().equals("Change")){
					agent.deleteConsumer(msg.getSender().getLocalName());
				}
			}else{
				this.agent.addConsumers(msg.getSender().getLocalName());
				System.out.println(agent.getLocalName()+" :SIZE: "+ agent.getConsumers().size());
			}

			finished = true;
		} else {
			block();
		}
	}

	public boolean done() {
		return finished;
	}

}
