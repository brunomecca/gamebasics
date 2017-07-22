package tutorial;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Mensagem extends MouseAdapter {
	
	BufferedImage image;
	boolean ocorrendo = false;
	Game game;
	
	public Mensagem(Game game){
		image = Game.loader.loadImage("/mensagem.png");
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e){
		if(ocorrendo){
			Game.texto = "";
			game.addKey();
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	public void next(){
		
	}
	
	public void render(Graphics g){
		if(!Game.texto.equals("")){
			game.removeKey();
			
			ocorrendo = true;
			g.drawImage(image, 0, 0, null);
			g.setColor(Color.white);
			Font fnt = new Font("arial", 1, 20);
			g.setFont(new Font("arial",1,10));
			g.drawString("Clique para continuar", 515, 440);
			g.setFont(fnt);
			g.drawString(Game.texto, 3, 350);
			
		}
	}
}
