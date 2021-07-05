import java.util.LinkedList;
import java.util.Scanner;

public class Funcoes {

    LinkedList<Horista> Horista = new LinkedList<Horista>();
    LinkedList<Mensal> Mensal = new LinkedList<Mensal>();

    Scanner input = new Scanner(System.in);
    Menu menu = new Menu();

    public int pagamento(int escolha) {
        Horista horista = new Horista();
        Mensal mensal = new Mensal();

        while(true) {
            if(escolha == 1) {
                System.out.printf("Salario por hora: ");
                horista.salario = input.nextDouble();
                input.nextLine();

                return 1;
            }
            else if(escolha == 2) {
                System.out.printf("Salario mensal: ");
                mensal.salario = input.nextDouble();
                input.nextLine();

                return 2;
            }
            else {
                menu.alert_escolha_invalida();
                escolha = input.nextInt();
                input.nextLine();

                continue;
            }
        }
    }

    public void Add_empregado (int id) {
        int tipo_pagamento;
        Horista Novo_horista = new Horista();
        Mensal Novo_mensal = new Mensal();

        menu.add_tipo_pagamento();
        tipo_pagamento = input.nextInt();
        input.nextLine();

        if(tipo_pagamento == 1) { //Horista
            menu.add_nome();
            Novo_horista.nome = input.nextLine();
            
            menu.add_endereco();
            Novo_horista.endereco = input.nextLine();

            menu.salario();
            Novo_horista.salario = input.nextDouble();

            Novo_horista.id = id;

            for (int i = 0; i < 30; i++) {
                Novo_horista.cartao_do_empregado[0].flag_trabalhou = 1;
            }

            Horista.add(Novo_horista);
        }
        else if(tipo_pagamento == 2) { //Mensal
            menu.add_nome();
            Novo_mensal.nome = input.nextLine();
            
            menu.add_endereco();
            Novo_mensal.endereco = input.nextLine();

            menu.salario();
            Novo_mensal.salario = input.nextDouble();

            menu.ver_comissao();
            Novo_mensal.ver_comissao = input.nextInt();
            input.nextLine();

            if(Novo_mensal.ver_comissao == 1) {
                menu.add_comissao();
                Novo_mensal.comissao = input.nextDouble();
            }

            Novo_mensal.id = id;

            Mensal.add(Novo_mensal);
        }
    }

    public void Lista_de_empregados () {
        System.out.println("Horistas:");
        for (int i = 0; i < Horista.size(); i++) {
            Horista aux = Horista.get(i);
            System.out.printf("%s(%d)\n", aux.nome, aux.id);
            for (int j = 0; j < 30; j++) {
                if(aux.cartao_do_empregado[j].flag_trabalhou != 1) {
                    System.out.printf("dia %d  :  %dh", j, aux.cartao_do_empregado[j].saida - aux.cartao_do_empregado[j].entrada);
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
    }

    public void cartao_de_ponto(int id) {
        for (int i = 0; i < Horista.size(); i++) {
            Horista empregado = Horista.get(i);

            if(empregado.id == id) {
                menu.menu_carto_de_ponto();
                int escolha_cartao = input.nextInt();
                input.nextLine();

                if(escolha_cartao == 1) {
                    menu.carto_de_ponto_chegada();
                    for (int j = 0; j < 30; j++) {
                        if(empregado.cartao_do_empregado[j].flag_trabalhou == 0) {
                            empregado.cartao_do_empregado[j].entrada = input.nextInt();
                            input.nextLine();

                            break;
                        }
                    }
                }
                else if(escolha_cartao == 2) {
                    menu.carto_de_ponto_saida();
                    for (int j = 0; j < 30; j++) {
                        if(empregado.cartao_do_empregado[j].flag_trabalhou == 0) {
                            empregado.cartao_do_empregado[j].saida = input.nextInt();
                            input.nextLine();

                            empregado.cartao_do_empregado[j].flag_trabalhou = 1;

                            break;
                        }
                    }
                }
            }
        }
    }
}
