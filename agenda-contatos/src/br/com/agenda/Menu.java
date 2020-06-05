package br.com.agenda;

public class Menu {

	public static final int OPCAO_INSERIR = 1;
	public static final int OPCAO_ALTERAR = 2;
	public static final int OPCAO_EXCLUIR = 3;
	public static final int OPCAO_LISTAR_LETRA = 4;
	public static final int OPCAO_PROCURAR = 5;
	public static final int OPCAO_SAIR = 6;
	
	
	public int exibirOpcao() throws AgendaException {
		
		System.out.println(" ---MENU DE OPÇÕES--- ");
		System.out.println("'# 1) Inserir");
		System.out.println("'# 2) Alterar");
		System.out.println("'# 3) Excluir");
		System.out.println("'# 4) Listar Letra");
		System.out.println("'# 5) Procurar");
		System.out.println("'# 6) Sair");
		System.out.println();
		System.out.println("Escolha uma opção: ");
		
		int opcao = Console.lerInteiro();
		
		if(opcao < OPCAO_INSERIR || opcao > OPCAO_SAIR) {
			throw new AgendaException("Opção inválida");
		}
		
		return opcao;
	}
}
