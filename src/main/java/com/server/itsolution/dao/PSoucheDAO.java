package com.server.itsolution.dao;

import com.server.itsolution.entities.FArticle;
import com.server.itsolution.entities.PSouche;
import com.server.itsolution.mapper.PCatComptaMapper;
import com.server.itsolution.mapper.PSoucheMapper;
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
public class PSoucheDAO extends JdbcDaoSupport {

    @Autowired
    public PSoucheDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public PSoucheMapper mapper = new PSoucheMapper();

    public List<Object> psoucheVente() {
        String sql = PSoucheMapper.psoucheVente;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getPSoucheVente(int cbIndice) {
        String sql = PSoucheMapper.getPSoucheVente;
        params = new Object[]{cbIndice};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public PSouche psoucheObject(String type) {
        String sql ="";
        if(type.equals("Vente"))
            sql = PSoucheMapper.psoucheVente;
        if(type.equals("Achat"))
            sql = PSoucheMapper.psoucheAchat;
        if(type.equals("Interne"))
            sql = PSoucheMapper.psoucheInterne;
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        PSouche pSouche = new PSouche();
        while(sqlRowSet.next()) {
            pSouche.setCbIndice(sqlRowSet.getInt("cbIndice"));
            pSouche.setJoNum(sqlRowSet.getString("JO_Num"));
            pSouche.setsIntitule(sqlRowSet.getString("S_Intitule"));
        }
        return pSouche;
    }


    public ArrayList<PSouche> psoucheListObject(String type) {
        String sql ="";
        if(type.equals("Vente"))
            sql = PSoucheMapper.psoucheVente;
        if(type.equals("Achat"))
            sql = PSoucheMapper.psoucheAchat;
        if(type.equals("Interne"))
            sql = PSoucheMapper.psoucheInterne;
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        ArrayList<PSouche> listPSouche = new ArrayList<PSouche>();
        PSouche pSouche = new PSouche();
        while(sqlRowSet.next()) {
            pSouche = new PSouche();
            pSouche.setCbIndice(sqlRowSet.getInt("cbIndice"));
            pSouche.setJoNum(sqlRowSet.getString("JO_Num"));
            pSouche.setsIntitule(sqlRowSet.getString("S_Intitule"));
            listPSouche.add(pSouche);
        }
        return listPSouche;
    }

    public List<Object> psoucheAchat() {
        String sql = PSoucheMapper.psoucheAchat;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getPSoucheAchat(int cbIndice) {
        String sql = PSoucheMapper.getPSoucheAchat;
        params = new Object[]{cbIndice};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> psoucheInterne() {
        String sql = PSoucheMapper.psoucheInterne;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getPSoucheInterne(int cbIndice) {
        String sql = PSoucheMapper.getPSoucheInterne;
        params = new Object[]{cbIndice};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


}
