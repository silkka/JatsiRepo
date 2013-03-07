package jatsi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
			System.out.println("Pelaaja " + i+1 + ", anna nimesi: ");
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
	}


	public static void saveGame(String saveName){
		try{
			FileWriter fstream = new FileWriter(saveName + ".saavi");
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write(""+kierros);
			out.newLine();
			out.write("" +pelaajienMaara);
			out.newLine();
			
			for(int i = 0; i<pelaajat.length; i++){
				out.write(pelaajat[i].getNimi());
				out.newLine();
				
				for(int j = 0; j<yhdistelmät.length; j++){
					out.write("" + pelaajat[i].getYhdistelmänArvo(yhdistelmät[i]));
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
		FileReader fstream = new FileReader(saveName + ".saavi");
		BufferedReader in = new BufferedReader(fstream);
		
		kierros = Integer.parseInt(in.readLine());
		pelaajienMaara = Integer.parseInt(in.readLine());
		pelaajat = new Pelaaja[pelaajienMaara];
		for(int i = 0; i<pelaajienMaara; i++){
			pelaajat[i] = new Pelaaja(in.readLine());
			for(int j = 0; j<yhdistelmät.length; j++){
				pelaajat[i].setYhdistelmänArvo(yhdistelmät[j], Integer.parseInt(in.readLine()));
				
			}
			
		}
		
		in.close();
		
		
		}	catch (IOException e){
			  System.err.println("Virhe: " + e.getMessage());
			 }
			
		
	}
		
	
	
	public static void main(String[] args) {
		alustaNopat();
		System.out.println("Jatsi, jou!");
		alustaPelaajat();
		/*while(kierros<pelaajienMaara*15){
			pelaajanVuoro = kierros % pelaajienMaara;
			
		}
		heitaNopat();
		for(int nopanArvo : nopat){
			System.out.println(nopanArvo);
		}*/
		//TarkistusMetodit.getYhdistelmaPisteet(nopat, "ykköset");
		//saveGame("lol");
		loadGame("lol");
		System.out.println(pelaajat[1].getNimi());
		System.out.println(pelaajat[1].getYhdistelmänArvo("kakkoset"));
	}
}