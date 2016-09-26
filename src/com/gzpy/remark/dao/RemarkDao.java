package com.gzpy.remark.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.gzpy.remark.entity.*;

public interface RemarkDao extends  JpaSpecificationExecutor<Remark>,JpaRepository<Remark, String>{
	public Remark updateRemark(Remark remark);
}
