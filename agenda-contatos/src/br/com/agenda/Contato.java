package br.com.agenda;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contato {

	private String nome;
	
	private String telefone;

	public Contato(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void validarDados() throws AgendaException {
		validarNome();
		validarTelefone();
	}
	
	
	private void validarNome() throws AgendaException {
		
		if(nome == null || nome.trim().length() == 0) {
			throw new AgendaException("O nome não pode ser vazio");
		}
	}
	
	private void validarTelefone() throws AgendaException {
		
		String regex = "\\d\\d\\d\\d-\\d\\d\\d\\d";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(telefone);
		
		if(!m.matches()) {
			throw new AgendaException("O Telefone " + telefone + " é inválido, use o padrão (####-####)");
		}
		
	}

	@Override
	public String toString() {
		return  nome + " => " + telefone;
	}
	
	
}
