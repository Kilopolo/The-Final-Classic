package v3;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase AIPaddle 'Raqueta Inteligencia Artificial' que nos crea un enemigo
 * invencible en el juego pong.
 * 
 * Declaramos un ancho, alto de JPanel, una posicion x,y y tan solo una
 * velocidad de y ya que solamente se va a mover por el eje y. En verdad no
 * haría falta declarar la mayoria de estas variables, pero estan pensadas para
 * futuras implementaciones del juego en las cuales nuestra Inteligencia
 * artificial mejore y deje de ser invencible. Declaramos una constante Gravedad
 * por el mismo motivo.
 * 
 * Declaramos una pelota para poder acceder a sus propiedades y un jugador por
 * el mismo motivo.
 * 
 * @author p.diaz
 *
 */
public class AIPaddle {

	private int width, height;
	private double y, yVel;
	private boolean upAccel, downAccel;
	private int x;
	private final double GRAVITY = 0.94;
	private Ball ball;
	private Player player;

	/**
	 * Constructor que nos iguala a las variables globales de clase los parametros
	 * de Jugador, Pelota, Ancho y Alto.
	 * 
	 * Establecemos la aceleracion vertical a falso(upAccel,downAccel).
	 * 
	 * Inicializamos la raqueta AI en su valor y a el centro de la pantalla y en su
	 * posición x a la derecha de la pantalla menos el margen de seguridad y el
	 * tamaño de ancho de la raqueta.
	 * 
	 * @param player
	 * @param ball
	 * @param width
	 * @param height
	 */
	public AIPaddle(Player player, Ball ball, int width, int height) {

		this.player = player;
		this.width = width;
		this.height = height;
		this.ball = ball;

		upAccel = false;
		downAccel = false;

		y = (int) this.height / 2;
		yVel = 0;

		x = width - player.getPlayerWidth() - 20;

	}

	/**
	 * Metodo de dibujado de la raqueta AI.
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(new Color(194, 32, 19));
		g.fillRect(x, (int) y, player.getPlayerWidth(), player.getPlayerHeight());

	}

	/**
	 * Metodo de movimiento de la raqueta AI. Solamente iguala el centro de la
	 * raqueta a el valor y de la pelota, por lo tanto siempre va a devolver la
	 * pelota
	 */
	public void move() {

		y = ball.getY() - 40;

		if (y < 0)
			y = 0;
		if (y > height - player.getPlayerHeight())
			y = height - player.getPlayerHeight();
	}

	
	//GETTERS Y SETTERS
	public int getY() {
		return (int) y;
	}

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

	public double getyVel() {
		return yVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public boolean isUpAccel() {
		return upAccel;
	}

	public void setUpAccel(boolean upAccel) {
		this.upAccel = upAccel;
	}

	public boolean isDownAccel() {
		return downAccel;
	}

	public void setDownAccel(boolean downAccel) {
		this.downAccel = downAccel;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
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

	public double getGRAVITY() {
		return GRAVITY;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	

}
