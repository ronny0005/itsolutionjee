package com.server.itsolution.entities;

import java.math.BigDecimal;

public class FTaxe {

    private String TA_Intitule,CG_Num
            ,TA_GrilleBase,TA_GrilleTaxe,cbProt,cbCreateur
            ,cbModification,cbReplication,cbFlag;

    private BigDecimal cbMarq;
    private Double TA_Taux,TA_Assujet;
    private int TA_TTaux,TA_Type,TA_Code,TA_NP,TA_No,TA_Sens,TA_Provenance,TA_Regroup;

    public FTaxe() {
    }

    public String getTA_Intitule() {
        return TA_Intitule;
    }

    public void setTA_Intitule(String TA_Intitule) {
        this.TA_Intitule = TA_Intitule;
    }

    public String getCG_Num() {
        return CG_Num;
    }

    public void setCG_Num(String CG_Num) {
        this.CG_Num = CG_Num;
    }

    public String getTA_GrilleBase() {
        return TA_GrilleBase;
    }

    public void setTA_GrilleBase(String TA_GrilleBase) {
        this.TA_GrilleBase = TA_GrilleBase;
    }

    public String getTA_GrilleTaxe() {
        return TA_GrilleTaxe;
    }

    public void setTA_GrilleTaxe(String TA_GrilleTaxe) {
        this.TA_GrilleTaxe = TA_GrilleTaxe;
    }

    public String getCbProt() {
        return cbProt;
    }

    public void setCbProt(String cbProt) {
        this.cbProt = cbProt;
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

    public String getCbReplication() {
        return cbReplication;
    }

    public void setCbReplication(String cbReplication) {
        this.cbReplication = cbReplication;
    }

    public String getCbFlag() {
        return cbFlag;
    }

    public void setCbFlag(String cbFlag) {
        this.cbFlag = cbFlag;
    }

    public BigDecimal getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(BigDecimal cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Double getTA_Taux() {
        return TA_Taux;
    }

    public void setTA_Taux(Double TA_Taux) {
        this.TA_Taux = TA_Taux;
    }

    public Double getTA_Assujet() {
        return TA_Assujet;
    }

    public void setTA_Assujet(Double TA_Assujet) {
        this.TA_Assujet = TA_Assujet;
    }

    public int getTA_TTaux() {
        return TA_TTaux;
    }

    public void setTA_TTaux(int TA_TTaux) {
        this.TA_TTaux = TA_TTaux;
    }

    public int getTA_Type() {
        return TA_Type;
    }

    public void setTA_Type(int TA_Type) {
        this.TA_Type = TA_Type;
    }

    public int getTA_Code() {
        return TA_Code;
    }

    public void setTA_Code(int TA_Code) {
        this.TA_Code = TA_Code;
    }

    public int getTA_NP() {
        return TA_NP;
    }

    public void setTA_NP(int TA_NP) {
        this.TA_NP = TA_NP;
    }

    public int getTA_No() {
        return TA_No;
    }

    public void setTA_No(int TA_No) {
        this.TA_No = TA_No;
    }

    public int getTA_Sens() {
        return TA_Sens;
    }

    public void setTA_Sens(int TA_Sens) {
        this.TA_Sens = TA_Sens;
    }

    public int getTA_Provenance() {
        return TA_Provenance;
    }

    public void setTA_Provenance(int TA_Provenance) {
        this.TA_Provenance = TA_Provenance;
    }

    public int getTA_Regroup() {
        return TA_Regroup;
    }

    public void setTA_Regroup(int TA_Regroup) {
        this.TA_Regroup = TA_Regroup;
    }
}
