package com.weitw.study.sbt.service.impl;
 
import com.weitw.study.sbt.dao.UmsMenuDao;
import com.weitw.study.sbt.domain.UmsMenu;
import com.weitw.study.sbt.service.UmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsMenuServiceImpl implements UmsMenuService {
    @Autowired
    private UmsMenuDao umsMenuDao;

    public UmsMenu findById(Integer id) {
        UmsMenu menu = umsMenuDao.findById(id);
        System.out.println(menu);
        return menu;
    }
}