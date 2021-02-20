
package producers;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;

public class BuyEnergy_ProducersBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 917670379267952175L;
	protected boolean finished;
	Behaviour simpleOneOrMoreTimesBehaviour = null;
	ProducersAgent agent;
	
	public BuyEnergy_ProducersBehaviour(Agent a) {
		super(a);
		
		agent = (ProducersAgent) a;
		finished = false;
		simpleOneOrMoreTimesBehaviour = new receiveMessage_ProducersBehaviour(agent);
		agent.addBehaviour(simpleOneOrMoreTimesBehaviour);
	}

	public void action(){
    	if (simpleOneOrMoreTimesBehaviour.done()){
    		
    		if (true){
    			//System.out.println("one more time");
				simpleOneOrMoreTimesBehaviour = new receiveMessage_ProducersBehaviour(agent);
				agent.addBehaviour(simpleOneOrMoreTimesBehaviour);
			}
			//else finished = true;
		}
    }

	public boolean done() {
		//System.out.println("DONE");
		return finished;
	}

}
