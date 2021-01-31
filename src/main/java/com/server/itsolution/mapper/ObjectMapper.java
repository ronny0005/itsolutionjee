package com.server.itsolution.mapper;

import org.json.JSONException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

public class ObjectMapper implements RowMapper<Object> {

    public static final String BASE_SQL //
            = "Select A.cbMarq, A.DE_No, DE_Intitule,DE_Complement FROM F_DEPOT A";

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            return resultSetToObject(rs);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
        //return new FDepot(deNo, intitule,cbMarq);
    }

    public Object resultSetToObject(ResultSet rs) throws SQLException, JSONException {

        ResultSetMetaData rsmd = rs.getMetaData();
        HashMap<String,Object> json = new HashMap<String,Object>();
            int numColumns = rsmd.getColumnCount();
            for (int i = 1; i < numColumns + 1; i++) {
                String column_name = rsmd.getColumnName(i);

                if (rsmd.getColumnType(i) == java.sql.Types.ARRAY) {
                    json.put(column_name, rs.getArray(column_name));
                } else if (rsmd.getColumnType(i) == java.sql.Types.BIGINT) {
                    json.put(column_name, rs.getInt(column_name));
                } else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
                    json.put(column_name, rs.getBoolean(column_name));
                } else if (rsmd.getColumnType(i) == java.sql.Types.BLOB) {
                    json.put(column_name, rs.getBlob(column_name));
                } else if (rsmd.getColumnType(i) == java.sql.Types.DOUBLE) {
                    json.put(column_name, rs.getDouble(column_name));
                } else if (rsmd.getColumnType(i) == java.sql.Types.FLOAT) {
                    json.put(column_name, rs.getFloat(column_name));
                } else if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
                    json.put(column_name, rs.getInt(column_name));
                } else if (rsmd.getColumnType(i) == java.sql.Types.NVARCHAR) {
                    json.put(column_name, rs.getNString(column_name));
                } else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
                    json.put(column_name, rs.getString(column_name));
                } else if (rsmd.getColumnType(i) == java.sql.Types.TINYINT) {
                    json.put(column_name, rs.getInt(column_name));
                } else if (rsmd.getColumnType(i) == java.sql.Types.SMALLINT) {
                    json.put(column_name, rs.getInt(column_name));
                } else if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
                    json.put(column_name, rs.getDate(column_name));
                } else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP) {
                    json.put(column_name, rs.getTimestamp(column_name));
                } else {
                    json.put(column_name, rs.getObject(column_name));
                }
            }

        return (json);
    }

}