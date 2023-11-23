package v3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

/**
 * 
 * Clase que nos implementa elmetodo main, extiende a JFrame para crear una
 * interfaz gráfica y implementa un ActionListener para la escucha de botones.
 * Declaramos una constante long como serialVersion, otra constante que nos
 * establezca el tamaño de ventana y otra para el alto. Como variables globales
 * de clase declaramos un Juego 'Game', los JPanel, JLabel, JButton, JTextArea
 * necesarios para la interfaz gráfica, un String que nos almacene el juego al
 * que estamos jugando, y un SaveDataManager para poder guardar la partida o la
 * puntuacion.
 * 
 * @author p.diaz
 *
 */
public class MainClass extends JFrame implements ActionListener {

	private static final long serialVersionUID = 2350704171326530882L;
	public static final int WIDTH = 800, HEIGHT = 600;
	private Game game;
	// Creamos panel con Layout centrada
	private JPanel menuPane, creditPane, howPane, statsPane;
	private String gameStage;
	private JButton startBtn, creditBtn, helpBtn, exitBtn, backBtn, backBtn2, backBtn3, resumeBtn, statsBtn;
	private JLabel titleLbl, namesLbl, howLbl, titleStatsLbl;
	private JTextArea statsLbl;
	private SaveDataManager sdc;

