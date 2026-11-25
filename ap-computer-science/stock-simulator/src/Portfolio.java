import java.util.ArrayList;

public class Portfolio {

	private double balance;
	private double originalBalance;
	
	private double maxProfits;
	private double minProfits;
	
	ArrayList<Stock> stocks = new ArrayList<Stock>();
	
	public Portfolio(double balance) {
		this.balance = balance;
		this.originalBalance = balance;
		
		this.maxProfits = this.getTotalAssets();
		this.minProfits = this.getTotalAssets();
		
	}
	
	public void buyStock(String name, double price, int quantity, String type) {
		Stock stock = new Stock(name, price, quantity, type);
		stocks.add(stock);
		this.balance -= stock.getQuantity()*stock.getPrice();
	}
	
	public void buyPennyStock(String name, double price, String type) {
		Stock stock = new Stock(name, price, type, this.balance);
		stocks.add(stock);
		this.balance -= stock.getQuantity()*stock.getPrice();
	}
	
	public void sell(Stock stock) {
		balance += stock.getQuantity() * stock.getPrice();
		stock.setQuantity(0);
	}
	
	public void handleStocks() {
		for(Stock stock: this.stocks) {
			if(stock.getQuantity() > 0) {
				stock.fluxuate();
				if(stock.isAtStopLoss() & (!stock.getType().equals("Penny"))) {
					System.out.println("Stop Loss for Stock "+stock.getName()+" Reached");
					this.sell(stock);
				}
			}
		}
	}
	
	public double calculateLiquidityValue() {
		double liquidTotal = 0;
		for(Stock stock: this.stocks) {
			liquidTotal += stock.getQuantity() * stock.getPrice();
		}
		
		return liquidTotal;
	}
	
	public double getTotalAssets() {
		return this.balance + this.calculateLiquidityValue();
	}
	
	public double getTotalAssetsPercentChange() {
		return (this.getTotalAssets()-this.originalBalance)/this.originalBalance;
	}
		
	
	public void sellAllStocks() {
		for(Stock stock: this.stocks) {
			if(stock.getQuantity() > 0) {
				this.sell(stock);
			}
		}
	}
	
	public void updateRecord() {
		double currentTotalAssets = this.getTotalAssets();
		if(currentTotalAssets > this.maxProfits) {
			this.maxProfits = currentTotalAssets;
		} else if(currentTotalAssets < this.minProfits) {
			this.minProfits = currentTotalAssets;
		}
	}
	
	public void outputStocksPercentChange() {
		for(Stock stock: this.stocks) {
			System.out.println("Stock "+stock.getName()+": $"+stock.getPrice()+"; Changed: "+stock.percentChangeSinceStart()+"%");
		}
	}
	public void outputStats() {
		System.out.println("Min Total: " + this.minProfits);
		System.out.println("Max Total: " + this.maxProfits);
		System.out.println("Total: " + this.balance + "\n");
	}
	
	public void outputUpdate() {
		System.out.println("Total Assests Value: "+getTotalAssets()+"; % Change: "+(getTotalAssetsPercentChange()*100)+"%");
		outputStocksPercentChange();
	}
}
