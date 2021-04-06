package sk.myproject.faktoorka.pdf.creator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Component;
import sk.myproject.faktoorka.entities.Invoice;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class InvoiceCreator {

    public Document createInvoicePdf(Invoice invoice) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(invoice.getId() + ".invoice.pdf"));
        document.open();

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
        headerTable.setWidthPercentage(100f); //Width 100%

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
        dateCell1.setFixedHeight(30f);
        dateCell1.setBorder(Rectangle.NO_BORDER);

        PdfPCell dateCell2 = new PdfPCell(new Paragraph(invoice.getIssueDate().toString(), new Font(Font.FontFamily.UNDEFINED, 14f)));
        dateCell2.setFixedHeight(30f);
        dateCell2.setBorder(Rectangle.NO_BORDER);
        dateCell2.setBorder(Rectangle.RIGHT);

        PdfPCell dateCell3 = new PdfPCell(new Paragraph("Dátum dodania", new Font(Font.FontFamily.UNDEFINED, 8f)));
        dateCell3.setBorder(Rectangle.NO_BORDER);
        dateCell3.setFixedHeight(30f);

        PdfPCell dateCell4 = new PdfPCell(new Paragraph(invoice.getDueDate().toString(), new Font(Font.FontFamily.UNDEFINED, 14f)));
        dateCell4.setFixedHeight(30f);
        dateCell4.setBorder(Rectangle.NO_BORDER);

        PdfPCell dateCell5 = new PdfPCell(new Paragraph("Dátum splatnosti", new Font(Font.FontFamily.UNDEFINED, 8f)));
        dateCell5.setBorder(Rectangle.NO_BORDER);
        dateCell5.setBorder(Rectangle.LEFT);
        dateCell5.setFixedHeight(30f);

        PdfPCell dateCell6 = new PdfPCell(new Paragraph(invoice.getMonth().toString(), new Font(Font.FontFamily.UNDEFINED, 14f)));
        dateCell6.setFixedHeight(30f);
        dateCell6.setBorder(Rectangle.NO_BORDER);

        dateTable.addCell(dateCell1);
        dateTable.addCell(dateCell2);
        dateTable.addCell(dateCell3);
        dateTable.addCell(dateCell4);
        dateTable.addCell(dateCell5);
        dateTable.addCell(dateCell6);

        PdfPTable serviceTable = new PdfPTable(7);
        serviceTable.setWidthPercentage(95f);
        serviceTable.setSpacingBefore(10f);
        serviceTable.setWidths(new float[] {7, 2, 1, 3, 4, 3, 4});

        PdfPCell serviceHeaderCell = new PdfPCell(new Paragraph("Popis položky", new Font(Font.FontFamily.UNDEFINED, 7f)));
        serviceHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        serviceHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        serviceHeaderCell.setFixedHeight(15f);
        PdfPCell quantityHeaderCell = new PdfPCell(new Paragraph("Množstvo", new Font(Font.FontFamily.UNDEFINED, 7f)));
        quantityHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        quantityHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        quantityHeaderCell.setFixedHeight(15f);
        PdfPCell mjHeaderCell = new PdfPCell(new Paragraph("MJ", new Font(Font.FontFamily.UNDEFINED, 7f)));
        mjHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        mjHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        mjHeaderCell.setFixedHeight(15f);
        PdfPCell pricePerUnitHeaderCell = new PdfPCell(new Paragraph("Cena za MJ", new Font(Font.FontFamily.UNDEFINED, 7f)));
        pricePerUnitHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pricePerUnitHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pricePerUnitHeaderCell.setFixedHeight(15f);
        PdfPCell sumWithoutVatHeaderCell = new PdfPCell(new Paragraph("Cena bez DPH", new Font(Font.FontFamily.UNDEFINED, 7f)));
        sumWithoutVatHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        sumWithoutVatHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        sumWithoutVatHeaderCell.setFixedHeight(15f);
        PdfPCell vatHeaderCell = new PdfPCell(new Paragraph("DPH", new Font(Font.FontFamily.UNDEFINED, 7f)));
        vatHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        vatHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        vatHeaderCell.setFixedHeight(15f);
        PdfPCell sumWithVatHeaderCell = new PdfPCell(new Paragraph("Cena s DPH", new Font(Font.FontFamily.UNDEFINED, 7f)));
        sumWithVatHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        sumWithVatHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        sumWithVatHeaderCell.setFixedHeight(15f);

        PdfPCell serviceCell = new PdfPCell(new Paragraph("invoice.getService()", new Font(Font.FontFamily.UNDEFINED, 7f)));
        serviceCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        serviceCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        serviceCell.setFixedHeight(15f);

        serviceTable.addCell(serviceHeaderCell);
        serviceTable.addCell(quantityHeaderCell);
        serviceTable.addCell(mjHeaderCell);
        serviceTable.addCell(pricePerUnitHeaderCell);
        serviceTable.addCell(sumWithoutVatHeaderCell);
        serviceTable.addCell(vatHeaderCell);
        serviceTable.addCell(sumWithVatHeaderCell);
        serviceTable.addCell(serviceCell);


        mainCell.addElement(headerTable);
        mainCell.addElement(infoTable);
        mainCell.addElement(dateTable);
        mainCell.addElement(serviceTable);
        mainFrame.addCell(mainCell);

        document.add(mainFrame);
        document.close();
        pdfWriter.close();
        return document;
    }
}
