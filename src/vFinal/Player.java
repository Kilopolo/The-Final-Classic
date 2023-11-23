package vFinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

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
	
	public Player(int width, int height) {
		this.width = width;
		this.height = height;
		upAccel = false;
		downAccel = false;
		y = (height / 2) - playerHeight;
		yVel = 0;
		x = 20;
		xVel = 0;
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

	public void resetSnake() {
		snakePoints = new ArrayList<Point>();
		xDir = 0;
		yDir = 0;
		isMoving = false;
		elongate = false;
		snakePoints.add(new Point(x, (int) y));
		for (int i = 0; i < startSize; i++) {
			snakePoints.add(new Point(x, (int) (y - i * getPlayerWidth())));
		}
	}

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

	public void move(String game) {
		if (game.equals("pong")) {
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
			countVel++;
			if (countVel > 2) {
				if (isMoving) {
					Point temp = snakePoints.get(0);
					Point last = snakePoints.get(snakePoints.size() - 1);
					Point newStart = new Point(temp.getX() + xDir * playerWidth, temp.getY() + yDir * playerWidth);
					for (int i = snakePoints.size() - 1; i >= 1; i--) {
						snakePoints.set(i, snakePoints.get(i - 1));
					}
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

	public Rectangle getRectanglePaddle() {
		return new Rectangle(getX(), (int) getY(), getPlayerWidth(), getPlayerHeight());
	}

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
