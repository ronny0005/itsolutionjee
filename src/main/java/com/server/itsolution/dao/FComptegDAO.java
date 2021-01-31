package com.server.itsolution.dao;

import com.server.itsolution.entities.FCompteg;
import com.server.itsolution.entities.FComptet;
import com.server.itsolution.mapper.FComptegMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class FComptegDAO extends JdbcDaoSupport {

    @Autowired
    public FComptegDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FComptegMapper mapper = new FComptegMapper();

    public List<Object> getAll() {
        String sql = FComptegMapper.BASE_SQL;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public List<Object> getComptegByType(int cgType) {
        String sql = FComptegMapper.getComptegByType;
        params = new Object[] {cgType};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public Object getComptegCount() {
        String sql = FComptegMapper.getComptegCount;
        return (this.getJdbcTemplate().query(sql, mapper)).get(0);
    }

    public List<Object> allSearch(String intitule,int top){
        String valeurSaisie =intitule.replace(" ","%");
        String value = "";
        if(top!=0)
            value = "TOP "+top;
        String sql =  "  SELECT  "+value+" CG_Num "+
                      "          ,CG_Intitule "+
                      "          ,CG_Num as id "+
                      "          ,CG_Analytique "+
                      "          ,CG_Tiers "+
                      "          ,CG_Echeance "+
                      "          ,N_Nature "+
                      "                    ,text = CONCAT(CONCAT(CG_Num,' - '),CG_Intitule)"+
                      "                    ,label = CONCAT(CONCAT(CG_Num,' - '),CG_Intitule)"+
                      "                   ,value = CG_Num"+
                      "  FROM  F_COMPTEG "+
                      "  WHERE CG_Type=0 "+
                      "  AND   CONCAT(CONCAT(CG_Num,' - '),CG_Intitule) LIKE CONCAT(CONCAT('%','"+valeurSaisie+"'),'%') ";
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public List<Object> getCGNum(String cgNum) {
        String sql = FComptegMapper.getCGNum;
        params = new Object[] {cgNum};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }
    public FCompteg getCompteG(String cgNum) {
        String sql = FComptegMapper.getCGNum;
        params = new Object[] {cgNum};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FCompteg fCompteg = new FCompteg();
        while(sqlRowSet.next()) {
            fCompteg.setCbCreateur(sqlRowSet.getString("cbCreateur"));
            fCompteg.setCbFlag(sqlRowSet.getString("cbFlag"));
            fCompteg.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
            fCompteg.setCbModification(sqlRowSet.getString("cbModification"));
            fCompteg.setCbProt(sqlRowSet.getString("cbProt"));
            fCompteg.setCbReplication(sqlRowSet.getString("cbReplication"));
            fCompteg.setCG_Num(sqlRowSet.getString("CG_Num"));
            fCompteg.setN_Devise(sqlRowSet.getString("N_Devise"));
            fCompteg.setTA_Code(sqlRowSet.getString("TA_Code"));
            fCompteg.setCG_Analytique(sqlRowSet.getString("CG_Analytique"));
            fCompteg.setCG_Classement(sqlRowSet.getString("CG_Classement"));
            fCompteg.setCG_DateCreate(sqlRowSet.getString("CG_DateCreate"));
            fCompteg.setCG_Devise(sqlRowSet.getString("CG_Devise"));
            fCompteg.setCG_Echeance(sqlRowSet.getString("CG_Echeance"));
            fCompteg.setCG_Intitule(sqlRowSet.getString("CG_Intitule"));
            fCompteg.setCG_Lettrage(sqlRowSet.getString("CG_Lettrage"));
            fCompteg.setCG_Quantite(sqlRowSet.getString("CG_Quantite"));
            fCompteg.setCG_Raccourci(sqlRowSet.getString("CG_Raccourci"));
            fCompteg.setCG_Regroup(sqlRowSet.getString("CG_Regroup"));
            fCompteg.setCG_Report(sqlRowSet.getString("CG_Report"));
            fCompteg.setCG_Saut(sqlRowSet.getString("CG_Saut"));
            fCompteg.setCG_Sommeil(sqlRowSet.getString("CG_Sommeil"));
            fCompteg.setCG_Tiers(sqlRowSet.getInt("CG_Tiers"));
            fCompteg.setCG_Type(sqlRowSet.getString("CG_Type"));
            fCompteg.setCR_Num(sqlRowSet.getString("CR_Num"));
            fCompteg.setN_Nature(sqlRowSet.getString("N_Nature"));
        }
        return fCompteg;
    }
}
