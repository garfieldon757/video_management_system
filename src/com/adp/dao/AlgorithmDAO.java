package com.adp.dao;

import java.util.List;

import com.adp.model.Algorithm;
import com.adp.model.AlgorithmCategory;

public interface AlgorithmDAO {
	
	public AlgorithmCategory getAlgorithmCategoryByAlgorithmCategoryID(int AlgorithmCategoryID); 
	public List<Algorithm> getAlgorithmsByCategory(AlgorithmCategory algorithmCategory);
}
