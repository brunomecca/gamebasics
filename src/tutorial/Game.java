package tutorial;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static boolean gameIsOver = false;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12*9;
	private Thread thread;
	private boolean running = false;
	public Player player;
	public static String texto = "";
	
	private Handler handler;
	
	private HUD hud;
	
	static BufferedImage states[] = new BufferedImage[5];
	
	private Menu menu;
	
	public static BufferedImage playerImage, playerImageSide;
	public static BufferedImage computerImages[] = new BufferedImage[5];
	public static BufferedImage mainScene;
	public static BufferedImage locked, dislocked, done;
	
	private Mensagem msgs;
	
	public static Computer computers[] = new Computer[5];
	
	public static BufferedImageLoader loader = new BufferedImageLoader();
	
	public enum STATE {
		Menu,Game
	}
	
	public KeyInput keyInput;
	
	public STATE gameState = STATE.Menu;
	boolean firstTimeLoader = true;
	public Game(){
		msgs = new Mensagem(this);
		handler = new Handler();
		menu = new Menu(this);
		locked = loader.loadImage("/locked.png");
		dislocked = loader.loadImage("/dislocked.png");
		done = loader.loadImage("/done.png");
		this.addMouseListener(msgs);
		this.addMouseListener(menu);
		
		new Window(WIDTH, HEIGHT, "Game", this);
		
		mainScene = loader.loadImage("/menucenario.png");	
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new Game();
	}
	
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		if(gameState == STATE.Game){
			if(firstTimeLoader){
				keyInput = new KeyInput(handler);
				this.addKeyListener(keyInput);
				
				states[0] = dislocked;
				states[1] = locked;
				states[2] = locked;
				states[3] = locked;
				states[4] = locked;
				
				hud = new HUD();
				
				playerImage = loader.loadImage("/player.png");
				playerImageSide = loader.loadImage("/playerSide.png");
				int locais[] = {40, 40+88+30, 40+88+30+88+30, 40+88+30+88+30+88+30, 40+88+30+88+30+88+30+88+30};
				for(int i = 0 ; i < 5; i++){
					computerImages[i] = loader.loadImage("/computer" + (i+1) + ".png");
					computers[i] = new Computer(locais[i],0,ID.Computer, i+1, computerImages[i],i+1);
					handler.addObject(computers[i]);

				}		
				mainScene = loader.loadImage("/cenario.png");
				player = new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, 100);
				handler.addObject(player);
				
				firstTimeLoader = false;
				this.removeMouseListener(menu);
				menu = null;
			}
			handler.tick();
			hud.tick();
		}
		else if(gameState == STATE.Menu){
			menu.tick();
		}
		
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(mainScene, 0, 0, null);

		int localX = 40+15;
		for(int i =0  ;i < 5 ; i++){
			g.drawImage(states[i], localX, 100, null);
			localX = localX + 88+30;
		}
		
		handler.render(g);
		
		
		if(gameState == STATE.Game){
			if(!firstTimeLoader)
				hud.render(g);
		}
		else if(gameState == STATE.Menu){
			menu.render(g);
		}
		
		msgs.render(g);
		g.dispose();
		bs.show();	
	}
	
	public static int clamp(int var, int min, int max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public void removeKey(){
		this.removeKeyListener(keyInput);
	}
	public void addKey(){
		this.addKeyListener(keyInput);
	}
}
