package in.ashokit.reports;

import java.awt.Color;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.binding.response.PlanResponse;

public class PdfReportGenerator {

	public void exportPdf(List<PlanResponse> plan, HttpServletResponse response) throws Exception
	{
		Document document= new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.blue);
		
		Paragraph p= new Paragraph("List Of Plans", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
		
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f,1.5f,2.0f,3.0f,2.0f});
		table.setSpacingBefore(10);
		
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.blue);
		cell.setPadding(5);
		
		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font1.setColor(Color.white);
		
		cell.setPhrase(new Phrase("PLAN ID", font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("PLAN NAME", font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("HOLDER NAME", font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("SSS", font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("STATUS", font1));
		table.addCell(cell);
		
		for (PlanResponse plans : plan) {
			table.addCell(plans.getPlanId()+"");
			table.addCell(plans.getPlanName());
			table.addCell(plans.getPlanHolderName());
			table.addCell(plans.getPlanHolderSsn()+"");
			table.addCell(plans.getPlanStatus());	
		}
		
		document.add(table);
		document.close();
		
	}
}
