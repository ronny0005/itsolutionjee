package com.server.itsolution.mapper;

import com.server.itsolution.entities.PPreferences;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PPreferencesMapper extends ObjectMapper {

    public static final String BASE_SQL
            =   " Select PR_StockNeg,PR_DelaiPreAlert " +
                " FROM P_PREFERENCES";

    public PPreferences mapRow(ResultSet rs, int rowNum) throws SQLException {
        PPreferences pPreferences = new PPreferences();
        pPreferences.setPR_StockNeg(rs.getInt("PR_StockNeg"));
        pPreferences.setPR_DelaiPreAlert(rs.getInt("PR_DelaiPreAlert"));
        return pPreferences;
    }
}