package com.server.itsolution.dao;

import com.server.itsolution.entities.*;
import com.server.itsolution.mapper.FCaisseMapper;
import com.server.itsolution.mapper.FProtectioncialMapper;
import org.json.JSONArray;
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

    public Object connexion(String protUser,String pwd,int jour,int heure){
        ZCalendarUserDAO zCalendarUserDAO = new ZCalendarUserDAO(this.getDataSource());
        fProtectioncial=getfProtectioncial(protUser,pwd,0);
        net.minidev.json.JSONObject json = new net.minidev.json.JSONObject();
        int isCalendar = 0;
        int isConnect = 0;
        if(fProtectioncial != null){
            isCalendar = zCalendarUserDAO.isCalendarUser(fProtectioncial.getProt_No());
            isConnect = 1;
            if(isCalendar == 1)
                isConnect = zCalendarUserDAO.canConnect(fProtectioncial.getProt_No(),jour,heure);
            else
                isConnect = 1;
        }

        if(fProtectioncial != null || isConnect == 0){
            if(isCalendar ==2)
                json.put("message", "Horaire de connexion non autorisé");
            if(isCalendar ==1)
                json.put("message", "Login ou mot de passe incorrect");
        }

        updateLastLogin(fProtectioncial.getProt_No());

        return fProtectioncial;
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
            fProtectioncial.setPROT_QTE_NEGATIVE(sqlRowSet.getInt("PROT_QTE_NEGATIVE"));
            fProtectioncial.setPROT_SAISIE_REGLEMENT(sqlRowSet.getInt("PROT_SAISIE_REGLEMENT"));
            fProtectioncial.setPROT_RISQUE_CLIENT(sqlRowSet.getInt("PROT_RISQUE_CLIENT"));
            fProtectioncial.setPROT_INFOLIBRE_ARTICLE(sqlRowSet.getInt("PROT_INFOLIBRE_ARTICLE"));
            fProtectioncial.setPROT_GENERATION_RGLT_CLIENT(sqlRowSet.getInt("PROT_GENERATION_RGLT_CLIENT"));
            fProtectioncial.setPROT_MODIF_SUPPR_COMPTOIR(sqlRowSet.getInt("PROT_MODIF_SUPPR_COMPTOIR"));
            fProtectioncial.setPROT_TICKET_APRES_IMPRESSION(sqlRowSet.getInt("PROT_TICKET_APRES_IMPRESSION"));
            fProtectioncial.setPROT_MODIFICATION_CLIENT(sqlRowSet.getInt("PROT_MODIFICATION_CLIENT"));
            fProtectioncial.setPROT_ETAT_INVENTAIRE_PREP(sqlRowSet.getInt("PROT_ETAT_INVENTAIRE_PREP"));
            fProtectioncial.setPROT_ETAT_LIVRE_INV(sqlRowSet.getInt("PROT_ETAT_LIVRE_INV"));
            fProtectioncial.setPROT_ETAT_STAT_ARTICLE_PAR_ART(sqlRowSet.getInt("PROT_ETAT_STAT_ARTICLE_PAR_ART"));
            fProtectioncial.setPROT_VENTE_COMPTOIR(sqlRowSet.getInt("PROT_VENTE_COMPTOIR"));
            fProtectioncial.setPROT_REAPPROVISIONNEMENT(sqlRowSet.getInt("PROT_REAPPROVISIONNEMENT"));


            fProtectioncial.setPROT_DOCUMENT_ENTREE(sqlRowSet.getInt("PROT_DOCUMENT_ENTREE"));
            fProtectioncial.setPROT_DOCUMENT_VENTE_AVOIR(sqlRowSet.getInt("PROT_DOCUMENT_VENTE_AVOIR"));
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
            fProtectioncial.setPROT_DOCUMENT_REGLEMENT(sqlRowSet.getInt("PROT_DOCUMENT_REGLEMENT"));
            fProtectioncial.setPROT_CLOTURE_CAISSE(sqlRowSet.getInt("PROT_CLOTURE_CAISSE"));
            fProtectioncial.setPROT_PLAN_COMPTABLE(sqlRowSet.getInt("PROT_PLAN_COMPTABLE"));
            fProtectioncial.setPROT_PLAN_ANALYTIQUE(sqlRowSet.getInt("PROT_PLAN_ANALYTIQUE"));
            fProtectioncial.setPROT_TAUX_TAXE(sqlRowSet.getInt("PROT_TAUX_TAXE"));
            fProtectioncial.setPROT_CODE_JOURNAUX(sqlRowSet.getInt("PROT_CODE_JOURNAUX"));
            fProtectioncial.setPROT_LISTE_BANQUE(sqlRowSet.getInt("PROT_LISTE_BANQUE"));
            fProtectioncial.setPROT_LISTE_MODELE_REGLEMENT(sqlRowSet.getInt("PROT_LISTE_MODELE_REGLEMENT"));
            fProtectioncial.setPROT_DOCUMENT_INTERNE_5(sqlRowSet.getInt("PROT_DOCUMENT_INTERNE_5"));
            fProtectioncial.setPROT_ETAT_RELEVE_ECH_CLIENT(sqlRowSet.getInt("PROT_ETAT_RELEVE_ECH_CLIENT"));
            fProtectioncial.setPROT_ETAT_STAT_ARTICLE_PAR_FAM(sqlRowSet.getInt("PROT_ETAT_STAT_ARTICLE_PAR_FAM"));
            fProtectioncial.setPROT_ETAT_STAT_ARTICLE_PALMARES(sqlRowSet.getInt("PROT_ETAT_STAT_ARTICLE_PALMARES"));
            fProtectioncial.setPROT_ETAT_MVT_STOCK(sqlRowSet.getInt("PROT_ETAT_MVT_STOCK"));
            fProtectioncial.setPROT_ETAT_CLT_PAR_FAM_ART(sqlRowSet.getInt("PROT_ETAT_CLT_PAR_FAM_ART"));
            fProtectioncial.setPROT_ETAT_CLT_PAR_ARTICLE(sqlRowSet.getInt("PROT_ETAT_CLT_PAR_ARTICLE"));
            fProtectioncial.setPROT_ETAT_PALMARES_CLT(sqlRowSet.getInt("PROT_ETAT_PALMARES_CLT"));
            fProtectioncial.setPROT_ETAT_STAT_FRS_FAM_ART(sqlRowSet.getInt("PROT_ETAT_STAT_FRS_FAM_ART"));
            fProtectioncial.setPROT_ETAT_STAT_FRS(sqlRowSet.getInt("PROT_ETAT_STAT_FRS"));
            fProtectioncial.setPROT_GEN_ECART_REGLEMENT(sqlRowSet.getInt("PROT_GEN_ECART_REGLEMENT"));
            fProtectioncial.setPROT_ETAT_STAT_CAISSE_ARTICLE(sqlRowSet.getInt("PROT_ETAT_STAT_CAISSE_ARTICLE"));
            fProtectioncial.setPROT_ETAT_STAT_CAISSE_FAM_ARTICLE(sqlRowSet.getInt("PROT_ETAT_STAT_CAISSE_FAM_ARTICLE"));
            fProtectioncial.setPROT_ETAT_CAISSE_MODE_RGLT(sqlRowSet.getInt("PROT_ETAT_CAISSE_MODE_RGLT"));
            fProtectioncial.setPROT_ETAT_RELEVE_CPTE_CLIENT(sqlRowSet.getInt("PROT_ETAT_RELEVE_CPTE_CLIENT"));
            fProtectioncial.setPROT_ETAT_STAT_COLLAB_PAR_TIERS(sqlRowSet.getInt("PROT_ETAT_STAT_COLLAB_PAR_TIERS"));
            fProtectioncial.setPROT_ETAT_STAT_COLLAB_PAR_ARTICLE(sqlRowSet.getInt("PROT_ETAT_STAT_COLLAB_PAR_ARTICLE"));
            fProtectioncial.setPROT_ETAT_STAT_COLLAB_PAR_FAMILLE(sqlRowSet.getInt("PROT_ETAT_STAT_COLLAB_PAR_FAMILLE"));
            fProtectioncial.setPROT_ETAT_STAT_FRS_PAR_FAMILLE(0);
            fProtectioncial.setPROT_ETAT_STAT_FRS_PAR_ARTICLE(0);
            fProtectioncial.setPROT_ETAT_STAT_ACHAT_ANALYTIQUE(0);
/*           fProtectioncial.setPROT_ETAT_STAT_FRS_PAR_FAMILLE(sqlRowSet.getInt("PROT_ETAT_STAT_FRS_PAR_FAMILLE"));
            fProtectioncial.setPROT_ETAT_STAT_FRS_PAR_ARTICLE(sqlRowSet.getInt("PROT_ETAT_STAT_FRS_PAR_ARTICLE"));
            fProtectioncial.setPROT_ETAT_STAT_ACHAT_ANALYTIQUE(sqlRowSet.getInt("PROT_ETAT_STAT_ACHAT_ANALYTIQUE"));
 */
            fProtectioncial.setPROT_ETAT_RELEVE_ECH_FRS(sqlRowSet.getInt("PROT_ETAT_RELEVE_ECH_FRS"));
            fProtectioncial.setPROT_SAISIE_PX_VENTE_REMISE(sqlRowSet.getInt("PROT_SAISIE_PX_VENTE_REMISE"));
            fProtectioncial.setPROT_TARIFICATION_CLIENT(sqlRowSet.getInt("PROT_TARIFICATION_CLIENT"));
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

    public Object getMenuItem(String classe,int module,int action,String type,String menuName,String link,String newLink,Object subMenu) throws JSONException {
        JSONObject menuItem = new JSONObject();
        menuItem.put("class", classe);
        menuItem.put("module", module);
        menuItem.put("action", action);
        menuItem.put("type", type);
        menuItem.put("menuName", menuName);
        menuItem.put("link", link);
        menuItem.put("newLink", newLink);
        menuItem.put("subMenu", subMenu);
        return menuItem;
    }
    public Object getBarreMenu (int protNo,String type) throws JSONException {
        FProtectioncial fProtectioncial = getfProtectioncial("","",protNo);
        JSONArray menu = new JSONArray();
        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_VENTE() != 2) {
            JSONArray menuVente = new JSONArray();
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_VENTE() != 2)
                menuVente.put(getMenuItem("customMenu",2,1,"Vente","Facture de vente"
                        ,"indexMVC.php?module=2&action=1&type=Vente","listeFacture-Vente",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_VENTE_DEVIS() != 2)
                menuVente.put(getMenuItem("customMenu",2,1,"Devis","Devis"
                        ,"indexMVC.php?module=2&action=1&type=Devis","listeFacture-Devis",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_VENTE_DEVIS() != 2)
                menuVente.put(getMenuItem("customMenu",2,1,"BonDeLivraison","Bon de livraison"
                        ,"indexMVC.php?module=2&action=1&type=BonDeLivraison","listeFacture-BonLivraison",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_VENTE_DEVIS() != 2)
                menuVente.put(getMenuItem("customMenu",2,1,"VenteAvoir","Facture d'avoir"
                        ,"indexMVC.php?module=2&action=1&type=VenteAvoir","listeFacture-VenteAvoir",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_VENTE_DEVIS() != 2)
                menuVente.put(getMenuItem("customMenu",2,1,"VenteRetour","Facture de retour"
                        ,"indexMVC.php?module=2&action=1&type=VenteRetour","listeFacture-VenteRetour",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_VENTE_DEVIS() != 2)
                menuVente.put(getMenuItem("customMenu",2,1,"Ticket","Ticket"
                        ,"indexMVC.php?module=2&action=1&type=Ticket","listeFacture-Ticket",null));
            menu.put(getMenuItem("customMenu",2,1,"Vente","Vente"
                    ,"#","#",menuVente));
        }

        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_ACHAT() != 2) {
            JSONArray menuAchat = new JSONArray();
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_ACHAT_FACTURE() != 2)
                menuAchat.put(getMenuItem("customMenu",7,1,"Achat","Facture d'achat"
                        ,"indexMVC.php?module=7&action=1&type=Achat","listeFacture-Achat",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE() != 2)
                menuAchat.put(getMenuItem("customMenu",7,3,"PreparationCommande","Preparation de commande"
                        ,"indexMVC.php?module=7&action=3&type=PreparationCommande","listeFacture-PreparationCommande",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE() != 2)
                menuAchat.put(getMenuItem("customMenu",7,5,"AchatPreparationCommande","Achat + Préparation de commande"
                        ,"indexMVC.php?module=7&action=5&type=AchatPreparationCommande","listeFacture-AchatPreparationCommande",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_ACHAT_RETOUR() != 2)
                menuAchat.put(getMenuItem("customMenu",7,7,"AchatRetour","Achat retour"
                        ,"indexMVC.php?module=7&action=7&type=AchatRetour","listeFacture-AchatRetour",null));
            menu.put(getMenuItem("customMenu",7,1,"Achat","Achat"
                    ,"#","#",menuAchat));
        }

        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_REGLEMENT() != 2) {
            JSONArray menuReglement = new JSONArray();
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_REGLEMENT() != 2)
                menuReglement.put(getMenuItem("customMenu",1,2,"","Client"
                        ,"indexMVC.php?module=1&action=2&typeRegl=Client","listeReglement-Client",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_SAISIE_REGLEMENT_FOURNISSEUR() != 2)
                menuReglement.put(getMenuItem("customMenu",1,4,"","Fournisseur"
                        ,"indexMVC.php?module=1&action=4&typeRegl=Fournisseur","listeFacture-PreparationCommande",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_GENERATION_RGLT_CLIENT() != 2)
                menuReglement.put(getMenuItem("customMenu",1,5,"","Bon de caisse"
                        ,"indexMVC.php?module=1&action=5&typeRegl=Collaborateur","listeFacture-AchatPreparationCommande",null));

            menu.put(getMenuItem("customMenu",7,1,"","Règlement"
                    ,"#","#",menuReglement));
        }

        //Structure
        JSONArray menuStructure = new JSONArray();
        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ARTICLE() != 2)
            menuStructure.put(getMenuItem("customMenu",3,3,"","Article"
                    ,"indexMVC.php?module=3&action=3","",null));

        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_CLIENT() != 2)
            menuStructure.put(getMenuItem("customMenu",3,4,"","Client"
                    ,"indexMVC.php?module=3&action=4","",null));

        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_FOURNISSEUR() != 2)
            menuStructure.put(getMenuItem("customMenu",3,8,"","Fournisseur"
                    ,"indexMVC.php?module=3&action=8","",null));

        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_FAMILLE() != 2)
            menuStructure.put(getMenuItem("customMenu",3,6,"","Famille"
                    ,"indexMVC.php?module=3&action=6","",null));

        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_FAMILLE() != 2)
            menuStructure.put(getMenuItem("customMenu",3,5,"","Catalogue"
                    ,"indexMVC.php?module=3&action=5","",null));

        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DEPOT() != 2)
            menuStructure.put(getMenuItem("customMenu",3,10,"","Depot"
                    ,"indexMVC.php?module=3&action=10","",null));

        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_COLLABORATEUR() != 2)
            menuStructure.put(getMenuItem("customMenu",3,12,"","Collaborateur"
                    ,"indexMVC.php?module=3&action=12","",null));

        if (fProtectioncial.getPROT_Right() == 1)
            menuStructure.put(getMenuItem("customMenu",3,14,"","Caisse"
                    ,"indexMVC.php?module=3&action=14","",null));

        if (fProtectioncial.getPROT_Right() == 1)
            menuStructure.put(getMenuItem("customMenu",3,16,"","Salarié"
                    ,"indexMVC.php?module=3&action=16","",null));

        if (fProtectioncial.getPROT_Right() == 1)
            menuStructure.put(getMenuItem("customMenu",3,11,"","Rabais remise et ristournes"
                    ,"indexMVC.php?module=3&action=18","",null));

        menu.put(getMenuItem("customMenu",3,1,"","Structure"
                ,"#","#",menuStructure));

