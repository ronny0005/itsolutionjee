package com.server.itsolution.entities;

public class FComptet {

    private Integer cbMarq,CT_Type,MR_No,CO_No,N_CatCompta,N_CatTarif,CT_Taux01,CT_Taux02,CT_Taux03,CT_Taux04
                    ,CT_BLFact,N_Condition,N_Devise,N_Expedition,CT_Facture,CT_Langue,N_Period;

    private String CT_Num,CT_Intitule,CG_NumPrinc,CT_Qualite,CT_Classement
            ,CT_Contact,CT_Adresse,CT_Complement,CT_CodePostal,CT_Ville,CT_CodeRegion
            ,CT_Pays,CT_Raccourci,BT_Num,CT_Ape,CT_Identifiant,CT_Siret,CT_Statistique01
            ,CT_Statistique02,CT_Statistique03,CT_Statistique04,CT_Statistique05,CT_Statistique06,CT_Statistique07
            ,CT_Statistique08,CT_Statistique09,CT_Statistique10,CT_Commentaire,CT_Encours,CT_Assurance,CT_NumPayeur
            ,N_Risque
            ,CT_Edi1,CT_Edi2,CT_Edi3,CT_DateCreate
            ,CT_Saut,CT_Lettrage,CT_ValidEch,CT_Sommeil,DE_No,CT_ControlEnc,CT_NotRappel,N_Analytique,CA_Num
            ,CT_Telephone,CT_Telecopie,CT_EMail,CT_Site,CT_Coface,CT_Surveillance,CT_SvDateCreate,CT_SvFormeJuri,CT_SvEffectif
            ,CT_SvCA,CT_SvResultat,CT_SvIncident,CT_SvDateIncid,CT_SvPrivil,CT_SvRegul,CT_SvCotation,CT_SvDateMaj,CT_SvObjetMaj,CT_SvDateBilan
            ,CT_SvNbMoisBilan,N_AnalytiqueIFRS,CA_NumIFRS,CT_PrioriteLivr,CT_LivrPartielle
            ,CT_NotPenal,EB_No,CT_NumCentrale,CT_DateFermeDebut,CT_DateFermeFin,CT_FactureElec,CT_TypeNIF,CT_RepresentInt
            ,CT_RepresentNIF,cbCreateur,cbModification;

    public FComptet() {
    }

