package com.weitw.study.sbt.service.dao;

import com.weitw.study.sbt.db.tables.daos.WebPushDzsdDao;
import com.weitw.study.sbt.db.tables.pojos.WebPushDzsd;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static com.weitw.study.sbt.db.tables.WebPushDzsd.WEB_PUSH_DZSD;

@Repository
public class PushDzsdDao extends WebPushDzsdDao {
    @Autowired
    private DSLContext dslContext;

    public PushDzsdDao(Configuration configuration) {
        super(configuration);
    }

    public List<WebPushDzsd> findDzsdList(Date start) {
        return dslContext.selectFrom(WEB_PUSH_DZSD)
                .where(WEB_PUSH_DZSD.ADD_TIME.ge(start))
                .limit(3)
                .fetchInto(WebPushDzsd.class);
    }
}
