package com.server.itsolution.dao;

import com.server.itsolution.entities.FCaisse;
import com.server.itsolution.entities.FCollaborateur;
import com.server.itsolution.entities.FProtectioncial;
import com.server.itsolution.entities.SaisieAnalytique;
import com.server.itsolution.mapper.FCaisseMapper;
import com.server.itsolution.mapper.FProtectioncialMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@Transactional
public class FProtectioncialDAO extends JdbcDaoSupport {

    @Autowired
    public FProtectioncialDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FProtectioncialMapper mapper = new FProtectioncialMapper();
    public FProtectioncial fProtectioncial = new FProtectioncial();

    public List<Object> getAll() {
        String sql = FProtectioncialMapper.BASE_SQL;
        return  this.getJdbcTemplate().query(sql, mapper);
    }

    public List<Object> getUserList(){
        String sql = FProtectioncialMapper.getUserList;
        return this.getJdbcTemplate().query(sql,mapper);
    }

    public List<Object> getDepotUser(int protNo){
        String sql = FProtectioncialMapper.getDepotUser;
        params = new Object[]{protNo};
        return this.getJdbcTemplate().query(sql,params,mapper);
    }

    public List<Object> allProfil(){
        String sql = FProtectioncialMapper.allProfil;
        return this.getJdbcTemplate().query(sql,mapper);
    }

    public FProtectioncial connexionProctectionByProtNo(int protNo) {
        String sql = FProtectioncialMapper.connexionProctectionByProtNo;
        params = new Object[]{protNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while (sqlRowSet.next()) {
            fProtectioncial=getfProtectioncial(sqlRowSet.getString("PROT_User"),sqlRowSet.getString("PROT_Pwd"),protNo);
        }
        return fProtectioncial;
    }

    public int insert(String ProtUser,String ProtPwd,String ProtDescription,int ProtRight,String ProtEmail,int ProtUserProfil,int ProtPwdStatus){
        String sql = FProtectioncialMapper.insert;
        params = new Object[]{ProtUser,ProtPwd,ProtDescription,ProtRight,ProtEmail,ProtUserProfil,ProtPwdStatus};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql,params); // Not update, you're returning a ResultSet.
        while (sqlRowSet.next()) {
            return sqlRowSet.getInt("PROT_No");
        }
        return -1;
    }

    public Object ajoutUser(String username,String description,String password,String email,int protRight,int protUserProfil,int protPwdStatus,int securiteAdmin, int protNo,String depot){
        int protNoResult = insert(username,password,description,protRight,email,protUserProfil,protPwdStatus);
        FProtectioncial fProtectioncial = connexionProctectionByProtNo(protNo);
        fProtectioncial.setProtectAdmin(securiteAdmin);
        ZDepotUserDAO zDepotUserDAO = new ZDepotUserDAO(this.getDataSource());
        ajoutDepotUser(depot,protNoResult);
        HashMap<String, String> map = new HashMap<>();
        map.put("PROT_No", String.valueOf(protNoResult));

        return map;
    }

    public void ajoutDepotUser(String depot,int protNo){
        ZDepotUserDAO zDepotUserDAO = new ZDepotUserDAO(this.getDataSource());
        zDepotUserDAO.supprDepotUser(protNo);
        String [] depots = depot.split(",");
        for(int i = 0;i<depots.length;i++)
            zDepotUserDAO.insertDepotUser(protNo,Integer.valueOf(depots[i]));
    }

    public void setDepotUser(String depot,int protNo){
        ZDepotUserDAO zDepotUserDAO = new ZDepotUserDAO(this.getDataSource());
        String [] depots = depot.split(",");
        for(int i = 0;i<depots.length;i++)
            zDepotUserDAO.setDepotUser(protNo,Integer.valueOf(depots[i]));
    }

