package com.server.itsolution.dao;

import com.server.itsolution.mapper.PCatTarifMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class PCatTarifDAO extends JdbcDaoSupport {

    @Autowired
    public PCatTarifDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public PCatTarifMapper mapper = new PCatTarifMapper();

    public List<Object> getAll() {
        String sql = PCatTarifMapper.BASE_SQL;
        return this.getJdbcTemplate().query(sql, mapper);
    }
    public Object getTarifCount() {
        String sql = PCatTarifMapper.getTarifCount;
        return (this.getJdbcTemplate().query(sql, mapper)).get(0);
    }

    public List<Object> getpCatTarif(int cbMarq) {
        String sql = PCatTarifMapper.getpCatTarif;
        params = new Object[] {cbMarq};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public double typeArticle(){
        String sql = PCatTarifMapper.typeArticle;
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getDouble("CT_PrixTTC");
        }
        return 0;
    }
}
