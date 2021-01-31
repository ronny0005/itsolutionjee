package com.server.itsolution.dao;

import com.server.itsolution.mapper.FCatalogueMapper;
import com.server.itsolution.mapper.PConditionnementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class FCatalogueDAO extends JdbcDaoSupport {

    @Autowired
    public FCatalogueDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FCatalogueMapper mapper = new FCatalogueMapper();

    public List<Object> getAll() {
        String sql = FCatalogueMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Object> getCatalogueByCL(int clNo) {
        String sql = FCatalogueMapper.getCatalogueByCL;
        params = new Object[]{clNo};
        List<Object> list = this.getJdbcTemplate().query(sql,params, mapper);
        return list;
    }

    public List<Object> getCatalogue(int clNiveau) {
        String sql = FCatalogueMapper.getCatalogue;
        params = new Object[]{clNiveau};
        List<Object> list = this.getJdbcTemplate().query(sql,params, mapper);
        return list;
    }

    public List<Object> getCatalogueChildren(int clNiveau,int clNoParent) {
        String sql = FCatalogueMapper.getCatalogueChildren;
        params = new Object[]{clNiveau,clNoParent};
        List<Object> list = this.getJdbcTemplate().query(sql,params, mapper);
        return list;
    }

    public List<Object> getLastCatalogue() {
        String sql = FCatalogueMapper.getLastCatalogue;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Object> insertFCatalogue(String clIntitule,int clNoParent,int clNiveau,String cbCreateur) {
        String sql = FCatalogueMapper.insertFCatalogue;
        params = new Object[]{clIntitule,clNoParent,clNiveau,cbCreateur};
        List<Object> list = this.getJdbcTemplate().query(sql,params,mapper);
        return list;
    }

    public List<Object> updateFCatalogue(String clIntitule,String cbCreateur,int clNo) {
        String sql = FCatalogueMapper.updateFCatalogue;
        params = new Object[]{clIntitule,cbCreateur,clNo};
        List<Object> list = this.getJdbcTemplate().query(sql,params,mapper);
        return list;
    }

    public List<Object> deleteFCatalogue(int clNo) {
        String sql = FCatalogueMapper.deleteFCatalogue;
        params = new Object[]{clNo};
        List<Object> list = this.getJdbcTemplate().query(sql,params,mapper);
        return list;
    }
}
