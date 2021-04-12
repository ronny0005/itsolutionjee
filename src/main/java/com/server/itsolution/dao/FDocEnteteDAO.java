package com.server.itsolution.dao;

import com.server.itsolution.entities.*;
import com.server.itsolution.mapper.FCReglementMapper;
import com.server.itsolution.mapper.FDocEnteteMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.sql.RowSet;
import java.math.BigDecimal;
import java.text.Bidi;
import java.text.Normalizer;
import java.util.*;

@Repository
@Transactional
public class FDocEnteteDAO extends JdbcDaoSupport {

    @Autowired
    public FDocEnteteDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Object[] params = new Object[] {};
    public FDocEnteteMapper mapper = new FDocEnteteMapper();
    public FDocEntete fDocEntete = new FDocEntete();

    public List<Object> getAll() {
        String sql = FDocEnteteMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public FDocRegl getDocRegl(FDocEntete fDocEntete) {
        String sql = " SELECT 	cbMarq " +
                " FROM 		F_DOCREGL " +
                " WHERE 	DO_Domaine = " + fDocEntete.getDO_Domaine() +
                " AND 		DO_Type = " + fDocEntete.getDO_Type() +
                " AND 		DO_Piece = '"+ fDocEntete.getDO_Piece() +"'";
        FDocReglDAO fDocReglDAO = new FDocReglDAO(this.getDataSource());
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(fDocEntete.getDO_Domaine());
        params.add(fDocEntete.getDO_Type());
        params.add(fDocEntete.getDO_Piece());
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql);
        while(sqlRowSet.next()) {
            return fDocReglDAO.getDocRegl(sqlRowSet.getBigDecimal("cbMarq"));
        }
        return null;
    }

    public List<Object> getListeLigne (FDocEntete fDocEntete){
        String sql = FDocEnteteMapper.listeLigne;
        params = new Object[] {fDocEntete.getCbMarq()};
        return this.getJdbcTemplate().query(sql, params,mapper);
    }

    public int journeeCloture(String date,int caNo){
        String sql = FDocEnteteMapper.journeeCloture;
        params = new Object[] {date,caNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql,params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getInt("Nb");
        }
        return 0;
    }

    public int getItem(ArrayList<Object> table,String val){
        int pos=-1;
        for(int i = 0;i<table.size();i++){
            if(((String)table.get(i)).equals(val)){
                pos = i;
            }
        }
        return pos;
    }

