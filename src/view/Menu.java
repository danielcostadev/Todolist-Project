package view;

import java.util.Scanner;

import static controller.TarefaController.excluirTarefaController;
import static controller.TarefaController.mostrarTarefaController;
import static model.TarefaDAO.criarTarefaAcao;

public class Menu {

    private static boolean continuar = true;

    // Construtor da classe para exibir o menu
    public Menu() {
        exibirMenu();
    }

    // Declarando o scanner como static para ser acessível em toda a classe
    private final static Scanner scanner = new Scanner(System.in);

    // Método para exibir o menu
    private void exibirMenu() {
        System.out.println("----------------------");
        System.out.println("   TODO LIST v1.1.0   ");
        System.out.println("  Autor Daniel Costa  ");
        System.out.println("----------------------");
        System.out.println("---------MENU---------");
        System.out.println("----------------------");
        System.out.println("1 - CADASTRAR TAREFA");
        System.out.println("2 - LISTAR TAREFAS");
        System.out.println("3 - EXCLUIR TAREFA");
        System.out.println("4 - EDITAR TAREFA");
        System.out.println("5 - ENCERRAR APLICAÇÃO");
    }

    // Método para validar uma entrada de String
    private static String validarString(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            if (entrada != null && !entrada.trim().isEmpty()) {
                return entrada;
            }
            System.out.println("Entrada inválida! Digite novamente.");
        }
    }

    // Método para verificar a opção selecionada
    private static void verificarOpcao() {
        String opcaoSelecionada = validarString("DIGITE A OPÇÃO DESEJADA: ");
        switch (opcaoSelecionada) {
            case "1":
                criarTarefaAcao();
                break;
            case "2":
                mostrarTarefaController();
                break;
            case "3":
                excluirTarefaController();
                break;
            case "4":
                //editarTarefaController();
                break;
            case "5":
                System.out.println("Encerrando aplicação...");
                fecharScanner();
                System.exit(0);
                continuar = false;
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;
        }
    }

    // Método para selecionar a opção no menu
    public static void selecionarOpcao() {
        while(continuar) {
            new Menu();
            verificarOpcao();
        }

    }

    // Método para fechar o scanner
    private static void fecharScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
