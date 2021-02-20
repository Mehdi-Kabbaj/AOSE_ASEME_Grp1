
package marketPlace;

import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;

public class _open_group_Advertising_MarketPlace_parallel_ConsultTable_MarketPlace_close_group_Behaviour
		extends
			SequentialBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5424281501811356340L;

	public _open_group_Advertising_MarketPlace_parallel_ConsultTable_MarketPlace_close_group_Behaviour(Agent a) {
		super(a);

		addSubBehaviour(new Advertising_MarketPlace_parallel_ConsultTable_MarketPlaceBehaviour(this.myAgent));

	}
}
