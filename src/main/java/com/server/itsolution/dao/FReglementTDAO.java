package com.server.itsolution.dao;

import com.server.itsolution.entities.FArtStock;
import com.server.itsolution.entities.FReglementT;
import com.server.itsolution.mapper.FArtStockMapper;
import com.server.itsolution.mapper.FReglementTMapper;
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
public class FReglementTDAO extends JdbcDaoSupport {

    @Autowired
    public FReglementTDAO(DataSource dataSource) {
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
    public FReglementTMapper mapper = new FReglementTMapper();
    public FReglementT fReglementT = new FReglementT();

    public void insert(String ctNum,int rtCondition,int rtNbJour, int rtNbJourTb01,int rtTRepart,double rtVRepart) {
        String sql = FReglementTMapper.insert;
        params = new Object[]{ctNum,rtCondition,rtNbJour,rtNbJourTb01,rtTRepart,rtVRepart};
        this.getJdbcTemplate().update(sql, params);
    }
}
