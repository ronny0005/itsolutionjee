package com.server.itsolution.dao;

import com.server.itsolution.entities.FCaisse;
import com.server.itsolution.entities.PPreferences;
import com.server.itsolution.mapper.FCaisseMapper;
import com.server.itsolution.mapper.PPreferencesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class PPreferencesDAO extends JdbcDaoSupport {

    @Autowired
    public PPreferencesDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public PPreferencesMapper mapper = new PPreferencesMapper();

    public PPreferences getObject() {
        String sql = PPreferencesMapper.BASE_SQL;
        return (PPreferences) this.getJdbcTemplate().queryForObject(sql, params, mapper);
    }

}

