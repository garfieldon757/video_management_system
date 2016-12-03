package com.adp.service;

import java.io.IOException;
import java.util.List;

import com.adp.model.Algorithm;

public interface AlgorithmManager {

	public List<Algorithm> getAllAlgorithm();
	public List<Algorithm> getAlgorithmsByAlgorithmCategoryID(int algorithmCategoryID);
	public void frameExtract(String sourceVideoLink , String destFolderLink) throws IOException, InterruptedException;
}
