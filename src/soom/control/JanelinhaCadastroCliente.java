package soom.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import soom.model.Carro;
import soom.model.Cliente;

public class JanelinhaCadastroCliente {

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTelefone;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbTelefone;

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btCancelar;

    private Stage dialogStage;
    private boolean btConfirmaClick = false;
    private Cliente cliente;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        this.tfNome.setText(cliente.getNome());
        this.tfTelefone.setText(cliente.getTelefone());

    }

    @FXML
    public void hbtConfirmar(){

        if (validaEntrada()){
            cliente.setNome(tfNome.getText());
            cliente.setTelefone(tfTelefone.getText());

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

        if (tfNome.getText() == null || tfTelefone.getText().length() == 0) {
            errorMessage += "Nome inválido!\n\n";

        }
        if (tfNome.getText() == null || tfTelefone.getText().length() == 0) {
            errorMessage += "Telefone inválido!\n\n";

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
