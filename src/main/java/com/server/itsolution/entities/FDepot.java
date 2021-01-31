package com.server.itsolution.entities;

public class FDepot {

    private Integer cbMarq, DE_No;

    private String DE_Intitule, DE_Adresse,DE_Complement,DE_CodePostal,DE_Ville,DE_Contact,DE_Principal
    ,DE_CatCompta,DE_Region,DE_Pays,DE_EMail,DE_Code,DE_Telephone,DE_Telecopie,DE_Replication
    ,DP_NoDefaut,cbModification,CA_CatTarif;

    public FDepot() {
    }

    public FDepot(Integer DE_No, String DE_Intitule, int cbMarq) {
        this.DE_No = DE_No;
        this.DE_Intitule = DE_Intitule;
        this.cbMarq = cbMarq;
    }

    public Integer getDeNo() {
        return DE_No;
    }

    public void setDeNo(Integer DE_No) {
        this.DE_No = DE_No;
    }

    public String getDeIntitule() {
        return DE_Intitule;
    }

    public void setDeIntitule(String DE_Intitule) {
        this.DE_Intitule = DE_Intitule;
    }

    public Integer getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(Integer cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Integer getDE_No() {
        return DE_No;
    }

    public void setDE_No(Integer DE_No) {
        this.DE_No = DE_No;
    }

    public String getDE_Intitule() {
        return DE_Intitule;
    }

    public void setDE_Intitule(String DE_Intitule) {
        this.DE_Intitule = DE_Intitule;
    }

    public String getDE_Adresse() {
        return DE_Adresse;
    }

    public void setDE_Adresse(String DE_Adresse) {
        this.DE_Adresse = DE_Adresse;
    }

    public String getDE_Complement() {
        return DE_Complement;
    }

    public void setDE_Complement(String DE_Complement) {
        this.DE_Complement = DE_Complement;
    }

    public String getDE_CodePostal() {
        return DE_CodePostal;
    }

    public void setDE_CodePostal(String DE_CodePostal) {
        this.DE_CodePostal = DE_CodePostal;
    }

    public String getDE_Ville() {
        return DE_Ville;
    }

    public void setDE_Ville(String DE_Ville) {
        this.DE_Ville = DE_Ville;
    }

    public String getDE_Contact() {
        return DE_Contact;
    }

    public void setDE_Contact(String DE_Contact) {
        this.DE_Contact = DE_Contact;
    }

    public String getDE_Principal() {
        return DE_Principal;
    }

    public void setDE_Principal(String DE_Principal) {
        this.DE_Principal = DE_Principal;
    }

    public String getDE_CatCompta() {
        return DE_CatCompta;
    }

    public void setDE_CatCompta(String DE_CatCompta) {
        this.DE_CatCompta = DE_CatCompta;
    }

    public String getDE_Region() {
        return DE_Region;
    }

    public void setDE_Region(String DE_Region) {
        this.DE_Region = DE_Region;
    }

    public String getDE_Pays() {
        return DE_Pays;
    }

    public void setDE_Pays(String DE_Pays) {
        this.DE_Pays = DE_Pays;
    }

    public String getDE_EMail() {
        return DE_EMail;
    }

    public void setDE_EMail(String DE_EMail) {
        this.DE_EMail = DE_EMail;
    }

    public String getDE_Code() {
        return DE_Code;
    }

    public void setDE_Code(String DE_Code) {
        this.DE_Code = DE_Code;
    }

    public String getDE_Telephone() {
        return DE_Telephone;
    }

    public void setDE_Telephone(String DE_Telephone) {
        this.DE_Telephone = DE_Telephone;
    }

    public String getDE_Telecopie() {
        return DE_Telecopie;
    }

    public void setDE_Telecopie(String DE_Telecopie) {
        this.DE_Telecopie = DE_Telecopie;
    }

    public String getDE_Replication() {
        return DE_Replication;
    }

    public void setDE_Replication(String DE_Replication) {
        this.DE_Replication = DE_Replication;
    }

    public String getDP_NoDefaut() {
        return DP_NoDefaut;
    }

    public void setDP_NoDefaut(String DP_NoDefaut) {
        this.DP_NoDefaut = DP_NoDefaut;
    }

    public String getCbModification() {
        return cbModification;
    }

    public void setCbModification(String cbModification) {
        this.cbModification = cbModification;
    }

    public String getCA_CatTarif() {
        return CA_CatTarif;
    }

    public void setCA_CatTarif(String CA_CatTarif) {
        this.CA_CatTarif = CA_CatTarif;
    }
}
