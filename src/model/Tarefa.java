package model;

import java.time.LocalDateTime;

public class Tarefa {

    // Definição de atributos
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataTermino;
    private int prioridade;
    private String categoria;
    private String status = "TODO";


    // Construtor da classe Tarefa Para CRIAÇÃO
    public Tarefa(Long id, String nome, String descricao, LocalDateTime dataTermino, int prioridade, String categoria) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.prioridade = prioridade;
        this.categoria = categoria;
    }

    // Construtor da classe Tarefa Para EDIÇÃO
    public Tarefa(String nome, String descricao,LocalDateTime dataTermino, int prioridade, String categoria, String status) {

        this.nome = nome;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = status;
    }

    // Construtor da classe Tarefa completo
    public Tarefa(Long id, String nome, String descricao,LocalDateTime dataTermino, int prioridade, String categoria, String status) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = status;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        if(id != null){
            this.id = id;
        } else {
            System.out.println("Esse campo não pode ficar em branco!");
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDateTime dataTermino) {

        this.dataTermino = dataTermino;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        if(prioridade >= 1 && prioridade <= 5) {
            this.prioridade = prioridade;
        } else {
            System.out.println("Nível de prioridade inválido!");
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String dataTerminoFormatada = (dataTermino != null && !dataTermino.equals(LocalDate.of(1, 1, 1)))
//                ? dataTermino.format(formatter)
//                : "NÃO FINALIZADO";

        return String.format("%d, %s, %s, %s, %d, %s, %s", id, nome, descricao, dataTermino, prioridade, categoria, status);
    }
}

