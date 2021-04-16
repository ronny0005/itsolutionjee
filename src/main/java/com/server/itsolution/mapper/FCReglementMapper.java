package com.server.itsolution.mapper;

public class FCReglementMapper extends ObjectMapper {

	public static final String BASE_SQL //
			= "Select   RG_No,CT_NumPayeur,RG_Date\n" +
			"    ,RG_Reference,RG_Libelle,RG_Montant,RG_MontantDev\n" +
			"    ,N_Reglement,RG_Impute,RG_Compta,EC_No\n" +
			"    ,RG_Type,RG_Cours,N_Devise\n" +
			"    ,JO_Num,CG_NumCont,RG_Impaye\n" +
			"    ,CG_Num,RG_TypeReg,RG_Heure\n" +
			"    ,RG_Piece,CA_No,cbCA_No\n" +
			"    ,CO_NoCaissier,RG_Banque,RG_Transfere\n" +
			"    ,RG_Cloture,RG_Ticket,RG_Souche,CT_NumPayeurOrig\n" +
			"    ,RG_DateEchCont,CG_NumEcart\n" +
			"    ,JO_NumEcart,RG_MontantEcart,RG_NoBonAchat,cbProt\n" +
			"    ,cbMarq,cbCreateur,cbModification,cbReplication\n" +
			"    ,cbFlag From F_CReglement";

	public static final String journeeCloture=
			"SET DATEFORMAT dmy; \n" +
			"DECLARE @date DATE = ?; \n" +
			"DECLARE @caNo INT = ?;\n" +
			"SELECT   Nb = count(*) \n" +
			" FROM    F_CREGLEMENT \n" +
			" WHERE   RG_Cloture=1 \n" +
			" AND     RG_Date=@date\n" +
			" AND     CA_No= @caNo";

	public static final String getListeReglementMajComptable =
			"\n" +
					"                    DECLARE @dateDebut DATE = ?\n" +
					"                            ,@dateFin DATE = ?\n" +
					"                            ,@etatPiece INT = ? \n" +
					"                            ,@typeTransfert INT = ?;\n" +
					"                    DECLARE @rgCompta INT = (SELECT CASE WHEN @etatPiece = 1 THEN 1 ELSE 0 END)\n" +
					"                            ,@rgType INT = (SELECT CASE WHEN @typeTransfert = 4 THEN 1 ELSE 0 END)\n" +
					"                            ,@caNo INT = ?;\n" +
					"\n" +
					"                    SELECT RG_No\n" +
					"                  FROM F_CREGLEMENT\n" +
					"                  WHERE (@dateDebut ='' OR RG_Date>= @dateDebut )\n" +
					"                  AND ( @dateFin ='' OR RG_Date<= @dateFin )\n" +
					"                  AND  RG_Compta = @rgCompta\n" +
					"                  AND RG_Type = @rgType\n" +
					"                  AND (@caNo =0 OR CA_No = @caNo) ";
	public static final String getReglementFacture =
			"SELECT creg.RG_No\n" +
					"                    FROM F_DOCENTETE doc\n" +
					"                    INNER JOIN F_REGLECH reg\n" +
					"                        ON  doc.cbDO_Piece = reg.cbDO_Piece\n" +
					"                        AND doc.DO_Domaine = reg.DO_Domaine\n" +
					"                        AND doc.DO_Type = reg.DO_Type\n" +
					"                    INNER F_CREGLEMENT creg\n" +
					"                        ON  creg.RG_No = reg.RG_No\n" +
					"                    WHERE   doc.cbMarq = ? \n" +
					"                    AND     EC_No = 0";

	public static final String modifReglement =
			"UPDATE F_CREGLEMENT SET RG_Date= ?,RG_Reference= ?,RG_Libelle= ?,RG_Montant= ?\n" +
					"                            ,RG_MontantDev= ?,N_Reglement= ?,RG_Impute= ?,RG_Compta= ?\n" +
					"                            ,EC_No= ?,RG_Type= ?,RG_Cours= ?,N_Devise= ?,JO_Num= ?\n" +
					"                            ,CG_NumCont= ?,RG_Impaye= ?,CG_Num= ?,RG_TypeReg= ?\n" +
					"                            ,RG_Heure= ?,RG_Piece= ?,CA_No= ?,CO_NoCaissier= ?\n" +
					"                            ,RG_Banque= ?,RG_Transfere= ?,RG_Cloture= ?,RG_Ticket= ?\n" +
					"                            ,RG_Souche= ?,CT_NumPayeurOrig= ?,RG_DateEchCont= ?\n" +
					"                            ,CG_NumEcart= ?,JO_NumEcart= ?,RG_MontantEcart= ?,RG_NoBonAchat= ?\n" +
					"                            ,CbCreateur= ? WHERE cbMarq = ?";
	public static final String getReglementByCbmarq = BASE_SQL + " WHERE cbMarq = ?";


	public static final String getMajCaisseRGNo
		= "UPDATE F_CREGLEMENT SET CA_No=?, CO_NoCaissier=? WHERE RG_No=?";


	public static final String updateRgImputeSupprReglt
			="DECLARE @rgNo AS INT = ? " +
			"                    UPDATE F_CREGLEMENT SET RG_Impute = \n" +
			"                     (SELECT CASE WHEN RG_Impute = 1 THEN 0 ELSE RG_Impute END\n" +
			"                    FROM F_CREGLEMENT\n" +
			"                    WHERE RG_No=@rgNo) WHERE RG_No=@rgNo";

	public static final String insertFactReglSuppr
		= "INSERT INTO [dbo].[Z_FACT_REGL_SUPPR]([DO_Domaine],[DO_Type],[DO_Piece],[CbMarq_Entete],[RG_No],[CbMarq_RG])"+
	"		VALUES      (/*DO_Domaine*/ ?,/*DO_Type*/    ?,/*DO_Piece, varchar(25),*/?"+
	"		,/*CbMarq_Entete*/?,/*RG_No*/?,/*CbMarq_RG*/?)";

