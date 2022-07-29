package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.request.SearchRequest;
import in.ashokit.binding.response.PlanResponse;


public interface InsurancePlanService {

	public List<PlanResponse> searchPlan(SearchRequest requset);
	
	public List<String> getUniquePlanName();
	
	public List<String> getUniquePlanStatus();
	
}
