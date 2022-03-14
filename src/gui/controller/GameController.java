package gui.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.model.*;
import gui.view.GameView;

public class GameController extends MouseAdapter {
	
	private GameModel model;
	private GameView view;
	private String projectPath;
	
	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
		this.projectPath = System.getProperty("user.dir");
	}

	//Mouse clicked event
	@Override
	public void mouseClicked(MouseEvent e) {
		//prepare a reference for a sender (button)
		JButton temp;
		
		//if sender == reset button then reset
		if((JButton) e.getSource() == view.reset)
		{
			model.reset();
			view.reset();
		}
		else
		{
			//variable for x and y position of the button on the grid
			int[] index;
			
			//switch case according to the button pressed, either left or right
			switch(e.getButton()) {
			case MouseEvent.BUTTON1: //left
				
				//save sender in temp
				temp = (JButton) e.getSource();
				
				//get x and y position of the sender (button)
				index = view.getIndex(temp);
				
				//if the button is not flagged
				if(model.getCurField()[index[0]][index[1]] != -2) {
					
					//if the button covers an empty space
					if(model.getCurField()[index[0]][index[1]] == 0)
					{
						//hide the button and reveal all empty spacec near by
						view.hideNeigbourIfEmpty(index[0], index[1]);
					}
					//else if the button covers a bomb
					else if(model.getCurField()[index[0]][index[1]] == -1) {
						
						//hide all buttons
						view.hideAllButtons();
						//change bombs to exploded bombs
						view.explodeBombs();
						//play explosion sound
						view.playSound(projectPath + "/sounds/explosion.wav");
						
						//pop yes/no window saying that you lost
						int result = JOptionPane.showConfirmDialog(null, "You lost. Do you want to try again?", "Defeat", 0);
						
						//if yes pressed reset the game
						if(result == 0)
						{
							model.reset();
							view.reset();
						}
						
						//if no pressed or closed then exit
						else
						{
							System.exit(0);
						}
						
						//if there is a digit under the button just hide the clicked button
					}else {
						temp.hide();
					}
					//check if the game has been won
					if(model.isVictory() && view.isVictory()) {
						//play victory sound
						view.playSound(projectPath + "/sounds/victory.wav");
						//if won show pop up window saying congratulations
						JOptionPane.showMessageDialog(null, "Congratulations!");
						
						//reset the game
						model.reset();
						view.reset();
					}
				}
				break;
				
				//right button pressed
			case MouseEvent.BUTTON3: //right
				
				//save the sender in temp
				temp = (JButton) e.getSource();
				
				//find senders x and y
				index = view.getIndex(temp);
				
				//if sender is not flagged
				if(model.getCurField()[index[0]][index[1]] != -2) {
					//if bombCounter > 0 decrease it and put a flag
					if(model.decreaseBombCounter()) {
						//flag the button
						ImageIcon Img = new ImageIcon(projectPath + "/images/buttonwithflag.jpg");
						temp.setIcon(Img);
						
						//save changes in the current field
						model.getCurField()[index[0]][index[1]] = -2;
						
						
						
						//check if the game has been won
						if(model.isVictory() && view.isVictory()) {
							//play victory sound
							view.playSound(projectPath + "/sounds/victory.wav");
							//if won show pop up window saying congratulations
							JOptionPane.showMessageDialog(null, "Congratulations!");
							
							//reset the game
							model.reset();
							view.reset();
						}
					}
				}
				//if the button is flagged
				else
				{
					//remove the flag
					ImageIcon Img = new ImageIcon(projectPath + "/images/button.jpg");
					temp.setIcon(Img);
					
					//save changes in the current field
					model.getCurField()[index[0]][index[1]] = model.getField()[index[0]][index[1]];
					
					//increase the bombCounter
					model.increaseBombCounter();
				}
				
				break;
			}
		
		}
	}
}
