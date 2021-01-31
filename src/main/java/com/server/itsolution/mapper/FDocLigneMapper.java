package com.server.itsolution.mapper;

public class FDocLigneMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select   DO_Domaine,DO_Type,CT_Num,DO_Piece,DL_PieceBC,DL_PieceBL,DO_Date,DL_DateBC\n" +
            ",DL_DateBL,DL_Ligne,DO_Ref,DL_TNomencl,DL_TRemPied,DL_TRemExep,AR_Ref,DL_Design\n" +
            ",DL_Qte,DL_QteBC,DL_QteBL,DL_PoidsNet,DL_PoidsBrut,DL_Remise01REM_Valeur,DL_Remise01REM_Type,DL_Remise02REM_Valeur\n" +
            ",DL_Remise02REM_Type,DL_Remise03REM_Valeur,DL_Remise03REM_Type,DL_PrixUnitaire\n" +
            ",DL_PUBC,DL_Taxe1,DL_TypeTaux1,DL_TypeTaxe1,DL_Taxe2,DL_TypeTaux2,DL_TypeTaxe2,CO_No\n" +
            ",AG_No1,AG_No2,DL_PrixRU,DL_CMUP,DL_MvtStock,DT_No,AF_RefFourniss,EU_Enumere\n" +
            ",EU_Qte,DL_TTC,DE_No,DL_NoRef,DL_TypePL,DL_PUDevise,DL_PUTTC,DL_No\n" +
            ",DO_DateLivr,CA_Num,cbCA_Num,DL_Taxe3,DL_TypeTaux3,DL_TypeTaxe3,DL_Frais,DL_Valorise\n" +
            ",AR_RefCompose,DL_NonLivre,AC_RefClient,DL_MontantHT,DL_MontantTTC,DL_FactPoids,DL_Escompte,DL_PiecePL\n" +
            ",DL_DatePL,DL_QtePL,DL_NoColis,DL_NoLink,RP_Code,DL_QteRessource,DL_DateAvancement,cbMarq\n" +
            ",cbCreateur,cbModification,USERGESCOM,NOMCLIENT,DATEMODIF,ORDONATEUR_REMISE,MACHINEPC,GROUPEUSER\n" +
            " From F_DOCLIGNE";

    public static final String getFDocLigne = BASE_SQL + " WHERE cbMarq = ?";

    public static final String getLigneFacture =
            "DECLARE @do_domaine as INT = ?\n" +
                    "DECLARE @do_type as INT = ?\n" +
                    "DECLARE @do_piece as VARCHAR(10) = ?\n" +
                    "\n" +
                    "SELECT DL_PUDevise,CA_Num,DL_TTC, DL_PUTTC,DL_MvtStock,CT_Num,cbMarq,DL_TypeTaux1,DL_TypeTaux2,DL_TypeTaux3,cbCreateur,DL_NoColis\n" +
                    "        ,CASE WHEN DL_TypeTaux1=0 THEN DL_MontantHT*(DL_Taxe1/100) ELSE CASE WHEN DL_TypeTaux1=1 THEN DL_Taxe1*DL_Qte ELSE DL_Taxe1 END END MT_Taxe1\n" +
                    "        ,CASE WHEN DL_TypeTaux2=0 THEN DL_MontantHT*(DL_Taxe2/100) ELSE CASE WHEN DL_TypeTaux2=1 THEN DL_Taxe2*DL_Qte ELSE DL_Taxe2 END END MT_Taxe2\n" +
                    "        ,CASE WHEN DL_TypeTaux3=0 THEN DL_MontantHT*(DL_Taxe3/100) ELSE CASE WHEN DL_TypeTaux3=1 THEN DL_Taxe3*DL_Qte ELSE DL_Taxe3 END END MT_Taxe3\n" +
                    "\t    ,DL_MontantHT,DO_Piece,\n" +
                    "        AR_Ref,DE_No,DL_CMUP AS AR_PrixAch,DL_Design,DL_Qte,DL_PrixUnitaire,DL_CMUP,DL_Taxe1,DL_Taxe2,DL_Taxe3,DL_MontantTTC,DL_Ligne,DL_Remise01REM_Valeur,DL_Remise01REM_Type,\n" +
                    "        CASE WHEN DL_Remise01REM_Type=0 THEN ''  ELSE CASE WHEN DL_Remise01REM_Type=1 THEN cast(cast(DL_Remise01REM_Valeur as numeric(9,2)) as varchar(10))+'%' ELSE cast(cast(DL_Remise01REM_Valeur as numeric(9,2)) as varchar(10))+'U' END END DL_Remise,\n" +
                    "        DL_PrixUnitaire -(CASE WHEN DL_Remise01REM_Type= 0 THEN DL_PrixUnitaire\n" +
                    "\tELSE CASE WHEN DL_Remise01REM_Type=1 THEN  DL_PrixUnitaire * DL_Remise01REM_Valeur / 100\n" +
                    "\t\tELSE CASE WHEN DL_Remise01REM_Type=2 THEN DL_Remise01REM_Valeur ELSE 0 END END END) DL_PrixUnitaire_Rem,\n" +
                    "        DL_PUTTC -(CASE WHEN DL_Remise01REM_Type= 0 THEN DL_PUTTC\n" +
                    "\tELSE CASE WHEN DL_Remise01REM_Type=1 THEN  DL_PrixUnitaire * DL_Remise01REM_Valeur / 100\n" +
                    "\t\tELSE CASE WHEN DL_Remise01REM_Type=2 THEN DL_Remise01REM_Valeur ELSE 0 END END END) DL_PUTTC_Rem,\n" +
                    "\t\tDL_PrixUnitaire -(CASE WHEN DL_Remise01REM_Type= 0 THEN 0\n" +
                    "\tELSE CASE WHEN DL_Remise01REM_Type=1 THEN  DL_PrixUnitaire * DL_Remise01REM_Valeur / 100\n" +
                    "\t\tELSE CASE WHEN DL_Remise01REM_Type=2 THEN DL_Remise01REM_Valeur ELSE 0 END END END) DL_PrixUnitaire_Rem0,\n" +
                    "        DL_PUTTC -(CASE WHEN DL_Remise01REM_Type= 0 THEN 0\n" +
                    "\tELSE CASE WHEN DL_Remise01REM_Type=1 THEN  DL_PrixUnitaire * DL_Remise01REM_Valeur / 100\n" +
                    "\t\tELSE CASE WHEN DL_Remise01REM_Type=2 THEN DL_Remise01REM_Valeur ELSE 0 END END END) DL_PUTTC_Rem0\n" +
                    "        FROM F_DOCLIGNE  \n" +
                    "        WHERE DO_Piece =@do_piece AND DO_Domaine=@do_domaine AND DO_Type = @do_type\n" +
                    "        ORDER BY cbMarq";

    public static final String getLigneFactureDernierElement = "SELECT cbMarq,DL_PUTTC,DL_NoColis,DO_Piece,AR_Ref,DL_Design,DL_Qte,DL_PrixUnitaire,DL_CMUP,DL_Taxe1,DL_Taxe2,\n" +
            "        DL_Taxe3,DL_MontantTTC,DL_MontantHT,DL_Ligne,\n" +
            "                CASE WHEN DL_Remise01REM_Type=0 THEN ''\n" +
            "        WHEN DL_Remise01REM_Type=1 THEN cast(cast(DL_Remise01REM_Valeur as numeric(9,2)) as varchar(10))+'%'\n" +
            "        ELSE cast(cast(DL_Remise01REM_Valeur as numeric(9,2)) as varchar(10))+'U' END DL_Remise\n" +
            "        FROM F_DOCLIGNE  WHERE cbMarq =?";

    public static final String  insertDocLigne =
            "              DECLARE @DO_Domaine VARCHAR(20) = ?\n" +
            "              DECLARE @DO_Type int = ?\n" +
            "              DECLARE @CT_Num VARCHAR(50) = ?\n" +
            "              DECLARE @DO_Piece VARCHAR(50) = ?\n" +
            "              DECLARE @DL_PieceBC  VARCHAR(50) = ?\n" +
            "              DECLARE @DL_PieceBL VARCHAR(50) = ?\n" +
            "              DECLARE @DO_Date VARCHAR(50) = ?\n" +
            "              DECLARE @DL_DateBC VARCHAR(50) = ?\n" +
            "              DECLARE @DL_DateBL  VARCHAR(50) = ?\n" +
            "              DECLARE @DO_Ref VARCHAR(50) = ?\n" +
            "              DECLARE @DL_TNomencl VARCHAR(50) = ?\n" +
            "              DECLARE @DL_TRemPied VARCHAR(50) = ?\n" +
            "              DECLARE @DL_TRemExep VARCHAR(50) = ?\n" +
            "\t\t\t  DECLARE @AR_Ref VARCHAR(50) = ?\n" +
            "              DECLARE @DL_Design VARCHAR(50) = ?\n" +
            "              DECLARE @DL_Qte FLOAT = ?\n" +
            "              DECLARE @DL_QteBC FLOAT = ?\n" +
            "              DECLARE @DL_QteBL VARCHAR(50) = ?\n" +
            "              DECLARE @DL_PoidsNet VARCHAR(50) = ?\n" +
            "              DECLARE @DL_PoidsBrut VARCHAR(50) = ?\n" +
            "              DECLARE @DL_Remise01REM_Valeur FLOAT = ?\n" +
            "              DECLARE @DL_Remise01REM_Type float = ?\n" +
            "              DECLARE @DL_Remise02REM_Valeur FLOAT = ?\n" +
            "              DECLARE @DL_Remise02REM_Type float = ?\n" +
            "              DECLARE @DL_Remise03REM_Valeur FLOAT = ?\n" +
            "              DECLARE @DL_Remise03REM_Type float = ?\n" +
            "              DECLARE @DL_PrixUnitaire FLOAT = ?\n" +
            "              DECLARE @DL_PUBC FLOAT = ?\n" +
            "              DECLARE @DL_Taxe1 FLOAT = ?\n" +
            "              DECLARE @DL_TypeTaux1 FLOAT = ?\n" +
            "              DECLARE @DL_TypeTaxe1 FLOAT = ?\n" +
            "              DECLARE @DL_Taxe2 FLOAT = ?\n" +
            "              DECLARE @DL_TypeTaux2 FLOAT = ?\n" +
            "              DECLARE @DL_TypeTaxe2 FLOAT = ?\n" +
            "              DECLARE @DL_Taxe3 FLOAT = ?\n" +
            "              DECLARE @DL_TypeTaux3 FLOAT = ?\n" +
            "              DECLARE @DL_TypeTaxe3 FLOAT = ?\n" +
            "              DECLARE @CO_No INT = ?\n" +
            "\t\t\t  DECLARE @AG_No1 INT = ?\n" +
            "\t\t\t  DECLARE @AG_No2 INT = ?\n" +
            "\t\t\t  DECLARE @DL_PrixRU FLOAT = ?\n" +
            "\t\t\t  DECLARE @DL_CMUP FLOAT = ?\n" +
            "\t\t\t  DECLARE @DL_MvtStock INT = ?\n" +
            "\t\t\t  DECLARE @DT_No INT = ?\n" +
            "\t\t\t  DECLARE @AF_RefFourniss VARCHAR(50) = ?\n" +
            "\t\t\t  DECLARE @EU_Enumere VARCHAR(50) = ?\n" +
            "\t\t\t  DECLARE @EU_Qte FLOAT = ?\n" +
            "\t\t\t  DECLARE @DL_TTC FLOAT = ?\n" +
            "\t\t\t  DECLARE @DE_No INT = ?\n" +
            "\t\t\t  DECLARE @DL_NoRef INT = ?\n" +
            "\t\t\t  DECLARE @DL_TypePL INT = ?\n" +
            "\t\t\t  DECLARE @DL_PUDevise FLOAT = ?\n" +
            "\t\t\t  DECLARE @DL_PUTTC FLOAT = ?\n" +
            "\t\t\t  DECLARE @DO_DateLivr VARCHAR(50) = ?\n" +
            "\t\t\t  DECLARE @CA_Num VARCHAR(50) = ?\n" +
            "\t\t\t  DECLARE @DL_Frais FLOAT = ?\n" +
            "\t\t\t  DECLARE @DL_Valorise FLOAT = ?\n" +
            "\t\t\t  DECLARE @DL_NonLivre FLOAT = ?\n" +
            "\t\t\t  DECLARE @AC_RefClient VARCHAR(50) = ?\n" +

            "\t\t\t  DECLARE @DL_MontantHT FLOAT = ?\n" +
            "\t\t\t  DECLARE @DL_MontantTTC FLOAT = ?\n" +
            "\t\t\t  DECLARE @DL_FactPoids FLOAT = ?\n" +
            "\t\t\t  DECLARE @DL_Escompte FLOAT = ?\n" +
            "\t\t\t  DECLARE @DL_PiecePL VARCHAR(50) = ?\n" +
            "\t\t\t  DECLARE @DL_DatePL VARCHAR(50) = ?\n" +
            "\t\t\t  DECLARE @DL_QtePL FLOAT = ?\n" +
            "\t\t\t  DECLARE @DL_NoColis VARCHAR(50) = ?\n" +
            "\t\t\t  DECLARE @DL_NoLink INT = ?\n" +
            "\t\t\t  DECLARE @DL_QteRessource FLOAT = ?\n" +
            "\t\t\t  DECLARE @DL_DateAvancement VARCHAR(50) = ?\n" +
            "\t\t\t  DECLARE @cbCreateur VARCHAR(50) = ?\n" +
            "\t\t\t  DECLARE @MACHINEPC VARCHAR(50) = ?\n" +
            "		 DECLARE @generated_keys table([cbMarq] int);" +
            "\t\t\t  INSERT INTO [dbo].[F_DOCLIGNE]\n" +
            "                ([DO_Domaine], [DO_Type], [CT_Num], [DO_Piece], [DL_PieceBC], [DL_PieceBL], [DO_Date], [DL_DateBC]\n" +
            "                , [DL_DateBL], [DL_Ligne], [DO_Ref], [DL_TNomencl], [DL_TRemPied], [DL_TRemExep], [AR_Ref], [DL_Design]\n" +
            "                , [DL_Qte], [DL_QteBC], [DL_QteBL], [DL_PoidsNet], [DL_PoidsBrut], [DL_Remise01REM_Valeur], [DL_Remise01REM_Type], [DL_Remise02REM_Valeur]\n" +
            "                , [DL_Remise02REM_Type], [DL_Remise03REM_Valeur], [DL_Remise03REM_Type], [DL_PrixUnitaire]\n" +
            "                , [DL_PUBC], [DL_Taxe1], [DL_TypeTaux1], [DL_TypeTaxe1], [DL_Taxe2], [DL_TypeTaux2], [DL_TypeTaxe2], [CO_No]\n" +
            "                , [AG_No1], [AG_No2], [DL_PrixRU], [DL_CMUP], [DL_MvtStock], [DT_No]\n" +
            "                , [AF_RefFourniss], [EU_Enumere], [EU_Qte], [DL_TTC], [DE_No], [DL_NoRef], [DL_TypePL]\n" +
            "                , [DL_PUDevise], [DL_PUTTC], [DL_No], [DO_DateLivr], [CA_Num], [DL_Taxe3], [DL_TypeTaux3], [DL_TypeTaxe3]\n" +
            "                , [DL_Frais], [DL_Valorise], [AR_RefCompose], [DL_NonLivre], [AC_RefClient], [DL_MontantHT], [DL_MontantTTC], [DL_FactPoids]\n" +
            "                , [DL_Escompte], [DL_PiecePL], [DL_DatePL], [DL_QtePL], [DL_NoColis], [DL_NoLink], [RP_Code]\n" +
            "                , [DL_QteRessource], [DL_DateAvancement], [cbProt], [cbCreateur], [cbModification], [cbReplication], [cbFlag],[USERGESCOM],[DATEMODIF],[MACHINEPC])\n" +
            "             OUTPUT INSERTED.cbMarq INTO @generated_keys VALUES\n" +
            "                (@DO_Domaine,@DO_Type ,@CT_Num,@DO_Piece\n" +
            "                ,@DL_PieceBC,@DL_PieceBL,@DO_Date,@DL_DateBC\n" +
            "                ,@DL_DateBL ,/*DL_Ligne*/ (SELECT (1+COUNT(*))*10000 FROM F_DOCLIGNE WHERE DO_PIECE=@DO_Piece AND DO_Domaine=@DO_Domaine AND DO_Type=@DO_Type),@DO_Ref\n" +
            "\t\t\t\t,@DL_TNomencl,@DL_TRemPied,@DL_TRemExep,@AR_Ref,@DL_Design\n" +
            "                ,@DL_Qte ,@DL_QteBC, @DL_QteBL ,@DL_PoidsNet,@DL_PoidsBrut,@DL_Remise01REM_Valeur\n" +
            "                ,@DL_Remise01REM_Type,@DL_Remise02REM_Valeur,@DL_Remise02REM_Type,@DL_Remise03REM_Valeur\n" +
            "                ,@DL_Remise03REM_Type,@DL_PrixUnitaire,@DL_PUBC,@DL_Taxe1,@DL_TypeTaux1,@DL_TypeTaxe1,@DL_Taxe2,@DL_TypeTaux2\n" +
            "                ,@DL_TypeTaxe2,@CO_No,@AG_No1,@AG_No2,@DL_PrixRU,@DL_CMUP,@DL_MvtStock,@DT_No,@AF_RefFourniss\n" +
            "                ,@EU_Enumere,@EU_Qte,@DL_TTC,@DE_No,@DL_NoRef,@DL_TypePL,@DL_PUDevise,@DL_PUTTC,/*DL_No*/ ISNULL((SELECT MAX(DL_No)+1 FROM F_DOCLIGNE),0),@DO_DateLivr\n" +
            "\t\t\t\t,@CA_Num,@DL_Taxe3,@DL_TypeTaux3,@DL_TypeTaxe3,@DL_Frais,@DL_Valorise,NULL,@DL_NonLivre,@AC_RefClient,@DL_MontantHT,@DL_MontantTTC\n" +
            "                ,@DL_FactPoids,@DL_Escompte,@DL_PiecePL,@DL_DatePL,@DL_QtePL,@DL_NoColis,@DL_NoLink,/*RP_Code*/NULL,@DL_QteRessource,@DL_DateAvancement,/*cbProt*/0\n" +
            "                ,@cbCreateur,/*cbModification*/GETDATE(),/*cbReplication*/0,/*cbFlag*/0,/*USERGESCOM*/(SELECT PROT_User FROM F_PROTECTIONCIAL WHERE PROT_No=@cbCreateur)\n" +
            "\t\t\t    ,/*DATEMODIF*/GETDATE(),/*MACHINEPC*/@MACHINEPC);" +
                    " SELECT cbMarq FROM @generated_keys;\n";

    public static final String getPrixClientAch =
            "SELECT *,CASE WHEN AC_PrixTTC = 1 THEN Prix /(1+taxe1/100)/(1+taxe2/100)/(1+taxe3/100) ELSE Prix END PrixVente \n" +
                    "                 FROM( \n" +
                    "                     SELECT a.FA_CodeFamille, \n" +
                    "\t\t\t\t\t\t\ta.AR_Ref, \n" +
                    "\t\t\t\t\t\t\tCASE WHEN AF_PrixAch IS NULL THEN AR_PrixAch ELSE AF_PrixAch END AR_PrixAch, \n" +
                    "\t\t\t\t\t\t\tAR_Design, \n" +
                    "\t\t\t\t\t\t\tAR_PrixVen,\n" +
                    "\t\t\t\t\t\t\tPrix_Min,\n" +
                    "\t\t\t\t\t\t\tPrix_Max,\n" +
                    "\t\t\t\t\t\t\tCASE WHEN AC_Categorie= 1 THEN AR_PrixVen \n" +
                    "\t\t\t\t\t\t\t\t\tWHEN AC_PrixVen<>0 THEN AC_PrixVen ELSE AR_PrixVen END AS Prix, \n" +
                    "\t\t\t\t\t\t\tAC_PrixTTC, \n" +
                    "\t\t\t\t\t\t\tAR_PrixTTC,\n" +
                    "                     (CASE WHEN ISNULL(TU.TA_Sens,0)=0 THEN 1 ELSE -1 END)*ISNULL(TU.TA_Taux,0) as taxe1, \n" +
                    "                   (CASE WHEN ISNULL(TD.TA_Sens,0)=0 THEN 1 ELSE -1 END)*ISNULL(TD.TA_Taux,0) as taxe2,\n" +
                    "                   (CASE WHEN ISNULL(TT.TA_Sens,0)=0 THEN 1 ELSE -1 END)*ISNULL(TT.TA_Taux,0) as taxe3, FCP_Champ \n" +
                    "                FROM F_ARTICLE A \n" +
                    "                LEFT JOIN (SELECT AR_Ref,CT_Num,AF_PrixAch FROM F_ARTFOURNISS WHERE CT_Num = ?) AF ON A.AR_Ref = AF.AR_Ref\n" +
                    "                  LEFT JOIN (select * from F_ARTCLIENT where AC_Categorie=(SELECT ISNULL((SELECT AC_Categorie FROM F_ARTCLIENT WHERE AR_REF = ? AND AC_Categorie = ?),1)))AR on AR.AR_Ref = A.AR_Ref\n" +
                    "                 LEFT JOIN (SELECT * FROM F_FAMCOMPTA WHERE FCP_Type=1 AND FCP_Champ= ?)FA ON FA.FA_CodeFamille = A.FA_CodeFamille \n" +
                    "                 LEFT JOIN F_TAXE TU ON TU.TA_Code = FA.FCP_ComptaCPT_Taxe1 \n" +
                    "                 LEFT JOIN F_TAXE TD ON TD.TA_Code = FA.FCP_ComptaCPT_Taxe2 \n" +
                    "                 LEFT JOIN F_TAXE TT ON TT.TA_Code = FA.FCP_ComptaCPT_Taxe3 \n" +
                    "                 WHERE  A.AR_REF = ?)A";

    public static final String getPrixClientHT =
    "BEGIN \n" +
            "                    SET NOCOUNT ON;\n" +
            "            DECLARE @prix as float,@rem as float,@ar_ref as varchar(30)\n" +
            "                    ,@fcp_champ as int,@ac_categorie as int,@qte as float\n" +
            "                    ,@fournisseur as int,@flagpxMinMax as int\n" +
            "            set @prix = ?;\n" +
            "            set @rem = ?;\n" +
            "            set @ar_ref = ?;\n" +
            "            set @fcp_champ = ?;\n" +
            "            set @ac_categorie=?;\n" +
            "            set @qte = ?;\n" +
            "            set @fournisseur = ?;\n" +
            "            SELECT\t@flagpxMinMax=P_ReportPrixRev\n" +
            "            FROM\tP_PARAMETRECIAL;\n" +
            "            \n" +
            "            WITH _FARTCOMPTA_ AS (\n" +
            "                SELECT\tcbAR_Ref\n" +
            "                        ,ACP_ComptaCPT_Taxe1\n" +
            "                        ,ACP_ComptaCPT_Taxe2\n" +
            "                        ,ACP_ComptaCPT_Taxe3\n" +
            "                FROM\tF_ARTCOMPTA \n" +
            "                WHERE\tACP_Type=@fournisseur \n" +
            "                AND\t\tACP_Champ=@fcp_champ \n" +
            "            )\n" +
            "            ,_FFAMCOMPTA_ AS (\n" +
            "                SELECT\tcbFA_CodeFamille\n" +
            "                        ,FCP_ComptaCPT_Taxe1\n" +
            "                        ,FCP_ComptaCPT_Taxe2\n" +
            "                        ,FCP_ComptaCPT_Taxe3\n" +
            "                        ,FCP_Champ\n" +
            "                FROM\tF_FAMCOMPTA \n" +
            "                WHERE\tFCP_Type=@fournisseur \n" +
            "                AND\t\tFCP_Champ=@fcp_champ \n" +
            "            )\n" +
            "            ,_FARTCLIENT_ AS (\n" +
            "                SELECT\tcbAR_Ref\n" +
            "                        ,AC_Coef\n" +
            "                        ,AC_PrixVen\n" +
            "                        ,AC_PrixTTC \n" +
            "                FROM\tF_ARTCLIENT \n" +
            "                WHERE\tAC_Categorie=(SELECT ISNULL((\tSELECT\tAC_Categorie \n" +
            "                                                        FROM\tF_ARTCLIENT \n" +
            "                                                        WHERE\tAR_REF = @ar_ref \n" +
            "                                                        AND\t\tAC_Categorie=@ac_categorie),1))\n" +
            "            )\n" +
            "            ,_QUERY_ AS (\n" +
            "            \n" +
            "            SELECT\t\n" +
            "                ISNULL(TU.TA_Intitule,'') as IntituleT1,ISNULL(TD.TA_Intitule,'') as IntituleT2,ISNULL(TT.TA_Intitule,'') as IntituleT3 \n" +
            "                ,ISNULL(a.FA_CodeFamille,'') FA_CodeFamille, ISNULL(a.AR_Ref,'')AR_Ref, ISNULL(AR_PrixAch,0)AR_PrixAch, ISNULL(AR_Design,'')AR_Design \n" +
            "                ,ISNULL(AR_PrixVen,0)AR_PrixVen,AC_PrixVen\n" +
            "                ,CASE WHEN @flagpxMinMax = 0 THEN ISNULL(Prix_Min,0) ELSE ISNULL(AC_Coef,0) END Prix_Min\n" +
            "                ,CASE WHEN @flagpxMinMax = 0 THEN ISNULL(Prix_Max,0) ELSE ISNULL(AC_PrixVen,0) END Prix_Max\n" +
            "                ,ISNULL(TU.TA_TTaux,0) AS TU_TA_TTaux\n" +
            "                ,ISNULL(TD.TA_TTaux,0) AS TD_TA_TTaux,ISNULL(TT.TA_TTaux,0) AS TT_TA_TTaux\n" +
            "                ,ISNULL(TU.TA_Type,0) AS TU_TA_Type\n" +
            "                ,ISNULL(TD.TA_Type,0) AS TD_TA_Type,ISNULL(TT.TA_Type,0) AS TT_TA_Type\n" +
            "                ,ISNULL(CASE WHEN AC_PrixVen<>0 THEN AC_PrixVen ELSE AR_PrixVen END,0) AS Prix\n" +
            "                ,ISNULL(CASE WHEN AC_PrixTTC IS NULL THEN AR_PrixTTC ELSE AC_PrixTTC END,0) AC_PrixTTC, ISNULL(AR_PrixTTC,0)AR_PrixTTC\n" +
            "                ,ISNULL((CASE WHEN @fournisseur=1 THEN CASE WHEN ISNULL(TU.TA_Sens,0)=0 THEN 1 ELSE -1 END ELSE CASE WHEN @fournisseur=0 THEN CASE WHEN ISNULL(TU.TA_Sens,0)=0 THEN -1 ELSE 1 END END END)*(CASE WHEN TU.TA_TTaux=0 THEN ISNULL(TU.TA_Taux,0) ELSE CASE WHEN TU.TA_TTaux IN (1,2) THEN ISNULL(TU.TA_Taux,0) ELSE 0 END END),0) as taxe1\n" +
            "                ,ISNULL((CASE WHEN @fournisseur=1 THEN CASE WHEN ISNULL(TD.TA_Sens,0)=0 THEN 1 ELSE -1 END ELSE CASE WHEN @fournisseur=0 THEN CASE WHEN ISNULL(TD.TA_Sens,0)=0 THEN -1 ELSE 1 END END END)*(CASE WHEN TD.TA_TTaux=0 THEN ISNULL(TD.TA_Taux,0) ELSE CASE WHEN TD.TA_TTaux IN (1,2) THEN ISNULL(TD.TA_Taux,0) ELSE 0 END END),0) as taxe2\n" +
            "                ,ISNULL((CASE WHEN @fournisseur=1 THEN CASE WHEN ISNULL(TT.TA_Sens,0)=0 THEN 1 ELSE -1 END ELSE CASE WHEN @fournisseur=0 THEN CASE WHEN ISNULL(TT.TA_Sens,0)=0 THEN -1 ELSE 1 END END END)*(CASE WHEN TT.TA_TTaux=0 THEN ISNULL(TT.TA_Taux,0) ELSE CASE WHEN TT.TA_TTaux IN (1,2) THEN ISNULL(TT.TA_Taux,0) ELSE 0 END END),0) as taxe3\n" +
            "                ,ISNULL(FCP_Champ,0)FCP_Champ  \n" +
            "            FROM F_ARTICLE A \n" +
            "            LEFT JOIN _FARTCLIENT_ AR \n" +
            "                ON AR.cbAR_Ref = A.cbAR_Ref\n" +
            "            LEFT JOIN _FFAMCOMPTA_ FF \n" +
            "                ON FF.cbFA_CodeFamille = A.cbFA_CodeFamille \n" +
            "            LEFT JOIN _FARTCOMPTA_ FA \n" +
            "                ON FA.cbAR_Ref = A.cbAR_Ref\n" +
            "            LEFT JOIN F_TAXE TU \n" +
            "                ON\tTU.cbTA_Code = (CASE WHEN ISNULL(FCP_ComptaCPT_Taxe1,'0') <> ISNULL(ACP_ComptaCPT_Taxe1,'0') \n" +
            "                AND ACP_ComptaCPT_Taxe1 IS NOT NULL THEN ACP_ComptaCPT_Taxe1 ELSE FCP_ComptaCPT_Taxe1 END)\n" +
            "            LEFT JOIN F_TAXE TD \n" +
            "                ON\tTD.cbTA_Code = (CASE WHEN ISNULL(FCP_ComptaCPT_Taxe2,'0') <> ISNULL(ACP_ComptaCPT_Taxe2,'0') \n" +
            "                AND ACP_ComptaCPT_Taxe2 IS NOT NULL THEN ACP_ComptaCPT_Taxe2 ELSE FCP_ComptaCPT_Taxe2 END) \n" +
            "            LEFT JOIN F_TAXE TT \n" +
            "                ON\tTT.cbTA_Code = (CASE WHEN ISNULL(FCP_ComptaCPT_Taxe3,'0') <> ISNULL(ACP_ComptaCPT_Taxe3,'0') \n" +
            "                AND ACP_ComptaCPT_Taxe3 IS NOT NULL THEN ACP_ComptaCPT_Taxe3 ELSE FCP_ComptaCPT_Taxe3 END) \n" +
            "            WHERE  A.AR_REF = @ar_ref\n" +
            "            )\n" +
            "            ,_CALCUL_PRIX_ AS (\n" +
            "                \n" +
            "            SELECT *,\n" +
            "                    DL_MontantHT =ROUND(CASE WHEN @fournisseur = 0 AND (AC_PrixTTC = 1  OR AR_PrixTTC = 1) THEN ROUND(((@prix-@rem)* @qte - (CASE WHEN TU_TA_TTaux=1 THEN taxe1 ELSE 0 END+CASE WHEN TD_TA_TTaux=1 THEN taxe2 ELSE 0 END+CASE WHEN TT_TA_TTaux=1 THEN taxe3 ELSE 0 END)\n" +
            "                                    -(CASE WHEN TU_TA_TTaux=2 THEN taxe1*@qte ELSE 0 END+CASE WHEN TD_TA_TTaux=2 THEN taxe2*@qte ELSE 0 END+CASE WHEN TT_TA_TTaux=2 THEN taxe3*@qte ELSE 0 END)) /\n" +
            "                                    (1 +CASE WHEN TU_TA_TTaux=0 THEN taxe1/100 ELSE 0 END+CASE WHEN TD_TA_TTaux=0 THEN taxe2/100 ELSE 0 END+CASE WHEN TT_TA_TTaux=0 THEN taxe3/100 ELSE 0 END)\n" +
            "                                    ,2) ELSE (@prix-@rem)* @qte END,2) ,\n" +
            "                    DL_PrixUnitaire = ROUND(CASE WHEN @fournisseur = 0 AND (AC_PrixTTC = 1  OR AR_PrixTTC = 1) THEN CASE WHEN @qte=0 THEN 0 ELSE ROUND(((@prix)*@qte - (CASE WHEN TU_TA_TTaux=1 THEN taxe1 ELSE 0 END+CASE WHEN TD_TA_TTaux=1 THEN taxe2 ELSE 0 END+CASE WHEN TT_TA_TTaux=1 THEN taxe3 ELSE 0 END)\n" +
            "                    -(CASE WHEN TU_TA_TTaux=2 THEN taxe1*@qte ELSE 0 END+CASE WHEN TD_TA_TTaux=2 THEN taxe2*@qte ELSE 0 END+CASE WHEN TT_TA_TTaux=2 THEN taxe3*@qte ELSE 0 END)) /\n" +
            "                    (1 +CASE WHEN TU_TA_TTaux=0 THEN taxe1/100 ELSE 0 END+CASE WHEN TD_TA_TTaux=0 THEN taxe2/100 ELSE 0 END+CASE WHEN TT_TA_TTaux=0 THEN taxe3/100 ELSE 0 END)\n" +
            "                    ,2)/@qte END ELSE @prix END,2)  ,\n" +
            "                    DL_MontantTTC = ROUND((CASE WHEN @fournisseur = 0 AND (AC_PrixTTC = 1  OR AR_PrixTTC = 1) THEN (@prix-@rem)* @qte ELSE ((@prix-@rem) +\n" +
            "                                    (CASE WHEN TU_TA_TTaux=0 THEN ((@prix-@rem)*taxe1/100) WHEN TU_TA_TTaux=2 THEN taxe1 ELSE 0 END)+\n" +
            "                                    (CASE WHEN TD_TA_TTaux=0 THEN ((@prix-@rem)*taxe2/100) WHEN TD_TA_TTaux=2 THEN taxe2 ELSE 0 END)+\n" +
            "                                    (CASE WHEN TT_TA_TTaux=0 THEN ((@prix-@rem)*taxe3/100) WHEN TT_TA_TTaux=2 THEN taxe3 ELSE 0 END))* @qte +\n" +
            "                                    CASE WHEN TU_TA_TTaux=1 THEN taxe1 ELSE 0 END+CASE WHEN TD_TA_TTaux=1 THEN taxe2 ELSE 0 END+CASE WHEN TT_TA_TTaux=1 THEN taxe3 ELSE 0 END END),2)  ,\n" +
            "                    DL_PUTTC =  ROUND((CASE WHEN @fournisseur = 0 AND (AC_PrixTTC = 1  OR AR_PrixTTC = 1)  THEN @prix ELSE (@prix +\n" +
            "                                (CASE WHEN TU_TA_TTaux=0 THEN (@prix*taxe1/100) WHEN TU_TA_TTaux=2 THEN taxe1 ELSE 0 END)+\n" +
            "                                (CASE WHEN TD_TA_TTaux=0 THEN (@prix*taxe2/100) WHEN TD_TA_TTaux=2 THEN taxe2 ELSE 0 END)+\n" +
            "                                (CASE WHEN TT_TA_TTaux=0 THEN (@prix*taxe3/100 ) WHEN TT_TA_TTaux=2 THEN taxe3 ELSE 0 END))+\n" +
            "                                CASE WHEN TU_TA_TTaux=1 THEN taxe1 ELSE 0 END+CASE WHEN TD_TA_TTaux=1 THEN taxe2 ELSE 0 END+CASE WHEN TT_TA_TTaux=1 THEN taxe3 ELSE 0 END END),2)    \n" +
            "            FROM _QUERY_ \n" +
            "            )\n" +
            "            ,_CALCUL_TAXE_ AS (\n" +
            "            \n" +
            "            SELECT *\n" +
            "                , CASE WHEN TU_TA_TTaux=0 THEN DL_MontantHT*(taxe1/100) \n" +
            "                    WHEN TU_TA_TTaux=1 THEN taxe1*@qte \n" +
            "                    ELSE taxe1 END MTT_Taxe1\n" +
            "                , CASE WHEN TD_TA_TTaux=0 THEN DL_MontantHT*(taxe2/100) \n" +
            "                    WHEN TD_TA_TTaux=1 THEN taxe2 \n" +
            "                    ELSE taxe2*@qte END MTT_Taxe2\n" +
            "                , CASE WHEN TT_TA_TTaux=0 THEN DL_MontantHT*(taxe3/100) \n" +
            "                    WHEN TT_TA_TTaux=1 THEN taxe3 \n" +
            "                    ELSE taxe3*@qte END MTT_Taxe3\n" +
            "            FROM _CALCUL_PRIX_\n" +
            "            )\n" +
            "            \n" +
            "            SELECT *,\n" +
            "            DL_PUTTC-@rem DL_PUNetTTC\n" +
            "            FROM _CALCUL_TAXE_\n" +
            "\n" +
            "END";

    public static final String getCbMarqEntete =
            "SELECT cbMarq\n" +
                    "                    FROM F_DOCENTETE \n" +
                    "                    WHERE  DO_Type = ? AND DO_Domaine = ? AND DO_Piece = ?";

    public static final String deleteLigne =
        " DECLARE @cbMarq AS INT = ?;"    +
        " DELETE FROM F_DOCLIGNE WHERE cbMarq = @cbMarq;"+
        " DELETE FROM Z_LIGNE_CONFIRMATION WHERE cbMarqLigneFirst = @cbMarq;";

    public static final String updateLigne =
            "\n" +
                    "\t\t\t\t\t\t\t\tUPDATE F_DOCLIGNE SET DL_Qte= ? ,DL_QteBC = ?,DL_QteBL = ? ,EU_Qte = ?,DL_Remise01REM_Valeur = ?,DL_PrixUnitaire = ?\n" +
                    "\t\t\t\t\t\t\t\t,DL_Taxe1 = ?,DL_Taxe2 = ?,DL_Taxe3 = ?,DL_PrixRU = ?,DL_PUTTC = ?,DL_MontantHT = ?,DL_MontantTTC = ?,DL_Remise01REM_Type = ?\n" +
                    "\t\t\t\t\t\t\t\t,DL_QtePL = ?,DL_TTC = ?,DL_TypeTaux1 = ?,DL_TypeTaux2 = ?,DL_TypeTaux3 = ?,DL_TypeTaxe1 = ?,DL_TypeTaxe2 = ?,DL_TypeTaxe3 = ?\n" +
                    "\t\t\t\t\t\t\t\t,DL_CMUP = ?,MACHINEPC = ?,CbCreateur = ? WHERE cbMarq = ?";
    public static final String getCbCreateurName =
            "SELECT Prot_User\n" +
                    "                    FROM F_DOCLIGNE A\n" +
                    "                    INNER JOIN F_PROTECTIONCIAL P ON A.cbCreateur = CAST(P.PROT_No AS VARCHAR(5))\n" +
                    "                  WHERE A.cbMarq = ?";

    public static final String addDocligneTransfertConfirmationProcess =
            "INSERT INTO Z_LIGNE_CONFIRMATION([AR_Ref],[Prix],[DL_Qte],[cbMarqEntete],[cbMarqLigneFirst]) " +
                    "VALUES (?,?,?,?,?)";

    public static final String insertFligneA =
            "DECLARE @cbMarq INT = ?" +
                    ",@nAnalytique INT = ?" +
                    ",@caNum NVARCHAR(100) = ?" +
                    ",@eaMontant FLOAT = ?" +
                    ",@eaQte FLOAT = ?" +
                    " INSERT INTO [Z_LIGNE_COMPTEA]" +
                    " ([CbMarq_Ligne],[N_Analytique],[EA_Ligne],[CA_Num],[EA_Montant],[EA_Quantite],[cbModification])" +
                    " VALUES" +
                    " (/*CbMarq_Ligne*/@cbMarq ,/*N_Analytique*/@nAnalytique ,/*EA_Ligne*/ISNULL((SELECT MAX(EA_Ligne)EA_Ligne FROM [Z_LIGNE_COMPTEA] WHERE CbMarq_Ligne=@cbMarq),0)+1,/*CA_Num*/@caNum,/*EA_Montant*/@eaMontant,/*EA_Quantite*/@eaQte" +
                    " ,/*cbModification*/CAST(GETDATE() AS DATE))";
    public static final String initRemise =
            "SELECT CASE WHEN DL_Remise01REM_Type=0 THEN ''\n" +
                    "    WHEN DL_Remise01REM_Type=1 THEN cast(cast(DL_Remise01REM_Valeur as numeric(9,2)) as varchar(10))+'%' ELSE cast(cast(DL_Remise01REM_Valeur as numeric(9,2)) as varchar(10))+'U' END DL_Remise\n" +
                    "        ,DL_PrixUnitaire -(CASE WHEN DL_Remise01REM_Type= 0 THEN DL_PrixUnitaire\n" +
                    "    WHEN DL_Remise01REM_Type=1 THEN  DL_PrixUnitaire * DL_Remise01REM_Valeur / 100\n" +
                    "    WHEN DL_Remise01REM_Type=2 THEN DL_Remise01REM_Valeur ELSE 0 END) DL_PrixUnitaire_Rem\n" +
                    "            ,DL_PUTTC -(CASE WHEN DL_Remise01REM_Type= 0 THEN DL_PUTTC\n" +
                    "    WHEN DL_Remise01REM_Type=1 THEN  DL_PrixUnitaire * DL_Remise01REM_Valeur / 100\n" +
                    "    WHEN DL_Remise01REM_Type=2 THEN DL_Remise01REM_Valeur ELSE 0 END) DL_PUTTC_Rem\n" +
                    "            ,DL_PrixUnitaire -(CASE WHEN DL_Remise01REM_Type= 0 THEN 0\n" +
                    "    WHEN DL_Remise01REM_Type=1 THEN  DL_PrixUnitaire * DL_Remise01REM_Valeur / 100\n" +
                    "    WHEN DL_Remise01REM_Type=2 THEN DL_Remise01REM_Valeur ELSE 0 END) DL_PrixUnitaire_Rem0\n" +
                    "            ,DL_PUTTC -(CASE WHEN DL_Remise01REM_Type= 0 THEN 0\n" +
                    "    WHEN DL_Remise01REM_Type=1 THEN  DL_PrixUnitaire * DL_Remise01REM_Valeur / 100\n" +
                    "    WHEN DL_Remise01REM_Type=2 THEN DL_Remise01REM_Valeur ELSE 0 END) DL_PUTTC_Rem0\n" +
                    "            ,CASE WHEN DL_TypeTaux1=0 THEN DL_MontantHT*(DL_Taxe1/100)\n" +
                    "    WHEN DL_TypeTaux1=1 THEN DL_Taxe1*DL_Qte ELSE DL_Taxe1 END MT_Taxe1\n" +
                    "            , CASE WHEN DL_TypeTaux2=0 THEN DL_MontantHT*(DL_Taxe2/100)\n" +
                    "    WHEN DL_TypeTaux2=1 THEN DL_Taxe2*DL_Qte ELSE DL_Taxe2 END MT_Taxe2\n" +
                    "            , CASE WHEN DL_TypeTaux3=0 THEN DL_MontantHT*(DL_Taxe3/100)\n" +
                    "    WHEN DL_TypeTaux3=1 THEN DL_Taxe3*DL_Qte ELSE DL_Taxe3 END MT_Taxe3\n" +
                    "    FROM F_DOCLIGNE\n" +
                    "    WHERE cbMarq = ?";
}