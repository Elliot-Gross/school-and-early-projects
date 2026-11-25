
public class RectangularObject {

	private double topHeightPercentError;
	private double bottomHeightPercentError;
	
	private double topHeight;
	private double bottomHeight;
	
	private double width;
	private double length;
	
	//For Expected Objects - Automatic percent error of 0
	public RectangularObject(double widthHeight, double width, double length) { 
		this.topHeight = widthHeight;
		this.bottomHeight = calcBottomHeight(topHeight, length);
		
		this.width = width;
		this.length = length;
		
		this.topHeightPercentError = 0;
		this.bottomHeightPercentError = 0;
	}
	
	//For Observed Object - percent error must be set with another rectangular object
	public RectangularObject(double widthHeight, double bottomHeight, double width, double length) {
		this.topHeight = widthHeight;
		this.bottomHeight = bottomHeight;
		
		this.width = width;
		this.length = length;
		
		this.topHeightPercentError = 0;
		this.bottomHeightPercentError = 0;
	}
	
	
	public static double calcBottomHeight(double topHeight, double length) {
		return topHeight - (length/12.0)*(0.125);
	}
	
	public void setPercentError(RectangularObject expectedObject) {
		this.topHeightPercentError = 100*((this.topHeight - expectedObject.topHeight)/expectedObject.topHeight);
		this.bottomHeightPercentError = 100*((this.bottomHeight - expectedObject.bottomHeight)/expectedObject.bottomHeight);
	}
	
	public double getTopHeight() {
		return topHeight;
	}
	public double getBottomHeight() {
		return bottomHeight;
	}
	public double getTopHeightPercentError() {
		return topHeightPercentError;
	}
	public double getBottomHeightPercentError() {
		return bottomHeightPercentError;
	}
	
	public void printStats() {
		System.out.println("Top Height: " + topHeight);
		System.out.println("Bottom Height: " + bottomHeight);
		
		System.out.println("Width: " + width);
		System.out.println("Length: " + length);
	}
}
