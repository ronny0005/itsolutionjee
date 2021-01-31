package com.server.itsolution.mapper;

public class EtatMapper extends ObjectMapper {

    public static String equationStkVendeur =
            "DECLARE @dateDeb VARCHAR(20);\n" +
                    "            DECLARE @dateFin VARCHAR(20);\n" +
                    "            DECLARE @deNo INT;\n" +
                    "            SET @dateDeb = ?;\n" +
                    "            SET @dateFin = ?;\n" +
                    "            SET @deNo = ?;\n" +
                    "            \n" +
                    "            SELECT * FROM (SELECT AR_Design,AR_Ref,\n" +
                    "                STOCKS,ENTREES,SORTIES, \n" +
                    "                (STOCKS+ENTREES)-(SORTIES) AS STOCK_FINAL, \n" +
                    "                VENTES AS QTE_VENDUES, \n" +
                    "                STOCKS+ENTREES-SORTIES-VENTES AS STOCK_RESTANTS \n" +
                    "                FROM( \n" +
                    "                SELECT  fArt.AR_Design,fArt.AR_Ref,\n" +
                    "                                SUM(CASE WHEN FDOC.DO_Date BETWEEN @datedeb AND @datefin AND ((DO_Domaine=1 AND DO_Type=16) OR (DO_Domaine=2 AND DO_Type IN (20,21))) THEN FDOC.DL_Qte ELSE 0 END) ENTREES, \n" +
                    "                                SUM(CASE WHEN FDOC.DO_Date BETWEEN @datedeb AND @datefin AND FDOC.DO_Domaine = 2 AND FDOC.DO_Type IN (21,23) THEN FDOC.DL_Qte ELSE 0 END) SORTIES,  \n" +
                    "                                SUM(CASE WHEN FDOC.DO_Date BETWEEN @datedeb AND @datefin AND LEFT(DO_Piece,2)<>'MT' AND FDOC.DL_MvtStock = 3 AND NOT(FDOC.DL_TypePL>0 OR FDOC.DL_Qte<0)  THEN FDOC.DL_Qte ELSE 0 END) VENTES, \n" +
                    "                                sum(CASE WHEN FDOC.DO_Date<@datedeb AND DL_MvtStock = 3 AND NOT(DL_TypePL>0 OR FDOC.DL_Qte<0) THEN -FDOC.DL_Qte ELSE 0 END) +sum(CASE WHEN FDOC.DO_Date<@dateDeb AND DL_MvtStock = 1 AND NOT (DL_TypePL>0 OR (FDOC.DL_Qte<0 AND DO_Domaine<>2)) THEN FDOC.DL_Qte ELSE 0 END) STOCKS, \n" +
                    "                       sum(CASE WHEN FDOC.DO_Date<@datefin AND DL_MvtStock = 3 AND NOT(DL_TypePL>0 OR FDOC.DL_Qte<0) THEN -FDOC.DL_Qte ELSE 0 END) +sum(CASE WHEN FDOC.DO_Date<@dateFin AND DL_MvtStock = 1 AND NOT (DL_TypePL>0 OR (FDOC.DL_Qte<0 AND DO_Domaine<>2)) THEN FDOC.DL_Qte ELSE 0 END) STOCK_RESTANTS \n" +
                    "                                 FROM F_DOCLIGNE FDoc \n" +
                    "                LEFT JOIN F_DEPOT FDepSource \n" +
                    "                    ON CASE WHEN isnumeric(FDOC.CT_Num)=1 THEN FDOC.CT_Num ELSE 0 END = FDepSource.DE_No \n" +
                    "                INNER JOIN F_DEPOT fdep \n" +
                    "                    ON fdep.DE_No=fDoc.DE_No \n" +
                    "                INNER JOIN F_ARTICLE fArt  \n" +
                    "                    ON (fArt.cbAR_Ref = fDoc.cbAR_Ref)\n" +
                    "                WHERE ISNULL(NULLIF(DO_Date,{d '1900-01-01'}),fDoc.DO_Date)<= @datefin  \n" +
                    "                AND (@deNo = 0 OR FDEP.DE_No = @deNo) \n" +
                    "                GROUP BY fArt.AR_Design,fArt.AR_Ref\n" +
                    "                ) A) A \n" +
                    "                WHERE A.SORTIES <> 0 OR A.ENTREES <> 0 OR A.STOCK_FINAL <> 0 OR A.QTE_VENDUES <> 0 \n" +
                    "                 ORDER BY AR_Design";

    public static String getetatpreparatoire =
            "declare @date_deb varchar(69)\n" +
                    "            declare @depot int \n" +
                    "\n" +
                    "            set @depot = ?;\n" +
                    "            set @date_deb = ?;\n" +
                    "                \n" +
                    "            SELECT AR_Ref,AR_Design,SUM(Qte) Qte,ROUND(SUM(PR),2) PR,ROUND(MAX(PU),2) PU,MAX(SUIVI) SUIVI\n" +
                    "            FROM(\n" +
                    "            SELECT * FROM (SELECT INVENTAIRE.DE_Intitule, INVENTAIRE.AR_Ref,INVENTAIRE.AR_Design,INVENTAIRE.Qte,INVENTAIRE.PR/INVENTAIRE.Qte PU,INVENTAIRE.PR,INVENTAIRE.AR_SuiviStock,S.SUIVI FROM(\tSELECT \n" +
                    "                    Article.DE_No,fDep.DE_Intitule,Article.IntituleTri,Article.IntituleTri2,fArt.AR_Ref,fArt.AR_Design,fArt.AR_SuiviStock,Article.AG_No1,\n" +
                    "                    Article.AG_No2,Article.Enumere1,Article.Enumere2,Article.AE_Ref,Article.LS_NoSerie,Article.LS_Peremption,Article.LS_Fabrication,\n" +
                    "                    Article.Qte,Article.CMUP,Article.PR,\n" +
                    "                    CASE WHEN 0 = 0 THEN 1 ELSE ISNULL(fCondi.EC_Quantite,1)END EC_Quantite\n" +
                    "            FROM(\n" +
                    "            SELECT sousReqSelLigne.DE_No,'' IntituleTri,'' IntituleTri2,sousReqSelLigne.cbAR_Ref,sousReqSelLigne.AG_No1,sousReqSelLigne.AG_No2,fgam1.EG_Enumere Enumere1,\n" +
                    "                       fgam2.EG_Enumere Enumere2,fArtEnumRef.AE_Ref AE_Ref,LS_NoSerie,LS_Peremption,LS_Fabrication,SUM(Qte) Qte,SUM(CMUP*Sens) CMUP,SUM(PR*Sens) PR\n" +
                    "            FROM(\n" +
                    "                    SELECT fDoc.DE_No,fDoc.cbAR_Ref,fDoc.AG_No1,fDoc.AG_No2,NULL LS_NoSerie,NULL LS_Peremption,NULL LS_Fabrication,\n" +
                    "             ( CASE WHEN DL_MvtStock = 3 THEN\n" +
                    "                                                            CASE WHEN DO_Type = 14 THEN\n" +
                    "                                                                    -fDoc.DL_Qte\n" +
                    "                                                            ELSE\n" +
                    "                                                                    CASE WHEN DL_TypePL>0 OR fDoc.DL_Qte<0 THEN\n" +
                    "                                                                            fDoc.DL_Qte\n" +
                    "                                                                    ELSE\n" +
                    "                                                                            -fDoc.DL_Qte\n" +
                    "                                                                    END\n" +
                    "                                                            END\n" +
                    "                                                    ELSE\n" +
                    "                                                            CASE WHEN DL_MvtStock = 1 THEN\n" +
                    "                                                                    CASE WHEN DO_Type = 27 OR DO_Type = 4 THEN\n" +
                    "                                                                            fDoc.DL_Qte\n" +
                    "                                                                    ELSE\n" +
                    "                                                                            CASE WHEN DL_TypePL>0 OR (fDoc.DL_Qte<0 AND DO_Domaine<>2) THEN\n" +
                    "                                                                                    -fDoc.DL_Qte\n" +
                    "                                                                            ELSE\n" +
                    "                                                                                    fDoc.DL_Qte\n" +
                    "                                                                            END\n" +
                    "                                                                    END\n" +
                    "                                                            ELSE\n" +
                    "                                                                    0\n" +
                    "                                                            END\n" +
                    "                                                    END) Qte,\n" +
                    "                                                    (CASE WHEN DL_MvtStock = 3 OR DL_MvtStock = 1 THEN\n" +
                    "                                                            ROUND(DL_CMUP * ABS(fDoc.DL_Qte),2)\n" +
                    "                                                    ELSE\n" +
                    "                                                            DL_CMUP\n" +
                    "                                                    END) CMUP,\n" +
                    "\n" +
                    "                                                    ( CASE WHEN (DL_MvtStock = 4 OR DL_MvtStock = 2) AND AR_SuiviStock<>2 THEN\n" +
                    "                                                            0\n" +
                    "                                                    ELSE\n" +
                    "                                                            CASE WHEN DL_MvtStock = 3 OR DL_MvtStock = 1 THEN\n" +
                    "                                                                    ROUND(DL_PrixRU * ABS(fDoc.DL_Qte),2)\n" +
                    "                                                            ELSE\n" +
                    "                                                                    DL_PrixRU\n" +
                    "                                                            END\n" +
                    "                                                    END)\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "                                                    PR,\n" +
                    "\n" +
                    "                                            (CASE WHEN DL_MvtStock = 3 THEN\n" +
                    "                                                            -1\n" +
                    "                                                    ELSE\n" +
                    "                                                            CASE WHEN DL_MvtStock = 1 THEN\n" +
                    "                                                                    CASE WHEN DO_Type=27 AND fDoc.DL_Qte<0 THEN\n" +
                    "                                                                            -1\n" +
                    "                                                                    ELSE\n" +
                    "                                                                            1\n" +
                    "                                                                    END\n" +
                    "                                                            ELSE\n" +
                    "                                                                    CASE WHEN DL_MvtStock = 4 THEN\n" +
                    "                                                                    CASE WHEN DO_Domaine = 1 AND (DL_TypePL = 2 OR DL_TypePL=3) THEN\n" +
                    "                                                                                    1\n" +
                    "                                                                            ELSE\n" +
                    "                                                                                    -1\n" +
                    "                                                                            END\n" +
                    "                                                                    ELSE\n" +
                    "                                                                            1\n" +
                    "                                                                    END\n" +
                    "                                                            END\n" +
                    "                                                    END) Sens\n" +
                    "                                    FROM\tF_DOCLIGNE fDoc INNER JOIN F_ARTICLE fArt ON (fArt.cbAR_Ref = fDoc.cbAR_Ref)\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "                                    WHERE\n" +
                    "                                                    fDoc.DL_MvtStock > 0\n" +
                    "                                                    AND\t(fDoc.DO_Type<5 OR fDoc.DO_Type>5)\n" +
                    "                                                    AND ISNULL(NULLIF(DL_DateBL,{d '1900-01-01'}),DO_Date) <= @date_deb\n" +
                    "                                                            AND \t(fArt.AR_SuiviStock>=2 AND fArt.AR_SuiviStock<=4)\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "                                                            AND (0=@depot OR fDoc.DE_No = @depot)\n" +
                    "             UNION all\n" +
                    "                                    SELECT \n" +
                    "                                            fDoc.DE_No,\n" +
                    "                                            fDoc.cbAR_Ref,\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "                                            fDoc.AG_No1,\n" +
                    "                                            fDoc.AG_No2,\n" +
                    "                                            fLot.LS_NoSerie,\n" +
                    "                                            fLot.LS_Peremption,\n" +
                    "                                            fLot.LS_Fabrication,\n" +
                    "             ( CASE WHEN DL_MvtStock = 3 THEN\n" +
                    "                                                            CASE WHEN DO_Type=14 THEN\n" +
                    "                                                                    -fDoc.DL_Qte\n" +
                    "                                                            ELSE\n" +
                    "                                                                    CASE WHEN DL_TypePL>0 OR fDoc.DL_Qte<0 THEN\n" +
                    "                                                                            fDoc.DL_Qte\n" +
                    "                                                                    ELSE\n" +
                    "                                                                            -fDoc.DL_Qte\n" +
                    "                                                                    END\n" +
                    "                                                            END\n" +
                    "                                                    ELSE\n" +
                    "                                                            CASE WHEN DL_MvtStock = 1 THEN\n" +
                    "                                                                    CASE WHEN DO_Type = 27 OR DO_Type = 4 THEN\n" +
                    "                                                                            fDoc.DL_Qte\n" +
                    "                                                                    ELSE\n" +
                    "                                                                            CASE WHEN DL_TypePL>0 OR (fDoc.DL_Qte<0 AND DO_Domaine<>2) THEN\n" +
                    "                                                                                    -fDoc.DL_Qte\n" +
                    "                                                                            ELSE\n" +
                    "                                                                                    fDoc.DL_Qte\n" +
                    "                                                                            END\n" +
                    "                                                                    END\n" +
                    "                                                            ELSE\n" +
                    "                                                                    0\n" +
                    "                                                            END\n" +
                    "                                                    END) Qte,\n" +
                    "                                             (CASE WHEN DL_MvtStock = 3 OR DL_MvtStock = 1 THEN\n" +
                    "                                                            ROUND(DL_CMUP * ABS(fDoc.DL_Qte),2)\n" +
                    "                                                    ELSE\n" +
                    "                                                            DL_CMUP\n" +
                    "                                                    END) CMUP,\n" +
                    "\n" +
                    "                                                    ( CASE WHEN (DL_MvtStock = 4 OR DL_MvtStock = 2) AND AR_SuiviStock<>2 THEN\n" +
                    "                                                            0\n" +
                    "                                                    ELSE\n" +
                    "                                                            CASE WHEN DL_MvtStock = 3 OR DL_MvtStock = 1 THEN\n" +
                    "                                                                    ROUND(DL_PrixRU * ABS(fDoc.DL_Qte),2)\n" +
                    "                                                            ELSE\n" +
                    "                                                                    DL_PrixRU\n" +
                    "                                                            END\n" +
                    "                                                    END)\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "                                                    PR,\n" +
                    "\n" +
                    "                                            (CASE WHEN DL_MvtStock = 3 THEN\n" +
                    "                                                            -1\n" +
                    "                                                    ELSE\n" +
                    "                                                            CASE WHEN DL_MvtStock = 1 THEN\n" +
                    "                                                                    CASE WHEN DO_Type=27 AND fDoc.DL_Qte<0 THEN\n" +
                    "                                                                            -1\n" +
                    "                                                                    ELSE\n" +
                    "                                                                            1\n" +
                    "                                                                    END\n" +
                    "                                                            ELSE\n" +
                    "                                                                    CASE WHEN DL_MvtStock = 4 THEN\n" +
                    "                                                                    CASE WHEN DO_Domaine = 1 AND (DL_TypePL = 2 OR DL_TypePL=3) THEN\n" +
                    "                                                                                    1\n" +
                    "                                                                            ELSE\n" +
                    "                                                                                    -1\n" +
                    "                                                                            END\n" +
                    "                                                                    ELSE\n" +
                    "                                                                            1\n" +
                    "                                                                    END\n" +
                    "                                                            END\n" +
                    "                                                    END) Sens\n" +
                    "\n" +
                    "                                    FROM \tF_DOCLIGNE fDoc INNER JOIN F_ARTICLE fArt ON (fArt.cbAR_Ref = fDoc.cbAR_Ref)\n" +
                    "                                                    INNER JOIN (\tSELECT DL_NoIn DL_No, LS_Peremption, LS_Fabrication, LS_NoSerie FROM F_LOTSERIE WHERE DL_NoOut = 0\n" +
                    "                                                                                    UNION \n" +
                    "                                                                                    SELECT DL_NoOut DL_No, LS_Peremption, LS_Fabrication, LS_NoSerie FROM F_LOTSERIE WHERE DL_NoOut> 0\n" +
                    "                                                                            ) fLot ON (fDoc.DL_No = fLot.DL_No)\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "                                    WHERE\t(fDoc.DL_MvtStock=1 OR fDoc.DL_MvtStock = 3)\n" +
                    "                                                    AND\t(fDoc.DO_Type<5 OR fDoc.DO_Type>5)\n" +
                    "                                                    AND ISNULL(NULLIF(DL_DateBL,{d '1900-01-01'}),DO_Date) <= @date_deb\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "                                                            AND (0=@depot OR fDoc.DE_No = @depot)\n" +
                    "\n" +
                    "            )\n" +
                    "            sousReqSelLigne\t\t\n" +
                    "                                                     LEFT OUTER JOIN F_ARTGAMME fgam1 ON (sousReqSelLigne.AG_No1=fgam1.AG_No AND fgam1.AG_Type = 0)\n" +
                    "                                                    LEFT OUTER JOIN F_ARTGAMME fgam2 ON (sousReqSelLigne.AG_No2 = fgam2.AG_No AND fgam2.AG_Type = 1)\n" +
                    "                                                    LEFT OUTER JOIN F_ARTENUMREF fArtEnumRef ON (sousReqSelLigne.AG_No1 = fArtEnumRef.AG_No1 AND sousReqSelLigne.AG_No2 = fArtEnumRef.AG_No2 AND sousReqSelLigne.cbAR_Ref = fArtEnumRef.cbAR_Ref)\n" +
                    "\n" +
                    "\n" +
                    "                    GROUP BY\n" +
                    "                     sousReqSelLigne.DE_No,\n" +
                    "                     sousReqSelLigne.cbAR_Ref,\n" +
                    "                     sousReqSelLigne.AG_No1,\n" +
                    "                     sousReqSelLigne.AG_No2,\n" +
                    "                     fgam1.EG_Enumere,\n" +
                    "                     fgam2.EG_Enumere,\n" +
                    "                     fArtEnumRef.AE_Ref,\n" +
                    "                     LS_NoSerie,\n" +
                    "                     LS_Peremption,\n" +
                    "                     LS_Fabrication\n" +
                    "                            HAVING SUM(Qte)>0)\tArticle INNER JOIN F_ARTICLE fArt ON (Article.cbAR_Ref = fArt.cbAR_Ref)\n" +
                    "                                                                            INNER JOIN F_DEPOT fDep ON (Article.DE_No = fDep.DE_No)\n" +
                    "                                                                    LEFT OUTER JOIN F_CONDITION fCondi ON (fArt.CO_No = fCondi.CO_No))INVENTAIRE\n" +
                    "\n" +
                    "             LEFT JOIN (SELECT S.CODE,S.SUIVI  FROM(SELECT 0 AS CODE, 'AUCUN' AS SUIVI  FROM F_ARTICLE\n" +
                    "                    UNION \n" +
                    "                    SELECT 1 AS CODE, 'SERIALISE' AS SUIVI FROM F_ARTICLE\n" +
                    "                    UNION \n" +
                    "                    SELECT 2 AS CODE, 'CUMP' AS SUIVI FROM F_ARTICLE\n" +
                    "                    UNION \n" +
                    "                    SELECT 3 AS CODE, 'FIFO' AS SUIVI FROM F_ARTICLE\n" +
                    "                    UNION \n" +
                    "                    SELECT 4 AS CODE, 'LIFO' AS SUIVI FROM F_ARTICLE\n" +
                    "                    UNION \n" +
                    "                    SELECT 5 AS CODE, 'PAR LOT' AS SUIVI FROM F_ARTICLE)S)S ON S.CODE = INVENTAIRE.AR_SuiviStock\n" +
                    "\n" +
                    "                    )A\n" +
                    "                    )A\n" +
                    "                    GROUP BY AR_Ref,AR_Design";

