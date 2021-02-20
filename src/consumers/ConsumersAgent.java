
package consumers;

import java.util.ArrayList;
import java.util.Calendar;

import interfaces.ConsumersAgentManager;
import jade.core.Agent;
import structures.Offer;

public class ConsumersAgent extends Agent implements ConsumersAgentManager{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1629467896595524479L;
	private ArrayList<Offer> table = new ArrayList<Offer>();
	private Offer chosenOffer;
	private long time = Calendar.getInstance().getTimeInMillis();
	private int nbMessageSent_consumers = 0;
	
	public void setup() {
		addBehaviour(new ConsultTable_Consumers_parallel_BuyEnergy_ConsumersBehaviour(this));
	}
	
	public ConsumersAgent(){
		registerO2AInterface(ConsumersAgentManager.class,this);
	}
	
	@Override
	public String toString() {
		return this.getLocalName()+ " : chosenOffer=" + chosenOffer + "]";
	}
	
	protected void takeDown() {
		doDelete();
	}
	
	
	//////////GETTER AND SETTERS///////////////
	public Offer getChosenOffer() {
		return chosenOffer;
	}
	public void setChosenOffer(Offer chosenOffer) {
		this.chosenOffer = chosenOffer;
	}
	public ArrayList<Offer> getTable() {
		return table;
	}
	public void setTable(ArrayList<Offer> table) {
		this.table = table;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getNbMessageSent_consumers() {
		return nbMessageSent_consumers;
	}

	public void setNbMessageSent_consumers(int nbMessageSent_consumers) {
		this.nbMessageSent_consumers = nbMessageSent_consumers;
	}
	
//	public String toString(){
//		StringBuilder sb = new StringBuilder();
//		sb.append(this.getLocalName()+" : [");
//		for(int i = 0; i < this.table.size(); i++){
//			sb.append(table.get(i).toString());
//			if(i != this.table.size()-1){
//				sb.append(", ");
//			}
//		}
//		sb.append("]");
//		return sb.toString();
//	}
	
}
