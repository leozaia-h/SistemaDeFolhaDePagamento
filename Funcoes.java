import java.util.LinkedList;
import java.util.Scanner;

public class Funcoes {

    LinkedList<Horista> Horista = new LinkedList<Horista>();
    LinkedList<Assalariado> Assalariado = new LinkedList<Assalariado>();

    Scanner input = new Scanner(System.in);
    Menu menu = new Menu();

	public void add_horista (int id, int idSindicato) {
		Horista novoHorista = new Horista();

		menu.add_nome();
        novoHorista.nome = input.nextLine();
            
        menu.add_endereco();
        novoHorista.endereco = input.nextLine();

        menu.salario_horista();
        novoHorista.salario = input.nextDouble();

        menu.tipo_pagamento();
        novoHorista.tipo_pagamento = input.nextInt();
        input.nextLine();
		
		novoHorista.id = id;

        menu.ver_sindicato();
		novoHorista.ver_sindicato = input.nextInt();
		input.nextLine();
		
		if(novoHorista.ver_sindicato == 1) {
            novoHorista.idSindicato = idSindicato;

			menu.taxa_sindicato();
			novoHorista.taxa_sindicato = input.nextDouble();
		}

        for (int i = 0; i < 31; i++) {
            novoHorista.cartao_do_empregado[i] = new Cartao_de_ponto();
        }
		
		Horista.add(novoHorista);
	}
	
	public void add_assalariado (int id, int idSindicato) {
		Assalariado novoAssalariado = new Assalariado();
		
		menu.add_nome();
        novoAssalariado.nome = input.nextLine();

        menu.add_endereco();
        novoAssalariado.endereco = input.nextLine();

        menu.salario();
        novoAssalariado.salario = input.nextDouble();
		
		novoAssalariado.id = id;
		
		menu.tipo_pagamento();
		novoAssalariado.tipo_pagamento = input.nextInt();
        input.nextLine();

        menu.ver_comissao();
        novoAssalariado.verComissao = input.nextInt();
        input.nextLine();

		menu.ver_sindicato();
		novoAssalariado.ver_sindicato = input.nextInt();
		input.nextLine();
		
		if(novoAssalariado.ver_sindicato == 1) {
            novoAssalariado.idSindicato = idSindicato;

			menu.taxa_sindicato();
			novoAssalariado.taxa_sindicato = input.nextDouble();
		}
		
		Assalariado.add(novoAssalariado);
	}

    public void Lista_de_empregados () {

        System.out.println("Assalariado:");
        for (int i = 0; i < Assalariado.size(); i++) {
            Assalariado aux = Assalariado.get(i);
            System.out.printf("%s(%d)\n", aux.nome, aux.id);
            System.out.printf("Salario: %.2f\nSindicato: %d\nTaxa: %.2f\nidSin: %d\n", aux.salario, aux.ver_sindicato, aux.taxa_sindicato, aux.idSindicato);
        }

        System.out.println("Horistas:");
        for (int i = 0; i < Horista.size(); i++) {
            Horista aux = Horista.get(i);
            System.out.printf("%s(%d)\n", aux.nome, aux.id);
            System.out.printf("Salario: %.2f\nSindicato: %d\nTaxa: %.2f\nidSin: %d\n", aux.salario, aux.ver_sindicato, aux.taxa_sindicato, aux.idSindicato);

            for (int j = 0; j < 30; j++) {
                if(aux.cartao_do_empregado[j].flag_trabalhou == 1) {
                    System.out.printf("dia %d  :  %dh\n", j + 1, aux.cartao_do_empregado[j].saida - aux.cartao_do_empregado[j].entrada);
                }
            }
        }
    }

