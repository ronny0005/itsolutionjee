package com.server.itsolution.mapper;

public class FCaisseMapper extends ObjectMapper {

    public static final String BASE_SQL
            =   "Select   CA_No,CA_Intitule,DE_No,CO_No,CO_NoCaissier,CT_Num,JO_Num,CA_Souche,cbCreateur,cbMarq From F_CAISSE";

    public static final String caisseCount
			=	"select count(*) Nb,max(cbModification)cbModification from F_CAISSE";
    public static final String getFCaisse
            =   BASE_SQL + " WHERE CA_No = ?";
	
	public static final String listeCaisseShort
			="SELECT JO_Num,CA_Intitule,CA_No,CO_NoCaissier,CA_Souche,CT_Num,cbModification "+
             "    FROM F_CAISSE";

	public static final String clotureCaisse =
			"BEGIN \n" +
					"                        SET NOCOUNT ON;\n" +
					"                        DECLARE @ProtNo INT = ?\n" +
					"                        ,@caisseDebut INT = ?\n" +
					"                        ,@caisseFin INT = ?\n" +
					"                        ,@dateCloture DATE = ?\n" +
					"                        ,@typeCloture INT = ?\n" +
					"                        ;\n" +
					"CREATE TABLE #TMPCAISSE (CA_No INT)\n" +
					"\n" +
					"                        IF (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE Prot_No=@ProtNo) = 1 \n" +
					"                                            BEGIN \n" +
					"                                                INSERT INTO #TMPCAISSE\n" +
					"                                                SELECT\tISNULL(CA.CA_No,0) CA_No \n" +
					"                                                FROM F_CAISSE CA\n" +
					"                                                INNER JOIN Z_DEPOTCAISSE C \n" +
					"                                                    ON CA.CA_No=C.CA_No\n" +
					"                                                INNER JOIN F_DEPOT D \n" +
					"                                                    ON C.DE_No=D.DE_No\n" +
					"                                                INNER JOIN F_COMPTET CT \n" +
					"                                                    ON CT.cbCT_Num = CA.cbCT_Num\n" +
					"                                                WHERE\t(@caisseDebut = 0 OR(@caisseDebut > 0 AND CA.CA_No>=@caisseDebut))\n" +
					"\t\t\t\t\t\t\t\t\t\t\t    AND\t\t(@caisseFin = 0 OR(@caisseFin> 0 AND CA.CA_No<=@caisseFin))\t\n" +
					"                                                GROUP BY CA.CA_No\n" +
					"                                            END \n" +
					"                                            ELSE \n" +
					"                                            BEGIN \n" +
					"                                                INSERT INTO #TMPCAISSE\n" +
					"                                                SELECT  CA_No = ISNULL(CA.CA_No,0)\n" +
					"                                                FROM    F_CAISSE CA\n" +
					"                                                LEFT JOIN Z_DEPOTCAISSE C \n" +
					"                                                    ON  CA.CA_No=C.CA_No\n" +
					"                                                LEFT JOIN (\tSELECT * \n" +
					"                                                            FROM Z_DEPOTUSER\n" +
					"                                                            WHERE IsPrincipal=1) D \n" +
					"                                                    ON  C.DE_No=D.DE_No\n" +
					"                                                LEFT JOIN F_COMPTET CT \n" +
					"                                                    ON  CT.cbCT_Num = CA.cbCT_Num\n" +
					"                                                WHERE   Prot_No=@ProtNo\n" +
					"                                                AND\t    (@caisseDebut = 0 OR(@caisseDebut > 0 AND CA.CA_No>=@caisseDebut))\n" +
					"                                                AND\t\t(@caisseFin = 0 OR(@caisseFin> 0 AND CA.CA_No<=@caisseFin))\t\n" +
					"                                                GROUP BY CA.CA_No\n" +
					"                                            END;\n" +
					"\n" +
					"                      UPDATE F_CREGLEMENT \n" +
					"\t\t\t\t\t\tSET RG_Cloture = @typeCloture\n" +
					"                      FROM #TMPCAISSE tmc\n" +
					"                      WHERE F_CREGLEMENT.CA_No = tmc.CA_No \n" +
					"                      AND   ((@typeCloture =1 AND RG_Date <= @dateCloture) OR (@typeCloture =0 AND RG_Date = @dateCloture))\n" +
					"                      AND   RG_Cloture<>@typeCloture;\n" +
					"                      UPDATE F_DOCENTETE \n" +
					"\t\t\t\t\t\tSET DO_Cloture = @typeCloture\n" +
					"                      FROM #TMPCAISSE tmc\n" +
					"                      WHERE F_DOCENTETE.CA_No = tmc.CA_No \n" +
					"                      AND   ((@typeCloture =1 AND DO_Date <= @dateCloture) OR (@typeCloture =0 AND DO_Date = @dateCloture))\n" +
					"                      AND   DO_Cloture <> @typeCloture;\n" +
					"END";

