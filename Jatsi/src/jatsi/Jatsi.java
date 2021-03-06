package jatsi;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Jatsi {
	
	static Scanner sc = new Scanner(System.in);
	static Pelaaja[] pelaajat;
	static int pelaajienMaara;
	static int kierros;
	static int pelaajanVuoro;
	static int[] nopat;
	static boolean[] kiinnitetytNopat;
	static Random rng = new Random();
	static String syote;
	static int uusintaHeitot;
	//static String[] noppaSyote; poista lopuksi!
	
	public static final String[] yhdistelmät = {"ykköset","kakkoset","kolmoset","neloset","vitoset","kutoset","pari","kaksiparia",
										"kolmesamaa","neljäsamaa","isosuora","pienisuora","täyskäsi","sattuma","jatsi",};
	
	public static void alustaNopat() {
		nopat = new int[5];
		kiinnitetytNopat = new boolean[5];
	}
	
	public static void alustaPelaajat() {
		System.out.print("Anna pelaajien lukumäärä: ");
		pelaajienMaara = sc.nextInt();
		pelaajat = new Pelaaja[pelaajienMaara]; 
		for(int i = 0; i < pelaajienMaara; i++){
			System.out.println("Pelaaja " + (i+1) + ", anna nimesi: ");
			pelaajat[i] = new Pelaaja();
			pelaajat[i].setNimi(sc.next());
		}
	}
	
	public static void heitaNopat(){
		for(int i = 0;i<nopat.length;i++){
			if(kiinnitetytNopat[i] == false){
				nopat[i] = rng.nextInt(6)+1;
			}
		}
		for(int i = 0;i<nopat.length;i++){
			kiinnitetytNopat[i] = false;
		}
	}

	/**
	 * 
	 */
	private static void tulostaNopat() {
		for(int nopanArvo : nopat){
			System.out.print(nopanArvo + ", ");
		}
	}

	public static void saveGame(String saveName){
		try{
			FileWriter fstream = new FileWriter(saveName + ".jgs");
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write("" + kierros);
			out.newLine();
			out.write("" + pelaajienMaara);
			out.newLine();
			
			for(int i = 0; i<pelaajat.length; i++){
				out.write(pelaajat[i].getNimi());
				out.newLine();
				for(int j = 0; j<yhdistelmät.length; j++){
					//System.out.println(pelaajat[i].getYhdistelmänArvo(yhdistelmät[j]));
					out.write("" + pelaajat[i].getYhdistelmänArvo(yhdistelmät[j]));
					out.newLine();
				}
				for(int j = 0; j<yhdistelmät.length; j++){
					//System.out.println(pelaajat[i].getKäytetyt(yhdistelmät[j]));
					out.write("" + pelaajat[i].getKäytetty(yhdistelmät[j]));
					out.newLine();
				}
			}
			out.close();
		}
		catch (IOException e){
		  System.err.println("Virhe: " + e.getMessage());
		 }
	}
	
	public static void loadGame(String saveName){
		try{
		FileReader fstream = new FileReader(saveName + ".jgs");
		BufferedReader in = new BufferedReader(fstream);
		
		kierros = Integer.parseInt(in.readLine());
		pelaajienMaara = Integer.parseInt(in.readLine());
		pelaajat = new Pelaaja[pelaajienMaara];
		for(int i = 0; i<pelaajienMaara; i++){
			pelaajat[i] = new Pelaaja(in.readLine());
			for(int j = 0; j<yhdistelmät.length; j++){
				pelaajat[i].setYhdistelmänArvo(yhdistelmät[j], Integer.parseInt(in.readLine()));
			}
			for(int j = 0; j<yhdistelmät.length; j++){
				if(in.readLine().equals("true")){
					pelaajat[i].setKäytetty(yhdistelmät[j]);
				}
			}
		}
		
		in.close();
		
		}catch (IOException e){
				System.err.println("Virhe: " + e.getMessage());
		}		
	}
	
	/**
	 * 
	 */
	private static void pelaaKierros() {
		String[] noppaSyote;
		while(uusintaHeitot > 0){
			
			System.out.println("Mitä haluat tehdä?");
			System.out.println("T: Tulosta pisteet ja nopat");
			System.out.println("H: Heitä noppia uudestaan (kaikki tai vain osa; heittoja jäljellä: "+ uusintaHeitot + ")");
			System.out.println("K: Kirjaa pisteet (lopettaa vuoron)");
			System.out.println("S: Tallenna peli.");
			syote = sc.next();
			switch(syote.toUpperCase()){
			
			case "H":
				System.out.println("Mitkä nopat haluat \"lukita\"? Lukittuja noppia ei heitetä. Esim. 2,3,5");
				syote = sc.next();
				noppaSyote = syote.split(",");
				for(String noppa : noppaSyote){
					int noppaInt;
					noppaInt = Integer.parseInt(noppa);
					if(noppaInt == 1 || noppaInt == 2 || noppaInt == 3 || noppaInt == 4 || noppaInt == 5){
						kiinnitetytNopat[noppaInt - 1] = true;
					}
				}
				heitaNopat();
				uusintaHeitot--;
				tulostaNopat();
				break;
				
			case "T":
				pelaajat[pelaajanVuoro].tulostaPisteet();
				tulostaNopat();
				break;
			
			case "K":
				return;
			
			case "S":
				System.out.println("Anna tallennustiedoston nimi (ilman päätettä):");
				syote = sc.next();
				saveGame(syote);				
			}	
		}
	}
	
	private static void asetaPisteet(){
		pelaajat[pelaajanVuoro].tulostaPisteet();
		tulostaNopat();
		System.out.println("Minkä yhdistelmän haluat käyttää? (Kirjoita yhdistelmän nimi.)");
		syote = sc.next();
		System.out.println(TarkistusMetodit.getYhdistelmaPisteet(nopat, syote));
		pelaajat[pelaajanVuoro].setYhdistelmänArvo(syote, TarkistusMetodit.getYhdistelmaPisteet(nopat, syote));
		pelaajat[pelaajanVuoro].setKäytetty(syote);
		System.out.println(pelaajat[pelaajanVuoro].getYhdistelmänArvo(syote));
		//System.out.print(pelaajat[pelaajanVuoro].pisteet.get(Jatsi.yhdistelmät[0]));
		pelaajat[pelaajanVuoro].setKokonaispisteet(pelaajat[pelaajanVuoro].laskePisteet());
	}
	
	public static void main(String[] args) {
		alustaNopat();
		System.out.println("Jatsi, jou!");
		System.out.println("Haluatko ladata pelin?");
		syote = sc.next();
		if(syote.toUpperCase().equals("Y")){
			System.out.println("Tallennustiedoston nimi: ");
			syote = sc.next();
			loadGame(syote);
		}else{
			alustaPelaajat();
		}
		while(kierros<pelaajienMaara*15){
			uusintaHeitot = 2;
			pelaajanVuoro = kierros % pelaajienMaara;
			System.out.println("Pelaajan " + pelaajat[pelaajanVuoro].getNimi() + " vuoro.");
			pelaajat[pelaajanVuoro].tulostaPisteet();
			heitaNopat();
			tulostaNopat();
			pelaaKierros();
			asetaPisteet();
			System.out.println("We're done.");
			kierros++;
		}		
		System.out.println("Peli ohi! Kokonaispisteet: ");
		for(Pelaaja pelaaja : pelaajat){
			System.out.println("Pelaajan " + pelaaja.getNimi() + " kokonaispisteet: " + pelaaja.getKokonaispisteet());
		}
	}

}