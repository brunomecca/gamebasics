package tutorial;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class GameObject {

	protected int x,y;
	protected int velX, velY;
	public ID id;
	
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setId(ID id){
		this.id = id;
	}
	
	public ID getId(){
		return this.id;
	}
	
	public void setVelX(int x){
		this.velX = x;
	}

	public void setVelY(int y){
		this.velY = y;
	}
	
	public int getVelX(){
		return velX;
	}

	public int getVelY(){
		return velY;
	}
}
