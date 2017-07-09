package tutorial;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	
	BufferedImage image;
	
	public BufferedImage loadImage(String path){
		try{
			image = ImageIO.read(getClass().getResource(path));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return image;
	}
	
}
