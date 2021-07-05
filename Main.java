import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Funcoes Funcoes = new Funcoes();
        Menu menu = new Menu();
        int escolha = 0;
        int id = 0;
        while(true) {
            menu.init();
            menu.escolha();

            escolha = input.nextInt();

            if(escolha == 1) {
                Funcoes.Add_empregado(id);
                id++;
            }
            else if(escolha == 2) {
                Funcoes.Rm_empregado();
            }
            else if(escolha == 3) {
                menu.id();
                int aux = input.nextInt();
                Funcoes.cartao_de_ponto(aux);
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
