
package consumers;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;

public class ConsultTable_ConsumersBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7248578580847186452L;
	protected boolean finished;
	Behaviour simpleOneOrMoreTimesBehaviour = null;

	public ConsultTable_ConsumersBehaviour(Agent a) {
		super(a);

		finished = false;
		simpleOneOrMoreTimesBehaviour = new _open_group_SendMessage_Consumers_sequence_ReceiveMessage_Consumers_close_group_Behaviour(
				this.myAgent);
		myAgent.addBehaviour(simpleOneOrMoreTimesBehaviour);
	}
	//to receive messages for ever
	public void action(){
 	        	if (simpleOneOrMoreTimesBehaviour.done()){
 	        		if (true){
						simpleOneOrMoreTimesBehaviour = new _open_group_SendMessage_Consumers_sequence_ReceiveMessage_Consumers_close_group_Behaviour(this.myAgent);
						myAgent.addBehaviour(simpleOneOrMoreTimesBehaviour);
					}
					//else finished = true;
				}
 	        }

	public boolean done() {
		return finished;
	}

}
