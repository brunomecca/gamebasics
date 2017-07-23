package tutorial;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

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
	private MouseObserver mouse;
	
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
		mouse = new MouseObserver(this);
		
		locked = loader.loadImage("/locked.png");
		dislocked = loader.loadImage("/dislocked.png");
		done = loader.loadImage("/done.png");
		this.addMouseListener(msgs);
		this.addMouseListener(menu);
		this.addMouseListener(mouse);
		
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
				states[0] = locked;
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
					computers[i] = new Computer(locais[i],0,ID.Computer, i+1, computerImages[i],i+1, this);
					handler.addObject(computers[i]);

				}		
				mainScene = loader.loadImage("/cenario.png");
				player = new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, 10);
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
	
	static boolean firstTalkGerente = true;
	static boolean firstTalkVera = true;
	
	public static void gerente(){
		if(states[0] == done && states[1] == locked){
			Game.texto = "Parab�ns pela fase 1! Estava torcendo para o seu sucesso. Sabia que aquela calculadora n�o era boa coisa. "+
		"Bom, agora que voc� fez o que deveria ter feito, vou explicar o que voc� fez. Voc� usou uma das t�cnicas de teste chamada t�cnica funcional, ou caixa preta! "
		+ "Nessa t�cnica � usado o conceito de n�o ver o c�digo fonte e trabalhar somente com os requisitos. Voc� analisou os requisitos, montou seus casos de teste e realizou os testes inserindo entradas "
		+ "v�lidas, n�o foi? Meus parab�ns outra vez! � assim que n�s, testadores, utilizamos a t�cnica de caixa preta. Ah, e acho que voc� viu que estava escrito classe de equival�ncia na documenta��o, n�o? Pois bem, n�s chamamos assim entradas"
		+ " que estejam executando o mesmo c�digo. Por exemplo, uma soma com valores positivos. Agora v� testar a fase 2!!";
			states[1] = dislocked;	
		}
		else if(states[1] == done && states[2] == locked){
			//completou fase 2
		}
		else if(states[2] == done && states[3] == locked){
			//completou fase 3
		}
		else if(states[3] == done && states[4] == locked){
			//completou fase 4
		}
		else if(states[4] == done){
			//completou fase 5
		}
		else{
			String textos[] = {"Acho que voc� j� pode ir testar!","O problema n�o foi solucionado."
					, "Eu n�o sei, cara.", "Acho que sim.", "Pra cima.","Computador, sabe?", 
					"Eu gosto de trabalhar.", "Lembre-se que testes s�o muito importantes!", "Eu sou muito bom em testes!",
					"Voc� gosta de testar?", "Claro que testamos software antes de entregar para o cliente!", "T�cnicas de teste? Hm..."};
			Random r = new Random();
			Game.texto = textos[r.nextInt(textos.length)];
		}

	}
	
	public static void vera(){
		String textos[] = {"Eu sou a Vera mesmo!","Sou muito bonita."
				, "Teste exaustivo � outra hist�ria!", "Teste caixa branca � aquele que vemos o c�digo.", "As vezes um ponto e v�rgula faz a diferen�a."
				,"C A I X A  P R E T A ! Esse � f�cil, mas temos que bolar os testes."};
		Random r = new Random();
		Game.texto = textos[r.nextInt(textos.length)];
	}
	
}
