package com.db520.util;

import com.db520.entity.PiData;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PiDataResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<PiData> piDataListTemp = new ArrayList<PiData>();
        while (rs.next()) {
            PiData piData = new PiData(
                    rs.getString("tag"),
                    new Date(rs.getDate("time").getTime()),
                    rs.getString("value"),
                    rs.getString("status"));
            piDataListTemp.add(piData);
        }
        return piDataListTemp;
    }
}
