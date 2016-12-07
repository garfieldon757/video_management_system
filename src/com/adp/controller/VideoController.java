package com.adp.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adp.dao.UserDAO;
import com.adp.model.AuthorizationRoleRelation;
import com.adp.model.Video;
import com.adp.model.VideoCategory;
import com.adp.service.DownloadManager;
import com.adp.service.UserManager;
import com.adp.service.VideoManager;

@Controller
public class VideoController {
	
	@Autowired(required=true)
	UserManager um;
	
	@Autowired(required=true)
	VideoManager vm;
	
	@Autowired(required=true)
	DownloadManager dm;
	
	@Autowired(required=true)
	private UserDAO userDAO;
	
	@RequestMapping(value="parse_json")
	public void parse_json() throws IOException{
		
		vm.jsonToDB();
		return ;
	}
	
	@RequestMapping(value="videoSearchInit")
	public ModelAndView videoSearchInit(@RequestParam("videoCategoryID") int videoCategoryID , @RequestParam("page") int page , HttpServletRequest request){
		ModelAndView mv = new ModelAndView("VideoSearch");
		List<VideoCategory> videoCategoryList = vm.getVideoCategoryList();
		List<Video> videoList = vm.getVideoListByVideoCategroyIDAndPage(videoCategoryID , page);
		int videoListSize = vm.getVideoListSizeByVideoCategoryID(videoCategoryID);
		if(videoCategoryList != null && videoList != null){
			mv.addObject("videoCategoryList" , videoCategoryList);//传给视频分类栏使用
			mv.addObject("videoList" , videoList);//传16个视频对象给16个视频区域使用
			mv.addObject("videoListSize", videoListSize);//传给分页组件使用
			mv.addObject("videoCategoryID", videoCategoryID);
			mv.addObject("page", page);
		}
		
		userDAO.fuck(1, "sfasfdfsf");
//		userDAO.getRole(1);
		
		return mv;
	}
	
	@RequestMapping(value = "paramTest")
	public void paramTest(@RequestParam("param") String param){
		System.out.println("param get :" + param);
	}
	
	@RequestMapping(value="videoPlay")
	public ModelAndView videoPlay(@RequestParam("videoID") int videoID , HttpServletRequest request){
		ModelAndView mv = new ModelAndView("VideoPlayer");
		Video video = vm.getVideoByVideoID(videoID);
		if(video != null){
			mv.addObject("video", video);
		}
		
		return mv;
	}
	
	@RequestMapping("pathTest")
	public String pathTest(HttpServletRequest request){
		String path = request.getServletContext().getRealPath("");//获取项目动态绝对路径 
		String filePath = path + "\\WebContent\\ImageProcess\\test";
		
		File file = new File( filePath );
		file.mkdirs();
		
		System.out.println(filePath);
		return "login_page";
	}
	
	@RequestMapping("downloadVideo")
	@ResponseBody
	public String downloadVideo(String urlStr , String userName) throws IOException{
		
		urlStr = URLDecoder.decode(urlStr, "utf-8");
		userName = URLDecoder.decode(userName, "utf-8");
		String fileName = urlStr.substring(urlStr.lastIndexOf("/") + 1 , urlStr.length() );
		String savePath = "E:/workspace2/ADP/WebContent/ImageProcess/" + userName;
		dm.downLoadFromUrl(urlStr, fileName, savePath);
		
		return "Video downloading success.";
	}

}
