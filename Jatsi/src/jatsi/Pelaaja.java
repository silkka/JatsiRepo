package jatsi;

import java.util.HashMap;

public class Pelaaja {
	
	private String nimi;
	private HashMap<String,Integer> pisteet;
	
	public Pelaaja() {
		//this.nimi = nimi;
		pisteet = new HashMap<String,Integer>();
		for(int i = 0;i<Jatsi.yhdistelmät.length;i++){
			pisteet.put(Jatsi.yhdistelmät[i], -1);
		}
	}
	
	public Pelaaja(String nimi) {
		this.nimi = nimi;
		pisteet = new HashMap<String,Integer>();
		for(int i = 0;i<Jatsi.yhdistelmät.length;i++){
			pisteet.put(Jatsi.yhdistelmät[i], -1);
		}
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
	
	public int laskePisteet(){
		int summa = 0;
		summa += laskeBonus();
		for(int i = 0;i<15;i++){
			summa += pisteet.get(Jatsi.yhdistelmät[i]);
		}
		return summa;
	}

	public int laskeBonus() {
		int bonusPisteet = 0;
		for(int i = 0;i<6;i++){
			bonusPisteet += pisteet.get(Jatsi.yhdistelmät[i]);
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
