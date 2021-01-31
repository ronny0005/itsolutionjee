package com.server.itsolution.dao;

import com.server.itsolution.mapper.PCatTarifMapper;
import com.server.itsolution.mapper.PDossierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class PDossierDAO extends JdbcDaoSupport {

    @Autowired
    public PDossierDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public PDossierMapper mapper = new PDossierMapper();

    public List<Object> getAll() {
        String sql = PDossierMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
}
