package v3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Clase Ball 'Pelota' que sirve como pelota en los juegos pong y wallBreak;
 * 
 * Declaramos una posicion (x,y) y una velocidad de x e y.
 * 
 * Declaramos un ancho y alto de JPanel para saber por donde se puede mover la
 * pelota.
 * 
 * @author p.diaz
 *
 */
public class Ball {

	private double xVel, yVel, x, y;
	private int width, height;
	private int diametro;

	/**
	 * Constructor con parametros ancho y alto para heredar sus propiedades y luego
	 * usarlas como delimitadores. En un principio la pelota empieza en el centro de
	 * nuestro JPanel y adopta una velocidad y dirección random para su movimiento.
	 * 
	 * @param width
	 * @param height
	 */
	public Ball(int width, int height) {
		this.width = width;
		this.height = height;
		x = (width / 2) - 10;
		y = (height / 2) - 10;
		xVel = getRandomSpeed() * getRandomDirection();
		yVel = getRandomSpeed() * getRandomDirection();
		diametro = 20;

	}

	/**
	 * Metodo que sirve para dibujar la pelota.
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(new Color(255, 141, 0));
		g.fillOval((int) x, (int) y, 20, 20);
	}

	/**
	 * Metodo que sirve para mover la pelota en el JPanel aplicandole el juego en el
	 * que estemos para que adopte unas propiedades o otras. En caso del pong la
	 * pared derecha o la del enemigo esta abierta en caso de que la IA no consiga
	 * devolver la pelota(imposible) y en el wallbreak si choca en esta pared se
	 * invierte la velocidad de su coordenada para que haya un rebote.
	 * 
	 * En caso de que el parametro godmode sea true no hay posibilidad de que la
	 * pelota se salga por ningun lado para imposibilitar así que el juego entre en
	 * gameOver.
	 * 
	 * @param game
	 * @param godmode
	 */
	public void move(String game, boolean godmode) {
		if (game.equals("pong")) {
			x += xVel;
			y += yVel;

			if (y < 10) {
				yVel = -yVel;
			}
			if (y > height - 10) {
				yVel = -yVel;
			}
			if (godmode) {
				if (x < 10) {
					xVel = -xVel;
				}
			}

		} else if (game.equals("wall")) {
			x += xVel;
			y += yVel;

			if (y < 10) {
				yVel = -yVel;
			}
			if (y > height - 10) {
				yVel = -yVel;
			}
			if (x > width - 10) {
				xVel = -xVel;
			}

			if (godmode) {
				if (x < 10) {
					xVel = -xVel;
				}
			}

		} else if (game.equals("snake")) {
			x += xVel;
			y += yVel;

			yVel = 0;
			xVel = 0;
		} else if (game.equals("pruebas")) {
			x += xVel;
			y += yVel;

			if (y < 10) {
				yVel = -yVel;
			}
			if (y > height - 10) {
				yVel = -yVel;
			}
			if (x > width - 10) {
				xVel = -xVel;
			}
			if (x < 10) {
				xVel = -xVel;
			}
		}

	}

