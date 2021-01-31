package com.server.itsolution.entities;

import java.math.BigDecimal;

public class SaisieAnalytique {

    private Integer Annee_Exercice, EC_Jour,EC_Sens,EC_StatusRegle,TA_Provenance,N_Reglement;

    private BigDecimal cbMarq;

    private double EC_MontantRegle,EC_MontantCredit,EC_MontantDebit;

    private String nomFichier,JO_Num,EC_RefPiece,EC_Reference,CG_Num,EC_Lettrage,CG_NumCont,CT_Num,CT_NumCont
            ,EC_Intitule,EC_Echeance,TA_Code,cbMarqList;

    public SaisieAnalytique() {
    }

    public String getCbMarqList() {
        return cbMarqList;
    }

    public void setCbMarqList(String cbMarqList) {
        this.cbMarqList = cbMarqList;
    }

    public Integer getAnnee_Exercice() {
        return Annee_Exercice;
    }

    public void setAnnee_Exercice(Integer annee_Exercice) {
        Annee_Exercice = annee_Exercice;
    }

    public Integer getEC_Jour() {
        return EC_Jour;
    }

    public void setEC_Jour(Integer EC_Jour) {
        this.EC_Jour = EC_Jour;
    }

    public Integer getEC_Sens() {
        return EC_Sens;
    }

    public void setEC_Sens(Integer EC_Sens) {
        this.EC_Sens = EC_Sens;
    }

    public Integer getEC_StatusRegle() {
        return EC_StatusRegle;
    }

    public void setEC_StatusRegle(Integer EC_StatusRegle) {
        this.EC_StatusRegle = EC_StatusRegle;
    }

    public Integer getTA_Provenance() {
        return TA_Provenance;
    }

    public void setTA_Provenance(Integer TA_Provenance) {
        this.TA_Provenance = TA_Provenance;
    }

    public Integer getN_Reglement() {
        return N_Reglement;
    }

    public void setN_Reglement(Integer n_Reglement) {
        N_Reglement = n_Reglement;
    }

    public BigDecimal getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(BigDecimal cbMarq) {
        this.cbMarq = cbMarq;
    }

    public double getEC_MontantRegle() {
        return this.EC_MontantRegle;
    }

    public void setEC_MontantRegle(double EC_MontantRegle) {
        this.EC_MontantRegle = EC_MontantRegle;
    }

    public double getEC_MontantCredit() {
        return EC_MontantCredit;
    }

    public void setEC_MontantCredit(double EC_MontantCredit) {
        this.EC_MontantCredit = EC_MontantCredit;
    }

    public double getEC_MontantDebit() {
        return EC_MontantDebit;
    }

    public void setEC_MontantDebit(double EC_MontantDebit) {
        this.EC_MontantDebit = EC_MontantDebit;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public String getJO_Num() {
        return JO_Num;
    }

    public void setJO_Num(String JO_Num) {
        this.JO_Num = JO_Num;
    }

    public String getEC_RefPiece() {
        return EC_RefPiece;
    }

    public void setEC_RefPiece(String EC_RefPiece) {
        this.EC_RefPiece = EC_RefPiece;
    }

    public String getEC_Reference() {
        return EC_Reference;
    }

    public void setEC_Reference(String EC_Reference) {
        this.EC_Reference = EC_Reference;
    }

    public String getCG_Num() {
        return CG_Num;
    }

    public void setCG_Num(String CG_Num) {
        this.CG_Num = CG_Num;
    }

    public String getEC_Lettrage() {
        return EC_Lettrage;
    }

    public void setEC_Lettrage(String EC_Lettrage) {
        this.EC_Lettrage = EC_Lettrage;
    }

    public String getCG_NumCont() {
        return CG_NumCont;
    }

    public void setCG_NumCont(String CG_NumCont) {
        this.CG_NumCont = CG_NumCont;
    }

    public String getCT_Num() {
        return CT_Num;
    }

    public void setCT_Num(String CT_Num) {
        this.CT_Num = CT_Num;
    }

    public String getCT_NumCont() {
        return CT_NumCont;
    }

    public void setCT_NumCont(String CT_NumCont) {
        this.CT_NumCont = CT_NumCont;
    }

    public String getEC_Intitule() {
        return EC_Intitule;
    }

    public void setEC_Intitule(String EC_Intitule) {
        this.EC_Intitule = EC_Intitule;
    }

    public String getEC_Echeance() {
        return EC_Echeance;
    }

    public void setEC_Echeance(String EC_Echeance) {
        this.EC_Echeance = EC_Echeance;
    }

    public String getTA_Code() {
        return TA_Code;
    }

    public void setTA_Code(String TA_Code) {
        this.TA_Code = TA_Code;
    }
}
