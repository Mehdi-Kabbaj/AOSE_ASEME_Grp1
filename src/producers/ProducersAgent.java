
package producers;

import java.util.ArrayList;
import java.util.Random;
import interfaces.ProducersAgentManager;

import java.util.Calendar;
import jade.core.Agent;
import structures.Offer;

public class ProducersAgent extends Agent implements ProducersAgentManager{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4870661022637425152L;
	private int quota;//consumers limit before rising price
	private Offer offer;//producer offer
	private ArrayList<String> consumers = new ArrayList<String>(); 
	private long time = Calendar.getInstance().getTimeInMillis();
	private long lastTimeChanged = 0;
	private boolean updated = false;
	private boolean firsTime = true;
	private boolean firsTime_PriceCheckReview = true;
	private int nbMessageSent_Producers = 0;
	
	public void setup() {
		Random random = new Random();
		quota = random.nextInt(6-4)+4;
		//quota = 1;
		int r = random.nextInt(2);
		//renewable or not
		if(r<1){
		offer = new Offer(random.nextInt(50)+50, this.getAID().getLocalName(), true);
		}else{offer = new Offer(random.nextInt(50)+50, this.getAID().getLocalName(), false);}
		Object[] args = getArguments();
		if(args != null){
			this.offer = (Offer) args[0];
			this.quota = (int) args[1];
		}
		addBehaviour(new _open_group_Advertising_Producers_sequence_BuyEnergy_Producers_close_group_Behaviour(this));
	}
	
	public ProducersAgent(){
		registerO2AInterface(ProducersAgentManager.class,this);
	}
	
	/*public ProducersAgent(Offer offer, int quota){
		this.offer = offer;
		this.quota = quota;
	}*/

	
	
	public boolean checkConsumers(String consumer){//check if consumer is already in the list, to dont add him again
		for (int i = 0; i < consumers.size(); i++) {
			if(consumers.get(i).equals(consumer)){
				return false;
			}
		}
		
		return true;
		
	}
	
	public void deleteConsumer(String consumer){
		for (int i = 0; i < consumers.size(); i++) {
			if(consumers.get(i).equals(consumer)){
				consumers.remove(i);
			}
		}
	}
	
	public int getQuota() {
		return quota;
	}
	public void setQuota(int quota) {
		this.quota = quota;
	}
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	protected void takeDown() {
		doDelete();
	}
	
	public void addConsumers(String consumer){
		consumers.add(consumer);
	}

	public ArrayList<String> getConsumers() {
		return consumers;
	}
	public void setConsumers(ArrayList<String> consumers) {
		this.consumers = consumers;
	}


	public long getTime() {
		return time;
	}


	public void setTime(long time) {
		this.time = time;
	}
	
	public long getLastTimeChanged() {
		return lastTimeChanged;
	}


	public void setLastTimeChanged(long time) {
		this.lastTimeChanged = time;
	}


	public boolean isUpdated() {
		return updated;
	}


	public void setUpdated(boolean updated) {
		this.updated = updated;
	}


	public boolean isFirsTime() {
		return firsTime;
	}


	public void setFirsTime(boolean firsTime) {
		this.firsTime = firsTime;
	}



	public boolean isFirsTime_PriceCheckReview() {
		return firsTime_PriceCheckReview;
	}



	public void setFirsTime_PriceCheckReview(boolean firsTime_PriceCheckReview) {
		this.firsTime_PriceCheckReview = firsTime_PriceCheckReview;
	}



	public int getNbMessageSent_Producers() {
		return nbMessageSent_Producers;
	}



	public void setNbMessageSent_Producers(int nbMessageSent_Producers) {
		this.nbMessageSent_Producers = nbMessageSent_Producers;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getLocalName()+ " : ");
		if(this.offer!=null){
			sb.append(this.offer.toString());
		}
		sb.append(" , quota : " + this.quota);
		return sb.toString();
	}
	
}
