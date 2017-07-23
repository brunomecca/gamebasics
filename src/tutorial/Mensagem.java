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
	String fullTexto = "";
	static boolean conteudoGerente = false;
	
	public Mensagem(Game game){
		image = Game.loader.loadImage("/mensagem.png");
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e){
		if(!fullTexto.equals("")){
			if(fullTexto.length() > 60)
				Game.texto = fullTexto;
			else{
				Game.texto = fullTexto.substring(0, fullTexto.length());
				fullTexto = "";
			}
		}
		else if(ocorrendo){
			Game.texto = "";
			game.addKey();
		}
		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	public void render(Graphics g){
		if(!Game.texto.equals("")){
			game.removeKey();
			if(conteudoGerente){
				//adicionar revisão de conteúdo
			}
			ocorrendo = true;
			g.drawImage(image, 0, 0, null);
			g.setColor(Color.white);
			Font fnt = new Font("arial", 1, 20);
			g.setFont(new Font("arial",1,10));
			g.drawString("Clique para continuar", 515, 440);
			g.setFont(fnt);
			if(Game.texto.length() > 60){
				fullTexto = Game.texto.substring(60, Game.texto.length());
				Game.texto = Game.texto.substring(0,60);
			}
			g.drawString(Game.texto, 3, 350);
			
			
		}
	}
}
