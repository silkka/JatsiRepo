package jatsi;

import java.util.Arrays;

public class TarkistusMetodit {
	/**
	 * Palauttaa tiettyä yhdistelmää koskevat pisteet
	 * 
	 * @param nopat
	 *            noppa taulukko
	 * @param yhdistelma
	 *            yhdistelmä jonka pisteet halutaan
	 * @return Halutun yhdistelmän pisteet; Palauttaa -1 jos yhdistelmää ei ole
	 *         olemassa
	 */

	public static int getYhdistelmaPisteet(int[] nopat, String yhdistelma) {

		Arrays.sort(nopat);

		switch (yhdistelma) {

		case "ykköset":
			return getAlkuYhdistelmaPisteet(nopat, 1);

		case "kakkoset":
			return getAlkuYhdistelmaPisteet(nopat, 2);

		case "kolmoset":
			return getAlkuYhdistelmaPisteet(nopat, 3);

		case "neloset":
			return getAlkuYhdistelmaPisteet(nopat, 4);

		case "vitoset":
			return getAlkuYhdistelmaPisteet(nopat, 5);

		case "kutoset":
			return getAlkuYhdistelmaPisteet(nopat, 6);

		case "pari":
			return getPariPisteet(nopat);

		case "kaksiparia":
			return getKaksiPariaPisteet(nopat);

		case "kolmesamaa":
			return getKolmeSamaaPisteet(nopat);

		case "neljäsamaa":
			return getNeljaSamaaPisteet(nopat);

		case "isosuora":
			return getIsoSuoraPisteet(nopat);

		case "pienisuora":
			return getPieniSuoraPisteet(nopat);

		case "täyskäsi":
			return getTayskasiPisteet(nopat);

		case "sattuma":
			return getSattumaPisteet(nopat);

		case "jatsi":
			return getJatsiPisteet(nopat);

		default:
			return -1;

		}

	}

	private static int getAlkuYhdistelmaPisteet(int[] nopat, int yhdistelma) {
		int summa = 0;
		for (int i = 0; i < nopat.length; i++) {
			if (nopat[i] == yhdistelma) {
				summa += yhdistelma;
			}
		}
		return summa;
	}

	private static int getPariPisteet(int[] nopat) {

		int suurimmanParinArvo = 0;
		for (int i = 1; i < nopat.length; i++) {
			if (nopat[i] == nopat[i - 1]) {
				suurimmanParinArvo = nopat[i];
			}
		}
		return suurimmanParinArvo * 2;

	}

	private static int getKaksiPariaPisteet(int[] nopat) {
		int suurempiPari = getPariPisteet(nopat);
		int pienempiPari = 0;

		if (suurempiPari == 0) {
			return 0;

		} else {

			int[] nopatPoislukienSuuremmanParinLuvut = new int[nopat.length];

			for (int i = 0; i < nopat.length; i++) {
				if (nopat[i] != suurempiPari / 2) {
					nopatPoislukienSuuremmanParinLuvut[i] = nopat[i];
				}

			}
			Arrays.sort(nopatPoislukienSuuremmanParinLuvut);
			pienempiPari = getPariPisteet(nopatPoislukienSuuremmanParinLuvut);

			if (pienempiPari != 0) {
				return suurempiPari + pienempiPari;
			}

			else
				return 0;
		}
	}

	private static int getKolmeSamaaPisteet(int[] nopat) {
		for (int i = 2; i < nopat.length; i++) {
			if (nopat[i] == nopat[i - 1] && nopat[i - 2] == nopat[i - 1]) {
				return nopat[i] * 3;
			}
		}
		return 0;

	}

	private static int getNeljaSamaaPisteet(int[] nopat) {
		for (int i = 3; i < nopat.length; i++) {
			if (nopat[i] == nopat[i - 1] && nopat[i - 2] == nopat[i - 1]
					&& nopat[i - 2] == nopat[i - 3]) {
				return nopat[i] * 4;
			}
		}
		return 0;

	}

	private static int getIsoSuoraPisteet(int[] nopat) {
		if (nopat[0] != 2)
			return 0;
		for (int i = 1; i < nopat.length; i++) {
			if (nopat[i] != nopat[i - 1] + 1) {
				return 0;
			}
		}
		return 20;
	}

	private static int getPieniSuoraPisteet(int[] nopat) {
		if (nopat[0] != 1)
			return 0;
		for (int i = 1; i < nopat.length; i++) {
			if (nopat[i] != nopat[i - 1] + 1) {
				return 0;
			}
		}
		return 15;
	}

	private static int getTayskasiPisteet(int[] nopat) {
		int kolmenSamanPisteet = getKolmeSamaaPisteet(nopat);
		if (kolmenSamanPisteet == 0)
			return 0;

		int[] nopatIlmanKolmeaSamaa = new int[nopat.length];
		for (int i = 0; i < nopat.length; i++) {
			if (nopat[i] != kolmenSamanPisteet / 3) {
				nopatIlmanKolmeaSamaa[i] = nopat[i];
			}
		}
		Arrays.sort(nopatIlmanKolmeaSamaa);
		int parinPisteet = getPariPisteet(nopatIlmanKolmeaSamaa);

		if (parinPisteet != 0)
			return kolmenSamanPisteet + parinPisteet;
		else
			return 0;

	}

	private static int getSattumaPisteet(int[] nopat) {
		int summa = 0;
		for (int noppa : nopat) {
			summa += noppa;
		}

		return summa;
	}

	private static int getJatsiPisteet(int[] nopat) {
		int jatsi = 50;
		for (int i = 1; i < nopat.length; i++) {
			if (nopat[i] != nopat[i - 1]) {
				jatsi = 0;
			}
		}

		return jatsi;
	}

}
