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

public class JanelaCadastroPeca {

    @FXML
    private TextField tfCategoria;

    @FXML
    private TextField tfNome;
//////////

    @FXML
    private TextField tfId;
//
//    @FXML
//    private TextField tfCategoria;
//
//    @FXML
//    private TextField tfNome;

    @FXML
    private Label lbId;

    @FXML
    private Label lbCategoria;

    @FXML
    private Label lbNome;

    @FXML
    private TableView<Peca> tbwPeca;

    @FXML
    private TableColumn<Peca, String> tbcCategoria;

    @FXML
    private TableColumn<Carro, String> tbcNome;

    @FXML
    private TableColumn<Carro, Integer> tbcId;

    @FXML
    private Button btdeletepeca;

    @FXML
    private Button btupdatePeca;

    @FXML
    private Button btinserePeca;

    @FXML
    private List<Carro> listPeca;

    private ObservableList<Carro> observableListPeca;

    private final PecaDAOImpl pecaDAO = new PecaDAOImpl();

    public void initialize() throws SQLException {
        carregaTela();

  //      tbwCarro.getSelectionModel().selectedItemProperty().addListener(((observableValue, carro, t1) -> seleItemTBWCarros(t1)));

        tbwPeca.getSelectionModel().selectedItemProperty().addListener((observableValue, peca, t1) -> seleItemTBWPecas(t1));
    }

    public void carregaTela() throws SQLException {
        tbcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbwPeca.setItems(Oficina.getInstance().listaPecas());

    }

    public void seleItemTBWPecas(Peca peca) {

        if (peca != null) {
            System.out.println("Peça selecionada: " + peca.toString());
            lbId.setText(String.valueOf(peca.getId()));
            lbCategoria.setText(peca.getCategoria());
            lbNome.setText(peca.getNome());

        } else {
            lbId.setText("");
            lbCategoria.setText("");
            lbNome.setText("");

        }
    }

    @FXML
    public void inserePeca() throws IOException, SQLException {
        Peca peca = new Peca();

        boolean btConfirmaClick = showJanelinhaPeca(peca);

        if (btConfirmaClick) {
            Oficina.getInstance().inserePeca(peca);
            carregaTela();

        }

    }

    @FXML
    public void deletaPeca() throws SQLException {

        Peca peca = tbwPeca.getSelectionModel().getSelectedItem();

        if (peca != null) {
            Oficina.getInstance().deletaPeca(peca);
            carregaTela();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione uma Peça!!!");
            alert.show();

        }
    }

    @FXML
    public void updatePeca() throws IOException, SQLException {

        Peca peca = tbwPeca.getSelectionModel().getSelectedItem();

        if (peca != null) {
            boolean btConfirmaClick = showJanelinhaPeca(peca);
            if (btConfirmaClick) {
                Oficina.getInstance().updatePeca(peca);
                System.out.println("Peça updatado: " + peca.toString());

                carregaTela();

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione uma Peça!!!");
            alert.show();

        }

    }

    public boolean showJanelinhaPeca(Peca peca) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(JanelinhaCadastroPeca.class.getResource("/view/janelinha_cadastro_peca.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Peça");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        JanelinhaCadastroPeca janelinha = loader.getController();
        janelinha.setDialogStage(dialogStage);
        janelinha.setPeca(peca);

        dialogStage.showAndWait();

        return janelinha.isBtConfirmaClick();
    }

}