package v3;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase "Manzana" para el juego Snake.
 * 
 * @author pablo
 */
public class Token {

	/**
	 * Declaramos variables de posicion x,y para colocar el token Declaramos un
	 * jugador que es el que interactua con los tokens pasando por encima de ellos
	 * Declaramos un ancho y un alto heredado de la ventana del JPanel Declaramos un
	 * tamaño de token
	 */
	private int x, y;
	private Player snake;
	private int width, height;
	private int sizeToken;

	/**
	 * Creamos un constructor parametrizado por un ancho, alto y jugador para
	 * heredar sus propiedades y usarlas en relacion a nuestro token
	 * 
	 * Inicializamos x,y a 20 y llamamos al metodo changePosition()
	 * 
	 * Inicializamos el tamaño de nuestro token a un 10% mas grande que el ancho de
	 * nuestro jugador.
	 * 
	 * @param width
	 * @param height
	 * @param s
	 */
	public Token(int width, int height, Player s) {
		this.width = width;
		this.height = height;

		x = 20;
		y = 20;
		snake = s;
		changePosition();

		sizeToken = (int) (snake.getPlayerWidth() + (snake.getPlayerWidth() * 0.1));

	}

	/**
	 * Método que asigna un valor aleatorio tanto a x como a y delimitado por el
	 * ancho y el alto de nuestro JPanel, además aumenta el valor de la misma
	 * variable hasta que esta sea divisible por el ancho del jugador dando un resto de 0.
	 */
	public void changePosition() {
		x = (int) (Math.random() * (width - snake.getPlayerWidth()));
		while (x % snake.getPlayerWidth() != 0)
			x++;
		y = (int) (Math.random() * (height - snake.getPlayerWidth()));
		while (y % snake.getPlayerWidth() != 0)
			y++;
	}

	/**
	 * Metodo de dibujado de nuestro token.
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(new Color(159, 20, 20));
		g.fillRect(x, y, sizeToken, sizeToken);
	}

	/**
	 * Metodo que devuelve true cuando nuestro jugador colisiona con el token
	 * @return
	 */
	public boolean snakeColision() {
		int snakeX = snake.getXsnake() + 2;
		int snakeY = snake.getYsnake() + 2;
		
		
		if (snakeX >= x - 1 && snakeX <= (x + sizeToken + 1))
			if (snakeY >= y - 1 && snakeY <= (y + sizeToken + 1)) {
				changePosition();
				snake.setScore(snake.getScore() + 1);
				snake.setElongate(true);
				return true;
			}
		return false;

	}

}
