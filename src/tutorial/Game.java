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
				player = new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, 5);
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
	static int dias = 0;
	public static void gerente(){
		if(states[0] == done && states[1] == locked){
			Game.texto = "Parab�ns pela fase 1! Estava torcendo para o seu sucesso. Sabia que aquela calculadora n�o era boa coisa. "+
		"Bom, agora que voc� fez o que deveria ter feito, vou explicar o que voc� fez. Voc� usou uma das t�cnicas de teste chamada t�cnica funcional, ou caixa preta! "
		+ "Nessa t�cnica � usado o conceito de n�o ver o c�digo fonte e trabalhar somente com os requisitos. Voc� analisou os requisitos, montou seus casos de teste e realizou os testes inserindo entradas "
		+ "v�lidas, n�o foi? E ainda voc� decidiu se as sa�das estavam corretas ou n�o. Quem faz essa fun��o � chamado de or�culo. Meus parab�ns outra vez! � assim que n�s, testadores, utilizamos a t�cnica de caixa preta. Ah, e acho que voc� viu que estava escrito classe de equival�ncia na documenta��o, n�o? Pois bem, n�s chamamos assim entradas"
		+ " que estejam executando o mesmo c�digo. Por exemplo, uma soma com valores positivos. Agora v� testar a fase 2!!";
			states[1] = dislocked;	
		}
		else if(states[1] == done && states[2] == locked){
			Game.texto = "Parab�ns pela fase 2, cabe�udo! Eu e toda a equipe escondida estamos satisfeitos com o seu trabalho!"
					+ " Voc� conseguiu treinar as quest�es do Java e ainda saber um pouco mais sobre teste de caixa branca! Esse � um teste"
					+ " onde � poss�vel ver o c�digo fonte e as entrada s�o selecionadas para passar por parte do c�digo. O que voc� fez � o"
					+ " oposto do teste de caixa preta. Voc� se baseou nos caminhos internos do programa para testar, o que � muito bom! Por�m, como voc� pode perceber"
					+ ", essa t�cnica requer conhecimento do c�digo. A pr�xima fase vai ser melhor ainda! Lembre-se que voc� pode ir at� a Vera para rever algum conceito!";
			
			states[2] = dislocked;
		} 
		else if(states[2] == done && states[3] == locked){
			Game.texto = "Sabia que aquele programa tinha erros! O sal�rio do digitador poderia ser alterado, ent�o? Que bom que nada aconteceu durante esse per�odo sem testes, mas foi uma ocorr�ncia de sorte. Parab�ns pela fase 3, cabe�udo! Agora, al�m do conceito de caixa preta estar bem fixado, "
					+ "voc� conhece um crit�rio dessa an�lise. Esse crit�rio � conhecido como an�lise de valor limite como foi visto"
					+ " e � sugerido uma an�lise do limtie superior, inferior e logo no limite para an�lise das classes de equival�ncia. "
					+ "Voc� j� passou mais da metade do game! Agora somente faltam 2 fases e � onde o desafio realmente vai come�ar! Prossiga!";
			states[3] = dislocked;
		}
		else if(states[3] == done && states[4] == locked){
			 Game.texto = "Muito bem! Parab�ns pela fase 4! Voc� j� esta na fase final do processo de introdu��o ao teste de software. "
			 		+ "Voc� viu que complexidade ciclom�tica � um assunto importante e define o quanto complexo um programa pode ser para ser testado. "
			 		+ "Mesmo um programa simples de m�dia tem a sua complexidade em 6 e 13 v�rtices!! Bom, agora voc� est� pronto para a �ltima fase! "
			 		+ "Corra e v� testar! NOSSA! Aconteceu um imprevisto e parece que s� temos mais um dia restante para testarmos "
					+ "essa fase! Sua barra de energia ficar� cheia, mas voc� s� ter� mais esse dia para terminar a "
					+ "fase 5! CORRA E TERMINE LOGO ESSA FASE! O verdadeiro teste come�a agora!";
			 dias = Player.diasRestantes;
			 Player.diasRestantes = 1;
			 HUD.ENERGY = HUD.FULLENERGY;
			 states[4] = dislocked;
		}
		else if(states[4] == done){
			if(HUD.ENERGY <= 0){
				Game.texto = "Parece que voc� n�o entendeu o �ltimo conceito e ficou testando at� desmaiar! Seu objetivo era apenas testar o valor limite, j� que � imposs�vel realizar o teste exaustivo daquele jeito. O verdeiro teste da fase 5 era somente realizar a an�lise do valor limite e me questionar sobre a fase 5. Voc� chegou at� a fase 5 com " + dias + " dias restantes";
			}
			else{
				Game.texto = "Parab�ns por ter realizado todas as fases do jogo! O verdadeiro teste foi o final! Voc� conseguiu perceber que o �ltimo teste era imposs�vel! Agora voc� j� pode ser considerado um tester PROFISSIONAL! "
					+ "Seus estudos em testes nunca foram t�o bem definidos como hoje. E, al�m disso, voc� terminou o jogo com " + Player.diasRestantes + " dias restantes." ;
			}
		}

	}
	
	public static void vera(){
		String textos[] = {"Eu sou a Vera mesmo!","Sou muito bonita."
				, "Teste exaustivo � outra hist�ria!", "Teste caixa branca � aquele que vemos o c�digo.", "As vezes um ponto e v�rgula faz a diferen�a."
				,"C A I X A  P R E T A ! Esse � f�cil, mas temos que bolar os testes.","Classe de equival�ncia � um m�todo de redu��o do conjunto de entradas"
						+ " de forma que o conjunto fique finito, pequeno e mais eficiente! Ela divide os dados em setores de um mesmo padr�o de entrada. � �til para analisar que "
						+ "tal entrada testa as mesmas partes do c�digo.", "Or�culo � aquele que decide se o caso de teste passou ou n�o.", "No teste caixa branca "
								+ "a visualiza��o do c�digo � v�lida!", "V E R A"};
		Random r = new Random();
		Game.texto = textos[r.nextInt(textos.length)];
	}
	
}
