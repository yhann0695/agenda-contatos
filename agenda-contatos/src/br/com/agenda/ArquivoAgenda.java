package br.com.agenda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class ArquivoAgenda {

	private static final String ARQUIVO_AGENDA = "agenda.txt";
	
	
	public void gravar(Collection<Contato> contatos) throws IOException {
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(ARQUIVO_AGENDA);
			
			for(Contato contato : contatos) {
				writer.print(contato.getNome());
				writer.print(",");
				writer.println(contato.getTelefone());
			}
			
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}
	
	public List<Contato> ler() {
		List<Contato> contatos = new ArrayList<Contato>();
		
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File(ARQUIVO_AGENDA));
			
			while(scanner.hasNextLine()) {
				String str = scanner.nextLine();
				
				String[] tokens = str.split(",");
				
				Contato contato = new Contato(tokens[0], tokens[1]);
				
				contatos.add(contato);
			}
			
			return contatos;
			
		} catch (FileNotFoundException e) {
			return contatos;
		} finally {
			
			if(scanner != null) {
				scanner.close();				
			}
		}
	}
}
