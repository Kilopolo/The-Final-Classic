package v3;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Clase que implementa el evento de escucha de teclado de nuestro juego.
 * 
 * Declaramos un Juego para poder acceder a las variables de Jugador y
 * establecer el movimiento del mismo con respecto a la tecla presionada.
 * 
 * Declaramos un Main para poder volver al menu principal en caso de que se
 * pulse la tecla apropiada.
 * 
 * @author p.diaz
 *
 */
public class KeyListenGame implements KeyListener {

	private Game game;
	private MainClass main;

	/**
	 * constructor por defecto con los valores de los parametros igualados a las
	 * variables globales de clase.
	 * 
	 * @param game
	 * @param main
	 */
	public KeyListenGame(Game game, MainClass main) {
		// TODO Auto-generated constructor stub

		this.game = game;
		this.main = main;

	}

	@Override
	public void keyPressed(KeyEvent e) {

		// CONTROLES SAVEDATA
		if (game.isStatsSaving()) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (game.getLetraABC() < 25) {
					game.setLetraABC(game.getLetraABC() + 1);
					
//					System.out.println(game.getLetraCambiando());
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (game.getLetraABC() > 0) {
					game.setLetraABC(game.getLetraABC() - 1);
//					System.out.println(game.getLetraCambiando());
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (game.getLetraActual() > 0) {
					game.setLetraActual(game.getLetraActual() - 1);
//					System.out.println(game.getLetraCambiando());
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (game.getLetraActual() < 9) {
					game.setLetraActual(game.getLetraActual() + 1);
//					System.out.println(game.getLetraCambiando());
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// establecemos guardarnombre a true para que entre en el metodo guardarnombre;
				game.setGuardarNombre(true);
//				game.setStatsSaving(false);
				System.out.println(game.getNombreJugador());

			}

		} else {

			/**
			 * Si el juego es el pong o el wallBreak establecemos que las teclas del teclado
			 * de 'arriba' y 'w' nos suben el personaje y viceversa con 'abajo' y 's'.
			 */
			if (game.getGameStage().equals("pong")) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					game.getPlayer().setUpAccel(true);
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					game.getPlayer().setDownAccel(true);
				}
//				if (e.getKeyCode() == KeyEvent.VK_UP) {
//					game.getPlayer().setUpAccel(true);
//				}
//				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//					game.getPlayer().setDownAccel(true);
//				}
			} else if (game.getGameStage().equals("wall")) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					game.getPlayer().setUpAccel(true);
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					game.getPlayer().setDownAccel(true);
				}

//				if (e.getKeyCode() == KeyEvent.VK_UP) {
//					game.getPlayer().setUpAccel(true);
//				}
//				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//					game.getPlayer().setDownAccel(true);
//
//				}
				/**
				 * Si el juego es el snake: y la serpiente esta quieta solo se podra dejar de
				 * mover cuando se pulse la tecla 'd'/'derecha'. En caso de que la serpiente
				 * esté en movimiento se usará 'w'/'arriba' para desplazarse hacia arriba,
				 * 's'/'abajo' para desplazarse hacia abajo, 'a'/'izquierda' para desplazarse
				 * hacia la izquierda y 'd'/'derecha' para desplazarse hacia la derecha.
				 */
			} else if (game.getGameStage().equals("snake")) {
				if (!game.getPlayer().isMoving()) {
					// e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S ||
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

//				if (!game.getPlayer().isMoving()) {
//
//					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//						game.getPlayer().setMoving(true);
//					}
//				}
//
//				if (e.getKeyCode() == KeyEvent.VK_UP) {
//
//					if (game.getPlayer().getyDir() != 1) {
//						game.getPlayer().setyDir(-1);
//						game.getPlayer().setxDir(0);
//					}
//
//				}
//				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//
//					if (game.getPlayer().getyDir() != -1) {
//						game.getPlayer().setyDir(1);
//						game.getPlayer().setxDir(0);
//					}
//
//				}
//				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//
//					if (game.getPlayer().getxDir() != 1) {
//						game.getPlayer().setxDir(-1);
//						game.getPlayer().setyDir(0);
//					}
//
//				}
//				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//
//					if (game.getPlayer().getxDir() != -1) {
//						game.getPlayer().setxDir(1);
//						game.getPlayer().setyDir(0);
//					}
//
//				}
			}

			
			
			// reiniciar juego
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {

				game.newGame();

			}

		}

		// salir juego
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.setPaused(true);
			main.addMenu();

		}
		// pausar juego
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (game.getPaused()) {
				game.setPaused(false);
			} else {
				game.setPaused(true);
			}

		}
		/**
		 * CHEATS
		 * 
		 * Esta tecla 'F1' sirve para porer el juego en GODMODE y asi poder probar las
		 * características del mismo en modo desarrollador. Estas teclas 'F2','F3','F4'
		 * sirven para cambiar la velocidad del juego. 'ESC' sirve para volver al menu.
		 * 'SPACE' sirve para poner el juego en pausa sin volver al menu. 'ENTER' sirve
		 * para reiniciar el juego 'F5' nos cambia de juego a pruebas. 'F6' nos cambia
		 * al pong. 'F7' nos cambia al wallBreak. 'F8' nos cambia al snake.
		 */
		//
		// velocidad lenta
		if (e.getKeyCode() == KeyEvent.VK_F4) {
			game.setCoeficienteDeVelocidad(4);
		}
		//velocidad normal
		if (e.getKeyCode() == KeyEvent.VK_F3) {
			game.setCoeficienteDeVelocidad(1.5);
		}
		// velocidad rapida
		if (e.getKeyCode() == KeyEvent.VK_F2) {
			game.setCoeficienteDeVelocidad(0.1);
		}
		// godMode
		if (e.getKeyCode() == KeyEvent.VK_F1) {
			if (game.isGodMode()) {
				game.setGodMode(false);
			} else {
				game.setGodMode(true);
			}

		}

		// cambiar a pruebas
		if (e.getKeyCode() == KeyEvent.VK_F5) {
			game.setGameStage("pruebas");
		}
		// cambiar a pong
		if (e.getKeyCode() == KeyEvent.VK_F6) {
			game.setGameStage("pong");

		}
		// cambiar a wall
		if (e.getKeyCode() == KeyEvent.VK_F7) {
			game.setGameStage("wall");

		}

		// cambiar a snake
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
//			if (e.getKeyCode() == KeyEvent.VK_UP) {
//				game.getPlayer().setUpAccel(false);
//			}
//			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//				game.getPlayer().setDownAccel(false);
//			}

		} else if (game.getGameStage().equals("wall")) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				game.getPlayer().setUpAccel(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				game.getPlayer().setDownAccel(false);
			}
//			if (e.getKeyCode() == KeyEvent.VK_UP) {
//				game.getPlayer().setUpAccel(false);
//			}
//			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//				game.getPlayer().setDownAccel(false);
//			}

		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
