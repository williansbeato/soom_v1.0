package soom.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import soom.model.Carro;

public class JanelinhaCadastroCarro {



    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfModelo;

    @FXML
    private Label lbModelo;

    @FXML
    private Label lbMarca;

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btCancelar;


    private Stage dialogStage;
    private boolean btConfirmaClick = false;
    private Carro carro;


    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtConfirmaClick() {
        return btConfirmaClick;
    }

    public void setBtConfirmaClick(boolean btConfirmaClick) {
        this.btConfirmaClick = btConfirmaClick;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;

        this.tfMarca.setText(carro.getMarca());
        this.tfModelo.setText(carro.getModelo());
    }

    @FXML
    public void hbtConfirmar(){

        if (validaEntrada()){
            carro.setMarca(tfMarca.getText());
            carro.setModelo(tfModelo.getText());

            btConfirmaClick = true;
            dialogStage.close();
        }
    }

    @FXML
    public void hbtCancelar(){
        dialogStage.close();

    }

    private boolean validaEntrada(){

        String errorMessage = "";

        if (tfModelo.getText() == null || tfModelo.getText().length() == 0) {
            errorMessage += "Modelo inválido!\n\n";

        }
        if (tfMarca.getText() == null || tfMarca.getText().length() == 0) {
            errorMessage += "Marca inválido!\n\n";

        }
        if (errorMessage.length() == 0){
            return true;

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos Invalidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

}
