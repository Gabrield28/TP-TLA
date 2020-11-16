package ex;

import java.io.IOException;

public class Automate {

	static final Integer transitions[][] = {
		{ 1,    null, null },   // depuis l'état 0 (attend un a)
		{ null, 2,    null },   // depuis l'état 1 (attend un b)
		{ null, null, 3    },   // depuis l'état 2 (attend un c)
		{ 4   , 2,    null },   // depuis l'état 3 (attend un a ou un b)
		{ null, null, null }
	};
	static final char alphabet[] = { 'a', 'b', 'c' };
	static final int ETAT_INITIAL = 0;
	static final int ETATS_ACCEPTATION[] = { 4 };

	private String entree;
	private int pos;

	private Character lectureSymbole() {
		if (pos >= entree.length()) return null;
		Character c = entree.charAt(pos);
		pos++;
		return c;
	}

	private int indiceSymbole(Character c) throws IOException {
		for(int i=0;i<alphabet.length;i++) {
			if (c == alphabet[i]) return i;
		}
		throw new IOException(); // symbole non trouvé dans alphabet
	}

	public boolean automate(String entree) throws IOException {
		this.entree = entree;
		pos = 0;

		int etat = ETAT_INITIAL;
		Character c = lectureSymbole();
		while (c != null) {
			Integer e = transitions[etat][indiceSymbole(c)];
			System.out.println("Symbole " + c + ", transition " + etat + " -> " + e);
			if (e == null) return false;
			etat = e;
			c = lectureSymbole();
		}

		for(int i=0;i<ETATS_ACCEPTATION.length;i++) {
			if (ETATS_ACCEPTATION[i] == etat) return true;
		}
		return false;

	}

}