	public static final String getCaNum
			="DECLARE @caNo INT = ?\n" +
			"                    SELECT\tC.CA_Num\n" +
			"                            ,C.CA_Intitule\n" +
			"                            ,CONCAT(CONCAT(C.CA_Num,' - '),C.CA_Intitule) AS intitule\n" +
			"                    FROM Z_DEPOTCAISSE A\n" +
			"                    INNER JOIN Z_DEPOTSOUCHE B ON A.DE_No=B.DE_No\n" +
			"                    INNER JOIN F_COMPTEA C ON C.CA_Num = B.CA_Num\n" +
			"                    WHERE CA_No=@caNo";

	public static final String getCaisseProtNo
			="					SELECT 	CA_No = ISNULL(fca.CA_No,0) \n" +
			"                            ,IsPrincipal = MAX(CASE WHEN fco.CO_No IS NOT NULL THEN 2\n" +
			"                                					WHEN zdu.IsPrincipal = 1 THEN 1 ELSE 0 END) \n" +
			"                     		,CA_Intitule = ISNULL(fca.CA_Intitule,'') \n" +
			"                    FROM 	F_CAISSE fca\n" +
			"                    LEFT JOIN Z_DEPOTCAISSE zde \n" +
			"                        ON fca.CA_No=zde.CA_No\n" +
			"                    LEFT JOIN Z_DEPOTUSER zdu \n" +
			"                        ON zde.DE_No=zdu.DE_No\n" +
			"                    LEFT JOIN F_COMPTET fct\n" +
			"                        ON fct.CT_Num = fca.CT_Num\n" +
			"                    LEFT JOIN F_PROTECTIONCIAL fpr\n" +
			"                        ON fpr.PROT_No = zdu.Prot_No\n" +
			"                    LEFT JOIN F_COLLABORATEUR fco \n" +
			"                        ON fco.CO_Nom = fpr.PROT_User\n" +
			"                        AND fco.CO_No = fca.CO_NoCaissier\n" +
			"                    WHERE	zdu.Prot_No=?\n" +
			"                    GROUP BY fca.CA_No\n" +
			"                            ,fca.CA_Intitule;";

	public static final String insertDepotCaisse
			="INSERT INTO Z_DEPOTCAISSE VALUES (?,?)";
	
	public static final String supprDepotCaisse
			="DELETE FROM Z_DEPOTCAISSE WHERE CA_No=?";
	public static final String getCaissierByCaisse 
			= "DECLARE @caNo INT = ?\n" +
			"                    SELECT \tCO.CO_No,CO_Nom\n" +
			"                    FROM \tF_COLLABORATEUR CO\n" +
			"                    LEFT JOIN F_CAISSECAISSIER CA \n" +
			"                        ON CO.CO_No = CA.CO_No\n" +
			"                    WHERE \tCO_Caissier=1 \n" +
			"                    AND \t(0 = @caNo OR CA_No = @caNo)\n" +
			"                    GROUP BY CO.CO_No,CO_Nom";

	public static final String getCaisseByCA_No =
			"SELECT  	CT_Intitule\n" +
			"			,C.* \n" +
			"FROM    	F_CAISSE C \n" +
			"LEFT JOIN 	F_COMPTET T \n" +
			"	ON  C.CT_Num=T.CT_Num \n" +
			"WHERE   	CA_No =?";

