package com.server.itsolution.entities;

public class FCatalogue {

    private String clIntitule,clCode,cbCreateur;

    private int cbMarq,clNo,clStock,clNoParent,clNiveau,cbReplication,cbFlag;

    public FCatalogue() {
    }

    public String getClIntitule() {
        return clIntitule;
    }

    public void setClIntitule(String clIntitule) {
        this.clIntitule = clIntitule;
    }

    public String getClCode() {
        return clCode;
    }

    public void setClCode(String clCode) {
        this.clCode = clCode;
    }

    public String getCbCreateur() {
        return cbCreateur;
    }

    public void setCbCreateur(String cbCreateur) {
        this.cbCreateur = cbCreateur;
    }

    public int getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(int cbMarq) {
        this.cbMarq = cbMarq;
    }

    public int getClNo() {
        return clNo;
    }

    public void setClNo(int clNo) {
        this.clNo = clNo;
    }

    public int getClStock() {
        return clStock;
    }

    public void setClStock(int clStock) {
        this.clStock = clStock;
    }

    public int getClNoParent() {
        return clNoParent;
    }

    public void setClNoParent(int clNoParent) {
        this.clNoParent = clNoParent;
    }

    public int getClNiveau() {
        return clNiveau;
    }

    public void setClNiveau(int clNiveau) {
        this.clNiveau = clNiveau;
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
}
