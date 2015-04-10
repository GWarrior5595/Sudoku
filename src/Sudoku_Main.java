/**Term Group Project - SUDOKU
 * COSC 1320 - Computer Science II: Menu Display
 * Created by: Erphun Ranjhar, Jenny Tran, Joseph Eldridge, Alejandro Sifuentes
 * @Copyright 2014. 
 */
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import javax.sound.sampled.*;
import java.lang.String.*;

public class Sudoku_Main extends JFrame
{	//defining variables and components
	private JButton cells[][] = new JButton[9][9]; // empty cells 9x9
	//private JTextField[][] cells =new JTextField[9][9];
	private int array[][] = new int[9][9]; // array: read from file integers go to array 9x9
	private int solution[][] = new int[9][9]; // array: for solutions
	private JPanel board = new JPanel(new GridLayout(9,9));	//9x9 Sudoku Matrix
	public static final int Width = 605;	//defining variables. Width and Height of Window
	public static final int Height = 605;
	public int zeroCounter=0;	//counter: count zero's read from file
	public int greenCounter = 0;	//counter: count correct integer
	public int start;
	public int end;
	
	public Sudoku_Main()	//Default Constructor
	{
		super("Sudoku!");
		setSize(Width, Height);
	}

	public Sudoku_Main(String _fileName)	//Constructor w/ string arg. of txt.file
	{
		super("Sudoku!");
		setSize(Width, Height);
		setLayout(new BorderLayout());
		board.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		read(_fileName);	//send txt file name from Menu to read function to output into array[][]
		
		start = (int) System.currentTimeMillis(); //starting time counter
		
		for(int i = 0; i < 9; i++)
		{
			
			for(int j = 0; j < 9; j++)
			{
				final int row = i;	// i = row
				final int column = j;	// j = column
				if(array[i][j] == 0)
				{
					cells[i][j] = new JButton("");	//read from txt file 0's in array cells to set to clear/empty ""
					zeroCounter++;	//incrementing zeroCounter.
				}
				else 
				{
					cells[i][j] = new JButton(Integer.toString(array[i][j]));	//integer converted by method toString to return the string
					cells[i][j].setForeground(Color.BLACK);	//representation of int's value to cells[][].
				}

				cells[i][j].addKeyListener(enterNumber);	//listener for keyboard events: 'ENTER' to input integers
				cells[i][j].putClientProperty("row", i);	//listener for JButton cells: process which button in class was pressed
				cells[i][j].putClientProperty("column", j);	//listener for JButton cells: able to tell difference
				cells[i][j].addFocusListener(new focus());	//listener: determine which component will receive keyboard input events
				cells[i][j].addKeyListener(new KeyAdapter() //receive keyboard events
				{
					public void keyPressed(KeyEvent e) //Method of KeyListener - Pressed
					{
						switch(e.getKeyCode())
						{
							case KeyEvent.VK_UP:	//key typed events: arrow keys for movement and 'c' for check
								if(row > 0)
									cells[row - 1][column].requestFocus();
								break;
							case KeyEvent.VK_DOWN:
								if(row < 8)
									cells[row + 1][column].requestFocus();
								break;
							case KeyEvent.VK_RIGHT:
								if(column < 8)
									cells[row][column + 1].requestFocus();
								break;
							case KeyEvent.VK_LEFT:
								if(column > 0)
									cells[row][column - 1].requestFocus();
								break;
							case KeyEvent.VK_C:	// 'c' : check and compare input to solution in solution array[][]
							{
								for(int i =0; i < 9; i++)
								{
									for(int j =0 ; j < 9 ; j++)
									{	//CONDITION: Check inputed cells(red and blue).
										if((cells[i][j].getForeground() != Color.BLACK) && (cells[i][j].getForeground() != Color.GREEN))
										{
											if((cells[i][j].getText()).equals(Integer.toString(solution[i][j])))  	//comparing/checking input in cells[][] w/solution array
											{
												cells[i][j].setForeground(Color.GREEN);	//Input correct.	
												greenCounter++;	//Incrementing greenCounter.
											}
											else
												cells[i][j].setForeground(Color.BLUE);	//Input incorrect.
										}
									}
								}
								if (greenCounter ==zeroCounter)	//CONDITION: Congrats.
								{
									end = (int) System.currentTimeMillis();	//when game is finished, stop time counter
						    		int delta= end-start;	//subtract final and initial time
						    		int finalDelta=(int) TimeUnit.MILLISECONDS.toSeconds(delta); //converting milliseconds to seconds
									JOptionPane.showMessageDialog(null, "Congratulations! You've successfully solved the game.\nElapsed time: " + (finalDelta) + " seconds." , "MESSAGE", JOptionPane.PLAIN_MESSAGE);
								}
								break;
							}
						}
					}
				});	//End of KeyListener Method
				
				//Setting areas in 9x9 matrix to certain color to distinguish.
				if((i<3 && j<3) || (i>2 && i<6 && j>2 && j<6) || (i<3 && j<9 && j>5) || (j<3 && i<9 && i>5) || (i>5 && i<9 && j>5 && j<9))
					cells[i][j].setBackground(Color.WHITE);
				else 
					cells[i][j].setBackground(Color.LIGHT_GRAY);
				
				board.add(cells[i][j]);	//add JButton cells to JPanel obj board.
				solution[i][j] = array[i][j];
			}
		}
		solve(0,0);	//Call to Solve Method
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());	//Layout Format and Option Buttons
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		
		JPanel panel = new JPanel();
   		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
   		bottomPanel.add(panel, BorderLayout.SOUTH);
   		
