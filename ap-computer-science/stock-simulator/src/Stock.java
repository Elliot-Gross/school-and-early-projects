
public class Stock {
	
	private String name;
	private double price;
	private int quantity;
	private String type;
	
	private double startPrice;
	private double stopLoss;
	
	public Stock(String name, double price, int quantity, String type) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.type = type;
		
		this.startPrice = price;
		this.stopLoss = price/2;
	}
	
	public Stock(String name, double price, String type, double balance) {
		this.name = name;
		this.price = price;
		this.quantity = (int) (balance/price);
		this.type = type;
		
		this.startPrice = price;
		this.stopLoss = price/2;
	}
	
	public void fluxuate() {
		if(this.type == "Normal") {
			double percentChange = (Math.random()*10)-5;
			this.price += this.price*(percentChange/100);

		} else if(this.type == "Penny") {
			double percentChange = (Math.random()*50)+50;
			if(Math.floor(Math.random()*2) == 1) {
				percentChange = ((Math.random()*50)+50)*-1;
			}
			this.price += this.price*(percentChange/100);
		}
	}
	
	public float percentChangeSinceStart() {
		return (float)((this.price - this.startPrice)/this.startPrice)*100;
	}

	public boolean isAtStopLoss() {
		return (this.price <= this.stopLoss);
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	public void setQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}
	
	public double getPrice() {
		return this.price;
	}
	public double getStartPrice() {
		return this.startPrice;
	}
	public String getName() {
		return this.name;
	}
	public String getType() {
		return this.type;
	}
	
	public void output() {
		System.out.println("Name: " + this.name);
		System.out.println("Price: " + this.price);
		System.out.println("Quantity: " + this.quantity);
		System.out.println("Type: " + this.type);
	}

}
