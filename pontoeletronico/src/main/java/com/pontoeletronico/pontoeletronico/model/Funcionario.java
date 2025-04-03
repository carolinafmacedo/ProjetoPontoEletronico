package com.pontoeletronico.pontoeletronico.model;

public class Funcionario {

    private Integer id;
    private String nome;
    private String cargo;
    private String setor;

    public Funcionario(Integer id, String nome, String cargo, String setor) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.setor = setor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}

