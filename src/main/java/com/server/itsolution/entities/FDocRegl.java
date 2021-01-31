package com.server.itsolution.entities;

import java.math.BigDecimal;

public class FDocRegl {

    private Integer DR_No, DO_Domaine, DO_Type, DR_TypeRegl
                    ,DR_Equil,EC_No,DR_Regle, N_Reglement;
    private float DR_Montant,DR_MontantDev,DR_Pourcent;
    private BigDecimal cbMarq;
    private String  DR_Date,DR_Libelle,cbCreateur,cbModification,DO_Piece;

    public FDocRegl() {
    }

    public BigDecimal getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(BigDecimal cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Integer getDR_No() {
        return DR_No;
    }

    public void setDR_No(Integer DR_No) {
        this.DR_No = DR_No;
    }

    public Integer getDO_Domaine() {
        return DO_Domaine;
    }

    public void setDO_Domaine(Integer DO_Domaine) {
        this.DO_Domaine = DO_Domaine;
    }

    public Integer getDO_Type() {
        return DO_Type;
    }

    public void setDO_Type(Integer DO_Type) {
        this.DO_Type = DO_Type;
    }

    public String getDO_Piece() {
        return DO_Piece;
    }

    public void setDO_Piece(String DO_Piece) {
        this.DO_Piece = DO_Piece;
    }

    public Integer getDR_TypeRegl() {
        return DR_TypeRegl;
    }

    public void setDR_TypeRegl(Integer DR_TypeRegl) {
        this.DR_TypeRegl = DR_TypeRegl;
    }

    public float getDR_Pourcent() {
        return DR_Pourcent;
    }

    public void setDR_Pourcent(float DR_Pourcent) {
        this.DR_Pourcent = DR_Pourcent;
    }

    public float getDR_Montant() {
        return DR_Montant;
    }

    public void setDR_Montant(float DR_Montant) {
        this.DR_Montant = DR_Montant;
    }

    public float getDR_MontantDev() {
        return DR_MontantDev;
    }

    public void setDR_MontantDev(float DR_MontantDev) {
        this.DR_MontantDev = DR_MontantDev;
    }

    public Integer getDR_Equil() {
        return DR_Equil;
    }

    public void setDR_Equil(Integer DR_Equil) {
        this.DR_Equil = DR_Equil;
    }

    public Integer getEC_No() {
        return EC_No;
    }

    public void setEC_No(Integer EC_No) {
        this.EC_No = EC_No;
    }

    public Integer getDR_Regle() {
        return DR_Regle;
    }

    public void setDR_Regle(Integer DR_Regle) {
        this.DR_Regle = DR_Regle;
    }

    public Integer getN_Reglement() {
        return N_Reglement;
    }

    public void setN_Reglement(Integer n_Reglement) {
        N_Reglement = n_Reglement;
    }

    public void setCbModification(String cbModification) {
        this.cbModification = cbModification;
    }

    public String getDR_Date() {
        return DR_Date;
    }

    public void setDR_Date(String DR_Date) {
        this.DR_Date = DR_Date;
    }

    public String getDR_Libelle() {
        return DR_Libelle;
    }

    public void setDR_Libelle(String DR_Libelle) {
        this.DR_Libelle = DR_Libelle;
    }

    public String getCbCreateur() {
        return cbCreateur;
    }

    public void setCbCreateur(String cbCreateur) {
        this.cbCreateur = cbCreateur;
    }
}
