package sk.myproject.faktoorka.pdf.creator;

import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Component;
import sk.myproject.faktoorka.entities.Invoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class InvoiceCreator {

    public static final String DEST = "results/zugferd/pdf/basic%05d.pdf";

    public Document createInvoicePdf(Invoice invoice) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(invoice.getId() + ".invoice.pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);

        PdfPTable mainFrame = new PdfPTable(1);
        mainFrame.setWidthPercentage(100); //Width 100%

        PdfPCell mainCell = new PdfPCell(new Paragraph("MainCell"));
        mainCell.setBorderColor(BaseColor.BLACK);
        mainCell.setFixedHeight(750f);
        mainCell.setBorderWidth(2f);
        mainCell.setHorizontalAlignment(Element.ALIGN_TOP);
        mainCell.setVerticalAlignment(Element.ALIGN_LEFT);


        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100); //Width 100%

        PdfPCell headerCell1 = new PdfPCell(new Paragraph("FAKTÚRA", new Font(Font.FontFamily.UNDEFINED, 16f)));
        headerCell1.setBorder(Rectangle.BOTTOM);
        headerCell1.setBorderWidth(2f);
        headerCell1.setFixedHeight(30f);
        headerCell1.setHorizontalAlignment(Element.ALIGN_TOP);
        headerCell1.setVerticalAlignment(Element.ALIGN_LEFT);

        PdfPCell headerCell2 = new PdfPCell(new Paragraph(invoice.getName(), new Font(Font.FontFamily.UNDEFINED, 16f)));
        headerCell2.setBorder(Rectangle.BOTTOM);
        headerCell2.setBorderWidth(2f);
        headerCell2.setFixedHeight(30f);
        headerCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        headerCell2.setVerticalAlignment(Element.ALIGN_RIGHT);

        headerTable.addCell(headerCell1);
        headerTable.addCell(headerCell2);


        PdfPTable infoTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100); //Width 100%

        PdfPCell infoCell1 = new PdfPCell(new Paragraph("Cell 3"));
//        infoCell1.setBorder(Rectangle.NO_BORDER);
        infoCell1.setFixedHeight(150f);

        PdfPCell infoCell2 = new PdfPCell(new Paragraph("Cell 4"));
        infoCell2.setPaddingRight(-5);
//        infoCell2.setBorder(Rectangle.NO_BORDER);
        infoCell2.setFixedHeight(150f);

        infoTable.addCell(infoCell1);
        infoTable.addCell(infoCell2);


        PdfPTable dateTable = new PdfPTable(6);
        dateTable.setWidthPercentage(100f);

        PdfPCell dateCell1 = new PdfPCell(new Paragraph("Dátum vystavenia", new Font(Font.FontFamily.UNDEFINED, 8f)));
        dateCell1.setBorder(Rectangle.BOTTOM);
        dateCell1.setFixedHeight(20f);

        PdfPCell dateCell2 = new PdfPCell(new Paragraph(invoice.getIssueDate().toString(), new Font(Font.FontFamily.UNDEFINED, 14f)));
        dateCell2.setBorder(Rectangle.BOTTOM);
        dateCell1.setFixedHeight(20f);

        PdfPCell dateCell3 = new PdfPCell(new Paragraph("Dátum dodania", new Font(Font.FontFamily.UNDEFINED, 8f)));
        dateCell3.setBorder(Rectangle.BOTTOM);
        dateCell2.setBorder(Rectangle.LEFT);
        dateCell1.setFixedHeight(20f);

        PdfPCell dateCell4 = new PdfPCell(new Paragraph(invoice.getDueDate().toString(), new Font(Font.FontFamily.UNDEFINED, 14f)));
        dateCell4.setBorder(Rectangle.BOTTOM);
        dateCell1.setFixedHeight(20f);

        PdfPCell dateCell5 = new PdfPCell(new Paragraph("Dátum splatnosti", new Font(Font.FontFamily.UNDEFINED, 8f)));
        dateCell5.setBorder(Rectangle.BOTTOM);
        dateCell5.setBorder(Rectangle.LEFT);
        dateCell1.setFixedHeight(20f);

        PdfPCell dateCell6 = new PdfPCell(new Paragraph(invoice.getMonth().toString(), new Font(Font.FontFamily.UNDEFINED, 14f)));
        dateCell6.setBorder(Rectangle.BOTTOM);
        dateCell1.setFixedHeight(20f);

        dateTable.addCell(dateCell1);
        dateTable.addCell(dateCell2);
        dateTable.addCell(dateCell3);
        dateTable.addCell(dateCell4);
        dateTable.addCell(dateCell5);
        dateTable.addCell(dateCell6);


        mainCell.addElement(headerTable);
        mainCell.addElement(infoTable);
        mainCell.addElement(dateTable);
        mainFrame.addCell(mainCell);

        document.add(mainFrame);
        document.close();
        pdfWriter.close();
        return document;
    }
}
