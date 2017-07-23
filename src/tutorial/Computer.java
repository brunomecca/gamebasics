package tutorial;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Computer extends GameObject{
	int number;
	BufferedImage image;
	int computerNumber;
	Game game;
	boolean complete[] = new boolean[5];
	boolean request[] = new boolean[5];
	
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
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
		for(int i = 0; i < 5; i++){
			if(request[i] == true){
				request[i] = false;
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
				Game.texto = "Ainda não! Vá falar com o gerente.";
				return;
			}
			request[0] = true;
			System.out.println("USANDO O 1");
		}
		else if(this.computerNumber == 2){
			if(complete[1] == false){
				Game.texto = "Você ainda não completou a fase 1!";
				return;
			}
			request[1] = true;
			System.out.println("USANDO O 2");
		}
		else if(this.computerNumber == 3){
			if(complete[2] == false){
				Game.texto = "Você ainda não completou a fase 2!";
				return;
			}
			request[2] = true;
			System.out.println("USANDO O 3");
		}
		else if(this.computerNumber == 4){
			if(complete[3] == false){
				Game.texto = "Você ainda não completou a fase 3!";
				return;
			}
			request[3] = true;
			System.out.println("USANDO O 4");
		}
		else if(this.computerNumber == 5){
			if(complete[4] == false){
				Game.texto = "Você ainda não completou a fase 4!";
				return;
			}
			request[4] = true;
			System.out.println("USANDO O 5");
		}
	}
	
	public void computer1(Graphics g){
		
		game.removeKey();
		Game.texto = "Você entrou na fase 1! Prepare-se para testar!";
		Player.usingComputer = true;
	}
	
	public void computer2(Graphics g){
		Player.usingComputer = true;
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
	
}
