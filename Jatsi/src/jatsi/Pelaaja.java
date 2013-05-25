package jatsi;

import java.util.HashMap;

public class Pelaaja {
	
	private String nimi;
	private HashMap<String, Integer> pisteet;
	private int kokonaispisteet;
	private HashMap<String, Boolean> k�ytetty;

	public Pelaaja() {
		pisteet = new HashMap<String,Integer>();
		for(int i = 0;i<Jatsi.yhdistelm�t.length;i++){
			pisteet.put(Jatsi.yhdistelm�t[i], 0);			
		}
		k�ytetty = new HashMap<String, Boolean>();
		for(int i = 0;i<Jatsi.yhdistelm�t.length;i++){
			k�ytetty.put(Jatsi.yhdistelm�t[i], false);
		}
	}
	
	public Pelaaja(String nimi) {
		this.nimi = nimi;
		pisteet = new HashMap<String,Integer>();
		for(int i = 0;i<Jatsi.yhdistelm�t.length;i++){
			pisteet.put(Jatsi.yhdistelm�t[i], 0);
		}
		k�ytetty = new HashMap<String, Boolean>();
		for(int i = 0;i<Jatsi.yhdistelm�t.length;i++){
			k�ytetty.put(Jatsi.yhdistelm�t[i], false);
		}
	}
	
	/*public Pelaaja(String nimi, int kokonaispisteet) {
		this.nimi = nimi;
		pisteet = new HashMap<String,Integer>();
		for(int i = 0;i<Jatsi.yhdistelm�t.length;i++){
			pisteet.put(Jatsi.yhdistelm�t[i], 0);
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
	
	public void setYhdistelm�nArvo(String yhdistelm�, int arvo){
		pisteet.put(yhdistelm�, arvo);
	}
	
	public int getYhdistelm�nArvo(String yhdistelm�){
		return pisteet.get(yhdistelm�);
	}
	
	public boolean getK�ytetty(String yhdistelm�){
		return k�ytetty.get(yhdistelm�);
	}
	
	public void setK�ytetty(String yhdistelm�){
		k�ytetty.put(yhdistelm�, true);
	}
	
	public int laskePisteet(){
		int summa = 0;
		summa += laskeBonus();
		for(int i = 0;i<15;i++){
			if(pisteet.get(Jatsi.yhdistelm�t[i]) != -1){
				summa += pisteet.get(Jatsi.yhdistelm�t[i]);
			}	
		}
		return summa;
	}

	public int laskeBonus() {
		int bonusPisteet = 0;
		for(int i = 0;i<6;i++){
			if(pisteet.get(Jatsi.yhdistelm�t[i]) != -1){
				bonusPisteet += pisteet.get(Jatsi.yhdistelm�t[i]);
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
			System.out.print("  " + Jatsi.yhdistelm�t[i] + ": " + '\t');			
			if(k�ytetty.get(Jatsi.yhdistelm�t[i]) == true){
				System.out.print(pisteet.get(Jatsi.yhdistelm�t[i]));
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
