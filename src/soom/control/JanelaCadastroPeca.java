package soom.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import soom.model.Oficina;

import java.sql.SQLException;

public class JanelaCadastroPeca {

    @FXML
    private TextField tfCategoria;

    @FXML
    private TextField tfNome;


    public void processaResultado(){

        String categoria = tfCategoria.getText();
        String nome = tfNome.getText();

        try {
            Oficina.getInstance().inserePeca(categoria,nome);
        }catch (SQLException e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR,e.getMessage());
            a.showAndWait();
        }
    }


}
