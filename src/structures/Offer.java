package structures;

import jade.util.leap.Serializable;

public class Offer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 260093204750977603L;

	@Override
	public String toString() {
		return "Offer [price=" + price + ", producer=" + producer + ", renewable=" + renewable + "]";
	}
	private double price;
	private String producer;
	private boolean renewable;
	
	public  Offer(double price, String producer, boolean renewable) {
		super();
		this.price = price;
		this.producer = producer;
		this.renewable = renewable;
	}
	public boolean isRenewable() {
		return renewable;
	}
	public void setRenewable(boolean renewable) {
		this.renewable = renewable;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
}
