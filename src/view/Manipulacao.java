package view;

import java.util.Scanner;

import static model.TarefaDAO.criarTarefa;

public class Manipulacao {

    public Boolean selecionarOpcao() {

        new Menu();

        //O scanner é tratado dentro do bloco try usando try-with-resources
        try(Scanner scanner = new Scanner(System.in)) {
            // Variável para armazenar escolha do usuário.
            String opcao = scanner.nextLine();
            switch (opcao){
                case "1":
                    criarTarefa();
                    break;
                case "2":
                    criarTarefa();
                    break;
                case "3":
                    criarTarefa();
                    break;
                case "4":
                    criarTarefa();
                    break;
                default:
                    System.out.printf("T");
                    break;
            }



        }
        return true;
    }
}
