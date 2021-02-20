
package consumers;

import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;

public class _open_group_SendMessage_Consumers_sequence_ReceiveMessage_Consumers_close_group_Behaviour
		extends
			SequentialBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7539333160958822899L;

	public _open_group_SendMessage_Consumers_sequence_ReceiveMessage_Consumers_close_group_Behaviour(Agent a) {
		super(a);
		
		//we send a request to the MarketPlace, and wait for a response
		addSubBehaviour(new SendMessage_ConsumersBehaviour((ConsumersAgent) this.myAgent, "Market"));
		addSubBehaviour(new ReceiveMessage_ConsumersBehaviour((ConsumersAgent) this.myAgent));

	}
}
