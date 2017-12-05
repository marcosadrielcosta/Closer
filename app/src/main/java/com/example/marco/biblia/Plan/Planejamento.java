package com.example.marco.biblia.Plan;

public class Planejamento {
    private int id;
    private String data;
    private String ref;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Planejamento() {
    }
    public Planejamento(String data, String ref) {
        super();
        this.data = data;
        this.ref = ref;
    }


    @Override
    public String toString() {
        return data + "\nLeitura: " + ref;
    }
}
