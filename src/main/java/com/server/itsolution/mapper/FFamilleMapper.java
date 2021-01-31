package com.server.itsolution.mapper;

public class FFamilleMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "SELECT FA_CodeFamille\n" +
            "      ,FA_Type\n" +
            "      ,FA_Intitule\n" +
            "      ,FA_UniteVen\n" +
            "      ,FA_Coef\n" +
            "      ,FA_SuiviStock\n" +
            "      ,FA_Garantie\n" +
            "      ,FA_Central\n" +
            "      ,FA_Stat01\n" +
            "      ,FA_Stat02\n" +
            "      ,FA_Stat03\n" +
            "      ,FA_Stat04\n" +
            "      ,FA_Stat05\n" +
            "      ,FA_CodeFiscal\n" +
            "      ,FA_Pays\n" +
            "      ,FA_UnitePoids\n" +
            "      ,FA_Escompte\n" +
            "      ,FA_Delai\n" +
            "      ,FA_HorsStat\n" +
            "      ,FA_VteDebit\n" +
            "      ,FA_NotImp\n" +
            "      ,FA_Frais01FR_Denomination\n" +
            "      ,FA_Frais01FR_Rem01REM_Valeur\n" +
            "      ,FA_Frais01FR_Rem01REM_Type\n" +
            "      ,FA_Frais01FR_Rem02REM_Valeur\n" +
            "      ,FA_Frais01FR_Rem02REM_Type\n" +
            "      ,FA_Frais01FR_Rem03REM_Valeur\n" +
            "      ,FA_Frais01FR_Rem03REM_Type\n" +
            "      ,FA_Frais02FR_Denomination\n" +
            "      ,FA_Frais02FR_Rem01REM_Valeur\n" +
            "      ,FA_Frais02FR_Rem01REM_Type\n" +
            "      ,FA_Frais02FR_Rem02REM_Valeur\n" +
            "      ,FA_Frais02FR_Rem02REM_Type\n" +
            "      ,FA_Frais02FR_Rem03REM_Valeur\n" +
            "      ,FA_Frais02FR_Rem03REM_Type\n" +
            "      ,FA_Frais03FR_Denomination\n" +
            "      ,FA_Frais03FR_Rem01REM_Valeur\n" +
            "      ,FA_Frais03FR_Rem01REM_Type\n" +
            "      ,FA_Frais03FR_Rem02REM_Valeur\n" +
            "      ,FA_Frais03FR_Rem02REM_Type\n" +
            "      ,FA_Frais03FR_Rem03REM_Valeur\n" +
            "      ,FA_Frais03FR_Rem03REM_Type\n" +
            "      ,FA_Contremarque\n" +
            "      ,FA_FactPoids\n" +
            "      ,FA_FactForfait\n" +
            "      ,FA_Publie\n" +
            "      ,FA_RacineRef\n" +
            "      ,FA_RacineCB\n" +
            "      ,CL_No1\n" +
            "      ,CL_No2\n" +
            "      ,CL_No3\n" +
            "      ,CL_No4\n" +
            "      ,cbProt\n" +
            "      ,cbMarq\n" +
            "      ,cbCreateur\n" +
            "      ,cbModification\n" +
            "      ,cbReplication\n" +
            "      ,cbFlag\n" +
            "  FROM F_FAMILLE ";

    public static final String getFamille //
            =   BASE_SQL + " WHERE FA_CodeFamille = ? ";

    public static final String getShortList
            =" SELECT cbModification,FA_CodeFamille,FA_Intitule,FA_Type "+
            " FROM F_FAMILLE "+
            " ORDER BY FA_CodeFamille ";

    public static final String getFamilleCount
            =" SELECT  Nb = count(*) \n" +
            "          ,cbModification = max(cbModification) \n" +
            "  FROM    F_FAMILLE";


    public static final String getNextArticleByFam =

    "DECLARE @codeFamille AS VARCHAR(20)\n" +
            "SET @codeFamille=?;\n" +
            "WITH _Article_ AS (\n" +
            "SELECT\tAR_Ref,FA_CodeFamille\n" +
            "FROM\tF_ARTICLE\n" +
            "UNION\n" +
            "SELECT\tLEFT(CONCAT(FA_CodeFamille,'000000000000'),(SELECT GE_ArtLen FROM P_GENAUTO)),FA_CodeFamille\n" +
            "FROM F_FAMILLE\n" +
            ")\n" +
            "SELECT F.FA_CodeFamille\n" +
            ",CONCAT(F.FA_CodeFamille,RIGHT('000000000000'+CAST((CASE WHEN MAX(AR_Ref) IS NOT NULL THEN \n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\tRIGHT(MAX(AR_Ref),(SELECT GE_ArtLen FROM P_GENAUTO)-LEN(F.FA_CodeFamille)) ELSE 0 END)+1 AS VARCHAR(100)),\n" +
            "(SELECT GE_ArtLen FROM P_GENAUTO)-LEN(F.FA_CodeFamille))) AR_Ref\n" +
            "FROM F_FAMILLE F \n" +
            "LEFT JOIN _Article_ A \n" +
            "ON F.FA_CodeFamille = A.FA_CodeFamille  \n" +
            "WHERE F.FA_CodeFamille=@codeFamille\n" +
            "AND A.AR_Ref LIKE CONCAT(@codeFamille,'%')\n" +
            "GROUP BY F.FA_CodeFamille";

     public static final String getCatComptaByCodeFamille =
             "SELECT  A.FA_CodeFamille,FCP_Type ACP_Type\n" +
             "                        ,FCP_Champ ACP_Champ\n" +
             "                        ,CG_Num = ISNULL(FCP_ComptaCPT_CompteG,'') \n" +
             "                        ,ISNULL(CG.CG_Intitule,'') CG_Intitule,\n" +
             "                        ISNULL(FCP_ComptaCPT_CompteA,'') CG_NumA,ISNULL(CA.CG_Intitule,'') CG_IntituleA,\n" +
             "                        ISNULL(FCP_ComptaCPT_CompteA,'')CG_NumA,ISNULL(FCP_ComptaCPT_Taxe1,'') Taxe1,\n" +
             "                        ISNULL(TU.TA_Intitule,'') TA_Intitule1,ISNULL(TU.TA_Taux,0) TA_Taux1\n" +
             "                        ,ISNULL(FCP_ComptaCPT_Taxe2,'') Taxe2,ISNULL(TD.TA_Intitule,'') TA_Intitule2,ISNULL(TD.TA_Taux,0) TA_Taux2,\n" +
             "                        ISNULL(FCP_ComptaCPT_Taxe3,'') Taxe3,ISNULL(TT.TA_Intitule,'') TA_Intitule3,ISNULL(TT.TA_Taux,0) TA_Taux3\n" +
             "                FROM    F_FAMCOMPTA A \n" +
             "                LEFT JOIN F_TAXE TU \n" +
             "                    ON A.FCP_ComptaCPT_Taxe1 = TU.TA_Code\n" +
             "                LEFT JOIN F_TAXE TD \n" +
             "                    ON A.FCP_ComptaCPT_Taxe2 = TD.TA_Code\n" +
             "                LEFT JOIN F_TAXE TT \n" +
             "                    ON A.FCP_ComptaCPT_Taxe3 = TT.TA_Code\n" +
             "                LEFT JOIN F_COMPTEG CG  \n" +
             "                    ON A.FCP_ComptaCPT_CompteG = CG.CG_Num\n" +
             "                LEFT JOIN F_COMPTEG CA \n" +
             "                    ON A.FCP_ComptaCPT_CompteA = CA.CG_Num\n" +
             "                WHERE   A.FA_CodeFamille=?\n" +
             "                AND     A.FCP_Type=?\n" +
             "                AND     A.FCP_Champ=?";

     public static final String insertFamille =
             "DECLARE @code AS NVARCHAR(50) = ?;\n" +
             "            DECLARE @intitule AS NVARCHAR(50) = ?;\n" +
             "            DECLARE @CL_No1 AS INT = ?;\n" +
             "            DECLARE @CL_No2 AS INT = ?;\n" +
             "            DECLARE @CL_No3 AS INT = ?;\n" +
             "            DECLARE @CL_No4 AS INT = ?;\n" +
             "            DECLARE @protNo AS INT = ?;\n" +
             "            \n" +
             "            INSERT INTO [dbo].[F_FAMILLE]\n" +
             "           ([FA_CodeFamille],[FA_Type],[FA_Intitule],[FA_UniteVen]\n" +
             "           ,[FA_Coef],[FA_SuiviStock],[FA_Garantie],[FA_Central]\n" +
             "           ,[FA_Stat01],[FA_Stat02],[FA_Stat03],[FA_Stat04]\n" +
             "           ,[FA_Stat05],[FA_CodeFiscal],[FA_Pays],[FA_UnitePoids]\n" +
             "           ,[FA_Escompte],[FA_Delai],[FA_HorsStat],[FA_VteDebit]\n" +
             "           ,[FA_NotImp],[FA_Frais01FR_Denomination],[FA_Frais01FR_Rem01REM_Valeur],[FA_Frais01FR_Rem01REM_Type]\n" +
             "           ,[FA_Frais01FR_Rem02REM_Valeur],[FA_Frais01FR_Rem02REM_Type],[FA_Frais01FR_Rem03REM_Valeur],[FA_Frais01FR_Rem03REM_Type]\n" +
             "           ,[FA_Frais02FR_Denomination],[FA_Frais02FR_Rem01REM_Valeur],[FA_Frais02FR_Rem01REM_Type],[FA_Frais02FR_Rem02REM_Valeur]\n" +
             "           ,[FA_Frais02FR_Rem02REM_Type],[FA_Frais02FR_Rem03REM_Valeur],[FA_Frais02FR_Rem03REM_Type],[FA_Frais03FR_Denomination]\n" +
             "           ,[FA_Frais03FR_Rem01REM_Valeur],[FA_Frais03FR_Rem01REM_Type],[FA_Frais03FR_Rem02REM_Valeur],[FA_Frais03FR_Rem02REM_Type]\n" +
             "           ,[FA_Frais03FR_Rem03REM_Valeur],[FA_Frais03FR_Rem03REM_Type],[FA_Contremarque],[FA_FactPoids]\n" +
             "           ,[FA_FactForfait],[FA_Publie],[FA_RacineRef],[FA_RacineCB]\n" +
             "           ,[CL_No1],[CL_No2],[CL_No3],[CL_No4]\n" +
             "           ,[cbProt],[cbCreateur],[cbModification],[cbReplication],[cbFlag])\n" +
             "     VALUES\n" +
             "           (/*FA_CodeFamille*/@code,/*FA_Type*/0,/*FA_Intitule, */@intitule,/*FA_UniteVen*/4\n" +
             "           ,/*FA_Coef*/0,/*FA_SuiviStock*/2,/*FA_Garantie*/0,/*FA_Central*/''\n" +
             "           ,/*FA_Stat01*/'',/*FA_Stat02*/'',/*FA_Stat03*/'',/*FA_Stat04*/''\n" +
             "           ,/*FA_Stat05*/'',/*FA_CodeFiscal*/'',/*FA_Pays, */'',/*FA_UnitePoids*/2\n" +
             "           ,/*FA_Escompte*/0,/*FA_Delai*/0,/*FA_HorsStat*/0,/*FA_VteDebit*/0\n" +
             "           ,/*FA_NotImp*/0,/*FA_Frais01FR_Denomination*/'Coût de stockage'\n" +
             "           ,/*FA_Frais01FR_Rem01REM_Valeur*/0,/*FA_Frais01FR_Rem01REM_Type*/0\n" +
             "           ,/*FA_Frais01FR_Rem02REM_Valeur*/0,/*FA_Frais01FR_Rem02REM_Type*/0\n" +
             "           ,/*FA_Frais01FR_Rem03REM_Valeur*/0,/*FA_Frais01FR_Rem03REM_Type*/0\n" +
             "           ,/*FA_Frais02FR_Denomination*/'Coût de transport',/*FA_Frais02FR_Rem01REM_Valeur*/0\n" +
             "           ,/*FA_Frais02FR_Rem01REM_Type*/0,/*FA_Frais02FR_Rem02REM_Valeur*/0\n" +
             "           ,/*FA_Frais02FR_Rem02REM_Type*/0,/*FA_Frais02FR_Rem03REM_Valeur*/0\n" +
             "           ,/*FA_Frais02FR_Rem03REM_Type*/0,/*FA_Frais03FR_Denomination*/0\n" +
             "           ,/*FA_Frais03FR_Rem01REM_Valeur*/0,/*FA_Frais03FR_Rem01REM_Type*/0\n" +
             "           ,/*FA_Frais03FR_Rem02REM_Valeur*/0,/*FA_Frais03FR_Rem02REM_Type*/0\n" +
             "           ,/*FA_Frais03FR_Rem03REM_Valeur*/0,/*FA_Frais03FR_Rem03REM_Type*/0\n" +
             "           ,/*FA_Contremarque*/0,/*FA_FactPoids*/0,/*FA_FactForfait*/0,/*FA_Publie*/0\n" +
             "           ,/*FA_RacineRef*/@code,/*FA_RacineCB*/''\n" +
             "           ,/*CL_No1*/@CL_No1,/*CL_No2*/@CL_No2\n" +
             "           ,/*CL_No3*/@CL_No3,/*CL_No4*/@CL_No4\n" +
             "           ,/*cbProt*/0,/*cbCreateur, char(4)*/@protNo,/*cbModification, smalldatetime*/CAST(GETDATE() AS DATE),/*cbReplication*/0,/*cbFlag*/0)";

     public static final String supprFamille =
            "\n" +
                    "DECLARE @codeFamille NVARCHAR(50) = ?;\n" +
                    "IF NOT EXISTS (SELECT cbMarq FROM F_ARTICLE WHERE FA_CodeFamille=@codeFamille)\n" +
                    "BEGIN \n" +
                    "\tIF EXISTS(SELECT 1 FROM sys.objects WHERE NAME='TG_CBDEL_F_FAMILLE')\n" +
                    "\t\tALTER TABLE F_ARTICLE DISABLE TRIGGER TG_CBDEL_F_FAMILLE;\n" +
                    "\tDELETE FROM F_FAMILLE WHERE FA_CodeFamille=@codeFamille;\n" +
                    "    IF EXISTS(SELECT 1 FROM sys.objects WHERE NAME='TG_CBDEL_F_FAMILLE')\n" +
                    "\t\tALTER TABLE F_ARTICLE ENABLE TRIGGER TG_CBDEL_F_FAMILLE;\n" +
                    "END";
}
