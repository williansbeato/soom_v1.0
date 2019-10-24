package soom.model;

public class Peca {

    private int id;
    private String categoria;
    private String nome;

    public Peca (){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String toString(){ return this.nome; }
}