    public void Rm_empregado(int id) {

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
        for (int i = 0; i < Assalariado.size(); i++) {
            Assalariado aux = Assalariado.get(i);

            if(aux.id == id && aux.verComissao == 1) {
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

    public void alterarDados(int escolha, int id, int idSindicato, int diasMes) {
        Horista horista = new Horista();
        Assalariado assalariado = new Assalariado();

        horista = procurarHorista(id);
        assalariado = procurarAssalariado(id);
        
        switch (escolha) {
            case 1:
                menu.alterarDadosNome();
                String novoNome = input.nextLine();

                if(horista != null) {
                    horista.nome = novoNome;
                }
                else if(assalariado != null) {
                    assalariado.nome = novoNome;
                }
                else {
                    System.out.println("Empregado não encontrado");
                }
                break;
            case 2:
                menu.alterarDadosEndereco();
                String novoEndereco = input.nextLine();

                if(horista != null) {
                    horista.nome = novoEndereco;
                }
                else if(assalariado != null) {
                    assalariado.nome = novoEndereco;
                }
                else {
                    System.out.println("Empregado não encontrado");
                }
                break;
            case 3:
                menu.alterarDadosTipoPagamento();
                int escolhaTipoEmpregado = input.nextInt();
                input.nextLine();

                if(escolhaTipoEmpregado == 1) {
                    if(horista != null) {
                        alterarPagamentoAssalariado(horista, id, idSindicato);
                        removerHorista(id);
                    }
                    else {
                        System.out.println("O funcionário já é assalariado!");
                    }
                }
                else if(escolhaTipoEmpregado == 2) {
                    if(assalariado != null) {
                        alterarPagamentoHorista(assalariado, id, idSindicato);
                        removerAssalariado(id);
                    }
                    else {
                        System.out.println("O funcionário já é horista!");
                    }
                }
                break;
            case 4:
                menu.alterarDadosMetodoPagamento();
                int novoTipoPagamento = input.nextInt();
                input.nextLine();

                if(horista != null) {
                    horista.tipo_pagamento = novoTipoPagamento;
                }
                else if(assalariado != null) {
                    assalariado.tipo_pagamento = novoTipoPagamento;
                }
                else {
                    System.out.println("Empregado não encontrado");
                }
                break;
            case 5:
                menu.alterarDadosSindicato();
                int novoIdentSindicato = input.nextInt();
                input.nextLine();

                int taxaSind = 0;

                if(novoIdentSindicato == 2) {
                    novoIdentSindicato = 0;
                }
                else {
                    novoIdentSindicato = 1;
                    menu.alterarDadosNovaTaxaSind();
                    taxaSind = input.nextInt();
                    input.nextLine();
                }

                if(horista != null) {
                    horista.ver_sindicato = novoIdentSindicato;
                    horista.idSindicato = idSindicato;
                    horista.taxa_sindicato = taxaSind;
                }
                else if(assalariado != null) {
                    assalariado.ver_sindicato = novoIdentSindicato;
                    assalariado.idSindicato = idSindicato;
                    assalariado.taxa_sindicato = taxaSind;
                }
                else {
                    System.out.println("Empregado não encontrado");
                }
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

    public void alterarPagamentoAssalariado(Horista func, int id, int idSindicato) {
        Assalariado novoAssalariado = new Assalariado();
		
        novoAssalariado.nome = func.nome;
        novoAssalariado.endereco = func.endereco;
		novoAssalariado.id = id;
        novoAssalariado.idSindicato = idSindicato;
        novoAssalariado.ver_sindicato = func.ver_sindicato;
		novoAssalariado.taxa_sindicato = func.taxa_sindicato;
		novoAssalariado.tipo_pagamento = func.tipo_pagamento;
        novoAssalariado.valor_vendas = 0;
        novoAssalariado.taxa_servico = 0;
        menu.salario();
        novoAssalariado.salario = input.nextDouble();
        menu.ver_comissao();
        novoAssalariado.verComissao = input.nextInt();
        input.nextLine();
        if(novoAssalariado.verComissao == 1) {
            menu.digitarTaxa();
            novoAssalariado.taxaComissao = input.nextDouble();
        }
		Assalariado.add(novoAssalariado);
    }

    public void alterarPagamentoHorista(Assalariado func, int id, int idSindicato) {
        Horista novoHorista = new Horista();

        novoHorista.nome = func.nome;
        novoHorista.endereco = func.endereco;
        novoHorista.id = id;
        novoHorista.idSindicato = idSindicato;
        novoHorista.taxa_sindicato = func.taxa_sindicato;
		novoHorista.tipo_pagamento = func.tipo_pagamento;
        novoHorista.taxa_servico = 0;
        menu.salario();
        novoHorista.salario = input.nextDouble();

        for (int i = 0; i < 31; i++) {
            novoHorista.cartao_do_empregado[i] = new Cartao_de_ponto();
        }
		
		Horista.add(novoHorista);
    }

    public void pagarAssalariados() {
        for (int i = 0; i < Assalariado.size(); i++) {
            Assalariado funcionario = Assalariado.get(i);
            if(funcionario.verComissao == 0) {
                System.out.printf("Foi pago R$%.2f para %s(%d)\n", funcionario.salario, funcionario.nome, funcionario.id);
            }
        }   
    }

    public void rodarPagamento(int hoje, int ultimoDiaUtil) {
        if(hoje == ultimoDiaUtil) {
            pagarAssalariados();
        }
    }

    public void removerHorista(int id) {
        for (int i = 0; i < Horista.size(); i++) {
            Horista aux = Horista.get(i);

            if(aux.id == id) {
                Horista.remove(i);
                menu.empregado_removido();
            }
        }
    }

    public void removerAssalariado(int id) {
        for (int i = 0; i < Assalariado.size(); i++) {
            Assalariado aux = Assalariado.get(i);

            if(aux.id == id) {
                Assalariado.remove(i);
                menu.empregado_removido();
            }
        }
    }
}
