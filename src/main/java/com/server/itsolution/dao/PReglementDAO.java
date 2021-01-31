package com.server.itsolution.dao;

import com.server.itsolution.mapper.FDocLigneMapper;
import com.server.itsolution.mapper.PReglementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class PReglementDAO extends JdbcDaoSupport {

    @Autowired
    public PReglementDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }



    public Object[] params = new Object[] {};
    public PReglementMapper mapper = new PReglementMapper();

    public List<Object> getAll() {
        String sql = PReglementMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Object getPReglement (BigDecimal cbMarq){
        String sql = PReglementMapper.getPReglement;
        params = new Object[] {cbMarq};
        return this.getJdbcTemplate().query(sql, params,mapper);
    }

    public Object listeTypeReglementCount (){
        String sql = PReglementMapper.listeTypeReglementCount;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list.get(0);
    }


}
