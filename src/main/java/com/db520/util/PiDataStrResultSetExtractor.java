package com.db520.util;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PiDataStrResultSetExtractor implements ResultSetExtractor {

    @Override
    public String extractData(ResultSet rs) throws SQLException, DataAccessException {
        String resultStr = "";
        while (rs.next()) {
            resultStr += rs.getString("tag") + "--";
            resultStr += new Date(rs.getDate("time").getTime()) + "--";
            resultStr += rs.getString("value") + "--";
            resultStr += rs.getString("status") + ";";
        }
        return resultStr;
    }
}
