package soom.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Oficina {

    private static Oficina instance = new Oficina();

    private ClienteDAO clienteDAO;
    private ObservableList<Cliente> clientes;


    private ServicoDAO servicoDAO;
    private ObservableList<Servico> servicos;

    private OrcamentoDAO orcamentoDAO;
    private ObservableList<Orcamento> orcamentos;

    private Orcamento orcamentoAtual = null;
   // private ObservableList<Servico> servicosOrcamentoAtual;


    private Oficina(){
        clientes = FXCollections.observableArrayList();
        clienteDAO = new ClienteDAOImpl();


        servicos = FXCollections.observableArrayList();
        servicoDAO = (ServicoDAO) new ServicoDAOImpl();

        orcamentos = FXCollections.observableArrayList();
        orcamentoDAO = new OrcamentoDAOImpl();

       // servicosOrcamentoAtual = FXCollections.observableArrayList();
    }

    public static Oficina getInstance(){

        return instance;
    }

    public Cliente insereCliente(String nome, String telefone) throws SQLException {

        Cliente c = new Cliente();

        c.setNome(nome);
        c.setTelefone(telefone);

        clienteDAO.insere(c);

        clientes.add(c);

        return c;

    }

    public  ObservableList listaClientes() throws SQLException {


        clientes.clear();
        clientes.addAll(clienteDAO.lista());

        return clientes;
    }

    public void insereServico(String categoria, String nome, Double valor) throws SQLException {
        Servico s = new Servico();
        s.setCategoria(categoria);
        s.setNome(nome);
        s.setValor(valor);

        servicoDAO.insere(s);
        servicos.add(s);
    }

    public ObservableList<Servico> listaServicos() throws SQLException {
        servicos.clear();
        servicos.addAll(servicoDAO.lista());

        return servicos;
    }


    public ObservableList<Orcamento> listaOrcamentos() throws SQLException{

        orcamentos.clear();
        orcamentos.setAll(orcamentoDAO.lista());

        return orcamentos;
    }


    public ObservableList<Servico> listaServicosOrcamentoAtual(){

        if (orcamentoAtual != null) {

           return orcamentoAtual.getServicos();
        }

        return  FXCollections.emptyObservableList();
    }

    public boolean adicionaServicoOrcamento(Servico s){

        if (orcamentoAtual != null){
            orcamentoAtual.adicionaServico(s);

            return true;
        }
        return false;
    }



    public boolean abreOrcamento() {

        if (orcamentoAtual == null){
            orcamentoAtual = new Orcamento();

            return true;
        }

        return false;
    }

    public double fechaOrcamento() throws  SQLException{

        if (orcamentoAtual!=null){
            double valor = orcamentoAtual.getValor();

            orcamentoAtual.setData(LocalDateTime.now());

            if (orcamentoAtual.getValor()>0 && orcamentoAtual.getCliente()!= null){

                orcamentoDAO.insere(orcamentoAtual);

            }

            orcamentoAtual = null;

            return valor;
        }

        return -1;
    }

//    public boolean adicionaServico(Servico s){
//        if (orcamentoAtual != null){
//            orcamentoAtual.adicionaServico(s);
//
//            return true;
//        }
//        return false;
//    }


    public boolean adicionaClienteOrcamento(Cliente c){

        if (orcamentoAtual != null){
            orcamentoAtual.setCliente(c);

            return true;
        }

        return false;
    }


}