	/**
	 * 
	 * Metodo MAIN en el cual creamos el objeto de clase mediante un invokeLater de
	 * SwingUtilities.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainClass();
			}
		});
	}

	/**
	 * Constructor de la clase con el JFrame. Primero le establecemos unos valores
	 * de titulo de frame, tamaño, operacion ce cerrado por defecto, localizacion y
	 * visibilidad.
	 * 
	 * Inicializamos el gameStage a 'pong' ya que es el juego del inicio. Creamos el
	 * juego insertandole por parametros el ancho, alto, y gameStage que necesita y
	 * finalmente le añadimos a esta clase un KeyListener para los eventos del
	 * teclado.
	 * 
	 * 
	 */
	public MainClass() {
		// GENERAL
		setTitle("The Final Game");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		// ACCIONES PREVIAS A LA CREACIÓN DEL FRAME.
		File f = new File(".//savedataTFG.txt");
		sdc = new SaveDataManager(f, game);
		sdc.iniSortedSaveManager();
		gameStage = "pong";
		game = new Game(WIDTH, HEIGHT, gameStage, this, sdc);
		game.setPaused(true);
		this.addKeyListener(new KeyListenGame(game, this));

		/**
		 * Declaramos unos estilos para usar luego en nuestra clase.
		 */
		// - ESTILOS -
		int btnWith = 270;
		int btnHeight = 90;
		Font btnFont = new Font("Arial", Font.BOLD, 40);
		Font creditFont = new Font("Arial", Font.BOLD, 21);
		Color btnColorPrimary = new Color(2, 167, 238);

		/**
		 * Creamos el menu de inicio con un Gridlayout de 5filas y 1columna. Le
		 * asignamos un ancho de toda la pantalla y lo añadimos a el frame.
		 */
		/**
		 * / MENU INICIO
		 */
		menuPane = new JPanel();
		menuPane.setLayout(new GridLayout(5, 0));
		menuPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		menuPane.setBackground(new Color(250, 250, 250));
		add(menuPane);
		// Boton START
		startBtn = new JButton("Nueva Partida");
		startBtn.setActionCommand("startBtn");
		startBtn.addActionListener(this);
		startBtn.setFocusable(false);
		startBtn.setFont(btnFont);
		startBtn.setPreferredSize(new Dimension(btnWith, btnHeight));
		startBtn.setForeground(Color.WHITE);
		startBtn.setBackground(btnColorPrimary);
		startBtn.setAlignmentX(CENTER_ALIGNMENT);
		menuPane.add(startBtn);
		// Boton CONTINUAR
		resumeBtn = new JButton("Continuar");
		resumeBtn.setActionCommand("resumeBtn");
		resumeBtn.addActionListener(this);
		resumeBtn.setFocusable(false);
		resumeBtn.setFont(btnFont);
		resumeBtn.setPreferredSize(new Dimension(btnWith, btnHeight));
		resumeBtn.setForeground(Color.WHITE);
		resumeBtn.setBackground(btnColorPrimary);
		resumeBtn.setAlignmentX(CENTER_ALIGNMENT);
		// Se añade reemplazando a comenzar
		// Boton Creditos
		creditBtn = new JButton("Créditos");
		creditBtn.setActionCommand("creditBtn");
		creditBtn.addActionListener(this);
		creditBtn.setFocusable(false);
		creditBtn.setFont(btnFont);
		creditBtn.setPreferredSize(new Dimension(btnWith, btnHeight));
		creditBtn.setForeground(Color.WHITE);
		creditBtn.setBackground(btnColorPrimary);
		creditBtn.setAlignmentX(CENTER_ALIGNMENT);
		menuPane.add(creditBtn);
		// Boton Instrucciones
		helpBtn = new JButton("Instrucciones");
		helpBtn.setActionCommand("helpBtn");
		helpBtn.addActionListener(this);
		helpBtn.setFocusable(false);
		helpBtn.setFont(btnFont);
		helpBtn.setPreferredSize(new Dimension(btnWith, btnHeight));
		helpBtn.setForeground(Color.WHITE);
		helpBtn.setBackground(btnColorPrimary);
		helpBtn.setAlignmentX(CENTER_ALIGNMENT);
		menuPane.add(helpBtn);
		// Boton Stats
		statsBtn = new JButton("Puntuaciones");
		statsBtn.setActionCommand("statsBtn");
		statsBtn.addActionListener(this);
		statsBtn.setFocusable(false);
		statsBtn.setFont(btnFont);
		statsBtn.setPreferredSize(new Dimension(btnWith, btnHeight));
		statsBtn.setForeground(Color.WHITE);
		statsBtn.setBackground(btnColorPrimary);
		statsBtn.setAlignmentX(CENTER_ALIGNMENT);
		menuPane.add(statsBtn);
		// Boton Salir
		exitBtn = new JButton("Salir");
		exitBtn.setActionCommand("exitBtn");
		exitBtn.addActionListener(this);
		exitBtn.setFocusable(false);
		exitBtn.setFont(btnFont);
		exitBtn.setPreferredSize(new Dimension(btnWith, btnHeight));
		exitBtn.setForeground(Color.WHITE);
		exitBtn.setBackground(btnColorPrimary);
		exitBtn.setAlignmentX(CENTER_ALIGNMENT);
		menuPane.add(exitBtn);

		/**
		 * / JPANEL CREDITOS
		 */
		creditPane = new JPanel();
		creditPane.setLayout(new BorderLayout());
		creditPane.setBackground(new Color(250, 250, 250));
		// Boton Volver
		backBtn = new JButton("Volver");
		backBtn.setActionCommand("backBtn");
		backBtn.addActionListener(this);
		backBtn.setFocusable(false);
		backBtn.setFont(creditFont);
		backBtn.setForeground(Color.WHITE);
		backBtn.setBackground(btnColorPrimary);
		backBtn.setPreferredSize(new Dimension(btnWith, btnHeight));
		backBtn.setAlignmentX(CENTER_ALIGNMENT);
		creditPane.add(backBtn, BorderLayout.SOUTH);
		// Label titulo creditos
		titleLbl = new JLabel("<html><br>Créditos</html>");
		titleLbl.setFont(creditFont);
		titleLbl.setHorizontalAlignment(JLabel.CENTER);
		creditPane.add(titleLbl, BorderLayout.NORTH);
		// Label nombres y creditos
		namesLbl = new JLabel(
				"<html>The Final Game<br>Proyecto Final de GFGS Desarrollo de Aplicaciones Multiplataforma<br>Curso 2017/18<br>IES Doctor Fleming, Oviedo<br>13 de Junio del 2018<hr><br>Carlos Ramón Ferreras Menéndez<br>Pablo Díaz Rubio</html>");
		namesLbl.setFont(creditFont);
		namesLbl.setHorizontalAlignment(JLabel.CENTER);
		creditPane.add(namesLbl, BorderLayout.CENTER);

		/**
		 * / PANEL INSTRUCCIONES
		 */
		howPane = new JPanel();
		howPane.setLayout(new BorderLayout());
		howPane.setBackground(new Color(64, 196, 255));
		howPane.setBackground(new Color(250, 250, 250));
		// Label instrucciones
		howLbl = new JLabel(
				"<html><center><b>PONG</b></center><br>Devuelve la pelota para vencer a la Inteligencia Artificial, ¿Serás capaz de derrotar a lo imposible?.<hr><br><center><b>WALL-BREAK</b></center><br>Si has llegado a este punto sabrás que no puedes parar ahora. Destruye toda la pared de ladrillos para subir de nivel.<hr><br><center><b>SNAKE</b></center><br>Aumenta tu puntuación hasta lo inalcanzable comiendo manzanas para crecer como serpiente. ¿Podrás hacerlo?.<hr><br></html> ");
		howLbl.setFont(creditFont);
		howLbl.setHorizontalAlignment(howLbl.CENTER);
		howPane.add(howLbl, BorderLayout.CENTER);
		// Boton volver
		backBtn2 = new JButton("Volver");
		backBtn2.setActionCommand("backBtn2");
		backBtn2.addActionListener(this);
		backBtn2.setFocusable(false);
		backBtn2.setFont(creditFont);
		backBtn2.setForeground(Color.WHITE);
		backBtn2.setBackground(btnColorPrimary);
		backBtn2.setPreferredSize(new Dimension(btnWith, btnHeight));
		howPane.add(backBtn2, BorderLayout.SOUTH);

		/**
		 * /PANEL PUNTUACIONES
		 */
		statsPane = new JPanel();
		statsPane.setLayout(new BorderLayout());
		statsPane.setBackground(new Color(250, 250, 250));
		// Label titulo puntuaciones
		titleStatsLbl = new JLabel("<html><center><b>PUNTUACIONES The Final Game </b></center></html>");
		titleStatsLbl.setFont(creditFont);
		titleStatsLbl.setHorizontalAlignment(titleStatsLbl.CENTER);
		statsPane.add(titleStatsLbl, BorderLayout.NORTH);
		// JTextArea con scroll para puntuaciones.
		statsLbl = new JTextArea("PLAYERNAME / GAME REACHED / POINTS \n");
		statsLbl.setFont(creditFont);
		JScrollPane scrollPane = new JScrollPane(statsLbl);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		statsPane.add(scrollPane, BorderLayout.CENTER);
		// Boton Volver
		backBtn3 = new JButton("Volver");
		backBtn3.setActionCommand("backBtn3");
		backBtn3.addActionListener(this);
		backBtn3.setFocusable(false);
		backBtn3.setFont(creditFont);
		backBtn3.setForeground(Color.WHITE);
		backBtn3.setBackground(btnColorPrimary);
		backBtn3.setPreferredSize(new Dimension(btnWith, btnHeight));
		statsPane.add(backBtn3, BorderLayout.SOUTH);

	}

