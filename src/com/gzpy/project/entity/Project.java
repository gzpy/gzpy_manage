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
	@GeneratedValue
	int projectId;
	@Column
	String projectTitle;
	@Column
	Date issueDate;
	@Column
	String introduction;
	@Column
	String imagePath;
	@Column
	String Isdelete;
	@Column
	int Uid;
	@Column
	String SEOTitle;
	@Column
	String SEOKeywords;
	@Column
	String SEODescribtion;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
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

	public String getIsdelete() {
		return Isdelete;
	}

	public void setIsdelete(String isdelete) {
		Isdelete = isdelete;
	}

	public int getUid() {
		return Uid;
	}

	public void setUid(int uid) {
		Uid = uid;
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

	public String getSEODescribtion() {
		return SEODescribtion;
	}

	public void setSEODescribtion(String sEODescribtion) {
		SEODescribtion = sEODescribtion;
	}

}