    public static String getPreparatoireCumul =
            "DECLARE @MAXREPL int,@deNo int,@P1 smallint,@P2 int,@P3 int,@P4 int,@P5 float,@P6 int;\n" +
                    "\tSET @MAXREPL =  (select ISNULL(MAX(DE_Replication),0) from F_DEPOT);\n" +
                    "\tSET @deNo = ?;\n"+
                    "\tSET @P1=0;\n" +
                    "\tSET @P2=@deNo;\n" +
                    "\tSET @P3=@deNo;\n" +
                    "\tSET @P4=@deNo;\n" +
                    "\tSET @P5=2\n" +
                    "\tSET @P6=@deNo;\n" +
                    "            \n" +
                    "SELECT AR_Ref,AR_Design, SUM(Qte)Qte,SUM(PR) PR\n" +
                    "FROM(\n" +
                    "SELECT\n" +
                    "\n" +
                    "\n" +
                    "\tReqGlobal.DE_No,\n" +
                    "\tfDep.DE_Intitule DE_Intitule,\n" +
                    "\tReqGlobal.IntituleTri,\n" +
                    "\tReqGlobal.IntituleTri2,\n" +
                    "\tfArt.AR_Ref AR_Ref,\n" +
                    "\tfArt.AR_Design AR_Design,\n" +
                    "\tfArt.AR_SuiviStock AR_SuiviStock,\n" +
                    "\tReqGlobal.AG_No1,\n" +
                    "\tReqGlobal.AG_No2,\n" +
                    "\tReqGlobal.Enumere1,\n" +
                    "\tReqGlobal.Enumere2,\n" +
                    "\tReqGlobal.AE_Ref,\n" +
                    "\tReqGlobal.LS_NoSerie,\n" +
                    "\tReqGlobal.LS_Peremption,\n" +
                    "\tReqGlobal.LS_Fabrication,\n" +
                    "\tReqGlobal.PR,\n" +
                    "\tReqGlobal.Qte,\n" +
                    "\tCASE WHEN @P1 = 0 THEN 1 ELSE ISNULL(fCondi.EC_Quantite,1)END EC_Quantite\n" +
                    "FROM\n" +
                    "(SELECT\n" +
                    "\tDE_No,\n" +
                    "\tIntituleTri,\n" +
                    "\tIntituleTri2,\n" +
                    "\tcbAR_Ref,\n" +
                    "\tAG_No1,\n" +
                    "\tAG_No2,\n" +
                    "\tEnumere1,\n" +
                    "\tEnumere2,\n" +
                    "\tAE_Ref,\n" +
                    "\tLS_NoSerie,\n" +
                    "\tLS_Peremption,\n" +
                    "\tLS_Fabrication,\n" +
                    "\tSUM(PR) PR,\n" +
                    "\tSUM(Qte) Qte\n" +
                    "FROM\n" +
                    "((\n" +
                    "SELECT\n" +
                    "\n" +
                    "\n" +
                    "\tfDepot.DE_No DE_No,\n" +
                    "\n" +
                    "\tfArt.cbAR_Ref cbAR_Ref,\n" +
                    "\t0\tAG_No1,\n" +
                    "\t0\tAG_No2,\n" +
                    "\t''''\tEnumere1,\n" +
                    "\t''''\tEnumere2,\n" +
                    "\t''''\tAE_Ref,\n" +
                    "\t''''\tLS_NoSerie,\n" +
                    "\tNULL\tLS_Peremption,\n" +
                    "\tNULL\tLS_Fabrication,\n" +
                    "\t\tAS_MontSto\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    " PR,\n" +
                    "\tAS_QteSto\tQte\n" +
                    "\n" +
                    ",''''\tIntituleTri\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    " ,'''' IntituleTri2\n" +
                    "FROM\n" +
                    "\t\tF_ARTICLE fArt INNER JOIN F_ARTSTOCK fSto ON (fArt.cbAR_Ref = fSto.cbAR_Ref)\n" +
                    "\t\tINNER JOIN F_DEPOT fDepot ON (fDepot.DE_No = fSto.DE_No)\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    " \t\t\n" +
                    "\t\t\n" +
                    "WHERE\tfSto.AS_QteSto>0\n" +
                    "\t\t AND AR_SuiviStock>=2 AND AR_SuiviStock<=4\n" +
                    "\t\t AND AR_Gamme1=0\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t AND (('0'=@P2) OR (fDepot.DE_No = @P2))\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    " UNION\n" +
                    "\tSELECT DE_No,ReqGamme.cbAR_Ref cbAR_Ref,ReqGamme.AG_No1 AG_No1,ReqGamme.AG_No2 AG_No2,Enumere1,\n" +
                    "\tEnumere2,fArtEnumRef.AE_Ref AE_Ref,LS_NoSerie,LS_Peremption,LS_Fabrication,PR,Qte,IntituleTri,IntituleTri2\n" +
                    "\n" +
                    "\tFROM \n" +
                    "\t(SELECT\n" +
                    "\t\t\n" +
                    "\t\tfDepot.DE_No DE_No,\n" +
                    "\t\tfArt.cbAR_Ref cbAR_Ref,fGam.AG_No AG_No1,0 AG_No2,fGam.EG_Enumere Enumere1,'''' Enumere2,\n" +
                    "\t\t'''' LS_NoSerie,NULL LS_Peremption,NULL LS_Fabrication,\n" +
                    "\t\tGS_MontSto\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "  PR,\n" +
                    "\t\t\tGS_QteSto Qte \n" +
                    "\t\t,'''' IntituleTri\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t ,'''' IntituleTri2\n" +
                    "\tFROM\n" +
                    "\t\tF_ARTICLE fArt INNER JOIN F_ARTGAMME fGam ON (fArt.cbAR_Ref = fGam.cbAR_Ref)\n" +
                    "\t\tINNER JOIN F_GAMSTOCK fGamSto ON (fGam.AG_No = fGamSto.AG_No1)\n" +
                    "\t\tINNER JOIN F_DEPOT fDepot ON (fDepot.DE_No = fGamSto.DE_No)\n" +
                    "\n" +
                    "\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\tWHERE fArt.cbAR_Ref = fGamSto.cbAR_Ref\n" +
                    "\t\tAND AG_Type=0 \n" +
                    "\t\tAND AR_SuiviStock>0 AND AR_Gamme1>0 AND AR_Gamme2=0 AND fGamSto.GS_QteSto>0\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t AND ('0'=@P3) OR (fDepot.DE_No = @P3)\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\n" +
                    "UNION\n" +
                    "\tSELECT\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\tfDepot.DE_No DE_No,\n" +
                    "\t\tfArt.cbAR_Ref cbAR_Ref,\n" +
                    "\t\tfGam1.AG_No AG_No1,\n" +
                    "\t\tfGam2.AG_No AG_No2,\n" +
                    "\t\tfGam1.EG_Enumere Enumere1,\n" +
                    "\t\tfGam2.EG_Enumere Enumere2,\n" +
                    "\t\t'''' LS_NoSerie,\n" +
                    "\t\tNULL LS_Peremption,\n" +
                    "\t\tNULL LS_Fabrication,\n" +
                    "\t\tGS_MontSto\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "  PR,\n" +
                    "\t\tGS_QteSto Qte \n" +
                    "\t,'''' IntituleTri\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t ,'''' IntituleTri2\n" +
                    "\tFROM\n" +
                    "\t\t\tF_ARTICLE fArt\n" +
                    "\t\t\tINNER JOIN F_GAMSTOCK fGamSto ON (fArt.cbAR_Ref = fGamSto.cbAR_Ref)\n" +
                    "\t\t\tINNER JOIN F_DEPOT fDepot ON (fDepot.DE_No = fGamSto.DE_No)\n" +
                    "\t\t\tINNER JOIN F_ARTGAMME fGam1 ON (fArt.cbAR_Ref = fGam1.cbAR_Ref AND fGam1.AG_No = fGamSto.AG_No1)\n" +
                    "\t\t\tINNER JOIN F_ARTGAMME fGam2 ON (fArt.cbAR_Ref = fGam2.cbAR_Ref AND fGam2.AG_No = fGamSto.AG_No2)\n" +
                    "\n" +
                    "\n" +
                    "\t\t\t\n" +
                    "\t\t\t\n" +
                    "\t\t\t\n" +
                    "\t\t\t\n" +
                    "\t\t\t\n" +
                    "\tWHERE\n" +
                    "\t\tfGam1.AG_Type=0 AND fGam2.AG_Type=1\n" +
                    "\t\tAND\tAR_SuiviStock>0 \n" +
                    "\t\tAND AR_Gamme2>0\n" +
                    "\t\tAND fGamSto.GS_QteSto>0\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t AND ('0'=@P4) OR (fDepot.DE_No = @P4)\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t) ReqGamme INNER JOIN F_ARTENUMREF fArtEnumRef ON (fArtEnumRef.cbAR_Ref = ReqGamme.cbAR_Ref AND fArtEnumRef.AG_No1 = ReqGamme.AG_No1 AND fArtEnumRef.AG_No2 = ReqGamme.AG_No2)\n" +
                    ")\n" +
                    " UNION all\n" +
                    "\tSELECT\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\tfDepot.DE_No DE_No,\n" +
                    "\t\n" +
                    "\t\tfArt.cbAR_Ref cbAR_Ref,\n" +
                    "\t\t0 AG_No1,\n" +
                    "\t\t0 AG_No2,\n" +
                    "\t\t'''' Enumere1,\n" +
                    "\t\t'''' Enumere2,\n" +
                    "\t\t'''' AE_Ref,\n" +
                    "\t\tLS_NoSerie LS_NoSerie,\n" +
                    "\t\tLS_Peremption LS_Peremption,\n" +
                    "\t\tLS_Fabrication LS_Fabrication,\n" +
                    "\t\t(ROUND(\n" +
                    "\t\tDL_PrixRU * ABS(LS_QteRestant)\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    " \n" +
                    "\t\t,@P5)) PR,\n" +
                    "\t\t( CASE WHEN DO_Type = 4 THEN\n" +
                    "\t\t\t\tLS_QteRestant\n" +
                    "\t\t\tELSE\n" +
                    "\t\t\t\tCASE WHEN DL_TypePL>0 OR (fLig.DL_Qte<0 AND DO_Domaine<>2) THEN\n" +
                    "\t\t\t\t\tCASE WHEN fLig.DL_Qte>0 THEN\n" +
                    "\t\t\t\t\t\t-LS_QteRestant\n" +
                    "\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\tLS_QteRestant\n" +
                    "\t\t\t\t\tEND\n" +
                    "\t\t\t\tELSE\n" +
                    "\t\t\t\t\tCASE WHEN fLig.DL_Qte>0 THEN\n" +
                    "\t\t\t\t\t\tLS_QteRestant\n" +
                    "\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t-LS_QteRestant\n" +
                    "\t\t\t\t\tEND\n" +
                    "\t\t\t\tEND\n" +
                    "\t\t\tEND) Qte\n" +
                    "\t,'''' IntituleTri\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t\n" +
                    "\t ,'''' IntituleTri2\n" +
                    "\tFROM\n" +
                    "\t\tF_ARTICLE fArt INNER JOIN F_LOTSERIE fLot ON (fArt.cbAR_Ref = fLot.cbAR_Ref)\n" +
                    "\t\tINNER JOIN F_DEPOT fDepot ON (fDepot.DE_No = fLot.DE_No)\n" +
                    "\t\tINNER JOIN F_DOCLIGNE fLig ON (fLig.DL_No = fLot.DL_NoIn)\n" +
                    "\t\tINNER JOIN F_ARTSTOCK fSto ON (fArt.cbAR_Ref = fSto.cbAR_Ref AND fDepot.DE_No = fSto.DE_No)\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\tWHERE LS_MvtStock = 1\n" +
                    "\t\tAND LS_LotEpuise=0\n" +
                    "\t\tAND DL_MvtStock=1\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t\t AND ('0'=@P6) OR (fDepot.DE_No = @P6)\n" +
                    "\t\t\n" +
                    "\t\t\n" +
                    "\t) Req\n" +
                    "\tGROUP BY\n" +
                    "\t\tDE_No,IntituleTri,IntituleTri2,cbAR_Ref,AG_No1,AG_No2,Enumere1,Enumere2,AE_Ref,LS_NoSerie,LS_Peremption,LS_Fabrication\n" +
                    "\t) ReqGlobal\t\tINNER JOIN F_ARTICLE fArt ON (fArt.cbAR_Ref = ReqGlobal.cbAR_Ref)\n" +
                    "\t\t\t\t\t INNER JOIN F_DEPOT fDep ON (fDep.DE_No = ReqGlobal.DE_No)\n" +
                    "\t\t\t\t\tLEFT OUTER JOIN F_CONDITION fCondi ON (fArt.CO_No = fCondi.CO_No)\n" +
                    "\t)A\n" +
                    "        GROUP BY AR_Ref,AR_Design";

