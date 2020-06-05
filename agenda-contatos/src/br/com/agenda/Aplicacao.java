package br.com.agenda;

import java.io.IOException;
import java.util.List;

public class Aplicacao {

	private Menu menu;
	
	private Agenda agenda;
	
	public void iniciar() throws IOException {
		
		menu = new Menu();
		agenda = new Agenda();
		
		int opcao = 0;

		while (opcao != Menu.OPCAO_SAIR) {

			try {
				opcao = menu.exibirOpcao();


				if(opcao == Menu.OPCAO_INSERIR) {
					inserir();
				} else if(opcao == Menu.OPCAO_ALTERAR) {
					alterar();
				} else if(opcao == Menu.OPCAO_EXCLUIR) {
					excluir();
				} else if(opcao == Menu.OPCAO_LISTAR_LETRA) {
					listarPorLetra();
				} else if(opcao == Menu.OPCAO_PROCURAR) {
					procurar();
				}
			} catch (AgendaException e) {
				System.out.println("ERRO: " + e.getMessage());
			}
		}
		System.out.println("Fim da aplicação");
	}

	private void inserir() throws AgendaException, IOException {

		System.out.println("Nome: ");
		String nome = Console.lerString();
		System.out.println("Telefone: ");
		String telefone = Console.lerString();
		
		Contato contato = new Contato(nome, telefone);
		agenda.inserir(contato);
		
		System.out.println("Contato inserido");
		System.out.println();
	}
	
	private void alterar() throws IOException, AgendaException {
		
		System.out.println("Nome: ");
		String nome = Console.lerString();
		
		Contato contato = agenda.obterContato(nome);
		
		if(contato == null) {
			System.out.println("O contato " + nome + " não existe");
		}
		
		System.out.println("Telefone: ");
		String telefone = Console.lerString();
		
		contato.setTelefone(telefone);
		agenda.alterar(contato);
		
		System.out.println("Contato alterado");
		System.out.println();
	}
	
	public void excluir() throws IOException {
		System.out.println("Nome: ");
		String nome = Console.lerString();
		
		agenda.excluirContato(nome);
		
		System.out.println("Contato excluído");
		System.out.println();
	}
	
	public void listarPorLetra() {
		System.out.println("Letra: ");
		char letra = Console.lerCaracter();
		
		List<Contato> contatos = agenda.listarContatosPorLetra(letra);
		
		System.out.println("Contatos com a letra '" + Character.toUpperCase(letra) + "':");
		
		if(contatos.isEmpty()) {
			System.out.println("Nenhum contato encontrado");
		} else {
			for (Contato contato : contatos) {
				System.out.println(contato);
			}
		}
		System.out.println();
	}
	
	public void procurar() {
		System.out.println("Parte do nome: ");
		String parteNome = Console.lerString();
		
		List<Contato> contatos = agenda.listarContatosPorParteNome(parteNome);
		
		System.out.println("Contatos encontrados com parte do nome => " + parteNome);
		
		if(contatos.isEmpty()) {
			System.out.println("Nenhum contato encontrado");
		} else {
			for (Contato contato : contatos) {
				System.out.println(contato);
			}
		}
		System.out.println();
	}
}
