package vFinal;
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

public class MainClass extends JFrame implements ActionListener {
	private static final long serialVersionUID = 2350704171326530882L;
	public static final int WIDTH = 800, HEIGHT = 600;
	private Game game;
	private JPanel menuPane, creditPane, howPane, statsPane;
	private String gameStage;
	private JButton startBtn, creditBtn, helpBtn, exitBtn, backBtn, backBtn2, backBtn3, resumeBtn, statsBtn;
	private JLabel titleLbl, namesLbl, howLbl, titleStatsLbl;
	private JTextArea statsLbl;
	private SaveDataManager sdc;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainClass();
			}
		});
	}

	public MainClass() {
		setTitle("The Final Game");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		File f = new File(".//savedataTFG.txt");
		sdc = new SaveDataManager(f, game);
		sdc.iniSortedSaveManager();
		gameStage = "pong";
		game = new Game(WIDTH, HEIGHT, gameStage, this, sdc);
		game.setPaused(true);
		this.addKeyListener(new KeyListenGame(game, this));
		int btnWith = 270;
		int btnHeight = 90;
		Font btnFont = new Font("Arial", Font.BOLD, 40);
		Font creditFont = new Font("Arial", Font.BOLD, 21);
		Color btnColorPrimary = new Color(2, 167, 238);
		menuPane = new JPanel();
		menuPane.setLayout(new GridLayout(5, 0));
		menuPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		menuPane.setBackground(new Color(250, 250, 250));
		add(menuPane);
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
		resumeBtn = new JButton("Continuar");
		resumeBtn.setActionCommand("resumeBtn");
		resumeBtn.addActionListener(this);
		resumeBtn.setFocusable(false);
		resumeBtn.setFont(btnFont);
		resumeBtn.setPreferredSize(new Dimension(btnWith, btnHeight));
		resumeBtn.setForeground(Color.WHITE);
		resumeBtn.setBackground(btnColorPrimary);
		resumeBtn.setAlignmentX(CENTER_ALIGNMENT);
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
		creditPane = new JPanel();
		creditPane.setLayout(new BorderLayout());
		creditPane.setBackground(new Color(250, 250, 250));
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
		titleLbl = new JLabel("<html><br>Créditos</html>");
		titleLbl.setFont(creditFont);
		titleLbl.setHorizontalAlignment(JLabel.CENTER);
		creditPane.add(titleLbl, BorderLayout.NORTH);
		namesLbl = new JLabel(
				"<html>The Final Game<br>Proyecto Final de GFGS Desarrollo de Aplicaciones Multiplataforma<br>Curso 2017/18<br>IES Doctor Fleming, Oviedo<br>13 de Junio del 2018<hr><br>Carlos Ramón Ferreras Menéndez<br>Pablo Díaz Rubio</html>");
		namesLbl.setFont(creditFont);
		namesLbl.setHorizontalAlignment(JLabel.CENTER);
		creditPane.add(namesLbl, BorderLayout.CENTER);
		howPane = new JPanel();
		howPane.setLayout(new BorderLayout());
		howPane.setBackground(new Color(64, 196, 255));
		howPane.setBackground(new Color(250, 250, 250));
		howLbl = new JLabel(
				"<html><center><b>PONG</b></center><br>Devuelve la pelota para vencer a la Inteligencia Artificial, ¿Serás capaz de derrotar a lo imposible?.<hr><br><center><b>WALL-BREAK</b></center><br>Si has llegado a este punto sabrás que no puedes parar ahora. Destruye toda la pared de ladrillos para subir de nivel.<hr><br><center><b>SNAKE</b></center><br>Aumenta tu puntuación hasta lo inalcanzable comiendo manzanas para crecer como serpiente. ¿Podrás hacerlo?.<hr><br></html> ");
		howLbl.setFont(creditFont);
		howLbl.setHorizontalAlignment(howLbl.CENTER);
		howPane.add(howLbl, BorderLayout.CENTER);
		backBtn2 = new JButton("Volver");
		backBtn2.setActionCommand("backBtn2");
		backBtn2.addActionListener(this);
		backBtn2.setFocusable(false);
		backBtn2.setFont(creditFont);
		backBtn2.setForeground(Color.WHITE);
		backBtn2.setBackground(btnColorPrimary);
		backBtn2.setPreferredSize(new Dimension(btnWith, btnHeight));
		howPane.add(backBtn2, BorderLayout.SOUTH);
		statsPane = new JPanel();
		statsPane.setLayout(new BorderLayout());
		statsPane.setBackground(new Color(250, 250, 250));
		titleStatsLbl = new JLabel("<html><center><b>PUNTUACIONES The Final Game </b></center></html>");
		titleStatsLbl.setFont(creditFont);
		titleStatsLbl.setHorizontalAlignment(titleStatsLbl.CENTER);
		statsPane.add(titleStatsLbl, BorderLayout.NORTH);
		statsLbl = new JTextArea("PLAYERNAME / GAME REACHED / POINTS \n");
		statsLbl.setFont(creditFont);
		JScrollPane scrollPane = new JScrollPane(statsLbl);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		statsPane.add(scrollPane, BorderLayout.CENTER);
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
		this.add(menuPane);
		repaint();
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("startBtn")) {
			this.remove(menuPane);
			gameStage = "pong";
			add(game);
			game.repaint();
			game.revalidate();
			startBtn.setText("Continuar");
		} else if (e.getActionCommand().equals("creditBtn")) {
			this.remove(menuPane);
			repaint();
			revalidate();
			this.add(creditPane);
			repaint();
			revalidate();
		} else if (e.getActionCommand().equals("helpBtn")) {
			this.remove(menuPane);
			repaint();
			revalidate();
			this.add(howPane);
			repaint();
			revalidate();
		} else if (e.getActionCommand().equals("exitBtn")) {
			System.exit(1);
		} else if (e.getActionCommand().equals("backBtn")) {
			this.remove(creditPane);
			repaint();
			revalidate();
			this.add(menuPane);
			repaint();
			revalidate();
		} else if (e.getActionCommand().equals("backBtn2")) {
			this.remove(howPane);
			repaint();
			revalidate();
			this.add(menuPane);
			repaint();
			revalidate();
		} else if (e.getActionCommand().equals("statsBtn")) {
			this.remove(menuPane);
			repaint();
			revalidate();
			statsLbl.setText(sdc.leerTxtStats());
			this.add(statsPane);
			repaint();
			revalidate();
		} else if (e.getActionCommand().equals("backBtn3")) {
			this.remove(statsPane);
			repaint();
			revalidate();
			this.add(menuPane);
			repaint();
			revalidate();
		}
	}
}