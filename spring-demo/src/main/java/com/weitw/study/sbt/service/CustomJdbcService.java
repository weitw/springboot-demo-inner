package com.weitw.study.sbt.service;

import cn.hutool.core.util.IdUtil;
import com.weitw.study.sbt.domain.UmsMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Service
@Slf4j
public class CustomJdbcService {

    @Qualifier("bmJdbcTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer findByUsername(String username) {
        String sql = "select id from ums_menu where name=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, username);
    }

    public String findById(Integer id) {
        String sql = "select name from ums_menu where id=?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }

    public Boolean exitsByName(String name) {
        String sql = "select id from ums_menu where name=?";
        try {
            Integer id = jdbcTemplate.queryForObject(sql, Integer.class, name);
            return id != null && id > 0;
        } catch (DataAccessException e) {
            log.info("执行报错：{}", e);
            return false;
        }
    }

    public Integer insert() {
        String sql = "INSERT INTO ums_menu\n" +
                "(parent_id, create_time, title, `level`, sort, name, icon, hidden)\n" +
                "VALUES(8, '2024-02-02 16:59:40', '自定义新增', 1, 0, 'returnReason', 'order-return-reason', 0);\n";
        return jdbcTemplate.update(sql);
    }

    public Integer insert2() {
        String sql = "INSERT INTO ums_menu\n" +
                "(parent_id, create_time, title, `level`, sort, name, icon, hidden)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?);\n";
        return jdbcTemplate.update(sql, 8, "2023-02-02 16:59:40", "自定义新增" + IdUtil.fastSimpleUUID(), 1, 0, "returnReason", "order-return-reason", 0);
    }

    public Integer insertAndReturnIdTest1() {
        String sql = "INSERT INTO ums_menu\n" +
                "(id, parent_id, create_time, title, `level`, sort, name, icon, hidden)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);\n";
        Object[] objects = new Object[]{
                null, 8, "2023-02-02 16:59:40", "自定义新增22222", 1, 0, "returnReason", "order-return-reason", 0
        };
        return insertAndReturn(sql, objects);
    }

    public Integer insertAndReturnIdTest2() {
        String sql = "INSERT INTO ums_menu\n" +
                "(parent_id, title)\n" +
                "VALUES(?, ?);\n";
        Object[] objects = new Object[]{
                8, "自定义新增22222"
        };
        return insertAndReturn(sql, objects);
    }

    public Integer insertAndReturn(String sql, Object[] params) {
        // 必须要有keyHolder
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // 改写如下
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            // 自增主键为null
            for (Object p : params){
                // 用Object可以添加null参数
                ps.setObject(i, p);
                i++;
            }
            return ps;
        }, keyHolder);
        // 返回主键id
        return keyHolder.getKey().intValue();
    }

    public void insertBatchTest() {
        List<String> fields = Arrays.asList("parent_id", "title", "name");
        List<List<Object>> valuesList = new ArrayList<>();
        for (int i = 0; i < fields.size(); i++) {
            List<Object> objectList = new ArrayList<>();
            objectList.add(9);
            objectList.add("批量新增" + IdUtil.fastSimpleUUID());
            objectList.add("批量新增name" + IdUtil.fastSimpleUUID());
            valuesList.add(objectList);
        }
        insertBatch("ums_menu", fields, valuesList);
        log.info("批量新增结束");
    }

    public void insertBatch(String tableName, List<String> fields, List<List<Object>> valuesList) {
        if (fields.isEmpty() || valuesList.isEmpty()) {
            log.info("批量新增数据失败，无字段或无数据,{},{}", fields, valuesList);
            return;
        }
        String sql = "INSERT INTO " + tableName + " (" + String.join(", ", fields) + ") VALUES (" +
                String.join(", ", Collections.nCopies(fields.size(), "?")) + ")";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                List<Object> values = valuesList.get(i);
                for (int j = 0; j < values.size(); j++) {
                    ps.setObject(j + 1, values.get(j));
                }
            }
            @Override
            public int getBatchSize() {
                return valuesList.size();
            }
        });
    }

//    public UmsMenu findUmsMenuById(Integer id) {
//        String sql = "select * from ums_menu where id=?";
//        return jdbcTemplate.queryForObject(sql, UmsMenu.class, id);
//    }
}
