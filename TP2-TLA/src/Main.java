import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		while(true) {
			System.out.println("Veuillez saisir votre mot : ");
			Scanner sc = new Scanner(System.in);
			String mot = sc.nextLine();
			Lexer a = new Lexer();
			boolean estUnMotValide = a.lexer(mot);
			
			if(estUnMotValide) {
				System.out.println("le mot " + mot + " est valide");
			}
			else ;
				System.out.println("le mot " + mot + " n'est pas valide");
		}
		
		
		
		
		
		

	}

}
