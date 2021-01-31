package com.server.itsolution.mapper;

public class FArticleMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select   AR_Ref\n" +
            "    ,AR_Design\n" +
            "    ,FA_CodeFamille\n" +
            "    ,AR_Substitut\n" +
            "    ,AR_Raccourci\n" +
            "    ,AR_Garantie\n" +
            "    ,AR_UnitePoids\n" +
            "    ,AR_PoidsNet\n" +
            "    ,AR_PoidsBrut\n" +
            "    ,AR_UniteVen\n" +
            "    ,AR_PrixAch\n" +
            "    ,AR_Coef\n" +
            "    ,AR_PrixVen\n" +
            "    ,AR_PrixTTC\n" +
            "    ,AR_Gamme1\n" +
            "    ,AR_Gamme2\n" +
            "    ,AR_SuiviStock\n" +
            "    ,AR_Nomencl\n" +
            "    ,AR_Stat01\n" +
            "    ,AR_Stat02\n" +
            "    ,AR_Stat03\n" +
            "    ,AR_Stat04\n" +
            "    ,AR_Stat05\n" +
            "    ,AR_Escompte\n" +
            "    ,AR_Delai\n" +
            "    ,AR_HorsStat\n" +
            "    ,AR_VteDebit\n" +
            "    ,AR_NotImp\n" +
            "    ,AR_Sommeil\n" +
            "    ,AR_Langue1\n" +
            "    ,AR_Langue2\n" +
            "    ,AR_CodeEdiED_Code1\n" +
            "    ,AR_CodeEdiED_Code2\n" +
            "    ,AR_CodeEdiED_Code3\n" +
            "    ,AR_CodeEdiED_Code4\n" +
            "    ,AR_CodeBarre\n" +
            "    ,AR_CodeFiscal\n" +
            "    ,AR_Pays\n" +
            "    ,AR_Frais01FR_Denomination\n" +
            "    ,AR_Frais01FR_Rem01REM_Valeur\n" +
            "    ,AR_Frais01FR_Rem01REM_Type\n" +
            "    ,AR_Frais01FR_Rem02REM_Valeur\n" +
            "    ,AR_Frais01FR_Rem02REM_Type\n" +
            "    ,AR_Frais01FR_Rem03REM_Valeur\n" +
            "    ,AR_Frais01FR_Rem03REM_Type\n" +
            "    ,AR_Frais02FR_Denomination\n" +
            "    ,AR_Frais02FR_Rem01REM_Valeur\n" +
            "    ,AR_Frais02FR_Rem01REM_Type\n" +
            "    ,AR_Frais02FR_Rem02REM_Valeur\n" +
            "    ,AR_Frais02FR_Rem02REM_Type\n" +
            "    ,AR_Frais02FR_Rem03REM_Valeur\n" +
            "    ,AR_Frais02FR_Rem03REM_Type\n" +
            "    ,AR_Frais03FR_Denomination\n" +
            "    ,AR_Frais03FR_Rem01REM_Valeur\n" +
            "    ,AR_Frais03FR_Rem01REM_Type\n" +
            "    ,AR_Frais03FR_Rem02REM_Valeur\n" +
            "    ,AR_Frais03FR_Rem02REM_Type\n" +
            "    ,AR_Frais03FR_Rem03REM_Valeur\n" +
            "    ,AR_Frais03FR_Rem03REM_Type\n" +
            "    ,AR_Condition\n" +
            "    ,AR_PUNet\n" +
            "    ,AR_Contremarque\n" +
            "    ,AR_FactPoids\n" +
            "    ,AR_FactForfait\n" +
            "    ,AR_DateCreation\n" +
            "    ,AR_SaisieVar\n" +
            "    ,AR_Transfere\n" +
            "    ,AR_Publie\n" +
            "    ,AR_DateModif\n" +
            "    ,AR_Photo\n" +
            "    ,AR_PrixAchNouv\n" +
            "    ,AR_CoefNouv\n" +
            "    ,AR_PrixVenNouv\n" +
            "    ,AR_DateApplication\n" +
            "    ,AR_CoutStd\n" +
            "    ,AR_QteComp\n" +
            "    ,AR_QteOperatoire\n" +
            "    ,CO_No\n" +
            "    ,AR_Prevision,CL_No1\n" +
            "    ,CL_No2\n" +
            "    ,CL_No3\n" +
            "    ,CL_No4\n" +
            "    ,AR_Type\n" +
            "    ,RP_CodeDefaut\n" +
            "    ,cbMarq\n" +
            "    ,cbCreateur\n" +
            "    ,cbModification\n" +
            "    ,Prix_Min\n" +
            "    ,Prix_Max\n" +
            "    ,Qte_Gros From F_ARTICLE";

    public static final String getFArticle //
            =   BASE_SQL + " WHERE AR_Ref = ? ";

    public static final String insertFArtCompta =
            "DECLARE @cbMarq INT = ? " +
            "DECLARE @val NVARCHAR(50) = ?";


    public static final String getCatComptaByArRef
            =   "DECLARE @arRef NVARCHAR(50) = ? " +
                "DECLARE @acpType INT = ? " +
                "DECLARE @acpChamp INT = ? " +
                "" +
            "SELECT         cbMarq = ISNULL(B.cbMarq,0), " +
            "           ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.ACP_Champ ELSE B.ACP_Champ END,0) ACP_Champ,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.CG_Num ELSE B.CG_Num END,'') CG_Num,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.CG_Intitule ELSE B.CG_Intitule END,'') CG_Intitule,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.CG_NumA ELSE B.CG_NumA END,'') CG_NumA,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.CG_IntituleA ELSE B.CG_IntituleA END,'') CG_IntituleA,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.Taxe1 ELSE B.Taxe1 END,'') Taxe1,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.TA_Intitule1 ELSE B.TA_Intitule1 END,'') TA_Intitule1,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.Taxe2 ELSE B.Taxe2 END,'') Taxe2,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.TA_Intitule2 ELSE B.TA_Intitule2 END,'') TA_Intitule2,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.Taxe3 ELSE B.Taxe3 END,'') Taxe3,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.TA_Intitule3 ELSE B.TA_Intitule3 END,'') TA_Intitule3,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.TA_Taux1 ELSE B.TA_Taux1 END,0) TA_Taux1,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.TA_Taux2 ELSE B.TA_Taux2 END,0) TA_Taux2,\n" +
            "                ISNULL(CASE WHEN B.AR_Ref IS NULL THEN A.TA_Taux3 ELSE B.TA_Taux3 END,0) TA_Taux3\n" +
            "                FROM (\n" +
            "                SELECT  AR.cbAR_Ref,A.FA_CodeFamille,FCP_Type ACP_Type,AR_Ref,FCP_Champ ACP_Champ\n" +
            "                        ,FCP_ComptaCPT_CompteG CG_Num,CG.CG_Intitule,FCP_ComptaCPT_CompteA CG_NumA\n" +
            "                        ,CA.CG_Intitule CG_IntituleA,FCP_ComptaCPT_Taxe1 Taxe1,TU.TA_Intitule TA_Intitule1\n" +
            "                        ,TU.TA_Taux TA_Taux1,FCP_ComptaCPT_Taxe2 Taxe2,TD.TA_Intitule TA_Intitule2\n" +
            "                        ,TD.TA_Taux TA_Taux2,FCP_ComptaCPT_Taxe3 Taxe3,TT.TA_Intitule TA_Intitule3\n" +
            "                        ,TT.TA_Taux TA_Taux3\n" +
            "                FROM    F_FAMCOMPTA A \n" +
            "                INNER JOIN F_ARTICLE AR \n" +
            "                    ON AR.cbFA_CodeFamille = A.cbFA_CodeFamille\n" +
            "                LEFT JOIN F_TAXE TU \n" +
            "                    ON A.FCP_ComptaCPT_Taxe1 = TU.TA_Code\n" +
            "                LEFT JOIN F_TAXE TD \n" +
            "                    ON A.FCP_ComptaCPT_Taxe2 = TD.TA_Code\n" +
            "                LEFT JOIN F_TAXE TT \n" +
            "                    ON A.FCP_ComptaCPT_Taxe3 = TT.TA_Code\n" +
            "                LEFT JOIN F_COMPTEG CG \n" +
            "                    ON A.FCP_ComptaCPT_CompteG = CG.CG_Num\n" +
            "                LEFT JOIN F_COMPTEG CA \n" +
            "                    ON A.FCP_ComptaCPT_CompteA = CA.CG_Num) A\n" +
            "                LEFT JOIN (SELECT A.cbMarq,A.AR_Ref,A.cbAR_Ref,ACP_Type,FA_CodeFamille,ACP_Champ,ACP_ComptaCPT_CompteG CG_Num,CG.CG_Intitule,ACP_ComptaCPT_CompteA CG_NumA,CA.CG_Intitule CG_IntituleA,ACP_ComptaCPT_Taxe1 Taxe1,TU.TA_Intitule TA_Intitule1,TU.TA_Taux TA_Taux1\n" +
            "                ,ACP_ComptaCPT_Taxe2 Taxe2,TD.TA_Intitule TA_Intitule2,TD.TA_Taux TA_Taux2,ACP_ComptaCPT_Taxe3 Taxe3,TT.TA_Intitule TA_Intitule3,TT.TA_Taux TA_Taux3\n" +
            "                FROM F_ARTCOMPTA A \n" +
            "                INNER JOIN F_ARTICLE AR \n" +
            "                    ON AR.cbAR_Ref = A.cbAR_Ref\n" +
            "                LEFT JOIN F_TAXE TU \n" +
            "                    ON A.ACP_ComptaCPT_Taxe1 = TU.TA_Code\n" +
            "                LEFT JOIN F_TAXE TD \n" +
            "                    ON A.ACP_ComptaCPT_Taxe2 = TD.TA_Code\n" +
            "                LEFT JOIN F_TAXE TT \n" +
            "                    ON A.ACP_ComptaCPT_Taxe3 = TT.TA_Code\n" +
            "                LEFT JOIN F_COMPTEG CG \n" +
            "                    ON A.ACP_ComptaCPT_CompteG = CG.CG_Num\n" +
            "                LEFT JOIN F_COMPTEG CA \n" +
            "                    ON A.ACP_ComptaCPT_CompteA = CA.CG_Num)B \n" +
            "                    ON  A.FA_CodeFamille=B.FA_CodeFamille \n" +
            "                    AND A.cbAR_Ref=B.cbAR_Ref \n" +
            "                    AND A.ACP_Champ=B.ACP_Champ \n" +
            "                    AND A.ACP_Type=B.ACP_Type\n" +
            "                WHERE A.AR_Ref=@arRef \n" +
            "                AND A.ACP_Type=@acpType \n" +
            "                AND A.ACP_Champ=@acpChamp ";
    public static final String getPrixClient =
            " BEGIN \n" +
            "\n" +
            " DECLARE @arRef AS VARCHAR(50) = ?\n" +
                    " DECLARE @catCompta AS INT = ?\n" +
                    " DECLARE @catTarif AS INT = ?\n" +
                    " SET NOCOUNT ON;\n" +
                    " DECLARE @flagCat AS TINYINT\n" +
                    " SELECT @flagCat = P_ReportPrixRev\n" +
                    " FROM P_PARAMETRECIAL     \n" +
                    " SELECT *,CASE WHEN AC_PrixTTC = 1 THEN Prix /(1+taxe1/100)/(1+taxe2/100)/(1+taxe3/100) ELSE Prix END PrixVente\n" +
                    " FROM(\n" +
                    " SELECT	a.FA_CodeFamille\n" +
                    " , a.AR_Ref\n" +
                    " , ISNULL(AR_PrixAch,0)AR_PrixAch\n" +
                    "           , AR_Design\n" +
                    "           , ISNULL(AR_PrixVen,0)AR_PrixVen\n" +
                    "           ,Qte_Gros\n" +
                    "           ,CASE WHEN @flagCat=0 THEN Prix_Min ELSE ISNULL(AC_Coef,0) END Prix_Min\n" +
                    "           ,CASE WHEN @flagCat=0 THEN Prix_Max ELSE ISNULL(AC_PrixVen,0) END Prix_Max\n" +
                    "           ,ISNULL(CASE WHEN AC_PrixVen<>0 THEN AC_PrixVen ELSE AR_PrixVen END,0) AS Prix, AC_PrixTTC, AR_PrixTTC,\n" +
                    " (CASE WHEN ISNULL(TU.TA_Sens,0)=0 THEN -1 ELSE 1 END)*ISNULL(TU.TA_Taux,0) as taxe1, \n" +
                    " (CASE WHEN ISNULL(TD.TA_Sens,0)=0 THEN -1 ELSE 1 END)*ISNULL(TD.TA_Taux,0) as taxe2,\n" +
                    " (CASE WHEN ISNULL(TT.TA_Sens,0)=0 THEN -1 ELSE 1 END)*ISNULL(TT.TA_Taux,0) as taxe3, FCP_Champ\n" +
                    " FROM F_ARTICLE A\n" +
                    " LEFT JOIN (select * from F_ARTCLIENT where AC_Categorie=(SELECT ISNULL((SELECT AC_Categorie FROM F_ARTCLIENT WHERE AR_REF = @arRef AND AC_Categorie=@catTarif),1)))AR on AR.AR_Ref = A.AR_Ref\n" +
                    " LEFT JOIN (SELECT * FROM F_FAMCOMPTA WHERE FCP_Type=0 AND FCP_Champ=@catCompta)FA ON FA.FA_CodeFamille = A.FA_CodeFamille\n" +
                    " LEFT JOIN F_TAXE TU ON TU.TA_Code = FA.FCP_ComptaCPT_Taxe1\n" +
                    " LEFT JOIN F_TAXE TD ON TD.TA_Code = FA.FCP_ComptaCPT_Taxe2\n" +
                    " LEFT JOIN F_TAXE TT ON TT.TA_Code = FA.FCP_ComptaCPT_Taxe3\n" +
                    " WHERE  A.AR_REF = @arRef  )A;\n" +
                    " END;";


    public static final String stockMinDepasse = "" +
            "SELECT CASE WHEN AS_QteMini<>0 THEN \n" +
            "                        CASE WHEN AS_QteSto < AS_QteMini THEN 1 ELSE 0 END \n" +
            "                        ELSE 0 END stockMinDepasse,AS_QteMini,AS_QteSto \n" +
            "                FROM F_ARTSTOCK\n" +
            "                WHERE DE_No=?\n" +
            "                AND AR_Ref=?";

    public static final String getShortList = "" +
            "SELECT AR_Ref,AR_Design FROM F_ARTICLE A ORDER BY AR_Ref";

    public static final String isArticleLigne = "SELECT cbMarq FROM F_DOCLIGNE WHERE AR_Ref=?";

    public static final String deleteArticle = "" +
            "DECLARE @cbMarq INT = ?\n" +
            "DECLARE @arRef NVARCHAR(50)\n" +
            "\n" +
            "\n" +
            "SELECT @arRef=AR_Ref\n" +
            "FROM F_ARTICLE\n" +
            "WHERE cbMarq=@cbMarq\n" +
            "\n" +
            "IF NOT EXISTS (SELECT 1 FROM F_DOCLIGNE WHERE AR_Ref=@arRef)\n" +
            "BEGIN \n" +
            "\tDELETE FROM F_ARTCLIENT WHERE AR_Ref=@arRef;\n" +
            "    DELETE FROM F_CONDITION WHERE AR_Ref=@arRef;\n" +
            "    DELETE FROM F_ARTMODELE WHERE AR_Ref=@arRef;\n" +
            "    IF EXISTS(SELECT * FROM sys.objects WHERE NAME='TG_CBDEL_F_ARTICLE')\n" +
            "    ALTER TABLE F_ARTICLE DISABLE TRIGGER TG_CBDEL_F_ARTICLE;\n" +
            "    DELETE FROM F_ARTICLE WHERE AR_Ref=@arRef;\n" +
            "    IF EXISTS(SELECT * FROM sys.objects WHERE NAME='TG_CBDEL_F_ARTICLE')\n" +
            "    ALTER TABLE F_ARTICLE ENABLE TRIGGER TG_CBDEL_F_ARTICLE;\n" +
            "\tSELECT 1 value\n" +
            "END\n" +
            "ELSE \n" +
            "\tSELECT 0 value";

    public static final String getTaxeArticle =
            "WITH _Taxe_ AS (\n" +
                    "                    SELECT T.TA_Code,C.CG_Num,CG_Tiers\n" +
                    "                    FROM F_TAXE T\n" +
                    "                    LEFT JOIN F_COMPTEG C \n" +
                    "                        ON T.cbCG_Num = C.cbCG_Num\n" +
                    "                )\n" +
                    "                SELECT   COMPTEG_ARTICLE = ISNULL(ACP_ComptaCPT_CompteG,FCP_ComptaCPT_CompteG) \n" +
                    "                                        ,CG_TiersArticle = Cg.CG_Tiers\n" +
                    "                                        ,CodeTaxe1 = TU.TA_Code\n" +
                    "                                        ,CG_NumTaxe1 = TU.CG_Num \n" +
                    "                                        ,CG_Tiers1 = TU.CG_Tiers \n" +
                    "                                        ,CodeTaxe2 = TD.TA_Code\n" +
                    "                                        ,CG_NumTaxe2 = TD.CG_Num \n" +
                    "                                        ,CG_Tiers2 = TD.CG_Tiers\n" +
                    "                                        ,CodeTaxe3 = TT.TA_Code \n" +
                    "                                        ,CG_NumTaxe3 = TT.CG_Num\n" +
                    "                                        ,CG_Tiers3 = TT.CG_Tiers  \n" +
                    "                                    FROM F_ARTICLE Art \n" +
                    "                                    LEFT JOIN F_FAMCOMPTA F \n" +
                    "                                        ON Art.cbFA_CodeFamille = F.cbFA_CodeFamille  \n" +
                    "                                    LEFT JOIN F_ARTCOMPTA A \n" +
                    "                                        ON  A.cbAR_Ref = Art.cbAR_Ref \n" +
                    "                                        AND ISNULL(ACP_Champ,FCP_Champ) =FCP_Champ \n" +
                    "                                        AND ISNULL(ACP_Type,FCP_Type)=FCP_Type \n" +
                    "                                    LEFT JOIN F_COMPTEG Cg \n" +
                    "                                        ON Cg.CG_Num = ISNULL(ACP_ComptaCPT_CompteG,FCP_ComptaCPT_CompteG)\n" +
                    "                                    LEFT JOIN _Taxe_ TU \n" +
                    "                                        ON  TU.TA_Code = (CASE WHEN ISNULL(FCP_ComptaCPT_Taxe1,'')  <> ISNULL(ACP_ComptaCPT_Taxe1,'')  \n" +
                    "                                        AND ACP_ComptaCPT_Taxe1 IS NOT NULL THEN ACP_ComptaCPT_Taxe1 ELSE FCP_ComptaCPT_Taxe1 END)\n" +
                    "                                    LEFT JOIN _Taxe_ TD \n" +
                    "                                        ON  TD.TA_Code = (CASE WHEN ISNULL(FCP_ComptaCPT_Taxe2,'')  <> ISNULL(ACP_ComptaCPT_Taxe2,'')  \n" +
                    "                                        AND ACP_ComptaCPT_Taxe2 IS NOT NULL THEN ACP_ComptaCPT_Taxe2 ELSE FCP_ComptaCPT_Taxe2 END) \n" +
                    "                                    LEFT JOIN _Taxe_ TT \n" +
                    "                                        ON  TT.TA_Code = (CASE WHEN ISNULL(FCP_ComptaCPT_Taxe3,'')  <> ISNULL(ACP_ComptaCPT_Taxe3,'')  \n" +
                    "                                        AND ACP_ComptaCPT_Taxe3 IS NOT NULL THEN ACP_ComptaCPT_Taxe3 ELSE FCP_ComptaCPT_Taxe3 END) \n" +
                    "                                    WHERE   FCP_Champ=? \n" +
                    "                                    AND     FCP_Type=?\n" +
                    "                                    AND     Art.AR_Ref=?";
	public static final String updateF_ArtStockBorne = 
			"DECLARE @AS_QteMini AS VARCHAR(50) = ?"+
			"				,@AS_QteMaxi AS VARCHAR(50) =?"+
			"				,@cbCreateur AS VARCHAR(50) = ?"+
			"				,@AR_Ref AS VARCHAR(50) = ?"+
			"				,@DE_No AS VARCHAR(50) = ?"+
			"		IF EXISTS (SELECT 1 FROM F_ARTSTOCK WHERE AR_Ref=@AR_Ref AND DE_No=@DE_No)"+
            "       BEGIN"+
            "          UPDATE F_ARTSTOCK SET AS_QteMini=@AS_QteMini,AS_QteMaxi=@AS_QteMaxi,cbCreateur=@cbCreateur WHERE AR_Ref=@AR_Ref AND DE_No=@DE_No;"+
            "        END"+
            "        ELSE "+
            "            INSERT INTO [dbo].[F_ARTSTOCK]"+
            "               ([AR_Ref],[DE_No],[AS_QteMini],[AS_QteMaxi]"+
            "               ,[AS_MontSto],[AS_QteSto],[AS_QteRes],[AS_QteCom]"+
            "               ,[AS_Principal],[AS_QteResCM],[AS_QteComCM],[AS_QtePrepa]"+
            "               ,[DP_NoPrincipal],[cbDP_NoPrincipal],[DP_NoControle],[cbDP_NoControle]"+
            "               ,[AS_QteAControler],[cbProt],[cbCreateur],[cbModification]"+
            "               ,[cbReplication],[cbFlag])"+
            "         VALUES"+
            "         (/*AR_Ref*/@AR_Ref,/*DE_No*/@DE_No"+
            "             ,/*AS_QteMini*/@AS_QteMini,/*AS_QteMaxi*/@AS_QteMaxi"+
            "             ,/*AS_MontSto*/0,/*AS_QteSto*/0"+
            "             ,/*AS_QteRes*/0,/*AS_QteCom*/0"+
            "             ,/*AS_Principal*/0,/*AS_QteResCM*/0"+
            "             ,/*AS_QteComCM*/0,/*AS_QtePrepa*/0"+
            "             ,/*DP_NoPrincipal*/0,/*cbDP_NoPrincipal*/NULL"+
            "             ,/*DP_NoControle*/0,/*cbDP_NoControle*/NULL"+
            "             ,/*AS_QteAControler*/0,/*cbProt*/0"+
            "             ,/*cbCreateur*/@cbCreateur,/*cbModification*/GETDATE()"+
            "             ,/*cbReplication*/0,/*cbFlag*/0)"+
            "        ;";


    public static final String majRefArticle =
            "BEGIN "+
                    "        SET NOCOUNT ON; "+
                    "            DECLARE @ref_ancien AS VARCHAR(30) "+
                    "            DECLARE @ref_nouveau AS VARCHAR(30) "+
                    "            SET @ref_nouveau=? "+
                    "            SET @ref_ancien=? "+
                    "            "+
                    "            UPDATE [F_DOCLIGNE] SET AR_Ref=@ref_nouveau WHERE AR_Ref= @ref_ancien "+
                    "            UPDATE [F_LIGNEARCHIVE] SET AR_Ref=@ref_nouveau WHERE AR_Ref= @ref_ancien "+
                    "            UPDATE F_ARTSTOCK SET	AS_QteSto= F_ARTSTOCK.AS_QteSto+A.AS_QteSto "+
                    "                                    ,AS_MontSto=F_ARTSTOCK.AS_MontSto+A.AS_MontSto "+
                    "                                    ,AS_QteRes=F_ARTSTOCK.AS_QteRes+A.AS_QteRes "+
                    "                                    ,AS_QteCom=F_ARTSTOCK.AS_QteCom+A.AS_QteCom "+
                    "            FROM (	SELECT AR_Ref,DE_No,AS_QteSto,AS_MontSto,AS_QteRes,AS_QteCom "+
                    "                    FROM F_ARTSTOCK "+
                    "                    WHERE AR_Ref=@ref_ancien)A "+
                    "            WHERE	A.DE_No = F_ARTSTOCK.DE_No "+
                    "                    AND F_ARTSTOCK.AR_Ref=@ref_nouveau "+
                    "            UPDATE [F_ARTSTOCK] SET AS_QteSto = 0 "+
                    "                                    ,AS_QteRes = 0 "+
                    "                                    ,AS_QteCom = 0 "+
                    "                                    ,AS_QteResCM = 0 "+
                    "                                    ,AS_QteComCM = 0 "+
                    "                                    ,AS_QtePrepa = 0 "+
                    "                                    ,AS_QteAControler = 0 "+
                    "                                    ,AS_MontSto = 0 "+
                    "            WHERE AR_Ref= @ref_ancien "+
                    "          "+
                    "            DELETE FROM [F_ARTSTOCK] WHERE AR_Ref= @ref_ancien "+
                    "          "+
                    "            UPDATE F_ARTICLE SET AR_Sommeil = 1 WHERE AR_Ref = @ref_ancien;  "+
                    "        END;";
	
	public static final String articleDoublons =
			"SELECT AR_Design "+
            "    FROM ( "+
            "    SELECT AR_Design,COUNT(AR_Ref) Nb "+
            "    FROM F_ARTICLE "+
            "    GROUP BY AR_Design)A "+
            "    WHERE Nb>1 ";
				
    public static final String queryListeArticle=
            "                BEGIN\n" +
            "                SET NOCOUNT ON;\n" +
            "                DECLARE @Sommeil AS INT,\n" +
            "                            @Stock AS INT,\n" +
            "                            @Prix AS INT,\n" +
            "                            @prot_admin AS INT;\n" +
            "    \n" +
            "                    SET @Sommeil = ? \n" +
            "                    SET @Stock = ? \n" +
            "                    SET @Prix = ? \n" +
            "                    \n" +
            "                    SELECT * \n" +
            "                    FROM(select           AR_Sommeil\n" +
            "                                          ,A.AR_Ref\n" +
            "                                          ,AR_Design\n" +
            "                                          ,FA_CodeFamille\n" +
            "                                          ,ROUND(ISNULL(AS_QteSto,0),2) AS_QteSto\n" +
            "                                          ,ROUND(ISNULL(AS_QteStoCumul,0),0) AS_QteStoCumul\n" +
            "                                          ,ROUND(ISNULL(AS_MontSto,0),2) AS_MontSto\n" +
            "                                          ,Prix_Min\n" +
            "                                          ,Prix_Max\n" +
            "                                          ,PROT_User\n" +
            "                                          $valAchat\n" +
            "             FROM F_ARTICLE A \n" +
            "             LEFT JOIN F_PROTECTIONCIAL P ON A.cbCreateur = CAST(P.PROT_No AS VARCHAR(5))\n" +
            "             LEFT JOIN (SELECT\t\tAR_Ref\n" +
            "                                    ,SUM(ISNULL(AS_MontSto,0)) AS_MontSto\n" +
            "                                    ,SUM(ISNULL(AS_QteSto,0)) AS_QteSto\n" +
            "                                    ,0 AS AS_QteStoCumul\n" +
            "                        FROM\t\tF_ARTSTOCK S \n" +
            "                        GROUP BY    AR_Ref) S on S.AR_Ref=A.AR_Ref\n" +
            "             WHERE (-1 = @Sommeil OR AR_Sommeil=@Sommeil) \n" +
            "             AND      (-1 = @Stock OR (@Stock=1 AND AS_QteSto<>0) OR (@Stock=0 AND (AS_QteSto IS NULL OR AS_QteSto = 0)) ) \n" +
            "             AND      (-1 = @Prix OR (@Prix=1 AND (Prix_Min<>0 AND Prix_Max<>0)) OR (@Prix=0 AND (Prix_Min=0 OR Prix_Min IS NULL OR Prix_Max IS NULL OR Prix_Max=0))) \n" +
            "                      ) A  \n" +
            "               \";\n" +
            "        return $query;\n";

    public static final String getAllArticleDispoByArRef =
            " DECLARE @deNo INT = ? " +
                    " DECLARE @codeFamille VARCHAR(30) = ? " +
                    " DECLARE @valeur VARCHAR(255) = ? " +
                    " SELECT TOP 10 AR_Type\n" +
                    "                        ,AR_Sommeil\n" +
                    "                        ,A.AR_Ref as id\n" +
                    "                        ,A.AR_Ref\n" +
                    "                        ,AR_Design\n" +
                    "                        ,CONCAT(CONCAT(A.AR_Ref,' - '),AR_Design) as text\n" +
                    "                        ,CONCAT(CONCAT(A.AR_Ref,' - '),AR_Design) as value\n" +
                    "                        ,AR_PrixAch\n" +
                    "                        ,AR_PrixVen \n" +
                    "                        ,AS_QteSto\n" +
                    "                  FROM F_ARTICLE A \n" +
                    "                  LEFT JOIN F_ARTSTOCK S ON A.AR_Ref=S.AR_Ref  \n" +
                    "                  WHERE (A.AR_SuiviStock = 0) OR  (A.AR_SuiviStock <> 0 AND (@deNo=0 OR DE_No=@deNo) \n" +
                    "                  AND   (S.AS_QteSto>0))\n" +
                    "                  AND   ('0'=@codeFamille OR FA_CodeFamille=@codeFamille)\n" +
                    "                  AND   AR_Sommeil=0\n" +
                    "                  AND CONCAT(CONCAT(CONCAT(CONCAT(A.AR_Ref,' - '),AR_Design), ' - '),AR_CodeBarre) LIKE CONCAT(CONCAT('%',@valeur),'%')\n" +
                    "                  ORDER BY AR_Design";

    public static String getArtFournisseurSelect =
                    "SELECT  *,\n" +
                    "                        DateApplication = CASE WHEN AF_DateApplication='1900-01-01' THEN 0 ELSE AF_DateApplication END  \n" +
                    "                FROM    F_ARTFOURNISS \n" +
                    "                WHERE   cbMarq = ?";
    public static String getArtFournisseur =
                    "SELECT\tAF_RefFourniss\n" +
                    "                            ,A.CT_Num\n" +
                    "                            ,CT_Intitule\n" +
                    "                            ,AF_PrixAch\n" +
                    "                            ,AF_TypeRem\n" +
                    "                            ,AF_Remise\n" +
                    "                            ,DL_Remise = CASE WHEN AF_TypeRem=0 THEN cast(cast(AF_Remise as numeric(9,2)) as varchar(10)) \n" +
                    "                                                WHEN AF_TypeRem=1 THEN cast(cast(AF_Remise as numeric(9,2)) as varchar(10))+'%' \n" +
                    "                                                    ELSE cast(cast(AF_Remise as numeric(9,2)) as varchar(10))+'U' END \n" +
                    "                            ,AF_Conv = CONCAT(CAST(AF_Conversion AS INT),CONCAT('/',CAST(AF_ConvDiv AS INT))) \n" +
                    "                            ,A.cbMarq\n" +
                    "                            FROM F_ARTFOURNISS A \n" +
                    "                            LEFT JOIN F_COMPTET B \n" +
                    "                                ON A.cbCT_Num = B.cbCT_Num\n" +
                    "                            WHERE AR_Ref=?";
    public static String allSearch =
            " DECLARE @arPublie AS INT =? " +
                    " DECLARE @sommeil AS INT =? " +
                    " DECLARE @valeurSaisie AS VARCHAR(250) =? " +
                    " SELECT  TOP 10 AR_Type\n" +
                    "                          ,AR_Sommeil\n" +
                    "                          ,AR_Ref\n" +
                    "                          ,AR_Design\n" +
                    "                          ,AR_Ref as id\n" +
                    "                          ,CONCAT(CONCAT(AR_Ref,' - '),AR_Design) as text\n" +
                    "                          ,CONCAT(CONCAT(AR_Ref,' - '),AR_Design) as value\n" +
                    "                        ,AR_PrixAch\n" +
                    "                        ,AR_PrixVen\n" +
                    "                  FROM F_ARTICLE\n" +
                    "                  WHERE (-1=@arPublie OR AR_Publie=@arPublie) \n" +
                    "                  AND (-1=@sommeil OR AR_Sommeil=@sommeil)\n" +
                    "                  AND CONCAT(CONCAT(AR_Ref,' - '),AR_Design) LIKE CONCAT(CONCAT('%',@valeurSaisie),'%') ";

    public static String getArtFournisseurByTiers =
            "SELECT  AF_Unite\n" +
            "                FROM F_ARTFOURNISS \n" +
            "                WHERE CT_Num= ?";

    public static String getArticleAndDepot =
            "SELECT A.AR_Ref,AR_Design,DE_Intitule,CASE WHEN ISNULL(AS_QteSto,0) =0 THEN 0 ELSE ISNULL(AS_MontSto,0)/ISNULL(AS_QteSto,0) END AS_MontSto,\n" +
                    "                    ISNULL(AS_QteSto,0) AS_QteSto ,AR_PrixAch,AR_PrixVen \n" +
                    "                    FROM F_ARTICLE A \n" +
                    "                    LEFT JOIN f_artstock S on S.AR_Ref=A.AR_Ref \n" +
                    "                    INNER JOIN  F_DEPOT D ON D.DE_No =S.DE_No\n" +
                    "                    WHERE A.AR_Ref= ? ";
    public static String detailConditionnement =
            "SELECT C.AR_Ref,EC_Enumere,EC_Quantite,TC_Prix\n" +
                    "                    FROM F_CONDITION C\n" +
                    "                    INNER JOIN F_TARIFCOND T ON T.AR_Ref=C.AR_Ref\n" +
                    "                    where C.ar_ref=? AND C.CO_No=T.CO_No\n" +
                    "                    AND TC_RefCF LIKE CONCAT('%',?)";

    public static String getStockDepot = "SELECT ISNULL(AS_QteMini,0) AS_QteMini,ISNULL(AS_QteMaxi,0) AS_QteMaxi,ISNULL(AS_QteSto,0) AS_QteSto\n" +
                    "                      FROM F_ARTSTOCK\n" +
                    "                      WHERE AR_Ref=? AND DE_No=?;";

    public static String getArticleByIntitule =
            "SELECT AR_Ref\n" +
            "                FROM F_ARTICLE\n" +
            "                WHERE AR_Design=?";

    public static String getPxMinMaxCatCompta =
                    "SELECT AC_Coef,AC_PrixVen\n" +
                    "                FROM F_ARTCLIENT\n" +
                    "                WHERE AR_Ref=?\n" +
                    "                AND AC_Categorie=?";

    public static String insertFArtModele =
        "INSERT INTO [dbo].[F_ARTMODELE]\n" +
                "        ([AR_Ref],[MO_No],[AM_Domaine],[cbProt]\n" +
                "                    ,[cbCreateur],[cbModification],[cbReplication],[cbFlag])\n" +
                "        VALUES\n" +
                "                (/*AR_Ref*/?,/*MO_No*/(SELECT MAX(MO_No) FROM F_MODELE),/*AM_Domaine*/0,/*cbProt*/0\n" +
                "                ,/*cbCreateur*/?,/*cbModification*/CAST(GETDATE() AS DATE),/*cbReplication*/0,/*cbFlag*/0)";

    public static String insertfArticle =
        "                DECLARE @arRef VARCHAR(150) = ? \n" +
        "                DECLARE @arDesign VARCHAR(150) = ?\n" +
        "                DECLARE @faCodeFamille VARCHAR(150) = ?\n" +
        "                DECLARE @arPrixAch VARCHAR(150) = ?\n" +
        "                DECLARE @arPrixVen VARCHAR(150) = ?\n" +
        "                DECLARE @arPrixTTC VARCHAR(150) = ?\n" +
        "                DECLARE @arNomencl VARCHAR(150) = ?\n" +
        "                DECLARE @arCondition VARCHAR(150) = ?\n" +
        "                DECLARE @arPUNet VARCHAR(150) = ?\n" +
        "                DECLARE @arSaisieVar VARCHAR(150) = ?\n" +
        "                DECLARE @arQteComp VARCHAR(150) = ?\n" +
        "                DECLARE @arQteOperatoire VARCHAR(150) = ?\n" +
        "                DECLARE @clNo1 VARCHAR(150) = ?\n" +
        "                DECLARE @clNo2 VARCHAR(150) = ?\n" +
        "                DECLARE @clNo3 VARCHAR(150) = ?\n" +
        "                DECLARE @clNo4 VARCHAR(150) = ?\n" +
        "                DECLARE @cbCreateur CHAR(4) = ?\n" +
        "                DECLARE @prixMin VARCHAR(150) = ?\n" +
        "                DECLARE @prixMax VARCHAR(150) = ?\n" +
        "                DECLARE @qteGros VARCHAR(150) = ?\n"+
        "                INSERT INTO [dbo].[F_ARTICLE] \n" +
        "                    ([AR_Ref],[AR_Design],[FA_CodeFamille],[AR_Substitut],[AR_Raccourci]\n" +
        "                    ,[AR_Garantie],[AR_UnitePoids],[AR_PoidsNet],[AR_PoidsBrut],[AR_UniteVen] \n" +
        "                    ,[AR_PrixAch],[AR_Coef],[AR_PrixVen],[AR_PrixTTC],[AR_Gamme1],[AR_Gamme2]\n" +
        "                    ,[AR_SuiviStock],[AR_Nomencl],[AR_Stat01],[AR_Stat02],[AR_Stat03],[AR_Stat04],[AR_Stat05] \n" +
        "                    ,[AR_Escompte],[AR_Delai],[AR_HorsStat],[AR_VteDebit],[AR_NotImp],[AR_Sommeil],[AR_Langue1],[AR_Langue2],[AR_CodeEdiED_Code1]\n" +
        "                    ,[AR_CodeEdiED_Code2],[AR_CodeEdiED_Code3],[AR_CodeEdiED_Code4],[AR_CodeBarre],[AR_CodeFiscal],[AR_Pays] \n" +
        "                    ,[AR_Frais01FR_Denomination],[AR_Frais01FR_Rem01REM_Valeur],[AR_Frais01FR_Rem01REM_Type],[AR_Frais01FR_Rem02REM_Valeur] \n" +
        "                    ,[AR_Frais01FR_Rem02REM_Type],[AR_Frais01FR_Rem03REM_Valeur],[AR_Frais01FR_Rem03REM_Type],[AR_Frais02FR_Denomination] \n" +
        "                    ,[AR_Frais02FR_Rem01REM_Valeur],[AR_Frais02FR_Rem01REM_Type],[AR_Frais02FR_Rem02REM_Valeur],[AR_Frais02FR_Rem02REM_Type] \n" +
        "                    ,[AR_Frais02FR_Rem03REM_Valeur],[AR_Frais02FR_Rem03REM_Type],[AR_Frais03FR_Denomination],[AR_Frais03FR_Rem01REM_Valeur] \n" +
        "                    ,[AR_Frais03FR_Rem01REM_Type],[AR_Frais03FR_Rem02REM_Valeur],[AR_Frais03FR_Rem02REM_Type],[AR_Frais03FR_Rem03REM_Valeur] \n" +
        "                    ,[AR_Frais03FR_Rem03REM_Type],[AR_Condition],[AR_PUNet],[AR_Contremarque],[AR_FactPoids],[AR_FactForfait],[AR_DateCreation],[AR_SaisieVar] \n" +
        "                    ,[AR_Transfere],[AR_Publie],[AR_DateModif],[AR_Photo],[AR_PrixAchNouv],[AR_CoefNouv],[AR_PrixVenNouv],[AR_DateApplication] \n" +
        "                    ,[AR_CoutStd],[AR_QteComp],[AR_QteOperatoire],[CO_No],[AR_Prevision],[CL_No1],[CL_No2],[CL_No3] \n" +
        "                    ,[CL_No4],[AR_Type],[RP_CodeDefaut],[cbProt],[cbCreateur] \n" +
        "                    ,[cbModification],[cbReplication],[cbFlag],Prix_Min,Prix_Max,Qte_Gros) \n" +
        "              VALUES \n" +
        "                    (/*AR_Ref*/@arRef,/*AR_Design*/@arDesign,/*FA_CodeFamille*/@faCodeFamille,/*AR_Substitut*/NULL,/*AR_Raccourci, varchar(7)*/NULL,/*AR_Garantie*/0 \n" +
        "                    ,/*AR_UnitePoids*/2,/*AR_PoidsNet*/0,/*AR_PoidsBrut*/0,/*AR_UniteVen*/(SELECT FA_UniteVen FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille) \n" +
        "                    ,/*AR_PrixAch*/@arPrixAch,/*AR_Coef*/(SELECT FA_Coef FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_PrixVen*/@arPrixVen,/*AR_PrixTTC*/@arPrixTTC \n" +
        "                    ,/*AR_Gamme1*/0,/*AR_Gamme2*/0,/*AR_SuiviStock*/(SELECT FA_SuiviStock FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Nomencl*/@arNomencl \n" +
        "                    ,/*AR_Stat01*/(SELECT FA_Stat01 FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Stat02*/(SELECT FA_Stat02 FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Stat03*/(SELECT FA_Stat03 FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Stat04*/(SELECT FA_Stat04 FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille) \n" +
        "                    ,/*AR_Stat05*/(SELECT FA_Stat05 FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Escompte*/(SELECT FA_Escompte FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Delai*/(SELECT FA_Delai FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_HorsStat*/(SELECT FA_HorsStat FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille) \n" +
        "                    ,/*AR_VteDebit*/(SELECT FA_VteDebit FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_NotImp*/(SELECT FA_NotImp FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Sommeil*/0,/*AR_Langue1*/'' \n" +
        "                    ,/*AR_Langue2*/'',/*AR_CodeEdiED_Code1, varchar(35)*/'',/*AR_CodeEdiED_Code2*/'',/*AR_CodeEdiED_Code3*/'' \n" +
        "                    ,/*AR_CodeEdiED_Code4*/'',/*AR_CodeBarre*/NULL,/*AR_CodeFiscal, varchar(25)*/(SELECT FA_CodeFiscal FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Pays, varchar(35)*/(SELECT FA_Pays FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille) \n" +
        "                    ,/*AR_Frais01FR_Denomination*/(SELECT FA_Frais01FR_Denomination FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais01FR_Rem01REM_Valeur*/(SELECT FA_Frais01FR_Rem01REM_Valeur FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais01FR_Rem01REM_Type*/(SELECT FA_Frais01FR_Rem01REM_Type FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais01FR_Rem02REM_Valeur*/(SELECT FA_Frais01FR_Rem02REM_Valeur FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille) \n" +
        "                    ,/*AR_Frais01FR_Rem02REM_Type*/(SELECT FA_Frais01FR_Rem02REM_Type FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais01FR_Rem03REM_Valeur*/(SELECT FA_Frais01FR_Rem03REM_Valeur FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais01FR_Rem03REM_Type*/(SELECT FA_Frais01FR_Rem03REM_Type FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais02FR_Denomination*/(SELECT FA_Frais02FR_Denomination FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille) \n" +
        "                    ,/*AR_Frais02FR_Rem01REM_Valeur*/(SELECT FA_Frais02FR_Rem01REM_Valeur FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille) ,/*AR_Frais02FR_Rem01REM_Type*/(SELECT FA_Frais02FR_Rem01REM_Type FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais02FR_Rem02REM_Valeur*/(SELECT FA_Frais02FR_Rem02REM_Valeur FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais02FR_Rem02REM_Type*/(SELECT FA_Frais02FR_Rem02REM_Type FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille) \n" +
        "                    ,/*AR_Frais02FR_Rem03REM_Valeur*/(SELECT FA_Frais02FR_Rem03REM_Valeur FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille) ,/*AR_Frais02FR_Rem03REM_Type*/(SELECT FA_Frais02FR_Rem03REM_Type FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais03FR_Denomination*/(SELECT FA_Frais03FR_Denomination FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais03FR_Rem01REM_Valeur*/(SELECT FA_Frais03FR_Rem01REM_Valeur FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille) \n" +
        "                    ,/*AR_Frais03FR_Rem01REM_Type*/(SELECT FA_Frais03FR_Rem01REM_Type FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais03FR_Rem02REM_Valeur*/(SELECT FA_Frais03FR_Rem02REM_Valeur FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais03FR_Rem02REM_Type*/(SELECT FA_Frais03FR_Rem02REM_Type FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Frais03FR_Rem03REM_Valeur*/(SELECT FA_Frais03FR_Rem03REM_Valeur FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille) \n" +
        "                    ,/*AR_Frais03FR_Rem03REM_Type*/(SELECT FA_Frais03FR_Rem03REM_Type FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_Condition*/@arCondition,/*AR_PUNet*/@arPUNet,/*AR_Contremarque*/(SELECT FA_Contremarque FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille) \n" +
        "                    ,/*AR_FactPoids*/(SELECT FA_FactPoids FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_FactForfait*/(SELECT FA_FactForfait FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_DateCreation*/CAST(GETDATE() AS DATE),/*AR_SaisieVar*/ @arSaisieVar \n" +
        "                    ,/*AR_Transfere*/0,/*AR_Publie*/(SELECT FA_Publie FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*AR_DateModif*/CAST(GETDATE() AS DATE),/*AR_Photo*/'' \n" +
        "                    ,/*AR_PrixAchNouv*/0,/*AR_CoefNouv*/0,/*AR_PrixVenNouv*/0,/*AR_DateApplication*/'1900-01-01' \n" +
        "                    ,/*AR_CoutStd*/0,/*AR_QteComp*/@arQteComp,/*AR_QteOperatoire*/@arQteOperatoire,/*CO_No*/0\n" +
        "                    ,/*AR_Prevision*/0,/*CL_No1*/@clNo1,/*CL_No2*/@clNo2,/*CL_No3*/@clNo3 \n" +
        "                    ,/*CL_No4*/@clNo4,/*AR_Type*/(SELECT FA_Type FROM F_Famille WHERE FA_CodeFamille=@faCodeFamille),/*RP_CodeDefaut*/NULL,/*cbProt*/0\n" +
        "                    ,/*cbCreateur, char(4)*/@cbCreateur,/*cbModification*/GETDATE(),/*cbReplication*/0,/*cbFlag*/0,@prixMin,@prixMax,@qteGros)";
}
