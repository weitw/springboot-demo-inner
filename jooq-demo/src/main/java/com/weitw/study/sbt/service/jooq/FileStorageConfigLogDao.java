package com.weitw.study.sbt.service.jooq;

import com.weitw.study.sbt.db.tables.daos.TFileStorageConfigLogDao;
import com.weitw.study.sbt.db.tables.pojos.TFileStorageConfigLog;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.weitw.study.sbt.db.Tables.T_FILE_STORAGE_CONFIG_LOG;


@Repository
public class FileStorageConfigLogDao extends TFileStorageConfigLogDao {
    @Autowired
    private DSLContext dslContext;

    @Autowired
    public FileStorageConfigLogDao(Configuration configuration) {
        super(configuration);
    }

    /**
     * 获取最新的一条记录
     * @return TFileStorageConfigLog
     */
    public TFileStorageConfigLog findByNewest() {
        // dp:server=1
        List<TFileStorageConfigLog> list = dslContext.selectFrom(T_FILE_STORAGE_CONFIG_LOG)
                .where(T_FILE_STORAGE_CONFIG_LOG.SERVER.eq(1))
                .orderBy(T_FILE_STORAGE_CONFIG_LOG.ADD_TIME.desc()).limit(1).fetchInto(TFileStorageConfigLog.class);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
