package com.server.itsolution.mapper;

public class FArtStockMapper extends ObjectMapper {

    public static final String artStock =
        "SELECT  AR_Ref,DE_No,ISNULL(AS_QteSto,0)AS_QteSto,ISNULL(AS_MontSto,0)AS_MontSto\n"+
                "                ,ISNULL(AS_QteMini,0)AS_QteMini,ISNULL(AS_QteMaxi,0)AS_QteMaxi\n"+
                "                ,AS_Principal\n"+
                "                ,AS_QteCom\n"+
                "                ,AS_QteMaxi\n"+
                "                ,AS_QteMini\n"+
				"                ,AS_QteRes\n"+
				"                ,cbMarq\n"+
            "        FROM F_ARTSTOCK\n"+
            "        WHERE DE_No = ? AND AR_Ref = ?";


	public static final String isStockDENo
			= "SELECT  ISNULL(AS_QteSto,0)AS_QteSto\n" +
			"                        ,ISNULL(AS_MontSto,0)AS_MontSto\n" +
			"                        ,ISNULL(AS_QteMini,0)AS_QteMini\n" +
			"                        ,ISNULL(AS_QteMaxi,0)AS_QteMaxi\n" +
			"                        ,CASE WHEN ? > ISNULL(AS_QteSto,0) THEN 1 ELSE 0 END isSuppr \n" +
			"                FROM F_ARTSTOCK \n" +
			"                WHERE DE_No = ? \n" +
			"                AND   AR_Ref = ?";

    public static final String setASQteMaxiArtStock =
            " UPDATE F_ARTSTOCK SET AS_QteMaxi = AS_QteSto\n" +
            "                WHERE AR_Ref=?\n" +
            "                AND DE_No = ?";

    public static final String updateArtStock =
            "BEGIN \n" +
                    "                      SET NOCOUNT ON;\n" +
                    "\t\t\t\t\t  \n" +
                    "\t\t\t\t\t  DECLARE @AR_Ref VARCHAR(50) = ?\n" +
                    "\t\t\t\t\t  DECLARE @DE_No INT = ?\n" +
                    "\t\t\t\t\t  DECLARE @montant FLOAT = ?\n" +
                    " DECLARE @qte FLOAT = ?\n"+
                    "                IF EXISTS (SELECT 1 FROM F_ARTSTOCK WHERE DE_No=@DE_No AND AR_Ref=@AR_Ref)\n" +
                    "                UPDATE F_ARTSTOCK SET AS_QteSto=AS_QteSto+@qte,cbModification=GETDATE(),\n" +
                    "                AS_MontSto=ROUND((CASE WHEN AS_MontSto+ @montant <0 THEN 0 ELSE ROUND(AS_MontSto+@montant,2) END),2) \n" +
                    "                WHERE DE_No=@DE_No AND AR_Ref=@AR_Ref;\n" +
                    "                ELSE\n" +
                    "                    INSERT INTO F_ARTSTOCK  \n" +
                    "                        VALUES(/*AR_Ref*/@AR_Ref,/*DE_No*/@DE_No\n" +
                    "                               ,/*AS_QteMini*/0,/*AS_QteMaxi*/0\n" +
                    "                               ,/*AS_MontSto*/ROUND(@montant,2),/*AS_QteSto*/@qte\n" +
                    "                               ,/*AS_QteRes*/0,/*AS_QteCom*/0\n" +
                    "                               ,/*AS_Principal*/0,/*AS_QteResCM*/0\n" +
                    "                               ,/*AS_QteComCM*/0,/*AS_QtePrepa*/0\n" +
                    "                               ,/*DP_NoPrincipal*/0,/*cbDP_NoPrincipal*/NULL\n" +
                    "                               ,/*DP_NoControle*/0,/*cbDP_NoControle*/NULL\n" +
                    "                               ,/*AS_QteAControler*/0,/*cbProt*/0\n" +
                    "                               ,/*cbCreateur*/'AND',/*cbModification*/GETDATE()\n" +
                    "                               ,/*cbReplication*/0,/*cbFlag*/0);\n" +
                    "                END;";

    public static final String updateArtStockReel =
            "UPDATE F_ARTSTOCK SET AS_QteCom=AS_QteCom+ ? ,cbModification=GETDATE() WHERE DE_No= ? AND AR_Ref = ?";

