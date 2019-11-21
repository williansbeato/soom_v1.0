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

public class JanelaCadastroServico {
    @FXML
    private TextField tfCategoria;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfValor;

    @FXML
    private TextField tfId;

    @FXML
    private Label lbId;

    @FXML
    private Label lbCategoria;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbValor;

    @FXML
    private TableView<Servico> tbwServico;

    @FXML
    private TableColumn<Servico, String> tbcCategoria;

    @FXML
    private TableColumn<Servico, String> tbcNome;

    @FXML
    private TableColumn<Servico, String> tbcValor;

    @FXML
    private TableColumn<Servico, Integer> tbcId;

    @FXML
    private Button btdeleteServico;

    @FXML
    private Button btupdateServico;

    @FXML
    private Button btinsereServico;

    @FXML
    private List<Servico> listServicos;

    private ObservableList<Servico> observableListServicos;

    private final ServicoDAOImpl servicoDAO = new ServicoDAOImpl();


    public void initialize() throws SQLException {
        carregaTela();

        tbwServico.getSelectionModel().selectedItemProperty().addListener(((observableValue, servico, t1) -> seleItemTBWServicos(t1)));

    }

    public void carregaTela() throws SQLException {
        tbcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tbwServico.setItems(Oficina.getInstance().listaServicos());

    }

    public void seleItemTBWServicos(Servico servico) {

        if (servico != null) {
            System.out.println("Serviço selecionado: " + servico.toString());
            lbId.setText(String.valueOf(servico.getId()));
            lbCategoria.setText(servico.getCategoria());
            lbNome.setText(servico.getNome());
            //lbValor.setText(servico.getValor());

            lbValor.setText(String.format("%.2f",servico.getValor()));

        } else {
            lbId.setText("");
            lbCategoria.setText("");
            lbNome.setText("");
            lbValor.setText("");
        }
    }

    @FXML
    public void insereServico() throws IOException, SQLException {

        Servico servico = new Servico();

        boolean btConfirmaClick = showJanelinhaServico(servico);

        if (btConfirmaClick) {
            Oficina.getInstance().insereServico(servico);
            carregaTela();

        }

    }

    @FXML
    public void deletarServico() throws SQLException {

        Servico servico = tbwServico.getSelectionModel().getSelectedItem();

        if (servico != null) {
            Oficina.getInstance().deletaServico(servico);
            carregaTela();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione um Serviço!!!");
            alert.show();

        }
    }

    @FXML
    public void updateServico() throws IOException, SQLException {

        Servico servico = tbwServico.getSelectionModel().getSelectedItem();

        if (servico != null) {
            boolean btConfirmaClick = showJanelinhaServico(servico);
            if (btConfirmaClick) {
                Oficina.getInstance().updateServico(servico);
                System.out.println("Serviço updatado: " + servico.toString());

                carregaTela();

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione um Serviço!!!");
            alert.show();

        }

    }

    public boolean showJanelinhaServico(Servico servico) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(JanelinhaCadastroServico.class.getResource("/view/janelinha_cadastro_servico.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Servico");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        JanelinhaCadastroServico janelinha = loader.getController();
        janelinha.setDialogStage(dialogStage);
        janelinha.setServico(servico);

        dialogStage.showAndWait();

        return janelinha.isBtConfirmaClick();
    }
}
