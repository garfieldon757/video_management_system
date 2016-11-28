package com.adp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity//声明当前类为hibernate映射到数据库中的实体�?
@Table(name = "ResourceType")
public class ResourceType {

	@Id//声明此列为主�?
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int resourceTypeID;
	
	private String resourceTypeName;
	
	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	@OneToMany(mappedBy = "resourceType", cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	private List<Resource> resourceList= new ArrayList<Resource>();

	public int getResourceTypeID() {
		return resourceTypeID;
	}

	public void setResourceTypeID(int resourceTypeID) {
		this.resourceTypeID = resourceTypeID;
	}

	public String getResourceTypeName() {
		return resourceTypeName;
	}

	public void setResourceTypeName(String resourceTypeName) {
		this.resourceTypeName = resourceTypeName;
	}

	
	
	
	
}
