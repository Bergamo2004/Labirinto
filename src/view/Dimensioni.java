package view;

public class Dimensioni {

	// Impostazioni Schermo
	public final int grandezzaOriginale = 16;
	public final int scala = 3;

	public final int grandezzaInGioco = grandezzaOriginale * scala; // 48
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;

	public final int lunghezzaSchermo = maxScreenCol * grandezzaInGioco; // 768
	public final int altezzaSchermo = maxScreenRow * grandezzaInGioco;   // 576

	// Impostazioni mondo
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = grandezzaInGioco * maxWorldCol;  // 2400
	public final int worldHeight = grandezzaInGioco * maxWorldRow; // 2400
}
