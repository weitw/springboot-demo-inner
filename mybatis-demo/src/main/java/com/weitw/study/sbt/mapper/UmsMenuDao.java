package com.weitw.study.sbt.mapper;

import com.weitw.study.sbt.domain.UmsMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UmsMenuDao {
    UmsMenu findById(Integer id);
}
