package jatsi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//Apuna HighScore taulukon luonnissa.

public  class ScoreClass implements Comparable{
	 public int pisteet;
	 public String nimi;
	 
	 public static void main(String[] asdasd){
		 Pelaaja[] pelaajat = new Pelaaja[10];
		 for(int i = 0; i<pelaajat.length; i++){
			 pelaajat[i] = new Pelaaja("p" + i, i+1);
		 }
		 
		 saveHighScore(pelaajat);
		 displayHighscores();
	 }
	 
	 
	 
	 public ScoreClass(String nimi, int pisteet){
		 this.pisteet = pisteet;
		 this.nimi = nimi;
		 
	 }
	 public ScoreClass(Pelaaja pelaaja){
		 this.nimi = pelaaja.getNimi();
		 this.pisteet= pelaaja.getKokonaispisteet();
		 
	 }
	 
	 
	@Override
	public int compareTo(Object o) {
		if (o instanceof ScoreClass){
			if(this.pisteet > ((ScoreClass) o).pisteet){
				return 1;
			}
			else if(this.pisteet < ((ScoreClass) o).pisteet){
		
		
				return -1;
			}
			else{
				return 0;
			}
			
	}
		return 0;
	}
	
	
	/**
	 * Tallentaa uudet ennätykset pelaajat taulukosta Highscores.scores tiedostoon.
	 * @param pelaajat 
	 */
	
	public static void saveHighScore(Pelaaja[] pelaajat){
		try{
			File file = new File("Highscores.scores");
			file.createNewFile();
		      
			FileReader inStream = new FileReader(file);
			BufferedReader in = new BufferedReader(inStream);
			ArrayList<ScoreClass> highscoreTable = new ArrayList<ScoreClass>();
			
			//Lukee entiset ennätykset
			String nimi = in.readLine();
			while(nimi != null){
				int piste = Integer.parseInt(in.readLine());
				highscoreTable.add(new ScoreClass(nimi, piste));
				
				nimi = in.readLine();

			}
			in.close();
			//Lukee pelaajien pisteet
			for(Pelaaja pelaaja: pelaajat){
				highscoreTable.add(new ScoreClass(pelaaja));
			}
			
			// Järjestää ennätykset ja leikkaa listan 10 kokoiseksi
			Collections.sort(highscoreTable);
			Collections.reverse(highscoreTable);
			
			while(highscoreTable.size() > 10){
				highscoreTable.remove(highscoreTable.size()-1);
			}
			
			//Kirjoittaa ennätykset tiedostoon
			FileWriter outStream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(outStream);
			for(int i = 0;i<highscoreTable.size();i++){
				out.write(highscoreTable.get(i).nimi);
				out.newLine();
				out.write(highscoreTable.get(i).pisteet + "");
				out.newLine();
			}
			out.close();
			
			
			
			
			
			}	catch (IOException e){
				  System.err.println("Virhe: " + e.getMessage());
			
			
			}	
	}
	
	/**
	 *Tulostaa top 10 pelaajat pisteineen
	 */
	
	public static void displayHighscores(){
		try{
		File file = new File("Highscores.scores");
		file.createNewFile();
		
		FileReader inStream = new FileReader(file);
		BufferedReader in = new BufferedReader(inStream);
		ArrayList<ScoreClass> highscoreTable = new ArrayList<ScoreClass>();
		
		//Lukee entiset ennätykset
		String nimi = in.readLine();
		while(nimi != null){
			int piste = Integer.parseInt(in.readLine());
			highscoreTable.add(new ScoreClass(nimi, piste));
			
			nimi = in.readLine();

		}
		in.close();
		
		for(int i = 0; i<highscoreTable.size(); i++){
			System.out.println((i + 1) + ". " + highscoreTable.get(i).nimi + " with the score of " + highscoreTable.get(i).pisteet); 
		}
		
		
		
		
		
		}
		catch(IOException e){
			
		}
		
	}
	
	
	
	
	
	
}