	public static final String isRegleFull
		= "SELECT CASE WHEN RG_Montant = RC_Montant THEN 1 ELSE 0 END AS VAL\n" +
			"                FROM(\n" +
			"                SELECT RG_Montant = Max(RG_Montant)" +
			"						,RC_Montant = ISNULL(SUM(RC_Montant),0) \n" +
			"                FROM 	F_CREGLEMENT C\n" +
			"                LEFT JOIN F_REGLECH D \n" +
			"                    ON D.RG_No=C.RG_No\n" +
			"                WHERE C.cbMarq=?)A";

	public static final String getReglementByClient =
					"BEGIN \n" +
							"                    SET DATEFORMAT dmy;" +
							"					 SET NOCOUNT ON;\n" +
							"                        \n" +
							"                    DECLARE @dateDeb AS DATE \n" +
							"                    ,@dateFin AS DATE\n" +
							"                    ,@rgImpute AS INT\n" +
							"                    ,@ctNum AS VARCHAR(50)\n" +
							"                    ,@collab AS INT\n" +
							"                    ,@nReglement AS INT \n" +
							"                    ,@caNo AS INT\n" +
							"                    ,@coNoCaissier AS INT\n" +
							"                    ,@rgType AS INT;\n" +
							"                    SET @dateDeb = ?;\n" +
							"                    SET @dateFin = ?;\n" +
							"                    SET @rgImpute = ?;\n" +
							"                    SET @ctNum = ?;\n" +
							"                    SET @collab = ?;\n" +
							"                    SET @nReglement = ?;\n" +
							"                    SET @caNo = ?;\n" +
							"                    SET @coNoCaissier = ?; \n" +
							"                    SET @rgType = ?;\n" +
							"                    DECLARE @admin INT\n" +
							"                    DECLARE @ProtNo INT\n" +
							"                    SET @ProtNo = ?\n" +
							"                    \n" +
							"                    CREATE TABLE #TMPCAISSE (CA_No INT)\n" +
							"                                        \n" +
							"                    IF (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE Prot_No=@ProtNo) = 1 \n" +
							"                                        BEGIN \n" +
							"                                        INSERT INTO #TMPCAISSE\n" +
							"                                        SELECT\tISNULL(CA.CA_No,0) CA_No \n" +
							"                                        FROM F_CAISSE CA\n" +
							"                                        INNER JOIN Z_DEPOTCAISSE C \n" +
							"                                            ON CA.CA_No=C.CA_No\n" +
							"                                        INNER JOIN F_DEPOT D \n" +
							"                                            ON C.DE_No=D.DE_No\n" +
							"                                        INNER JOIN F_COMPTET CT \n" +
							"                                            ON CT.cbCT_Num = CA.cbCT_Num\n" +
							"                                        WHERE (@caNo = 0 OR CA.CA_No = @caNo) \n" +
							"                                        GROUP BY CA.CA_No\n" +
							"                                        END \n" +
							"                                        ELSE \n" +
							"                                        BEGIN \n" +
							"                                        INSERT INTO #TMPCAISSE\n" +
							"                                        SELECT\tISNULL(CA.CA_No,0) CA_No\n" +
							"                                        FROM F_CAISSE CA\n" +
							"                                        LEFT JOIN Z_DEPOTCAISSE C \n" +
							"                                            ON CA.CA_No=C.CA_No\n" +
							"                                        LEFT JOIN (\tSELECT * \n" +
							"                                                    FROM Z_DEPOTUSER\n" +
							"                                                    WHERE IsPrincipal=1) D \n" +
							"                                            ON C.DE_No=D.DE_No\n" +
							"                                        LEFT JOIN F_COMPTET CT ON CT.CT_Num = CA.CT_Num\n" +
							"                                        WHERE Prot_No=@ProtNo\n" +
							"                                        AND (@caNo = 0 OR CA.CA_No = @caNo) \n" +
							"                                        GROUP BY CA.CA_No\n" +
							"                                        END;\n" +
							"\n" +
							"                    SELECT PROT_User\n" +
							"                            ,CASE WHEN ABS(DATEDIFF(d,GETDATE(),C.RG_Date))>= (select PR_DelaiPreAlert from P_PREFERENCES) THEN 1 ELSE 0 END DO_Modif\n" +
							"                            ,C.JO_Num,C.CO_NoCaissier,C.CT_NumPayeur,C.CG_Num,RG_Piece,ISNULL(RC_Montant,0) AS RC_Montant\n" +
							"                            ,C.RG_No,CAST(RG_Date as date) RG_Date,RG_Libelle,RG_Montant,C.CA_No,C.CO_NoCaissier\n" +
							"                            ,ISNULL(CO_Nom,'')CO_Nom,ISNULL(CA_Intitule,'')CA_Intitule,RG_Impute,RG_TypeReg,N_Reglement\n" +
							"                    FROM F_CREGLEMENT C\n" +
							"                    LEFT JOIN F_CAISSE CA \n" +
							"                        ON CA.CA_No=C.CA_No \n" +
							"                    LEFT JOIN F_PROTECTIONCIAL Pr \n" +
							"                        ON CAST(Pr.PROT_No AS VARCHAR(5)) = C.cbCreateur\n" +
							"                    LEFT JOIN F_COLLABORATEUR CO \n" +
							"                        ON C.CO_NoCaissier = CO.CO_No\n" +
							"                    LEFT JOIN (\tSELECT A.RG_No,SUM(RC_Montant) AS RC_Montant\n" +
							"                                FROM(\tSELECT RG_No,sum(RC_Montant) AS RC_Montant \n" +
							"                                        FROM F_REGLECH\n" +
							"                                        GROUP BY RG_No\n" +
							"                                        UNION\n" +
							"                                        SELECT  A.RG_No\n" +
							"                                                ,SUM(ISNULL(ABS(C.RG_Montant),0)) \n" +
							"                                        FROM    F_CREGLEMENT A\n" +
							"                                        INNER JOIN Z_RGLT_BONDECAISSE B \n" +
							"                                            ON A.RG_No = B.RG_No_RGLT\n" +
							"                                        INNER JOIN F_CREGLEMENT C \n" +
							"                                            ON B.RG_No = C.RG_No\n" +
							"                                        GROUP BY A.RG_No\n" +
							"                                        UNION\n" +
							"                                        SELECT  A.RG_No\n" +
							"                                                ,SUM(ISNULL(ABS(RG_Montant),0)) \n" +
							"                                        FROM F_CREGLEMENT A\n" +
							"                                        INNER JOIN Z_RGLT_BONDECAISSE B \n" +
							"                                            ON A.RG_No = B.RG_No\n" +
							"                                        GROUP BY A.RG_No\n" +
							"                                ) A \n" +
							"\t\t\t\t\t\t\t\tGROUP BY RG_No) R ON R.RG_No=c.RG_No\n" +
							"\t\t\t        WHERE @rgType = RG_Type \n" +
							"\t\t\t        AND RG_Date BETWEEN @dateDeb AND @dateFin \n" +
							"\t\t\t        AND (-1=@rgImpute OR RG_Impute=@rgImpute)\n" +
							"                    AND ((''=@ctNum AND ct_numpayeur IS NOT NULL) OR ct_numpayeur = @ctNum OR ('1'=@collab AND CAST(C.CO_NoCaissier AS NVARCHAR(10)) = @ctNum))\n" +
							"                    AND (((0=@nReglement OR N_Reglement=@nReglement) \n" +
							"\t\t\t                AND ((@collab = 1 AND RG_Banque=3) OR (@collab = 0))\n" +
							"                                AND \tCA.CA_No IN (SELECT CA_No FROM #TMPCAISSE))\n" +
							"\t\t\t                OR ('0'<>@coNoCaissier AND C.CO_NoCaissier=@coNoCaissier AND N_Reglement='05') )\n" +
							"                    ORDER BY C.RG_No\n" +
							"                END";

