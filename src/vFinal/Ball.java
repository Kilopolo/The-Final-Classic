package vFinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Ball {

	private double xVel, yVel, x, y;
	private int width, height;
	private int diametro;

	public Ball(int width, int height) {
		this.width = width;
		this.height = height;
		x = (width / 2) - 10;
		y = (height / 2) - 10;
		xVel = getRandomSpeed() * getRandomDirection();
		yVel = getRandomSpeed() * getRandomDirection();
		diametro = 20;

	}

	public void draw(Graphics g) {
		g.setColor(new Color(255, 141, 0));
		g.fillOval((int) x, (int) y, 20, 20);
	}

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

	public boolean crossCenter() {
		if (x >= (width / 2) - 10 && x <= (width / 2) + 10) {
			return true;
		}
		return false;
	}

	public void checkPaddleCollision(Player player, AIPaddle AI) {
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
		if (x >= width - (player.getPlayerWidth() + 20 + 10)) {
			if (y >= AI.getY() && y <= AI.getY() + player.getPlayerHeight()) {
				xVel = -xVel;
				player.setScore(player.getScore() + 1);
			}
		}
	}

	public void checkPaddleAndBrickCollision(Player player, ArrayList<Brick> bricks) {
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

	public void newBall() {
		x = (width / 2) - 10;
		y = (height / 2) - 10;
		xVel = getRandomSpeed() * getRandomDirection();
		yVel = getRandomSpeed() * getRandomDirection();
	}

	public double getRandomSpeed() {
		return (Math.random() * 5 + 2);
	}

	public int getRandomDirection() {
		int rand = (int) (Math.random() * 2);
		if (rand == 1) {
			return 1;
		} else
			return -1;
	}

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
