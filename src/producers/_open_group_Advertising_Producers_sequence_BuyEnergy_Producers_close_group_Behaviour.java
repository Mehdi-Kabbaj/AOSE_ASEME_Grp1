
package producers;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

public class _open_group_Advertising_Producers_sequence_BuyEnergy_Producers_close_group_Behaviour
		extends
		ParallelBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2865216351315625715L;
	ThreadedBehaviourFactory tbf = null;
	public _open_group_Advertising_Producers_sequence_BuyEnergy_Producers_close_group_Behaviour(Agent a) {
		super(a, ParallelBehaviour.WHEN_ALL);

		tbf = new ThreadedBehaviourFactory();
		
		myAgent.addBehaviour(tbf.wrap(new Advertising_ProducersBehaviour(this.myAgent)));
		myAgent.addBehaviour(tbf.wrap(new BuyEnergy_ProducersBehaviour(this.myAgent)));

	}
}
