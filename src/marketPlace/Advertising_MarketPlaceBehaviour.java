
package marketPlace;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;

public class Advertising_MarketPlaceBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3221874798921126932L;
	protected boolean finished;
	Behaviour simpleOneOrMoreTimesBehaviour = null;

	public Advertising_MarketPlaceBehaviour(Agent a) {
		super(a);

		finished = false;
		simpleOneOrMoreTimesBehaviour = new _open_group_ReceiveMessage_MarketPlace_sequence_AddTheOfferToTheTable_close_group_Behaviour(this.myAgent);
		myAgent.addBehaviour(simpleOneOrMoreTimesBehaviour);
	}

	public void action(){
 	        	if (simpleOneOrMoreTimesBehaviour.done()){
 	        		if (true){
						simpleOneOrMoreTimesBehaviour = new _open_group_ReceiveMessage_MarketPlace_sequence_AddTheOfferToTheTable_close_group_Behaviour(this.myAgent);
						myAgent.addBehaviour(simpleOneOrMoreTimesBehaviour);
					}
					//else finished = true;
				}
 	        }

	public boolean done() {
		return finished;
	}

}
