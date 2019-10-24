//package soom.model;
//
//import javafx.application.Platform;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class Ferramenta {
//
//    private static Ferramenta instance;
//
//
//    public static Ferramenta getInstance(){
//        if(instance==null){
//            instance = new Ferramenta();
//        }
//        return instance;
//    }
//    public void mudaJanela(BorderPane TELA, String endereco){
//
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource(endereco));
//
//                try {
//                    Parent layoutJanela = loader.load();
//
//                    Stage stage=(Stage)TELA.getScene().getWindow();
//
//                    stage.setScene(new Scene(layoutJanela,800 , 600));
//                    stage.setResizable(false);
//
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//    public void mensagem(Alert.AlertType tipo, String mensagem){
//        Alert alerta = new Alert(tipo);
//        alerta.setTitle("Alerta!!");
//        alerta.setContentText(mensagem);
//        alerta.showAndWait();
//    }
//
//}
