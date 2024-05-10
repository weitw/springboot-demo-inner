package com.weitw.study.sbt.service;

import com.weitw.study.sbt.db.tables.pojos.WebPushDzsd;

import java.util.Date;
import java.util.List;

public interface IDzsdService {
    List<WebPushDzsd> findDzsdList(Date start);
}
