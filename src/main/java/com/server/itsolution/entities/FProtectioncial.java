package com.server.itsolution.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class FProtectioncial implements Serializable {

    private BigDecimal cbMarq;
    private Integer CA_No,PROT_Right,Prot_No,PROT_CLIENT,PROT_FOURNISSEUR,PROT_COLLABORATEUR,PROT_FAMILLE,PROT_ARTICLE,PROT_DOCUMENT_STOCK,
            PROT_DOCUMENT_ACHAT ,PROT_DOCUMENT_VENTE,PROT_PX_ACHAT,PROT_PX_REVIENT,PROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE,
            PROT_DATE_VENTE,PROT_DATE_ACHAT,PROT_DATE_COMPTOIR,PROT_DATE_RGLT,PROT_DOCUMENT_VENTE_DEVIS,
            PROT_DOCUMENT_VENTE_FACTURE,PROT_DOCUMENT_VENTE_BLIVRAISON,PROT_DOCUMENT_VENTE_RETOUR,PROT_DOCUMENT_VENTE_AVOIR,
            PROT_DOCUMENT_ENTREE , PROT_DATE_STOCK, PROT_DOCUMENT_SORTIE,PROT_DOCUMENT_REGLEMENT ,PROT_MVT_CAISSE,PROT_QTE_NEGATIVE,
            PROT_SAISIE_REGLEMENT_FOURNISSEUR,PROT_DEPRECIATION_STOCK,PROT_SAISIE_REGLEMENT,PROT_DEPOT,PROT_RISQUE_CLIENT,
            PROT_SAISIE_INVENTAIRE,PROT_AFFICHAGE_VAL_CAISSE ,PROT_CTRL_TT_CAISSE,PROT_INFOLIBRE_ARTICLE,PROT_DATE_MVT_CAISSE
            ,PROT_GENERATION_RGLT_CLIENT,PROT_DOCUMENT_ACHAT_FACTURE,PROT_MODIF_SUPPR_COMPTOIR,PROT_APRES_IMPRESSION
            ,PROT_TICKET_APRES_IMPRESSION,PROT_AVANT_IMPRESSION, PROT_DOCUMENT_INTERNE_2,PROT_MODIFICATION_CLIENT
            ,PROT_ETAT_INVENTAIRE_PREP,PROT_ETAT_LIVRE_INV,PROT_ETAT_STAT_ARTICLE_PAR_ART,PROT_ETAT_STAT_ARTICLE_PAR_FAM
            ,PROT_ETAT_STAT_ARTICLE_PALMARES,PROT_ETAT_MVT_STOCK,PROT_ETAT_CLT_PAR_FAM_ART,PROT_ETAT_CLT_PAR_ARTICLE,PROT_ETAT_PALMARES_CLT
            ,PROT_ETAT_STAT_FRS_FAM_ART,PROT_ETAT_STAT_FRS
            ,PROT_GEN_ECART_REGLEMENT,PROT_ETAT_STAT_CAISSE_ARTICLE
            ,PROT_ETAT_STAT_CAISSE_FAM_ARTICLE,PROT_ETAT_CAISSE_MODE_RGLT
            ,PROT_ETAT_RELEVE_CPTE_CLIENT,PROT_ETAT_STAT_COLLAB_PAR_TIERS,PROT_DOCUMENT_ACHAT_RETOUR
            ,PROT_ETAT_STAT_COLLAB_PAR_ARTICLE,PROT_ETAT_STAT_COLLAB_PAR_FAMILLE,PROT_ETAT_STAT_FRS_PAR_FAMILLE,PROT_ETAT_STAT_FRS_PAR_ARTICLE
            ,PROT_ETAT_STAT_ACHAT_ANALYTIQUE,ProtectAdmin
            ,PROT_ETAT_RELEVE_ECH_CLIENT,PROT_ETAT_RELEVE_ECH_FRS,PROT_VENTE_COMPTOIR
            ,PROT_SAISIE_PX_VENTE_REMISE,PROT_TARIFICATION_CLIENT,PROT_Administrator
            ,PROT_CLOTURE_CAISSE,PROT_PLAN_COMPTABLE,PROT_PLAN_ANALYTIQUE,PROT_TAUX_TAXE,PROT_CODE_JOURNAUX,PROT_LISTE_BANQUE
            ,PROT_LISTE_MODELE_REGLEMENT,PROT_DOCUMENT_INTERNE_5
            ,PROT_DOCUMENT_VENTE_BONCOMMANDE
            ,PROT_REAPPROVISIONNEMENT
            ,PROT_VIREMENT_DEPOT;

    public Integer getPROT_VIREMENT_DEPOT() {
        return PROT_VIREMENT_DEPOT;
    }

    public void setPROT_VIREMENT_DEPOT(Integer PROT_VIREMENT_DEPOT) {
        this.PROT_VIREMENT_DEPOT = PROT_VIREMENT_DEPOT;
    }

    public Integer getPROT_REAPPROVISIONNEMENT() {
        return PROT_REAPPROVISIONNEMENT;
    }

    public void setPROT_REAPPROVISIONNEMENT(Integer PROT_REAPPROVISIONNEMENT) {
        this.PROT_REAPPROVISIONNEMENT = PROT_REAPPROVISIONNEMENT;
    }

    public Integer getPROT_CLOTURE_CAISSE() {
        return PROT_CLOTURE_CAISSE;
    }

    public void setPROT_CLOTURE_CAISSE(Integer PROT_CLOTURE_CAISSE) {
        this.PROT_CLOTURE_CAISSE = PROT_CLOTURE_CAISSE;
    }

    public Integer getPROT_PLAN_COMPTABLE() {
        return PROT_PLAN_COMPTABLE;
    }

    public void setPROT_PLAN_COMPTABLE(Integer PROT_PLAN_COMPTABLE) {
        this.PROT_PLAN_COMPTABLE = PROT_PLAN_COMPTABLE;
    }

    public Integer getPROT_PLAN_ANALYTIQUE() {
        return PROT_PLAN_ANALYTIQUE;
    }

    public void setPROT_PLAN_ANALYTIQUE(Integer PROT_PLAN_ANALYTIQUE) {
        this.PROT_PLAN_ANALYTIQUE = PROT_PLAN_ANALYTIQUE;
    }

    public Integer getPROT_TAUX_TAXE() {
        return PROT_TAUX_TAXE;
    }

    public void setPROT_TAUX_TAXE(Integer PROT_TAUX_TAXE) {
        this.PROT_TAUX_TAXE = PROT_TAUX_TAXE;
    }

    public Integer getPROT_CODE_JOURNAUX() {
        return PROT_CODE_JOURNAUX;
    }

    public void setPROT_CODE_JOURNAUX(Integer PROT_CODE_JOURNAUX) {
        this.PROT_CODE_JOURNAUX = PROT_CODE_JOURNAUX;
    }

    public Integer getPROT_LISTE_BANQUE() {
        return PROT_LISTE_BANQUE;
    }

    public void setPROT_LISTE_BANQUE(Integer PROT_LISTE_BANQUE) {
        this.PROT_LISTE_BANQUE = PROT_LISTE_BANQUE;
    }

    public Integer getPROT_LISTE_MODELE_REGLEMENT() {
        return PROT_LISTE_MODELE_REGLEMENT;
    }

    public void setPROT_LISTE_MODELE_REGLEMENT(Integer PROT_LISTE_MODELE_REGLEMENT) {
        this.PROT_LISTE_MODELE_REGLEMENT = PROT_LISTE_MODELE_REGLEMENT;
    }

    public Integer getPROT_DOCUMENT_INTERNE_5() {
        return PROT_DOCUMENT_INTERNE_5;
    }

    public void setPROT_DOCUMENT_INTERNE_5(Integer PROT_DOCUMENT_INTERNE_5) {
        this.PROT_DOCUMENT_INTERNE_5 = PROT_DOCUMENT_INTERNE_5;
    }

    private String PROT_User,PROT_Pwd,ProfilName,PROT_Description,PROT_PwdStatus, PROT_CBCREATEUR;

    public FProtectioncial() {
    }

    public FProtectioncial(String PROT_User,String PROT_Pwd) {
        this.PROT_User = PROT_User;
        this.PROT_Pwd = PROT_Pwd;
    }

    public Integer getProtectAdmin() {
        return ProtectAdmin;
    }

    public void setProtectAdmin(Integer protectAdmin) {
        ProtectAdmin = protectAdmin;
    }

    public BigDecimal getCbMarq() {
        return cbMarq;
    }

    public Integer getPROT_DOCUMENT_ACHAT_RETOUR() {
        return PROT_DOCUMENT_ACHAT_RETOUR;
    }

    public void setPROT_DOCUMENT_ACHAT_RETOUR(Integer PROT_DOCUMENT_ACHAT_RETOUR) {
        this.PROT_DOCUMENT_ACHAT_RETOUR = PROT_DOCUMENT_ACHAT_RETOUR;
    }

    public void setPROT_Administrator(Integer PROT_Administrator) {
        this.PROT_Administrator = PROT_Administrator;
    }

    public int setvalue(String type){
        int val = 0;
        if(type .equals("famille"))
            val=this.PROT_FAMILLE;
        if(type .equals("client"))
            val=this.PROT_CLIENT;
        if(type .equals("fournisseur"))
            val=this.PROT_FOURNISSEUR;
        if(type .equals("collaborateur"))
            val=this.PROT_COLLABORATEUR;
        if(type .equals("depot"))
            val=this.PROT_DEPOT;
        if(type .equals("article"))
            val=this.PROT_ARTICLE;
        if(type .equals("Vente") || type .equals("VenteC") || type .equals("VenteT"))
                val=this.PROT_DOCUMENT_VENTE_FACTURE;
        if(type .equals("Achat") || type .equals("AchatC") || type .equals("AchatT"))
                val=this.PROT_DOCUMENT_ACHAT_FACTURE;
        if(type .equals("AchatPreparationCommande") || type .equals("PreparationCommande"))
        val=this.PROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE;
        if(type .equals("Devis"))
            val=this.PROT_DOCUMENT_VENTE_DEVIS;
        if(type .equals("VenteRetour"))
            val=this.PROT_DOCUMENT_VENTE_RETOUR;
        if(type .equals("Avoir"))
            val=this.PROT_DOCUMENT_VENTE_AVOIR;
        if(type .equals("BonLivraison"))
            val=this.PROT_DOCUMENT_VENTE_BLIVRAISON;
        if(type .equals("Entree"))
            val=this.PROT_DOCUMENT_ENTREE;
        if(type .equals("Sortie"))
            val=this.PROT_DOCUMENT_SORTIE;
        if(type .equals("Transfert"))
            val=this.PROT_DEPRECIATION_STOCK;
        if(type.equals("Transfert_detail"))
            val=this.PROT_DOCUMENT_INTERNE_2;
        if(type .equals("ReglementClient"))
            val=this.PROT_SAISIE_REGLEMENT;
        if(type .equals("ReglementFournisseur"))
            val=this.PROT_SAISIE_REGLEMENT_FOURNISSEUR;
        if(type .equals("MvtCaisse"))
            val=this.PROT_MVT_CAISSE;
        if(type .equals("documentVente"))
            val=this.PROT_DOCUMENT_VENTE;
        if(type .equals("documentAchat"))
            val=this.PROT_DOCUMENT_ACHAT;
        if(type .equals("documentStock"))
            val=this.PROT_DOCUMENT_STOCK;
        if(type .equals("infoLibreArticle"))
            val=this.PROT_INFOLIBRE_ARTICLE;
        return val;
    }

    public int protectedType(String type){
        int val = this.setvalue(type);
        if (val != 0 && val != 3)
            return 0;
        return 1;
    }

    public void setCbMarq(BigDecimal cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Integer getCA_No() {
        return CA_No;
    }

    public void setCA_No(Integer CA_No) {
        this.CA_No = CA_No;
    }

    public Integer getPROT_Right() {
        return PROT_Right;
    }

    public void setPROT_Right(Integer PROT_Right) {
        this.PROT_Right = PROT_Right;
    }

    public Integer getProt_No() {
        return Prot_No;
    }

    public void setProt_No(Integer prot_No) {
        Prot_No = prot_No;
    }

    public Integer getPROT_CLIENT() {
        return PROT_CLIENT;
    }

    public void setPROT_CLIENT(Integer PROT_CLIENT) {
        this.PROT_CLIENT = PROT_CLIENT;
    }

    public Integer getPROT_FOURNISSEUR() {
        return PROT_FOURNISSEUR;
    }

    public void setPROT_FOURNISSEUR(Integer PROT_FOURNISSEUR) {
        this.PROT_FOURNISSEUR = PROT_FOURNISSEUR;
    }

    public Integer getPROT_COLLABORATEUR() {
        return PROT_COLLABORATEUR;
    }

    public void setPROT_COLLABORATEUR(Integer PROT_COLLABORATEUR) {
        this.PROT_COLLABORATEUR = PROT_COLLABORATEUR;
    }

    public Integer getPROT_FAMILLE() {
        return PROT_FAMILLE;
    }

    public void setPROT_FAMILLE(Integer PROT_FAMILLE) {
        this.PROT_FAMILLE = PROT_FAMILLE;
    }

    public Integer getPROT_ARTICLE() {
        return PROT_ARTICLE;
    }

    public void setPROT_ARTICLE(Integer PROT_ARTICLE) {
        this.PROT_ARTICLE = PROT_ARTICLE;
    }

    public Integer getPROT_DOCUMENT_STOCK() {
        return PROT_DOCUMENT_STOCK;
    }

    public void setPROT_DOCUMENT_STOCK(Integer PROT_DOCUMENT_STOCK) {
        this.PROT_DOCUMENT_STOCK = PROT_DOCUMENT_STOCK;
    }

    public Integer getPROT_DOCUMENT_ACHAT() {
        return PROT_DOCUMENT_ACHAT;
    }

    public void setPROT_DOCUMENT_ACHAT(Integer PROT_DOCUMENT_ACHAT) {
        this.PROT_DOCUMENT_ACHAT = PROT_DOCUMENT_ACHAT;
    }

    public Integer getPROT_DOCUMENT_VENTE() {
        return PROT_DOCUMENT_VENTE;
    }

    public void setPROT_DOCUMENT_VENTE(Integer PROT_DOCUMENT_VENTE) {
        this.PROT_DOCUMENT_VENTE = PROT_DOCUMENT_VENTE;
    }

    public Integer getPROT_PX_ACHAT() {
        return PROT_PX_ACHAT;
    }

    public void setPROT_PX_ACHAT(Integer PROT_PX_ACHAT) {
        this.PROT_PX_ACHAT = PROT_PX_ACHAT;
    }

    public Integer getPROT_PX_REVIENT() {
        return PROT_PX_REVIENT;
    }

    public void setPROT_PX_REVIENT(Integer PROT_PX_REVIENT) {
        this.PROT_PX_REVIENT = PROT_PX_REVIENT;
    }

    public Integer getPROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE() {
        return PROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE;
    }

    public void setPROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE(Integer PROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE) {
        this.PROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE = PROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE;
    }

    public Integer getPROT_DATE_VENTE() {
        return PROT_DATE_VENTE;
    }

    public void setPROT_DATE_VENTE(Integer PROT_DATE_VENTE) {
        this.PROT_DATE_VENTE = PROT_DATE_VENTE;
    }

    public Integer getPROT_DATE_ACHAT() {
        return PROT_DATE_ACHAT;
    }

    public void setPROT_DATE_ACHAT(Integer PROT_DATE_ACHAT) {
        this.PROT_DATE_ACHAT = PROT_DATE_ACHAT;
    }

    public Integer getPROT_DATE_COMPTOIR() {
        return PROT_DATE_COMPTOIR;
    }

    public void setPROT_DATE_COMPTOIR(Integer PROT_DATE_COMPTOIR) {
        this.PROT_DATE_COMPTOIR = PROT_DATE_COMPTOIR;
    }

    public Integer getPROT_DATE_RGLT() {
        return PROT_DATE_RGLT;
    }

    public void setPROT_DATE_RGLT(Integer PROT_DATE_RGLT) {
        this.PROT_DATE_RGLT = PROT_DATE_RGLT;
    }

    public Integer getPROT_DOCUMENT_VENTE_DEVIS() {
        return PROT_DOCUMENT_VENTE_DEVIS;
    }

    public void setPROT_DOCUMENT_VENTE_DEVIS(Integer PROT_DOCUMENT_VENTE_DEVIS) {
        this.PROT_DOCUMENT_VENTE_DEVIS = PROT_DOCUMENT_VENTE_DEVIS;
    }

    public Integer getPROT_DOCUMENT_VENTE_FACTURE() {
        return PROT_DOCUMENT_VENTE_FACTURE;
    }

    public void setPROT_DOCUMENT_VENTE_FACTURE(Integer PROT_DOCUMENT_VENTE_FACTURE) {
        this.PROT_DOCUMENT_VENTE_FACTURE = PROT_DOCUMENT_VENTE_FACTURE;
    }

    public Integer getPROT_DOCUMENT_VENTE_BLIVRAISON() {
        return PROT_DOCUMENT_VENTE_BLIVRAISON;
    }

    public void setPROT_DOCUMENT_VENTE_BLIVRAISON(Integer PROT_DOCUMENT_VENTE_BLIVRAISON) {
        this.PROT_DOCUMENT_VENTE_BLIVRAISON = PROT_DOCUMENT_VENTE_BLIVRAISON;
    }

    public Integer getPROT_DOCUMENT_VENTE_RETOUR() {
        return PROT_DOCUMENT_VENTE_RETOUR;
    }

    public void setPROT_DOCUMENT_VENTE_RETOUR(Integer PROT_DOCUMENT_VENTE_RETOUR) {
        this.PROT_DOCUMENT_VENTE_RETOUR = PROT_DOCUMENT_VENTE_RETOUR;
    }

    public Integer getPROT_DOCUMENT_VENTE_AVOIR() {
        return PROT_DOCUMENT_VENTE_AVOIR;
    }

    public void setPROT_DOCUMENT_VENTE_AVOIR(Integer PROT_DOCUMENT_VENTE_AVOIR) {
        this.PROT_DOCUMENT_VENTE_AVOIR = PROT_DOCUMENT_VENTE_AVOIR;
    }

    public Integer getPROT_DOCUMENT_ENTREE() {
        return PROT_DOCUMENT_ENTREE;
    }

    public void setPROT_DOCUMENT_ENTREE(Integer PROT_DOCUMENT_ENTREE) {
        this.PROT_DOCUMENT_ENTREE = PROT_DOCUMENT_ENTREE;
    }

    public Integer getPROT_DATE_STOCK() {
        return PROT_DATE_STOCK;
    }

    public void setPROT_DATE_STOCK(Integer PROT_DATE_STOCK) {
        this.PROT_DATE_STOCK = PROT_DATE_STOCK;
    }

    public Integer getPROT_DOCUMENT_SORTIE() {
        return PROT_DOCUMENT_SORTIE;
    }

    public void setPROT_DOCUMENT_SORTIE(Integer PROT_DOCUMENT_SORTIE) {
        this.PROT_DOCUMENT_SORTIE = PROT_DOCUMENT_SORTIE;
    }

    public Integer getPROT_DOCUMENT_REGLEMENT() {
        return PROT_DOCUMENT_REGLEMENT;
    }

    public void setPROT_DOCUMENT_REGLEMENT(Integer PROT_DOCUMENT_REGLEMENT) {
        this.PROT_DOCUMENT_REGLEMENT = PROT_DOCUMENT_REGLEMENT;
    }

    public Integer getPROT_MVT_CAISSE() {
        return PROT_MVT_CAISSE;
    }

    public void setPROT_MVT_CAISSE(Integer PROT_MVT_CAISSE) {
        this.PROT_MVT_CAISSE = PROT_MVT_CAISSE;
    }

    public Integer getPROT_QTE_NEGATIVE() {
        return PROT_QTE_NEGATIVE;
    }

    public void setPROT_QTE_NEGATIVE(Integer PROT_QTE_NEGATIVE) {
        this.PROT_QTE_NEGATIVE = PROT_QTE_NEGATIVE;
    }

    public Integer getPROT_SAISIE_REGLEMENT_FOURNISSEUR() {
        return PROT_SAISIE_REGLEMENT_FOURNISSEUR;
    }

    public void setPROT_SAISIE_REGLEMENT_FOURNISSEUR(Integer PROT_SAISIE_REGLEMENT_FOURNISSEUR) {
        this.PROT_SAISIE_REGLEMENT_FOURNISSEUR = PROT_SAISIE_REGLEMENT_FOURNISSEUR;
    }

    public Integer getPROT_DEPRECIATION_STOCK() {
        return PROT_DEPRECIATION_STOCK;
    }

    public void setPROT_DEPRECIATION_STOCK(Integer PROT_DEPRECIATION_STOCK) {
        this.PROT_DEPRECIATION_STOCK = PROT_DEPRECIATION_STOCK;
    }

    public Integer getPROT_SAISIE_REGLEMENT() {
        return PROT_SAISIE_REGLEMENT;
    }

    public void setPROT_SAISIE_REGLEMENT(Integer PROT_SAISIE_REGLEMENT) {
        this.PROT_SAISIE_REGLEMENT = PROT_SAISIE_REGLEMENT;
    }

    public Integer getPROT_DEPOT() {
        return PROT_DEPOT;
    }

    public void setPROT_DEPOT(Integer PROT_DEPOT) {
        this.PROT_DEPOT = PROT_DEPOT;
    }

    public Integer getPROT_RISQUE_CLIENT() {
        return PROT_RISQUE_CLIENT;
    }

    public void setPROT_RISQUE_CLIENT(Integer PROT_RISQUE_CLIENT) {
        this.PROT_RISQUE_CLIENT = PROT_RISQUE_CLIENT;
    }

    public Integer getPROT_SAISIE_INVENTAIRE() {
        return PROT_SAISIE_INVENTAIRE;
    }

    public void setPROT_SAISIE_INVENTAIRE(Integer PROT_SAISIE_INVENTAIRE) {
        this.PROT_SAISIE_INVENTAIRE = PROT_SAISIE_INVENTAIRE;
    }

    public Integer getPROT_AFFICHAGE_VAL_CAISSE() {
        return PROT_AFFICHAGE_VAL_CAISSE;
    }

    public void setPROT_AFFICHAGE_VAL_CAISSE(Integer PROT_AFFICHAGE_VAL_CAISSE) {
        this.PROT_AFFICHAGE_VAL_CAISSE = PROT_AFFICHAGE_VAL_CAISSE;
    }

    public Integer getPROT_CTRL_TT_CAISSE() {
        return PROT_CTRL_TT_CAISSE;
    }

    public void setPROT_CTRL_TT_CAISSE(Integer PROT_CTRL_TT_CAISSE) {
        this.PROT_CTRL_TT_CAISSE = PROT_CTRL_TT_CAISSE;
    }

    public Integer getPROT_INFOLIBRE_ARTICLE() {
        return PROT_INFOLIBRE_ARTICLE;
    }

    public void setPROT_INFOLIBRE_ARTICLE(Integer PROT_INFOLIBRE_ARTICLE) {
        this.PROT_INFOLIBRE_ARTICLE = PROT_INFOLIBRE_ARTICLE;
    }

    public Integer getPROT_DATE_MVT_CAISSE() {
        return PROT_DATE_MVT_CAISSE;
    }

    public void setPROT_DATE_MVT_CAISSE(Integer PROT_DATE_MVT_CAISSE) {
        this.PROT_DATE_MVT_CAISSE = PROT_DATE_MVT_CAISSE;
    }

    public Integer getPROT_GENERATION_RGLT_CLIENT() {
        return PROT_GENERATION_RGLT_CLIENT;
    }

    public void setPROT_GENERATION_RGLT_CLIENT(Integer PROT_GENERATION_RGLT_CLIENT) {
        this.PROT_GENERATION_RGLT_CLIENT = PROT_GENERATION_RGLT_CLIENT;
    }

    public Integer getPROT_DOCUMENT_ACHAT_FACTURE() {
        return PROT_DOCUMENT_ACHAT_FACTURE;
    }

    public void setPROT_DOCUMENT_ACHAT_FACTURE(Integer PROT_DOCUMENT_ACHAT_FACTURE) {
        this.PROT_DOCUMENT_ACHAT_FACTURE = PROT_DOCUMENT_ACHAT_FACTURE;
    }

    public Integer getPROT_MODIF_SUPPR_COMPTOIR() {
        return PROT_MODIF_SUPPR_COMPTOIR;
    }

    public void setPROT_MODIF_SUPPR_COMPTOIR(Integer PROT_MODIF_SUPPR_COMPTOIR) {
        this.PROT_MODIF_SUPPR_COMPTOIR = PROT_MODIF_SUPPR_COMPTOIR;
    }

    public Integer getPROT_APRES_IMPRESSION() {
        return PROT_APRES_IMPRESSION;
    }

    public void setPROT_APRES_IMPRESSION(Integer PROT_APRES_IMPRESSION) {
        this.PROT_APRES_IMPRESSION = PROT_APRES_IMPRESSION;
    }

    public Integer getPROT_TICKET_APRES_IMPRESSION() {
        return PROT_TICKET_APRES_IMPRESSION;
    }

    public void setPROT_TICKET_APRES_IMPRESSION(Integer PROT_TICKET_APRES_IMPRESSION) {
        this.PROT_TICKET_APRES_IMPRESSION = PROT_TICKET_APRES_IMPRESSION;
    }

    public Integer getPROT_AVANT_IMPRESSION() {
        return PROT_AVANT_IMPRESSION;
    }

    public void setPROT_AVANT_IMPRESSION(Integer PROT_AVANT_IMPRESSION) {
        this.PROT_AVANT_IMPRESSION = PROT_AVANT_IMPRESSION;
    }

    public Integer getPROT_DOCUMENT_INTERNE_2() {
        return PROT_DOCUMENT_INTERNE_2;
    }

    public void setPROT_DOCUMENT_INTERNE_2(Integer PROT_DOCUMENT_INTERNE_2) {
        this.PROT_DOCUMENT_INTERNE_2 = PROT_DOCUMENT_INTERNE_2;
    }

    public Integer getPROT_MODIFICATION_CLIENT() {
        return PROT_MODIFICATION_CLIENT;
    }

    public void setPROT_MODIFICATION_CLIENT(Integer PROT_MODIFICATION_CLIENT) {
        this.PROT_MODIFICATION_CLIENT = PROT_MODIFICATION_CLIENT;
    }

    public Integer getPROT_ETAT_INVENTAIRE_PREP() {
        return PROT_ETAT_INVENTAIRE_PREP;
    }

    public void setPROT_ETAT_INVENTAIRE_PREP(Integer PROT_ETAT_INVENTAIRE_PREP) {
        this.PROT_ETAT_INVENTAIRE_PREP = PROT_ETAT_INVENTAIRE_PREP;
    }

    public Integer getPROT_ETAT_LIVRE_INV() {
        return PROT_ETAT_LIVRE_INV;
    }

    public void setPROT_ETAT_LIVRE_INV(Integer PROT_ETAT_LIVRE_INV) {
        this.PROT_ETAT_LIVRE_INV = PROT_ETAT_LIVRE_INV;
    }

    public Integer getPROT_ETAT_STAT_ARTICLE_PAR_ART() {
        return PROT_ETAT_STAT_ARTICLE_PAR_ART;
    }

    public void setPROT_ETAT_STAT_ARTICLE_PAR_ART(Integer PROT_ETAT_STAT_ARTICLE_PAR_ART) {
        this.PROT_ETAT_STAT_ARTICLE_PAR_ART = PROT_ETAT_STAT_ARTICLE_PAR_ART;
    }

    public Integer getPROT_ETAT_STAT_ARTICLE_PAR_FAM() {
        return PROT_ETAT_STAT_ARTICLE_PAR_FAM;
    }

    public void setPROT_ETAT_STAT_ARTICLE_PAR_FAM(Integer PROT_ETAT_STAT_ARTICLE_PAR_FAM) {
        this.PROT_ETAT_STAT_ARTICLE_PAR_FAM = PROT_ETAT_STAT_ARTICLE_PAR_FAM;
    }

    public Integer getPROT_ETAT_STAT_ARTICLE_PALMARES() {
        return PROT_ETAT_STAT_ARTICLE_PALMARES;
    }

    public void setPROT_ETAT_STAT_ARTICLE_PALMARES(Integer PROT_ETAT_STAT_ARTICLE_PALMARES) {
        this.PROT_ETAT_STAT_ARTICLE_PALMARES = PROT_ETAT_STAT_ARTICLE_PALMARES;
    }

    public Integer getPROT_ETAT_MVT_STOCK() {
        return PROT_ETAT_MVT_STOCK;
    }

    public void setPROT_ETAT_MVT_STOCK(Integer PROT_ETAT_MVT_STOCK) {
        this.PROT_ETAT_MVT_STOCK = PROT_ETAT_MVT_STOCK;
    }

    public Integer getPROT_ETAT_CLT_PAR_FAM_ART() {
        return PROT_ETAT_CLT_PAR_FAM_ART;
    }

    public void setPROT_ETAT_CLT_PAR_FAM_ART(Integer PROT_ETAT_CLT_PAR_FAM_ART) {
        this.PROT_ETAT_CLT_PAR_FAM_ART = PROT_ETAT_CLT_PAR_FAM_ART;
    }

    public Integer getPROT_ETAT_CLT_PAR_ARTICLE() {
        return PROT_ETAT_CLT_PAR_ARTICLE;
    }

    public void setPROT_ETAT_CLT_PAR_ARTICLE(Integer PROT_ETAT_CLT_PAR_ARTICLE) {
        this.PROT_ETAT_CLT_PAR_ARTICLE = PROT_ETAT_CLT_PAR_ARTICLE;
    }

    public Integer getPROT_ETAT_PALMARES_CLT() {
        return PROT_ETAT_PALMARES_CLT;
    }

    public void setPROT_ETAT_PALMARES_CLT(Integer PROT_ETAT_PALMARES_CLT) {
        this.PROT_ETAT_PALMARES_CLT = PROT_ETAT_PALMARES_CLT;
    }

    public Integer getPROT_ETAT_STAT_FRS_FAM_ART() {
        return PROT_ETAT_STAT_FRS_FAM_ART;
    }

    public void setPROT_ETAT_STAT_FRS_FAM_ART(Integer PROT_ETAT_STAT_FRS_FAM_ART) {
        this.PROT_ETAT_STAT_FRS_FAM_ART = PROT_ETAT_STAT_FRS_FAM_ART;
    }

    public Integer getPROT_ETAT_STAT_FRS() {
        return PROT_ETAT_STAT_FRS;
    }

    public void setPROT_ETAT_STAT_FRS(Integer PROT_ETAT_STAT_FRS) {
        this.PROT_ETAT_STAT_FRS = PROT_ETAT_STAT_FRS;
    }

    public Integer getPROT_GEN_ECART_REGLEMENT() {
        return PROT_GEN_ECART_REGLEMENT;
    }

    public void setPROT_GEN_ECART_REGLEMENT(Integer PROT_GEN_ECART_REGLEMENT) {
        this.PROT_GEN_ECART_REGLEMENT = PROT_GEN_ECART_REGLEMENT;
    }

    public Integer getPROT_ETAT_STAT_CAISSE_ARTICLE() {
        return PROT_ETAT_STAT_CAISSE_ARTICLE;
    }

    public void setPROT_ETAT_STAT_CAISSE_ARTICLE(Integer PROT_ETAT_STAT_CAISSE_ARTICLE) {
        this.PROT_ETAT_STAT_CAISSE_ARTICLE = PROT_ETAT_STAT_CAISSE_ARTICLE;
    }

    public Integer getPROT_ETAT_STAT_CAISSE_FAM_ARTICLE() {
        return PROT_ETAT_STAT_CAISSE_FAM_ARTICLE;
    }

    public void setPROT_ETAT_STAT_CAISSE_FAM_ARTICLE(Integer PROT_ETAT_STAT_CAISSE_FAM_ARTICLE) {
        this.PROT_ETAT_STAT_CAISSE_FAM_ARTICLE = PROT_ETAT_STAT_CAISSE_FAM_ARTICLE;
    }

    public Integer getPROT_ETAT_CAISSE_MODE_RGLT() {
        return PROT_ETAT_CAISSE_MODE_RGLT;
    }

    public void setPROT_ETAT_CAISSE_MODE_RGLT(Integer PROT_ETAT_CAISSE_MODE_RGLT) {
        this.PROT_ETAT_CAISSE_MODE_RGLT = PROT_ETAT_CAISSE_MODE_RGLT;
    }

    public Integer getPROT_ETAT_RELEVE_CPTE_CLIENT() {
        return PROT_ETAT_RELEVE_CPTE_CLIENT;
    }

    public void setPROT_ETAT_RELEVE_CPTE_CLIENT(Integer PROT_ETAT_RELEVE_CPTE_CLIENT) {
        this.PROT_ETAT_RELEVE_CPTE_CLIENT = PROT_ETAT_RELEVE_CPTE_CLIENT;
    }

    public Integer getPROT_ETAT_STAT_COLLAB_PAR_TIERS() {
        return PROT_ETAT_STAT_COLLAB_PAR_TIERS;
    }

    public void setPROT_ETAT_STAT_COLLAB_PAR_TIERS(Integer PROT_ETAT_STAT_COLLAB_PAR_TIERS) {
        this.PROT_ETAT_STAT_COLLAB_PAR_TIERS = PROT_ETAT_STAT_COLLAB_PAR_TIERS;
    }

    public Integer getPROT_ETAT_STAT_COLLAB_PAR_ARTICLE() {
        return PROT_ETAT_STAT_COLLAB_PAR_ARTICLE;
    }

    public void setPROT_ETAT_STAT_COLLAB_PAR_ARTICLE(Integer PROT_ETAT_STAT_COLLAB_PAR_ARTICLE) {
        this.PROT_ETAT_STAT_COLLAB_PAR_ARTICLE = PROT_ETAT_STAT_COLLAB_PAR_ARTICLE;
    }

    public Integer getPROT_ETAT_STAT_COLLAB_PAR_FAMILLE() {
        return PROT_ETAT_STAT_COLLAB_PAR_FAMILLE;
    }

    public void setPROT_ETAT_STAT_COLLAB_PAR_FAMILLE(Integer PROT_ETAT_STAT_COLLAB_PAR_FAMILLE) {
        this.PROT_ETAT_STAT_COLLAB_PAR_FAMILLE = PROT_ETAT_STAT_COLLAB_PAR_FAMILLE;
    }

    public Integer getPROT_ETAT_STAT_FRS_PAR_FAMILLE() {
        return PROT_ETAT_STAT_FRS_PAR_FAMILLE;
    }

    public void setPROT_ETAT_STAT_FRS_PAR_FAMILLE(Integer PROT_ETAT_STAT_FRS_PAR_FAMILLE) {
        this.PROT_ETAT_STAT_FRS_PAR_FAMILLE = PROT_ETAT_STAT_FRS_PAR_FAMILLE;
    }

    public Integer getPROT_ETAT_STAT_FRS_PAR_ARTICLE() {
        return PROT_ETAT_STAT_FRS_PAR_ARTICLE;
    }

    public void setPROT_ETAT_STAT_FRS_PAR_ARTICLE(Integer PROT_ETAT_STAT_FRS_PAR_ARTICLE) {
        this.PROT_ETAT_STAT_FRS_PAR_ARTICLE = PROT_ETAT_STAT_FRS_PAR_ARTICLE;
    }

    public Integer getPROT_ETAT_STAT_ACHAT_ANALYTIQUE() {
        return PROT_ETAT_STAT_ACHAT_ANALYTIQUE;
    }

    public void setPROT_ETAT_STAT_ACHAT_ANALYTIQUE(Integer PROT_ETAT_STAT_ACHAT_ANALYTIQUE) {
        this.PROT_ETAT_STAT_ACHAT_ANALYTIQUE = PROT_ETAT_STAT_ACHAT_ANALYTIQUE;
    }

    public Integer getPROT_ETAT_RELEVE_ECH_CLIENT() {
        return PROT_ETAT_RELEVE_ECH_CLIENT;
    }

    public void setPROT_ETAT_RELEVE_ECH_CLIENT(Integer PROT_ETAT_RELEVE_ECH_CLIENT) {
        this.PROT_ETAT_RELEVE_ECH_CLIENT = PROT_ETAT_RELEVE_ECH_CLIENT;
    }

    public Integer getPROT_ETAT_RELEVE_ECH_FRS() {
        return PROT_ETAT_RELEVE_ECH_FRS;
    }

    public void setPROT_ETAT_RELEVE_ECH_FRS(Integer PROT_ETAT_RELEVE_ECH_FRS) {
        this.PROT_ETAT_RELEVE_ECH_FRS = PROT_ETAT_RELEVE_ECH_FRS;
    }

    public Integer getPROT_VENTE_COMPTOIR() {
        return PROT_VENTE_COMPTOIR;
    }

    public void setPROT_VENTE_COMPTOIR(Integer PROT_VENTE_COMPTOIR) {
        this.PROT_VENTE_COMPTOIR = PROT_VENTE_COMPTOIR;
    }

    public Integer getPROT_SAISIE_PX_VENTE_REMISE() {
        return PROT_SAISIE_PX_VENTE_REMISE;
    }

    public void setPROT_SAISIE_PX_VENTE_REMISE(Integer PROT_SAISIE_PX_VENTE_REMISE) {
        this.PROT_SAISIE_PX_VENTE_REMISE = PROT_SAISIE_PX_VENTE_REMISE;
    }

    public Integer getPROT_TARIFICATION_CLIENT() {
        return PROT_TARIFICATION_CLIENT;
    }

    public void setPROT_TARIFICATION_CLIENT(Integer PROT_TARIFICATION_CLIENT) {
        this.PROT_TARIFICATION_CLIENT = PROT_TARIFICATION_CLIENT;
    }

    public String getPROT_User() {
        return PROT_User;
    }

    public void setPROT_User(String PROT_User) {
        this.PROT_User = PROT_User;
    }

    public String getPROT_Pwd() {
        return PROT_Pwd;
    }

    public void setPROT_Pwd(String PROT_Pwd) {
        this.PROT_Pwd = PROT_Pwd;
    }

    public String getProfilName() {
        return ProfilName;
    }

    public void setProfilName(String profilName) {
        ProfilName = profilName;
    }

    public int getPROT_Administrator() {
        return PROT_Administrator;
    }

    public void setPROT_Administrator(int PROT_Administrator) {
        this.PROT_Administrator = PROT_Administrator;
    }

    public String getPROT_Description() {
        return PROT_Description;
    }

    public void setPROT_Description(String PROT_Description) {
        this.PROT_Description = PROT_Description;
    }

    public String getPROT_PwdStatus() {
        return PROT_PwdStatus;
    }

    public void setPROT_PwdStatus(String PROT_PwdStatus) {
        this.PROT_PwdStatus = PROT_PwdStatus;
    }

    public String getPROT_CBCREATEUR() {
        return PROT_CBCREATEUR;
    }

    public void setPROT_CBCREATEUR(String PROT_CBCREATEUR) {
        this.PROT_CBCREATEUR = PROT_CBCREATEUR;
    }

    public Integer getPROT_DOCUMENT_VENTE_BONCOMMANDE() {
        return PROT_DOCUMENT_VENTE_BONCOMMANDE;
    }

    public void setPROT_DOCUMENT_VENTE_BONCOMMANDE(Integer PROT_DOCUMENT_VENTE_BONCOMMANDE) {
        this.PROT_DOCUMENT_VENTE_BONCOMMANDE = PROT_DOCUMENT_VENTE_BONCOMMANDE;
    }

    @Override
    public String toString() {
        return "FProtectioncial{" +
                "cbMarq=" + cbMarq +
                ", CA_No=" + CA_No +
                ", PROT_Right=" + PROT_Right +
                ", Prot_No=" + Prot_No +
                ", PROT_CLIENT=" + PROT_CLIENT +
                ", PROT_FOURNISSEUR=" + PROT_FOURNISSEUR +
                ", PROT_COLLABORATEUR=" + PROT_COLLABORATEUR +
                ", PROT_FAMILLE=" + PROT_FAMILLE +
                ", PROT_ARTICLE=" + PROT_ARTICLE +
                ", PROT_DOCUMENT_STOCK=" + PROT_DOCUMENT_STOCK +
                ", PROT_DOCUMENT_ACHAT=" + PROT_DOCUMENT_ACHAT +
                ", PROT_DOCUMENT_VENTE=" + PROT_DOCUMENT_VENTE +
                ", PROT_PX_ACHAT=" + PROT_PX_ACHAT +
                ", PROT_PX_REVIENT=" + PROT_PX_REVIENT +
                ", PROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE=" + PROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE +
                ", PROT_DATE_VENTE=" + PROT_DATE_VENTE +
                ", PROT_DATE_ACHAT=" + PROT_DATE_ACHAT +
                ", PROT_DATE_COMPTOIR=" + PROT_DATE_COMPTOIR +
                ", PROT_DATE_RGLT=" + PROT_DATE_RGLT +
                ", PROT_DOCUMENT_VENTE_DEVIS=" + PROT_DOCUMENT_VENTE_DEVIS +
                ", PROT_DOCUMENT_VENTE_FACTURE=" + PROT_DOCUMENT_VENTE_FACTURE +
                ", PROT_DOCUMENT_VENTE_BLIVRAISON=" + PROT_DOCUMENT_VENTE_BLIVRAISON +
                ", PROT_DOCUMENT_VENTE_RETOUR=" + PROT_DOCUMENT_VENTE_RETOUR +
                ", PROT_DOCUMENT_VENTE_AVOIR=" + PROT_DOCUMENT_VENTE_AVOIR +
                ", PROT_DOCUMENT_ENTREE=" + PROT_DOCUMENT_ENTREE +
                ", PROT_DATE_STOCK=" + PROT_DATE_STOCK +
                ", PROT_DOCUMENT_SORTIE=" + PROT_DOCUMENT_SORTIE +
                ", PROT_DOCUMENT_REGLEMENT=" + PROT_DOCUMENT_REGLEMENT +
                ", PROT_MVT_CAISSE=" + PROT_MVT_CAISSE +
                ", PROT_QTE_NEGATIVE=" + PROT_QTE_NEGATIVE +
                ", PROT_SAISIE_REGLEMENT_FOURNISSEUR=" + PROT_SAISIE_REGLEMENT_FOURNISSEUR +
                ", PROT_DEPRECIATION_STOCK=" + PROT_DEPRECIATION_STOCK +
                ", PROT_SAISIE_REGLEMENT=" + PROT_SAISIE_REGLEMENT +
                ", PROT_DEPOT=" + PROT_DEPOT +
                ", PROT_RISQUE_CLIENT=" + PROT_RISQUE_CLIENT +
                ", PROT_SAISIE_INVENTAIRE=" + PROT_SAISIE_INVENTAIRE +
                ", PROT_AFFICHAGE_VAL_CAISSE=" + PROT_AFFICHAGE_VAL_CAISSE +
                ", PROT_CTRL_TT_CAISSE=" + PROT_CTRL_TT_CAISSE +
                ", PROT_INFOLIBRE_ARTICLE=" + PROT_INFOLIBRE_ARTICLE +
                ", PROT_DATE_MVT_CAISSE=" + PROT_DATE_MVT_CAISSE +
                ", PROT_GENERATION_RGLT_CLIENT=" + PROT_GENERATION_RGLT_CLIENT +
                ", PROT_DOCUMENT_ACHAT_FACTURE=" + PROT_DOCUMENT_ACHAT_FACTURE +
                ", PROT_MODIF_SUPPR_COMPTOIR=" + PROT_MODIF_SUPPR_COMPTOIR +
                ", PROT_APRES_IMPRESSION=" + PROT_APRES_IMPRESSION +
                ", PROT_TICKET_APRES_IMPRESSION=" + PROT_TICKET_APRES_IMPRESSION +
                ", PROT_AVANT_IMPRESSION=" + PROT_AVANT_IMPRESSION +
                ", PROT_DOCUMENT_INTERNE_2=" + PROT_DOCUMENT_INTERNE_2 +
                ", PROT_MODIFICATION_CLIENT=" + PROT_MODIFICATION_CLIENT +
                ", PROT_ETAT_INVENTAIRE_PREP=" + PROT_ETAT_INVENTAIRE_PREP +
                ", PROT_ETAT_LIVRE_INV=" + PROT_ETAT_LIVRE_INV +
                ", PROT_ETAT_STAT_ARTICLE_PAR_ART=" + PROT_ETAT_STAT_ARTICLE_PAR_ART +
                ", PROT_ETAT_STAT_ARTICLE_PAR_FAM=" + PROT_ETAT_STAT_ARTICLE_PAR_FAM +
                ", PROT_ETAT_STAT_ARTICLE_PALMARES=" + PROT_ETAT_STAT_ARTICLE_PALMARES +
                ", PROT_ETAT_MVT_STOCK=" + PROT_ETAT_MVT_STOCK +
                ", PROT_ETAT_CLT_PAR_FAM_ART=" + PROT_ETAT_CLT_PAR_FAM_ART +
                ", PROT_ETAT_CLT_PAR_ARTICLE=" + PROT_ETAT_CLT_PAR_ARTICLE +
                ", PROT_ETAT_PALMARES_CLT=" + PROT_ETAT_PALMARES_CLT +
                ", PROT_ETAT_STAT_FRS_FAM_ART=" + PROT_ETAT_STAT_FRS_FAM_ART +
                ", PROT_ETAT_STAT_FRS=" + PROT_ETAT_STAT_FRS +
                ", PROT_GEN_ECART_REGLEMENT=" + PROT_GEN_ECART_REGLEMENT +
                ", PROT_ETAT_STAT_CAISSE_ARTICLE=" + PROT_ETAT_STAT_CAISSE_ARTICLE +
                ", PROT_ETAT_STAT_CAISSE_FAM_ARTICLE=" + PROT_ETAT_STAT_CAISSE_FAM_ARTICLE +
                ", PROT_ETAT_CAISSE_MODE_RGLT=" + PROT_ETAT_CAISSE_MODE_RGLT +
                ", PROT_ETAT_RELEVE_CPTE_CLIENT=" + PROT_ETAT_RELEVE_CPTE_CLIENT +
                ", PROT_ETAT_STAT_COLLAB_PAR_TIERS=" + PROT_ETAT_STAT_COLLAB_PAR_TIERS +
                ", PROT_ETAT_STAT_COLLAB_PAR_ARTICLE=" + PROT_ETAT_STAT_COLLAB_PAR_ARTICLE +
                ", PROT_ETAT_STAT_COLLAB_PAR_FAMILLE=" + PROT_ETAT_STAT_COLLAB_PAR_FAMILLE +
                ", PROT_ETAT_STAT_FRS_PAR_FAMILLE=" + PROT_ETAT_STAT_FRS_PAR_FAMILLE +
                ", PROT_ETAT_STAT_FRS_PAR_ARTICLE=" + PROT_ETAT_STAT_FRS_PAR_ARTICLE +
                ", PROT_ETAT_STAT_ACHAT_ANALYTIQUE=" + PROT_ETAT_STAT_ACHAT_ANALYTIQUE +
                ", PROT_ETAT_RELEVE_ECH_CLIENT=" + PROT_ETAT_RELEVE_ECH_CLIENT +
                ", PROT_ETAT_RELEVE_ECH_FRS=" + PROT_ETAT_RELEVE_ECH_FRS +
                ", PROT_VENTE_COMPTOIR=" + PROT_VENTE_COMPTOIR +
                ", PROT_SAISIE_PX_VENTE_REMISE=" + PROT_SAISIE_PX_VENTE_REMISE +
                ", PROT_TARIFICATION_CLIENT=" + PROT_TARIFICATION_CLIENT +
                ", PROT_Administrator=" + PROT_Administrator +
                ", PROT_User='" + PROT_User + '\'' +
                ", PROT_Pwd='" + PROT_Pwd + '\'' +
                ", ProfilName='" + ProfilName + '\'' +
                ", PROT_Description='" + PROT_Description + '\'' +
                ", PROT_PwdStatus='" + PROT_PwdStatus + '\'' +
                ", PROT_CBCREATEUR='" + PROT_CBCREATEUR + '\'' +
                '}';
    }
}
