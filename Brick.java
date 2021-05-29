import java.awt.Color;

public class Brick extends Bumper {
	
	private int hits;
	
	public Brick (int aX, int aY, int aWidth, int aHeight, Color aColor) {
		super(aX, aY, aWidth, aHeight, aColor); 
			
	}
	
	public void setHits(int tmp) {
		if (tmp >= 7) {
			hits = 7;
			setColor(Color.black);
		}
		else if (tmp == 6) {
			hits = tmp;
			setColor(Color.gray);
		}
		else if (tmp == 5) {
			hits = tmp;
			setColor(Color.green.darker());
		}
		else if (tmp == 4) {
			hits = tmp;
			setColor(Color.green);
		}
		else if (tmp == 3) {
			hits = tmp;
			setColor(Color.yellow);
		}
		else if (tmp == 2) {
			hits = tmp;
			setColor(Color.orange);
		}
		else if (tmp == 1) {
			hits = tmp;
			setColor(Color.red);
		}
		else {
			hits = 0;
		}
	}
		
	public int getHits() {
		return hits;
	}
	
}
