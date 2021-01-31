package com.server.itsolution.entities;

public class FCaisse {

    private Integer cbMarq, CA_No,DE_No,CO_No,CO_NoCaissier;

    private String CA_Intitule,CT_Num,JO_Num,CA_Souche,cbCreateur;

    public FCaisse() {
    }

    public FCaisse(Integer CA_No, String CA_Intitule, int cbMarq) {
        this.CA_No = CA_No;
        this.CA_Intitule = CA_Intitule;
        this.cbMarq = cbMarq;
    }

    public int getCO_NoCaissier() {
        return CO_NoCaissier;
    }

    public void setCO_NoCaissier(int CO_NoCaissier) {
        this.CO_NoCaissier = CO_NoCaissier;
    }

    public Integer getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(Integer cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Integer getCA_No() {
        return CA_No;
    }

    public void setCA_No(Integer CA_No) {
        this.CA_No = CA_No;
    }

    public Integer getDE_No() {
        return DE_No;
    }

    public void setDE_No(Integer DE_No) {
        this.DE_No = DE_No;
    }

    public Integer getCO_No() {
        return CO_No;
    }

    public void setCO_No(Integer CO_No) {
        this.CO_No = CO_No;
    }

    public void setCO_NoCaissier(Integer CO_NoCaissier) {
        this.CO_NoCaissier = CO_NoCaissier;
    }

    public String getCA_Intitule() {
        return CA_Intitule;
    }

    public void setCA_Intitule(String CA_Intitule) {
        this.CA_Intitule = CA_Intitule;
    }

    public String getCT_Num() {
        return CT_Num;
    }

    public void setCT_Num(String CT_Num) {
        this.CT_Num = CT_Num;
    }

    public String getJO_Num() {
        return JO_Num;
    }

    public void setJO_Num(String JO_Num) {
        this.JO_Num = JO_Num;
    }

    public String getCA_Souche() {
        return CA_Souche;
    }

    public void setCA_Souche(String CA_Souche) {
        this.CA_Souche = CA_Souche;
    }

    public String getCbCreateur() {
        return cbCreateur;
    }

    public void setCbCreateur(String cbCreateur) {
        this.cbCreateur = cbCreateur;
    }
}
