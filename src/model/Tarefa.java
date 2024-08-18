package model;

import java.time.LocalDate;

public class Tarefa {

    // Definição de atributos
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataTermino = LocalDate.of(0001,01,01);
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

    // Construtor da classe Tarefa completo
    public Tarefa(Long id, String nome, String descricao,LocalDate dataTermino, int prioridade, String categoria, Status status) {

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

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %d, %s, %s", id, nome, descricao, dataTermino, prioridade, categoria, status);
    }
}

