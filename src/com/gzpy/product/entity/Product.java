package com.gzpy.product.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gzpy_products")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6248899362699688980L;

	@Id
	@Column(name = "productId")
	private String productId; // 产品id

	@Column(name = "productTitle")
	private String productTitle;// 产品标题

	@Column(name = "issueDate")
	private Date issueDate; // 发布时间

	@Column(name = "introduction")
	private String introduction;// 详细介绍

	@Column(name = "imagePath")
	private String imagePath; // 图片路径
	
	@Column(name = "SEOTitle")
	private String SEOTitle; // SEO标题

	@Column(name = "SEOKeywords")
	private String SEOKeywords; // SEO关键字

	@Column(name = "SEODescription")
	private String SEODescription;// SEO描述

	@Column(name = "userId")
	private String userId; // 发布人

	@Column(name = "delStatus")
	private String delStatus; // 删除状态
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getSEOTitle() {
		return SEOTitle;
	}

	public void setSEOTitle(String SEOTitle) {
		this.SEOTitle = SEOTitle;
	}

	public String getSEOKeywords() {
		return SEOKeywords;
	}

	public void setSEOKeywords(String SEOKeywords) {
		this.SEOKeywords = SEOKeywords;
	}

	public String getSEODescription() {
		return SEODescription;
	}

	public void setSEODescription(String SEODescription) {
		this.SEODescription = SEODescription;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}

}
