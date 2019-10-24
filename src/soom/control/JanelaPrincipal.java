package soom.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import soom.model.Carro;
import soom.model.Cliente;
import soom.model.Oficina;
import soom.model.Servico;
//import soom.model.Ferramenta;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class JanelaPrincipal {

    @FXML
    private ComboBox<Cliente> ltwClientes;
    @FXML
    private ListView<Carro> cbCarros;

///

//    @FXML
//    private TableView<Servico> tbwServicosbolados;
//
//    @FXML
//    private TableColumn<Servico, String> tbcCategoria;
//
//    @FXML
//    private TableColumn<Servico, String> tbcNome;
//
//    @FXML
//    private TableColumn<Servico, Double> tbcValorServico;
//

    ////


    @FXML
    private ListView<Servico> ltwServicos;
    @FXML
    private ListView<Servico> ltwServicosOrcamento;
    @FXML
    private Button btIniciar;
    @FXML
    private Button btFechar;
    @FXML
    private Button btIncluir;



//    @FXML
//    private TableView<Servico> tbwServicosOrcamento;
//
//    @FXML
//    private TableColumn<Servico, String> tbcCategoria;
//
//    @FXML
//    private TableColumn<Servico, String> tbcNome;
//
//    @FXML
//    private TableColumn<Servico, Double> tbcValorServico;



    public void initialize(){
        try {
            ltwClientes.setItems(Oficina.getInstance().listaClientes());
            ltwServicos.setItems(Oficina.getInstance().listaServicos());



////
//            tbcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
//            tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
//            tbcValorServico.setCellValueFactory(new PropertyValueFactory<>("valor"));
////



        }catch (SQLException e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR,e.getMessage());
            a.showAndWait();
        }

        alteraComponentes(true);

    }

    @FXML
    public void cadastraCliente(){

        Dialog<ButtonType> dialog = new Dialog<>();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/janela_cadastro_cliente.fxml"));

        try {

            Pane root = loader.load();

            dialog.getDialogPane().setContent(root);

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

           Optional<ButtonType> ret = dialog.showAndWait();

           if (ret.isPresent() && ret.get()==ButtonType.OK){

               JanelaCadastroCliente cadastroCliente = loader.getController();

               cadastroCliente.processaResultado();
           }


        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void cadastraCarro(){

        Dialog<ButtonType> dialog = new Dialog<>();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/janela_cadastro_carro.fxml"));

        try {

            Pane root = loader.load();

            dialog.getDialogPane().setContent(root);

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> ret = dialog.showAndWait();

            if (ret.isPresent() && ret.get()==ButtonType.OK){

                JanelaCadastroCarro cadastroCarro = loader.getController();

                cadastroCarro.processaResultado();
            }


        }catch (IOException e){
            e.printStackTrace();
        }


    }


    @FXML
    public void cadastraPeca(){

        Dialog<ButtonType> dialog = new Dialog<>();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/janela_cadastro_peca.fxml"));

        try {

            Pane root = loader.load();

            dialog.getDialogPane().setContent(root);

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> ret = dialog.showAndWait();

            if (ret.isPresent() && ret.get()==ButtonType.OK){

                JanelaCadastroPeca cadastroPeca = loader.getController();

                cadastroPeca.processaResultado();
            }


        }catch (IOException e){
            e.printStackTrace();
        }


    }


    @FXML
    public void cadastraServico(){

        Dialog<ButtonType> dialog = new Dialog<>();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/janela_cadastro_servico.fxml"));
        try {
            Pane root = loader.load();
            dialog.getDialogPane().setContent(root);

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

             Optional<ButtonType> ret = dialog.showAndWait();

             if (ret.isPresent() && ret.get()==ButtonType.OK){
                 JanelaCadastroServico cadastroServico = loader.getController();

                 cadastroServico.processaResultado();

             }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void iniciarOrcamento() {

        if (Oficina.getInstance().abreOrcamento()) {
            alteraComponentes(false);

            ltwServicosOrcamento.setItems(Oficina.getInstance().listaServicosOrcamentoAtual());

        }

    }

    @FXML
    public void incluirServicoOrcamento(){

        Servico servico = ltwServicos.getSelectionModel().getSelectedItem();

        if(servico != null){
            Oficina.getInstance().adicionaServicoOrcamento(servico);
        }

    }

    /////////////teste
    @FXML
    public void mostrarCarro(){

        Carro carro = cbCarros.getSelectionModel().getSelectedItem();

//        if (carro != null){
//
//        }
    }
//
//    @FXML
//    public void excluirServicoOrcamento(){
//
//        Servico servico = ltwServicos.getSelectionModel().getSelectedItem();
//        if (servico == null) {
//            Oficina.getInstance().delete(ltwServicos.getSelectionModel().getSelectedItem());
//        }
//
//    }

    @FXML
    public void fecharOrcamento() {

        try {
            double valor = Oficina.getInstance().fechaOrcamento();

            if (valor >=0){
                alteraComponentes(true);
            }

        }catch (SQLException e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR,e.getMessage());
            a.showAndWait();
        }



    }


    @FXML
    public void adicionaClienteOrcamento(){

        Cliente cliente = ltwClientes.getSelectionModel().getSelectedItem();

        if (cliente != null){

            Oficina.getInstance().adicionaClienteOrcamento(cliente);
        }
    }


    @FXML
    public void listaOrcamentos(){

        Dialog<ButtonType> dialog = new Dialog<>();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/janela_lista_orcamentos.fxml"));
        try {
            Pane root = loader.load();

            dialog.getDialogPane().setContent(root);

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

            dialog.showAndWait();

        }catch (IOException e){
            e.printStackTrace();
        }


    }


    private void alteraComponentes(boolean estado){
        ltwServicosOrcamento.setDisable(estado);
        ltwServicos.setDisable(estado);
        ltwClientes.setDisable(estado);

        ltwServicos.getSelectionModel().clearSelection();
        ltwClientes.getSelectionModel().clearSelection();

        btFechar.setDisable(estado);
        btIncluir.setDisable(estado);
        btIniciar.setDisable(!estado);
    }


}
