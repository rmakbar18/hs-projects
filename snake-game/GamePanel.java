package Snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{

	//These are all the constants and what we need to set up the methods.
	
	static final int SCREEN_WIDTH = 400;
	static final int SCREEN_HEIGHT = 400;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 80;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 3;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;


	
	GamePanel(){ 
		random = new Random(); // This instance of the random class will be used for the apple's coordinates
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	public void startGame() {
		newApple(); 
		running = true;
		
		// Higher delay = slower snake. The timer is used to decide how long it takes for the snake to move to the next position.
		timer = new Timer(DELAY,this);
		timer.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		if(running==true) {
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				//This is used to draw the grid lines shown on the frame. 
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			
			}
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
		
			for(int i=0;i<bodyParts;i++) {
				//i = 0, as it is the first iteration, and will draw in the head of the snake. 
				if(i==0){
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
					//These will draw the body of the snake on subsequent iterations.
					g.setColor(new Color(45,180,0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free",Font.BOLD,40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
		}
		else {
			gameOver(g);
		}
	}
	public void newApple() {
		//Makes the apples appear on the frame. 
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

	}
	public void move() {
		//This is to update the body parts of the snake. Without the -1, the snake head will move on its own without body parts. 
		for(int i=bodyParts;i>0;i--) {
			x[i]= x[i-1];
			y[i]=y[i-1];
		}
		//This is to update either the x or y coordinate value based on what direction the snake is moving and how far.
		switch(direction) {
		case 'U':
			y[0]=y[0]-UNIT_SIZE;
			break;
		case 'D':
			y[0]=y[0]+UNIT_SIZE;
			break;
		case 'L':
			x[0]=x[0]-UNIT_SIZE;
			break;
		case 'R':
			x[0]=x[0]+UNIT_SIZE;
			break;
		}
	}
	public void checkApple() {
		//This is to check if the snake head ate the apple. Body parts and apples eaten will increment by 1, and a new apple is generated.
		if((x[0]==appleX) && (y[0]==appleY)){
			bodyParts++;
			applesEaten++;
			newApple();
			
			
		}
	}
	public void checkCollisions() {
		// checks if head collides with body
		for(int i=bodyParts;i>0;i--) {
			if((x[0]==x[i])&& (y[0]==y[i])) {
				running = false;
			}	
		}
		//check if head touches left border
		if(x[0]<0) {
			running = false;
		}
		//check if head touches right border
		if(x[0]>SCREEN_WIDTH) {
			running = false;
		}
		//check if head touches top border
		if(y[0]<0) {
			running = false;
		}
		//check if head touches bottom border
		if(y[0]>SCREEN_HEIGHT) {
			running = false;
		}
		
		if(!running) {
			timer.stop();
		}
	}
	public void gameOver(Graphics g) {
		//Score
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());;
		//Game over text
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCollisions();
			
		}
		//The repaint method is used to make changes to the frame. If it was not there, the GUI would display, but nothing would happen.
		repaint();
		
	}



	public class MyKeyAdapter extends KeyAdapter{
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		//This is to prevent the user from trying to move the opposite way without changing directions first (like trying to move up while facing down).
		case KeyEvent.VK_LEFT:
			if(direction!='R') {
				direction = 'L';
				
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(direction!='L') {
				direction = 'R';
				
			}
			break;
		case KeyEvent.VK_UP:
			if(direction!='D') {
				direction = 'U';
				
			}
			break;
		case KeyEvent.VK_DOWN:
			if(direction!='U') {
				direction = 'D';
				
			}
			break;
		}
	}
	}



}









