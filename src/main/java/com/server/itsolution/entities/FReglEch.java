package com.server.itsolution.entities;

import java.math.BigDecimal;

public class FReglEch {

    private BigDecimal cbMarq,RG_No;
    private double rcMontant;

    private Integer DR_No, DO_Domaine, DO_Type, RG_TypeReg;

    private String  DO_Piece,cbCreateur;

    public FReglEch() {
    }

    public BigDecimal getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(BigDecimal cbMarq) {
        this.cbMarq = cbMarq;
    }

    public double getRcMontant() {
        return rcMontant;
    }

    public void setRcMontant(double rcMontant) {
        this.rcMontant = rcMontant;
    }

    public BigDecimal getRG_No() {
        return RG_No;
    }

    public void setRG_No(BigDecimal RG_No) {
        this.RG_No = RG_No;
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

    public Integer getRG_TypeReg() {
        return RG_TypeReg;
    }

    public void setRG_TypeReg(Integer RG_TypeReg) {
        this.RG_TypeReg = RG_TypeReg;
    }

    public String getDO_Piece() {
        return DO_Piece;
    }

    public void setDO_Piece(String DO_Piece) {
        this.DO_Piece = DO_Piece;
    }

    public String getCbCreateur() {
        return cbCreateur;
    }

    public void setCbCreateur(String cbCreateur) {
        this.cbCreateur = cbCreateur;
    }

    @Override
    public String toString() {
        return "FReglEch{" +
                "cbMarq=" + cbMarq +
                ", RG_No=" + RG_No +
                ", rcMontant=" + rcMontant +
                ", DR_No=" + DR_No +
                ", DO_Domaine=" + DO_Domaine +
                ", DO_Type=" + DO_Type +
                ", RG_TypeReg=" + RG_TypeReg +
                ", DO_Piece='" + DO_Piece + '\'' +
                ", cbCreateur='" + cbCreateur + '\'' +
                '}';
    }
}
