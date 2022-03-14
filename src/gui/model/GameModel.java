package gui.model;

import java.util.Random;

import javax.swing.JOptionPane;

import java.util.Observable;

public class GameModel extends Observable {
	//rows
	public int height = 10;
	//columns
	public int width = 10;
	//amount of bombs
	private int bombsAmount = 15;
	//amount of used flags
	private int bombCounter = 0;
	//generated field
	private int[][] field;
	//field modified during the game
	private int[][] curField;
	
	
	public GameModel() {
		field = new int[width][height];
		generateMap();
		createCurField();	
		printMap();
	}
	
	//resets the game model
	public void reset() {
		bombCounter = 0;
		generateMap();
		createCurField();
		printMap();
	}
	
	//increase BombCounter and update label
	public void increaseBombCounter() {
		bombCounter++;
		this.setChanged();
		this.notifyObservers();
	}
	
	//decrease BombCounter and update label
	public boolean decreaseBombCounter() {
		if(bombCounter > 0) {
		bombCounter--;
		this.setChanged();
		this.notifyObservers();		
		}
		else
		{
		 return false;	
		}
		return true;
	}
	
	//generate new field, empty spaces, randomized bombs, digits
	private void generateMap() {
		
		//empty field
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				field[i][j] = 0;
			}
		}
		
		//bombs
		Random rand = new Random();
		int x;
		int y;
		while(bombCounter < bombsAmount)
		{
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			if(field[x][y] != -1) {
				field[x][y] = -1;
				bombCounter++;
			}				
		}
		
		//digits
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(field[i][j] != -1)//if not bomb
				field[i][j] = checkNeighbours(i, j);
			}
		}
			
	}
	
	//funtciont counting amount of bombs around a field
	private int checkNeighbours(int x, int y) {
		int neighbours = 0;
		
		//not first column
		if(x != 0) {
			//left
			if(field[x - 1][y] == -1) {
				neighbours++;
			}
			
			//not first row
			if(y != 0) {
				//left up
				if(field[x - 1][y - 1] == -1) {
					neighbours++;
				}
			}
			
			
			//not first row
			if(y != width - 1) {
				//left bottom
				if(field[x - 1][y + 1] == -1) {
					neighbours++;
				}
			}
		}
		
		//not last column
		if(x != width - 1) {
			//right
			if(field[x + 1][y] == -1) {
				neighbours++;
			}
			
			//not first row
			if(y != 0) {
				//right up
				if(field[x + 1][y - 1] == -1) {
					neighbours++;
				}
			}
			
			//not last row
			if(y != height - 1) { 
				//right bottom
				if(field[x + 1][y + 1] == -1) {
					neighbours++;
				}
			}
		}

		
		//not last row
		if(y != height - 1) {
			//bottom
			if(field[x][y + 1] == -1) {
				neighbours++;
			}
		}
		
		 //not first row
		if(y != 0) {
			//up
			if(field[x][y - 1] == -1) {
				neighbours++;
			}
		}
		
	
		

		
		return neighbours;
	}
	
	
	//prints the field
	private void printMap()
	{
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				System.out.print(field[j][i] + " | ");
			}
			System.out.println(" ");
		}
	}
	
	//copies values of the starting field to the current field 
	private void createCurField()
	{
		curField = new int[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				curField[i][j] = field[i][j] ;
			}
		}
	}
	
	//checks if the game has been won
	public boolean isVictory() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(curField[i][j] == -1)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	//getters
	
	public int[][] getField(){
		return field;
	}
	
	public int[][] getCurField(){
		return curField;
	}
	
	public int getBombCounter(){
		return bombCounter;
	}
	
	public int getBombsAmount(){
		return bombsAmount;
	}
	
}
