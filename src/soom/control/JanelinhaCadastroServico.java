package soom.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import soom.model.Servico;

public class JanelinhaCadastroServico {


    @FXML
    private TextField tfCategoria;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfValor;

    @FXML
    private Label lbCategoria;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbValor;

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btCancelar;

    private Stage dialogStage;
    private boolean btConfirmaClick = false;
    private Servico servico;

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

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;

        this.tfCategoria.setText(servico.getCategoria());
        this.tfNome.setText(servico.getNome());
        //this.tfValor.setT(servico.getValor());
        this.tfValor.setText(String.format("%.2f",servico.getValor()));
    }

    @FXML
    public void hbtConfirmar(){

        if (validaEntrada()){
            servico.setCategoria(tfCategoria.getText());
            servico.setNome(tfNome.getText());
//            servico.setValor(tfValor.getText());
            servico.setValor(Double.parseDouble(tfValor.getText()));

//            lbValor.setText(String.format("%.2f",servico.getValor()));

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

        if (tfValor.getText() == null || tfValor.getText().length() == 0) {
            errorMessage += "Valor inválido!\n\n";

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
