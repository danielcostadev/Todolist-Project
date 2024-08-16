package model;

import java.io.*;
import java.util.Scanner;
import controller.TarefaController;

public class TarefaDAO {

    // Seta caminho do arquivo para persistencia
    private static String caminho = "tarefas.csv";

    // CREATE
    private static void createDAO() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true))) {

            Tarefa novaTarefa = TarefaController.adicionaTarefa();
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
    private static void readDAO() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.printf("Arquivo não encontrado!");
        }
    } // FIM DO READ

    public static void criarTarefa() {
        createDAO();
    }

    public static void listarTarefas(){
        readDAO();
    }

}

