//package soom.model;
//
//        import com.itextpdf.text.*;
//        import com.itextpdf.text.pdf.PdfPCell;
//        import com.itextpdf.text.pdf.PdfPTable;
//        import com.itextpdf.text.pdf.PdfWriter;
//        import sample.model.JDBC.JDBCAtendimento;
//
//        import java.io.FileNotFoundException;
//        import java.io.FileOutputStream;
//        import java.text.Format;
//        import java.time.LocalDate;
//        import java.time.format.DateTimeFormatter;
//
//public class Relatorio {
//
//    public Relatorio() {
//    }
//
//    public void gerarRelatorio(LocalDate data){
//        Document docu = new Document();
//
//        try {
//            PdfWriter.getInstance(docu,new FileOutputStream("./relatorio"+ LocalDate.now()+".pdf"));
//
//            docu.open();
//            docu.setPageSize(PageSize.A4);
//
//            Image image=  Image.getInstance("iconMain.png");
//            image.scaleToFit(100,100);
//            docu.add(image);
//
//            Font f= new Font();
//            f.setSize(20);
//            f.setStyle(Font.BOLD);
//
//
//            docu.add(new Paragraph( "\n                                    Relatório\n\n\n",f));
//
//            int quantidade=0;
//            double valor=0;
//
//            for (Atendimento a:JDBCAtendimento.getInstance().listDoDiaSearch(data)) {
//                quantidade++;
//                valor+=a.getServico().getValor();
//            }
//
//            docu.add(new Paragraph("Quantidade de Atendimentos no dia "+data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+": "+quantidade));
//
//            docu.add(new Paragraph("\n"));
//
//            /*PdfPTable table = new PdfPTable(new float[] { 10f, 5f, 2f });
//            PdfPCell celulaServico = new PdfPCell(new Phrase("Serivoço"));*/
//            for(Atendimento b:JDBCAtendimento.getInstance().listDoDiaSearch(data)){
//                docu.add(new Paragraph(b.getServico().toString()+"    "+b.getCliente().getNome()+"  | CPF: "+b.getCliente().getCpf()));
//
///*                PdfPCell celulaValorServico = new PdfPCell(new Phrase(String.valueOf(b.getServico().getValor())));
//                celulaServico.setHorizontalAlignment(Element.ALIGN_CENTER);
//                celulaValorServico.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(celulaServico);
//                table.addCell(celulaValorServico);*/
//            }
//            //docu.add(table);
//            docu.add(new Paragraph("\n"));
//
//
//
//
//
//
//            docu.add(new Paragraph("\n Valor Total: "+valor));
//
//
//            //Process p = Runtime.getRuntime().exec("chmod 777 ./relatorio"+ LocalDate.now()+".pdf");
//
//            docu.close();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