	public static final String getReglementByClientFacture =
			"DECLARE @CT_Num as VARCHAR(50) = ?\n" +
					"                DECLARE @DO_Piece as VARCHAR(10) = ?\n" +
					"                DECLARE @DO_Type as VARCHAR(10) = ?\n" +
					"                DECLARE @DO_Domaine as VARCHAR(10) = ?;\n" +
					"                \n" +
					"               with cte (ligne,do_piece,ct_numpayeur,RG_No,RG_Date,DR_Date,RG_Libelle,RG_Montant,CA_No,RC_Montant,DL_MontantTTC,cumul) \n" +
					"                as( \n" +
					"                SELECT 0 AS ligne,'SOLDE INITIALE' do_piece,'' ct_numpayeur,'' RG_No,'' RG_Date,''DR_Date,'SOLDE INITIALE' RG_Libelle,0 RG_Montant,'' CA_No,0 RC_Montant, 0 DL_MontantTTC,DL_MontantTTC AS cumul \n" +
					"                FROM F_CREGLEMENT C \n" +
					"                LEFT JOIN (SELECT RG_No,DR_No,sum(RC_Montant) AS RC_Montant FROM F_REGLECH GROUP BY RG_No,DR_No) R ON R.RG_No=c.RG_No \n" +
					"                INNER JOIN F_DOCREGL D ON D.DR_No = R.DR_No \n" +
					"                INNER JOIN (SELECT DO_PIECE,DO_Type,DO_Domaine,SUM(DL_MontantTTC) DL_MontantTTC FROM F_DOCLIGNE GROUP BY DO_PIECE,DO_Type,DO_Domaine) DL ON DL.DO_Piece=D.DO_Piece AND DL.DO_Type=D.DO_Type AND DL.DO_Domaine =D.DO_Domaine \n" +
					"                where D.DO_Piece = @DO_Piece AND D.DO_Type = @DO_Type AND D.DO_Domaine = @DO_Domaine AND CT_NumPayeur=@CT_Num \n" +
					"                UNION \n" +
					"                SELECT ROW_NUMBER() OVER(order by c.cbMarq asc) AS ligne,D.do_piece,ct_numpayeur,C.RG_No,RG_Date,DR_Date,RG_Libelle,RG_Montant,CA_No,ISNULL(RC_Montant,0) AS RC_Montant,SUM(DL_MontantTTC) DL_MontantTTC,-ISNULL(RC_Montant,0) CUMUL \n" +
					"                FROM F_CREGLEMENT C \n" +
					"                LEFT JOIN (SELECT RG_No,DR_No,sum(RC_Montant) AS RC_Montant FROM F_REGLECH GROUP BY RG_No,DR_No) R ON R.RG_No=c.RG_No \n" +
					"                INNER JOIN F_DOCREGL D ON D.DR_No = R.DR_No \n" +
					"                INNER JOIN (SELECT DO_PIECE,DO_Type,DO_Domaine,SUM(DL_MontantTTC) DL_MontantTTC FROM F_DOCLIGNE GROUP BY DO_PIECE,DO_Type,DO_Domaine) DL ON DL.DO_Piece=D.DO_Piece AND DL.DO_Type=D.DO_Type AND DL.DO_Domaine =D.DO_Domaine \n" +
					"                where D.DO_Piece = @DO_Piece AND D.DO_Type = @DO_Type AND D.DO_Domaine = @DO_Domaine AND CT_NumPayeur=@CT_Num \n" +
					"                group by D.do_piece,ct_numpayeur,C.RG_No,RG_Date,DR_Date,RG_Libelle,RG_Montant,RC_Montant,CA_No,c.cbMarq) \n" +
					"                SELECT T1.RG_No,T1.ligne,T1.do_piece,T1.ct_numpayeur,T1.RG_No,T1.RG_Date,T1.DR_Date,T1.RG_Libelle,T1.RG_Montant,T1.CA_No,T1.RC_Montant,T1.DL_MontantTTC,SUM(T2.cumul) CUMUL \n" +
					"                FROM CTE T1 \n" +
					"                INNER JOIN CTE T2 ON T1.ligne>=T2.ligne \n" +
					"                GROUP BY T1.RG_No,T1.ligne,T1.do_piece,T1.ct_numpayeur,T1.RG_No,T1.RG_Date,T1.DR_Date,T1.RG_Libelle,T1.RG_Montant,T1.CA_No,T1.RC_Montant,T1.DL_MontantTTC \n" +
					"                ORDER BY T1.ligne ";

