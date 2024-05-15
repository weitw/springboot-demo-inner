package com.weitw.study.sbt.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author weitw
 * @since 2024-05-15
 */
@Mapper
public interface TMenuDao {

    String findById(Integer id);

}
