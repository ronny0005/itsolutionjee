package com.server.itsolution.dao;

import com.server.itsolution.mapper.PConditionnementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PConditionnementDAO extends JdbcDaoSupport {

    @Autowired
    public PConditionnementDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public PConditionnementMapper mapper = new PConditionnementMapper();

    public List<Object> getAll() {
        String sql = PConditionnementMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Object> getpConditionnement(int cbMarq) {
        String sql = PConditionnementMapper.getpConditionnement;
        params = new Object[] {cbMarq};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public Object getConditionnementMax() {
        String sql = PConditionnementMapper.getConditionnementMax;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        if(list.size()>0)
            return list.get(0);
        return null;
    }

    public List<Object> getPrixConditionnement(String arRef){
        String sql = PConditionnementMapper.getPrixConditionnement;
        params = new Object[] {arRef};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;

    }
}
