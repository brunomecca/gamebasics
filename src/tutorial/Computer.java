package tutorial;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

public class Computer extends GameObject{
	int number;
	BufferedImage image;
	static BufferedImage tick;
	int computerNumber;
	Game game;
	static boolean complete[] = new boolean[5];
	static boolean request[] = new boolean[5];
	static boolean acontecendo[] = new boolean[5];
	
	static boolean acao = false;
	
	static BufferedImage current = null;
	
	//requisitos da fase1
	static BufferedImage fase1bg1 = Game.loader.loadImage("/fase1/fase1bg1.png");
	static BufferedImage fase1bg2 = Game.loader.loadImage("/fase1/fase1bg2.png");
	static boolean etapasf1[] = new boolean[3];
	static boolean operacoes[] = new boolean[5];
	static boolean teste[] = new boolean[5];
	
	public Computer(int x, int y, ID id) {
		super(x, y, id);
	}

	public Computer(int x, int y, ID id, int number, BufferedImage image, int computerNumber, Game game) {
		super(x, y, id);
		this.number = number;
		this.image = image;
		this.computerNumber = computerNumber;
		for(int i = 0 ;i < 5;i++)
			request[i] = false;
		this.game = game;
		tick = Game.loader.loadImage("/tick.png");
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
		for(int i = 0; i < 5; i++){
			if(request[i] == true){
				computerRequest(i, g);
				return;
			}
		}
	}
	
	public void computerRequest(int i, Graphics g){
		if(i == 0)
			computer1(g);
		else if(i == 1)
			computer2(g);
		else if(i == 2)
			computer3(g);
		else if(i == 3)
			computer4(g);
		else
			computer5(g);
	}
	
	public int getX(){ return this.x; }
	
	public int getY(){ return this.y; }
	
	public void inUse(){
		if(this.computerNumber == 1){
			if(Game.states[0] == Game.locked){
				Game.texto = "Ainda n�o! V� falar com o gerente.";
				return;
			}
			current = fase1bg1;
			request[0] = true;
			System.out.println("USANDO O 1");
		}
		else if(this.computerNumber == 2){
			if(complete[1] == false){
				Game.texto = "Voc� ainda n�o completou a fase 1!";
				return;
			}
			request[1] = true;
			System.out.println("USANDO O 2");
		}
		else if(this.computerNumber == 3){
			if(complete[2] == false){
				Game.texto = "Voc� ainda n�o completou a fase 2!";
				return;
			}
			request[2] = true;
			System.out.println("USANDO O 3");
		}
		else if(this.computerNumber == 4){
			if(complete[3] == false){
				Game.texto = "Voc� ainda n�o completou a fase 3!";
				return;
			}
			request[3] = true;
			System.out.println("USANDO O 4");
		}
		else if(this.computerNumber == 5){
			if(complete[4] == false){
				Game.texto = "Voc� ainda n�o completou a fase 4!";
				return;
			}
			request[4] = true;
			System.out.println("USANDO O 5");
		}
	}
	
