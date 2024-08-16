package controller;

import model.Tarefa;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TarefaController {

    private List<Tarefa> listaTarefa = new ArrayList<Tarefa>();
    private String categoria;

    private enum status {}

    ;

    public void adicionaTarefa() {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite o ID da tarefa: "); // Quando iniciar com banco de dados esse id será autoicrementado
            Long id = scanner.nextLong();
            System.out.print("Digite o nome do tarefa: ");
            String nome = scanner.nextLine();
            System.out.print("Digite a descrição da tarefa: ");
            String descricao = scanner.nextLine();
            System.out.print("Escolha a prioridade da tarefa entre [1] e [5]: ");
            int prioridade = scanner.nextInt();
            System.out.print("Escolha a categoria da tarefa entre [1]-Estudos [2]-Trabalho: ");
            int categoriaTemp = scanner.nextInt();
            System.out.print("Defina o STATUS da tarefa [1]-TODO [2]-DOING [3]-DONE");
            int statusInt = scanner.nextInt();

            switch (categoriaTemp) {
                case 1:
                    categoria = "Estudos";
                    break;
                case 2:
                    categoria = "Trabalho";
                    break;
            }

            Tarefa tarefa = new Tarefa(id, nome, descricao, prioridade, categoria);


        } catch (Exception e) {
            System.out.println("Entrada inválida!");
            adicionaTarefa();
        }


    }


}
