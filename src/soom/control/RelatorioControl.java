//package soom.control;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//import soom.model.GeradorPDF;
//
//import java.awt.event.ActionEvent;
//import java.io.File;
//
//public class RelatorioControl {
//
//
//
//    @FXML
//    private Button btgeraRela;
//
//
//
//    @FXML
//    private void geraPdf(ActionEvent av){
//        GeradorPDF geradorPDF = new GeradorPDF();
//
//        Button bt = (Button) av.getSource();
//
//        FileChooser fc = new FileChooser();
//        File f = fc.showSaveDialog(null);
//
//
//        if (f != null){
//            String arq = f.getAbsolutePath();
//            if (bt.getText().equals("geraRela")){
//                geradorPDF.geraRela(arq);
//                System.out.println("Carro selecionado: " + geradorPDF.toString());
//
//            }
//        }
//    }
//
//
//
//
//    @FXML
//    public void fechar(ActionEvent av){
//        Stage stage = (Stage) ((Button)av.getSource()).getScene().getWindow();
//
//        stage.close();
//    }
//    @FXML
//    public void geraPdf(javafx.event.ActionEvent av) {
//        GeradorPDF geradorPDF = new GeradorPDF();
//
//        Button bt = (Button) av.getSource();
//
//        FileChooser fc = new FileChooser();
//        File f = fc.showSaveDialog(null);
//
//
//        if(f != null){
//            String arq = f.getAbsolutePath();
//
//            //if(bt.getText().equals("Gerar 1")){
//                geradorPDF.geraRela(arq);
//            //}else{
//              //  geradorPDF.criaPdf_2(arq);
//            //}
//
//
//        }
//
//    }
////
////    public void geraPdf(javafx.event.ActionEvent actionEvent) {
////        GeradorPDF geradorPDF = new GeradorPDF();
////
////        Button bt = (Button) actionEvent.getSource();
////
////        FileChooser fc = new FileChooser();
////        File f = fc.showSaveDialog(null);
////
////
////        if (f != null){
////            String arq = f.getAbsolutePath();
////            if (bt.getText().equals("geraRela")){
////                geradorPDF.geraRela(arq);
////            }
////        }
////    }
//}
