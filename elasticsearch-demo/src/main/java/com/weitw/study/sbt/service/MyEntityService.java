package com.weitw.study.sbt.service;

import com.weitw.study.sbt.dao.MyEntityRepository;
import com.weitw.study.sbt.domain.MyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  
  
@Service
public class MyEntityService {
    @Autowired
    private MyEntityRepository repository;

    public MyEntity saveEntity(MyEntity entity) {
        return repository.save(entity);
    }

    // 其他服务方法...  
}