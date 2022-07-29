package in.ashokit.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.request.SearchRequest;
import in.ashokit.binding.response.PlanResponse;
import in.ashokit.reports.ExcelReportGenerator;
import in.ashokit.reports.PdfReportGenerator;
import in.ashokit.service.InsurancePlanService;

@RestController
public class InsurancePlanRestController {

	@Autowired
	private InsurancePlanService service;
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception
	{
		response.setContentType("application/pdf");
		
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=plan.pdf";
		response.setHeader(headerKey, headerValue);
		
		List<PlanResponse> plan = service.searchPlan(null);
		PdfReportGenerator report= new PdfReportGenerator();
	    report.exportPdf(plan, response);
		
	}
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception
	{
		response.setContentType("application/octet-stream");
		DateFormat dateFormater=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormater.format(new Date());
		
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=plans_" +currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<PlanResponse> plan = service.searchPlan(null);
		ExcelReportGenerator excelReport = new ExcelReportGenerator();
		excelReport.report(plan, response);
		
	}
	
	@PostMapping("/plans")
	public ResponseEntity<List<PlanResponse>> getPlan(@RequestBody SearchRequest request)
	{
		List<PlanResponse> list = service.searchPlan(request);
		return new ResponseEntity<List<PlanResponse>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/planname")
	public ResponseEntity<List<String>> getUniquePlanName()
	{
		List<String> uniquePlanName = service.getUniquePlanName();
		return new ResponseEntity<List<String>>(uniquePlanName, HttpStatus.OK);	
	}
	
	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getUniquePlanStatus()
	{
		List<String> uniquePlanStatus = service.getUniquePlanStatus();
		return new ResponseEntity<List<String>>(uniquePlanStatus, HttpStatus.OK);
	}
}
