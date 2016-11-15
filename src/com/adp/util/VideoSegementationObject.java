package com.adp.util;

import java.util.ArrayList;

public class VideoSegementationObject {
	private String destFolderLink;
	private ArrayList<String> boundryList;
	

	public String getDestFolderLink() {
		return destFolderLink;
	}
	public void setDestFolderLink(String destFolderLink) {
		this.destFolderLink = destFolderLink;
	}
	public ArrayList<String> getBoundryList() {
		return boundryList;
	}
	public void setBoundryList(ArrayList<String> boundryList) {
		this.boundryList = boundryList;
	}
	
	
	
}
