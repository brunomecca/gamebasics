package tutorial;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int ENERGY = 200;
	public static int FULLENERGY = 200;
	
	public void tick(){
		if(Player.usingComputer){
			ENERGY--;
		}
		ENERGY = Game.clamp(ENERGY, 0,200);
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(30, 400, 200, 32);
		g.setColor(Color.green);
		g.fillRect(30, 400, ENERGY, 32);
		g.setColor(Color.white);
		g.drawRect(30, 400,200, 32);
	}
}
