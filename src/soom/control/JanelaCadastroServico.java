package soom.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import soom.model.Oficina;

import java.sql.SQLException;

public class JanelaCadastroServico {
    @FXML
    private TextField tfCategoria;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfValor;

    public void processaResultado(){

        String categoria = tfCategoria.getText();
        String nome = tfNome.getText();
        double valor = Double.valueOf(tfValor.getText());

        try {
            Oficina.getInstance().insereServico(categoria,nome,valor);
        }catch (SQLException e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR,e.getMessage());
            a.showAndWait();
        }
    }
}
