import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean prog = true;
		while(prog = true) {
			System.out.println("Veuillez saisir votre mot (ou appuyez sur entrer pour finir) : ");
			Scanner sc = new Scanner(System.in);
			String mot = sc.nextLine();
			Lexer a = new Lexer();
			
			
			if(mot.length()==0) {
				prog = false;
				System.out.println("Fin du programme");
				break;
			}
			test(mot);
			
				
		}
	
	}

	private static void test(String entree) {
		System.out.println();
		SourceReader sr = new SourceReader(entree);
		Lexer a = new Lexer();
		ArrayList<Token> tokens = a.lexer(sr);
		for(Token t: tokens) {
			System.out.println(t);
		}
	}
}
