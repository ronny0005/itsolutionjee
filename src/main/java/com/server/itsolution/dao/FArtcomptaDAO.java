package com.server.itsolution.dao;

import com.server.itsolution.entities.FArtcompta;
import com.server.itsolution.entities.FFamcompta;
import com.server.itsolution.mapper.FArtcomptaMapper;
import com.server.itsolution.mapper.FFamcomptaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@Repository
@Transactional
public class FArtcomptaDAO extends JdbcDaoSupport {

    @Autowired
    public FArtcomptaDAO(DataSource dataSource) {
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
    public FArtcomptaMapper mapper = new FArtcomptaMapper();
    public FArtcompta fArtcompta = new FArtcompta();

    public Object all() {
        String sql = FArtcomptaMapper.BASE_SQL;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public Object fartcomptacount() {
        String sql = FArtcomptaMapper.fartcomptacount;
        return (this.getJdbcTemplate().query(sql, mapper)).get(0);
    }



}
