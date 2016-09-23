package com.gzpy.news.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "gzpy_news")
public class News implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8511151818141954493L;

	@Id
	@Column(name = "newsId")
	private String newsId; // 文章ID
	
	@Column(name = "newsTitle")
	private String newsTitle; //文章标题
	
	@Column(name = "issueDate")
	private Date issueDate;//发布时间
	
	@Column(name = "newsContent")
	private String newsContent;//文章内容
	
	@Column(name = "imagePath")
	private String imagePath;//图片路径
	
	@Column(name = "SEOTitle")
	private String SEOTitle;//SEO标题
	
	@Column(name = "SEOKeywords")
	private String SEOKeywords;//SEO关键字
	
	@Column(name = "SEODescription")
	private String SEODescription;//SEO描述
	
	@Column(name = "typeId")
	private String typeId;//文章类型ID
	
	@Transient
	private String typeName; //文章类型

	@Column(name = "isStick")
	private String isStick;//是否置顶
	
	@Column(name = "userId")
	private String userId;//发布人ID
	
	@Column(name = "delStatus")
	private String delStatus;//删除状态

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
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

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getIsStick() {
		return isStick;
	}

	public void setIsStick(String isStick) {
		this.isStick = isStick;
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
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
