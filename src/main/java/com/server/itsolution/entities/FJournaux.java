package com.server.itsolution.entities;

import java.math.BigDecimal;

public class FJournaux {

    private Integer JO_Type,JO_NumPiece,JO_Contrepartie,JO_SaisAnal,JO_NotCalcTot
            ,JO_Rappro,JO_Sommeil,JO_IFRS,JO_Reglement,JO_SuiviTreso;

    private String JO_Num,JO_Intitule,CG_Num
    ,cbCreateur,cbModification;;

    public BigDecimal cbMarq;

    public FJournaux() {
    }

    public Integer getJO_Type() {
        return JO_Type;
    }

    public void setJO_Type(Integer JO_Type) {
        this.JO_Type = JO_Type;
    }

    public Integer getJO_NumPiece() {
        return JO_NumPiece;
    }

    public void setJO_NumPiece(Integer JO_NumPiece) {
        this.JO_NumPiece = JO_NumPiece;
    }

    public Integer getJO_Contrepartie() {
        return JO_Contrepartie;
    }

    public void setJO_Contrepartie(Integer JO_Contrepartie) {
        this.JO_Contrepartie = JO_Contrepartie;
    }

    public Integer getJO_SaisAnal() {
        return JO_SaisAnal;
    }

    public void setJO_SaisAnal(Integer JO_SaisAnal) {
        this.JO_SaisAnal = JO_SaisAnal;
    }

    public Integer getJO_NotCalcTot() {
        return JO_NotCalcTot;
    }

    public void setJO_NotCalcTot(Integer JO_NotCalcTot) {
        this.JO_NotCalcTot = JO_NotCalcTot;
    }

    public Integer getJO_Rappro() {
        return JO_Rappro;
    }

    public void setJO_Rappro(Integer JO_Rappro) {
        this.JO_Rappro = JO_Rappro;
    }

    public Integer getJO_Sommeil() {
        return JO_Sommeil;
    }

    public void setJO_Sommeil(Integer JO_Sommeil) {
        this.JO_Sommeil = JO_Sommeil;
    }

    public Integer getJO_IFRS() {
        return JO_IFRS;
    }

    public void setJO_IFRS(Integer JO_IFRS) {
        this.JO_IFRS = JO_IFRS;
    }

    public Integer getJO_Reglement() {
        return JO_Reglement;
    }

    public void setJO_Reglement(Integer JO_Reglement) {
        this.JO_Reglement = JO_Reglement;
    }

    public Integer getJO_SuiviTreso() {
        return JO_SuiviTreso;
    }

    public void setJO_SuiviTreso(Integer JO_SuiviTreso) {
        this.JO_SuiviTreso = JO_SuiviTreso;
    }

    public String getJO_Num() {
        return JO_Num;
    }

    public void setJO_Num(String JO_Num) {
        this.JO_Num = JO_Num;
    }

    public String getJO_Intitule() {
        return JO_Intitule;
    }

    public void setJO_Intitule(String JO_Intitule) {
        this.JO_Intitule = JO_Intitule;
    }

    public String getCG_Num() {
        return CG_Num;
    }

    public void setCG_Num(String CG_Num) {
        this.CG_Num = CG_Num;
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

    public BigDecimal getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(BigDecimal cbMarq) {
        this.cbMarq = cbMarq;
    }
}
