package soom.model;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.ListNumberingType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import javafx.scene.control.Alert;
import soom.model.*;
import soom.*;

import java.io.IOException;
import java.sql.SQLException;

public class GeradorPDF {

    //este método cria um documento para receber o conteúdo
    private Document abreDocumento(String arq) throws IOException {
        PdfWriter writer = new PdfWriter(arq);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        return document;
    }

    public void geraRela(String arq, Orcamento o) {
        try {

            //utilizado para buscar a lista de pessoas
            OrcamentoDAO orcamentoDAO = new OrcamentoDAOImpl();
            java.util.List<Orcamento> lista = orcamentoDAO.lista();

            //cria o documento
            Document document = abreDocumento(arq);

            //coloca um parágrafo de cabeçalho, com alinhamento centralizado
            Paragraph paragrafo = new Paragraph("Oficina Mecânica");
            //alinha contéudo do parágrafo no centro da página
            paragrafo.setTextAlignment(TextAlignment.CENTER);

            //indica que o parágrafo é negrito
            paragrafo.setBold();

            //inclui o paragrafo no documento
            document.add(paragrafo);

            //cria a tabela
            Table table = new Table(UnitValue.createPercentArray(new float[]{50}))
                    .useAllAvailableWidth();

            //utilizado para criar o cabeçalho da tabela
            String[] cabecalho = {"Orçamento"};

            //percore o vetor colocando cada elemento dentro de uma célula
            for (String s : cabecalho) {
                //cria uma célula ue irá conter o conteúdo
                Cell cell = new Cell();
                //o conteúdo é coloca em um parágrafo
                cell.add(new Paragraph(s));
                //ajusta a cor de fundo da célula
                cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                //ajusta a linha de borda da célula
                //https://api.itextpdf.com/iText7/7.1.7/com/itextpdf/layout/borders/SolidBorder.html
                cell.setBorder(new SolidBorder(ColorConstants.BLACK, 2));
                //inclui a célula como cabeçalho, que irá se repetir por todas páginas em que a tabela aparecer (caso a quantidade
                //de dados for muito grande e precise de várias páginas)
                table.addHeaderCell(cell);

            }

            //cria uma fonte
            //https://api.itextpdf.com/iText7/7.1.7/com/itextpdf/io/font/constants/StandardFonts.html
            PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);
            //ajusta a fonte da tabela, será utilizada por todas as células
            table.setFont(font);
            //ajusta o tamanho da fonte
            table.setFontSize(12);
            //percorre a lista e inclui as células. Cada atributo da pessoa
            //vai em uma célula separada

            Orcamento orcamento = null;//tbwOrcamentos.getSelectionModel().getSelectedItem();

            table.addCell("Nome: "+o.getCliente().getNome());
            table.addCell("Telefone:"+ o.getCliente().getTelefone());

            //table.addCell("Carro:"+ o.getCarro().getModelo());

            for(Servico serv:o.getServicos()){

                table.addCell("Categoria: "+serv.getCategoria());
                table.addCell("Nome do serviço: "+serv.getNome());
                table.addCell("Valor: "+serv.getValor());
            }

            table.addCell("Valor Total: "+ o.getValor());




            //adiciona a tabela ao documento
            document.add(table);

            document.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}

