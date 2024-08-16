package model;

import java.time.LocalDate;

public class Tarefa {

    // Definição de atributos
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataTermino = LocalDate.of(0000,00,00);
    private int prioridade;
    private String categoria;
    private Status status = Status.TODO;

    public enum Status {
        TODO, DOING, DONE;
    }

    // Construtor da classe Tarefa
    public Tarefa(Long id, String nome, String descricao, int prioridade, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.categoria = categoria;
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}