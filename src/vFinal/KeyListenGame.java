package vFinal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenGame implements KeyListener {

	private Game game;
	private MainClass main;

	public KeyListenGame(Game game, MainClass main) {
		this.game = game;
		this.main = main;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (game.isStatsSaving()) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (game.getLetraABC() < 25) {
					game.setLetraABC(game.getLetraABC() + 1);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (game.getLetraABC() > 0) {
					game.setLetraABC(game.getLetraABC() - 1);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (game.getLetraActual() > 0) {
					game.setLetraActual(game.getLetraActual() - 1);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (game.getLetraActual() < 9) {
					game.setLetraActual(game.getLetraActual() + 1);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				game.setGuardarNombre(true);
			}
		} else {
			if (game.getGameStage().equals("pong")) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					game.getPlayer().setUpAccel(true);
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					game.getPlayer().setDownAccel(true);
				}
			} else if (game.getGameStage().equals("wall")) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					game.getPlayer().setUpAccel(true);
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					game.getPlayer().setDownAccel(true);
				}
			} else if (game.getGameStage().equals("snake")) {
				if (!game.getPlayer().isMoving()) {
					if (e.getKeyCode() == KeyEvent.VK_D) {
						game.getPlayer().setMoving(true);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_W) {
					if (game.getPlayer().getyDir() != 1) {
						game.getPlayer().setyDir(-1);
						game.getPlayer().setxDir(0);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					if (game.getPlayer().getyDir() != -1) {
						game.getPlayer().setyDir(1);
						game.getPlayer().setxDir(0);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					if (game.getPlayer().getxDir() != 1) {
						game.getPlayer().setxDir(-1);
						game.getPlayer().setyDir(0);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					if (game.getPlayer().getxDir() != -1) {
						game.getPlayer().setxDir(1);
						game.getPlayer().setyDir(0);
					}
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				game.newGame();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.setPaused(true);
			main.addMenu();
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (game.getPaused()) {
				game.setPaused(false);
			} else {
				game.setPaused(true);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_F4) {
			game.setCoeficienteDeVelocidad(4);
		}
		if (e.getKeyCode() == KeyEvent.VK_F3) {
			game.setCoeficienteDeVelocidad(1.5);
		}
		if (e.getKeyCode() == KeyEvent.VK_F2) {
			game.setCoeficienteDeVelocidad(0.1);
		}
		if (e.getKeyCode() == KeyEvent.VK_F1) {
			if (game.isGodMode()) {
				game.setGodMode(false);
			} else {
				game.setGodMode(true);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_F5) {
			game.setGameStage("pruebas");
		}
		if (e.getKeyCode() == KeyEvent.VK_F6) {
			game.setGameStage("pong");
		}
		if (e.getKeyCode() == KeyEvent.VK_F7) {
			game.setGameStage("wall");
		}
		if (e.getKeyCode() == KeyEvent.VK_F8) {
			game.setGameStage("snake");
		}
		if (e.getKeyCode() == KeyEvent.VK_F9) {
			game.start();
		}
		if (e.getKeyCode() == KeyEvent.VK_F10) {
			game.stop();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (game.getGameStage().equals("pong")) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				game.getPlayer().setUpAccel(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				game.getPlayer().setDownAccel(false);
			}
		} else if (game.getGameStage().equals("wall")) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				game.getPlayer().setUpAccel(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				game.getPlayer().setDownAccel(false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
