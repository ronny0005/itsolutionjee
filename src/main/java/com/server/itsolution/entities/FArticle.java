package com.server.itsolution.entities;

public class FArticle {

    private Integer cbMarq, CA_No,CO_No,CL_No1,CL_No2,CL_No3,CL_No4
            ,AR_Garantie,AR_UnitePoids,AR_PoidsNet,AR_PoidsBrut,AR_UniteVen,AR_Coef,AR_Gamme1,AR_Gamme2,AR_SuiviStock
            ,AR_Escompte,AR_Delai,AR_HorsStat,AR_VteDebit,AR_NotImp,AR_Sommeil
            ,AR_Frais01FR_Rem01REM_Type,AR_Frais01FR_Rem02REM_Type,AR_Frais01FR_Rem03REM_Type,AR_Frais02FR_Rem01REM_Type
            ,AR_Frais02FR_Rem02REM_Type,AR_Frais02FR_Rem03REM_Type,AR_Frais03FR_Rem01REM_Type,AR_Frais03FR_Rem02REM_Type,AR_Frais03FR_Rem03REM_Type
            ,AR_Condition,AR_Contremarque,AR_Publie;

    private double   AR_PrixVen ,AR_PrixTTC,AR_PrixAch,Prix_Min,Prix_Max,Qte_Gros,AR_PrixVenNouv,AR_PrixAchNouv
            ,AR_CoefNouv,AR_Frais01FR_Rem01REM_Valeur,AR_Frais01FR_Rem02REM_Valeur,AR_Frais01FR_Rem03REM_Valeur,AR_Frais02FR_Rem01REM_Valeur,AR_CoutStd,AR_QteComp,AR_QteOperatoire
            ,AR_Frais02FR_Rem02REM_Valeur,AR_Frais02FR_Rem03REM_Valeur,AR_Frais03FR_Rem01REM_Valeur,AR_Frais03FR_Rem02REM_Valeur,AR_Frais03FR_Rem03REM_Valeur,AR_PUNet;
    private String AR_Ref
            ,AR_Design
            ,FA_CodeFamille
            ,AR_Substitut
            ,AR_Raccourci
            ,AR_Nomencl
            ,AR_Stat01
            ,AR_Stat02
            ,AR_Stat03
            ,AR_Stat04
            ,AR_Stat05
            ,AR_Langue1
            ,AR_Langue2
            ,AR_CodeEdiED_Code1
            ,AR_CodeEdiED_Code2
            ,AR_CodeEdiED_Code3
            ,AR_CodeEdiED_Code4
            ,AR_CodeBarre
            ,AR_CodeFiscal
            ,AR_Pays
            ,AR_Frais01FR_Denomination
            ,AR_Frais02FR_Denomination
            ,AR_Frais03FR_Denomination
            ,AR_FactPoids
            ,AR_FactForfait
            ,AR_DateCreation
            ,AR_SaisieVar
            ,AR_Transfere
            ,AR_DateModif
            ,AR_Photo
            ,AR_DateApplication
            ,AR_Prevision
            ,AR_Type
            ,RP_CodeDefaut
            ,cbCreateur
            ,cbModification
            ;

    public FArticle() {
    }

