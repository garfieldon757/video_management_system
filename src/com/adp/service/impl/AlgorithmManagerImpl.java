package com.adp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adp.dao.AlgorithmDAO;
import com.adp.model.Algorithm;
import com.adp.model.AlgorithmCategory;
import com.adp.service.AlgorithmManager;

@Service("am")
public class AlgorithmManagerImpl implements AlgorithmManager{

	@Autowired(required=true)
	private AlgorithmDAO algorithmDAO;
	
	@Override
	public List<Algorithm> getAlgorithmsByAlgorithmCategoryID(int algorithmCategoryID) {
		AlgorithmCategory algorithmCategory = algorithmDAO.getAlgorithmCategoryByAlgorithmCategoryID(algorithmCategoryID);
		List<Algorithm> algorithms = algorithmDAO.getAlgorithmsByCategory(algorithmCategory);
		return algorithms;
	}
	
	
	
}
