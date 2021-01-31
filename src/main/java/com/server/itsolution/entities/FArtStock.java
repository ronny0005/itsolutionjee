package com.server.itsolution.entities;

public class FArtStock {

    private Integer cbMarq, DE_No,AS_Principal;

    private double AS_MontSto,AS_QteMini,AS_QteMaxi,AS_QteSto,AS_QteRes,AS_QteCom,cbCreateur;

    private String AR_Ref;

    public FArtStock() {
    }

    public String getAR_Ref() {
        return AR_Ref;
    }

    public void setAR_Ref(String AR_Ref) {
        this.AR_Ref = AR_Ref;
    }

    public Integer getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(Integer cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Integer getDE_No() {
        return DE_No;
    }

    public void setDE_No(Integer DE_No) {
        this.DE_No = DE_No;
    }

    public Integer getAS_Principal() {
        return AS_Principal;
    }

    public void setAS_Principal(Integer AS_Principal) {
        this.AS_Principal = AS_Principal;
    }

    public double getAS_MontSto() {
        return AS_MontSto;
    }

    public void setAS_MontSto(double AS_MontSto) {
        this.AS_MontSto = AS_MontSto;
    }

    public double getAS_QteMini() {
        return AS_QteMini;
    }

    public void setAS_QteMini(double AS_QteMini) {
        this.AS_QteMini = AS_QteMini;
    }

    public double getAS_QteMaxi() {
        return AS_QteMaxi;
    }

    public void setAS_QteMaxi(double AS_QteMaxi) {
        this.AS_QteMaxi = AS_QteMaxi;
    }

    public double getAS_QteSto() {
        return AS_QteSto;
    }

    public void setAS_QteSto(double AS_QteSto) {
        this.AS_QteSto = AS_QteSto;
    }

    public double getAS_QteRes() {
        return AS_QteRes;
    }

    public void setAS_QteRes(double AS_QteRes) {
        this.AS_QteRes = AS_QteRes;
    }

    public double getAS_QteCom() {
        return AS_QteCom;
    }

    public void setAS_QteCom(double AS_QteCom) {
        this.AS_QteCom = AS_QteCom;
    }

    public double getCbCreateur() {
        return cbCreateur;
    }

    public void setCbCreateur(double cbCreateur) {
        this.cbCreateur = cbCreateur;
    }
}
