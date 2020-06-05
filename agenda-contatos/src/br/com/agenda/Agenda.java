package br.com.agenda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Agenda {

	private Map<String, Contato> contatosMap = new TreeMap<String, Contato>();
	
	private Map<Character, List<Contato>> contatosPorLetraMap = new TreeMap<Character, List<Contato>>();
	
	private ArquivoAgenda arquivo = new ArquivoAgenda();
	
	public Agenda() throws IOException {
		List<Contato> contatos = arquivo.ler();
		
		for(Contato contato : contatos) {
			
			try {
				inserir(contato);
			} catch (AgendaException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public void inserir(Contato contato) throws AgendaException, IOException {
		
		contato.validarDados();
		
		String nome = contato.getNome();
		if(contatosMap.containsKey(nome)) {
			throw new AgendaException("O contato " + nome + " já existe");
		}
		
		contatosMap.put(nome, contato);
		
		char letraInicial = Character.toUpperCase(nome.charAt(0));
		List<Contato> contatosLetras = contatosPorLetraMap.get(letraInicial);
		if(contatosLetras == null) {
			contatosLetras = new ArrayList<Contato>();
			contatosPorLetraMap.put(letraInicial, contatosLetras);
		}
		
		contatosLetras.add(contato);
		
		arquivo.gravar(contatosMap.values());
	}
	
	public void excluirContato(String nome) throws IOException {
		
		verificarExistenciaContato(nome);
		
		Contato contatos = obterContato(nome);
		
		contatosMap.remove(nome);
		
		List<Contato> contato = contatosPorLetraMap.get(nome.toUpperCase().charAt(0));
		contato.remove(contato);
		
		if(contato.size() == 0) {
			contatosPorLetraMap.remove(nome.toUpperCase().charAt(0));
		}
		
		arquivo.gravar(contatosMap.values());
		
	}
	
	public void alterar(Contato contato) throws IOException, AgendaException {
		
		contato.validarDados();
		
		verificarExistenciaContato(contato.getNome());
		
		arquivo.gravar(contatosMap.values());
	}
	
	public List<Contato> listarContatosPorLetra(char letra) {
		
		List<Contato> contatos = contatosPorLetraMap.get(Character.toUpperCase(letra));
		
		if(contatos == null) {
			return new ArrayList<Contato>();
		}
		
		return contatos;
	}
	
	public List<Contato> listarContatosPorParteNome (String parteNome) {
		
		String regex = "\\w*" + parteNome + "\\w*";
		
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		 
		List<Contato> contatosEncontrado = new ArrayList<Contato>();
		
		Collection<Contato> contatosCadastrados = contatosMap.values();
		
		for(Contato contato : contatosCadastrados) {
			Matcher m = p.matcher(contato.getNome());
			if(m.matches()) {
				contatosEncontrado.add(contato);
			}
		}
		
		return contatosEncontrado;
	}
	
	public void verificarExistenciaContato(String nome) {
		
		if(!contatosMap.containsKey(nome)) {
			System.out.println("Não existe " + nome + " na lista de contatos");
		}
	}
	
	public Contato obterContato(String nome) {
		return contatosMap.get(nome);
	}
}
