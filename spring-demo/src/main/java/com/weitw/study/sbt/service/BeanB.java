package com.weitw.study.sbt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeanB {
//    @Autowired
    private BeanA beanA;

    public void setBeanA(BeanA beanA) {
        this.beanA = beanA;
    }

    public String print() {
        return "我是B";
    }

}
