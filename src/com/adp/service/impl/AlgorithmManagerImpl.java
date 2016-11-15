package com.adp.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
	
	@Override
	public void frameExtract(String sourceVideoLink , String destFolderLink) throws IOException, InterruptedException
	{
			
		System.out.println("start");  
//		String sourceVideoLink1 = URLDecoder.decode(sourceVideoLink, "utf-8");
//		String destFolderLink1 = URLDecoder.decode(destFolderLink, "utf-8");
		Process pr = Runtime.getRuntime().exec("python C:/Users/oliverfan/PycharmProjects/opencv/frameExtract.py " + sourceVideoLink + destFolderLink);
		
		BufferedReader in = new BufferedReader(new  
		InputStreamReader(pr.getInputStream()));  
		String line;  
		
		while ((line = in.readLine()) != null) {  
		    System.out.println(line);  
		  }  
		in.close();  
		pr.waitFor();  
		System.out.println("end");

	}
	
}
