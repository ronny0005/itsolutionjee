
package com.server.itsolution.entities;

import java.math.BigDecimal;

public class FCReglement {

    private BigDecimal RG_No,cbMarq;
    private Integer RG_Transfere,RG_Souche,EC_No,RG_Compta,N_Devise,RG_Cloture,RG_NoBonAchat,cbProt,cbReplication,CA_No,RG_Impute,CO_NoCaissier
            ,N_Reglement,RG_Type,RG_Ticket,RG_TypeReg,RG_Banque;
    private double RG_MontantDev,RG_MontantEcart,RG_Montant,RG_Cours;
    private String CT_NumPayeur,cbCT_NumPayeur,RG_Date
            ,RG_Reference,RG_Libelle
            ,JO_Num,CG_NumCont,RG_Impaye
            ,CG_Num,RG_Heure
            ,RG_Piece,CT_NumPayeurOrig
            ,RG_DateEchCont,CG_NumEcart
            ,JO_NumEcart
            ,cbCreateur,cbModification
            ,cbFlag,DO_Modif,RG_DateSage;

    public FCReglement() {
    }


    public void setReglement(String ctNum, String rgDate, double rgMontant, String joNum, String cgNum, int caNo
            , String rgLibelle,int impute,int rgType,int modeReglement,int rgTypeReg,int rgTicket,int rgBanque,String cbCreateur){
        this.initVariables();
        this.RG_Date = rgDate;
        this.CT_NumPayeur = ctNum;
        this.CT_NumPayeurOrig = ctNum;
        this.CA_No = caNo;
        this.CG_Num = cgNum;
        this.RG_Reference = "";
        this.JO_Num = joNum;
        this.CO_NoCaissier = 0;
        this.RG_Cloture = 0;
        this.RG_Montant = rgMontant;
        this.RG_Libelle = rgLibelle;
        this.RG_Impute = impute;
        this.RG_Type = rgType;
        this.N_Reglement = modeReglement;
        this.RG_TypeReg=rgTypeReg;
        this.RG_Ticket=rgTicket;
        this.RG_Banque=rgBanque;
        this.N_Devise = 0;
        this.RG_Cours = 0;
        this.RG_DateEchCont=rgDate;
        this.cbCreateur = cbCreateur;
    }

    public void initVariables(){
        this.RG_Reference="";
        this.RG_MontantDev=0;
        this.RG_Compta=0;
        this.EC_No=0;
        this.RG_Cours=0;
        this.N_Devise=0;
        this.CG_NumCont=null;
        this.RG_Impaye="1900-01-01";
        this.RG_Transfere = 0;
        this.RG_Cloture=0;
        this.RG_Souche=0;
        this.CG_NumEcart=null;
        this.JO_NumEcart=null;
        this.RG_MontantEcart=0;
        this.RG_TypeReg=0;
        this.RG_NoBonAchat=0;
        this.cbCreateur="AND";
    }

    public Integer getCbProt() {
        return cbProt;
    }

    public void setCbProt(Integer cbProt) {
        this.cbProt = cbProt;
    }

    public Integer getCbReplication() {
        return cbReplication;
    }

    public void setCbReplication(Integer cbReplication) {
        this.cbReplication = cbReplication;
    }

    public Integer getRG_Banque() {
        return RG_Banque;
    }

    public void setRG_Banque(Integer RG_Banque) {
        this.RG_Banque = RG_Banque;
    }

    public Integer getCA_No() {
        return CA_No;
    }

    public void setCA_No(Integer CA_No) {
        this.CA_No = CA_No;
    }

    public Integer getRG_Impute() {
        return RG_Impute;
    }

    public void setRG_Impute(Integer RG_Impute) {
        this.RG_Impute = RG_Impute;
    }

    public Integer getCO_NoCaissier() {
        return CO_NoCaissier;
    }

    public void setCO_NoCaissier(Integer CO_NoCaissier) {
        this.CO_NoCaissier = CO_NoCaissier;
    }

    public Integer getN_Reglement() {
        return N_Reglement;
    }

    public void setN_Reglement(Integer n_Reglement) {
        N_Reglement = n_Reglement;
    }

    public Integer getRG_Type() {
        return RG_Type;
    }

    public void setRG_Type(Integer RG_Type) {
        this.RG_Type = RG_Type;
    }

    public Integer getRG_Ticket() {
        return RG_Ticket;
    }

    public void setRG_Ticket(Integer RG_Ticket) {
        this.RG_Ticket = RG_Ticket;
    }

    public Integer getRG_TypeReg() {
        return RG_TypeReg;
    }

    public void setRG_TypeReg(Integer RG_TypeReg) {
        this.RG_TypeReg = RG_TypeReg;
    }

    public double getRG_MontantDev() {
        return RG_MontantDev;
    }

    public void setRG_MontantDev(double RG_MontantDev) {
        this.RG_MontantDev = RG_MontantDev;
    }

    public double getRG_MontantEcart() {
        return RG_MontantEcart;
    }

    public void setRG_MontantEcart(double RG_MontantEcart) {
        this.RG_MontantEcart = RG_MontantEcart;
    }

    public double getRG_Montant() {
        return RG_Montant;
    }

