package tutorial;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

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
				game.player.playerImage = Game.playerImage;
				Computer.request[0] = false;
				game.addKey();
				Computer.acontecendo[0] = false;
				Player.usingComputer = false;
			}
			
		}
		else if(Computer.acontecendo[1]){//fase 2
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
				game.player.playerImage = Game.playerImage;
				Computer.request[1] = false;
				game.addKey();
				Computer.acontecendo[1] = false;
				Player.usingComputer = false;
			}
		}
		else if(Computer.acontecendo[2]){//fase 3
			if(mx >= 500 && mx <= 620 && my >= 290 && my <= 325){ // testar
				Computer.acao = true;
			}
			else if(mx >= 395 && mx <= 470 && my >=340 && my <= 370){ // seta
				Computer.current = Computer.fase3bg2;
				Computer.objetivosf3[0] = true;
			}
			else if(mx >20 && mx <= 100 && my >= 330 && my <= 380){
				Computer.current = Computer.fase3bg1;
			}
			else if(my >= 170 && my <= 190) 
				Computer.funcionarios[0] = true;
			else if(my >= 200 && my <= 220) 
				Computer.funcionarios[1] = true;
			else if(my >= 225 && my <= 245) 
				Computer.funcionarios[2] = true;
			else if(my >= 250 && my <= 265)
				Computer.funcionarios[3] = true;
			else if(my >= 270 && my <= 290) 
				Computer.funcionarios[4] = true;
			else if(my >= 295 && my <= 315)
				Computer.funcionarios[5] = true;
				
			else if(mx >= 500 && mx <= 620 && my >= 340 && my <= 380){ // voltar
				game.player.playerImage = Game.playerImage;
				Computer.request[2] = false;
				game.addKey();
				Computer.acontecendo[2] = false;
				Player.usingComputer = false;
			}
		}
		else if(Computer.acontecendo[3]){//fase 4
			if(mx >= 500 && mx <= 620 && my >= 290 && my <= 325){ // testar
				Computer.acao = true;
			}
			else if(mx >= 395 && mx <= 470 && my >=340 && my <= 370){ // seta
				Computer.current = Computer.fase4bg2;
				
			}
			else if(mx >20 && mx <= 100 && my >= 330 && my <= 380){
				Computer.current = Computer.fase4bg1;
			}
			else if(mx >= 388 && mx <= 410 && my >= 7 && my <= 27){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 1)
						Computer.vertices[0] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 390 && mx <= 410 && my >= 60 && my <= 80){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 2)
						Computer.vertices[1] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 388 && mx <= 408 && my >= 98 && my <= 118){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 3)
						Computer.vertices[2] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 388 && mx <= 408 && my >= 142 && my <= 162){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 4)
						Computer.vertices[3] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 390 && mx <= 410 && my >= 180 && my <= 200){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 5)
						Computer.vertices[4] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 385 && mx <= 412 && my >= 220 && my <= 240){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 6)
						Computer.vertices[5] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 350 && mx <= 375 && my >= 245 && my <= 270){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 8)
						Computer.vertices[7] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 400 && mx <= 420 && my >= 288 && my <= 315){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 9)
						Computer.vertices[8] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 335 && mx <= 360 && my >= 169 && my <= 192){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 11)
						Computer.vertices[11] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 297 && mx <= 320 && my >= 140 && my <= 161){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 10)
						Computer.vertices[9] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 267 && mx <= 290 && my >= 169 && my <= 192){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 12)
						Computer.vertices[10] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 300 && mx <= 330 && my >= 198 && my <= 218){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 13)
						Computer.vertices[12] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 420 && mx <= 440 && my >= 247 && my <= 267){
				String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
				if(entrada.matches(".*[0-9]+")){
					if(Integer.parseInt(entrada) == 7)
						Computer.vertices[6] = true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(mx >= 500 && mx <= 620 && my >= 340 && my <= 380){ // voltar
				game.player.playerImage = Game.playerImage;
				Computer.request[3] = false;
				game.addKey();
				Computer.acontecendo[3] = false;
				Player.usingComputer = false;
			}
		}
		else if(Computer.acontecendo[4]){//fase 5
			if(mx >= 500 && mx <= 620 && my >= 290 && my <= 325){ // testar
				Computer.acao = true;
			}
			else if(mx >= 395 && mx <= 470 && my >=340 && my <= 370){ // seta
				Computer.current = Computer.fase5bg2;
				
			}
			else if(mx >20 && mx <= 100 && my >= 330 && my <= 380){
				Computer.current = Computer.fase5bg1;
			}
			else if(mx >= 500 && mx <= 620 && my >= 340 && my <= 380){ // voltar
				game.player.playerImage = Game.playerImage;
				Computer.request[4] = false;
				game.addKey();
				Computer.acontecendo[4] = false;
				Player.usingComputer = false;
			}
		}
	}
	

}
