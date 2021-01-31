package com.server.itsolution.dao;

import com.server.itsolution.entities.FFamille;
import com.server.itsolution.mapper.FFamilleMapper;
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
public class FFamilleDAO extends JdbcDaoSupport {

    @Autowired
    public FFamilleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[]{};
    public FFamilleMapper mapper = new FFamilleMapper();
    public FFamille fFamille = new FFamille();

    public List<Object> getAll() {
        String sql = FFamilleMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Object> getCatComptaByCodeFamille(String faCodeFamille,int fcpType, int fcpChamp){
        String sql = FFamilleMapper.getCatComptaByCodeFamille;
        params = new Object[] {faCodeFamille,fcpType,fcpChamp};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
    public List<Object> getFFamille(String faCodeFamille){
        String sql = FFamilleMapper.getFamille;
        params = new Object[] {faCodeFamille};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }
    public Object getFamilleCount(){
        String sql = FFamilleMapper.getFamilleCount;
        return (this.getJdbcTemplate().query(sql, mapper)).get(0);
    }

    public List<Object> getNextArticleByFam(String faCodeFamille){
        String sql = FFamilleMapper.getNextArticleByFam;
        params = new Object[] {faCodeFamille};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getShortList(){
        String sql = FFamilleMapper.getShortList;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public void insertFamille(String code,String intitule,int CL_No1,int CL_No2,int CL_No3,int CL_No4,int protNo){
        String sql = FFamilleMapper.insertFamille;
        params = new Object[] {code,intitule,CL_No1,CL_No2,CL_No3,CL_No4,protNo};
        this.getJdbcTemplate().update(sql,params);
    }

    public void maj(String nom, String valeur, BigDecimal cbMarq, String cbCreateur) {
        String cbCreateurSql="";
        if(!cbCreateur.equals(""))
            cbCreateurSql=",cbCreateur='"+cbCreateur+"'";
        String updateQuery = "UPDATE F_FAMILLE SET "+nom+" = '"+valeur+"' "+cbCreateurSql+" WHERE cbMarq = "+cbMarq;
        this.getJdbcTemplate().update(updateQuery);
    }

    public void supprFamille(String codeFamille){
        String sql = FFamilleMapper.supprFamille;
        params = new Object[] {codeFamille};
        this.getJdbcTemplate().update(sql,params);
    }


}
