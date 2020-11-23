
public class Lexer {

	static Integer transitions[][] = 
		{  //    espace lettre  chiffre    =    <     >   !   ;     +     -     *   /      (   )    {    }   autre  
		/* 0 */	{ 0, 	  1   ,    2 ,     3  , 4  ,  5,  6  , 101 , 102, 103, 104, 105, 106, 107, 108, 109, null },
		/* 1 */	{ 201,    1,       1 ,   201,  201,   201, 201,201,  201, 201, 201, 201, 201, 201, 201, 201, null }, 
		/* 2 */ { 202,   202,      2 ,   202,  202,   202, 202,202,  202, 202, 202, 202, 202, 202, 202, 202, null },
		/* 3 */ { 203,   203,      203,  204,  203,   203, 203,203,  203, 203, 203, 203, 203, 203, 203, 203, null },
		/* 4 */ { 205,  205,      205,   206,  205,   205, 205,205,  205, 205, 205, 205, 205, 205, 205, 205, null },
		/* 5 */ { 207,  207,      207,   208,  207,   207, 207,207,  207, 207, 207, 207, 207, 207, 207, 207, null },
		/* 6 */ { 209,  209,      209,   210,  209,   209, 209,209,  209, 209, 209, 209, 209, 209, 209, 209, null },
		};
	
	// 101 accepte ;                        (goBack : non)
	// 102 accepte +                        (goBack : non)
	// 103 accepte -                        (goBack : non)
	// 104 accepte *                        (goBack : non)
	// 105 accepte /                        (goBack : non)
	// 106 accepte (                        (goBack : non)
	// 107 accepte )                        (goBack : non)
	// 108 accepte {                        (goBack : non)
	// 109 accepte }                        (goBack : non)

	
	static final int ETAT_INITIAL = 0;
	
	private String entree;
	private int pos = 0;

	public boolean lexer(String entree) {
		this.entree = entree;
		int etat = ETAT_INITIAL;
		Character c = lectureSymbole();
		while (c != null) {
			int i = indiceSymbole(c);
			Integer e = transitions[etat][i];
			System.out.println("Symbole '" + c  + "', transition " + etat +"->" + e );
			// 101 accepte ;                        (goBack : non)
			// 102 accepte +                        (goBack : non)
			// 103 accepte -                        (goBack : non)
			// 104 accepte *                        (goBack : non)
			// 105 accepte /                        (goBack : non)
			// 106 accepte (                        (goBack : non)
			// 107 accepte )                        (goBack : non)
			// 108 accepte {                        (goBack : non)
			// 109 accepte }                        (goBack : non)
			if (e == null) {
				System.out.println("Aucune transition pour le symbole " + c + " ...");
				return false;
			}else if (e == 101) {
				System.out.println("Création d'un token ; ");
				etat = 0;
			}else if (e == 102) {
				System.out.println("Création d'un token + ");
				etat = 0;
			}else if (e == 103) {
				System.out.println("Création d'un token - ");
				etat = 0;
			}else if (e == 104) {
				System.out.println("Création d'un token * ");
				etat = 0;
			}else if (e == 105) {
				System.out.println("Création d'un token / ");
				etat = 0;
			}else if (e == 106) {
				System.out.println("Création d'un token ( ");
				etat = 0;
			}else if (e == 107) {
				System.out.println("Création d'un token ) ");
				etat = 0;
			}else if (e == 108) {
				System.out.println("Création d'un token {");
				etat = 0;
			}else if (e == 109) {
				System.out.println("Création d'un token }");
				etat = 0;
			}
			// 201 accepte identifiant ou mot clé   (goBack : oui)
			// 202 accepte entier                   (goBack : oui)
			// 203 accepte =                        (goBack : oui)
			// 204 accepte ==                       (goBack : non)
			// 205 accepte <                        (goBack : oui)
			// 206 accepte <=                       (goBack : non)
			// 207 accepte >                        (goBack : oui)
			// 208 accepte >=                       (goBack : non)
			// 209 accepte !                        (goBack : oui)
			// 210 accepte !=                       (goBack : non)

			else if (e == 201) {
				System.out.println("Création d'un token identifiant ou mot clef ");
				etat = 0;
			}else if (e == 202) {
				System.out.println("Création d'un token entier");
				etat = 0;
			}else if (e == 203) {
				System.out.println("Création d'un token =");
				etat = 0;
			}else if (e == 204) {
				System.out.println("Création d'un token ==");
				etat = 0;
			}else if (e == 205) {
				System.out.println("Création d'un token <");
				etat = 0;
			}else if (e == 206) {
				System.out.println("Création d'un token <=");
				etat = 0;
			}else if (e == 207) {
				System.out.println("Création d'un token >");
				etat = 0;
			}else if (e == 208) {
				System.out.println("Création d'un token >= ");
				etat = 0;
			}else if (e == 209) {
				System.out.println("Création d'un token !");
				etat = 0;
			}else if (e == 210) {
				System.out.println("Création d'un token !=");
				etat = 0;
			}else {
				etat = e;
				c = lectureSymbole();
			}
		}
		return false;
	}
	private int indiceSymbole(Character c) {
		//fin de l'entrée ?
		if(c == null) return 0;
		//si c'est un espace ?
		if(Character.isWhitespace(c)) return 0;
		// Lettre ?
		if(Character.isLetter(c)) return 1;
		// Chiffre ?
		if(Character.isDigit(c)) return 2;
		if(c == '=') return 3;
		if(c == '<') return 4;
		if(c == '>') return 5;
		if(c == '!') return 6;
		if(c == ';') return 7;
		if(c == '+') return 8;
		if(c == '-') return 9;
		if(c == '*') return 10;
		if(c == '/') return 11;
		if(c == '(') return 12;
		if(c == ')') return 13;
		if(c == '{') return 14;
		if(c == '}') return 15;
		return 16;	
	}
	
	private Character lectureSymbole() {
		if (pos >= entree.length())
			return null;
		Character c = entree.charAt(pos);
		pos++;
		return c;
	}
}
