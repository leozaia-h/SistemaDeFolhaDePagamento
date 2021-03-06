import java.util.Scanner;
/**
 * Falta:
 * Undo/redo
**/
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Funcoes Funcoes = new Funcoes();
        Menu menu = new Menu();
        FolhaDePagamento f = new FolhaDePagamento();


        int escolha = 0;
        int id = 0;
        int idSindicato = 0;
        int dia = f.hoje();
        int diasMes = f.diasMes();
        int ultimoDiaUtil = f.getUltimoDiaUtilDoMes();

        while(true) {
            menu.init();
            menu.escolha();

            if(escolha == 1) {
                System.out.printf("O ID do funcionario adicionado é %d\n", id-1);
            }

            escolha = input.nextInt();
            input.nextLine();

            menu.clearScreen(); 
        
            if(escolha == 1) {
                menu.tipoFuncionario();
                int tipoFuncionario = input.nextInt();
                input.nextLine();

                if(tipoFuncionario == 1) {
                    Funcoes.add_assalariado(id, idSindicato);
                }
                else if(tipoFuncionario == 2) {
                    Funcoes.addHorista(id, idSindicato);
                }
                id++;
                idSindicato++;
            }
            else if(escolha == 2) {
                menu.rmEmpregado();
                int aux = input.nextInt();
                input.nextLine();

                Funcoes.Rm_empregado(aux);
            }
            else if(escolha == 3) {
                menu.id();
                int aux = input.nextInt();
                input.nextLine();
                Funcoes.cartao_de_ponto(aux, dia-1);
            }
            else if(escolha == 4) {
                menu.id();
                int aux = input.nextInt();
                Funcoes.vendas(aux);
            }
            else if(escolha == 5) {
                menu.id();
                int aux = input.nextInt();
                input.nextLine();
                Funcoes.taxa_de_servico(aux);
            }
            else if(escolha == 6) {
                menu.alterarDados();
                int aux = input.nextInt();
                input.nextLine();

                menu.id();
                int idChange = input.nextInt();
                input.nextLine();

                Funcoes.alterarDados(aux, idChange, idSindicato, diasMes);
                idSindicato++;
            }
            else if(escolha == 7) {
                Funcoes.Lista_de_empregados();
            }
            else if(escolha == 8) {
                Funcoes.rodarPagamento(dia, ultimoDiaUtil);
            }
            else if(escolha == 99) {
                break;
            }
            else {
                menu.alert_escolha_invalida();
            }
        }
    }
}
