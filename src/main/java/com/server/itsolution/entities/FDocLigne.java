package com.server.itsolution.entities;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FDocLigne {

    private BigDecimal cbMarq;
    private Integer DO_Domaine,DO_Type,CO_No,DL_TTC,DE_No,DL_MvtStock
            ,DL_TNomencl,DL_TRemPied,DL_TRemExep,AG_No1,AG_No2,DT_No
            ,DL_NoRef,DL_TypePL,DL_PUDevise,DL_No,DL_Frais,DL_Valorise
            ,DL_NonLivre,DL_FactPoids,DL_Escompte,DL_NoLink,DL_QteRessource
            ;

    private String CT_Num,DO_Piece,DL_PieceBC,DL_PieceBL,DO_Date,DL_DateBC
            ,DL_DateBL,DL_Ligne,DO_Ref,AR_Ref,DL_Design
            ,AF_RefFourniss,DL_Remise
            ,DO_DateLivr,CA_Num
            ,AR_RefCompose,AC_RefClient,DL_PiecePL
            ,DL_DatePL,DL_NoColis,RP_Code,DL_DateAvancement,EU_Enumere
            ,cbCreateur,cbModification,USERGESCOM,NOMCLIENT,DATEMODIF,ORDONATEUR_REMISE,MACHINEPC,GROUPEUSER;

    private double DL_PUTTC_Rem0,DL_PrixUnitaire_Rem0,DL_PUTTC_Rem,DL_PrixRU,DL_CMUP,DL_QtePL
            ,DL_PrixUnitaire_Rem,MT_Taxe1,MT_Taxe2,MT_Taxe3,EU_Qte,DL_PUTTC,DL_MontantHT,DL_MontantTTC
            ,DL_Qte,DL_QteBC,DL_QteBL,DL_PoidsNet,DL_PoidsBrut,DL_Remise01REM_Valeur,DL_Remise01REM_Type,DL_Remise02REM_Valeur
            ,DL_Remise02REM_Type,DL_Remise03REM_Valeur,DL_Remise03REM_Type,DL_PrixUnitaire,DL_Taxe3,DL_TypeTaux3,DL_TypeTaxe3
            ,DL_PUBC,DL_Taxe1,DL_TypeTaux1,DL_TypeTaxe1,DL_Taxe2,DL_TypeTaux2,DL_TypeTaxe2;
    public FDocLigne() {
    }

    public BigDecimal getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(BigDecimal cbMarq) {
        this.cbMarq = cbMarq;
    }

    public Integer getDO_Domaine() {
        return DO_Domaine;
    }

    public void setDO_Domaine(Integer DO_Domaine) {
        this.DO_Domaine = DO_Domaine;
    }

    public Integer getDO_Type() {
        return DO_Type;
    }

    public void setDO_Type(Integer DO_Type) {
        this.DO_Type = DO_Type;
    }

    public Integer getCO_No() {
        return CO_No;
    }

    public void setCO_No(Integer CO_No) {
        this.CO_No = CO_No;
    }

    public Integer getDL_TTC() {
        return DL_TTC;
    }

    public void setDL_TTC(Integer DL_TTC) {
        this.DL_TTC = DL_TTC;
    }

    public Integer getDE_No() {
        return DE_No;
    }

    public void setDE_No(Integer DE_No) {
        this.DE_No = DE_No;
    }

    public Integer getDL_MvtStock() {
        return DL_MvtStock;
    }

    public void setDL_MvtStock(Integer DL_MvtStock) {
        this.DL_MvtStock = DL_MvtStock;
    }

    public String getCT_Num() {
        return CT_Num;
    }

    public void setCT_Num(String CT_Num) {
        this.CT_Num = CT_Num;
    }

    public String getDO_Piece() {
        return DO_Piece;
    }

    public void setDO_Piece(String DO_Piece) {
        this.DO_Piece = DO_Piece;
    }

    public String getDL_PieceBC() {
        return DL_PieceBC;
    }

    public void setDL_PieceBC(String DL_PieceBC) {
        this.DL_PieceBC = DL_PieceBC;
    }

    public String getDL_PieceBL() {
        return DL_PieceBL;
    }

    public void setDL_PieceBL(String DL_PieceBL) {
        this.DL_PieceBL = DL_PieceBL;
    }

    public String getDO_Date() {
        return DO_Date;
    }

    public void setDO_Date(String DO_Date) {
        this.DO_Date = DO_Date;
    }

    public String getDL_DateBC() {
        return DL_DateBC;
    }

    public void setDL_DateBC(String DL_DateBC) {
        this.DL_DateBC = DL_DateBC;
    }

    public String getDL_DateBL() {
        return DL_DateBL;
    }

    public void setDL_DateBL(String DL_DateBL) {
        this.DL_DateBL = DL_DateBL;
    }

    public String getDL_Ligne() {
        return DL_Ligne;
    }

    public void setDL_Ligne(String DL_Ligne) {
        this.DL_Ligne = DL_Ligne;
    }

    public String getDO_Ref() {
        return DO_Ref;
    }

    public void setDO_Ref(String DO_Ref) {
        this.DO_Ref = DO_Ref;
    }

    public int getDL_TNomencl() {
        return DL_TNomencl;
    }

    public void setDL_TNomencl(int DL_TNomencl) {
        this.DL_TNomencl = DL_TNomencl;
    }

    public int getDL_TRemPied() {
        return DL_TRemPied;
    }

    public void setDL_TRemPied(int DL_TRemPied) {
        this.DL_TRemPied = DL_TRemPied;
    }

    public int getDL_TRemExep() {
        return DL_TRemExep;
    }

    public void setDL_TRemExep(int DL_TRemExep) {
        this.DL_TRemExep = DL_TRemExep;
    }

    public String getAR_Ref() {
        return AR_Ref;
    }

    public void setAR_Ref(String AR_Ref) {
        this.AR_Ref = AR_Ref;
    }

    public String getDL_Design() {
        return DL_Design;
    }

    public void setDL_Design(String DL_Design) {
        this.DL_Design = DL_Design;
    }

    public int getAG_No1() {
        return AG_No1;
    }

    public void setAG_No1(int AG_No1) {
        this.AG_No1 = AG_No1;
    }

    public int getAG_No2() {
        return AG_No2;
    }

    public void setAG_No2(int AG_No2) {
        this.AG_No2 = AG_No2;
    }

    public int getDT_No() {
        return DT_No;
    }

    public void setDT_No(int DT_No) {
        this.DT_No = DT_No;
    }

    public String getAF_RefFourniss() {
        return AF_RefFourniss;
    }

    public void setAF_RefFourniss(String AF_RefFourniss) {
        this.AF_RefFourniss = AF_RefFourniss;
    }

    public int getDL_NoRef() {
        return DL_NoRef;
    }

    public void setDL_NoRef(int DL_NoRef) {
        this.DL_NoRef = DL_NoRef;
    }

    public int getDL_TypePL() {
        return DL_TypePL;
    }

    public void setDL_TypePL(int DL_TypePL) {
        this.DL_TypePL = DL_TypePL;
    }

    public int getDL_PUDevise() {
        return DL_PUDevise;
    }

    public void setDL_PUDevise(int DL_PUDevise) {
        this.DL_PUDevise = DL_PUDevise;
    }

    public int getDL_No() {
        return DL_No;
    }

    public void setDL_No(int DL_No) {
        this.DL_No = DL_No;
    }

    public String getDO_DateLivr() {
        return DO_DateLivr;
    }

    public void setDO_DateLivr(String DO_DateLivr) {
        this.DO_DateLivr = DO_DateLivr;
    }

    public String getCA_Num() {
        return CA_Num;
    }

    public void setCA_Num(String CA_Num) {
        this.CA_Num = CA_Num;
    }

    public int getDL_Frais() {
        return DL_Frais;
    }

    public void setDL_Frais(int DL_Frais) {
        this.DL_Frais = DL_Frais;
    }

    public int getDL_Valorise() {
        return DL_Valorise;
    }

    public void setDL_Valorise(int DL_Valorise) {
        this.DL_Valorise = DL_Valorise;
    }

    public String getAR_RefCompose() {
        return AR_RefCompose;
    }

    public void setAR_RefCompose(String AR_RefCompose) {
        this.AR_RefCompose = AR_RefCompose;
    }

    public int getDL_NonLivre() {
        return DL_NonLivre;
    }

    public void setDL_NonLivre(int DL_NonLivre) {
        this.DL_NonLivre = DL_NonLivre;
    }

    public String getAC_RefClient() {
        return AC_RefClient;
    }

    public void setAC_RefClient(String AC_RefClient) {
        this.AC_RefClient = AC_RefClient;
    }

    public int getDL_FactPoids() {
        return DL_FactPoids;
    }

    public void setDL_FactPoids(int DL_FactPoids) {
        this.DL_FactPoids = DL_FactPoids;
    }

    public int getDL_Escompte() {
        return DL_Escompte;
    }

    public void setDL_Escompte(int DL_Escompte) {
        this.DL_Escompte = DL_Escompte;
    }

    public String getDL_PiecePL() {
        return DL_PiecePL;
    }

    public void setDL_PiecePL(String DL_PiecePL) {
        this.DL_PiecePL = DL_PiecePL;
    }

    public String getDL_DatePL() {
        return DL_DatePL;
    }

    public void setDL_DatePL(String DL_DatePL) {
        this.DL_DatePL = DL_DatePL;
    }

    public String getDL_NoColis() {
        return DL_NoColis;
    }

    public void setDL_NoColis(String DL_NoColis) {
        this.DL_NoColis = DL_NoColis;
    }

    public int getDL_NoLink() {
        return DL_NoLink;
    }

    public void setDL_NoLink(int DL_NoLink) {
        this.DL_NoLink = DL_NoLink;
    }

    public String getRP_Code() {
        return RP_Code;
    }

    public void setRP_Code(String RP_Code) {
        this.RP_Code = RP_Code;
    }

    public int getDL_QteRessource() {
        return DL_QteRessource;
    }

    public void setDL_QteRessource(int DL_QteRessource) {
        this.DL_QteRessource = DL_QteRessource;
    }

    public String getDL_DateAvancement() {
        return DL_DateAvancement;
    }

    public void setDL_DateAvancement(String DL_DateAvancement) {
        this.DL_DateAvancement = DL_DateAvancement;
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

    public String getUSERGESCOM() {
        return USERGESCOM;
    }

    public void setUSERGESCOM(String USERGESCOM) {
        this.USERGESCOM = USERGESCOM;
    }

    public String getNOMCLIENT() {
        return NOMCLIENT;
    }

    public void setNOMCLIENT(String NOMCLIENT) {
        this.NOMCLIENT = NOMCLIENT;
    }

    public String getDATEMODIF() {
        return DATEMODIF;
    }

    public void setDATEMODIF(String DATEMODIF) {
        this.DATEMODIF = DATEMODIF;
    }

    public String getORDONATEUR_REMISE() {
        return ORDONATEUR_REMISE;
    }

    public void setORDONATEUR_REMISE(String ORDONATEUR_REMISE) {
        this.ORDONATEUR_REMISE = ORDONATEUR_REMISE;
    }

    public String getMACHINEPC() {
        return MACHINEPC;
    }

    public void setMACHINEPC(String MACHINEPC) {
        this.MACHINEPC = MACHINEPC;
    }

    public String getGROUPEUSER() {
        return GROUPEUSER;
    }

    public void setGROUPEUSER(String GROUPEUSER) {
        this.GROUPEUSER = GROUPEUSER;
    }

    public double getDL_PUTTC_Rem0() {
        return DL_PUTTC_Rem0;
    }

    public void setDL_PUTTC_Rem0(double DL_PUTTC_Rem0) {
        this.DL_PUTTC_Rem0 = DL_PUTTC_Rem0;
    }

    public double getDL_PrixUnitaire_Rem0() {
        return DL_PrixUnitaire_Rem0;
    }

    public void setDL_PrixUnitaire_Rem0(double DL_PrixUnitaire_Rem0) {
        this.DL_PrixUnitaire_Rem0 = DL_PrixUnitaire_Rem0;
    }

    public double getDL_PUTTC_Rem() {
        return DL_PUTTC_Rem;
    }

    public void setDL_PUTTC_Rem(double DL_PUTTC_Rem) {
        this.DL_PUTTC_Rem = DL_PUTTC_Rem;
    }

    public double getDL_PrixRU() {
        return DL_PrixRU;
    }

    public void setDL_PrixRU(double DL_PrixRU) {
        this.DL_PrixRU = DL_PrixRU;
    }

    public double getDL_CMUP() {
        return DL_CMUP;
    }

    public void setDL_CMUP(double DL_CMUP) {
        this.DL_CMUP = DL_CMUP;
    }

    public double getDL_QtePL() {
        return DL_QtePL;
    }

    public void setDL_QtePL(double DL_QtePL) {
        this.DL_QtePL = DL_QtePL;
    }

    public double getDL_PrixUnitaire_Rem() {
        return DL_PrixUnitaire_Rem;
    }

    public void setDL_PrixUnitaire_Rem(double DL_PrixUnitaire_Rem) {
        this.DL_PrixUnitaire_Rem = DL_PrixUnitaire_Rem;
    }

    public String getDL_Remise() {
        return DL_Remise;
    }

    public void setDL_Remise(String DL_Remise) {
        this.DL_Remise = DL_Remise;
    }

    public double getMT_Taxe1() {
        return MT_Taxe1;
    }

    public void setMT_Taxe1(double MT_Taxe1) {
        this.MT_Taxe1 = MT_Taxe1;
    }

    public double getMT_Taxe2() {
        return MT_Taxe2;
    }

    public void setMT_Taxe2(double MT_Taxe2) {
        this.MT_Taxe2 = MT_Taxe2;
    }

    public double getMT_Taxe3() {
        return MT_Taxe3;
    }

    public void setMT_Taxe3(double MT_Taxe3) {
        this.MT_Taxe3 = MT_Taxe3;
    }

    public double getEU_Qte() {
        return EU_Qte;
    }

    public void setEU_Qte(double EU_Qte) {
        this.EU_Qte = EU_Qte;
    }

    public String getEU_Enumere() {
        return EU_Enumere;
    }

    public void setEU_Enumere(String EU_Enumere) {
        this.EU_Enumere = EU_Enumere;
    }

    public double getDL_PUTTC() {
        return DL_PUTTC;
    }

    public void setDL_PUTTC(double DL_PUTTC) {
        this.DL_PUTTC = DL_PUTTC;
    }

    public double getDL_MontantHT() {
        return DL_MontantHT;
    }

    public void setDL_MontantHT(double DL_MontantHT) {
        this.DL_MontantHT = DL_MontantHT;
    }

    public double getDL_MontantTTC() {
        return DL_MontantTTC;
    }

    public void setDL_MontantTTC(double DL_MontantTTC) {
        this.DL_MontantTTC = DL_MontantTTC;
    }

    public double getDL_Qte() {
        return DL_Qte;
    }

    public void setDL_Qte(double DL_Qte) {
        this.DL_Qte = DL_Qte;
    }

    public double getDL_QteBC() {
        return DL_QteBC;
    }

    public void setDL_QteBC(double DL_QteBC) {
        this.DL_QteBC = DL_QteBC;
    }

    public double getDL_QteBL() {
        return DL_QteBL;
    }

    public void setDL_QteBL(double DL_QteBL) {
        this.DL_QteBL = DL_QteBL;
    }

    public double getDL_PoidsNet() {
        return DL_PoidsNet;
    }

    public void setDL_PoidsNet(double DL_PoidsNet) {
        this.DL_PoidsNet = DL_PoidsNet;
    }

    public double getDL_PoidsBrut() {
        return DL_PoidsBrut;
    }

    public void setDL_PoidsBrut(double DL_PoidsBrut) {
        this.DL_PoidsBrut = DL_PoidsBrut;
    }

    public double getDL_Remise01REM_Valeur() {
        return DL_Remise01REM_Valeur;
    }

    public void setDL_Remise01REM_Valeur(double DL_Remise01REM_Valeur) {
        this.DL_Remise01REM_Valeur = DL_Remise01REM_Valeur;
    }

    public double getDL_Remise01REM_Type() {
        return DL_Remise01REM_Type;
    }

    public void setDL_Remise01REM_Type(double DL_Remise01REM_Type) {
        this.DL_Remise01REM_Type = DL_Remise01REM_Type;
    }

    public double getDL_Remise02REM_Valeur() {
        return DL_Remise02REM_Valeur;
    }

    public void setDL_Remise02REM_Valeur(double DL_Remise02REM_Valeur) {
        this.DL_Remise02REM_Valeur = DL_Remise02REM_Valeur;
    }

    public double getDL_Remise02REM_Type() {
        return DL_Remise02REM_Type;
    }

    public void setDL_Remise02REM_Type(double DL_Remise02REM_Type) {
        this.DL_Remise02REM_Type = DL_Remise02REM_Type;
    }

    public double getDL_Remise03REM_Valeur() {
        return DL_Remise03REM_Valeur;
    }

    public void setDL_Remise03REM_Valeur(double DL_Remise03REM_Valeur) {
        this.DL_Remise03REM_Valeur = DL_Remise03REM_Valeur;
    }

    public double getDL_Remise03REM_Type() {
        return DL_Remise03REM_Type;
    }

    public void setDL_Remise03REM_Type(double DL_Remise03REM_Type) {
        this.DL_Remise03REM_Type = DL_Remise03REM_Type;
    }

    public double getDL_PrixUnitaire() {
        return DL_PrixUnitaire;
    }

    public void setDL_PrixUnitaire(double DL_PrixUnitaire) {
        this.DL_PrixUnitaire = DL_PrixUnitaire;
    }

    public double getDL_Taxe3() {
        return DL_Taxe3;
    }

    public void setDL_Taxe3(double DL_Taxe3) {
        this.DL_Taxe3 = DL_Taxe3;
    }

    public double getDL_TypeTaux3() {
        return DL_TypeTaux3;
    }

    public void setDL_TypeTaux3(double DL_TypeTaux3) {
        this.DL_TypeTaux3 = DL_TypeTaux3;
    }

    public double getDL_TypeTaxe3() {
        return DL_TypeTaxe3;
    }

    public void setDL_TypeTaxe3(double DL_TypeTaxe3) {
        this.DL_TypeTaxe3 = DL_TypeTaxe3;
    }

    public double getDL_PUBC() {
        return DL_PUBC;
    }

    public void setDL_PUBC(double DL_PUBC) {
        this.DL_PUBC = DL_PUBC;
    }

    public double getDL_Taxe1() {
        return DL_Taxe1;
    }

    public void setDL_Taxe1(double DL_Taxe1) {
        this.DL_Taxe1 = DL_Taxe1;
    }

    public double getDL_TypeTaux1() {
        return DL_TypeTaux1;
    }

    public void setDL_TypeTaux1(double DL_TypeTaux1) {
        this.DL_TypeTaux1 = DL_TypeTaux1;
    }

    public double getDL_TypeTaxe1() {
        return DL_TypeTaxe1;
    }

    public void setDL_TypeTaxe1(double DL_TypeTaxe1) {
        this.DL_TypeTaxe1 = DL_TypeTaxe1;
    }

    public double getDL_Taxe2() {
        return DL_Taxe2;
    }

    public void setDL_Taxe2(double DL_Taxe2) {
        this.DL_Taxe2 = DL_Taxe2;
    }

    public double getDL_TypeTaux2() {
        return DL_TypeTaux2;
    }

    public void setDL_TypeTaux2(double DL_TypeTaux2) {
        this.DL_TypeTaux2 = DL_TypeTaux2;
    }

    public double getDL_TypeTaxe2() {
        return DL_TypeTaxe2;
    }

    public void setDL_TypeTaxe2(double DL_TypeTaxe2) {
        this.DL_TypeTaxe2 = DL_TypeTaxe2;
    }

    @Override
    public String toString() {
        return "FDocLigne{" +
                "cbMarq=" + cbMarq +
                ", DO_Domaine=" + DO_Domaine +
                ", DO_Type=" + DO_Type +
                ", CO_No=" + CO_No +
                ", DL_TTC=" + DL_TTC +
                ", DE_No=" + DE_No +
                ", DL_MvtStock=" + DL_MvtStock +
                ", DL_TNomencl=" + DL_TNomencl +
                ", DL_TRemPied=" + DL_TRemPied +
                ", DL_TRemExep=" + DL_TRemExep +
                ", AG_No1=" + AG_No1 +
                ", AG_No2=" + AG_No2 +
                ", DT_No=" + DT_No +
                ", DL_NoRef=" + DL_NoRef +
                ", DL_TypePL=" + DL_TypePL +
                ", DL_PUDevise=" + DL_PUDevise +
                ", DL_No=" + DL_No +
                ", DL_Frais=" + DL_Frais +
                ", DL_Valorise=" + DL_Valorise +
                ", DL_NonLivre=" + DL_NonLivre +
                ", DL_FactPoids=" + DL_FactPoids +
                ", DL_Escompte=" + DL_Escompte +
                ", DL_NoLink=" + DL_NoLink +
                ", DL_QteRessource=" + DL_QteRessource +
                ", CT_Num='" + CT_Num + '\'' +
                ", DO_Piece='" + DO_Piece + '\'' +
                ", DL_PieceBC='" + DL_PieceBC + '\'' +
                ", DL_PieceBL='" + DL_PieceBL + '\'' +
                ", DO_Date='" + DO_Date + '\'' +
                ", DL_DateBC='" + DL_DateBC + '\'' +
                ", DL_DateBL='" + DL_DateBL + '\'' +
                ", DL_Ligne='" + DL_Ligne + '\'' +
                ", DO_Ref='" + DO_Ref + '\'' +
                ", AR_Ref='" + AR_Ref + '\'' +
                ", DL_Design='" + DL_Design + '\'' +
                ", AF_RefFourniss='" + AF_RefFourniss + '\'' +
                ", DL_Remise='" + DL_Remise + '\'' +
                ", DO_DateLivr='" + DO_DateLivr + '\'' +
                ", CA_Num='" + CA_Num + '\'' +
                ", AR_RefCompose='" + AR_RefCompose + '\'' +
                ", AC_RefClient='" + AC_RefClient + '\'' +
                ", DL_PiecePL='" + DL_PiecePL + '\'' +
                ", DL_DatePL='" + DL_DatePL + '\'' +
                ", DL_NoColis='" + DL_NoColis + '\'' +
                ", RP_Code='" + RP_Code + '\'' +
                ", DL_DateAvancement='" + DL_DateAvancement + '\'' +
                ", EU_Enumere='" + EU_Enumere + '\'' +
                ", cbCreateur='" + cbCreateur + '\'' +
                ", cbModification='" + cbModification + '\'' +
                ", USERGESCOM='" + USERGESCOM + '\'' +
                ", NOMCLIENT='" + NOMCLIENT + '\'' +
                ", DATEMODIF='" + DATEMODIF + '\'' +
                ", ORDONATEUR_REMISE='" + ORDONATEUR_REMISE + '\'' +
                ", MACHINEPC='" + MACHINEPC + '\'' +
                ", GROUPEUSER='" + GROUPEUSER + '\'' +
                ", DL_PUTTC_Rem0=" + DL_PUTTC_Rem0 +
                ", DL_PrixUnitaire_Rem0=" + DL_PrixUnitaire_Rem0 +
                ", DL_PUTTC_Rem=" + DL_PUTTC_Rem +
                ", DL_PrixRU=" + DL_PrixRU +
                ", DL_CMUP=" + DL_CMUP +
                ", DL_QtePL=" + DL_QtePL +
                ", DL_PrixUnitaire_Rem=" + DL_PrixUnitaire_Rem +
                ", MT_Taxe1=" + MT_Taxe1 +
                ", MT_Taxe2=" + MT_Taxe2 +
                ", MT_Taxe3=" + MT_Taxe3 +
                ", EU_Qte=" + EU_Qte +
                ", DL_PUTTC=" + DL_PUTTC +
                ", DL_MontantHT=" + DL_MontantHT +
                ", DL_MontantTTC=" + DL_MontantTTC +
                ", DL_Qte=" + DL_Qte +
                ", DL_QteBC=" + DL_QteBC +
                ", DL_QteBL=" + DL_QteBL +
                ", DL_PoidsNet=" + DL_PoidsNet +
                ", DL_PoidsBrut=" + DL_PoidsBrut +
                ", DL_Remise01REM_Valeur=" + DL_Remise01REM_Valeur +
                ", DL_Remise01REM_Type=" + DL_Remise01REM_Type +
                ", DL_Remise02REM_Valeur=" + DL_Remise02REM_Valeur +
                ", DL_Remise02REM_Type=" + DL_Remise02REM_Type +
                ", DL_Remise03REM_Valeur=" + DL_Remise03REM_Valeur +
                ", DL_Remise03REM_Type=" + DL_Remise03REM_Type +
                ", DL_PrixUnitaire=" + DL_PrixUnitaire +
                ", DL_Taxe3=" + DL_Taxe3 +
                ", DL_TypeTaux3=" + DL_TypeTaux3 +
                ", DL_TypeTaxe3=" + DL_TypeTaxe3 +
                ", DL_PUBC=" + DL_PUBC +
                ", DL_Taxe1=" + DL_Taxe1 +
                ", DL_TypeTaux1=" + DL_TypeTaux1 +
                ", DL_TypeTaxe1=" + DL_TypeTaxe1 +
                ", DL_Taxe2=" + DL_Taxe2 +
                ", DL_TypeTaux2=" + DL_TypeTaux2 +
                ", DL_TypeTaxe2=" + DL_TypeTaxe2 +
                '}';
    }
}
