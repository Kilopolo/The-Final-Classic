package v3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Variables comunes a todos los juegos: ancho y alto de JPanel, Puntuacion,
 * Nombre del jugador.
 * 
 * posicion 'y' como doble ya que va a estar en movimiento y probablemente se
 * quede en una posicion que no sea un numero entero.
 * 
 * yVel: Numero aplicado para sumar o restar a la posicion de 'y' y asi darle
 * movimiento
 * 
 * Booleanos upAccel y downAccel: sirven para determinar hacia que lado se esta
 * moviendo nuestro jugador, ya sea hacia arriba o hacia abajo.
 * 
 * Constante GRAVITY para que nuestra raqueta no se pare de golpe cuando dejamos
 * de apretar hacia arriba o hacia abajo. Multiplicando un numero por otro que
 * es menor que 1 conseguimos que nuestro valor que determina el movimiento se
 * vaya reduciendo poco a poco hasta llegar a cero.
 * 
 * List<Point> snakePoints que va a guardar todos los puntos que conforman
 * nuestra serpiente y va a ser aumentado por cada vez que la serpiente
 * colisione con un token.
 * 
 * xDir y yDir son las variables que determinan la direccion que tomara la
 * serpiente.
 * 
 * Boolean isMoving determina si nuestra serpiente esta en movimiento o esta
 * quieta.
 * 
 * Boolean elongate nos sirve para aumentar el tamaño de la serpiente.
 * 
 * Variable startSize es el tamaño de nuestra serpiente en un comienzo del
 * juego.
 * 
 * Variables playerWidth y playerHeight nos dan el tamaño de nuestro jugador
 * "raqueta/paddle".
 * 
 * 
 * 
 * 
 * @author pablo
 */
public class Player {

	private int width, height;
	private int score;
	private String name;
	private double y, yVel;
	private boolean upAccel, downAccel;
	private int x, xVel;
	private final double GRAVITY = 0.94;
	private List<Point> snakePoints;
	private int xDir, yDir, countVel;
	private boolean isMoving, elongate;
	private final int startSize = 8, playerWidth = 20, playerHeight = 80;

	/**
	 * Ya que tanto para el pong como para el wallBreak necesitamos una raqueta
	 * usamos la misma.
	 * 
	 * Inicializamos upAccel y downAccel a falso para que al principio nuestra
	 * raqueta este quieta.
	 * 
	 * establecemos la posicion del jugador y la velocidad del mismo a 0 ya qque en
	 * un principio esta quieto.
	 * 
	 * Inicializamos el List<> de nuestra serpiente, declaramos que la direccion
	 * tanto de x como y es 0 para que continue estática.
	 * Inicializamos a 0 la variable de control de velocidad del juego.
	 * Establecemos a false isMoving y elongate para que nuestra serpiente este quieta y no crezca.
	 * Añadimos los puntos iniciales al array de puntos de nuestra serpiente.
	 * 
	 * @param width
	 * @param height
	 */
	public Player(int width, int height) {
		// all
		this.width = width;
		this.height = height;

		// pong y wall
		upAccel = false;
		downAccel = false;
		y = (height / 2) - playerHeight;
		yVel = 0;
		x = 20;
		xVel = 0;

		// snake
		snakePoints = new ArrayList<Point>();
		xDir = 0;
		yDir = 0;
		countVel = 0;
		isMoving = false;
		elongate = false;

		snakePoints.add(new Point(x, (int) y));
		for (int i = 0; i < startSize; i++) {
			snakePoints.add(new Point(x, (int) (y - i * getPlayerWidth())));
		}

	}

	/**
	 * Metodo que nos restablece nuestra serpiente a la posicion inicial, establece
	 * que no se esta moviendo y que no esta creciendo. Ademas nos restablece la
	 * cantidad de puntos que contiene a el valor inicial.
	 */
	public void resetSnake() {
		snakePoints = new ArrayList<Point>();
		xDir = 0;
		yDir = 0;

		isMoving = false;
		elongate = false;

		// le decimos donde esta colocada la cabeza.
		snakePoints.add(new Point(x, (int) y));
		// rellenamos el tamaño inicial de la serpiente
		for (int i = 0; i < startSize; i++) {
			snakePoints.add(new Point(x, (int) (y - i * getPlayerWidth())));
		}
	}

