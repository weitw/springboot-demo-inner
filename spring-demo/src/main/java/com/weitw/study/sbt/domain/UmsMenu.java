package com.weitw.study.sbt.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UmsMenu {
    private Integer id;
    private Integer parent_id;
    private Date create_time;
    private String title;
    private Integer level;
    private Integer sort;
    private String name;
    private String icon;
    private Integer hidden;
}
