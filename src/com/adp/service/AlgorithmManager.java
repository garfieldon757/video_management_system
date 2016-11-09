package com.adp.service;

import java.util.List;

import com.adp.model.Algorithm;

public interface AlgorithmManager {

	public List<Algorithm> getAlgorithmsByAlgorithmCategoryID(int algorithmCategoryID);
}
