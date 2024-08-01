package com.weitw.study.sbt.mapper.primary;

import com.weitw.study.sbt.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM product")
    List<Product> findAll();
}
