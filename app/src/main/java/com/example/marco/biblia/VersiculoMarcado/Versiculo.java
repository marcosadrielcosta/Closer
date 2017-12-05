package com.example.marco.biblia.VersiculoMarcado;

public class Versiculo {
    private int id;
    private String versiculo;
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersiculo() {
        return versiculo;
    }

    public void setVersiculo(String versiculo) {
        this.versiculo = versiculo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Versiculo() {
    }

    public Versiculo(String versiculo, String data) {
        super();
        this.versiculo = versiculo;
        this.data = data;
    }

    @Override
    public String toString() {
        return "\n"+versiculo +"\n \nMarcado em: "+data+"\n";
    }
}
