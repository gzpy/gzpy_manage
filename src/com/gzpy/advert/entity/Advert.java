package com.gzpy.advert.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class Advert implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1186642442815418208L;
	
	@Id
	@Column(name = "id")
	private String id; 
	
	@Column(name = "adName")
	private String adName; 
	
	@Column(name = "adLink")
	private String adLink; 
	
	@Column(name = "adDescribtion")
	private String adDescribtion; 
	
	@Column(name = "adWidth")
	private int adWidth; 
	
	@Column(name = "adHeight")
	private int adHeight; 
	
	@Column(name = "imagePath")
	private String imagePath; 
	
	@Column(name = "order")
	private int order; 
	
	@Column(name = "delStatus")
	private String delStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getAdLink() {
		return adLink;
	}

	public void setAdLink(String adLink) {
		this.adLink = adLink;
	}

	public String getAdDescribtion() {
		return adDescribtion;
	}

	public void setAdDescribtion(String adDescribtion) {
		this.adDescribtion = adDescribtion;
	}

	public int getAdWidth() {
		return adWidth;
	}

	public void setAdWidth(int adWidth) {
		this.adWidth = adWidth;
	}

	public int getAdHeight() {
		return adHeight;
	}

	public void setAdHeight(int adHeight) {
		this.adHeight = adHeight;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
}
