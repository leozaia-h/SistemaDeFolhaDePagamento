import java.util.LinkedList;
import java.util.Scanner;

public class Funcoes {

    LinkedList<Horista> Horista = new LinkedList<Horista>();
    LinkedList<Mensal> Mensal = new LinkedList<Mensal>();

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
	
	public void add_assalariado (int id) {
		Mensal Novo_mensal = new Mensal();
		
		menu.add_nome();
        Novo_mensal.nome = input.nextLine();

        menu.add_endereco();
        Novo_mensal.endereco = input.nextLine();

        menu.salario_mensal();
        Novo_mensal.salario = input.nextDouble();
		
		Novo_mensal.id = id;
		
		menu.tipo_pagamento();
		
		menu.ver_comissao();
		Novo_mensal.ver_comissao = input.nextInt();
		input.nextLine();
		
		if(Novo_mensal.ver_comissao == 1) {
			menu.porcentagem_comissao();
			Novo_mensal.comissao = input.nextDouble();
		}
		
		menu.ver_sindicato();
		Novo_mensal.ver_sindicato = input.nextInt();
		input.nextLine();
		
		if(Novo_mensal.ver_sindicato == 1) {
			menu.taxa_sindicato();
			Novo_mensal.taxa_sindicato = input.nextDouble();
		}
		
		menu.tipo_pagamento();
		Novo_mensal.tipo_pagamento = input.nextInt();
		input.nextLine();
		
		Mensal.add(Novo_mensal);
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
        System.out.println("Mensal:");
        for (int i = 0; i < Mensal.size(); i++) {
            Mensal aux = Mensal.get(i);
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
		for (int i = 0; i < Mensal.size(); i++) {
            Mensal aux = Mensal.get(i);

            if(aux.id == id) {
                Mensal.remove(i);
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
}
