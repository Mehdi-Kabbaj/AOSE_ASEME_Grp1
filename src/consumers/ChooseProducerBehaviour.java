
package consumers;

import java.util.Calendar;

import consumers.ConsumersAgent;
import jade.core.AID;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import structures.Offer;

public class ChooseProducerBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6180306424329656519L;
	boolean finished = false;
	ConsumersAgent agent;
	public ChooseProducerBehaviour(ConsumersAgent a) {
		super(a);
		agent = a;
	}

	public void action() {
		//We choose a producer randomly
		/*Descision Theory*/
		//if we never had a chosen offer
		if(agent.getTable().size() > 0 && agent.getChosenOffer() == null){
			double currentUtility;
			double bestUtility = 0;
			int choose=0;
			Offer currentOffer;
			for (int i = 0; i < agent.getTable().size();i++){
				currentOffer = agent.getTable().get(i);
				if(currentOffer.isRenewable()==true){
					currentUtility = 100;
				}else{
					currentUtility = 80;
				}
				currentUtility = currentUtility - currentOffer.getPrice();
				if(currentUtility > bestUtility){
					bestUtility = currentUtility;
					choose = i;
				}
				currentUtility = 0;
			}
//			Random r = new Random();
//			
//			if(agent.getTable().size()>1){
//				 choose = r.nextInt(agent.getTable().size()-1);
//			}
			agent.setChosenOffer(agent.getTable().get(choose));
			System.out.println(agent.getAID().getLocalName()+" Offer chosen: "+ agent.getChosenOffer().toString());
		}else{
			//after a day, we can choose another producer, if we choose the same producer we don't pay the raised price
			if(Calendar.getInstance().getTimeInMillis()-agent.getTime()>10000 && agent.getChosenOffer() != null){
				double currentUtility;
				double bestUtility = 0;
				int choose=0;
				Offer currentOffer;
				for (int i = 0; i < agent.getTable().size();i++){
					currentOffer = agent.getTable().get(i);
					if(currentOffer.isRenewable()==true){
						currentUtility = 100;
					}else{
						currentUtility = 80;
					}
					currentUtility = currentUtility - currentOffer.getPrice();
					if(currentUtility > bestUtility){
						bestUtility = currentUtility;
						choose = i;
					}
					currentUtility = 0;
				}//if it's a new producer, we choose the new price
				if(!agent.getTable().get(choose).getProducer().equals(agent.getChosenOffer().getProducer())){
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.setContent("Change");
					msg.addReceiver(new AID(agent.getChosenOffer().getProducer(), AID.ISLOCALNAME));//Send request to market place and wait for Table 
					myAgent.send(msg);
					agent.setChosenOffer(agent.getTable().get(choose));
				}
				//reset the calendar, we can choose a new producer in 24h 
				agent.setTime(Calendar.getInstance().getTimeInMillis());
				System.out.println(agent.getAID().getLocalName()+" Offer chosen is : "+ agent.getChosenOffer().toString());
			}
		}
		
		finished = true;
	}

	public boolean done() {
		return finished;
	}
}