	public static final String articleWithStock =
			"SELECT 	AR_Condition,A.cbModification,A.AR_Ref,AR_Type,AR_Sommeil" +
			"			,AR_Design,AR_PrixTTC,FA_CodeFamille,ISNULL(AR_PrixAch,0)AR_PrixAch" +
			"			,ISNULL(AR_PrixVen,0)AR_PrixVen,ISNULL(Prix_Min,0)Prix_Min,ISNULL(Prix_Max,0)Prix_Max" +
			"			,ISNULL(AS_QteSto,0) AS_QteSto,ISNULL(AS_MontSto,0) AS_MontSto\n" +
			"FROM 		F_ARTICLE A\n" +
			"LEFT JOIN (SELECT AR_Ref, AS_QteSto, AS_MontSto " +
			"			FROM F_ARTSTOCK " +
			"			WHERE DE_No = ? )B " +
			"	ON 		A.AR_Ref = B.AR_Ref";

	public static final String articleWithStockCount =
			"SELECT max(A.cbModification) cbModification, count(*) Nb " +
					"FROM("+articleWithStock+")A";

	public static final String	getArticleWithStock =
			"SELECT B.cbModification,A.AR_Ref,AR_Type,AR_Sommeil,AR_Design,AR_PrixTTC,FA_CodeFamille,ISNULL(AR_PrixAch,0)AR_PrixAch,ISNULL(AR_PrixVen,0)AR_PrixVen,ISNULL(Prix_Min,0)Prix_Min,ISNULL(Prix_Max,0)Prix_Max,ISNULL(AS_QteSto,0) AS_QteSto,ISNULL(AS_MontSto,0) AS_MontSto\n" +
			"                FROM F_ARTICLE A\n" +
			"                INNER JOIN (SELECT AR_Ref, AS_QteSto, AS_MontSto,cbModification FROM F_ARTSTOCK WHERE DE_No = ? )B on A.AR_Ref = B.AR_Ref\n" +
			"                WHERE AS_QteSto IS NOT NULL AND AS_QteSto <>0";

	public static final String articleWithStockMax =
			"SELECT Max(cbModification)cbModification,COUNT(*) Nb\n" +
			"                FROM ("+getArticleWithStock+")A";

	public static final String  insertF_ArtStock=
	
			"\t\t\t\t\t  DECLARE @AR_Ref VARCHAR(50) = ?\n" +
			"\t\t\t\t\t  DECLARE @DE_No INT = ?\n" +
			"\t\t\t\t\t  DECLARE @montantStock VARCHAR(50) = ?\n" +
			"\t\t\t\t\t  DECLARE @qte VARCHAR(50) = ?\n" +
			"\t\t\t\t\t  DECLARE @cbCreateur VARCHAR(50) = ?;\n" +
			" INSERT INTO [dbo].[F_ARTSTOCK] "+
            " ([AR_Ref],[DE_No],[AS_QteMini],[AS_QteMaxi] "+
            " ,[AS_MontSto],[AS_QteSto],[AS_QteRes],[AS_QteCom] "+
            " ,[AS_Principal],[AS_QteResCM],[AS_QteComCM],[AS_QtePrepa] "+
            " ,[DP_NoPrincipal],[cbDP_NoPrincipal],[DP_NoControle],[cbDP_NoControle] "+
            " ,[AS_QteAControler],[cbProt],[cbCreateur],[cbModification] "+
            " ,[cbReplication],[cbFlag]) "+
    "  VALUES "+
    "        (/*AR_Ref*/@AR_Ref,/*DE_No*/@DE_No "+
    "        ,/*AS_QteMini*/0,/*AS_QteMaxi*/0 "+
    "        ,/*AS_MontSto*/ROUND(@montantStock,2),/*AS_QteSto*/@qte "+
    "        ,/*AS_QteRes*/0,/*AS_QteCom*/0 "+
    "        ,/*AS_Principal*/0,/*AS_QteResCM*/0 "+
    "        ,/*AS_QteComCM*/0,/*AS_QtePrepa*/0 "+
    "        ,/*DP_NoPrincipal*/0,/*cbDP_NoPrincipal*/NULL "+
    "        ,/*DP_NoControle*/0,/*cbDP_NoControle*/NULL "+
    "        ,/*AS_QteAControler*/0,/*cbProt*/0 "+
    "        ,/*cbCreateur*/@cbCreateur,/*cbModification*/GETDATE() "+
    "        ,/*cbReplication*/0,/*cbFlag*/0) ";
}