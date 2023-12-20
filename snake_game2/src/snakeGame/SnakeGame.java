package snakeGame;

import javax.swing.JFrame;
import snakeGame.*;
public class SnakeGame extends JFrame
{
	public SnakeGame()
	{
		super("Snake Game");
		add(new Board());
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
	}
public static void main(String[] args) 
{
	try
	{
		new SnakeGame().setVisible(true);
	} catch (Exception e)
	{
		e.printStackTrace();
	}
	
}
}
