package model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import controller.TarefaController;

public class TarefaDAO {

    // Seta caminho do arquivo para persistencia
    private static String caminho = "tarefas.csv";

    // CREATE
    private static void criarTarefaDAO() {
        // Ler todas as tarefas existentes do arquivo
        List<Tarefa> tarefas = OrdenarTarefaDAO();

        // Adicionar a nova tarefa
        Tarefa novaTarefa = TarefaController.adicionaTarefaController();
        if (novaTarefa != null) {
            tarefas.add(novaTarefa);

            // Ordenar a lista de tarefas pela prioridade
            tarefas.sort(Comparator.comparingInt(Tarefa::getPrioridade));

            // Reescrever o arquivo com a lista ordenada
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
                for (Tarefa tarefa : tarefas) {
                    bw.write(tarefa.toString());
                    bw.newLine();
                }
                System.out.println("Tarefa adicionada e lista ordenada com sucesso.");
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo!");
            }
        } else {
            System.out.println("Não foi possível adicionar a tarefa.");
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
            boolean encontrou = false;
            while ((linha = br.readLine()) != null) {
                int end = linha.indexOf(",");

                String id = linha.substring(0, end);

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
            System.out.print("Arquivo não encontrado!");
        }
    } // FIM DO DELETE

    // EDITAR
    private static void editarTarefaDAO(String idParaEditar) {
        List<Tarefa> tarefas = OrdenarTarefaDAO();
        boolean encontrou = false;

        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa tarefa = tarefas.get(i);
            if (tarefa.getId().toString().equals(idParaEditar)) {
                encontrou = true;

                System.out.println("\nValores atuais da tarefa:");
                System.out.println("Nome: " + tarefa.getNome());
                System.out.println("Descrição: " + tarefa.getDescricao());
                System.out.println("Data de Término: " + (tarefa.getDataTermino().equals(LocalDate.of(1, 1, 1)) ? "NÃO FINALIZADO" : tarefa.getDataTermino()));
                System.out.println("Prioridade: " + tarefa.getPrioridade());
                System.out.println("Categoria: " + tarefa.getCategoria());
                System.out.println("Status: " + tarefa.getStatus()+"\n");

//                // Coleta dados de edição do Controller
//                Tarefa tarefaAtualizada = TarefaController.formularioEdicaoTarefaController();
//                tarefa.setNome(tarefaAtualizada.getNome());
//                tarefa.setDescricao(tarefaAtualizada.getDescricao());
//                tarefa.setDataTermino(tarefaAtualizada.getDataTermino());
//                tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
//                tarefa.setCategoria(tarefaAtualizada.getCategoria());
//                tarefa.setStatus(tarefaAtualizada.getStatus());

                // Coleta dados de edição do Controller
                Tarefa tarefaAtualizada = TarefaController.formularioEdicaoTarefaController();

                if (tarefaAtualizada.getNome() != null && !tarefaAtualizada.getNome().isEmpty()) {
                    tarefa.setNome(tarefaAtualizada.getNome());
                }
                if (tarefaAtualizada.getDescricao() != null && !tarefaAtualizada.getDescricao().isEmpty()) {
                    tarefa.setDescricao(tarefaAtualizada.getDescricao());
                }
                if (tarefaAtualizada.getDataTermino() != null) {
                    tarefa.setDataTermino(tarefaAtualizada.getDataTermino());
                }
                if (tarefaAtualizada.getPrioridade() > 0) {
                    tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
                }
                if (tarefaAtualizada.getCategoria() != null && !tarefaAtualizada.getCategoria().isEmpty()) {
                    tarefa.setCategoria(tarefaAtualizada.getCategoria());
                }
                if (tarefaAtualizada.getStatus() != null && !tarefaAtualizada.getStatus().isEmpty()) {
                    tarefa.setStatus(tarefaAtualizada.getStatus());
                }

                tarefas.set(i, tarefa);

            }
        }

        if (encontrou) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
                for (Tarefa tarefa : tarefas) {
                    bw.write(tarefa.toString());
                    bw.newLine();
                }
                System.out.println("Tarefa editada com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Não foi possível editar a Tarefa! Tarefa não encontrada!");
        }
    } // FIM DO EDITAR

    // ORDER
    private static List<Tarefa> OrdenarTarefaDAO() {
        String linha;
        List<Tarefa> tarefas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            while ((linha = br.readLine()) != null) {
                // Separa as informações do arquivo tendo como base a virgula e os espaços em branco, linha por linha.
                String[] parts = linha.split(",\\s*");

                // Converte e cria o objeto Tarefa
                if (parts.length >= 7) {
                    Long id = Long.parseLong(parts[0]);
                    String nome = parts[1];
                    String descricao = parts[2];
                    LocalDate dataTermino = LocalDate.parse(parts[3]);
                    int prioridade = Integer.parseInt(parts[4]);
                    String categoria = parts[5];
                    String status = parts[6];

                    // Criar e adicionar tafera a lista
                    Tarefa tarefa = new Tarefa(id, nome, descricao, dataTermino, prioridade, categoria, status);
                    tarefas.add(tarefa);
                }

            }
        } catch (IOException e) {
            System.out.printf("Arquivo não encontrado!");
        }
        return tarefas;
    } // FIM DO ORDER

    public static void criarTarefaAcao() {
        criarTarefaDAO();
    }

    // Para garantir a ordenação optei por exibir a tarefa utilizando uma lista
    public static void lerTarefaAcao() {
        LerTarefaDAO();
    }

    public static void deletarTarefaAcao(String idParaDeletar) {
        deletarTarefaDAO(idParaDeletar);
    }

    public static void editarTarefaAcao(String idParaEditar) {
        editarTarefaDAO(idParaEditar);
    }
    public static List<Tarefa> ordenarTarefaAcao() {
        return OrdenarTarefaDAO();
    }

}

