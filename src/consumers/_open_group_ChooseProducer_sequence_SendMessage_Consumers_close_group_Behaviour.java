
package consumers;

import jade.core.behaviours.SequentialBehaviour;

public class _open_group_ChooseProducer_sequence_SendMessage_Consumers_close_group_Behaviour
		extends
			SequentialBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1771426056195517083L;

	public _open_group_ChooseProducer_sequence_SendMessage_Consumers_close_group_Behaviour(ConsumersAgent a) {
		super(a);

		//if have already received a table from the MarketPlace, then we can choose a producer and send a message to him
		addSubBehaviour(new ChooseProducerBehaviour(a));
		addSubBehaviour(new SendMessage_ConsumersBehaviour(a,"Producer"));
	}
}
