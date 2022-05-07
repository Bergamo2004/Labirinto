package model;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OggKey extends SuperOggetto{
	
	public OggKey() {
		nome = "chiave";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/oggetti/chiave.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