    public static String etatCaisse =
            "declare @dt_deb as varchar(20)\n" +
                    "                declare @dt_fin as varchar(20)\n" +
                    "                declare @ct_num as varchar(30)\n" +
                    "                declare @ca_no as int\n" +
                    "                declare @mode_reglement as int\n" +
                    "                declare @type_reglement as int\n" +
                    "                declare @premierFondDeCaisse as int\n" +
                    "                set @dt_deb=?\n" +
                    "                set @dt_fin=?\n" +
                    "                set @ca_no=?\n" +
                    "                set @mode_reglement=?\n" +
                    "                set @type_reglement=?\n" +
                    "DECLARE @admin INT\n" +
                    "DECLARE @ProtNo INT\n" +
                    "SET @ProtNo = ?\n" +
                    "\n" +
                    "CREATE TABLE #TMPCAISSE (CA_No INT)\n" +
                    "\n" +
                    "CREATE TABLE #TMPFondCaisse (CA_No INT,cbMarq INT)\n" +
                    "\n" +
                    "IF (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE Prot_No=@ProtNo) = 1 \n" +
                    "                    BEGIN \n" +
                    "\t\t\t\t\tINSERT INTO #TMPCAISSE\n" +
                    "\t\t\t\t\tSELECT\tISNULL(CA.CA_No,0) CA_No \n" +
                    "                    FROM F_CAISSE CA\n" +
                    "                    INNER JOIN Z_DEPOTCAISSE C \n" +
                    "\t\t\t\t\t\tON CA.CA_No=C.CA_No\n" +
                    "                    INNER JOIN F_DEPOT D \n" +
                    "\t\t\t\t\t\tON C.DE_No=D.DE_No\n" +
                    "                    INNER JOIN F_COMPTET CT \n" +
                    "\t\t\t\t\t\tON CT.cbCT_Num = CA.cbCT_Num\n" +
                    "\t\t\t\t\tWHERE (@ca_no = 0 OR CA.CA_No = @ca_no) \n" +
                    "\t\t\t\t\tGROUP BY CA.CA_No\n" +
                    "                    END \n" +
                    "\t\t\t\t\tELSE \n" +
                    "                    BEGIN \n" +
                    "\t\t\t\t\tINSERT INTO #TMPCAISSE\n" +
                    "\t\t\t\t\tSELECT\tISNULL(CA.CA_No,0) CA_No\n" +
                    "                    FROM F_CAISSE CA\n" +
                    "                    LEFT JOIN Z_DEPOTCAISSE C \n" +
                    "\t\t\t\t\t\tON CA.CA_No=C.CA_No\n" +
                    "                    LEFT JOIN (\tSELECT * \n" +
                    "                                FROM Z_DEPOTUSER\n" +
                    "                                WHERE IsPrincipal=1) D \n" +
                    "\t\t\t\t\t\tON C.DE_No=D.DE_No\n" +
                    "                    LEFT JOIN F_COMPTET CT ON CT.CT_Num = CA.CT_Num\n" +
                    "                    WHERE Prot_No=@ProtNo\n" +
                    "                    AND (@ca_no = 0 OR CA.CA_No = @ca_no) \n" +
                    "\t\t\t\t\tGROUP BY CA.CA_No\n" +
                    "\t\t\t\t\tEND;\n" +
                    "\n" +
                    "\n" +
                    "INSERT INTO #TMPFondCaisse \n" +
                    "SELECT\tCA_No\n" +
                    "\t\t,cbMarq = MIN(cbMarq) \n" +
                    "FROM\t F_CREGLEMENT \n" +
                    "WHERE\tRG_DATE BETWEEN '2020-01-01' \n" +
                    "AND\t\t'2020-01-03'           \n" +
                    "AND\t\t((0=@ca_no AND CA_No IN (SELECT CA_No FROM #TMPCAISSE)) OR CA_NO = @ca_no)\n" +
                    "AND\t\tRG_TypeReg=2\n" +
                    "GROUP BY CA_No;\n" +
                    "\t\t\t\n" +
                    "with _ReglEch_ AS (\n" +
                    "SELECT\tRG_No,SUM(RC_Montant) RC_Montant\n" +
                    "FROM\tF_REGLECH\n" +
                    "GROUP BY RG_No\n" +
                    ")\n" +
                    ", _Reglement_ AS (\n" +
                    "SELECT\tRG_TypeReg,C.CA_No,RG_Date,RG_Libelle,N_Reglement,RG_HEURE\n" +
                    "\t\t,RG_No =CASE WHEN RG_TypeReg = 2 THEN 0 ELSE RG_No END \n" +
                    "\t\t,RG_Type = CASE WHEN RG_TypeReg = 2 THEN 0 ELSE RG_Type END\n" +
                    "\t\t,FOND_CAISSE = CASE WHEN RG_TypeReg=2 THEN RG_MONTANT ELSE 0 END\n" +
                    "\t\t,RG_MONTANT = CASE WHEN RG_TYPEREG = 4 THEN -RG_MONTANT ELSE RG_MONTANT END\n" +
                    "\t\t,DEBIT = CASE WHEN (RG_Type = 0 AND RG_Montant<0) OR (RG_BANQUE =3 AND RG_TypeReg=0) OR (RG_BANQUE <> 3 AND ((RG_Type=1 AND RG_Montant>0)OR RG_TypeReg = 4))  OR RG_TypeReg = 3 OR (RG_TypeReg = 5 AND RG_Banque=1) THEN ABS(RG_Montant) ELSE 0 END \n" +
                    "        ,CREDIT = CASE WHEN RG_Type = 3 OR (RG_Type = 0  AND RG_TypeReg<>4 AND RG_Montant>=0) OR (RG_Type = 1 AND RG_Montant<0) OR (RG_BANQUE =3 AND RG_TypeReg=4) OR (RG_TypeReg = 5 AND RG_Banque<>1) THEN ABS(RG_MONTANT) ELSE 0 END \n" +
                    "\t\t,RG_BANQUE,CT_NUMPAYEUR,cbCT_NumPayeur,C.cbMarq\n" +
                    "FROM F_CREGLEMENT C\n" +
                    "LEFT JOIN #TMPFondCaisse T\n" +
                    "\tON C.cbMarq = T.cbMarq\n" +
                    "WHERE ((0=@ca_no AND C.CA_No IN (SELECT CA_No FROM #TMPCAISSE)) OR C.CA_NO = @ca_no)\n" +
                    "                AND (0=@mode_reglement OR N_Reglement=@mode_reglement)\n" +
                    "                AND (((-1=@type_reglement  AND N_Reglement<>8 AND ((RG_TypeReg=5 AND RG_Banque IN (0,1,3)) OR RG_TypeReg<>5) )\n" +
                    "                        OR (RG_TypeReg=@type_reglement AND @type_reglement NOT IN (6,4))\n" +
                    "                        OR (RG_TypeReg=4 AND RG_Banque = 1 AND @type_reglement=6)\n" +
                    "                        OR (RG_TypeReg=4 AND RG_Banque = 0 AND @type_reglement=4))\n" +
                    "                        OR (5=@type_reglement AND RG_TypeReg=5 AND RG_Banque=0) \n" +
                    "\t\t\t\t\t\t\tOR (@type_reglement NOT IN(-1,5) AND RG_TypeReg=@type_reglement))\n" +
                    "\t\t\t    AND ((rg_typereg=2 AND  C.cbMarq IN(\tSELECT cbMarq FROM(SELECT CA_No,min(cbMarq) cbMarq\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tFROM \tF_CREGLEMENT\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE \tRG_Date between @dt_deb AND @dt_fin\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND \tC.CA_No IN (SELECT CA_No FROM #TMPCAISSE)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND \tRG_TypeReg=2\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tGROUP BY CA_No)A)) OR rg_typereg<>2)\n" +
                    "\t\t\tAND\t  T.cbMarq IS NULL\n" +
                    ")\n" +
                    ",_ReglementPeriod_ AS (\n" +
                    "SELECT R.*\n" +
                    "FROM _Reglement_ R\n" +
                    "WHERE RG_DATE BETWEEN @dt_deb AND @dt_fin  \n" +
                    ")\n" +
                    ",_ReglementFondDeCaisse_ AS (\n" +
                    "SELECT *\n" +
                    "FROM _Reglement_ \n" +
                    "WHERE RG_DATE BETWEEN '2020-01-01' AND DATEADD(D,-1,@dt_deb)           \n" +
                    ")\n" +
                    ",_PremierReglementFondDeCaisse_ AS (\n" +
                    "SELECT\tR.CA_No \n" +
                    "\t\t,FOND_CAISSE = R.RG_Montant\n" +
                    "FROM\tF_CREGLEMENT R\n" +
                    "INNER JOIN #TMPFondCaisse M\n" +
                    "ON\tR.cbMarq = M.cbMarq\n" +
                    "AND R.CA_No = M.CA_No\n" +
                    ")\n" +
                    "\n" +
                    ",cte as(\n" +
                    "                SELECT\t\tLigne = 1,pfc.CA_No,CA_Intitule\n" +
                    "\t\t\t\t\t\t\t,RG_No = 0 \n" +
                    "\t\t\t\t\t\t\t,RG_Date = null \n" +
                    "\t\t\t\t\t\t\t,RG_Libelle = CONCAT('Report de solde au ',RIGHT(CONCAT('0',DAY(@dt_deb)),2),'/',RIGHT(CONCAT('0',MONTH(@dt_deb)),2),'/',YEAR(@dt_deb))\n" +
                    "\t\t\t\t\t\t\t,R_Intitule = 'Report de caisse' \n" +
                    "\t\t\t\t\t\t\t,N_Reglement = 1 \n" +
                    "\t\t\t\t\t\t\t,RG_Type = 0\n" +
                    "\t\t\t\t\t\t\t,RG_TypeReg = 0\n" +
                    "\t\t\t\t\t\t\t,FOND_CAISSE = MAX(pfc.FOND_CAISSE)\n" +
                    "\t\t\t\t\t\t\t,DEBIT = NULL\n" +
                    "\t\t\t\t\t\t\t,CREDIT = NULL \n" +
                    "\t\t\t\t\t\t\t,CT_Intitule = '' \n" +
                    "\t\t\t\t\t\t\t,CUMUL = MAX(pfc.FOND_CAISSE) + ISNULL(SUM(CASE WHEN rfc.N_Reglement IN (1,10) THEN rfc.CREDIT ELSE 0 END),0)-ISNULL(SUM(abs(CASE WHEN rfc.N_Reglement in(5,2) THEN 0 ELSE rfc.DEBIT END)),0)\n" +
                    "\t\t\t\tFROM _PremierReglementFondDeCaisse_ pfc\n" +
                    "\t\t\t\tLEFT JOIN F_CAISSE fca\n" +
                    "\t\t\t\t\tON fca.CA_No = pfc.CA_No\n" +
                    "\t\t\t\tLEFT JOIN _ReglementFondDeCaisse_ rfc\n" +
                    "\t\t\t\t\tON pfc.CA_No = rfc.CA_No\n" +
                    "\n" +
                    "\t\t\t\tGROUP BY pfc.CA_No,CA_Intitule\n" +
                    "\t\t\t\tUNION\n" +
                    "\t\t\t\tselect\tLigne = ROW_NUMBER() OVER(ORDER BY CA_No,RG_Date,RG_Type,RG_No)+1 \n" +
                    "\t\t\t\t\t\t,*\n" +
                    "\t\t\t\t\t\t,CUMUL = CREDIT-abs(CASE WHEN N_Reglement IN(5,2) THEN 0 ELSE DEBIT END) \n" +
                    "                FROM (\n" +
                    "                    \n" +
                    "                    SELECT  CA_No,CA_Intitule\n" +
                    "\t\t\t\t\t\t\t,RG_No,RG_Date,RG_Libelle,R_Intitule,N_Reglement\n" +
                    "\t\t\t\t\t\t\t,RG_Type,RG_TypeReg\n" +
                    "                            ,FOND_CAISSE\n" +
                    "                            ,DEBIT \n" +
                    "                            ,CREDIT\n" +
                    "                            ,CT_Intitule\n" +
                    "                \n" +
                    "                    FROM (\n" +
                    "                    select FOND_CAISSE,LEFT(T.CT_Intitule,10) CT_Intitule,RC_Montant, C.RG_No,RG_BANQUE,CT_Type, C.CT_NUMPAYEUR\n" +
                    "\t\t\t\t\t\t\t,RG_DATE,RG_LIBELLE,N_Reglement,ISNULL(R.R_Intitule,'') R_Intitule,RG_TYPE,RG_TYPEREG\n" +
                    "\t\t\t\t\t\t\t,RG_HEURE,C.CA_NO,CA_Intitule,RG_MONTANT,DEBIT ,CREDIT\n" +
                    "                from _ReglementPeriod_ C \n" +
                    "\t\t\t\tINNER JOIN F_Caisse Ca \n" +
                    "\t\t\t\t\tON Ca.CA_No = C.CA_No\n" +
                    "                LEFT JOIN _ReglEch_ RE \n" +
                    "\t\t\t\t\tON RE.RG_No=C.RG_No\n" +
                    "                LEFT JOIN P_REGLEMENT R \n" +
                    "\t\t\t\t\tON R.cbIndice = C.N_Reglement\n" +
                    "                LEFT JOIN F_COMPTET T \n" +
                    "\t\t\t\t\tON T.cbCT_Num = C.cbCT_NumPayeur\n" +
                    "                \n" +
                    "\t\t\t    )A)A)\n" +
                    "\n" +
                    "\t\n" +
                    " SELECT \tT1.ligne\n" +
                    "\t\t\t\t\t\t,T1.CT_Intitule\n" +
                    "\t\t\t\t\t\t,T1.CA_No\n" +
                    "\t\t\t\t\t\t,T1.CA_Intitule\n" +
                    "\t\t\t\t\t\t,RG_Date = CONCAT(CONCAT(RIGHT('00'+CAST(DAY(T1.RG_Date) AS VARCHAR(2)),2)\n" +
                    "\t\t\t\t\t\t,RIGHT('00'+CAST(MONTH(T1.RG_Date) AS VARCHAR(2)),2)),RIGHT(CAST(YEAR(T1.RG_Date) AS VARCHAR(4)),2)) \n" +
                    "\t\t\t\t\t\t,RG_DateInit = T1.RG_Date\n" +
                    "\t\t\t\t\t\t,T1.RG_Libelle\n" +
                    "\t\t\t\t\t\t,R_Intitule = LEFT(T1.R_Intitule,3) \n" +
                    "\t\t\t\t\t\t,T1.N_Reglement,T1.RG_TypeReg\n" +
                    "\t\t\t\t\t\t,FOND_CAISSE = ISNULL(T1.FOND_CAISSE,0),CREDIT = ISNULL(T1.CREDIT,0)\n" +
                    "\t\t\t\t\t\t,DEBIT = ISNULL(ABS(T1.DEBIT),0)\n" +
                    "\t\t\t\t\t\t,CUMUL = ISNULL(SUM(CASE WHEN T2.N_Reglement IN (1,10) THEN T2.cumul ELSE 0 END),0) \n" +
                    "\t\t\t\t\t\t,CumulGeneral = ISNULL(CASE WHEN T1.Ligne = MAX(T1.ligne) OVER (PARTITION BY T1.CA_No) THEN \n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tSUM(CASE WHEN T2.N_Reglement IN (1,10) THEN T2.cumul ELSE 0 END) ELSE 0 END,0)\n" +
                    "                FROM CTE T1\n" +
                    "                INNER JOIN CTE T2 \n" +
                    "\t\t\t\t\tON\tT1.ligne>=T2.ligne  \n" +
                    "\t\t\t\t\tAND T1.CA_No=T2.CA_No\n" +
                    "                GROUP BY T1.ligne,T1.CT_Intitule,T1.CA_No,T1.CA_Intitule,T1.RG_Date,T1.RG_Libelle,T1.R_Intitule,T1.N_Reglement,T1.RG_TypeReg,T1.FOND_CAISSE,T1.CREDIT,T1.DEBIT\n" +
                    "                ORDER BY T1.RG_Date,T1.ligne";

