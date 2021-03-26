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
        mainFrame.setWidthPercentage(95); //Width 100%
        mainFrame.setSpacingBefore(5f); //Space before table
        mainFrame.setSpacingAfter(5f); //Space after table

        PdfPCell mainCell = new PdfPCell(new Paragraph("Cell 1"));
        mainCell.setBorderColor(BaseColor.BLACK);
        mainCell.setPaddingLeft(10);
        mainCell.setFixedHeight(650f);
        mainCell.setHorizontalAlignment(Element.ALIGN_TOP);
        mainCell.setVerticalAlignment(Element.ALIGN_LEFT);

        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100); //Width 100%

        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Cell 1"));
        headerCell1.setBorderColor(BaseColor.BLACK);
        headerCell1.setFixedHeight(50f);
        headerCell1.setHorizontalAlignment(Element.ALIGN_TOP);
        headerCell1.setVerticalAlignment(Element.ALIGN_LEFT);
        headerCell1.disableBorderSide(Rectangle.NO_BORDER);

        Paragraph headerParagraph = new Paragraph("Cell 2");
        headerParagraph.setAlignment(TextAlignment.RIGHT);
        PdfPCell headerCell2 = new PdfPCell(headerParagraph);
        headerCell2.setBorderColor(BaseColor.BLACK);
        headerCell2.setFixedHeight(50f);
        headerCell2.setHorizontalAlignment(Element.ALIGN_TOP);
        headerCell2.setVerticalAlignment(Element.ALIGN_LEFT);
        headerCell1.disableBorderSide(Rectangle.NO_BORDER);


        PdfPTable infoTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100); //Width 100%

        PdfPCell infoCell1 = new PdfPCell(new Paragraph("Cell 3"));
        infoCell1.setBorderColor(BaseColor.BLACK);
        infoCell1.setFixedHeight(150f);
        infoCell1.setHorizontalAlignment(Element.ALIGN_TOP);
        infoCell1.setVerticalAlignment(Element.ALIGN_LEFT);
        infoCell1.disableBorderSide(Rectangle.NO_BORDER);

        PdfPCell infoCell2 = new PdfPCell(new Paragraph("Cell 4"));
        infoCell2.setBorderColor(BaseColor.BLACK);
        infoCell2.setFixedHeight(150f);
        infoCell2.setHorizontalAlignment(Element.ALIGN_TOP);
        infoCell2.setVerticalAlignment(Element.ALIGN_LEFT);
        infoCell2.disableBorderSide(Rectangle.NO_BORDER);
        
        
        

        headerTable.addCell(headerCell1);
        headerTable.addCell(headerCell2);

        infoTable.addCell(infoCell1);
        infoTable.addCell(infoCell2);


        mainCell.addElement(headerTable);
        mainCell.addElement(infoTable);
        mainFrame.addCell(mainCell);

        document.add(mainFrame);
        document.close();
        pdfWriter.close();
        return document;
    }
}
