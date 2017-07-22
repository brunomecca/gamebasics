package tutorial;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter{
	Game game;
	public Menu(Game game){
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX(), my = e.getY();
		
		if(mouseOver(mx, my, 230, 150, 200, 64)){
			game.gameState = Game.STATE.Game;
		}
		else if(mouseOver(mx, my, 230, 250, 200, 64)){
			Game.mainScene = Game.loader.loadImage("/ajudacenario.png");
		}
		else if(mouseOver(mx, my, 230, 350, 200, 64)){
			System.exit(1);
		}
		else if(mouseOver(mx, my, 500, 400, 120, 40)){
			Game.mainScene = Game.loader.loadImage("/menucenario.png");
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x+width){
			if(my > y && my < y+height) return true;
			else return false;
		}
		else return false;
	}
	
	public void render(Graphics g){
		/*
		g.setColor(Color.white);
		Font fnt = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.drawRect(230, 150, 200, 64);
		g.drawString("Jogar", 285, 190);
		
		g.drawRect(230, 250, 200, 64);
		g.drawString("Ajuda", 285, 290);
		
		g.drawRect(230, 350, 200, 64);
		g.drawString("Sair", 300, 390);
		*/
	}
	
	public void tick(){
		
	}
	
}
