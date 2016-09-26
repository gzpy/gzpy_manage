package com.gzpy.remark.service;

import org.springframework.data.domain.Page;

import com.gzpy.remark.entity.Remark;

public interface RemarkService {
	public Page findRemark(int currentpage, int size);
	public Remark findRemarkById(String remarkId);
	public Remark updateRemark(Remark remark);
	public Page findRemarkBySearch(int currentpage, int size,String name,String status);
	public Page findRemarkByDelStatus(int currentpage,int size);
	public Remark delRemarkById(String remarkId);
}
