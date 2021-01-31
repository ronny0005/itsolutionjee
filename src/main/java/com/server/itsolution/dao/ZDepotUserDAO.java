package com.server.itsolution.dao;

import com.server.itsolution.entities.ZDepotUser;
import com.server.itsolution.mapper.ZDepotUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ZDepotUserDAO extends JdbcDaoSupport {

    @Autowired
    public ZDepotUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public ZDepotUserMapper mapper = new ZDepotUserMapper();

    public List<Object> getAll() {
        String sql = ZDepotUserMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public void supprDepotUser(int protNo){
        String sql = ZDepotUserMapper.supprDepotUser;
        params = new Object[]{protNo};
        this.getJdbcTemplate().update(sql, params);
    }

    public void insertDepotUser(int protNo,int depot){
        String sql = ZDepotUserMapper.insertDepotUser;
        params = new Object[]{protNo,depot};
        this.getJdbcTemplate().update(sql, params);
    }

    public void setDepotUser(int protNo,int depot){
        String sql = ZDepotUserMapper.setDepotUser;
        params = new Object[]{depot,protNo};
        this.getJdbcTemplate().update(sql,params);
    }

    public Object getPrincipalDepot(int protNo){
        String sql = ZDepotUserMapper.getPrincipalDepot;
        params = new Object[]{protNo};
        return this.getJdbcTemplate().query(sql, params,mapper);
    }


    public ArrayList<Integer> getDepotPrincipal(int protNo){
        String sql = ZDepotUserMapper.getDepotPrincipal;
        params = new Object[]{protNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        ArrayList<Integer> liste =new ArrayList<Integer>();
        while(sqlRowSet.next()) {
            liste.add(sqlRowSet.getInt("DE_No"));
        }
        return liste;
    }

    public List<Object> getDepotUser(int protNo) {
        String sql = ZDepotUserMapper.getDepotUser;
        params = new Object[]{protNo};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public Object getDepotUserCount(int protNo) {
        String sql = ZDepotUserMapper.getDepotUserCount;
        params = new Object[]{protNo};
        return (this.getJdbcTemplate().query(sql, params,mapper)).get(0);
    }


}
