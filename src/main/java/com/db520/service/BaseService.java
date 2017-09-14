package com.db520.service;

import com.db520.dao.BaseDao;
import com.db520.entity.PiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseService {
    @Autowired
    BaseDao baseDao;

    public String findSnapByTag(String tagName) {
        return baseDao.findSnapByTag(tagName);
    }

    public List<PiData> findSnapListByTag(String tagName) {
        return baseDao.findSnapListByTag(tagName);
    }

    public String findHisDataByTagAndTimePerMinute(String tagName, String beginTime, String endTime) {
        return baseDao.findHisDataByTagAndTimePerMinute(tagName, beginTime, endTime);
    }

    public List<PiData> findHisDataListByTagAndTimePerMinute(String tagName, String beginTime, String endTime) {
        return baseDao.findHisDataListByTagAndTimePerMinute(tagName, beginTime, endTime);
    }
}