    public Object connexionProctectionByProtNoJSON(int protNo) {
        String sql = FProtectioncialMapper.connexionProctectionByProtNo;
        params = new Object[]{protNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while (sqlRowSet.next()) {
            return getUser(sqlRowSet.getString("PROT_User"),sqlRowSet.getString("PROT_Pwd"),protNo);
        }
        return null;
    }



    public int getCoNo(int protNo){
        String sql = FProtectioncialMapper.getCoNo;
        FProtectioncial fProtectioncial = connexionProctectionByProtNo(protNo);
        params = new Object[]{fProtectioncial.getPROT_User()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while (sqlRowSet.next()) {
            return sqlRowSet.getInt("CO_No");
        }
        return 0;
    }
    public ArrayList<FCollaborateur> getInfoRAFControleur() {
        String sql = FProtectioncialMapper.getInfoRAFControleur;
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FCollaborateur fCollaborateur = new FCollaborateur();
        ArrayList<FCollaborateur> listCollaborateur = new ArrayList<FCollaborateur>();
        while (sqlRowSet.next()) {
            fCollaborateur = new FCollaborateur();
            fCollaborateur.setCO_No(sqlRowSet.getInt("CO_No"));
            fCollaborateur.setCO_EMail(sqlRowSet.getString("CO_Email"));
            fCollaborateur.setCO_Telephone(sqlRowSet.getString("CO_Telephone"));
            fCollaborateur.setCO_Nom(sqlRowSet.getString("CO_Nom"));
            fCollaborateur.setCO_Prenom(sqlRowSet.getString("CO_Prenom"));
            listCollaborateur.add(fCollaborateur);
        }
        return listCollaborateur;
    }


    public int isSecuriteAdmin(int protNo,int protectAdmin,int deNo){
        int isSecurite = 0;
        
        if(protectAdmin==1) {
            ZDepotUserDAO zDepotUserDAO = new ZDepotUserDAO(this.getDataSource());
            ArrayList<Integer> liste = zDepotUserDAO.getDepotPrincipal(protNo);
            for (int elt : liste) {
                if(elt == deNo)
                    isSecurite = 1;
            }
        } else
            isSecurite = 1;

        return isSecurite;
    }

    public int isSecuriteAdminCaisse(int protNo,int protectAdmin,int caNo){
        int isSecurite = 0;
        if(protectAdmin==1) {
            FCaisseDAO fCaisseDAO = new FCaisseDAO(this.getDataSource());
            ArrayList<Integer> listeCaNo = fCaisseDAO.getCaisseProtNo(protNo);
            for (Integer elt : listeCaNo) {
                if(elt == caNo)
                    isSecurite = 1;
            }
        } else
            isSecurite = 1;
        return isSecurite;
    }

    public List<Object> alerteDocumentCatComptaTaxe(){
        String sql = FProtectioncialMapper.alerteDocumentCatComptaTaxe;
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public void getCollaborateurEnvoiMail(String message,String intitule){
        String sql = FProtectioncialMapper.getCollaborateurEnvoiMail;
        params = new Object[]{intitule};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while (sqlRowSet.next()) {
            sqlRowSet.getInt("CO_No");
        }
    }

    public void getCollaborateurEnvoiSMS(String message,String intitule){
        String sql = FProtectioncialMapper.getCollaborateurEnvoiSMS;
        params = new Object[]{intitule};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while (sqlRowSet.next()) {
            sqlRowSet.getInt("CO_No");
        }
    }


    public FProtectioncial getfProtectioncial(String user,String pwd,int protNo) {
        String sql = FProtectioncialMapper.Connexion;
        params = new Object[]{user,cryptMdp(pwd),protNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);

        while (sqlRowSet.next()) {
            fProtectioncial.setProfilName(sqlRowSet.getString("ProfilName"));
            fProtectioncial.setPROT_Administrator(sqlRowSet.getInt("PROT_Administrator"));
            fProtectioncial.setPROT_AFFICHAGE_VAL_CAISSE(sqlRowSet.getInt("PROT_AFFICHAGE_VAL_CAISSE"));
            fProtectioncial.setPROT_APRES_IMPRESSION(sqlRowSet.getInt("PROT_APRES_IMPRESSION"));
            fProtectioncial.setPROT_ARTICLE(sqlRowSet.getInt("PROT_ARTICLE"));
            fProtectioncial.setPROT_AVANT_IMPRESSION(sqlRowSet.getInt("PROT_AVANT_IMPRESSION"));
            fProtectioncial.setPROT_CLIENT(sqlRowSet.getInt("PROT_CLIENT"));
            fProtectioncial.setPROT_COLLABORATEUR(sqlRowSet.getInt("PROT_COLLABORATEUR"));
            fProtectioncial.setPROT_CTRL_TT_CAISSE(sqlRowSet.getInt("PROT_CTRL_TT_CAISSE"));
            fProtectioncial.setPROT_DATE_ACHAT(sqlRowSet.getInt("PROT_DATE_ACHAT"));
            fProtectioncial.setPROT_DATE_COMPTOIR(sqlRowSet.getInt("PROT_DATE_COMPTOIR"));
            fProtectioncial.setPROT_DATE_MVT_CAISSE(sqlRowSet.getInt("PROT_DATE_MVT_CAISSE"));
            fProtectioncial.setPROT_DOCUMENT_ACHAT_RETOUR(sqlRowSet.getInt("PROT_DOCUMENT_ACHAT_RETOUR"));
            fProtectioncial.setPROT_DATE_RGLT(sqlRowSet.getInt("PROT_DATE_RGLT"));
            fProtectioncial.setPROT_DATE_STOCK(sqlRowSet.getInt("PROT_DATE_STOCK"));
            fProtectioncial.setPROT_DATE_VENTE(sqlRowSet.getInt("PROT_DATE_VENTE"));
            fProtectioncial.setPROT_DEPOT(sqlRowSet.getInt("PROT_DEPOT"));
            fProtectioncial.setPROT_DOCUMENT_INTERNE_2(sqlRowSet.getInt("PROT_DOCUMENT_INTERNE_2"));

            fProtectioncial.setPROT_DOCUMENT_ENTREE(sqlRowSet.getInt("PROT_DOCUMENT_ENTREE"));
            fProtectioncial.setPROT_DOCUMENT_VENTE_RETOUR(sqlRowSet.getInt("PROT_DOCUMENT_VENTE_RETOUR"));
            fProtectioncial.setPROT_DOCUMENT_VENTE_BLIVRAISON(sqlRowSet.getInt("PROT_DOCUMENT_VENTE_BLIVRAISON"));
            fProtectioncial.setPROT_DOCUMENT_SORTIE(sqlRowSet.getInt("PROT_DOCUMENT_SORTIE"));
            fProtectioncial.setPROT_DEPRECIATION_STOCK(sqlRowSet.getInt("PROT_DEPRECIATION_STOCK"));
            fProtectioncial.setPROT_Description(sqlRowSet.getString("PROT_Description"));
            fProtectioncial.setPROT_DOCUMENT_ACHAT(sqlRowSet.getInt("PROT_DOCUMENT_ACHAT"));
            fProtectioncial.setPROT_DOCUMENT_ACHAT_FACTURE(sqlRowSet.getInt("PROT_DOCUMENT_ACHAT_FACTURE"));
            fProtectioncial.setPROT_DOCUMENT_VENTE_FACTURE(sqlRowSet.getInt("PROT_DOCUMENT_VENTE_FACTURE"));
            fProtectioncial.setPROT_Right(sqlRowSet.getInt("PROT_Right"));
            fProtectioncial.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
            fProtectioncial.setPROT_DOCUMENT_VENTE_DEVIS(sqlRowSet.getInt("PROT_DOCUMENT_VENTE_DEVIS"));
            fProtectioncial.setPROT_FOURNISSEUR(sqlRowSet.getInt("PROT_FOURNISSEUR"));
            fProtectioncial.setPROT_DOCUMENT_STOCK(sqlRowSet.getInt("PROT_DOCUMENT_STOCK"));
            fProtectioncial.setPROT_DOCUMENT_VENTE(sqlRowSet.getInt("PROT_DOCUMENT_VENTE"));
            fProtectioncial.setPROT_MVT_CAISSE(sqlRowSet.getInt("PROT_MVT_CAISSE"));
            fProtectioncial.setPROT_FAMILLE(sqlRowSet.getInt("PROT_FAMILLE"));
            fProtectioncial.setPROT_PX_ACHAT(sqlRowSet.getInt("PROT_PX_ACHAT"));
            fProtectioncial.setPROT_Pwd(sqlRowSet.getString("PROT_Pwd"));
            fProtectioncial.setPROT_PwdStatus(sqlRowSet.getString("PROT_PwdStatus"));
            fProtectioncial.setPROT_CBCREATEUR(sqlRowSet.getString("PROT_CBCREATEUR"));
            fProtectioncial.setPROT_PX_REVIENT(sqlRowSet.getInt("PROT_PX_REVIENT"));
            fProtectioncial.setPROT_User(sqlRowSet.getString("PROT_User"));
            fProtectioncial.setProtectAdmin(sqlRowSet.getInt("ProtectAdmin"));
            fProtectioncial.setProt_No(sqlRowSet.getInt("Prot_No"));
            fProtectioncial.setPROT_SAISIE_REGLEMENT_FOURNISSEUR(sqlRowSet.getInt("PROT_SAISIE_REGLEMENT_FOURNISSEUR"));
            fProtectioncial.setPROT_ETAT_STAT_COLLAB_PAR_FAMILLE(sqlRowSet.getInt("PROT_ETAT_STAT_COLLAB_PAR_FAMILLE"));
            fProtectioncial.setPROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE(sqlRowSet.getInt("PROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE"));
        }
        return fProtectioncial;
    }
    public String cryptMdp(String mdp){
        mdp = mdp.replace("1", "›");
        mdp = mdp.replace("2", "˜");
        mdp = mdp.replace("3", "™");
        mdp = mdp.replace("4", "ž");
        mdp = mdp.replace("5", "Ÿ");
        mdp = mdp.replace("6", "œ");
        mdp = mdp.replace("7", " ");
        mdp = mdp.replace("8", "’");
        mdp = mdp.replace("9", "“");
        mdp = mdp.replace("0", "š");
        mdp = mdp.replace("A", "ë");
        mdp = mdp.replace("B", "è");
        mdp = mdp.replace("C", "é");
        mdp = mdp.replace("D", "î");
        mdp = mdp.replace("E", "ï");
        mdp = mdp.replace("F", "ì");
        mdp = mdp.replace("G", "í");
        mdp = mdp.replace("H", "â");
        mdp = mdp.replace("I", "ã");
        mdp = mdp.replace("J", "à");
        mdp = mdp.replace("L", "æ");
        mdp = mdp.replace("M", "ç");
        mdp = mdp.replace("N", "ä");
        mdp = mdp.replace("O", "å");
        mdp = mdp.replace("P", "ú");
        mdp = mdp.replace("Q", "û");
        mdp = mdp.replace("R", "ø");
        mdp = mdp.replace("S", "ù");
        mdp = mdp.replace("T", "þ");
        mdp = mdp.replace("U", "ÿ");
        mdp = mdp.replace("V", "ü");
        mdp = mdp.replace("W", "ý");
        mdp = mdp.replace("X", "ò");
        mdp = mdp.replace("Y", "ó");
        mdp = mdp.replace("Z", "ð");
        mdp = mdp.replace("é", "C");
        mdp = mdp.replace("è", "B");
        mdp = mdp.replace("ç", "M");
        mdp = mdp.replace("à", "J");
        mdp = mdp.replace("ï", "E");
        mdp = mdp.replace("a", "Ë");
        mdp = mdp.replace("b", "È");
        mdp = mdp.replace("c", "É");
        mdp = mdp.replace("d", "Î");
        mdp = mdp.replace("e", "Ï");
        mdp = mdp.replace("f", "Ì");
        mdp = mdp.replace("g", "Í");
        mdp = mdp.replace("h", "Â");
        mdp = mdp.replace("i", "Ã");
        mdp = mdp.replace("j", "À");
        mdp = mdp.replace("l", "Æ");
        mdp = mdp.replace("m", "Ç");
        mdp = mdp.replace("n", "Ä");
        mdp = mdp.replace("o", "Å");
        mdp = mdp.replace("p", "Ú");
        mdp = mdp.replace("q", "Û");
        mdp = mdp.replace("r", "Ø");
        mdp = mdp.replace("s", "Ù");
        mdp = mdp.replace("t", "Þ");
        mdp = mdp.replace("u", "ß");
        mdp = mdp.replace("v", "Ü");
        mdp = mdp.replace("w", "Ý");
        mdp = mdp.replace("x", "Ò");
        mdp = mdp.replace("y", "Ó");
        mdp = mdp.replace("z", "Ð");
        mdp = mdp.replace("É", "c");
        mdp = mdp.replace("È", "b");
        mdp = mdp.replace("Ç", "m");
        mdp = mdp.replace("À", "j");
        mdp = mdp.replace("Ï", "e");
        return mdp;
    }

    public Object getUser(String username,String mdp,int protNo) {
        if(mdp.equals("''")) mdp = "";
        String sql = FProtectioncialMapper.Connexion;

        params = new Object[]{username,cryptMdp(mdp),protNo};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        if(list.size()>0)
            return list.get(0);
        return null;
    }

    public List<Object> getSoucheDepotGrpSouche(int protNo,String type){
        String sql = FProtectioncialMapper.getSoucheDepotGrpSouche;
        params = new Object[]{protNo,type};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
    public List<Object> getSoucheDepotCaisse(String type,int souche,int caNo,int deNo,String caNum){
        String sql = FProtectioncialMapper.getSoucheDepotCaisse;
        if(caNum.equals("+"))caNum="";
        params = new Object[]{souche,deNo,caNo,caNum,type};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }


    public List<Object> getSoucheDepotGrpAffaire(int protNo,String type,int sommeil){
        String sql = FProtectioncialMapper.getSoucheDepotGrpAffaire;
        params = new Object[]{type,protNo,sommeil};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getAllProfils(){
        String sql = FProtectioncialMapper.getAllProfils;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public List<Object> getUtilisateurAdminMain(){
        String sql = FProtectioncialMapper.getUtilisateurAdminMain;
        return this.getJdbcTemplate().query(sql, mapper);
    }


    public void maj(String nom, String valeur, BigDecimal cbMarq, String cbCreateur) {
        String cbCreateurSql="";
        if(!cbCreateur.equals(""))
            cbCreateurSql=",cbCreateur='"+cbCreateur+"'";
        if(nom.equals("PROT_Pwd"))
            valeur = cryptMdp(valeur);
        String updateQuery = "UPDATE F_PROTECTIONCIAL SET "+nom+" = '"+valeur+"' "+cbCreateurSql+" WHERE cbMarq = "+cbMarq;
        this.getJdbcTemplate().update(updateQuery);
    }

    public void updateLastLogin(int protNo) {
        String sql = FProtectioncialMapper.UpdateLastLogin;
        params = new Object[]{protNo,protNo};
        this.getJdbcTemplate().update(sql, params);
    }

    public Object createUser(String protUser,String protPwd,String protDescription,int protRight,String protEmail,String protUserProfil,int ProtPwdStatus) {
        String sql = FProtectioncialMapper.createUser;
        params = new Object[]{protUser,protPwd,protDescription,protRight,protEmail,protUserProfil,ProtPwdStatus};
        this.getJdbcTemplate().query(sql, params,mapper);
        return "Le compte a bien été crée !";
    }




}