    @Override
    public String toString() {
        return "FComptet{" +
                "cbMarq=" + cbMarq +
                ", CT_Num='" + CT_Num + '\'' +
                ", CT_Intitule='" + CT_Intitule + '\'' +
                ", CT_Type='" + CT_Type + '\'' +
                ", CG_NumPrinc='" + CG_NumPrinc + '\'' +
                ", CT_Qualite='" + CT_Qualite + '\'' +
                ", CT_Classement='" + CT_Classement + '\'' +
                ", CT_Contact='" + CT_Contact + '\'' +
                ", CT_Adresse='" + CT_Adresse + '\'' +
                ", CT_Complement='" + CT_Complement + '\'' +
                ", CT_CodePostal='" + CT_CodePostal + '\'' +
                ", CT_Ville='" + CT_Ville + '\'' +
                ", CT_CodeRegion='" + CT_CodeRegion + '\'' +
                ", CT_Pays='" + CT_Pays + '\'' +
                ", CT_Raccourci='" + CT_Raccourci + '\'' +
                ", BT_Num='" + BT_Num + '\'' +
                ", N_Devise='" + N_Devise + '\'' +
                ", CT_Ape='" + CT_Ape + '\'' +
                ", CT_Identifiant='" + CT_Identifiant + '\'' +
                ", CT_Siret='" + CT_Siret + '\'' +
                ", CT_Statistique01='" + CT_Statistique01 + '\'' +
                ", CT_Statistique02='" + CT_Statistique02 + '\'' +
                ", CT_Statistique03='" + CT_Statistique03 + '\'' +
                ", CT_Statistique04='" + CT_Statistique04 + '\'' +
                ", CT_Statistique05='" + CT_Statistique05 + '\'' +
                ", CT_Statistique06='" + CT_Statistique06 + '\'' +
                ", CT_Statistique07='" + CT_Statistique07 + '\'' +
                ", CT_Statistique08='" + CT_Statistique08 + '\'' +
                ", CT_Statistique09='" + CT_Statistique09 + '\'' +
                ", CT_Statistique10='" + CT_Statistique10 + '\'' +
                ", CT_Commentaire='" + CT_Commentaire + '\'' +
                ", CT_Encours='" + CT_Encours + '\'' +
                ", CT_Assurance='" + CT_Assurance + '\'' +
                ", CT_NumPayeur='" + CT_NumPayeur + '\'' +
                ", N_Risque='" + N_Risque + '\'' +
                ", CO_No='" + CO_No + '\'' +
                ", N_CatTarif='" + N_CatTarif + '\'' +
                ", CT_Taux01='" + CT_Taux01 + '\'' +
                ", CT_Taux02='" + CT_Taux02 + '\'' +
                ", CT_Taux03='" + CT_Taux03 + '\'' +
                ", CT_Taux04='" + CT_Taux04 + '\'' +
                ", N_CatCompta='" + N_CatCompta + '\'' +
                ", N_Period='" + N_Period + '\'' +
                ", CT_Facture='" + CT_Facture + '\'' +
                ", CT_BLFact='" + CT_BLFact + '\'' +
                ", CT_Langue='" + CT_Langue + '\'' +
                ", CT_Edi1='" + CT_Edi1 + '\'' +
                ", CT_Edi2='" + CT_Edi2 + '\'' +
                ", CT_Edi3='" + CT_Edi3 + '\'' +
                ", N_Expedition='" + N_Expedition + '\'' +
                ", N_Condition='" + N_Condition + '\'' +
                ", CT_DateCreate='" + CT_DateCreate + '\'' +
                ", CT_Saut='" + CT_Saut + '\'' +
                ", CT_Lettrage='" + CT_Lettrage + '\'' +
                ", CT_ValidEch='" + CT_ValidEch + '\'' +
                ", CT_Sommeil='" + CT_Sommeil + '\'' +
                ", DE_No='" + DE_No + '\'' +
                ", CT_ControlEnc='" + CT_ControlEnc + '\'' +
                ", CT_NotRappel='" + CT_NotRappel + '\'' +
                ", N_Analytique='" + N_Analytique + '\'' +
                ", CA_Num='" + CA_Num + '\'' +
                ", CT_Telephone='" + CT_Telephone + '\'' +
                ", CT_Telecopie='" + CT_Telecopie + '\'' +
                ", CT_EMail='" + CT_EMail + '\'' +
                ", CT_Site='" + CT_Site + '\'' +
                ", CT_Coface='" + CT_Coface + '\'' +
                ", CT_Surveillance='" + CT_Surveillance + '\'' +
                ", CT_SvDateCreate='" + CT_SvDateCreate + '\'' +
                ", CT_SvFormeJuri='" + CT_SvFormeJuri + '\'' +
                ", CT_SvEffectif='" + CT_SvEffectif + '\'' +
                ", CT_SvCA='" + CT_SvCA + '\'' +
                ", CT_SvResultat='" + CT_SvResultat + '\'' +
                ", CT_SvIncident='" + CT_SvIncident + '\'' +
                ", CT_SvDateIncid='" + CT_SvDateIncid + '\'' +
                ", CT_SvPrivil='" + CT_SvPrivil + '\'' +
                ", CT_SvRegul='" + CT_SvRegul + '\'' +
                ", CT_SvCotation='" + CT_SvCotation + '\'' +
                ", CT_SvDateMaj='" + CT_SvDateMaj + '\'' +
                ", CT_SvObjetMaj='" + CT_SvObjetMaj + '\'' +
                ", CT_SvDateBilan='" + CT_SvDateBilan + '\'' +
                ", CT_SvNbMoisBilan='" + CT_SvNbMoisBilan + '\'' +
                ", N_AnalytiqueIFRS='" + N_AnalytiqueIFRS + '\'' +
                ", CA_NumIFRS='" + CA_NumIFRS + '\'' +
                ", CT_PrioriteLivr='" + CT_PrioriteLivr + '\'' +
                ", CT_LivrPartielle='" + CT_LivrPartielle + '\'' +
                ", MR_No='" + MR_No + '\'' +
                ", CT_NotPenal='" + CT_NotPenal + '\'' +
                ", EB_No='" + EB_No + '\'' +
                ", CT_NumCentrale='" + CT_NumCentrale + '\'' +
                ", CT_DateFermeDebut='" + CT_DateFermeDebut + '\'' +
                ", CT_DateFermeFin='" + CT_DateFermeFin + '\'' +
                ", CT_FactureElec='" + CT_FactureElec + '\'' +
                ", CT_TypeNIF='" + CT_TypeNIF + '\'' +
                ", CT_RepresentInt='" + CT_RepresentInt + '\'' +
                ", CT_RepresentNIF='" + CT_RepresentNIF + '\'' +
                ", cbCreateur='" + cbCreateur + '\'' +
                ", cbModification='" + cbModification + '\'' +
                '}';
    }

