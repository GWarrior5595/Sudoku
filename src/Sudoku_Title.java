/**Term Group Project - SUDOKU
 * COSC 1320 - Computer Science II: Title
 * Created by: Erphun Ranjhar, Jenny Tran, Joseph Eldridge, Alejandro Sifuentes
 * @Copyright 2014. 
 */
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Sudoku_Title extends JFrame implements ActionListener
{
	public static final int Width = 605;			//defining variables. Width and Height of Window
	public static final int Height = 605;
	
	public static void main(String[] args) 
	{
		Sudoku_Title image = new Sudoku_Title();
		image.setVisible(true);
	}
	public Sudoku_Title()
	{
		super("Sudoku!");
		setSize(Width, Height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new JLabel(new ImageIcon("title1.jpg")));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		JButton startButton = new JButton("Start");					//Button Start
        startButton.setFont(new Font("Baskerville Old Face", Font.BOLD, 32));
        startButton.setForeground(Color.WHITE);
        startButton.setBorderPainted(false);  							//methods of JButton to hide the button itself
        startButton.addActionListener(this);									//displaying only text
        startButton.setContentAreaFilled(false); 
        startButton.setFocusPainted(false); 
        startButton.setOpaque(false);
        add(startButton, BorderLayout.SOUTH);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		String actionCommand = e.getActionCommand();				//Action Command for Button Start
		if(actionCommand.equals("Start"))
		{
			setVisible(false);
	    	Sudoku_Menu menu = new Sudoku_Menu();			//close wondow and goes to Menu
	    	menu.setVisible(true);
		}
		else
			System.out.println("ERROR: Unexpected Error has occured.");
	}
}
