package com.db520.service;

import com.db520.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    @Autowired
    BaseDao baseDao;

    public String findSnapByTagName(String tagName) {
        return  baseDao.findSnapByTagName(tagName);
    }
}
