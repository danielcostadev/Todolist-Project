package view;

import model.Tarefa;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static model.TarefaDAO.criarTarefa;
import static model.TarefaDAO.listarTarefas;


public class ToDoList {
    public static void main(String[] args) {

        criarTarefa();
        listarTarefas();


//        List<Tarefa> todoList = new ArrayList<>();
//        Tarefa tarefa1 = new Tarefa("Fazer Tarefa", "Descrição Tarefa", LocalDate.of(0000,00,00),5,"Teste Categoria", Tarefa.Status.TODO);
//        todoList.add(tarefa1);
    }
}
