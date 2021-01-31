package com.server.itsolution.dao;

import com.server.itsolution.entities.FDepot;
import com.server.itsolution.mapper.FDepotMapper;
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
public class FDepotDAO extends JdbcDaoSupport {

    @Autowired
    public FDepotDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FDepotMapper mapper = new FDepotMapper();

    public List<Object> getAll() {
        String sql = FDepotMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }


    public List<Object> getFDepot(int deNo) {
        String sql = FDepotMapper.getDepot;
        params = new Object[]{deNo};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public void maj(String nom, String valeur, BigDecimal cbMarq, String cbCreateur) {
        String cbCreateurSql="";
        if(!cbCreateur.equals(""))
            cbCreateurSql=",cbCreateur='"+cbCreateur+"'";
        String updateQuery = "UPDATE F_DEPOT SET "+nom+" = '"+valeur+"' "+cbCreateurSql+" WHERE cbMarq = "+cbMarq;
        this.getJdbcTemplate().update(updateQuery);
    }

    public void majCatTarif(int deNo,int caCatTarif){
        String sql = FDepotMapper.majCatTarif;
        params = new Object[]{deNo,caCatTarif};
        this.getJdbcTemplate().update(sql,params);
    }

    public void  insertDepotClient (int deNo,String value){
        String sql = FDepotMapper.insertDepotClient;
        params = new Object[]{deNo,value};
        this.getJdbcTemplate().update(sql,params);
    }

    public void  deleteDepot (int deNo){
        String sql = FDepotMapper.deleteDepot;
        params = new Object[]{deNo};
        this.getJdbcTemplate().update(sql,params);
    }

    public Object insertDepot(String deIntitule,String deAdresse,String deComplement,String deCodePostal
                            ,String deVille,String deContact,String deRegion,String dePays,String deEmail
                            ,String deTelephone,String deTelecopie,String protNo,int caSoucheVente, int caSoucheAchat
                            , int caSoucheInterne, String affaire,int caisse,String codeClient){
        String sql = FDepotMapper.insertDepot;
        params = new Object[]{deIntitule,deAdresse,deComplement,deCodePostal,deVille,deContact,deRegion,dePays,deEmail
                                ,deTelephone,deTelecopie,protNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql,params); // Not update, you're returning a ResultSet.
        FDepot fDepot = new FDepot();

        while (sqlRowSet.next()) {
            fDepot.setDE_No(sqlRowSet.getInt("DE_No"));
            fDepot.setCbMarq(sqlRowSet.getInt("cbMarq"));
        }
        insertDepotSouche(fDepot.getDeNo(),affaire,caSoucheVente,caSoucheAchat,caSoucheInterne);
        insertDepotCaisse(fDepot.getDeNo(),caisse);
        String[] codeClients = codeClient.split(";");
        for(int i=0;i<codeClients.length;i++){
            insertDepotClient(fDepot.getDeNo(),codeClients[i]);
        }
        return fDepot;
    }

    public void insertDepotSouche (int deNo,String caNum,int caSoucheVente,int caSoucheAchat,int caSoucheStock){
        String sql = FDepotMapper.insertDepotSouche;
        params = new Object[]{deNo,caSoucheVente,caSoucheAchat,caSoucheStock,caNum};
        this.getJdbcTemplate().update(sql,params);
    }

    public void insertDepotCaisse (int deNo,int caNo){
        String sql = FDepotMapper.insertDepotCaisse;
        params = new Object[]{deNo,caNo};
        this.getJdbcTemplate().update(sql,params);
    }

    public void  supprDepotClient (int deNo){
        String sql = FDepotMapper.supprDepotClient;
        params = new Object[]{deNo};
        this.getJdbcTemplate().update(sql,params);
    }

    public FDepot getFDepotObject(int deNo) {
        String sql = FDepotMapper.getDepot;
        params = new Object[]{deNo};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FDepot fDepot = new FDepot();
        while(sqlRowSet.next()) {
            fDepot.setDE_No(sqlRowSet.getInt("DE_No"));
            fDepot.setDE_Intitule(sqlRowSet.getString("DE_Intitule"));
        }
        return fDepot;
    }


    public List<Object> depotShortDetail() {
        String sql = FDepotMapper.depotShortDetail;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Object> getDepotByIntitule(String intitule,int deNo){
        String sql = FDepotMapper.getDepotByIntitule;
        params = new Object[]{intitule,deNo};
        List<Object> list = this.getJdbcTemplate().query(sql, params,mapper);
        return list;
    }

    public String setCatTarif(int deNo){
        String sql = FDepotMapper.setCatTarif;
        params = new Object[]{deNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getString("CA_CatTarif");
        }
        return "0";
    }

    public List<Object> getDepotUserSearch(int protNo,int depotExclude, String searchTerm,int principal){
        String sql = FDepotMapper.getDepotUserSearch;
        params = new Object[]{protNo,depotExclude,searchTerm,principal};
        List<Object> list = this.getJdbcTemplate().query(sql, params,mapper);
        return list;
    }

    public List<Object> getDepotUser(int protNo){
        String sql = FDepotMapper.getDepotUser;
        params = new Object[]{protNo};
        List<Object> list = this.getJdbcTemplate().query(sql, params,mapper);
        return list;
    }

    public Object depotCount(){
        String sql = FDepotMapper.depotCount;
        return (this.getJdbcTemplate().query(sql,mapper)).get(0);
    }

    public List<Object> getDepotUserPrincipal(int protNo){
        String sql = FDepotMapper.getDepotUserPrincipal;
        params = new Object[]{protNo};
        List<Object> list = this.getJdbcTemplate().query(sql, params,mapper);
        return list;
    }

}

