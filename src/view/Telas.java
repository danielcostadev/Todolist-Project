package view;

import static controller.TarefaController.receberOpcaoPrioridade;

public class Telas {

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

        receberOpcaoPrioridade();

    }



}
