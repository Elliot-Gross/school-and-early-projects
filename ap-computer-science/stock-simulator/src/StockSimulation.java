
public class StockSimulation {
	
	public static void main(String args[]) {
		
		Portfolio portfolio = new Portfolio(1000);
		
		portfolio.buyStock("TSLA",20,1000/3/20,"Normal");
		portfolio.buyStock("AMD",15,1000/3/15,"Normal");
		portfolio.buyStock("LULU",20,1000/3/20,"Normal");
		portfolio.buyPennyStock("P",0.01,"Penny");
		
		runSimulation(253, portfolio);
		
		System.out.println("Simulation Done\n");
		portfolio.outputStats();
		
	}
	
	public static void runSimulation(int days, Portfolio portfolio) {
		boolean hasCrashed = false;
		
		for(int i = 0; i<days; i++) {
			portfolio.handleStocks();
			portfolio.updateRecord();
			
			if(checkForCrash()) {
				System.out.println("Market Crashed");
				hasCrashed = true;
				break;
			}
			
			if(i==62 || i==125 || i==188 || i==252) {
				System.out.println("-------------");
				System.out.println("Day "+(i+1)+" Update:");
				portfolio.outputUpdate();
				System.out.println("-------------");
			}
		}
		
		if(!hasCrashed) {
			portfolio.sellAllStocks();
		}
		
	}
	
	public static boolean checkForCrash() {
		return Math.floor(Math.random()*12*253) == 1;
	}

}
 