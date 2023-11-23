package v3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 
 * Clase Game 'Juego' que es un Panel que se puede implementar encualquer JFrame
 * gracias a que extiende JPanel,esta clase es la que nos implementa el metodo
 * run() el cual nos va a servir para hacer el bucle del juego el cual va a
 * estar corriendo en un thread aparte para no bloquear la pantalla.
 * 
 * En esta clase declaramos un serialVersionUID, un ancho de Panel y un alto. Un
 * Thread para el bucle del juego, varias variables boolean de control
 * inicializadas a false: running, paused, gameStarted, statsSave, godMode.
 * Declaramos otra variable boolean 'gameOver' que nos va a servir para detectar
 * cuando entramos en estado gameOver.
 * 
 * Para poder acceder a las caracteristicas de los objetos de las clases
 * respectivas declaramos: 'Ball','Player','AIPaddle','Token'.
 * 
 * Declaramos una variable que almacene la etapa del juego en forma de String
 * (gameStage).
 * 
 * Declaramos un int para almacenar la velocidad del juego.
 * 
 * Declaramos un ArrayList<Brick>bricks para guardar los ladrillos que generemos
 * en la partida. Así mismo declaramos una variable para guardar la cantidad de
 * ladrillos, un margen y una cantidad de columnas de ladrillos(wallsQuantity);
 * 
 * Declaramos una variable contador 'cont' para hacer un debug cada 100 vueltas
 * de bucle.
 * 
 * Declaramos otra variable 'brickCount' para poder asignar un ID específico a
 * cada ladrillo del wall break.
 * 
 * 
 * 
 * 
 * 
 * 
 * @author p.diaz
 *
 */
