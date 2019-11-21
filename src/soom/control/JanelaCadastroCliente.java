package soom.control;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import soom.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class JanelaCadastroCliente {

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfId;


    @FXML
    private Label lbId;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbTelefone;

    @FXML
    private TableView<Cliente> tbwCliente;

    @FXML
    private TableColumn<Cliente, String> tbcNome;

    @FXML
    private TableColumn<Cliente, String> tbcTelefone;

    @FXML
    private TableColumn<Cliente, Integer> tbcId;

    @FXML
    private Button btdeletecliente;

    @FXML
    private Button btupdatecliente;

    @FXML
    private Button btinsereCliente;

    @FXML
    private List<Cliente> listClietes;

    private ObservableList<Cliente> observableListClientes;

    private final ClienteDAOImpl clienteDAO = new ClienteDAOImpl();


    public void initialize() throws SQLException {
        carregaTela();

        tbwCliente.getSelectionModel().selectedItemProperty().addListener(((observableValue, cliente, t1) -> seleItemTBWClientes(t1)));

    }

    public void carregaTela() throws SQLException {
        tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tbwCliente.setItems(Oficina.getInstance().listaClientes());

    }

    public void seleItemTBWClientes(Cliente cliente) {

        if (cliente != null) {
            System.out.println("Cliente selecionado: " + cliente.toString());
            lbId.setText(String.valueOf(cliente.getId()));
            lbNome.setText(cliente.getNome());
            lbTelefone.setText(cliente.getTelefone());

        } else {
            lbId.setText("");
            lbNome.setText("");
            lbTelefone.setText("");

        }
    }

    @FXML
    public void insereCliente() throws IOException, SQLException {
        Cliente cliente = new Cliente();
        boolean btConfirmaClick = showJanelinhaCliente(cliente);

        if (btConfirmaClick) {
            Oficina.getInstance().insereCliente(cliente);
            carregaTela();

        }

    }

    @FXML
    public void deletarCliente() throws SQLException {

        Cliente cliente = tbwCliente.getSelectionModel().getSelectedItem();

        if (cliente != null) {
            Oficina.getInstance().deletaCliente(cliente);
            carregaTela();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione um Cliente!!!");
            alert.show();

        }
    }

    @FXML
    public void updateCliente() throws IOException, SQLException {

        Cliente cliente = tbwCliente.getSelectionModel().getSelectedItem();

        if (cliente != null) {
            boolean btConfirmaClick = showJanelinhaCliente(cliente);
            if (btConfirmaClick) {
                Oficina.getInstance().updateCliente(cliente);
                System.out.println("Cliente updatado: " + cliente.toString());

                carregaTela();

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione um Cliente!!!");
            alert.show();

        }

    }

    public boolean showJanelinhaCliente(Cliente cliente) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(JanelinhaCadastroCliente.class.getResource("/view/janelinha_cadastro_cliente.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Cliente");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        JanelinhaCadastroCliente janelinha = loader.getController();
        janelinha.setDialogStage(dialogStage);
        janelinha.setCliente(cliente);

        dialogStage.showAndWait();

        return janelinha.isBtConfirmaClick();
    }



}