    public static String statClientParAgence =
            "DECLARE @DO_Type AS INT\n" +
                    "DECLARE @DateDebut AS VARCHAR(50)\n" +
                    "DECLARE @DateFin AS VARCHAR(50)\n" +
                    "DECLARE @Depot AS INT\n" +
                    "DECLARE @rupture AS INT\n" +
                    "\n" +
                    "SET @Depot = ?\n" +
                    "SET @DateDebut = ?\n" +
                    "SET @DateFin = ?\n" +
                    "SET @DO_Type = ?\n" +
                    "SET @rupture = 0\n" +
                    "\n" +
                    "CREATE TABLE #TMPDEPOT (DE_No INT)\n" +
                    "DECLARE @admin INT\n" +
                    "DECLARE @ProtNo INT\n" +
                    "SET @ProtNo = ?\n" +
                    "\n" +
                    "SELECT @admin = CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No = @ProtNo               \n" +
                    "IF (@admin=0)\n" +
                    "BEGIN \n" +
                    "\tINSERT INTO #TMPDEPOT\n" +
                    "    SELECT\tA.DE_No \n" +
                    "    FROM\tF_DEPOT A\n" +
                    "    LEFT JOIN Z_DEPOTUSER D \n" +
                    "\t\tON A.DE_No=D.DE_No\n" +
                    "    WHERE\t(1 = (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No=@ProtNo) OR D.PROT_No =@ProtNo)\n" +
                    "    AND IsPrincipal = 1\n" +
                    "\tAND (@Depot = 0 OR A.DE_No = @Depot) \n" +
                    "    GROUP BY A.DE_No\n" +
                    "END\n" +
                    "ELSE \n" +
                    "BEGIN\n" +
                    "\tINSERT\tINTO #TMPDEPOT \n" +
                    "    SELECT DE_No \n" +
                    "    FROM F_DEPOT \n" +
                    "\tWHERE (@Depot = 0 OR DE_No = @Depot) \n" +
                    "END \n" +
                    "\n" +
                    "SELECT  CASE WHEN @rupture=0 THEN 0 ELSE fdep.DE_No END DE_No\n" +
                    "\t\t,CASE WHEN @rupture=0 THEN '0' ELSE fdep.DE_Intitule END DE_Intitule\n" +
                    "\t\t,CT_Intitule,\n" +
                    "\t\tTotNbDoc,\n" +
                    "\t\tTotCAHTNet,TotCATTCNet-TotCAHTNet as PRECOMPTE,TotCATTCNet,TotQteVendues,TotMarge,TotCAHTBrut,TotCATTCBrut\n" +
                    "\tFROM\n" +
                    "\t\t(SELECT DE_No\n" +
                    "\t\t\t\t,NumTiers\n" +
                    "\t\t\t\t,SUM(CAHTNet) TotCAHTNet,SUM(CATTCNet) TotCATTCNet,SUM(QteVendues) TotQteVendues,SUM(CATTCNet*DL_Taxe1/100) PRECOMPTE,\n" +
                    "\t\t\t\t\tSUM(CAHTNet)-SUM(PrxRevientU) TotMarge,\n" +
                    "\t\t\t\tSUM(CASE WHEN (DO_Type=4 OR DO_Type=14)  \n" +
                    "\t\t\t\t\t\t\t\tTHEN -CAHTBrut  \n" +
                    "\t\t\t\t\t\t\t\tELSE CAHTBrut \n" +
                    "\t\t\t\t\t\t\t\tEND) TotCAHTBrut,\n" +
                    "\t\t\t\tSUM(CASE WHEN (DO_Type=4 OR DO_Type=14)  \n" +
                    "\t\t\t\t\t\t\t\tTHEN -CATTCBrut  \n" +
                    "\t\t\t\t\t\t\t\tELSE CATTCBrut \n" +
                    "\t\t\t\t\t\t\t\tEND) TotCATTCBrut\n" +
                    "\t,COUNT (DISTINCT fr.DO_Piece) TotNbDoc\n" +
                    "\t\t\t\tFROM (SELECT fdoc.DE_No,DO_Piece,DO_Type,DL_Taxe1,\n" +
                    "\t\t\t\tfDoc.cbCT_Num NumTiers,\n" +
                    "\t\t\t\t\t(CASE WHEN ((fDoc.DO_Type>=4 AND fDoc.DO_Type<=5) OR (fDoc.DO_Type>=14 AND \n" +
                    "\t\t\t\t\t\tfDoc.DO_Type<=15)) \n" +
                    "\t\t\t\t\t\t\t\tTHEN -DL_MontantHT \n" +
                    "\t\t\t\t\t\t\t\tELSE DL_MontantHT\n" +
                    "\t\t\t\t\t\t\t\tEND) CAHTNet,\n" +
                    "\t\t\t\t\t(\tCASE WHEN ((fDoc.DO_Type>=4 AND fDoc.DO_Type<=5) OR (fDoc.DO_Type>=14 AND \n" +
                    "\t\t\t\t\t\t\t\tfDoc.DO_Type<=15)) \n" +
                    "\t\t\t\t\t\t\t\tTHEN -DL_MontantTTC \n" +
                    "\t\t\t\t\t\t\t\tELSE DL_MontantTTC\n" +
                    "\t\t\t\t\t\t\t\tEND) CATTCNet,\n" +
                    "\t\t\t\t\tROUND((CASE WHEN fDoc.cbAR_Ref =convert(varbinary(255),AR_RefCompose) THEN\n" +
                    "\t\t\t\t\t\t\t\t(select SUM(toto)\n" +
                    "\t\t\t\t\t\t\t\t\t\tfrom (SELECT  \n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN fDoc2.DL_TRemPied = 0 AND fDoc2.DL_TRemExep = 0 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN (fDoc2.DL_FactPoids = 0 OR fArt2.AR_SuiviStock > 0) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN fDoc2.DO_Type <= 2 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_Qte * fDoc2.DL_CMUP\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN (\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTHEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * (-fDoc2.DL_Qte)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * fDoc2.DL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tELSE CASE WHEN (fDoc2.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * (-fDoc2.DL_PoidsNet) / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * fDoc2.DL_PoidsNet / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tELSE 0\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    " toto \n" +
                    "\t\t\t\t\t\t\t\t\t\t\tFROM F_DOCLIGNE fDoc2 INNER JOIN F_ARTICLE fArt2 ON (fDoc2.cbAR_Ref = fArt2.cbAR_Ref)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tWHERE fDoc.cbAR_Ref = convert(varbinary(255),fDoc2.AR_RefCompose)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc2.DL_Valorise<>fDoc.DL_Valorise\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc2.cbDO_Piece=fDoc.cbDO_Piece \n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc2.DO_Type=fDoc.DO_Type\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc2.DL_Ligne > fDoc.DL_Ligne\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tAND (NOT EXISTS (SELECT TOP 1 DL_Ligne FROM F_DOCLIGNE fDoc3\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE\tfDoc.AR_Ref = fDoc3.AR_Ref\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.AR_Ref = fDoc3.AR_RefCompose\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.cbDO_Piece=fDoc.cbDO_Piece\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DO_Type=fDoc.DO_Type\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DL_Ligne>fDoc.DL_Ligne\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tOR fDoc2.DL_Ligne < (SELECT TOP 1 DL_Ligne FROM F_DOCLIGNE fDoc3\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE\tfDoc.AR_Ref = fDoc3.AR_Ref\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.AR_Ref = fDoc3.AR_RefCompose\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.cbDO_Piece=fDoc.cbDO_Piece\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DO_Type=fDoc.DO_Type\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DL_Ligne>fDoc.DL_Ligne\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t)fcompo\n" +
                    "\t\t\t\t\t\t\t\t)ELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\tCASE WHEN fDoc.DL_TRemPied = 0 AND fDoc.DL_TRemExep = 0 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tCASE WHEN (fDoc.DL_FactPoids = 0 OR fArt.AR_SuiviStock > 0) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN fDoc.DO_Type <= 2 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_Qte * fDoc.DL_CMUP\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN (\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tTHEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * (-fDoc.DL_Qte)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * fDoc.DL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tELSE CASE WHEN (fDoc.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * (-fDoc.DL_PoidsNet) / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t ELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * fDoc.DL_PoidsNet / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\tELSE 0\n" +
                    "\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\tEND),0) PrxRevientU,\n" +
                    "\t\t\t\t\t(CASE WHEN fDoc.DO_Type<>5 AND fDoc.DO_Type<>15 AND DL_TRemPied=0 AND DL_TRemExep =0 \n" +
                    "\t\t\t\t\tAND (DL_TypePL<2 OR DL_TypePL>3) AND AR_FactForfait=0 THEN \n" +
                    "\t\t\t\t\t\t\t\t\t\t\tCASE WHEN (fDoc.DO_Domaine = 4) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t0\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tELSE CASE WHEN (fDoc.DO_Type=4 OR fDoc.DO_Type=14) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t-DL_Qte \n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tDL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\tELSE 0\n" +
                    "\t\t\t\t\t\t\t\tEND) QteVendues,\n" +
                    "\t\t\t\t\t(CASE WHEN (DL_TRemPied>0 OR DL_TRemExep>0 OR \n" +
                    "\t\t\t\t\t\t\t\t\tDO_Type=5 OR DO_Type=15 OR DL_TypePL=2 OR DL_TypePL=3) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t0\n" +
                    "\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\tCASE WHEN (DL_FactPoids=0) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\tCASE WHEN (DL_Qte=0) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tDL_PrixUnitaire \n" +
                    "\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tDL_PrixUnitaire*DL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\tDL_PrixUnitaire*DL_PoidsNet/1000 \n" +
                    "\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\tEND) CAHTBrut,\n" +
                    "\t\t\t\t\t(CASE WHEN (DL_TRemPied>0 OR DL_TRemExep>0 OR \n" +
                    "\t\t\t\t\t\t\t\t\tDO_Type=5 OR DO_Type=15 OR DL_TypePL=2 OR DL_TypePL=3) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t0\n" +
                    "\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\tCASE WHEN (DL_FactPoids=0) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\tCASE WHEN (DL_Qte=0) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tDL_PUTTC \n" +
                    "\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tDL_PUTTC*DL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\tDL_PUTTC*DL_PoidsNet/1000 \n" +
                    "\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\tEND) CATTCBrut\n" +
                    "\t\t\t\tFROM F_ARTICLE fArt \n" +
                    "\t\t\t\t\tINNER JOIN F_DOCLIGNE fDoc ON (fArt.cbAR_Ref = fDoc.cbAR_Ref)  \n" +
                    "\t\t\t\tWHERE (\n" +
                    "\t\t\t\t\t (@DO_Type = 2 AND fDoc.DO_Type IN (30)\n" +
                    "\t\t\t\t\tOR @DO_Type = 7 AND fDoc.DO_Type IN (7,30)\n" +
                    "\t\t\t\t\tOR @DO_Type = 6 AND fDoc.DO_Type IN (6,7,30)\n" +
                    "\t\t\t\t\tOR @DO_Type = 3 AND fDoc.DO_Type IN (6,7,30,3))\n" +
                    "\t\t\t\t\tAND fDoc.DO_Type <= 8\n" +
                    "\t\t\t\t\tAND fDoc.DL_Valorise=1\n" +
                    "\t\t\t\t\tAND fDoc.DL_TRemExep <2\n" +
                    "\t\t\t\t\tAND fDoc.DO_Date >= @DateDebut AND fDoc.DO_Date <= @DateFin\n" +
                    "\t\t\t\t\tAND fDoc.DE_No IN (SELECT DE_No FROM #TMPDEPOT)\n" +
                    "\t\t\t\t\tAND fDoc.DL_NonLivre=0\n" +
                    "\t\t\t\t\tAND fArt.AR_SuiviStock>0 \n" +
                    " AND fArt.AR_HorsStat = 0\n" +
                    "\t\t\t\t)) fr\n" +
                    "\t\tGROUP BY DE_No,NumTiers  \n" +
                    "\t\t) totcum \n" +
                    "INNER JOIN DBO.F_COMPTET C \n" +
                    "\tON C.cbCT_Num = NumTiers\n" +
                    "LEFT JOIN DBO.F_DEPOT fDep \n" +
                    "\tON fDep.DE_No = totcum.DE_No\n" +
                    "ORDER BY\tCT_Intitule,TotCAHTNet DESC";

