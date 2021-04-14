package com.server.itsolution.dao;

import com.server.itsolution.entities.FComptet;
import com.server.itsolution.entities.FDocEntete;
import com.server.itsolution.entities.FEModeleR;
import com.server.itsolution.mapper.FComptetMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class FComptetDAO extends JdbcDaoSupport {

    @Autowired
    public FComptetDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FComptetMapper mapper = new FComptetMapper();

    public List<Object> getAll() {
        String sql = FComptetMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
	
    public List<Object> allClientsSelect() {
        String sql = FComptetMapper.allClientsSelect;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }


    public List<Object> getF_ComptetJSON(String ctNum) {
        String sql = FComptetMapper.getFComptet;
        params = new Object[]{ctNum, -1, -1};
        List<Object> list = this.getJdbcTemplate().query(sql, params,mapper);
        return list;
    }

        public FComptet getF_Comptet(String ctNum, int ctType) {
        String sql = FComptetMapper.getFComptet;
        params = new Object[] {ctNum,ctType,ctType};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FComptet fComptet = new FComptet();
        while(sqlRowSet.next()) {
            fComptet.setCT_Num(sqlRowSet.getString("CT_Num"));
            fComptet.setCT_Intitule(sqlRowSet.getString("CT_Intitule"));
            fComptet.setMR_No(sqlRowSet.getInt("MR_No"));
            fComptet.setCT_Type(sqlRowSet.getInt("CT_Type"));
            fComptet.setCT_BLFact(sqlRowSet.getInt("CT_BLFact"));
            fComptet.setCA_Num(sqlRowSet.getString("CA_Num"));
            fComptet.setCG_NumPrinc(sqlRowSet.getString("CG_NumPrinc"));
            fComptet.setN_Condition(sqlRowSet.getInt("N_Condition"));
            fComptet.setN_Devise(sqlRowSet.getInt("N_Devise"));
            fComptet.setN_Expedition(sqlRowSet.getInt("N_Expedition"));
            fComptet.setCT_Langue(sqlRowSet.getInt("CT_Langue"));
            fComptet.setCT_Facture(sqlRowSet.getInt("CT_Facture"));
            fComptet.setN_Period(sqlRowSet.getInt("N_Period"));
            fComptet.setCT_Facture(sqlRowSet.getInt("CT_Facture"));
            fComptet.setCT_Taux02((int)(sqlRowSet.getFloat("CT_Taux02")));
            fComptet.setCT_NumPayeur(sqlRowSet.getString("CT_NumPayeur"));
            fComptet.setN_CatCompta(sqlRowSet.getInt("N_CatCompta"));
            fComptet.setCT_ControlEnc(sqlRowSet.getString("CT_ControlEnc"));
            fComptet.setCO_No(sqlRowSet.getInt("CO_No"));
            fComptet.setCbMarq(sqlRowSet.getInt("cbMarq"));
        }
        return fComptet;
    }

    public List<Object> getOptionModeleReglementByMRNo(int MR_No) {

        String sql = FComptetMapper.getOptionModeleReglementByMRNo;
        params = new Object[]{MR_No};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }


    public List<Object> getTiersByNumIntitule(String ctNum,int ctType,int all) {
        String sql = FComptetMapper.getTiersByNumIntitule;
        params = new Object[]{ctType,ctNum,all};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public Double getMontantAutorise(String ctNum,double montant){
        String sql = FComptetMapper.getMontantAutorise;
        params = new Object[]{ctNum,montant};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        Double montantAutorise=Double.valueOf(0);
        while(sqlRowSet.next()) {
            montantAutorise=sqlRowSet.getDouble("MontantAutorise");
        }
        return montantAutorise;
    }

    public net.minidev.json.JSONObject controleEncours(FComptet fcomptet, String typeFacG, double prixG, String acte, double dlMontantTTC){
        net.minidev.json.JSONObject json = new net.minidev.json.JSONObject();

        if(typeFacG.equals("Vente") || typeFacG.equals("BonLivraison") || typeFacG.equals("VenteRetour")){
            double montant = prixG;
            if(acte.equals("modif")){
                montant = montant-dlMontantTTC;
            }
            if(fcomptet.getCT_ControlEnc().equals("2")){
                json.put("message", "Ce compte client est bloqué !");
                return json;
            }

            Double montantAutorise = this.getMontantAutorise(fcomptet.getCT_Num(),montant);

            if(montantAutorise<0) {
                json.put("message", "Le montant de la dette ne doit pas dépasser "+fcomptet.getCT_Encours()+" !");
                return json;
            }
        }
        return null;
    }

    public List<Object> getTiersByNumIntituleListe(String term,String typeFacture,int all){
        FDocEntete fDocEntete = new FDocEntete();
        fDocEntete.setTypeFac(typeFacture);
        return this.getTiersByNumIntitule(term,fDocEntete.getCtType(),all);
    }


    public List<Object> getTiersByNumIntituleSearch(String intitule,int type, int ctSommeil){
        if(type!=2) {
            String typeSelect = "TOUT LES CLIENTS";
            if(type==1)
                typeSelect = "TOUT LES FOURNISSEURS";
            String sql = FComptetMapper.getTiersByNumIntituleSearch;
            ArrayList<Object> params = new ArrayList<Object>();
            params.add(type);
            params.add(ctSommeil);
            params.add(intitule);
            params.add(typeSelect);
            return  this.getJdbcTemplate().query(sql, params.toArray(),mapper);
        }else{
            FCollaborateurDAO fCollaborateurDAO = new FCollaborateurDAO(this.getDataSource());
            return fCollaborateurDAO.getCaissierByIntitule(intitule);
        }
    }

    public int getFLivraisonByCTNum ( String CT_Num){
        String sql = FComptetMapper.getFLivraisonByCTNum;
        params = new Object[]{CT_Num};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        int liNo=0;
        while(sqlRowSet.next()) {
            liNo=sqlRowSet.getInt("LI_No");
        }
        return liNo;
    }

    public List<Object> allClientShort(int sommeil) {
        String sql = FComptetMapper.allClientShort;
        params = new Object[]{sommeil,sommeil};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> getDepotClient(int deNo,int ctType) {
        String sql = FComptetMapper.getDepotClient;
        params = new Object[]{deNo,ctType};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

public List<Object> queryListeClient(int ctType,int ctSommeil,String searchString,String orderBy,String orderType,String start,String length) {

    String search = "";
    if(!searchString.equals(""))
        search = " WHERE CT_Num LIKE '%"+searchString+"%' OR CT_Intitule LIKE '%"+searchString+"%'";

    String orderByParam = "";
    if(!orderBy.equals(""))
        orderByParam = " ORDER BY "+orderBy+" "+orderType+ " OFFSET "+ start + " ROWS FETCH NEXT "+ length +" ROWS ONLY";

    String query="          DECLARE @CT_Type AS INT = "+ctType+" " +
            "					DECLARE @CT_Sommeil AS INT = "+ctSommeil+" " +
            "					SELECT * " +
                            "   FROM (SELECT CT_Num,C.CT_Intitule,CG_NumPrinc,P.CT_Intitule AS LibCatTarif,LibCatCompta,PROT_User " +
            "                                            FROM F_COMPTET C  " +
            "                                            LEFT JOIN P_CATTARIF P " +
                        "                                   ON P.cbIndice = C.N_CatTarif  " +
            "                                            LEFT JOIN F_PROTECTIONCIAL Pr " +
                            "                               ON CAST(Pr.PROT_No AS VARCHAR(5)) = C.cbCreateur " +
            "                                             LEFT JOIN (   SELECT  row_number() over (order by u.subject) as idcompta, u.LibCatCompta  " +
            "                                                           FROM    P_CATCOMPTA  " +
            "                                             UNPIVOT  " +
            "                                                   (  " +
            "                                                    LibCatCompta  " +
            "                                             FOR subject IN (CA_ComptaVen01, CA_ComptaVen02, CA_ComptaVen03, CA_ComptaVen04, CA_ComptaVen05, CA_ComptaVen06, CA_ComptaVen07, CA_ComptaVen08, CA_ComptaVen09, CA_ComptaVen10, CA_ComptaVen11, CA_ComptaVen12, CA_ComptaVen13, CA_ComptaVen14, CA_ComptaVen15, CA_ComptaVen16, CA_ComptaVen17, CA_ComptaVen18, CA_ComptaVen19, CA_ComptaVen20, CA_ComptaVen21, CA_ComptaVen22) " +
            "                                             ) u) M ON M.idcompta = C.N_CatCompta " +
                "                           WHERE   CT_Type=@CT_Type " +
                "                           AND     (-1 = @CT_Sommeil OR CT_Sommeil=@CT_Sommeil)" +
                "                   )A " + search + " " + orderByParam +
                " ; \n";
        return this.getJdbcTemplate().query(query, mapper);
    }

    public List<Object> allFournisseurShort(int sommeil) {
        String sql = FComptetMapper.allFournisseurShort;
        params = new Object[]{sommeil,sommeil};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public Object getModeleReglementCount() {
        String sql = FComptetMapper.getModeleReglementCount;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list.get(0);
    }

    public Object allTiersMax() {
        String sql = FComptetMapper.allTiersMax;
        return (this.getJdbcTemplate().query(sql, mapper)).get(0);
    }

    public List<Object> getCodeAuto(String type) {
        String sql = FComptetMapper.getCodeAuto;
		ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(type);
        List<Object> list = this.getJdbcTemplate().query(sql, parames.toArray(), mapper);
        return list;
    }

    public List<Object> tiersByCTIntitule(String ctIntitule) {
        String sql = FComptetMapper.tiersByCTIntitule;
		ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(ctIntitule);
        List<Object> list = this.getJdbcTemplate().query(sql, parames.toArray(), mapper);
        return list;
    }

    public List<Object> remplacementTiers(String ctNumNouveau,String ctNumAncien) {
        String sql = FComptetMapper.tiersByCTIntitule;
		ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(ctNumNouveau);
        parames.add(ctNumAncien);
        List<Object> list = this.getJdbcTemplate().query(sql, parames.toArray(), mapper);
        return list;
    }

    public List<Object> TiersDoublons() {
        String sql = FComptetMapper.TiersDoublons;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
	
    public List<Object> allFournisseurSelect() {
        String sql = FComptetMapper.allFournisseurSelect;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public void modifClientUpdateCANum(String ctNum,String caNum){
        String sql = FComptetMapper.modifClientUpdateCANum;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(caNum);
        parames.add(ctNum);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public void majMRNo(String ctNum,int mrNo){
        FEModeleRDAO feModeleRDAO = new FEModeleRDAO(this.getDataSource());
        FReglementTDAO fReglementTDAO = new FReglementTDAO(this.getDataSource());
        FEModeleR feModeleR = feModeleRDAO.findByMRNo(mrNo);
        fReglementTDAO.insert(ctNum,feModeleR.getER_Condition(),feModeleR.getER_NbJour(),feModeleR.getER_JourTb01(),feModeleR.getER_TRepart(),feModeleR.getER_VRepart());
    }

    public void maj(String nom, String valeur, BigDecimal cbMarq, String cbCreateur) {
        String cbCreateurSql="";
        if(!cbCreateur.equals(""))
            cbCreateurSql=",cbCreateur='"+cbCreateur+"'";
        String updateQuery = "UPDATE F_COMPTET SET "+nom+" = '"+valeur+"' "+cbCreateurSql+" WHERE cbMarq = "+cbMarq;
        this.getJdbcTemplate().update(updateQuery);
    }

	public void createClientMin(String ctNum,String ctIntitule,String ctType,String cgNumPrinc,String ctAdresse,String ctCodePostal,String ctVille,String ctCodeRegion,String ctIdentifiant,String ctSiret,int coNo
								,int nCatTarif,int nCatCompta, int deNo, String caNum,String ctTelephone,int mrNo,String cbCreateur) {
        FComptet fComptet = getF_Comptet(ctNum,Integer.valueOf(ctType));
        if(fComptet.getCbMarq()==null) {
            String sql = FComptetMapper.createClientMin;
            ArrayList<Object> parames = new ArrayList<Object>();
            parames.add(ctNum);
            parames.add(ctIntitule);
            parames.add(ctType);
            parames.add(cgNumPrinc);
            parames.add(ctAdresse);
            parames.add(ctCodePostal);
            parames.add(ctVille);
            parames.add(ctCodeRegion);
            parames.add(ctIdentifiant);
            parames.add(ctSiret);
            parames.add(coNo);
            parames.add(nCatTarif);
            parames.add(nCatCompta);
            parames.add(deNo);
            parames.add(caNum);
            parames.add(ctTelephone);
            parames.add(mrNo);
            parames.add(cbCreateur);
            this.getJdbcTemplate().update(sql, parames.toArray());
            if (mrNo != 0)
                majMRNo(ctNum, mrNo);
            sql = FComptetMapper.insertFComptetg;
            parames = new ArrayList<Object>();
            parames.add(ctNum);
            parames.add(cgNumPrinc);
            this.getJdbcTemplate().update(sql, parames.toArray());

            if (ctType.equals("0")) {
                sql = FComptetMapper.insertFLivraison;
                parames = new ArrayList<Object>();
                parames.add(ctNum);
                parames.add(ctIntitule);
                parames.add(ctAdresse);
                parames.add("");
                parames.add(ctCodePostal);
                parames.add(ctVille);
                parames.add(ctCodeRegion);
                parames.add("");
                parames.add("");
                parames.add(1);
                parames.add(1);
                parames.add("");
                parames.add("");
                parames.add(ctTelephone);
                this.getJdbcTemplate().update(sql, parames.toArray());
            }
        }
    }
	
	public List<Object> rafraichir_listeClient(String typeFacture){
		if(!typeFacture.equals("Achat") && !typeFacture.equals("PreparationCommande"))
			return this.allClientShort(-1);
		else
			return this.allFournisseurShort(-1);
	}
}


