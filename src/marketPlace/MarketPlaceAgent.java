
package marketPlace;

import java.util.ArrayList;

import interfaces.MarketPlaceAgentManager;
import jade.core.Agent;
import structures.Offer;

public class MarketPlaceAgent extends Agent implements MarketPlaceAgentManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7985793503396122176L;
	private ArrayList<Offer> table = new ArrayList<Offer>();
	private ArrayList<String> consumers = new ArrayList<String>(); 
	private ArrayList<Offer> newOffer = new ArrayList<Offer>();
	private int day = 0;
	private int nbMessageSent_MarketPlace = 0;
	
	

	public void setup() {
		//add behaviour
		addBehaviour(new _open_group_Advertising_MarketPlace_parallel_ConsultTable_MarketPlace_close_group_Behaviour(this));
	}
	
	public MarketPlaceAgent(){
		registerO2AInterface(MarketPlaceAgentManager.class,this);
	}
	
	
	public void updateProducers(String producer, Offer offer){
		boolean b = true;
		for (int i = 0; i < table.size(); i++) {
			if(table.get(i).getProducer().equals(producer)){
				table.remove(i);
				table.add(offer);
				//table.set(i, offer);
				b = false;
			}
		}
		if(b == true){
			addOffer(offer);
		}
		
	}
	
	public void addOffer(Offer offer){
		this.table.add(offer);
	}
	public void addNewOffer(Offer offer){
		this.newOffer.add(offer);
	}
	
	public ArrayList<Offer> getNewOffer() {
		return newOffer;
	}
	public void setNewOffer(ArrayList<Offer> newOffer) {
		this.newOffer = newOffer;
	}
	
	public void addConsumers(String consumer){
		consumers.add(consumer);
	}
	public void deleteConsumers(){
		consumers.remove(0);
	}
	public ArrayList<Offer> getTable() {
		return table;
	}
	public void setTable(ArrayList<Offer> table) {
		this.table = table;
	}
	public ArrayList<String> getConsumers() {
		return consumers;
	}
	public void setConsumers(ArrayList<String> consumers) {
		this.consumers = consumers;
	}
	protected void takeDown() {
		doDelete();
	}
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getNbMessageSent_MarketPlace() {
		return nbMessageSent_MarketPlace;
	}

	public void setNbMessageSent_MarketPlace(int nbMessageSent_MarketPlace) {
		this.nbMessageSent_MarketPlace = nbMessageSent_MarketPlace;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("MarketTable : [");
		for(int i = 0; i < this.table.size(); i++){
			sb.append(table.get(i));
			if(i != this.table.size()-1){
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();	
	}

}