	public void computer1(Graphics g){
		acontecendo[0] = true;
		game.player.playerImage = null;
		g.drawImage(current, 0, 0,null);
		if(HUD.ENERGY <= 0){
			sairPorEnergia(0);
			return;
		}
		game.removeKey();
		Player.usingComputer = true;
		
		if(acao){
			
			acao = false;
			String entrada = JOptionPane.showInputDialog("Digite a entrada a ser testada:");
			while(entrada == null)
				entrada = JOptionPane.showInputDialog("Digite a entrada a ser testada:");
			if(entrada.split(" ")[1].matches(".*[0-9]+") && entrada.split(" ")[2].matches(".*[0-9]+")){
				if(entrada.charAt(0) == 'S'){
					if(entrada.charAt(1) == 'O'){//soma
						teste[0] = true;
						JOptionPane.showMessageDialog(null, "O resultado foi: "
								+Integer.sum(Integer.parseInt(entrada.split(" ")[1]),Integer.parseInt(entrada.split(" ")[2]) ), "Resultado", JOptionPane.PLAIN_MESSAGE);
						
						if (JOptionPane.showConfirmDialog(null, "O resultado est� correto?", "Decis�o",
						        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						    operacoes[0] = true;
						} else {
						    operacoes[0] = false;
						}
					}
					else{//sub
						teste[1] = true;
						JOptionPane.showMessageDialog(null, "O resultado foi: "
								+(Integer.parseInt(entrada.split(" ")[1])*-1 +Integer.parseInt(entrada.split(" ")[2])), "Resultado", JOptionPane.PLAIN_MESSAGE);
						
						if (JOptionPane.showConfirmDialog(null, "O resultado est� correto?", "Decis�o",
						        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						    operacoes[1] = false;
						} else {
						    operacoes[1] = true;
						}
					}
				}
				else if(entrada.charAt(0) == 'M'){//mult
					teste[2] = true;
					JOptionPane.showMessageDialog(null, "O resultado foi: "
							+(Integer.parseInt(entrada.split(" ")[1])*Integer.parseInt(entrada.split(" ")[2])), "Resultado", JOptionPane.PLAIN_MESSAGE);
					
					if (JOptionPane.showConfirmDialog(null, "O resultado est� correto?", "Decis�o",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					    operacoes[2] = true;
					} else {
					    operacoes[2] = false;
					}
				}
				else if(entrada.charAt(0) == 'D'){//div
					teste[3] = true;
					JOptionPane.showMessageDialog(null, "O resultado foi: "
							+(Integer.parseInt(entrada.split(" ")[1])/Integer.parseInt(entrada.split(" ")[2])), "Resultado", JOptionPane.PLAIN_MESSAGE);
					
					if (JOptionPane.showConfirmDialog(null, "O resultado est� correto? Lembre-se que n�o nos importamos com casas decimais!", "Decis�o",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					    operacoes[3] = true;
					} else {
					    operacoes[3] = false;
					}
				}
				else{//exp
					teste[4] = true;
					JOptionPane.showMessageDialog(null, "O resultado foi: "
							+Math.pow(Integer.parseInt(entrada.split(" ")[1]),Integer.parseInt(entrada.split(" ")[2])), "Resultado", JOptionPane.PLAIN_MESSAGE);
					
					if (JOptionPane.showConfirmDialog(null, "O resultado est� correto?", "Decis�o",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					    operacoes[4] = true;
					} else {
					    operacoes[4] = false;
					}
				}
			}
			else{
				JOptionPane.showMessageDialog( null, "Entre com uma das op��es da calculadora!","AVISO", JOptionPane.PLAIN_MESSAGE );
			}
		}
		etapasf1[1] = true;
		etapasf1[2] = true;
		for(int i =0 ; i < 5 ; i++){
			if(!teste[i])
				etapasf1[1] = false;
			if(!operacoes[i])
				etapasf1[2] = false;
			
		}
		
		//verificando etapas
		if(etapasf1[0])
			g.drawImage(tick, 550, 50, null);
		
		if(etapasf1[1])
			g.drawImage(tick, 550, 150, null);
		
		if(etapasf1[2])
			g.drawImage(tick, 550, 225, null);
		
		if(etapasf1[0] && etapasf1[1] && etapasf1[2]){
			Game.states[0] = Game.done;
			complete[1] = true;
		}
	}
	
	public void computer2(Graphics g){
		Player.usingComputer = true;
		acontecendo[1] = true;
	}
	
	public void computer3(Graphics g){
		Player.usingComputer = true;
	}
	
	public void computer4(Graphics g){
		Player.usingComputer = true;
	}
	
	public void computer5(Graphics g){
		Player.usingComputer = true;
	}
	
	public void sairPorEnergia(int fase){
		Player.usingComputer = false;
		game.addKey();
		game.player.playerImage = Game.playerImage;
		acontecendo[fase] = false;
		request[0] = false;
		Game.texto = "Voc� ficou sem energia para continuar a fase 1! Recupere sua energia e tente de novo!";
	}
	
}
