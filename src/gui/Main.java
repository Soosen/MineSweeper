package gui;

import java.awt.Color;

import javax.swing.JFrame;

import gui.model.*;
import gui.view.*;

public class Main {

	
	public static void main (String[] args) {
		//create a model
		GameModel model = new GameModel();
		
		//create a window called Mine Sweeper
		JFrame frame = new JFrame("Mine Sweeper");
		
		//create a view
		GameView view = new GameView(model);
		
		//set view to the content pane of the frame
		frame.setContentPane(view);
		frame.pack();
		
		//set size of the window
		frame.setSize(55 + view.width * 50, 110 + view.height * 50);
		
		//make the window not resizable
		frame.setResizable(false);
		
		//set title of the window to Mine Sweeper
		frame.setTitle("Mine Sweeper");
		
		//make the background color white
		frame.getContentPane().setBackground(Color.WHITE);
		
		//exit the program as default close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//center the window
		frame.setLocationRelativeTo(null);
		
		//show the window
		frame.setVisible(true);
	}
}

