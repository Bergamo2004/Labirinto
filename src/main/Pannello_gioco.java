package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Pannello_gioco extends JPanel implements Runnable{
  //IMPOSTAZIONI SCHERMO
	final int originalTileSize=16;
	final int scale=3;
	
	final int tileSize=originalTileSize*scale;
	final int maxScreenCol=16;
	final int maxScreenRow=12;
	final int screenWidth=tileSize*maxScreenCol;
	final int screenHeight=tileSize*maxScreenRow;
	
	KeyHandler KeyH=new KeyHandler();
	
	Thread gameThread;
	
	int playerX=100;
	int playerY=100;
	int playerSpeed=4;
	
	public Pannello_gioco() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(KeyH);
		this.setFocusable(true);
	}

	public void startGameThread()
	{
		gameThread=new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
      
		while(gameThread!=null) {

		update();
		
		repaint();
			
		}
		
		
	}
	
	public void update()
	{
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}
