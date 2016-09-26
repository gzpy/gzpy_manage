package com.gzpy.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="gzpy_projects")
public class Project {
	@Column
	@Id
	String projectId;
	@Column
	String projectTitle;
	@Column
	Date issueDate;
	@Column
	String introduction;
	@Column
	String imagePath;
	@Column
	String SEOTitle;
	@Column
	String SEOKeywords;
	@Column
	String SEODescription;
	@Column
    String userId;
    @Column
    String delStatus;
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
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
	public void setSEOTitle(String sEOTitle) {
		SEOTitle = sEOTitle;
	}
	public String getSEOKeywords() {
		return SEOKeywords;
	}
	public void setSEOKeywords(String sEOKeywords) {
		SEOKeywords = sEOKeywords;
	}
    public String getSEODescription() {
			return SEODescription;
		}
    public void setSEODescription(String sEODescription) {
			SEODescription = sEODescription;
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
