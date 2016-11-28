package com.adp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity//声明当前类为hibernate映射到数据库中的实体�?
@Table(name = "Resource")
public class Resource {

	@Id//声明此列为主�?
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer resourceID;
	
	private String resourceURI;
	private String resourceDescription;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="resourceTypeID")
	private ResourceType resourceType;
	
	@OneToMany(mappedBy = "resource", cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	private List<Authorization> authList= new ArrayList<Authorization>();

	public Integer getResourceID() {
		return resourceID;
	}

	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	public String getResourceDescription() {
		return resourceDescription;
	}

	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public List<Authorization> getAuthList() {
		return authList;
	}

	public void setAuthList(List<Authorization> authList) {
		this.authList = authList;
	}

	
	
	
}
