package tutorial;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(int i =0 ; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.id == ID.Player){
				Player player = (Player) tempObject;
				if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
					player.setVelY(-5);
				}
				else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
					player.setVelY(5);
				}
				else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
					player.setVelX(5);
					player.playerImage = Game.playerImage;
				}
				else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
					tempObject.setVelX(-5);
					player.playerImage = Game.playerImageSide;
				}
			}

		}
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i =0 ; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.id == ID.Player){
				Player player = (Player) tempObject;
				if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) tempObject.setVelY(0);
				else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) tempObject.setVelY(0);
				else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) tempObject.setVelX(0);
				else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) tempObject.setVelX(0);
				// pc1 = 40+88, pc2 = 40+88+30, pc3 = 40+88+30+88+30, pc4 = 40+88+30+88+30+88+30+88+30, pc5 = 40+88+30+88+30+88+30+88+30
				// o -15 e +15 da soma � para a "gordura" e facilitar o usu�rio a entrar no pc
				if(player.x >= 40-15 && player.x <= 40+88+15 && player.y <= 93 && key == KeyEvent.VK_ENTER){
					Game.computers[0].inUse();
				}//PC1
				else if(player.x >= 40+88+30-15 && player.x <= 40+88+30+88+15 && player.y <= 93 && key == KeyEvent.VK_ENTER){
					Game.computers[1].inUse();
				}//PC2
				else if(player.x >= 40+88+30+88+30-15 && player.x <= 40+88+30+88+30+88+15 && player.y <= 93 && key == KeyEvent.VK_ENTER){
					Game.computers[2].inUse();
				}//PC3
				else if(player.x >= 40+88+30+88+30+88+30-15 && player.x <= 40+88+30+88+30+88+30+88+15 && player.y <= 93 && key == KeyEvent.VK_ENTER){
					Game.computers[3].inUse();
				}//PC4
				else if(player.x >= 40+88+30+88+30+88+30+88+30-15 && player.x <= 40+88+30+88+30+88+30+88+30+88+15 && player.y <= 93 && key == KeyEvent.VK_ENTER){
					Game.computers[4].inUse();
				}//PC5
				else{
					Player.usingComputer = false;
				}
				if(player.x >= 238 && player.x <= 348 && player.y >=332 && player.y <= 377 && key == KeyEvent.VK_ENTER){
					if(HUD.ENERGY == HUD.FULLENERGY){
						Game.texto = "Mas j� est� cansado? Ainda � hora de testar!";
					}
					else if(player.diasRestantes > 0){
						Game.texto = "Energia recuperada! (-1 dia)";
						player.diasRestantes--;
						player.diasRestantes = Game.clamp(player.diasRestantes, 0, player.dias);
						HUD.ENERGY = HUD.FULLENERGY;
					}
					else{
						Game.texto = "Game over! � hora de come�ar do ZERO.";
					}
					
				}
				else if(player.x >= 530 && player.y >= 170 && player.y <= 400 && key == KeyEvent.VK_ENTER){
					if(Game.firstTalkGerente){
						Game.texto = "Ol�, cabe�udo! Eu sou o gerente e te contratei porque est�vamos precisando de um testador! Que bom que voc� chegou! Existem 5 computadores e cada "
								+ "um tem uma li��o diferente para voc� fazer. Ao resolver os 5 problemas, voc� ter� passado pela "
								+ "introdu��o de como testar um software e seus conhecimentos ser�o suficientes para come�ar os seus estudos!"
								+ " Se ficar alguma d�vida, pode falar comigo! Se voc� esquecer algum conceito, estarei disposto a te mostrar novamente! Ao finalizar a li��o de um dos computadores � bom vir falar comigo! "
								+ "Voc� j� pode ir para a fase 1! BOA SORTE!";
						Game.firstTalkGerente = false;
						Game.states[0] = Game.dislocked;
					}
					else {
						Mensagem.conteudoGerente = true;
						Game.gerente();
					}
				}
				else if(player.x <= 161 && player.y >=160 && player.y <= 370 && key == KeyEvent.VK_ENTER){
					//vera
					if(Game.firstTalkVera){
						Game.texto = "As vezes eu posso te dar umas dicas de como testar o software. Depende mesmo do meu humor! "
								+ "Mas irei tentar ser legal com voc�, novato!";
						Game.firstTalkVera = false;
					}
					else Game.vera();
				}
			}
			
		}
	}
}
