package vFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveDataManager {
	private  File file;
	private  Game game;
	private String Cabecera = "PLAYERNAME / GAME REACHED / POINTS ;\n";
	private  ArrayList<SavePlayer> jugadores = new ArrayList<SavePlayer>();
	private  SortedArrayList<SavePlayer> jugadoresOrdenados = new SortedArrayList<SavePlayer>();

	public SaveDataManager(File file, Game game) {
		this.file = file;
		this.game = game;
		if (!file.exists()) {
			crearTxtStats();
		}

	}

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
			e.printStackTrace();
		}
	}

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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



