package com.server.itsolution.mapper;

public class FJournauxMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "SELECT     JO_Type,JO_NumPiece,JO_Contrepartie,JO_SaisAnal,JO_NotCalcTot\n" +
            "               ,JO_Rappro,JO_Sommeil,JO_IFRS,JO_Reglement,JO_SuiviTreso\n" +
            "               ,JO_Num,JO_Intitule,CG_Num,JO_SaisAnal \n" +
            "               ,cbCreateur,cbModification,cbMarq " +
            "       FROM    F_JOURNAUX";

    public static final String getJournal = BASE_SQL + " WHERE JO_Num= ?";

    public static final String getJournaux
            = " DECLARE @joSommeil AS INT = ? " +
            " SELECT JO_Num,JO_Intitule,CG_Num,cbModification "+
            " FROM F_JOURNAUX "+
            " WHERE (@joSommeil=0 AND 1=1) OR (@joSommeil=1 AND JO_Sommeil=0) OR (@joSommeil=2 AND JO_Sommeil=1) ";

    public static final String getJournauxCount
            = " DECLARE @joSommeil AS INT = ? " +
            " SELECT Nb = count(*) " +
            "        ,cbModification = MAX(cbModification) "+
            " FROM F_JOURNAUX "+
            " WHERE (@joSommeil=0 AND 1=1) OR (@joSommeil=1 AND JO_Sommeil=0) OR (@joSommeil=2 AND JO_Sommeil=1) ";


    public  static final String getJournauxSaufTotaux
         = " SELECT JO_Num,JO_Intitule,CG_Num,cbModification"+
           " FROM F_JOURNAUX" +
           " WHERE JO_Type<>0";

    public static final String getJournauxType
         = " DECLARE @joType AS INT = ?" +
            " DECLARE @joSommeil AS INT = ? " +
            "SELECT JO_Num,JO_Intitule,CG_Num,cbModification "+
            " FROM F_JOURNAUX "+
            " WHERE JO_Type=@joType "+
            " AND (@joSommeil = -1 OR JO_Sommeil=@joSommeil)";

    public static final String getJournauxSaisieSelect =
            "SET language french;\n" +
                    "                    DECLARE @mois AS INT = ?;\n" +
                    "                    DECLARE @ouvert AS INT = ?;\n" +
                    "                    DECLARE @journal AS VARCHAR(50) = ?;\n" +
                    "                \n" +
                    "                WITH months(NomMois,MonthNumber) AS\n" +
                    "                (\n" +
                    "                    SELECT  NomMois = DateName( month , DateAdd( month , 1, 0 ) - 1 )\n" +
                    "                            ,MonthNumber = 1 \n" +
                    "                    UNION ALL\n" +
                    "                    SELECT  NomMois = DateName( month , DateAdd( month , MonthNumber+1, 0 ) - 1 )\n" +
                    "                            ,MonthNumber = MonthNumber+1  \n" +
                    "                    FROM    months\n" +
                    "                    WHERE   MonthNumber < 12\n" +
                    "                )\n" +
                    "\n" +
                    "                SELECT  MonthNumber\n" +
                    "                        ,NomMois = CASE WHEN @mois=1 THEN NomMois ELSE '' END \n" +
                    "                        ,JO_Num = CASE WHEN @journal=1 THEN A.JO_Num ELSE '' END \n" +
                    "                FROM(\n" +
                    "                SELECT  NomMois\n" +
                    "                        ,MonthNumber\n" +
                    "                        ,JO_Num\n" +
                    "                        ,JO_Intitule\n" +
                    "                FROM    F_JOURNAUX,months\n" +
                    "                )A\n" +
                    "                LEFT JOIN F_JMOUV B \n" +
                    "                    ON  A.JO_Num =B.JO_Num \n" +
                    "                    AND A.MonthNumber=MONTH(JM_Date)\n" +
                    "                WHERE   (@ouvert!=1 OR (@ouvert=1 AND B.JO_Num IS NOT NULL))\n" +
                    "                AND     (@ouvert!=2 OR (@ouvert=2 AND B.JO_Num IS NULL))\n" +
                    "                GROUP BY MonthNumber\n" +
                    "                         ,CASE WHEN @mois=1 THEN NomMois ELSE '' END \n" +
                    "                         ,CASE WHEN @journal=1 THEN A.JO_Num ELSE '' END\n" +
                    "                ORDER BY MonthNumber";

    public static String getJournauxSaisie
            =       "SET language french;\n" +
            "                    DECLARE @NomMois AS VARCHAR(50) = ?;\n" +
            "                    DECLARE @ouvert AS INT = ?;\n" +
            "                    DECLARE @annee AS INT = ?;\n" +
            "                    DECLARE @joNum AS VARCHAR(50) = ?;\n" +
            "                    \n" +
            "WITH months AS\n" +
            "(\n" +
            "    SELECT  NomMois = DateName( month , DateAdd( month , 1, 0 ) - 1 )\n" +
            "            ,MonthNumber = 1 \n" +
            "\t\t\t,YearMonth = CONCAT(@annee,'01')\n" +
            "    UNION ALL\n" +
            "    SELECT  NomMois = DateName( month , DateAdd( month , MonthNumber+1, 0 ) - 1 )\n" +
            "            ,MonthNumber = MonthNumber+1  \n" +
            "\t\t\t,YearMonth = CONCAT(@annee,RIGHT(CONCAT('0',MonthNumber+1),2))\n" +
            "    FROM    months\n" +
            "    WHERE   MonthNumber < 12\n" +
            "),\n" +
            "_Journal_ AS (\n" +
            "SELECT  NomMois\n" +
            "        ,MonthNumber\n" +
            "        ,JO_Num\n" +
            "        ,JO_Intitule\n" +
            "\t\t,YearMonth\n" +
            "FROM    F_JOURNAUX,months\n" +
            "),\n" +
            "_Mouv_ AS (\n" +
            "SELECT\tJO_Num\n" +
            "\t\t,YearMonth = CONCAT(YEAR(JM_Date),RIGHT(CONCAT('0',MONTH(JM_Date)),2))\n" +
            "FROM\tF_JMOUV \n" +
            "WHERE\tYEAR(JM_Date) = @annee\n" +
            ")\n" +
            "SELECT\tA.*\n" +
            "FROM\t_Journal_ A\n" +
            "LEFT JOIN _Mouv_ B\n" +
            "\tON  A.JO_Num = B.JO_Num \n" +
            "    AND A.YearMonth = B.YearMonth\n" +
            "WHERE \n" +
            "(@ouvert<>1 OR (@ouvert=1 AND B.JO_Num IS NOT NULL))\n" +
            "AND (@ouvert<>2 OR (@ouvert=2 AND B.JO_Num IS NULL))\n" +
            "AND (@NomMois='0' OR NomMois=@NomMois)\n" +
            "AND (@joNum='0' OR A.JO_Num=@joNum)\n" +
            "ORDER BY MonthNumber";

    public static String calculSoldeLettrage =
            "                DECLARE @joNum VARCHAR(50) = ?;\n" +
            "                DECLARE @Mois INT = ?;\n" +
            "                DECLARE @Annee INT = ?;\n" +
            "                DECLARE @ctNum NVARCHAR(50) = ?;\n" +
            "                DECLARE @dateDebut NVARCHAR(50) = ?;\n" +
            "                DECLARE @dateFin NVARCHAR(50) = ?;\n" +
            "                DECLARE @lettrage INT = ?;\n" +
            "                DECLARE @cgNum NVARCHAR(50) = ?;\n" +
            "                \n" +
            "                SELECT  A.JO_Num\n" +
            "                        ,A.cbMarq\n" +
            "                        ,A.EC_No\n" +
            "                        ,JM_Date\n" +
            "                        ,EC_Jour\n" +
            "                        ,EC_Reference\n" +
            "                        ,EC_Piece\n" +
            "                        ,EC_Date\n" +
            "                        ,A.EC_Lettrage\n" +
            "                        ,EC_RefPiece,EC_TresoPiece\n" +
            "                        ,Lien_Fichier = ISNULL(Lien_Fichier,'')\n" +
            "                        ,A.CG_Num,A.CT_Num,EC_Intitule,N_Reglement\n" +
            "                        ,EC_Echeance,EC_Sens,EC_Montant\n" +
            "                        ,EC_MontantCredit = CASE WHEN EC_Sens=1 THEN EC_Montant ELSE 0 END \n" +
            "                        ,EC_MontantDebit = CASE WHEN EC_Sens=0 THEN EC_Montant ELSE 0 END \n" +
            "                        ,EC_Echeance_C = CAST(EC_Echeance AS DATE) \n" +
            "                        ,B.CG_Analytique\n" +
            "                FROM    F_ECRITUREC A\n" +
            "                LEFT JOIN  F_COMPTEG B \n" +
            "                    ON  A.CG_Num = B.CG_Num\n" +
            "                LEFT JOIN Z_ECRITURECPIECE C \n" +
            "                    ON  A.EC_No = C.EC_No\n" +
            "                WHERE   (@joNum ='' OR JO_Num=@joNum) \n" +
            "                AND     (@ctNum ='' OR A.CT_Num=@ctNum) \n" +
            "                AND     (@cgNum ='' OR A.CG_Num=@cgNum) \n" +
            "                AND     (   (@dateDebut <>'' OR @dateFin<>'') \n" +
            "                            OR (@dateDebut ='' AND @dateFin='' AND MONTH(JM_Date) = @Mois))\n" +
            "                AND     YEAR(JM_Date) = @Annee\n" +
            "                AND     (@lettrage = -1 OR A.EC_Lettre = @lettrage)\n" +
            "                AND     (@dateDebut = '' OR CAST(DATEADD(DAY,A.EC_Jour-1,A.JM_Date) AS DATE) >= @dateDebut)\n" +
            "                AND     (@dateFin = '' OR CAST(DATEADD(DAY,A.EC_Jour-1,A.JM_Date) AS DATE) <= @dateFin)\n" +
            "                ORDER BY A.cbMarq";

    public static String headerSaisiJournal =
            "DECLARE @exercice AS INT = ?;\n" +
            "        DECLARE @joNum AS VARCHAR(50) = ?;\n" +
            "        DECLARE @position AS INT = ?;\n" +
            "        DECLARE @debutExercice AS INT = CONCAT(LEFT(@exercice,4),'01')\n" +
            "        DECLARE @annee AS INT = LEFT(@exercice,4);\n" +
            "\n" +
            "        WITH _AncienSolde_ AS (\n" +
            "                SELECT\tDebit = SUM(CASE WHEN C.EC_Sens=0 THEN C.EC_Montant ELSE -C.EC_Montant END)\n" +
            "                ,Credit = SUM(CASE WHEN C.EC_Sens=1 THEN C.EC_Montant ELSE -C.EC_Montant END)\n" +
            "                ,Total = SUM(CASE WHEN C.EC_Sens=1 THEN C.EC_Montant ELSE -C.EC_Montant END)\n" +
            "                FROM\tF_ECRITUREC C\n" +
            "                LEFT JOIN F_JOURNAUX J\n" +
            "                ON\t\tC.CG_Num=J.CG_Num\n" +
            "                WHERE\tYEAR(C.JM_Date) = @annee\n" +
            "                        AND\t\t(\n" +
            "                        (J.JO_Num=@joNum\n" +
            "                                AND\t\tC.JO_Num<>@joNum)\n" +
            "        OR\n" +
            "                (C.JO_Num=@joNum\n" +
            "                        AND\tMONTH(JM_Date) < RIGHT(@exercice,2)\n" +
            "        AND C.CG_Num IN (\tSELECT\tCG_Num\n" +
            "        FROM\tF_JOURNAUX\n" +
            "        WHERE\tJO_Num=@joNum))\t\t\t\t\n" +
            "                    )\n" +
            "                )\n" +
            "                ,_TotalSolde_ AS (\n" +
            "                SELECT\tDebit = SUM(CASE WHEN EC_Sens=0 THEN EC_Montant ELSE 0 END)\n" +
            "                ,Credit = SUM(CASE WHEN EC_Sens=1 THEN EC_Montant ELSE 0 END)\n" +
            "                ,Total = SUM(CASE WHEN EC_Sens=1 THEN EC_Montant ELSE -EC_Montant END)\n" +
            "        FROM\tF_ECRITUREC\n" +
            "        WHERE\tYEAR(JM_Date) = LEFT(@exercice,4)\n" +
            "        AND\t\tJO_Num = @joNum\n" +
            "                AND\t\tMonth(JM_Date) = RIGHT(@exercice,2)\n" +
            "                )\n" +
            "                ,_NouveauSolde_ AS (\n" +
            "                SELECT\tDebit = SUM(CASE WHEN EC_Sens=0 THEN (EC_Montant) ELSE 0 END)-SUM(CASE WHEN EC_Sens=1 THEN (EC_Montant) ELSE 0 END)\n" +
            "                ,Credit = SUM(CASE WHEN EC_Sens=1 THEN (EC_Montant) ELSE 0 END)-SUM(CASE WHEN EC_Sens=0 THEN (EC_Montant) ELSE 0 END)\n" +
            "                ,Total = SUM(CASE WHEN EC_Sens=1 THEN (EC_Montant) ELSE -EC_Montant END)\n" +
            "        FROM\tF_ECRITUREC C\n" +
            "        LEFT JOIN F_JOURNAUX J\n" +
            "        ON\t\tC.CG_Num=J.CG_Num\n" +
            "        WHERE YEAR(C.JM_Date) = LEFT(@annee,4)\n" +
            "        AND (\n" +
            "                (C.JO_Num=@joNum\n" +
            "                        AND\tMONTH(JM_Date) <= RIGHT(@exercice,2)\n" +
            "        AND C.CG_Num IN (\tSELECT\tCG_Num\n" +
            "        FROM\tF_JOURNAUX\n" +
            "        WHERE\tJO_Num=@joNum))\n" +
            "\n" +
            "        OR (J.JO_Num=@joNum\n" +
            "                AND\tC.JO_Num<>@joNum))\n" +
            "                \n" +
            "                )\n" +
            "                ,_NouveauSoldeFinal_ AS (\n" +
            "                SELECT\tDebit\n" +
            "                ,Credit\n" +
            "                ,Total\n" +
            "        FROM\t_NouveauSolde_\n" +
            "                )\n" +
            "\n" +
            "SELECT  Position\n" +
            "                        ,Libelle\n" +
            "                        ,Debit\n" +
            "                        ,Credit\n" +
            "                        ,Total\n" +
            "                FROM (\n" +
            "                    SELECT\tPosition = 1\n" +
            "                            ,Libelle = 'Ancien solde' \n" +
            "                            ,Debit = CASE WHEN Debit<0 THEN 0 ELSE Debit END\n" +
            "                            ,Credit = CASE WHEN Credit<0 THEN 0 ELSE Credit END\n" +
            "                            ,Total = ABS(Total)\n" +
            "                    FROM\t_AncienSolde_\n" +
            "                    UNION ALL\n" +
            "                    SELECT\tPosition = 2\n" +
            "                            ,Libelle = 'Total journal' \n" +
            "                            ,Debit\n" +
            "                            ,Credit\n" +
            "                            ,Total\n" +
            "                    FROM\t_TotalSolde_\n" +
            "                    UNION ALL \n" +
            "                    SELECT\tPosition = 3\n" +
            "                            ,Libelle = 'Nouveau solde' \n" +
            "                            ,Debit = CASE WHEN Debit<0 THEN 0 ELSE Debit END\n" +
            "                            ,Credit = CASE WHEN Credit<0 THEN 0 ELSE Credit END\n" +
            "                            ,ABS(Total)\n" +
            "                    FROM _NouveauSoldeFinal_\n" +
            "                ) unionSaisie \n" +
            "                WHERE @position = 0 OR @position = Position";

    public static final String getSaisieJournalExercice =
            "DECLARE @joNum VARCHAR(50) = ?;\n" +
            "                DECLARE @Mois INT = ?;\n" +
            "                DECLARE @Annee INT = ?;\n" +
            "                DECLARE @ctNum NVARCHAR(50) = ?;\n" +
            "                DECLARE @dateDebut NVARCHAR(50) = ?;\n" +
            "                DECLARE @dateFin NVARCHAR(50) = ?;\n" +
            "                DECLARE @lettrage INT = ?;\n" +
            "                DECLARE @cgNum NVARCHAR(50) = ?;\n" +
            "                \n" +
            "                SELECT  A.JO_Num\n" +
            "                        ,A.cbMarq\n" +
            "                        ,A.EC_No\n" +
            "                        ,JM_Date\n" +
            "                        ,EC_Jour\n" +
            "                        ,EC_Reference\n" +
            "                        ,EC_Piece\n" +
            "                        ,EC_Date\n" +
            "                        ,A.EC_Lettrage\n" +
            "                        ,EC_RefPiece,EC_TresoPiece\n" +
            "                        ,Lien_Fichier = ISNULL(Lien_Fichier,'')\n" +
            "                        ,A.CG_Num,A.CT_Num,EC_Intitule,N_Reglement\n" +
            "                        ,EC_Echeance,EC_Sens,EC_Montant\n" +
            "                        ,EC_MontantCredit = CASE WHEN EC_Sens=1 THEN EC_Montant ELSE 0 END \n" +
            "                        ,EC_MontantDebit = CASE WHEN EC_Sens=0 THEN EC_Montant ELSE 0 END \n" +
            "                        ,EC_Echeance_C = CAST(EC_Echeance AS DATE) \n" +
            "                        ,B.CG_Analytique\n" +
            "                FROM    F_ECRITUREC A\n" +
            "                LEFT JOIN  F_COMPTEG B \n" +
            "                    ON  A.CG_Num = B.CG_Num\n" +
            "                LEFT JOIN Z_ECRITURECPIECE C \n" +
            "                    ON  A.EC_No = C.EC_No\n" +
            "                WHERE   (@joNum ='' OR JO_Num=@joNum) \n" +
            "                AND     (@ctNum ='' OR A.CT_Num=@ctNum) \n" +
            "                AND     (@cgNum ='' OR A.CG_Num=@cgNum) \n" +
            "                AND     (   (@dateDebut <>'' OR @dateFin<>'') \n" +
            "                            OR (@dateDebut ='' AND @dateFin='' AND MONTH(JM_Date) = @Mois))\n" +
            "                AND     YEAR(JM_Date) = @Annee\n" +
            "                AND     (@lettrage = -1 OR A.EC_Lettre = @lettrage)\n" +
            "                AND     (@dateDebut = '' OR CAST(DATEADD(DAY,A.EC_Jour-1,A.JM_Date) AS DATE) >= @dateDebut)\n" +
            "                AND     (@dateFin = '' OR CAST(DATEADD(DAY,A.EC_Jour-1,A.JM_Date) AS DATE) <= @dateFin)\n" +
            "                ORDER BY A.cbMarq";

    public static final String getTotalJournal
            ="DECLARE @joNum VARCHAR(50) =?;\n" +
            "                DECLARE @Mois INT =?;\n" +
            "                DECLARE @Annee INT =?;\n" +
            "                DECLARE @sens INT =?;\n" +
            "                DECLARE @ctNum NVARCHAR(50) =?;\n" +
            "                DECLARE @cgNum NVARCHAR(50) =?;\n" +
            "                DECLARE @dateDebut NVARCHAR(50) =?;\n" +
            "                DECLARE @dateFin NVARCHAR(50) =?;\n" +
            "                DECLARE @lettrage INT =?;\n" +
            "                \n" +
            "                SELECT EC_Montant = CASE WHEN @sens = 0 THEN ABS(EC_Montant) ELSE EC_Montant END\n" +
            "                FROM(\n" +
            "                SELECT  EC_Montant = SUM(CASE WHEN EC_Sens = 1 THEN EC_Montant ELSE - EC_Montant END) \n" +
            "                FROM    F_ECRITUREC A\n" +
            "                LEFT JOIN   F_COMPTEG B \n" +
            "                    ON  A.CG_Num = B.CG_Num\n" +
            "                LEFT JOIN   Z_ECRITURECPIECE C \n" +
            "                    ON  A.EC_No = C.EC_No\n" +
            "                WHERE   (@joNum ='' OR JO_Num=@joNum) \n" +
            "                AND     (@ctNum ='' OR A.CT_Num=@ctNum) \n" +
            "                AND     (@cgNum ='' OR A.CG_Num=@cgNum) \n" +
            "                AND     (   (@dateDebut <>'' OR @dateFin<>'') \n" +
            "                            OR (@dateDebut ='' AND @dateFin='' AND MONTH(JM_Date) = @Mois))\n" +
            "                AND     YEAR(JM_Date) = @Annee\n" +
            "                AND     (@sens=2 OR EC_Sens = @sens)\n" +
            "                AND     (@lettrage = -1 OR A.EC_Lettre = @lettrage)\n" +
            "                AND     (@dateDebut = '' OR CAST(DATEADD(DAY,A.EC_Jour-1,A.JM_Date) AS DATE) >= @dateDebut)\n" +
            "                AND     (@dateFin = '' OR CAST(DATEADD(DAY,A.EC_Jour-1,A.JM_Date) AS DATE) <= @dateFin)\n" +
            "                )A";

    public static final String getJournalLastDate
            ="\n" +
            "                DECLARE @joNum VARCHAR(50) =?;\n" +
            "                DECLARE @Mois INT =?;\n" +
            "                DECLARE @Annee INT =?;\n" +
            "                \n" +
            "                SELECT  EC_Jour = ISNULL(MAX(EC_Jour),1) \n" +
            "                FROM    F_ECRITUREC A\n" +
            "                LEFT JOIN  F_COMPTEG B \n" +
            "                    ON  A.CG_Num = B.CG_Num\n" +
            "                LEFT JOIN Z_ECRITURECPIECE C \n" +
            "                    ON  A.EC_No = C.EC_No\n" +
            "                WHERE   JO_Num=@joNum \n" +
            "                AND     MONTH(JM_Date) = @Mois\n" +
            "                AND     YEAR(JM_Date) = @Annee";

    public static final String getECPiece
            ="\n" +
            "BEGIN \n" +
            "                    DECLARE @joNum VARCHAR(50) = ?\n" +
            "                    DECLARE @joNumPiece INT = 0\n" +
            "                    DECLARE @date VARCHAR(10)= ?\n" +
            "                    \n" +
            "                    SELECT @joNumPiece = JO_NumPiece FROM F_JOURNAUX WHERE JO_Num=@joNum\n" +
            "                    IF @joNumPiece = 1  \n" +
            "                        SELECT\tEC_Piece = ISNULL(MAX(EC_Piece),0) + 1\n" +
            "                        FROM\tF_ECRITUREC \n" +
            "                        WHERE\tJO_Num=@joNum\n" +
            "                    IF @joNumPiece = 2  \t\n" +
            "                        SELECT\tEC_Piece = ISNULL(MAX(EC_Piece),0) + 1\n" +
            "                        FROM\tF_ECRITUREC \n" +
            "                    IF @joNumPiece = 3  \n" +
            "                        SELECT\tEC_Piece = ISNULL(MAX(EC_Piece),0) + 1\n" +
            "                        FROM\tF_JOURNAUX fj\n" +
            "                        LEFT JOIN (SELECT JO_Num,EC_Piece\n" +
            "                                  FROM F_ECRITUREC \n" +
            "                                  WHERE JM_Date = @date) ec\t\n" +
            "                            ON\tec.JO_Num = fj.JO_Num\n" +
            "                        WHERE\tfj.JO_Num=@joNum\n" +
            "                        END";

    public static final String getJournalPiece
            =" DECLARE @joNum VARCHAR(50) =?;\n" +
            "                DECLARE @Mois INT =?;\n" +
            "                DECLARE @Annee INT =?;\n" +
            "                \n" +
            "                WITH _MaxPiece_ AS (\n" +
            "                    SELECT  EC_Montant = SUM(CASE WHEN EC_Sens = 1 THEN EC_Montant ELSE - EC_Montant END)\n" +
            "                            ,MaxEC_Piece = MAX(EC_Piece)\n" +
            "                    FROM    F_ECRITUREC C\n" +
            "                    WHERE   JO_Num=@joNum \n" +
            "                    AND     MONTH(JM_Date) = @Mois\n" +
            "                    AND     YEAR(JM_Date) = @Annee\n" +
            "                )\n" +
            "                \n" +
            "                SELECT\t@ecPiece = CASE WHEN EC_Montant = 0 THEN ISNULL(TRY_CAST(MaxEC_Piece AS INT),0)+1 ELSE ISNULL(MaxEC_Piece,1) END\n" +
            "                FROM\t_MaxPiece_ \n" +
            "                \n" +
            "                SELECT EC_Piece = @ecPiece";

    public static final String  getLettrage
            ="BEGIN \n" +
            "\t\t\t\tDECLARE @result VARCHAR(10);\n" +
            "\t\t\t\tDECLARE @ctNum VARCHAR(50) =?;\n" +
            "\t\t\t\tDECLARE @cgNum VARCHAR(50) =?;\n" +
            "                DECLARE @dateDebut NVARCHAR(50) =?;\n" +
            "                DECLARE @dateFin NVARCHAR(50) =?;\n" +
            "                \n" +
            "\t\t\t\tSELECT  @result = CHAR(ASCII(EC_Lettrage)+1)\n" +
            "                FROM    F_ECRITUREC A\n" +
            "                WHERE   EC_Lettre=1\n" +
            "\t\t\t\tAND\t\t(@ctNum='' OR CT_Num=@ctNum)\n" +
            "                AND\t\t(@cgNum='' OR CG_Num=@cgNum)\n" +
            "                AND     (@dateDebut = '' OR CAST(DATEADD(DAY,A.EC_Jour-1,A.JM_Date) AS DATE) >= @dateDebut)\n" +
            "                AND     (@dateFin = '' OR CAST(DATEADD(DAY,A.EC_Jour-1,A.JM_Date) AS DATE) <= @dateFin)\n" +
            "                \n" +
            "\t\t\t\tselect EC_Lettrage = ISNULL(@result,'A')\n" +
            "\t\t\t\tEND";
}

