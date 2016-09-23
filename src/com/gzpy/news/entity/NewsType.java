package com.gzpy.news.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gzpy_news_type")
public class NewsType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5227130079609911989L;

	@Id
	@Column(name = "typeId")
	private String typeId; //文章类型ID
	
	@Column(name = "typeName")
	private String typeName;//文章类型
	
	@Column(name = "delStatus")
	private String delStatus;//删除状态

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}
}
