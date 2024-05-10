package com.weitw.study.sbt.service.jooq;

import org.jooq.Converter;

import java.util.Date;
import java.util.Objects;

public class SQLDate2DateConverter implements Converter<java.sql.Date, Date> {

    @Override
    public Date from(java.sql.Date databaseObject) {
        return Objects.isNull(databaseObject) ? null : new Date(databaseObject.getTime());
    }

    @Override
    public java.sql.Date to(Date userObject) {
        return Objects.isNull(userObject) ? null : new java.sql.Date(userObject.getTime());
    }

    @Override
    public Class<java.sql.Date> fromType() {
        return java.sql.Date.class;
    }

    @Override
    public Class<Date> toType() {
        return Date.class;
    }

}
