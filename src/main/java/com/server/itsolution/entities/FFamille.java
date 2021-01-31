package com.server.itsolution.entities;

public class FFamille {

    private Integer cbMarq, cbProt,FA_Type,FA_UniteVen,FA_Coef,FA_SuiviStock,FA_Garantie,FA_UnitePoids
            ,FA_Escompte,FA_Delai,FA_HorsStat,FA_VteDebit,FA_NotImp,cbReplication,cbFlag
            ,FA_Frais01FR_Rem01REM_Type,FA_Frais01FR_Rem02REM_Type,FA_Frais01FR_Rem03REM_Type,FA_Frais02FR_Rem01REM_Type
            ,FA_Frais02FR_Rem02REM_Type,FA_Frais02FR_Rem03REM_Type,FA_Frais03FR_Rem01REM_Type,FA_Frais03FR_Rem02REM_Type
            ,FA_Frais03FR_Rem03REM_Type,FA_Contremarque,FA_FactPoids,FA_FactForfait,FA_Publie,CL_No1,CL_No2,CL_No3,CL_No4;

    private float     FA_Frais01FR_Rem01REM_Valeu,FA_Frais01FR_Rem02REM_Valeur
                      ,FA_Frais01FR_Rem03REM_Valeur,FA_Frais02FR_Rem01REM_Valeur
                      ,FA_Frais02FR_Rem02REM_Valeur,FA_Frais02FR_Rem03REM_Valeur
                      ,FA_Frais03FR_Rem01REM_Valeur,FA_Frais03FR_Rem02REM_Valeur
                      ,FA_Frais03FR_Rem03REM_Valeur;

    private String FA_CodeFamille,FA_Intitule,FA_Central,cbModification
            ,FA_Stat01,FA_Stat02,FA_Stat03,FA_Stat04,FA_Stat05,FA_CodeFiscal,FA_Pays
            ,FA_Frais01FR_Denomination,FA_Frais02FR_Denomination,FA_Frais03FR_Denomination
            ,FA_RacineRef,FA_RacineCB,cbCreateur;

    public FFamille() {
    }

