
public class Patio extends RectangularObject{

	private RectangularObject[][] pavers;
	
	private int[][] errors;
	private Patio expected;
	
	private double paverWidth;
	private double paverLength;
	
	//For Expected Patio
	public Patio(double widthHeight, double width, double length, double paverWidth, double paverLength) {
		super(widthHeight, width, length);
		
		this.paverWidth = paverWidth;
		this.paverLength = paverLength;
		
		pavers = new RectangularObject[(int) (length/paverLength)][(int) (width/paverWidth)];	
		populatePavers();
	}	
	
	//For Observed Patio
	public Patio(double widthHeight, double width, double length, double paverWidth, double paverLength, double[][][] paverHeights) {
		super(widthHeight, width, length);
		
		this.paverWidth = paverWidth;
		this.paverLength = paverLength;
		
		pavers = new RectangularObject[(int) (length/paverLength)][(int) (width/paverWidth)];	
		populatePavers(paverHeights);
		
		expected = new Patio(widthHeight, width, length, paverWidth, paverLength);
		calculatePercentError(expected.pavers);
		
		errors = getMineSweeperGrid();
	}
	
	// For Observed Patio with set heights
	public void populatePavers(double[][][] paverHeights) {
		for(int i=0; i<pavers.length; i++) { 
			for(int j=0; j<pavers[i].length; j++) {
				pavers[i][j] = new RectangularObject(paverHeights[i][j][0], paverHeights[i][j][1], paverWidth, paverLength);
			}
		}
	}
	
	public void calculatePercentError(RectangularObject[][] expectedPatio) {
		for(int i=0; i<pavers.length; i++) { 
			for(int j=0; j<pavers[i].length; j++) {
				pavers[i][j].setPercentError(expectedPatio[i][j]);
			}
		}
	}
	
	//For Expected Patio
	public void populatePavers() {
		for(int i=0; i<pavers.length; i++) { 
			for(int j=0; j<pavers[i].length; j++) {
				double paverWidthHeight = calcBottomHeight(this.getTopHeight(), paverLength*i); //Find Bottom Height of Previous Paver
				pavers[i][j] = new RectangularObject(paverWidthHeight, paverWidth, paverLength);
			}
		}
	}
	
	public int[][] getMineSweeperGrid() {
		RectangularObject[][] pavers = this.getPavers();
		int[][] grid = new int[pavers.length][pavers[0].length];
		
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = getAdjacentErrorCount(pavers, i, j);
			}
		}
		
		return grid;
	}
	
	public static int getAdjacentErrorCount(RectangularObject[][] pavers, int i, int j) {
		double topHeightPercentError = pavers[i][j].getTopHeightPercentError();
		double bottomHeightPercentError = pavers[i][j].getBottomHeightPercentError();
		
		int gridCount = 0;
		if(Math.abs(topHeightPercentError) >= 5 || Math.abs(bottomHeightPercentError) >= 5) {
			for(int x=i-1; x<i+2; x++) {
				for(int y=j-1; y<j+2; y++) {
					if((x >= 0 && x < pavers.length) && (y >= 0 && y < pavers[0].length)) {
						topHeightPercentError = pavers[x][y].getTopHeightPercentError();
						bottomHeightPercentError = pavers[x][y].getBottomHeightPercentError();
						if(Math.abs(topHeightPercentError) >= 5 || Math.abs(bottomHeightPercentError) >= 5) {
							gridCount++;
						}
					}
				}
			}
		}
		
		return gridCount;
	}
	
	public void printPatio() {
		for (RectangularObject[] row : pavers) {
			for (RectangularObject paver : row) {
				System.out.print(Math.round(paver.getTopHeight() * 1000) / 1000.0 + "," 
						+ Math.round(paver.getBottomHeight() * 1000) / 1000.0 + "    \t");
			}
			System.out.println("\n");
		}
	}
	
	public void printPatioPercentError() {
		for (RectangularObject[] row : pavers) {
			for (RectangularObject paver : row) {
				System.out.print(Math.round(paver.getTopHeightPercentError() * 1000) / 1000.0 + "," 
						+ Math.round(paver.getBottomHeightPercentError() * 1000) / 1000.0 + "    \t");
			}
			System.out.println("\n");
		}
	}
	
	public RectangularObject[][] getPavers() {
		return pavers;
	}
	public Patio getExpected() {
		return expected;
	}
	
	public void printErrors() {
		if (errors == null)
			System.out.println();
		else {
			for (int[] r : errors) {
				for (int c : r)
					System.out.print(c + "\t");
				System.out.println("\n");
			}
		}
	}


}
