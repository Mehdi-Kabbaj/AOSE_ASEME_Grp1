
package consumers;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;

public class BuyEnergy_ConsumersBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6370457280899240794L;
	protected boolean finished;
	Behaviour simpleOneOrMoreTimesBehaviour = null;
	ConsumersAgent agent;
	public BuyEnergy_ConsumersBehaviour(Agent myAgent) {
		super(myAgent);
		agent = (ConsumersAgent) myAgent;
		finished = false;
		simpleOneOrMoreTimesBehaviour = new _open_group_ChooseProducer_sequence_SendMessage_Consumers_close_group_Behaviour((ConsumersAgent) myAgent);
		myAgent.addBehaviour(simpleOneOrMoreTimesBehaviour);
	}
	//there we add the behaviour to ask for the new table of the marketPlace, for ever
	public void action(){
 	        	if (simpleOneOrMoreTimesBehaviour.done()){
 	        		if (simpleOneOrMoreTimesBehaviour.done()){
						simpleOneOrMoreTimesBehaviour = new _open_group_ChooseProducer_sequence_SendMessage_Consumers_close_group_Behaviour(agent);
						myAgent.addBehaviour(simpleOneOrMoreTimesBehaviour);
					}
					else finished = true;
				}
 	        }

	public boolean done() {
		return finished;
	}

}
