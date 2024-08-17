package controller;

import model.Tarefa;
import model.TarefaDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TarefaController {

    // Declarando o scanner como static o mesmo ficará aberto e disponível para todos os métodos que precisarem
    private final static Scanner scanner = new Scanner(System.in);

    // Entrada de dados para o usuário preencher os dados da tarefa
    public static Tarefa criarTarefaController() {

        // Melhoria na entrada e no tratamento de dados
        Long id = validarLong("Digite o ID da tarefa: ");
        String nome = validarString("Digite o nome da tarefa: ");
        String descricao = validarString("Digite a descrição da tarefa: ");
        String categoria = validarString("Digite a categoria da tarefa: ");
        int prioridade = validarInt("Defina a prioridade entre 1 e 5: ", 1, 5);

        //Retorna um novo objeto
        return new Tarefa(id, nome, descricao, prioridade, categoria);
    }

    // MÉTODOS PARA VALIDAÇÃO DA ENTRADA DE DADOS
    // Definição do método para validar um valor passado como Long
    private static Long validarLong(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID inválido! Digite um número válido.");
            }
        }
    }

    // Definição do método para validar um valor passado como String
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

    // Definição do método para validar um valor passado como Int
    private static int validarInt(String mensagem, int min, int max) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("Valor fora do intervalo! Digite um número entre " + min + " e " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Número inválido! Digite um número válido.");
            }

        }
    }

    // EXIBIÇÕES
    // Cabecalho da lista de tarefas
    public static void cabecalhoTarefaController() {

        System.out.println("\n-------------------------------------------------------------");
        System.out.println("ID | NOME | DESCRICAO | DATA DE TERMINO | CATEGORIA | STATUS |");
        System.out.println("--------------------------------------------------------------");
    }

    // Rodapé da lista de tarefas com opção de ordenação
    public static void rodapeTarefaController() {
        System.out.println("\n-------------------------ORDENAR POR-------------------------");
        System.out.println("1 - Prioridade   |     2 - Categoria       |        3 - Status");
        System.out.println("--------------------------------------------------------------");
        // Reutilização da melhoria na entrada de dados
        int ordem = validarInt("Defina a ordenação entre 1 e 3: ", 1, 5);

    }

    // Entrada de dados para escolher id da tarefa a ser deletada
    public static String deletarTarefaController() {
        System.out.println("\n------------------------------------------------------------");
        mostrarTarefaController();
        return validarString("Digite o ID da tarefa que deseja deletar: ");
    }

    // Método Adicionar Tarefa Controller se comunica com o TaferaDAO
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
        TarefaDAO.lerTarefaAcao(); // Responsável por mostrar as tarefas
        rodapeTarefaController();
    }

    // Método para excluir tarefa
    public static void exluirTarefaController() {
        try {
            TarefaDAO.deletarTarefaAcao(deletarTarefaController()); // Responsável por deletar as tarefas
        } catch (Exception e) {
            System.out.println("Não foi possível deletar tarefa!");
        }
    }
}