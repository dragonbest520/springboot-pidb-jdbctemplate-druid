package com.db520.web;

import com.db520.entity.PiData;
import com.db520.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class BaseController {
    @Autowired
    BaseService baseService;

    @GetMapping("/findSnapByTag")
    public String findSnapByTag(String tagName) {
        return baseService.findSnapByTag(tagName);
    }

    @GetMapping("/findSnapListByTag")
    public List<PiData> findSnapListByTag(String tagName) {
        return baseService.findSnapListByTag(tagName);
    }

    @GetMapping("/findHisDataByTagAndTimePerMinute")
    public String findHisDataByTagAndTimePerMinute(String tagName, String beginTime, String endTime) {
        return baseService.findHisDataByTagAndTimePerMinute(tagName, beginTime, endTime);
    }

    @GetMapping("/findHisDataListByTagAndTimePerMinute")
    public List<PiData> findHisDataListByTagAndTimePerMinute(String tagName, String beginTime, String endTime) {
        return baseService.findHisDataListByTagAndTimePerMinute(tagName, beginTime, endTime);
    }

    @GetMapping("/test")
    public List<PiData> test() {
        List<PiData> piDataList = new ArrayList<PiData>();
        Date dateTemp = new java.util.Date(new java.sql.Date(5345345).getTime());
        PiData piData = new PiData("test", dateTemp, "998.8", "0");
        piDataList.add(piData);
        return  piDataList;
    }

}
