package main;

import java.awt.Color;
import java.awt.Dimension;

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
	
	Thread gameThread;
	
	public Pannello_gioco() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}

	public void startGameThread()
	{
		gameThread=new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
      
		while(gameThread!=null) {

		}
		
		
	}
}
