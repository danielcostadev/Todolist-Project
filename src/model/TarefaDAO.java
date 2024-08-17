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

    // DELETE
    private static synchronized void deletarTarefaDAO(String idParaDeletar) {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            Boolean encontrou = false;
            while ((linha = br.readLine()) != null) {
                int start = linha.indexOf("#");
                int end = linha.indexOf(",");

                String id = linha.substring(start + 1, end);

                if (!id.equals(idParaDeletar)) {
                    conteudo.append(linha).append("\n");
                } else {
                    encontrou = true;
                }
            }
            if (encontrou) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
                    bw.write(conteudo.toString());
                    System.out.println("Tarefa deletada com sucesso!");
                } catch (IOException e) {
                    System.out.println("Arquivo não encontrado!");
                }
            } else {
                System.out.println("Não foi possívivel deletar Tarefa! Tarefa não encontrado!");
            }

        } catch (IOException e) {
            System.out.printf("Arquivo não encontrado!");
        }
    } // FIM DO DELETE

    public static void criarTarefaAcao() {
        criarTarefaDAO();
    }

    public static void lerTarefaAcao() {
        LerTarefaDAO();
    }

    public static void deletarTarefaAcao(String idParaDeletar) {
        deletarTarefaDAO(idParaDeletar);
    }

}