	/**
	 * 
	 * 
	 * Metodo que nos dibuja a nuestro jugador de una forma u otra con respecto a el
	 * juego en el que estemos jugando. Este metodo solo tiene valor gráfico en
	 * nuestro juego.
	 * 
	 * @param g
	 * @param game
	 */
	public void draw(Graphics g, String game) {
		if (game.equals("pong")) {
			g.setColor(new Color(250, 250, 250));
			g.fillRect(x, (int) y, playerWidth, playerHeight);
		} else if (game.equals("wall")) {
			g.setColor(new Color(198, 150, 10));
			g.fillRect(x, (int) y, playerWidth, playerHeight);

		} else if (game.equals("snake")) {
			g.setColor(new Color(110, 150, 10));

			for (Point p : snakePoints) {

				g.fillRect(p.getX(), p.getY(), playerWidth, playerWidth);
			}
		}

	}

	/**
	 * Metodo que mueve nuestro jugador de forma distinta con respecto el juego que
	 * le pasemos por parametro.
	 * 
	 * @param game
	 */
	public void move(String game) {

		if (game.equals("pong")) {
			/**
			 * dentro del juego pong solo necesitamos que nuestra raqueta se mueva de arriba
			 * a abajo asique solo hacemos que la y tenga cambios de valor.
			 */
			if (upAccel) {
				yVel -= 2;
			} else if (downAccel) {
				yVel += 2;
			} else if (!upAccel && !downAccel) {
				yVel *= GRAVITY;
			}

			if (yVel >= 5) {
				yVel = 5;
			} else if (yVel <= -5) {
				yVel = -5;
			}
			y += yVel;

			if (y < 0)
				y = 0;
			if (y > (height - playerHeight))
				y = (height - playerHeight);
		} else if (game.equals("wall")) {
			/**
			 * dentro del juego wallBreak solo necesitamos que nuestra raqueta se mueva de
			 * arriba a abajo asique solo hacemos que la y tenga cambios de valor.
			 */
			if (upAccel) {
				yVel -= 2;
			} else if (downAccel) {
				yVel += 2;
			} else if (!upAccel && !downAccel) {
				yVel *= GRAVITY;
			}

			if (yVel >= 5) {
				yVel = 5;
			} else if (yVel <= -5) {
				yVel = -5;
			}
			y += yVel;

			if (y < 0)
				y = 0;
			if (y > (height - playerHeight))
				y = (height - playerHeight);
		} else if (game.equals("snake")) {
			/**
			 * teniendo en cuenta que estos tres juegos son en realidad un unico juego, no
			 * podemos dejar la velocidad de la serpiente con tan poco tiempo de reacción,
			 * asi que, lo primero que hacemos es dividir la cantidad de veces que entra en
			 * el metodo move() para que asi vaya a una velocidad asequible. Lo segundo que
			 * hacemos es detectar que nuestra serpiente se esta moviendo o no para que
			 * cuando así sea le metamos las variables de los controles necesarios. Primero
			 * almacenamos en un Punto temporal los atributos de la cabeza de la
			 * serpiente(x,y,...). Lo segundo almacenamos en otro punto temporal los datos
			 * de la cola. Finalmente almacenamos en otro punto temporal un nuevo punto que
			 * es el que se va a desplazar la cabeza de forma que asi parezca que hay
			 * movimiento. Ahora recorremos desde la cola hasta la cabeza todos los puntos
			 * de nuestra serpiente asignandole a cada uno de esos puntos el punto mas
			 * cercano a ellos por parte de la cabeza. Todo lo anterior solo nos moveria la
			 * serpiente. En caso de hacerla crecer necesitamos que la variable boolean
			 * elongate sea true y asi añadir al finar de nuestro List<Point>snakepoints la
			 * variable temporal que ya habiamos almacenado en un punto de nuestra cola de
			 * la serpiente. de esta forma cuando nuestra serpiente colisione con un token
			 * se habra movido hacia la misma posición que estaba este token, pero como
			 * ahora nuestra lista de snakePoints es mas larga parecera que la cola sigue en
			 * el mismo punto ya que ahi es donde se colocara nuestro ultimo punto de la
			 * serpiente.
			 */
			countVel++;
			// dividimos la velocidad a la mitad
			if (countVel > 2) {

				if (isMoving) {
					Point temp = snakePoints.get(0);
					// System.out.println("Temp: " + temp.toString());

					Point last = snakePoints.get(snakePoints.size() - 1);
					// System.out.println("Last: " + last.toString());

					// consigue el nuevo punto en el que se va a desplazar la cabeza
					Point newStart = new Point(temp.getX() + xDir * playerWidth, temp.getY() + yDir * playerWidth);
					// System.out.println("newStart: " + newStart.toString());
					// System.out.println();
					for (int i = snakePoints.size() - 1; i >= 1; i--) {
						// mueve desde la cola cada cuadrado parte de la serpiente a la posicion donde
						// se encontraba el cubo mas cercano suyo en direccion a la cabeza
						snakePoints.set(i, snakePoints.get(i - 1));
					}
					// mueve la cabeza de la serpiente a donde se este desplazando el usuario
					snakePoints.set(0, newStart);
					if (elongate) {
						snakePoints.add(last);
						elongate = false;
					}
				}

				countVel = 0;
			}

		}

	}