	public static final String getCaisseDepot
			="DECLARE @protNo INT = '$prot_no'\n" +
			"                    IF (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE Prot_No=@protNo) = 1 \n" +
			"                    SELECT  ISNULL(CA.CA_No,0) CA_No\n" +
			"                            ,1 IsPrincipal\n" +
			"                            ,ISNULL(CA_Intitule,'') CA_Intitule\n" +
			"                    FROM F_CAISSE CA\n" +
			"                    INNER JOIN Z_DEPOTCAISSE C \n" +
			"                        ON CA.CA_No=C.CA_No\n" +
			"                    INNER JOIN F_DEPOT D \n" +
			"                        ON C.DE_No=D.DE_No\n" +
			"                    INNER JOIN F_COMPTET CT \n" +
			"                        ON CT.CT_Num = CA.CT_Num\n" +
			"                    GROUP BY CA.CA_No\n" +
			"                            ,CA_Intitule;                  \n" +
			"                    \n" +
			"                    ELSE \n" +
			"                  \n" +
			"                    SELECT\tISNULL(fca.CA_No,0) CA_No\n" +
			"                            ,MAX(CASE WHEN fco.CO_No IS NOT NULL THEN 2\n" +
			"                                WHEN zdu.IsPrincipal = 1 THEN 1 ELSE 0 END) IsPrincipal\n" +
			"                            ,ISNULL(fca.CA_Intitule,'') CA_Intitule\n" +
			"                    FROM F_CAISSE fca\n" +
			"                    LEFT JOIN Z_DEPOTCAISSE zde \n" +
			"                        ON fca.CA_No=zde.CA_No\n" +
			"                    LEFT JOIN Z_DEPOTUSER zdu \n" +
			"                        ON zde.DE_No=zdu.DE_No\n" +
			"                    LEFT JOIN F_COMPTET fct\n" +
			"                        ON fct.CT_Num = fca.CT_Num\n" +
			"                    LEFT JOIN F_PROTECTIONCIAL fpr\n" +
			"                        ON fpr.PROT_No = zdu.Prot_No\n" +
			"                    LEFT JOIN F_COLLABORATEUR fco \n" +
			"                        ON fco.CO_Nom = fpr.PROT_User\n" +
			"                        AND fco.CO_No = fca.CO_NoCaissier\n" +
			"                    WHERE\tzdu.Prot_No=@protNo\n" +
			"                    \n" +
			"                    GROUP BY fca.CA_No\n" +
			"                            ,fca.CA_Intitule;";

	public static final String getCaisseDepotCount
			="IF (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE Prot_No=?) = 1 \n" +
			"                   SELECT count(*) Nb FROM (" +
		"						SELECT\tD.DE_No\n" +
			"                            ,ISNULL(CA.CA_No,0) CA_No\n" +
			"                            ,1 IsPrincipal\n" +
			"                            ,ISNULL(CO_NoCaissier,'') CO_NoCaissier\n" +
			"                            ,ISNULL(CA_Intitule,'') CA_Intitule\n" +
			"                            ,ISNULL(JO_Num,'') JO_Num\n" +
			"                            ,ISNULL(CA.CT_Num,'') CT_Num\n" +
			"                            ,ISNULL(CT_Intitule,'') CT_Intitule\n" +
			"                    FROM F_CAISSE CA\n" +
			"                    INNER JOIN Z_DEPOTCAISSE C ON CA.CA_No=C.CA_No\n" +
			"                    INNER JOIN F_DEPOT D ON C.DE_No=D.DE_No\n" +
			"                    INNER JOIN F_COMPTET CT ON CT.CT_Num = CA.CT_Num\n" +
			"                    GROUP BY D.DE_No,CA.CA_No,CA_Intitule,CA.CT_Num,CT_Intitule,CO_NoCaissier,JO_Num) A;\n" +
			"                    ELSE \n" +
			"                   SELECT count(*) Nb FROM (" +
			"					SELECT\tD.DE_No\n" +
			"                            ,ISNULL(CA.CA_No,0) CA_No\n" +
			"                            ,ISNULL(IsPrincipal,1)IsPrincipal\n" +
			"                            ,ISNULL(CO_NoCaissier,'') CO_NoCaissier\n" +
			"                            ,ISNULL(CA_Intitule,'') CA_Intitule\n" +
			"                            ,ISNULL(JO_Num,'') JO_Num\n" +
			"                            ,ISNULL(CA.CT_Num,'') CT_Num\n" +
			"                            ,ISNULL(CT_Intitule,'') CT_Intitule\n" +
			"                    FROM F_CAISSE CA\n" +
			"                    LEFT JOIN Z_DEPOTCAISSE C ON CA.CA_No=C.CA_No\n" +
			"                    LEFT JOIN (\tSELECT * \n" +
			"                                FROM Z_DEPOTUSER\n" +
			"                                WHERE IsPrincipal=1) D ON C.DE_No=D.DE_No\n" +
			"                    LEFT JOIN F_COMPTET CT ON CT.CT_Num = CA.CT_Num\n" +
			"                    WHERE Prot_No=? \n" +
			"                    GROUP BY D.DE_No,CA.CA_No,IsPrincipal,CA_Intitule,CA.CT_Num,CT_Intitule,CO_NoCaissier,JO_Num)A;";

