import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BrickBreakerUltimate extends JPanel {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 700;
	
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	public static final String[] choices = {"Easy", "Medium", "Hard"};
	private int lives = 3;
	private int brickHits = 0;
	
	// declare stuff
	private Ball ball;
	private ArrayList<Brick> bricks = new ArrayList<Brick>();
	private Bumper movingBumper;
	
	private String difficulty;
	
	
	public BrickBreakerUltimate() {
		
		difficulty = (String)JOptionPane.showInputDialog(null,"Choose an difficulty.", "Choices", JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
		
		
		
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		
		// instantiate ball and bricks
		ball = new Ball(400, 400, 80, Color.BLACK);
		
		movingBumper = new Bumper(WIDTH/2, 550, 100, 30, Color.black);
		
		for (int i = 0; i < 4; i++) {
			bricks.add(i, new Brick(i*200+100, 50, 100, 30, Color.red));
		}
		
		for (int i = 4; i < 8; i++) {
			bricks.add(i, new Brick(bricks.get(i-4).getX(), 125, 100, 30, Color.red));
		}
		
		for (int i = 8; i < 12; i++) {
			bricks.add(i, new Brick(bricks.get(i-4).getX(), 200, 100, 30, Color.red));
		}
		
		if (difficulty.equals(choices[0])) {
			ball.setXSpeed(4);
			ball.setYSpeed(-4);
			for (int i = 0; i < bricks.size(); i++) {
				bricks.get(i).setHits(3);
			}
		}
		
		if (difficulty.equals(choices[1])) {
			ball.setXSpeed(5);
			ball.setYSpeed(-5);
			for (int i = 0; i < bricks.size(); i++) {
				bricks.get(i).setHits(5);
			}
		}
		
		if (difficulty.equals(choices[2])) {
			ball.setXSpeed(6);
			ball.setYSpeed(-6);
			for (int i = 0; i < bricks.size(); i++) {
				bricks.get(i).setHits(7);
			}
		}
		
		timer = new Timer(5, new TimerListener());
		timer.start();
		
		addKeyListener(new Keyboard());
		setFocusable(true);

	}
	
	private class Keyboard implements KeyListener {
		//dont use key typed
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_A) {
				if (movingBumper.getX() > movingBumper.getWidth()) {
					movingBumper.setX(movingBumper.getX() - 25);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				if (movingBumper.getX() < WIDTH - movingBumper.getWidth() - 15) {
					movingBumper.setX(movingBumper.getX() + 25);
				}
			}
			
			if (e.getKeyCode() == KeyEvent.VK_S) {
				if (difficulty.equals(choices[0])) {
					ball.setYSpeed(-4);
				}
				if (difficulty.equals(choices[1])) {
					ball.setYSpeed(-5);
				}
				if (difficulty.equals(choices[2])) {
					ball.setYSpeed(-6);
				}
			}
			

			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				timer.stop();
			}
			if (e.getKeyCode() == KeyEvent.VK_Z) {
				timer.start();
			}
			
			
		}
		
	}
	
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			g.setColor(Color.blue.brighter());
			g.fillRect(0, 0, WIDTH, HEIGHT);

			ball.move(WIDTH, HEIGHT);
			
			
			ball.draw(g);
			movingBumper.draw(g);
			for (int i = 0; i < bricks.size(); i++) {
				bricks.get(i).draw(g);
			}
			
			for (int i = 0; i < bricks.size(); i++) {
				if (bricks.get(i).inBumper(ball)) {
					BumperCollision.collide(bricks.get(i), ball);
					brickHits++;
					bricks.get(i).setHits(bricks.get(i).getHits()-1);
					
				}
			}
			
			for (int i = 0; i < bricks.size(); i++) {
				if (bricks.get(i).getHits() == 0) {
					bricks.remove(bricks.get(i));
				}
			}
				
			if (movingBumper.inBumper(ball)) {
				BumperCollision.collide(movingBumper, ball);
				
			}
			
			
			
			if (ball.getY() + ball.getRadius() >= HEIGHT) {
				ball.setX(400);
				ball.setY(400);
				
				if (difficulty.equals(choices[0])) {
					ball.setXSpeed(4);
					ball.setYSpeed(-4);
				}
				
				if (difficulty.equals(choices[1])) {
					ball.setXSpeed(5);
					ball.setYSpeed(-5);
				}
				
				if (difficulty.equals(choices[2])) {
					ball.setXSpeed(6);
					ball.setYSpeed(-6);
				}
				timer.stop();
				lives--;
			}
			
			g.setFont(new Font("Times New Roman", Font.BOLD, 50));
			
			g.drawString("Lives: " + lives, 550, 525);
			g.drawString("Score: " + brickHits*lives, 550, 600);
			
			if (lives < 1) {
				g.drawString("You Lose", 325, 400);
				timer.stop();
				
			}
			if (bricks.size() < 1) {
				g.drawString("You Win", 325, 400);
				timer.stop();
				
			}
			
			
			repaint();
			
			
		}
		
	}
	
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Brick Breaker Ultimate");
		frame.setSize(WIDTH + 18, HEIGHT + 47);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new BrickBreakerUltimate());
		frame.setVisible(true);
	}
}