package tutorial;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		BufferedImageLoader loader = new BufferedImageLoader();
		int key = e.getKeyCode();
		for(int i =0 ; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.id == ID.Player){
				Player player = (Player) tempObject;
				if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
					player.setVelY(-5);
				}
				if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
					player.setVelY(5);
				}
				if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
					player.setVelX(5);
					player.playerImage = Game.playerImage;
				}
				if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
					tempObject.setVelX(-5);
					player.playerImage = Game.playerImageSide;
				}
				if(player.x >= 68 && player.x <= 68+88 && player.y <= 93){
					Player.usingComputer = true;
				}
				else{
					Player.usingComputer = false;
				}
				if(player.x >= 238 && player.x <= 348 && player.y >=332 && player.y <= 377 && key == KeyEvent.VK_ENTER)
					HUD.ENERGY = HUD.FULLENERGY;
			}
		}
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i =0 ; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.id == ID.Player){
				if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) tempObject.setVelY(0);
				if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) tempObject.setVelX(0);
				if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) tempObject.setVelX(0);
			}
		}
	}
}
