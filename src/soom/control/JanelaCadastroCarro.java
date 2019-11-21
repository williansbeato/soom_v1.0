package soom.control;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import soom.model.*;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.KeyStore;
import java.sql.SQLException;
import java.util.List;

public class JanelaCadastroCarro {

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfModelo;

    @FXML
    private Label lbId;

    @FXML
    private Label lbModelo;

    @FXML
    private Label lbMarca;

    @FXML
    private TableView<Carro> tbwCarro;

    @FXML
    private TableColumn<Carro, String> tbcMarca;

    @FXML
    private TableColumn<Carro, String> tbcModelo;

    @FXML
    private TableColumn<Carro, Integer> tbcId;

    @FXML
    private Button btdeletecarro;

    @FXML
    private Button btupdatecarro;

    @FXML
    private Button btinsereCarro;

    @FXML
    private List<Carro> listCarros;

    private ObservableList<Carro> observableListCarros;

    private final CarroDAOImpl carroDAO = new CarroDAOImpl();

    public void initialize() throws SQLException {
        carregaTela();

        tbwCarro.getSelectionModel().selectedItemProperty().addListener(((observableValue, carro, t1) -> seleItemTBWCarros(t1)));

    }

    public void carregaTela() throws SQLException {
        tbcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tbcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tbwCarro.setItems(Oficina.getInstance().listaCarros());

    }

    public void seleItemTBWCarros(Carro carro) {

        if (carro != null) {
            System.out.println("Carro selecionado: " + carro.toString());
            lbId.setText(String.valueOf(carro.getId()));
            lbMarca.setText(carro.getMarca());
            lbModelo.setText(carro.getModelo());

        } else {
            lbId.setText("");
            lbMarca.setText("");
            lbModelo.setText("");

        }
    }

    @FXML
    public void insereCarro() throws IOException, SQLException {
        Carro carro = new Carro();

        boolean btConfirmaClick = showJanelinhaCarro(carro);

        if (btConfirmaClick) {
            Oficina.getInstance().insereCarro(carro);
            carregaTela();

        }

    }

    @FXML
    public void deletarCarro() throws SQLException {

        Carro carro = tbwCarro.getSelectionModel().getSelectedItem();

        if (carro != null) {
            Oficina.getInstance().deletaCarro(carro);
            carregaTela();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione um Carro!!!");
            alert.show();

        }
    }

    @FXML
    public void updateCarro() throws IOException, SQLException {

        Carro carro = tbwCarro.getSelectionModel().getSelectedItem();

        if (carro != null) {
            boolean btConfirmaClick = showJanelinhaCarro(carro);
            if (btConfirmaClick) {
                Oficina.getInstance().updateCarro(carro);
                System.out.println("Carro updatado: " + carro.toString());

                carregaTela();

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione um Carro!!!");
            alert.show();

        }

    }

    public boolean showJanelinhaCarro(Carro carro) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(JanelinhaCadastroCarro.class.getResource("/view/janelinha_cadastro_carro.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Cliente");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        JanelinhaCadastroCarro janelinha = loader.getController();
        janelinha.setDialogStage(dialogStage);
        janelinha.setCarro(carro);

        dialogStage.showAndWait();

        return janelinha.isBtConfirmaClick();
    }

}