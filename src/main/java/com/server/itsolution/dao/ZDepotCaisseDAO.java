package com.server.itsolution.dao;

import com.server.itsolution.mapper.ZDepotCaisseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ZDepotCaisseDAO extends JdbcDaoSupport {

    @Autowired
    public ZDepotCaisseDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public ZDepotCaisseMapper mapper = new ZDepotCaisseMapper();

    public List<Object> getAll() {
        String sql = ZDepotCaisseMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public Object getForProtNoCaisseDepot(int protNo){
        String sql = ZDepotCaisseMapper.getForProtNoCaisseDepot;
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(protNo);
        return this.getJdbcTemplate().query(sql, params.toArray(), mapper);

    }

    public List<Object> getDepotCaisseSelect (int caNo){
        String sql = ZDepotCaisseMapper.getDepotCaisseSelect;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(caNo);
        List<Object> list = this.getJdbcTemplate().query(sql, parames.toArray(), mapper);
        return list;
    }
}
