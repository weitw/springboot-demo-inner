package com.weitw.study.sbt.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UmsMenu {
    private Integer id;
    private Integer parent_id;
    private Integer level;
    private Integer sort;
    private Integer hidden;
    private String title;
    private String name;
    private String icon;
    private Date createTime;
}
