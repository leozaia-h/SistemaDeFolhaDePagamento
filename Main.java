import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Funcoes Funcoes = new Funcoes();
        Menu menu = new Menu();
        int escolha = 0;
        int id = 0;
        int dia;

        while(true) {
            menu.init();
            menu.escolha();

            escolha = input.nextInt();

            if(escolha == 1) {
                menu.tipoFuncionario();
                int tipoFuncionario = input.nextInt();
                input.nextLine();

                if(tipoFuncionario == 1) {
                    Funcoes.add_assalariado(id);
                }
                else if(tipoFuncionario == 2) {
                    Funcoes.add_horista(id);
                }
                id++;
            }
            else if(escolha == 2) {
                Funcoes.Rm_empregado();
            }
            else if(escolha == 3) {
                menu.id();
                int aux = input.nextInt();
                menu.dia();
                dia = input.nextInt() - 1;
                Funcoes.cartao_de_ponto(aux, dia);
            }
            else if(escolha == 4) {
                menu.venda();
                int aux = input.nextInt();
                Funcoes.vendas(aux);
            }
            else if(escolha == 5) {
                menu.taxa_de_servico();
                int aux = input.nextInt();
                Funcoes.taxa_de_servico(aux);
            }
            else if(escolha == 6) {
                menu.alterarDados();
                int aux = input.nextInt();
                Funcoes.alterarDados(aux);
            }
            else if(escolha == 7) {
                Funcoes.Lista_de_empregados();
            }
            else if(escolha == 8) {
                break;
            }
            else {
                menu.alert_escolha_invalida();
            }
        }
    }
}