    public static String statArticleParAgence =
            "DECLARE @DE_No AS INT \n" +
                    "\t\t, @DateDeb AS DATE\n" +
                    "\t\t, @DateFin AS DATE\n" +
                    "\t\t, @Famille AS VARCHAR(50) \n" +
                    "\t\t, @ArticleDebut AS VARCHAR(50) \n" +
                    "\t\t, @ArticleFin AS VARCHAR(50) \n" +
                    "\t\t, @DO_Type AS INT \n" +
                    "\t\t, @PROT_No AS INT\n" +
                    "\t\t, @siteMarchand AS INT\n" +
                    "\t\t, @admin INT;\n" +
                    "CREATE TABLE #TMPDEPOT (DE_No INT)\n" +
                    "SET @DE_No = ? \n" +
                    "SET @DateDeb = ?\n" +
                    "SET @DateFin = ?\n" +
                    "SET @Famille = ?\n" +
                    "SET @ArticleDebut = ?\n" +
                    "SET @ArticleFin = ?\n" +
                    "SET @DO_Type = ?\n" +
                    "SET @siteMarchand = ?;\n" +
                    "SET @PROT_No = ?;\n" +
                    "SET NOCOUNT ON;\n" +
                    "SELECT @admin = CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No = @PROT_No \n" +
                    "IF (@admin=0)\n" +
                    "BEGIN \n" +
                    "\tINSERT INTO #TMPDEPOT\n" +
                    "\tSELECT\tA.DE_No \n" +
                    "\tFROM\tF_DEPOT A\n" +
                    "\tLEFT JOIN Z_DEPOTUSER D \n" +
                    "\t\tON  A.DE_No=D.DE_No\n" +
                    "\tWHERE\t(1 = (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No=@PROT_No) OR D.PROT_No =@PROT_No)\n" +
                    "\tAND     (@DE_No='0' OR @DE_No=A.DE_No)\n" +
                    "\tAND     IsPrincipal = 1\n" +
                    "\tGROUP BY A.DE_No\n" +
                    "END\n" +
                    "ELSE \n" +
                    "BEGIN\n" +
                    "\tINSERT\tINTO #TMPDEPOT \n" +
                    "\tSELECT  DE_No \n" +
                    "\tFROM    F_DEPOT \n" +
                    "\tWHERE   (@DE_No='0' OR @DE_No=DE_No)\n" +
                    "\n" +
                    "END ;\n" +
                    "\n" +
                    "WITH _StatArticle_ AS (\n" +
                    "SELECT  d.DE_No,DE_Intitule,f.AR_Ref,f.cbAR_Ref,f.AR_Design,\n" +
                    "TotCAHTNet,TotCATTCNet\n" +
                    ",PRECOMPTE = TotCATTCNet-TotCAHTNet\n" +
                    ",TotQteVendues\n" +
                    ",TotPrxRevientU,TotPrxRevientCA\n" +
                    ",PourcMargeHT = CASE WHEN TotCAHTNet=0 THEN 0 ELSE ROUND(TotPrxRevientU/TotCAHTNet*100,2) END \n" +
                    ",PourcMargeTT = CASE WHEN TotCATTCNet=0 THEN 0 ELSE ROUND(TotPrxRevientCA/TotCATTCNet*100,2) END \n" +
                    "\n" +
                    "FROM\n" +
                    "(SELECT cbAR_Ref\n" +
                    "\t\t,TotCAHTNet = SUM(CAHTNet) \n" +
                    "\t\t,TotCATTCNet = SUM(CATTCNet) \n" +
                    "\t\t,TotQteVendues = SUM(QteVendues) \n" +
                    "\t\t,TotPrxRevientU = SUM(CAHTNet)-SUM(PrxRevientU)\n" +
                    "\t\t,TotPrxRevientCA = SUM(CATTCNet)-SUM(PrxRevientU)\n" +
                    "\t\t,PRECOMPTE = SUM(CATTCNet*DL_Taxe1/100) \n" +
                    "\t\t,DE_No\n" +
                    "\t\tFROM (SELECT fDoc.cbAR_Ref,DL_Taxe1,DE_No,\n" +
                    "\t\t\t(\tCASE WHEN (fDoc.DO_Type>=4 AND fDoc.DO_Type<=5) \n" +
                    "\t\t\t\t\t\tTHEN -DL_MontantHT \n" +
                    "\t\t\t\t\t\tELSE DL_MontantHT\n" +
                    "\t\t\t\t\t\tEND) CAHTNet,\n" +
                    "\t\t\t\t(\tCASE WHEN (fDoc.DO_Type>=4 AND fDoc.DO_Type<=5) \n" +
                    "\t\t\t\t\t\tTHEN -DL_MontantTTC \n" +
                    "\t\t\t\t\t\tELSE DL_MontantTTC\n" +
                    "\t\t\t\t\t\tEND) CATTCNet,\n" +
                    "\t\t\t\tROUND((CASE WHEN fDoc.cbAR_Ref =convert(varbinary(255),AR_RefCompose) THEN\n" +
                    "\t\t\t\t\t\t(select SUM(toto)\n" +
                    "\t\t\t\t\t\t\t\tfrom (SELECT  \n" +
                    "\t\t\t\t\t\t\t\t\t\tCASE WHEN fDoc2.DL_TRemPied = 0 AND fDoc2.DL_TRemExep = 0 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tCASE WHEN (fDoc2.DL_FactPoids = 0 OR fArt2.AR_SuiviStock > 0) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN fDoc2.DO_Type <= 2 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_Qte * fDoc2.DL_CMUP\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN (\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tTHEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * (-fDoc2.DL_Qte)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * fDoc2.DL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tELSE CASE WHEN (fDoc2.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * (-fDoc2.DL_PoidsNet) / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t ELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * fDoc2.DL_PoidsNet / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\tELSE 0\n" +
                    "\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "toto\n" +
                    "\t\t\t\t\t\t\t\t\tFROM F_DOCLIGNE fDoc2 INNER JOIN F_ARTICLE fArt2 ON (fDoc2.cbAR_Ref = fArt2.cbAR_Ref)\n" +
                    "\n" +
                    "\t\t\t\t\t\t\t\t\tWHERE fDoc.cbAR_Ref =convert(varbinary(255),fDoc2.AR_RefCompose)\n" +
                    "\t\t\t\t\t\t\t\t\t\tAND fDoc2.DL_Valorise<>fDoc.DL_Valorise\n" +
                    "\t\t\t\t\t\t\t\t\t\tAND fDoc2.cbDO_Piece=fDoc.cbDO_Piece \n" +
                    "\t\t\t\t\t\t\t\t\t\tAND fDoc2.DO_Type=fDoc.DO_Type\n" +
                    "\t\t\t\t\t\t\t\t\t\tAND fDoc2.DL_Ligne>fDoc.DL_Ligne\n" +
                    "\t\t\t\t\t\t\t\t\t\tAND (NOT EXISTS (SELECT TOP 1 DL_Ligne FROM F_DOCLIGNE fDoc3\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE\tfDoc.AR_Ref = fDoc3.AR_Ref\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.AR_Ref = fDoc3.AR_RefCompose\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.cbDO_Piece=fDoc.cbDO_Piece\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DO_Type=fDoc.DO_Type\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DL_Ligne>fDoc.DL_Ligne\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tOR fDoc2.DL_Ligne < (SELECT TOP 1 DL_Ligne FROM F_DOCLIGNE fDoc3\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE\tfDoc.AR_Ref = fDoc3.AR_Ref\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.AR_Ref = fDoc3.AR_RefCompose\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.cbDO_Piece=fDoc.cbDO_Piece\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DO_Type=fDoc.DO_Type\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DL_Ligne>fDoc.DL_Ligne\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t)fcompo\n" +
                    "\t\t\t\t\t\t)ELSE\n" +
                    "\t\t\t\t\t\t\tCASE WHEN fDoc.DL_TRemPied = 0 AND fDoc.DL_TRemExep = 0 THEN\n" +
                    "\t\t\t\t\t\t\t\tCASE WHEN (fDoc.DL_FactPoids = 0 OR fArt.AR_SuiviStock > 0) THEN\n" +
                    "\t\t\t\t\t\t\t\t\tCASE WHEN fDoc.DO_Type <= 2 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\tfDoc.DL_Qte * fDoc.DL_CMUP\n" +
                    "\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\tCASE WHEN (\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\tTHEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * (-fDoc.DL_Qte)\n" +
                    "\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * fDoc.DL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\tELSE CASE WHEN (fDoc.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * (-fDoc.DL_PoidsNet) / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t ELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * fDoc.DL_PoidsNet / 1000\n" +
                    "\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\tELSE 0\n" +
                    "\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\tEND),0) PrxRevientU,\n" +
                    "\t\t(CASE WHEN (fDoc.DO_Type<5 OR fDoc.DO_Type>5)AND DL_TRemPied=0 AND DL_TRemExep =0\n" +
                    "\t\t\t\t\tAND (DL_TypePL < 2 OR DL_TypePL >3)  AND AR_FactForfait=0 THEN \n" +
                    "\t\t\t\t\t\t\tCASE WHEN fDoc.DO_Domaine = 4 THEN \n" +
                    "\t\t\t\t\t\t\t\t0\n" +
                    "\t\t\t\t\t\t\t\tELSE CASE WHEN (fDoc.DO_Type=4) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t-DL_Qte \n" +
                    "\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\tDL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\tELSE 0\n" +
                    "\t\t\t\t\t\tEND) QteVendues\n" +
                    "\t\t\tFROM F_ARTICLE fArt INNER JOIN F_DOCLIGNE fDoc ON (fArt.cbAR_Ref = fDoc.cbAR_Ref)\n" +
                    "\t\tWHERE\n" +
                    "\t\t\t( \n" +
                    "\t\t\t\t\t\t\t\t(@DO_Type = 2 AND fDoc.DO_Type IN (30)\n" +
                    "\t\t\tOR @DO_Type = 7 AND fDoc.DO_Type IN (7,30)\n" +
                    "\t\t\tOR @DO_Type = 6 AND fDoc.DO_Type IN (6,7,30)\n" +
                    "\t\t\tOR @DO_Type = 3 AND fDoc.DO_Type IN (6,7,30,3))\n" +
                    "\t\t\tAND fDoc.DL_Valorise=1\n" +
                    "\t\t\tAND fDoc.DL_TRemExep <2\n" +
                    "\t\t\tAND\t\t(@Famille='0' OR FA_CodeFamille = @Famille) \n" +
                    "\t\t\t\t\t\t\t\tAND\t\t(@ArticleDebut='0' OR fDoc.AR_Ref >= @ArticleDebut) \n" +
                    "\t\t\t\t\t\t\t\tAND\t\t(@ArticleFin='0' OR fDoc.AR_Ref <= @ArticleFin) \n" +
                    "\t\t\t\t\t\t\t\tAND\t\tfDoc.DE_No IN (\tSELECT  DE_No\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tFROM    #TMPDEPOT)\n" +
                    "\t\t\t\t\t\t\t\tAND\t\tfDoc.DO_Date >= @DateDeb AND fDoc.DO_Date <= @DateFin  \n" +
                    "\t\t   AND fDoc.DL_NonLivre=0\n" +
                    "\t\t\t AND fArt.AR_HorsStat = 0 \n" +
                    "\t\t--\t AND fArt.AR_SuiviStock>0\n" +
                    "\t\t)) fr\n" +
                    "GROUP BY cbAR_Ref,DE_No \n" +
                    "\n" +
                    ") totcum\n" +
                    " INNER JOIN F_ARTICLE f ON (totcum.cbAR_Ref = f.cbAR_Ref) \n" +
                    " INNER JOIN F_DEPOT d ON (totcum.DE_No = d.DE_No)\n" +
                    " WHERE (CASE WHEN @siteMarchand=2 THEN f.AR_Publie ELSE @siteMarchand END) = f.AR_Publie\n" +
                    ")\n" +
                    ",_StockReel_ AS (\n" +
                    "\tSELECT  DE_No \n" +
                    "\t\t\t,cbAR_Ref\n" +
                    "\t\t\t,AS_QteSto AS DL_Qte\n" +
                    "\tFROM F_ARTSTOCK\n" +
                    "\t\t\t\t\t\t \n" +
                    "\t\t\t\t\t\t\t\t \n" +
                    "\t\t\t\t\t\t\t\n" +
                    "\t\t\t\t\t\t\t \n" +
                    "\t\t\t\t\t\t \n" +
                    "\t\t\t\t\t\t   \n" +
                    "\t\t\t\t\t   \n" +
                    "\t\t\t\t\t \n" +
                    "\tWHERE\tDE_No IN (SELECT DE_No FROM #TMPDEPOT) \n" +
                    "\t\t\t\t\t\n" +
                    "\t\t\t\t\t\t  \n" +
                    "\tAND\t\t('0' = @ArticleDebut OR AR_Ref >= @ArticleDebut)\n" +
                    "\tAND\t\t('0' = @ArticleFin OR AR_Ref <= @ArticleFin)\n" +
                    "\t\t\t\t\t\t\t  \n" +
                    ")\n" +
                    "\n" +
                    "SELECT  Stat.DE_No\n" +
                    "\t\t,Stat.DE_Intitule\n" +
                    "\t\t,Stat.AR_Ref\n" +
                    "\t\t,Stat.AR_Design\n" +
                    "\t\t,Stat.TotCAHTNet\n" +
                    "\t\t,Stat.TotCATTCNet\n" +
                    "\t\t,Stat.PRECOMPTE\n" +
                    "\t\t,Stat.TotQteVendues\n" +
                    "\t\t,Stat.TotPrxRevientU\n" +
                    "\t\t,Stat.TotPrxRevientCA\n" +
                    "\t\t,PourcMargeHT = CASE WHEN Stat.TotCAHTNet=0 THEN 0 ELSE ROUND(Stat.TotPrxRevientU/Stat.TotCAHTNet*100,2) END \n" +
                    "\t\t,PourcMargeTT = CASE WHEN Stat.TotCATTCNet=0 THEN 0 ELSE ROUND(Stat.TotPrxRevientCA/Stat.TotCATTCNet*100,2) END \n" +
                    "\t\t,DL_Qte\n" +
                    "FROM    _StatArticle_ Stat\n" +
                    "LEFT JOIN _StockReel_ Stock\n" +
                    "\tON\tStat.DE_No = Stock.DE_No\n" +
                    "\tAND\tStat.cbAR_Ref = Stock.cbAR_Ref\n" +
                    "\t\t\t\t\t   \n" +
                    "\t\t\t  ";

