
package consumers;

import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

public class ConsultTable_Consumers_parallel_BuyEnergy_ConsumersBehaviour extends ParallelBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6696611475886835098L;
	ThreadedBehaviourFactory tbf = null;

	public ConsultTable_Consumers_parallel_BuyEnergy_ConsumersBehaviour(ConsumersAgent a) {
		super(a, ParallelBehaviour.WHEN_ALL);

		tbf = new ThreadedBehaviourFactory();
		
		myAgent.addBehaviour(tbf.wrap(new ConsultTable_ConsumersBehaviour(this.myAgent)));
		myAgent.addBehaviour(tbf.wrap(new BuyEnergy_ConsumersBehaviour(this.myAgent)));
	}
}
