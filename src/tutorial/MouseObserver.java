package tutorial;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseObserver extends MouseAdapter {
	Game game;
	
	public MouseObserver(Game game){
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX(), my = e.getY();
		System.out.println("X: "+e.getX()+" Y: "+e.getY());
		if(Computer.acontecendo[0]){ // primeira fase
			if(mx >= 500 && mx <= 620 && my >= 290 && my <= 325){ // testar
				Computer.acao = true;
			}
			else if(mx >= 395 && mx <= 470 && my >=340 && my <= 370){ // seta
				Computer.current = Computer.fase1bg2;
				Computer.etapasf1[0] = true;//primeiro objetivo ok
			}
			else if(mx >20 && mx <= 100 && my >= 330 && my <= 380){
				Computer.current = Computer.fase1bg1;
			}
			else if(mx >= 500 && mx <= 620 && my >= 340 && my <= 380){ // voltar
				game.player.playerImage = game.playerImage;
				Computer.request[0] = false;
				game.addKey();
				Computer.acontecendo[0] = false;
				Player.usingComputer = false;
			}
			
		}
		if(Computer.acontecendo[1]){//fase 2
			if(mx >= 500 && mx <= 620 && my >= 290 && my <= 325){ // testar
				Computer.acao = true;
			}
			else if(my >= 210 && my <= 230){
				Computer.botaof2[0] = true;
			}
			else if(my >= 250 && my <= 270){
				Computer.botaof2[1] = true;
			}
			else if(mx >= 395 && mx <= 470 && my >=340 && my <= 370){ // seta
				Computer.current = Computer.fase2bg2;
			}
			else if(mx >20 && mx <= 100 && my >= 330 && my <= 380){
				Computer.current = Computer.fase2bg1;
			}
			else if(mx >= 500 && mx <= 620 && my >= 340 && my <= 380){ // voltar
				game.player.playerImage = game.playerImage;
				Computer.request[1] = false;
				game.addKey();
				Computer.acontecendo[1] = false;
				Player.usingComputer = false;
			}
		}
	}
	

}
