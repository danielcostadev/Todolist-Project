package view;

import java.util.Scanner;

import static controller.TarefaController.mostrarTarefaController;
import static model.TarefaDAO.criarTarefaAcao;

public class Manipulacao {

    public Boolean selecionarOpcao() {

        new Menu();

        //O scanner é tratado dentro do bloco try usando try-with-resources
        try(Scanner scanner = new Scanner(System.in)) {
            // Variável para armazenar escolha do usuário.
            String opcao = scanner.nextLine();
            switch (opcao){
                case "1":
                    criarTarefaAcao();
                    break;
                case "2":
                    mostrarTarefaController();
                    break;
                case "3":
                    criarTarefaAcao();
                    break;
                case "4":
                    criarTarefaAcao();
                    break;
                default:
                    System.out.printf("T");
                    break;
            }



        }
        return true;
    }
}