    public Integer getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(Integer cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Integer getCA_No() {
        return CA_No;
    }

    public void setCA_No(Integer CA_No) {
        this.CA_No = CA_No;
    }

    public Integer getCO_No() {
        return CO_No;
    }

    public void setCO_No(Integer CO_No) {
        this.CO_No = CO_No;
    }

    public Integer getCL_No1() {
        return CL_No1;
    }

    public void setCL_No1(Integer CL_No1) {
        this.CL_No1 = CL_No1;
    }

    public Integer getCL_No2() {
        return CL_No2;
    }

    public void setCL_No2(Integer CL_No2) {
        this.CL_No2 = CL_No2;
    }

    public Integer getCL_No3() {
        return CL_No3;
    }

    public void setCL_No3(Integer CL_No3) {
        this.CL_No3 = CL_No3;
    }

    public Integer getCL_No4() {
        return CL_No4;
    }

    public void setCL_No4(Integer CL_No4) {
        this.CL_No4 = CL_No4;
    }

    public Integer getAR_Garantie() {
        return AR_Garantie;
    }

    public void setAR_Garantie(Integer AR_Garantie) {
        this.AR_Garantie = AR_Garantie;
    }

    public Integer getAR_UnitePoids() {
        return AR_UnitePoids;
    }

    public void setAR_UnitePoids(Integer AR_UnitePoids) {
        this.AR_UnitePoids = AR_UnitePoids;
    }

    public Integer getAR_PoidsNet() {
        return AR_PoidsNet;
    }

    public void setAR_PoidsNet(Integer AR_PoidsNet) {
        this.AR_PoidsNet = AR_PoidsNet;
    }

    public Integer getAR_PoidsBrut() {
        return AR_PoidsBrut;
    }

    public void setAR_PoidsBrut(Integer AR_PoidsBrut) {
        this.AR_PoidsBrut = AR_PoidsBrut;
    }

    public Integer getAR_UniteVen() {
        return AR_UniteVen;
    }

    public void setAR_UniteVen(Integer AR_UniteVen) {
        this.AR_UniteVen = AR_UniteVen;
    }

    public Integer getAR_Coef() {
        return AR_Coef;
    }

    public void setAR_Coef(Integer AR_Coef) {
        this.AR_Coef = AR_Coef;
    }

    public Integer getAR_Gamme1() {
        return AR_Gamme1;
    }

    public void setAR_Gamme1(Integer AR_Gamme1) {
        this.AR_Gamme1 = AR_Gamme1;
    }

    public Integer getAR_Gamme2() {
        return AR_Gamme2;
    }

    public void setAR_Gamme2(Integer AR_Gamme2) {
        this.AR_Gamme2 = AR_Gamme2;
    }

    public Integer getAR_SuiviStock() {
        return AR_SuiviStock;
    }

    public void setAR_SuiviStock(Integer AR_SuiviStock) {
        this.AR_SuiviStock = AR_SuiviStock;
    }

    public Integer getAR_Escompte() {
        return AR_Escompte;
    }

    public void setAR_Escompte(Integer AR_Escompte) {
        this.AR_Escompte = AR_Escompte;
    }

    public Integer getAR_Delai() {
        return AR_Delai;
    }

    public void setAR_Delai(Integer AR_Delai) {
        this.AR_Delai = AR_Delai;
    }

    public Integer getAR_HorsStat() {
        return AR_HorsStat;
    }

    public void setAR_HorsStat(Integer AR_HorsStat) {
        this.AR_HorsStat = AR_HorsStat;
    }

    public Integer getAR_VteDebit() {
        return AR_VteDebit;
    }

    public void setAR_VteDebit(Integer AR_VteDebit) {
        this.AR_VteDebit = AR_VteDebit;
    }

    public Integer getAR_NotImp() {
        return AR_NotImp;
    }

    public void setAR_NotImp(Integer AR_NotImp) {
        this.AR_NotImp = AR_NotImp;
    }

    public Integer getAR_Sommeil() {
        return AR_Sommeil;
    }

    public void setAR_Sommeil(Integer AR_Sommeil) {
        this.AR_Sommeil = AR_Sommeil;
    }

    public Integer getAR_Frais01FR_Rem01REM_Type() {
        return AR_Frais01FR_Rem01REM_Type;
    }

    public void setAR_Frais01FR_Rem01REM_Type(Integer AR_Frais01FR_Rem01REM_Type) {
        this.AR_Frais01FR_Rem01REM_Type = AR_Frais01FR_Rem01REM_Type;
    }

    public Integer getAR_Frais01FR_Rem02REM_Type() {
        return AR_Frais01FR_Rem02REM_Type;
    }

    public void setAR_Frais01FR_Rem02REM_Type(Integer AR_Frais01FR_Rem02REM_Type) {
        this.AR_Frais01FR_Rem02REM_Type = AR_Frais01FR_Rem02REM_Type;
    }

    public Integer getAR_Frais01FR_Rem03REM_Type() {
        return AR_Frais01FR_Rem03REM_Type;
    }

    public void setAR_Frais01FR_Rem03REM_Type(Integer AR_Frais01FR_Rem03REM_Type) {
        this.AR_Frais01FR_Rem03REM_Type = AR_Frais01FR_Rem03REM_Type;
    }

    public Integer getAR_Frais02FR_Rem01REM_Type() {
        return AR_Frais02FR_Rem01REM_Type;
    }

    public void setAR_Frais02FR_Rem01REM_Type(Integer AR_Frais02FR_Rem01REM_Type) {
        this.AR_Frais02FR_Rem01REM_Type = AR_Frais02FR_Rem01REM_Type;
    }

    public Integer getAR_Frais02FR_Rem02REM_Type() {
        return AR_Frais02FR_Rem02REM_Type;
    }

    public void setAR_Frais02FR_Rem02REM_Type(Integer AR_Frais02FR_Rem02REM_Type) {
        this.AR_Frais02FR_Rem02REM_Type = AR_Frais02FR_Rem02REM_Type;
    }

    public Integer getAR_Frais02FR_Rem03REM_Type() {
        return AR_Frais02FR_Rem03REM_Type;
    }

    public void setAR_Frais02FR_Rem03REM_Type(Integer AR_Frais02FR_Rem03REM_Type) {
        this.AR_Frais02FR_Rem03REM_Type = AR_Frais02FR_Rem03REM_Type;
    }

    public Integer getAR_Frais03FR_Rem01REM_Type() {
        return AR_Frais03FR_Rem01REM_Type;
    }

    public void setAR_Frais03FR_Rem01REM_Type(Integer AR_Frais03FR_Rem01REM_Type) {
        this.AR_Frais03FR_Rem01REM_Type = AR_Frais03FR_Rem01REM_Type;
    }

    public Integer getAR_Frais03FR_Rem02REM_Type() {
        return AR_Frais03FR_Rem02REM_Type;
    }

    public void setAR_Frais03FR_Rem02REM_Type(Integer AR_Frais03FR_Rem02REM_Type) {
        this.AR_Frais03FR_Rem02REM_Type = AR_Frais03FR_Rem02REM_Type;
    }

    public Integer getAR_Frais03FR_Rem03REM_Type() {
        return AR_Frais03FR_Rem03REM_Type;
    }

    public void setAR_Frais03FR_Rem03REM_Type(Integer AR_Frais03FR_Rem03REM_Type) {
        this.AR_Frais03FR_Rem03REM_Type = AR_Frais03FR_Rem03REM_Type;
    }

    public Integer getAR_Condition() {
        return AR_Condition;
    }

    public void setAR_Condition(Integer AR_Condition) {
        this.AR_Condition = AR_Condition;
    }

    public Integer getAR_Contremarque() {
        return AR_Contremarque;
    }

    public void setAR_Contremarque(Integer AR_Contremarque) {
        this.AR_Contremarque = AR_Contremarque;
    }

    public Integer getAR_Publie() {
        return AR_Publie;
    }

    public void setAR_Publie(Integer AR_Publie) {
        this.AR_Publie = AR_Publie;
    }

    public double getAR_PrixVen() {
        return AR_PrixVen;
    }

    public void setAR_PrixVen(double AR_PrixVen) {
        this.AR_PrixVen = AR_PrixVen;
    }

    public double getAR_PrixTTC() {
        return AR_PrixTTC;
    }

    public void setAR_PrixTTC(double AR_PrixTTC) {
        this.AR_PrixTTC = AR_PrixTTC;
    }

    public double getAR_PrixAch() {
        return AR_PrixAch;
    }

    public void setAR_PrixAch(double AR_PrixAch) {
        this.AR_PrixAch = AR_PrixAch;
    }

    public double getPrix_Min() {
        return Prix_Min;
    }

    public void setPrix_Min(double prix_Min) {
        Prix_Min = prix_Min;
    }

    public double getPrix_Max() {
        return Prix_Max;
    }

    public void setPrix_Max(double prix_Max) {
        Prix_Max = prix_Max;
    }

    public double getQte_Gros() {
        return Qte_Gros;
    }

    public void setQte_Gros(double qte_Gros) {
        Qte_Gros = qte_Gros;
    }

    public double getAR_PrixVenNouv() {
        return AR_PrixVenNouv;
    }

    public void setAR_PrixVenNouv(double AR_PrixVenNouv) {
        this.AR_PrixVenNouv = AR_PrixVenNouv;
    }

    public double getAR_PrixAchNouv() {
        return AR_PrixAchNouv;
    }

    public void setAR_PrixAchNouv(double AR_PrixAchNouv) {
        this.AR_PrixAchNouv = AR_PrixAchNouv;
    }

    public double getAR_CoefNouv() {
        return AR_CoefNouv;
    }

    public void setAR_CoefNouv(double AR_CoefNouv) {
        this.AR_CoefNouv = AR_CoefNouv;
    }

    public double getAR_Frais01FR_Rem01REM_Valeur() {
        return AR_Frais01FR_Rem01REM_Valeur;
    }

    public void setAR_Frais01FR_Rem01REM_Valeur(double AR_Frais01FR_Rem01REM_Valeur) {
        this.AR_Frais01FR_Rem01REM_Valeur = AR_Frais01FR_Rem01REM_Valeur;
    }

    public double getAR_Frais01FR_Rem02REM_Valeur() {
        return AR_Frais01FR_Rem02REM_Valeur;
    }

    public void setAR_Frais01FR_Rem02REM_Valeur(double AR_Frais01FR_Rem02REM_Valeur) {
        this.AR_Frais01FR_Rem02REM_Valeur = AR_Frais01FR_Rem02REM_Valeur;
    }

    public double getAR_Frais01FR_Rem03REM_Valeur() {
        return AR_Frais01FR_Rem03REM_Valeur;
    }

    public void setAR_Frais01FR_Rem03REM_Valeur(double AR_Frais01FR_Rem03REM_Valeur) {
        this.AR_Frais01FR_Rem03REM_Valeur = AR_Frais01FR_Rem03REM_Valeur;
    }

    public double getAR_Frais02FR_Rem01REM_Valeur() {
        return AR_Frais02FR_Rem01REM_Valeur;
    }

    public void setAR_Frais02FR_Rem01REM_Valeur(double AR_Frais02FR_Rem01REM_Valeur) {
        this.AR_Frais02FR_Rem01REM_Valeur = AR_Frais02FR_Rem01REM_Valeur;
    }

    public double getAR_CoutStd() {
        return AR_CoutStd;
    }

    public void setAR_CoutStd(double AR_CoutStd) {
        this.AR_CoutStd = AR_CoutStd;
    }

    public double getAR_QteComp() {
        return AR_QteComp;
    }

    public void setAR_QteComp(double AR_QteComp) {
        this.AR_QteComp = AR_QteComp;
    }

    public double getAR_QteOperatoire() {
        return AR_QteOperatoire;
    }

    public void setAR_QteOperatoire(double AR_QteOperatoire) {
        this.AR_QteOperatoire = AR_QteOperatoire;
    }

    public double getAR_Frais02FR_Rem02REM_Valeur() {
        return AR_Frais02FR_Rem02REM_Valeur;
    }

    public void setAR_Frais02FR_Rem02REM_Valeur(double AR_Frais02FR_Rem02REM_Valeur) {
        this.AR_Frais02FR_Rem02REM_Valeur = AR_Frais02FR_Rem02REM_Valeur;
    }

    public double getAR_Frais02FR_Rem03REM_Valeur() {
        return AR_Frais02FR_Rem03REM_Valeur;
    }

    public void setAR_Frais02FR_Rem03REM_Valeur(double AR_Frais02FR_Rem03REM_Valeur) {
        this.AR_Frais02FR_Rem03REM_Valeur = AR_Frais02FR_Rem03REM_Valeur;
    }

    public double getAR_Frais03FR_Rem01REM_Valeur() {
        return AR_Frais03FR_Rem01REM_Valeur;
    }

    public void setAR_Frais03FR_Rem01REM_Valeur(double AR_Frais03FR_Rem01REM_Valeur) {
        this.AR_Frais03FR_Rem01REM_Valeur = AR_Frais03FR_Rem01REM_Valeur;
    }

    public double getAR_Frais03FR_Rem02REM_Valeur() {
        return AR_Frais03FR_Rem02REM_Valeur;
    }

    public void setAR_Frais03FR_Rem02REM_Valeur(double AR_Frais03FR_Rem02REM_Valeur) {
        this.AR_Frais03FR_Rem02REM_Valeur = AR_Frais03FR_Rem02REM_Valeur;
    }

    public double getAR_Frais03FR_Rem03REM_Valeur() {
        return AR_Frais03FR_Rem03REM_Valeur;
    }

    public void setAR_Frais03FR_Rem03REM_Valeur(double AR_Frais03FR_Rem03REM_Valeur) {
        this.AR_Frais03FR_Rem03REM_Valeur = AR_Frais03FR_Rem03REM_Valeur;
    }

    public double getAR_PUNet() {
        return AR_PUNet;
    }

    public void setAR_PUNet(double AR_PUNet) {
        this.AR_PUNet = AR_PUNet;
    }

    public String getAR_Ref() {
        return AR_Ref;
    }

    public void setAR_Ref(String AR_Ref) {
        this.AR_Ref = AR_Ref;
    }

    public String getAR_Design() {
        return AR_Design;
    }

    public void setAR_Design(String AR_Design) {
        this.AR_Design = AR_Design;
    }

    public String getFA_CodeFamille() {
        return FA_CodeFamille;
    }

    public void setFA_CodeFamille(String FA_CodeFamille) {
        this.FA_CodeFamille = FA_CodeFamille;
    }

    public String getAR_Substitut() {
        return AR_Substitut;
    }

    public void setAR_Substitut(String AR_Substitut) {
        this.AR_Substitut = AR_Substitut;
    }

    public String getAR_Raccourci() {
        return AR_Raccourci;
    }

    public void setAR_Raccourci(String AR_Raccourci) {
        this.AR_Raccourci = AR_Raccourci;
    }

    public String getAR_Nomencl() {
        return AR_Nomencl;
    }

    public void setAR_Nomencl(String AR_Nomencl) {
        this.AR_Nomencl = AR_Nomencl;
    }

    public String getAR_Stat01() {
        return AR_Stat01;
    }

    public void setAR_Stat01(String AR_Stat01) {
        this.AR_Stat01 = AR_Stat01;
    }

    public String getAR_Stat02() {
        return AR_Stat02;
    }

    public void setAR_Stat02(String AR_Stat02) {
        this.AR_Stat02 = AR_Stat02;
    }

    public String getAR_Stat03() {
        return AR_Stat03;
    }

    public void setAR_Stat03(String AR_Stat03) {
        this.AR_Stat03 = AR_Stat03;
    }

    public String getAR_Stat04() {
        return AR_Stat04;
    }

    public void setAR_Stat04(String AR_Stat04) {
        this.AR_Stat04 = AR_Stat04;
    }

    public String getAR_Stat05() {
        return AR_Stat05;
    }

    public void setAR_Stat05(String AR_Stat05) {
        this.AR_Stat05 = AR_Stat05;
    }

    public String getAR_Langue1() {
        return AR_Langue1;
    }

    public void setAR_Langue1(String AR_Langue1) {
        this.AR_Langue1 = AR_Langue1;
    }

    public String getAR_Langue2() {
        return AR_Langue2;
    }

    public void setAR_Langue2(String AR_Langue2) {
        this.AR_Langue2 = AR_Langue2;
    }

    public String getAR_CodeEdiED_Code1() {
        return AR_CodeEdiED_Code1;
    }

    public void setAR_CodeEdiED_Code1(String AR_CodeEdiED_Code1) {
        this.AR_CodeEdiED_Code1 = AR_CodeEdiED_Code1;
    }

    public String getAR_CodeEdiED_Code2() {
        return AR_CodeEdiED_Code2;
    }

    public void setAR_CodeEdiED_Code2(String AR_CodeEdiED_Code2) {
        this.AR_CodeEdiED_Code2 = AR_CodeEdiED_Code2;
    }

    public String getAR_CodeEdiED_Code3() {
        return AR_CodeEdiED_Code3;
    }

    public void setAR_CodeEdiED_Code3(String AR_CodeEdiED_Code3) {
        this.AR_CodeEdiED_Code3 = AR_CodeEdiED_Code3;
    }

    public String getAR_CodeEdiED_Code4() {
        return AR_CodeEdiED_Code4;
    }

    public void setAR_CodeEdiED_Code4(String AR_CodeEdiED_Code4) {
        this.AR_CodeEdiED_Code4 = AR_CodeEdiED_Code4;
    }

    public String getAR_CodeBarre() {
        return AR_CodeBarre;
    }

    public void setAR_CodeBarre(String AR_CodeBarre) {
        this.AR_CodeBarre = AR_CodeBarre;
    }

    public String getAR_CodeFiscal() {
        return AR_CodeFiscal;
    }

    public void setAR_CodeFiscal(String AR_CodeFiscal) {
        this.AR_CodeFiscal = AR_CodeFiscal;
    }

    public String getAR_Pays() {
        return AR_Pays;
    }

    public void setAR_Pays(String AR_Pays) {
        this.AR_Pays = AR_Pays;
    }

    public String getAR_Frais01FR_Denomination() {
        return AR_Frais01FR_Denomination;
    }

    public void setAR_Frais01FR_Denomination(String AR_Frais01FR_Denomination) {
        this.AR_Frais01FR_Denomination = AR_Frais01FR_Denomination;
    }

    public String getAR_Frais02FR_Denomination() {
        return AR_Frais02FR_Denomination;
    }

    public void setAR_Frais02FR_Denomination(String AR_Frais02FR_Denomination) {
        this.AR_Frais02FR_Denomination = AR_Frais02FR_Denomination;
    }

    public String getAR_Frais03FR_Denomination() {
        return AR_Frais03FR_Denomination;
    }

    public void setAR_Frais03FR_Denomination(String AR_Frais03FR_Denomination) {
        this.AR_Frais03FR_Denomination = AR_Frais03FR_Denomination;
    }

    public String getAR_FactPoids() {
        return AR_FactPoids;
    }

    public void setAR_FactPoids(String AR_FactPoids) {
        this.AR_FactPoids = AR_FactPoids;
    }

    public String getAR_FactForfait() {
        return AR_FactForfait;
    }

    public void setAR_FactForfait(String AR_FactForfait) {
        this.AR_FactForfait = AR_FactForfait;
    }

    public String getAR_DateCreation() {
        return AR_DateCreation;
    }

    public void setAR_DateCreation(String AR_DateCreation) {
        this.AR_DateCreation = AR_DateCreation;
    }

    public String getAR_SaisieVar() {
        return AR_SaisieVar;
    }

    public void setAR_SaisieVar(String AR_SaisieVar) {
        this.AR_SaisieVar = AR_SaisieVar;
    }

    public String getAR_Transfere() {
        return AR_Transfere;
    }

    public void setAR_Transfere(String AR_Transfere) {
        this.AR_Transfere = AR_Transfere;
    }

    public String getAR_DateModif() {
        return AR_DateModif;
    }

    public void setAR_DateModif(String AR_DateModif) {
        this.AR_DateModif = AR_DateModif;
    }

    public String getAR_Photo() {
        return AR_Photo;
    }

    public void setAR_Photo(String AR_Photo) {
        this.AR_Photo = AR_Photo;
    }

    public String getAR_DateApplication() {
        return AR_DateApplication;
    }

    public void setAR_DateApplication(String AR_DateApplication) {
        this.AR_DateApplication = AR_DateApplication;
    }

    public String getAR_Prevision() {
        return AR_Prevision;
    }

    public void setAR_Prevision(String AR_Prevision) {
        this.AR_Prevision = AR_Prevision;
    }

    public String getAR_Type() {
        return AR_Type;
    }

    public void setAR_Type(String AR_Type) {
        this.AR_Type = AR_Type;
    }

    public String getRP_CodeDefaut() {
        return RP_CodeDefaut;
    }

    public void setRP_CodeDefaut(String RP_CodeDefaut) {
        this.RP_CodeDefaut = RP_CodeDefaut;
    }

    public String getCbCreateur() {
        return cbCreateur;
    }

    public void setCbCreateur(String cbCreateur) {
        this.cbCreateur = cbCreateur;
    }

    public String getCbModification() {
        return cbModification;
    }

    public void setCbModification(String cbModification) {
        this.cbModification = cbModification;
    }

    @Override
    public String toString() {
        return "FArticle{" +
                "cbMarq=" + cbMarq +
                ", CA_No=" + CA_No +
                ", CO_No=" + CO_No +
                ", CL_No1=" + CL_No1 +
                ", CL_No2=" + CL_No2 +
                ", CL_No3=" + CL_No3 +
                ", CL_No4=" + CL_No4 +
                ", AR_Garantie=" + AR_Garantie +
                ", AR_UnitePoids=" + AR_UnitePoids +
                ", AR_PoidsNet=" + AR_PoidsNet +
                ", AR_PoidsBrut=" + AR_PoidsBrut +
                ", AR_UniteVen=" + AR_UniteVen +
                ", AR_Coef=" + AR_Coef +
                ", AR_Gamme1=" + AR_Gamme1 +
                ", AR_Gamme2=" + AR_Gamme2 +
                ", AR_SuiviStock=" + AR_SuiviStock +
                ", AR_Escompte=" + AR_Escompte +
                ", AR_Delai=" + AR_Delai +
                ", AR_HorsStat=" + AR_HorsStat +
                ", AR_VteDebit=" + AR_VteDebit +
                ", AR_NotImp=" + AR_NotImp +
                ", AR_Sommeil=" + AR_Sommeil +
                ", AR_Frais01FR_Rem01REM_Type=" + AR_Frais01FR_Rem01REM_Type +
                ", AR_Frais01FR_Rem02REM_Type=" + AR_Frais01FR_Rem02REM_Type +
                ", AR_Frais01FR_Rem03REM_Type=" + AR_Frais01FR_Rem03REM_Type +
                ", AR_Frais02FR_Rem01REM_Type=" + AR_Frais02FR_Rem01REM_Type +
                ", AR_Frais02FR_Rem02REM_Type=" + AR_Frais02FR_Rem02REM_Type +
                ", AR_Frais02FR_Rem03REM_Type=" + AR_Frais02FR_Rem03REM_Type +
                ", AR_Frais03FR_Rem01REM_Type=" + AR_Frais03FR_Rem01REM_Type +
                ", AR_Frais03FR_Rem02REM_Type=" + AR_Frais03FR_Rem02REM_Type +
                ", AR_Frais03FR_Rem03REM_Type=" + AR_Frais03FR_Rem03REM_Type +
                ", AR_Condition=" + AR_Condition +
                ", AR_Contremarque=" + AR_Contremarque +
                ", AR_Publie=" + AR_Publie +
                ", AR_PrixVen=" + AR_PrixVen +
                ", AR_PrixTTC=" + AR_PrixTTC +
                ", AR_PrixAch=" + AR_PrixAch +
                ", Prix_Min=" + Prix_Min +
                ", Prix_Max=" + Prix_Max +
                ", Qte_Gros=" + Qte_Gros +
                ", AR_PrixVenNouv=" + AR_PrixVenNouv +
                ", AR_PrixAchNouv=" + AR_PrixAchNouv +
                ", AR_CoefNouv=" + AR_CoefNouv +
                ", AR_Frais01FR_Rem01REM_Valeur=" + AR_Frais01FR_Rem01REM_Valeur +
                ", AR_Frais01FR_Rem02REM_Valeur=" + AR_Frais01FR_Rem02REM_Valeur +
                ", AR_Frais01FR_Rem03REM_Valeur=" + AR_Frais01FR_Rem03REM_Valeur +
                ", AR_Frais02FR_Rem01REM_Valeur=" + AR_Frais02FR_Rem01REM_Valeur +
                ", AR_CoutStd=" + AR_CoutStd +
                ", AR_QteComp=" + AR_QteComp +
                ", AR_QteOperatoire=" + AR_QteOperatoire +
                ", AR_Frais02FR_Rem02REM_Valeur=" + AR_Frais02FR_Rem02REM_Valeur +
                ", AR_Frais02FR_Rem03REM_Valeur=" + AR_Frais02FR_Rem03REM_Valeur +
                ", AR_Frais03FR_Rem01REM_Valeur=" + AR_Frais03FR_Rem01REM_Valeur +
                ", AR_Frais03FR_Rem02REM_Valeur=" + AR_Frais03FR_Rem02REM_Valeur +
                ", AR_Frais03FR_Rem03REM_Valeur=" + AR_Frais03FR_Rem03REM_Valeur +
                ", AR_PUNet=" + AR_PUNet +
                ", AR_Ref='" + AR_Ref + '\'' +
                ", AR_Design='" + AR_Design + '\'' +
                ", FA_CodeFamille='" + FA_CodeFamille + '\'' +
                ", AR_Substitut='" + AR_Substitut + '\'' +
                ", AR_Raccourci='" + AR_Raccourci + '\'' +
                ", AR_Nomencl='" + AR_Nomencl + '\'' +
                ", AR_Stat01='" + AR_Stat01 + '\'' +
                ", AR_Stat02='" + AR_Stat02 + '\'' +
                ", AR_Stat03='" + AR_Stat03 + '\'' +
                ", AR_Stat04='" + AR_Stat04 + '\'' +
                ", AR_Stat05='" + AR_Stat05 + '\'' +
                ", AR_Langue1='" + AR_Langue1 + '\'' +
                ", AR_Langue2='" + AR_Langue2 + '\'' +
                ", AR_CodeEdiED_Code1='" + AR_CodeEdiED_Code1 + '\'' +
                ", AR_CodeEdiED_Code2='" + AR_CodeEdiED_Code2 + '\'' +
                ", AR_CodeEdiED_Code3='" + AR_CodeEdiED_Code3 + '\'' +
                ", AR_CodeEdiED_Code4='" + AR_CodeEdiED_Code4 + '\'' +
                ", AR_CodeBarre='" + AR_CodeBarre + '\'' +
                ", AR_CodeFiscal='" + AR_CodeFiscal + '\'' +
                ", AR_Pays='" + AR_Pays + '\'' +
                ", AR_Frais01FR_Denomination='" + AR_Frais01FR_Denomination + '\'' +
                ", AR_Frais02FR_Denomination='" + AR_Frais02FR_Denomination + '\'' +
                ", AR_Frais03FR_Denomination='" + AR_Frais03FR_Denomination + '\'' +
                ", AR_FactPoids='" + AR_FactPoids + '\'' +
                ", AR_FactForfait='" + AR_FactForfait + '\'' +
                ", AR_DateCreation='" + AR_DateCreation + '\'' +
                ", AR_SaisieVar='" + AR_SaisieVar + '\'' +
                ", AR_Transfere='" + AR_Transfere + '\'' +
                ", AR_DateModif='" + AR_DateModif + '\'' +
                ", AR_Photo='" + AR_Photo + '\'' +
                ", AR_DateApplication='" + AR_DateApplication + '\'' +
                ", AR_Prevision='" + AR_Prevision + '\'' +
                ", AR_Type='" + AR_Type + '\'' +
                ", RP_CodeDefaut='" + RP_CodeDefaut + '\'' +
                ", cbCreateur='" + cbCreateur + '\'' +
                ", cbModification='" + cbModification + '\'' +
                '}';
    }
}
