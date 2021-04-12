package com.server.itsolution.dao;

import com.server.itsolution.entities.FCaisse;
import com.server.itsolution.entities.ZCalendarUser;
import com.server.itsolution.mapper.FCaisseMapper;
import com.server.itsolution.mapper.ZCalendarUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Repository
@Transactional
public class ZCalendarUserDAO extends JdbcDaoSupport {

    @Autowired
    public ZCalendarUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public ZCalendarUserMapper mapper = new ZCalendarUserMapper();

    public List<Object> getAll() {
        String sql = ZCalendarUserMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public int isCalendarUser(int PROT_No) {
        String sql = ZCalendarUserMapper.getCalendarUser;
        params = new Object[] {PROT_No};
        List<Object> list = this.getJdbcTemplate().query(sql, params,mapper);
        if(list.size()>0)
            return 1;
        return 0;
    }

    public int canConnect(int PROT_No,int jour,String heure) {
        String sql = ZCalendarUserMapper.getCalendarUserConnexion;
        params = new Object[] {heure,PROT_No,jour};
        SqlRowSet sqlRowSet  = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getInt("canConnect");
        }
        return 0;
    }
}

