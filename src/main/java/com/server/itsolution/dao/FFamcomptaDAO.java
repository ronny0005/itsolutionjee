package com.server.itsolution.dao;

import com.server.itsolution.entities.FArtStock;
import com.server.itsolution.entities.FFamcompta;
import com.server.itsolution.mapper.FArtStockMapper;
import com.server.itsolution.mapper.FFamcomptaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class FFamcomptaDAO extends JdbcDaoSupport {

    @Autowired
    public FFamcomptaDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public static int mapParams(PreparedStatement ps, Object... args) throws SQLException {
        int i = 1;
        for (Object arg : args) {
            if (arg instanceof Date) {
                ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Long) {
                ps.setLong(i++, (Long) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (double) arg);
            } else {
                ps.setString(i++, (String) arg);
            }
        }
        return 0;
    }

    public Object[] params = new Object[]{};
    public FFamcomptaMapper mapper = new FFamcomptaMapper();
    public FFamcompta fFamcompta = new FFamcompta();

    public Object all() {
        String sql = FFamcomptaMapper.BASE_SQL;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public Object ffamcomptacount() {
        String sql = FFamcomptaMapper.ffamcomptacount;
        return (this.getJdbcTemplate().query(sql, mapper)).get(0);
    }

    public Object getLibTaxePied(int fcpType,int fcpChamp) {
        String sql = FFamcomptaMapper.getLibTaxePied;
        params = new Object[]{fcpType,fcpChamp};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }





}
