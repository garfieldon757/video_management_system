package com.adp.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.adp.service.DownloadManager;

@Service("dm")
public class DownloadManagerImpl implements DownloadManager{

	/** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }   
	
	/** 
     * 从网络Url中下载文件 
     * @param urlStr 
     * @param fileName 
     * @param savePath 
     * @throws IOException 
     */  
    public void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{  
	        URL url = new URL(urlStr);    
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
	                //设置超时间为3秒  
	        conn.setConnectTimeout(3*1000);  
	        //防止屏蔽程序抓取而返回403错误  
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
	  
	        //得到输入流  
	        InputStream inputStream = conn.getInputStream();    
	        //获取自己数组  
	        byte[] getData = readInputStream(inputStream);      
	        
	        fileName = URLDecoder.decode(fileName, "utf-8");
	        int fileName_hashCode = fileName.substring( 0 , fileName.indexOf(".") ).hashCode();
			fileName = fileName_hashCode + fileName.substring( fileName.indexOf(".") , fileName.length() );//解码中文视频名称，并将视频名称hashCode进行文件夹结构的命名
			
			//文件保存位置  
			savePath = savePath + "/" + fileName_hashCode;
	        File saveDir = new File(savePath);  
	        if(!saveDir.exists()){  
	            saveDir.mkdir();  
	        }  
			
	        File file = new File(saveDir+File.separator+fileName);      
	        FileOutputStream fos = new FileOutputStream(file);       
	        fos.write(getData);   
	        if(fos!=null){  
	            fos.close();    
	        }  
	        if(inputStream!=null){  
	            inputStream.close();  
	        }  
	  
	  
	        System.out.println("info:"+url+" download success");   
	  
	    }  
  
    @Test
    public void downloadTest() throws IOException{
    	 downLoadFromUrl("http://101.95.48.97:8005/res/upload/interface/apptutorials/manualstypeico/6f83ce8f-0da5-49b3-bac8-fd5fc67d2725.png",  
                 						"百度.jpg",
                 						"E:/workspace2/ADP/WebContent/ImageProcess/fyn");  
    }
	
}
