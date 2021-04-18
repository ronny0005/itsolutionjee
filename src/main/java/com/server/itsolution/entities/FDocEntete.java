package com.server.itsolution.entities;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FDocEntete {

    private BigDecimal cbMarq;
    private Integer DO_Domaine,DO_Type,doccurent_type,DO_Souche,AB_No,CA_No,DO_Modif
            ,CO_NoCaissier,DE_No,DO_BLFact,DO_NbFacture,DO_TxEscompte,DO_Reliquat
            ,DO_Cloture,DO_Attente,DO_Provenance,DO_Tarif,DO_Colisage,DO_TypeColis,DO_Transaction
            ,DO_Condition,DO_Ecart,DO_Regime,DO_Expedit,DO_Devise,DO_Imprim,DO_Langue,DO_Period,DO_Statut
            ,CO_No,LI_No,DO_Transfere,DO_Ventile,N_CatCompta,MR_No,DO_TypeFrais,DO_ValFrais,DO_TypeLigneFrais
            ,DO_TypeFranco,DO_ValFranco,DO_TypeLigneFranco,DO_Taxe1,DO_TypeTaux1,DO_TypeTaxe1,DO_Taxe2,DO_TypeTaux2
            ,DO_TypeTaxe2,DO_Taxe3,DO_TypeTaux3,DO_TypeTaxe3,DO_MajCpta,DO_FactureElec,DO_TypeTransac,cbProt
            ,cbReplication,cbFlag,ctType;
    private double   DO_Cours;

    private String DO_Piece,DO_Date,DO_Ref,DO_Tiers
            ,CT_NumPayeur
            ,CA_Num,DO_Coord01,DO_Coord02,DO_Coord03
            ,DO_Coord04,DO_DateLivr,DO_Motif
            ,DO_DebutAbo,DO_FinAbo
            ,DO_DebutPeriod,DO_FinPeriod,CG_Num
            ,DO_Heure,DO_NoWeb
            ,CA_NumIFRS,CT_NumCentrale,DO_Contact
            ,cbModification,VEHICULE,CHAUFFEUR
            ,statut,typeFacture, cbCreateur,
            DO_DateSage,
            CT_Intitule,DE_Intitule,DE_Intitule_dest;

    private double longitude,latitude,ttc,avance,resteAPayer;

    public FDocEntete() {
        initValue();
    }

    public Integer getCO_No() {
        return CO_No;
    }

    public void setCO_No(Integer CO_No) {
        this.CO_No = CO_No;
    }

    public Integer getDO_Domaine() {
        return DO_Domaine;
    }

    public void setDO_Domaine(Integer DO_Domaine) {
        this.DO_Domaine = DO_Domaine;
    }

    public Integer getDO_Souche() {
        return DO_Souche;
    }

    public void setDO_Souche(Integer DO_Souche) {
        this.DO_Souche = DO_Souche;
    }

    public Integer getCA_No() {
        return CA_No;
    }

    public void setCA_No(Integer CA_No) {
        this.CA_No = CA_No;
    }

    public Integer getCO_NoCaissier() {
        return CO_NoCaissier;
    }

    public void setCO_NoCaissier(Integer CO_NoCaissier) {
        this.CO_NoCaissier = CO_NoCaissier;
    }

    public Integer getDE_No() {
        return DE_No;
    }

    public void setDE_No(Integer DE_No) {
        this.DE_No = DE_No;
    }

    public Integer getDO_Provenance() {
        return DO_Provenance;
    }

    public void setDO_Provenance(Integer DO_Provenance) {
        this.DO_Provenance = DO_Provenance;
    }

    public Integer getDO_Imprim() {
        return DO_Imprim;
    }

    public void setDO_Imprim(Integer DO_Imprim) {
        this.DO_Imprim = DO_Imprim;
    }

    public Integer getDO_Langue() {
        return DO_Langue;
    }

    public void setDO_Langue(Integer DO_Langue) {
        this.DO_Langue = DO_Langue;
    }

    public Integer getDO_Period() {
        return DO_Period;
    }

    public Integer getCtType() {
        return ctType;
    }

    public void setCtType(Integer ctType) {
        this.ctType = ctType;
    }

    public boolean isVisu(int PROT_Administrator, int protectedDocP, int flagProtApresImpressionP,int isSecurite)
    {
        if(PROT_Administrator == 0 && this.typeFacture.equals("Transfert_confirmation")){
            if(this.DO_Imprim == 1)
                return true;
        }else
        if(this.DO_Type ==7 || this.DO_Type==17) {
            return true;
        }
        else
        if(isSecurite==0)
            return true;
        else
            if(this.avance >0)
                return true;
            else
        if (PROT_Administrator == 1) {
            if (this.statut.equals("comptant"))
                return true;
            else
                return false;
        }
        else {
            if(this.DO_Modif==1){
                return true;
            }
            else{
                if(protectedDocP==0){
                    return true;
                }else{
                    if(this.DO_Imprim==1 && flagProtApresImpressionP!=0){
                        return true;
                    }else{
                        if(this.statut=="comptant" || this.avance>0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isModif(int PROT_Administrator,int PROT_Right,int protectedDocP,int flagProtApresImpressionP,int isSecurite){

        if(isSecurite==0)
            return false;
        else
        if(this.DO_Type ==7 || this.DO_Type==17) {
            return false;
        }
        else
        if(PROT_Administrator==1 || PROT_Right==1) {
            if (this.avance == 0)
                return true;
        }else{
            if(this.DO_Modif==1) {
                return false;
            }
            else{
                if(protectedDocP==0){
                    return false;
                }else{
                    if(this.DO_Imprim==1 && flagProtApresImpressionP!=0){
                        return false;
                    }else{
                        if(this.avance!=0){
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void setDO_Period(Integer DO_Period) {
        this.DO_Period = DO_Period;
    }

    public Integer getDO_Statut() {
        return DO_Statut;
    }

    public void setDO_Statut(Integer DO_Statut) {
        this.DO_Statut = DO_Statut;
    }

    public Integer getLI_No() {
        return LI_No;
    }

    public void setLI_No(Integer LI_No) {
        this.LI_No = LI_No;
    }

    public Integer getN_CatCompta() {
        return N_CatCompta;
    }

    public void setN_CatCompta(Integer n_CatCompta) {
        N_CatCompta = n_CatCompta;
    }

    public Integer getMR_No() {
        return MR_No;
    }

    public void setMR_No(Integer MR_No) {
        this.MR_No = MR_No;
    }

    public String getDO_Date() {
        return DO_Date;
    }

    public void setDO_Date(String DO_Date) {
        this.DO_Date = DO_Date;
    }

    public String getDO_Ref() {
        return DO_Ref;
    }

    public void setDO_Ref(String DO_Ref) {
        this.DO_Ref = DO_Ref;
    }

    public String getDO_Tiers() {
        return DO_Tiers;
    }

    public void setDO_Tiers(String DO_Tiers) {
        this.DO_Tiers = DO_Tiers;
    }

    public String getCT_NumPayeur() {
        return CT_NumPayeur;
    }

    public void setCT_NumPayeur(String CT_NumPayeur) {
        this.CT_NumPayeur = CT_NumPayeur;
    }

    public String getCA_Num() {
        return CA_Num;
    }

    public void setCA_Num(String CA_Num) {
        this.CA_Num = CA_Num;
    }

    public String getDO_Coord01() {
        return DO_Coord01;
    }

    public void setDO_Coord01(String DO_Coord01) {
        this.DO_Coord01 = DO_Coord01;
    }

    public String getDO_Coord02() {
        return DO_Coord02;
    }

    public void setDO_Coord02(String DO_Coord02) {
        this.DO_Coord02 = DO_Coord02;
    }

    public String getDO_Coord03() {
        return DO_Coord03;
    }

    public void setDO_Coord03(String DO_Coord03) {
        this.DO_Coord03 = DO_Coord03;
    }

    public String getDO_Coord04() {
        return DO_Coord04;
    }

    public void setDO_Coord04(String DO_Coord04) {
        this.DO_Coord04 = DO_Coord04;
    }

    public String getDO_Motif() {
        return DO_Motif;
    }

    public void setDO_Motif(String DO_Motif) {
        this.DO_Motif = DO_Motif;
    }

    public String getCG_Num() {
        return CG_Num;
    }

    public void setCG_Num(String CG_Num) {
        this.CG_Num = CG_Num;
    }

    public String getDO_Piece() {
        return DO_Piece;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getVEHICULE() {
        return VEHICULE;
    }

    public void setVEHICULE(String VEHICULE) {
        this.VEHICULE = VEHICULE;
    }

    public String getCHAUFFEUR() {
        return CHAUFFEUR;
    }

    public void setCHAUFFEUR(String CHAUFFEUR) {
        this.CHAUFFEUR = CHAUFFEUR;
    }

    public double getTtc() {
        return ttc;
    }

    public void setTtc(double ttc) {
        this.ttc = ttc;
    }

    public double getAvance() {
        return avance;
    }

    public void setAvance(double avance) {
        this.avance = avance;
    }

    public double getResteAPayer() {
        return resteAPayer;
    }

    public void setResteAPayer(double resteAPayer) {
        this.resteAPayer = resteAPayer;
    }

    public String redirectToListe(String type){
        int module = 0 ;
        int action = 0 ;

        if(type.equals("Vente") || type.equals("VenteC") || type.equals("VenteT")){
            action = 1;module = 2;
        }

        if(type.equals("Achat") || type.equals("AchatC") || type.equals("AchatT")){
            action = 1;module = 7;
        }

        if(type.equals("AchatRetour") || type.equals("AchatRetourC") || type.equals("AchatRetourT")){
            action = 7;module = 7;
        }

        if(type.equals("BonLivraison")){
            action = 5;module = 2;
        }

        if(type.equals("VenteRetour")){
            action = 9;module = 2;
        }

        if(type.equals("VenteAvoir")){
            action = 7;module = 2;
        }

        if(type.equals("AchatPreparationCommande")){
            action = 5;module = 7;
        }

        if(type.equals("PreparationCommande")){
            action = 3;module = 7;
        }

        if(type.equals("Devis")){
            action = 2;module = 2;
        }

        if(type.equals("Entree")){
            action = 3;module = 4;
        }

        if(type.equals("Sortie")){
            action = 4;module = 4;
        }

        if(type.equals("Transfert")){
            action = 1;module = 4;
        }

        if(type.equals("TransfertDetail")){
            action = 13;module = 2;
        }

        if(type.equals("TransfertDetail")){
            action = 9;module = 4;
        }

        if(type.equals("Ticket")){
            action = 14;module = 2;
        }

        return "indexMVC.php?module="+module+"&action="+action+"&type="+type;
    }

    public String getCbCreateur() {
        return cbCreateur;
    }

    public void setCbCreateur(String cbCreateur) {
        this.cbCreateur = cbCreateur;
    }

    public int getDO_Modif() {
        return DO_Modif;
    }

    public void setDO_Modif(int DO_Modif) {
        this.DO_Modif = DO_Modif;
    }

    public String getDO_DateSage() {
        return DO_DateSage;
    }

    public void setDO_DateSage(String DO_DateSage) {
        this.DO_DateSage = DO_DateSage;
    }

    public String getCT_Intitule() {
        return CT_Intitule;
    }

    public void setCT_Intitule(String CT_Intitule) {
        this.CT_Intitule = CT_Intitule;
    }

    public String getDE_Intitule() {
        return DE_Intitule;
    }

    public void setDE_Intitule(String DE_Intitule) {
        this.DE_Intitule = DE_Intitule;
    }

    public String getDE_Intitule_dest() {
        return DE_Intitule_dest;
    }

    public void setDE_Intitule_dest(String DE_Intitule_dest) {
        this.DE_Intitule_dest = DE_Intitule_dest;
    }

    public void setDO_Piece(String DO_Piece) {
        this.DO_Piece = DO_Piece;
    }

    public Integer getDO_Type() {
        return DO_Type;
    }

    public void setDO_Type(Integer DO_Type) {
        this.DO_Type = DO_Type;
    }

    public Integer getDoccurent_type() {
        return doccurent_type;
    }

    public void setDoccurent_type(Integer doccurent_type) {
        this.doccurent_type = doccurent_type;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getTypeFacture() {
        return typeFacture;
    }

    public void setTypeFacture(String typeFacture) {
        this.typeFacture = typeFacture;
    }

    public void initValue(){
        this.DO_Domaine = 0;
        this.DO_Type = 0;
        this.doccurent_type = 0;
        this.DO_Souche = 0;
        this.ctType = 0;
    }

    public void initTypeFacture(){
        if(this.DO_Domaine == 0 && this.DO_Type == 6)
            this.typeFacture = "Vente";

        if(this.DO_Domaine == 0 && this.DO_Type == 1)
            this.typeFacture = "BonCommande";

        if(this.DO_Domaine == 0 && this.DO_Type == 6 && this.DO_Provenance == 1){
            this.typeFacture = "VenteRetour";
        }

        if(this.DO_Domaine == 0 && this.DO_Type == 16 && this.DO_Provenance == 1){
            this.typeFacture = "AchatRetour";
        }

        if(this.DO_Domaine == 0 && this.DO_Type == 17 && this.DO_Provenance == 1){
            this.typeFacture = "AchatRetourC";
        }

        if(this.DO_Domaine == 0 && this.DO_Type == 6 && this.DO_Provenance == 2){
            this.typeFacture = "Avoir";
        }

        if(this.DO_Domaine == 4 && this.DO_Type == 44){
            this.typeFacture = "Transfert_confirmation";
        }

        if(this.DO_Domaine == 0 && this.DO_Type == 3)
            this.typeFacture = "BonLivraison";

        if(this.DO_Domaine == 0 && this.DO_Type == 7)
            this.typeFacture = "VenteC";

        if(this.DO_Domaine == 1 && this.DO_Type == 7)
            this.typeFacture = "AchatC";

        if(this.DO_Domaine == 0 && this.DO_Type == 0)
            this.typeFacture = "Devis";

        if(this.DO_Domaine == 1 && this.DO_Type == 11)
            this.typeFacture = "PreparationCommande";

        if(this.DO_Domaine == 2 && this.DO_Type == 20)
            this.typeFacture = "Entree";

        if(this.DO_Domaine == 2 && this.DO_Type == 21)
            this.typeFacture = "Sortie";

        if(this.DO_Domaine == 2 && this.DO_Type == 23){
            this.typeFacture = "Transfert";
        }

        if(this.DO_Domaine == 4 && this.DO_Type == 41)
            this.typeFacture = "Transfert_detail";

        if(this.DO_Domaine == 4 && this.DO_Type == 40)
            this.typeFacture = "Transfert_detail40";

        if(this.DO_Domaine == 1 && this.DO_Type == 16)
            this.typeFacture = "Achat";

        if(this.DO_Domaine == 1 && this.DO_Type == 12)
            this.typeFacture = "AchatPreparationCommande";

        if(this.DO_Domaine == 3 && this.DO_Type == 30)
            this.typeFacture = "Ticket";
    }

    public void setTypeFac(String typefac){
        this.typeFacture = typefac;
        if(this.typeFacture.equals("Vente")){
            this.DO_Domaine = 0;
            this.DO_Type = 6;
            this.doccurent_type = 6;
            this.DO_Souche = 0;
            this.ctType = 0;
        }

        if(this.typeFacture.equals("VenteT")){
            this.DO_Domaine = 0;
            this.DO_Type = 6;
            this.doccurent_type = 6;
            this.DO_Provenance=0;
        }
        if(this.typeFacture.equals("VenteRetour" )){
            this.DO_Domaine = 0;
            this.DO_Type = 6;
            this.doccurent_type = 7;
            this.ctType = 0;
            this.DO_Provenance = 1;
        }

        if(this.typeFacture.equals("VenteRetourT" )){
            this.DO_Domaine = 0;
            this.DO_Type = 6;
            this.doccurent_type = 7;
            this.ctType = 0;
            this.DO_Provenance = 1;
        }

        if(this.typeFacture.equals("BonCommande")){
            this.DO_Domaine = 0;
            this.DO_Type = 1;
            this.DO_Provenance = 0;
            this.doccurent_type = 1;
        }

        if(this.typeFacture.equals("VenteRetourC")){
            this.DO_Domaine = 0;
            this.DO_Type = 7;
            this.DO_Provenance = 1;
            this.doccurent_type = 7;
        }

        if(this.typeFacture.equals("VenteAvoir")){
            this.DO_Domaine = 0;
            this.DO_Type = 6;
            this.doccurent_type = 8;
            this.ctType = 0;
        }

        if(this.typeFacture.equals("BonLivraison")){
            this.DO_Domaine = 0;
            this.DO_Type = 3;
            this.doccurent_type = 3;
            this.ctType = 0;
        }

        if(this.typeFacture.equals("VenteC")){
            this.DO_Domaine = 0;
            this.DO_Type = 7;
            this.doccurent_type = 6;
            this.ctType = 0;
        }

        if(this.typeFacture.equals("AchatC")){
            this.DO_Domaine = 1;
            this.DO_Type = 17;
            this.doccurent_type = 6;
            this.ctType = 1;
        }

        if(this.typeFacture.equals("AchatRetourC")){
            this.DO_Domaine = 1;
            this.DO_Type = 17;
            this.DO_Provenance = 1;
            this.doccurent_type = 7;
        }

        if(this.typeFacture.equals("AchatRetour")){
            this.DO_Domaine = 1;
            this.DO_Type = 16;
            this.DO_Provenance = 1;
            this.doccurent_type = 7;
        }


        if(this.typeFacture.equals("Devis")){
            this.DO_Domaine = 0;
            this.DO_Type = 0;
            this.doccurent_type = 0;
            this.ctType = 0;
        }

        if(this.typeFacture.equals("PreparationCommande")){
            this.DO_Domaine  = 1;
            this.DO_Type = 11;
            this.doccurent_type =1;
            this.ctType = 1;
        }

        if(this.typeFacture.equals("Entree")){
            this.DO_Domaine  = 2;
            this.DO_Type = 20;
            this.doccurent_type =0;
            this.DO_Souche =0;
        }

        if(this.typeFacture.equals("Sortie")){
            this.DO_Domaine  = 2;
            this.DO_Type = 21;
            this.doccurent_type =1;
            this.DO_Souche =0;
        }

        if(this.typeFacture.equals("Transfert")){
            this.DO_Domaine  = 2;
            this.DO_Type = 23;
            this.doccurent_type =3;
            this.DO_Souche =0;
        }

        if(this.typeFacture.equals("Transfert_confirmation") || this.typeFacture.equals("Transfert_valid_confirmation")){
            this.DO_Domaine  = 4;
            this.DO_Type = 44;
            this.doccurent_type =4;
            this.DO_Souche =0;
        }

        if(this.typeFacture.equals("Transfert_detail")){
            this.DO_Domaine  = 4;
            this.DO_Type = 41;
            this.doccurent_type =0;
            this.DO_Souche =0;
        }

        if(this.typeFacture.equals("Transfert_detail40")){
            this.DO_Domaine  = 4;
            this.DO_Type = 40;
            this.doccurent_type =0;
            this.DO_Souche =0;
            this.typeFacture="Transfert_detail";
            typefac="Transfert_detail";
        }

        if(this.typeFacture.equals("Achat")){
            this.DO_Domaine = 1;
            this.DO_Type = 16;
            this.doccurent_type =6;
            this.ctType = 1;
        }

        if(this.typeFacture.equals("AchatT")){
            this.DO_Domaine = 1;
            this.DO_Type = 16;
            this.doccurent_type =6;
            this.DO_Provenance=0;
        }

        if(this.typeFacture.equals("AchatRetour" )){
            this.DO_Domaine = 1;
            this.DO_Type = 16;
            this.doccurent_type = 7;
            this.ctType = 1;
        }

        if(this.typeFacture.equals("AchatPreparationCommande")){
            this.DO_Domaine = 1;
            this.doccurent_type =6;
            this.DO_Type = 12;
            this.ctType = 1;
            this.DO_Provenance=0;
        }

        if(this.typeFacture.equals("Ticket")) {
            this.DO_Domaine = 3;
            this.doccurent_type =6;
            this.DO_Type = 30;
            this.DO_Provenance=0;
        }
    }

    public BigDecimal getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(BigDecimal cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Integer getAB_No() {
        return AB_No;
    }

    public void setAB_No(Integer AB_No) {
        this.AB_No = AB_No;
    }

    public Integer getDO_BLFact() {
        return DO_BLFact;
    }

    public void setDO_BLFact(Integer DO_BLFact) {
        this.DO_BLFact = DO_BLFact;
    }

    public Integer getDO_NbFacture() {
        return DO_NbFacture;
    }

    public void setDO_NbFacture(Integer DO_NbFacture) {
        this.DO_NbFacture = DO_NbFacture;
    }

    public Integer getDO_TxEscompte() {
        return DO_TxEscompte;
    }

    public void setDO_TxEscompte(Integer DO_TxEscompte) {
        this.DO_TxEscompte = DO_TxEscompte;
    }

    public Integer getDO_Reliquat() {
        return DO_Reliquat;
    }

    public void setDO_Reliquat(Integer DO_Reliquat) {
        this.DO_Reliquat = DO_Reliquat;
    }

    public Integer getDO_Cloture() {
        return DO_Cloture;
    }

    public void setDO_Cloture(Integer DO_Cloture) {
        this.DO_Cloture = DO_Cloture;
    }

    public Integer getDO_Attente() {
        return DO_Attente;
    }

    public void setDO_Attente(Integer DO_Attente) {
        this.DO_Attente = DO_Attente;
    }

    public Integer getDO_Tarif() {
        return DO_Tarif;
    }

    public void setDO_Tarif(Integer DO_Tarif) {
        this.DO_Tarif = DO_Tarif;
    }

    public Integer getDO_Colisage() {
        return DO_Colisage;
    }

    public void setDO_Colisage(Integer DO_Colisage) {
        this.DO_Colisage = DO_Colisage;
    }

    public Integer getDO_TypeColis() {
        return DO_TypeColis;
    }

    public void setDO_TypeColis(Integer DO_TypeColis) {
        this.DO_TypeColis = DO_TypeColis;
    }

    public Integer getDO_Transaction() {
        return DO_Transaction;
    }

    public void setDO_Transaction(Integer DO_Transaction) {
        this.DO_Transaction = DO_Transaction;
    }

    public Integer getDO_Condition() {
        return DO_Condition;
    }

    public void setDO_Condition(Integer DO_Condition) {
        this.DO_Condition = DO_Condition;
    }

    public Integer getDO_Ecart() {
        return DO_Ecart;
    }

    public void setDO_Ecart(Integer DO_Ecart) {
        this.DO_Ecart = DO_Ecart;
    }

    public Integer getDO_Regime() {
        return DO_Regime;
    }

    public void setDO_Regime(Integer DO_Regime) {
        this.DO_Regime = DO_Regime;
    }

    public Integer getDO_Expedit() {
        return DO_Expedit;
    }

    public void setDO_Expedit(Integer DO_Expedit) {
        this.DO_Expedit = DO_Expedit;
    }

    public Integer getDO_Devise() {
        return DO_Devise;
    }

    public void setDO_Devise(Integer DO_Devise) {
        this.DO_Devise = DO_Devise;
    }

    public double getDO_Cours() {
        return DO_Cours;
    }

    public void setDO_Cours(double DO_Cours) {
        this.DO_Cours = DO_Cours;
    }

    public Integer getDO_Transfere() {
        return DO_Transfere;
    }

    public void setDO_Transfere(Integer DO_Transfere) {
        this.DO_Transfere = DO_Transfere;
    }

    public Integer getDO_Ventile() {
        return DO_Ventile;
    }

    public void setDO_Ventile(Integer DO_Ventile) {
        this.DO_Ventile = DO_Ventile;
    }

    public Integer getDO_TypeFrais() {
        return DO_TypeFrais;
    }

    public void setDO_TypeFrais(Integer DO_TypeFrais) {
        this.DO_TypeFrais = DO_TypeFrais;
    }

    public Integer getDO_ValFrais() {
        return DO_ValFrais;
    }

    public void setDO_ValFrais(Integer DO_ValFrais) {
        this.DO_ValFrais = DO_ValFrais;
    }

    public Integer getDO_TypeLigneFrais() {
        return DO_TypeLigneFrais;
    }

    public void setDO_TypeLigneFrais(Integer DO_TypeLigneFrais) {
        this.DO_TypeLigneFrais = DO_TypeLigneFrais;
    }

    public Integer getDO_TypeFranco() {
        return DO_TypeFranco;
    }

    public void setDO_TypeFranco(Integer DO_TypeFranco) {
        this.DO_TypeFranco = DO_TypeFranco;
    }

    public Integer getDO_ValFranco() {
        return DO_ValFranco;
    }

    public void setDO_ValFranco(Integer DO_ValFranco) {
        this.DO_ValFranco = DO_ValFranco;
    }

    public Integer getDO_TypeLigneFranco() {
        return DO_TypeLigneFranco;
    }

    public void setDO_TypeLigneFranco(Integer DO_TypeLigneFranco) {
        this.DO_TypeLigneFranco = DO_TypeLigneFranco;
    }

    public Integer getDO_Taxe1() {
        return DO_Taxe1;
    }

    public void setDO_Taxe1(Integer DO_Taxe1) {
        this.DO_Taxe1 = DO_Taxe1;
    }

    public Integer getDO_TypeTaux1() {
        return DO_TypeTaux1;
    }

    public void setDO_TypeTaux1(Integer DO_TypeTaux1) {
        this.DO_TypeTaux1 = DO_TypeTaux1;
    }

    public Integer getDO_TypeTaxe1() {
        return DO_TypeTaxe1;
    }

    public void setDO_TypeTaxe1(Integer DO_TypeTaxe1) {
        this.DO_TypeTaxe1 = DO_TypeTaxe1;
    }

    public Integer getDO_Taxe2() {
        return DO_Taxe2;
    }

    public void setDO_Taxe2(Integer DO_Taxe2) {
        this.DO_Taxe2 = DO_Taxe2;
    }

    public Integer getDO_TypeTaux2() {
        return DO_TypeTaux2;
    }

    public void setDO_TypeTaux2(Integer DO_TypeTaux2) {
        this.DO_TypeTaux2 = DO_TypeTaux2;
    }

    public Integer getDO_TypeTaxe2() {
        return DO_TypeTaxe2;
    }

    public void setDO_TypeTaxe2(Integer DO_TypeTaxe2) {
        this.DO_TypeTaxe2 = DO_TypeTaxe2;
    }

    public Integer getDO_Taxe3() {
        return DO_Taxe3;
    }

    public void setDO_Taxe3(Integer DO_Taxe3) {
        this.DO_Taxe3 = DO_Taxe3;
    }

    public Integer getDO_TypeTaux3() {
        return DO_TypeTaux3;
    }

    public void setDO_TypeTaux3(Integer DO_TypeTaux3) {
        this.DO_TypeTaux3 = DO_TypeTaux3;
    }

    public Integer getDO_TypeTaxe3() {
        return DO_TypeTaxe3;
    }

    public void setDO_TypeTaxe3(Integer DO_TypeTaxe3) {
        this.DO_TypeTaxe3 = DO_TypeTaxe3;
    }

    public Integer getDO_MajCpta() {
        return DO_MajCpta;
    }

    public void setDO_MajCpta(Integer DO_MajCpta) {
        this.DO_MajCpta = DO_MajCpta;
    }

    public Integer getDO_FactureElec() {
        return DO_FactureElec;
    }

    public void setDO_FactureElec(Integer DO_FactureElec) {
        this.DO_FactureElec = DO_FactureElec;
    }

    public Integer getDO_TypeTransac() {
        return DO_TypeTransac;
    }

    public void setDO_TypeTransac(Integer DO_TypeTransac) {
        this.DO_TypeTransac = DO_TypeTransac;
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

    public Integer getCbFlag() {
        return cbFlag;
    }

    public void setCbFlag(Integer cbFlag) {
        this.cbFlag = cbFlag;
    }

    public String getDO_DateLivr() {
        return DO_DateLivr;
    }

    public void setDO_DateLivr(String DO_DateLivr) {
        this.DO_DateLivr = DO_DateLivr;
    }

    public String getDO_DebutAbo() {
        return DO_DebutAbo;
    }

    public void setDO_DebutAbo(String DO_DebutAbo) {
        this.DO_DebutAbo = DO_DebutAbo;
    }

    public String getDO_FinAbo() {
        return DO_FinAbo;
    }

    public void setDO_FinAbo(String DO_FinAbo) {
        this.DO_FinAbo = DO_FinAbo;
    }

    public String getDO_DebutPeriod() {
        return DO_DebutPeriod;
    }

    public void setDO_DebutPeriod(String DO_DebutPeriod) {
        this.DO_DebutPeriod = DO_DebutPeriod;
    }

    public String getDO_FinPeriod() {
        return DO_FinPeriod;
    }

    public void setDO_FinPeriod(String DO_FinPeriod) {
        this.DO_FinPeriod = DO_FinPeriod;
    }

    public String getDO_Heure() {
        return DO_Heure;
    }

    public void setDO_Heure(String DO_Heure) {
        this.DO_Heure = DO_Heure;
    }

    public String getDO_NoWeb() {
        return DO_NoWeb;
    }

    public void setDO_NoWeb(String DO_NoWeb) {
        this.DO_NoWeb = DO_NoWeb;
    }

    public String getCA_NumIFRS() {
        return CA_NumIFRS;
    }

    public void setCA_NumIFRS(String CA_NumIFRS) {
        this.CA_NumIFRS = CA_NumIFRS;
    }

    public String getCT_NumCentrale() {
        return CT_NumCentrale;
    }

    public void setCT_NumCentrale(String CT_NumCentrale) {
        this.CT_NumCentrale = CT_NumCentrale;
    }

    public String getDO_Contact() {
        return DO_Contact;
    }

    public void setDO_Contact(String DO_Contact) {
        this.DO_Contact = DO_Contact;
    }

    public String getCbModification() {
        return cbModification;
    }

    public void setCbModification(String cbModification) {
        this.cbModification = cbModification;
    }

    public void setValueMvtEntree (){
        this.CO_No=0;
        this.DO_Period=0;
        this.DO_Devise=0;
        this.DO_Cours=Double.valueOf(0);
        this.DE_No=0;
        this.LI_No=0;
        this.CT_NumPayeur=null;
        this.DO_Expedit=0;
        this.DO_NbFacture=0;
        this.DO_BLFact=0;
        this.DO_TxEscompte=0;
        this.DO_Reliquat=0;
        this.DO_Imprim=0;
        this.CA_Num="";
        this.DO_Coord01="";
        this.DO_Coord02="";
        this.DO_Coord03="";
        this.DO_Coord04="";
        this.DO_Souche=0;
        this.DO_Condition=0;
        this.DO_Tarif=0;
        this.DO_Colisage=1;
        this.DO_TypeColis=1;
        this.DO_Transaction=0;
        this.DO_Langue=0;
        this.DO_Ecart=0;
        this.DO_Regime=0;
        this.N_CatCompta=0;
        this.DO_Ventile=0;
        this.AB_No=0;
        this.DO_DebutAbo="1900-01-01";
        this.DO_FinAbo="1900-01-01";
        this.DO_DebutPeriod="1900-01-01";
        this.DO_FinPeriod="1900-01-01";
        this.CG_Num=null;
        this.DO_Statut=0;
        this.CA_No=0;
        this.CO_NoCaissier=0;
        this.DO_Transfere=0;
        this.DO_Cloture=0;
        this.DO_NoWeb="";
        this.DO_Attente=0;
        this.DO_Provenance=0;
        this.CA_NumIFRS="";
        this.MR_No=0;
        this.DO_TypeFrais=0;
        this.DO_ValFrais=0;
        this.DO_TypeLigneFrais=0;
        this.DO_TypeFranco=0;
        this.DO_ValFranco=0;
        this.DO_TypeLigneFranco=0;
        this.DO_Taxe1=0;
        this.DO_TypeTaux1=0;
        this.DO_TypeTaxe1=0;
        this.DO_Taxe2=0;
        this.DO_TypeTaux2=0;
        this.DO_TypeTaxe2=0;
        this.DO_Taxe3=0;
        this.DO_TypeTaux3=0;
        this.DO_TypeTaxe3=0;
        this.DO_MajCpta=0;
        this.DO_Motif="";
        this.CT_NumCentrale=null;
        this.DO_Contact="";
        this.DO_FactureElec=0;
        this.DO_TypeTransac=0;
        this.cbProt=0;
        this.cbReplication=0;
        this.cbFlag=0;
    }

    public void setDefaultValueVente(FComptet client){
        if((this.DO_Domaine ==0 && this.DO_Type >=0 && this.DO_Type <=7)|| this.DO_Domaine ==3) {
            this.DO_Tiers = client.getCT_Num();
            this.AB_No = 0;
            this.CA_No = 0;
            if(client.getCA_Num()!=null)
                this.CA_Num = client.getCA_Num();
            if(client.getCG_NumPrinc()!=null)
                this.CG_Num = client.getCG_NumPrinc();
            //if(client.getCT_BLFact()!=null)
            this.DO_BLFact = client.getCT_BLFact();
            this.DO_Cloture = 0;
            this.DO_Colisage = 1;
            //if(client.getN_Condition()!=null)
            this.DO_Condition = client.getN_Condition();
            this.DO_Coord01 = "";
            this.DO_Coord02 = "";
            this.DO_Coord03 = "";
            this.DO_Coord04 = "";
            this.DO_DateLivr = "1900-01-01" ;
            this.DO_DebutAbo  = "1900-01-01";
            this.DO_DebutPeriod  = "1900-01-01";
            //if(client.getN_Devise()!=null)
            this.DO_Devise = client.getN_Devise();
            this.DO_Ecart = 0;
            //if(client.getN_Expedition()!=null)
            this.DO_Expedit = client.getN_Expedition();
            this.DO_FinAbo  = "1900-01-01";
            this.DO_FinPeriod  = "1900-01-01";
            this.DO_Imprim = 0;
            //if(client.getCT_Langue()!=null)
            this.DO_Langue = client.getCT_Langue();
            //if(client.getCT_Facture()!=null)
            this.DO_NbFacture = client.getCT_Facture();
            //if(client.getN_Period()!=null)
            this.DO_Period = client.getN_Period();
            this.DO_Ref ="";
            this.DO_Reliquat =0;
            this.DO_Souche=0;
            this.DO_Statut = 2;
            //if(client.getN_CatTarif()==null)
            this.DO_Tarif = 0;
            //else
            //    this.DO_Tarif = client.getN_CatTarif();
            this.DO_Transfere = 0 ;
            //if(client.getCT_Taux02()!=null)
            this.DO_TxEscompte = client.getCT_Taux02();
            if(client.getCT_NumPayeur()!="")
                this.CT_NumPayeur = client.getCT_NumPayeur();
            this.DO_Tiers = client.getCT_Num();
            this.DO_TypeColis = 1;
            this.DO_Ventile = 0;
            //if(client.getN_CatCompta()!=null)
            this.N_CatCompta = client.getN_CatCompta();
            //if(client.getCO_No()!=null)
            this.CO_No = client.getCO_No();
            this.CO_NoCaissier = 0;
            this.DO_Attente = 0;
            this.DO_NoWeb = "";
            this.DO_Regime = 0;
            this.DO_Transaction = 0;
        }
    }

    public void setInfoAjoutEntete(){
        if(this.typeFacture.equals("BonCommande") || this.typeFacture.equals("BonLivraison")
                || this.typeFacture.equals("Devis")
                || this.typeFacture.equals("Vente")) {
            this.DO_Transaction = 11;
            this.DO_Regime = 21;
            this.DO_Provenance = 0;
        }

        if(this.typeFacture.equals("Achat") || this.typeFacture.equals("AchatPreparationCommande")
                || this.typeFacture.equals("PreparationCommande")) {
            this.DO_Transaction = 11;
            this.DO_Regime = 11;
            this.DO_Provenance = 0;
        }

        if(this.typeFacture.equals("Entree") || this.typeFacture.equals("Sortie") || this.typeFacture.equals("Transfert") || this.typeFacture.equals("Transfert_detail")) {
            this.DO_Transaction = 0;
            this.DO_Regime = 0;
            this.DO_Provenance = 0;
            this.DO_Condition  = 0;
        }

        if(this.typeFacture.equals("VenteRetour") || this.typeFacture.equals("AchatRetour"))
            this.DO_Provenance = 1;
        if(this.typeFacture.equals("VenteAvoir") || this.typeFacture.equals("AchatAvoir"))
            this.DO_Provenance = 2;
        if(this.typeFacture.equals("Achat") || this.typeFacture.equals("PreparationCommande"))
            this.DO_Regime = 11;
        if(this.typeFacture.equals("VenteAvoir") || this.typeFacture.equals("VenteRetour")){
            this.DO_Transaction = 21;
            this.DO_Regime = 25;
        }
    }

    public void setValueMvt(){
        this.DO_Period=0;
        this.DO_Expedit=0;
        this.DO_NbFacture=0;
    }

    public void setDefaultValueAchat(FComptet client){
        if(this.DO_Domaine ==1 && this.DO_Type >=10 && this.DO_Type <=17) {
            this.DO_Tiers = client.getCT_Num();
            this.AB_No = 0;
            this.CA_No = 0;
            if(client.getCA_Num()!=null)
                this.CA_Num = client.getCA_Num();
            if(client.getCG_NumPrinc()!="")
                this.CG_Num = client.getCG_NumPrinc();
            this.DO_BLFact = 0;
            this.DO_Cloture = 0;
            this.DO_Colisage = 1;
//            if(client.getN_Condition()!="")
            this.DO_Condition = client.getN_Condition();
            this.DO_Coord01 = "";
            this.DO_Coord02 = "";
            this.DO_Coord03 = "";
            this.DO_Coord04 = "";
            this.DO_DateLivr = "1900-01-01" ;
            this.DO_DebutAbo  = "1900-01-01";
            this.DO_DebutPeriod  = "1900-01-01";
            //if(client.getN_Devise()!="")
            this.DO_Devise = client.getN_Devise();
            this.DO_Ecart = 0;
            this.DO_Expedit = 1;
            this.DO_FinAbo  = "1900-01-01";
            this.DO_FinPeriod  = "1900-01-01";
            this.DO_Imprim = 0;
            //if(client.getCT_Langue()!="")
            this.DO_Langue = client.getCT_Langue();
            //if(client.getCT_Facture()!="")
            this.DO_NbFacture = client.getCT_Facture();
            this.DO_Period = 1;
            this.DO_Ref ="";
            this.DO_Reliquat =0;
            this.DO_Souche=0;
            this.DO_Statut = 2;
            this.DO_Tarif = 1;
            this.DO_Transfere = 0 ;
            //if(client.CT_Taux02!="")
            this.DO_TxEscompte = client.getCT_Taux02();
            if(client.getCT_NumPayeur()!=null)
                this.CT_NumPayeur = client.getCT_NumPayeur();
            if(client.getCT_Num()!=null)
                this.DO_Tiers = client.getCT_Num();
            this.DO_TypeColis = 1;
            this.DO_Ventile = 0;
            //if(client.getN_CatCompta()!=null)
            this.N_CatCompta = client.getN_CatCompta();
            //if(client.getCO_No()!="")
            this.CO_No = client.getCO_No();
            this.CO_NoCaissier = 0;
            this.DO_Attente = 0;
            this.DO_NoWeb = "";
            this.DO_Regime = 0;
            this.DO_Transaction = 0;
        }
    }

    public void defaultValue(){
        this.DO_Period=1;
        this.DO_Devise=0;
        this.DO_Cours=Double.valueOf(0);
        this.DO_Expedit=1;
        this.DO_NbFacture=1;
        this.DO_BLFact=0;
        this.DO_TxEscompte=0;
        this.DO_Reliquat=0;
        this.DO_Imprim=0;
        this.DO_Coord01="";
        this.DO_Coord04="";
        this.DO_Colisage=1;
        this.DO_TypeColis=1;
        this.DO_Langue=0;
        this.DO_Ecart=0;
        this.DO_Ventile=0;
        this.AB_No=0;
        this.DO_DebutAbo="1900-01-01";
        this.DO_FinAbo="1900-01-01";
        this.DO_DebutPeriod="1900-01-01";
        this.DO_FinPeriod="1900-01-01";
        this.DO_Transfere=0;
        this.DO_Cloture=0;
        this.DO_NoWeb="";
        this.DO_Attente=0;
        this.CA_NumIFRS="";
        this.MR_No=0;
        this.DO_TypeFrais=0;
        this.DO_ValFrais=0;
        this.DO_TypeLigneFrais=0;
        this.DO_TypeFranco=0;
        this.DO_ValFranco=0;
        this.DO_TypeLigneFranco=0
        ;this.DO_Taxe1=0;
        this.DO_TypeTaux1=0;
        this.DO_TypeTaxe1=0;
        this.DO_Taxe2=0
        ;this.DO_TypeTaux2=0;
        this.DO_TypeTaxe2=0;
        this.DO_Taxe3=0;
        this.DO_TypeTaux3=0;
        this.DO_TypeTaxe3=0;
        this.DO_MajCpta=0;
        this.DO_Motif="";
        this.CT_NumCentrale=null;
        this.DO_Contact="";
        this.DO_FactureElec=0;
        this.DO_TypeTransac=0;
        this.cbProt=0;
        this.cbReplication=0;
        this.cbFlag=0;
        this.VEHICULE="";
        this.CHAUFFEUR="";
        this.DO_Provenance=0;
    }

    public void setDefaultValueStock() {
        if (this.DO_Domaine == 2) {
            this.AB_No = 0;
            this.CA_No = 0;
            this.CA_Num = "";
            this.CG_Num = null;
            this.DE_No = 0;
            this.DO_BLFact = 0;
            this.DO_Cloture = 0;
            this.DO_Colisage = 1;
            this.DO_Condition = 0;
            this.DO_Coord01 = "";
            this.DO_Coord02 = "";
            this.DO_Coord03 = "";
            this.DO_Coord04 = "";
            this.DO_DateLivr = "1900-01-01";
            this.DO_DebutAbo = "1900-01-01";
            this.DO_DebutPeriod = "1900-01-01";
            this.DO_Devise = 0;
            this.DO_Ecart = 0;
            this.DO_Expedit = 0;
            this.DO_FinAbo = "1900-01-01";
            this.DO_FinPeriod = "1900-01-01";
            this.DO_Imprim = 0;
            this.DO_Langue = 0;
            this.DO_NbFacture = 0;
            this.DO_Period = 0;
            this.DO_Ref = "";
            this.DO_Reliquat = 0;
            this.DO_Souche = 0;
            this.DO_Statut = 0;
            this.DO_Tarif = 0;
            this.DO_Transfere = 0;
            this.DO_TxEscompte = 0;
            this.CT_NumPayeur = null;
            this.DO_Tiers = "0";
            this.DO_TypeColis = 1;
            this.DO_Ventile = 0;
            this.N_CatCompta = 0;
            this.CO_No = 0;
            this.CO_NoCaissier = 0;
            this.DO_Attente = 0;
            this.DO_NoWeb = "";
            this.DO_Regime = 0;
            this.DO_Transaction = 0;
        }
    }

    @Override
    public String toString() {
        return "FDocEntete{" +
                "cbMarq=" + cbMarq +
                ", DO_Domaine=" + DO_Domaine +
                ", DO_Type=" + DO_Type +
                ", doccurent_type=" + doccurent_type +
                ", DO_Souche=" + DO_Souche +
                ", AB_No=" + AB_No +
                ", CA_No=" + CA_No +
                ", DO_Modif=" + DO_Modif +
                ", CO_NoCaissier=" + CO_NoCaissier +
                ", DE_No=" + DE_No +
                ", DO_BLFact=" + DO_BLFact +
                ", DO_NbFacture=" + DO_NbFacture +
                ", DO_TxEscompte=" + DO_TxEscompte +
                ", DO_Reliquat=" + DO_Reliquat +
                ", DO_Cloture=" + DO_Cloture +
                ", DO_Attente=" + DO_Attente +
                ", DO_Provenance=" + DO_Provenance +
                ", DO_Tarif=" + DO_Tarif +
                ", DO_Colisage=" + DO_Colisage +
                ", DO_TypeColis=" + DO_TypeColis +
                ", DO_Transaction=" + DO_Transaction +
                ", DO_Condition=" + DO_Condition +
                ", DO_Ecart=" + DO_Ecart +
                ", DO_Regime=" + DO_Regime +
                ", DO_Expedit=" + DO_Expedit +
                ", DO_Devise=" + DO_Devise +
                ", DO_Imprim=" + DO_Imprim +
                ", DO_Langue=" + DO_Langue +
                ", DO_Period=" + DO_Period +
                ", DO_Statut=" + DO_Statut +
                ", CO_No=" + CO_No +
                ", DO_Cours=" + DO_Cours +
                ", LI_No=" + LI_No +
                ", DO_Transfere=" + DO_Transfere +
                ", DO_Ventile=" + DO_Ventile +
                ", N_CatCompta=" + N_CatCompta +
                ", MR_No=" + MR_No +
                ", DO_TypeFrais=" + DO_TypeFrais +
                ", DO_ValFrais=" + DO_ValFrais +
                ", DO_TypeLigneFrais=" + DO_TypeLigneFrais +
                ", DO_TypeFranco=" + DO_TypeFranco +
                ", DO_ValFranco=" + DO_ValFranco +
                ", DO_TypeLigneFranco=" + DO_TypeLigneFranco +
                ", DO_Taxe1=" + DO_Taxe1 +
                ", DO_TypeTaux1=" + DO_TypeTaux1 +
                ", DO_TypeTaxe1=" + DO_TypeTaxe1 +
                ", DO_Taxe2=" + DO_Taxe2 +
                ", DO_TypeTaux2=" + DO_TypeTaux2 +
                ", DO_TypeTaxe2=" + DO_TypeTaxe2 +
                ", DO_Taxe3=" + DO_Taxe3 +
                ", DO_TypeTaux3=" + DO_TypeTaux3 +
                ", DO_TypeTaxe3=" + DO_TypeTaxe3 +
                ", DO_MajCpta=" + DO_MajCpta +
                ", DO_FactureElec=" + DO_FactureElec +
                ", DO_TypeTransac=" + DO_TypeTransac +
                ", cbProt=" + cbProt +
                ", cbReplication=" + cbReplication +
                ", cbFlag=" + cbFlag +
                ", DO_Piece='" + DO_Piece + '\'' +
                ", DO_Date='" + DO_Date + '\'' +
                ", DO_Ref='" + DO_Ref + '\'' +
                ", DO_Tiers='" + DO_Tiers + '\'' +
                ", CT_NumPayeur='" + CT_NumPayeur + '\'' +
                ", CA_Num='" + CA_Num + '\'' +
                ", DO_Coord01='" + DO_Coord01 + '\'' +
                ", DO_Coord02='" + DO_Coord02 + '\'' +
                ", DO_Coord03='" + DO_Coord03 + '\'' +
                ", DO_Coord04='" + DO_Coord04 + '\'' +
                ", DO_DateLivr='" + DO_DateLivr + '\'' +
                ", DO_Motif='" + DO_Motif + '\'' +
                ", DO_DebutAbo='" + DO_DebutAbo + '\'' +
                ", DO_FinAbo='" + DO_FinAbo + '\'' +
                ", DO_DebutPeriod='" + DO_DebutPeriod + '\'' +
                ", DO_FinPeriod='" + DO_FinPeriod + '\'' +
                ", CG_Num='" + CG_Num + '\'' +
                ", DO_Heure='" + DO_Heure + '\'' +
                ", DO_NoWeb='" + DO_NoWeb + '\'' +
                ", CA_NumIFRS='" + CA_NumIFRS + '\'' +
                ", CT_NumCentrale='" + CT_NumCentrale + '\'' +
                ", DO_Contact='" + DO_Contact + '\'' +
                ", cbModification='" + cbModification + '\'' +
                ", VEHICULE='" + VEHICULE + '\'' +
                ", CHAUFFEUR='" + CHAUFFEUR + '\'' +
                ", statut='" + statut + '\'' +
                ", typeFacture='" + typeFacture + '\'' +
                ", cbCreateur='" + cbCreateur + '\'' +
                ", DO_DateSage='" + DO_DateSage + '\'' +
                ", CT_Intitule='" + CT_Intitule + '\'' +
                ", DE_Intitule='" + DE_Intitule + '\'' +
                ", DE_Intitule_dest='" + DE_Intitule_dest + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", ttc=" + ttc +
                ", avance=" + avance +
                ", resteAPayer=" + resteAPayer +
                '}';
    }
}
