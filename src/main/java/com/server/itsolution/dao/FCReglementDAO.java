package com.server.itsolution.dao;

import com.server.itsolution.entities.*;
import com.server.itsolution.mapper.FCReglementMapper;
import com.server.itsolution.mapper.FDocEnteteMapper;
import com.server.itsolution.mapper.FDocLigneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class FCReglementDAO extends JdbcDaoSupport {

    @Autowired
    public FCReglementDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[]{};
    public FCReglementMapper mapper = new FCReglementMapper();

    public List<Object> getAll() {
        String sql = FCReglementMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getReglement(BigDecimal rgNo) {
        String sql = FCReglementMapper.getReglementByRGNo;
        params = new Object[]{rgNo};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public ArrayList<BigDecimal> getListeReglementMajComptable(String dateDebut, String dateFin, int etatPiece, int typeTransfert, int caNo) {
        String sql = FCReglementMapper.getListeReglementMajComptable;
        params = new Object[]{dateDebut, dateFin, etatPiece, typeTransfert, caNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        ArrayList<BigDecimal> listeReglt = new ArrayList<BigDecimal>();
        while (sqlRowSet.next()) {
            listeReglt.add(sqlRowSet.getBigDecimal("RG_No"));
        }
        return listeReglt;
    }

    public FCReglement getFCReglement(BigDecimal rgNo) {

        String sql = FCReglementMapper.getReglementByRGNo;
        params = new Object[]{rgNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FCReglement fcReglement = new FCReglement();
        while (sqlRowSet.next()) {
            fcReglement.setRG_No(sqlRowSet.getBigDecimal("RG_No"));
            fcReglement.setCA_No(sqlRowSet.getInt("CA_No"));
            fcReglement.setCbCreateur(sqlRowSet.getString("cbCreateur"));
            fcReglement.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
            fcReglement.setCO_NoCaissier(sqlRowSet.getInt("CO_NoCaissier"));
            fcReglement.setCG_Num(sqlRowSet.getString("CG_Num"));
            fcReglement.setCT_NumPayeur(sqlRowSet.getString("CT_NumPayeur"));
            fcReglement.setJO_Num(sqlRowSet.getString("JO_Num"));
            fcReglement.setN_Devise(sqlRowSet.getInt("N_Devise"));
            fcReglement.setRG_Montant(sqlRowSet.getDouble("RG_Montant"));
            fcReglement.setRG_Date(sqlRowSet.getString("RG_Date"));
            fcReglement.setRG_Libelle(sqlRowSet.getString("RG_Libelle"));
            fcReglement.setRG_Type(sqlRowSet.getInt("RG_Type"));
            fcReglement.setRG_TypeReg(sqlRowSet.getInt("RG_TypeReg"));
            fcReglement.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
            fcReglement.setRG_Piece(sqlRowSet.getString("RG_Piece"));
            fcReglement.setRG_Banque(sqlRowSet.getInt("RG_Banque"));
        }
        return fcReglement;
    }


    public int journeeCloture(String date, int caNo) {
        String sql = FCReglementMapper.journeeCloture;
        params = new Object[]{date, caNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql);
        while (sqlRowSet.next()) {
            return sqlRowSet.getInt("Nb");
        }
        return 0;
    }

    public int setDO_Modif(BigDecimal rgNo) {
        String sql = FCReglementMapper.setDO_Modif;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(rgNo);
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, parames.toArray());
        while (sqlRowSet.next()) {
            return sqlRowSet.getInt("DO_Modif");
        }
        return 0;
    }

    public List<Object> getMajAnalytique(String dateDeb, String dateFin, String caNum, int statut) {
        String sql = FCReglementMapper.getMajAnalytique;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(dateDeb);
        parames.add(dateFin);
        parames.add(caNum);
        parames.add(statut);
        List<Object> list = this.getJdbcTemplate().query(sql, parames.toArray(), mapper);
        return list;
    }

    public void setMajAnalytique(String dateDeb, String dateFin, String caNum, String cbCreateur) {
        String sql = FCReglementMapper.setMajAnalytique;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(dateDeb);
        parames.add(dateFin);
        parames.add(caNum);
        parames.add(cbCreateur);
        this.getJdbcTemplate().update(sql, parames.toArray());

    }

    public void remboursementRglt(BigDecimal rgNo, String date, double montant) {
        FCReglement fcReglementInit = getFCReglement(rgNo);
        FCReglement fcReglement = new FCReglement();
        fcReglement.initVariables();
        fcReglement.setRG_Date(date);
        fcReglement.setRG_DateEchCont(date);
        fcReglement.setJO_Num(fcReglementInit.getJO_Num());
        fcReglement.setCG_Num(fcReglementInit.getCG_Num());
        fcReglement.setCA_No(fcReglementInit.getCA_No());
        fcReglement.setCO_NoCaissier(fcReglementInit.getCO_NoCaissier());
        fcReglement.setRG_Libelle("Remboursement " + fcReglementInit.getRG_Piece());
        fcReglement.setRG_Montant(montant);
        fcReglement.setRG_Impute(1);
        fcReglement.setRG_Type(fcReglementInit.getRG_Type());
        fcReglement.setN_Reglement(1);
        fcReglement.setRG_TypeReg(4);
        if (fcReglementInit.getRG_Type() == 1)
            fcReglement.setRG_TypeReg(5);
        fcReglement.setRG_Ticket(0);
        fcReglement.setRG_Banque(fcReglementInit.getRG_Banque());
        fcReglement.setCT_NumPayeur(fcReglementInit.getCT_NumPayeur());
        insertF_Reglement(fcReglement);
        //liaison du remboursement et reglement
        insertZ_RGLT_BONDECAISSE(fcReglement.getRG_No(), fcReglementInit.getRG_No());
        fcReglement.setRG_Impute(isImpute(fcReglement.getRG_No()));
        majReglement(fcReglement);
    }

    public int isImpute (BigDecimal rgNo){
        String sql = FCReglementMapper.isImpute;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(rgNo);
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        int isImpute = 0;
        while (sqlRowSet.next()) {
            isImpute = sqlRowSet.getInt("isImpute");
        }
        return isImpute;
    }

    public void supprRgltAssocie(BigDecimal rgNo) {
        String sql = FCReglementMapper.supprRgltAssocie;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(rgNo);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public void deleteF_ReglementVrstBancaire(BigDecimal rgNo) {
        String sql = FCReglementMapper.deleteF_ReglementVrstBancaire;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(rgNo);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public void insertF_ReglementVrstBancaire(BigDecimal rgNo, BigDecimal rgNoCache) {
        String sql = FCReglementMapper.insertF_ReglementVrstBancaire;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(rgNo);
        parames.add(rgNoCache);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public void insertCaNum(BigDecimal rgNo, String caNum) {
        String sql = FCReglementMapper.insertCaNum;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(rgNo);
        parames.add(caNum);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public void insertZ_RGLT_BONDECAISSE(BigDecimal rgNo, BigDecimal rgNoLier) {
        String sql = FCReglementMapper.insertZ_RGLT_BONDECAISSE;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(rgNo);
        parames.add(rgNoLier);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public ArrayList<Integer> getReglementFacture(BigDecimal cbMarqEntete) {
        String sql = FCReglementMapper.getReglementFacture;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(cbMarqEntete);
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        ArrayList<Integer> liste = new ArrayList<Integer>();
        while (sqlRowSet.next()) {
            liste.add(sqlRowSet.getInt("RG_No"));
        }
        return liste;
    }

    public void insertMvtCaisse(double rgMontant, int protNo, String caNum, String libelle, int rgTypeReg, int caNo
            , String cgNumBanque, int isModif, String rgDate, String joNum, int caNoDest, int cgAnalytique
            , int rgTyperegModif, String journalRec, BigDecimal rgNoDest) {
        FCaisseDAO fCaisseDAO = new FCaisseDAO(this.getDataSource());
        FCaisse fcaisse = fCaisseDAO.getF_Caisse(caNo);
        int coNoCaissier = fcaisse.getCO_NoCaissier();
        String caIntitule = fcaisse.getCA_Intitule();
        joNum = fcaisse.getJO_Num();
        FCollaborateurDAO fCollaborateurDAO = new FCollaborateurDAO(this.getDataSource());
        FCollaborateur fCollaborateur = fCollaborateurDAO.getCollaborateurJSON(coNoCaissier);
        FProtectioncialDAO fProtectioncialDAO = new FProtectioncialDAO(this.getDataSource());
        FProtectioncial fProtectioncial = fProtectioncialDAO.connexionProctectionByProtNo(protNo);
        FCReglement fcReglement = new FCReglement();
        String collaborateurCaissier = "";
        if (fCollaborateur.getCbMarq() != null)
            collaborateurCaissier = fCollaborateur.getCO_Nom();

        int banque = 0;
        String cgNum = cgNumBanque;
        if (rgTypeReg == 2)
            cgNum = null;
        if (rgTypeReg == 6) {
            // Pour les vrst bancaire mettre a jour le compteur du RGPIECE
            banque = 1;
        }

        BigDecimal rgNo = BigDecimal.ZERO;

        if (rgTypeReg != 16) {
            int rg_typeregVal = rgTypeReg;
            if (rgTypeReg == 6)
                rg_typeregVal = 4;

            if (rgTypeReg == 6) {
                fcReglement.setReglement(null, rgDate, rgMontant, joNum, cgNum, caNo, libelle, 0, 2, 1, rg_typeregVal, 1, 1, String.valueOf(protNo));
                this.insertF_Reglement(fcReglement);
                rgNo = fcReglement.getRG_No();
                fcReglement = new FCReglement();
                fcReglement.setReglement(null, rgDate, rgMontant, fcaisse.getJO_Num(), cgNum, caNo, libelle + "_" + fcaisse.getJO_Num(), 0, 2, 8, rg_typeregVal, 1, 1, String.valueOf(protNo));
                this.insertF_Reglement(fcReglement);
                rgNoDest = fcReglement.getRG_No();
                this.insertF_ReglementVrstBancaire(rgNo, rgNoDest);
            } else {
                fcReglement.setReglement(null, rgDate, rgMontant, joNum, cgNum, caNo, libelle, 0, 2, 1, rg_typeregVal, 1, banque, String.valueOf(protNo));
                this.insertF_Reglement(fcReglement);
                rgNo = fcReglement.getRG_No();
            }
        } else {
            fcReglement.setReglement(null, rgDate, rgMontant, joNum, cgNum, caNo, libelle, 0, 2, 1, 4, 1, banque, String.valueOf(protNo));
            this.insertF_Reglement(fcReglement);
            FCaisse fcaisseDest = fCaisseDAO.getF_Caisse(caNoDest);
            fcReglement.setReglement(null, rgDate, rgMontant, fcaisseDest.getJO_Num(), cgNum, fcaisseDest.getCA_No(), libelle, 0, 2, 1, 5, 1, banque, String.valueOf(protNo));
            this.insertF_Reglement(fcReglement);
            rgNo = fcReglement.getRG_No();
        }

        if (isModif == 0) {
            if (!caNum.equals("") && cgAnalytique == 1)
                this.insertCaNum(rgNo, caNum);
            if (rgTypeReg == 4) {
                String msg = "SORTIE D'UN MONTANT DE " + FormatText.getMontant(rgMontant) + " POUR " + libelle + " DANS LA CAISSE " + caIntitule + " SAISI PAR " + protNo + " LE " + rgDate;
            }
        }

        String message = "";
        if (isModif == 0)
            if (rgTypeReg == 4) {
                message = "SORTIE D' UN MONTANT DE " + rgMontant + " POUR " + libelle +
                        " DANS LA CAISSE " + caIntitule + " SAISI PAR " + fProtectioncial.getPROT_User() +
                        " LE " + rgDate;
                fProtectioncialDAO.getCollaborateurEnvoiMail(message, "Mouvement de sortie");
                fProtectioncialDAO.getCollaborateurEnvoiSMS(message, "Mouvement de sortie");
            }

        if (isModif == 0)
            if (rgTypeReg == 6) {
                message = "VERSEMENT BANCAIRE D'UN MONTANT DE $montant DANS LA CAISSE " +
                        caIntitule + " SAISI PAR " + fProtectioncial.getPROT_User() + " LE " + rgDate;

                message = "SORTIE D' UN MONTANT DE " + rgMontant + " POUR " + libelle +
                        " DANS LA CAISSE " + caIntitule + " SAISI PAR " + fProtectioncial.getPROT_User() +
                        " LE " + rgDate;
                fProtectioncialDAO.getCollaborateurEnvoiMail(message, "Mouvement de sortie");
                fProtectioncialDAO.getCollaborateurEnvoiSMS(message, "Mouvement de sortie");
            }

        if (isModif == 0)
            if (rgTypeReg != 2)
                this.incrementeCOLREGLEMENT();


        if (isModif == 1) {
            fcReglement = getFCReglement(rgNo);
            fcReglement.setRG_Date(rgDate);

            fcaisse = fCaisseDAO.getF_Caisse(caNo);
            fcReglement.setJO_Num(fcaisse.getJO_Num());
            fcReglement.setCA_No(caNo);
            fcReglement.setRG_Libelle(libelle);
            fcReglement.setCG_Num(cgNumBanque);
            fcReglement.setRG_Montant(rgMontant);

            if (rgTyperegModif == 6) {
                fcReglement.setRG_TypeReg(5);
                fcReglement.setJO_Num(journalRec);
            }

            if (rgTyperegModif == 16) {
                fcReglement.setRG_TypeReg(4);
                majReglement(fcReglement);
                fcReglement = getFCReglement(rgNoDest);
                fcReglement.setRG_Date(rgDate);
                fcReglement.setCA_No(caNoDest);
                fcaisse = fCaisseDAO.getF_Caisse(caNoDest);
                fcReglement.setJO_Num(fcaisse.getJO_Num());
                fcReglement.setRG_Libelle(libelle);
                fcReglement.setCG_Num(cgNumBanque);
                fcReglement.setRG_TypeReg(5);
                fcReglement.setRG_Montant(rgMontant);
            }
            majReglement(fcReglement);
        }
    }


    public List<Object> getFactureRGNo(BigDecimal rgNo) {
        String sql = FCReglementMapper.getFactureRGNo;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(rgNo);
        return this.getJdbcTemplate().query(sql, parames.toArray(), mapper);
    }

    public void supprReglement(BigDecimal rgNo) {
        FCReglement fCreglement = getFCReglement(rgNo);
        String sql = FCReglementMapper.supprReglement;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(rgNo);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public void supprReglementTiers(boolean mvtCaisse, BigDecimal rgNo, int protNo) {
        String typemvt = "Suppression règlement";
        if (mvtCaisse)
            typemvt = "Suppression mouvement de caisse";

        FCReglement fcReglement = getFCReglement(rgNo);
        FCollaborateurDAO fCollaborateurDAO = new FCollaborateurDAO(this.getDataSource());
        FCollaborateur fCollaborateur = fCollaborateurDAO.getCollaborateurJSON(protNo);
        this.supprReglement(rgNo);
        FProtectioncialDAO fProtectioncialDAO = new FProtectioncialDAO(this.getDataSource());
        FProtectioncial fProtectioncial = fProtectioncialDAO.connexionProctectionByProtNo(protNo);
        String message = "";
        FComptetDAO fComptetDAO = new FComptetDAO(this.getDataSource());
        FComptet fComptet = null;
        String libelle = "";
        if (fcReglement.getCT_NumPayeur() != null) {
            fComptet = fComptetDAO.getF_Comptet(fcReglement.getCT_NumPayeur(), fcReglement.getRG_Type());
            libelle = fComptet.getCT_Intitule();
        }
        message = "Le règlement " + fcReglement.getRG_Piece() + " a été supprimé par " + fProtectioncial.getPROT_User() + "<br/>"
                + " Le règlement concerne le client " + libelle + "<br/>"
                + " Libellé :" + fcReglement.getRG_Libelle() + "<br/>"
                + " Montant du règlement : " + fcReglement.getRG_Montant() + "<br/>"
                + " Date du règlement : " + fcReglement.getRG_Date() + "<br/><br/>"
                + " Cordialement.<br/><br/>";
        fProtectioncialDAO.getCollaborateurEnvoiMail(message, typemvt);
        //if (preg_match('#^[\w.-]+@[\w.-]+\.[a-z]{2,6}$#i', $email))
        //    $objet->envoiMail($corpsMail ,"Suppression du règlement ".$reglement->RG_Piece,$email);
    }

    public void modifReglementTiers(int protNo, String coNo, int bonCaisse, BigDecimal rgNo, String rgLibelle, double montant, String rgDate, String joNum, String ctNum) {
        if (bonCaisse == 1) {
            coNo = ctNum;
        }
        FCReglement fcReglement = getFCReglement(rgNo);
        FProtectioncialDAO fProtectioncialDAO = new FProtectioncialDAO(this.getDataSource());
        FProtectioncial fProtectioncial = fProtectioncialDAO.connexionProctectionByProtNo(protNo);
        int isSecurite = fProtectioncialDAO.isSecuriteAdminCaisse(protNo, fProtectioncial.getProtectAdmin(), fcReglement.getCA_No());
        if (isSecurite == 1) {
            fcReglement.setRG_Libelle(rgLibelle);
            fcReglement.setRG_Montant(montant);
            fcReglement.setRG_Date(rgDate);
            fcReglement.setCO_NoCaissier(Integer.valueOf(coNo));
            fcReglement.setCbCreateur(String.valueOf(protNo));
            majReglement(fcReglement);
        }
    }

    public void updateImpute(BigDecimal rgNo) {
        FCReglement fcReglement = this.getFCReglement(rgNo);
        isRegleFull(fcReglement.getCbMarq());
        /*if(isRegleFull==1) {
            fcReglement.setRG_Impute(1);
            majReglement(fcReglement);
        }*/
    }

    public void isRegleFull(BigDecimal cbMarq) {
        String sql = "BEGIN \t \n" +
                "\tDECLARE @val INT \n" +
                "\tDECLARE @cbMarq INT =" + cbMarq + "\n" +
                "\n" +
                "\tSELECT @val = CASE WHEN Max(RG_Montant) = ISNULL(SUM(RC_Montant),0) THEN 1 ELSE 0 END \n" +
                "\t\t FROM \tF_CREGLEMENT C\n" +
                "\t\t LEFT JOIN F_REGLECH D \n" +
                "\t\t\t ON D.RG_No=C.RG_No\n" +
                "\tWHERE C.cbMarq=@cbMarq\n" +
                "\tIF @val = 1 \n" +
                "\tBEGIN\n" +
                "\t\tUPDATE F_CREGLEMENT SET RG_Impute = 1 WHERE cbMarq = @cbMarq\n" +
                "\tEND\n" +
                "END ";
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(sql);
        this.getJdbcTemplate().execute(sql);
    }


    public void insertFactReglSuppr(BigDecimal cbMarqEntete, BigDecimal rgNo) {
        String sql = FCReglementMapper.insertFactReglSuppr;
        FCReglement fcReglement = getFCReglement(rgNo);
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(fDocEntete.getDO_Domaine());
        parames.add(fDocEntete.getDO_Type());
        parames.add(fDocEntete.getDO_Piece());
        parames.add(cbMarqEntete);
        parames.add(rgNo);
        parames.add(fcReglement.getCbMarq());
        this.getJdbcTemplate().update(sql, params);
    }

    public void updateRgImputeSupprReglt(BigDecimal rgNo) {
        String sql = FCReglementMapper.updateRgImputeSupprReglt;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(rgNo);
        this.getJdbcTemplate().update(sql, params);
    }

    public void addEcheance(int protNo, BigDecimal rgNo, String typeRegl, BigDecimal cbMarqEntete, double montant) {
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
        FDocReglDAO fDocReglDAO = new FDocReglDAO(this.getDataSource());
        FCReglement fcReglement = getFCReglement(rgNo);

        if (typeRegl.equals("Collaborateur")) {
            if (fDocEntete.getCbMarq() != null) {
                fcReglement.setCT_NumPayeur(fDocEntete.getDO_Tiers());
                majReglement(fcReglement);
            }
        }
        FDocRegl fDocRegl = fDocEnteteDAO.getDocRegl(fDocEntete);
        FReglEchDAO fReglEchDAO = new FReglEchDAO(this.getDataSource());
        FReglEch fReglEch = new FReglEch();
        fReglEch.setRG_No(fcReglement.getRG_No());
        fReglEch.setDR_No(fDocRegl.getDR_No());
        fReglEch.setDO_Domaine(fDocEntete.getDO_Domaine());
        fReglEch.setDO_Type(fDocEntete.getDO_Type());
        fReglEch.setDO_Piece(fDocEntete.getDO_Piece());
        fReglEch.setCbCreateur(String.valueOf(protNo));
        fReglEch.setRG_TypeReg(fcReglement.getRG_TypeReg());
        fReglEch.setRcMontant(montant);
        fReglEchDAO.addReglEch(fReglEch);
        this.updateImpute(rgNo);
        fDocReglDAO.isDocReglFull(fDocEntete.getCbMarq());
    }

    public void majReglement(FCReglement fcReglement) {
        String sql = FCReglementMapper.modifReglement;
        params = new Object[]{fcReglement.getRG_Date(), fcReglement.getRG_Reference(), fcReglement.getRG_Libelle(), fcReglement.getRG_Montant()
                , fcReglement.getRG_MontantDev(), fcReglement.getN_Reglement(), fcReglement.getRG_Impute(), fcReglement.getRG_Compta()
                , fcReglement.getEC_No(), fcReglement.getRG_Type(), fcReglement.getRG_Cours(), fcReglement.getN_Devise(), fcReglement.getJO_Num()
                , fcReglement.getCG_NumCont(), fcReglement.getRG_Impaye(), fcReglement.getCG_Num(), fcReglement.getRG_TypeReg()
                , fcReglement.getRG_Heure(), fcReglement.getRG_Piece(), fcReglement.getCA_No(), fcReglement.getCO_NoCaissier()
                , fcReglement.getRG_Banque(), fcReglement.getRG_Transfere(), fcReglement.getRG_Cloture(), fcReglement.getRG_Ticket()
                , fcReglement.getRG_Souche(), fcReglement.getCT_NumPayeurOrig(), fcReglement.getRG_DateEchCont()
                , fcReglement.getCG_NumEcart(), fcReglement.getJO_NumEcart(), fcReglement.getRG_MontantEcart(), fcReglement.getRG_NoBonAchat()
                , fcReglement.getCbCreateur(), fcReglement.getCbMarq()};
        this.getJdbcTemplate().update(sql, params);
    }

    public List<Object> getReglementByClient(String dateDeb, String dateFin, int rgImpute, String ctNum, int collab, int nReglement, int caNo, int coNoCaissier, int rgType) {
        String sql = FCReglementMapper.getReglementByClient;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(dateDeb);
        parames.add(dateFin);
        parames.add(rgImpute);
        parames.add(ctNum);
        parames.add(collab);
        parames.add(nReglement);
        parames.add(caNo);
        parames.add(coNoCaissier);
        parames.add(rgType);
        return this.getJdbcTemplate().query(sql, parames.toArray(), mapper);
    }


    public BigDecimal addReglement(int protNo, String joNum, int rgNoLier, String ctNum, int caNo, int bonCaisse, String libelle, String caissier
            , String date, String modeReglementRec, double montant, int impute, int rgType, boolean afficheData, String typeRegl) {
        int boncaisse = 0;
        int banque = 0;
        String co_no = "0";
        int rgTypeN = 0;
        FComptet fComptet = null;
        String cgNum = null;
        String ctIntitule = "";

        if (boncaisse == 1) {
            co_no = ctNum;
            ctNum = "";
            banque = 3;
        }

        if (typeRegl.equals("Collaborateur")) {
            caissier = ctNum;
            ctNum = "";
            banque = 3;
            rgTypeN = 1;
        } else {
            FComptetDAO fComptetDAO = new FComptetDAO(this.getDataSource());
            fComptet = fComptetDAO.getF_Comptet(ctNum, rgType);
            if (fComptet.getCT_Num() != null) {
                cgNum = fComptet.getCG_NumPrinc();
                ctIntitule = fComptet.getCT_Intitule();
                rgTypeN = fComptet.getCT_Type();
            } else {
                banque = 3;
                rgTypeN = 2;
            }
        }

        String email = "";
        String telephone = "";
        String collabIntitule = "";
        String caissierIntitule = "";
        if (boncaisse == 1)
            caissier = co_no;
        int rgTypeReg = 0;
        if (modeReglementRec.equals("05")) {
            banque = 2;
            libelle = "Verst distant " + libelle;
        }

        if (modeReglementRec.equals("10")) {
            rgTypeReg = 5;
        }
        FCaisseDAO fCaisseDAO = new FCaisseDAO(this.getDataSource());
        FCaisse fCaisse = fCaisseDAO.getF_Caisse(caNo);
        String caIntitule = "";
        if (fCaisse.getCbMarq() != null) {
            caIntitule = fCaisse.getCA_Intitule();
        }

        FCollaborateurDAO fCollaborateurDAO = new FCollaborateurDAO(this.getDataSource());
        FCollaborateur fCollaborateur = fCollaborateurDAO.getCollaborateurJSON(Integer.valueOf(caissier));
        String collaborateurCaissier = "";
        if (fCollaborateur.getCbMarq() != null) {
            collaborateurCaissier = fCollaborateur.getCO_Nom();
            email = fCollaborateur.getCO_EMail();
            collabIntitule = fCollaborateur.getCO_Nom();
            telephone = fCollaborateur.getCO_Telephone();
        }

        FCReglement fcReglement = new FCReglement();
        fcReglement.initVariables();
        fcReglement.setRG_Date(date);
        fcReglement.setRG_DateEchCont(date);
        fcReglement.setJO_Num(joNum);
        fcReglement.setCG_Num(cgNum);
        fcReglement.setCA_No(caNo);
        fcReglement.setCO_NoCaissier(Integer.valueOf(caissier));
        fcReglement.setRG_Libelle(libelle); //libelle.substring(0,34)
        fcReglement.setRG_Montant((double) montant);
        fcReglement.setRG_Impute(impute);
        fcReglement.setRG_Type(rgTypeN);
        fcReglement.setN_Reglement(Integer.valueOf(modeReglementRec));
        fcReglement.setRG_Ticket(0);
        fcReglement.setRG_Banque(banque);
        fcReglement.setCT_NumPayeur(ctNum);
        fcReglement.setCT_NumPayeurOrig(ctNum);
        fcReglement.setCbCreateur(String.valueOf(protNo));
        this.insertF_Reglement(fcReglement);

        if ((!telephone.equals("") || telephone != null) && modeReglementRec.equals("05")) {
            FContactDDAO fContactDDAO = new FContactDDAO(this.getDataSource());
            FContactD fContactD = fContactDDAO.getContactD(1);
            String message = "";
            fContactDDAO.sendMessage(telephone, message);
        }

        if (rgNoLier == 0) {
            String message = "VERSEMENT DISTANT D' UN MONTANT DE " + montant + " AFFECTE AU COLLABORATEUR "
                    + collaborateurCaissier + " POUR LE CLIENT " + ctIntitule + " A DATE DU " + date;
            FProtectioncialDAO fProtectioncialDAO = new FProtectioncialDAO(this.getDataSource());
            if (modeReglementRec.equals("05")) {
                fProtectioncialDAO.getCollaborateurEnvoiMail(message, "Versement distant");
                fProtectioncialDAO.getCollaborateurEnvoiSMS(message, "Versement distant");
            }
        }

        if (rgNoLier != 0) {
            fcReglement = this.getReglementByCbmarq(fcReglement.getCbMarq());
            BigDecimal rgNo = fcReglement.getRG_No();
            this.insertZ_RGLT_BONDECAISSE(rgNo, BigDecimal.valueOf(rgNoLier));

            caNo = 0;
            int coNoCaissier = 0;
            FCReglement fcReglementLier = getFCReglement(BigDecimal.valueOf(rgNoLier));
            if (fcReglementLier.getCbMarq() != null) {
                caNo = fcReglementLier.getCA_No();
                coNoCaissier = fcReglementLier.getCO_NoCaissier();
                this.getMajCaisseRGNo(caNo, coNoCaissier, rgNo);
            }
            this.updateImpute(rgNoLier);
        }
        this.incrementeCOLREGLEMENT();

        return fcReglement.getCbMarq();
    }

    public void getMajCaisseRGNo(int caNo, int coNo, BigDecimal rgNo) {
        String sql = FCReglementMapper.getMajCaisseRGNo;
        params = new Object[]{caNo, coNo, rgNo};
        this.getJdbcTemplate().update(sql, params);
    }

    public List<Object> listeReglementCaisse(String dateDeb, String dateFin, int caNo, int type, int protNo) {
        String listeCaisse = "";


        String sql = "BEGIN \n" +
                "                        DECLARE @ProtNo AS INT\n" +
                "                        DECLARE @CA_No AS INT\n" +
                "                        DECLARE @DateDeb AS VARCHAR(10)\n" +
                "                        DECLARE @DateFin AS VARCHAR(10)\n" +
                "                        DECLARE @Type AS INT\n" +
                "                        \n" +
                "                        SET @ProtNo = " + protNo +
                "                        SET @CA_No = " + caNo +
                "                        SET @DateDeb = '" + dateDeb + "'" +
                "                        SET @DateFin = '" + dateFin + "'" +
                "                        SET @Type = " + type +
                "                        ;" +
                "                        IF OBJECT_ID('tempdb..#TMPCAISSE') IS NOT NULL \n" +
                "                          DROP TABLE #TMPCAISSE;" +
                "                        IF OBJECT_ID('tempdb..#tmpTrsft') IS NOT NULL \n" +
                "                          DROP TABLE #tmpTrsft;" +
                "                        CREATE TABLE #TMPCAISSE (CA_No INT)\n" +
                "                        SET NOCOUNT ON;\n" +
                "                        \n" +
                "                        IF (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE Prot_No=@ProtNo) = 1 \n" +
                "                        BEGIN \n" +
                "                            INSERT INTO #TMPCAISSE\n" +
                "                            SELECT\tISNULL(CA.CA_No,0) CA_No \n" +
                "                            FROM F_CAISSE CA\n" +
                "                            INNER JOIN Z_DEPOTCAISSE C \n" +
                "                                ON CA.CA_No=C.CA_No\n" +
                "                            INNER JOIN F_DEPOT D \n" +
                "                                ON C.DE_No=D.DE_No\n" +
                "                            INNER JOIN F_COMPTET CT \n" +
                "                                ON CT.CT_Num = CA.CT_Num\n" +
                "                            WHERE (@CA_No IN (0,-1) OR @CA_No=CA.CA_No)\n" +
                "                            GROUP BY CA.CA_No\n" +
                "                        END \n" +
                "                        ELSE \n" +
                "                        BEGIN \n" +
                "                            INSERT INTO #TMPCAISSE\n" +
                "                            SELECT\tISNULL(CA.CA_No,0) CA_No\n" +
                "                            FROM F_CAISSE CA\n" +
                "                            LEFT JOIN Z_DEPOTCAISSE C \n" +
                "                                ON CA.CA_No=C.CA_No\n" +
                "                            LEFT JOIN (\tSELECT * \n" +
                "                                        FROM Z_DEPOTUSER\n" +
                "                                        WHERE IsPrincipal=1) D \n" +
                "                                ON C.DE_No=D.DE_No\n" +
                "                            LEFT JOIN F_COMPTET CT \n" +
                "                                ON CT.CT_Num = CA.CT_Num\n" +
                "                            WHERE Prot_No=@ProtNo\n" +
                "                            AND\t(@CA_No=0 OR @CA_No=CA.CA_No)\n" +
                "                            GROUP BY CA.CA_No\n" +
                "                        END;\n" +
                "\n" +
                "SELECT RG_No,RG_Piece,CA_No,16 RG_TypeReg,CG_Num\n" +
                "                    ,RG_Banque,RG_Type,RG_Montant,RG_Date\n" +
                "                    ,RG_Impute,RG_Libelle,CO_NoCaissier\n" +
                "                    ,CA_No_Dest\n" +
                "                    ,RG_No_Source,RG_No_Dest\n" +
                "                    ,JO_Num into #tmpTrsft\n" +
                "FROM (          SELECT  count(*) nb,0 RG_No,RG_Piece\n" +
                "                        ,SUM(CASE WHEN RG_TypeReg=4 THEN CA_No ELSE 0 END)CA_No,16 RG_TypeReg,CG_Num\n" +
                "                        ,SUM(CASE WHEN RG_TypeReg=4 THEN RG_No ELSE 0 END)RG_No_Source\n" +
                "                        ,SUM(CASE WHEN RG_TypeReg=5 THEN RG_No ELSE 0 END)RG_No_Dest\n" +
                "                        ,RG_Banque,RG_Type,RG_Montant,RG_Date\n" +
                "                        ,RG_Impute,RG_Libelle,CO_NoCaissier\n" +
                "                        ,SUM(CASE WHEN RG_TypeReg=5 THEN CA_No ELSE 0 END)CA_No_Dest\n" +
                "                        ,'' JO_Num \n" +
                "                FROM    F_CREGLEMENT \n" +
                "                WHERE   ((@Type) IN(-1,16) AND RG_TypeReg IN (5,4))\n" +
                "                AND     RG_Banque = 0\n" +
                "                GROUP BY RG_Piece,CG_Num\n" +
                "                    ,RG_Banque,RG_Type,RG_Montant,RG_Date\n" +
                "                    ,RG_Impute,RG_Libelle,CO_NoCaissier)A\n" +
                "                    \tWHERE nb=2\n" +
                "                    \t\n" +
                "                    SELECT C.RG_No,RG_Piece,C.CA_No,CA_No_Dest,CO_Nom\n" +
                "                          ,CA_Intitule,CO.CO_No,RG_TypeReg\n" +
                "                          ,C.CG_Num,CONCAT(CONCAT(C.CG_Num,' - '),CG_Intitule) CG_Intitule\n" +
                "                          ,RG_Banque,RG_Type,RG_Montant,CAST(RG_Date AS DATE) RG_Date\n" +
                "                          ,RG_Impute,RG_Libelle,Lien_Fichier\n" +
                "                          ,ISNULL(ZCPTEA.CA_Num,'') CA_Num\n" +
                "                          ,ZCPTEA.CA_IntituleText\n" +
                "                          ,RG_No_Source,RG_No_Dest \n" +
                "                          ,C.JO_Num \n" +
                "                FROM (  \n" +
                "                    SELECT RG_No,RG_Piece,CA_No,RG_TypeReg,CG_Num\n" +
                "                    ,RG_Banque,RG_Type,RG_Montant,RG_Date\n" +
                "                    ,RG_Impute,RG_Libelle,CO_NoCaissier\n" +
                "                    ,CA_No_Dest\n" +
                "                    ,RG_No_Source,RG_No_Dest \n" +
                "                    ,JO_Num \n" +
                "                    FROM #tmpTrsft\n" +
                "\n" +
                "                    UNION\n" +
                "                        SELECT  RG_No,RG_Piece,CA_No,6 RG_TypeReg,CG_Num\n" +
                "                                ,RG_Banque,RG_Type,RG_Montant,RG_Date\n" +
                "                                ,RG_Impute,RG_Libelle,CO_NoCaissier\n" +
                "                                ,0 CA_No_Dest,RG_No RG_No_Source,0 RG_No_Dest \n" +
                "                                ,JO_Num \n" +
                "                        FROM    F_CREGLEMENT \n" +
                "                        WHERE   (('-1' IN (@Type) AND RG_TypeReg IN ('5') AND RG_Banque=1) \n" +
                "                        OR (@Type=6 AND RG_TypeReg IN (5) AND RG_Banque=1))\n" +
                "                        UNION\n" +
                "                        SELECT  RG_No,RG_Piece,CA_No,RG_TypeReg,CG_Num\n" +
                "                                ,RG_Banque,RG_Type,RG_Montant,RG_Date\n" +
                "                                ,RG_Impute,RG_Libelle,CO_NoCaissier\n" +
                "                                ,0 CA_No_Dest,RG_No RG_No_Source,0 RG_No_Dest \n" +
                "                                ,JO_Num \n" +
                "                        FROM    F_CREGLEMENT \n" +
                "                        WHERE   \n" +
                "                        (RG_No NOT IN (SELECT RG_No_Source FROM #tmpTrsft) AND RG_No NOT IN (SELECT RG_No_Dest FROM #tmpTrsft))\n" +
                "                        AND ('-1' IN (@Type) AND (((RG_TypeReg IN ('2','4','3','5') AND RG_Banque=0) OR (RG_TypeReg=4 AND RG_Banque=1)) ) \n" +
                "                        OR (@Type NOT IN (6,4) AND RG_TypeReg =@Type) \n" +
                "                        OR (@Type=6 AND RG_TypeReg =4 AND RG_Banque=1)\n" +
                "                        OR (@Type=5 AND RG_TypeReg =5 AND RG_Banque=0)\n" +
                "                        OR (@Type=4 AND RG_TypeReg =4 AND RG_Banque=0))) C\n" +
                "                LEFT JOIN F_CAISSE CA \n" +
                "                    ON C.CA_No=CA.CA_No\n" +
                "                LEFT JOIN F_COMPTEG CptG \n" +
                "                    ON CptG.CG_Num=C.CG_Num\n" +
                "                LEFT JOIN ( SELECT RG_No,A.CA_Num,CONCAT(CONCAT(A.CA_Num,' - '),CA_Intitule) CA_IntituleText\n" +
                "                            FROM Z_RGLT_COMPTEA A\n" +
                "                            INNER JOIN F_COMPTEA B ON A.CA_Num=B.CA_Num) ZCPTEA \n" +
                "                    ON ZCPTEA.RG_No=C.RG_No\n" +
                "                LEFT JOIN Z_REGLEMENTPIECE RG\n" +
                "                    ON RG.RG_No=C.RG_No\n" +
                "                LEFT JOIN F_COLLABORATEUR CO \n" +
                "                    ON C.CO_NoCaissier=CO.CO_No\n" +
                "                WHERE RG_Date BETWEEN @DateDeb AND @DateFin \n" +
                "                AND (C.CA_No IN (SELECT CA_No FROM #TMPCAISSE))\n" +
                "                AND C.RG_No NOT IN (SELECT RG_NoCache FROM [dbo].[Z_RGLT_VRSTBANCAIRE])\n" +
                "                ORDER BY C.RG_No\n" +
                "END;";
        System.out.println(sql);
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public List<Object> getReglementByClientFacture(BigDecimal cbMarq) {
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarq);

        String sql = FCReglementMapper.getReglementByClientFacture;
        params = new Object[]{fDocEntete.getDO_Tiers(), fDocEntete.getDO_Piece(), fDocEntete.getDO_Type(), fDocEntete.getDO_Domaine()};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public String regle(BigDecimal cbMarqEntete, String typeFacture, int protNo, int valideRegle, int valideRegltImprime, double mttAvance, double montantTotal, int modeReglement, String dateRglt, String libRglt) {
        FDocEntete fDocEntete = new FDocEntete();
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FProtectioncialDAO fProtectioncialDAO = new FProtectioncialDAO(this.getDataSource());
        FComptetDAO fComptetDAO = new FComptetDAO(this.getDataSource());
        if (cbMarqEntete.compareTo(BigDecimal.ZERO) != 0)
            fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
        if (protNo != 0) {

            FProtectioncial protection = fProtectioncialDAO.connexionProctectionByProtNo(protNo);
            FComptet comptet = fComptetDAO.getF_Comptet(fDocEntete.getDO_Tiers(), fDocEntete.getDO_Domaine());
            int rg_type = 0;
            if (valideRegle == 0)
                valideRegle = 1;
            String entete = fDocEntete.getDO_Piece();
            int caisse = fDocEntete.getCA_No();
            int do_domaine = fDocEntete.getDO_Domaine();
            int do_type = fDocEntete.getDO_Type();
            double mtt = 0;
            String dateEch = "";
            if (valideRegle == 1) {
                if (valideRegltImprime != 0) {
                    dateEch = dateRglt;
                } else {
                    mtt = fDocEnteteDAO.montantRegle(fDocEntete);
                }

                if (mttAvance != 0) {
                    addCReglementFacture(cbMarqEntete, mttAvance, comptet.getCT_Type(), modeReglement, caisse, dateRglt, libRglt, dateEch, protNo);
                }
            }
            //if (valideRegltImprime!=0) {
            //        doImprim($docEntete->cbMarq);
        }
        return "ok";
    }

    public void addCReglementFacture() {
        String sql = FCReglementMapper.getReglementByClientFacture;
    }

    public void addCReglementFacture(BigDecimal cbMarqEntete, double montant, int rg_type, int mode_reglement, int caisse, String date_reglt, String lib_reglt, String date_ech, int protNo) {
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FProtectioncialDAO fProtectioncialDAO = new FProtectioncialDAO(this.getDataSource());
        FReglEchDAO fReglEchDAO = new FReglEchDAO(this.getDataSource());
        FCaisseDAO fCaisseDAO = new FCaisseDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
        FCReglement creglement = new FCReglement();
        creglement.initVariables();
        String doDate = date_reglt;
        String ctNum = fDocEntete.getDO_Tiers();
        int deNo = fDocEntete.getDE_No();
        String caNum = fDocEntete.getCA_Num();
        String doRef = fDocEntete.getDO_Ref();
        int coNo = fDocEntete.getCO_No();
        int coNocaissier = 0;
        int caNo = 0;
        String joNum = "";
        if (protNo != 0) {
            FProtectioncial protection = fProtectioncialDAO.connexionProctectionByProtNo(protNo);
            coNo = fProtectioncialDAO.getCoNo(protection.getProt_No());
            if (coNo == 0)
                coNo = fDocEntete.getCO_No();
        }

        String cg_num = fDocEntete.getCG_Num();
        int doDebise = fDocEntete.getDO_Devise();
        double doCours = fDocEntete.getDO_Cours();
        int doDomaine = fDocEntete.getDO_Domaine();
        int doType = fDocEntete.getDO_Type();
        FCaisse caisseVal = fCaisseDAO.getF_Caisse(caisse);
        if (caisseVal.getCA_No() != 0) {
            coNocaissier = caisseVal.getCO_NoCaissier();
            caNo = caisseVal.getCA_No();
            joNum = caisseVal.getJO_Num();
        }
        lib_reglt = lib_reglt + "_" + fDocEntete.getDO_Ref();
        int maxLength = (lib_reglt.length() < 30) ? lib_reglt.length() : 30;

        int ticket = 0;
        if (doType == 30) ticket = 1;
        creglement.setRG_Date(doDate);
        creglement.setCT_NumPayeur(ctNum);
        creglement.setCT_NumPayeurOrig(ctNum);
        creglement.setCA_No(caisse);
        creglement.setCG_Num(cg_num);
        creglement.setRG_Reference(doRef);
        creglement.setJO_Num(joNum);
        creglement.setCO_NoCaissier(coNo);
        creglement.setRG_Montant(montant);
        creglement.setRG_Libelle((lib_reglt + fDocEntete.getDO_Ref()).substring(0, maxLength));
        creglement.setRG_Impute(1);
        creglement.setRG_Type(rg_type);
        creglement.setN_Reglement(mode_reglement);
        creglement.setRG_TypeReg(0);
        creglement.setRG_Ticket(ticket);
        creglement.setRG_Banque(0);
        creglement.setN_Devise(doDebise);
        creglement.setRG_Cours(doCours);
        creglement.setRG_DateEchCont(doDate);
        creglement.setCbCreateur(String.valueOf(protNo));
        insertF_Reglement(creglement);
        creglement = getReglementByCbmarq(creglement.getCbMarq());
        incrementeCOLREGLEMENT();
        FDocReglDAO fDocReglDAO = new FDocReglDAO(getDataSource());
        int drNo = fDocReglDAO.getDocReglByDO_PieceDRNo(fDocEntete.getCbMarq());
        double montantTTC = fDocEnteteDAO.montantRegle(fDocEntete);
        double montantTTC_regle = fDocEnteteDAO.AvanceDoPiece(fDocEntete);
        double reste_a_regler = montantTTC - montantTTC_regle;
        FReglEch fReglEch = new FReglEch();
        fReglEch.setCbCreateur(String.valueOf(protNo));
        fReglEch.setRcMontant(reste_a_regler);
        fReglEch.setDO_Piece(fDocEntete.getDO_Piece());
        fReglEch.setDO_Type(fDocEntete.getDO_Type());
        fReglEch.setDO_Domaine(fDocEntete.getDO_Domaine());
        fReglEch.setDR_No(drNo);
        fReglEch.setRG_No(creglement.getRG_No());
        fReglEch.setRG_TypeReg(creglement.getRG_TypeReg());

        if ((reste_a_regler >= 0 && montant > reste_a_regler) || (reste_a_regler < 0 && montant < reste_a_regler)) {
            fReglEchDAO.addReglEch(fReglEch);
        } else {
            fReglEch.setRcMontant(montant);
            fReglEchDAO.addReglEch(fReglEch);

            if (montant == reste_a_regler) {
                fDocReglDAO.updateDrRegle(drNo);
                updateImpute(creglement.getRG_No());
            }
        }
        /*
        if((reste_a_regler>=0 && montant>reste_a_regler) || (reste_a_regler<0 && montant<reste_a_regler)){
            fReglEchDAO.addReglEch(creglement.getRG_No(),drNo,fDocEntete.getDO_Domaine(),fDocEntete.getDO_Type(),fDocEntete.getDO_Piece(),reste_a_regler);
        }else{
            fReglEchDAO.addReglEch(creglement.getRG_No(),drNo,fDocEntete.getDO_Domaine(),fDocEntete.getDO_Type(),fDocEntete.getDO_Piece(),montant);
            if(montant==reste_a_regler){
                fDocReglDAO.updateDrRegle(drNo);
                updateImpute(creglement.getRG_No());
            }
        }
        fDocReglDAO.updateDateRegle(date_ech,drNo);

         */
        //return $rg_no;
    }


    public void updateImpute(int rgNo) {
        String sql = FCReglementMapper.updateImpute;
        params = new Object[]{rgNo};
        this.getJdbcTemplate().update(sql, params);
    }

    public void incrementeCOLREGLEMENT() {
        String sql = FCReglementMapper.incrementeCOLREGLEMENT;
        this.getJdbcTemplate().execute(sql);
    }

    public static int mapParams(PreparedStatement ps, Object... args) throws SQLException {
        int i = 1;
        for (Object arg : args) {
            if (arg instanceof Date) {
                ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Long) {
                ps.setLong(i++, (Long) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (double) arg);
            } else {
                ps.setString(i++, (String) arg);
            }
        }
        return 0;
    }

    public FCReglement getReglementByCbmarq(BigDecimal cbMarq) {
        String sql = FCReglementMapper.getReglementByCbmarq;
        params = new Object[]{cbMarq};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FCReglement fcReglement = new FCReglement();
        while (sqlRowSet.next()) {
            fcReglement.setRG_No(sqlRowSet.getBigDecimal("RG_No"));
            fcReglement.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
            fcReglement.setCbCreateur(sqlRowSet.getString("cbCreateur"));
            fcReglement.setRG_Date(sqlRowSet.getString("RG_Date"));
            fcReglement.setCA_No(sqlRowSet.getInt("CA_No"));
            fcReglement.setCG_Num(sqlRowSet.getString("CG_Num"));
            fcReglement.setCO_NoCaissier(sqlRowSet.getInt("CO_NoCaissier"));
            fcReglement.setCT_NumPayeur(sqlRowSet.getString("CT_NumPayeur"));
            fcReglement.setJO_Num(sqlRowSet.getString("JO_Num"));
            fcReglement.setN_Devise(sqlRowSet.getInt("N_Devise"));
            fcReglement.setN_Reglement(sqlRowSet.getInt("N_Reglement"));
            fcReglement.setRG_Banque(sqlRowSet.getInt("RG_Banque"));
            fcReglement.setRG_Cours(sqlRowSet.getDouble("RG_Cours"));
            fcReglement.setRG_DateEchCont(sqlRowSet.getString("RG_DateEchCont"));
            fcReglement.setRG_Impute(sqlRowSet.getInt("RG_Impute"));
            fcReglement.setRG_Libelle(sqlRowSet.getString("RG_Libelle"));
            fcReglement.setRG_Montant(sqlRowSet.getDouble("RG_Montant"));
            fcReglement.setRG_Reference(sqlRowSet.getString("RG_Reference"));
            fcReglement.setRG_Ticket(sqlRowSet.getInt("RG_Ticket"));
            fcReglement.setRG_Type(sqlRowSet.getInt("RG_Type"));
            fcReglement.setRG_TypeReg(sqlRowSet.getInt("RG_TypeReg"));
            fcReglement.setRG_Cloture(sqlRowSet.getInt("RG_Cloture"));
        }
        return fcReglement;
    }

    public void insertF_Reglement(FCReglement fcReglement) {

        String sql = FCReglementMapper.addCReglementFacture;
        params = new Object[]{fcReglement.getCT_NumPayeur(), fcReglement.getRG_Date(), fcReglement.getRG_Reference(), fcReglement.getRG_Libelle(), fcReglement.getRG_Montant(), fcReglement.getRG_MontantDev()
                , fcReglement.getN_Reglement(), fcReglement.getRG_Impute(), fcReglement.getRG_Compta(), fcReglement.getEC_No(), fcReglement.getRG_Type(), fcReglement.getRG_Cours(), fcReglement.getN_Devise()
                , fcReglement.getJO_Num(), fcReglement.getCG_NumCont(), fcReglement.getRG_Impaye(), fcReglement.getCG_Num(), fcReglement.getRG_TypeReg(), fcReglement.getRG_Heure()
                , fcReglement.getCA_No(), fcReglement.getCO_NoCaissier(), fcReglement.getRG_Banque(), fcReglement.getRG_Transfere(), fcReglement.getRG_Cloture(), fcReglement.getRG_Ticket(), fcReglement.getRG_Souche()
                , fcReglement.getCT_NumPayeurOrig(), fcReglement.getRG_DateEchCont(), fcReglement.getCG_NumEcart(), fcReglement.getJO_NumEcart(), fcReglement.getRG_MontantEcart(), fcReglement.getRG_NoBonAchat()
                , fcReglement.getCbCreateur()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql,params); // Not update, you're returning a ResultSet.
        while (sqlRowSet.next()) {
            fcReglement.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
        }
        sql = FCReglementMapper.getRgNoFromCbMarq;
        params = new Object[]{fcReglement.getCbMarq()};
        sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql,params);
        while (sqlRowSet.next()) {
            fcReglement.setRG_No(sqlRowSet.getBigDecimal("RG_No"));
        }
    }
}