	public static final String getReglementByRGNo =
			BASE_SQL + " WHERE RG_No=?";

	public static final String updateImpute =
			"UPDATE F_CREGLEMENT SET RG_Impute=1,cbModification=GETDATE() WHERE RG_No=?";

	public static final String incrementeCOLREGLEMENT =
			"IF EXISTS (SELECT CR_Numero01 FROM P_COLREGLEMENT)\n" +
					"BEGIN\n" +
					"\tUPDATE P_COLREGLEMENT SET CR_Numero01 = CR_Numero01+1\n" +
					"END\n" +
					"ELSE \n" +
					"BEGIN \n" +
					"\tINSERT INTO [dbo].[P_COLREGLEMENT]\n" +
					"           ([CR_NumPiece01],[CR_NumPiece02],[CR_Numero01]\n" +
					"           ,[CR_Numero02],[CR_ColReglement01],[CR_ColReglement02],[CR_ColReglement03]\n" +
					"           ,[CR_ColReglement04],[CR_ColReglement05],[CR_ColReglement06],[CR_ColReglement07]\n" +
					"           ,[CR_ColReglement08],[CR_ColReglement09],[CR_ColReglement10],[CR_ColReglement11]\n" +
					"           ,[CR_ColReglement12],[CR_ColReglement13],[CR_ColReglement14],[CR_ColReglement15]\n" +
					"           ,[CR_ColReglement16],[CR_ColReglement17],[CR_ColReglement18],[CR_ColReglement19]\n" +
					"           ,[CR_ColReglement20],[CR_ColReglement21],[CR_ColReglement22],[CR_ColReglement23])\n" +
					"     VALUES\n" +
					"           (/*CR_NumPiece01*/0,/*CR_NumPiece02*/0,/*CR_Numero01*/'0',/*CR_Numero02*/'0'\n" +
					"           ,/*CR_ColReglement01*/0,/*CR_ColReglement02*/0,/*CR_ColReglement03*/0,/*CR_ColReglement04*/0\n" +
					"           ,/*CR_ColReglement05*/0,/*CR_ColReglement06*/0,/*CR_ColReglement07*/0,/*CR_ColReglement08*/0\n" +
					"           ,/*CR_ColReglement09*/0,/*CR_ColReglement10*/0,/*CR_ColReglement11*/0,/*CR_ColReglement12*/0\n" +
					"           ,/*CR_ColReglement13*/0,/*CR_ColReglement14*/0,/*CR_ColReglement15*/0,/*CR_ColReglement16*/0\n" +
					"           ,/*CR_ColReglement17*/0,/*CR_ColReglement18*/0,/*CR_ColReglement19*/0,/*CR_ColReglement20*/0\n" +
					"           ,/*CR_ColReglement21*/0,/*CR_ColReglement22*/0,/*CR_ColReglement23*/0)\n" +
					"END\n";

	public static final String getRgNoFromCbMarq = "SELECT RG_No FROM F_CREGLEMENT WHERE cbMarq = ?";

