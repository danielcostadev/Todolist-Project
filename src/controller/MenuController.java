package controller;

import utilitarios.TarefaComparator;
import view.Menu;
import java.util.Scanner;
import static controller.TabelaConsoleController.tabelaConsole;
import static controller.TarefaController.*;
import static model.TarefaDAO.criarTarefaAcao;

public class MenuController {
    private static boolean continuar = true;
    // Declarando o scanner como static para ser acessível em toda a classe
    private final static Scanner scanner = new Scanner(System.in);

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

    // Recebe a opção selecionada no Menu
    public static void receberOpcaoSelecionadaMenu() {
        String opcaoSelecionada = validarString("DIGITE A OPÇÃO DESEJADA: ");
        switch (opcaoSelecionada) {
            case "1":
                criarTarefaAcao();
                break;
            case "2":
                tabelaConsole(TarefaComparator.porPrioridade());
//                mostrarTarefaController();
                break;
            case "3":
                excluirTarefaController();
                break;
            case "4":
                editarTarefaController();
                break;
            case "5":
                quantidadeStatus();
                break;
            case "6":
                System.out.println("Encerrando aplicação...");
                fecharScannerMenu();
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
            receberOpcaoSelecionadaMenu();
        }

    }

    // Método para fechar o scanner
    public static void fecharScannerMenu() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
