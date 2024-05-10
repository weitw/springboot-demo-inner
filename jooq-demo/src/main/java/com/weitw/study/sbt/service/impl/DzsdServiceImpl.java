package com.weitw.study.sbt.service.impl;

import com.weitw.study.sbt.db.tables.pojos.WebPushDzsd;
import com.weitw.study.sbt.service.IDzsdService;
import com.weitw.study.sbt.service.dao.PushDzsdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DzsdServiceImpl implements IDzsdService {
    @Autowired
    private PushDzsdDao pushDzsdDao;

    @Override
    public List<WebPushDzsd> findDzsdList(Date start) {
        return pushDzsdDao.findDzsdList(start);
    }
}
