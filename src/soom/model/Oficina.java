package soom.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sqlite.ExtendedCommand;

import java.sql.SQLException;
import java.time.LocalDateTime;


public class Oficina {

    private static Oficina instance = new Oficina();

    private ClienteDAO clienteDAO;
    private ObservableList<Cliente> clientes;

    private CarroDAO carroDAO;
    private ObservableList<Carro> carros;

    private PecaDAO pecaDAO;
    private ObservableList<Peca> pecas;

    private ServicoDAO servicoDAO;
    private ObservableList<Servico> servicos;

    private OrcamentoDAO orcamentoDAO;
    private ObservableList<Orcamento> orcamentos;

    private Orcamento orcamentoAtual = null;
   // private ObservableList<Servico> servicosOrcamentvoidoAtual;




    private Oficina(){
        clientes = FXCollections.observableArrayList();
        clienteDAO = new ClienteDAOImpl();

        carros = FXCollections.observableArrayList();
        carroDAO = new CarroDAOImpl();

        pecas = FXCollections.observableArrayList();
        pecaDAO = new PecaDAOImpl();

        servicos = FXCollections.observableArrayList();
        servicoDAO = (ServicoDAO) new ServicoDAOImpl();

        orcamentos = FXCollections.observableArrayList();
        orcamentoDAO = new OrcamentoDAOImpl();

       // servicosOrcamentoAtual = FXCollections.observableArrayList();
    }

    public static Oficina getInstance(){

        return instance;
    }
//Cliente
    public ObservableList listaClientes() throws SQLException {
    clientes.clear();
    clientes.addAll(clienteDAO.lista());

    return clientes;
}

    public Cliente insereCliente(Cliente c) throws SQLException {
        clienteDAO.insere(c);

        return c;
    }

    public Cliente deletaCliente(Cliente c) throws SQLException {
        clienteDAO.delete(c);

        return c;
    }

    public Cliente updateCliente(Cliente c) throws SQLException {
        clienteDAO.update(c);

        return  c;
    }
//Cliente
//Carro
    public ObservableList listaCarros() throws SQLException {
        carros.clear();
        carros.addAll(carroDAO.lista());

        return carros;
    }

    public Carro insereCarro(Carro ca) throws SQLException {
        carroDAO.insere(ca);

        return ca;
    }

    public Carro deletaCarro(Carro ca) throws SQLException {
        carroDAO.delete(ca);

        return  ca;
    }

    public Carro updateCarro(Carro ca) throws SQLException {
        carroDAO.update(ca);

        return  ca;
    }
// Carro

//Peça
    public ObservableList listaPecas() throws SQLException {
     pecas.clear();
     pecas.addAll(pecaDAO.lista());

     return pecas;
    }

    public Peca inserePeca(Peca p) throws SQLException {
        pecaDAO.insere(p);

        return p;
    }

    public Peca deletaPeca(Peca p) throws SQLException {
        pecaDAO.delete(p);

        return p;
    }

    public Peca updatePeca(Peca p) throws SQLException {
        pecaDAO.update(p);

        return p;
    }
//Peça
    //Servico
    public ObservableList listaServicos() throws SQLException {
        servicos.clear();
        servicos.addAll(servicoDAO.lista());

        return servicos;
    }

    public Servico insereServico(Servico s) throws SQLException {
        servicoDAO.insere(s);

        return s;
    }

    public Servico deletaServico(Servico s) throws SQLException {
        servicoDAO.delete(s);

        return s;
    }

    public Servico updateServico(Servico s) throws SQLException {
        servicoDAO.update(s);

        return s;
    }
//Serviço
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

    public double fechaOrcamento() throws SQLException{

        if (orcamentoAtual!=null){


            double valor = orcamentoAtual.getValor() ;

            orcamentoAtual.setData(LocalDateTime.now());

            if (orcamentoAtual.getValor()>0 && orcamentoAtual.getCliente()!= null){

                orcamentoDAO.insere(orcamentoAtual);

            }

            orcamentoAtual = null;

            return valor;
        }

        return -1;
    }

    public boolean adicionaClienteOrcamento(Cliente c){

        if (orcamentoAtual != null){
            orcamentoAtual.setCliente(c);

            return true;
        }

        return false;
    }

////////
public boolean adicionaCarroOrcamento(Carro ca){

    if (orcamentoAtual != null){
        orcamentoAtual.setCarro(ca);

        return true;
    }

    return false;
}

    ////////////
  //  adicionaCarroOrcamento

}
