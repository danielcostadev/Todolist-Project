package controller;

import model.Tarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.TarefaDAO.deletarTarefaAcao;
import static model.TarefaDAO.lerTarefaAcao;

public class TarefaController {
    // Entrada de dados para o usuário preencher os dados da tarefa
    public static Tarefa criarTarefaController() {

        try (Scanner scanner = new Scanner(System.in)) {
            // Coleta e validação dos dados da tarefa
            System.out.println(" ");
            System.out.print("Digite o ID da tarefa: ");
            Long id = null;
            while (id == null) {
                try {
                    id = Long.parseLong(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.print("ID inválido! Digite novamente: ");
                }
            }

            System.out.print("Digite o nome da tarefa: ");
            String nome = scanner.nextLine();
            while (nome == null || nome.trim().isEmpty()) {
                System.out.print("Nome inválido! Digite novamente: ");
                nome = scanner.nextLine();
            }

            System.out.print("Digite a descrição da tarefa: ");
            String descricao = scanner.nextLine();
            while (descricao == null || descricao.trim().isEmpty()) {
                System.out.print("Descrição inválida! Digite novamente: ");
                descricao = scanner.nextLine();
            }

            System.out.print("Escolha a prioridade da tarefa entre [1] e [5]: ");
            int prioridade = 0;
            while (prioridade < 1 || prioridade > 5) {
                try {
                    prioridade = Integer.parseInt(scanner.nextLine());
                    if (prioridade < 1 || prioridade > 5) {
                        System.out.print("Prioridade inválida! Digite novamente entre [1] e [5]: ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Prioridade inválida! Digite novamente entre [1] e [5]: ");
                }
            }

            System.out.print("Escolha a categoria da tarefa: ");
            String categoria = scanner.nextLine();
            while (categoria == null || categoria.trim().isEmpty()) {
                System.out.print("Categoria inválida! Digite novamente: ");
                categoria = scanner.nextLine();
            }

            return new Tarefa(id, nome, descricao, prioridade, categoria);
        }
    }
    // Cabecalho da lista de tarefas
    public static void cabecalhoTarefaController(){
        System.out.println(" ");
        System.out.println("--------------------------------------------------------------");
        System.out.println("ID | NOME | DESCRICAO | DATA DE TERMINO | CATEGORIA | STATUS |");
        System.out.println("--------------------------------------------------------------");
    }

    // Entrada de dados para escolher id da tarefa a ser deletada
    public static String deletarTarefaController(){
        mostrarTarefaController();
        System.out.println(" ");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Digite o ID da tarefa que deseja excluir: ");
        String idParaDeletar;
        try (Scanner scanner = new Scanner(System.in)) {
            idParaDeletar = scanner.nextLine();
            while (idParaDeletar == null || idParaDeletar.trim().isEmpty()) {
                System.out.print("ID inválido! Digite novamente: ");
                idParaDeletar = scanner.nextLine();
            }
        }
        return idParaDeletar;
    }

    // Método Adicionar Tarefa Controller
    public static Tarefa adicionaTarefaController() {
        List<Tarefa> listaDeTarefas = new ArrayList<>();
        try {
            // Cria uma instância de Tarefa dos dados da tarefa
            Tarefa tarefa = criarTarefaController(); // Método que cria a tarefa
            listaDeTarefas.add(tarefa);
            System.out.println("Tarefa adicionada com sucesso.");
            return tarefa; // Retorna apenas a tarefa criada recentemente
        } catch (Exception e) {
            System.out.println("Entrada inválida!");
            return null;
        }
    }
    // Método Mostrar Tarefa Controller
    public static void mostrarTarefaController() {
        cabecalhoTarefaController();
        lerTarefaAcao();
    }

    // Método para excluir tarefa
    public static void exluirTarefaController() {
        try{
            deletarTarefaAcao(deletarTarefaController());
        } catch (Exception e){
            System.out.println("Não foi possível deletar tarefa!");
        }
    }

}
