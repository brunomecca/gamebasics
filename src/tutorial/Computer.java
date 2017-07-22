package tutorial;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Computer extends GameObject{
	int number;
	BufferedImage image;
	int computerNumber;
	boolean complete[] = new boolean[5];
	
	public Computer(int x, int y, ID id) {
		super(x, y, id);
	}

	public Computer(int x, int y, ID id, int number, BufferedImage image, int computerNumber) {
		super(x, y, id);
		this.number = number;
		this.image = image;
		this.computerNumber = computerNumber;
	}
	
	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	
	public int getX(){ return this.x; }
	
	public int getY(){ return this.y; }
	
	public void inUse(){
		if(this.computerNumber == 1){
			
			System.out.println("USANDO O 1");
			Player.usingComputer = true;
		}
		else if(this.computerNumber == 2){
			if(complete[1] == false){
				Game.texto = "Você ainda não completou a fase 1!";
				return;
			}
			Player.usingComputer = true;
			System.out.println("USANDO O 2");
		}
		else if(this.computerNumber == 3){
			if(complete[2] == false){
				Game.texto = "Você ainda não completou a fase 2!";
				return;
			}
			Player.usingComputer = true;
			System.out.println("USANDO O 3");
		}
		else if(this.computerNumber == 4){
			if(complete[3] == false){
				Game.texto = "Você ainda não completou a fase 3!";
				return;
			}
			Player.usingComputer = true;
			System.out.println("USANDO O 4");
		}
		else if(this.computerNumber == 5){
			if(complete[4] == false){
				Game.texto = "Você ainda não completou a fase 4!";
				return;
			}
			Player.usingComputer = true;
			System.out.println("USANDO O 5");
		}
	}
	
}
