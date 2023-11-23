package vFinal;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Brick {

	private int width, height, x, y, ID;
	private Player player;
	private Random r;
	private boolean brickAlive;
	private int sumaColor, sumaColor2;

	public Brick(Player player, int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.player = player;
		r = new Random();
		brickAlive = true;
	}

	public Brick(Player player, int x, int y, int ID) {
		this.x = x;
		this.y = y;
		this.player = player;
		this.ID = ID;
		r = new Random();
		sumaColor = r.nextInt(50);
		sumaColor2 = r.nextInt(50) + 50;
		brickAlive = true;
	}

	public void draw(Graphics g) {
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
