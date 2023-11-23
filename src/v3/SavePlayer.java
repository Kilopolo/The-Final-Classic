package v3;

/**
 * Clase para ordenar los jugadores en relacion a su puntuación. Nos almacena el
 * nombre, el juego y los puntos.
 * 
 * 
 * @author p.diaz
 *
 */
public class SavePlayer implements Comparable<SavePlayer> {

	private String nombre;
	private String game;
	private int puntos;

	/**
	 * Constructor para meter automaticamente los valores mediante un split();
	 * 
	 * @param campos
	 */
	public SavePlayer(String[] campos) {
		
		
		try {
			nombre = campos[0].replaceAll(" ", "");
			
			game = campos[1].replaceAll(" ", "");

			try {

				puntos = Integer.valueOf(campos[2].replaceAll(" ", "").replaceAll(";", ""));

			} catch (NumberFormatException e) {
				System.out.println(campos[2].replaceAll(" ", "").replaceAll(";", "") + " no se pudo Parsear");
				e.printStackTrace();
			}

		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println(campos[0] + "+" + campos[1] + "+" + campos[2]);
			e.printStackTrace();
		}
		
			
		

	}

	/**
	 * 
	 * Constructor para meter a mano los valores.
	 * 
	 * @param nombre
	 * @param game
	 * @param puntos
	 */
	public SavePlayer(String nombre, String game, int puntos) {
		super();
		this.nombre = nombre;
		this.game = game;
		this.puntos = puntos;
	}

	/**
	 * Metodo CompareTo necesario al implementar el Comparable <SavePlayer> en el
	 * cual comparamos por cantidad de puntos.
	 * 
	 */
	@Override
	public int compareTo(SavePlayer o) {
		return o.getPuntos() - this.puntos;
	}

	@Override
	public String toString() {
		return nombre + " / " + game + " / " + puntos + " ;\n";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

}
