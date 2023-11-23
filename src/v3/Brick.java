package v3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Clase Brick del juego wallBreak. Esta clase nos permite crear ladrillos con
 * diferentes caracteristicas en nuestro juego.
 * 
 * Declaramos un ancho, alto de JPanel, además de una posicion x,y en la cual va
 * a estar colocado nuestro ladrillo. Finalmente le damos un ID para poder
 * identificar a el ladrillo.
 * 
 * Declaramos un Jugador para poder sacar caracteristicas del mismo, un Random
 * para nuestro segundo constructor el cual nos da un color aleatorio para hacer
 * un degradado con los ladrillos. Ademas en un futuro puede que se implemente
 * colocación aleatoria de ladrillos en la pantalla.
 * 
 * Declaramos un boolean brickAlive para establecer un estado de ladrillo(vivo o
 * muerto). Declaramos variables para hacer el degradado de los
 * ladrillos(sumaColor).
 * 
 * @author p.diaz
 *
 */
public class Brick {

	private int width, height, x, y, ID;

	// private Ball ball;
	private Player player;
	private Random r;
	private boolean brickAlive;
	private int sumaColor, sumaColor2;

	/**
	 * Constructor sobrecargado en desuso para futuras implementaciones del juego.
	 * 
	 * @param player
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 */
	public Brick(Player player, int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.player = player;
		// this.ball = ball;
		r = new Random();
		brickAlive = true;
	}

	/**
	 * Constructor de ladrillos que le pasamos por parametro la posición en la que
	 * va a estar colocado nuestro ladrillo, un jugador para obtener sus
	 * características y un ID para identificar nuestro ladrillo.
	 * 
	 * @param player
	 * @param x
	 * @param y
	 * @param ID
	 */
	public Brick(Player player, int x, int y, int ID) {
		this.x = x;
		this.y = y;
		this.player = player;
		// this.ball = ball;
		this.ID = ID;
		r = new Random();
		sumaColor = r.nextInt(50);
		sumaColor2 = r.nextInt(50) + 50;
		brickAlive = true;
	}

	/**
	 * Metodo de dibujado de cada ladrillo en relación a su ID.
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		// g.setColor(new
		// Color(r.nextInt(150)+100,r.nextInt(150)+100,r.nextInt(150)+100));
		g.setColor(new Color(50, 50, 50));
		g.fillRect(x, (int) y, player.getPlayerWidth(), player.getPlayerHeight());
		if (ID < 6) {
			g.setColor(new Color(150 + sumaColor2, 70 + sumaColor2, 50 + sumaColor2));
		} else if (ID < 12) {
			g.setColor(new Color(150 + sumaColor, 70 + sumaColor, 50 + sumaColor));
		} else if (ID < 18) {
			g.setColor(new Color(150, 70, 50));
		}
		g.fillRect(x + 1, ((int) y) + 1, player.getPlayerWidth() - 2, player.getPlayerHeight() - 2);

	}

	/**
	 * Metodo que le pasamos por parametro una pelota para determinar si la posición
	 * de esta colisiona con la posición de alguno de nuestros ladrillos.
	 * 
	 * en caso de que así sea establecemos el ladrillo como no vivo, la pelota rebota, y la puntuacion del jugador aumenta.
	 * 
	 * ademas devolvemos true en caso de que exista colisión.
	 * 
	 * @param ball
	 * @return
	 */
	public boolean brickColision(Ball ball) {

		if (ball.getX() + 10 >= this.x && ball.getX() + 10 <= (this.x + player.getPlayerWidth())) {
			if (ball.getY() >= this.y && ball.getY() <= (this.y + player.getPlayerHeight())) {

				setBrickAlive(false);
				ball.setxVel(ball.getxVel() * -1);
				player.setScore(player.getScore() + 1);
				return true;
			}
		}
		setBrickAlive(true);
		return false;
	}

	// GETTER Y SETTERS
	public boolean isBrickAlive() {
		return brickAlive;
	}

	public void setBrickAlive(boolean brickAlive) {
		this.brickAlive = brickAlive;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	public void imprimir() {
		System.out.println("Point [x=" + x + ", y=" + y + "]");

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

}
