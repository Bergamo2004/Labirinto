package model;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OggDoor extends SuperOggetto{
	
	public OggDoor() {
		nome = "porta";
    collision = true;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/oggetti/porta.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