	public static final String isImpute =
				"DECLARE @rgNo INT = ?;\n" +
						"                WITH _UnionReglEch_ AS(\n" +
						"                    SELECT  RG_No\n" +
						"                            ,RC_Montant = sum(RC_Montant) \n" +
						"                    FROM    F_REGLECH\n" +
						"                    GROUP BY RG_No\n" +
						"                    UNION\n" +
						"                    SELECT  A.RG_No\n" +
						"                            ,SUM(ISNULL(ABS(C.RG_Montant),0)) \n" +
						"                    FROM    F_CREGLEMENT A\n" +
						"                    INNER JOIN Z_RGLT_BONDECAISSE B \n" +
						"                        ON  A.RG_No = B.RG_No_RGLT\n" +
						"                    INNER JOIN F_CREGLEMENT C\n" +
						"                        ON  B.RG_No = C.RG_No\n" +
						"                    GROUP BY A.RG_No\n" +
						"                )\n" +
						"                \n" +
						"                SELECT  A.RG_No\n" +
						"                        ,MontantImpute = RG_Montant-isnull(RC_Montant,0)\n" +
						"                        ,isImpute = CASE WHEN RG_Montant-isnull(RC_Montant,0) = 0 THEN 1 ELSE 0 END \n" +
						"                FROM    F_CREGLEMENT A\n" +
						"                LEFT JOIN (\n" +
						"                    SELECT  A.RG_No\n" +
						"                            ,RC_Montant = SUM(RC_Montant)\n" +
						"                    FROM    _UnionReglEch_ A \n" +
						"                    GROUP BY RG_No\n" +
						"                )B \n" +
						"                    ON A.RG_No = B.RG_No\n" +
						"                WHERE A.RG_No =@rgNo;";
	public static final String addCReglementFacture =
					"                SET DATEFORMAT dmy;\n" +
					"				 DECLARE @CT_NumPayeur AS VARCHAR(50) = ?\n" +
					"                DECLARE @RG_Date AS DATE = ?\n" +
					"                DECLARE @RG_Reference AS VARCHAR(50) = ?\n" +
					"                DECLARE @RG_Libelle AS VARCHAR(50) = ?\n" +
					"                DECLARE @RG_Montant AS FLOAT = ?\n" +
					"                DECLARE @RG_MontantDev AS FLOAT = ?\n" +
					"                DECLARE @N_Reglement AS INT = ?\n" +
					"                DECLARE @RG_Impute AS INT = ?\n" +
					"                DECLARE @RG_Compta AS INT = ?\n" +
					"                DECLARE @EC_No AS INT = ?\n" +
					"                DECLARE @RG_Type AS INT = ?\n" +
					"                DECLARE @RG_Cours AS FLOAT = ?\n" +
					"                DECLARE @N_Devise AS INT = ?\n" +
					"                DECLARE @JO_Num AS VARCHAR(50) = ?\n" +
					"                DECLARE @CG_NumCont AS VARCHAR(50) = ?\n" +
					"                DECLARE @RG_Impaye AS VARCHAR(50) = ?\n" +
					"                DECLARE @CG_Num AS VARCHAR(50) = ?\n" +
					"                DECLARE @RG_TypeReg AS INT = ?\n" +
					"                DECLARE @RG_Heure AS VARCHAR(50) = ?\n" +
					"                DECLARE @CA_No AS INT = ?\n" +
					"                DECLARE @CO_NoCaissier AS INT = ?\n" +
					"                DECLARE @RG_Banque AS INT = ?\n" +
					"                DECLARE @RG_Transfere AS INT = ?\n" +
					"                DECLARE @RG_Cloture AS INT = ?\n" +
					"                DECLARE @RG_Ticket AS INT = ?\n" +
					"                DECLARE @RG_Souche AS INT = ?\n" +
					"                DECLARE @CT_NumPayeurOrig AS VARCHAR(20) = ?\n" +
					"                DECLARE @RG_DateEchCont AS  DATE = ?\n" +
					"                DECLARE @CG_NumEcart AS VARCHAR(20) = ?\n" +
					"                DECLARE @JO_NumEcart AS VARCHAR(20) = ?\n" +
					"                DECLARE @RG_MontantEcart AS FLOAT = ?\n" +
					"                DECLARE @RG_NoBonAchat AS INT = ?\n" +
					"                DECLARE @cbCreateur AS VARCHAR(20) = ?\n" +
					"                DECLARE @RG_Piece AS VARCHAR(20) = ''\n" +
					"				DECLARE @generated_keys table([cbMarq] int);" +
					"\t\t\t\t\n" +
					//"                IF NOT EXISTS (SELECT 1 FROM F_CREGLEMENT WHERE RG_Libelle = @RG_Libelle AND RG_Date=@RG_Date AND RG_Montant=@RG_Montant AND RG_Type=@RG_Type AND RG_TypeReg = @RG_TypeReg) \n" +
					"                INSERT INTO [dbo].[F_CREGLEMENT] \n" +
					"                 ([RG_No],[CT_NumPayeur],[RG_Date],[RG_Reference] \n" +
					"                 ,[RG_Libelle],[RG_Montant],[RG_MontantDev],[N_Reglement] \n" +
					"                 ,[RG_Impute],[RG_Compta],[EC_No] \n" +
					"                 ,[RG_Type],[RG_Cours],[N_Devise],[JO_Num] \n" +
					"                 ,[CG_NumCont],[RG_Impaye],[CG_Num],[RG_TypeReg] \n" +
					"                 ,[RG_Heure],[RG_Piece],[CA_No] \n" +
					"                 ,[CO_NoCaissier],[RG_Banque],[RG_Transfere] \n" +
					"                 ,[RG_Cloture],[RG_Ticket],[RG_Souche],[CT_NumPayeurOrig] \n" +
					"                 ,[RG_DateEchCont],[CG_NumEcart],[JO_NumEcart],[RG_MontantEcart] \n" +
					"                 ,[RG_NoBonAchat],[cbProt],[cbCreateur],[cbModification] \n" +
					"                 ,[cbReplication],[cbFlag]) OUTPUT INSERTED.cbMarq INTO @generated_keys\n" +
					"                 VALUES \n" +
					"                    (/*RG_No*/ISNULL((SELECT MAX(RG_No)+1 FROM F_CREGLEMENT),0),/*CT_NumPayeur*/@CT_NumPayeur\n" +
					"\t\t\t\t\t,/*RG_Date*/@RG_Date,/*RG_Reference*/@RG_Reference \n" +
					"\t\t\t\t\t,/*RG_Libelle*/@RG_Libelle,/*RG_Montant*/ @RG_Montant\n" +
					"\t\t\t\t\t,/*RG_MontantDev*/@RG_MontantDev,/*N_Reglement*/@N_Reglement\n" +
					"\t\t\t\t\t,/*RG_Impute*/@RG_Impute,/*RG_Compta*/@RG_Compta\n" +
					"\t\t\t\t\t,/*EC_No*/@EC_No,/*RG_Type*/@RG_Type,/*RG_Cours*/@RG_Cours\n" +
					"\t\t\t\t\t,/*N_Devise*/@N_Devise,/*JO_Num*/@JO_Num\n" +
					"\t\t\t\t\t,/*CG_NumCont*/@CG_NumCont,/*RG_Impaye*/@RG_Impaye,/*CG_Num*/@CG_Num,/*RG_TypeReg*/ @RG_TypeReg\n" +
					"\t\t\t\t\t,/*RG_Heure, char(9),*/(SELECT '000' + CAST(DATEPART(HOUR, GETDATE()) as VARCHAR(2)) + CAST(DATEPART(MINUTE, GETDATE()) as VARCHAR(2)) + CAST(DATEPART(SECOND, GETDATE()) as VARCHAR(2)))\n" +
					"\t\t\t\t\t,/*RG_Piece*/(CASE WHEN @RG_TypeReg <>2 THEN (SELECT(ISNULL((SELECT TOP 1 CR_Numero01 AS valeur FROM P_COLREGLEMENT ORDER BY cbMarq DESC),1))) ELSE @RG_Piece END) ,/*CA_No*/@CA_No,/*CO_NoCaissier*/@CO_NoCaissier,/*RG_Banque*/@RG_Banque,/*RG_Transfere*/@RG_Transfere \n" +
					"\t\t\t\t\t,/*RG_Cloture*/@RG_Cloture,/*RG_Ticket*/@RG_Ticket\n" +
					"\t\t\t\t\t,/*RG_Souche*/@RG_Souche,/*CT_NumPayeurOrig*/@CT_NumPayeurOrig,/*RG_DateEchCont*/@RG_DateEchCont,/*CG_NumEcart*/@CG_NumEcart\n" +
					"\t\t\t\t\t,/*JO_NumEcart*/@JO_NumEcart,/*RG_MontantEcart*/@RG_MontantEcart,/*RG_NoBonAchat*/@RG_NoBonAchat,/*cbProt*/0,/*cbCreateur*/@cbCreateur\n" +
					"\t\t\t\t\t,/*cbModification*/GETDATE(),/*cbReplication*/0,/*cbFlag*/0);" +
					"SELECT cbMarq FROM @generated_keys";

