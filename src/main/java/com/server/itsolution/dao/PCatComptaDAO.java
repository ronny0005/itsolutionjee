package com.server.itsolution.dao;

import com.server.itsolution.mapper.PCatComptaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class PCatComptaDAO extends JdbcDaoSupport {

    @Autowired
    public PCatComptaDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public PCatComptaMapper mapper = new PCatComptaMapper();

    public List<Object> getAll() {
        String sql = PCatComptaMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
    public Object getAllCount() {
        String sql = PCatComptaMapper.allCount;
        return (this.getJdbcTemplate().query(sql, mapper)).get(0);
    }

    public List<Object> getCatComptaAchat() {
        String sql = PCatComptaMapper.catComptaAchat;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getCatComptaVente() {
        String sql = PCatComptaMapper.catComptaVente;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
}
