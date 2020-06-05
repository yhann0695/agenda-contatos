package br.com.agenda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

	public static String lerString() {
		
		try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler um dado do teclado");
		}
	}
	
	public static char lerCaracter() {
		
		try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine().charAt(0);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler um dado do teclado");
		}
	}
	
	public static int lerInteiro() {
		
		String str = lerString();
		
		try {
			
			return Integer.parseInt(str);
		} catch(NumberFormatException e) {
			throw new RuntimeException(str + " valor inteiro inválido");
		}
	}
}