    public static final String deleteCaisse
			="DELETE FROM F_CAISSE WHERE CA_No = ?";

	public static final String insertCaisse
			="BEGIN  "+
             "     SET NOCOUNT ON; "+
			"	  DECLARE @CA_Intitule AS VARCHAR(50) = ? "+
			"				,@CO_NoCaissier AS INT = ? "+
			"				,@JO_Num AS VARCHAR(50) = ? "+
			"				,@cbCreateur AS VARCHAR(50) = ? "+
			"				,@DE_No AS INT = ? "+
			"				,@caNo AS INT = ISNULL((SELECT MAX(CA_No) FROM F_CAISSE)+1,0);"+
			"				DECLARE @generated_keys table([cbMarq] int);\n" +
			"				 "+
            "      INSERT INTO [dbo].[F_CAISSE] "+
            "    ([CA_No],[CA_Intitule],[DE_No],[CO_No] "+
            "    ,[CO_NoCaissier],[CT_Num] "+
            "    ,[JO_Num],[CA_IdentifCaissier],[CA_DateCreation],[N_Comptoir] "+
            "    ,[N_Clavier],[CA_LignesAfficheur],[CA_ColonnesAfficheur],[CA_ImpTicket] "+
            "    ,[CA_SaisieVendeur],[CA_Souche],[cbProt],[cbCreateur] "+
            "    ,[cbModification],[cbReplication],[cbFlag]) OUTPUT INSERTED.cbMarq INTO @generated_keys "+
          "VALUES "+
            "    (/*CA_No*/@caNo,/*CA_Intitule*/@CA_Intitule,/*DE_No*/@DE_No "+
            "    ,/*CO_No*/0,/*CO_NoCaissier*/@CO_NoCaissier "+
            "    ,/*CT_Num*/(SELECT TOP 1 CT_Num "+
            "                FROM F_COMPTET "+
            "                WHERE CT_Num LIKE '%DIVERS%' "+
            "                AND CT_Type=0),/*JO_Num*/@JO_Num,/*CA_IdentifCaissier*/0 "+
            "    ,/*CA_DateCreation*/CAST(GETDATE() AS DATE),/*N_Comptoir*/1,/*N_Clavier*/1 "+
            "    ,/*CA_LignesAfficheur*/0,/*CA_ColonnesAfficheur*/0,/*CA_ImpTicket*/0 "+
            "    ,/*CA_SaisieVendeur*/0,/*CA_Souche*/0,/*cbProt*/0 "+
            "    ,/*cbCreateur*/@cbCreateur,/*cbModification*/CAST(GETDATE() AS DATE),/*cbReplication*/0,/*cbFlag*/0); "+
			"\tSELECT\tcbMarq\n" +
			"\t\t\t,CA_No = @caNo \n" +
			"\tFROM\t@generated_keys;\n" +
            "    END ";
}