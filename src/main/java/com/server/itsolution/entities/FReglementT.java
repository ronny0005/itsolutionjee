package com.server.itsolution.entities;

public class FReglementT {

    private Integer cbMarq, N_Reglement
,RT_Condition
,RT_NbJour
,RT_JourTb01
,RT_JourTb02
,RT_JourTb03
,RT_JourTb04
,RT_JourTb05
,RT_JourTb06
,RT_TRepart;

    private double RT_VRepart;

    private String cbCreateur;

    public FReglementT() {
    }

    public void init(){
        N_Reglement = 0;
        RT_Condition = 0;
        RT_NbJour = 0;
        RT_JourTb01 = 0;
        RT_JourTb02 = 0;
        RT_JourTb03 = 0;
        RT_JourTb04 = 0;
        RT_JourTb05 = 0;
        RT_JourTb06 = 0;
        RT_TRepart = 0;
        RT_VRepart = 0;
    }

    public Integer getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(Integer cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Integer getN_Reglement() {
        return N_Reglement;
    }

    public void setN_Reglement(Integer n_Reglement) {
        N_Reglement = n_Reglement;
    }

    public Integer getRT_Condition() {
        return RT_Condition;
    }

    public void setRT_Condition(Integer RT_Condition) {
        this.RT_Condition = RT_Condition;
    }

    public Integer getRT_NbJour() {
        return RT_NbJour;
    }

    public void setRT_NbJour(Integer RT_NbJour) {
        this.RT_NbJour = RT_NbJour;
    }

    public Integer getRT_JourTb01() {
        return RT_JourTb01;
    }

    public void setRT_JourTb01(Integer RT_JourTb01) {
        this.RT_JourTb01 = RT_JourTb01;
    }

    public Integer getRT_JourTb02() {
        return RT_JourTb02;
    }

    public void setRT_JourTb02(Integer RT_JourTb02) {
        this.RT_JourTb02 = RT_JourTb02;
    }

    public Integer getRT_JourTb03() {
        return RT_JourTb03;
    }

    public void setRT_JourTb03(Integer RT_JourTb03) {
        this.RT_JourTb03 = RT_JourTb03;
    }

    public Integer getRT_JourTb04() {
        return RT_JourTb04;
    }

    public void setRT_JourTb04(Integer RT_JourTb04) {
        this.RT_JourTb04 = RT_JourTb04;
    }

    public Integer getRT_JourTb05() {
        return RT_JourTb05;
    }

    public void setRT_JourTb05(Integer RT_JourTb05) {
        this.RT_JourTb05 = RT_JourTb05;
    }

    public Integer getRT_JourTb06() {
        return RT_JourTb06;
    }

    public void setRT_JourTb06(Integer RT_JourTb06) {
        this.RT_JourTb06 = RT_JourTb06;
    }

    public Integer getRT_TRepart() {
        return RT_TRepart;
    }

    public void setRT_TRepart(Integer RT_TRepart) {
        this.RT_TRepart = RT_TRepart;
    }

    public double getRT_VRepart() {
        return RT_VRepart;
    }

    public void setRT_VRepart(double RT_VRepart) {
        this.RT_VRepart = RT_VRepart;
    }

    public String getCbCreateur() {
        return cbCreateur;
    }

    public void setCbCreateur(String cbCreateur) {
        this.cbCreateur = cbCreateur;
    }
}
