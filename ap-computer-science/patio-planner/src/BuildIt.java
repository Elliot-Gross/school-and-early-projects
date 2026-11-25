import java.util.Scanner;

public class BuildIt {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		double width = Double.parseDouble(input.nextLine());
		double length = Double.parseDouble(input.nextLine());

		double widthHeight = Double.parseDouble(input.nextLine());
		
		double paverWidth = Double.parseDouble(input.nextLine());
		double paverLength = Double.parseDouble(input.nextLine());
		
		double[][][] observedHeights = new double[(int) (length/paverLength)][(int) (width/paverWidth)][2];
		for(int i=0; i<observedHeights[0].length; i++) {
			for(int j=0; j<observedHeights.length; j++) {
				String[] line = input.nextLine().split(",");
				observedHeights[j][i][0] = Double.parseDouble(line[0]);
				observedHeights[j][i][1] = Double.parseDouble(line[1]);
			}
		
		}

		
		Patio observed = new Patio(widthHeight, width, length, paverWidth, paverLength, observedHeights);
		
		observed.getExpected().printPatio();
		observed.printPatio();
		observed.printErrors();
		
	}
	
}