   		JPanel panelOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));
   		panelOptions.setBorder(BorderFactory.createTitledBorder(" Options: "));
   		panel.add(panelOptions);
   		
   		JButton giveUpButton = new JButton("Give Up");	//Give Up Button
        giveUpButton.addActionListener(new giveUpSolution());
        giveUpButton.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
        panelOptions.add(giveUpButton);
        
        JButton instructionButton = new JButton("Instructions");	//Instruction Button for user reference.
        instructionButton.addActionListener(new instructions());
        instructionButton.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
        panelOptions.add(instructionButton);
        
        JButton newGameButton = new JButton("New Game");	//New Game Button -> to Menu
        newGameButton.addActionListener(new newGame());
        newGameButton.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
        panelOptions.add(newGameButton);

        add(board, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
	}

	private KeyListener enterNumber = new KeyAdapter() //KeyListener method keyTyped for 'Enter' : when user input
	{
      public void keyTyped(KeyEvent e) 
      {
         if (e.getKeyChar() == KeyEvent.VK_ENTER) 
         {	//CONDITION: Int. in black and green: No Change.
         	if(((JButton) e.getComponent()).getForeground() == Color.BLACK || ((JButton) e.getComponent()).getForeground() == Color.GREEN)
         		((JButton) e.getComponent()).doClick();
         	else
         	{
	            try	//Else: 
	            {
		            String input = JOptionPane.showInputDialog(null, "Enter Number:");
		            int check = Integer.parseInt(input);	//convert input to int.
	            	if(!(check > 0 && check < 10) )
	            		JOptionPane.showMessageDialog(null, "ERROR: The input must be an integer from 1 through 9.", "Warning!" , JOptionPane.WARNING_MESSAGE);
		            else 
		            {
			            ((JButton)e.getComponent()).setText(input);
			            ((JButton)e.getComponent()).setForeground(Color.RED);	//Inputs are set to Red.
		            }
	            } 
	            catch(NumberFormatException f)
	            {
	            	JOptionPane.showMessageDialog(null, "ERROR: The input must be an INTEGER from 1 through 9", "Warning!" , JOptionPane.WARNING_MESSAGE);
	            }
         	}
         }
      }
    };	//End of KeyListener Method
    
    private class instructions implements ActionListener	//Listener Method for Instruction Button
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(null, "Need Help? Press 'Enter' on the keyboard to write into the box."
            + "  Press 'C' to recieve hints in solving this puzzle!\n"
            + "\t The color green indicates that your input is correct. \n"
            + "\t Blue indicates that your input is incorrect.", "INSTRUCTION DETAILS", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private class giveUpSolution implements ActionListener	//Listener Method for Give Up Button
    {
    	
    	public void actionPerformed(ActionEvent e)
    	{
    		for(int i =0 ;i < 9; i++)
    		{
    			for(int j = 0; j < 9; j++)
    			{
    				if(cells[i][j].getForeground() != Color.BLACK)
    				{
    					cells[i][j].setText(Integer.toString(solution[i][j]));	//solutions are copied to cells[][]
    					cells[i][j].setForeground(Color.GREEN);
    				}	
    			}
    		}
    		end = (int) System.currentTimeMillis(); 	//when game is finished, stop time counter
    		int delta= end-start;	//subtract final and initial time
    		int finalDelta=(int) TimeUnit.MILLISECONDS.toSeconds(delta); //converting milliseconds to seconds
    		JOptionPane.showMessageDialog(null, "Game Over. \nThanks for playing!\n Elapsed time: " + (finalDelta) + " seconds." , "MESSAGE", JOptionPane.PLAIN_MESSAGE);
    	}
    }
    
    private class newGame implements ActionListener	//Listener Method for New Game Button
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		setVisible(false);
    		Sudoku_Menu game = new Sudoku_Menu();	//Open Menu
    		game.setVisible(true);
    	}
    }
    
    private class focus implements FocusListener	//FocusListener: Able to see movement on game.
    {
    	Color block;
    	public void focusGained(FocusEvent e)
    	{
    		block = ((JButton)e.getComponent()).getBackground();
    		((JButton)e.getComponent()).setBackground(Color.ORANGE);	//movement area is highlighted in white.
    	}
    	public void focusLost(FocusEvent e)
    	{
    		((JButton)e.getComponent()).setBackground(block);
    	}
    }
    
    public void read(String _fileName)	//Read Method: Menu determine which .txt file to send.
	{
    	try
    	{ 
    		Scanner inputStream = new Scanner(new FileInputStream(_fileName));
    		array = new int[9][9];
    		while(inputStream.hasNext())	//Outputting txt input from file into array
    		{
    			for(int i =0; i < 9 ;i++)
    				for(int j =0; j<9 ; j++)
    					array[i][j] = inputStream.nextInt();
    		}
			inputStream.close();
		} 
		catch( FileNotFoundException e)
		{	
			System.out.println("ERROR: System has found not file name \"sudoku.txt\".");
		}	

	}

	public boolean solve(int i, int j) //Solve Method: Gives Solution to solution array[][]
	{
        if (i == 9) 
        {
            i = 0;
            if (++j == 9)
                return true;
        }
        if (solution[i][j] != 0)  	// skip filled cells
            return solve(i+1, j);
        for (int val = 1; val <= 9; ++val) 
        {
            if (legal(i, j, val)) 
            {
                solution[i][j] = val;
                if (solve(i+1, j))
                    return true;
            }
        }
        solution[i][j] = 0; // reset on backtrack
        return false;
    }
    public boolean legal(int i, int j, int val) //Legal Method
    {
        for (int a = 0; a < 9; ++a)  	//a= row
            if (val == solution[a][j])
                return false;

        for (int b = 0; b < 9; ++b) //b= column
            if (val == solution[i][b])
                return false;

        int boxRowOffset = (i / 3)*3;
        int boxColOffset = (j / 3)*3;
        
        for (int a = 0; a < 3; ++a) //box
            for (int b = 0; b < 3; ++b)
                if (val == solution[boxRowOffset+a][boxColOffset+b])
                    return false;
        return true; //No violations, so it's legal.
    }
    public static void main(String[] args)
    {	
    	Sudoku_Main game = new Sudoku_Main();
    	game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	game.setVisible(true);
    }
}