package com.server.itsolution.entities;

public class PConditionnement {

    private String P_Conditionnement;

    private int cbIndice,cbMarq;

    public PConditionnement() {
    }

    public String getP_Conditionnement() {
        return P_Conditionnement;
    }

    public void setP_Conditionnement(String p_Conditionnement) {
        P_Conditionnement = p_Conditionnement;
    }

    public int getCbIndice() {
        return cbIndice;
    }

    public void setCbIndice(int cbIndice) {
        this.cbIndice = cbIndice;
    }

    public int getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(int cbMarq) {
        this.cbMarq = cbMarq;
    }
}