public class Game extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8033673042210859245L;
	private int width, height;
	private Thread thread;
	private boolean running = false;
	private boolean paused = false;
	private boolean gameStarted = false;
	private boolean gameOver;
	private boolean statsSaving = false;
	private Ball ball;
	private Player player;
	private String GameStage, playerName;
	private Token token;
	private AIPaddle AI;
	private boolean godMode = false;
	private int velocidadJuego;
	private MainClass main;
	private SaveDataManager sdc;
	// wall
	private ArrayList<Brick> bricks;
	private int bricksQuantity, margin, wallsQuantity;
	private int cont = 0;
	private int brickCount;
	// SaveGame
	private int letraABC;
	private int letraActual;
	private char[] letras = new char[26];
	private char[] nombre = new char[10];
	private String NombreJugador = "";
	private boolean nombreGuardado = false;
	private boolean guardarNombre = true;
	private String underscore = "_";
	private char barrabaja = 95;
	private boolean ckGOverSave = false;
	private int DebugCont = 0;
	private double coeficienteDeVelocidad = 1.5;

	/**
	 * 
	 * En este constructor pedimos por parámetros tanto el anco como el alto de
	 * panel y el 'gameStage' en el que empieze el juego.
	 * 
	 * 
	 * @param width
	 * @param height
	 * @param game
	 */
	public Game(int width, int height, String game, MainClass main, SaveDataManager sdc) {

		this.setPreferredSize(new Dimension(width, height));
		this.width = width - 18;
		this.height = height - 40;
		this.GameStage = game;
		this.main = main;
		this.sdc = sdc;

		crearElementosDelJuego();

		crearNombreJugadorDraw();

		start();

	}

	/**
	 * Metodo DEBUG para revisar errores cada x vueltas de bucle.
	 */
	private void DEBUGsysos(int vueltasDeBucle) {
		if (DebugCont > vueltasDeBucle) {
			// System.out.println("running: " + running);
			// System.out.println("paused: " + paused);
			System.out.println("gameOver: " + gameOver);
			System.out.println("gameStarted: " + gameStarted);
			System.out.println("statsSaving: " + statsSaving);
			// System.out.println("godMode: " + godMode);
			System.out.println("nombreGuardado: " + nombreGuardado);
			System.out.println("guardarNombre: " + guardarNombre);
			System.out.println("ckGOverSave: " + ckGOverSave);
			System.out.println("Nombre Jugador:" + NombreJugador);
			System.out.println(" ------------------------------------- ");
			DebugCont = 0;
		}

		DebugCont++;

	}

	/**
	 * Metodo usado para resetear todos los juegos a su estado inicial en caso de
	 * que se vuelva a echar otra partida.
	 */
	public void newGame() {

		// crearElementosDelJuego();

		// gameOver = false;
		// statsSave = false;
		// nombreGuardado = false;
		getPlayer().setScore(0);
		getPlayer().resetSnake();
		getBall().newBall();
		crearWall();
		for (Brick brick : bricks) {
			brick.setBrickAlive(true);
		}
		setGameStage("pong");
		setGameOver(false);
		setPaused(false);
		setStatsSaving(false);
		setGuardarNombre(false);

	}

	/**
	 * Metodo que nos obtiene el nombre que haya sido dibujado en dibujaNombre(); y
	 * nos lo guarda en el documento de texto de puntuaciones junto a su puntuación
	 * y juego al que se ha llegado.
	 */
	private void getNombreDibujado() {
		NombreJugador = creaNombre();
		if (NombreJugador.contains("_")) {
			NombreJugador = NombreJugador.substring(0, NombreJugador.indexOf(barrabaja));
		}
		// while (NombreJugador.equals("")) {
		//
		// NombreJugador = creaNombre();
		//
		// if (NombreJugador.contains("_")) {
		// NombreJugador = NombreJugador.substring(0, NombreJugador.indexOf(barrabaja));
		// }
		// }

		System.out.println("HE GUARDADO UN NOMBRE = [ " + NombreJugador + " ].");
		sdc.appendTxtStats(NombreJugador, GameStage, getPlayer().getScore());

		setGuardarNombre(false);
		setNombreGuardado(true);
		setStatsSaving(false);

	}

	/**
	 * si el juego ha caido en game over, necesitamos que este metodo ponga a true
	 * la variable booleana StatsSaving ya que cada vez que se pierda es necesario
	 * introducir un nombre para saber de quien son las stats que se han creado.
	 */
	private void checkGameOverState() {
		setStatsSaving(true);
		setGuardarNombre(false);
		setNombreGuardado(false);
		ckGOverSave = false;
	}

	/**
	 * Sobreescritura del metodo paintComponent que va llamando a cada metodo draw
	 * de cada clase necesaria en el momento indicado
	 * 
	 * @param g
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(30, 30, 30));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Arial", Font.PLAIN, 24));
		g.setColor(new Color(197, 0, 0));

		g.drawString("Score: " + player.getScore(), width - 160, 48);

		// ----------------------------------------------

		// DEBUGsysos(100);

		if (godMode) {
			g.setColor(new Color(197, 0, 0));
			g.drawString("GODMODE", width - 160, 24);
		}

		if (paused) {
			g.setFont(new Font("Arial", Font.PLAIN, 16));
			g.setColor(new Color(197, 0, 0));
			g.drawString("PAUSE (pulsa 'espacio' para continuar)", (width / 2) - 150, height / 2);
			// g.drawString("Score: "+player.getScore(), width / 2, height / 2 + 24);
		} else {

			if (gameOver) {
				g.setFont(new Font("Arial", Font.PLAIN, 20));
				g.setColor(new Color(197, 0, 0));
				g.drawString("GAME OVER", (width / 2) - 60, height / 2);
				if (statsSaving) {

					if (!nombreGuardado) {
						dibujaNombre(g);
						if (guardarNombre) {
							getNombreDibujado();
						}

					}

				} else {
					g.drawString("(pulsa 'escape' para salir al menu)", (width / 2) - 150, (height / 2) + 24);
					g.drawString("(pulsa 'intro' para jugar de nuevo)", (width / 2) - 150, (height / 2) + 48);
				}

			} else {
				if (getGameStage().equals("snake")) {
					token.draw(g);
				} else if (getGameStage().equals("pong")) {
					AI.draw(g);
					ball.draw(g);
				} else if (getGameStage().equals("wall")) {
					for (Brick brick : bricks) {
						if (brick.isBrickAlive()) {
							brick.draw(g);
						}
					}
					ball.draw(g);
				}

				player.draw(g, GameStage);

			}
		}

		// ----------------------------------------------
		g.dispose();
	}

	/**
	 * metodo que controla el gameStage dej juego haciendo que segun suben los
	 * puntos pase de un juego a otro.
	 */
	private void controlPartida() {
		if (player.getScore() >= 3 && ball.getX() <= (getWidth() / 2)) {

			setGameStage("wall");
		}
		if (player.getScore() >= (3 + brickCount)) {
			setGameStage("snake");
		}
	}

	/**
	 * metodo ejecudado en el override del run
	 */
	private void runVersion3() {
		// capturo el tiempo en nanosegundos en este instante de ejecución
		long tiempoOrigenRepintado = System.currentTimeMillis();
		long tiempoOrigenMovimiento = System.currentTimeMillis();
		long tiempoOrigenFrameCount = System.currentTimeMillis();
		// establezco el tiempo de espera para que se ejecute el run de nuevo.
		long tiempoEsperaRepintado = 0;
		long tiempoEsperaMovimiento = 0;
		long segundo = 1000L;
		long frames = 60;

		int framesPerSecond = 0;

		tiempoEsperaRepintado = segundo / frames;
		tiempoEsperaMovimiento = (long) ((segundo / frames) * coeficienteDeVelocidad);

		// comienzo el bucle infinito del juego.
		while (running) {
			// capturo el tiempo nada mas comenzar el bucle
			long tiempoActualRepintado = System.currentTimeMillis();
			long tiempoActualMovimiento = System.currentTimeMillis();
			long tiempoActualFrameCount = System.currentTimeMillis();
			controlPartida();
			/**
			 * si la diferencia entre el tiempo actual y el tiempo de origen es mayor o
			 * igual a el tiempo de espera repintado. Igualamos el tiempo actial a el tiempo
			 * de origen para que vuelva a entrar a la siguiente vuelta de bucle.
			 * 
			 * imprimimos 60 frames por segundo.
			 * 
			 */
			if ((tiempoActualRepintado - tiempoOrigenRepintado) >= tiempoEsperaRepintado) {
				tiempoOrigenRepintado = tiempoActualRepintado;
				// metodo que imprime el juego
				repaintPanel();
				// aumentamos un frame por cada vez que entra aqui.
				framesPerSecond++;

			}
			if ((tiempoActualMovimiento - tiempoOrigenMovimiento) >= tiempoEsperaMovimiento) {
				tiempoOrigenMovimiento = tiempoActualMovimiento;
				// metodo que mueve el juego
				parametrosMovimiento();
			}

			if ((tiempoActualFrameCount - tiempoOrigenFrameCount) >= segundo) {
				tiempoOrigenFrameCount = tiempoActualFrameCount;
				// imprimimos por pantalla los frames que se redibujan en un segundo y los
				// restablecemos a 0
				System.out.println("FPS= [" + framesPerSecond + " ]");
				framesPerSecond = 0;
			}

			//

		}
		stop();

	}

	/**
	 * metodo que permite que el juego se mueva de una forma o otra segun el juego
	 * en el que se este jugando.
	 */
	private void parametrosMovimiento() {

		if (!paused) {
			if (!gameOver) {
				if (!godMode) {
					checkGameOver();
				}
				move();
				if (getGameStage().equals("snake")) {
					token.snakeColision();
				}
				if (getGameStage().equals("wall")) {
					ball.checkPaddleAndBrickCollision(player, bricks);

				}
				if (getGameStage().equals("pong")) {

					ball.checkPaddleCollision(player, AI);

				}
			} else {
				if (ckGOverSave) {
					checkGameOverState();
				}

			}
		}
	}

	/**
	 * METODO RUN. Metodo necesario para la creación del bucle del juego, ya que
	 * crea un nuevo thread y dentro de el se mete en un bucle infinito para que
	 * podamos llamar al metodo dibujar y al metodo mover. Esto genera la ilusion de
	 * un videojuego pudiendo establecer los frames per second a el valor preferido
	 * y permitiendo que en cada uno de esos frames se muestre una imagen distinta a
	 * la anterior dando asi una ilusión de movimiento.
	 */
	@Override
	public void run() {

		runVersion3();

	}

	/**
	 * Variables necesarias que se han de cumplir para que Game Over = true;
	 */
	public void checkGameOver() {
		if (getGameStage().equals("snake")) {
			if (player.getXsnake() < 0 || player.getXsnake() > getWidth()) {
				gameOver = true;
				ckGOverSave = true;
			}
			if (player.getYsnake() < 0 || player.getYsnake() > getHeight()) {
				gameOver = true;
				ckGOverSave = true;
			}

			if (player.snakeCollision()) {
				gameOver = true;
				ckGOverSave = true;
			}
		}

		if (getGameStage().equals("pong")) {
			if (ball.getX() < -10 || ball.getX() > width + 10) {
				gameOver = true;
				ckGOverSave = true;
			}
		}

		if (getGameStage().equals("wall")) {
			if (ball.getX() < -10) {
				gameOver = true;
				ckGOverSave = true;
			}
		}

	}

	/**
	 * Método que crea los Objetos que van a ser usados en el juego.
	 */
	public void crearElementosDelJuego() {
		gameOver = false;
		gameStarted = true;
		ball = new Ball(this.width, this.height);
		player = new Player(this.width, this.height);
		token = new Token(this.width, this.height, getPlayer());
		AI = new AIPaddle(player, ball, this.width, this.height);
		// wall
		crearWall();
		// thread Start
		velocidadJuego = 30;

	}

	/**
	 * 
	 * Este metodo sirve para crear una pared de ladrillos que cubra toda la
	 * pantalla independientemente de lo alta que sea.
	 * 
	 */
	public void crearWall() {

		bricks = new ArrayList<Brick>();
		bricksQuantity = (int) ((this.height - 10) / player.getPlayerHeight());
		margin = (int) ((this.height - (player.getPlayerHeight() * bricksQuantity)) / 2);
		wallsQuantity = 3;
		brickCount = 0;
		for (int i = 1; i <= wallsQuantity; i++) {
			for (int j = 0; j < bricksQuantity; j++) {
				bricks.add(new Brick(player, (this.width - 20 - (player.getPlayerWidth() * i)),
						margin + (j * player.getPlayerHeight()), brickCount));
				brickCount++;
			}
		}

	}

	/**
	 * Metodo que nos llama a el metodo repaint() dentro de un nuevo thread de
	 * SwingUtilities.invokeLater
	 */
	private void repaintPanel() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				repaint();
			}
		});
	}

	/**
	 * Método sincronizado que comienza el bucle del juego.
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	/**
	 * Metodo sincronizado que permite parar el thread del bucle del juego.
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que permite que haya movimiento de los objetos del juego.
	 */
	public void move() {
		// ----------------------------------------------

		// cont++;
		// if (cont > 100) {
		// cont = 0;
		//
		// }
		if (getGameStage().equals("pong")) {
			AI.move();
			ball.move(GameStage, godMode);
		} else if (getGameStage().equals("wall")) {
			ball.move(GameStage, godMode);
		} else if (getGameStage().equals("snake")) {

		}

		player.move(GameStage);

		// ----------------------------------------------
	}

	/**
	 * Metodo que nos devuelve un string con la posición del cursor en el momento de
	 * la creacíon del nombre.
	 * 
	 * @return
	 */
	public String getLetraCambiando() {
		return new String("letraActual: " + letraActual + ", letraABC: " + letraABC);
	}

	/**
	 * 
	 * Metodo que inicializa las variables necesarias para poder guardar el nombre
	 * del jugador.
	 * 
	 */
	private void crearNombreJugadorDraw() {
		for (int i = 0; i < letras.length; i++) {
			letras[i] = (char) (65 + i);
		}
		letraABC = 0;
		letraActual = 0;
		for (int i = 0; i < nombre.length; i++) {
			nombre[i] = 95;
		}
	}

	/**
	 * Metodo que recorre el array de char para elegir la letra deseada
	 * 
	 * @param posLetra
	 * @return
	 */
	public char dameLetra(int posLetra) {
		for (int i = 0; i < letras.length; i++) {
			if (posLetra == i) {
				return letras[i];
			}
		}
		return barrabaja;
	}

	/**
	 * metodo que nos rellena el array de char 'nombre' para completar el nombre
	 * deseado
	 * 
	 * @return String del array de char 'nombre'
	 */
	public String creaNombre() {
		for (int i = 0; i < nombre.length; i++) {
			if (i == getLetraActual()) {
				nombre[i] = dameLetra(getLetraABC());
				return new String(nombre);
			}
		}
		return new String(nombre);
	}

	/**
	 * Metodo que nos dibuja en pantalla las instrucciones para crear un nombre para
	 * guardar la partida y nos lo va mostrando segun lo generamos.
	 * 
	 * @param g
	 */
	public void dibujaNombre(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		g.setColor(new Color(197, 0, 0));
		g.drawString("Introduce tu nombre: (flecha arriba, abajo, izquierda, y derecha)", (width / 2) - 120,
				(height / 2) + 24);
		g.drawString("Pulsa 'intro' para guardar", (width / 2) - 120, (height / 2) + 48);
		g.drawString(creaNombre(), (width / 2) - 100, (height / 2) + 72);
	}

	// GETTERS Y SETTERS
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public boolean isRunning() {
		return running;
	}

	public String getGameStage() {
		return GameStage;
	}

	public void setGameStage(String gameStage) {
		GameStage = gameStage;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public AIPaddle getAI() {
		return AI;
	}

	public void setAI(AIPaddle aI) {
		AI = aI;
	}

	public boolean isGodMode() {
		return godMode;
	}

	public void setGodMode(boolean godMode) {
		this.godMode = godMode;
	}

	public ArrayList<Brick> getBricks() {
		return bricks;
	}

	public void setBricks(ArrayList<Brick> bricks) {
		this.bricks = bricks;
	}

	public int getBricksQuantity() {
		return bricksQuantity;
	}

	public void setBricksQuantity(int bricksQuantity) {
		this.bricksQuantity = bricksQuantity;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public int getWallsQuantity() {
		return wallsQuantity;
	}

	public void setWallsQuantity(int wallsQuantity) {
		this.wallsQuantity = wallsQuantity;
	}

	public int getBrickCount() {
		return brickCount;
	}

	public void setBrickCount(int brickCount) {
		this.brickCount = brickCount;
	}

	public int getVelocidadJuego() {
		return velocidadJuego;
	}

	public void setVelocidadJuego(int velocidadJuego) {
		this.velocidadJuego = velocidadJuego;
	}

	public synchronized boolean getPaused() {
		return paused;
	}

	public synchronized void setPaused(boolean paused) {
		this.paused = paused;
	}

	public synchronized boolean getRunning() {
		return running;
	}

	public synchronized void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isStatsSaving() {
		return statsSaving;
	}

	public void setStatsSaving(boolean statsSaving) {
		this.statsSaving = statsSaving;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public MainClass getMain() {
		return main;
	}

	public void setMain(MainClass main) {
		this.main = main;
	}

	public SaveDataManager getSdc() {
		return sdc;
	}

	public void setSdc(SaveDataManager sdc) {
		this.sdc = sdc;
	}

	public char[] getLetras() {
		return letras;
	}

	public void setLetras(char[] letras) {
		this.letras = letras;
	}

	public char[] getNombre() {
		return nombre;
	}

	public void setNombre(char[] nombre) {
		this.nombre = nombre;
	}

	public String getUnderscore() {
		return underscore;
	}

	public void setUnderscore(String underscore) {
		this.underscore = underscore;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNombreJugador() {
		return NombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		NombreJugador = nombreJugador;
	}

	public boolean isNombreGuardado() {
		return nombreGuardado;
	}

	public void setNombreGuardado(boolean nombreGuardado) {
		this.nombreGuardado = nombreGuardado;
	}

	public boolean isGuardarNombre() {
		return guardarNombre;
	}

	public void setGuardarNombre(boolean guardarNombre) {
		this.guardarNombre = guardarNombre;
	}

	public char getBarrabaja() {
		return barrabaja;
	}

	public void setBarrabaja(char barrabaja) {
		this.barrabaja = barrabaja;
	}

	public int getLetraABC() {
		return letraABC;
	}

	public void setLetraABC(int letraStart) {
		this.letraABC = letraStart;
	}

	public int getLetraActual() {
		return letraActual;
	}

	public void setLetraActual(int letraActual) {
		this.letraActual = letraActual;
	}

	public boolean isCkGOverSave() {
		return ckGOverSave;
	}

	public void setCkGOverSave(boolean ckGOverSave) {
		this.ckGOverSave = ckGOverSave;
	}

	public int getDebugCont() {
		return DebugCont;
	}

	public void setDebugCont(int debugCont) {
		DebugCont = debugCont;
	}

	public double getCoeficienteDeVelocidad() {
		return coeficienteDeVelocidad;
	}

	public void setCoeficienteDeVelocidad(double coeficienteDeVelocidad) {
		this.coeficienteDeVelocidad = coeficienteDeVelocidad;
	}

	/**
	 * 
	 * Metodo antiguo de introduccion de nombre. Obsoleto por motivos de estética.
	 * 
	 * @deprecated
	 */
	public void statsSave() {
		playerName = JOptionPane.showInputDialog(main, "Introduce tu Nick:", "Anonimo");
		if (playerName.equalsIgnoreCase("")) {
			playerName = "Anonimo";
		}
		playerName = playerName.replace("\\", "");
		getPlayer().setName(playerName);
		sdc.appendTxtStats(getPlayer().getName(), getGameStage(), getPlayer().getScore());
		statsSaving = true;
	}

}
