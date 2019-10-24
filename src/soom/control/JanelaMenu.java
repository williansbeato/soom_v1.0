//package soom.control;
//
//import com.itextpdf.io.font.constants.StandardFonts;
//import com.itextpdf.kernel.colors.ColorConstants;
//import com.itextpdf.kernel.font.PdfFont;
//import com.itextpdf.kernel.font.PdfFontFactory;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.borders.SolidBorder;
//import com.itextpdf.layout.element.Cell;
//import com.itextpdf.layout.element.Paragraph;
//import com.itextpdf.layout.element.Table;
//import com.itextpdf.layout.property.TextAlignment;
//import com.itextpdf.layout.property.UnitValue;
//import javafx.application.Platform;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
//import soom.model.Ferramenta;
//import soom.model.Orcamento;
//import soom.model.OrcamentoDAO;
//import soom.model.OrcamentoDAOImpl;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//public class JanelaMenu {
//
//
//    @FXML
//    public BorderPane TELA;
//
//    @FXML
//    private Button btgeraRela;
//    @FXML
//    private Button btfazerOrcamento;
//
//    public void fazerOrcamento(){
//        Ferramenta.getInstance().mudaJanela(TELA,"/view/janela_principal.fxml");
//    }
//
//
//    private Document abreDocumento(String arq) throws IOException {
//        PdfWriter writer = new PdfWriter(arq);
//        PdfDocument pdf = new PdfDocument(writer);
//        Document document = new Document(pdf);
//
//        return document;
//    }
//
//
//    public void geraRela(String arq) {
//        try {
//
//            //utilizado para buscar a lista de pessoas
//            OrcamentoDAO orcamentoDAO = new OrcamentoDAOImpl();
//            java.util.List<Orcamento> lista = orcamentoDAO.lista();
//
//            //cria o documento
//            Document document = abreDocumento(arq);
//
//            //carrega uma imagem da pasta resources/images
//            //Image java = new Image(ImageDataFactory.create(getClass().getResource("/images/java.jpeg")));
//
//            //diminui o tamanho da imagem em 50% na horizontal e 50% na vertical
//            // java.scale(0.5f,0.5f);
//
//            //inclui um parágrafo e no parágrafo é inserida a imagem
//            //document.add(new Paragraph("").add(java));
//
//
//            //coloca um parágrafo de cabeçalho, com alinhamento centralizado
//            Paragraph paragrafo = new Paragraph("Pessoas Cadastradas");
//            //alinha contéudo do parágrafo no centro da página
//            paragrafo.setTextAlignment(TextAlignment.CENTER);
//
//            //indica que o parágrafo é negrito
//            paragrafo.setBold();
//
//            //inclui o paragrafo no documento
//            document.add(paragrafo);
//
//
//            //cria a tabela
//            Table table = new Table(UnitValue.createPercentArray(new float[]{50, 30, 20}))
//                    .useAllAvailableWidth();
//
//
//            //utilizado para criar o cabeçalho da tabela
//            String[] cabecalho = {"Cliente", "Serviço", "Valor"};
//
//            //percore o vetor colocando cada elemento dentro de uma célula
//            for (String s : cabecalho) {
//                //cria uma célula ue irá conter o conteúdo
//                Cell cell = new Cell();
//                //o conteúdo é coloca em um parágrafo
//                cell.add(new Paragraph(s));
//                //ajusta a cor de fundo da célula
//                cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
//                //ajusta a linha de borda da célula
//                //https://api.itextpdf.com/iText7/7.1.7/com/itextpdf/layout/borders/SolidBorder.html
//                cell.setBorder(new SolidBorder(ColorConstants.BLACK, 2));
//                //inclui a célula como cabeçalho, que irá se repetir por todas páginas em que a tabela aparecer (caso a quantidade
//                //de dados for muito grande e precise de várias páginas)
//                table.addHeaderCell(cell);
//
//            }
//
//            //cria uma fonte
//            //https://api.itextpdf.com/iText7/7.1.7/com/itextpdf/io/font/constants/StandardFonts.html
//            PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);
//            //ajusta a fonte da tabela, será utilizada por todas as células
//            table.setFont(font);
//            //ajusta o tamanho da fonte
//            table.setFontSize(12);
//            //percorre a lista e inclui as células. Cada atributo da pessoa
//            //vai em uma célula separada
//            for (Orcamento p : lista) {
//                table.addCell(p.getCliente());
//                table.addCell((Cell) p.getServicos());
//                table.addCell(p.getValor());
//
//            }
//
//            //adiciona a tabela ao documento
//            document.add(table);
//
//            document.close();
//
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
