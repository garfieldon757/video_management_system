package com.adp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class PythonMethod {

	@Test
	public void frameExtract() throws IOException, InterruptedException
	{
			
		System.out.println("start");  
	//	Process pr = Runtime.getRuntime().exec("ffmpeg -i C:/Users/oliverfan/PycharmProjects/opencv/video/xxx.flv -vf select='eq(pict_type\\,I)' -vsync 2 -s 1600x900 -f image2 thumbnails-%02d.jpeg");
//		Process pr = Runtime.getRuntime().exec("python C:/Users/oliverfan/PycharmProjects/opencv/change.py C:/Users/oliverfan/PycharmProjects/opencv/video/xxx.flv");
		Process pr = Runtime.getRuntime().exec("python C:/Users/oliverfan/PycharmProjects/opencv/test.py 'hello w orld' ");
		
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
