package jatsi;

import java.util.HashMap;

public class Pelaaja {
	
	private String nimi;
	private HashMap<String,Integer> pisteet;
	
	public Pelaaja() {
		//this.nimi = nimi;
		pisteet = new HashMap<String,Integer>();
		for(int i = 0;i<Jatsi.yhdistelm�t.length;i++){
			pisteet.put(Jatsi.yhdistelm�t[i], -1);
		}
	}
	
	public Pelaaja(String nimi) {
		this.nimi = nimi;
		pisteet = new HashMap<String,Integer>();
		for(int i = 0;i<Jatsi.yhdistelm�t.length;i++){
			pisteet.put(Jatsi.yhdistelm�t[i], -1);
		}
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
	
	public int laskePisteet(){
		int summa = 0;
		summa += laskeBonus();
		for(int i = 0;i<15;i++){
			summa += pisteet.get(Jatsi.yhdistelm�t[i]);
		}
		return summa;
	}

	public int laskeBonus() {
		int bonusPisteet = 0;
		for(int i = 0;i<6;i++){
			bonusPisteet += pisteet.get(Jatsi.yhdistelm�t[i]);
		}
		if(bonusPisteet>=63){
			return 50;
		}else{
			return 0;
		}
	}
	
	public void tulostaPisteet(){
		 
	}
}
