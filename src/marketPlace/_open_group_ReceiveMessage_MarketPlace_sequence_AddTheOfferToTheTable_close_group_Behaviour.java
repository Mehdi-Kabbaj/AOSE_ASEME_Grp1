
package marketPlace;

import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;

public class _open_group_ReceiveMessage_MarketPlace_sequence_AddTheOfferToTheTable_close_group_Behaviour extends SequentialBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8419670341179108100L;

	public _open_group_ReceiveMessage_MarketPlace_sequence_AddTheOfferToTheTable_close_group_Behaviour(Agent a) {
		super(a);

		addSubBehaviour(new ReceiveMessage_MarketPlaceBehaviour(this.myAgent));
		
	}
}