    public Integer getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(Integer cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Integer getCT_Type() {
        return CT_Type;
    }

    public void setCT_Type(Integer CT_Type) {
        this.CT_Type = CT_Type;
    }

    public String getCT_Num() {
        return CT_Num;
    }

    public void setCT_Num(String CT_Num) {
        this.CT_Num = CT_Num;
    }

    public String getCT_Intitule() {
        return CT_Intitule;
    }

    public void setCT_Intitule(String CT_Intitule) {
        this.CT_Intitule = CT_Intitule;
    }

    public String getCG_NumPrinc() {
        return CG_NumPrinc;
    }

    public void setCG_NumPrinc(String CG_NumPrinc) {
        this.CG_NumPrinc = CG_NumPrinc;
    }

    public String getCT_Qualite() {
        return CT_Qualite;
    }

    public void setCT_Qualite(String CT_Qualite) {
        this.CT_Qualite = CT_Qualite;
    }

    public String getCT_Classement() {
        return CT_Classement;
    }

    public void setCT_Classement(String CT_Classement) {
        this.CT_Classement = CT_Classement;
    }

    public String getCT_Contact() {
        return CT_Contact;
    }

    public void setCT_Contact(String CT_Contact) {
        this.CT_Contact = CT_Contact;
    }

    public String getCT_Adresse() {
        return CT_Adresse;
    }

    public void setCT_Adresse(String CT_Adresse) {
        this.CT_Adresse = CT_Adresse;
    }

    public String getCT_Complement() {
        return CT_Complement;
    }

    public void setCT_Complement(String CT_Complement) {
        this.CT_Complement = CT_Complement;
    }

    public String getCT_CodePostal() {
        return CT_CodePostal;
    }

    public void setCT_CodePostal(String CT_CodePostal) {
        this.CT_CodePostal = CT_CodePostal;
    }

    public String getCT_Ville() {
        return CT_Ville;
    }

    public void setCT_Ville(String CT_Ville) {
        this.CT_Ville = CT_Ville;
    }

    public String getCT_CodeRegion() {
        return CT_CodeRegion;
    }

    public void setCT_CodeRegion(String CT_CodeRegion) {
        this.CT_CodeRegion = CT_CodeRegion;
    }

    public String getCT_Pays() {
        return CT_Pays;
    }

    public void setCT_Pays(String CT_Pays) {
        this.CT_Pays = CT_Pays;
    }

    public String getCT_Raccourci() {
        return CT_Raccourci;
    }

    public void setCT_Raccourci(String CT_Raccourci) {
        this.CT_Raccourci = CT_Raccourci;
    }

    public String getBT_Num() {
        return BT_Num;
    }

    public void setBT_Num(String BT_Num) {
        this.BT_Num = BT_Num;
    }

    public int getN_Devise() {
        return N_Devise;
    }

    public void setN_Devise(int n_Devise) {
        N_Devise = n_Devise;
    }

    public String getCT_Ape() {
        return CT_Ape;
    }

    public void setCT_Ape(String CT_Ape) {
        this.CT_Ape = CT_Ape;
    }

    public String getCT_Identifiant() {
        return CT_Identifiant;
    }

    public void setCT_Identifiant(String CT_Identifiant) {
        this.CT_Identifiant = CT_Identifiant;
    }

    public String getCT_Siret() {
        return CT_Siret;
    }

    public void setCT_Siret(String CT_Siret) {
        this.CT_Siret = CT_Siret;
    }

    public String getCT_Statistique01() {
        return CT_Statistique01;
    }

    public void setCT_Statistique01(String CT_Statistique01) {
        this.CT_Statistique01 = CT_Statistique01;
    }

    public String getCT_Statistique02() {
        return CT_Statistique02;
    }

    public void setCT_Statistique02(String CT_Statistique02) {
        this.CT_Statistique02 = CT_Statistique02;
    }

    public String getCT_Statistique03() {
        return CT_Statistique03;
    }

    public void setCT_Statistique03(String CT_Statistique03) {
        this.CT_Statistique03 = CT_Statistique03;
    }

    public String getCT_Statistique04() {
        return CT_Statistique04;
    }

    public void setCT_Statistique04(String CT_Statistique04) {
        this.CT_Statistique04 = CT_Statistique04;
    }

    public String getCT_Statistique05() {
        return CT_Statistique05;
    }

    public void setCT_Statistique05(String CT_Statistique05) {
        this.CT_Statistique05 = CT_Statistique05;
    }

    public String getCT_Statistique06() {
        return CT_Statistique06;
    }

    public void setCT_Statistique06(String CT_Statistique06) {
        this.CT_Statistique06 = CT_Statistique06;
    }

    public String getCT_Statistique07() {
        return CT_Statistique07;
    }

    public void setCT_Statistique07(String CT_Statistique07) {
        this.CT_Statistique07 = CT_Statistique07;
    }

    public String getCT_Statistique08() {
        return CT_Statistique08;
    }

    public void setCT_Statistique08(String CT_Statistique08) {
        this.CT_Statistique08 = CT_Statistique08;
    }

    public String getCT_Statistique09() {
        return CT_Statistique09;
    }

    public void setCT_Statistique09(String CT_Statistique09) {
        this.CT_Statistique09 = CT_Statistique09;
    }

    public String getCT_Statistique10() {
        return CT_Statistique10;
    }

    public void setCT_Statistique10(String CT_Statistique10) {
        this.CT_Statistique10 = CT_Statistique10;
    }

    public String getCT_Commentaire() {
        return CT_Commentaire;
    }

    public void setCT_Commentaire(String CT_Commentaire) {
        this.CT_Commentaire = CT_Commentaire;
    }

    public String getCT_Encours() {
        return CT_Encours;
    }

    public void setCT_Encours(String CT_Encours) {
        this.CT_Encours = CT_Encours;
    }

    public String getCT_Assurance() {
        return CT_Assurance;
    }

    public void setCT_Assurance(String CT_Assurance) {
        this.CT_Assurance = CT_Assurance;
    }

    public String getCT_NumPayeur() {
        return CT_NumPayeur;
    }

    public void setCT_NumPayeur(String CT_NumPayeur) {
        this.CT_NumPayeur = CT_NumPayeur;
    }

    public String getN_Risque() {
        return N_Risque;
    }

    public void setN_Risque(String n_Risque) {
        N_Risque = n_Risque;
    }

    public int getCO_No() {
        return CO_No;
    }

    public void setCO_No(int CO_No) {
        this.CO_No = CO_No;
    }

    public int getN_CatTarif() {
        return N_CatTarif;
    }

    public void setN_CatTarif(int n_CatTarif) {
        N_CatTarif = n_CatTarif;
    }

    public int getCT_Taux01() {
        return CT_Taux01;
    }

    public void setCT_Taux01(int CT_Taux01) {
        this.CT_Taux01 = CT_Taux01;
    }

    public int getCT_Taux02() {
        return CT_Taux02;
    }

    public void setCT_Taux02(int CT_Taux02) {
        this.CT_Taux02 = CT_Taux02;
    }

    public int getCT_Taux03() {
        return CT_Taux03;
    }

    public void setCT_Taux03(int CT_Taux03) {
        this.CT_Taux03 = CT_Taux03;
    }

    public int getCT_Taux04() {
        return CT_Taux04;
    }

    public void setCT_Taux04(int CT_Taux04) {
        this.CT_Taux04 = CT_Taux04;
    }

    public int getN_CatCompta() {
        return N_CatCompta;
    }

    public void setN_CatCompta(int n_CatCompta) {
        N_CatCompta = n_CatCompta;
    }

    public int getN_Period() {
        return N_Period;
    }

    public void setN_Period(int n_Period) {
        N_Period = n_Period;
    }

    public int getCT_Facture() {
        return CT_Facture;
    }

    public void setCT_Facture(int CT_Facture) {
        this.CT_Facture = CT_Facture;
    }

    public int getCT_BLFact() {
        return CT_BLFact;
    }

    public void setCT_BLFact(int CT_BLFact) {
        this.CT_BLFact = CT_BLFact;
    }

    public int getCT_Langue() {
        return CT_Langue;
    }

    public void setCT_Langue(int CT_Langue) {
        this.CT_Langue = CT_Langue;
    }

    public String getCT_Edi1() {
        return CT_Edi1;
    }

    public void setCT_Edi1(String CT_Edi1) {
        this.CT_Edi1 = CT_Edi1;
    }

    public String getCT_Edi2() {
        return CT_Edi2;
    }

    public void setCT_Edi2(String CT_Edi2) {
        this.CT_Edi2 = CT_Edi2;
    }

    public String getCT_Edi3() {
        return CT_Edi3;
    }

    public void setCT_Edi3(String CT_Edi3) {
        this.CT_Edi3 = CT_Edi3;
    }

    public int getN_Expedition() {
        return N_Expedition;
    }

    public void setN_Expedition(int n_Expedition) {
        N_Expedition = n_Expedition;
    }

    public int getN_Condition() {
        return N_Condition;
    }

    public void setN_Condition(int n_Condition) {
        N_Condition = n_Condition;
    }

    public String getCT_DateCreate() {
        return CT_DateCreate;
    }

    public void setCT_DateCreate(String CT_DateCreate) {
        this.CT_DateCreate = CT_DateCreate;
    }

    public String getCT_Saut() {
        return CT_Saut;
    }

    public void setCT_Saut(String CT_Saut) {
        this.CT_Saut = CT_Saut;
    }

    public String getCT_Lettrage() {
        return CT_Lettrage;
    }

    public void setCT_Lettrage(String CT_Lettrage) {
        this.CT_Lettrage = CT_Lettrage;
    }

    public String getCT_ValidEch() {
        return CT_ValidEch;
    }

    public void setCT_ValidEch(String CT_ValidEch) {
        this.CT_ValidEch = CT_ValidEch;
    }

    public String getCT_Sommeil() {
        return CT_Sommeil;
    }

    public void setCT_Sommeil(String CT_Sommeil) {
        this.CT_Sommeil = CT_Sommeil;
    }

    public String getDE_No() {
        return DE_No;
    }

    public void setDE_No(String DE_No) {
        this.DE_No = DE_No;
    }

    public String getCT_ControlEnc() {
        return CT_ControlEnc;
    }

    public void setCT_ControlEnc(String CT_ControlEnc) {
        this.CT_ControlEnc = CT_ControlEnc;
    }

    public String getCT_NotRappel() {
        return CT_NotRappel;
    }

    public void setCT_NotRappel(String CT_NotRappel) {
        this.CT_NotRappel = CT_NotRappel;
    }

    public String getN_Analytique() {
        return N_Analytique;
    }

    public void setN_Analytique(String n_Analytique) {
        N_Analytique = n_Analytique;
    }

    public String getCA_Num() {
        return CA_Num;
    }

    public void setCA_Num(String CA_Num) {
        this.CA_Num = CA_Num;
    }

    public String getCT_Telephone() {
        return CT_Telephone;
    }

    public void setCT_Telephone(String CT_Telephone) {
        this.CT_Telephone = CT_Telephone;
    }

    public String getCT_Telecopie() {
        return CT_Telecopie;
    }

    public void setCT_Telecopie(String CT_Telecopie) {
        this.CT_Telecopie = CT_Telecopie;
    }

    public String getCT_EMail() {
        return CT_EMail;
    }

    public void setCT_EMail(String CT_EMail) {
        this.CT_EMail = CT_EMail;
    }

    public String getCT_Site() {
        return CT_Site;
    }

    public void setCT_Site(String CT_Site) {
        this.CT_Site = CT_Site;
    }

    public String getCT_Coface() {
        return CT_Coface;
    }

    public void setCT_Coface(String CT_Coface) {
        this.CT_Coface = CT_Coface;
    }

    public String getCT_Surveillance() {
        return CT_Surveillance;
    }

    public void setCT_Surveillance(String CT_Surveillance) {
        this.CT_Surveillance = CT_Surveillance;
    }

    public String getCT_SvDateCreate() {
        return CT_SvDateCreate;
    }

    public void setCT_SvDateCreate(String CT_SvDateCreate) {
        this.CT_SvDateCreate = CT_SvDateCreate;
    }

    public String getCT_SvFormeJuri() {
        return CT_SvFormeJuri;
    }

    public void setCT_SvFormeJuri(String CT_SvFormeJuri) {
        this.CT_SvFormeJuri = CT_SvFormeJuri;
    }

    public String getCT_SvEffectif() {
        return CT_SvEffectif;
    }

    public void setCT_SvEffectif(String CT_SvEffectif) {
        this.CT_SvEffectif = CT_SvEffectif;
    }

    public String getCT_SvCA() {
        return CT_SvCA;
    }

    public void setCT_SvCA(String CT_SvCA) {
        this.CT_SvCA = CT_SvCA;
    }

    public String getCT_SvResultat() {
        return CT_SvResultat;
    }

    public void setCT_SvResultat(String CT_SvResultat) {
        this.CT_SvResultat = CT_SvResultat;
    }

    public String getCT_SvIncident() {
        return CT_SvIncident;
    }

    public void setCT_SvIncident(String CT_SvIncident) {
        this.CT_SvIncident = CT_SvIncident;
    }

    public String getCT_SvDateIncid() {
        return CT_SvDateIncid;
    }

    public void setCT_SvDateIncid(String CT_SvDateIncid) {
        this.CT_SvDateIncid = CT_SvDateIncid;
    }

    public String getCT_SvPrivil() {
        return CT_SvPrivil;
    }

    public void setCT_SvPrivil(String CT_SvPrivil) {
        this.CT_SvPrivil = CT_SvPrivil;
    }

    public String getCT_SvRegul() {
        return CT_SvRegul;
    }

    public void setCT_SvRegul(String CT_SvRegul) {
        this.CT_SvRegul = CT_SvRegul;
    }

    public String getCT_SvCotation() {
        return CT_SvCotation;
    }

    public void setCT_SvCotation(String CT_SvCotation) {
        this.CT_SvCotation = CT_SvCotation;
    }

    public String getCT_SvDateMaj() {
        return CT_SvDateMaj;
    }

    public void setCT_SvDateMaj(String CT_SvDateMaj) {
        this.CT_SvDateMaj = CT_SvDateMaj;
    }

    public String getCT_SvObjetMaj() {
        return CT_SvObjetMaj;
    }

    public void setCT_SvObjetMaj(String CT_SvObjetMaj) {
        this.CT_SvObjetMaj = CT_SvObjetMaj;
    }

    public String getCT_SvDateBilan() {
        return CT_SvDateBilan;
    }

    public void setCT_SvDateBilan(String CT_SvDateBilan) {
        this.CT_SvDateBilan = CT_SvDateBilan;
    }

    public String getCT_SvNbMoisBilan() {
        return CT_SvNbMoisBilan;
    }

    public void setCT_SvNbMoisBilan(String CT_SvNbMoisBilan) {
        this.CT_SvNbMoisBilan = CT_SvNbMoisBilan;
    }

    public String getN_AnalytiqueIFRS() {
        return N_AnalytiqueIFRS;
    }

    public void setN_AnalytiqueIFRS(String n_AnalytiqueIFRS) {
        N_AnalytiqueIFRS = n_AnalytiqueIFRS;
    }

    public String getCA_NumIFRS() {
        return CA_NumIFRS;
    }

    public void setCA_NumIFRS(String CA_NumIFRS) {
        this.CA_NumIFRS = CA_NumIFRS;
    }

    public String getCT_PrioriteLivr() {
        return CT_PrioriteLivr;
    }

    public void setCT_PrioriteLivr(String CT_PrioriteLivr) {
        this.CT_PrioriteLivr = CT_PrioriteLivr;
    }

    public String getCT_LivrPartielle() {
        return CT_LivrPartielle;
    }

    public void setCT_LivrPartielle(String CT_LivrPartielle) {
        this.CT_LivrPartielle = CT_LivrPartielle;
    }

    public int getMR_No() {
        return MR_No;
    }

    public void setMR_No(int MR_No) {
        this.MR_No = MR_No;
    }

    public String getCT_NotPenal() {
        return CT_NotPenal;
    }

    public void setCT_NotPenal(String CT_NotPenal) {
        this.CT_NotPenal = CT_NotPenal;
    }

    public String getEB_No() {
        return EB_No;
    }

    public void setEB_No(String EB_No) {
        this.EB_No = EB_No;
    }

    public String getCT_NumCentrale() {
        return CT_NumCentrale;
    }

    public void setCT_NumCentrale(String CT_NumCentrale) {
        this.CT_NumCentrale = CT_NumCentrale;
    }

    public String getCT_DateFermeDebut() {
        return CT_DateFermeDebut;
    }

    public void setCT_DateFermeDebut(String CT_DateFermeDebut) {
        this.CT_DateFermeDebut = CT_DateFermeDebut;
    }

    public String getCT_DateFermeFin() {
        return CT_DateFermeFin;
    }

    public void setCT_DateFermeFin(String CT_DateFermeFin) {
        this.CT_DateFermeFin = CT_DateFermeFin;
    }

    public String getCT_FactureElec() {
        return CT_FactureElec;
    }

    public void setCT_FactureElec(String CT_FactureElec) {
        this.CT_FactureElec = CT_FactureElec;
    }

    public String getCT_TypeNIF() {
        return CT_TypeNIF;
    }

    public void setCT_TypeNIF(String CT_TypeNIF) {
        this.CT_TypeNIF = CT_TypeNIF;
    }

    public String getCT_RepresentInt() {
        return CT_RepresentInt;
    }

    public void setCT_RepresentInt(String CT_RepresentInt) {
        this.CT_RepresentInt = CT_RepresentInt;
    }

    public String getCT_RepresentNIF() {
        return CT_RepresentNIF;
    }

    public void setCT_RepresentNIF(String CT_RepresentNIF) {
        this.CT_RepresentNIF = CT_RepresentNIF;
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
