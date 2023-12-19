package snakeGame;
import java.awt.PointerInfo;
import snakeGame.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener
{
	
	private Image apple;
	private Image dot;
	private Image head;
	private final int ALL_DOTS=900;
	private final int DOT_SIZE=10;
	private final int RANDOM_POSSITION=29;
	private int apple_x;
	private int apple_y;
	private final int x[]=new int[ALL_DOTS];
	private final int y[]=new int[ALL_DOTS];
	private boolean left_direction=false;
	private boolean right_direction=true;
	private boolean up_direction=false;
	private boolean down_direction=false;
	private int dots;
	private javax.swing.Timer timer;
	private boolean inGame=true;
	
public Board()
{
	addKeyListener(new TAdapter());
	setPreferredSize(new Dimension(300,300));
	setBackground(Color.black);
	setFocusable(true);
	try 
	{
	loadImages();
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
	initGame();
}

private void loadImages() 
{
	ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/apple.png"));
	apple=i1.getImage();
	ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("icons/dot.png"));
	dot=i2.getImage();
	ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("icons/head.png"));
	head=i3.getImage();
	
}
private void initGame() 
{
	
	dots=3;
	for (int i = 0; i < dots; i++) 
	{
		y[i]=50;
		x[i]=50-i*DOT_SIZE;
	}
	locateApple();
	timer = new javax.swing.Timer(140, this);
    timer.start();
}
private void locateApple()
{
	int r=(int)( Math.random() * RANDOM_POSSITION);
	apple_x=r*DOT_SIZE;
	 r=(int)( Math.random() * RANDOM_POSSITION);
	 apple_y=r*DOT_SIZE;
}

public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	draw(g);
}

private void draw(Graphics g)
{
	g.drawImage(apple, apple_x, apple_y, this);
	if(inGame)
	{
	for (int i = 0; i < dots; i++)
	{
		if (i==0)
		{
			g.drawImage(head, x[i], y[i], this);
		}
		else
		{
			g.drawImage(dot, x[i], y[i], this);
		}
	}
	Toolkit.getDefaultToolkit().sync();
	}
	else
	{
		gameOver(g);
	}
}
private void gameOver(Graphics g) 
{
	String msg="Game Over";
	Font font = new Font("SAN_SERIF", Font.BOLD, 16 );
	g.setColor(Color.white);
	g.setFont(font);
	FontMetrics metrices=getFontMetrics(font);
	g.drawString(msg, (300-metrices.stringWidth(msg))/2, 300/2);
	
}

public void move()
{
	for(int i=dots;i>0;i--)
	{
		x[i]=x[i-1];
		y[i]=y[i-1];
	}
	if (left_direction) 
	{
		x[0]=x[0]-DOT_SIZE;
	}
	if (right_direction) 
	{
		x[0]=x[0]+DOT_SIZE;
	}
	if (up_direction) 
	{
		y[0]=y[0]-DOT_SIZE;
	}
	if (down_direction) 
	{
		y[0]=y[0]+DOT_SIZE;
	}
	//x[0]+=DOT_SIZE;
	//y[0]+=DOT_SIZE;
}
private void checkApple()
{

if ((x[0]==apple_x) && (y[0]==apple_y))
{
	dots++;
	locateApple();
}
	
}

private void checkCollison() 
{
	for (int i =dots; i >0; i--)
	{
		if ((i>4)&& (x[0]==x[i])&& (y[0]==y[i])) 
		{
			inGame=false;
		}
	}
	if (y[0]>=300)
	{
		inGame=false;
	}
	if (x[0]>=300)
	{
		inGame=false;
	}if (y[0]<0)
	{
		inGame=false;
	}if (x[0]<0)
	{
		inGame=false;
	}
	if(!inGame)
	{
		timer.stop();
	}
}


public void actionPerformed(ActionEvent ae)
{
	if(inGame)
	{
	checkApple();
	checkCollison();
	move();
	}
	repaint();
}

public class TAdapter extends KeyAdapter
{
	@Override
	public void keyPressed(KeyEvent e)
	{
		int key=e.getKeyCode();
		if (key==KeyEvent.VK_LEFT && !right_direction)
		{
			left_direction=true;
			up_direction=false;
			down_direction=false;
		}
		if (key==KeyEvent.VK_RIGHT && !left_direction)
		{
			right_direction=true;
			up_direction=false;
			down_direction=false;
		}if (key==KeyEvent.VK_UP && !down_direction)
		{
			up_direction=true;
			left_direction=false;
			right_direction=false;
		}if (key==KeyEvent.VK_DOWN && !up_direction)
		{
			down_direction=true;
			left_direction=false;
			right_direction=false;
		}
	}
}
}
