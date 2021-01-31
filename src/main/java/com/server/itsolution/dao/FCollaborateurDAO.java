package com.server.itsolution.dao;

import com.server.itsolution.entities.FCollaborateur;
import com.server.itsolution.mapper.FCollaborateurMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class FCollaborateurDAO extends JdbcDaoSupport {

    @Autowired
    public FCollaborateurDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FCollaborateurMapper mapper = new FCollaborateurMapper();

    public List<Object> getAll() {
        String sql = FCollaborateurMapper.BASE_SQL;
        return this.getJdbcTemplate().query(sql, mapper);
    }
    public Object getCollaborateurCount() {
        String sql = FCollaborateurMapper.getCollaborateurCount;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list.get(0);
    }

    public List<Object> allVendeur () {
        String sql = FCollaborateurMapper.allVendeur;
        return this.getJdbcTemplate().query(sql, mapper);
    }


    public List<Object> getCollaborateur (int coNo) {
        String sql = FCollaborateurMapper.getCollaborateur;
        params = new Object[]{coNo};
        return this.getJdbcTemplate().query(sql, params,mapper);
    }

    public List<Object> getCaissierByCaisse (int caNo) {
        String sql = FCollaborateurMapper.getCaissierByCaisse;
        params = new Object[]{caNo};
        return this.getJdbcTemplate().query(sql, params,mapper);
    }


    public FCollaborateur getCollaborateurJSON (int coNo) {
        String sql = FCollaborateurMapper.getCollaborateur;
        params = new Object[]{coNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FCollaborateur fCollaborateur = new FCollaborateur();
        while(sqlRowSet.next()) {
            fCollaborateur.setCO_No(sqlRowSet.getInt("CO_No"));
            fCollaborateur.setCO_Nom(sqlRowSet.getString("CO_Nom"));
            fCollaborateur.setCO_Prenom(sqlRowSet.getString("CO_Prenom"));
            fCollaborateur.setCO_Telephone(sqlRowSet.getString("CO_Telephone"));
            fCollaborateur.setCO_EMail(sqlRowSet.getString("CO_Email"));
            fCollaborateur.setCO_Acheteur(sqlRowSet.getString("CO_Acheteur"));
            fCollaborateur.setCO_Adresse(sqlRowSet.getString("CO_Adresse"));
            fCollaborateur.setCO_Caissier(sqlRowSet.getString("CO_Caissier"));
            fCollaborateur.setCO_ChargeRecouvr(sqlRowSet.getString("CO_ChargeRecouvr"));
            fCollaborateur.setCO_CodePostal(sqlRowSet.getString("CO_CodePostal"));
            fCollaborateur.setCO_CodeRegion(sqlRowSet.getString("CO_CodeRegion"));
            fCollaborateur.setCO_Complement(sqlRowSet.getString("CO_Complement"));
            fCollaborateur.setCO_DateCreation(sqlRowSet.getString("CO_DateCreation"));
            fCollaborateur.setCbMarq(sqlRowSet.getInt("cbMarq"));
        }
        return fCollaborateur;
    }

    public List<Object> allCaissier () {
        String sql = FCollaborateurMapper.allCaissier;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public List<Object> getCaissierByIntitule(String value){
        String sql = FCollaborateurMapper.getCaissierByIntitule;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(value);
        return this.getJdbcTemplate().query(sql, parames.toArray(), mapper);
    }

    public List<Object> allAcheteur () {
        String sql = FCollaborateurMapper.allAcheteur;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public void delete (BigInteger cbMarq) {
        String sql = FCollaborateurMapper.delete;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(cbMarq);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public Object insertCollaborateur (String coNom,String coPrenom,String codePostal,String coFonction,String coAdresse,String coComplement,String coVille,String coCodeRegion,String coPays,String coService
										,int coVendeur,int coCaissier, int coAcheteur, String coTelephone,String coTelecopie,String coEmail,int coReceptionnaire,int coChargeRecouvr,String cbCreateur) {
        String sql = FCollaborateurMapper.insertCollaborateur;
		ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(coNom);
        parames.add(coPrenom);
        parames.add(coFonction);
        parames.add(coAdresse);
        parames.add(coComplement);
        parames.add(coVille);
        parames.add(coCodeRegion);
        parames.add(coPays);
        parames.add(coService);
        parames.add(coVendeur);
        parames.add(coCaissier);
        parames.add(coAcheteur);
        parames.add(coTelephone);
        parames.add(coTelecopie);
        parames.add(coEmail);
        parames.add(coReceptionnaire);
        parames.add(coChargeRecouvr);
        parames.add(cbCreateur);
        parames.add(codePostal);
        return this.getJdbcTemplate().query(sql, parames.toArray(),mapper);
    }

    public void maj(String nom, String valeur, BigDecimal cbMarq, String cbCreateur) {
        String cbCreateurSql="";
        if(!cbCreateur.equals(""))
            cbCreateurSql=",cbCreateur='"+cbCreateur+"'";
        String updateQuery = "UPDATE F_COLLABORATEUR SET "+nom+" = '"+valeur+"' "+cbCreateurSql+" WHERE cbMarq = "+cbMarq;
        this.getJdbcTemplate().update(updateQuery);
    }

    public void modifCollaborateur (String coNom,String coPrenom,String codePostal,String coFonction,String coAdresse,String coComplement,String coVille,String coCodeRegion,String coPays,String coService
										,int coVendeur,int coCaissier, int coAcheteur, String coTelephone,String coTelecopie,String coEmail,int coReceptionnaire,int coChargeRecouvr,String cbCreateur,int coNo) {
        String sql = FCollaborateurMapper.modifCollaborateur;
		ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(coNom);
        parames.add(coPrenom);
        parames.add(coFonction);
        parames.add(coAdresse);
        parames.add(coComplement);
        parames.add(coVille);
        parames.add(coCodeRegion);
        parames.add(coPays);
        parames.add(coService);
        parames.add(coVendeur);
        parames.add(coCaissier);
        parames.add(coAcheteur);
        parames.add(coTelephone);
        parames.add(coTelecopie);
        parames.add(coEmail);
        parames.add(coReceptionnaire);
        parames.add(coChargeRecouvr);
        parames.add(cbCreateur);
        parames.add(codePostal);
        parames.add(coNo);

        this.getJdbcTemplate().update(sql, parames.toArray());
    }
	
	
	
	

}

