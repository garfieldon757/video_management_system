package com.adp.service;

import java.io.IOException;
import java.io.InputStream;

public interface DownloadManager {
	
	public  byte[] readInputStream(InputStream inputStream) throws IOException;
	 public void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException;
}
