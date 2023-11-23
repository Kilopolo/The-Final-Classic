package vFinal;

import java.awt.Color;
import java.awt.Graphics;

public class Token {

	private int x, y;
	private Player snake;
	private int width, height;
	private int sizeToken;

	public Token(int width, int height, Player s) {
		this.width = width;
		this.height = height;
		x = 20;
		y = 20;
		snake = s;
		changePosition();
		sizeToken = (int) (snake.getPlayerWidth() + (snake.getPlayerWidth() * 0.1));
	}

	public void changePosition() {
		x = (int) (Math.random() * (width - snake.getPlayerWidth()));
		while (x % snake.getPlayerWidth() != 0)
			x++;
		y = (int) (Math.random() * (height - snake.getPlayerWidth()));
		while (y % snake.getPlayerWidth() != 0)
			y++;
	}

	public void draw(Graphics g) {
		g.setColor(new Color(159, 20, 20));
		g.fillRect(x, y, sizeToken, sizeToken);
	}

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
