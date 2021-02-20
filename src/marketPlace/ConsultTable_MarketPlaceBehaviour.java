
package marketPlace;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;

public class ConsultTable_MarketPlaceBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5734680736117250018L;
	protected boolean finished;
	Behaviour simpleOneOrMoreTimesBehaviour = null;

	public ConsultTable_MarketPlaceBehaviour(Agent a) {
		super(a);

		finished = false;
		simpleOneOrMoreTimesBehaviour = new _open_group_ReceiveMessage_MarketPlace_sequence_SendMessage_MarketPlace_close_group_Behaviour(this.myAgent);
		myAgent.addBehaviour(simpleOneOrMoreTimesBehaviour);
	}

	public void action(){
 	        	if (simpleOneOrMoreTimesBehaviour.done()){
 	        		if (true){
						simpleOneOrMoreTimesBehaviour = new _open_group_ReceiveMessage_MarketPlace_sequence_SendMessage_MarketPlace_close_group_Behaviour(this.myAgent);
						myAgent.addBehaviour(simpleOneOrMoreTimesBehaviour);
					}
					//else finished = true;
				}
 	        }

	public boolean done() {
		return finished;
	}

}
