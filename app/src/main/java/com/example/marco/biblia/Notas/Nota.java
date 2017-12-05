package com.example.marco.biblia.Notas;


public class Nota {
    private int id;
    private String titulo;
    private String versiculo;
    private String corpo;
    private String data;
    private Nova nova;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVersiculo() {
        return versiculo;
    }

    public void setVersiculo(String versiculo) {
        this.versiculo = versiculo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Nota(){}

    public Nota(String titulo, String versiculo, String corpo, String data) {
        super();
        this.titulo = titulo;
        this.versiculo = versiculo;
        this.corpo = corpo;
        this.data = data;
    }

    @Override
    public String toString() {
        return "\n"+titulo.toUpperCase()+"\nCriada em: "+data+"\n";
    }
}

