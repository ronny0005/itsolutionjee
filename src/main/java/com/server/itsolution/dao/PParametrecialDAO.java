package com.server.itsolution.dao;

import com.server.itsolution.entities.PParametrecial;
import com.server.itsolution.entities.PPreferences;
import com.server.itsolution.mapper.PParametrecialMapper;
import com.server.itsolution.mapper.PPreferencesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
@Transactional
public class PParametrecialDAO extends JdbcDaoSupport {

    @Autowired
    public PParametrecialDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public PParametrecialMapper mapper = new PParametrecialMapper();

    public PParametrecial getObject() {
        String sql = PParametrecialMapper.BASE_SQL;
        return (PParametrecial) this.getJdbcTemplate().queryForObject(sql, params, mapper);
    }

}