    public void setRG_Montant(double RG_Montant) {
        this.RG_Montant = RG_Montant;
    }

    public String getCT_NumPayeur() {
        return CT_NumPayeur;
    }

    public void setCT_NumPayeur(String CT_NumPayeur) {
        this.CT_NumPayeur = CT_NumPayeur;
    }

    public String getCbCT_NumPayeur() {
        return cbCT_NumPayeur;
    }

    public void setCbCT_NumPayeur(String cbCT_NumPayeur) {
        this.cbCT_NumPayeur = cbCT_NumPayeur;
    }

    public String getRG_Date() {
        return RG_Date;
    }

    public void setRG_Date(String RG_Date) {
        this.RG_Date = RG_Date;
    }

    public String getRG_Reference() {
        return RG_Reference;
    }

    public void setRG_Reference(String RG_Reference) {
        this.RG_Reference = RG_Reference;
    }

    public String getRG_Libelle() {
        return RG_Libelle;
    }

    public void setRG_Libelle(String RG_Libelle) {
        this.RG_Libelle = RG_Libelle;
    }

    public String getJO_Num() {
        return JO_Num;
    }

    public void setJO_Num(String JO_Num) {
        this.JO_Num = JO_Num;
    }

    public String getCG_NumCont() {
        return CG_NumCont;
    }

    public void setCG_NumCont(String CG_NumCont) {
        this.CG_NumCont = CG_NumCont;
    }

    public String getRG_Impaye() {
        return RG_Impaye;
    }

    public void setRG_Impaye(String RG_Impaye) {
        this.RG_Impaye = RG_Impaye;
    }

    public String getCG_Num() {
        return CG_Num;
    }

    public void setCG_Num(String CG_Num) {
        this.CG_Num = CG_Num;
    }

    public String getRG_Heure() {
        return RG_Heure;
    }

    public void setRG_Heure(String RG_Heure) {
        this.RG_Heure = RG_Heure;
    }

    public String getRG_Piece() {
        return RG_Piece;
    }

    public void setRG_Piece(String RG_Piece) {
        this.RG_Piece = RG_Piece;
    }

    public String getCT_NumPayeurOrig() {
        return CT_NumPayeurOrig;
    }

    public void setCT_NumPayeurOrig(String CT_NumPayeurOrig) {
        this.CT_NumPayeurOrig = CT_NumPayeurOrig;
    }

    public String getRG_DateEchCont() {
        return RG_DateEchCont;
    }

    public void setRG_DateEchCont(String RG_DateEchCont) {
        this.RG_DateEchCont = RG_DateEchCont;
    }

    public String getCG_NumEcart() {
        return CG_NumEcart;
    }

    public void setCG_NumEcart(String CG_NumEcart) {
        this.CG_NumEcart = CG_NumEcart;
    }

    public String getJO_NumEcart() {
        return JO_NumEcart;
    }

    public void setJO_NumEcart(String JO_NumEcart) {
        this.JO_NumEcart = JO_NumEcart;
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

    public String getCbFlag() {
        return cbFlag;
    }

    public void setCbFlag(String cbFlag) {
        this.cbFlag = cbFlag;
    }

    public String getDO_Modif() {
        return DO_Modif;
    }

    public void setDO_Modif(String DO_Modif) {
        this.DO_Modif = DO_Modif;
    }

    public String getRG_DateSage() {
        return RG_DateSage;
    }

    public void setRG_DateSage(String RG_DateSage) {
        this.RG_DateSage = RG_DateSage;
    }

    public BigDecimal getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(BigDecimal cbMarq) {
        this.cbMarq = cbMarq;
    }

    public BigDecimal getRG_No() {
        return RG_No;
    }

    public void setRG_No(BigDecimal RG_No) {
        this.RG_No = RG_No;
    }

    public Integer getRG_Transfere() {
        return RG_Transfere;
    }

    public void setRG_Transfere(Integer RG_Transfere) {
        this.RG_Transfere = RG_Transfere;
    }

    public Integer getRG_Souche() {
        return RG_Souche;
    }

    public void setRG_Souche(Integer RG_Souche) {
        this.RG_Souche = RG_Souche;
    }

    public Integer getEC_No() {
        return EC_No;
    }

    public void setEC_No(Integer EC_No) {
        this.EC_No = EC_No;
    }

    public Integer getRG_Compta() {
        return RG_Compta;
    }

    public void setRG_Compta(Integer RG_Compta) {
        this.RG_Compta = RG_Compta;
    }

    public double getRG_Cours() {
        return RG_Cours;
    }

    public void setRG_Cours(double RG_Cours) {
        this.RG_Cours = RG_Cours;
    }

    public Integer getN_Devise() {
        return N_Devise;
    }

    public void setN_Devise(Integer n_Devise) {
        N_Devise = n_Devise;
    }

    public Integer getRG_Cloture() {
        return RG_Cloture;
    }

    public void setRG_Cloture(Integer RG_Cloture) {
        this.RG_Cloture = RG_Cloture;
    }

    public Integer getRG_NoBonAchat() {
        return RG_NoBonAchat;
    }

    public void setRG_NoBonAchat(Integer RG_NoBonAchat) {
        this.RG_NoBonAchat = RG_NoBonAchat;
    }
}
