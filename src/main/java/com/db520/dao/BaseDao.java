package com.db520.dao;

import com.db520.entity.PiData;
import com.db520.util.PiDataResultSetExtractor;
import com.db520.util.PiDataStrResultSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BaseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    String piSnapShotSqlStr = "SELECT tag, time, value, status FROM pisnapshot ";
    String piCompSqlStr = "SELECT tag, time, value, status FROM picomp ";

    /**
     * Find Snapshot values string by tag name
     * @param tagName
     * @return
     */
    @Transactional(readOnly = true)
    public String findSnapByTag(String tagName) {
        if(tagName.equals("")) {
            return "null";
        }
        String[] tagNameList = tagName.split("\\-");
        String subSqlStr = "";
        int size = tagNameList.length;
        Object[] args = new Object[size];
        for (int i = 0; i < size; i++) {
            args[i] = tagNameList[i];
            if(i == 0) {
                subSqlStr += "where tag like ?";
            } else {
                subSqlStr += "or tag like ?";
            }
        }
        String sqlStr = piSnapShotSqlStr + subSqlStr;
        return (String) jdbcTemplate.query(sqlStr, args, new PiDataStrResultSetExtractor());
    }

    /**
     * Find Snapshot values string by tag name
     * @param tagName
     * @return
     */
    @Transactional(readOnly = true)
    public List<PiData> findSnapListByTag(String tagName) {
        if(tagName.equals("")) {
            return new ArrayList<PiData>();
        }
        String[] tagNameList = tagName.split("\\-");
        String subSqlStr = "";
        int size = tagNameList.length;
        Object[] args = new Object[size];
        for (int i = 0; i < size; i++) {
            args[i] = tagNameList[i];
            if(i == 0) {
                subSqlStr += "where tag like ?";
            } else {
                subSqlStr += "or tag like ?";
            }
        }
        String sqlStr = piSnapShotSqlStr + subSqlStr;
        return (List<PiData>) jdbcTemplate.query(sqlStr, args, new PiDataResultSetExtractor());
    }

    /**
     * Find historical values string by tag name, begin time, end time. The timestep is one minute.
     * @param tagName
     * @param beginTime, string format is yyyy-MM-dd HH:mm:ss
     * @param endTime, string format is yyyy-MM-dd HH:mm:ss
     * @return
     */
    @Transactional(readOnly = true)
    public String findHisDataByTagAndTimePerMinute(String tagName, String beginTime, String endTime) {
        if(tagName.equals("")) {
            return "null";
        }
        String[] tagNameList = tagName.split("\\-");
        String subSqlStr = "";
        int size = tagNameList.length;
        Object[] args = new Object[size * 2 + 2];
        args[0] = beginTime;
        args[1] = endTime;
        for (int i = 0; i < size; i++) {
            args[i + 2] = tagNameList[i];
            args[i + 2 + size] = tagNameList[i];
            if(i == 0) {
                subSqlStr += "(tag like ?";
            } else {
                subSqlStr += "or tag like ?";
            }
        }
        subSqlStr += ") ";
        String subSqlTime = " where time in "  +
                "(select min(time) from picomp where time >= ? and time < ? and " +
                subSqlStr +
                " group by YEAR(time), MONTH(time), DAY(time), HOUR(time), MINUTE(time) " +
                "order by min(time)) and " +
                subSqlStr;
        String sqlStr = piCompSqlStr + subSqlTime;
        return (String) jdbcTemplate.query(sqlStr, args, new PiDataStrResultSetExtractor());
    }

    /**
     * Find historical values string by tag name, begin time, end time. The timestep is one minute.
     * @param tagName
     * @param beginTime, string format is yyyy-MM-dd HH:mm:ss
     * @param endTime, string format is yyyy-MM-dd HH:mm:ss
     * @return
     */
    @Transactional(readOnly = true)
    public List<PiData> findHisDataListByTagAndTimePerMinute(String tagName, String beginTime, String endTime) {
        if(tagName.equals("")) {
            return new ArrayList<PiData>();
        }

        String subSqlTime = " where time in"  +
                " (select min(time) from picomp where time >= ? and time < ? and " +
                " tag like ? " +
                " group by YEAR(time), MONTH(time), DAY(time), HOUR(time), MINUTE(time) " +
                "order by min(time)) and " +
                " tag like ? ";
        String sqlStr = piCompSqlStr + subSqlTime;
        //Object[] args = {beginTime, endTime, tagName, tagName};
        String[] tagNameList = tagName.split("\\-");
        String resultSqlStr = "";
        int size = tagNameList.length;
        List<PiData> piDataList = new ArrayList<PiData>();
        for (int i = 0; i < size; i++) {
            Object[] args = {beginTime, endTime, tagNameList[i], tagNameList[i]};
            List<PiData> piDataListTemp = (List<PiData>) jdbcTemplate.query(sqlStr, args, new PiDataResultSetExtractor());
            piDataList.addAll(piDataListTemp);
        }

        /*String[] tagNameList = tagName.split("\\-");
        String resultSqlStr = "";
        int size = tagNameList.length;
        Object[] args = new Object[size * 4];
        for (int i = 0; i < size; i++) {
            args[i * 4] = beginTime;
            args[i * 4 + 1] = endTime;
            args[i * 4 + 2] = tagNameList[i];
            args[i * 4 + 3] = tagNameList[i];
            if(i == 0) {
                resultSqlStr += sqlStr;
            } else {
                resultSqlStr += " UNION " + sqlStr;
            }
        }*/
        return piDataList;
    }
}
