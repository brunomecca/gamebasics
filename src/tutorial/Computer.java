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
	
	//requisitos da fase2
	static BufferedImage fase2bg1 = Game.loader.loadImage("/fase2/fase2bg1.png");
	static BufferedImage fase2bg2 = Game.loader.loadImage("/fase2/fase2bg2.png");
	static boolean erros[] = new boolean[2];
	static boolean botaof2[] = new boolean[2];
	static boolean objetivosf2[] = new boolean[2];
	
	//requisitos da fase3
	static BufferedImage fase3bg1 = Game.loader.loadImage("/fase3/fase3bg1.png");
	static BufferedImage fase3bg2 = Game.loader.loadImage("/fase3/fase3bg2.png");
	static boolean objetivosf3[] = new boolean[3];
	static boolean funcionarios[] = new boolean[6];
	static boolean funcionariosCorretos[] = new boolean[6];
	static int checkGe = 0, checkMo = 0, checkRh = 0, checkLi = 0, checkDi = 0, checkEs = 0;
	static boolean funcionariosTesteValor[] = new boolean[6];
	
	//requisitos da fase4
	static BufferedImage fase4bg1 = Game.loader.loadImage("/fase4/fase4bg1.png");
	static BufferedImage fase4bg2 = Game.loader.loadImage("/fase4/fase4bg2.png");
	static boolean objetivosf4[] = new boolean[2];
	static boolean vertices[] = new boolean[13];
	
	//requisitos da fase5
	static BufferedImage fase5bg1 = Game.loader.loadImage("/fase5/fase5bg1.png");
	static BufferedImage fase5bg2 = Game.loader.loadImage("/fase5/fase5bg2.png");
	static boolean objetivosf5[] = new boolean[2];
	static int entradas1 = 0;
	static boolean entradas[] = new boolean[6];
	
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
				Game.texto = "Ainda não! Vá falar com o gerente.";
				return;
			}
			current = fase1bg1;
			request[0] = true;
			System.out.println("USANDO O 1");
		}
		else if(this.computerNumber == 2){
			if(complete[1] == false || Game.states[1] == Game.locked){
				Game.texto = "Você ainda não completou a fase 1!";
				return;
			}
			current = fase2bg1;
			request[1] = true;
			System.out.println("USANDO O 2");
		}
		else if(this.computerNumber == 3){
			if(complete[2] == false || Game.states[2] == Game.locked){
				Game.texto = "Você ainda não completou a fase 2!";
				return;
			}
			current = fase3bg1;
			request[2] = true;
			System.out.println("USANDO O 3");
		}
		else if(this.computerNumber == 4){
			if(complete[3] == true){
				Game.texto = "Você ainda não completou a fase 3!";
				return;
			}
			request[3] = true;
			current = fase4bg1;
			System.out.println("USANDO O 4");
		}
		else if(this.computerNumber == 5){
			if(complete[4] == false || Game.states[4] == Game.locked){
				Game.texto = "Você ainda não completou a fase 4!";
				return;
			}
			request[4] = true;
			current = fase5bg1;
			
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
						
						if (JOptionPane.showConfirmDialog(null, "O resultado está correto?", "Decisão",
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
						
						if (JOptionPane.showConfirmDialog(null, "O resultado está correto?", "Decisão",
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
					
					if (JOptionPane.showConfirmDialog(null, "O resultado está correto?", "Decisão",
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
					
					if (JOptionPane.showConfirmDialog(null, "O resultado está correto? Lembre-se que não nos importamos com casas decimais!", "Decisão",
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
					
					if (JOptionPane.showConfirmDialog(null, "O resultado está correto?", "Decisão",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					    operacoes[4] = true;
					} else {
					    operacoes[4] = false;
					}
				}
			}
			else{
				JOptionPane.showMessageDialog( null, "Entre com uma das opções da calculadora!","AVISO", JOptionPane.PLAIN_MESSAGE );
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
		game.player.playerImage = null;
		g.drawImage(current, 0, 0, null);
		if(HUD.ENERGY <= 0){
			sairPorEnergia(1);
			return;
		}
		game.removeKey();
		acontecendo[1] = true;
		if(acao){
			acao = false;
			if(erros[0] == false || erros[1] == false){
				JOptionPane.showMessageDialog(null, "Corrija os erros para executar um teste!", "Ainda não!", JOptionPane.ERROR_MESSAGE);
			}
			else{
				String entrada = JOptionPane.showInputDialog("Escreva a entrada para teste:");
				if(entrada.contains("gerente")){
					objetivosf2[0] = true;
				}
				if(entrada.matches(".*[0-9]+"))
					if(Integer.parseInt(entrada) >= 30)
						objetivosf2[1] = true;
			}
		}
		if(botaof2[0]){
			botaof2[0] = false;
			String entrada = JOptionPane.showInputDialog("Escreva o novo conteúdo da linha:");
			if(entrada.contains("cargo") && entrada.contains("local") && entrada.contains("idade")){
				erros[0] = true;
			}
			else{
				JOptionPane.showMessageDialog(null, "Linha incorreta!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(botaof2[1]){
			botaof2[1] = false;
			String entrada = JOptionPane.showInputDialog("Escreva o novo conteúdo da linha:");
			if(entrada.contains("equals")){
				erros[1] = true;
			}
		}
		if(erros[0] && current != fase2bg2)
			g.drawImage(tick, 283, 219-40, null);
		if(erros[1] && current != fase2bg2)
			g.drawImage(tick, 283, 219, null);
		if(erros[0] && erros[1])
			g.drawImage(tick, 550, 60, null);
		if(objetivosf2[0])
			g.drawImage(tick, 550, 110, null);
		if(objetivosf2[1] && objetivosf2[0]){
			g.drawImage(tick, 550, 170, null);
			Game.states[1] = Game.done;
			complete[2] = true;
		}
			
	}
	
	public void computer3(Graphics g){
		Player.usingComputer = true;
		game.player.playerImage = null;
		g.drawImage(current, 0, 0, null);
		if(HUD.ENERGY <= 0){
			sairPorEnergia(2);
			return;
		}
		game.removeKey();
		acontecendo[2] = true;
		
		if(acao){
			acao = false;
			JOptionPane.showMessageDialog( null, "Clique nos funcionários para testar os valores!","Programa rodando!", JOptionPane.PLAIN_MESSAGE );
		}
		
		if(funcionarios[0]){//gerente
			funcionarios[0] = false;
			String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do gerente:");
			if(entrada.matches(".*[0-9]+")){
				int valor = Integer.parseInt(entrada);
				if(valor == 3401 || valor == 3399 || valor == 3400){
					checkGe++;
					JOptionPane.showMessageDialog(null, "Dentro da análise de valor limite!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
					
				}
				else if(valor == 720 || valor == 1450 || valor == 1800 || valor == 2300 || valor == 2800 || valor == 3400){
					funcionariosTesteValor[0] = true;
					JOptionPane.showMessageDialog(null, "Você testou um salário de outro funcionário!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Valor incorreto! Parece que você não entendeu o conceito!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}

			}
			else{
				JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
			}
			if(checkGe == 3){
				funcionariosCorretos[0] = true;
			}
		}
		else if(funcionarios[1]){//montador de circuito
			funcionarios[1] = false;
			String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do montador de circuito:");
			if(entrada.matches(".*[0-9]+")){
				int valor = Integer.parseInt(entrada);
				if(valor == 2800 || valor == 2799 || valor == 2801){
					checkMo++;
					JOptionPane.showMessageDialog(null, "Dentro da análise de valor limite!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
				}
				else if(valor == 720 || valor == 1450 || valor == 1800 || valor == 2300 || valor == 2800 || valor == 3400){
					funcionariosTesteValor[1] = true;
					JOptionPane.showMessageDialog(null, "Você testou um salário de outro funcionário!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Valor incorreto! Parece que você não entendeu o conceito!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
			}
			if(checkMo == 3){
				funcionariosCorretos[1] = true;
			}
		}
		else if(funcionarios[2]){//pessoal rh
			funcionarios[2] = false;
			String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do pessoal do RH:");
			if(entrada.matches(".*[0-9]+")){
				int valor = Integer.parseInt(entrada);
				if(valor == 2300 || valor == 2299 || valor == 2301){
					checkRh++;
					JOptionPane.showMessageDialog(null, "Dentro da análise de valor limite!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
				}
				else if(valor == 720 || valor == 1450 || valor == 1800 || valor == 2300 || valor == 2800 || valor == 3400){
					funcionariosTesteValor[2] = true;
					JOptionPane.showMessageDialog(null, "Você testou um salário de outro funcionário!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Valor incorreto! Parece que você não entendeu o conceito!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
			}
			if(checkRh == 3){
				funcionariosCorretos[2] = true;
			}
		}
		else if(funcionarios[3]){//pessoal limpeza
			funcionarios[3] = false;
			String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do pessoal da limpeza:");
			if(entrada.matches(".*[0-9]+")){
				int valor = Integer.parseInt(entrada);
				if(valor == 1800 || valor == 1799 || valor == 1801){
					checkLi++;
					JOptionPane.showMessageDialog(null, "Dentro da análise de valor limite!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
				}
				else if(valor == 720 || valor == 1450 || valor == 1800 || valor == 2300 || valor == 2800 || valor == 3400){
					funcionariosTesteValor[3] = true;
					JOptionPane.showMessageDialog(null, "Você testou um salário de outro funcionário!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Valor incorreto! Parece que você não entendeu o conceito!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
			}
			if(checkLi == 3){
				funcionariosCorretos[3] = true;
			}
		}
		else if(funcionarios[4]){//digitador
			funcionarios[4] = false;
			String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do digitador:");
			if(entrada.matches(".*[0-9]+")){
				int valor = Integer.parseInt(entrada);
				if(valor == 1450 || valor == 1451 || valor == 1449){
					checkDi++;
					JOptionPane.showMessageDialog(null, "Dentro da análise de valor limite!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
					if(valor == 1451){
						JOptionPane.showMessageDialog(null, "Você encontrou um erro! O salário do digitador foi aceito como 1451! Então, é possível estipular que também vale para R$1800, R$5000 e até R$10000! Coloque isso no seu relatório e diga para o gerente!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
					}
				}
				else if(valor == 720 || valor == 1450 || valor == 1800 || valor == 2300 || valor == 2800 || valor == 3400){
					funcionariosTesteValor[4] = true;
					JOptionPane.showMessageDialog(null, "Você testou um salário de outro funcionário!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Valor incorreto! Parece que você não entendeu o conceito!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
			}
			if(checkDi == 3){
				funcionariosCorretos[4] = true;
			}
		}
		else if(funcionarios[5]){//estagiario
			funcionarios[5] = false;
			String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do estagiário:");
			if(entrada.matches(".*[0-9]+")){
				int valor = Integer.parseInt(entrada);
				if(valor == 720 || valor == 721 || valor == 719){
					checkEs++;
					JOptionPane.showMessageDialog(null, "Dentro da análise de valor limite!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
				}
				else if(valor == 720 || valor == 1450 || valor == 1800 || valor == 2300 || valor == 2800 || valor == 3400){
					funcionariosTesteValor[5] = true;
					JOptionPane.showMessageDialog(null, "Você testou um salário de outro funcionário!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Valor incorreto! Parece que você não entendeu o conceito!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Somente valores numéricos!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
			}
			if(checkEs == 3){
				funcionariosCorretos[5] = true;
			}
		}
	
		if(funcionariosCorretos[0] && current == fase3bg1) g.drawImage(tick,260, 150,null);
		if(funcionariosCorretos[1] && current == fase3bg1) g.drawImage(tick,408, 178,null);
		if(funcionariosCorretos[2] && current == fase3bg1) g.drawImage(tick,335, 198,null);
		if(funcionariosCorretos[3] && current == fase3bg1) g.drawImage(tick,378, 218,null);
		if(funcionariosCorretos[4] && current == fase3bg1) g.drawImage(tick,267, 238,null);
		if(funcionariosCorretos[5] && current == fase3bg1) g.drawImage(tick,277, 268,null);
		
		objetivosf3[2] = true;
		objetivosf3[1] = true;
		for(int i =0 ; i < 6 ;i++){
			if(funcionariosCorretos[i] == false)
				objetivosf3[2] = false;
			if(funcionariosTesteValor[i] == false)
				objetivosf3[1] = false;
		}
		
		if(objetivosf3[0])
			g.drawImage(tick,550, 140,null);
		if(objetivosf3[2])
			g.drawImage(tick, 550, 220, null);
		if(objetivosf3[1])
			g.drawImage(tick, 550, 70, null);
		if(objetivosf3[0] && objetivosf3[1] && objetivosf3[2]){
			Game.states[2] = Game.done;
			complete[3] = true;
		}
	}
	
	public void computer4(Graphics g){
		Player.usingComputer = true;
		game.player.playerImage = null;
		g.drawImage(current, 0, 0, null);
		if(HUD.ENERGY <= 0){
			sairPorEnergia(3);
			return;
		}
		game.removeKey();
		acontecendo[3] = true;
		
		if(acao){
			acao = false;
			String entrada = JOptionPane.showInputDialog("Escreva o valor para teste do digitador:");
			if(entrada.matches(".*[0-9]+")){
				int valor = Integer.parseInt(entrada);
				if(valor == 6){
					objetivosf4[1] = true;
					JOptionPane.showMessageDialog(null, "Você acertou a complexidade ciclomática!", "Teste correto!", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Valor incorreto! Parece que você não entendeu o conceito!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Somente números!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		objetivosf4[0] = true;
		for(int i = 0;i < 13;i++)
			if(vertices[i] == false)
				objetivosf4[0] = false;
			else if(current == fase4bg2){
				if(i == 0)
					g.drawImage(tick, 400, 0,null);
				else if(i == 1)
					g.drawImage(tick, 400, 30,null);
				else if(i == 2)
					g.drawImage(tick, 400, 80,null);
				else if(i == 3)
					g.drawImage(tick, 400, 120,null);
				else if(i == 4)
					g.drawImage(tick, 400, 160,null);
				else if(i == 5)
					g.drawImage(tick, 400, 200,null);
				else if(i == 6)
					g.drawImage(tick, 430, 240,null);//7
				else if(i == 7)
					g.drawImage(tick, 370, 240,null);//8
				else if(i == 8)
					g.drawImage(tick, 400, 280,null);//
				else if(i == 9)
					g.drawImage(tick, 300, 120,null);//
				else if(i == 10)
					g.drawImage(tick, 270, 160,null);
					
				else if(i == 11)
					g.drawImage(tick, 350, 160,null);
				else if(i == 12)
					g.drawImage(tick, 300, 180,null);//13
			}

		if(objetivosf4[0])
			g.drawImage(tick,550,70,null);
		if(objetivosf4[1])
			g.drawImage(tick, 550, 180,null);
		if(objetivosf4[0] && objetivosf4[1]){
			Game.states[3] = Game.done;
			complete[4] = true;
		}
	}
	
	public void computer5(Graphics g){
		Player.usingComputer = true;
		game.player.playerImage = null;
		g.drawImage(current, 0, 0, null);
		if(HUD.ENERGY <= 0){
			sairPorEnergia(4);
			return;
		}
		game.removeKey();
		acontecendo[4] = true;
		if(acao){
			acao = false;
			String entrada = JOptionPane.showInputDialog("Escreva o valor 1 para teste:");
			if(entrada.matches(".*[0-9]+")){
				int valor = Integer.parseInt(entrada);
				if(valor == 0 && !entradas[0]){
					entradas1++;
					entradas[0] = true;
				}
				else if(valor == 1 && !entradas[1]){
					entradas1++;
					entradas[1] = true;
				}
				else if(valor == -1 && !entradas[2]){
					entradas1++;
					entradas[2] = true;
				}
				else if(valor == 499999 && !entradas[3]){
					entradas1++;
					entradas[3] = true;
				}
				else if(valor == 500000 && !entradas[4]){
					entradas1++;
					entradas[4] = true;
					
				}
				else if(valor == 500001 && !entradas[5]){
					entradas1++;
					entradas[5] = true;
				}
				if(entradas1 == 6){
					objetivosf5[1] = true;
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Somente números!", "Tente de novo!", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(objetivosf5[1]){
			Game.states[4] = Game.done;
			g.drawImage(tick, 550, 150, null);
		}
	}
	
	public void sairPorEnergia(int fase){
		Player.usingComputer = false;
		game.addKey();
		game.player.playerImage = Game.playerImage;
		acontecendo[fase] = false;
		request[fase] = false;
		Game.texto = "Você ficou sem energia para continuar a fase! Recupere sua energia e tente de novo!";
	}
	
}
