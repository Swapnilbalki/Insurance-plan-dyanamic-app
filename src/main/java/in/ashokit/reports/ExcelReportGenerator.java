package in.ashokit.reports;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import in.ashokit.binding.response.PlanResponse;

public class ExcelReportGenerator {

	public void report(List<PlanResponse> plan , HttpServletResponse response) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();

		XSSFRow headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("PLAN_ID");
		headerRow.createCell(1).setCellValue("PLAN_NAME");
		headerRow.createCell(2).setCellValue("PLAN_HOLDER_NAME");
		headerRow.createCell(3).setCellValue("PLAN_HOLDER_SSN");
		headerRow.createCell(4).setCellValue("PLAN_STATUS");

		for (int i = 0; i < plan.size(); i++) {
			PlanResponse plans = plan.get(i);
			XSSFRow dataRow = sheet.createRow(i + 1);

			dataRow.createCell(0).setCellValue(plans.getPlanId());
			dataRow.createCell(1).setCellValue(plans.getPlanName());
			dataRow.createCell(2).setCellValue(plans.getPlanHolderName());
			dataRow.createCell(3).setCellValue(plans.getPlanHolderSsn());
			dataRow.createCell(4).setCellValue(plans.getPlanStatus());
		}

		ServletOutputStream stream = response.getOutputStream();
		workbook.write(stream);
		workbook.close();
		stream.close();
	}
}
