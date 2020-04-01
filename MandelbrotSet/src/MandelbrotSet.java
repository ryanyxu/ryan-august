import java.awt.*;
import javax.swing.*;


public class MandelbrotSet extends JFrame{
	
	public static final int CANVAS_WIDTH = 1600;
	public static final int CANVAS_HEIGHT = 1200;
	public static final int X_MIN = -3;
	public static final int X_MAX = 3;
	public static final int Y_MIN = -2;
	public static final int Y_MAX = 2;
	
	public MandelbrotSet() throws HeadlessException {
		setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(new DrawArea());
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MandelbrotSet();
	}
		
}

class DrawArea extends JPanel {
	
	private int blowUpValue;
	private int iterations;
	
	public DrawArea() {
		blowUpValue = 30;
		iterations = 30;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		for (int i = 0; i <= MandelbrotSet.CANVAS_WIDTH; i++) {
			for (int j = 0; j <= MandelbrotSet.CANVAS_HEIGHT; j++) {
				double brightness = map(
						blowUpFactor(
								map(
										i, 0, MandelbrotSet.CANVAS_WIDTH, 
										MandelbrotSet.X_MIN, MandelbrotSet.X_MAX
								), 
								map(
										j, 0, MandelbrotSet.CANVAS_HEIGHT, MandelbrotSet.Y_MIN, 
										MandelbrotSet.Y_MAX
								)
						), 
						iterations, 0, 0, 1);
				g.setColor(Color.getHSBColor(0, (float) 0, (float) brightness));
				g.drawLine(i, j, i, j);
			}
		}
	}
	
	private int blowUpFactor(double i, double j) {
		int count = 0;
		double a = 0;
		double b = 0;
		for (int k = 0; k < iterations; k++) {
			if (magnitude(a, b) > blowUpValue) {
				return count;
			} else {
				a = (a*a) - (b*b) + i;
				b = (2*a*b) + j;
				count++;
			}
		}
		return count;
	}
	
	private double magnitude(double a, double b) {
		return Math.sqrt(a*a + b*b);
	}
	
	private double map(double val, double oMin, double oMax, double fMin, double fMax) {
		return (((val - oMin) / (oMax - oMin)) * (fMax - fMin)) + fMin; 
	}
}
