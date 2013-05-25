package jatsi;

import java.util.HashMap;

public class Pelaaja {
	
	private String nimi;
	private HashMap<String, Integer> pisteet;
	private int kokonaispisteet;
	private HashMap<String, Boolean> käytetty;

	public Pelaaja() {
		pisteet = new HashMap<String,Integer>();
		for(int i = 0;i<Jatsi.yhdistelmät.length;i++){
			pisteet.put(Jatsi.yhdistelmät[i], 0);			
		}
		käytetty = new HashMap<String, Boolean>();
		for(int i = 0;i<Jatsi.yhdistelmät.length;i++){
			käytetty.put(Jatsi.yhdistelmät[i], false);
		}
	}
	
	public Pelaaja(String nimi) {
		this.nimi = nimi;
		pisteet = new HashMap<String,Integer>();
		for(int i = 0;i<Jatsi.yhdistelmät.length;i++){
			pisteet.put(Jatsi.yhdistelmät[i], 0);
		}
		käytetty = new HashMap<String, Boolean>();
		for(int i = 0;i<Jatsi.yhdistelmät.length;i++){
			käytetty.put(Jatsi.yhdistelmät[i], false);
		}
	}
	
	/*public Pelaaja(String nimi, int kokonaispisteet) {
		this.nimi = nimi;
		pisteet = new HashMap<String,Integer>();
		for(int i = 0;i<Jatsi.yhdistelmät.length;i++){
			pisteet.put(Jatsi.yhdistelmät[i], 0);
		}
		this.kokonaispisteet = kokonaispisteet;
	}*/
	
	public int getKokonaispisteet() {
		return kokonaispisteet;
	}

	public void setKokonaispisteet(int kokonaispisteet) {
		this.kokonaispisteet = kokonaispisteet;
	}
	
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	public void setYhdistelmänArvo(String yhdistelmä, int arvo){
		pisteet.put(yhdistelmä, arvo);
	}
	
	public int getYhdistelmänArvo(String yhdistelmä){
		return pisteet.get(yhdistelmä);
	}
	
	public boolean getKäytetty(String yhdistelmä){
		return käytetty.get(yhdistelmä);
	}
	
	public void setKäytetty(String yhdistelmä){
		käytetty.put(yhdistelmä, true);
	}
	
	public int laskePisteet(){
		int summa = 0;
		summa += laskeBonus();
		for(int i = 0;i<15;i++){
			if(pisteet.get(Jatsi.yhdistelmät[i]) != -1){
				summa += pisteet.get(Jatsi.yhdistelmät[i]);
			}	
		}
		return summa;
	}

	public int laskeBonus() {
		int bonusPisteet = 0;
		for(int i = 0;i<6;i++){
			if(pisteet.get(Jatsi.yhdistelmät[i]) != -1){
				bonusPisteet += pisteet.get(Jatsi.yhdistelmät[i]);
			}
		}
		if(bonusPisteet>=63){
			return 50;
		}else{
			return 0;
		}
	}
	
	public void tulostaPisteet(){
		System.out.println("Pisteet: ");
		for(int i = 0;i<15;i++){
			System.out.print("  " + Jatsi.yhdistelmät[i] + ": " + '\t');			
			if(käytetty.get(Jatsi.yhdistelmät[i]) == true){
				System.out.print(pisteet.get(Jatsi.yhdistelmät[i]));
			}
			if(i % 2 == 1){
				System.out.print('\n');
			}
			if(i == 5){
				System.out.println("+ " + "bonus: " + '\t' + laskeBonus());
			}
		}
		System.out.println("\n");
	}
}
