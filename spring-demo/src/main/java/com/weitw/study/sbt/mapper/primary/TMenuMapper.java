package com.weitw.study.sbt.mapper.primary;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author weitw
 * @since 2024-05-15
 */
@Mapper
public interface TMenuMapper {

    String findById(Integer id);

}
