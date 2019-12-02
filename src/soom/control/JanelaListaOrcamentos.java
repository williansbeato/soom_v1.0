package soom.control;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import soom.model.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import soom.model.GeradorPDF;

import java.io.File;


import java.sql.SQLException;
import java.time.LocalDateTime;

public class  JanelaListaOrcamentos {

    @FXML
    private TableView<Orcamento> tbwOrcamentos;

    @FXML
    private TableColumn<Orcamento, LocalDateTime> tbcData;

    @FXML
    private TableColumn<Orcamento, Cliente> tbcCliente;

    @FXML
    private TableColumn<Orcamento, Double> tbcValor;


    @FXML
    private TableView<Servico> tbwServicosOrcamento;

    @FXML
    private TableColumn<Servico, String> tbcCategoria;

    @FXML
    private TableColumn<Servico, String> tbcNome;

    @FXML
    private TableColumn<Servico, Double> tbcValorServico;


    @FXML
    private Button btgeraRela;


    public void initialize(){

        tbcData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tbcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tbcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        try {
            tbwOrcamentos.setItems(Oficina.getInstance().listaOrcamentos());

        }catch (SQLException e){

            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR,e.getMessage());
            a.showAndWait();

        }

        tbcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbcValorServico.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }


    @FXML
    public void selecionaOrcamento(){

        Orcamento orcamento = tbwOrcamentos.getSelectionModel().getSelectedItem();

        if (orcamento != null){
            tbwServicosOrcamento.setItems(orcamento.getServicos());
        }
    }


    @FXML
    public void geraPdf(javafx.event.ActionEvent av) {

        GeradorPDF geradorPDF = new GeradorPDF();

        Button bt = (Button) av.getSource();

        FileChooser fc = new FileChooser();
        File f = fc.showSaveDialog(null);


        if(f != null){

            String arq = f.getAbsolutePath();
            Orcamento item = tbwOrcamentos.getSelectionModel().getSelectedItem();
            geradorPDF.geraRela(arq,item);

        }

    }



}
