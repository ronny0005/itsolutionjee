package com.server.itsolution.dao;

import com.server.itsolution.mapper.FCompteAMapper;
import com.server.itsolution.mapper.PCatComptaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class FCompteADAO extends JdbcDaoSupport {

    @Autowired
    public FCompteADAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FCompteAMapper mapper = new FCompteAMapper();

    public List<Object> getAll() {
        String sql = FCompteAMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    public List<Object> getAllSeach(String intitule,int top) {
        String valeurSaisie =intitule.replace(" ","%");
        String value = "";
        if(top!=0)
            value = "TOP "+top;
        String sql = "SELECT "+value+" CA_Num\n" +
                "                          ,CA_Intitule\n" +
                "                          ,CA_type as id\n" +
                "                          ,N_Analytique\n" +
                "                          ,CONCAT(CONCAT(CA_Num,' - '),CA_Intitule) as text\n" +
                "                          ,CONCAT(CONCAT(CA_Num,' - '),CA_Intitule) as value\n" +
                "     FROM  F_COMPTEA\n" +
                "     WHERE CA_Type=0\n" +
                "     AND   CONCAT(CONCAT(CA_Num,' - '),CA_Intitule) LIKE '%"+valeurSaisie+"%'";
        List<Object> list = this.getJdbcTemplate().query(sql,mapper);
        return list;
    }

    public List<Object> affaire(int sommeil) {
        String sql = FCompteAMapper.affaire;
        params = new Object[]{sommeil,sommeil};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
    public SqlRowSet getLigneCompteA(BigDecimal cbMarq) {
        String sql = FCompteAMapper.getLigneCompteA;
        params = new Object[]{cbMarq};
        return this.getJdbcTemplate().queryForRowSet(sql, params);
    }



    public List<Object> getCANum(String caNum) {
        String sql = FCompteAMapper.getCANum;
        params = new Object[]{caNum};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }



}
