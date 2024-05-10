package com.weitw.study.sbt.dao;

import com.weitw.study.sbt.domain.UmsMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UmsMenuDao {
    UmsMenu findById(Integer id);
}
