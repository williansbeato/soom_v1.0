package soom.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import soom.model.Peca;

public class JanelinhaCadastroPeca {


    @FXML
    private TextField tfCategoria;

    @FXML
    private TextField tfNome;

    @FXML
    private Label lbCategoria;

    @FXML
    private Label lbNome;

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btCancelar;

    private Stage dialogStage;
    private boolean btConfirmaClick = false;
    private Peca peca;

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

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;

        this.tfCategoria.setText(peca.getCategoria());
        this.tfNome.setText(peca.getNome());

    }

    @FXML
    public void hbtConfirmar(){

        if (validaEntrada()){
            peca.setCategoria(tfCategoria.getText());
            peca.setNome(tfNome.getText());

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

        if (tfCategoria.getText() == null || tfCategoria.getText().length() == 0) {
            errorMessage += "Categoria inválido!\n\n";

        }
        if (tfNome.getText() == null || tfNome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n\n";

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
