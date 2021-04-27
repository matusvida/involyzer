package sk.myproject.faktoorka.pdf.creator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Component;
import sk.myproject.faktoorka.entities.Invoice;
import sk.myproject.faktoorka.entities.Service;
import sk.myproject.faktoorka.errorhandling.InvoException;

import java.io.ByteArrayOutputStream;

@Component
public class InvoiceCreator {

	public byte[] createInvoicePdf(Invoice invoice) throws DocumentException, InvoException {
		Document document = new Document();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, byteArrayOutputStream);
		document.open();

		PdfPTable mainFrame = new PdfPTable(1);
		mainFrame.setWidthPercentage(100); //Width 100%

		PdfPCell mainCell = new PdfPCell(new Paragraph("MainCell"));
		mainCell.setBorderColor(BaseColor.BLACK);
		mainCell.setFixedHeight(750f);
		mainCell.setBorderWidth(2f);
		mainCell.setHorizontalAlignment(Element.ALIGN_TOP);
		mainCell.setVerticalAlignment(Element.ALIGN_LEFT);

		mainCell.addElement(createHeaderSection(invoice));
		mainCell.addElement(createInfoSection(invoice));
		mainCell.addElement(createDateSection(invoice));
		mainCell.addElement(createServiceHeaderSection());
		for (Service item : invoice.getServices()) {
			mainCell.addElement(createServiceSection(item));
		}
		mainCell.addElement(createTotalRow(invoice));

		mainFrame.addCell(mainCell);

