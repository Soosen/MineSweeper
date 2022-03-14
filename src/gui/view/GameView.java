package gui.view;

import java.awt.Font;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.controller.GameController;
import gui.model.GameModel;

public class GameView extends JPanel implements Observer {

	//rows
	public int width;
	//columns
	public int height;
	
	private GameModel model;
	//buttons in an array representing a 2d board
	private JButton[][] fields;
	
	//labels (images) in an array covered by buttons at the beginning of the game
	private JLabel[][] images;
	//reset button
	public JButton reset;
	//label showing amount of bombs to flag
	private JLabel bombsLeft; 
	//project path
	private String projectPath;
	
	
	public GameView(GameModel model)
	{
		this.width = model.width;
		this.height = model.height;
		this.model = model;
		this.model.addObserver(this);
		this.setLayout(null);
		this.projectPath = System.getProperty("user.dir");
		setLabels();
		setButtons();
		drawImages();		
	}

	//reset view function
	public void reset() {
		
		removeAll();
		revalidate();
		repaint();
		setLabels();
		setButtons();
		drawImages();	
	}
	
	//sets a grid of buttons
	private void setButtons() {
		//Reset button
		reset = new JButton("Reset");
		reset.setSize(80, 30);
		reset.setLocation(448, 5);
		reset.addMouseListener(new GameController(this.model, this));
		this.add(reset);
		
		//Field butotns
		fields = new JButton[width][height];
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++ ) {
				fields[i][j] = new JButton(" ");
				fields[i][j].setSize(50, 50);
				fields[i][j].setLocation(10 + 50 * i + 2 * i, 45 + 50 * j + 2 * j);
				fields[i][j].addMouseListener(new GameController(this.model, this));
				this.add(fields[i][j]);
			}
		}
	}
	
	//sets the bombsLeft label
	private void setLabels()
	{
		bombsLeft = new JLabel("Bombs Left: " + model.getBombCounter());
		bombsLeft.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		bombsLeft.setSize(100, 15);
		bombsLeft.setLocation(15, 5);
		this.add(bombsLeft);
	}
	
	//draws images representing a field under the buttons
	private void drawImages() {
		images = new JLabel[width][height];
		ImageIcon Img;
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				
				switch(model.getField()[i][j]) {
				case -1:
				Img = new ImageIcon(projectPath + "/images/bomb.jpg");
				images[i][j] = new JLabel(Img);
				images[i][j].setSize(50, 50);
				images[i][j].setLocation(10 + 50 * i + 2 * i, 45 + 50 * j + 2 * j);	
				this.add(images[i][j]);
					break;
	
				case 1:
					Img = new ImageIcon(projectPath + "/images/1.jpg");
					images[i][j] = new JLabel(Img);
					images[i][j].setSize(50, 50);
					images[i][j].setLocation(10 + 50 * i + 2 * i, 45 + 50 * j + 2 * j);	
					this.add(images[i][j]);
					break;
					
				case 2:
					Img = new ImageIcon(projectPath + "/images/2.jpg");
					images[i][j] = new JLabel(Img);
					images[i][j].setSize(50, 50);
					images[i][j].setLocation(10 + 50 * i + 2 * i, 45 + 50 * j + 2 * j);	
					this.add(images[i][j]);
					break;
					
				case 3:
					Img = new ImageIcon(projectPath + "/images/3.jpg");
					images[i][j] = new JLabel(Img);
					images[i][j].setSize(50, 50);
					images[i][j].setLocation(10 + 50 * i + 2 * i, 45 + 50 * j + 2 * j);	
					this.add(images[i][j]);
					break;
					
				case 4:
					Img = new ImageIcon(projectPath + "/images/4.jpg");
					images[i][j] = new JLabel(Img);
					images[i][j].setSize(50, 50);
					images[i][j].setLocation(10 + 50 * i + 2 * i, 45 + 50 * j + 2 * j);	
					this.add(images[i][j]);
					break;
					
				case 5:
					Img = new ImageIcon(projectPath + "/images/5.jpg");
					images[i][j] = new JLabel(Img);
					images[i][j].setSize(50, 50);
					images[i][j].setLocation(10 + 50 * i + 2 * i, 45 + 50 * j + 2 * j);	
					this.add(images[i][j]);
					break;
					
				case 6:
					Img = new ImageIcon(projectPath + "/images/6.jpg");
					images[i][j] = new JLabel(Img);
					images[i][j].setSize(50, 50);
					images[i][j].setLocation(10 + 50 * i + 2 * i, 45 + 50 * j + 2 * j);	
					this.add(images[i][j]);
					break;
					
				case 7:
					Img = new ImageIcon(projectPath + "/images/7.jpg");
					images[i][j] = new JLabel(Img);
					images[i][j].setSize(50, 50);
					images[i][j].setLocation(10 + 50 * i + 2 * i, 45 + 50 * j + 2 * j);	
					this.add(images[i][j]);
					break;
					
				case 8:
					Img = new ImageIcon(projectPath + "/images/8.jpg");
					images[i][j] = new JLabel(Img);
					images[i][j].setSize(50, 50);
					images[i][j].setLocation(10 + 50 * i + 2 * i, 45 + 50 * j + 2 * j);	
					this.add(images[i][j]);
					break;			
				}
				
			}
		}
	}
	
	//returns the x and y position of the pressed button
	public int[] getIndex(JButton JB) {
		int[] ret = new int[2];
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++ ) {
				if(JB == fields[i][j]) {
					ret[0] = i;
					ret[1] = j;
					return ret;
				}
			}
		}
		return new int[2];
	}
	
	//shows all empty spaces near the clicked button, that are not bordered by digits or bombs
	public void hideNeigbourIfEmpty(int x, int y) {
		if(model.getCurField()[x][y] == 0) {
			if(fields[x][y].isVisible())
			{
				fields[x][y].hide();
				if(x !=  height - 1)
				hideNeigbourIfEmpty(x + 1, y);
				if(x != 0)
				hideNeigbourIfEmpty(x - 1, y);
				if(y != height - 1)
				hideNeigbourIfEmpty(x, y + 1);
				if(y != 0)
				hideNeigbourIfEmpty(x, y -1);
			}
		}
	}
	
	//hides all buttons to show the entire field
	public void hideAllButtons() {
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++ ) {
				fields[i][j].hide();
			}
		}
	}
	
	//changes the normal bombs image to exploded bombs
	public void explodeBombs() {
		ImageIcon Img;
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++ ) {
				if(model.getField()[i][j] == -1) {
					remove(images[i][j]);
					Img = new ImageIcon(projectPath + "/images/explosion.jpg");
					images[i][j] = new JLabel(Img);
					images[i][j].setSize(50, 50);
					images[i][j].setLocation(10 + 50 * i + 2 * i, 45 + 50 * j + 2 * j);	
					this.add(images[i][j]);
				}
			}
		}
		revalidate();
		repaint();
	}
	
	//check if the amount of the ramaining buttons == bombsAmount
	public boolean isVictory() {
		int remainingButtons = 0;
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(fields[i][j].isVisible())
				{
					remainingButtons++;
				}
			}
		}		
		return remainingButtons == model.getBombsAmount();
	}
	
	//updates label bombs left
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		bombsLeft.setText("Bombs Left: " + model.getBombCounter());
		
	}
	
	public void playSound(String path) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
		ex.printStackTrace();
		}
		}
	
}
