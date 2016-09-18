package com.atguigu.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atguigu.crm.entity.Storage;

public interface StorageDao extends JpaRepository<Storage, Long>,JpaSpecificationExecutor<Storage>{

}
