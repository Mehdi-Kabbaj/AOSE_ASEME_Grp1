
package marketPlace;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

public class Advertising_MarketPlace_parallel_ConsultTable_MarketPlaceBehaviour extends ParallelBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8653695527843603839L;
	ThreadedBehaviourFactory tbf = null;

	public Advertising_MarketPlace_parallel_ConsultTable_MarketPlaceBehaviour(Agent a) {
		super(a, ParallelBehaviour.WHEN_ALL);

		tbf = new ThreadedBehaviourFactory();

		myAgent.addBehaviour(tbf.wrap(new Advertising_MarketPlaceBehaviour(this.myAgent)));
		myAgent.addBehaviour(tbf.wrap(new ConsultTable_MarketPlaceBehaviour(this.myAgent)));
	}
}
