package com.server.itsolution.entities;

public class LogFile {
    private String action,type,doEntete,arRef,date,userName,cbMarq,table,cbCreateur,dateDocument;

    private int doType,deNo,doDomaine;

    private double prix,remise,montant,qte;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoEntete() {
        return doEntete;
    }

    public void setDoEntete(String doEntete) {
        this.doEntete = doEntete;
    }

    public String getArRef() {
        return arRef;
    }

    public void setArRef(String arRef) {
        this.arRef = arRef;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(String cbMarq) {
        this.cbMarq = cbMarq;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCbCreateur() {
        return cbCreateur;
    }

    public void setCbCreateur(String cbCreateur) {
        this.cbCreateur = cbCreateur;
    }

    public int getDoType() {
        return doType;
    }

    public void setDoType(int doType) {
        this.doType = doType;
    }

    public int getDeNo() {
        return deNo;
    }

    public void setDeNo(int deNo) {
        this.deNo = deNo;
    }

    public int getDoDomaine() {
        return doDomaine;
    }

    public void setDoDomaine(int doDomaine) {
        this.doDomaine = doDomaine;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(float qte) {
        this.qte = qte;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getDateDocument() {
        return dateDocument;
    }

    public void setDateDocument(String dateDocument) {
        this.dateDocument = dateDocument;
    }

    public void writeFacture(String action, int doType, String doEntete, int doDomaine, String arRef, double qte, double prix, double remise, double montant, String cbMarq, String table, String cbCreateur){
        this.action = action;
        this.doDomaine = doDomaine;
        this.doType = doType;
        this.doEntete = doEntete;
        this.arRef = arRef;
        this.qte = qte;
        this.prix = prix;
        this.remise = remise;
        this.montant = montant;
        this.cbMarq = cbMarq;
        this.table = table;
        this.userName = userName;
        this.cbCreateur = cbCreateur;
        if(doDomaine==0){
            if(doType==0)
                this.type="Devis";
            if(doType==6)
                this.type="Vente";
            if(doType==7)
                this.type="Vente Comptabilisée";
        }
        if(doDomaine==1){
            if(doType==12)
                this.type="AchatPrecommande";
            if(doType==16)
                this.type="Achat";
            if(doType==17)
                this.type="Achat comptabilisé";
        }
    }

    public void writeReglement(String action,float montant,String rgNo,String rgPiece,String cbMarq,String table,String cbCreateur,String rgDate){
        this.type="Reglement";
        this.action = action;
        this.doDomaine = 0;
        this.doType = 0;
        this.doEntete = rgNo;
        this.arRef = rgPiece;
        this.qte = 0;
        this.prix = 0;
        this.remise = 0;
        this.deNo = 0;
        this.montant = montant;
        this.cbMarq = cbMarq;
        this.table = table;
        this.cbCreateur = cbCreateur;
        this.dateDocument = rgDate;
    }

    public void writeStock(String type,String arRef,int deNo,double qte,double montant,String cbMarq,String table,String cbCreateur){
        this.type=type;
        this.action = "Artstock";
        this.doDomaine = 0;
        this.doType = 0;
        this.doEntete = "";
        this.deNo = deNo;
        this.arRef = arRef;
        this.qte = qte;
        this.prix = 0;
        this.remise = 0;
        this.montant = montant;
        this.cbMarq = cbMarq;
        this.table = table;
        this.cbCreateur = cbCreateur;
        this.userName = cbCreateur;
    }

    @Override
    public String toString() {
        return "LogFile{" +
                "action='" + action + '\'' +
                ", type='" + type + '\'' +
                ", doEntete='" + doEntete + '\'' +
                ", arRef='" + arRef + '\'' +
                ", date='" + date + '\'' +
                ", userName='" + userName + '\'' +
                ", cbMarq='" + cbMarq + '\'' +
                ", table='" + table + '\'' +
                ", cbCreateur='" + cbCreateur + '\'' +
                ", doType=" + doType +
                ", deNo=" + deNo +
                ", doDomaine=" + doDomaine +
                ", prix=" + prix +
                ", remise=" + remise +
                ", montant=" + montant +
                ", qte=" + qte +
                '}';
    }
}
