package com.adp.service;

import java.io.IOException;
import java.util.List;

import com.adp.model.Algorithm;
import com.adp.model.Video;
import com.adp.model.VideoCategory;

public interface VideoManager {
	
	public String jsonToStr(String fileUrl) throws IOException;
	public void sampleDataToDB(String resultStr);
	public void AllDataToDB(String resultStr);
	public void jsonToDB( ) throws IOException;
	public List<VideoCategory> getVideoCategoryList();
	public List<Video> getVideoListByVideoCategroyIDAndPage(int videoCategroyID , int page);
	public int getVideoListSizeByVideoCategoryID(int videoCategoryID);
	public Video getVideoByVideoID(int videoID);
	
}
