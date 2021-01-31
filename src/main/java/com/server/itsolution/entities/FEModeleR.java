package com.server.itsolution.entities;

import java.math.BigDecimal;

public class FEModeleR {

    private Integer MR_No
            ,N_Reglement
            ,ER_Condition
            ,ER_NbJour
            ,ER_JourTb01
            ,ER_JourTb02
            ,ER_JourTb03
            ,ER_JourTb04
            ,ER_JourTb05
            ,ER_JourTb06
            ,ER_TRepart;
    private Float ER_VRepart;
    private BigDecimal cbMarq;

    public FEModeleR() {
    }

    public Integer getMR_No() {
        return MR_No;
    }

    public void setMR_No(Integer MR_No) {
        this.MR_No = MR_No;
    }

    public Integer getN_Reglement() {
        return N_Reglement;
    }

    public void setN_Reglement(Integer n_Reglement) {
        N_Reglement = n_Reglement;
    }

    public Integer getER_Condition() {
        return ER_Condition;
    }

    public void setER_Condition(Integer ER_Condition) {
        this.ER_Condition = ER_Condition;
    }

    public Integer getER_NbJour() {
        return ER_NbJour;
    }

    public void setER_NbJour(Integer ER_NbJour) {
        this.ER_NbJour = ER_NbJour;
    }

    public Integer getER_JourTb01() {
        return ER_JourTb01;
    }

    public void setER_JourTb01(Integer ER_JourTb01) {
        this.ER_JourTb01 = ER_JourTb01;
    }

    public Integer getER_JourTb02() {
        return ER_JourTb02;
    }

    public void setER_JourTb02(Integer ER_JourTb02) {
        this.ER_JourTb02 = ER_JourTb02;
    }

    public Integer getER_JourTb03() {
        return ER_JourTb03;
    }

    public void setER_JourTb03(Integer ER_JourTb03) {
        this.ER_JourTb03 = ER_JourTb03;
    }

    public Integer getER_JourTb04() {
        return ER_JourTb04;
    }

    public void setER_JourTb04(Integer ER_JourTb04) {
        this.ER_JourTb04 = ER_JourTb04;
    }

    public Integer getER_JourTb05() {
        return ER_JourTb05;
    }

    public void setER_JourTb05(Integer ER_JourTb05) {
        this.ER_JourTb05 = ER_JourTb05;
    }

    public Integer getER_JourTb06() {
        return ER_JourTb06;
    }

    public void setER_JourTb06(Integer ER_JourTb06) {
        this.ER_JourTb06 = ER_JourTb06;
    }

    public Integer getER_TRepart() {
        return ER_TRepart;
    }

    public void setER_TRepart(Integer ER_TRepart) {
        this.ER_TRepart = ER_TRepart;
    }

    public Float getER_VRepart() {
        return ER_VRepart;
    }

    public void setER_VRepart(Float ER_VRepart) {
        this.ER_VRepart = ER_VRepart;
    }

    public BigDecimal getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(BigDecimal cbMarq) {
        this.cbMarq = cbMarq;
    }

}
