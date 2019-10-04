package soom.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import soom.model.Oficina;

import java.sql.SQLException;

public class JanelaCadastroCliente {

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTelefone;


    public void processaResultado(){

        String nome = tfNome.getText();
        String telefone = tfTelefone.getText();

        try{
            Oficina.getInstance().insereCliente(nome,telefone);
        }catch (SQLException e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR,e.getMessage());
            a.showAndWait();
        }
}


}
