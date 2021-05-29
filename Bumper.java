import java.awt.Color;
import java.awt.Graphics;

public class Bumper {

	private int x;
	private int y;
	private int height;
	private int width;
	private Color color;
	
	
	public Bumper(int aX, int aY, int aWidth, int aHeight, Color aColor) {
		x = aX;
		y = aY;
		height = aHeight;
		width = aWidth;
		color = aColor;
	}
	
	public Bumper() {
		x = 200;
		y = 200;
		height = 50;
		width = 50;
		color = Color.red;
	}
	
	
	public int getX() {
		return x;
	}
	
	public void setX(int temp) {
		x = temp;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int temp) {
		y = temp;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int temp) {
		height = temp;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int temp) {
		width = temp;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color temp) {
		color = temp;
	}
	
	
	/**
	 * Returns true if any part of the Ball is inside the bumper
	 * @param ball the Ball
	 * @return true if any part of the Ball is inside the bumper, false otherwise
	 */
	public boolean inBumper(Ball ball) {
		for (int x = getX() - getWidth()/2; x <= getX() + getWidth()/2; x++) {
			for (int y = getY() - getHeight()/2; y <= getY() + getHeight()/2; y++) {
				if (getDistance(x, y, ball.getX(), ball.getY()) <= ball.getRadius()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Calculates the distance between (x1, y1) and (x2, y2)
	 * @param x1 the x-coordinate of the first point
	 * @param y1 the y-coordinate of the first point
	 * @param x2 the x-coordinate of the second point
	 * @param y2 the y-coordinate of the second point
	 * @return the distance between (x1, y1) and (x2, y2)
	 */
	private double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	public void setLocation (int Ax, int Ay) {
		x = Ax;
		y = Ay;
	}
	
	public void draw (Graphics g) {
		g.setColor(color);
		g.fillRect((int)(x-(width/2)), (int)(y-(height/2)), width, height);
	}

	
	
}