package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.binding.request.SearchRequest;
import in.ashokit.binding.response.PlanResponse;
import in.ashokit.entity.InsurancePlanEntity;
import in.ashokit.repository.InsurancePlanRepository;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService {

	@Autowired
	private InsurancePlanRepository repo;

	@Override
	public List<PlanResponse> searchPlan(SearchRequest request) {

		InsurancePlanEntity entity=new InsurancePlanEntity();
		if (request !=null && request.getPlanName() !=null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}
		if (request !=null && request.getPlanStatus() !=null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		Example<InsurancePlanEntity> of = Example.of(entity);
		List<InsurancePlanEntity> findAll = repo.findAll(of);
		List<PlanResponse> plans=new ArrayList<PlanResponse>();
		for (InsurancePlanEntity plan : findAll) {
			PlanResponse pr= new PlanResponse();
			BeanUtils.copyProperties(plan, pr);
			plans.add(pr);
		}

		return plans;
	}
	
	@Override
	public List<String> getUniquePlanName() {
		
		return repo.getPlanNames();
	}
	
	@Override
	public List<String> getUniquePlanStatus() {

		return repo.getPlanStatus();
	}
}
