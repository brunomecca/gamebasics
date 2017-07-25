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
			Game.texto = "Parabéns pela fase 1! Estava torcendo para o seu sucesso. Sabia que aquela calculadora não era boa coisa. "+
		"Bom, agora que você fez o que deveria ter feito, vou explicar o que você fez. Você usou uma das técnicas de teste chamada técnica funcional, ou caixa preta! "
		+ "Nessa técnica é usado o conceito de não ver o código fonte e trabalhar somente com os requisitos. Você analisou os requisitos, montou seus casos de teste e realizou os testes inserindo entradas "
		+ "válidas, não foi? E ainda você decidiu se as saídas estavam corretas ou não. Quem faz essa função é chamado de oráculo. Meus parabéns outra vez! É assim que nós, testadores, utilizamos a técnica de caixa preta. Ah, e acho que você viu que estava escrito classe de equivalência na documentação, não? Pois bem, nós chamamos assim entradas"
		+ " que estejam executando o mesmo código. Por exemplo, uma soma com valores positivos. Agora vá testar a fase 2!!";
			states[1] = dislocked;	
		}
		else if(states[1] == done && states[2] == locked){
			Game.texto = "Parabéns pela fase 2, cabeçudo! Eu e toda a equipe escondida estamos satisfeitos com o seu trabalho!"
					+ " Você conseguiu treinar as questões do Java e ainda saber um pouco mais sobre teste de caixa branca! Esse é um teste"
					+ " onde é possível ver o código fonte e as entrada são selecionadas para passar por parte do código. O que você fez é o"
					+ " oposto do teste de caixa preta. Você se baseou nos caminhos internos do programa para testar, o que é muito bom! Porém, como você pode perceber"
					+ ", essa técnica requer conhecimento do código. A próxima fase vai ser melhor ainda! Lembre-se que você pode ir até a Vera para rever algum conceito!";
			
			states[2] = dislocked;
		} 
		else if(states[2] == done && states[3] == locked){
			Game.texto = "Sabia que aquele programa tinha erros! O salário do digitador poderia ser alterado, então? Que bom que nada aconteceu durante esse período sem testes, mas foi uma ocorrência de sorte. Parabéns pela fase 3, cabeçudo! Agora, além do conceito de caixa preta estar bem fixado, "
					+ "você conhece um critério dessa análise. Esse critério é conhecido como análise de valor limite como foi visto"
					+ " e é sugerido uma análise do limtie superior, inferior e logo no limite para análise das classes de equivalência. "
					+ "Você já passou mais da metade do game! Agora somente faltam 2 fases e é onde o desafio realmente vai começar! Prossiga!";
			states[3] = dislocked;
		}
		else if(states[3] == done && states[4] == locked){
			 Game.texto = "Muito bem! Parabéns pela fase 4! Você já esta na fase final do processo de introdução ao teste de software. "
			 		+ "Você viu que complexidade ciclomática é um assunto importante e define o quanto complexo um programa pode ser para ser testado. "
			 		+ "Mesmo um programa simples de média tem a sua complexidade em 6 e 13 vértices!! Bom, agora você está pronto para a última fase! "
			 		+ "Corra e vá testar! NOSSA! Aconteceu um imprevisto e parece que só temos mais um dia restante para testarmos "
					+ "essa fase! Sua barra de energia ficará cheia, mas você só terá mais esse dia para terminar a "
					+ "fase 5! CORRA E TERMINE LOGO ESSA FASE! O verdadeiro teste começa agora!";
			 dias = Player.diasRestantes;
			 Player.diasRestantes = 1;
			 HUD.ENERGY = HUD.FULLENERGY;
			 states[4] = dislocked;
		}
		else if(states[4] == done){
			if(HUD.ENERGY <= 0){
				Game.texto = "Parece que você não entendeu o último conceito e ficou testando até desmaiar! Seu objetivo era apenas testar o valor limite, já que é impossível realizar o teste exaustivo daquele jeito. O verdeiro teste da fase 5 era somente realizar a análise do valor limite e me questionar sobre a fase 5. Você chegou até a fase 5 com " + dias + " dias restantes";
			}
			else{
				Game.texto = "Parabéns por ter realizado todas as fases do jogo! O verdadeiro teste foi o final! Você conseguiu perceber que o último teste era impossível! Agora você já pode ser considerado um tester PROFISSIONAL! "
					+ "Seus estudos em testes nunca foram tão bem definidos como hoje. E, além disso, você terminou o jogo com " + Player.diasRestantes + " dias restantes." ;
			}
		}

	}
	
	public static void vera(){
		String textos[] = {"Eu sou a Vera mesmo!","Sou muito bonita."
				, "Teste exaustivo é outra história!", "Teste caixa branca é aquele que vemos o código.", "As vezes um ponto e vírgula faz a diferença."
				,"C A I X A  P R E T A ! Esse é fácil, mas temos que bolar os testes.","Classe de equivalência é um método de redução do conjunto de entradas"
						+ " de forma que o conjunto fique finito, pequeno e mais eficiente! Ela divide os dados em setores de um mesmo padrão de entrada. É útil para analisar que "
						+ "tal entrada testa as mesmas partes do código.", "Oráculo é aquele que decide se o caso de teste passou ou não.", "No teste caixa branca "
								+ "a visualização do código é válida!", "V E R A"};
		Random r = new Random();
		Game.texto = textos[r.nextInt(textos.length)];
	}
	
}
