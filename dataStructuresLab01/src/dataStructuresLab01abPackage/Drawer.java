package dataStructuresLab01abPackage;

public class Drawer {
	private static void drawLine(int n, char ch) {
		for(int i=0; i<n; i++) {
			System.out.print(ch);
		}
	}
	
	public static void drawPyramid(int n) {
		for(int i=1; i<2*n; i+=2) {
			for(int j = (2*n - i)/2; j>0; j--) {
				System.out.print(" ");
			}
			drawLine(i,'X');
			System.out.print("\n");
		}
	}
	
	public static void drawChristmassTree(int n) {
		int m = 2;
		for(int i=1; i<=n; i++) {
			int k = 1;
			while(k < m) {
				for(int l = (2*n - k)/2; l>0; l--) {
					System.out.print(" ");
				}
				drawLine(k,'X');
				System.out.print("\n");
				k += 2;
			}
			m += 2;
		}
	}
}