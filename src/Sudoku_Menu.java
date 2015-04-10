/**Term Group Project - SUDOKU
 * COSC 1320 - Computer Science II: Menu Display
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
import java.awt.GridBagConstraints;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Sudoku_Menu extends JFrame implements ActionListener
{
	public static final int Width = 605;			//defining variables. Width and Height of Window
	public static final int Height = 605;
	
	public Sudoku_Menu()
	{
		super("Sudoku Menu");
		setSize(Width, Height);
		setBackground(Color.BLACK);
		setContentPane(new JLabel(new ImageIcon("title1_Menu.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagConstraints gbc = new GridBagConstraints();			//GridBagLayout aligns the component(buttons) vertically/horizontally along their baseline
		setLayout(new GridBagLayout());									//compose of rectangular grid cells
		
		
		JButton easyButton = new JButton("Easy");
		easyButton.setFont(new Font("Baskerville Old Face", Font.BOLD, 32));		//Easy Button
		easyButton.setForeground(Color.WHITE);
		easyButton.setBorderPainted(false); 							//methods of JButton to hide the button itself
		easyButton.addActionListener(this);										// displaying only text
		easyButton.setContentAreaFilled(false); 
        easyButton.setFocusPainted(false); 
        easyButton.setOpaque(false);
        
        JButton mediumButton = new JButton("Medium");
        mediumButton.setFont(new Font("Baskerville Old Face", Font.BOLD, 32));		//Medium Button
        mediumButton.setForeground(Color.WHITE);
        mediumButton.setBorderPainted(false); 							//methods of JButton to hide the button itself
        mediumButton.addActionListener(this);										// displaying only text
        mediumButton.setContentAreaFilled(false); 
        mediumButton.setFocusPainted(false); 
        mediumButton.setOpaque(false);
        
        JButton hardButton = new JButton("Hard");
        hardButton.setFont(new Font("Baskerville Old Face", Font.BOLD, 32));		//Hard Button
        hardButton.setForeground(Color.WHITE);
        hardButton.setBorderPainted(false); 							//methods of JButton to hide the button itself
        hardButton.addActionListener(this);										// displaying only text
        hardButton.setContentAreaFilled(false); 
        hardButton.setFocusPainted(false); 
        hardButton.setOpaque(false);
        
       gbc.fill = GridBagConstraints.HORIZONTAL;		//horizontal alignment
       gbc.gridx =0;								//specifies where a button should be located on grid
       gbc.gridy = 1;
       add(easyButton, gbc);			//add buttons onto Window
       gbc.gridx =0;
       gbc.gridy = 2;
       add(mediumButton, gbc);
       gbc.gridx =0;
       gbc.gridy = 3;
       add(hardButton, gbc);
	}

	public void actionPerformed(ActionEvent e)		//action command for buttons
	{
		String actionCommand = e.getActionCommand();		//creating string object of the text on button
		if(actionCommand == "Easy")				//Easy	
		{
			JOptionPane.showMessageDialog(null, "You have selected the level Easy. Good Luck!", "Confirm", JOptionPane.PLAIN_MESSAGE);
			setVisible(false);
			Sudoku_Main game = new Sudoku_Main("easy.txt");		//access to easy.txt file to run an Easy Sudoku Game
	    	game.setVisible(true);
		}	
		else if(actionCommand == "Medium")		//Medium
		{
			JOptionPane.showMessageDialog(null, "You have selected the level Medium. Good Luck!", "Confirm", JOptionPane.PLAIN_MESSAGE);
			setVisible(false);
			Sudoku_Main game = new Sudoku_Main("medium.txt");		//access to medium.txt file to run an Medium Sudoku Game
	    	game.setVisible(true);
		}
		else if(actionCommand == "Hard")		//Hard
		{
			JOptionPane.showMessageDialog(null, "You have selected the level Hard. Good Luck!", "Confirm", JOptionPane.PLAIN_MESSAGE);
			setVisible(false);
			Sudoku_Main game = new Sudoku_Main("hard.txt");		//access to hard.txt file to run an Hard Sudoku Game
	    	game.setVisible(true);
		}
	}
	public static void main(String[] args) 
	{
		Sudoku_Menu menu = new Sudoku_Menu();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	menu.setVisible(true);
	}
}
