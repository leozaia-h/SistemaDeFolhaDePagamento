import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;

public class Funcoes {

    LinkedList<Horista> Horista = new LinkedList<Horista>();
    LinkedList<Assalariado> Assalariado = new LinkedList<Assalariado>();

    Scanner input = new Scanner(System.in);
    Menu menu = new Menu();
    FolhaDePagamento folhaDePagamento = new FolhaDePagamento();

	public void addHorista (int id, int idSindicato) {
		Horista novoHorista = new Horista();

		menu.addName();
        novoHorista.nome = input.nextLine();
            
        menu.addAddress();
        novoHorista.endereco = input.nextLine();

        menu.salario_horista();
        novoHorista.salario = input.nextDouble();

        menu.tipo_pagamento();
        novoHorista.tipo_pagamento = input.nextInt();
        input.nextLine();
		
		novoHorista.id = id;

        menu.verSindicate();
		novoHorista.verSindicate = input.nextInt();
		input.nextLine();
		
		if(novoHorista.verSindicate == 1) {
            novoHorista.idSindicato = idSindicato;

			menu.taxSindicate();
			novoHorista.taxSindicate = input.nextDouble();
		}

        for (int i = 0; i < 30; i++) {
            novoHorista.employeeTimecard[i] = new Timecard();
        }

        novoHorista.agendaPagamento = formaPagamento();
		
		Horista.add(novoHorista);
	}
	
    public String formaPagamento() {
        String sSemanas = "";
        String result = "";
        String sDias = "";
        menu.formaPagamento();
        int escolha = input.nextInt();
        input.nextLine();

        if(escolha == 2) {
            result = "Semanal";

            menu.semanasPagamento();
            int semanas = input.nextInt();
            input.nextLine();

            menu.diaSemana();
            int dia = input.nextInt();
            input.nextLine();

            sSemanas = Integer.toString(semanas);

            if(dia == 2) {
                sDias = "segunda";
            }
            else if(dia == 3) {
                sDias = "terça";
            }
            else if(dia == 4) {
                sDias = "quarta";
            }
            else if(dia == 5) {
                sDias = "quinta";
            }
            else if(dia == 6) {
                sDias = "sexta";
            }
            result += " " + sSemanas + " " + sDias;
        }
        else if (escolha == 1) {
            result = "Mensal";

            menu.ultimoDia();
            int ultimoDia = input.nextInt();
            input.nextLine();

            if(ultimoDia != 1) {
                menu.dia();
                int dia = input.nextInt();
                input.nextLine();
                String sDia = Integer.toString(dia);
                result += " " + sDia;
            }
            else {
                result += " $";
            }
        }
        return result;
    }

	public void add_assalariado (int id, int idSindicato) {
		Assalariado novoAssalariado = new Assalariado();
		
		menu.addName();
        novoAssalariado.nome = input.nextLine();

        menu.addAddress();
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

        if(novoAssalariado.verComissao == 1) {
            menu.porcentagem_comissao();
            novoAssalariado.taxaComissao = input.nextDouble();
        }
		menu.verSindicate();
		novoAssalariado.verSindicate = input.nextInt();
		input.nextLine();
		
		if(novoAssalariado.verSindicate == 1) {
            novoAssalariado.idSindicato = idSindicato;

			menu.taxSindicate();
			novoAssalariado.taxSindicate = input.nextDouble();
		}
		
        novoAssalariado.agendaPagamento = formaPagamento();

		Assalariado.add(novoAssalariado);
	}

