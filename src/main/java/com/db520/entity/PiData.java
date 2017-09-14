package com.db520.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PiData {

    private String tag;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:MI:SS")
    private Date time;
    private String value;
    private String status;

    public PiData() {

    }
    public PiData(String tag, Date time, String value, String status) {
        this.tag = tag;
        this.time = time;
        this.value = value;
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