	public static final String setDO_Modif
			= " SELECT CASE WHEN ABS(DATEDIFF(d,GETDATE(),RG_Date))>= (select PR_DelaiPreAlert" +
			" FROM P_PREFERENCES) THEN 1 ELSE 0 END DO_Modif " +
			" FROM F_CREGLEMENT " +
			" WHERE RG_No=?";

	public static final String getMajAnalytique
			= " DECLARE @dateDeb AS DATE = ? " +
			" ,@dateFin AS DATE = ? " +
			" ,@CA_Num AS VARCHAR(50) = ? " +
			" ,@statut AS INT = ? " +
			" " +
			" SELECT A.RG_No,RG_Libelle,RG_Montant,ca.CA_Intitule,EC_No,N_Analytique,Cpa.CA_Num,RG_Montant AS EA_Montant,0 as EA_Quantite " +
			" FROM F_CREGLEMENT A " +
			" INNER JOIN F_CAISSE CA  " +
			" 	ON CA.CA_No = A.CA_No " +
			" INNER JOIN Z_RGLT_COMPTEA B " +
			" 	ON A.RG_No = B.RG_No " +
			" INNER JOIN F_COMPTEA Cpa  " +
			" 	ON Cpa.CA_Num = B.CA_Num " +
			" WHERE RG_Compta=1 " +
			" AND RG_Date BETWEEN @dateDeb AND @dateFin " +
			" AND (@CA_Num='' OR (@CA_Num=B.CA_Num)) " +
			" AND ((@statut = 0 AND EC_No NOT IN (SELECT EC_No FROM F_ECRITUREA)) OR (@statut = 1 AND EC_No IN (SELECT EC_No FROM F_ECRITUREA)))";

		public static final String supprRgltAssocie
				=	"BEGIN  "+
                    "    SET NOCOUNT ON; "+
                    "    DECLARE @rgNo AS INT = ? "+
					"	DELETE FROM F_CREGLEMENT WHERE RG_No IN (   SELECT RG_No  "+
                    "                                                FROM [Z_RGLT_BONDECAISSE]  "+
                    "                                                WHERE RG_No_RGLT=@rgNo) "+
                    "    DELETE FROM [Z_RGLT_BONDECAISSE] WHERE RG_No_RGLT=@rgNo; END";
					
	
		public static final String supprReglement
				  = " DECLARE @RG_No AS INT = ? "+
					" DELETE FROM F_REGLECH WHERE RG_No = @RG_No AND RC_Montant=0; "+
                    " DELETE FROM F_CREGLEMENT WHERE RG_No = @RG_No; "+
                    " DELETE FROM Z_RGLT_BONDECAISSE WHERE RG_No = @RG_No; "+
                    " DELETE FROM Z_RGLT_BONDECAISSE WHERE RG_No_RGLT = @RG_No";

