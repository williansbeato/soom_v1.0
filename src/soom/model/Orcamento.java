package soom.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class  Orcamento {

    private int id;
    private Cliente cliente;
    private ObservableList<Servico> servicos;
    private double valor;
    private LocalDateTime data;


    public Orcamento(){

        servicos = FXCollections.observableArrayList();
    }

    public void adicionaServico(Servico s){
        servicos.add(s);

        valor+=s.getValor();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ObservableList<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(ObservableList<Servico> servicos) {
        this.servicos = servicos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String toString(){
        return cliente.getNome()+"(RS"+valor+")";
    }
}