    public String getPiedPage(BigDecimal cbMarq,String typeFacture,int catCompta,int catTarif){
        String html = "";
        FDocEntete fDocEntete = this.getFDocEntete(cbMarq);
        fDocEntete.setTypeFac(typeFacture);
        String entete = fDocEntete.getDO_Piece();
        double totalHT = 0,totalTTC = 0,totalQte = 0;

        int doDomaine = fDocEntete.getDO_Domaine();
        int doType = fDocEntete.getDO_Type();
        ArrayList<Object> table = new ArrayList<Object>();
        ArrayList<Object> tabLib = new ArrayList<Object>();
        tabLib.add("Montant HT");
        table.add(0);
        int type=0;
        if(doDomaine!=0)
            type=1;

        int i=0;
        ArrayList<BigDecimal> rowsligne = new ArrayList<BigDecimal>();
        if(entete!=null)
            rowsligne = this.getListeFDocLigne(cbMarq);
        FDocLigneDAO fDocLigneDAO = new FDocLigneDAO(this.getDataSource());
        for (BigDecimal row : rowsligne) {
            FDocLigne fDocLigne = fDocLigneDAO.getFDocLigne (row);
            totalQte = totalQte + fDocLigne.getDL_Qte();
            if (typeFacture.equals("VenteRetour")) {
                totalHT = totalHT - fDocLigne.getDL_MontantHT();
                totalTTC = totalTTC - fDocLigne.getDL_MontantTTC();
            } else {
                totalHT = totalHT + fDocLigne.getDL_MontantHT();
                totalTTC = totalTTC + fDocLigne.getDL_MontantTTC();
            }
            double prix = fDocLigne.getDL_PUTTC();
            double rem = 0;
            if (fDocLigne.getDL_TTC() == 0)
                prix = fDocLigne.getDL_PrixUnitaire();
            int catcomptafinal = catCompta;
            if (doType == 11)
                if(fDocLigne.getDL_NoColis().equals(""))
                    catcomptafinal= 0;
                else
                    catcomptafinal = Integer.valueOf(fDocLigne.getDL_NoColis());
            ArrayList<Object> rowsPrix = fDocLigneDAO.getPrixClientHT(fDocLigne.getAR_Ref(),catcomptafinal,catTarif,prix,rem,fDocLigne.getDL_Qte(),type);
            int pos = getItem(tabLib, (String)rowsPrix.get(17) /*IntituleT1*/);
            if (pos == -1) {
                tabLib.add( rowsPrix.get(17) /*IntituleT1*/);
                if (typeFacture.equals("VenteRetour"))
                    table.add(-fDocLigne.getMT_Taxe1());
                else
                    table.add(fDocLigne.getMT_Taxe1());
            } else {
                if (typeFacture.equals("VenteRetour"))
                    table.set(pos,(double)table.get(pos) - fDocLigne.getMT_Taxe1());
                else
                    table.set(pos,(double)table.get(pos) + fDocLigne.getMT_Taxe1());
            }
            pos = getItem(tabLib, (String)rowsPrix.get(18) /*IntituleT2*/);
            if (pos == -1) {
                tabLib.add( rowsPrix.get(18) /*IntituleT2*/);
                if (typeFacture.equals("VenteRetour"))
                    table.add(-fDocLigne.getMT_Taxe2());
                else
                    table.add(fDocLigne.getMT_Taxe2());
            } else {
                if (typeFacture.equals("VenteRetour"))
                    table.set(pos,-fDocLigne.getMT_Taxe2());
                else
                    table.set(pos,fDocLigne.getMT_Taxe2());
            }
            pos = getItem(tabLib, (String)rowsPrix.get(19) /*IntituleT3*/);
            if (pos == -1) {
                tabLib.add( rowsPrix.get(19) /*IntituleT3*/);
                if (typeFacture.equals("VenteRetour"))
                    table.add(-fDocLigne.getMT_Taxe3());
                else
                    table.add(fDocLigne.getMT_Taxe3());
            } else {
                if (typeFacture.equals("VenteRetour"))
                    table.set(pos,-fDocLigne.getMT_Taxe3());
                else
                    table.set(pos,fDocLigne.getMT_Taxe3());
            }
        }

        table.set(0,totalHT);
        tabLib.add("Total quantité");
        table.add(totalQte);

        if(doDomaine!=2) {
            tabLib.add("Montant TTC");
            table.add(totalTTC);
        }


        if(rowsligne.size()>0){
            i = 0;
            for (Object lib : tabLib){
                if(!lib.equals("")) {
                    double montant = (double) table.get(i);
                    if (doDomaine == 2 && doType == 23)
                        montant = montant / 2;
                    html = html + "<b>" + lib + " : <br/>";
                    html = html + FormatText.getMontant(montant) + "<br/><br/></b>";
                }
                i++;
            }
        }
        return html;
    }
    public ArrayList<BigDecimal> getListeFDocLigne (BigDecimal cbMarq){
        String sql = FDocEnteteMapper.listeLigne;
        params = new Object[] {cbMarq};
        ArrayList<BigDecimal> listeLigne = new ArrayList<BigDecimal>();
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            listeLigne.add(sqlRowSet.getBigDecimal("cbMarq"));
        }
        return listeLigne;
    }

    public int getdrRegle (BigDecimal cbMarq){
        String sql = FDocEnteteMapper.drRegle;
        params = new Object[] {cbMarq};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getInt("DR_Regle");
        }
        return 0;
    }

    public String getCbCreateurName(BigDecimal cbMarq){
        String sql = FDocEnteteMapper.getCbCreateurName;
        params = new Object[] {fDocEntete.getCbMarq()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getString("Prot_User");
        }
        return "";
    }

    public Object getFDocEnteteJSon(BigDecimal cbMarq) {
        String sql = FDocEnteteMapper.getFDocEntete;
        params = new Object[]{cbMarq};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
    public FDocEntete getFDocEntete(BigDecimal cbMarq) {
        String sql = FDocEnteteMapper.getFDocEntete;
        params = new Object[] {cbMarq};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FDocEntete fDocEntete = new FDocEntete();
        fDocEntete.setDO_Modif(0);
        while(sqlRowSet.next()) {
            fDocEntete.setAB_No(sqlRowSet.getInt("AB_No"));
            fDocEntete.setCA_No(sqlRowSet.getInt("CA_No"));
            fDocEntete.setDO_Devise(sqlRowSet.getInt("DO_Devise"));
            fDocEntete.setDO_Ref(sqlRowSet.getString("DO_Ref"));
            fDocEntete.setCA_Num(sqlRowSet.getString("CA_Num"));
            fDocEntete.setCA_NumIFRS(sqlRowSet.getString("CA_NumIFRS"));
            fDocEntete.setCbCreateur(sqlRowSet.getString("cbCreateur"));
            fDocEntete.setDO_Imprim(sqlRowSet.getInt("DO_Imprim"));
            fDocEntete.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
            fDocEntete.setCbModification(sqlRowSet.getString("cbModification"));
            fDocEntete.setCO_No(sqlRowSet.getInt("CO_No"));
            fDocEntete.setCO_NoCaissier(sqlRowSet.getInt("CO_NoCaissier"));
            fDocEntete.setCT_NumCentrale(sqlRowSet.getString("CT_NumCentrale"));
            fDocEntete.setDE_No(sqlRowSet.getInt("DE_No"));
            fDocEntete.setDO_Attente(sqlRowSet.getInt("DO_Attente"));
            fDocEntete.setDO_BLFact(sqlRowSet.getInt("DO_BLFact"));
            fDocEntete.setDO_Cloture(sqlRowSet.getInt("DO_Cloture"));
            fDocEntete.setDO_Colisage(sqlRowSet.getInt("DO_Colisage"));
            fDocEntete.setDO_Condition(sqlRowSet.getInt("DO_Condition"));
            fDocEntete.setDO_Contact(sqlRowSet.getString("DO_Contact"));
            fDocEntete.setDO_Coord01(sqlRowSet.getString("DO_Coord01"));
            fDocEntete.setDO_Coord02(sqlRowSet.getString("DO_Coord02"));
            fDocEntete.setDO_Coord03(sqlRowSet.getString("DO_Coord03"));
            fDocEntete.setDO_Coord04(sqlRowSet.getString("DO_Coord04"));
            fDocEntete.setDO_Cours(sqlRowSet.getDouble("DO_Cours"));
            fDocEntete.setDO_Date(sqlRowSet.getString("DO_Date"));
            fDocEntete.setDO_DateLivr(sqlRowSet.getString("DO_DateLivr"));
            fDocEntete.setDO_Type(sqlRowSet.getInt("DO_Type"));
            fDocEntete.setDO_Domaine(sqlRowSet.getInt("DO_Domaine"));
            fDocEntete.setDO_Piece(sqlRowSet.getString("DO_Piece"));
            fDocEntete.setDO_Souche(sqlRowSet.getInt("DO_Souche"));
            fDocEntete.setDO_Provenance(sqlRowSet.getInt("DO_Provenance"));
            fDocEntete.setDO_Tiers(sqlRowSet.getString("DO_Tiers"));
            fDocEntete.setN_CatCompta(sqlRowSet.getInt("N_CatCompta"));
            fDocEntete.setCG_Num(sqlRowSet.getString("CG_Num"));
            fDocEntete.setAvance(AvanceDoPiece(fDocEntete));
            fDocEntete.setTtc(montantRegle(fDocEntete));
            fDocEntete.setDO_Modif(this.setDoModif(fDocEntete.getCbMarq()));
            fDocEntete.setResteAPayer(fDocEntete.getTtc()-fDocEntete.getAvance());
            fDocEntete.setStatut("crédit");
            if(getListeLigne(fDocEntete).size()==0)
                fDocEntete.setStatut("crédit");
            else
            if((fDocEntete.getDO_Domaine()==0 || fDocEntete.getDO_Domaine()==1) && this.getdrRegle(fDocEntete.getCbMarq())==1 && fDocEntete.getResteAPayer()==0)
                fDocEntete.setStatut("comptant");
            fDocEntete.initTypeFacture();
        }
        fDocEntete.setDO_Modif(setDoModif(cbMarq));


        return fDocEntete;
    }



    public double AvanceDoPiece(FDocEntete fDocEntete){
        String sql = FDocEnteteMapper.AvanceDoPiece;
        params = new Object[]{fDocEntete.getCbMarq()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getDouble("avance_regle");
        }
        return 0;
    }
    public List<Object> statutVente(String type){
        String sql = FDocEnteteMapper.statutVente;
        params = new Object[]{type};
        return  this.getJdbcTemplate().query(sql, params,mapper);
    }
    public List<Object> statutAchat(String type){
        String sql = FDocEnteteMapper.statutAchat;
        params = new Object[]{type};
        return  this.getJdbcTemplate().query(sql, params,mapper);
    }

    public List<Object> resteARegler(BigDecimal cbMarq,double avance){
        String sql = FDocEnteteMapper.resteARegler;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(avance);
        parames.add(cbMarq);
        return  this.getJdbcTemplate().query(sql, parames.toArray(),mapper);
    }


    public int setDoModif(BigDecimal cbMarq){
        String sql = FDocEnteteMapper.setDoModif;
        params = new Object[]{cbMarq};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getInt("DO_Modif");
        }
        return 0;
    }

    public List<Object> getListeFacture(int doProvenance,int doType,int doDomaine, int deNo, String datedeb,String dateFin,String client,int protNo) {
        String sql = FDocEnteteMapper.getListeFacture;
        params = new Object[]{protNo,doProvenance,deNo,datedeb,dateFin,client,doDomaine,doType};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getlisteEntree(String doTiers,String dateDeb,String dateFin){
        String sql = FDocEnteteMapper.listeEntree;
        params = new Object[]{doTiers,dateDeb,dateFin};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getlisteSortie(String doTiers,String dateDeb,String dateFin){
        String sql = FDocEnteteMapper.listeSortie;
        params = new Object[]{doTiers,dateDeb,dateFin};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getlisteTransfert(String doTiers,String dateDeb,String dateFin){
        String sql = FDocEnteteMapper.listeTransfert;
        params = new Object[]{doTiers,dateDeb,dateFin};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> listeTransfertConfirmation(String dateDeb,String dateFin,int doDomaine,int doType,int protNo,String typeFac){
        String sql = FDocEnteteMapper.listeTransfertConfirmation;
        params = new Object[]{dateDeb,dateFin,doDomaine,doType,protNo,typeFac};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getlisteTransfertDetail(String doTiers,String dateDeb,String dateFin){
        String sql = FDocEnteteMapper.listeTransfertDetail;
        params = new Object[]{doTiers,dateDeb,dateFin};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<Object> getLigneFactureTransfert(BigDecimal cbMarq){
        String sql = FDocEnteteMapper.getLigneFactureTransfert;
        params = new Object[]{cbMarq};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public double montantRegle(FDocEntete fDocEntete){
        String sql = FDocEnteteMapper.montantRegle;
        params = new Object[]{fDocEntete.getCbMarq()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getDouble("montantRegle");
        }
        return 0;
    }

    public List<Object> getLigneTransfert(BigDecimal cbMarq){
        String sql = FDocEnteteMapper.getLigneTransfert;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(cbMarq);
        List<Object> list = this.getJdbcTemplate().query(sql, parames.toArray(), mapper);
        return list;
    }

    public int isVisu(BigDecimal cbMarq,int protNo,String typeFacture)
    {
        FProtectioncialDAO fProtectioncialDao = new FProtectioncialDAO(this.getDataSource());
        FProtectioncial fProtectioncial = fProtectioncialDao.connexionProctectionByProtNo(protNo);
        int protAdministrator = fProtectioncial.getPROT_Administrator();
        int protectedDocP = fProtectioncial.protectedType(typeFacture);
        int flagProtApresImpressionP = fProtectioncial.getPROT_APRES_IMPRESSION();
        FDocEntete fDocEntete = getFDocEntete(cbMarq);
        int deNo=0;
        if(fDocEntete.getCbMarq()!=null)
            deNo = fDocEntete.getDE_No();
        int isSecurite = fProtectioncialDao.isSecuriteAdmin(fProtectioncial.getProt_No(),fProtectioncial.getProtectAdmin(),deNo);
        if(fProtectioncial.getPROT_Administrator() == 0 && typeFacture.equals("Transfert_confirmation")) {
            if (fDocEntete.getDO_Imprim() == 1)
                return 1;
        }
        else{
            if(fDocEntete.getDO_Type()==7 || fDocEntete.getDO_Type()==17)
            {
                return 1;
            }
        }
        if(isSecurite == 0)
            return 1;
        else
        if(fDocEntete.getCbMarq()!=null && fDocEntete.getAvance()>0)
            return 1;
        else
        if (protAdministrator == 1) {
            if ( fDocEntete.getCbMarq()!=null && fDocEntete.getStatut().equals("comptant"))
                return 1;
            else
                return 0;
        }
        else {
            if(fDocEntete.getCbMarq()!=null && fDocEntete.getDO_Modif()==1){
                return 1;
            }
            else{
                if(protectedDocP==0){
                    return 1;
                }else{
                    if(fDocEntete.getCbMarq()!=null && fDocEntete.getDO_Imprim()==1 && flagProtApresImpressionP!=0){
                        return 1;
                    }else{
                        if(fDocEntete.getCbMarq()!=null && fDocEntete.getStatut().equals("comptant") || fDocEntete.getAvance()>0){
                            return 1;
                        }
                    }
                }
            }
        }
        return 0;
    }


    public void suppr_facture(BigDecimal cbMarq,String typeFacture,String protNo){
        FDocEntete fDocEntete = getFDocEntete(cbMarq);
        FDocLigneDAO fDocLigneDAO = new FDocLigneDAO(this.getDataSource());
        FArtStockDAO fArtStockDAO = new FArtStockDAO(this.getDataSource());
        for(BigDecimal cbMarqLigne : this.getListeFDocLigne(cbMarq)){
            FDocLigne fDocLigne = fDocLigneDAO.getFDocLigne(cbMarqLigne);
            Double dlQte = fDocLigne.getDL_Qte();
            int deNo = fDocLigne.getDE_No();
            Double arPrixAch = fDocLigne.getDL_CMUP();
            String arRef = fDocLigne.getAR_Ref();
            if(typeFacture.equals("Vente") || typeFacture.equals("BonLivraison") || typeFacture.equals("Sortie"))
                fArtStockDAO.updateFArtstock(arRef,deNo,arPrixAch*dlQte,dlQte,"supprLigne",protNo);
            if(typeFacture.equals("Entree") || typeFacture.equals("Achat"))
                fArtStockDAO.updateFArtstock(arRef,deNo,-arPrixAch*dlQte,-dlQte,"insertLigne",protNo);
            if(typeFacture.equals("Transfert")){
                if(fDocLigne.getDL_MvtStock()==3)
                    fArtStockDAO.updateFArtstock(arRef,deNo,arPrixAch*dlQte,dlQte,"insertLigne",protNo);
                else
                    fArtStockDAO.updateFArtstock(arRef,deNo,-arPrixAch*dlQte,-dlQte,"insertLigne",protNo);
            }
            fDocLigneDAO.deleteLigne(cbMarqLigne);
        }
        suppressionReglement(fDocEntete);
    }

    public void suppressionReglement(FDocEntete fDocEntete){
        String sql = FDocEnteteMapper.suppressionReglement;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(fDocEntete.getDO_Piece());
        parames.add(fDocEntete.getDO_Domaine());
        parames.add(fDocEntete.getDO_Type());
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public int isModif(BigDecimal cbMarq,int protNo,String typeFacture){
        FProtectioncialDAO fProtectioncialDao = new FProtectioncialDAO(this.getDataSource());
        FProtectioncial fProtectioncial = fProtectioncialDao.connexionProctectionByProtNo(protNo);
        int protAdministrator = fProtectioncial.getPROT_Administrator();
        int protectedDocP = fProtectioncial.protectedType(typeFacture);
        int flagProtApresImpressionP = fProtectioncial.getPROT_APRES_IMPRESSION();
        FDocEntete fDocEntete = getFDocEntete(cbMarq);
        int deNo=0;
        if(fDocEntete.getCbMarq()!=null)
            deNo = fDocEntete.getDE_No();
        int isSecurite = fProtectioncialDao.isSecuriteAdmin(fProtectioncial.getProt_No(),fProtectioncial.getProtectAdmin(),deNo);
        if(isSecurite==0)
            return 0;
        else
            if(fDocEntete.getDO_Type() == 7 || fDocEntete.getDO_Type() == 17)
                return 0;
            else
        if(protAdministrator==1 || fProtectioncial.getPROT_Right()==1) {
            if (fDocEntete.getAvance() == 0)
                return 1;
        }else{
            if(fDocEntete.getDO_Modif()==1) {
                return 0;
            }
            else{
                if(protectedDocP==0){
                    return 0;
                }else{
                    if(fDocEntete.getCbMarq()!=null && fDocEntete.getDO_Imprim()==1 && flagProtApresImpressionP!=0){
                        return 0;
                    }else{
                        if(fDocEntete.getAvance()!=0){
                            return 0;
                        }
                    }
                }
            }
        }
        return 0;
    }

    public void regle(BigDecimal cbMarq,String typeFacture,int protNo,int valideRegle,int valideRegltImprime,double montantAvance,int modeReglement,String dateReglt,String libReglt,String dateEch){
        FDocEntete fDocEntete = getFDocEntete(cbMarq);
        fDocEntete.setTypeFac(typeFacture);
        FComptetDAO fComptetDAO = new FComptetDAO(this.getDataSource());
        FComptet fComptet = fComptetDAO.getF_Comptet(fDocEntete.getDO_Tiers(),fDocEntete.getCtType());
        double montant =0;
        if (valideRegle == 1) {
            if (valideRegltImprime!=1)
                montant = this.montantRegle(fDocEntete);

            FCReglementDAO fCReglementDao = new FCReglementDAO(this.getDataSource());

            if (montantAvance != 0) {
                //$log = new LogFile();
                //$log->user = $_GET["PROT_No"];
                if(Math.round(fDocEntete.getResteAPayer())!=0) {
                    fCReglementDao.addCReglementFacture(cbMarq, montantAvance, fComptet.getCT_Type(), modeReglement, fDocEntete.getCA_No(), dateReglt, libReglt, dateEch, protNo);
                    fComptet = fComptetDAO.getF_Comptet(fDocEntete.getDO_Tiers(), fDocEntete.getCtType());
                }
                //$log->writeReglement("Ajout Règlement",$mtt_avance,$creglement->RG_No,$creglement->RG_Piece,$dr_no,'F_ARTSTOCK',$_GET["PROT_No"]);
            }
        }

        if (valideRegltImprime == 1)
            this.doImprim(cbMarq);
    }

    public Object AjoutMvtStock(String typeFacture,double latitude,double longitude,String doRef,String caNum,String doTiers
            ,String doDate,int deNo, int protNo){
        FDocEntete fDocEntete = new FDocEntete();
        fDocEntete.setTypeFac(typeFacture);
        String entete = getEnteteDocument(typeFacture,0);
        entete = this.getEnteteDispo(entete);
        BigDecimal cbMarq = BigDecimal.ZERO;
        if(!entete.equals("")) {
            fDocEntete.setDefaultValueStock();
            fDocEntete.setLatitude(latitude);
            fDocEntete.setLongitude(longitude);
            fDocEntete.setDO_Ref(doRef);
            fDocEntete.setDO_Tiers(doTiers);
            fDocEntete.setCA_Num(caNum);
            fDocEntete.setDO_Date(doDate);
            fDocEntete.setDE_No(deNo);
            fDocEntete.setDO_Piece(entete);
            fDocEntete.setCbCreateur(String.valueOf(protNo));
            cbMarq = this.insertDocEntete(fDocEntete);
            this.updateEnteteTable(this.getEnteteDispo(fDocEntete.getDO_Piece()),fDocEntete);
        }
        return getFDocEnteteJSon(cbMarq);
    }


    public int getItemCompta(ArrayList<SaisieAnalytique> table,String val){
        int pos=-1;
        for(SaisieAnalytique saisieAnalytique : table){
            if(saisieAnalytique.getCG_NumCont().equals(val))
                return pos+1;
            pos++;
        }
        return -1;
    }

    public List<Object> testCorrectLigneA(BigDecimal cbMarq){
        String sql = FDocEnteteMapper.testCorrectLigneA;
        params = new Object[]{cbMarq};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public ArrayList<SaisieAnalytique> saisieComptable(BigDecimal cbMarq,int trans) {
        FDocEntete fdocEntete = getFDocEntete(cbMarq);
        String jo_num = "";
        String cg_num = "";
        int souche = 0;
        double total_regle = 0;
        String dateEntete = "";
        String reference = "";
        String date_ech = "";
        ArrayList<SaisieAnalytique> alldata = new ArrayList<SaisieAnalytique>();
        String cg_numContrepartie = "";
        int cmpcol = 0;
        int cat_compta = 0;
        String client = "";
        if (fdocEntete.getCbMarq() != null) {
            reference = fdocEntete.getDO_Ref();
            dateEntete = fdocEntete.getDO_Date();
            cat_compta = fdocEntete.getN_CatCompta();
            client = fdocEntete.getDO_Tiers();
            souche = fdocEntete.getDO_Souche();
            String affaire = fdocEntete.getCA_Num();
            total_regle = montantRegle(fdocEntete);
            double resteAregle = 0;
            int ec_statut = 0;
            String ec_lettrage = "";
            if (fdocEntete.getAvance() != 0) {
                resteAregle = fdocEntete.getAvance();
                if (Math.abs(total_regle - resteAregle) < 5) {
                    ec_statut = 2;
                    ec_lettrage = "d";
                }
            } else
                resteAregle = total_regle;
            PSoucheDAO pSoucheDAO = new PSoucheDAO(this.getDataSource());
            ArrayList<PSouche> listPSouche = new ArrayList<PSouche>();
            if (fdocEntete.getDO_Domaine() == 0)
                listPSouche = pSoucheDAO.psoucheListObject("Vente");
            else
                listPSouche = pSoucheDAO.psoucheListObject("Achat");
            for(PSouche pSouche : listPSouche)
                if (pSouche.getCbIndice() == souche)
                    jo_num = pSouche.getJoNum();


            FComptetDAO fComptetDAO = new FComptetDAO(this.getDataSource());

            FComptet fComptet = fComptetDAO.getF_Comptet(client, fdocEntete.getDO_Domaine());
            cg_num = fComptet.getCG_NumPrinc();
            FDocRegl fDocRegl = getDocRegl(fdocEntete);
            String drDate = "";
            if(fDocRegl.getCbMarq()!=null)
                drDate = fDocRegl.getDR_Date();
            date_ech = drDate;
            Calendar dateEntConv = FormatText.getDateYYYYMMDD(dateEntete);
            int annee = dateEntConv.get(Calendar.YEAR);
            int mois = dateEntConv.get(Calendar.MONTH)+1;
            int jour =  dateEntConv.get(Calendar.DAY_OF_MONTH);
            SaisieAnalytique saisieAnalytique = new SaisieAnalytique();
            saisieAnalytique.setJO_Num(jo_num);
            saisieAnalytique.setAnnee_Exercice((annee*100)+mois);
            saisieAnalytique.setEC_Jour(jour);
            saisieAnalytique.setEC_RefPiece(fdocEntete.getDO_Piece());
            saisieAnalytique.setEC_Reference(reference);
            saisieAnalytique.setCG_Num(cg_num);
            saisieAnalytique.setEC_Sens(0);
            saisieAnalytique.setEC_Lettrage(ec_lettrage);
            saisieAnalytique.setEC_StatusRegle(ec_statut);
            saisieAnalytique.setEC_MontantRegle(resteAregle);
            saisieAnalytique.setTA_Provenance(0);
            saisieAnalytique.setCG_NumCont(cg_num);
            saisieAnalytique.setCT_Num(client);
            saisieAnalytique.setEC_Intitule(fdocEntete.getDO_Piece() + "_" + reference);
            saisieAnalytique.setEC_Echeance(date_ech);
            saisieAnalytique.setEC_MontantCredit(total_regle);
            saisieAnalytique.setCbMarqList(String.valueOf(cbMarq));
            if (trans == 0) {
                alldata.set(cmpcol, saisieAnalytique);
                cmpcol++;
            }
            FJournauxDAO fJournauxDAO = new FJournauxDAO(this.getDataSource());
            FJournaux fJournaux = fJournauxDAO.getJournalObject(jo_num);
            if(fJournaux.getCbMarq()!=null)
            if (fJournaux.getJO_Contrepartie() == 1)
                cg_numContrepartie = fJournaux.getCG_Num();
            FDocLigneDAO fDocLigneDAO = new FDocLigneDAO(this.getDataSource());
            FArticleDAO fArticleDAO = new FArticleDAO(this.getDataSource());
            ArrayList<BigDecimal> rows = getLigneFactureCbMarq(fdocEntete.getCbMarq());
            for (BigDecimal row : rows) {
                FDocLigne fDocLigne = fDocLigneDAO.getFDocLigne(row);
                double MT_Taxe1 = fDocLigne.getMT_Taxe1();
                double MT_Taxe2 = fDocLigne.getMT_Taxe2();
                double MT_Taxe3 = fDocLigne.getMT_Taxe3();
                cbMarq = fDocLigne.getCbMarq();
                if(!fDocLigne.getDL_NoColis().equals(""))
                    cat_compta =Integer.valueOf(fDocLigne.getDL_NoColis());
                FArticle fArticle = fArticleDAO.getF_Article(fDocLigne.getAR_Ref());
                ArrayList<TaxeArticle> listTaxeArticle = fArticleDAO.getTaxeArticleObject(cat_compta,1,fDocLigne.getAR_Ref());
                TaxeArticle taxeArticle = listTaxeArticle.get(0);
                int pos =-1;
                if(taxeArticle.getCOMPTEG_ARTICLE()!=null)
                    pos = getItemCompta(alldata,taxeArticle.getCOMPTEG_ARTICLE());
                double montantDeb = fDocLigne.getDL_MontantHT();
                if(trans==1)
                    montantDeb = fDocLigne.getDL_MontantTTC();
                else
                    date_ech = "";
                if(pos==-1){
                    String client_art="";
                    if(taxeArticle.getCG_TiersArticle()!=null){
                        if(taxeArticle.getCG_TiersArticle().equals("1"))
                            client_art = client;
                        saisieAnalytique = new SaisieAnalytique();
                        saisieAnalytique.setJO_Num(jo_num);
                        dateEntConv = FormatText.getDateYYYYMMDD(dateEntete);
                        annee = dateEntConv.get(Calendar.YEAR);
                        mois = dateEntConv.get(Calendar.MONTH)+1;
                        jour =  dateEntConv.get(Calendar.DAY_OF_MONTH);
                        saisieAnalytique.setAnnee_Exercice((annee*100) + mois);
                        saisieAnalytique.setEC_Jour(jour);
                        saisieAnalytique.setEC_RefPiece(fdocEntete.getDO_Piece());
                        saisieAnalytique.setEC_Reference(reference);
                        saisieAnalytique.setCG_Num(taxeArticle.getCOMPTEG_ARTICLE());
                        saisieAnalytique.setEC_Sens(1);
                        saisieAnalytique.setEC_StatusRegle(0);
                        saisieAnalytique.setEC_MontantRegle(0);
                        saisieAnalytique.setTA_Provenance(1);
                        saisieAnalytique.setCG_NumCont(cg_num);
                        saisieAnalytique.setCT_Num(client);
                        saisieAnalytique.setEC_Intitule(fdocEntete.getDO_Piece() + "_" + reference);
                        saisieAnalytique.setN_Reglement(0);
                        saisieAnalytique.setEC_Echeance(date_ech);
                        saisieAnalytique.setEC_MontantDebit(montantDeb);
                        saisieAnalytique.setTA_Code(taxeArticle.getCodeTaxe1());
                        saisieAnalytique.setCbMarqList(String.valueOf(cbMarq));
                        alldata.add(/*cmpcol, */saisieAnalytique);
                        cmpcol++;
                    }
                    alldata.get(0).setCG_NumCont(taxeArticle.getCOMPTEG_ARTICLE());
                }
                else{
                    double alldataPos = 0;
                    if(alldata.get(pos).getEC_MontantDebit()!=0)
                        alldataPos = alldata.get(pos).getEC_MontantDebit();
                    alldata.get(pos).setEC_MontantDebit(alldataPos + montantDeb);
                    alldata.get(pos).setCbMarqList(alldata.get(pos).getCbMarqList()+","+cbMarq);
                }

                for (TaxeArticle taxeArticleElt : listTaxeArticle) {
                    if(taxeArticleElt.getCodeTaxe1()!=null)
                    if(!taxeArticleElt.getCodeTaxe1().equals("")){
                        pos = getItemCompta(alldata,taxeArticleElt.getCG_NumTaxe1());
                        if(pos==-1) {
                            if(trans==0){
                                String client_taxe="";
                                if(taxeArticle.getCG_Tiers1().equals("1"))
                                    client_taxe = client;
                                dateEntConv = FormatText.getDateYYYYMMDD(dateEntete);
                                annee = dateEntConv.get(Calendar.YEAR);
                                mois = dateEntConv.get(Calendar.MONTH)+1;
                                jour =  dateEntConv.get(Calendar.DAY_OF_MONTH);
                                saisieAnalytique = new SaisieAnalytique();
                                saisieAnalytique.setJO_Num(jo_num);
                                saisieAnalytique.setAnnee_Exercice((annee*100)+mois);
                                saisieAnalytique.setEC_Jour(jour);
                                saisieAnalytique.setEC_RefPiece(fdocEntete.getDO_Piece());
                                saisieAnalytique.setEC_Reference(reference);
                                saisieAnalytique.setCG_Num(taxeArticleElt.getCG_NumTaxe1());
                                saisieAnalytique.setEC_Sens(1);
                                saisieAnalytique.setEC_StatusRegle(0);
                                saisieAnalytique.setEC_MontantRegle(0);
                                saisieAnalytique.setTA_Provenance(1);
                                saisieAnalytique.setCG_NumCont(cg_num);
                                saisieAnalytique.setCT_Num("");
                                saisieAnalytique.setEC_Intitule(fdocEntete.getDO_Piece() + "_" + reference);
                                saisieAnalytique.setN_Reglement(0);
                                saisieAnalytique.setEC_MontantDebit(MT_Taxe1);
                                saisieAnalytique.setTA_Code(taxeArticleElt.getCodeTaxe1());
                                alldata.add(/*cmpcol,*/ saisieAnalytique);
                                cmpcol++;
                            }
                        }else{
                            alldata.get(pos).setEC_MontantDebit(alldata.get(pos).getEC_MontantDebit()+Math.round(MT_Taxe1));
                        }
                    }
                    if(taxeArticleElt.getCodeTaxe2()!=null)
                    if(!taxeArticleElt.getCodeTaxe2().equals("")){
                        pos = getItemCompta(alldata,taxeArticleElt.getCG_NumTaxe1());
                        if(pos==-1) {
                            if(trans==0){
                                String client_taxe="";
                                if(taxeArticle.getCG_Tiers2().equals("1"))
                                    client_taxe = client;
                                saisieAnalytique = new SaisieAnalytique();
                                saisieAnalytique.setJO_Num(jo_num);
                                dateEntConv = FormatText.getDateYYYYMMDD(dateEntete);
                                annee = dateEntConv.get(Calendar.YEAR);
                                mois = dateEntConv.get(Calendar.MONTH)+1;
                                jour = dateEntConv.get(Calendar.DAY_OF_MONTH);

                                saisieAnalytique.setAnnee_Exercice((annee*100)+mois);
                                saisieAnalytique.setEC_Jour(jour);
                                saisieAnalytique.setEC_RefPiece(fdocEntete.getDO_Piece());
                                saisieAnalytique.setEC_Reference(reference);
                                saisieAnalytique.setCG_Num(taxeArticleElt.getCG_NumTaxe2());
                                saisieAnalytique.setEC_Sens(1);
                                saisieAnalytique.setEC_StatusRegle(0);
                                saisieAnalytique.setEC_MontantRegle(0);
                                saisieAnalytique.setTA_Provenance(1);
                                saisieAnalytique.setCG_NumCont(cg_num);
                                saisieAnalytique.setCT_Num("");
                                saisieAnalytique.setEC_Intitule(fdocEntete.getDO_Piece() + "_" + reference);
                                saisieAnalytique.setN_Reglement(0);
                                saisieAnalytique.setEC_MontantDebit(MT_Taxe2);
                                saisieAnalytique.setTA_Code(taxeArticleElt.getCodeTaxe2());
                                alldata.add(/*cmpcol,*/ saisieAnalytique);
                                cmpcol++;
                            }
                        }else{
                            alldata.get(pos).setEC_MontantDebit(alldata.get(pos).getEC_MontantDebit()+Math.round(MT_Taxe2));
                        }
                    }
                    if(taxeArticleElt.getCodeTaxe3()!=null)
                        if(!taxeArticleElt.getCodeTaxe3().equals("")){
                        pos = getItemCompta(alldata,taxeArticleElt.getCG_NumTaxe1());
                        if(pos==-1) {
                            if(trans==0){
                                String client_taxe="";
                                if(taxeArticle.getCG_Tiers3().equals("1"))
                                    client_taxe = client;

                                saisieAnalytique = new SaisieAnalytique();
                                saisieAnalytique.setJO_Num(jo_num);
                                dateEntConv = FormatText.getDateYYYYMMDD(dateEntete);
                                annee = dateEntConv.get(Calendar.YEAR);
                                mois = dateEntConv.get(Calendar.MONTH)+1;
                                jour =  dateEntConv.get(Calendar.DAY_OF_MONTH);
                                saisieAnalytique.setAnnee_Exercice((annee*100)+mois);
                                saisieAnalytique.setEC_Jour(jour);
                                saisieAnalytique.setEC_RefPiece(fdocEntete.getDO_Piece());
                                saisieAnalytique.setEC_Reference(reference);
                                saisieAnalytique.setCG_Num(taxeArticleElt.getCG_NumTaxe3());
                                saisieAnalytique.setEC_Sens(1);
                                saisieAnalytique.setEC_StatusRegle(0);
                                saisieAnalytique.setEC_MontantRegle(0);
                                saisieAnalytique.setTA_Provenance(1);
                                saisieAnalytique.setCG_NumCont(cg_num);
                                saisieAnalytique.setCT_Num("");
                                saisieAnalytique.setEC_Intitule(fdocEntete.getDO_Piece() + "_" + reference);
                                saisieAnalytique.setN_Reglement(0);
                                saisieAnalytique.setEC_MontantDebit(MT_Taxe3);
                                saisieAnalytique.setTA_Code(taxeArticleElt.getCodeTaxe3());
                                alldata.add(/*cmpcol,*/ saisieAnalytique);
                                cmpcol++;
                            }
                        }else{
                            alldata.get(pos).setEC_MontantDebit(alldata.get(pos).getEC_MontantDebit()+Math.round(MT_Taxe3));
                        }
                    }

                }
            }
        }
        return alldata;
    }

    public ArrayList<FEcritureA> saisieCompteAnal(BigDecimal cbMarq,int insert){
        FDocEntete fDocEntete = getFDocEntete(cbMarq);
        ArrayList<FEcritureA> listFEcritureA = new ArrayList<FEcritureA>();
        PSoucheDAO pSoucheDAO = new PSoucheDAO(this.getDataSource());
        ArrayList<PSouche> listPSouche = new ArrayList<PSouche>();
        listPSouche = pSoucheDAO.psoucheListObject("Achat");
        String joNum ="";
        for(PSouche pSouche : listPSouche)
            if (pSouche.getCbIndice() == fDocEntete.getDO_Souche())
                joNum = pSouche.getJoNum();
        FJournauxDAO fJournauxDAO = new FJournauxDAO(this.getDataSource());
        FJournaux fJournaux = fJournauxDAO.getJournalObject(joNum);
        if(fJournaux.getJO_SaisAnal()==1) {
            for(BigDecimal cbMarqLigne : getListeFDocLigne(fDocEntete.getCbMarq())) {
                FDocLigneDAO fDocLigneDAO = new FDocLigneDAO(this.getDataSource());
                FDocLigne fDocLigne = fDocLigneDAO.getFDocLigne(cbMarqLigne);
                BigDecimal ecNo = BigDecimal.ZERO;
                int dlNoColis =1;
                if(insert==1)
                    ecNo = cbMarqLigne;
                if(!fDocLigne.getDL_NoColis().equals(""))
                    dlNoColis = Integer.valueOf(fDocLigne.getDL_NoColis());
                FArticleDAO fArticleDAO = new FArticleDAO(this.getDataSource());
                ArrayList<TaxeArticle> listTaxeArticle = fArticleDAO.getTaxeArticleObject(dlNoColis,1,fDocLigne.getAR_Ref());
                TaxeArticle taxeArticle = listTaxeArticle.get(0);
                String cgNum = "";
                if(taxeArticle.getCOMPTEG_ARTICLE()!=null)
                    cgNum = taxeArticle.getCOMPTEG_ARTICLE();
                FCompteADAO fCompteADAO = new FCompteADAO(this.getDataSource());
                Calendar dateEntConv = FormatText.getDateYYYYMMDD(fDocEntete.getDO_Date());
                int annee =dateEntConv.get(Calendar.YEAR);
                int mois = dateEntConv.get(Calendar.MONTH)+1;
                int jour =  dateEntConv.get(Calendar.DAY_OF_MONTH);
                SqlRowSet sqlNext = fCompteADAO.getLigneCompteA(cbMarqLigne);
                int cmp = 0;
                while(sqlNext.next()) {
                    cmp++;
                    FEcritureA fEcritureA = new FEcritureA();
                    dateEntConv = FormatText.getDateYYYYMMDD(fDocEntete.getDO_Date());
                    annee = dateEntConv.get(Calendar.YEAR);
                    mois = dateEntConv.get(Calendar.MONTH)+1;
                    jour =  dateEntConv.get(Calendar.DAY_OF_MONTH);
                    fEcritureA.setJO_Num(joNum);
                    fEcritureA.setAnneExercice((annee*100)+mois);
                    fEcritureA.setCA_Num(sqlNext.getString("CA_Num"));
                    fEcritureA.setCG_Num(cgNum);
                    fEcritureA.setA_Intitule(sqlNext.getString("A_Intitule"));
                    fEcritureA.setEA_Quantite(sqlNext.getDouble("EA_Quantite"));
                    fEcritureA.setEA_Montant(sqlNext.getDouble("EA_Montant"));
                    fEcritureA.setEC_No(ecNo);
                    fEcritureA.setN_Analytique(sqlNext.getInt("N_Analytique"));
                    listFEcritureA.add(fEcritureA);
                }
                if(cmp==0) {
                    FEcritureA fEcritureA = new FEcritureA();
                    dateEntConv = FormatText.getDateYYYYMMDD(fDocEntete.getDO_Date());
                    annee = dateEntConv.get(Calendar.YEAR);
                    mois = dateEntConv.get(Calendar.MONTH)+1;
                    jour =  dateEntConv.get(Calendar.DAY_OF_MONTH);
                    fEcritureA.setAnneExercice((annee*100)+mois);
                    fEcritureA.setJO_Num(joNum);
                    fEcritureA.setCA_Num(fDocLigne.getCA_Num());
                    fEcritureA.setCG_Num("");
                    fEcritureA.setA_Intitule("");
                    fEcritureA.setEA_Quantite(fDocLigne.getDL_Qte());
                    fEcritureA.setEA_Montant(fDocLigne.getDL_MontantHT());
                    fEcritureA.setEC_No(ecNo);
                    fEcritureA.setN_Analytique(0);
                    fEcritureA.setCbMarq(ecNo);
                    listFEcritureA.add(fEcritureA);
                }
                else cmp=0;
            }
        }
        System.out.println(listFEcritureA);
        return listFEcritureA;
    }
    public ArrayList<SaisieAnalytique> saisieComptableCaisse(BigDecimal cbMarq, int trans){
        FCaisseDAO fCaisseDAO = new FCaisseDAO(this.getDataSource());
        FComptetDAO fComptetDAO = new FComptetDAO(this.getDataSource());
        FDocEntete fDocEntete = getFDocEntete(cbMarq);
        FCaisse fCaisse = fCaisseDAO.getF_Caisse(fDocEntete.getCA_No());
        FComptet fComptet = fComptetDAO.getF_Comptet(fDocEntete.getDO_Tiers(),fDocEntete.getDO_Domaine());
        String client = fDocEntete.getDO_Tiers();
        String cgNum = "";
        if(!fComptet.getCG_NumPrinc().equals("")){
            cgNum = fComptet.getCG_NumPrinc();
            FComptegDAO fComptegDAO = new FComptegDAO(this.getDataSource());
            FCompteg fCompteg = fComptegDAO.getCompteG(fComptet.getCG_NumPrinc());
            if(fCompteg.getCG_Tiers()==0)
                client="";
        }
        FDocRegl fDocRegl = getDocRegl(fDocEntete);
        SaisieAnalytique saisieAnalytique = new SaisieAnalytique();
        Calendar dateEntConv =null;

        int annee = 0;
        int mois = 0;
        int jour =  0;
        ArrayList<SaisieAnalytique> alldata = new ArrayList<SaisieAnalytique>();
        if(trans==0){
            dateEntConv = FormatText.getDateYYYYMMDD(fDocEntete.getDO_Date());
            annee = dateEntConv.get(Calendar.YEAR);
            mois = dateEntConv.get(Calendar.MONTH)+1;
            jour =  dateEntConv.get(Calendar.DAY_OF_MONTH);
            saisieAnalytique = new SaisieAnalytique();
            saisieAnalytique.setJO_Num(fCaisse.getJO_Num());
            saisieAnalytique.setAnnee_Exercice((annee*100)+mois);
            saisieAnalytique.setEC_Jour(jour);
            saisieAnalytique.setEC_RefPiece(fDocEntete.getDO_Piece());
            saisieAnalytique.setEC_Reference(fDocEntete.getDO_Ref());
            saisieAnalytique.setCG_Num(cgNum);
            saisieAnalytique.setEC_Sens(1);
            saisieAnalytique.setEC_StatusRegle(0);
            saisieAnalytique.setEC_MontantRegle(0);
            saisieAnalytique.setTA_Provenance(0);
            saisieAnalytique.setEC_Echeance(fDocRegl.getDR_Date());
            saisieAnalytique.setCG_NumCont("");
            saisieAnalytique.setCT_Num(client);
            saisieAnalytique.setEC_Intitule("Rglt "+fDocEntete.getDO_Piece()+"_"+fDocEntete.getDO_Ref());
            saisieAnalytique.setN_Reglement(1);
            saisieAnalytique.setEC_MontantDebit(fDocEntete.getAvance());
            saisieAnalytique.setEC_MontantCredit(0);
            saisieAnalytique.setTA_Code("");
            alldata.add(saisieAnalytique);
        }

        String cgNumOld = cgNum;
        String ctNumOld = client;
        FJournauxDAO fJournauxDAO = new FJournauxDAO(this.getDataSource());
        FJournaux fJournaux = fJournauxDAO.getJournalObject(fCaisse.getJO_Num());
        if(!fJournaux.getCG_Num().equals("")){
            cgNum=fJournaux.getCG_Num();
            FComptegDAO fComptegDAO = new FComptegDAO(this.getDataSource());
            FCompteg fCompteg = fComptegDAO.getCompteG(fComptet.getCG_NumPrinc());
            if(fCompteg.getCG_Tiers()==0)
                client="";
        }
        dateEntConv = FormatText.getDateYYYYMMDD(fDocEntete.getDO_Date());
        annee = dateEntConv.get(Calendar.YEAR);
        mois = dateEntConv.get(Calendar.MONTH)+1;
        jour =  dateEntConv.get(Calendar.DAY_OF_MONTH);
        saisieAnalytique = new SaisieAnalytique();
        saisieAnalytique.setJO_Num(fCaisse.getJO_Num());
        saisieAnalytique.setAnnee_Exercice((annee*100)+mois);
        saisieAnalytique.setEC_Jour(jour);
        saisieAnalytique.setEC_Echeance(fDocRegl.getDR_Date());
        saisieAnalytique.setEC_RefPiece(fDocEntete.getDO_Piece());
        saisieAnalytique.setEC_Reference(fDocEntete.getDO_Ref());
        saisieAnalytique.setCG_Num(cgNum);
        saisieAnalytique.setEC_Sens(0);
        saisieAnalytique.setEC_StatusRegle(0);
        saisieAnalytique.setEC_MontantRegle(0);
        saisieAnalytique.setTA_Provenance(0);
        saisieAnalytique.setCG_NumCont("");
        saisieAnalytique.setCT_Num(client);
        saisieAnalytique.setEC_Intitule("Rglt "+fDocEntete.getDO_Piece()+"_"+fDocEntete.getDO_Ref());
        saisieAnalytique.setN_Reglement(1);
        saisieAnalytique.setEC_MontantDebit(0);
        saisieAnalytique.setEC_MontantCredit(fDocEntete.getAvance());
        saisieAnalytique.setTA_Code("");
        alldata.add(saisieAnalytique);
        return alldata;
    }

    public String canTransform(BigDecimal cbMarq)
    {

        ArrayList<BigDecimal> listeLigne = getListeFDocLigne(cbMarq);
        FDocLigneDAO docLigneDAO = new FDocLigneDAO(this.getDataSource());
        FArtStockDAO fArtStockDAO = new FArtStockDAO(this.getDataSource());
        int isTransform = 0;
        String listeArticle ="";
        for (BigDecimal id : listeLigne) {
            FDocLigne docLigne = docLigneDAO.getFDocLigne(id);
            FArtStock fArtStock = fArtStockDAO.isStock(docLigne.getAR_Ref(),docLigne.getDE_No());
            double qteStock = 0;
            if (fArtStock.getCbMarq()!=null)
                qteStock = fArtStock.getAS_QteSto();
            if (Math.round(qteStock*100)/100 - Math.round(docLigne.getDL_Qte()*100)/100 >= 0) {

            } else{
                listeArticle = listeArticle+docLigne.getAR_Ref()+", ";
                isTransform = 1;
            }
        }
        if(isTransform==1)
            return listeArticle;
        return null;
    }

    public Object transBLFacture(BigDecimal cbMarq,int conserv,int typeTrans,String reference,String date,int protNo){
        BigDecimal cbMarqBL=cbMarq;
        FDocEntete docEnteteBL= getFDocEntete(cbMarqBL);
        String type = docEnteteBL.getTypeFacture();
        int doDomaine = 0;
        int doType = 3;
        String dateBl="";
        String typeRes="Vente";
        if(typeTrans==3)
            typeRes="BonLivraison";
        if(type.equals("Devis"))
            doType=0;
        String resultat="";
        BigDecimal enteteCbMarq = BigDecimal.ZERO;
        int deNo = 0;

        FDocLigneDAO fDocLigneDAO = new FDocLigneDAO(this.getDataSource());
        FArtStockDAO fArtStockDAO = new FArtStockDAO(this.getDataSource());
        String listeArticle = canTransform(docEnteteBL.getCbMarq());
        if(!type.equals("Devis") || listeArticle == null) {
            String dateIns = docEnteteBL.getDO_Date();
            dateBl = docEnteteBL.getDO_Date();
            String enteteBl = docEnteteBL.getDO_Piece();
            deNo = docEnteteBL.getDE_No();
            if (!date.equals("")) {
                dateIns = "20" + date.substring(-2) + "-" + date.substring(2, 2) + "-" + date.substring(0, 2);
            }
            String refIns = docEnteteBL.getDO_Ref();
            if (!reference.equals(""))
                refIns = reference;
            double latitude = docEnteteBL.getLatitude();
            double longitude = docEnteteBL.getLongitude();

            FDocEntete fDocentete = (FDocEntete)ajoutEntete(protNo, "", type, dateIns, docEnteteBL.getDO_Souche(), dateIns, ""
                    , "", "", "", docEnteteBL.getDO_Coord02(), docEnteteBL.getDO_Coord03(),
                    docEnteteBL.getDO_Coord04(), docEnteteBL.getDO_Statut(), docEnteteBL.getDO_Tarif(), docEnteteBL.getN_CatCompta()
                    , docEnteteBL.getDE_No(), docEnteteBL.getCA_No(), docEnteteBL.getCO_No(), reference
                    , longitude, latitude);
            enteteCbMarq = fDocentete.getCbMarq();
            ArrayList<BigDecimal> rows = getListeFDocLigne(enteteCbMarq);
            FDocLigne fDocLigneRes = new FDocLigne();
            for(BigDecimal elt : rows) {
                FDocLigne fDocLigne = fDocLigneDAO.getFDocLigne(elt);
                int typeHT = fDocLigne.getDL_TTC();
                FArtStock fArtStock = fArtStockDAO.isStock(fDocLigne.getAR_Ref(),docEnteteBL.getDE_No());
                double qteStock = 0;
                if (fArtStock.getAS_QteSto() != 0)
                    qteStock = fArtStock.getAS_QteSto();
                if ((Math.round(qteStock*100)/100 - Math.round(fDocLigne.getDL_Qte()*100)/100 >= 0) || !type.equals("Devis")) {
                    double AR_PrixAch = fDocLigne.getDL_CMUP();
                    if (conserv == 0){
                        fDocLigneDAO.deleteLigne(fDocLigne.getCbMarq());
                    }
                    double prix = fDocLigne.getDL_PrixUnitaire();
                    if (fDocLigne.getDL_TTC() == 1)
                        prix = fDocLigne.getDL_PUTTC();
                    if(!type.equals("Devis"))
                        fArtStockDAO.updateFArtstock(fDocLigne.getAR_Ref(),docEnteteBL.getDE_No(),(Math.round(fDocLigne.getDL_CMUP()*100)/100) * fDocLigne.getDL_Qte(),fDocLigne.getDL_Qte(),"insertLigne",String.valueOf(protNo));
                    if (conserv == 0){
                        fDocLigneDAO.ajoutLigne(fDocLigne.getCbMarq(),protNo,fDocLigne.getDL_Qte(),fDocLigne.getAR_Ref()
                                ,enteteCbMarq,type,0,prix,"","","ajout_ligne","");
                        fDocentete = getFDocEntete(enteteCbMarq);
                        fDocLigneDAO.maj("DL_PieceBL",enteteBl,fDocLigne.getCbMarq(),String.valueOf(protNo));
                        fDocLigneDAO.maj("DL_DateBL",fDocLigne.getDL_DateBL(),fDocLigne.getCbMarq(),String.valueOf(protNo));
                    }
                    else
                        fDocLigneRes = (FDocLigne) fDocLigneDAO.ajoutLigne(BigDecimal.ZERO,protNo,fDocLigne.getDL_Qte(),fDocLigne.getAR_Ref(),enteteCbMarq,type,0
                                ,fDocLigne.getDL_PrixUnitaire(),"","","ajout_ligne","");
                    fDocLigneDAO.maj("DL_DateBL",dateBl,fDocLigneRes.getCbMarq(),String.valueOf(protNo));
                    fDocLigneDAO.maj("DL_DateBC",dateBl,fDocLigneRes.getCbMarq(),String.valueOf(protNo));
                }
                else
                    resultat = resultat + " " + fDocLigne.getAR_Ref();
            }

            deleteEntete(docEnteteBL.getCbMarq());
        }

        if(type.equals("Devis"))
            return listeArticle;
        return null;
    }

    public void deleteEntete(BigDecimal cbMarq){
        String sql = FDocEnteteMapper.deleteEntete;
        params = new Object[] {cbMarq};
        this.getJdbcTemplate().update(sql, params, mapper);
    }

    public void majComptaFonction(String datedeb,String datefin,String doPieceDeb,String doPieceFin,int doSouche,int catCompta,int caisse,int typeTransfert,int etatPiece){
        ArrayList<BigDecimal> list = getListeFactureMajComptable(datedeb,datefin,doPieceDeb,doPieceFin,doSouche,catCompta,caisse,typeTransfert,etatPiece);
        for (BigDecimal cbMarq : list) {
            FDocEntete fDocEntete = getFDocEntete(cbMarq);
            int doTypeCible = 7;
            if (fDocEntete.getDO_Domaine() == 1)
                doTypeCible = 17;
            majEnteteComptable(fDocEntete.getDO_Type(),fDocEntete.getDO_Domaine(),fDocEntete.getDO_Piece(),doTypeCible);
        }
    }
    public ArrayList<BigDecimal> getListeFactureMajComptable(String datedeb,String datefin,String doPieceDeb,String doPieceFin,int doSouche,int catCompta,int caisse,int typeTransfert,int etatPiece){
        String sql = FDocEnteteMapper.getListeFactureMajComptable;
        FCReglementDAO fcReglementDAO = new FCReglementDAO(this.getDataSource());
        params = new Object[] {datedeb,datefin,doPieceDeb,doPieceFin,doSouche,catCompta,caisse,typeTransfert,etatPiece};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        ArrayList<BigDecimal> listeReglt = new ArrayList<BigDecimal>();
        while(sqlRowSet.next()) {
            listeReglt.add(sqlRowSet.getBigDecimal("cbMarq"));
        }
        if(typeTransfert==3 || typeTransfert==4) {
            listeReglt = fcReglementDAO.getListeReglementMajComptable(datedeb,datefin,etatPiece,typeTransfert,caisse);

        }

        return listeReglt;
    }

    public Object ajoutEntete(int protNo, String do_pieceG, String typeFacture, String doDate, int doSouche, String doDateEch, String affaireG, String client, String machineName
            , String doCoord1, String doCoord2, String doCoord3, String doCoord4, int doStatut, int catTarif, int catCompta, int deNo, int caNo, int coNo, String reference, double longitude, double latitude) {
        fDocEntete.setTypeFac(typeFacture);
        fDocEntete.defaultValue();
        FProtectioncialDAO fProtectioncialDao = new FProtectioncialDAO(this.getDataSource());
        FProtectioncial fProtectioncial = fProtectioncialDao.connexionProctectionByProtNo(protNo);
        Boolean isFacture = true;

        if(fProtectioncial.getPROT_Right()!=1) {
            int delai = this.getDelai();
            if (delai != 0) {
                Date dateLimiteMoinsDate = new Date();
                Calendar cMoins = Calendar.getInstance();
                cMoins.setTime(dateLimiteMoinsDate);
                cMoins.add(Calendar.DATE, -delai);
                dateLimiteMoinsDate = cMoins.getTime();

                Date dateLimitePlusDate = new Date();
                Calendar cPlus = Calendar.getInstance();
                cPlus.setTime(dateLimitePlusDate);
                cPlus.add(Calendar.DATE, delai);
                dateLimitePlusDate = cPlus.getTime();

                Date dateFacture = new Date(doDate);
                if (dateFacture.compareTo(dateLimiteMoinsDate) >= 0 && dateFacture.compareTo(dateLimitePlusDate) <= 0)
                    isFacture = false;
            }
        }
        int cloture = this.journeeCloture(doDate,caNo);

        if(isFacture && ((!typeFacture.equals("Devis") && !typeFacture.equals("AchatPreparationCommande") && cloture == 0)
                || typeFacture.equals("Devis") || typeFacture.equals("AchatPreparationCommande"))) {
            net.minidev.json.JSONObject controleClient = null;

            PParametreLivrDAO pParametreLivrDAO = new PParametreLivrDAO(this.getDataSource());
            PParametreLivr pparametreLivr = pParametreLivrDAO.getpParametreLivrObject(1);
            if (pparametreLivr.getPL_Reliquat() == 2 && !fDocEntete.getTypeFacture().equals("Devis")) {
                FComptetDAO fComptetDAO = new FComptetDAO(this.getDataSource());
                FComptet fComptet = fComptetDAO.getF_Comptet(fDocEntete.getDO_Tiers(), fDocEntete.getCtType());
                controleClient = fComptetDAO.controleEncours(fComptet, fDocEntete.getTypeFacture(), 0, "", Double.valueOf(fDocEntete.getTtc()));
            }

            if (controleClient != null)
                return controleClient;

            String DO_Coord01 = (doCoord1 != null) ? doCoord1 : "";
            String DO_Coord02 = (doCoord2 != null) ? doCoord2 : "";
            String DO_Coord03 = (doCoord3 != null) ? doCoord3 : "";
            String DO_Coord04 = (doCoord4 != null) ? doCoord4 : "";
            String do_piece = (do_pieceG != null) ? do_pieceG : "";
            String affaire = (affaireG.equals("null") || affaireG.equals("0") || affaireG.equals("") || affaireG.equals(" ")) ? null : affaireG;
            String date_ech = (doDateEch == null || doDateEch.equals("")) ? doDate : doDateEch;
            String machine = (machineName != null) ? machineName : "";
            List<Object> list = null;
            if (do_piece.equals(""))
                do_piece = this.getEnteteDocument(typeFacture, doSouche);
            do_piece = this.getEnteteDispo(do_piece);


            if (fDocEntete.getDO_Domaine() == 0 || fDocEntete.getDO_Domaine() == 1) {
                FComptetDAO fComptetDAO = new FComptetDAO(this.getDataSource());
                int ctType = 0;
                if (fDocEntete.getDO_Domaine() == 1)
                    ctType = 1;


                FComptet fComptet = fComptetDAO.getF_Comptet(client, ctType);

                if (fComptet.getMR_No() != 0) {
                    list = fComptetDAO.getOptionModeleReglementByMRNo(fComptet.getMR_No());
                    FEModeleRDAO fEModeleRDAO = new FEModeleRDAO(this.getDataSource());
                    FEModeleR modeleR = fEModeleRDAO.findByMRNo(1);
                    if (list.size() > 0) {
                        int nbjour = modeleR.getER_NbJour();
                        int jour = modeleR.getER_JourTb01();
                        int condition = modeleR.getER_Condition();
                        date_ech = getCalculDateEcheance(condition, nbjour, jour, date_ech);
                    }
                }

                FCaisseDAO fCaisseDAO = new FCaisseDAO(this.getDataSource());
                FCaisse fCaisse = (fCaisseDAO).getF_Caisse(caNo);
                int liNo = 0;
                if (fDocEntete.getDO_Domaine() == 0)
                    liNo = fComptetDAO.getFLivraisonByCTNum(client);
                fDocEntete.setDefaultValueVente(fComptet);
                fDocEntete.setDefaultValueAchat(fComptet);
                fDocEntete.setInfoAjoutEntete();
                fDocEntete.setLI_No(liNo);
                fDocEntete.setCO_NoCaissier(fCaisse.getCO_NoCaissier());
            }
            fDocEntete.setDO_Tiers(client);
            if (typeFacture.equals("Entree") || typeFacture.equals("Sortie"))
                fDocEntete.setDO_Tiers(String.valueOf(deNo));
            fDocEntete.setDO_Date(doDate);
            fDocEntete.setDO_Ref(reference);
            fDocEntete.setCO_No(coNo);
            fDocEntete.setDE_No(deNo);
            if (typeFacture.equals("Entree") || typeFacture.equals("Sortie"))
                fDocEntete.setDE_No(0);

            fDocEntete.setCA_Num(affaire);
            fDocEntete.setDO_Souche(doSouche);
            fDocEntete.setN_CatCompta(catCompta);
            fDocEntete.setCA_No(caNo);
            fDocEntete.setDO_Tarif(catTarif);
            fDocEntete.setLongitude(longitude);
            fDocEntete.setLatitude(latitude);
            fDocEntete.setDO_Piece(do_piece);
            fDocEntete.setDO_Statut(doStatut);
            fDocEntete.setDO_Coord02(doCoord2);
            fDocEntete.setDO_Coord03(doCoord3);
            fDocEntete.setDO_Coord04(doCoord4);
            fDocEntete.setCbCreateur(String.valueOf(protNo));

            if (fDocEntete.getDO_Domaine() != 0 && fDocEntete.getDO_Domaine() != 1) {
                if (fDocEntete.getTypeFacture().equals("Entree") || fDocEntete.getTypeFacture().equals("Sortie"))
                    fDocEntete.setDO_DateLivr("1900-01-01");
                fDocEntete.setValueMvt();
            }
            BigDecimal cbMarqInsert = insertDocEntete(fDocEntete);
            this.updateEnteteTable(this.getEnteteDispo(fDocEntete.getDO_Piece()), fDocEntete);
            if (fDocEntete.getDO_Domaine() == 0 || fDocEntete.getDO_Domaine() == 1 || fDocEntete.getDO_Domaine() == 3) {
                FDocReglDAO fDocReglDAO = new FDocReglDAO(getDataSource());
                FDocRegl fDocRegl = new FDocRegl();
                fDocRegl.setDO_Domaine(fDocEntete.getDO_Domaine());
                fDocRegl.setDO_Type(fDocEntete.getDO_Type());
                fDocRegl.setDO_Piece(fDocEntete.getDO_Piece());
                fDocRegl.setDR_Regle(0);
                fDocRegl.setN_Reglement(1);
                fDocRegl.setDR_Date(date_ech);
                fDocRegl.setCbCreateur(String.valueOf(protNo));
                fDocReglDAO.ajoutDocRegl(fDocRegl);
            }
            return (List<Object>) getFDocEnteteJSon(cbMarqInsert);
        }
        return null;
    }

    public void removeFacRglt(BigDecimal cbMarqEntete,BigDecimal rgNo){
        FCReglementDAO fcReglementDAO = new FCReglementDAO(this.getDataSource());
        FDocReglDAO fDocReglDAO = new FDocReglDAO(this.getDataSource());
        FCReglement fcReglement = fcReglementDAO.getFCReglement(rgNo);
        fDocReglDAO.UpdateDrRegleByRgNo(cbMarqEntete,rgNo);
        fDocReglDAO.deleteReglEchByRgNo(cbMarqEntete,rgNo);
        if(fcReglement.getCbMarq()!=null)
            fcReglementDAO.supprRgltAssocie(rgNo);
    }

    public int getZFactReglSuppr(BigDecimal cbMarq){
        String sql = FDocEnteteMapper.getZFactReglSuppr;
        FDocEntete fDocEntete = getFDocEntete(cbMarq);
        params = new Object[] {fDocEntete.getDO_Domaine(),fDocEntete.getDO_Piece(),fDocEntete.getDO_Type()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getInt("valeur");
        }
        return 0;
    }

    public List<Object> getDateEcgetTiersheance(String ctNum,String date){
        String sql = FDocEnteteMapper.getDateEcgetTiersheance;
        params = new Object[] {date,date,ctNum};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getFactureCORecouvrement(int collab,String ctNum){
        String sql = FDocEnteteMapper.getFactureCORecouvrement;
        params = new Object[] {collab,ctNum};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getDateEcgetTiersheanceSelect(int reglement,String date,int mrNo){
        String sql = FDocEnteteMapper.getDateEcgetTiersheanceSelect;
        params = new Object[] {date,date,mrNo,reglement};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }



    public Object ajoutEnteteTrsftDetail(String entete,String typeFacture,double latitude,double longitude,String doRef,String doDate,int depot,int collaborateur,String ctNum,int protNo){
        FComptetDAO fComptetDAO = new FComptetDAO(this.getDataSource());
        FComptet fComptet = fComptetDAO.getF_Comptet("41TRANSFERTDETAIL",0);
        if(fComptet.getCbMarq()==null) {
            fComptetDAO.createClientMin("41TRANSFERTDETAIL", "TRANSFERT DETAILS", "0", "4110000", "", "", "", "", "", "", 0
                    ,0, 0, 1, "", "", 0,"");
            fComptet = fComptetDAO.getF_Comptet("41TRANSFERTDETAIL",0);
        }
        addDocenteteTransfertDetailProcess(fComptet,41,entete,typeFacture,latitude,longitude,doRef,doDate,depot,protNo);
        return (List<Object>) getFDocEnteteJSon(addDocenteteTransfertDetailProcess(fComptet,40,entete,typeFacture,latitude,longitude,doRef,doDate,Integer.valueOf(ctNum),protNo))  ;
    }

    public BigDecimal addDocenteteTransfertDetailProcess(FComptet fComptet,int doType,String doPieceParam,String typeFacture,double latitude,double longitude,String doRef,String doDate,int depot,int protNo){
        String doPiece ="";
        BigDecimal cbMarq = BigDecimal.ZERO;
        if(doType==40) {
            doPiece = doPieceParam;
        }
        else {
            doPiece = getEnteteDocument(typeFacture,0);
        }
        FDocEntete fDocEntete = new FDocEntete();
        fDocEntete.defaultValue();
        fDocEntete.setValueMvtEntree();
        fDocEntete.setTypeFac(typeFacture);
        fDocEntete.setDO_Type(doType);
        fDocEntete.setDO_Piece(doPiece);
        fDocEntete.setLatitude(latitude);
        fDocEntete.setLongitude(longitude);
        fDocEntete.setDO_Ref(doRef);
        fDocEntete.setDO_Tiers(fComptet.getCT_Num());
        fDocEntete.setCA_Num("");
        fDocEntete.setDO_Date(doDate);
        fDocEntete.setDO_DateLivr("1900-01-01");
        fDocEntete.setDE_No(depot);
        fDocEntete.setValueMvt();
        fDocEntete.setCbCreateur(String.valueOf(protNo));
        return insertDocEntete(fDocEntete);
    }

    public BigDecimal insertDocEntete(FDocEntete fDocEntete){
        String sql = FDocEnteteMapper.insertFDocEntete;
        params = new Object[]{fDocEntete.getDO_Domaine(),fDocEntete.getDO_Type(),fDocEntete.getDO_Date(),fDocEntete.getDO_Ref()
                ,fDocEntete.getDO_Tiers(),fDocEntete.getCO_No(),fDocEntete.getDO_Period(),fDocEntete.getDO_Devise()
                ,fDocEntete.getDO_Cours(),fDocEntete.getDE_No(),fDocEntete.getLI_No(),fDocEntete.getCT_NumPayeur()
                ,fDocEntete.getDO_Expedit(),fDocEntete.getDO_NbFacture(),fDocEntete.getDO_BLFact()
                ,fDocEntete.getDO_TxEscompte(),fDocEntete.getDO_Reliquat(),fDocEntete.getDO_Imprim(),fDocEntete.getCA_Num()
                ,fDocEntete.getDO_Coord01(),fDocEntete.getDO_Coord02(),fDocEntete.getDO_Coord03(),fDocEntete.getDO_Coord04()
                ,fDocEntete.getDO_Souche(),fDocEntete.getDO_DateLivr(),fDocEntete.getDO_Tarif()
                ,fDocEntete.getDO_Colisage(),fDocEntete.getDO_TypeColis(),fDocEntete.getDO_Transaction(),fDocEntete.getDO_Langue()
                ,fDocEntete.getDO_Regime(),fDocEntete.getN_CatCompta(),fDocEntete.getDO_Ventile()
                ,fDocEntete.getAB_No(),fDocEntete.getDO_DebutAbo(),fDocEntete.getDO_FinAbo(),fDocEntete.getDO_DebutPeriod()
                ,fDocEntete.getDO_FinPeriod(),fDocEntete.getCG_Num()
                ,fDocEntete.getDO_Statut()
                ,fDocEntete.getCA_No(),fDocEntete.getCO_NoCaissier(),fDocEntete.getDO_Transfere()
                ,fDocEntete.getDO_Cloture(),fDocEntete.getDO_NoWeb(),fDocEntete.getDO_Attente(),fDocEntete.getDO_Provenance()
                ,fDocEntete.getCA_NumIFRS(),fDocEntete.getMR_No(),fDocEntete.getDO_TypeFrais(),fDocEntete.getDO_ValFrais()
                ,fDocEntete.getDO_TypeLigneFrais(),fDocEntete.getDO_TypeFranco(),fDocEntete.getDO_ValFranco(),fDocEntete.getDO_TypeLigneFranco()
                ,fDocEntete.getDO_Taxe1(),fDocEntete.getDO_TypeTaux1(),fDocEntete.getDO_TypeTaxe1()
                ,fDocEntete.getDO_Taxe2(),fDocEntete.getDO_TypeTaux2(),fDocEntete.getDO_TypeTaxe2()
                ,fDocEntete.getDO_Taxe3(),fDocEntete.getDO_TypeTaux3(),fDocEntete.getDO_TypeTaxe3()
                ,fDocEntete.getDO_MajCpta(),fDocEntete.getDO_Motif(),fDocEntete.getDO_Contact(),fDocEntete.getDO_FactureElec()
                ,fDocEntete.getDO_TypeTransac(),fDocEntete.getCbProt(),fDocEntete.getCbCreateur(),fDocEntete.getCbFlag()
                ,fDocEntete.getVEHICULE(),fDocEntete.getCHAUFFEUR(),fDocEntete.getLongitude(),fDocEntete.getLatitude()
                ,fDocEntete.getDO_Piece()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        BigDecimal cbMarq = BigDecimal.ZERO;
        while(sqlRowSet.next()) {
            cbMarq = sqlRowSet.getBigDecimal("cbMarq");
        }
        return cbMarq;
    }

    public String getCalculDateEcheance ( int condition,int nbjour, int jour, String dateEch){
        String sql = FDocEnteteMapper.calculDateEcheance;
        params = new Object[]{condition, nbjour, jour, dateEch};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        String dateEchCalcule = "";
        while(sqlRowSet.next()) {
            dateEchCalcule =sqlRowSet.getString("dateEchCalcule");
        }
        return dateEchCalcule;
    }


    public void updateEnteteTable(String nextDO_Piece,FDocEntete fDocEntete)
    {
        String query = " UPDATE F_DOCCURRENTPIECE SET DC_Piece='"+nextDO_Piece+"',cbModification=GETDATE() " +
                " WHERE DC_Domaine="+fDocEntete.getDO_Domaine()+" AND DC_Souche="+fDocEntete.getDO_Souche()+" AND DC_IdCol="+fDocEntete.getDoccurent_type();
        this.getJdbcTemplate().execute(query);
    }

    public int getDelai() {
        String sql = FDocEnteteMapper.PR_DelaiPreAlert;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return  (int) ((HashMap)list.get(0)).get("PR_DelaiPreAlert");
    }

    public String getEnteteTable(String typeFac,int DO_Souche){
        fDocEntete.setTypeFac(typeFac);
        fDocEntete.setDO_Souche(DO_Souche);
        String DO_Piece="";
        String sql = FDocEnteteMapper.getEnteteTable;
        params = new Object[] {fDocEntete.getDO_Domaine(),fDocEntete.getDO_Souche(),fDocEntete.getDoccurent_type()};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        getEnteteDispo((String) ((HashMap)list.get(0)).get("DC_Piece"));
        if(list.size()>0)
            DO_Piece = (String) ((HashMap)list.get(0)).get("DC_Piece");
        return DO_Piece;
    }

    public String getEnteteDocument(String typeFac,int DO_Souche){
        return getEnteteDispo(getEnteteTable(typeFac,DO_Souche));
    }

    public String getEnteteByDOPiece(String DO_Piece) {
        String sql = FDocEnteteMapper.getEnteteByDOPiece;
        StringBuffer alpha = new StringBuffer(),
                num = new StringBuffer(), special = new StringBuffer();
        for (int i=0; i<DO_Piece.length(); i++)
        {
            if (Character.isDigit(DO_Piece.charAt(i)))
                num.append(DO_Piece.charAt(i));
            else if(Character.isAlphabetic(DO_Piece.charAt(i)))
                alpha.append(DO_Piece.charAt(i));
            else
                special.append(DO_Piece.charAt(i));
        }

        params = new Object[] {fDocEntete.getDO_Type(),fDocEntete.getDO_Domaine(),alpha ,num};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        if(list.size()>0)
            return  (String) ((HashMap)list.get(0)).get("DO_Piece");
        return null;
    }

    static String incrementeDOPiece(String str)
    {
        StringBuffer alpha = new StringBuffer(),
                num = new StringBuffer(), special = new StringBuffer();

        for (int i=0; i<str.length(); i++)
        {
            if (Character.isDigit(str.charAt(i)))
                num.append(str.charAt(i));
            else if(Character.isAlphabetic(str.charAt(i)))
                alpha.append(str.charAt(i));
            else
                special.append(str.charAt(i));
        }
        String number = "000000000"+String.valueOf(Integer.valueOf(num.toString())+1);
        number = number.substring(number.length()-9);
        return alpha+ (number).substring(alpha.length());
    }

    public String getEnteteDispo(String DO_Piece){
        String dopiece = DO_Piece;
        String rowsTour = getEnteteByDOPiece(dopiece);
        while(rowsTour!=null){
            dopiece = incrementeDOPiece(dopiece);
            rowsTour = getEnteteByDOPiece(dopiece);
        }
        if(fDocEntete.getDO_Type()==30){
            rowsTour = getEnteteTicketByDOPiece();
            dopiece = rowsTour+1;
        }
        return dopiece;
    }

    public void majEnteteComptable(int doType,int doDomaine,String doPiece,int doTypeCible){
        String sql = FDocEnteteMapper.majEnteteComptable;
        params = new Object[] {doType,doDomaine,doPiece,doTypeCible};
        this.getJdbcTemplate().update(sql,params);
    }

    public Object enteteTransfertDetail(BigDecimal cbMarq){
        String sql = FDocEnteteMapper.enteteTransfertDetail;
        params = new Object[] {cbMarq};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public Object getLigneTransfertDetail(String doPiece){
        String sql = FDocEnteteMapper.getLigneTransfertDetail;
        params = new Object[]{doPiece};
        return this.getJdbcTemplate().query(sql,params,mapper);
    }
    public ArrayList<BigDecimal> enteteTransfertDetailCbMarq(BigDecimal cbMarq){
        String sql = FDocEnteteMapper.enteteTransfertDetail;
        params = new Object[] {cbMarq};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        ArrayList<BigDecimal> liste = new ArrayList<BigDecimal>();
        while(sqlRowSet.next()) {
            liste.add(sqlRowSet.getBigDecimal("cbMarq"));
        }
        return liste;
    }

    public String getEnteteTicketByDOPiece() {
        String sql = FDocEnteteMapper.getEnteteTicketByDOPiece;
        params = new Object[] {fDocEntete.getDO_Domaine(),fDocEntete.getDO_Type()};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        if(list.size()>0)
            return  (String) ((HashMap)list.get(0)).get("DO_Piece");
        return "0";
    }

    public void maj(String nom,String valeur,BigDecimal cbMarq,String cbCreateur) {
        String cbCreateurSql="";
        if(!cbCreateur.equals(""))
            cbCreateurSql=",cbCreateur='"+cbCreateur+"'";
        String updateQuery = "UPDATE F_DOCENTETE SET "+nom+" = '"+valeur+"' "+cbCreateurSql+" WHERE cbMarq = "+cbMarq;
        this.getJdbcTemplate().update(updateQuery);
    }

    public void doImprim(BigDecimal cbMarq) {
        int imprim = 0;
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarq);
        if ((fDocEntete.getDO_Domaine() == 0 && fDocEntete.getDO_Type() == 0)
                || (fDocEntete.getDO_Domaine() == 1 && (fDocEntete.getDO_Type() == 11 || fDocEntete.getDO_Type() == 12)))
            imprim = 1;
        if (imprim == 0) {
            this.maj("DO_Imprim" , "1" , cbMarq, "");
        }
    }

    public void majCollaborateur(String coNo,BigDecimal cbMarq){
        this.maj("CO_No",coNo,cbMarq,"");
        ArrayList<BigDecimal> listeLigne = this.getListeFDocLigne(cbMarq);
        FDocLigneDAO fDocLigneDao = new FDocLigneDAO(this.getDataSource());
        for (BigDecimal list : listeLigne) {
            fDocLigneDao.maj("CO_No",coNo,list,"");
        }
    }

    public void majAffaire(String caNum,BigDecimal cbMarq){
        this.maj("CA_Num",caNum,cbMarq,"");
        ArrayList<BigDecimal> listeLigne = this.getListeFDocLigne(cbMarq);
        FDocLigneDAO fDocLigneDao = new FDocLigneDAO(this.getDataSource());
        for(BigDecimal list : listeLigne)
            fDocLigneDao.maj("CA_Num",caNum,list,"");
    }

    public List<Object> getLigneFacture(BigDecimal cbMarq){
        FDocEntete fDocEntete = getFDocEntete(cbMarq);
        String sql = FDocEnteteMapper.getLigneFacture;
        params = new Object[] {fDocEntete.getDO_Piece(),fDocEntete.getDO_Domaine(),fDocEntete.getDO_Type()};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public ArrayList<BigDecimal> getLigneFactureCbMarq(BigDecimal cbMarq){
        FDocEntete fDocEntete = getFDocEntete(cbMarq);
        String sql = FDocEnteteMapper.getLigneFacture;
        params = new Object[] {fDocEntete.getDO_Piece(),fDocEntete.getDO_Domaine(),fDocEntete.getDO_Type()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        ArrayList<BigDecimal> liste = new ArrayList<BigDecimal>();
        while(sqlRowSet.next()) {
            liste.add(sqlRowSet.getBigDecimal("cbMarq"));
        }
        return liste;
    }
}

