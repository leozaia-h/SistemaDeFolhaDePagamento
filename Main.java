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
                
            }
            else if(escolha == 5) {
                
            }
            else if(escolha == 6) {
                menu.alterar_dados();
            }
            else if(escolha == 7) {
                break;
            }
            else if(escolha == 8) {
                Funcoes.Lista_de_empregados();
            }
            else {
                menu.alert_escolha_invalida();
            }
        }
    }
}
