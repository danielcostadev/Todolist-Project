package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import controller.TarefaController;

public class TarefaDAO {

    // Seta caminho do arquivo para persistencia
    private static String caminho = "tarefas.csv";

    // CREATE
    private static void criarTarefaDAO() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true))) {

            Tarefa novaTarefa = TarefaController.adicionaTarefaController();
            if (novaTarefa != null) {
                bw.write(novaTarefa.toString() + "\n");
            } else {
                System.out.println("Não foi possível adicionar a tarefa.");
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado!");
        }
    } // FIM DO CREATE

    // READ
    private static void LerTarefaDAO() {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.printf("Arquivo não encontrado!");
        }
        for (String linha : linhas) {
            System.out.println(linha);
        }
    } // FIM DO READ

    public static void criarTarefaAcao() {
        criarTarefaDAO();
    }

    public static void lerTarefaAcao() {
        LerTarefaDAO();
    }

}

