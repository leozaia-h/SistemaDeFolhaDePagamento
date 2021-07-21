import java.util.LinkedList;
import java.util.Scanner;

public class Funcoes {

    LinkedList<Horista> Horista = new LinkedList<Horista>();
    LinkedList<Assalariado> Assalariado = new LinkedList<Assalariado>();
    LinkedList<Comissionado> Comissionado = new LinkedList<Comissionado>();

    Scanner input = new Scanner(System.in);
    Menu menu = new Menu();

	public void add_horista (int id) {
		Horista Novo_horista = new Horista();

		menu.add_nome();
        Novo_horista.nome = input.nextLine();
            
        menu.add_endereco();
        Novo_horista.endereco = input.nextLine();

        menu.salario_horista();
        Novo_horista.salario = input.nextDouble();
		
		Novo_horista.id = id;
		
        for (int i = 0; i < 30; i++) {
            Novo_horista.cartao_do_empregado[i] = new Cartao_de_ponto();
        }
		
		Horista.add(Novo_horista);
	}

    public void addComissionado (int id) {
        Comissionado novoComissionado = new Comissionado();

        menu.add_nome();
        novoComissionado.nome = input.nextLine();

        menu.add_endereco();
        novoComissionado.endereco = input.nextLine();

        menu.porcentagem_comissao();
        novoComissionado.taxaComissao = input.nextDouble();

        menu.ver_sindicato();
        int aux = input.nextInt();
        input.nextLine();
        if(aux == 1) {
            novoComissionado.ver_sindicato = 1;

            novoComissionado.taxa_sindicato = input.nextDouble();
        }
        else {
            novoComissionado.ver_sindicato = 0;
        }

        menu.tipo_pagamento();
        novoComissionado.tipo_pagamento = input.nextInt();
        input.nextLine();

        novoComissionado.id = id;
    }
	
	public void add_assalariado (int id) {
		Assalariado novoAssalariado = new Assalariado();
		
		menu.add_nome();
        novoAssalariado.nome = input.nextLine();

        menu.add_endereco();
        novoAssalariado.endereco = input.nextLine();

        menu.salario_mensal();
        novoAssalariado.salario = input.nextDouble();
		
		novoAssalariado.id = id;
		
		menu.tipo_pagamento();
		
		menu.ver_sindicato();
		novoAssalariado.ver_sindicato = input.nextInt();
		input.nextLine();
		
		if(novoAssalariado.ver_sindicato == 1) {
			menu.taxa_sindicato();
			novoAssalariado.taxa_sindicato = input.nextDouble();
		}
		
		menu.tipo_pagamento();
		novoAssalariado.tipo_pagamento = input.nextInt();
		input.nextLine();
		
		Assalariado.add(novoAssalariado);
	}

    public void Lista_de_empregados () {
        System.out.println("Horistas:");
        for (int i = 0; i < Horista.size(); i++) {
            Horista aux = Horista.get(i);
            System.out.printf("%s(%d)\n", aux.nome, aux.id);
            for (int j = 0; j < 30; j++) {
                if(aux.cartao_do_empregado[j].flag_trabalhou == 1) {
                    System.out.printf("dia %d  :  %dh\n", j + 1, aux.cartao_do_empregado[j].saida - aux.cartao_do_empregado[j].entrada);
                }
            }
        }
        System.out.println("Assalariado:");
        for (int i = 0; i < Assalariado.size(); i++) {
            Assalariado aux = Assalariado.get(i);
            System.out.printf("%s(%d)\n", aux.nome, aux.id);
        }
    }

    public void Rm_empregado() {
        System.out.println("Digite o ID do funcionario que deseja remover:");
        int id = input.nextInt();
        input.nextLine();

        for (int i = 0; i < Horista.size(); i++) {
            Horista aux = Horista.get(i);

            if(aux.id == id) {
                Horista.remove(i);
                menu.empregado_removido();
            }
        }
		for (int i = 0; i < Assalariado.size(); i++) {
            Assalariado aux = Assalariado.get(i);

            if(aux.id == id) {
                Assalariado.remove(i);
                menu.empregado_removido();
            }
        }
    }

    public void cartao_de_ponto(int id, int dia) {
        menu.inOut();
        int escolha = input.nextInt();
        input.nextLine();

        for (int i = 0; i < Horista.size(); i++) {
            Horista aux = Horista.get(i);

            if(aux.id == id) {
                if(escolha == 1) {
                    menu.carto_de_ponto_chegada();
                    int horarioChegada = input.nextInt();
                    input.nextLine();
                    aux.cartao_do_empregado[dia].entrada = horarioChegada;
                }
                else if(escolha == 2) {
                    menu.carto_de_ponto_saida();
                    int horarioSaida = input.nextInt();
                    input.nextLine();
                    aux.cartao_do_empregado[dia].saida = horarioSaida;
                    aux.cartao_do_empregado[dia].flag_trabalhou = 1;
                }
                else {
                    System.out.println("Casa");
                }
            }
        }
    }

    public void vendas(int id) {
        for (int i = 0; i < Comissionado.size(); i++) {
            Comissionado aux = Comissionado.get(i);

            if(aux.id == id ) {
                menu.valorVenda();
                Double venda = input.nextDouble();
                aux.valor_vendas += venda;
                System.out.printf("O valor de %f foi adicionado às vendas do funcionário %d", venda, id);
            }
            else {
                System.out.println("Funcionario não comissionado.");
            }
        }
    }

    public void taxa_de_servico(int id) {
        for (int i = 0; i < Assalariado.size(); i++) {
            Assalariado aux = Assalariado.get(i);

            if(aux.id == id) {
                if(aux.ver_sindicato == 1) {
                    menu.digitarTaxa();
                    double servico = input.nextDouble();
                    aux.taxa_servico = servico;
                }
            }
        }
    }

    public void alterarDados(int escolha) {
        switch (escolha) {
            case 1:
                menu.alterarDadosNome();

                break;
        
            default:
                break;
        }
    }

    public Horista procurarHorista(int id) {
        for (int i = 0; i < Horista.size(); i++) {
            Horista aux = Horista.get(i);

            if(aux.id == id) {
                return aux;
            }
        }
        return null;
    }

    public Assalariado procurarAssalariado(int id) {
        for (int i = 0; i < Assalariado.size(); i++) {
            Assalariado aux = Assalariado.get(i);

            if(aux.id == id) {
                return aux;
            }
        }
        return null;
    }

    public Comissionado procurarComissionado(int id) {
        for (int i = 0; i < Comissionado.size(); i++) {
            Comissionado aux = Comissionado.get(i);

            if(aux.id == id) {
                return aux;
            }
        }
        return null;
    }
}
