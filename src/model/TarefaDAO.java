package model;

import java.io.*;
import java.util.Scanner;

public class TarefaDAO {

    // Seta caminho do arquivo para persistencia
    private static String caminho = "tarefas.txt";

    // CREATE
    private static void createDAO() {
        File file = new File(caminho);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true))) {
            String linha = "";



//            try (Scanner scanner = new Scanner(System.in)) {
//                linha = scanner.nextLine();
//                bw.append(linha + "\r\n");
//            }
        } catch (IOException e) {
            System.out.printf("Arquivo não encontrado!");
        }
    } // FIM DO CREATE

    // READ
    private static void readDAO() {
        File file = new File(caminho);
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