    public void Lista_de_empregados () {

        System.out.println("Assalariado:");
        System.out.println("-------------------------");
        for (int i = 0; i < Assalariado.size(); i++) {
            Assalariado aux = Assalariado.get(i);
            System.out.printf("%s[ID: %d / IDs: %d]\n", aux.nome, aux.id, aux.idSindicato);
            System.out.printf("Salario: %.2f\nSindicato: %d\nTaxa: %.2f\nAgenda de Pagamento: %s\n", aux.salario, aux.verSindicate, aux.taxSindicate, aux.agendaPagamento);
            System.out.println("-------------------------");
        }

        System.out.println("Horistas:");
        System.out.println("-------------------------");
        for (int i = 0; i < Horista.size(); i++) {
            Horista aux = Horista.get(i);
            System.out.printf("%s[ID: %d / IDs: %d]\n", aux.nome, aux.id, aux.idSindicato);
            System.out.printf("Salario: %.2f\nSindicato: %d\nTaxa: %.2f\nAgenda de Pagamento: %s\n", aux.salario, aux.verSindicate, aux.taxSindicate, aux.agendaPagamento);

            for (int j = 0; j < 30; j++) {
                if(aux.employeeTimecard[j].flag_trabalhou == 1) {
                    System.out.printf("dia %d  :  %dh\n", j + 1, aux.employeeTimecard[j].saida - aux.employeeTimecard[j].entrada);
                }
            }
            System.out.println("-------------------------");
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
                    aux.employeeTimecard[dia].entrada = horarioChegada;
                }
                else if(escolha == 2) {
                    menu.carto_de_ponto_saida();
                    int horarioSaida = input.nextInt();
                    input.nextLine();
                    aux.employeeTimecard[dia].saida = horarioSaida;
                    aux.employeeTimecard[dia].flag_trabalhou = 1;
                }
                else {
                    System.out.println("Escolha inválida");
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
                System.out.printf("O valor de %.2f foi adicionado às vendas do funcionário %d\n", venda, id);
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
                if(aux.verSindicate == 1) {
                    menu.digitarTaxa();
                    double servico = input.nextDouble();
                    input.nextLine();
                    aux.taxa_servico = servico;
                }
                else {
                    System.out.println("Esse funcionario não é comissionado!");
                }
            }
        }
    }

    public void alterarDados(int escolha, int id, int idSindicato, int diasMes) {
        Horista horista = new Horista();
        Assalariado assalariado = new Assalariado();
        String novoNome;

        horista = procurarHorista(id);
        assalariado = procurarAssalariado(id);
        
        switch (escolha) {
            case 1:
                menu.alterarDadosNome();
                novoNome = input.nextLine();

                if(horista == null && assalariado == null) {
                    System.out.println("Empregado não encontrado");
                }
                else if(horista != null) {
                    horista.nome = novoNome;
                }
                else if(assalariado != null) {
                    assalariado.nome = novoNome;
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
                    horista.verSindicate = novoIdentSindicato;
                    horista.idSindicato = idSindicato;
                    horista.taxSindicate = taxaSind;
                }
                else if(assalariado != null) {
                    assalariado.verSindicate = novoIdentSindicato;
                    assalariado.idSindicato = idSindicato;
                    assalariado.taxSindicate = taxaSind;
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
        novoAssalariado.verSindicate = func.verSindicate;
		novoAssalariado.taxSindicate = func.taxSindicate;
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

        novoAssalariado.agendaPagamento = formaPagamento();

		Assalariado.add(novoAssalariado);
    }

    public void alterarPagamentoHorista(Assalariado func, int id, int idSindicato) {
        Horista novoHorista = new Horista();

        novoHorista.nome = func.nome;
        novoHorista.endereco = func.endereco;
        novoHorista.id = id;
        novoHorista.idSindicato = idSindicato;
        novoHorista.taxSindicate = func.taxSindicate;
		novoHorista.tipo_pagamento = func.tipo_pagamento;
        novoHorista.taxa_servico = 0;
        menu.salario();
        novoHorista.salario = input.nextDouble();

        for (int i = 0; i < 30; i++) {
            novoHorista.employeeTimecard[i] = new Timecard();
        }

        novoHorista.agendaPagamento = formaPagamento();
		
		Horista.add(novoHorista);
    }

    public void pagarAssalariados(int hoje, int ultimoDiaUtil) {
        double salario = 0;

        for (int i = 0; i < Assalariado.size(); i++) {
            Assalariado funcionario = Assalariado.get(i);
            String sHoje = Integer.toString(hoje);

            if(funcionario.agendaPagamento.contains(sHoje) || (funcionario.agendaPagamento.contains("$") && hoje == ultimoDiaUtil)) {
                if(funcionario.verComissao == 0) {
                    salario += funcionario.taxa_servico + funcionario.taxSindicate + funcionario.salario;
                    System.out.printf("[%d]%s\nSalario: R$%.2f\nTaxa de Serviço: R$%.2f\nTaxa Sindicato: R$%.2f\nTotal: R$ %.2f\n\n",funcionario.id, funcionario.nome, funcionario.salario, funcionario.taxa_servico, funcionario.taxSindicate, salario);
                }
                else {
                    salario = ((funcionario.taxaComissao/100)*funcionario.valor_vendas) + funcionario.salario + funcionario.taxa_servico + funcionario.taxSindicate;
                    System.out.printf("[%d]%s\nSalario: R$%.2f\nTaxa de Serviço: R$%.2f\nTaxa Sindicato: R$%.2f\nComissao: R$%.2f\nTotal: R$%.2f\n\n",funcionario.id, funcionario.nome, funcionario.salario, funcionario.taxa_servico, funcionario.taxSindicate, ((funcionario.taxaComissao/100)*funcionario.valor_vendas),salario);
                }
            }
        }   
    }

    public void pagarHoristas(int hoje) {
        int horasDia;
        float salario = 0;

        for (int i = 0; i < Horista.size(); i++) {
            Horista funcionario = Horista.get(i);
            String sHoje = folhaDePagamento.sHoje();

            System.out.printf("[%d]%s\nHoras trabalhadas:\n", funcionario.id, funcionario.nome);

            if(funcionario.agendaPagamento.contains(sHoje)) {
                for (int j = 0; j < 30; j++) {
                    if(funcionario.employeeTimecard[j].flag_trabalhou == 1) {
                        horasDia = funcionario.employeeTimecard[j].saida - funcionario.employeeTimecard[j].entrada;
                        if(horasDia <= 8) {
                            salario += horasDia * funcionario.salario;
                        }
                        else {
                            salario += 8 * funcionario.salario;
                            salario += ((horasDia - 8) * (funcionario.salario * 1.5));
                        }
                        System.out.printf("Dia %d: %dh\n", j+1, horasDia);
                    }
                }
            }
            System.out.printf("Total por horas: R$%.2f\n", salario);
            salario += funcionario.taxa_servico + funcionario.taxSindicate;
            System.out.printf("Taxa Sindicato: R$%.2f\n", funcionario.taxSindicate);
            System.out.printf("Taxa de serviço: R$%.2f\n", funcionario.taxa_servico);
            System.out.printf("Total a pagar: R$%.2f\n\n", salario);
            salario = 0;
        }
    }


    public void rodarPagamento(int hoje, int ultimoDiaUtil) {
        pagarAssalariados(hoje, ultimoDiaUtil);
        pagarHoristas(hoje);
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