	/**
	 * Metodo que devuelve true por cada vez que la pelota cruza el centro de
	 * nuestra pantalla.
	 * 
	 * @return
	 */
	public boolean crossCenter() {
		if (x >= (width / 2) - 10 && x <= (width / 2) + 10) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo que comprueba cuando la pelota colisiona con una raqueta para asi
	 * invertir su velocidad y se muestre como un rebote. En este metodo se pasan
	 * por parametro tanto el jugador como la raqueta de la AI para así poder
	 * detectar ambos.
	 * 
	 * Creamos un rectangulo que almacene el rectangulo de la pelota, otro que
	 * almacene el de la raqueta del jugador, este lo dividimos en dos partes,
	 * superior e inferior. Cada una de estas partes devuelve la direccion de x
	 * invertida, pero la direccion de y dependera de que rectangulo colisione: en
	 * el caso de que sea el superior y la velocidad de desplazamiento de la y sea
	 * positiva nos la invertira si no nó. En el caso de que la colisión sea con el
	 * rectángulo inferior y la velocidad de desplazamiento de la variable y de la
	 * pelota sea negativa, invertimos nuevamente su valor. esto nos da mas
	 * oportunidades a la hora de jugar y mas flexibilidad en cuanto a la hora de
	 * devolver la pelota con la raqueta.
	 * 
	 * En el caso de la raqueta de la AI, simplemente, invertimos la direccion de la
	 * cordenada x de la pelota en el momento de que esta colisione con el frontal
	 * de la raqueta. En ese mismo instante añadimos un punto a el jugador para que
	 * el juego pueda avanzar.
	 * 
	 * @param player
	 * @param AI
	 */
	public void checkPaddleCollision(Player player, AIPaddle AI) {

		// RECTANGLE
		Rectangle rBall = getRectangleBall();
		Rectangle rPaddle = player.getRectanglePaddle();
		Rectangle rPaddleSup = new Rectangle((int) rPaddle.getX(), (int) rPaddle.getY(), (int) rPaddle.getWidth(),
				(int) rPaddle.getHeight() / 2);
		Rectangle rPaddleInf = new Rectangle((int) rPaddle.getX(), (int) rPaddle.getCenterY(), (int) rPaddle.getWidth(),
				(int) rPaddle.getHeight() / 2);
		if (rBall.intersects(rPaddle)) {
			if (rBall.intersects(rPaddleSup)) {
				if (yVel > 0) {
					yVel = -yVel;
				}
			}
			if (rBall.intersects(rPaddleInf)) {
				if (yVel < 0) {
					yVel = -yVel;
				}
			}
			xVel = -xVel;
		}

		// FRONTAL AI
		if (x >= width - (player.getPlayerWidth() + 20 + 10)) {
			if (y >= AI.getY() && y <= AI.getY() + player.getPlayerHeight()) {
				xVel = -xVel;
				player.setScore(player.getScore() + 1);
			}
		}
	}

	/**
	 * Metodo que detecta cuando la pelota choca con un ladrillo del Arrray de
	 * ladrillos o con el jugador.
	 * 
	 * @param player
	 * @param bricks
	 */
	public void checkPaddleAndBrickCollision(Player player, ArrayList<Brick> bricks) {
//		if (x <= player.getPlayerWidth() + 20 + 10) {
//			if (y >= player.getY() && y <= player.getY() + player.getPlayerHeight()) {
//				xVel = -xVel;
//			}
//		}
		
		// RECTANGLE
		Rectangle rBall = getRectangleBall();
		Rectangle rPaddle = player.getRectanglePaddle();
		Rectangle rPaddleSup = new Rectangle((int) rPaddle.getX(), (int) rPaddle.getY(), (int) rPaddle.getWidth(),
				(int) rPaddle.getHeight() / 2);
		Rectangle rPaddleInf = new Rectangle((int) rPaddle.getX(), (int) rPaddle.getCenterY(), (int) rPaddle.getWidth(),
				(int) rPaddle.getHeight() / 2);
		if (rBall.intersects(rPaddle)) {
			if (rBall.intersects(rPaddleSup)) {
				if (yVel > 0) {
					yVel = -yVel;
				}
			}
			if (rBall.intersects(rPaddleInf)) {
				if (yVel < 0) {
					yVel = -yVel;
				}
			}
			xVel = -xVel;
		}
		
		

		for (int j = 0; j < bricks.size(); j++) {
			bricks.get(j).brickColision(this);
			if (!bricks.get(j).isBrickAlive()) {
				bricks.remove(j);
				break;
			}
		}

	}

	/**
	 * Metodo que genera una nueva pelota con una nueva direccion y velocidad.
	 */
	public void newBall() {

		x = (width / 2) - 10;
		y = (height / 2) - 10;
		xVel = getRandomSpeed() * getRandomDirection();
		yVel = getRandomSpeed() * getRandomDirection();
	}

	/**
	 * Metodo para generar una velocidad randomizada entre unos valores seguros.
	 * 
	 * @return
	 */
	public double getRandomSpeed() {
		return (Math.random() * 5 + 2);
	}

	/**
	 * Metodo que nos genera una direccion aleatoria , ya sea 1 o -1 para poder
	 * multiplicarla por la velocidad y asi nos de una velocidad con una direccion
	 * aleatoria. Si esto no se hiciese la velocidad siempre seria positiva, por lo
	 * cual nunca tendriamos una pelota que sale hacia la izquierda.
	 * 
	 * @return
	 */
	public int getRandomDirection() {
		int rand = (int) (Math.random() * 2);
		if (rand == 1) {
			return 1;
		} else
			return -1;
	}

	
	//GETTERS Y SETTERS
	public double getxVel() {
		return xVel;
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

	public int getDiametro() {
		return diametro;
	}

	public void setDiametro(int diametro) {
		this.diametro = diametro;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Rectangle getRectangleBall() {
		return new Rectangle((int) getX(), (int) getY(), diametro, diametro);
	}

}
