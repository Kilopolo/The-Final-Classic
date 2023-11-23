package vFinal;

import java.awt.Color;
import java.awt.Graphics;

public class AIPaddle {

	private int width, height;
	private double y, yVel;
	private boolean upAccel, downAccel;
	private int x;
	private final double GRAVITY = 0.94;
	private Ball ball;
	private Player player;

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

	public void draw(Graphics g) {
		g.setColor(new Color(194, 32, 19));
		g.fillRect(x, (int) y, player.getPlayerWidth(), player.getPlayerHeight());
	}

	public void move() {
		y = ball.getY() - 40;
		if (y < 0)
			y = 0;
		if (y > height - player.getPlayerHeight())
			y = height - player.getPlayerHeight();
	}

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
