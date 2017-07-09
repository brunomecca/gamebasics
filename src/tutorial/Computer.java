package tutorial;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Computer extends GameObject{
	int number;
	BufferedImage image;
	public Computer(int x, int y, ID id) {
		super(x, y, id);
		
	}

	public Computer(int x, int y, ID id, int number, BufferedImage image) {
		super(x, y, id);
		this.number = number;
		this.image = image;
	}
	
	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
		
	}
	
	public int getX(){ return this.x; }
	
	public int getY(){ return this.y; }
	
}
