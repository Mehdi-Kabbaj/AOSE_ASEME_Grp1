
package marketPlace;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import structures.Offer;

public class ReceiveMessage_MarketPlaceBehaviour extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5210536852879565674L;
	protected MessageTemplate mt = null;
	boolean finished = false;
	MarketPlaceAgent mp;
	public ReceiveMessage_MarketPlaceBehaviour(Agent a) {
		super(a);
		mp =(MarketPlaceAgent) a;
	}

	public void action() {
		//System.out.println(this.getParent().getBehaviourName().toString());
		if(this.getParent().getBehaviourName().toString().equals("_open_group_ReceiveMessage_MarketPlace_sequence_SendMessage_MarketPlace_close_group_Behaviour")){
			mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
			ACLMessage msg = myAgent.receive(mt);
			if(msg != null){
				System.out.println("Market: conulting request received from "+msg.getSender().getLocalName());
				mp.addConsumers(msg.getSender().getLocalName());
				finished = true;
			}else{
				block();
			}
		}else{ 
			if(this.getParent().getBehaviourName().toString().equals("_open_group_ReceiveMessage_MarketPlace_sequence_AddTheOfferToTheTable_close_group_Behaviour")){
				mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
				ACLMessage msg = myAgent.receive(mt);
				if (msg != null) {
					Offer of;
					try {
						of = (Offer) msg.getContentObject();
						mp.updateProducers(msg.getSender().getLocalName(), of);
					} catch (UnreadableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(msg.getSender().getLocalName().equals("Producer")){
						mp.setDay(mp.getDay()+1);
					}
					finished = true;
				}else{
					block();
				}
			}
		}	
	}

	public boolean done() {
		return finished;
	}

}