		document.add(mainFrame);
		document.close();
		pdfWriter.close();
		return byteArrayOutputStream.toByteArray();
	}

	private PdfPTable createHeaderSection(Invoice invoice) {
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

		return headerTable;
	}

	private PdfPTable createInfoSection(Invoice invoice) {
		PdfPTable infoTable = new PdfPTable(2);
		infoTable.setWidthPercentage(100f); //Width 100%

		PdfPCell infoCell1 = new PdfPCell(new Paragraph("Cell 3"));
        infoCell1.setBorder(Rectangle.NO_BORDER);
		infoCell1.setFixedHeight(150f);

		PdfPCell infoCell2 = new PdfPCell(new Paragraph("Cell 4"));
		infoCell2.setPaddingRight(-5);
        infoCell2.setBorder(Rectangle.NO_BORDER);
		infoCell2.setFixedHeight(150f);

		infoTable.addCell(infoCell1);
		infoTable.addCell(infoCell2);

		return infoTable;
	}

	private PdfPTable createDateSection(Invoice invoice) {
		PdfPTable dateTable = new PdfPTable(6);
		dateTable.setWidthPercentage(101f);

		PdfPCell dateCell1 = new PdfPCell(new Paragraph("Dátum vystavenia", new Font(Font.FontFamily.UNDEFINED, 8f)));
		dateCell1.setFixedHeight(30f);
		dateCell1.disableBorderSide(Rectangle.RIGHT);

		PdfPCell dateCell2 = new PdfPCell(new Paragraph(invoice.getIssueDate().toString(), new Font(Font.FontFamily.UNDEFINED, 14f)));
		dateCell2.setFixedHeight(30f);
		dateCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		dateCell2.disableBorderSide(Rectangle.LEFT);

		PdfPCell dateCell3 = new PdfPCell(new Paragraph("Dátum dodania", new Font(Font.FontFamily.UNDEFINED, 8f)));
		dateCell3.setFixedHeight(30f);
		dateCell3.disableBorderSide(Rectangle.RIGHT);

		PdfPCell dateCell4 = new PdfPCell(new Paragraph(invoice.getDueDate().toString(), new Font(Font.FontFamily.UNDEFINED, 14f)));
		dateCell4.setFixedHeight(30f);
		dateCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		dateCell4.disableBorderSide(Rectangle.LEFT);

		PdfPCell dateCell5 = new PdfPCell(new Paragraph("Dátum splatnosti", new Font(Font.FontFamily.UNDEFINED, 8f)));
		dateCell5.setFixedHeight(30f);
		dateCell5.disableBorderSide(Rectangle.RIGHT);

		PdfPCell dateCell6 = new PdfPCell(new Paragraph(invoice.getMonth().toString(), new Font(Font.FontFamily.UNDEFINED, 14f)));
		dateCell6.setFixedHeight(30f);
		dateCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		dateCell6.disableBorderSide(Rectangle.LEFT);

		dateTable.addCell(dateCell1);
		dateTable.addCell(dateCell2);
		dateTable.addCell(dateCell3);
		dateTable.addCell(dateCell4);
		dateTable.addCell(dateCell5);
		dateTable.addCell(dateCell6);

		return dateTable;
	}

	private PdfPTable createServiceHeaderSection() throws DocumentException {
		PdfPTable serviceTable = new PdfPTable(7);
		serviceTable.setWidthPercentage(95f);
		serviceTable.setSpacingBefore(10f);
		serviceTable.setWidths(new float[]{9, 3, 2, 4, 5, 4, 5});

		PdfPCell serviceHeaderCell = new PdfPCell(new Paragraph("Popis položky", new Font(Font.FontFamily.UNDEFINED, 7f)));
		setServiceCell(serviceHeaderCell);

		PdfPCell quantityHeaderCell = new PdfPCell(new Paragraph("Množstvo", new Font(Font.FontFamily.UNDEFINED, 7f)));
		setServiceCell(quantityHeaderCell);

		PdfPCell mjHeaderCell = new PdfPCell(new Paragraph("MJ", new Font(Font.FontFamily.UNDEFINED, 7f)));
		setServiceCell(mjHeaderCell);

		PdfPCell pricePerUnitHeaderCell = new PdfPCell(new Paragraph("Cena za MJ", new Font(Font.FontFamily.UNDEFINED, 7f)));
		setServiceCell(pricePerUnitHeaderCell);

		PdfPCell sumWithoutVatHeaderCell = new PdfPCell(new Paragraph("Cena bez DPH", new Font(Font.FontFamily.UNDEFINED, 7f)));
		setServiceCell(sumWithoutVatHeaderCell);

		PdfPCell vatHeaderCell = new PdfPCell(new Paragraph("DPH", new Font(Font.FontFamily.UNDEFINED, 7f)));
		setServiceCell(vatHeaderCell);

		PdfPCell sumWithVatHeaderCell = new PdfPCell(new Paragraph("Cena s DPH", new Font(Font.FontFamily.UNDEFINED, 7f)));
		setServiceCell(sumWithVatHeaderCell);

		serviceTable.addCell(serviceHeaderCell);
		serviceTable.addCell(quantityHeaderCell);
		serviceTable.addCell(mjHeaderCell);
		serviceTable.addCell(pricePerUnitHeaderCell);
		serviceTable.addCell(sumWithoutVatHeaderCell);
		serviceTable.addCell(vatHeaderCell);
		serviceTable.addCell(sumWithVatHeaderCell);

		return serviceTable;
	}

	private PdfPTable createServiceSection(Service service) throws InvoException {
		float fontSize = 8f;
		PdfPTable serviceTable = new PdfPTable(7);
		serviceTable.setWidthPercentage(95f);

		try {
			serviceTable.setWidths(new float[]{9, 3, 2, 4, 5, 4, 5});
		} catch (DocumentException e) {
			throw new InvoException("spadlo to pri vytvoreni faktury"); //TODO
		}

		PdfPCell serviceHeaderCell = new PdfPCell(new Paragraph(service.getServiceName(), new Font(Font.FontFamily.UNDEFINED, fontSize)));
		setServiceCell(serviceHeaderCell);

		PdfPCell quantityHeaderCell = new PdfPCell(new Paragraph(service.getQuantity().toString(), new Font(Font.FontFamily.UNDEFINED, fontSize)));
		setServiceCell(quantityHeaderCell);

		PdfPCell mjHeaderCell = new PdfPCell(new Paragraph(service.getUnit(), new Font(Font.FontFamily.UNDEFINED, fontSize)));
		setServiceCell(mjHeaderCell);

		PdfPCell pricePerUnitHeaderCell = new PdfPCell(new Paragraph(service.getPricePerUnit().toPlainString(), new Font(Font.FontFamily.UNDEFINED, fontSize)));
		setServiceCell(pricePerUnitHeaderCell);

		PdfPCell sumWithoutVatHeaderCell = new PdfPCell(new Paragraph(service.getTotalExclVat().toPlainString(), new Font(Font.FontFamily.UNDEFINED, fontSize)));
		setServiceCell(sumWithoutVatHeaderCell);

		PdfPCell vatHeaderCell = new PdfPCell(new Paragraph(String.format("%s %s",service.getVat().toPlainString(), "%"), new Font(Font.FontFamily.UNDEFINED, fontSize)));
		setServiceCell(vatHeaderCell);

		PdfPCell sumWithVatHeaderCell = new PdfPCell(new Paragraph(service.getTotalWithVat().toPlainString(), new Font(Font.FontFamily.UNDEFINED, fontSize)));
		setServiceCell(sumWithVatHeaderCell);

		serviceTable.addCell(serviceHeaderCell);
		serviceTable.addCell(quantityHeaderCell);
		serviceTable.addCell(mjHeaderCell);
		serviceTable.addCell(pricePerUnitHeaderCell);
		serviceTable.addCell(sumWithoutVatHeaderCell);
		serviceTable.addCell(vatHeaderCell);
		serviceTable.addCell(sumWithVatHeaderCell);

		return serviceTable;
	}

	private PdfPTable createTotalRow(Invoice invoice) throws InvoException {
		float fontSize = 8f;
		PdfPTable serviceTable = new PdfPTable(4);
		serviceTable.setWidthPercentage(95f);
		try {
			serviceTable.setWidths(new float[]{18, 5, 4, 5});
		} catch (DocumentException e) {
			throw new InvoException("spadlo to pri vytvoreni faktury"); //TODO
		}
		PdfPCell serviceHeaderCell = new PdfPCell(new Paragraph("Spolu:", new Font(Font.FontFamily.UNDEFINED, fontSize)));
		serviceHeaderCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		serviceHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		serviceHeaderCell.setFixedHeight(15f);

		PdfPCell quantityHeaderCell = new PdfPCell(new Paragraph(invoice.getTotalExclVat().toPlainString(), new Font(Font.FontFamily.UNDEFINED, fontSize)));
		setServiceCell(quantityHeaderCell);

		PdfPCell mjHeaderCell = new PdfPCell(new Paragraph(invoice.getVatTotal().toPlainString(), new Font(Font.FontFamily.UNDEFINED, fontSize)));
		setServiceCell(mjHeaderCell);

		PdfPCell pricePerUnitHeaderCell = new PdfPCell(new Paragraph(invoice.getTotal().toPlainString(), new Font(Font.FontFamily.UNDEFINED, fontSize)));
		setServiceCell(pricePerUnitHeaderCell);

		serviceTable.addCell(serviceHeaderCell);
		serviceTable.addCell(quantityHeaderCell);
		serviceTable.addCell(mjHeaderCell);
		serviceTable.addCell(pricePerUnitHeaderCell);

		return serviceTable;
	}

	private void setServiceCell(PdfPCell cell) {
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setFixedHeight(15f);
	}
}
