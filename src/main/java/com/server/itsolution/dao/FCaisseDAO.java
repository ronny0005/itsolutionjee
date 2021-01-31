package com.server.itsolution.dao;

import com.server.itsolution.entities.FCaisse;
import com.server.itsolution.mapper.FCaisseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class FCaisseDAO extends JdbcDaoSupport {

    @Autowired
    public FCaisseDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FCaisseMapper mapper = new FCaisseMapper();

    public List<Object> getAll() {
        String sql = FCaisseMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Object caisseCount() {
        String sql = FCaisseMapper.caisseCount;
        return (this.getJdbcTemplate().query(sql, mapper)).get(0);
    }


    public List<Object> listeCaisseShort() {
        String sql = FCaisseMapper.listeCaisseShort;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public void clotureCaisse(String dateCloture,int caisseDebut,int caisseFin,int protNo,int typeCloture) {
        String sql = FCaisseMapper.clotureCaisse;
        params = new Object[] {protNo,caisseDebut,caisseFin,dateCloture,typeCloture};
        this.getJdbcTemplate().update(sql, params);
    }


    public ArrayList<Integer> getCaisseProtNo(int protNo){
        String sql = FCaisseMapper.getCaisseProtNo;
        params = new Object[] {protNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FCaisse fCaisse= new FCaisse();
        ArrayList<Integer> listeCaNo = new ArrayList<Integer>();

        while(sqlRowSet.next()) {
            listeCaNo.add(sqlRowSet.getInt("CA_No"));
        }
        return listeCaNo;
    }

    public FCaisse getF_Caisse(int CA_No) {
        String sql = FCaisseMapper.getFCaisse;
        params = new Object[] {CA_No};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FCaisse fCaisse= new FCaisse();
        while(sqlRowSet.next()) {
            fCaisse.setCA_No(sqlRowSet.getInt("CA_No"));
            fCaisse.setCA_Intitule(sqlRowSet.getString("CA_Intitule"));
            fCaisse.setCO_NoCaissier(sqlRowSet.getInt("CO_NoCaissier"));
            fCaisse.setJO_Num(sqlRowSet.getString("JO_Num"));
        }
        return fCaisse;
    }
    public Object getF_CaisseJSON(int CA_No) {
        String sql = FCaisseMapper.getFCaisse;
        params = new Object[] {CA_No};
        return this.getJdbcTemplate().query(sql, params,mapper);
    }

    public Object getCaNum(int CA_No) {
        String sql = FCaisseMapper.getCaNum;
        params = new Object[] {CA_No};
        return this.getJdbcTemplate().query(sql, params,mapper);
    }


	
    public Object getCaissierByCaisse(int caNo) {
        String sql = FCaisseMapper.getCaissierByCaisse;
		ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(caNo);
        return this.getJdbcTemplate().query(sql, parames.toArray(),mapper);
    }

    public List<Object> getCaisseDepot(int protNo) {
        String sql = FCaisseMapper.getCaisseDepot;
        params = new Object[] {protNo,protNo};
        return this.getJdbcTemplate().query(sql, params,mapper);
    }

    public Object getCaisseDepotCount(int protNo) {
        String sql = FCaisseMapper.getCaisseDepotCount;
        params = new Object[] {protNo,protNo};
        return (this.getJdbcTemplate().query(sql, params,mapper)).get(0);
    }



    public void deleteCaisse(int caNo){
        String sql = FCaisseMapper.deleteCaisse;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(caNo);
        this.supprDepotCaisse(caNo);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public void insertCaisse(String caIntitule,int coNoCaissier,String joNum,String cbCreateur,String deNo) {
        String sql = FCaisseMapper.insertCaisse;
        ArrayList<Object> parames = new ArrayList<Object>();
        String[] depots = deNo.split(",");
        parames.add(caIntitule);
        parames.add(coNoCaissier);
        parames.add(joNum);
        parames.add(cbCreateur);
        parames.add(depots[0]);
        FCaisse fCaisse = new FCaisse();
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, parames.toArray());
        while (sqlRowSet.next()) {
            fCaisse.setCA_No(sqlRowSet.getInt("CA_No"));
            fCaisse.setCbMarq(sqlRowSet.getInt("cbMarq"));
        }
        this.supprDepotCaisse(fCaisse.getCA_No());
        for(int i=0;i<depots.length;i++){
            this.insertDepotCaisse(fCaisse.getCA_No(),Integer.valueOf(depots[i]));
        }
    }
	
    public void insertDepotCaisse(int caNo,int deNo) {
        String sql = FCaisseMapper.insertDepotCaisse;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(deNo);
        parames.add(caNo);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public void maj(String nom, String valeur, BigDecimal cbMarq, String cbCreateur) {
        String cbCreateurSql="";
        if(!cbCreateur.equals(""))
            cbCreateurSql=",cbCreateur='"+cbCreateur+"'";
        String updateQuery = "UPDATE F_CAISSE SET "+nom+" = '"+valeur+"' "+cbCreateurSql+" WHERE cbMarq = "+cbMarq;
        this.getJdbcTemplate().update(updateQuery);
    }

    public void supprDepotCaisse(int caNo) {
        String sql = FCaisseMapper.supprDepotCaisse;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(caNo);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }
	
	
	
	



}

