
public class Lexer {

	static Integer transitions[][] = {
			//             espace lettre chiffre   =    <     >     !     ;     +     -     *     /     (     )     {     }     autre
			/*  0 */    {      0,     1,      2,   3,   4,    5,    6,  101,  102,  103,  104,  105,  106,  107,  108,  109,     null      },
			/*  1 */    {    201,     1,      1, 201, 201,  201,  201,  201,  201,  201,  201,  201,  201,  201,  201,  201,     null      },
			/*  2 */    {    202,   202,      2, 202, 202,  202,  202,  202,  202,  202,  202,  202,  202,  202,  202,  202,     null      },
			/*  3 */    {    203,   203,    203, 204, 203,  203,  203,  203,  203,  203,  203,  203,  203,  203,  203,  203,     null      },
			/*  4 */    {    205,   205,    205, 206, 205,  205,  205,  205,  205,  205,  205,  205,  205,  205,  205,  205,     null      },
			/*  5 */    {    207,   207,    207, 208, 207,  207,  207,  207,  207,  207,  207,  207,  207,  207,  207,  207,     null      },
			/*  6 */    {    209,   209,    209, 210, 209,  209,  209,  209,  209,  209,  209,  209,  209,  209,  209,  209,     null      },

			// 101 accepte ;                        (goBack : non)
			// 102 accepte +                        (goBack : non)
			// 103 accepte -                        (goBack : non)
			// 104 accepte *                        (goBack : non)
			// 105 accepte /                        (goBack : non)
			// 106 accepte (                        (goBack : non)
			// 107 accepte )                        (goBack : non)
			// 108 accepte {                        (goBack : non)
			// 109 accepte }                        (goBack : non)

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

	};

	static final int ETAT_INITIAL = 0;

	private int indiceSymbole(Character c) {
		if (c == null) return 0;
		if (Character.isWhitespace(c)) return 0;
		if (Character.isLetter(c)) return 1;
		if (Character.isDigit(c)) return 2;
		if (c == '=') return 3;
		if (c == '<') return 4;
		if (c == '>') return 5;
		if (c == '!') return 6;
		if (c == ';') return 7;
		if (c == '+') return 8;
		if (c == '-') return 9;
		if (c == '*') return 10;
		if (c == '/') return 11;
		if (c == '(') return 12;
		if (c == ')') return 13;
		if (c == '{') return 14;
		if (c == '}') return 15;
		return 16;
	}

	public void lexer(SourceReader sr) {
		String buf="";
		int etat = ETAT_INITIAL;
		while (true) {
			Character c = sr.lectureSymbole();
			Integer e = transitions[etat][indiceSymbole(c)];
			if (e == null) {
				System.out.println(" pas de transition depuis état " + etat + " avec symbole " + c);
				return;
			}
			if (e >= 100) {
				if (e == 101) {
					System.out.println("Accepte ;");
					// tokens.add(new Token(TokenClass.semicolon));
				} else if (e == 102) {
					System.out.println("Accepte +");
					// tokens.add(new Token(TokenClass.add));
				} else if (e == 103) {
					System.out.println("Accepte -");
					// tokens.add(new Token(TokenClass.subtract));
				} else if (e == 104) {
					System.out.println("Accepte *");
					// tokens.add(new Token(TokenClass.multiply));
				} else if (e == 105) {
					System.out.println("Accepte /");
					// tokens.add(new Token(TokenClass.divide));
				} else if (e == 106) {
					System.out.println("Accepte (");
					// tokens.add(new Token(TokenClass.leftPar));
				} else if (e == 107) {
					System.out.println("Accepte )");
					// tokens.add(new Token(TokenClass.rightPar));
				} else if (e == 108) {
					System.out.println("Accepte {");
					// tokens.add(new Token(TokenClass.leftBrace));
				} else if (e == 109) {
					System.out.println("Accepte }");
					// tokens.add(new Token(TokenClass.rightBrace));
				} else if (e == 201) {
					System.out.println("Accepte identifiant " + buf);
					// tokens.add(new Token(TokenClass.ident, buf));
					sr.goBack();
				} else if (e == 202) {
					System.out.println("Accepte intVal " + buf);
					// tokens.add(new Token(TokenClass.intVal, buf));
					sr.goBack();
				} else if (e == 203) {
					System.out.println("Accepte =");
					// tokens.add(new Token(TokenClass.assign));
					sr.goBack();
				} else if (e == 204) {
					System.out.println("Accepte ==");
					// tokens.add(new Token(TokenClass.equal));
				} else if (e == 205) {
					System.out.println("Accepte <");
					// tokens.add(new Token(TokenClass.lessThan));
					sr.goBack();
				} else if (e == 206) {
					System.out.println("Accepte <=");
					// tokens.add(new Token(TokenClass.lessOrEqual));
				} else if (e == 207) {
					System.out.println("Accepte >");
					// tokens.add(new Token(TokenClass.greaterThan));
					sr.goBack();
				} else if (e == 208) {
					System.out.println("Accepte >=");
					// tokens.add(new Token(TokenClass.greaterOrEqual));
				} else if (e == 209) {
					System.out.println("Accepte !");
					// tokens.add(new Token(TokenClass.logicalNeg));
					sr.goBack();
				} else if (e == 210) {
					System.out.println("Accepte !=");
					// tokens.add(new Token(TokenClass.notEqual));
				}
				etat = 0;
				buf = "";
			} else {
				etat = e;
				if (etat>0) buf = buf + c;
			}
			if (c==null) break;
		}
	}

}