	/**
	 * Metodo usado al pulsar la tecla de 'ESC' el cual nos elimina todos los
	 * paneles que podamos tener en el frame y nos añade el panel principal del
	 * menu.
	 */
	public void addMenu() {
		this.remove(howPane);
		repaint();
		revalidate();
		this.remove(creditPane);
		repaint();
		revalidate();
		this.remove(statsPane);
		repaint();
		revalidate();
		this.remove(game);
		// añadimos el menu inicio
		this.add(menuPane);
		repaint();
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Acción de boton START
		if (e.getActionCommand().equals("startBtn")) {
			// quitamos el menu
			this.remove(menuPane);
			// revalidamos el juego de inicio
			gameStage = "pong";
			// Iniciamos el thread del bucle del juego
			// game.start();
			// añadimos el juego al frame
			add(game);
			// game.setPaused(false);
			game.repaint();
			game.revalidate();
			// startBtn.setLabel("Continuar");
			// if(game.isGameOver()) {
			// startBtn.setText("Nueva Partida");
			// } else
			startBtn.setText("Continuar");

		}

		// Acción de boton CREDITOS
		else if (e.getActionCommand().equals("creditBtn")) {

			this.remove(menuPane);
			repaint();
			revalidate();
			this.add(creditPane);
			repaint();
			revalidate();

		}
		// Acción de boton INSTRUCCIONES
		else if (e.getActionCommand().equals("helpBtn")) {

			this.remove(menuPane);
			repaint();
			revalidate();
			this.add(howPane);
			repaint();
			revalidate();

		}

		// Acción de boton SALIR
		else if (e.getActionCommand().equals("exitBtn")) {

			System.exit(1);

		}

		// Acción de boton VOLVER desde creditos
		else if (e.getActionCommand().equals("backBtn")) {

			this.remove(creditPane);
			repaint();
			revalidate();

			this.add(menuPane);
			repaint();
			revalidate();

		}
		// Acción de boton VOLVER desde instrucciones
		else if (e.getActionCommand().equals("backBtn2")) {

			this.remove(howPane);
			repaint();
			revalidate();

			this.add(menuPane);
			repaint();
			revalidate();

		}
		// Acción de boton PUNTUACIONES
		else if (e.getActionCommand().equals("statsBtn")) {

			this.remove(menuPane);
			repaint();
			revalidate();

			statsLbl.setText(sdc.leerTxtStats());

			this.add(statsPane);
			repaint();
			revalidate();

		}

		// Acción de boton VOLVER desde puntuaciones
		else if (e.getActionCommand().equals("backBtn3")) {

			this.remove(statsPane);
			repaint();
			revalidate();

			this.add(menuPane);
			repaint();
			revalidate();

		}

	}

}