    public Integer getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(Integer cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Integer getCbProt() {
        return cbProt;
    }

    public void setCbProt(Integer cbProt) {
        this.cbProt = cbProt;
    }

    public Integer getFA_Type() {
        return FA_Type;
    }

    public void setFA_Type(Integer FA_Type) {
        this.FA_Type = FA_Type;
    }

    public Integer getFA_UniteVen() {
        return FA_UniteVen;
    }

    public void setFA_UniteVen(Integer FA_UniteVen) {
        this.FA_UniteVen = FA_UniteVen;
    }

    public Integer getFA_Coef() {
        return FA_Coef;
    }

    public void setFA_Coef(Integer FA_Coef) {
        this.FA_Coef = FA_Coef;
    }

    public Integer getFA_SuiviStock() {
        return FA_SuiviStock;
    }

    public void setFA_SuiviStock(Integer FA_SuiviStock) {
        this.FA_SuiviStock = FA_SuiviStock;
    }

    public Integer getFA_Garantie() {
        return FA_Garantie;
    }

    public void setFA_Garantie(Integer FA_Garantie) {
        this.FA_Garantie = FA_Garantie;
    }

    public Integer getFA_UnitePoids() {
        return FA_UnitePoids;
    }

    public void setFA_UnitePoids(Integer FA_UnitePoids) {
        this.FA_UnitePoids = FA_UnitePoids;
    }

    public Integer getFA_Escompte() {
        return FA_Escompte;
    }

    public void setFA_Escompte(Integer FA_Escompte) {
        this.FA_Escompte = FA_Escompte;
    }

    public Integer getFA_Delai() {
        return FA_Delai;
    }

    public void setFA_Delai(Integer FA_Delai) {
        this.FA_Delai = FA_Delai;
    }

    public Integer getFA_HorsStat() {
        return FA_HorsStat;
    }

    public void setFA_HorsStat(Integer FA_HorsStat) {
        this.FA_HorsStat = FA_HorsStat;
    }

    public Integer getFA_VteDebit() {
        return FA_VteDebit;
    }

    public void setFA_VteDebit(Integer FA_VteDebit) {
        this.FA_VteDebit = FA_VteDebit;
    }

    public Integer getFA_NotImp() {
        return FA_NotImp;
    }

    public void setFA_NotImp(Integer FA_NotImp) {
        this.FA_NotImp = FA_NotImp;
    }

    public Integer getCbReplication() {
        return cbReplication;
    }

    public void setCbReplication(Integer cbReplication) {
        this.cbReplication = cbReplication;
    }

    public Integer getCbFlag() {
        return cbFlag;
    }

    public void setCbFlag(Integer cbFlag) {
        this.cbFlag = cbFlag;
    }

    public Integer getFA_Frais01FR_Rem01REM_Type() {
        return FA_Frais01FR_Rem01REM_Type;
    }

    public void setFA_Frais01FR_Rem01REM_Type(Integer FA_Frais01FR_Rem01REM_Type) {
        this.FA_Frais01FR_Rem01REM_Type = FA_Frais01FR_Rem01REM_Type;
    }

    public Integer getFA_Frais01FR_Rem02REM_Type() {
        return FA_Frais01FR_Rem02REM_Type;
    }

    public void setFA_Frais01FR_Rem02REM_Type(Integer FA_Frais01FR_Rem02REM_Type) {
        this.FA_Frais01FR_Rem02REM_Type = FA_Frais01FR_Rem02REM_Type;
    }

    public Integer getFA_Frais01FR_Rem03REM_Type() {
        return FA_Frais01FR_Rem03REM_Type;
    }

    public void setFA_Frais01FR_Rem03REM_Type(Integer FA_Frais01FR_Rem03REM_Type) {
        this.FA_Frais01FR_Rem03REM_Type = FA_Frais01FR_Rem03REM_Type;
    }

    public Integer getFA_Frais02FR_Rem01REM_Type() {
        return FA_Frais02FR_Rem01REM_Type;
    }

    public void setFA_Frais02FR_Rem01REM_Type(Integer FA_Frais02FR_Rem01REM_Type) {
        this.FA_Frais02FR_Rem01REM_Type = FA_Frais02FR_Rem01REM_Type;
    }

    public Integer getFA_Frais02FR_Rem02REM_Type() {
        return FA_Frais02FR_Rem02REM_Type;
    }

    public void setFA_Frais02FR_Rem02REM_Type(Integer FA_Frais02FR_Rem02REM_Type) {
        this.FA_Frais02FR_Rem02REM_Type = FA_Frais02FR_Rem02REM_Type;
    }

    public Integer getFA_Frais02FR_Rem03REM_Type() {
        return FA_Frais02FR_Rem03REM_Type;
    }

    public void setFA_Frais02FR_Rem03REM_Type(Integer FA_Frais02FR_Rem03REM_Type) {
        this.FA_Frais02FR_Rem03REM_Type = FA_Frais02FR_Rem03REM_Type;
    }

    public Integer getFA_Frais03FR_Rem01REM_Type() {
        return FA_Frais03FR_Rem01REM_Type;
    }

    public void setFA_Frais03FR_Rem01REM_Type(Integer FA_Frais03FR_Rem01REM_Type) {
        this.FA_Frais03FR_Rem01REM_Type = FA_Frais03FR_Rem01REM_Type;
    }

    public Integer getFA_Frais03FR_Rem02REM_Type() {
        return FA_Frais03FR_Rem02REM_Type;
    }

    public void setFA_Frais03FR_Rem02REM_Type(Integer FA_Frais03FR_Rem02REM_Type) {
        this.FA_Frais03FR_Rem02REM_Type = FA_Frais03FR_Rem02REM_Type;
    }

    public Integer getFA_Frais03FR_Rem03REM_Type() {
        return FA_Frais03FR_Rem03REM_Type;
    }

    public void setFA_Frais03FR_Rem03REM_Type(Integer FA_Frais03FR_Rem03REM_Type) {
        this.FA_Frais03FR_Rem03REM_Type = FA_Frais03FR_Rem03REM_Type;
    }

    public Integer getFA_Contremarque() {
        return FA_Contremarque;
    }

    public void setFA_Contremarque(Integer FA_Contremarque) {
        this.FA_Contremarque = FA_Contremarque;
    }

    public Integer getFA_FactPoids() {
        return FA_FactPoids;
    }

    public void setFA_FactPoids(Integer FA_FactPoids) {
        this.FA_FactPoids = FA_FactPoids;
    }

    public Integer getFA_FactForfait() {
        return FA_FactForfait;
    }

    public void setFA_FactForfait(Integer FA_FactForfait) {
        this.FA_FactForfait = FA_FactForfait;
    }

    public Integer getFA_Publie() {
        return FA_Publie;
    }

    public void setFA_Publie(Integer FA_Publie) {
        this.FA_Publie = FA_Publie;
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

    public float getFA_Frais01FR_Rem01REM_Valeu() {
        return FA_Frais01FR_Rem01REM_Valeu;
    }

    public void setFA_Frais01FR_Rem01REM_Valeu(float FA_Frais01FR_Rem01REM_Valeu) {
        this.FA_Frais01FR_Rem01REM_Valeu = FA_Frais01FR_Rem01REM_Valeu;
    }

    public float getFA_Frais01FR_Rem02REM_Valeur() {
        return FA_Frais01FR_Rem02REM_Valeur;
    }

    public void setFA_Frais01FR_Rem02REM_Valeur(float FA_Frais01FR_Rem02REM_Valeur) {
        this.FA_Frais01FR_Rem02REM_Valeur = FA_Frais01FR_Rem02REM_Valeur;
    }

    public float getFA_Frais01FR_Rem03REM_Valeur() {
        return FA_Frais01FR_Rem03REM_Valeur;
    }

    public void setFA_Frais01FR_Rem03REM_Valeur(float FA_Frais01FR_Rem03REM_Valeur) {
        this.FA_Frais01FR_Rem03REM_Valeur = FA_Frais01FR_Rem03REM_Valeur;
    }

    public float getFA_Frais02FR_Rem01REM_Valeur() {
        return FA_Frais02FR_Rem01REM_Valeur;
    }

    public void setFA_Frais02FR_Rem01REM_Valeur(float FA_Frais02FR_Rem01REM_Valeur) {
        this.FA_Frais02FR_Rem01REM_Valeur = FA_Frais02FR_Rem01REM_Valeur;
    }

    public float getFA_Frais02FR_Rem02REM_Valeur() {
        return FA_Frais02FR_Rem02REM_Valeur;
    }

    public void setFA_Frais02FR_Rem02REM_Valeur(float FA_Frais02FR_Rem02REM_Valeur) {
        this.FA_Frais02FR_Rem02REM_Valeur = FA_Frais02FR_Rem02REM_Valeur;
    }

    public float getFA_Frais02FR_Rem03REM_Valeur() {
        return FA_Frais02FR_Rem03REM_Valeur;
    }

    public void setFA_Frais02FR_Rem03REM_Valeur(float FA_Frais02FR_Rem03REM_Valeur) {
        this.FA_Frais02FR_Rem03REM_Valeur = FA_Frais02FR_Rem03REM_Valeur;
    }

    public float getFA_Frais03FR_Rem01REM_Valeur() {
        return FA_Frais03FR_Rem01REM_Valeur;
    }

    public void setFA_Frais03FR_Rem01REM_Valeur(float FA_Frais03FR_Rem01REM_Valeur) {
        this.FA_Frais03FR_Rem01REM_Valeur = FA_Frais03FR_Rem01REM_Valeur;
    }

    public float getFA_Frais03FR_Rem02REM_Valeur() {
        return FA_Frais03FR_Rem02REM_Valeur;
    }

    public void setFA_Frais03FR_Rem02REM_Valeur(float FA_Frais03FR_Rem02REM_Valeur) {
        this.FA_Frais03FR_Rem02REM_Valeur = FA_Frais03FR_Rem02REM_Valeur;
    }

    public float getFA_Frais03FR_Rem03REM_Valeur() {
        return FA_Frais03FR_Rem03REM_Valeur;
    }

    public void setFA_Frais03FR_Rem03REM_Valeur(float FA_Frais03FR_Rem03REM_Valeur) {
        this.FA_Frais03FR_Rem03REM_Valeur = FA_Frais03FR_Rem03REM_Valeur;
    }

    public String getFA_CodeFamille() {
        return FA_CodeFamille;
    }

    public void setFA_CodeFamille(String FA_CodeFamille) {
        this.FA_CodeFamille = FA_CodeFamille;
    }

    public String getFA_Intitule() {
        return FA_Intitule;
    }

    public void setFA_Intitule(String FA_Intitule) {
        this.FA_Intitule = FA_Intitule;
    }

    public String getFA_Central() {
        return FA_Central;
    }

    public void setFA_Central(String FA_Central) {
        this.FA_Central = FA_Central;
    }

    public String getCbModification() {
        return cbModification;
    }

    public void setCbModification(String cbModification) {
        this.cbModification = cbModification;
    }

    public String getFA_Stat01() {
        return FA_Stat01;
    }

    public void setFA_Stat01(String FA_Stat01) {
        this.FA_Stat01 = FA_Stat01;
    }

    public String getFA_Stat02() {
        return FA_Stat02;
    }

    public void setFA_Stat02(String FA_Stat02) {
        this.FA_Stat02 = FA_Stat02;
    }

    public String getFA_Stat03() {
        return FA_Stat03;
    }

    public void setFA_Stat03(String FA_Stat03) {
        this.FA_Stat03 = FA_Stat03;
    }

    public String getFA_Stat04() {
        return FA_Stat04;
    }

    public void setFA_Stat04(String FA_Stat04) {
        this.FA_Stat04 = FA_Stat04;
    }

    public String getFA_Stat05() {
        return FA_Stat05;
    }

    public void setFA_Stat05(String FA_Stat05) {
        this.FA_Stat05 = FA_Stat05;
    }

    public String getFA_CodeFiscal() {
        return FA_CodeFiscal;
    }

    public void setFA_CodeFiscal(String FA_CodeFiscal) {
        this.FA_CodeFiscal = FA_CodeFiscal;
    }

    public String getFA_Pays() {
        return FA_Pays;
    }

    public void setFA_Pays(String FA_Pays) {
        this.FA_Pays = FA_Pays;
    }

    public String getFA_Frais01FR_Denomination() {
        return FA_Frais01FR_Denomination;
    }

    public void setFA_Frais01FR_Denomination(String FA_Frais01FR_Denomination) {
        this.FA_Frais01FR_Denomination = FA_Frais01FR_Denomination;
    }

    public String getFA_Frais02FR_Denomination() {
        return FA_Frais02FR_Denomination;
    }

    public void setFA_Frais02FR_Denomination(String FA_Frais02FR_Denomination) {
        this.FA_Frais02FR_Denomination = FA_Frais02FR_Denomination;
    }

    public String getFA_Frais03FR_Denomination() {
        return FA_Frais03FR_Denomination;
    }

    public void setFA_Frais03FR_Denomination(String FA_Frais03FR_Denomination) {
        this.FA_Frais03FR_Denomination = FA_Frais03FR_Denomination;
    }

    public String getFA_RacineRef() {
        return FA_RacineRef;
    }

    public void setFA_RacineRef(String FA_RacineRef) {
        this.FA_RacineRef = FA_RacineRef;
    }

    public String getFA_RacineCB() {
        return FA_RacineCB;
    }

    public void setFA_RacineCB(String FA_RacineCB) {
        this.FA_RacineCB = FA_RacineCB;
    }

    public String getCbCreateur() {
        return cbCreateur;
    }

    public void setCbCreateur(String cbCreateur) {
        this.cbCreateur = cbCreateur;
    }
}
