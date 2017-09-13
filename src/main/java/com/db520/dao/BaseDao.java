package com.db520.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BaseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public String findSnapByTagName(String tagName) {
        List<Object> params = new ArrayList<Object>();
        Object[] args = new Object[1];
        args[0] = tagName;
        return (String) jdbcTemplate.query("SELECT tag, value FROM pisnapshot WHERE tag like ?", args, new ResultSetExtractor() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                String resultStr = "";
                while (rs.next()) {
                    resultStr += rs.getString("tag") + "--";
                    resultStr += rs.getString("value") + "";
                }
                return resultStr;
            }
        });
    }
}
