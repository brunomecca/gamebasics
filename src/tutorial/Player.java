package tutorial;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class Player extends GameObject{
	public BufferedImage playerImage;
	int dias = 10, diasRestantes;
	public static boolean usingComputer = false;
	
	public Player(int x, int y, ID id, int dias){
		super(x,y,id);
		playerImage = Game.playerImage;
		usingComputer = false;
		this.dias = dias;
		diasRestantes = dias;
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH-50 );
		y = Game.clamp(y, 0, Game.HEIGHT-100 );
		if(HUD.ENERGY == 0){
			usingComputer = false;
		}
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect(x, y, 32, 32);
		g.drawImage(playerImage,(int)x,(int)y,null);
		g.setFont(new Font("arial",1,15));
		g.drawString("dias restantes", 500, 420);
		g.setFont(new Font("arial",1,20));
		g.drawString(""+diasRestantes, 530, 440);
	}
	
}
