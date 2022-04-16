package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window =new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Prova Labirinto");
		
		Pannello_gioco pannello=new Pannello_gioco();
		window.add(pannello);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);

	}

}
