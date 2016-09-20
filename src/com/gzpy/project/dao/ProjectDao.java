package com.gzpy.project.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.gzpy.project.entity.Project;


public interface ProjectDao extends  JpaSpecificationExecutor,JpaRepository<Project, Long>{

}
