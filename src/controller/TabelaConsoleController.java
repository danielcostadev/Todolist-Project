package controller;

import model.Tarefa;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static model.TarefaDAO.ordenarTarefaAcao;

public class TabelaConsoleController {
    public static void tabelaConsole(Comparator<Tarefa> comparator) {

        // Obter a lista de tarefas Principal, dessa lista são exibidas todas telas
        List<Tarefa> tarefas = ordenarTarefaAcao();
        // Ordena a lista dinamicamente de acordo com o valor passado como argumento no método tabelaConsole
        tarefas.sort(comparator);

        // Cabeçalho da tabela
        String[] cabecalho = {"ID", "NOME", "DESCRIÇÃO", "DATA DE TERMINO", "PRIORIDADE", "CATEGORIA", "STATUS"};

        // Preparando os dados através da lista
        List<String[]> dados = new ArrayList<>();
        for (Tarefa tarefa : tarefas) {
            dados.add(new String[]{
                    tarefa.getId().toString(),
                    tarefa.getNome(),
                    tarefa.getDescricao(),
                    tarefa.getDataTermino().toString(),
                    Integer.toString(tarefa.getPrioridade()),
                    tarefa.getCategoria(),
                    tarefa.getStatus()
            });
        }

        // Calculando a largura das colunas com base no conteúdo
        int[] columnWidths = new int[cabecalho.length];
        for (int i = 0; i < cabecalho.length; i++) {
            columnWidths[i] = cabecalho[i].length();
            for (String[] row : dados) {
                if (row[i].length() > columnWidths[i]) {
                    columnWidths[i] = row[i].length();
                }
            }
        }

        // Imprimir cabeçalho
        printLine(columnWidths);
        printRow(cabecalho, columnWidths);
        printLine(columnWidths);

        // Imprimir dados das tarefas
        for (String[] row : dados) {
            printRow(row, columnWidths);
        }
        printLine(columnWidths);
    }

    // Método auxiliar para imprimir linhas horizontais
    private static void printLine(int[] columnWidths) {
        System.out.print("+");
        for (int columnWidth : columnWidths) {
            for (int j = 0; j < columnWidth + 2; j++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }

    // Método auxiliar para imprimir uma linha da tabela
    private static void printRow(String[] row, int[] columnWidths) {
        System.out.print("|");
        for (int i = 0; i < row.length; i++) {
            System.out.printf(" %-" + columnWidths[i] + "s |", row[i]);
        }
        System.out.println();
    }
}