	/**
	 * Metodo que detecta cuando un punto de nuestra serpiente colisiona con otro
	 * punto de si misma.
	 * 
	 * Para detectar esto simplemente comparamos la posicion de la cabeza de nuestra
	 * serpiente con la posicion de todos los puntos de la misma. En caso de que
	 * sean igual significa que la serpiente se ha chocado y el metodo devolvera
	 * true.
	 * 
	 * @return
	 */
	public boolean snakeCollision() {
		int xHead = this.getXsnake();
		int yHead = this.getYsnake();

		for (int i = startSize; i < snakePoints.size(); i++) {
			if (snakePoints.get(i).getX() == xHead && snakePoints.get(i).getY() == yHead) {
				return true;
			}
		}
		return false;
	}

	// PONG
	/**
	 * Creamos un rectangulo con la libreria rectangle para que tengamos una raqueta
	 * mas precisa y luego poder usar intersects();
	 * 
	 * @return
	 */
	public Rectangle getRectanglePaddle() {
		return new Rectangle(getX(), (int) getY(), getPlayerWidth(), getPlayerHeight());
	}

	// --- GETTER Y SETTERS ---
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
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

	public int getxVel() {
		return xVel;
	}

	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	public double getGRAVITY() {
		return GRAVITY;
	}
	// SNAKE

	public int getxDir() {
		return xDir;
	}

	public void setxDir(int xDir) {
		this.xDir = xDir;
	}

	public int getyDir() {
		return yDir;
	}

	public void setyDir(int yDir) {
		this.yDir = yDir;
	}

	public int getXsnake() {
		return snakePoints.get(0).getX();
	}

	public int getYsnake() {
		return snakePoints.get(0).getY();
	}

	public boolean isElongate() {
		return elongate;
	}

	public void setElongate(boolean elongate) {
		this.elongate = elongate;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public int getPlayerWidth() {
		return playerWidth;
	}

	public int getPlayerHeight() {
		return playerHeight;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Point> getSnakePoints() {
		return snakePoints;
	}

	public void setSnakePoints(List<Point> snakePoints) {
		this.snakePoints = snakePoints;
	}

	public int getCountVel() {
		return countVel;
	}

	public void setCountVel(int countVel) {
		this.countVel = countVel;
	}

	public int getStartSize() {
		return startSize;
	}

}
