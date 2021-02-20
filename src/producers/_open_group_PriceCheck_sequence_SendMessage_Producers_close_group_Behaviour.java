
package producers;

import jade.core.behaviours.SequentialBehaviour;

public class _open_group_PriceCheck_sequence_SendMessage_Producers_close_group_Behaviour extends SequentialBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4672777360399872770L;

	public _open_group_PriceCheck_sequence_SendMessage_Producers_close_group_Behaviour(ProducersAgent a) {
		super(a);

		addSubBehaviour(new PriceCheckBehaviour((ProducersAgent) this.myAgent));
		addSubBehaviour(new SendMessage_ProducersBehaviour(this.myAgent));

	}
}
