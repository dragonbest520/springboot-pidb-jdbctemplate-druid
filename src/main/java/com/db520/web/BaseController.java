package com.db520.web;

import com.db520.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @Autowired
    BaseService baseService;

    @GetMapping("/findSnapByTagName")
    public String findSnapByTagName(String tagName) {
        return  baseService.findSnapByTagName(tagName);
    }
}
