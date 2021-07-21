public class Menu {

    public void init() {
        System.out.println("SISTEMA DE FOLHA DE PAGAMENTOS");
    }
    
    public void escolha() {
        System.out.println("[1] - Adicionar empregado");
        System.out.println("[2] - Remover empregado");
        System.out.println("[3] - Lançar cartão de ponto");
        System.out.println("[4] - Lançar Resultado Venda");
        System.out.println("[5] - lançar uma taxa de serviço");
        System.out.println("[6] - Alterar detalhes de um empregado");
        System.out.println("[7] - Listar todos empregados");
        System.out.println("[8] - Sair");
    }

    public void add_nome() {
        System.out.println("Digite o nome do empregado:");
    }

    public void add_endereco() {
        System.out.println("Digite o nome do endereço do empregado:");
    }

    public void tipo_pagamento() {
        System.out.println("Digite a forma de pagamento do funcionario:");
    }

    public void alert_escolha_invalida() {
        System.out.println("Escolha inválida");
        System.out.println("Digite novamente:");
    }

    public void empregado_removido() {
        System.out.println("Empregado removido com sucesso!");
    }

    public void ver_comissao() {
        System.out.println("1 - Comissionado");
        System.out.println("2 - Não comissionado");
    }

    public void porcentagem_coissao () {
        System.out.println("Digite a porcentagem da comissão:");
    }

    public void menu_carto_de_ponto () {
        System.out.println("1 - Horario de chegada");
        System.out.println("2 - Horario de saída");
    }

    public void id() {
        System.out.println("Digite o ID do empregado:");
    }

    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.printf("\n");
        }
    }

    public void salario_horista(){
        System.out.println("Digite o salario por hora:");
    }
	
	public void salario_mensal(){
        System.out.println("Digite o salario mensal:");
    }

    public void carto_de_ponto_chegada() {
        System.out.println("Digite o horario de chegada:");
    }
    public void carto_de_ponto_saida() {
        System.out.println("Digite o horario de saída:");
    }
	
	public void ver_sindicato() {
		System.out.println("O empregado participa do sindicato?");
		System.out.println("1 - SIM			2 - NÃO");
	}
	
	public void taxa_sindicato () {
		
	}

    public void porcentagem_comissao () {
        System.out.println("Digite a porcentagem da comissão:");
    }

    public void tipoFuncionario() {
        System.out.println("Digite o tipo do funcionario:");
        System.out.println("1 - Assalariado   2 - Horista");
    }

    public void digiteId() {
        System.out.println("Digite o ID do empregado:");
    }

    public void dia() {
        System.out.println("Digite o dia de hoje:");
    }

    public void inOut() {
        System.out.println("1 - Entrada");
        System.out.println("2 - Saída");
    }

    public void venda() {
        System.out.println("Digite o ID do funcionário:");
    }

    public void taxa_de_servico() {
        System.out.println("Digite o ID do funcionário:");
    }

    public void digitarTaxa() {
        System.out.println("Digite o valor da taxa a ser cobrada:");
    }

    public void alterarDados() {
        System.out.println("Qual dado você deseja alterar?");
        System.out.println("[1] - Nome");
        System.out.println("[2] - Endereço");
        System.out.println("[3] - Tipo do funcionário");
        System.out.println("[4] - Método de pagamento");
        System.out.println("[5] - Membro do sindicato");
        System.out.println("[6] - Identificação no sindicato");
        System.out.println("[7] - Taxa sindical");
    }

    public void alterarDadosNome() {
        System.out.println("Digite o ");
    }

    public void valorVenda() {
        System.out.println("Digite o valor da venda:");
    }
}
