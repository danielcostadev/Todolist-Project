package utilitarios;

import model.Tarefa;

import java.util.Comparator;

public class TarefaComparator {

    // Comparador para ordenar por ID
    public static Comparator<Tarefa> porId() {
        return Comparator.comparingLong(Tarefa::getId);
    }

    // Comparador para ordenar por Categoria
    public static Comparator<Tarefa> porCategoria() {
        return Comparator.comparing(Tarefa::getCategoria);
    }

    // Comparador para ordenar por prioridade
    public static Comparator<Tarefa> porPrioridade() {
        return Comparator.comparingInt(Tarefa::getPrioridade);
    }

    // Comparador para ordenar por Status
    public static Comparator<Tarefa> porStatus() {
        return Comparator.comparing(Tarefa::getStatus);
    }
}
