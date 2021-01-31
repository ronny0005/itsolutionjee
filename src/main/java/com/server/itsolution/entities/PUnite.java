package com.server.itsolution.entities;

public class PUnite {

    private String U_Intitule;

    private int U_Correspondance,U_NbUnite,U_UniteTemps,cbIndice,cbMarq;

    public PUnite() {
    }

    public String getU_Intitule() {
        return U_Intitule;
    }

    public void setU_Intitule(String u_Intitule) {
        U_Intitule = u_Intitule;
    }

    public int getU_Correspondance() {
        return U_Correspondance;
    }

    public void setU_Correspondance(int u_Correspondance) {
        U_Correspondance = u_Correspondance;
    }

    public int getU_NbUnite() {
        return U_NbUnite;
    }

    public void setU_NbUnite(int u_NbUnite) {
        U_NbUnite = u_NbUnite;
    }

    public int getU_UniteTemps() {
        return U_UniteTemps;
    }

    public void setU_UniteTemps(int u_UniteTemps) {
        U_UniteTemps = u_UniteTemps;
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
