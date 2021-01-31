package com.server.itsolution.entities;

import java.math.BigDecimal;

public class FEcritureA {

    private int N_Analytique,EA_Ligne,cbProt,cbReplication,cbFlag,AnneExercice;

    private BigDecimal EC_No,cbMarq;

    private double EA_Montant,EA_Quantite;

    private String CA_Num,cbCreateur,cbModification,JO_Num,CG_Num,A_Intitule;

    public int getAnneExercice() {
        return AnneExercice;
    }

    public BigDecimal getEC_No() {
        return EC_No;
    }

    public void setEC_No(BigDecimal EC_No) {
        this.EC_No = EC_No;
    }

    public void setAnneExercice(int anneExercice) {
        AnneExercice = anneExercice;
    }

    public String getA_Intitule() {
        return A_Intitule;
    }

    public void setA_Intitule(String a_Intitule) {
        A_Intitule = a_Intitule;
    }

    public FEcritureA() {
    }

    public String getCG_Num() {
        return CG_Num;
    }

    public void setCG_Num(String CG_Num) {
        this.CG_Num = CG_Num;
    }

    public String getJO_Num() {
        return JO_Num;
    }

    public void setJO_Num(String JO_Num) {
        this.JO_Num = JO_Num;
    }

    public int getN_Analytique() {
        return N_Analytique;
    }

    public void setN_Analytique(int n_Analytique) {
        N_Analytique = n_Analytique;
    }

    public int getEA_Ligne() {
        return EA_Ligne;
    }

    public void setEA_Ligne(int EA_Ligne) {
        this.EA_Ligne = EA_Ligne;
    }

    public int getCbProt() {
        return cbProt;
    }

    public void setCbProt(int cbProt) {
        this.cbProt = cbProt;
    }

    public int getCbReplication() {
        return cbReplication;
    }

    public void setCbReplication(int cbReplication) {
        this.cbReplication = cbReplication;
    }

    public int getCbFlag() {
        return cbFlag;
    }

    public void setCbFlag(int cbFlag) {
        this.cbFlag = cbFlag;
    }

    public double getEA_Montant() {
        return EA_Montant;
    }

    public void setEA_Montant(double EA_Montant) {
        this.EA_Montant = EA_Montant;
    }

    public double getEA_Quantite() {
        return EA_Quantite;
    }

    public void setEA_Quantite(double EA_Quantite) {
        this.EA_Quantite = EA_Quantite;
    }

    public BigDecimal getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(BigDecimal cbMarq) {
        this.cbMarq = cbMarq;
    }

    public String getCA_Num() {
        return CA_Num;
    }

    public void setCA_Num(String CA_Num) {
        this.CA_Num = CA_Num;
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
}
