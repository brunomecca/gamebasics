package tutorial;

import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class Player extends GameObject{
	public BufferedImage playerImage;
	
	public static boolean usingComputer = false;
	
	public Player(int x, int y, ID id){
		super(x,y,id);
		playerImage = Game.playerImage;
		usingComputer = false;
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH-50 );
		y = Game.clamp(y, 0, Game.HEIGHT-100 );
		
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect(x, y, 32, 32);
		g.drawImage(playerImage,(int)x,(int)y,null);
	}
	
}