		public static final String insertF_Reglement
		 = " BEGIN "+
			" SET NOCOUNT ON; "+
			" DECLARE @CT_NumPayeur AS VARCHAR(50) = ? "+
			"			,@RG_Libelle AS VARCHAR(150) = ? "+
			"			,@RG_Date AS VARCHAR(150) = ? "+
			"			,@RG_Reference AS VARCHAR(150) = ? "+
			"			,@RG_Montant AS VARCHAR(150) = ?  "+
			"			,@RG_MontantDev AS VARCHAR(150) = ?  "+
			"			,@RG_Impute AS INT = ?  "+
			"			,@RG_Compta AS INT = ?  "+
			"			,@EC_No AS INT = ?  "+
			"			,@N_Devise AS INT = ?  "+
			"			,@JO_Num AS VARCHAR(150) = ? "+
			"			,@CG_NumCount AS VARCHAR(150) = ? "+
			"			,@CG_Impaye AS INT = ?  "+
			"			,@CG_Num AS VARCHAR(150) = ? "+
			"			,@RG_TypeReg AS INT = ?  "+
			"			,@RG_Heure AS VARCHAR(150) = ? "+
			"			,@RG_Piece AS VARCHAR(150) = ?  "+
			"			,@CA_No AS INT = ?  "+
			"			,@RG_Banque AS INT = ? "+
			"			,@RG_Transfere AS INT = ? "+
			"			,@RG_Cloture AS INT = ?  "+
			"			,@RG_Ticket AS INT = ?  "+
			"			,@RG_Souche AS VARCHAR(150) = ?  "+
			"			,@CT_NumPayeurOrig AS VARCHAR(150) = ? "+
			"			,@RG_DateEchCont AS VARCHAR(150) = ?  "+
			"			,@CG_NumEcart AS VARCHAR(150) = ?  "+
			"			,@JO_NumEcart AS VARCHAR(150) = ?  "+
			"			,@RG_MontantEcart AS VARCHAR(150) = ? "+
			"			,@RG_NoBonAchat AS VARCHAR(150) = ?  "+
			"			,@CG_NumEcart AS VARCHAR(150) = ?  "+
			"			,@cbCreateur AS VARCHAR(150) = ? "+
			""+
			" IF NOT EXISTS ( SELECT 1 "+
			"		FROM F_CREGLEMENT "+
			"		WHERE RG_Libelle = @RG_Libelle "+
			"				AND RG_Date=@RG_Date "+
			"				AND RG_Montant=@RG_Montant "+
			"				AND RG_Type=@RG_Type "+
			"				AND RG_TypeReg = @RG_TypeReg "+
			"				AND CA_No=@CA_No ) "+
			" INSERT INTO [dbo].[F_CREGLEMENT] "+
			"([RG_No],[CT_NumPayeur],[RG_Date],[RG_Reference]  "+
            "     ,[RG_Libelle],[RG_Montant],[RG_MontantDev],[N_Reglement]  "+
            "     ,[RG_Impute],[RG_Compta],[EC_No]  "+
            "     ,[RG_Type],[RG_Cours],[N_Devise],[JO_Num]  "+
            "     ,[CG_NumCont],[RG_Impaye],[CG_Num],[RG_TypeReg] "+
            "     ,[RG_Heure],[RG_Piece],[CA_No]  "+
            "     ,[CO_NoCaissier],[RG_Banque],[RG_Transfere]  "+
            "     ,[RG_Cloture],[RG_Ticket],[RG_Souche],[CT_NumPayeurOrig]  "+
            "     ,[RG_DateEchCont],[CG_NumEcart],[JO_NumEcart],[RG_MontantEcart] "+
            "     ,[RG_NoBonAchat],[cbProt],[cbCreateur],[cbModification]  "+
            "     ,[cbReplication],[cbFlag]) "+
			" VALUES "+
			"		(/*RG_No*/ISNULL((SELECT MAX(RG_No)+1 FROM F_CREGLEMENT),0),/*CT_NumPayeur*/ "+
			"		(CASE WHEN @CT_NumPayeur ='' THEN '' "+
			" WHEN @CT_NumPayeur = 'NULL' THEN NULL ELSE @CT_NumPayeur END) "+
            "        ,/*RG_Date*/@RG_Date,/*RG_Reference*/@RG_Reference  "+
            "       ,/*RG_Libelle*/@RG_Libelle,/*RG_Montant*/ @RG_Montant "+
            "       ,/*RG_MontantDev*/@RG_MontantDev,/*N_Reglement*/@N_Reglement "+
			"		,/*RG_Impute*/@RG_Impute,/*RG_Compta*/@RG_Compta "+
            "       ,/*EC_No*/@EC_No,/*RG_Type*/@RG_Type,/*RG_Cours*/@RG_Cours "+
            "       ,/*N_Devise*/@N_Devise,/*JO_Num*/@JO_Num "+
            "       ,/*CG_NumCont*/(CASE WHEN @CG_NumCont ='' OR @CG_NumCont = 'NULL' THEN NULL ELSE @CG_NumCont END) "+
            "        ,/*RG_Impaye*/@RG_Impaye "+
            "       ,/*CG_Num*/(CASE WHEN @CG_Num ='' OR @CG_Num = 'NULL' THEN NULL ELSE @CG_Num END),/*RG_TypeReg*/ @RG_TypeReg, "+
			" /*RG_Heure, char(9),*/(SELECT '000' + CAST(DATEPART(HOUR, GETDATE()) as VARCHAR(2)) + CAST(DATEPART(MINUTE, GETDATE()) as VARCHAR(2)) + CAST(DATEPART(SECOND, GETDATE()) as VARCHAR(2))), "+
			" /*RG_Piece*/(CASE WHEN @RG_TypeReg=2 THEN '' ELSE "+
			"		(SELECT(ISNULL((SELECT TOP 1 CR_Numero01 AS valeur FROM P_COLREGLEMENT ORDER BY cbMarq DESC),1)) as VAL) END) "+
            "   ,/*CA_No*/(SELECT CASE WHEN @CA_No=0 THEN NULL ELSE @CA_No END) "+
            "   ,/*CO_NoCaissier*/(SELECT CASE WHEN @CO_NoCaissier =0 THEN NULL ELSE @CO_NoCaissier END) "+
            "   ,/*RG_Banque*/@RG_Banque,/*RG_Transfere*/@RG_Transfere  "+
            "   ,/*RG_Cloture*/@RG_Cloture,/*RG_Ticket*/@RG_Ticket "+
            "   ,/*RG_Souche*/@RG_Souche,/*CT_NumPayeurOrig*/(CASE WHEN @CT_NumPayeurOrig ='' THEN '' "+
			" WHEN @CT_NumPayeurOrig = 'NULL' THEN NULL ELSE @CT_NumPayeurOrig END) "+
            "    ,/*RG_DateEchCont*/@RG_DateEchCont,/*CG_NumEcart*/ "+
			" (CASE WHEN @CG_NumEcart ='' OR @CG_NumEcart = 'NULL' THEN NULL ELSE @CG_NumEcart END),/*JO_NumEcart*/ "+
			" (CASE WHEN @JO_NumEcart ='' THEN '' "+
			" WHEN @JO_NumEcart = 'NULL' THEN NULL ELSE @JO_NumEcart END),/*RG_MontantEcart*/@RG_MontantEcart "+
            "   ,/*RG_NoBonAchat*/@RG_NoBonAchat,/*cbProt*/0,/*cbCreateur*/@userName,/*cbModification*/GETDATE() "+
            "   ,/*cbReplication*/@cbReplication,/*cbFlag*/0);";

