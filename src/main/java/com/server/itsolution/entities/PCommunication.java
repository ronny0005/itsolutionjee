package com.server.itsolution.entities;

public class PCommunication {

    private String CO_SoucheSite;

    private int N_CatTarif,N_CatCompta,DE_No,cbMarq;

    public PCommunication() {
    }

    public String getCO_SoucheSite() {
        return CO_SoucheSite;
    }

    public void setCO_SoucheSite(String CO_SoucheSite) {
        this.CO_SoucheSite = CO_SoucheSite;
    }

    public int getN_CatTarif() {
        return N_CatTarif;
    }

    public void setN_CatTarif(int n_CatTarif) {
        N_CatTarif = n_CatTarif;
    }

    public int getN_CatCompta() {
        return N_CatCompta;
    }

    public void setN_CatCompta(int n_CatCompta) {
        N_CatCompta = n_CatCompta;
    }

    public int getDE_No() {
        return DE_No;
    }

    public void setDE_No(int DE_No) {
        this.DE_No = DE_No;
    }

    public int getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(int cbMarq) {
        this.cbMarq = cbMarq;
    }
}
