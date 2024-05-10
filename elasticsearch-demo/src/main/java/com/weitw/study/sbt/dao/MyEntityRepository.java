package com.weitw.study.sbt.dao;

import com.weitw.study.sbt.domain.MyEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
  
public interface MyEntityRepository extends ElasticsearchRepository<MyEntity, String> {
}