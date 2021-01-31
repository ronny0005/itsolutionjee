package com.server.itsolution.entities;

public class FArtcompta {

    private Integer cbMarq, cbProt,ACP_Type,ACP_Champ,ACP_TypeFacture,cbFlag;

    private String  AR_Ref,ACP_ComptaCPT_CompteG,ACP_ComptaCPT_CompteA,ACP_ComptaCPT_Taxe1
                    ,ACP_ComptaCPT_Taxe2,ACP_ComptaCPT_Taxe3,cbCreateur,cbModification;

    public FArtcompta() {
    }

    public Integer getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(Integer cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Integer getCbProt() {
        return cbProt;
    }

    public void setCbProt(Integer cbProt) {
        this.cbProt = cbProt;
    }

    public Integer getACP_Type() {
        return ACP_Type;
    }

    public void setACP_Type(Integer ACP_Type) {
        this.ACP_Type = ACP_Type;
    }

    public Integer getACP_Champ() {
        return ACP_Champ;
    }

    public void setACP_Champ(Integer ACP_Champ) {
        this.ACP_Champ = ACP_Champ;
    }

    public Integer getACP_TypeFacture() {
        return ACP_TypeFacture;
    }

    public void setACP_TypeFacture(Integer ACP_TypeFacture) {
        this.ACP_TypeFacture = ACP_TypeFacture;
    }

    public Integer getCbFlag() {
        return cbFlag;
    }

    public void setCbFlag(Integer cbFlag) {
        this.cbFlag = cbFlag;
    }

    public String getAR_Ref() {
        return AR_Ref;
    }

    public void setAR_Ref(String AR_Ref) {
        this.AR_Ref = AR_Ref;
    }

    public String getACP_ComptaCPT_CompteG() {
        return ACP_ComptaCPT_CompteG;
    }

    public void setACP_ComptaCPT_CompteG(String ACP_ComptaCPT_CompteG) {
        this.ACP_ComptaCPT_CompteG = ACP_ComptaCPT_CompteG;
    }

    public String getACP_ComptaCPT_CompteA() {
        return ACP_ComptaCPT_CompteA;
    }

    public void setACP_ComptaCPT_CompteA(String ACP_ComptaCPT_CompteA) {
        this.ACP_ComptaCPT_CompteA = ACP_ComptaCPT_CompteA;
    }

    public String getACP_ComptaCPT_Taxe1() {
        return ACP_ComptaCPT_Taxe1;
    }

    public void setACP_ComptaCPT_Taxe1(String ACP_ComptaCPT_Taxe1) {
        this.ACP_ComptaCPT_Taxe1 = ACP_ComptaCPT_Taxe1;
    }

    public String getACP_ComptaCPT_Taxe2() {
        return ACP_ComptaCPT_Taxe2;
    }

    public void setACP_ComptaCPT_Taxe2(String ACP_ComptaCPT_Taxe2) {
        this.ACP_ComptaCPT_Taxe2 = ACP_ComptaCPT_Taxe2;
    }

    public String getACP_ComptaCPT_Taxe3() {
        return ACP_ComptaCPT_Taxe3;
    }

    public void setACP_ComptaCPT_Taxe3(String ACP_ComptaCPT_Taxe3) {
        this.ACP_ComptaCPT_Taxe3 = ACP_ComptaCPT_Taxe3;
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
}
