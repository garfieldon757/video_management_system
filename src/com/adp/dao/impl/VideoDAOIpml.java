package com.adp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adp.dao.VideoDAO;
import com.adp.model.Video;
import com.adp.model.VideoCategory;

@Repository("vd")
@Transactional
public class VideoDAOIpml implements VideoDAO{

	@PersistenceContext(name="un")
	private EntityManager em ;
	
	@Override
	public List<VideoCategory> getVideoCategoryList() {
		
		String jpql = "select vc from VideoCategory vc";
		List<VideoCategory> videoCategoryList = em.createQuery(jpql).getResultList();
		return videoCategoryList;
		
	}
	
	@Override
	public VideoCategory getVideoCategoryByVideoCategoryID(String videoCategoryID) {
		String jpql = "select vc from VideoCategory vc where vc.videoCategoryID =:videoCategoryID";
		VideoCategory videoCategroy = (VideoCategory) em.createQuery(jpql).setParameter("videoCategoryID", videoCategoryID ).getResultList().get(0);
		return videoCategroy;
	}

	@Override
	public List<Video> getVideoListByVideoCategroyIDAndPage(VideoCategory videoCategory , Integer page) {
		String jpql = "select v from Video v where v.videoCategory =:videoCategory";
		List<Video> videoList = em.createQuery(jpql).setParameter("videoCategory", videoCategory).getResultList();
		videoList = videoList.subList( ( page - 1 ) * 16 , page * 16 -1 );//根据page参数选取16条video
		return videoList;
	}

	@Override
	public int getVideoListSizeByVideoCategoryID(String videoCategoryID) {
		String jpql = "select vc from VideoCategory vc where vc.videoCategoryID =:videoCategoryID";
		int videoListSize=em.createQuery(jpql).setParameter("videoCategoryID", videoCategoryID ).getResultList().size();
		return videoListSize;
	}

	

}
