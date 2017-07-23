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
			if(fullTexto.length() > caractere)
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
	int caractere = 60;
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
			caractere = 60;
			if(Game.texto.length() > caractere){
				while(Game.texto.charAt(caractere) != ' '){
					caractere--;
				}
				fullTexto = Game.texto.substring(caractere, Game.texto.length());
				Game.texto = Game.texto.substring(0,caractere);
			}
			g.setFont(fnt);
			g.drawString(Game.texto, 3, 350);
			
			
		}
	}
}
