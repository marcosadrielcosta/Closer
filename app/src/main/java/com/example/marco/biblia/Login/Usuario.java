package com.example.marco.biblia.Login;


public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String data) {
        super();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.data = data;
    }

    @Override
    public String toString() {
        return "id = "+id+"\nnome = "+nome+"\nemail = "+email+"\nsenha = "+senha+"\ndata = "+data;
    }
}
