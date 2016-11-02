package com.adp.service;

import java.io.IOException;

public interface VideoManager {
	
	public String jsonToStr(String fileUrl) throws IOException;
	public void sampleDataToDB(String resultStr);
	public void AllDataToDB(String resultStr);
	public void jsonToDB( ) throws IOException;
	
}
