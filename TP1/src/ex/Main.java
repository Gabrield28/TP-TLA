package ex;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		test("xyz");
		test("abca");
		test("abcaa");
		test("ac");
		test("abcbca");
	}

	private static void test(String entree) {
		Automate a = new Automate();
		System.out.println();
		try {
			boolean b = a.automate(entree);
			if (b) {
				System.out.println(entree + " -> reconnu");
			} else {
				System.out.println(entree + " -> non reconnu");
			}
		} catch (IOException ex) {
			System.out.println(entree + " -> au moins un symbole incorrect");
		}
	}

}