		public static final String insertZ_RGLT_BONDECAISSE
				= " INSERT INTO [dbo].[Z_RGLT_BONDECAISSE] VALUES (?,?)";
				
		public static final String insertCaNum =
				"DECLARE @RG_No AS INT = ? "+
				"DECLARE @CA_Num AS VARCHAR(50) = ? "+
				"INSERT INTO [Z_RGLT_COMPTEA](RG_No,CA_Num) values(@RG_No,@CA_Num)";
				
		public static final String insertF_ReglementVrstBancaire =
				" INSERT INTO [dbo].[Z_RGLT_VRSTBANCAIRE] VALUES(?,?)";
				
		public static final String deleteF_ReglementVrstBancaire =
				" DECLARE @RG_No AS INT = ? "+
				"	DELETE FROM F_CREGLEMENT "+
                "     WHERE RG_No=(SELECT RG_NoCache FROM [dbo].[Z_RGLT_VRSTBANCAIRE] WHERE RG_No=@RG_No); "+
                "     DELETE FROM [dbo].[Z_RGLT_VRSTBANCAIRE] WHERE RG_No=@RG_No";
				
				
		public static final String getFactureRGNo
				= 
				" DECLARE @rgNo AS INT = ?; "+
				" SELECT E.cbMarq,DE_Intitule,L.DO_Piece,L.DO_Ref,CAST(CAST(L.DO_Date AS DATE) AS VARCHAR(10)) AS DO_Date,CO.CT_Num,CT_Intitule, ROUND(SUM(L.DL_MontantTTC),0) AS ttc, \n" +
				"                ISNULL(sum(avance),0) AS avance  \n" +
				"                FROM F_CREGLEMENT C\n" +
				"                INNER JOIN (SELECT RG_No,DO_Piece,DO_Domaine,DO_Type, SUM(RC_MONTANT) avance FROM F_REGLECH R GROUP BY RG_No,DO_Piece,DO_Domaine,DO_Type) R ON C.RG_No=R.RG_No \n" +
				"                LEFT JOIN (SELECT cbMarq,DO_Piece,DO_Type,DO_Domaine,DE_No,DO_Ref,DO_Date,DO_Tiers FROM F_DOCENTETE  GROUP BY cbMarq,DO_Piece,DO_Type,DO_Domaine,DE_No,DO_Ref,DO_Date,DO_Tiers) E on R.DO_Piece=E.DO_Piece  AND R.DO_Domaine= E.DO_Domaine AND R.DO_Type=E.DO_Type\n" +
				"                LEFT JOIN (SELECT DO_Piece,DO_Type,DO_Domaine,DE_No,DO_Ref,DO_Date,CT_Num,SUM(DL_MontantTTC) DL_MontantTTC FROM F_DOCLIGNE L GROUP BY DO_Piece,DO_Type,DO_Domaine,DE_No,DO_Ref,DO_Date,CT_Num) L on R.DO_Piece=L.DO_Piece  AND R.DO_Domaine= L.DO_Domaine AND R.DO_Type=L.DO_Type\n" +
				"                LEFT JOIN F_COMPTET CO on CO.CT_Num=L.CT_Num\n" +
				"                LEFT JOIN F_DEPOT D on D.DE_No=(CASE WHEN L.DE_No=0 THEN E.DE_No ELSE L.DE_No END)\n" +
				"                WHERE C.RG_No=@rgNo\n" +
				"                GROUP BY E.cbMarq,DE_Intitule,L.DO_Piece,L.DO_Ref,L.DO_Date,CO.CT_Num,CT_Intitule\n" +
				"                UNION\n" +
				"                SELECT 0 cbMarq,'' AS DE_Intitule,'' AS DO_Piece,RG_Libelle AS DO_Ref,CAST(CAST(RG_Date AS DATE) AS VARCHAR(10)) AS DO_Date,CT_NumPayeur CT_Num,'' CT_Intitule, RG_Montant AS ttc,0 AS avance \n" +
				"                FROM [dbo].[Z_RGLT_BONDECAISSE] A\n" +
				"                INNER JOIN F_CREGLEMENT B ON A.RG_No=B.RG_No\n" +
				"                WHERE RG_No_RGLT=@rgNo";
		
		public static final String setMajAnalytique
				= "	DECLARE @dateDeb DATE = ? "+
					" ,@dateFin DATE = ? "+
					" ,@CA_Num VARCHAR(50) = ? "+
					" ,@cbCreateur VARCHAR(50) = ? "+
					" "+
					" INSERT INTO F_ECRITUREA (EC_No,N_Analytique,EA_Ligne,CA_Num,EA_Montant,EA_Quantite,cbCreateur,cbModification) "+
                    " SELECT	EC_No,N_Analytique,1,Cpa.CA_Num,RG_Montant,0,@cbCreateur,GETDATE() "+
                    " FROM	F_CREGLEMENT A "+
                    " INNER JOIN F_CAISSE CA "+ 
					"	ON CA.CA_No = A.CA_No "+
                    " INNER JOIN Z_RGLT_COMPTEA B  "+
					"	ON A.RG_No = B.RG_No "+
                    " INNER JOIN F_COMPTEA Cpa  "+
					"	ON Cpa.CA_Num = B.CA_Num "+
                    " WHERE	RG_Compta=1 "+
                    " AND     RG_Date BETWEEN @dateDeb AND @dateFin "+
                    " AND (@CA_Num='' OR (@CA_Num=B.CA_Num)) "+
                    " AND     EC_No NOT IN (SELECT EC_No FROM F_ECRITUREA)";
}