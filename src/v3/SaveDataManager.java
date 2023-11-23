package v3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

//import com.sun.tools.javac.util.List;

/**
 * 
 * Clase SaveDataManager que nos sirve para poder guardar las puntuaciones de
 * los jugadores en un archivo txt, ordenarlas de mayor puntuacion a menor y
 * mostrarlas en el JPanel de Puntuaciones.
 * 
 * @author p.diaz
 */
public class SaveDataManager {
	private  File file;
	private  Game game;
	private String Cabecera = "PLAYERNAME / GAME REACHED / POINTS ;\n";
	private  ArrayList<SavePlayer> jugadores = new ArrayList<SavePlayer>();
	private  SortedArrayList<SavePlayer> jugadoresOrdenados = new SortedArrayList<SavePlayer>();

	/**
	 * Constructor con un parametro de tipo File para saber cual es el archivo del
	 * savedata y otro parametro de tipo Game que nos sirve para cojer los datos del
	 * jugador de la partida.
	 * 
	 * @param file
	 * @param game
	 */
	public SaveDataManager(File file, Game game) {
		this.file = file;
		this.game = game;
		if (!file.exists()) {
			crearTxtStats();
		}

	}

	/**
	 * Metodo que se lanza solo en caso de que no exista un savedata para crearlo e
	 * insertar la cabezera.
	 */
	public  void crearTxtStats() {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(Cabecera);
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Metodo que nos devuelve un String con los datos del txt savedata
	 * 
	 * @return
	 */
	public  String leerTxtStats() {
		String txt = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String linea = "";
			while ((linea = br.readLine()) != null) {

				if (!linea.equals("null")) {
					txt = txt + linea;
				}

			}

			txt = txt.replaceAll(";", ";\n");

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return txt;
	}

	
	/**
	 * Metodo que nos añade una nueva linea anuestro savedata cojiendo el jugador,
	 * juego y puntos respectivos que se hayan conseguido antes de un gameover.
	 * 
	 */
	public  void appendTxtStats(String nombre, String juego, int puntos) {
		
		SavePlayer p = new SavePlayer(nombre,juego,puntos);
		jugadoresOrdenados.insertSorted(p);
		
		
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(Cabecera);
			for (SavePlayer sp : jugadoresOrdenados) {
				bw.write(sp.toString());
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	


	/**
	 * Metodo estático que nos sirve para ordenar los jugadores por puntuacion y
	 * ordenarlos de mayor a menor.
	 */
	
	public  void iniSortedSaveManager() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String linea = "";
			int cont = 0;
			while ((linea = br.readLine()) != null) {
				
				if (cont != 0) {
					SavePlayer sp = new SavePlayer(linea.split("/"));
					jugadores.add(sp);
				}
				cont++;
			}
			br.close();

			
			
			for (int i = 0; i < jugadores.size(); i++) {
				jugadoresOrdenados.insertSorted(jugadores.get(i));
			}
			
			
			
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(Cabecera);
			for (SavePlayer sp : jugadoresOrdenados) {
				bw.write(sp.toString());
			}
			bw.flush();
			bw.close();

//			jugadores = new ArrayList<SavePlayer>();
//			jugadoresOrdenados = new SortedArrayList<SavePlayer>();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}



