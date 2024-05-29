package com.weitw.study.sbt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeanA {
    private BeanB beanB;

    public BeanA(BeanB beanB) {
        this.beanB = beanB;
    }

    public String print() {
        return "我是A";
    }
}
