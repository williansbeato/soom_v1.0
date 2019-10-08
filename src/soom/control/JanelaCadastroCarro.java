package soom.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import soom.model.Oficina;

import java.sql.SQLException;

public class JanelaCadastroCarro {

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfModelo;

    @FXML
    private TextField tfAno;

    public void processaResultado(){

        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        String ano = tfAno.getText();

        try {
            Oficina.getInstance().insereCarro(marca,modelo,ano);
        }catch (SQLException e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR,e.getMessage());
            a.showAndWait();
        }
    }

}
