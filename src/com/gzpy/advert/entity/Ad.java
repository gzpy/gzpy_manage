package com.gzpy.advert.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity(name="gzpy_ad")
public class Ad implements Serializable {

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
	
	@Column(name = "adDescription")
	private String adDescription; 
	
	@Column(name = "adWidth")
	private Integer adWidth; 
	
	@Column(name = "adHeight")
	private Integer adHeight; 
	
	@Column(name = "imagePath")
	private String imagePath; 
	
	@Column(name = "adOrder")
	private Integer adOrder; 
	
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

	public String getAdDescription() {
		return adDescription;
	}

	public void setAdDescription(String adDescription) {
		this.adDescription = adDescription;
	}

	public Integer getAdWidth() {
		return adWidth;
	}

	public void setAdWidth(Integer adWidth) {
		this.adWidth = adWidth;
	}

	public Integer getAdHeight() {
		return adHeight;
	}

	public void setAdHeight(Integer adHeight) {
		this.adHeight = adHeight;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getAdOrder() {
		return adOrder;
	}

	public void setAdOrder(Integer adOrder) {
		this.adOrder = adOrder;
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