    public static String statMouvementStock=
            "SET NOCOUNT ON ;\n" +
                    "DECLARE @depot as int\n" +
                    "DECLARE @dateDebut as varchar(10)\n" +
                    "DECLARE @dateFin as varchar(10)\n" +
                    "DECLARE @articleDebut as varchar(30)\n" +
                    "DECLARE @articleFin as varchar(30)\n" +
                    "\n" +
                    "SET @depot = ? \n" +
                    "SET @dateDebut = ? \n" +
                    "SET @dateFin = ? \n" +
                    "SET @articleDebut = ? \n" +
                    "SET @articleFin = ?\n" +
                    "\n" +
                    "BEGIN \n" +
                    "     SELECT *,CASE WHEN DL_Qte<0 THEN 1 ELSE 0 END Sens INTO #TMP\n" +
                    "                    FROM (\n" +
                    "                    SELECT  0 AS ligne,0 AS cbMarq,'1960-01-01' DO_Date,AR.AR_Ref,AR_Design,'' DO_Piece,'' CT_Num,'' AS CT_Intitule,\n" +
                    "                    SUM(CASE WHEN DL_MvtStock=3 THEN -1 ELSE 1 END * ABS(DL_Qte))DL_Qte,0 DO_Type,0 DO_Domaine,0 DL_MvtStock,0 AR_PrixAch,SUM(CASE WHEN DL_MvtStock=3 THEN -DL_PrixRU ELSE DL_PrixRU END) DL_PrixRU,SUM(CASE WHEN DL_MvtStock=3 THEN -DL_PrixRU*ABS(DL_Qte) ELSE DL_PrixRU*ABS(DL_Qte) END) cumul\n" +
                    "                    FROM F_DOCENTETE E\n" +
                    "                    INNER JOIN F_DOCLIGNE D \n" +
                    "                        ON  D.DO_Domaine = E.DO_Domaine \n" +
                    "                        AND D.DO_Type = E.DO_Type \n" +
                    "                        AND E.cbDO_Piece = D.cbDO_Piece\n" +
                    "                    INNER JOIN F_ARTICLE AR \n" +
                    "                        ON AR.cbAR_Ref=D.cbAR_Ref\n" +
                    "                    INNER JOIN F_DEPOT DE \n" +
                    "                        ON DE.DE_No=D.DE_No\n" +
                    "                    WHERE   (@depot='0' OR D.DE_No = @depot) \n" +
                    "                    AND     DL_MvtStock<>0\n" +
                    "                    AND     D.DO_Date < @dateDebut\n" +
                    "                    AND     ('0' =@articleDebut OR AR.cbAR_Ref>=@articleDebut)\n" +
                    "                    AND     ('0' =@articleFin OR AR.cbAR_Ref<= @articleFin)\n" +
                    "                    GROUP BY AR.AR_Ref,AR_Design\n" +
                    "                    union\n" +
                    "                    SELECT  ROW_NUMBER() OVER(PARTITION BY AR.AR_Ref order by E.DO_Date,DL_MvtStock,D.cbMarq) AS ligne,D.cbMarq,D.DO_Date,AR.AR_Ref,AR_Design,D.DO_Piece,CASE WHEN T.CT_Num IS NULL THEN DT.DE_No ELSE T.CT_Num END CT_Num\n" +
                    ",CASE WHEN CT_Intitule IS NULL THEN DT.DE_Intitule ELSE CT_Intitule END CT_Intitule,CASE WHEN DL_MvtStock=3 THEN -1 ELSE 1 END * ABS(DL_Qte),D.DO_Type,D.DO_Domaine,DL_MvtStock,AR_PrixAch,DL_PrixRU,CASE WHEN DL_MvtStock=3 THEN -(DL_PrixRU*ABS(DL_Qte)) ELSE (DL_PrixRU*ABS(DL_Qte)) END cumul\n" +
                    "                    FROM F_DOCENTETE E\n" +
                    "                    INNER JOIN F_DOCLIGNE D \n" +
                    "                        ON  D.DO_Domaine = E.DO_Domaine \n" +
                    "                        AND D.DO_Type = E.DO_Type \n" +
                    "                        AND E.cbDO_Piece = D.cbDO_Piece\n" +
                    "                    INNER JOIN F_ARTICLE AR \n" +
                    "                        ON AR.cbAR_Ref=D.cbAR_Ref\n" +
                    "                    LEFT JOIN F_COMPTET T \n" +
                    "                        ON T.CT_Num = E.DO_Tiers\n" +
                    "                    INNER JOIN F_DEPOT DE \n" +
                    "                        ON DE.DE_No=D.DE_No\n" +
                    "                    LEFT JOIN (SELECT CAST(DE_No AS VARCHAR(50)) DE_No,DE_Intitule FROM F_DEPOT) DT \n" +
                    "                        ON DT.DE_No = D.CT_Num\n" +
                    "                    WHERE   (@depot ='0' OR D.DE_No = @depot) \n" +
                    "                    AND     DL_MvtStock<>0\n" +
                    "                    AND     ('0' = @articleDebut OR AR.AR_Ref>=@articleDebut)\n" +
                    "                    AND     ('0' =@articleFin OR AR.AR_Ref<=@articleFin)\n" +
                    "                    AND D.DO_Date >= @dateDebut AND D.DO_Date <= @dateFin)A;\n" +
                    "\n" +
                    "SELECT *\n" +
                    "        FROM(\n" +
                    "            SELECT T1.ligne,T1.Sens,CAST(T1.DO_Date as Date) DO_Date,T1.AR_Ref,T1.DO_Piece,T1.AR_Design,T1.CT_Num,T1.CT_Intitule,T1.DL_Qte,T1.DO_Type,T1.DO_Domaine,T1.DL_MvtStock,T1.AR_PrixAch,T1.DL_PrixRU,SUM(T2.DL_Qte) as cumul,SUM(T2.cumul) as cumul_PRIX,T1.cbMarq\n" +
                    "                        FROM #TMP T1\n" +
                    "                        INNER JOIN #TMP T2 ON T1.ligne>=T2.ligne and T1.AR_Ref=T2.AR_Ref\n" +
                    "                        GROUP BY T1.ligne,T1.Sens,T1.cbMarq,T1.DO_Date,T1.AR_Ref,T1.DO_Piece,T1.AR_Design,T1.CT_Num,T1.CT_Intitule,T1.DL_Qte,T1.DO_Type,T1.DO_Domaine,T1.DL_MvtStock,T1.AR_PrixAch,T1.DL_PrixRU,T1.cbMarq)A\n" +
                    "                        WHERE NOT EXISTS (\n" +
                    "            SELECT 1\n" +
                    "                        FROM(\n" +
                    "                            SELECT T1.ligne,CAST(T1.DO_Date as Date) DO_Date,T1.AR_Ref,T1.DO_Piece,T1.AR_Design,T1.CT_Num,T1.CT_Intitule,T1.DL_Qte,T1.DO_Type,T1.DO_Domaine,T1.DL_MvtStock,T1.AR_PrixAch,T1.DL_PrixRU,SUM(T2.DL_Qte) as cumul,SUM(T2.cumul) as cumul_PRIX,T1.cbMarq\n" +
                    "                        FROM #TMP  T1\n" +
                    "                        INNER JOIN #TMP  T2 ON T1.ligne>=T2.ligne and T1.AR_Ref=T2.AR_Ref\n" +
                    "                        GROUP BY T1.ligne,T1.cbMarq,T1.DO_Date,T1.AR_Ref,T1.DO_Piece,T1.AR_Design,T1.CT_Num,T1.CT_Intitule,T1.DL_Qte,T1.DO_Type,T1.DO_Domaine,T1.DL_MvtStock,T1.AR_PrixAch,T1.DL_PrixRU,T1.cbMarq)B\n" +
                    "                        WHERE DL_Qte=0 AND YEAR(DO_Date)=1960 AND A.AR_Design=B.AR_Design AND A.DO_Date=B.DO_Date AND A.DL_Qte=B.DL_Qte)\n" +
                    "                        ORDER BY AR_Ref,DO_Date,Sens,cbMarq;\n" +
                    "END";
    public static String menuCaParDepot =
            "DECLARE @DE_No AS INT \n" +
                    "        DECLARE @DateDeb AS DATE\n" +
                    "        DECLARE @DateFin AS DATE \n" +
                    "        DECLARE @Famille AS VARCHAR(50) \n" +
                    "        DECLARE @ArticleDebut AS VARCHAR(50) \n" +
                    "        DECLARE @ArticleFin AS VARCHAR(50) \n" +
                    "        DECLARE @DO_Type AS INT \n" +
                    "        DECLARE @PROT_No AS INT\n" +
                    "\t\tDECLARE @admin INT\n" +
                    "\t\tCREATE TABLE #TMPDEPOT (DE_No INT)\n" +
                    "        SET @PROT_No = ?\n" +
                    "\t\tSET @DO_Type = 3\n" +
                    "\t\tSET @DateDeb = (SELECT CONCAT(LEFT(CAST(GETDATE() AS DATE),8),'01'))\n" +
                    "\t\tSET @DateFin = (SELECT EOMONTH(GETDATE()))\n" +
                    "\t\tSET @ArticleDebut = '0'\n" +
                    "\t\tSET @ArticleFin = '0'\n" +
                    "\t\tSET @Famille = '0'\n" +
                    "\t\tSET @DE_No = 0;\n" +
                    "\n" +
                    "\t\tSELECT @admin = CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No = @PROT_No \n" +
                    "        IF (@admin=0)\n" +
                    "        BEGIN \n" +
                    "            INSERT INTO #TMPDEPOT\n" +
                    "            SELECT\tA.DE_No \n" +
                    "            FROM\tF_DEPOT A\n" +
                    "            LEFT JOIN Z_DEPOTUSER D \n" +
                    "                ON  A.DE_No=D.DE_No\n" +
                    "            WHERE\t(1 = (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No=@PROT_No) OR D.PROT_No =@PROT_No)\n" +
                    "            AND     (@DE_No='0' OR @DE_No=A.DE_No)\n" +
                    "            AND     IsPrincipal = 1\n" +
                    "            GROUP BY A.DE_No\n" +
                    "        END\n" +
                    "        ELSE \n" +
                    "        BEGIN\n" +
                    "            INSERT\tINTO #TMPDEPOT \n" +
                    "            SELECT  DE_No \n" +
                    "            FROM    F_DEPOT \n" +
                    "            WHERE   (@DE_No='0' OR @DE_No=DE_No)\n" +
                    "\n" +
                    "        END ;\n" +
                    "\n" +
                    "        WITH _StatArticle_ AS (\n" +
                    "        SELECT  d.DE_No,DE_Intitule,\n" +
                    "\t\tTotCAHTNet,TotCATTCNet,TotCATTCNet-TotCAHTNet as PRECOMPTE,TotQteVendues,\n" +
                    "\t\tTotPrxRevientU,TotPrxRevientCA,\n" +
                    "\t\tCASE WHEN TotCAHTNet=0 THEN 0 ELSE ROUND(TotPrxRevientU/TotCAHTNet*100,2) END PourcMargeHT,\n" +
                    "\t\tCASE WHEN TotCATTCNet=0 THEN 0 ELSE ROUND(TotPrxRevientCA/TotCATTCNet*100,2) END PourcMargeTT\n" +
                    "\t\t\n" +
                    "\tFROM\n" +
                    "\t\t(SELECT \n" +
                    "\t\t\t\tTotCAHTNet = SUM(CAHTNet)\n" +
                    "\t\t\t\t,TotCATTCNet = SUM(CATTCNet) \n" +
                    "\t\t\t\t,TotQteVendues = SUM(QteVendues) \n" +
                    "\t\t\t\t,TotPrxRevientU = SUM(CAHTNet)-SUM(PrxRevientU) \n" +
                    "\t\t\t\t,TotPrxRevientCA = SUM(CATTCNet)-SUM(PrxRevientU)\n" +
                    "\t\t\t\t,PRECOMPTE = SUM(CATTCNet*DL_Taxe1/100) \n" +
                    "\t\t\t\t,DE_No\n" +
                    "\t\t\t\tFROM (SELECT fDoc.cbAR_Ref,DL_Taxe1,DE_No,\n" +
                    "\t\t\t\t\t(\tCASE WHEN (fDoc.DO_Type>=4 AND fDoc.DO_Type<=5) \n" +
                    "\t\t\t\t\t\t\t\tTHEN -DL_MontantHT \n" +
                    "\t\t\t\t\t\t\t\tELSE DL_MontantHT\n" +
                    "\t\t\t\t\t\t\t\tEND) CAHTNet,\n" +
                    "\t\t\t\t\t\t(\tCASE WHEN (fDoc.DO_Type>=4 AND fDoc.DO_Type<=5) \n" +
                    "\t\t\t\t\t\t\t\tTHEN -DL_MontantTTC \n" +
                    "\t\t\t\t\t\t\t\tELSE DL_MontantTTC\n" +
                    "\t\t\t\t\t\t\t\tEND) CATTCNet,\n" +
                    "\t\t\t\t\t\tROUND((CASE WHEN fDoc.cbAR_Ref =convert(varbinary(255),AR_RefCompose) THEN\n" +
                    "\t\t\t\t\t\t\t\t(select SUM(toto)\n" +
                    "\t\t\t\t\t\t\t\t\t\tfrom (SELECT  \n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN fDoc2.DL_TRemPied = 0 AND fDoc2.DL_TRemExep = 0 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN (fDoc2.DL_FactPoids = 0 OR fArt2.AR_SuiviStock > 0) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN fDoc2.DO_Type <= 2 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_Qte * fDoc2.DL_CMUP\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN (\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    " \t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTHEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * (-fDoc2.DL_Qte)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * fDoc2.DL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tELSE CASE WHEN (fDoc2.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * (-fDoc2.DL_PoidsNet) / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t ELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * fDoc2.DL_PoidsNet / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tELSE 0\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    " toto\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tFROM F_DOCLIGNE fDoc2 INNER JOIN F_ARTICLE fArt2 ON (fDoc2.cbAR_Ref = fArt2.cbAR_Ref)\n" +
                    "\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tWHERE fDoc.cbAR_Ref =convert(varbinary(255),fDoc2.AR_RefCompose)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc2.DL_Valorise<>fDoc.DL_Valorise\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc2.cbDO_Piece=fDoc.cbDO_Piece \n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc2.DO_Type=fDoc.DO_Type\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc2.DL_Ligne>fDoc.DL_Ligne\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tAND (NOT EXISTS (SELECT TOP 1 DL_Ligne FROM F_DOCLIGNE fDoc3\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE\tfDoc.AR_Ref = fDoc3.AR_Ref\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.AR_Ref = fDoc3.AR_RefCompose\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.cbDO_Piece=fDoc.cbDO_Piece\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DO_Type=fDoc.DO_Type\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DL_Ligne>fDoc.DL_Ligne\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tOR fDoc2.DL_Ligne < (SELECT TOP 1 DL_Ligne FROM F_DOCLIGNE fDoc3\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE\tfDoc.AR_Ref = fDoc3.AR_Ref\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.AR_Ref = fDoc3.AR_RefCompose\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.cbDO_Piece=fDoc.cbDO_Piece\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DO_Type=fDoc.DO_Type\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DL_Ligne>fDoc.DL_Ligne\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t)fcompo\n" +
                    "\t\t\t\t\t\t\t\t)ELSE\n" +
                    "\t\t\t\t\t\t\t\t\tCASE WHEN fDoc.DL_TRemPied = 0 AND fDoc.DL_TRemExep = 0 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\tCASE WHEN (fDoc.DL_FactPoids = 0 OR fArt.AR_SuiviStock > 0) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tCASE WHEN fDoc.DO_Type <= 2 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_Qte * fDoc.DL_CMUP\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN (\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tTHEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * (-fDoc.DL_Qte)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * fDoc.DL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\tELSE CASE WHEN (fDoc.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * (-fDoc.DL_PoidsNet) / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t ELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * fDoc.DL_PoidsNet / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\tELSE 0\n" +
                    "\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\tEND),0) PrxRevientU,\n" +
                    "\t\t\t\t(CASE WHEN (fDoc.DO_Type<5 OR fDoc.DO_Type>5)AND DL_TRemPied=0 AND DL_TRemExep =0\n" +
                    "\t\t\t\t\t\t\tAND (DL_TypePL < 2 OR DL_TypePL >3)  AND AR_FactForfait=0 THEN \n" +
                    "\t\t\t\t\t\t\t\t\tCASE WHEN fDoc.DO_Domaine = 4 THEN \n" +
                    "\t\t\t\t\t\t\t\t\t\t0\n" +
                    "\t\t\t\t\t\t\t\t\t\tELSE CASE WHEN (fDoc.DO_Type=4) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t-DL_Qte \n" +
                    "\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tDL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\tELSE 0\n" +
                    "\t\t\t\t\t\t\t\tEND) QteVendues\n" +
                    "\t\t\t\t\tFROM F_ARTICLE fArt \n" +
                    "\t\t\t\t\tINNER JOIN F_DOCLIGNE fDoc \n" +
                    "\t\t\t\t\t\tON (fArt.cbAR_Ref = fDoc.cbAR_Ref)\n" +
                    "\t\t\t\tWHERE\n" +
                    "\t\t\t\t\t( \n" +
                    "                                        (@DO_Type = 2 AND fDoc.DO_Type IN (30)\n" +
                    "\t\t\t\t\tOR @DO_Type = 7 AND fDoc.DO_Type IN (7,30)\n" +
                    "\t\t\t\t\tOR @DO_Type = 6 AND fDoc.DO_Type IN (6,7,30)\n" +
                    "\t\t\t\t\tOR @DO_Type = 3 AND fDoc.DO_Type IN (6,7,30,3))\n" +
                    "\t\t\t\t\tAND fDoc.DL_Valorise=1\n" +
                    "\t\t\t\t\tAND fDoc.DL_TRemExep <2\n" +
                    "\t\t\t\t\tAND\t\t(@Famille='0' OR FA_CodeFamille = @Famille) \n" +
                    "                                        AND\t\t(@ArticleDebut='0' OR fDoc.cbAR_Ref >= @ArticleDebut) \n" +
                    "                                        AND\t\t(@ArticleFin='0' OR fDoc.cbAR_Ref <= @ArticleFin) \n" +
                    "                                        AND\t\tfDoc.DE_No IN (\tSELECT  DE_No\n" +
                    "\t\t\t\t                                                FROM    #TMPDEPOT)\n" +
                    "                                       AND\t\tfDoc.DO_Date >= @DateDeb AND fDoc.DO_Date <= @DateFin  \n" +
                    "                   AND fDoc.DL_NonLivre=0\n" +
                    "\t\t\t\t\t AND fArt.AR_HorsStat = 0 \n" +
                    "\t\t\t\t\t AND fArt.AR_SuiviStock>0\n" +
                    "\t\t\t\t)) fr\n" +
                    "\t\tGROUP BY DE_No \n" +
                    "\t\t\n" +
                    "\t\t) totcum\n" +
                    "\t\t INNER JOIN F_DEPOT d \n" +
                    "\t\t\tON\t(totcum.DE_No = d.DE_No)\n" +
                    "        )\n" +
                    "\n" +
                    "\t\tSELECT  Stat.DE_No\n" +
                    "\t\t\t\t,Stat.DE_Intitule\n" +
                    "\t\t\t\t,Stat.TotCAHTNet\n" +
                    "\t\t\t\t,Stat.TotCATTCNet\n" +
                    "\t\t\t\t,Stat.TotQteVendues\n" +
                    "\t\tFROM    _StatArticle_ Stat\n" +
                    "\t\tORDER BY  DE_Intitule";

    public static String MesureReglement =
            "_Reglement_ AS (\n" +
                    "\tSELECT\tRG_No\n" +
                    "\t\t\t,CT_Num = CT_NumPayeur\n" +
                    "\t\t\t,FOND_CAISSE = CASE WHEN RG_TypeReg=2 THEN RG_MONTANT ELSE 0 END\n" +
                    "\t\t\t,RG_MONTANT = CASE WHEN RG_TYPEREG = 4 THEN -RG_MONTANT ELSE RG_MONTANT END\n" +
                    "\t\t\t,DEBIT = CASE WHEN (RG_Type = 0 AND RG_Montant<0) OR (RG_BANQUE =3 AND RG_TypeReg=0) OR (RG_BANQUE <> 3 AND ((RG_Type=1 AND RG_Montant>0)OR RG_TypeReg = 4))  OR RG_TypeReg = 3 OR (RG_TypeReg = 5 AND RG_Banque=1) THEN ABS(RG_Montant) ELSE 0 END \n" +
                    "\t\t\t,CREDIT = CASE WHEN RG_Type = 3 OR (RG_Type = 0  AND RG_TypeReg<>4 AND RG_Montant>=0) OR (RG_Type = 1 AND RG_Montant<0) OR (RG_BANQUE =3 AND RG_TypeReg=4) OR (RG_TypeReg = 5 AND RG_Banque<>1) THEN ABS(RG_MONTANT) ELSE 0 END \n" +
                    "\tFROM F_CREGLEMENT\n" +
                    "\tWHERE RG_Date = CAST(GETDATE() AS DATE)\n" +
                    ")";

    public static String paramQuery =
            "DECLARE @dateDebut AS NVARCHAR(50);\n" +
            "DECLARE @dateFin AS NVARCHAR(50);\n" +
            "SET dateDebut = ?\n"+
            "SET dateFin = ?\n";

    public static String MesureDocLigne =
            "_PrepMesureDocLigne_ AS (\n" +
                    "\t\t SELECT " +
                    "\t\t fDoc.DO_Piece\n" +
                    "\t\t,fDoc.DO_Domaine\n" +
                    "\t\t,fDoc.DO_Type\n" +
                    "\t\t,fart.AR_Ref\n" +
                    "\t\t,fDoc.DE_No\n" +
                    "\t\t,fDoc.CT_Num\n" +
                    "\t\t,(CASE WHEN (fDoc.DO_Type>=4 AND fDoc.DO_Type<=5) \n" +
                    "\t\t\t\t\t\tTHEN -DL_MontantHT \n" +
                    "\t\t\t\t\t\tELSE DL_MontantHT\n" +
                    "\t\t\t\t\t\tEND) CAHTNet\n" +
                    "\t\t,(\tCASE WHEN (fDoc.DO_Type>=4 AND fDoc.DO_Type<=5) \n" +
                    "\t\tTHEN -DL_MontantTTC \n" +
                    "\t\tELSE DL_MontantTTC\n" +
                    "\t\tEND) CATTCNet\n" +
                    "\t\t,(CASE WHEN (fDoc.DO_Type<5 OR fDoc.DO_Type>5)AND DL_TRemPied=0 AND DL_TRemExep =0\n" +
                    "\t\t\t\t\tAND (DL_TypePL < 2 OR DL_TypePL >3)  AND AR_FactForfait=0 THEN \n" +
                    "\t\t\t\t\t\t\tCASE WHEN fDoc.DO_Domaine = 4 THEN \n" +
                    "\t\t\t\t\t\t\t\t0\n" +
                    "\t\t\t\t\t\t\t\tELSE CASE WHEN (fDoc.DO_Type=4) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t-DL_Qte \n" +
                    "\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\tDL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\tELSE 0\n" +
                    "\t\t\t\t\t\tEND) QteVendues\n" +
                    "\t\t,ROUND((CASE WHEN fDoc.cbAR_Ref =convert(varbinary(255),AR_RefCompose) THEN\n" +
                    "\t\t\t\t\t\t(select SUM(toto)\n" +
                    "\t\t\t\t\t\t\t\tfrom (SELECT  \n" +
                    "\t\t\t\t\t\t\t\t\t\tCASE WHEN fDoc2.DL_TRemPied = 0 AND fDoc2.DL_TRemExep = 0 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tCASE WHEN (fDoc2.DL_FactPoids = 0 OR fArt2.AR_SuiviStock > 0) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN fDoc2.DO_Type <= 2 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_Qte * fDoc2.DL_CMUP\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tCASE WHEN (\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tTHEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * (-fDoc2.DL_Qte)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * fDoc2.DL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tELSE CASE WHEN (fDoc2.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * (-fDoc2.DL_PoidsNet) / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t ELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc2.DL_PrixRU * fDoc2.DL_PoidsNet / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\t\tELSE 0\n" +
                    "\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "toto\n" +
                    "\t\t\t\t\t\t\t\t\tFROM F_DOCLIGNE fDoc2 INNER JOIN F_ARTICLE fArt2 ON (fDoc2.cbAR_Ref = fArt2.cbAR_Ref)\n" +
                    "\n" +
                    "\t\t\t\t\t\t\t\t\tWHERE fDoc.cbAR_Ref =convert(varbinary(255),fDoc2.AR_RefCompose)\n" +
                    "\t\t\t\t\t\t\t\t\t\tAND fDoc2.DL_Valorise<>fDoc.DL_Valorise\n" +
                    "\t\t\t\t\t\t\t\t\t\tAND fDoc2.cbDO_Piece=fDoc.cbDO_Piece \n" +
                    "\t\t\t\t\t\t\t\t\t\tAND fDoc2.DO_Type=fDoc.DO_Type\n" +
                    "\t\t\t\t\t\t\t\t\t\tAND fDoc2.DL_Ligne>fDoc.DL_Ligne\n" +
                    "\t\t\t\t\t\t\t\t\t\tAND (NOT EXISTS (SELECT TOP 1 DL_Ligne FROM F_DOCLIGNE fDoc3\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE\tfDoc.AR_Ref = fDoc3.AR_Ref\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.AR_Ref = fDoc3.AR_RefCompose\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.cbDO_Piece=fDoc.cbDO_Piece\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DO_Type=fDoc.DO_Type\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DL_Ligne>fDoc.DL_Ligne\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tOR fDoc2.DL_Ligne < (SELECT TOP 1 DL_Ligne FROM F_DOCLIGNE fDoc3\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE\tfDoc.AR_Ref = fDoc3.AR_Ref\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.AR_Ref = fDoc3.AR_RefCompose\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.cbDO_Piece=fDoc.cbDO_Piece\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DO_Type=fDoc.DO_Type\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND fDoc3.DL_Ligne>fDoc.DL_Ligne\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t)fcompo\n" +
                    "\t\t\t\t\t\t)ELSE\n" +
                    "\t\t\t\t\t\t\tCASE WHEN fDoc.DL_TRemPied = 0 AND fDoc.DL_TRemExep = 0 THEN\n" +
                    "\t\t\t\t\t\t\t\tCASE WHEN (fDoc.DL_FactPoids = 0 OR fArt.AR_SuiviStock > 0) THEN\n" +
                    "\t\t\t\t\t\t\t\t\tCASE WHEN fDoc.DO_Type <= 2 THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\tfDoc.DL_Qte * fDoc.DL_CMUP\n" +
                    "\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\tCASE WHEN (\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                    "\t\t\t\t\t\t\t\t\t\tTHEN\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * (-fDoc.DL_Qte)\n" +
                    "\t\t\t\t\t\t\t\t\t\tELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * fDoc.DL_Qte\n" +
                    "\t\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\tELSE CASE WHEN (fDoc.DO_Type = 4\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t) THEN\n" +
                    "\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * (-fDoc.DL_PoidsNet) / 1000\n" +
                    "\t\t\t\t\t\t\t\t\t ELSE\n" +
                    "\t\t\t\t\t\t\t\t\t\tfDoc.DL_PrixRU * fDoc.DL_PoidsNet / 1000\n" +
                    "\t\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\t\tELSE 0\n" +
                    "\t\t\t\t\t\t\tEND\n" +
                    "\t\t\t\t\t\tEND),0) PrxRevientU\n" +
                    "FROM F_DOCENTETE ent\n" +
                    "LEFT JOIN F_DOCLIGNE fDoc\n" +
                    "\tON\tfdoc.DO_Domaine = ent.DO_Domaine \n" +
                    "\tAND\tfdoc.DO_Type = ent.DO_Type\n" +
                    "\tAND\tfdoc.DO_Piece = ent.DO_Piece\n" +
                    "LEFT JOIN F_ARTICLE fart\n" +
                    "\tON fDoc.AR_Ref = fart.AR_Ref\n" +
                    "WHERE ent.DO_Date = CAST(GETDATE() AS DATE)\n" +
                    ")\n" +
                    ",_MesureDocLigne_ AS (\n" +
                    "\tSELECT\tAR_Ref\n" +
                    "\t\t\t,DO_Piece\n" +
                    "\t\t\t,DO_Domaine\n" +
                    "\t\t\t,DO_Type\n" +
                    "\t\t\t,DE_No\n" +
                    "\t\t\t,CT_Num\n" +
                    "\t\t\t,CAHTNet\n" +
                    "\t\t\t,CATTCNet\n" +
                    "\t\t\t,QteVendues\n" +
                    "\t\t\t,PrxRevientU\n" +
                    "\t\t\t,Marge = CAHTNet-PrxRevientU \n" +
                    "\tFROM\t_PrepMesureDocLigne_\n" +
                    ")";

    public static String top10Vente =
            "DECLARE @period AS NVARCHAR(10) = ?; " +
                    "WITH _PrepMesureDocLigne_ AS ( \n" +
                    "                     SELECT  \n" +
                    "                     fDoc.DO_Piece \n" +
                    "                    ,fDoc.DO_Domaine \n" +
                    "                    ,fDoc.DO_Type \n" +
                    "                    ,fart.AR_Ref \n" +
                    "                    ,fDoc.DE_No \n" +
                    "                    ,fDoc.CT_Num \n" +
                    "                    ,(CASE WHEN (fDoc.DO_Type>=4 AND fDoc.DO_Type<=5)  \n" +
                    "                    THEN -DL_MontantHT  \n" +
                    "                    ELSE DL_MontantHT \n" +
                    "                    END) CAHTNet \n" +
                    "                    ,(CASE WHEN (fDoc.DO_Type>=4 AND fDoc.DO_Type<=5)  \n" +
                    "                    THEN -DL_MontantTTC  \n" +
                    "                    ELSE DL_MontantTTC \n" +
                    "                    END) CATTCNet \n" +
                    "                    ,(CASE WHEN (fDoc.DO_Type<5 OR fDoc.DO_Type>5)AND DL_TRemPied=0 AND DL_TRemExep =0 \n" +
                    "                    AND (DL_TypePL < 2 OR DL_TypePL >3)  AND AR_FactForfait=0 THEN  \n" +
                    "                    CASE WHEN fDoc.DO_Domaine = 4 THEN  \n" +
                    "                    0 \n" +
                    "                    ELSE CASE WHEN (fDoc.DO_Type=4) THEN \n" +
                    "                    -DL_Qte  \n" +
                    "                    ELSE \n" +
                    "                    DL_Qte \n" +
                    "                    END \n" +
                    "                    END \n" +
                    "                    ELSE 0 \n" +
                    "                    END) QteVendues \n" +
                    "                    ,ROUND((CASE WHEN fDoc.cbAR_Ref =convert(varbinary(255),AR_RefCompose) THEN \n" +
                    "                    (select SUM(toto) \n" +
                    "                    from (SELECT   \n" +
                    "                    CASE WHEN fDoc2.DL_TRemPied = 0 AND fDoc2.DL_TRemExep = 0 THEN \n" +
                    "                    CASE WHEN (fDoc2.DL_FactPoids = 0 OR fArt2.AR_SuiviStock > 0) THEN \n" +
                    "                    CASE WHEN fDoc2.DO_Type <= 2 THEN \n" +
                    "                    fDoc2.DL_Qte * fDoc2.DL_CMUP \n" +
                    "                    ELSE \n" +
                    "                    CASE WHEN ( \n" +
                    "                    fDoc2.DO_Type = 4 \n" +
                    "                    ) \n" +
                    "                    THEN \n" +
                    "                    fDoc2.DL_PrixRU * (-fDoc2.DL_Qte) \n" +
                    "                    ELSE \n" +
                    "                    fDoc2.DL_PrixRU * fDoc2.DL_Qte \n" +
                    "                    END \n" +
                    "                    END \n" +
                    "                    ELSE CASE WHEN (fDoc2.DO_Type = 4 \n" +
                    "                    ) THEN \n" +
                    "                    fDoc2.DL_PrixRU * (-fDoc2.DL_PoidsNet) / 1000 \n" +
                    "                     ELSE \n" +
                    "                    fDoc2.DL_PrixRU * fDoc2.DL_PoidsNet / 1000 \n" +
                    "                    END \n" +
                    "                    END \n" +
                    "                    ELSE 0 \n" +
                    "                    END \n" +
                    "                    toto \n" +
                    "                    FROM F_DOCLIGNE fDoc2 INNER JOIN F_ARTICLE fArt2 ON (fDoc2.cbAR_Ref = fArt2.cbAR_Ref) \n" +
                    "                     \n" +
                    "                    WHERE fDoc.cbAR_Ref =convert(varbinary(255),fDoc2.AR_RefCompose) \n" +
                    "                    AND fDoc2.DL_Valorise<>fDoc.DL_Valorise \n" +
                    "                    AND fDoc2.cbDO_Piece=fDoc.cbDO_Piece  \n" +
                    "                    AND fDoc2.DO_Type=fDoc.DO_Type \n" +
                    "                    AND fDoc2.DL_Ligne>fDoc.DL_Ligne \n" +
                    "                    AND (NOT EXISTS (SELECT TOP 1 DL_Ligne FROM F_DOCLIGNE fDoc3 \n" +
                    "                    WHERE fDoc.AR_Ref = fDoc3.AR_Ref \n" +
                    "                    AND fDoc3.AR_Ref = fDoc3.AR_RefCompose \n" +
                    "                    AND fDoc3.cbDO_Piece=fDoc.cbDO_Piece \n" +
                    "                    AND fDoc3.DO_Type=fDoc.DO_Type \n" +
                    "                    AND fDoc3.DL_Ligne>fDoc.DL_Ligne \n" +
                    "                    ) \n" +
                    "                    OR fDoc2.DL_Ligne < (SELECT TOP 1 DL_Ligne FROM F_DOCLIGNE fDoc3 \n" +
                    "                    WHERE fDoc.AR_Ref = fDoc3.AR_Ref \n" +
                    "                    AND fDoc3.AR_Ref = fDoc3.AR_RefCompose \n" +
                    "                    AND fDoc3.cbDO_Piece=fDoc.cbDO_Piece \n" +
                    "                    AND fDoc3.DO_Type=fDoc.DO_Type \n" +
                    "                    AND fDoc3.DL_Ligne>fDoc.DL_Ligne \n" +
                    "                    ) \n" +
                    "                    ) \n" +
                    "                    )fcompo \n" +
                    "                    )ELSE \n" +
                    "                    CASE WHEN fDoc.DL_TRemPied = 0 AND fDoc.DL_TRemExep = 0 THEN \n" +
                    "                    CASE WHEN (fDoc.DL_FactPoids = 0 OR fArt.AR_SuiviStock > 0) THEN \n" +
                    "                    CASE WHEN fDoc.DO_Type <= 2 THEN \n" +
                    "                    fDoc.DL_Qte * fDoc.DL_CMUP \n" +
                    "                    ELSE \n" +
                    "                    CASE WHEN ( \n" +
                    "                    fDoc.DO_Type = 4 \n" +
                    "                    ) \n" +
                    "                    THEN \n" +
                    "                    fDoc.DL_PrixRU * (-fDoc.DL_Qte) \n" +
                    "                    ELSE \n" +
                    "                    fDoc.DL_PrixRU * fDoc.DL_Qte \n" +
                    "                    END \n" +
                    "                    END \n" +
                    "                    ELSE CASE WHEN (fDoc.DO_Type = 4 \n" +
                    "                    ) THEN \n" +
                    "                    fDoc.DL_PrixRU * (-fDoc.DL_PoidsNet) / 1000 \n" +
                    "                     ELSE \n" +
                    "                    fDoc.DL_PrixRU * fDoc.DL_PoidsNet / 1000 \n" +
                    "                    END \n" +
                    "                    END \n" +
                    "                    ELSE 0 \n" +
                    "                    END \n" +
                    "                    END),0) PrxRevientU \n" +
                    "                    FROM F_DOCENTETE ent \n" +
                    "                    LEFT JOIN F_DOCLIGNE fDoc \n" +
                    "                    ON fdoc.DO_Domaine = ent.DO_Domaine  \n" +
                    "                    AND fdoc.DO_Type = ent.DO_Type \n" +
                    "                    AND fdoc.DO_Piece = ent.DO_Piece \n" +
                    "                    LEFT JOIN F_ARTICLE fart \n" +
                    "                    ON fDoc.AR_Ref = fart.AR_Ref \n" +
                    "                    WHERE (@period='MONTH' AND EOMONTH(ent.DO_Date) = EOMONTH(CAST(GETDATE() AS DATE)))" +
                    "                    OR (@period='DAY' AND ent.DO_Date = CAST(GETDATE() AS DATE)) \n" +
                    ") \n" +
                    "                    ,_MesureDocLigne_ AS ( \n" +
                    "                    SELECT AR_Ref \n" +
                    "                    ,DO_Piece \n" +
                    "                    ,DO_Domaine \n" +
                    "                    ,DO_Type \n" +
                    "                    ,DE_No \n" +
                    "                    ,CT_Num \n" +
                    "                    ,CAHTNet \n" +
                    "                    ,CATTCNet \n" +
                    "                    ,QteVendues \n" +
                    "                    ,PrxRevientU \n" +
                    "                    ,Marge = CAHTNet-PrxRevientU  \n" +
                    "                    FROM _PrepMesureDocLigne_ \n" +
                    "                    )\n" +
                    "SELECT TOP 10 \tfart.AR_Design\n" +
                    "\t\t,CATTCNet = SUM(CATTCNet)\n" +
                    "\t\t,Marge = SUM(Marge)\n" +
                    "\t\t,QteVendues = SUM(QteVendues)\n" +
                    "FROM\t_MesureDocLigne_ mdl\n" +
                    "LEFT JOIN F_ARTICLE fart\n" +
                    "ON\tmdl.AR_Ref = fart.AR_Ref\n" +
                    "GROUP BY fart.AR_Design\n" +
                    "ORDER BY 2 DESC\n";

    public static String detteDuJour ="\n" +
            "CREATE TABLE #TMPDEPOT (DE_No INT)\n" +
            "DECLARE @admin INT\n" +
            "DECLARE @ProtNo INT\n" +
            "SET @ProtNo = ?\n" +
            "SELECT @admin = CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No = @ProtNo               \n" +
            "IF (@admin=0)\n" +
            "BEGIN \n" +
            "\tINSERT INTO #TMPDEPOT\n" +
            "    SELECT\tA.DE_No \n" +
            "    FROM\tF_DEPOT A\n" +
            "    LEFT JOIN Z_DEPOTUSER D \n" +
            "\t\tON A.DE_No=D.DE_No\n" +
            "    WHERE\t(1 = (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No=@ProtNo) OR D.PROT_No =@ProtNo)\n" +
            "    AND IsPrincipal = 1\n" +
            "    GROUP BY A.DE_No\n" +
            "END\n" +
            "ELSE \n" +
            "BEGIN\n" +
            "\tINSERT\tINTO #TMPDEPOT \n" +
            "    SELECT DE_No \n" +
            "    FROM F_DEPOT \n" +
            "END \n" +
            ";\n" +
            "WITH _Ligne_ AS (\n" +
            "SELECT DO_Domaine,DO_Type,cbDO_Piece,SUM(DL_MontantTTC) DL_MontantTTC\n" +
            "FROM\tF_DOCLIGNE L\n" +
            "GROUP BY DO_Domaine,DO_Type,cbDO_Piece\n" +
            ")\n" +
            ", _Entete_ AS (\n" +
            "SELECT\tDO_Domaine,DO_Type,DO_Piece,cbDO_Piece,DO_Date,DE_No,DO_Tiers AS CT_Num,cbDO_Tiers\n" +
            "FROM\tF_DOCENTETE\n" +
            "GROUP BY DO_Domaine,DO_Type,DO_Piece,cbDO_Piece,DO_Date,DE_No,DO_Tiers,cbDO_Tiers\n" +
            ")\n" +
            ", _EnteteDoc_ AS (\n" +
            "SELECT\tA.DO_Domaine,A.DO_Type,A.DO_Piece,A.cbDO_Piece,A.CT_Num,A.cbDO_Tiers,A.DE_No,A.DO_Date,DL_MontantTTC\n" +
            "FROM\t_Entete_ A \n" +
            "LEFT JOIN _Ligne_ B \n" +
            "\tON\tA.DO_Domaine=B.DO_Domaine \n" +
            "\tAND A.DO_Type=B.DO_Type \n" +
            "\tAND A.cbDO_Piece=B.cbDO_Piece\n" +
            ")\n" +
            "SELECT\tCT_Num\n" +
            "\t\t,CT_Intitule\n" +
            "\t\t,Reste_A_Payer\n" +
            "\t\t,CASE WHEN NbJoursEcart>0 THEN NbJoursEcart ELSE 0 END NbJoursRetard\n" +
            "\t\t,CASE WHEN NbJoursEcart>0 THEN  'échue' ELSE 'non échue' END Statut\n" +
            "\tFROM(\n" +
            "\tSELECT\tL.CT_Num\n" +
            "\t\t\t,C.CT_Intitule\n" +
            "\t\t\t,ISNULL(ER_NbJour,0) Echeance\n" +
            "\t\t\t,ISNULL(DATEDIFF(DAY,DR_Date,GETDATE()),0) NbJoursEcart \n" +
            "\t\t\t, CAST(L.DO_Date AS DATE)DO_Date\n" +
            "\t\t\t, CAST(DR_Date AS DATE)DR_Date\n" +
            "\t\t\t, SUM(ISNULL(DL_MontantTTC,0)) AS DL_MontantTTC \n" +
            "\t\t\t, SUM(ISNULL(RC_Montant,0))  AS RC_Montant \n" +
            "\t\t\t,SUM(ISNULL(DL_MontantTTC,0)) - SUM(ISNULL(RC_Montant,0)) AS Reste_A_Payer\n" +
            "\tFROM _EnteteDoc_ L\n" +
            "\tINNER JOIN \tF_COMPTET C \n" +
            "\t\tON\tL.cbDO_Tiers = C.cbCT_Num \n" +
            "\tLEFT JOIN F_DOCREGL D \n" +
            "\t\tON\tL.cbDO_Piece=D.cbDO_Piece \n" +
            "\t\tAND L.DO_Domaine=D.DO_Domaine \n" +
            "\t\tAND L.DO_Type=D.DO_Type  \n" +
            "\tLEFT JOIN (SELECT cbDO_Piece,DO_Domaine,DO_Type, SUM(RC_Montant) RC_Montant \n" +
            "\t\t\t\tFROM F_REGLECH\n" +
            "\t\t\t\tGROUP BY cbDO_Piece,DO_Domaine,DO_Type) RE \n" +
            "\tON\tL.cbDO_Piece=RE.cbDO_Piece \n" +
            "\tAND L.DO_Domaine=RE.DO_Domaine \n" +
            "\tAND L.DO_Type=RE.DO_Type\n" +
            "\tINNER JOIN  F_DEPOT DE \n" +
            "\t\tON L.DE_No = DE.DE_No \n" +
            "\tLEFT JOIN Z_DEPOTSOUCHE DS \n" +
            "\t\tON DS.DE_No = DE.DE_No\n" +
            "\tLEFT JOIN  F_EMODELER EM \n" +
            "\t\tON\tEM.MR_No = C.MR_No \n" +
            "\t\tAND EM.N_Reglement = D.N_Reglement  \n" +
            "\tWHERE\tL.DO_Domaine = 0\n" +
            "\tAND     L.DO_Type in (6,7)  \n" +
            "\tAND\t\tL.DO_Date = CAST(GETDATE() AS DATE)\n" +
            "\tAND\t\tL.DE_No IN (SELECT DE_No FROM #TMPDEPOT) \n" +
            "\tGROUP BY L.CT_Num\n" +
            "\t\t\t,CT_Intitule\n" +
            "\t\t\t,ER_NbJour\n" +
            "\t\t\t,L.DO_Date\n" +
            "\t\t\t,DR_Date)A\n" +
            "\t WHERE  Reste_A_Payer<>0\n" +
            "AND DL_MontantTTC<>0\n" +
            "ORDER BY NbJoursRetard,DR_Date DESC";

    public static String statCaisseDuJour = "" +
            "declare @premierFondDeCaisse as int\n" +
            "DECLARE @admin INT\n" +
            "DECLARE @ProtNo INT\n" +
            "SET @ProtNo = ?\n" +
            "\n" +
            "CREATE TABLE #TMPCAISSE (CA_No INT)\n" +
            "\n" +
            "IF (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE Prot_No=@ProtNo) = 1 \n" +
            "                    BEGIN \n" +
            "\t\t\t\t\tINSERT INTO #TMPCAISSE\n" +
            "\t\t\t\t\tSELECT\tISNULL(CA.CA_No,0) CA_No \n" +
            "                    FROM F_CAISSE CA\n" +
            "                    INNER JOIN Z_DEPOTCAISSE C \n" +
            "\t\t\t\t\t\tON CA.CA_No=C.CA_No\n" +
            "                    INNER JOIN F_DEPOT D \n" +
            "\t\t\t\t\t\tON C.DE_No=D.DE_No\n" +
            "                    INNER JOIN F_COMPTET CT \n" +
            "\t\t\t\t\t\tON CT.cbCT_Num = CA.cbCT_Num\n" +
            "\t\t\t\t\tGROUP BY CA.CA_No\n" +
            "                    END \n" +
            "\t\t\t\t\tELSE \n" +
            "                    BEGIN \n" +
            "\t\t\t\t\tINSERT INTO #TMPCAISSE\n" +
            "\t\t\t\t\tSELECT\tISNULL(CA.CA_No,0) CA_No\n" +
            "                    FROM F_CAISSE CA\n" +
            "                    LEFT JOIN Z_DEPOTCAISSE C \n" +
            "\t\t\t\t\t\tON CA.CA_No=C.CA_No\n" +
            "                    LEFT JOIN (\tSELECT * \n" +
            "                                FROM Z_DEPOTUSER\n" +
            "                                WHERE IsPrincipal=1) D \n" +
            "\t\t\t\t\t\tON C.DE_No=D.DE_No\n" +
            "                    LEFT JOIN F_COMPTET CT ON CT.CT_Num = CA.CT_Num\n" +
            "                    WHERE Prot_No=@ProtNo\n" +
            "\t\t\t\t\tGROUP BY CA.CA_No\n" +
            "\t\t\t\t\tEND;\n" +
            "\n" +
            "\t\t\t\n" +
            "with _ReglEch_ AS (\n" +
            "SELECT\tRG_No,SUM(RC_Montant) RC_Montant\n" +
            "FROM\tF_REGLECH\n" +
            "GROUP BY RG_No\n" +
            ")\n" +
            ", _Reglement_ AS (\n" +
            "SELECT\tRG_TypeReg,C.CA_No,RG_Date,RG_Libelle,N_Reglement,RG_HEURE\n" +
            "\t\t,RG_No =CASE WHEN RG_TypeReg = 2 THEN 0 ELSE RG_No END \n" +
            "\t\t,RG_Type = CASE WHEN RG_TypeReg = 2 THEN 0 ELSE RG_Type END\n" +
            "\t\t,FOND_CAISSE = CASE WHEN RG_TypeReg=2 THEN RG_MONTANT ELSE 0 END\n" +
            "\t\t,RG_MONTANT = CASE WHEN RG_TYPEREG = 4 THEN -RG_MONTANT ELSE RG_MONTANT END\n" +
            "\t\t,DEBIT = CASE WHEN (RG_Type = 0 AND RG_Montant<0) OR (RG_BANQUE =3 AND RG_TypeReg=0) OR (RG_BANQUE <> 3 AND ((RG_Type=1 AND RG_Montant>0)OR RG_TypeReg = 4))  OR RG_TypeReg = 3 OR (RG_TypeReg = 5 AND RG_Banque=1) THEN ABS(RG_Montant) ELSE 0 END \n" +
            "        ,CREDIT = CASE WHEN RG_Type = 3 OR (RG_Type = 0  AND RG_TypeReg<>4 AND RG_Montant>=0) OR (RG_Type = 1 AND RG_Montant<0) OR (RG_BANQUE =3 AND RG_TypeReg=4) OR (RG_TypeReg = 5 AND RG_Banque<>1) THEN ABS(RG_MONTANT) ELSE 0 END \n" +
            "\t\t,RG_BANQUE,CT_NUMPAYEUR,cbCT_NumPayeur,C.cbMarq\n" +
            "FROM F_CREGLEMENT C\n" +
            "WHERE C.CA_No IN (SELECT CA_No FROM #TMPCAISSE)\n" +
            "AND N_Reglement<>8 AND ((RG_TypeReg=5 AND RG_Banque IN (0,1,3)) OR RG_TypeReg<>5)\n" +
            "AND RG_Date = CAST(GETDATE() AS DATE)\n" +
            "\t\t\t\n" +
            ")\n" +
            "\n" +
            " SELECT \tca.CA_Intitule\n" +
            "\t\t\t,CREDIT = SUM(T1.CREDIT)\n" +
            "\t\t\t,DEBIT = SUM(ABS(T1.DEBIT))\n" +
            "FROM\t\t_Reglement_ T1\n" +
            "LEFT JOIN\tF_CAISSE ca\n" +
            "\tON T1.CA_No = ca.CA_No\n" +
            "GROUP BY ca.CA_Intitule\n" ;
}