//Compta

        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_CLOTURE_CAISSE() != 2) {
            JSONArray menuComptabilite = new JSONArray();
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_PLAN_COMPTABLE() != 2)
                menuComptabilite.put(getMenuItem("customMenu",9,1,"","¨Plan comptable"
                        ,"indexMVC.php?module=9&action=1","PlanComptable",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_PLAN_ANALYTIQUE() != 2)
                menuComptabilite.put(getMenuItem("customMenu",9,3,"","Plan analytique"
                        ,"indexMVC.php?module=9&action=3","PlanAnalytique",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_TAUX_TAXE() != 2)
                menuComptabilite.put(getMenuItem("customMenu",9,5,"","Taxe"
                        ,"indexMVC.php?module=9&action=5","Taxe",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_CODE_JOURNAUX() != 2)
                menuComptabilite.put(getMenuItem("customMenu",9,7,"","Journal"
                        ,"indexMVC.php?module=9&action=7","Journal",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_LISTE_BANQUE() != 2)
                menuComptabilite.put(getMenuItem("customMenu",9,9,"","Banque"
                        ,"indexMVC.php?module=9&action=9","Banque",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_LISTE_MODELE_REGLEMENT() != 2)
                menuComptabilite.put(getMenuItem("customMenu",9,11,"","Modèle de rglt"
                        ,"indexMVC.php?module=9&action=11","ModeleDeRglt",null));

            if (fProtectioncial.getPROT_Right() == 1) {
                menuComptabilite.put(getMenuItem("customMenu", 9, 13, "", "Saisie compta"
                        , "indexMVC.php?module=9&action=13", "SaisieCompta", null));
                menuComptabilite.put(getMenuItem("customMenu", 9, 15, "", "Ctrle de caisse"
                        , "indexMVC.php?module=9&action=15", "CtrleDeCaisse", null));
                menuComptabilite.put(getMenuItem("customMenu", 9, 16, "", "Mise à jour comptable"
                        , "indexMVC.php?module=9&action=16", "MajComptable", null));
                menuComptabilite.put(getMenuItem("customMenu", 9, 17, "", "Mise à jour analytique"
                        , "indexMVC.php?module=9&action=17", "MajAnalytique", null));
                menuComptabilite.put(getMenuItem("customMenu", 9, 19, "", "Interrogation tiers"
                        , "indexMVC.php?module=9&action=19", "InterrogationTiers", null));
                menuComptabilite.put(getMenuItem("customMenu", 9, 19, "", "Interrogation et lettrage"
                        , "indexMVC.php?module=9&action=19", "InterrogationEtLettrage", null));

            }
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_CLOTURE_CAISSE() != 2)
            menuComptabilite.put(getMenuItem("customMenu", 9, 18, "", "Clôture de caisse"
                    , "indexMVC.php?module=9&action=18", "ClotureDeCaisse", null));
            menu.put(getMenuItem("customMenu",9,1,"","Comptabilité"
                    ,"#","#",menuComptabilite));
        }

        //Mouvement de stock

        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_STOCK() != 2) {
            JSONArray menuMvt = new JSONArray();
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DEPRECIATION_STOCK() != 2)
                menuMvt.put(getMenuItem("customMenu",4,1,"Transfert","Transfert"
                        ,"indexMVC.php?module=4&action=1&type=Transfert","listeFacture-Vente",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_ENTREE() != 2)
                menuMvt.put(getMenuItem("customMenu",4,3,"Entree","Entrée"
                        ,"indexMVC.php?module=4&action=3&type=Entree","listeFacture-Devis",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_SORTIE() != 2)
                menuMvt.put(getMenuItem("customMenu",4,4,"Sortie","Sortie"
                        ,"indexMVC.php?module=4&action=4&type=Sortie","listeFacture-BonLivraison",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_INTERNE_2() != 2)
                menuMvt.put(getMenuItem("customMenu",4,9,"Transfert_detail","Trsft détail"
                        ,"indexMVC.php?module=4&action=9&type=Transfert_detail","listeFacture-Transfert_detail",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_INTERNE_5() != 2)
                menuMvt.put(getMenuItem("customMenu",4,11,"Transfert_confirmation","Emission"
                        ,"indexMVC.php?module=4&action=11&type=Transfert_confirmation","listeFacture-Transfert_confirmation",null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_DOCUMENT_INTERNE_5() != 2)
                menuMvt.put(getMenuItem("customMenu",4,13,"Transfert_valid_confirmation","Confirmation"
                        ,"indexMVC.php?module=4&action=13&type=Transfert_valid_confirmation","listeFacture-Transfert_valid_confirmation",null));
            menu.put(getMenuItem("customMenu",4,1,"","Mouvements"
                    ,"#","#",menuMvt));
        }

        //Caisse
        if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_MVT_CAISSE() != 2) {
            JSONArray menuCaisse = new JSONArray();
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_MVT_CAISSE() != 2)
                menuCaisse.put(getMenuItem("customMenu",6,1,"","Caisse"
                        ,"indexMVC.php?module=6&action=1","MvtCaisse",null));

            if (fProtectioncial.getPROT_Right() == 1)
                menuCaisse.put(getMenuItem("customMenu",6,2,"","Banque"
                        ,"indexMVC.php?module=6&action=2","MvtBanque",null));

            menu.put(getMenuItem("customMenu",4,1,"","Trésorerie"
                    ,"#","#",menuCaisse));
        }


        //Administration
        if (fProtectioncial.getPROT_Right() == 1) {
            JSONArray menuAdmin = new JSONArray();
            menuAdmin.put(getMenuItem("customMenu",8,1,"","Utilisateur"
                    ,"indexMVC.php?module=8&action=1","Utilisateur",null));
            menuAdmin.put(getMenuItem("customMenu",8,2,"","Profil"
                    ,"indexMVC.php?module=8&action=2","Profil",null));
            menuAdmin.put(getMenuItem("customMenu",8,5,"","Droits"
                    ,"indexMVC.php?module=8&action=5","Droits",null));
            menuAdmin.put(getMenuItem("customMenu",8,7,"","Envoi mail"
                    ,"indexMVC.php?module=8&action=7","EnvoiMail",null));
            menuAdmin.put(getMenuItem("customMenu",8,8,"","Envoi SMS"
                    ,"indexMVC.php?module=8&action=8","EnvoiSms",null));
            menuAdmin.put(getMenuItem("customMenu",8,9,"","Compte SMS"
                    ,"indexMVC.php?module=8&action=9","CompteSms",null));
            menuAdmin.put(getMenuItem("customMenu",8,10,"","Configuration accès"
                    ,"indexMVC.php?module=8&action=10","ConfigAcces",null));
            menuAdmin.put(getMenuItem("customMenu",8,11,"","Configuration profil utilisateur"
                    ,"indexMVC.php?module=8&action=11","ConfigProfilUtilisateur",null));
            menuAdmin.put(getMenuItem("customMenu",8,12,"","Déconnexion totale"
                    ,"indexMVC.php?module=8&action=12","DeconnexionTotale",null));
            menuAdmin.put(getMenuItem("customMenu",8,13,"","Fusion article"
                    ,"indexMVC.php?module=8&action=13","FusionArticle",null));
            menuAdmin.put(getMenuItem("customMenu",8,14,"","Fusion client"
                    ,"indexMVC.php?module=8&action=14","FusionClient",null));
            menuAdmin.put(getMenuItem("customMenu",8,15,"","Calendrier connexion"
                    ,"indexMVC.php?module=8&action=15","CalendrierConnexion",null));
            menuAdmin.put(getMenuItem("customMenu",8,16,"","Suppr. Ligne mvt"
                    ,"indexMVC.php?module=8&action=16","SupprLigneMvt",null));
            menuAdmin.put(getMenuItem("customMenu",8,17,"","Fix cat compta"
                    ,"indexMVC.php?module=8&action=17","FixCatCompta",null));

            menu.put(getMenuItem("customMenu",8,1,"","Administration"
                    ,"#","#",menuAdmin));
        }




        //Etat Compta
        if(type.equals("Haut")) {
            JSONArray menuEtat = new JSONArray();

            JSONArray subEtat = new JSONArray();
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_RELEVE_ECH_CLIENT() == 0) {
                subEtat.put(getMenuItem("customMenu", 5, 6, "", "Echéance client"
                        , "indexMVC.php?module=5&action=6", "GrdLivreDesTiers", null));
                subEtat.put(getMenuItem("customMenu", 5, 30, "", "Echéance client agé"
                        , "indexMVC.php?module=5&action=30", "BalanceDesTiersCommercial", null));
                subEtat.put(getMenuItem("customMenu", 5, 26, "", "Echéance client 2"
                        , "indexMVC.php?module=5&action=26", "JustificatifSoldeTiers", null));

                menuEtat.put(getMenuItem("customMenu", 5, 32, "", "Grand livre tiers commercial"
                        , "#", "#", subEtat));
            }

            subEtat = new JSONArray();
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_STAT_ARTICLE_PAR_ART() == 0)
                subEtat.put(getMenuItem("customMenu", 5, 26, "", "Statistique article par agence"
                        , "indexMVC.php?module=5&action=26", "JustificatifSoldeTiers", null));
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_STAT_FRS() == 0)
                subEtat.put(getMenuItem("customMenu", 5, 20, "", "Statistique des articles par fournisseur"
                        , "indexMVC.php?module=5&action=20", "JustificatifSoldeTiers", null));
            menuEtat.put(getMenuItem("customMenu", 5, 1, "", "Statistique article"
                    , "#", "#", subEtat));

            subEtat = new JSONArray();
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_STAT_ARTICLE_PAR_ART() == 0)
                subEtat.put(getMenuItem("customMenu", 5, 19, "", "Statistique des Achats"
                        , "indexMVC.php?module=5&action=19", "JustificatifSoldeTiers", null));
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_STAT_FRS() == 0)
                subEtat.put(getMenuItem("customMenu", 5, 18, "", "Statistique des Achats analytique"
                        , "indexMVC.php?module=5&action=18", "JustificatifSoldeTiers", null));
            menuEtat.put(getMenuItem("customMenu", 5, 1, "", "Statistique Achat"
                    , "#", "#", subEtat));

            subEtat = new JSONArray();
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_STAT_COLLAB_PAR_ARTICLE() == 0)
                subEtat.put(getMenuItem("customMenu", 5, 13, "", "Statistique collaborateur par article"
                        , "indexMVC.php?module=5&action=13", "JustificatifSoldeTiers", null));
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_STAT_COLLAB_PAR_TIERS() == 0)
                subEtat.put(getMenuItem("customMenu", 5, 11, "", "Statistique collaborateur par client"
                        , "indexMVC.php?module=5&action=11", "JustificatifSoldeTiers", null));
            menuEtat.put(getMenuItem("customMenu", 5, 1, "", "Statistique collaborateur"
                    , "#", "#", subEtat));

            subEtat = new JSONArray();
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_PALMARES_CLT() == 0)
                subEtat.put(getMenuItem("customMenu", 5, 5, "", "Statistique client par agence"
                        , "indexMVC.php?module=5&action=13", "JustificatifSoldeTiers", null));
            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_PALMARES_CLT() == 0)
                subEtat.put(getMenuItem("customMenu", 5, 39, "", "Statistique client par article"
                        , "indexMVC.php?module=5&action=11", "JustificatifSoldeTiers", null));
            menuEtat.put(getMenuItem("customMenu", 5, 1, "", "Statistique client"
                    , "#", "#", subEtat));

            menuEtat.put(getMenuItem("customMenu", 5, 1, "", "Mouvement de stock"
                    , "indexMVC.php?module=5&action=1", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_MVT_STOCK() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 33, "", "Articles Absent Boutique"
                        , "indexMVC.php?module=5&action=33", "#", null));
            if (fProtectioncial.getPROT_Right() == 1) {
                menuEtat.put(getMenuItem("customMenu", 5, 34, "", "Articles Dormants"
                        , "indexMVC.php?module=5&action=34", "#", null));
            }

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_RELEVE_ECH_FRS() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 6, "", "Echéance fournisseur"
                        , "indexMVC.php?module=5&action=6", "#", null));

            if (fProtectioncial.getPROT_Right() == 1)
                menuEtat.put(getMenuItem("customMenu", 5, 7, "", "Règlement client"
                        , "indexMVC.php?module=5&action=7", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_RELEVE_CPTE_CLIENT() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 6, "", "Relevé compte client"
                        , "indexMVC.php?module=5&action=6", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_CAISSE_MODE_RGLT() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 9, "", "Etat caisse"
                        , "indexMVC.php?module=5&action=9", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_RELEVE_ECH_CLIENT() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 10, "", "Etat des dettes"
                        , "indexMVC.php?module=5&action=10", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_LIVRE_INV() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 12, "", "Livre d'inventaire"
                        , "indexMVC.php?module=5&action=12", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_RISQUE_CLIENT() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 14, "", "Versement distant"
                        , "indexMVC.php?module=5&action=14", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_RISQUE_CLIENT() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 15, "", "Versement bancaire"
                        , "indexMVC.php?module=5&action=15", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_STAT_CAISSE_ARTICLE() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 16, "", "Ctrle report fond de caisse"
                        , "indexMVC.php?module=5&action=16", "#", null));

            if (fProtectioncial.getPROT_Right() == 1)
                menuEtat.put(getMenuItem("customMenu", 5, 22, "", "Ech tiers"
                        , "indexMVC.php?module=5&action=22", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_REAPPROVISIONNEMENT() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 23, "", "Etat de réapprovisionnement"
                        , "indexMVC.php?module=5&action=23", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_STAT_CAISSE_ARTICLE() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 24, "", "Etat d'exploitation"
                        , "indexMVC.php?module=5&action=24", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_STAT_CAISSE_ARTICLE() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 25, "", "Transfert de caisse"
                        , "indexMVC.php?module=5&action=25", "#", null));

            if (fProtectioncial.getPROT_Right() == 1)
                menuEtat.put(getMenuItem("customMenu", 5, 27, "", "Stock grand depot"
                        , "indexMVC.php?module=5&action=27", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_STAT_CAISSE_ARTICLE() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 28, "", "Etat du compte de résultat"
                        , "indexMVC.php?module=5&action=28", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_STAT_ARTICLE_PAR_ART() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 29, "", "Fichier central rap"
                        , "indexMVC.php?module=5&action=29", "#", null));

            if (fProtectioncial.getPROT_Right() == 1 || fProtectioncial.getPROT_ETAT_STAT_ARTICLE_PAR_ART() == 0)
                menuEtat.put(getMenuItem("customMenu", 5, 31, "", "Ecriture comptable"
                        , "indexMVC.php?module=5&action=31", "#", null));

            menuEtat.put(getMenuItem("customMenu", 5, 43, "", "Stock magasin"
                    , "indexMVC.php?module=5&action=43", "#", null));


            menu.put(getMenuItem("customMenu", 5, 1, "", "Etats"
                    , "#", "#", menuEtat));

            JSONArray subEtatCompta = new JSONArray();
            subEtatCompta.put(getMenuItem("customMenu", 5, 40, "", "Grand livre des tiers"
                    , "indexMVC.php?module=5&action=40", "GrdLivreDesTiers", null));
            subEtatCompta.put(getMenuItem("customMenu", 5, 21, "", "Balance des tiers commercial"
                    , "indexMVC.php?module=5&action=21", "BalanceDesTiersCommercial", null));
            subEtatCompta.put(getMenuItem("customMenu", 5, 41, "", "Justificatif solde tiers"
                    , "indexMVC.php?module=5&action=41", "JustificatifSoldeTiers", null));
            subEtatCompta.put(getMenuItem("customMenu", 5, 42, "", "Statistiques tiers"
                    , "indexMVC.php?module=5&action=42", "StatistiquesTiers", null));

            JSONArray menuEtatCompta = new JSONArray();
            menuEtatCompta.put(getMenuItem("customMenu", 5, 32, "", "Grand livre tiers commercial"
                    , "indexMVC.php?module=5&action=32", "GrdLivreTiersCommercial", subEtatCompta));

            subEtatCompta = new JSONArray();
            subEtatCompta.put(getMenuItem("customMenu", 5, 38, "", "Journal"
                    , "indexMVC.php?module=5&action=38", "EtatJournal", null));
            subEtatCompta.put(getMenuItem("customMenu", 5, 35, "", "Balance Analytique"
                    , "indexMVC.php?module=5&action=35", "BalanceAnalytique", null));
            subEtatCompta.put(getMenuItem("customMenu", 5, 37, "", "Grand livre analytique"
                    , "indexMVC.php?module=5&action=37", "GrandLivreAnalytique", null));

            menuEtatCompta.put(getMenuItem("customMenu", 5, 36, "", "Balance des comptes"
                    , "indexMVC.php?module=5&action=36", "BalanceDesComptes", subEtatCompta));

            menu.put(getMenuItem("customMenu", 5, 1, "", "Etat compta"
                    , "#", "#", menuEtatCompta));
        }

        return menu.toString();
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
