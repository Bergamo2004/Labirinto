package controllo;
import java.lang.String;
import java.io.File;
import java.io.FileWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

	public class GeneraMappa {
	   public static void main(String args[])throws Exception {
	      FileWriter writer = new FileWriter("C:\\Users\\jackv\\Documents\\GitHub\\Labirinto\\res\\maps\\map06.txt");

	      File file= new File("C:\\Users\\jackv\\Documents\\GitHub\\Labirinto\\res\\maps\\img\\mappadis.png");
	      BufferedImage img = ImageIO.read(file);
	      for (int y = 0; y < img.getHeight(); y++) {
	         for (int x = 0; x < img.getWidth(); x++) {

	            int pixel = img.getRGB(x,y);

	            Color color = new Color(pixel, true);

	            int red = color.getRed();
	            int green = color.getGreen();
	            int blue = color.getBlue();
	            
	            if(red==0 && green==255 && blue==0)  
	            {
	            writer.append("0");	//verde==erba
	            }
	            
	            if(red==255 && green==255 && blue==255)
	            {
	            writer.append("4");	//bianco==sabbia
	            }
	            
	            if(red==0 && green==0 && blue==0)
	            {
	            writer.append("1");	//nero==muro
	            }
	            
	            
	            if(red==255 && green==0 && blue==0)
	            {
	            writer.append("3");	//rosso==albero
	            }
	            
	            
	            if(red==255 && green==255 && blue==0)
	            {
	            writer.append("5");	//giallo==terra
	            }
	            
	            
	            if(red==0 && green==0 && blue==255)
	            {
	            writer.append("2");	//blue==acqua
	            }
	            
	                writer.append(" ");	
	            writer.flush();
	         }
	         writer.append("\n");   
	      }
	      writer.close();
	      System.out.println("Mappatura eseguita con successo");
	   }
}
	
	
	
	
	
	

