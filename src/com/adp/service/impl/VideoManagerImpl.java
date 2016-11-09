package com.adp.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adp.dao.UserDAO;
import com.adp.dao.VideoDAO;
import com.adp.model.Algorithm;
import com.adp.model.User;
import com.adp.model.Video;
import com.adp.model.VideoCategory;
import com.adp.service.VideoManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service("vm")
public class VideoManagerImpl implements VideoManager {
	
	@Autowired(required=true)
	private UserDAO userDAO;
	
	@Autowired(required=true)
	private VideoDAO videoDAO;
	

	public String jsonToStr(String fileUrl) throws IOException{
		
		File file = new File(fileUrl);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String s ="";
		while( (s = br.readLine()) != null) {
		sb.append(s + "\n");
		}
		
		 System.out.println(sb);
		return sb.toString();
	}
	
	public void sampleDataToDB(String resultStr){
		
		JSONObject jsonObject = JSONObject.fromObject(resultStr);
		 String sortName = jsonObject.getString("所属分类");//视频资源集合
		 JSONArray videoJsonData = jsonObject.getJSONArray("视频资源集合");
		 for(int i=0; i<videoJsonData.size(); i++){
			 
			 JSONObject videoObj = videoJsonData.getJSONObject(i);
			 int isDownloadable = (int) videoObj.get("可否下载");
			 String language = (String) videoObj.get("语种");
			 String brand = (String) videoObj.get("品牌名称");
			 String publishYear = (String) videoObj.get("广告年份");
			 String videoSourceLink = (String) videoObj.get("广告视频资源链接");
			 String country = (String) videoObj.get("国家或地区");
			 String videoName = (String) videoObj.get("视频名称");
			 String videoCoverLink = (String) videoObj.get("广告视频封面图片链接");
			 
			 Video video = new Video();
			 video.setBrand(brand);
			 video.setCountry(country);
			 video.setIsDownloadable(isDownloadable);
			 video.setLanguage(language);
			 video.setPublishYear(publishYear);
			 video.setVideoSourceLink(videoSourceLink);
			 video.setVideoName(videoName);
			 video.setVideoCoverLink(videoCoverLink);
			 VideoCategory videoCategory = userDAO.getVideoCategoryByVideoCategoryName(sortName);
			 video.setVideoCategory(videoCategory);
			 User user = userDAO.getUserByEmail("472161613@qq.com");//默认初始的视频关联用户是管理员
			 video.setUser(user);
			 
			userDAO.insertVideo(video);
			 
		 }
	}
	
	public void AllDataToDB(String resultStr){
		
		JSONArray jsonArray = new JSONArray().fromObject(resultStr);
		int size = jsonArray.size();
		for(int i =0 ;i <size; i++){
			JSONObject sortObject = jsonArray.getJSONObject(i);
		
			 String sortName = sortObject.getString("所属分类");//视频资源集合
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 System.out.println("*********************************现在所属分类为：" + sortName + "(序号："+ i +")**************" );
			 System.out.println("*********************************当前时间：" + df.format(new Date()) );
			 JSONArray videoJsonData = sortObject.getJSONArray("视频资源集合");
			 System.out.println("*********************************当前分类共有资源总数目：" +  videoJsonData.size() );
			 for(int j=0; j<videoJsonData.size(); j++){
				 
				 System.out.println("*********************************当前存储视频序号为：" +  j );
				try{
					JSONObject videoObj = videoJsonData.getJSONObject(j);
					 int isDownloadable = videoObj.getInt("可否下载");
					 String language = videoObj.getString("语种");
					 String brand =  videoObj.getString("品牌名称");
					 String publishYear = videoObj.getString("广告年份");
					 String videoSourceLink = videoObj.getString("广告视频资源链接");
					 String country = videoObj.getString("国家或地区");
					 String videoName = videoObj.getString("视频名称");
					 String videoCoverLink = videoObj.getString("广告视频封面图片链接");
					 
					 Video video = new Video();
					 video.setBrand(brand);
					 video.setCountry(country);
					 video.setIsDownloadable(isDownloadable);
					 video.setLanguage(language);
					 video.setPublishYear(publishYear);
					 video.setVideoSourceLink(videoSourceLink);
					 video.setVideoName(videoName);
					 video.setVideoCoverLink(videoCoverLink);
					 VideoCategory videoCategory = userDAO.getVideoCategoryByVideoCategoryName(sortName);
					 video.setVideoCategory(videoCategory);
					 User user = userDAO.getUserByEmail("472161613@qq.com");//默认初始的视频关联用户是管理员
					 video.setUser(user);
					 
					userDAO.insertVideo(video);
				}catch(Exception e){
					System.out.println("********************************Exception***********************************");
					continue;
				}
				 
				 
			 }
			 
		}
	}
	
	@Test
	public void jsonToDB( ) throws IOException{
					
		String fileUrl = "C:\\Users\\oliverfan\\Desktop\\crawlerData\\adInfo.txt"; //test2
		String resultStr = jsonToStr(fileUrl);
		//sampleDataToDB(resultStr);
		AllDataToDB(resultStr);
		
	}

	@Override
	public List<VideoCategory> getVideoCategoryList() {
		List<VideoCategory> videoCategoryList = videoDAO.getVideoCategoryList();
		return videoCategoryList;
	}

	@Override
	public List<Video> getVideoListByVideoCategroyIDAndPage(int videoCategroyID , int page) {
		VideoCategory videoCategory = videoDAO.getVideoCategoryByVideoCategoryID(videoCategroyID);
		List<Video> videoList = videoDAO.getVideoListByVideoCategroyIDAndPage(videoCategory , page);
		return videoList;
	}

	@Override
	public int getVideoListSizeByVideoCategoryID(int videoCategoryID) {
		VideoCategory videoCategory = videoDAO.getVideoCategoryByVideoCategoryID(videoCategoryID);
		int videoListSize = videoDAO.getVideoListSizeByVideoCategory(videoCategory);
		return videoListSize;
	}

	@Override
	public Video getVideoByVideoID(int videoID) {
		Video video = videoDAO.getVideoByVideoID(videoID);
		return video;
	}

	
}
