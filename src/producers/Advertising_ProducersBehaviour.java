
package producers;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;

public class Advertising_ProducersBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6371957081084610287L;
	protected boolean finished;
	Behaviour simpleOneOrMoreTimesBehaviour = null;

	public Advertising_ProducersBehaviour(Agent a) {
		super(a);

		finished = false;
		simpleOneOrMoreTimesBehaviour = new _open_group_PriceCheck_sequence_SendMessage_Producers_close_group_Behaviour(
				(ProducersAgent) this.myAgent);
		myAgent.addBehaviour(simpleOneOrMoreTimesBehaviour);
	}

	public void action(){
 	        	if (simpleOneOrMoreTimesBehaviour.done()){
 	        		if (true){	
						simpleOneOrMoreTimesBehaviour = new _open_group_PriceCheck_sequence_SendMessage_Producers_close_group_Behaviour((ProducersAgent) this.myAgent);
						myAgent.addBehaviour(simpleOneOrMoreTimesBehaviour);
					}
					//else finished = true;
				}
 	        }

	public boolean done() {
		return finished;
	}

}
