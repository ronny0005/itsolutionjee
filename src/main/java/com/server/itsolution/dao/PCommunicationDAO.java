package com.server.itsolution.dao;

import com.server.itsolution.entities.PCommunication;
import com.server.itsolution.mapper.PCommunicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class PCommunicationDAO extends JdbcDaoSupport {

    @Autowired
    public PCommunicationDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public PCommunicationMapper mapper = new PCommunicationMapper();

    public List<Object> getAll() {
        String sql = PCommunicationMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public PCommunication getPCommunication() {
        String sql = PCommunicationMapper.BASE_SQL;
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        PCommunication pCommunication = new PCommunication();
        while(sqlRowSet.next()) {
            pCommunication.setCbMarq(sqlRowSet.getInt("CbMarq"));
            pCommunication.setCO_SoucheSite(sqlRowSet.getString("CO_SoucheSite"));
            pCommunication.setDE_No(sqlRowSet.getInt("DE_No"));
            pCommunication.setN_CatCompta(sqlRowSet.getInt("N_CatCompta"));
            pCommunication.setN_CatTarif(sqlRowSet.getInt("N_CatTarif"));
        }
        return pCommunication;
    }

}
