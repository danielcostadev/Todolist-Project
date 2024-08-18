package controller;

import model.Tarefa;
import model.TarefaDAO;
import utilitarios.TarefaComparator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.TarefaDAO.ordenarTarefaAcao;

public class TarefaController {

    // Declarando o scanner como static o mesmo ficará aberto e disponível para todos os métodos que precisarem
    private final static Scanner scanner = new Scanner(System.in);

    // Entrada de dados para o usuário preencher os dados na criação da tarefa
    public static Tarefa formularioCadastroTarefaController() {

        // Melhoria na entrada e no tratamento de dados
        Long id = validarLong("\nDigite o ID: ");
        String nome = validarString("Digite o nome: ");
        String descricao = validarString("Digite a descrição: ");
        int checarCategoria = validarInt("Defina a categoria 1-Trabalho | 2-Estudos | 3-Outros: ",1,3);
        int prioridade = validarInt("Defina a prioridade entre 1 e 5: ", 1, 5);

        String categoria = "Outros";
        switch (checarCategoria) {
            case 1: categoria = "Trabalho"; break;
            case 2: categoria = "Estudos"; break;
            case 3: categoria = "Outros"; break;
        }

        //Retorna um novo objeto
        return new Tarefa(id, nome, descricao, prioridade, categoria);
    }

    // Entrada de dados para o usuário preencher os dados na EDIÇÃO da tarefa
    public static Tarefa formularioEdicaoTarefaController() {

        // Melhoria na entrada e no tratamento de dados
        String nome = validarString("Digite o nome da tarefa: ");
        String descricao = validarString("Digite a descrição da tarefa: ");
        LocalDate dataTermino;
        int prioridade = validarInt("Defina a prioridade entre 1 e 5: ", 1, 5);
        int checarCategoria = validarInt("Defina a categoria 1-Trabalho | 2-Estudos | 3-Outros: ",1,3);

        String categoria = "Outros";
        switch (checarCategoria) {
            case 1: categoria = "Trabalho"; break;
            case 2: categoria = "Estudos"; break;
            case 3: categoria = "Outros"; break;
        }

        int checarStatus = validarInt("Defina o status 1-TODO | 2-DOING | 3-DONE: ",1,3);

        String status = "TODO";
        switch (checarStatus) {
            case 1: status = "TODO"; break;
            case 2: status = "DOING"; break;
            case 3: status = "DONE"; break;
        }
        // Verifica se a tarefa já foi finalizada e atribui uma data a dataTermino caso seja verdadeiro
        if (status.equals("DONE")){
            dataTermino = LocalDate.now();
        } else {
            dataTermino = LocalDate.of(0001,01,01);
        }

        //Retorna um novo objeto
        return new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status);
    }



    // MÉTODOS PARA VALIDAÇÃO DA ENTRADA DE DADOS
    // Definição do método para validar um valor passado como Long
    private static Long validarLong(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nID inválido! Digite um número válido.");
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
            System.out.println("\nEntrada inválida! Digite novamente.");
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
                System.out.println("\nValor fora do intervalo! Digite um número entre " + min + " e " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("\nNúmero inválido! Digite um número válido.");
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

        List<Tarefa> listaDeTarefas = ordenarTarefaAcao(); // Obter a lista de tarfas
        // Reutilização da melhoria na entrada de dados
        int ordem = validarInt("\nDefina a ordenação entre 1 e 3: ", 1, 3);

        switch (ordem) {
            case 1:
                listaDeTarefas.sort(TarefaComparator.porPrioridade());
                break;
            case 2:
                listaDeTarefas.sort(TarefaComparator.porCategoria());
                break;
            case 3:
                listaDeTarefas.sort(TarefaComparator.porStatus());
        }

        for (Tarefa tarefa : listaDeTarefas) {
            System.out.println(tarefa);
        }

    }

    // Entrada de dados para escolher id da tarefa a ser deletada
    public static String deletarTarefaController() {
        cabecalhoTarefaController();
        List<Tarefa> listaDeTarefas = ordenarTarefaAcao(); // Obter a lista de tarefas
        // Ordenda exibição de lista por ID
        listaDeTarefas.sort(TarefaComparator.porId());
        // Mostra a lista de tarefas
        for (Tarefa tarefa : listaDeTarefas) {
            System.out.println(tarefa);
        }
        return validarString("\nDigite o ID da tarefa que deseja deletar: ");
    }

    // Entrada de dados para escolher id da tarefa a ser editada
    public static String atualizarTarefaController() {
        cabecalhoTarefaController();
        List<Tarefa> listaDeTarefas = ordenarTarefaAcao(); // Obter a lista de tarefas
        // Ordenda exibição de lista por ID
        listaDeTarefas.sort(TarefaComparator.porId());
        // Mostra a lista de tarefas
        for (Tarefa tarefa : listaDeTarefas) {
            System.out.println(tarefa);
        }
        return validarString("\nDigite o ID da tarefa que deseja editar: ");
    }

    // Método Adicionar Tarefa Controller se comunica com o TaferaDAO
    public static Tarefa adicionaTarefaController() {
        List<Tarefa> listaDeTarefas = new ArrayList<>();
        try {
            // Cria uma instância de Tarefa dos dados da tarefa
            Tarefa tarefa = formularioCadastroTarefaController(); // Método que cria a tarefa e recebe as informações do formulário
            listaDeTarefas.add(tarefa);
            return tarefa; // Retorna apenas aa tarefa criada recentemente
        } catch (Exception e) {
            System.out.println("\nEntrada inválida!");
            return null;
        }
    }

    // Método Mostrar Tarefa Controller
    public static void mostrarTarefaController() {
        cabecalhoTarefaController();
        List<Tarefa> listaDeTarefas = ordenarTarefaAcao(); // Obter a lista de tarefas
        for (Tarefa tarefa : listaDeTarefas) {
            System.out.println(tarefa);
        }
        rodapeTarefaController();
    }

    // Método para excluir tarefa
    public static void excluirTarefaController() {
        try {
            TarefaDAO.deletarTarefaAcao(deletarTarefaController()); // Responsável por deletar as tarefas
        } catch (Exception e) {
            System.out.println("\nNão foi possível deletar tarefa!");
        }
    }

    // Método para editar tarefa
    public static void editarTarefaController() {
        try {
            TarefaDAO.editarTarefaAcao(atualizarTarefaController()); // Responsável por editar as tarefas
        } catch (Exception e) {
            System.out.println("\nNão foi possível editar tarefa!");
        }
    }

}