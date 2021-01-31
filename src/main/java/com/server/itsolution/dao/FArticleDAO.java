package com.server.itsolution.dao;

import com.server.itsolution.entities.*;
import com.server.itsolution.mapper.FArticleMapper;
import com.server.itsolution.mapper.PCatTarifMapper;
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
public class FArticleDAO extends JdbcDaoSupport {

    @Autowired
    public FArticleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[]{};
    public FArticleMapper mapper = new FArticleMapper();
    public FArticle fArticle = new FArticle();

    public List<Object> getAll(String intitule,int top,int sommeil,int arPublie) {
        String valeurSaisie =intitule.replace(" ","%");
        String value = "";
        if(top!=0)
            value = "TOP "+top;
        String sql = "DECLARE @sommeil AS INT = "+sommeil+
                "		, @arPublie AS INT = "+arPublie+
                "; SELECT  "+value+" AR_Type"+
                "     ,AR_Sommeil"+
                "     ,AR_Ref"+
                "     ,AR_Design"+
                "     ,AR_Ref as id"+
                "     ,CONCAT(CONCAT(AR_Ref,' - '),AR_Design) as text"+
                "     ,CONCAT(CONCAT(AR_Ref,' - '),AR_Design) as value"+
                "   ,AR_PrixAch"+
                "   ,AR_PrixVen"+
                " FROM F_ARTICLE"+
                " WHERE (-1=@arPublie OR AR_Publie=@arPublie)"+
                " AND (-1=@sommeil OR AR_Sommeil=@sommeil)"+
                " AND CONCAT(CONCAT(AR_Ref,' - '),AR_Design) LIKE '%"+valeurSaisie+"%'";
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Object> getAllArticleDispoByArRef(int deNo,String codeFamille,String valeur){
        String sql = FArticleMapper.getAllArticleDispoByArRef;
        params = new Object[] {deNo,codeFamille,valeur};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }


    public List<Object> stockMinDepasse(int deNo,String arRef){
        String sql = FArticleMapper.stockMinDepasse;
        params = new Object[] {deNo,arRef};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public void majRefArticle(String newArRef,String arRef){
        String sql = FArticleMapper.majRefArticle;
        params = new Object[] {newArRef,arRef};
        this.getJdbcTemplate().update(sql, params, mapper);
    }

    public List<Object> articleDoublons(){
        String sql = FArticleMapper.articleDoublons;
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getPrixClient(String arRef,int catCompta,int catTarif){
        String sql = FArticleMapper.getPrixClient;
        params = new Object[] {arRef,catCompta,catTarif};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> allSearch(int arPublie, int sommeil, String valeurSaisie){
        String sql = FArticleMapper.allSearch;
        params = new Object[] {arPublie,sommeil,valeurSaisie};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getArtFournisseurSelect(BigDecimal cbMarq){
        String sql = FArticleMapper.getArtFournisseurSelect;
        params = new Object[] {cbMarq};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getArtFournisseur(String arRef){
        String sql = FArticleMapper.getArtFournisseur;
        params = new Object[] {arRef};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getF_ArticleJSON(String arRef) {
        String sql = FArticleMapper.getFArticle;
        params = new Object[]{arRef};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }
    public List<Object> getShortList() {
        String sql = FArticleMapper.getShortList;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public List<Object> isArticleLigne(String arRef) {
        String sql = FArticleMapper.isArticleLigne;
        params = new Object[]{arRef};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }

    public int deleteArticle(int cbMarq) {
        String sql = FArticleMapper.deleteArticle;
        params = new Object[]{cbMarq};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getInt("value");
        }
        return 0;
    }
    public void majFArtCompta(BigDecimal cbMarq,String val,String champ) {
        String sql = "UPDATE F_ARTCOMPTA SET "+champ+"=(CASE WHEN '"+val+"'='' THEN NULL ELSE '"+val+"' END) WHERE cbMarq="+cbMarq;
        this.getJdbcTemplate().execute(sql);
    }

    public void insertFArtCompta(String arRef,int acpType,int acpChamp,String val,String champ,int acpTypeFacture,String protNo){

        String sql = "DECLARE @arRef AS NVARCHAR(50) = '"+arRef+"'\n" +
                "\t\t,@acpType AS INT = "+acpType+" \n" +
                "\t\t,@acpChamp AS INT = "+acpChamp+"\n" +
                "\t\t,@val AS NVARCHAR(50) = '"+val+"'\n" +
                "\t\t,@acpTypeFacture AS INT = "+acpTypeFacture+"\n" +
                "\t\t,@protNo AS NVARCHAR(10) = '"+protNo+"'\n" +
                "\t\t;\n" +
                "INSERT INTO [dbo].[F_ARTCOMPTA]\n" +
                "           ([AR_Ref]\n" +
                "           ,[ACP_Type]\n" +
                "           ,[ACP_Champ]\n" +
                "           ,["+champ+"]\n" +
                "           ,[ACP_TypeFacture]\n" +
                "           ,[cbProt]\n" +
                "           ,[cbCreateur]\n" +
                "           ,[cbModification]\n" +
                "           ,[cbReplication]\n" +
                "           ,[cbFlag])\n" +
                "     VALUES\n" +
                "           (@arRef,@acpType,@acpChamp,CASE WHEN @val='' THEN NULL ELSE @val END,@acpTypeFacture,0,@protNo,GETDATE(),0,0)";
        this.getJdbcTemplate().execute(sql);
    }

    public List<Object> getArticleAndDepot(String arRef){
        String sql = FArticleMapper.getArticleAndDepot;
        params = new Object[]{arRef};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }

    public List<Object> getCatComptaByArRef(String arRef,int acpChamp,int acpType) {
        String sql = FArticleMapper.getCatComptaByArRef;
        params = new Object[]{arRef,acpType,acpChamp};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }

    public List<Object> getStockDepot(String arRef, int deNo){
        String sql = FArticleMapper.getStockDepot;
        params = new Object[]{arRef,deNo};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getArticleByIntitule(String arIntitule){
        String sql = FArticleMapper.getArticleByIntitule;
        params = new Object[]{arIntitule};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public void updateF_ArtStockBorne(double asQteMini,double asQteMaxi,String cbCreateur,String arRef,int deNo){
        String sql = FArticleMapper.updateF_ArtStockBorne;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(asQteMini);
        parames.add(asQteMaxi);
        parames.add(cbCreateur);
        parames.add(arRef);
        parames.add(deNo);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public List<Object> getArticleByRefDesignation(int deNo,String term,String typeFacture){
        if (typeFacture.equals("Ticket") || typeFacture.equals("AchatRetour") || typeFacture.equals("Vente") || typeFacture.equals("BonLivraison") || typeFacture.equals("Sortie") || typeFacture.equals("Transfert") || typeFacture.equals("Transfert_confirmation") || typeFacture.equals("Transfert_detail"))
            return this.getAllArticleDispoByArRef(deNo,"0",term);
        else
            return this.allSearch(-1,-1,term);
    }

    public Object ajout_article(String reference,String designation
            ,double pxAchat,String faCodeFamille,int condition,double pxHt,double pxMin,double pxMax
            ,double qteGros,double arPrixTTC,int clNo1,int clNo2,int clNo3,int clNo4,String cbCreateur) {
        Message message = new Message();
        message.setCode(1);
        FArticle fArticleInsert = null;
        fArticleInsert = getF_Article(reference);
        FArtClientDAO fArtClientDAO = new FArtClientDAO(this.getDataSource());
        if (fArticleInsert.getCbMarq() == null) {
            fArticleInsert = new FArticle();
            if (!reference.equals(""))
                fArticleInsert.setAR_Ref(reference);
            if (!designation.equals(""))
                fArticleInsert.setAR_Design(designation.replace("'" , "''"));
            fArticleInsert.setAR_PrixAch(pxAchat);
            fArticleInsert.setFA_CodeFamille(faCodeFamille);
            fArticleInsert.setAR_Condition(condition);
            fArticleInsert.setAR_PrixVen(pxHt);
            fArticleInsert.setPrix_Min(pxMin);
            fArticleInsert.setPrix_Max(pxMax);
            fArticleInsert.setCL_No1(clNo1);
            fArticleInsert.setCL_No2(clNo2);
            fArticleInsert.setCL_No3(clNo3);
            fArticleInsert.setCL_No4(clNo4);
            fArticleInsert.setQte_Gros(qteGros);
            fArticleInsert.setAR_PrixTTC(arPrixTTC);
            fArticleInsert.setAR_Nomencl("0");
            fArticleInsert.setAR_QteOperatoire(1);
            fArticleInsert.setAR_QteComp(1);
            fArticleInsert.setAR_SaisieVar("0");
            fArticleInsert.setAR_PUNet(0);
            fArticleInsert.setCbCreateur(cbCreateur);
            insertfArticle(fArticleInsert);
            fArtClientDAO.insertFArtClient(fArticleInsert.getAR_Ref(),1,fArticleInsert.getAR_PrixTTC(),0,0);
            fArtClientDAO.insertFArtClient(fArticleInsert.getAR_Ref(),2,fArticleInsert.getAR_PrixTTC(),0,0);
            insertFArtModele(fArticleInsert);

            message.setCode(0);
            message.setMessage(fArticleInsert.getAR_Ref()+" a été crée !");
            /*
             */
        }
        else
            message.setMessage(fArticleInsert.getAR_Ref()+" existe déjà !");

        return message;
    }

    public void majQteGros(){

    }


    public List<Object> getPxMinMaxCatCompta(String arRef, int acCategorie) {
        String sql = FArticleMapper.getPxMinMaxCatCompta;
        params = new Object[] {arRef,acCategorie};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public void insertFArtModele(FArticle fArticle){
        String sql = FArticleMapper.insertFArtModele;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(fArticle.getAR_Ref());
        parames.add(fArticle.getCbCreateur());
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public void insertfArticle(FArticle fArticle){
        String sql = FArticleMapper.insertfArticle;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(fArticle.getAR_Ref());
        parames.add(fArticle.getAR_Design());
        parames.add(fArticle.getFA_CodeFamille());
        parames.add(fArticle.getAR_PrixAch());
        parames.add(fArticle.getAR_PrixVen());
        parames.add(fArticle.getAR_PrixTTC());
        parames.add(fArticle.getAR_Nomencl());
        parames.add(fArticle.getAR_Condition());
        parames.add(fArticle.getAR_PUNet());
        parames.add(fArticle.getAR_SaisieVar());
        parames.add(fArticle.getAR_QteComp());
        parames.add(fArticle.getAR_QteOperatoire());
        parames.add(fArticle.getCL_No1());
        parames.add(fArticle.getCL_No2());
        parames.add(fArticle.getCL_No3());
        parames.add(fArticle.getCL_No4());
        parames.add(fArticle.getCbCreateur());
        parames.add(fArticle.getPrix_Min());
        parames.add(fArticle.getPrix_Max());
        parames.add(fArticle.getQte_Gros());
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public List<Object> detailConditionnement(String arRef,String tcRefCf) {
        String sql = FArticleMapper.detailConditionnement;
        params = new Object[]{arRef,tcRefCf};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> queryListeArticle(int flagPxAchat,int flagPxRevient,int ar_sommeil,int prixFlag,int stockFlag,String searchString,String orderBy,String orderType,String start,String length) {
        String valAchat = "";
        if(flagPxRevient!=2 && flagPxAchat==0)
            valAchat = ",AR_PrixAch,AR_PrixVen";
        if(flagPxAchat !=0 && flagPxRevient!=2)
            valAchat = ",AR_PrixVen";
        if(flagPxAchat ==0 && flagPxRevient==2)
            valAchat = ",AR_PrixAch";
        String search = "";
        if(!searchString.equals(""))
            search = " WHERE AR_Ref LIKE '%"+searchString+"%' OR AR_Design LIKE '%"+searchString+"%'";

        String orderByParam = "";
        if(!orderBy.equals(""))
            orderByParam = " ORDER BY "+orderBy+" "+orderType+ " OFFSET "+ start + " ROWS FETCH NEXT "+ length +" ROWS ONLY";

        String query="        BEGIN\n" +
                "        SET NOCOUNT ON;\n" +
                "        DECLARE @Sommeil AS INT,\n" +
                "                @Stock AS INT,\n" +
                "                @Prix AS INT,\n" +
                "                @prot_admin AS INT;\n" +
                "\n" +
                "        SET @Sommeil = "+ar_sommeil+
                "        SET @Stock = "+stockFlag+
                "        SET @Prix = "+prixFlag+
                "\n" +
                "        SELECT *\n" +
                "                FROM(select           AR_Sommeil\n" +
                "                        ,A.AR_Ref\n" +
                "                        ,AR_Design\n" +
                "                        ,FA_CodeFamille\n" +
                "                        ,ROUND(ISNULL(AS_QteSto,0),2) AS_QteSto\n" +
                "                        ,ROUND(ISNULL(AS_QteStoCumul,0),0) AS_QteStoCumul\n" +
                "                        ,ROUND(ISNULL(AS_MontSto,0),2) AS_MontSto\n" +
                "                        ,Prix_Min\n" +
                "                        ,Prix_Max\n" +
                "                        ,PROT_User\n" +
                "                        " +valAchat+
                "                        FROM F_ARTICLE A\n" +
                "                        LEFT JOIN F_PROTECTIONCIAL P ON A.cbCreateur = CAST(P.PROT_No AS VARCHAR(5))\n" +
                "                        LEFT JOIN (SELECT\t\tAR_Ref\n" +
                "                                ,SUM(ISNULL(AS_MontSto,0)) AS_MontSto\n" +
                "                                ,SUM(ISNULL(AS_QteSto,0)) AS_QteSto\n" +
                "                                ,0 AS AS_QteStoCumul\n" +
                "                                FROM\t\tF_ARTSTOCK S\n" +
                "                                GROUP BY    AR_Ref) S on S.AR_Ref=A.AR_Ref\n" +
                "                        WHERE (-1 = @Sommeil OR AR_Sommeil=@Sommeil)\n" +
                "        AND      (-1 = @Stock OR (@Stock=1 AND AS_QteSto<>0) OR (@Stock=0 AND (AS_QteSto IS NULL OR AS_QteSto = 0)) )\n" +
                "        AND      (-1 = @Prix OR (@Prix=1 AND (Prix_Min<>0 AND Prix_Max<>0)) OR (@Prix=0 AND (Prix_Min=0 OR Prix_Min IS NULL OR Prix_Max IS NULL OR Prix_Max=0))) \n" +
                "                      ) A " + search + " " + orderByParam +
                " END \n";
        return this.getJdbcTemplate().query(query, mapper);
    }

    public int getArtFournisseurByTiers(String ctNum){
        String sql = FArticleMapper.getArtFournisseurByTiers;
        params = new Object[]{ctNum};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getInt("AF_Unite");
        }
        return 0;
    }

    public FArticle getF_Article(String arRef) {
        String sql = FArticleMapper.getFArticle;
        params = new Object[] {arRef};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        fArticle = new FArticle();
        while(sqlRowSet.next()) {
            fArticle.setAR_Ref(sqlRowSet.getString("AR_Ref"));
            fArticle.setAR_UniteVen(sqlRowSet.getInt("AR_UniteVen"));
            fArticle.setAR_Design(sqlRowSet.getString("AR_Design"));
            fArticle.setAR_PrixVen(sqlRowSet.getDouble("AR_PrixVen"));
            fArticle.setAR_PrixAch(sqlRowSet.getDouble("AR_PrixAch"));
            fArticle.setAR_PrixAchNouv(sqlRowSet.getDouble("AR_PrixAchNouv"));
            fArticle.setAR_PrixTTC(sqlRowSet.getDouble("AR_PrixTTC"));
            fArticle.setAR_PrixVenNouv(sqlRowSet.getDouble("AR_PrixVenNouv"));
            fArticle.setAR_Publie(sqlRowSet.getInt("AR_Publie"));
            fArticle.setCbCreateur(sqlRowSet.getString("cbCreateur"));
            fArticle.setAR_SuiviStock(sqlRowSet.getInt("AR_SuiviStock"));

        }
        return fArticle;
    }

    public List<Object> getTaxeArticle(int fcpChamp,int fcpType,String arRef){
        String sql = FArticleMapper.getTaxeArticle;
        params = new Object[] {fcpChamp,fcpType,arRef};
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public ArrayList<TaxeArticle> getTaxeArticleObject(int fcpChamp, int fcpType, String arRef){
        String sql = FArticleMapper.getTaxeArticle;
        params = new Object[] {fcpChamp,fcpType,arRef};

        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        ArrayList<TaxeArticle> listTaxeArticle = new ArrayList<TaxeArticle>();
        TaxeArticle taxeArticle = new TaxeArticle();
        while(sqlRowSet.next()) {
            taxeArticle.setCG_NumTaxe1(sqlRowSet.getString("CG_NumTaxe1"));
            taxeArticle.setCG_NumTaxe2(sqlRowSet.getString("CG_NumTaxe2"));
            taxeArticle.setCG_NumTaxe3(sqlRowSet.getString("CG_NumTaxe3"));
            taxeArticle.setCG_Tiers1(sqlRowSet.getString("CG_NumTaxe1"));
            taxeArticle.setCG_Tiers2(sqlRowSet.getString("CG_NumTaxe2"));
            taxeArticle.setCG_Tiers3(sqlRowSet.getString("CG_NumTaxe3"));
            taxeArticle.setCG_TiersArticle(sqlRowSet.getString("CG_TiersArticle"));
            taxeArticle.setCodeTaxe1(sqlRowSet.getString("CodeTaxe1"));
            taxeArticle.setCodeTaxe2(sqlRowSet.getString("CodeTaxe2"));
            taxeArticle.setCodeTaxe3(sqlRowSet.getString("CodeTaxe3"));
            taxeArticle.setCOMPTEG_ARTICLE(sqlRowSet.getString("COMPTEG_ARTICLE"));
            listTaxeArticle.add(taxeArticle);
        }
        return listTaxeArticle;
    }

    public List<Object> listeArticleSource(int deNo,String type){
        if(deNo!=0) {
            if (type.equals("Ticket") || type.equals("Vente") || type.equals("BonLivraison") || type.equals("Sortie") || type.equals("Transfert") || type.equals("Transfert_detail"))
                return this.getAllArticleDispoByArRef(deNo,"0","");
            else
                return this.getAll("",0,-1,-1);
        }
        return null;
    }

    public void maj(String nom,String valeur,BigDecimal cbMarq,String cbCreateur) {
        String cbCreateurSql="";
        if(!cbCreateur.equals(""))
            cbCreateurSql=",cbCreateur='"+cbCreateur+"'";
        String updateQuery = "UPDATE F_ARTICLE SET "+nom+" = '"+valeur+"' "+cbCreateurSql+" WHERE cbMarq = "+cbMarq;
        this.getJdbcTemplate().update(updateQuery);
    }

}
