package com.server.itsolution.dao;

import com.server.itsolution.entities.*;
import com.server.itsolution.mapper.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class FDocLigneDAO extends JdbcDaoSupport {

    @Autowired
    public FDocLigneDAO(DataSource dataSource) {
        this.setDataSource(dataSource);

        fDocEnteteDAO = new FDocEnteteDAO(dataSource);
        fProtectioncialDAO = new FProtectioncialDAO(dataSource);
        fArticleDAO = new FArticleDAO(dataSource);
        fArtStockDAO = new FArtStockDAO(dataSource);
        pPreferencesDAO = new PPreferencesDAO(dataSource);
        pParametrecialDAO = new PParametrecialDAO(dataSource);
        pUniteDAO = new PUniteDAO(dataSource);
    }


    public Object[] params = new Object[] {};
    public FDocLigneMapper mapper = new FDocLigneMapper();
    public FDocLigne fDocLigne = new FDocLigne();
    public FDocEntete fDocEntete = new FDocEntete();
    public FDocEnteteDAO fDocEnteteDAO = null;
    public FProtectioncialDAO fProtectioncialDAO = null;
    public FArticleDAO fArticleDAO = null;
    public FArtStockDAO fArtStockDAO = null;
    public PPreferencesDAO pPreferencesDAO = null;
    public PParametrecialDAO pParametrecialDAO = null;
    public PUniteDAO pUniteDAO = null;


    public List<Object> getAll() {
        String sql = FDocLigneMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getPrixClientAch(String arRef,int catCompta,int catTarif,String ctNum){
        String sql = FDocLigneMapper.getPrixClientAch;
        params = new Object[] {ctNum,arRef,catTarif,catCompta,arRef};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getLigneFacture(BigDecimal cbMarq){
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarq);
        String sql = FDocLigneMapper.getLigneFacture;
        params = new Object[] {fDocEntete.getDO_Domaine(),fDocEntete.getDO_Type(),fDocEntete.getDO_Piece()};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public void maj(String nom,String valeur,BigDecimal cbMarq,String cbCreateur) {
        String cbCreateurSql="";
        if(!cbCreateur.equals(""))
            cbCreateurSql=",cbCreateur='"+cbCreateur+"'";
        String updateQuery = "UPDATE F_DOCLIGNE SET "+nom+" = '"+valeur+"' "+cbCreateurSql+" WHERE cbMarq = "+cbMarq;
        this.getJdbcTemplate().update(updateQuery);
    }

    public void supprMvt (BigDecimal cbMarq,String protNo,String typeFacture) {
        FDocLigne fDocLigne = getFDocLigne(cbMarq);
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FArtStockDAO fArtStockDAO = new FArtStockDAO(this.getDataSource());
        FProtectioncialDAO fProtectioncialDAO = new FProtectioncialDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(getCbMarqEntete(cbMarq));
        FProtectioncial fProtectioncial = fProtectioncialDAO.connexionProctectionByProtNo(Integer.valueOf(protNo));
        int DE_No = Integer.valueOf(fDocEntete.getDO_Tiers());
        int isSecurite = fProtectioncialDAO.isSecuriteAdmin(fProtectioncial.getProt_No(),fProtectioncial.getProtectAdmin(),DE_No);
        boolean isVisu = fDocEntete.isVisu(fProtectioncial.getPROT_Administrator(),fProtectioncial.protectedType(typeFacture),fProtectioncial.getPROT_APRES_IMPRESSION(),isSecurite);

        if (!isVisu) {
            Double AR_PrixAch = fDocLigne.getDL_CMUP();
            Double DL_Qte = fDocLigne.getDL_Qte();
            String AR_Ref = fDocLigne.getAR_Ref();
            if(typeFacture.equals("Entree"))
                DL_Qte = -fDocLigne.getDL_Qte();

            FArtStock fArtStock = fArtStockDAO.isStock(AR_Ref,DE_No);
            fArtStockDAO.updateFArtstock(AR_Ref,DE_No,(AR_PrixAch * DL_Qte),DL_Qte,"SupprLigne",protNo);
            this.deleteLigne(cbMarq);
        }
    }

    public void supprLigneFacture(BigDecimal cbMarq,String typeFacture,int protNo){

        FProtectioncialDAO fProtectioncialDao = new FProtectioncialDAO(this.getDataSource());
        FProtectioncial fProtectioncial = fProtectioncialDao.connexionProctectionByProtNo(protNo);
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocLigneDAO fDocLigneDao = new FDocLigneDAO(this.getDataSource());
        FArticleDAO fArticleDAO = new FArticleDAO(this.getDataSource());
        FDocLigne fDocLigne = getFDocLigne (cbMarq);
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(fDocLigneDao.getCbMarqEntete(cbMarq));
        int isSecurite = fProtectioncialDAO.isSecuriteAdmin(fProtectioncial.getProt_No(),fProtectioncial.getProtectAdmin(),fDocEntete.getDE_No());
        boolean isVisu = fDocEntete.isVisu(fProtectioncial.getPROT_Administrator(),fProtectioncial.protectedType(typeFacture),fProtectioncial.getPROT_APRES_IMPRESSION(),isSecurite);
        if(isVisu==false){
            double AR_PrixAch = fDocLigne.getDL_CMUP();
            double DL_Qte = fDocLigne.getDL_Qte();
            String AR_Ref = fDocLigne.getAR_Ref();
            int DE_No = fDocEntete.getDE_No();
            LogFileDAO logFileDAO = new LogFileDAO(this.getDataSource());
            FArticleDAO fArticleDao = new FArticleDAO(this.getDataSource());
            FArticle fArticle = fArticleDao.getF_Article(AR_Ref);
            FArtStockDAO fArtStockDAO = new FArtStockDAO(this.getDataSource());
            logFileDAO.writeFacture("suppression",fDocEntete.getDO_Type(),fDocEntete.getDO_Piece(),fDocEntete.getDO_Domaine()
                    ,fDocLigne.getAR_Ref(),fDocLigne.getDL_Qte(),fDocLigne.getDL_PrixUnitaire()
                    ,0,fDocLigne.getDL_MontantTTC(),String.valueOf(fDocLigne.getCbMarq()),"F_DOCLIGNE",String.valueOf(protNo));
//			$docligne->logMvt("suppresion",fDocEntete.getcbMarq(),DE_No,AR_Ref,DL_Qte,fDocligne.getDL_PrixUnitaire()
//				,"",fDocLigne.getDL_MontantTTC(),protNo);

            fDocLigneDao.deleteLigne(cbMarq);
            if(fArticle.getAR_SuiviStock()!=0)
                if(typeFacture.equals("AchatPreparationCommande") || typeFacture.equals("PreparationCommande") || typeFacture.equals("Achat") || typeFacture.equals("AchatRetour")) {
                    fArtStockDAO.updateFArtstock(AR_Ref,DE_No,-(AR_PrixAch * DL_Qte),-DL_Qte,"supprLigne",String.valueOf(protNo));
                }else {
                    if (!typeFacture.equals("PreparationCommande") && !typeFacture.equals("Devis")) {
                        fArtStockDAO.updateFArtstock(AR_Ref,DE_No,(AR_PrixAch * DL_Qte),DL_Qte,"supprLigne",String.valueOf(protNo));
                    }
                }

            if((getZFactReglSuppr(fDocEntete).size())>0){
                ArrayList<FCollaborateur> listCollaborateur = fProtectioncialDao.getInfoRAFControleur();
                for(FCollaborateur fCollaborateur : listCollaborateur){
                    String email = fCollaborateur.getCO_EMail();
                    String nom = fCollaborateur.getCO_Prenom()+" "+fCollaborateur.getCO_Nom();
                    String corpsMail = "La facture $DO_Piece a été modifié par "+fProtectioncial.getPROT_User()+"<br/>" +
                            " La ligne concernant l'article "+fArticle.getAR_Ref()+" - "+fArticle.getAR_Design()+" de quantité "+FormatText.getMontant(DL_Qte)+" a été supprimée <br/><br/>" +
                            "Cordialement.<br/><br/>";
                /*
						if (preg_match('#^[\w.-]+@[\w.-]+\.[a-z]{2,6}$#i', $email)) {
							require '../Send/class.phpmailer.php';
							$objet->envoiMail($corpsMail, "Suppression de ligne dans la facture $DO_Piece", $email);
						}
				*/
                }
            }

        }
    }

    public void supprLigneTransfert(BigDecimal cbMarq,BigDecimal cbMarqSec,String typeFacture,int protNo){
        FProtectioncialDAO fProtectioncialDao = new FProtectioncialDAO(this.getDataSource());
        FProtectioncial fProtectioncial = fProtectioncialDao.connexionProctectionByProtNo(protNo);
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocLigneDAO fDocLigneDao = new FDocLigneDAO(this.getDataSource());
        FDocLigne fDocLigne = getFDocLigne (cbMarq);
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(fDocLigneDao.getCbMarqEntete(cbMarq));
        int isSecurite = fProtectioncialDAO.isSecuriteAdmin(fProtectioncial.getProt_No(),fProtectioncial.getProtectAdmin(),fDocEntete.getDE_No());
        boolean isVisu = fDocEntete.isVisu(fProtectioncial.getPROT_Administrator(),fProtectioncial.protectedType(typeFacture),fProtectioncial.getPROT_APRES_IMPRESSION(),isSecurite);
        LogFileDAO logFileDAO = new LogFileDAO(this.getDataSource());
        int deNo = 0;
        double arPrixAch = 0;
        double dlQte = 0;

        if(isVisu==false){
            String arRef = fDocLigne.getAR_Ref();
            if (fDocLigne.getDL_MvtStock() == 3) {
                deNo = fDocEntete.getDE_No();
                arPrixAch = fDocLigne.getDL_CMUP();
                dlQte = fDocLigne.getDL_Qte();
            } else {
                deNo = Integer.valueOf(fDocEntete.getDO_Tiers());
                arPrixAch = fDocLigne.getDL_CMUP();
                dlQte = -fDocLigne.getDL_Qte();
            }
            FArticleDAO fArticleDAO = new FArticleDAO(this.getDataSource());
            FArticle fArticle = fArticleDAO.getF_Article(arRef);
            FArtStockDAO fArtStockDAO = new FArtStockDAO(this.getDataSource());
            logFileDAO.writeFacture("suppression",fDocEntete.getDO_Type(),fDocEntete.getDO_Piece(),fDocEntete.getDO_Domaine()
                    ,fDocLigne.getAR_Ref(),fDocLigne.getDL_Qte(),fDocLigne.getDL_PrixUnitaire()
                    ,0,fDocLigne.getDL_MontantTTC(),String.valueOf(fDocLigne.getCbMarq()),"F_DOCLIGNE",String.valueOf(protNo));
            Double qteMontant = (arPrixAch * dlQte);
            fArtStockDAO.updateFArtstock(arRef,deNo,qteMontant,dlQte,"supprLigne",String.valueOf(protNo));

            FDocLigne fDocLigneSec = getFDocLigne (cbMarqSec);
            arRef = fDocLigneSec.getAR_Ref();
            if (fDocLigneSec.getDL_MvtStock() == 3) {
                deNo = fDocEntete.getDE_No();
                arPrixAch = fDocLigneSec.getDL_CMUP();
                dlQte = fDocLigneSec.getDL_Qte();
            } else {
                deNo = Integer.valueOf(fDocEntete.getDO_Tiers());
                arPrixAch = fDocLigneSec.getDL_CMUP();
                dlQte = -fDocLigneSec.getDL_Qte();
            }
            qteMontant = (arPrixAch * dlQte);
            fArtStockDAO.updateFArtstock(arRef,deNo, qteMontant,dlQte,"supprLigne",String.valueOf(protNo));

            logFileDAO.writeFacture("suppression",fDocEntete.getDO_Type(),fDocEntete.getDO_Piece(),fDocEntete.getDO_Domaine()
                    ,fDocLigneSec.getAR_Ref(),fDocLigneSec.getDL_Qte(),fDocLigneSec.getDL_PrixUnitaire()
                    ,0,fDocLigneSec.getDL_MontantTTC(),String.valueOf(fDocLigneSec.getCbMarq()),"F_DOCLIGNE",String.valueOf(protNo));
            fDocLigneDao.deleteLigne(cbMarq);
            fDocLigneDao.deleteLigne(cbMarqSec);
        }
    }


    public List<Object> getZFactReglSuppr(FDocEntete fDocEnete){
        String sql = FDocEnteteMapper.getZFactReglSuppr;
        params = new Object[]{fDocEnete.getDO_Domaine(),fDocEnete.getDO_Piece(),fDocEnete.getDO_Type()};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public Object ajoutLigne(BigDecimal cbMarq,int protNo, double dlQte, String arRef, BigDecimal cbMarqEntete, String typeFacture,
                             int catTarif, double dlPrix, String dlRemise, String machineName, String acte,String entetePrev) {
        JSONObject json = new JSONObject();
        int vteNegatif = 0;
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FProtectioncialDAO fProtectioncialDAO = new FProtectioncialDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
        FProtectioncial fProtectioncial = fProtectioncialDAO.connexionProctectionByProtNo(protNo);
        int cloture = fDocEnteteDAO.journeeCloture(fDocEntete.getDO_Date(),fDocEntete.getCA_No());
        boolean isVisu = false;

        if((cloture==0 || typeFacture.equals("Devis") || typeFacture.equals("AchatPreparationCommande"))) {

            int isSecurite = fProtectioncialDAO.isSecuriteAdmin(protNo, fProtectioncial.getProtectAdmin(), fDocEntete.getDE_No());
            if (!typeFacture.equals("Devis"))
                isVisu = fDocEntete.isVisu(fProtectioncial.getPROT_Administrator(), fProtectioncial.protectedType(typeFacture), fProtectioncial.getPROT_APRES_IMPRESSION(), isSecurite);

            if (!isVisu) {

                if (cbMarq.compareTo(BigDecimal.ZERO) != 0) {
                    fDocLigne = getFDocLigne(cbMarq);
                    arRef = fDocLigne.getAR_Ref();
                }

                fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
                FCReglementDAO fcReglementDAO = new FCReglementDAO(this.getDataSource());
                List<Object> listReglement = fcReglementDAO.getReglementByClientFacture(cbMarqEntete);
                if (listReglement.size() > 0) {
                    json = new JSONObject();
                    json.put("message", "La facture est déjà réglé ! Vous ne pouvez plus la modifier !");
                    return json;
                }

                    int deNoStock = fDocEntete.getDE_No();
                    if (typeFacture.equals("Entree") || typeFacture.equals("Sortie"))
                        deNoStock = Integer.valueOf(fDocEntete.getDO_Tiers());
                    FArticle fArticle = fArticleDAO.getF_Article(arRef);
                    FArtStock fArtStock = fArtStockDAO.isStock(arRef, deNoStock);
                    PPreferences pPreferences = pPreferencesDAO.getObject();
                    vteNegatif = pPreferences.getPR_StockNeg();
                    FArtStockDAO fArtStockDAO = new FArtStockDAO(this.getDataSource());

                    if (catTarif == 1) {
                        if (!(typeFacture.equals("Vente"))
                                || (fArticle.getQte_Gros() != 0 && ((fArticle.getQte_Gros() <= dlQte) || (fArticle.getQte_Gros() > dlQte && fArticle.getPrix_Min() < dlPrix)))
                                || fArticle.getQte_Gros() == 0) {
                        } else {
                            json = new JSONObject();
                            json.put("message", "La quantité saisie de " + arRef + " (" + dlQte + ") < quantité gros (" + fArticle.getQte_Gros() + ")");
                            return json;
                        }
                    } else {
                        if (!typeFacture.equals("VenteRetour")) {
                            FArtClientDAO fArtClientDAO = new FArtClientDAO(this.getDataSource());
                            FArtClient fArtClient = fArtClientDAO.getFArtClient(arRef, catTarif);
                            if (fDocEntete.getDO_Domaine() == 0 && catTarif > 1
                                    && ((dlPrix == fArtClient.getAC_Coef() && dlQte < fArtClient.getAC_Remise())
                                    || (dlPrix < fArtClient.getAC_Coef()))) {
                                if ((dlPrix < fArtClient.getAC_Coef())) {
                                    json = new JSONObject();
                                    json.put("message", "Le montant saisie (" + dlPrix + ") est inférieur au montant minimum (" + fArtClient.getAC_Coef() + ")");
                                    return json;
                                } else {
                                    json = new JSONObject();
                                    json.put("message", "La quantité saisie (" + dlQte + ")  est inférieure à la quantité minimum (" + fArtClient.getAC_Remise() + ")");
                                    return json;
                                }
                            }
                        }
                    }

                    if (typeFacture.equals("Achat") || typeFacture.equals("AchatPreparationCommande")
                            || (!typeFacture.equals("Achat")
                            && ((vteNegatif == 1 && (typeFacture.equals("BonCommande") || typeFacture.equals("PreparationLivraison") || typeFacture.equals("BonLivraison")))
                            || (/*$VteNegatif==0 &&*/ (fArtStock.getAS_QteSto() + fDocLigne.getDL_Qte()) >= (dlQte)))) || typeFacture.equals("Devis")
                            || typeFacture.equals("PreparationCommande") || typeFacture.equals("Avoir") || typeFacture.equals("BonCommande") || typeFacture.equals("VenteRetour")
                            || typeFacture.equals("Entree")) {

                    } else {
                        json = new JSONObject();
                        json.put("message", "La quantité de "+arRef+" ne doit pas dépasser " + Math.round(fArtStock.getAS_QteSto() + fDocLigne.getDL_Qte()) + " !");
                        return json;
                    }

                    String remise = "0";
                    if (!dlRemise.equals("undefined") && !dlRemise.equals(""))
                        remise = dlRemise;

                    double ADL_Qte = fDocLigne.getDL_Qte();
                    int mvtStock = 1;
                    if (dlQte >= 0 && !typeFacture.equals("VenteRetour")) {
                        mvtStock = 3;
                    }
                    if (fArticle.getAR_SuiviStock() == 0)
                        mvtStock = 0;
                    int type_remise = 0;
                    if (!remise.equals("0") && remise.length() != 0) {
                        if (remise.lastIndexOf("%") != -1 || remise.lastIndexOf("P") != -1) {
                            if (remise.lastIndexOf("%") != -1)
                                remise = remise.replace("%", "");
                            else
                                remise = remise.replace("P", "");
                            type_remise = 1;
                        } else {
                            remise = remise.replace("U", "");
                            type_remise = 2;
                        }
                    } else remise = "0";

                    if (fDocEntete.getDO_Domaine() == 0 || fDocEntete.getDO_Domaine() == 1 || typeFacture.equals("Entree") || typeFacture.equals("Sortie") || typeFacture.equals("Transfert")) {
                        if (acte.equals("ajout_ligne")) {
                            if (fDocEntete.getDO_Domaine() == 0 || fDocEntete.getDO_Domaine() == 1)
                                return ajoutLigneFacture(typeFacture, dlQte, mvtStock, remise, type_remise, dlPrix, fArticle, catTarif, protNo, fArtStock, entetePrev, machineName, fDocEntete);
                            if (typeFacture.equals("Entree"))
                                return ajoutLigneEntree(typeFacture, dlQte, 1, remise, type_remise, dlPrix, fArticle, catTarif, protNo, fArtStock, entetePrev, machineName, fDocEntete);
                            if (typeFacture.equals("Sortie"))
                                return ajoutligneSortie(typeFacture, dlQte, 3, remise, type_remise, dlPrix, fArticle, catTarif, protNo, fArtStock, entetePrev, machineName, fDocEntete);
                        } else {
                            if (typeFacture.equals("Entree") || typeFacture.equals("Transfert") || typeFacture.equals("Sortie"))
                                return ModifLigneStock(fDocLigne, remise, type_remise, dlPrix, typeFacture, dlQte, fArticle, catTarif
                                        , protNo, fArtStock, machineName);
                            else
                                return ModifLigneFacturation(fDocLigne, remise, type_remise, dlPrix, typeFacture, dlQte, fArticle, catTarif
                                        , protNo, fArtStock, machineName, fDocEntete);
                        }
                    }

            }
        }else{
            json.put("message", "Cette journée est déjà cloturée !");
            return json;
        }
        return null;
    }

    public Object ModifLigneFacturation(FDocLigne fDocLigne,String remise,int type_remise,double dlPrix,String typeFacture,double dlQte,FArticle fArticle, int catTarif
            ,int protNo,FArtStock fArtStock,String machineName,FDocEntete fDocEntete){
        double anDLCmup = fDocLigne.getDL_CMUP();
        double ADL_Qte = fDocLigne.getDL_Qte();
        double data = Double.valueOf(remise);
        String arRef = fArticle.getAR_Ref();
        if (fArticle.getAR_SuiviStock()!=0 && typeFacture.equals("AchatRetour")) {
            dlQte = -dlQte;
        }
        double val_rem = val_remise(Double.valueOf(remise), type_remise, dlPrix);
        String doDate = fDocEntete.getDO_Date();
        String CT_Num = fDocEntete.getDO_Tiers();
        int deNo = fDocEntete.getDE_No();
        if (typeFacture.equals("Entree"))
            deNo = Integer.valueOf(fDocEntete.getDO_Tiers());
        String caNum = fDocEntete.getCA_Num();
        String doRef = fDocEntete.getDO_Ref();
        int coNo = fDocEntete.getCO_No();
        int doDomaine = fDocEntete.getDO_Domaine();
        int doType = fDocEntete.getDO_Type();
        int type_fourn = 0;
        if (fDocEntete.getDO_Domaine()==1)
            type_fourn = 1;
        if (typeFacture.equals("VenteRetour"))
            dlQte = -dlQte;

        ArrayList<Object> tab = getPrixClientHT(arRef, fDocEntete.getN_CatCompta(), catTarif, dlPrix, val_rem, dlQte, type_fourn);
        Object verifborePrix = null;
        verifborePrix = verifbornePrix(protNo, (double)tab.get(0) /* Prix_Min*/,
                (double)tab.get(1) /*"Prix_Max"*/, (double)tab.get(2) /*"DL_PUNetTTC"*/, typeFacture,catTarif,arRef,doDomaine);
        String U_Intitule="";
        if (verifborePrix != null)
            return verifborePrix;

            double montantHT = (double)tab.get(3); //objet.getClass().getField("DL_MontantHT").getdouble(objet);
            double pu = (double)tab.get(4); //objet.getClass().getField("DL_PrixUnitaire").getdouble(objet);
            double taxe1 = (double)tab.get(5); //objet.getClass().getField("taxe1").getdouble(objet);
            double taxe2 = (double)tab.get(6); //objet.getClass().getField("taxe2").getdouble(objet);
            double taxe3 = (double)tab.get(7); //objet.getClass().getField("taxe3").getdouble(objet);
            double TypeTaxe1 = (double)tab.get(8); //objet.getClass().getField("TU_TA_Type").getdouble(objet) ;
            double TypeTaxe2 = (double)tab.get(9); //objet.getClass().getField("TD_TA_Type").getdouble(objet) ;
            double TypeTaxe3 = (double)tab.get(10); //objet.getClass().getField("TT_TA_Type").getdouble(objet) ;
            double TypeTauxTaxe1 = (double)tab.get(11); //objet.getClass().getField("TU_TA_TTaux").getdouble(objet) ;
            double TypeTauxTaxe2 = (double)tab.get(12); //objet.getClass().getField("TD_TA_TTaux").getdouble(objet) ;
            double TypeTauxTaxe3 = (double)tab.get(13); //objet.getClass().getField("TT_TA_TTaux").getdouble(objet) ;
            double DL_MontantTTC = (double)tab.get(14); //objet.getClass().getField("DL_MontantTTC").getdouble(objet);
            double puttc = (double)tab.get(15); //objet.getClass().getField("DL_PUTTC").getdouble(objet) ;
            int typeHT = (int) tab.get(16); //objet.getClass().getField("AC_PrixTTC").getInt(objet) ;
            if(fDocEntete.getDO_Domaine() == 1)
                typeHT=0;
            U_Intitule = "0";
            double AR_PrixAch = 0;
            if (doDomaine == 1)
                AR_PrixAch = pu ;

            if (doDomaine == 0)
                AR_PrixAch = fArticle.getAR_PrixAch();

            String AR_Ref = fArticle.getAR_Ref();
            int AR_UniteVen = 0;
            int artfourn = 0;
            if (fArticle.getAR_UniteVen() != null)
                AR_UniteVen = fArticle.getAR_UniteVen();
            if (doDomaine == 1) {
                artfourn = fArticleDAO.getArtFournisseurByTiers(fDocEntete.getDO_Tiers());
                if (artfourn != 0)
                    AR_UniteVen = artfourn;
            }
            double pxMin = fArticle.getPrix_Min();
            fArtStock = fArtStockDAO.isStock(AR_Ref, deNo);

            double AS_MontSto = 0;
            double AS_QteSto = 0;
            if (fArtStock != null) {
                AS_MontSto = fArtStock.getAS_MontSto();
                AS_QteSto = fArtStock.getAS_QteSto();
                if (!typeFacture.equals("PreparationCommande"))
                    if (AS_QteSto > 0)
                        AR_PrixAch = (double)(AS_MontSto / AS_QteSto);
                    else if (typeFacture.equals("AchatRetour")) {
                        if (AS_QteSto != 0) {
                            AR_PrixAch = (double)(AS_MontSto / AS_QteSto);
                        }
                    } else {
                        if (!typeFacture.equals("VenteRetour") && !typeFacture.equals("Entree"))
                            AR_PrixAch = 0;
                    }
            }
            AS_MontSto = 0;

            double DL_QtePL = dlQte;
            double DL_QteBL = dlQte;
            double DL_QteBC = dlQte;
            double EU_Qte = dlQte;
            double DL_CMUP = AR_PrixAch;
            if(AR_PrixAch==0)
                DL_CMUP = fArticle.getAR_PrixAch();
            if (typeFacture.equals("PreparationCommande")) {
                DL_QtePL = 0;
                DL_QteBL = 0;
                AR_PrixAch = DL_CMUP;
                DL_CMUP = 0;
            }

            if (typeFacture.equals("PreparationCommande") || typeFacture.equals("BonCommande")) {
                if (typeFacture.equals("BonCommande")) {
                    DL_QtePL = 0;
                    DL_CMUP = 0;
                }
                DL_QteBL = 0;
            }

            if (typeFacture.equals("Achat") || typeFacture.equals("AchatRetour") || typeFacture.equals("AchatPreparationCommande")) {
                DL_QtePL = 0;
                DL_QteBL = dlQte;
                DL_CMUP = AR_PrixAch;
            }

            if (typeFacture.equals("PreparationCommande") || typeFacture.equals("AchatPreparationCommande"))
                updateDLColis(fDocEntete.getN_CatCompta(), fDocLigne.getCbMarq());

            PParametreLivr pParametreLivr = (new PParametreLivrDAO(this.getDataSource())).getpParametreLivrObject(0);
            if(pParametreLivr.getPL_Reliquat() == 1 && !fDocEntete.getTypeFacture().equals("Devis")){
                FComptetDAO fComptetDAO = new FComptetDAO(this.getDataSource());
                net.minidev.json.JSONObject controle = fComptetDAO.controleEncours(fComptetDAO.getF_Comptet(CT_Num,doDomaine),typeFacture,dlPrix,"modif",DL_MontantTTC);
                if(controle != null)
                    return controle;
            }
            fDocLigne.setDL_Qte(dlQte);
            fDocLigne.setDL_QteBC(DL_QteBC);
            fDocLigne.setDL_QteBL(DL_QteBL);
            fDocLigne.setDL_QtePL(DL_QtePL);
            fDocLigne.setEU_Qte(EU_Qte);
            fDocLigne.setDL_Remise01REM_Valeur(Double.parseDouble(remise));
            fDocLigne.setDL_PrixUnitaire(pu);
            fDocLigne.setDL_Taxe1(taxe1);
            fDocLigne.setDL_Taxe2(taxe2);
            fDocLigne.setDL_Taxe3(taxe3);
            if(typeFacture.equals("BonCommande"))
                fDocLigne.setDL_PrixRU(0);
            else
                fDocLigne.setDL_PrixRU(Math.round(AR_PrixAch*100)/100);
            fDocLigne.setDL_PUTTC(puttc);
            fDocLigne.setDL_MontantHT(montantHT);
            fDocLigne.setDL_MontantTTC(DL_MontantTTC);
            fDocLigne.setDL_Remise01REM_Type(type_remise);
            fDocLigne.setDL_TTC(typeHT);
            fDocLigne.setDL_TypeTaux1(TypeTauxTaxe1);
            fDocLigne.setDL_TypeTaux2(TypeTauxTaxe2);
            fDocLigne.setDL_TypeTaux3(TypeTauxTaxe3);
            fDocLigne.setDL_TypeTaxe1(TypeTaxe1);
            fDocLigne.setDL_TypeTaxe2(TypeTaxe2);
            fDocLigne.setDL_TypeTaxe3(TypeTaxe3);
            fDocLigne.setDL_CMUP(Math.round(DL_CMUP*100)/100);
            fDocLigne.setMACHINEPC(machineName);
            fDocLigne.setCbCreateur(String.valueOf(protNo));
            majLigne(fDocLigne);
            if (typeFacture.equals("Achat") || typeFacture.equals("AchatRetour")) {
                dlQte = -dlQte;
                ADL_Qte = -ADL_Qte;
            }
            if(fArticle.getAR_SuiviStock()!=0)
                majStock(fArticle, deNo, typeFacture, ADL_Qte - dlQte, (anDLCmup * ADL_Qte) - (AR_PrixAch * dlQte),"modifLigne",String.valueOf(protNo));
            return this.getLigneFactureDernierElement(fDocLigne);

    }

    public void updateDLColis(int dl_nocolis, BigDecimal cbMarq)
    {
        this.getJdbcTemplate().execute("UPDATE F_DOCLIGNE SET DL_NoColis='"+dl_nocolis+"',cbModification=GETDATE() WHERE cbMarq="+cbMarq);
    }
    public Object ModifLigneStock(FDocLigne fDocLigne,String remise,int type_remise,double dlPrix,String typeFacture,double dlQte,FArticle fArticle, int catTarif
            ,int protNo,FArtStock fArtStock,String machineName){
        int deNo = Integer.valueOf(fDocEntete.getDO_Tiers());
        double anDLCmup = fDocLigne.getDL_CMUP();
        double ADL_Qte = fDocLigne.getDL_Qte();

        double AR_PrixAch = dlPrix;
        double montantHT = AR_PrixAch * dlQte;
        double DL_PUTTC = montantHT;
        double DL_CMUP = 0;
        double DL_PrixRU = 0;
        if (typeFacture.equals("Transfert") || typeFacture.equals("Sortie") || typeFacture.equals("Entree"))
            DL_PUTTC = AR_PrixAch;
        if (typeFacture.equals("Entree") || typeFacture.equals("Sortie") || typeFacture.equals("Transfert") || typeFacture.equals("Achat") || typeFacture.equals("AchatPreparationCommande")) {
            DL_CMUP = AR_PrixAch;
            DL_PrixRU = AR_PrixAch;
        }
        fDocLigne.setDL_QtePL(dlQte);
        fDocLigne.setDL_Qte(dlQte);
        fDocLigne.setDL_QteBC(dlQte);
        fDocLigne.setDL_QteBL(dlQte);
        if (typeFacture.equals("Sortie")) {
            fDocLigne.setDL_QteBL(0);
            fDocLigne.setDL_QtePL(0);
        }
        fDocLigne.setEU_Qte(dlQte);
        fDocLigne.setDL_PUTTC(DL_PUTTC);
        fDocLigne.setDL_MontantHT(montantHT);
        fDocLigne.setDL_MontantTTC(montantHT);
        fDocLigne.setDL_PrixUnitaire(AR_PrixAch);
        fDocLigne.setCbCreateur(String.valueOf(protNo));
        majLigne(fDocLigne);
        if (fDocLigne.getDL_MvtStock()==1) {
            dlQte = -dlQte;
            ADL_Qte = -ADL_Qte;
        }

        majStock(fArticle, deNo, typeFacture, ADL_Qte - dlQte, (anDLCmup* ADL_Qte) - (AR_PrixAch * dlQte),"modifLigne",String.valueOf(protNo));
        return this.getLigneFactureDernierElement(fDocLigne);
    }

    public FDocLigne ajoutligneSortie(String typeFacture,double dlQte,int mvtStock,String remise,int type_remise,double dlPrix
            ,FArticle fArticle,int catTarif, int protNo,FArtStock fArtStock,String entetePrev
            ,String machineName,FDocEntete fDocEntete){
        double AR_PrixAch = 0;
        double montantHT = 0;
        String U_Intitule = "";
        String DO_Date = "";

        AR_PrixAch = 0;
        if (fArtStock.getAS_QteSto() != 0)
            AR_PrixAch = fArtStock.getAS_MontSto() / fArtStock.getAS_QteSto();
        montantHT = AR_PrixAch * dlQte;
        if (fArticle.getAR_UniteVen() != null)
            U_Intitule = (pUniteDAO.getUniteByIndice(fArticle.getAR_UniteVen())).getU_Intitule();
        DO_Date = fDocEntete.getDO_Date();
        String do_ref = fDocEntete.getDO_Ref();
        initVariables(fDocLigne);
        fDocLigne.setDL_MvtStock(mvtStock);
        fDocLigne.setDO_Domaine(fDocEntete.getDO_Domaine());
        fDocLigne.setDO_Type(fDocEntete.getDO_Type());
        fDocLigne.setDO_Piece(fDocEntete.getDO_Piece());
        fDocLigne.setCT_Num(fDocEntete.getDO_Tiers());
        fDocLigne.setDO_Date(DO_Date);
        fDocLigne.setDO_Ref(fDocEntete.getDO_Ref());
        fDocLigne.setAR_Ref(fArticle.getAR_Ref());
        fDocLigne.setDL_Design(fArticle.getAR_Design());
        fDocLigne.setDL_Qte(dlQte);
        fDocLigne.setDL_QteBC(dlQte);
        fDocLigne.setDL_QteBL(0);
        fDocLigne.setEU_Qte(dlQte);
        fDocLigne.setDL_Remise01REM_Valeur(0);
        fDocLigne.setDL_PrixUnitaire(AR_PrixAch);
        fDocLigne.setDL_Taxe1(0);
        fDocLigne.setDL_Taxe2(0);
        fDocLigne.setDL_Taxe3(0);
        fDocLigne.setCO_No(0);
        fDocLigne.setDL_PrixRU(AR_PrixAch);
        fDocLigne.setEU_Enumere(U_Intitule);
        fDocLigne.setDE_No(Integer.valueOf(fDocEntete.getDO_Tiers()));
        fDocLigne.setDL_PUTTC(AR_PrixAch);
        fDocLigne.setCA_Num(fDocEntete.getCA_Num());
        fDocLigne.setDL_MontantHT(montantHT);
        fDocLigne.setDL_MontantTTC(montantHT);
        fDocLigne.setDL_Remise01REM_Type(0);
        fDocLigne.setDL_QtePL(0);
        fDocLigne.setDL_TypePL(0);
        fDocLigne.setDL_TTC(0);
        fDocLigne.setDL_TypeTaux1(0);
        fDocLigne.setDL_TypeTaux2(0);
        fDocLigne.setDL_TypeTaux3(0);
        fDocLigne.setDL_TypeTaxe1(0);
        fDocLigne.setDL_TypeTaxe2(0);
        fDocLigne.setDL_TypeTaxe3(0);
        fDocLigne.setDL_PieceBL("");
        fDocLigne.setDL_DateBC("1900-01-01");
        fDocLigne.setDL_DateBL(DO_Date);
        fDocLigne.setDL_CMUP(AR_PrixAch);
        fDocLigne.setDL_DatePL("1900-01-01");
        fDocLigne.setMACHINEPC(machineName);
        fDocLigne.setCbCreateur(String.valueOf(protNo));
        insertLigne(fDocLigne);

        int deNo = Integer.valueOf(fDocEntete.getDO_Tiers());
        majStock(fArticle, deNo , typeFacture, -dlQte, -(AR_PrixAch * dlQte),"insertLigne",String.valueOf(protNo));
        return fDocLigne;

    }

    public FDocLigne ajoutLigneEntree(String typeFacture,double dlQte,int mvtStock,String remise,int type_remise,double dlPrix
            ,FArticle fArticle,int catTarif, int protNo,FArtStock fArtStock,String entetePrev
            ,String machineName,FDocEntete fDocEntete){
        double AR_PrixAch = 0;
        double AR_PrixVen = 0;
        double montantHT = 0;
        int AR_UniteVen = 0;
        String U_Intitule = "";
        String DO_Date = fDocEntete.getDO_Date();
        int DE_No = 0;
        if (typeFacture.equals("Entree"))
            DE_No = Integer.parseInt(fDocEntete.getDO_Tiers());
        int mvtEntree = 1;
        if (typeFacture.equals("Entree"))
            mvtEntree = 0;
        if (mvtEntree == 1) {
            AR_PrixAch = fArticle.getAR_PrixAch();
            AR_PrixVen = fArticle.getAR_PrixVen();
        }
        else
            AR_PrixAch = dlPrix;
        montantHT = AR_PrixAch * dlQte;

        if (fArticle.getAR_UniteVen() != null)
            U_Intitule = (pUniteDAO.getUniteByIndice(fArticle.getAR_UniteVen())).getU_Intitule();

        FDocLigne fDocLigne = new FDocLigne();
        initVariables(fDocLigne);
        fDocLigne.setDL_MvtStock(mvtStock);
        fDocLigne.setDO_Domaine(fDocEntete.getDO_Domaine());
        fDocLigne.setDO_Type(fDocEntete.getDO_Type());
        fDocLigne.setCT_Num(fDocEntete.getDO_Tiers());
        fDocLigne.setDO_Piece(fDocEntete.getDO_Piece());
        fDocLigne.setDO_Date(DO_Date);
        fDocLigne.setDO_Ref(fDocEntete.getDO_Ref());
        fDocLigne.setAR_Ref(fArticle.getAR_Ref());
        fDocLigne.setDL_Design(fArticle.getAR_Design());
        fDocLigne.setDL_Qte(dlQte);
        fDocLigne.setDL_QteBC(dlQte);
        fDocLigne.setDL_QteBL(0);
        fDocLigne.setEU_Qte(dlQte);
        fDocLigne.setDL_Remise01REM_Valeur(0);
        fDocLigne.setDL_PrixUnitaire(AR_PrixAch);
        fDocLigne.setDL_Taxe1(0);
        fDocLigne.setDL_Taxe2(0);
        fDocLigne.setDL_Taxe3(0);
        fDocLigne.setCO_No(0);
        fDocLigne.setDL_PrixRU(AR_PrixAch);
        fDocLigne.setEU_Enumere(U_Intitule);
        fDocLigne.setDE_No(DE_No);
        fDocLigne.setDL_PUTTC(AR_PrixAch);
        fDocLigne.setCA_Num(fDocEntete.getCA_Num());
        fDocLigne.setDL_MontantHT(montantHT);
        fDocLigne.setDL_MontantTTC(montantHT);
        fDocLigne.setDL_Remise01REM_Type(0);
        fDocLigne.setDL_QtePL(0);
        fDocLigne.setDL_TypePL(0);
        fDocLigne.setDL_TTC(0);
        fDocLigne.setDL_TypeTaux1(0);
        fDocLigne.setDL_TypeTaux2(0);
        fDocLigne.setDL_TypeTaux3(0);
        fDocLigne.setDL_TypeTaxe1(0);
        fDocLigne.setDL_TypeTaxe2(0);
        fDocLigne.setDL_TypeTaxe3(0);
        fDocLigne.setDL_PieceBL("");
        fDocLigne.setDL_DateBC("1900-01-01");
        fDocLigne.setDL_DateBL(DO_Date);
        fDocLigne.setDL_CMUP(AR_PrixAch);
        fDocLigne.setDL_DatePL("1900-01-01");
        fDocLigne.setMACHINEPC(machineName);
        fDocLigne.setCbCreateur(String.valueOf(protNo));
        insertLigne(fDocLigne);

        if (typeFacture.equals("Entree"))
            dlQte = -dlQte;
        int deNo = Integer.valueOf(fDocEntete.getDO_Tiers());
        majStock(fArticle, deNo , typeFacture, -dlQte, -(AR_PrixAch * dlQte),"insertLigne",String.valueOf(protNo));
        if (typeFacture.equals("Entree"))
            fArtStockDAO.setASQteMaxiArtStock(fArticle.getAR_Ref(),deNo);
        return fDocLigne;
    }


    public Object ajoutLigneFacture(String typeFacture, double dlQte, int mvtStock, String remise, int type_remise, double dlPrix
            , FArticle fArticle, int catTarif, int protNo, FArtStock fArtStock, String entetePrev
            , String machineName, FDocEntete fDocEntete){

        String arRef = fArticle.getAR_Ref();
        if (fArticle.getAR_SuiviStock()!=0 && typeFacture.equals("AchatRetour")) {
            dlQte = -dlQte;
            mvtStock = 3;
        }
        double val_rem = val_remise(Double.valueOf(remise), type_remise, dlPrix);
        String doDate = fDocEntete.getDO_Date();
        String CT_Num = fDocEntete.getDO_Tiers();
        int deNo = fDocEntete.getDE_No();
        if (typeFacture.equals("Entree"))
            deNo = Integer.valueOf(fDocEntete.getDO_Tiers());
        String caNum = fDocEntete.getCA_Num();
        String doRef = fDocEntete.getDO_Ref();
        int coNo = fDocEntete.getCO_No();
        int doDomaine = fDocEntete.getDO_Domaine();
        int doType = fDocEntete.getDO_Type();
        int type_fourn = 0;
        if (fDocEntete.getDO_Domaine()==1)
            type_fourn = 1;
        if (typeFacture.equals("VenteRetour"))
            dlQte = -dlQte;
        ArrayList<Object> tab = getPrixClientHT(arRef, fDocEntete.getN_CatCompta(), catTarif, dlPrix, val_rem, dlQte, type_fourn);
        Object verifborePrix = null;
        if(!typeFacture.equals("VenteRetour"))
            verifborePrix = verifbornePrix(protNo, (double)tab.get(0) /* Prix_Min*/,
                    (double)tab.get(1) /*"Prix_Max"*/, (double)tab.get(2) /*"DL_PUNetTTC"*/, typeFacture,catTarif,arRef,doDomaine);
        String U_Intitule="";
        if (verifborePrix == null) {
            double montantHT = (double)tab.get(3); //objet.getClass().getField("DL_MontantHT").getdouble(objet);
            double pu = (double)tab.get(4); //objet.getClass().getField("DL_PrixUnitaire").getdouble(objet);
            double taxe1 = (double)tab.get(5); //objet.getClass().getField("taxe1").getdouble(objet);
            double taxe2 = (double)tab.get(6); //objet.getClass().getField("taxe2").getdouble(objet);
            double taxe3 = (double)tab.get(7); //objet.getClass().getField("taxe3").getdouble(objet);
            double TypeTaxe1 = (double)tab.get(8); //objet.getClass().getField("TU_TA_Type").getdouble(objet) ;
            double TypeTaxe2 = (double)tab.get(9); //objet.getClass().getField("TD_TA_Type").getdouble(objet) ;
            double TypeTaxe3 = (double)tab.get(10); //objet.getClass().getField("TT_TA_Type").getdouble(objet) ;
            double TypeTauxTaxe1 = (double)tab.get(11); //objet.getClass().getField("TU_TA_TTaux").getdouble(objet) ;
            double TypeTauxTaxe2 = (double)tab.get(12); //objet.getClass().getField("TD_TA_TTaux").getdouble(objet) ;
            double TypeTauxTaxe3 = (double)tab.get(13); //objet.getClass().getField("TT_TA_TTaux").getdouble(objet) ;
            double DL_MontantTTC = (double)tab.get(14); //objet.getClass().getField("DL_MontantTTC").getdouble(objet);
            double puttc = (double)tab.get(15); //objet.getClass().getField("DL_PUTTC").getdouble(objet) ;
            int typeHT = (int) tab.get(16); //objet.getClass().getField("AC_PrixTTC").getInt(objet) ;

            PParametreLivr pParametreLivr = (new PParametreLivrDAO(this.getDataSource())).getpParametreLivrObject(0);
            if(pParametreLivr.getPL_Reliquat() == 1 && !fDocEntete.getTypeFacture().equals("Devis")){
                FComptetDAO fComptetDAO = new FComptetDAO(this.getDataSource());
                net.minidev.json.JSONObject controle = fComptetDAO.controleEncours(fComptetDAO.getF_Comptet(CT_Num,doDomaine),typeFacture,dlPrix,"ajout",DL_MontantTTC);
                if(controle != null)
                    return controle;
            }
            if(fDocEntete.getDO_Domaine() == 1)
                typeHT=0;
            U_Intitule = "0";
            double AR_PrixAch = 0;
            if (doDomaine == 1)
                AR_PrixAch = pu ;

            if (doDomaine == 0)
                AR_PrixAch = fArticle.getAR_PrixAch();

            String AR_Design = fArticle.getAR_Design();
            String AR_Ref = fArticle.getAR_Ref();
            double AR_PrixVen = fArticle.getAR_PrixVen();
            int AR_UniteVen = 0;
            int artfourn = 0;
            if (fArticle.getAR_UniteVen() != null)
                AR_UniteVen = fArticle.getAR_UniteVen();
            if (doDomaine == 1) {
                artfourn = fArticleDAO.getArtFournisseurByTiers(fDocEntete.getDO_Tiers());
                if (artfourn != 0)
                    AR_UniteVen = artfourn;
            }
            double pxMin = fArticle.getPrix_Min();
            fArtStock = fArtStockDAO.isStock(AR_Ref, deNo);

            double AS_MontSto = 0;
            double AS_QteSto = 0;
            if (fArtStock != null) {
                AS_MontSto = fArtStock.getAS_MontSto();
                AS_QteSto = fArtStock.getAS_QteSto();
                if (!typeFacture.equals("PreparationCommande"))
                if (AS_QteSto > 0)
                        AR_PrixAch = (double)(AS_MontSto / AS_QteSto);
                else if (typeFacture.equals("AchatRetour")) {
                    if (AS_QteSto != 0) {
                        AR_PrixAch = (double)(AS_MontSto / AS_QteSto);
                    }
                } else {
                    if (!typeFacture.equals("VenteRetour") && !typeFacture.equals("Entree"))
                        AR_PrixAch = 0;
                }
            }
            AS_MontSto = 0;

            if (fArticle.getAR_UniteVen() != null)
                U_Intitule = (pUniteDAO.getUniteByIndice(fArticle.getAR_UniteVen())).getU_Intitule();

            if (typeFacture.equals("AchatPreparationCommande")) {
                mvtStock = 0;
            }

            int dl_typl = 0;
            if (typeFacture.equals("VenteRetour"))
                dl_typl = 1;
            if (typeFacture.equals("Avoir"))
                dl_typl = 2;
            String do_dateBC = doDate;
            String do_dateBL = doDate;
            String do_datePL = doDate;
            double DL_QtePL = dlQte;
            double DL_QteBL = dlQte;
            double DL_QteBC = dlQte;
            double EU_Qte = dlQte;
            double DL_CMUP = AR_PrixAch;
            if(AR_PrixAch==0)
                DL_CMUP = fArticle.getAR_PrixAch();
            if (typeFacture.equals("PreparationCommande") || typeFacture.equals("BonCommande")) {
                do_dateBL = "1900-01-01";
                do_datePL = "1900-01-01";
                if(typeFacture.equals("BonCommande")){
                    do_dateBC = "1900-01-01";
                    DL_QtePL = 0;
                    DL_CMUP = 0;
                }
                DL_QteBL = 0;
                mvtStock = 0;
            }

            if (typeFacture.equals("Devis")) {
                mvtStock = 0;
            }

            if (typeFacture.equals("Achat") || typeFacture.equals("AchatRetour") || typeFacture.equals("AchatPreparationCommande")) {
                do_dateBC = doDate;
                do_dateBL = doDate;
                do_datePL = "1900-01-01";
                DL_QtePL = 0;
                DL_QteBL = dlQte;
                DL_CMUP = AR_PrixAch;
                mvtStock = 1;
            }
            AR_PrixAch = (double)Math.round((double)AR_PrixAch*100)/100;
            DL_CMUP = (double)Math.round((double)DL_CMUP*100)/100;

            FDocLigne fDocLigne = new FDocLigne();
            initVariables(fDocLigne);
            fDocLigne.setDL_MvtStock(mvtStock);
            fDocLigne.setDO_Domaine(doDomaine);
            fDocLigne.setDO_Type(doType);
            fDocLigne.setCT_Num(CT_Num);
            fDocLigne.setDO_Piece(fDocEntete.getDO_Piece());
            fDocLigne.setDO_Date(doDate);

            fDocLigne.setDO_Ref(doRef);
            fDocLigne.setAR_Ref(AR_Ref);
            fDocLigne.setDL_Design(AR_Design);
            fDocLigne.setDL_Qte(dlQte);
            fDocLigne.setDL_QteBC(DL_QteBC);
            fDocLigne.setDL_QteBL(DL_QteBL);
            fDocLigne.setEU_Qte(EU_Qte);
            fDocLigne.setDL_Remise01REM_Valeur(Double.parseDouble(remise));
            fDocLigne.setDL_PrixUnitaire(pu);
            fDocLigne.setDL_Taxe1(taxe1);
            fDocLigne.setDL_Taxe2(taxe2);
            fDocLigne.setDL_Taxe3(taxe3);
            fDocLigne.setCO_No(coNo);
            fDocLigne.setDL_PrixRU(AR_PrixAch);
            fDocLigne.setEU_Enumere(U_Intitule);
            fDocLigne.setDE_No(deNo);
            fDocLigne.setDL_PUTTC(puttc);
            fDocLigne.setCA_Num(caNum);
            fDocLigne.setDL_MontantHT(montantHT);
            fDocLigne.setDL_MontantTTC(DL_MontantTTC);
            fDocLigne.setDL_Remise01REM_Type(type_remise);
            fDocLigne.setDL_QtePL(DL_QtePL);
            fDocLigne.setDL_TypePL(dl_typl);
            fDocLigne.setDL_TTC(typeHT);
            fDocLigne.setDL_TypeTaux1(TypeTauxTaxe1);
            fDocLigne.setDL_TypeTaux2(TypeTauxTaxe2);
            fDocLigne.setDL_TypeTaux3(TypeTauxTaxe3);
            fDocLigne.setDL_TypeTaxe1(TypeTaxe1);
            fDocLigne.setDL_TypeTaxe2(TypeTaxe2);
            fDocLigne.setDL_TypeTaxe3(TypeTaxe3);
            fDocLigne.setDL_PieceBL(entetePrev);
            fDocLigne.setDL_DateBC(do_dateBC);
            fDocLigne.setDL_DateBL(do_dateBL);
            fDocLigne.setDL_CMUP(DL_CMUP);
            fDocLigne.setDL_DatePL(do_datePL);
            fDocLigne.setMACHINEPC(machineName);
            fDocLigne.setCbCreateur(String.valueOf(protNo));

            if (typeFacture.equals("PreparationCommande") || typeFacture.equals("AchatPreparationCommande"))
                fDocLigne.setDL_NoColis(String.valueOf(fDocEntete.getN_CatCompta()));
            insertLigne(fDocLigne);
            this.getLigneFactureDernierElement(fDocLigne);
            if (typeFacture.equals("Achat") || typeFacture.equals("AchatRetour"))
                dlQte = -dlQte;
            if(fArticle.getAR_SuiviStock()!=0)
                majStock(fArticle, deNo, typeFacture, -dlQte, -(AR_PrixAch * dlQte),"insertLigne",String.valueOf(protNo));
            if(typeFacture.equals("AchatPreparationCommande")){
                insertFligneA(fDocLigne.getCbMarq(),1,fDocLigne.getCA_Num(),fDocLigne.getDL_MontantHT(),fDocLigne.getDL_Qte());
            }
            if (typeFacture.equals("PreparationCommande") || typeFacture.equals("AchatPreparationCommande"))
                updateDLColis(fDocEntete.getN_CatCompta(), fDocLigne.getCbMarq());

            return fDocLigne;

        }
        return null;
    }

    public void majStock (FArticle fArticle,int deNo,String typeFacture,double qte,double montant,String action,String protNo){
        if (!typeFacture.equals("PreparationCommande")  && !typeFacture.equals("Devis") && !typeFacture.equals("Avoir"))
            fArtStockDAO.updateFArtstock(fArticle.getAR_Ref(),deNo,montant,qte,action,protNo);

        if (typeFacture.equals("PreparationCommande")) {
            fArtStockDAO.updateFArtstockReel(fArticle.getAR_Ref(),deNo, qte);
        }

        if(typeFacture.equals("BonCommande")){
            fArtStockDAO.updateArtStockQteRes(fArticle.getAR_Ref(),deNo,qte,protNo);
        }
    }

    public void setLigneEntree(){

    }

    public static int mapParams(PreparedStatement ps, Object... args) throws SQLException {
        int i = 1;
        for (Object arg : args) {
            if (arg instanceof Date) {
                ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Long) {
                ps.setLong(i++, (Long) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (double) arg);
            } else {
                ps.setString(i++, (String) arg);
            }
        }
        return 0;
    }


    public void supprTransfertDetail (BigDecimal cbMarq,BigDecimal cbMarqSec,String protNo){
        FDocLigne fDocLigne = getFDocLigne(cbMarq);
        FDocLigne fDocLigneSec = getFDocLigne(cbMarqSec);
        FArtStockDAO fArtStockDAO = new FArtStockDAO(this.getDataSource());
        fArtStockDAO.updateFArtstock(fDocLigne.getAR_Ref(), fDocLigne.getDE_No(),(fDocLigne.getDL_CMUP()*fDocLigne.getDL_Qte()),fDocLigne.getDL_Qte(),"SupprLigne",protNo);
        fArtStockDAO.updateFArtstock(fDocLigneSec.getAR_Ref(), fDocLigneSec.getDE_No(),(fDocLigneSec.getDL_CMUP()*fDocLigneSec.getDL_Qte()),fDocLigneSec.getDL_Qte(),"SupprLigne",protNo);
        deleteLigne(cbMarq);
        deleteLigne(cbMarqSec);
    }

    public Object ajoutLigneTransfertDetail(double qte,double prix,double qteDest,double prixDest,BigDecimal cbMarqEntete,int protNo,String acte,String arRef,String arRefDest,String machineName) {
        FDocLigne fDocLigne = new FDocLigne();
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        ArrayList<BigDecimal> liste = fDocEnteteDAO.enteteTransfertDetailCbMarq(cbMarqEntete);
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(liste.get(0));
        FDocEntete fDocEnteteSecond = fDocEnteteDAO.getFDocEntete(liste.get(1));
        int deNo = 0;
        int deNoSecond = 0;
        if(fDocEntete.getDO_Type()==41)
            deNo = fDocEntete.getDE_No();
        if(fDocEnteteSecond.getDO_Type()==41)
            deNo = fDocEnteteSecond.getDE_No();
        if(fDocEntete.getDO_Type()==40)
            deNoSecond = fDocEntete.getDE_No();
        if(fDocEnteteSecond.getDO_Type()==40)
            deNoSecond = fDocEnteteSecond.getDE_No();

        if(acte.equals("ajout_ligne")){
            this.addDocligneTransfertDetailProcess(4,41,cbMarqEntete,arRef,prix,qte,3,deNo,machineName,protNo);
            fDocLigne = this.addDocligneTransfertDetailProcess(4,40,cbMarqEntete,arRefDest, prixDest,qteDest,1,deNoSecond,machineName,protNo);
            FArtStockDAO fArtStockDAO = new FArtStockDAO(this.getDataSource());
            fArtStockDAO.updateFArtstock(arRef,deNo,-(prix*qte),-qte,"insertLigne",String.valueOf(protNo));
            fArtStockDAO.updateFArtstock(arRefDest,deNoSecond,-(prixDest*qteDest),-qteDest,"insertLigne",String.valueOf(protNo));
        }
        return null;
    }


    public FDocLigne addDocligneTransfertDetailProcess(int doDomaine,int doType,BigDecimal cbMarqEntete,String arRef,double prix,double dlQte, int mvtStock,int deNo,String machine,int protNo)
    {
        FArticleDAO fArticleDAO = new FArticleDAO(this.getDataSource());
        FArticle fArticle = fArticleDAO.getF_Article(arRef);
        double arPrixAch = prix;

        double montantHT = Math.round(arPrixAch * dlQte);
        String uIntitule = "";
        if (fArticle.getAR_UniteVen() != null)
            uIntitule = (pUniteDAO.getUniteByIndice(fArticle.getAR_UniteVen())).getU_Intitule();

        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
        FDocLigne fDocLigne = new FDocLigne();
        initVariables(fDocLigne);
        fDocLigne.setDL_MvtStock(mvtStock);
        fDocLigne.setDO_Domaine(doDomaine);
        fDocLigne.setDO_Type(doType);
        fDocLigne.setCT_Num(fDocEntete.getDO_Tiers());
        fDocLigne.setDO_Piece(fDocEntete.getDO_Piece());
        fDocLigne.setDO_Date(fDocEntete.getDO_Date());
        fDocLigne.setDO_Ref(fDocEntete.getDO_Ref());
        fDocLigne.setAR_Ref(arRef);
        fDocLigne.setDL_Design(fArticle.getAR_Design());
        fDocLigne.setDL_Qte(dlQte);
        fDocLigne.setDL_QteBC(dlQte);
        fDocLigne.setDL_QteBL(0);
        fDocLigne.setEU_Qte(dlQte);
        fDocLigne.setDL_Remise01REM_Valeur(0);
        fDocLigne.setDL_PrixUnitaire(arPrixAch);
        fDocLigne.setDL_Taxe1(0);
        fDocLigne.setDL_Taxe2(0);
        fDocLigne.setDL_Taxe3(0);
        fDocLigne.setCO_No(0);
        fDocLigne.setDL_PrixRU(arPrixAch);
        fDocLigne.setEU_Enumere(uIntitule);
        fDocLigne.setDE_No(deNo);
        fDocLigne.setDL_PUTTC(arPrixAch);
        fDocLigne.setCA_Num(fDocEntete.getCA_Num());
        fDocLigne.setDL_MontantHT(montantHT);
        fDocLigne.setDL_MontantTTC(montantHT);
        fDocLigne.setDL_Remise01REM_Type(0);
        fDocLigne.setDL_QtePL(0);
        fDocLigne.setDL_QteBL(0);
        fDocLigne.setDL_TypePL(0);
        fDocLigne.setDL_TTC(0);
        fDocLigne.setDL_TypeTaux1(0);
        fDocLigne.setDL_TypeTaux2(0);
        fDocLigne.setDL_TypeTaux3(0);
        fDocLigne.setDL_TypeTaxe1(0);
        fDocLigne.setDL_TypeTaxe2(0);
        fDocLigne.setDL_TypeTaxe3(0);
        fDocLigne.setDL_PieceBL("");
        fDocLigne.setDL_DateBC("1900-01-01");
        fDocLigne.setDL_DateBL(fDocEntete.getDO_Date());
        fDocLigne.setDL_CMUP(arPrixAch);
        fDocLigne.setDL_DatePL("1900-01-01");
        fDocLigne.setMACHINEPC(machine);
        fDocLigne.setCbCreateur(String.valueOf(protNo));
        insertLigne(fDocLigne);
        return fDocLigne;
    }

    public Object ajoutLigneTransfert(double qte,double prix,String typeFacture,BigDecimal cbMarq,BigDecimal cbMarqEntete,int protNo,String acte,String arRef,String machineName){
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
        FProtectioncialDAO fProtectioncialDAO = new FProtectioncialDAO(this.getDataSource());
        FProtectioncial fProtectioncial = fProtectioncialDAO.connexionProctectionByProtNo(protNo);
        int isVisu = fDocEnteteDAO.isVisu(cbMarqEntete,protNo,typeFacture);

        if (isVisu==0) {
            if (acte.equals("ajout_ligne")) {
                double qteSource = 0;
                double prixSource = 0;
                FArtStockDAO fArtStockDAO = new FArtStockDAO(this.getDataSource());
                FArtStock fArtStock = fArtStockDAO.isStock(arRef,fDocEntete.getDE_No());
                FDepotDAO fDepotDAO = new FDepotDAO(this.getDataSource());
                FDepot fDepot = fDepotDAO.getFDepotObject(fDocEntete.getDE_No());
                if (fArtStock.getCbMarq() == null) {
                    return "Le dépot "+fDepot.getDeIntitule()+" n'a pas de stock pour l'article "+arRef+" !";
                } else {
                    prixSource = fArtStock.getAS_MontSto();
                    qteSource = fArtStock.getAS_QteSto();
                    cbMarq = addDocligneTransfertProcess(arRef, Double.valueOf(Math.round((prixSource / qteSource)*100))/100, qte, 3, machineName, cbMarqEntete, protNo, BigDecimal.ZERO);
                    if (typeFacture.equals("Transfert"))
                        cbMarq = addDocligneTransfertProcess(arRef, prix, qte, 1, machineName, cbMarqEntete,protNo, cbMarq);
                    if (typeFacture.equals("Transfert_confirmation"))
                        addDocligneTransfertConfirmationProcess(arRef, prix, qte, cbMarqEntete, cbMarq);

                        /*if ($typefac == "Transfert")
                            echo json_encode($var2);

                        if ($typefac == "Transfert_confirmation") {
                            $data = array('entete' => "", 'cbMarq' => $var1->cbMarq);
                            echo json_encode($data);
                        }*/
                }
            } else {
                //$docligne->alerteCumulStock();
                //$docligne->majQteZLigneConfirmation($cbMarq, $qte);
                //$data = $docligne->getZLigneConfirmation($cbMarq);
                //echo json_encode($data);
            }
        }
//        $protectionClass = new ProtectionClass("","",$objet->db);
//        $html = $protectionClass->alerteTransfert();
//        if($html !="")
//        $objet->envoiMail($html, "Liste des transferts à une ligne", "info@it-solution-sarl.com");
        return null;
    }


    public void addDocligneTransfertConfirmationProcess(String arRef, double prix, double dlQte, BigDecimal cbMarqEntete, BigDecimal cbFirst)
    {
        String sql = FDocLigneMapper.addDocligneTransfertConfirmationProcess;
        params = new Object[]{arRef,prix,dlQte,cbMarqEntete,cbFirst};
        this.getJdbcTemplate().update(sql,params);

    }
    public BigDecimal addDocligneTransfertProcess(String arRef, double prix, double dlQte, int mvtStock, String machineName, BigDecimal cbMarqEntete, int protNo, BigDecimal cbFirst)
    {
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
        String ctNum = fDocEntete.getDO_Tiers();
        int deNo = fDocEntete.getDE_No();
        FArticleDAO fArticleDAO = new FArticleDAO(this.getDataSource());
        FArticle fArticle = fArticleDAO.getF_Article(arRef);
        double arPrixAch = prix;
        String arDesign = fArticle.getAR_Design();
        int arUniteVen = fArticle.getAR_UniteVen();

        double montantHT = Double.valueOf(Math.round ((arPrixAch) * dlQte*100))/100;
        PUniteDAO pUniteDAO = new PUniteDAO(this.getDataSource());
        String uIntitule = (pUniteDAO.getUniteByIndice(fArticle.getAR_UniteVen())).getU_Intitule();

        if (mvtStock == 1)
            deNo = Integer.valueOf(fDocEntete.getDO_Tiers());
        else {
            FArtStockDAO fArtStockDAO = new FArtStockDAO(this.getDataSource());
            FArtStock fArtStock = fArtStockDAO.isStock(arRef,fDocEntete.getDE_No());
            arPrixAch = fArtStock.getAS_MontSto() / fArtStock.getAS_QteSto();
        }

        String doDate =fDocEntete.getDO_Date();
        FDocLigne fDocLigne = new FDocLigne();
        initVariables(fDocLigne);
        fDocLigne.setDL_MvtStock(mvtStock);
        fDocLigne.setDO_Domaine(fDocEntete.getDO_Domaine());
        fDocLigne.setDO_Type(fDocEntete.getDO_Type());
        fDocLigne.setCT_Num(ctNum);
        fDocLigne.setDO_Piece(fDocEntete.getDO_Piece());
        fDocLigne.setDO_Date(doDate);
        fDocLigne.setDO_Ref(fDocEntete.getDO_Ref());
        fDocLigne.setAR_Ref(fArticle.getAR_Ref());
        fDocLigne.setDL_Design(fArticle.getAR_Design());
        fDocLigne.setDL_Qte(dlQte);
        fDocLigne.setDL_QteBC(dlQte);
        fDocLigne.setDL_QteBL(0);
        fDocLigne.setEU_Qte(dlQte);
        fDocLigne.setDL_Remise01REM_Valeur(0);
        fDocLigne.setDL_PrixUnitaire(Double.valueOf(Math.round(arPrixAch*100))/100);
        fDocLigne.setDL_Taxe1(0);
        fDocLigne.setDL_Taxe2(0);
        fDocLigne.setDL_Taxe3(0);
        fDocLigne.setCO_No(0);
        fDocLigne.setDL_PrixRU(Double.valueOf(Math.round(arPrixAch*100))/100);
        fDocLigne.setEU_Enumere(uIntitule);
        fDocLigne.setDE_No(deNo);
        fDocLigne.setDL_PUTTC(Double.valueOf(Math.round(arPrixAch*100))/100);
        fDocLigne.setCA_Num(fDocEntete.getCA_Num());
        fDocLigne.setDL_MontantHT(montantHT);
        fDocLigne.setDL_MontantTTC(montantHT);
        fDocLigne.setDL_Remise01REM_Type(0);
        fDocLigne.setDL_QtePL(0);
        fDocLigne.setDL_TypePL(0);
        fDocLigne.setDL_TTC(0);
        fDocLigne.setDL_TypeTaux1(0);
        fDocLigne.setDL_TypeTaux2(0);
        fDocLigne.setDL_TypeTaux3(0);
        fDocLigne.setDL_TypeTaxe1(0);
        fDocLigne.setDL_TypeTaxe2(0);
        fDocLigne.setDL_TypeTaxe3(0);
        fDocLigne.setDL_PieceBL("");
        fDocLigne.setDL_DateBC("1900-01-01");
        fDocLigne.setDL_DateBL(doDate);
        fDocLigne.setDL_CMUP(Double.valueOf(Math.round(arPrixAch*100))/100);
        fDocLigne.setDL_DatePL("1900-01-01");
        fDocLigne.setMACHINEPC(machineName);
        fDocLigne.setCbCreateur(String.valueOf(protNo));
        insertLigne(fDocLigne);
        LogFileDAO logFileDAO = new LogFileDAO(this.getDataSource());

        deNo = Integer.valueOf(fDocEntete.getDO_Tiers());
        if (mvtStock == 3)
            deNo = fDocEntete.getDE_No();
        FArtStock fArtStock = fArtStockDAO.isStock(arRef,deNo);
        FArtStock fArtStockRepart = fArtStockDAO.isStock(arRef,fDocEntete.getDE_No());
        if (mvtStock == 1) {
            double qteTransfert = dlQte;
            double montantTransfert = (Double.valueOf(Math.round(arPrixAch*100))/100) * dlQte;

            if (fArtStock.getCbMarq() == null)
                fArtStockDAO.insertF_ArtStock(arRef,deNo,montantTransfert,qteTransfert,String.valueOf(protNo));
            else
                fArtStockDAO.updateFArtstock(arRef,deNo,montantTransfert,qteTransfert,"insertLigne",String.valueOf(protNo));
        } else {
            double prixSource = fArtStockRepart.getAS_MontSto();
            double qteSource = fArtStockRepart.getAS_QteSto();
            double value = Double.valueOf(Math.round((prixSource / qteSource)*100))/100;
            double qteTransfert = -dlQte;
            double montantTransfert = value * -dlQte;
            fArtStockDAO.updateFArtstock(arRef,deNo,montantTransfert,qteTransfert,"insertLigne",String.valueOf(protNo));
        }
        return fDocLigne.getCbMarq();
    }

    public void majLigne(FDocLigne fDocLigne){
        String sql = FDocLigneMapper.updateLigne;
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(fDocLigne.getDL_Qte());
        params.add(fDocLigne.getDL_QteBC());
        params.add(fDocLigne.getDL_QteBL());
        params.add(fDocLigne.getEU_Qte());
        params.add(fDocLigne.getDL_Remise01REM_Valeur());
        params.add(fDocLigne.getDL_PrixUnitaire());
        params.add(fDocLigne.getDL_Taxe1());
        params.add(fDocLigne.getDL_Taxe2());
        params.add(fDocLigne.getDL_Taxe3());
        params.add(fDocLigne.getDL_PrixRU());
        params.add(fDocLigne.getDL_PUTTC());
        params.add(fDocLigne.getDL_MontantHT());
        params.add(fDocLigne.getDL_MontantTTC());
        params.add(fDocLigne.getDL_Remise01REM_Type());
        params.add(fDocLigne.getDL_QtePL());
        params.add(fDocLigne.getDL_TTC());
        params.add(fDocLigne.getDL_TypeTaux1());
        params.add(fDocLigne.getDL_TypeTaux2());
        params.add(fDocLigne.getDL_TypeTaux3());
        params.add(fDocLigne.getDL_TypeTaxe1());
        params.add(fDocLigne.getDL_TypeTaxe2());
        params.add(fDocLigne.getDL_TypeTaxe3());
        params.add(fDocLigne.getDL_CMUP());
        params.add(fDocLigne.getMACHINEPC());
        params.add(fDocLigne.getCbCreateur());
        params.add(fDocLigne.getCbMarq());

        this.getJdbcTemplate().update(sql, params.toArray());
    }
    public void insertLigne(FDocLigne fDocLigne) {
        String sql = FDocLigneMapper.insertDocLigne;

        params = new Object[]{fDocLigne.getDO_Domaine(), fDocLigne.getDO_Type(), fDocLigne.getCT_Num(), fDocLigne.getDO_Piece(), fDocLigne.getDL_PieceBC(), fDocLigne.getDL_PieceBL(), fDocLigne.getDO_Date()
                , fDocLigne.getDL_DateBC(), fDocLigne.getDL_DateBL(), fDocLigne.getDO_Ref(), fDocLigne.getDL_TNomencl(), fDocLigne.getDL_TRemPied(), fDocLigne.getDL_TRemExep(), fDocLigne.getAR_Ref()
                , fDocLigne.getDL_Design(), fDocLigne.getDL_Qte(), fDocLigne.getDL_QteBC(), fDocLigne.getDL_QteBL(), fDocLigne.getDL_PoidsNet(), fDocLigne.getDL_PoidsBrut()
                , fDocLigne.getDL_Remise01REM_Valeur(), fDocLigne.getDL_Remise01REM_Type(), fDocLigne.getDL_Remise02REM_Valeur(), fDocLigne.getDL_Remise02REM_Type()
                , fDocLigne.getDL_Remise03REM_Valeur(), fDocLigne.getDL_Remise03REM_Type(), fDocLigne.getDL_PrixUnitaire(), fDocLigne.getDL_PUBC(), fDocLigne.getDL_Taxe1(), fDocLigne.getDL_TypeTaux1(), fDocLigne.getDL_TypeTaxe1()
                , fDocLigne.getDL_Taxe2(), fDocLigne.getDL_TypeTaux2(), fDocLigne.getDL_TypeTaxe2(), fDocLigne.getDL_Taxe3(), fDocLigne.getDL_TypeTaux3(), fDocLigne.getDL_TypeTaxe3(), fDocLigne.getCO_No()
                , fDocLigne.getAG_No1(), fDocLigne.getAG_No2(), fDocLigne.getDL_PrixRU(), fDocLigne.getDL_CMUP(), fDocLigne.getDL_MvtStock(), fDocLigne.getDT_No(), fDocLigne.getAF_RefFourniss()
                , fDocLigne.getEU_Enumere(), fDocLigne.getEU_Qte(), fDocLigne.getDL_TTC(), fDocLigne.getDE_No(), fDocLigne.getDL_NoRef(), fDocLigne.getDL_TypePL(), fDocLigne.getDL_PUDevise()
                , fDocLigne.getDL_PUTTC(), fDocLigne.getDO_DateLivr(), fDocLigne.getCA_Num(), fDocLigne.getDL_Frais(), fDocLigne.getDL_Valorise(), fDocLigne.getDL_NonLivre(), fDocLigne.getAC_RefClient()
                , fDocLigne.getDL_MontantHT(), fDocLigne.getDL_MontantTTC(), fDocLigne.getDL_FactPoids(), fDocLigne.getDL_Escompte(), fDocLigne.getDL_PiecePL(), fDocLigne.getDL_DatePL()
                , fDocLigne.getDL_QtePL(), fDocLigne.getDL_NoColis(), fDocLigne.getDL_NoLink(), fDocLigne.getDL_QteRessource(), fDocLigne.getDL_DateAvancement(), fDocLigne.getCbCreateur(), fDocLigne.getMACHINEPC()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql,params); // Not update, you're returning a ResultSet.
        while (sqlRowSet.next()) {
            fDocLigne.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
        }
    }

    public Object getLigneFactureDernierElement(FDocLigne fDocLigne)
    {
        String sql = FDocLigneMapper.getLigneFactureDernierElement;
        params = new Object[] {fDocLigne.getCbMarq()};
        return this.getJdbcTemplate().queryForObject(sql, params,mapper);
    }

    public void insertFligneA(BigDecimal cbMarq,int nAnalytique,String caNum,Double eaMontant,Double eaQte){
        String sql = FDocLigneMapper.insertFligneA;
        params = new Object[]{cbMarq,nAnalytique,caNum,eaMontant,eaQte};
        this.getJdbcTemplate().update(sql,params);
    }
    public Object verifbornePrix(int protNo, double pxMin, double pxMax, double DL_PUNetTTC,String typeFacture,int catTarif, String arRef,int doDomaine)
    {
        FProtectioncial fProtectioncial = fProtectioncialDAO.connexionProctectionByProtNo(protNo);
        PParametrecial pParametrecial = pParametrecialDAO.getObject();
        PCommunication pCommunication = (new PCommunicationDAO(this.getDataSource())).getPCommunication();
        FArtClient fArtClient = (new FArtClientDAO(this.getDataSource())).getFArtClient(arRef,catTarif);

        net.minidev.json.JSONObject json = new net.minidev.json.JSONObject();
        int flag_minMax = 0;
        if (pParametrecial.getP_GestionPlanning() == 1 || pParametrecial.getP_ReportPrixRev() == 1)
            flag_minMax = 1;
        if (((typeFacture.equals("Vente") || typeFacture.equals("Devis") || typeFacture.equals("BonLivraison")) && fProtectioncial.getPROT_Right() != 1 && flag_minMax == 1)
                || (pxMin == 0 && pxMax == 0)
        ) {
            if(pCommunication.getN_CatTarif()==3){
                if(catTarif>=2){
                    pxMin = fArtClient.getAC_PrixVen();
                    pxMax = fArtClient.getAC_Coef();
                }
            }
            if((pxMin != 0 && pxMax != 0) &&
                ((catTarif>=2) && (DL_PUNetTTC<pxMin || DL_PUNetTTC > pxMax)
                || (DL_PUNetTTC < pxMin))){
                json.put("message", "Le prix doit être compris entre " + pxMin + " et " + pxMax +" !");
                return json;
            }

            if(catTarif != 1){
                if(doDomaine == 0 && catTarif >1 && ((DL_PUNetTTC == fArtClient.getAC_Coef() && DL_PUNetTTC < fArtClient.getAC_Remise()))
                || (DL_PUNetTTC < fArtClient.getAC_Coef())) {
                    if(DL_PUNetTTC < fArtClient.getAC_Coef()){
                        json.put("message", "Le montant saisie ("+DL_PUNetTTC+") est inférieur au montant minimum ("+fArtClient.getAC_Coef()+")");
                        return json;
                    }
                    else{
                        json.put("message", "La quantité saisie ("+DL_PUNetTTC+")  est inférieure à la quantité minimum ("+fArtClient.getAC_Remise()+")");
                        return json;
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Object> getPrixClientHT(String ar_ref, int catcompta, int cattarif, double prix,double rem,double qte,int fournisseur) {
        String sql = FDocLigneMapper.getPrixClientHT;
        params = new Object[] {prix,rem,ar_ref,catcompta,cattarif,qte,fournisseur};
        ArrayList<Object> tab =new ArrayList<Object>();
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            tab.add(sqlRowSet.getDouble("Prix_Min"));
            tab.add(sqlRowSet.getDouble("Prix_Max"));
            tab.add(sqlRowSet.getDouble("DL_PUNetTTC"));
            tab.add(sqlRowSet.getDouble("DL_MontantHT"));
            tab.add(sqlRowSet.getDouble("DL_PrixUnitaire"));
            tab.add(sqlRowSet.getDouble("taxe1"));
            tab.add(sqlRowSet.getDouble("taxe2"));
            tab.add(sqlRowSet.getDouble("taxe3"));
            tab.add(sqlRowSet.getDouble("TU_TA_Type"));
            tab.add(sqlRowSet.getDouble("TD_TA_Type"));
            tab.add(sqlRowSet.getDouble("TT_TA_Type"));
            tab.add(sqlRowSet.getDouble("TU_TA_TTaux"));
            tab.add(sqlRowSet.getDouble("TD_TA_TTaux"));
            tab.add(sqlRowSet.getDouble("TT_TA_TTaux"));
            tab.add(sqlRowSet.getDouble("DL_MontantTTC"));
            tab.add(sqlRowSet.getDouble("DL_PUTTC"));
            tab.add(sqlRowSet.getInt("AC_PrixTTC"));
            tab.add(sqlRowSet.getString("IntituleT1"));
            tab.add(sqlRowSet.getString("IntituleT2"));
            tab.add(sqlRowSet.getString("IntituleT3"));
        }
        return tab;
    }

    public List<Object> getPrixClientHTJSON(String ar_ref, int catcompta, int cattarif, double prix,double rem,double qte,int fournisseur) {
        String sql = FDocLigneMapper.getPrixClientHT;
        params = new Object[] {prix,rem,ar_ref,catcompta,cattarif,qte,fournisseur};
        return this.getJdbcTemplate().query(sql, params,mapper);
    }


    public String getCbCreateurName(BigDecimal cbMarq){
        String sql = FDocLigneMapper.getCbCreateurName;
        params = new Object[] {cbMarq};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getString("Prot_User");
        }
        return "";
    }

    public void deleteLigne(BigDecimal cbMarq){
        String sql = FDocLigneMapper.deleteLigne;
        params = new Object[] {cbMarq};
        this.getJdbcTemplate().update(sql, params);
    }


    public BigDecimal getCbMarqEntete(BigDecimal cbMarq){
        String sql = FDocLigneMapper.getCbMarqEntete;
        FDocLigne fDocLigne = getFDocLigne(cbMarq);
        params = new Object[] {fDocLigne.getDO_Type(),fDocLigne.getDO_Domaine(),fDocLigne.getDO_Piece()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getBigDecimal("cbMarq");
        }
        return null;
    }

    public void majLigneByCbMarq(String champ,String value,int cbMarq,int protNo){
        String sql = "DECLARE @cbMarq INT = "+cbMarq+";\n" +
                "                    DECLARE @protNo INT = "+protNo+";\n" +
                "                    DECLARE @value NVARCHAR(50) = '"+value+"';\n" +
                "\n" +
                "                    UPDATE F_DOCENTETE \n" +
                "\t\t\t\t\t\tSET "+champ+" = @value\n" +
                "\t\t\t\t\t\t\t,cbCreateur = protNo\n" +
                "\t\t\t\t\tWHERE cbMarq = @cbMarq;\n" +
                "\t\t\t\t\tUPDATE F_DOCLIGNE \n" +
                "\t\t\t\t\t\tSET F_DOCLIGNE."+champ+" = @value \n" +
                "\t\t\t\t\t\t\t,cbCreateur = @protNo\n" +
                "\t\t\t\t\tFROM \tF_DOCENTETE\n" +
                "\t\t\t\t\tWHERE\tF_DOCENTETE.DO_Domaine = F_DOCLIGNE.DO_Domaine  \n" +
                "\t\t\t\t\tAND \tF_DOCENTETE.DO_Type = F_DOCLIGNE.DO_Type  \n" +
                "\t\t\t\t\tAND \tF_DOCENTETE.cbDO_Piece = F_DOCLIGNE.cbDO_Piece \n" +
                "\t\t\t\t\tAND \tF_DOCENTETE.cbMarq=@cbMarq";
        this.getJdbcTemplate().execute(sql);
    }



    public double val_remise(double remise, int type_remise, double prix)
    {
        double val_remise = 0;
        if (type_remise == 2)
            val_remise = remise;
        if (type_remise == 1)
            val_remise = prix * remise / 100;
        return val_remise;
    }

    public Object getFDocligneJSON (BigDecimal cbMarq){
        String sql = FDocLigneMapper.getFDocLigne;
        params = new Object[] {cbMarq};
        return this.getJdbcTemplate().query(sql, params,mapper);
    }

    public Object verifSupprAjout (BigDecimal cbMarq){

        FDocLigne fDocLigne = getFDocLigne (cbMarq);
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocEntete fDocEntete= fDocEnteteDAO.getFDocEntete(getCbMarqEntete(cbMarq));
        FArtStockDAO fArtStockDAO = new FArtStockDAO(this.getDataSource());
        return fArtStockDAO.isStockJSON(fDocLigne.getAR_Ref(),fDocEntete.getDE_No());
    }


    public FDocLigne getFDocLigne(BigDecimal cbMarq) {
        String sql = FDocLigneMapper.getFDocLigne;
        params = new Object[] {cbMarq};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FDocLigne fDocLigne = new FDocLigne();
        while(sqlRowSet.next()) {
            fDocLigne.setDO_Domaine(sqlRowSet.getInt("DO_Domaine"));
            fDocLigne.setDO_Type(sqlRowSet.getInt("DO_Type"));
            fDocLigne.setCO_No(sqlRowSet.getInt("CO_No"));
            fDocLigne.setCbCreateur(sqlRowSet.getString("cbCreateur"));
            fDocLigne.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
            fDocLigne.setCbModification(sqlRowSet.getString("cbModification"));
            fDocLigne.setCO_No(sqlRowSet.getInt("CO_No"));
            fDocLigne.setCA_Num(sqlRowSet.getString("CA_Num"));
            fDocLigne.setCT_Num(sqlRowSet.getString("CT_Num"));
            fDocLigne.setDL_Qte(sqlRowSet.getDouble("DL_Qte"));
            fDocLigne.setDE_No(sqlRowSet.getInt("DE_No"));
            fDocLigne.setAR_Ref(sqlRowSet.getString("AR_Ref"));
            fDocLigne.setDL_CMUP(sqlRowSet.getDouble("DL_CMUP"));
            fDocLigne.setDL_PrixRU(sqlRowSet.getDouble("DL_PrixRU"));
            fDocLigne.setDL_PrixUnitaire(sqlRowSet.getDouble("DL_PrixUnitaire"));
            fDocLigne.setDL_MontantHT(sqlRowSet.getDouble("DL_MontantHT"));
            fDocLigne.setDL_MontantTTC(sqlRowSet.getDouble("DL_MontantTTC"));
            fDocLigne.setDL_MvtStock(sqlRowSet.getInt("DL_MvtStock"));
            fDocLigne.setDL_Remise01REM_Type(sqlRowSet.getInt("DL_Remise01REM_Type"));
            fDocLigne.setDL_Remise02REM_Type(sqlRowSet.getInt("DL_Remise02REM_Type"));
            fDocLigne.setDL_Remise03REM_Type(sqlRowSet.getInt("DL_Remise03REM_Type"));
            fDocLigne.setDL_Remise01REM_Valeur(sqlRowSet.getDouble("DL_Remise01REM_Valeur"));
            fDocLigne.setDL_Remise02REM_Valeur(sqlRowSet.getDouble("DL_Remise02REM_Valeur"));
            fDocLigne.setDL_Remise03REM_Valeur(sqlRowSet.getDouble("DL_Remise03REM_Valeur"));
            fDocLigne.setDL_TypeTaux1(sqlRowSet.getInt("DL_TypeTaux1"));
            fDocLigne.setDL_TypeTaux2(sqlRowSet.getInt("DL_TypeTaux2"));
            fDocLigne.setDL_TypeTaux3(sqlRowSet.getInt("DL_TypeTaux3"));
            fDocLigne.setDO_Date(sqlRowSet.getString("DO_Date"));
            fDocLigne.setDO_DateLivr(sqlRowSet.getString("DO_DateLivr"));
            fDocLigne.setDO_Type(sqlRowSet.getInt("DO_Type"));
            fDocLigne.setDO_Domaine(sqlRowSet.getInt("DO_Domaine"));
            fDocLigne.setDO_Piece(sqlRowSet.getString("DO_Piece"));
            fDocLigne.setDL_TTC(sqlRowSet.getInt("DL_TTC"));
            fDocLigne.setDL_NoColis(sqlRowSet.getString("DL_NoColis"));
            fDocLigne.setDO_Ref(sqlRowSet.getString("DO_Ref"));
            initRemise(fDocLigne);
        }
        return fDocLigne;
    }

    public void initVariables(FDocLigne fDocLigne)
    {
        fDocLigne.setDL_PieceBC("");
        fDocLigne.setDL_PieceBL("") ;
        fDocLigne.setDL_DateBC("1900-01-01");
        fDocLigne.setDL_TNomencl(0);
        fDocLigne.setDL_TRemPied(0);
        fDocLigne.setDL_TRemExep(0);
        fDocLigne.setDL_QteBL(0);
        fDocLigne.setDL_PoidsNet(0);
        fDocLigne.setDL_PoidsBrut(0);
        fDocLigne.setDL_Remise01REM_Valeur(0);
        fDocLigne.setDL_Remise01REM_Type(0);
        fDocLigne.setDL_Remise02REM_Valeur(0);
        fDocLigne.setDL_Remise02REM_Type(0);
        fDocLigne.setDL_Remise03REM_Valeur(0);
        fDocLigne.setDL_Remise03REM_Type(0);
        fDocLigne.setDL_PUBC(0);
        fDocLigne.setDL_Taxe1(0);
        fDocLigne.setDL_TypeTaux1(0);
        fDocLigne.setDL_TypeTaxe1(0);
        fDocLigne.setDL_Taxe2(0);
        fDocLigne.setDL_TypeTaux2(0);
        fDocLigne.setDL_TypeTaxe2(0);
        fDocLigne.setCO_No(0);
        fDocLigne.setAG_No1 (0);
        fDocLigne.setAG_No2(0);
        fDocLigne.setDT_No(0);
        fDocLigne.setAF_RefFourniss ("");
        fDocLigne.setEU_Enumere("");
        fDocLigne.setDL_TTC (0);
        fDocLigne.setDL_NoRef (1);
        fDocLigne.setDL_TypePL (0);
        fDocLigne.setDL_PUDevise (0);
        fDocLigne.setDL_No  (0);
        fDocLigne.setDO_DateLivr ("1900-01-01");
        fDocLigne.setCA_Num ("");
        fDocLigne.setDL_Taxe3(0);
        fDocLigne.setDL_TypeTaux3 (0);
        fDocLigne.setDL_TypeTaxe3 (0);
        fDocLigne.setDL_Frais (0);
        fDocLigne.setDL_Valorise (1);
        fDocLigne.setDL_NonLivre (0);
        fDocLigne.setAC_RefClient ("");
        fDocLigne.setDL_FactPoids (0);
        fDocLigne.setDL_Escompte(0);
        fDocLigne.setDL_PiecePL("");
        fDocLigne.setDL_DatePL("1900-01-01");
        fDocLigne.setDL_QtePL(0);
        fDocLigne.setDL_NoColis("");
        fDocLigne.setDL_NoLink(0);
        fDocLigne.setDL_QteRessource(0);
        fDocLigne.setDL_DateAvancement("1900-01-01");
    }


    public Object initRemise(FDocLigne fDocLigne) {
        String sql = FDocLigneMapper.initRemise;
        params = new Object[]{fDocLigne.getCbMarq()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while (sqlRowSet.next()) {
            fDocLigne.setDL_PUTTC_Rem0(sqlRowSet.getDouble("DL_PUTTC_Rem0"));
            fDocLigne.setDL_PrixUnitaire_Rem0(sqlRowSet.getDouble("DL_PrixUnitaire_Rem0"));
            fDocLigne.setDL_PUTTC_Rem(sqlRowSet.getDouble("DL_PUTTC_Rem"));
            fDocLigne.setDL_PrixUnitaire_Rem(sqlRowSet.getDouble("DL_PrixUnitaire_Rem"));
            fDocLigne.setDL_Remise(sqlRowSet.getString("DL_Remise"));
            fDocLigne.setMT_Taxe1(sqlRowSet.getDouble("MT_Taxe1"));
            fDocLigne.setMT_Taxe2(sqlRowSet.getDouble("MT_Taxe2"));
            fDocLigne.setMT_Taxe3(sqlRowSet.getDouble("MT_Taxe3"));
            return  this.getJdbcTemplate().query(sql, params,mapper);
        }
        return null;
    }



}
