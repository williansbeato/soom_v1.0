package soom.control;
import com.itextpdf.kernel.colors.Lab;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Button;



import soom.model.Cliente;
import soom.model.ClienteDAOImpl;
import soom.model.Usuario;
import soom.model.UsuarioDAOImpl;

import java.io.IOException;

public class JanelaLogin {


    @FXML
    private Label lbMsg;
    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfSenha;
    @FXML
    private Usuario usuario = new Usuario();
    @FXML
    private Button submitButton;

    private static UsuarioDAOImpl instance;

    public static UsuarioDAOImpl getInstance(){
        if(instance==null){
            instance = new UsuarioDAOImpl();
        }
        return instance;
    }

    @FXML
    private void Login(ActionEvent event) throws Exception{






        Window owner = submitButton.getScene().getWindow();

        System.out.println(tfLogin.getText());
        System.out.println(tfSenha.getText());

        if (tfLogin.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (tfSenha.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String login = tfLogin.getText();
        String senha = tfSenha.getText();

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        boolean flag = usuarioDAO.validate(login , senha);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            //infoBox("Login Successful!", null, "Failed");
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/view/janela_principal.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Soom  ");
            stage.show();
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

//        String login = this.tfLogin.getText();
//        String senha = this.tfSenha.getText();
//
//        usuario.setLogin(login);
//        usuario.setSenha(senha);
//
//        usuario = UsuarioDAOImpl.class
//
//
//        if (login == ){
//            lbMsg.setText("iae : " + tfLogin.getText());
//            ((Node) (event.getSource())).getScene().getWindow().hide();
//            Parent parent = FXMLLoader.load(getClass().getResource("/view/janela_principal.fxml"));
//            Stage stage = new Stage();
//            Scene scene = new Scene(parent);
//            stage.setScene(scene);
//            stage.setTitle("IAE");
//            stage.show();
//        }
//        else {
//            lbMsg.setText("Tem algo errado ai!!!");
//        }
//    }

}








//
//        if (tfLogin.getText().equals("test") && tfSenha.getText().equals("test")){
//                lbMsg.setText("iae : " + tfLogin.getText());
//                ((Node) (event.getSource())).getScene().getWindow().hide();
//                Parent parent = FXMLLoader.load(getClass().getResource("/view/janela_principal.fxml"));
//                Stage stage = new Stage();
//                Scene scene = new Scene(parent);
//                stage.setScene(scene);
//                stage.setTitle("IAE");
//                stage.show();
//                }
//                else {
//                lbMsg.setText("Tem algo errado ai!!!");
//                }