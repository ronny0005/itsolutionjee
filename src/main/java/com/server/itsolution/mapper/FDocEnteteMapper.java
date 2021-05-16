package com.server.itsolution.mapper;

public class FDocEnteteMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select   DO_Domaine,DO_Type,DO_Piece,CAST(DO_Date AS DATE) DO_Date,DO_Ref,DO_Tiers\n" +
            "    ,CO_No,DO_Period,DO_Devise,DO_Cours,DE_No,LI_No,CT_NumPayeur,DO_Expedit\n" +
            "    ,DO_NbFacture,DO_BLFact,DO_TxEscompte,DO_Reliquat\n" +
            "    ,DO_Imprim,CA_Num,DO_Coord01,DO_Coord02,DO_Coord03\n" +
            "    ,DO_Coord04,DO_Souche,CAST(DO_DateLivr AS DATE)DO_DateLivr,DO_Condition\n" +
            "    ,DO_Tarif,DO_Colisage,DO_TypeColis,DO_Transaction\n" +
            "    ,DO_Langue,DO_Ecart,DO_Regime,N_CatCompta\n" +
            "    ,DO_Ventile,AB_No,DO_DebutAbo,DO_FinAbo\n" +
            "    ,DO_DebutPeriod,DO_FinPeriod,CG_Num,DO_Statut\n" +
            "    ,DO_Heure,CA_No,CO_NoCaissier,DO_Transfere\n" +
            "    ,DO_Cloture,DO_NoWeb,DO_Attente,DO_Provenance\n" +
            "    ,CA_NumIFRS,MR_No,DO_TypeFrais,DO_ValFrais,DO_TypeLigneFrais,DO_TypeFranco\n" +
            "    ,DO_ValFranco,DO_TypeLigneFranco,DO_Taxe1,DO_TypeTaux1\n" +
            "    ,DO_TypeTaxe1,DO_Taxe2,DO_TypeTaux2,DO_TypeTaxe2\n" +
            "    ,DO_Taxe3,DO_TypeTaux3,DO_TypeTaxe3,DO_MajCpta\n" +
            "    ,DO_Motif,CT_NumCentrale,DO_Contact ,DO_FactureElec,DO_TypeTransac\n" +
            "    ,cbMarq,cbModification,cbFlag,longitude,latitude,VEHICULE,CHAUFFEUR,\n" +
            "    cbProt, cbCreateur From F_DOCENTETE";

    public static final String getDocRegl =
			" SELECT 	cbMarq " +
			" FROM 		F_DOCREGL " +
			" WHERE 	DO_Domaine = ? " +
			" AND 		DO_Type = ? " +
			" AND 		DO_Piece = ? ";

    public static final String getFDocEntete = BASE_SQL + " WHERE cbMarq = ?";

    public static final String enteteTransfertDetail =
			"WITH _List_ AS (" +
			" SELECT cbDO_Piece,DO_Domaine" +
			" FROM F_DOCENTETE" +
			" WHERE cbMarq = ?" +
			")" +
			" SELECT cbMarq,DO_Type " +
			" FROM 	F_DOCENTETE doce " +
			" INNER JOIN _List_ list" +
			"	ON doce.cbDO_Piece = list.cbDO_Piece" +
					" AND doce.DO_Domaine = list.DO_Domaine";

    public static final String getLigneTransfertDetail =
			"DECLARE @doPiece NVARCHAR(50) = ?\n" +
					"                SELECT *\n" +
					"                FROM(\n" +
					"                SELECT L.cbMarq,A.AR_Ref,AR_Design AS DL_Design,P_Conditionnement,E.DO_Piece,E.cbDO_Piece, ROUND(DL_Qte*DL_CMUP,2) DL_MontantHT,DL_Ligne, ROUND(DL_Qte*DL_CMUP,2) DL_MontantTTC,DL_Qte\n" +
					"                ,DL_CMUP DL_PrixUnitaire,DL_CMUP,DL_Taxe1,DL_Taxe2,DL_Taxe3,0 AS DL_Remise\n" +
					"                FROM F_DOCENTETE E\n" +
					"                LEFT JOIN F_DOCLIGNE L \n" +
					"                    on E.cbDO_Piece=L.cbDO_Piece \n" +
					"                    AND E.DO_Domaine=L.DO_Domaine \n" +
					"                   AND E.DO_Type= L.DO_Type\n" +
					"                INNER JOIN F_ARTICLE A \n" +
					"                    ON L.AR_Ref=A.AR_Ref \n" +
					"                LEFT JOIN P_CONDITIONNEMENT Co \n" +
					"                    ON AR_Condition = Co.cbIndice\n" +
					"                INNER JOIN F_DEPOT DE \n" +
					"                    ON DE.DE_No=E.DE_No \n" +
					"                WHERE E.DO_Domaine=4 AND E.DO_Type=41 \n" +
					"                  AND E.DO_Piece=@doPiece)A \n" +
					"                INNER JOIN(\n" +
					"                SELECT L.cbMarq AS idSec,A.AR_Ref AS AR_Ref_Dest,AR_Design AS DL_Design_Dest,P_Conditionnement as P_Conditionnement_Dest\n" +
					"                     ,E.cbDO_Piece AS DO_Piece_dest,ROUND(DL_Qte*DL_CMUP,2) AS DL_MontantHT_dest,ROUND(DL_Qte*DL_CMUP,2) AS DL_MontantTTC_dest\n" +
					"                     ,DL_Ligne AS DL_Ligne_dest,DL_Qte AS DL_Qte_dest\n" +
					"                ,DL_CMUP AS DL_PrixUnitaire_dest,DL_CMUP AS DL_CMUP_dest,0 AS DL_Remise_dest\n" +
					"                FROM F_DOCENTETE E\n" +
					"                LEFT JOIN F_DOCLIGNE L \n" +
					"                    on E.cbDO_Piece=L.cbDO_Piece \n" +
					"                    AND E.DO_Domaine=L.DO_Domaine \n" +
					"                    AND E.DO_Type= L.DO_Type\n" +
					"                INNER JOIN F_ARTICLE A \n" +
					"                    ON L.AR_Ref=A.AR_Ref \n" +
					"                LEFT JOIN P_CONDITIONNEMENT Co \n" +
					"                    ON AR_Condition = Co.cbIndice\n" +
					"                INNER JOIN F_DEPOT DE \n" +
					"                    ON DE.DE_No=E.DE_No \n" +
					"                WHERE E.DO_Domaine=4 AND E.DO_Type=40 AND E.DO_Piece=@doPiece) B \n" +
					"                    ON A.cbDO_PIECE=B.DO_Piece_dest AND DL_Ligne=DL_Ligne_dest";
    public static final String majEnteteComptable =
			"DECLARE @do_type AS int\n" +
					"                DECLARE @do_domaine AS int\n" +
					"                DECLARE @do_piece AS varchar(20)\n" +
					"                DECLARE @do_typeCible AS int\n" +
					"                SET @do_type = ?\n" +
					"                SET @do_domaine = ?\n" +
					"                SET @do_piece = ?\n" +
					"                SET @do_typeCible = ?;\n" +
					"                \n" +
					"                DISABLE TRIGGER TG_UPD_F_DOCLIGNE ON F_DOCLIGNE;\n" +
					"                DISABLE TRIGGER TG_UPD_F_DOCREGL ON F_DOCREGL;\n" +
					"                DISABLE TRIGGER TG_UPD_F_REGLECH ON F_REGLECH;\n" +
					"                \n" +
					"                UPDATE F_DOCLIGNE \n" +
					"                    SET F_DOCLIGNE.DO_Type=@do_typeCible\n" +
					"                FROM (SELECT cbDO_Piece,DO_Domaine,DO_Type\t\n" +
					"                        FROM F_DOCENTETE\t\t\n" +
					"                        WHERE DO_Piece = @do_piece\n" +
					"                        AND DO_Domaine=@do_domaine\n" +
					"                        AND DO_Type=@do_type)A\n" +
					"                WHERE   A.cbDO_Piece= F_DOCLIGNE.cbDO_Piece \n" +
					"                AND     A.DO_Domaine= F_DOCLIGNE.DO_Domaine \n" +
					"                AND     A.DO_Type = F_DOCLIGNE.DO_Type\n" +
					"                \n" +
					"                UPDATE F_DOCREGL \n" +
					"                    SET F_DOCREGL.DO_Type=@do_typeCible\n" +
					"                FROM (SELECT cbDO_Piece,DO_Domaine,DO_Type\t\n" +
					"                        FROM F_DOCENTETE\t\n" +
					"                        WHERE DO_Piece = @do_piece\n" +
					"                        AND DO_Domaine=@do_domaine\n" +
					"                        AND DO_Type=@do_type)A\n" +
					"                WHERE   A.cbDO_Piece= F_DOCREGL.cbDO_Piece \n" +
					"                AND     A.DO_Domaine= F_DOCREGL.DO_Domaine \n" +
					"                AND     A.DO_Type = F_DOCREGL.DO_Type\n" +
					"                \n" +
					"                UPDATE F_REGLECH \n" +
					"                    SET F_REGLECH.DO_Type=@do_typeCible\n" +
					"                FROM (SELECT cbDO_Piece,DO_Domaine,DO_Type\t\n" +
					"                        FROM F_DOCENTETE\t\t\n" +
					"                        WHERE DO_Piece = @do_piece\n" +
					"                        AND DO_Domaine=@do_domaine\n" +
					"                        AND DO_Type=@do_type)A\n" +
					"                WHERE   A.cbDO_Piece= F_REGLECH.cbDO_Piece \n" +
					"                AND     A.DO_Domaine= F_REGLECH.DO_Domaine \n" +
					"                AND     A.DO_Type = F_REGLECH.DO_Type\n" +
					"                \n" +
					"                UPDATE F_DOCENTETE \n" +
					"                    SET DO_Type=@do_typeCible\n" +
					"                WHERE   DO_Piece = @do_piece\n" +
					"                AND     DO_Domaine=@do_domaine\n" +
					"                AND     DO_Type=@do_type;\n" +
					"                \n" +
					"                ENABLE TRIGGER TG_UPD_F_DOCLIGNE ON F_DOCLIGNE;\n" +
					"                ENABLE TRIGGER TG_UPD_F_DOCREGL ON F_DOCREGL;\n" +
					"                ENABLE TRIGGER TG_UPD_F_REGLECH ON F_REGLECH;";

    public static final String statutAchat ="DECLARE @type NVARCHAR(50) = ?\n" +
			"SELECT * FROM(\n" +
			"            SELECT *,CASE WHEN (Val=0 AND D_Saisie=1) OR (Val=1 AND D_Confirme=1) OR (Val=2 AND D_Valide=1) THEN 1 ELSE 0 END FLAG\n" +
			"                FROM(\n" +
			"                    SELECT *\n" +
			"                    FROM (\n" +
			"                    SELECT 'Saisi' as Lib ,0 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'Confirmé' as Lib ,1 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'A facturer' as Lib ,2 as Val)A\n" +
			"                    WHERE ('BonLivraison'= @type)\n" +
			"                    UNION\n" +
			"                    SELECT *\n" +
			"                    FROM (\n" +
			"                    SELECT 'Saisi' as Lib ,0 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'Confirmé' as Lib ,1 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'Accepté' as Lib ,2 as Val)A\n" +
			"                    WHERE 'Devis'=@type OR 'PreparationCommande'=@type\n" +
			"                    UNION\n" +
			"                    SELECT *\n" +
			"                    FROM (\n" +
			"                    SELECT 'Saisi' as Lib ,0 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'Confirmé' as Lib ,1 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'A comptabiliser' as Lib ,2 as Val)A\n" +
			"                    WHERE 'Vente'=@type OR 'Achat'= @type OR 'Retour'= @type)A\n" +
			"            INNER JOIN (SELECT *\n" +
			"                    FROM(\n" +
			"                    SELECT cbIndice, D_Valide,D_Saisie,D_Confirme,\n" +
			"                    CASE WHEN cbIndice=2 THEN 'PreparationCommande' \n" +
			"                            WHEN cbIndice=3 THEN 'BonCommande' \n" +
			"                            WHEN cbIndice=4 THEN 'BonLivraison' \n" +
			"                            WHEN cbIndice=5 THEN 'BonRetour'  \n" +
			"                            WHEN cbIndice=6 THEN 'BonAvoirFinancier'\n" +
			"                            WHEN cbIndice=7 THEN 'Achat' \n" +
			"                            WHEN cbIndice=8 THEN 'Retour'\n" +
			"                            WHEN cbIndice=9 THEN 'Avoir' \n" +
			"                            WHEN cbIndice=10 THEN 'Comptabilise' ELSE '' END AS Typ\n" +
			"                        FROM P_ORGAACH )A) B ON B.Typ = @type)A\n" +
			"                    WHERE FLAG=1";

    public static final String 	getLigneTransfert =
			"DECLARE @cbMarq AS INT = ?; \n" +
			"SELECT ISNULL(idSec,0)idSec,A.*,userReception,cbCreateurReception\n" +
			"                FROM (\tSELECT\tDL_Ligne AS Ligne , M.cbMarq,E.DO_Piece,AR_Ref,DL_Design,DL_PieceBL\n" +
			"                                ,cbCreateurEmission = M.cbCreateur,userEmission = pro.PROT_User\n" +
			"                                ,DL_Qte,DL_PrixUnitaire,DL_CMUP ,DL_Taxe1,DL_Taxe2,DL_Taxe3,DL_MontantTTC,DL_MontantHT,DL_Ligne \n" +
			"                                ,CASE WHEN DL_Remise01REM_Type=0 THEN '' WHEN DL_Remise01REM_Type=1 THEN cast(cast(DL_Remise01REM_Valeur as numeric(9,2)) as varchar(10))+'%' ELSE cast(cast(DL_Remise01REM_Valeur as numeric(9,2)) as varchar(10))+'U' END DL_Remise \n" +
			"                        FROM\tF_DOCENTETE E \n" +
			"                        INNER JOIN F_DOCLIGNE M \n" +
			"                            ON  E.DO_Domaine = M.DO_Domaine \n" +
			"                            AND E.DO_Type = M.DO_Type \n" +
			"                            AND E.DO_Piece = M.DO_Piece \n" +
			"                        LEFT JOIN F_PROTECTIONCIAL pro\n" +
			"                            ON  CAST(pro.PROT_No AS NVARCHAR(30)) = CAST(M.CbCreateur AS NVARCHAR(30))\n" +
			"                        WHERE\tM.DL_MvtStock=3 AND E.cbMarq=@cbMarq) AS A \n" +
			"                LEFT JOIN (SELECT\tDL_Ligne Ligne,idSec = cbMarq,AR_Ref,userReception,cbCreateurReception\n" +
			"                            FROM\t(\tSELECT DL_Ligne,M.cbMarq,M.AR_Ref\n" +
			"                                                ,cbCreateurReception = M.cbCreateur,userReception = pro.PROT_User\n" +
			"                                        FROM\tF_DOCENTETE E \n" +
			"                                        INNER JOIN F_DOCLIGNE M \n" +
			"                                            ON  E.DO_Domaine = M.DO_Domaine \n" +
			"                                            AND E.DO_Type = M.DO_Type \n" +
			"                                            AND E.DO_Piece = M.DO_Piece \n" +
			"                                        LEFT JOIN F_PROTECTIONCIAL pro\n" +
			"                                            ON  CAST(pro.PROT_No AS NVARCHAR(30)) = CAST(M.CbCreateur AS NVARCHAR(30))\n" +
			"                                        WHERE\tM.DL_MvtStock=1 \n" +
			"                                        AND     E.cbMarq=@cbMarq)B \n" +
			"                            ) B ON (B.Ligne-A.Ligne )=10000 AND A.AR_Ref = B.AR_Ref";
    public static final String statutVente ="  DECLARE @type NVARCHAR(30) = ?\n" +
			"SELECT * \n" +
			"FROM(\n" +
			"            SELECT *,CASE WHEN (Val=0 AND D_Saisie=1) OR (Val=1 AND D_Confirme=1) OR (Val=2 AND D_Valide=1) THEN 1 ELSE 0 END FLAG\n" +
			"                FROM(\n" +
			"                    SELECT *\n" +
			"                    FROM (\n" +
			"                    SELECT 'Saisi' as Lib ,0 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'Confirmé' as Lib ,1 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'A facturer' as Lib ,2 as Val)A\n" +
			"                    WHERE ('BonLivraison'=@type)\n" +
			"                    UNION\n" +
			"                    SELECT *\n" +
			"                    FROM (\n" +
			"                    SELECT 'Saisi' as Lib ,0 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'Confirmé' as Lib ,1 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'A préparer' as Lib ,2 as Val)A\n" +
			"                    WHERE 'BonCommande'=@type\n" +
			"                    UNION\n" +
			"                    SELECT *\n" +
			"                    FROM (\n" +
			"                    SELECT 'Saisi' as Lib ,0 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'Confirmé' as Lib ,1 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'Accepté' as Lib ,2 as Val)A\n" +
			"                    WHERE 'Devis'=@type\n" +
			"                    UNION\n" +
			"                    SELECT *\n" +
			"                    FROM (\n" +
			"                    SELECT 'Saisi' as Lib ,0 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'Confirmé' as Lib ,1 as Val\n" +
			"                    UNION\n" +
			"                    SELECT 'A comptabiliser' as Lib ,2 as Val)A\n" +
			"                    WHERE CASE WHEN 'Vente'=@type THEN 1\n" +
			"                                WHEN 'ACHAT'=@type THEN 1\n" +
			"                                 WHEN 'VenteRetour'=@type THEN 1 \n" +
			"                                  WHEN 'Avoir'=@type THEN 1 END = 1)A\n" +
			"            INNER JOIN (SELECT *\n" +
			"                    FROM(\n" +
			"                    SELECT cbIndice, D_Valide,D_Saisie,D_Confirme,\n" +
			"                    CASE WHEN cbIndice=1 THEN 'Devis' ELSE \n" +
			"                            CASE WHEN cbIndice=2 THEN 'BonCommande' ELSE \n" +
			"                                    CASE WHEN cbIndice=3 THEN 'PreparationLivraison' ELSE\n" +
			"                                            CASE WHEN cbIndice=4 THEN 'BonLivraison' ELSE \n" +
			"                                                    CASE WHEN cbIndice=5 THEN 'BonRetour' ELSE\n" +
			"                                                            CASE WHEN cbIndice=6 THEN 'BonAvoirFinancier' ELSE \n" +
			"                                                            CASE WHEN cbIndice=7 THEN 'Vente' ELSE \n" +
			"                                                            CASE WHEN cbIndice=8 THEN 'VenteRetour' ELSE \n" +
			"                                                            CASE WHEN cbIndice=9 THEN 'Avoir' ELSE \n" +
			"                                                            CASE WHEN cbIndice=10 THEN 'Comptabilise' ELSE '' END END END END END END END END END END AS Typ\n" +
			"                    FROM P_ORGAVEN)A) B ON B.Typ = @type)A\n" +
			"                    WHERE FLAG=1";

    public static final String suppressionReglement =
			"DECLARE @doPiece VARCHAR(20) = ?" +
					", @doDomaine INT = ?" +
					", @doType INT = ?;" +
					"DELETE \n" +
			"                FROM F_CREGLEMENT \n" +
			"                WHERE RG_No IN(SELECT RG_No\n" +
			"                                FROM F_REGLECH R\n" +
			"                                WHERE DO_PIECE=@doPiece AND DO_Domaine=@doDomaine AND DO_Type=@doType);\n" +
			"                DELETE \n" +
			"                FROM F_REGLECH \n" +
			"                WHERE DO_PIECE=@doPiece AND DO_Domaine=@doDomaine AND DO_Type=@doType;\n" +
			"                DELETE \n" +
			"                FROM F_DOCREGL \n" +
			"                WHERE DO_PIECE=@doPiece AND DO_Domaine=@doDomaine AND DO_Type=@doType;\n" +
			"                DELETE\n" +
			"                FROM F_DOCENTETE\n" +
			"                WHERE DO_PIECE=@doPiece AND DO_Domaine=@doDomaine AND DO_Type=@doType";

    public static final String isRegleFullDOPiece =
			"	DECLARE @cbMarq AS INT = ?;" +
			"	WITH _Entete_ AS(\n" +
			"                    SELECT  fde.DO_Type,fde.DO_Domaine,fde.cbDO_Piece,DL_MontantTTC = SUM(DL_MontantTTC) \n" +
			"                    FROM    F_DOCENTETE fde\n" +
			"                    INNER JOIN F_DOCLIGNE fdl\n" +
			"                        ON  fde.DO_Domaine = fdl.DO_Domaine\n" +
			"                        AND fde.DO_Type = fdl.DO_Type\n" +
			"                        AND fde.cbDO_Piece = fdl.cbDO_Piece\n" +
			"                    WHERE   fde.cbMarq = @cbMarq \n" +
			"                    GROUP BY fde.DO_Type,fde.DO_Domaine,fde.cbDO_Piece  \n" +
			"                )\n" +
			"                ,_ReglEch_ AS (\n" +
			"                    SELECT  DO_Type,DO_Domaine,cbDO_Piece, SUM(RC_Montant)RC_Montant\n" +
			"                    FROM    F_REGLECH \n" +
			"                    GROUP BY DO_Type,DO_Domaine,cbDO_Piece\n" +
			"                )\n" +
			"\n" +
			"                SELECT VAL = CASE WHEN RC_Montant >= DL_MontantTTC THEN 1 ELSE 0 END\n" +
			"                FROM(\n" +
			"                SELECT  A.*,RC_Montant = ISNULL(RC_Montant,0)  \n" +
			"                FROM    _Entete_ A\n" +
			"                LEFT JOIN _ReglEch_ D \n" +
			"                    ON  D.cbDO_Piece=A.cbDO_Piece \n" +
			"                    AND D.DO_Domaine = A.DO_Domaine \n" +
			"                    AND A.DO_Type = D.DO_Type\n" +
			"                ) A";
    public static final String getFactureCORecouvrement =
					"				 DECLARE @collab AS INT\n" +
					"                DECLARE @ctNum AS NVARCHAR(150) \n" +
					"                SET @collab = ?;\n" +
					"                SET @ctNum = ?;\n" +
					"                WITH _ReglEch_ AS (\n" +
					"                    SELECT DR_No, SUM(RC_MONTANT) avance \n" +
					"                    FROM F_REGLECH R \n" +
					"                    INNER JOIN F_Creglement Re \n" +
					"                        ON Re.RG_No=R.RG_No \n" +
					"                    GROUP BY DR_No\n" +
					"                )\n" +
					"                ,_DocLigne_ AS (\n" +
					"                    SELECT cbDO_Piece,DO_Type,DO_Domaine,SUM(DL_MontantTTC) DL_MontantTTC \n" +
					"                    FROM F_DOCLIGNE L \n" +
					"                    GROUP BY cbDO_Piece,DO_Type,DO_Domaine,DO_Date\n" +
					"                )\n" +
					"                SELECT *\n" +
					"                    FROM(\n" +
					"                         SELECT E.cbMarq,DO_Provenance,E.cbModification,DE_Intitule,E.DO_Piece,E.DO_Type,E.DO_Domaine,E.DO_Ref,CAST(CAST(E.DO_Date AS DATE) AS VARCHAR(10)) AS DO_Date,ISNULL(MAX(CASE WHEN E.N_CatCompta=0 THEN (C.N_CatCompta) ELSE (E.N_CatCompta) END),'0') N_CatCompta,E.DO_Tiers as CT_Num,CT_Intitule, ROUND(SUM(L.DL_MontantTTC),0) AS ttc, \n" +
					"                                ISNULL(MAX(latitude),0) as latitude,ISNULL(MAX(longitude),0) as longitude,ISNULL(sum(avance),0) AS avance  \n" +
					"                         FROM  F_DOCENTETE E \n" +
					"                         LEFT JOIN _DocLigne_ L \n" +
					"                            ON  E.cbDO_Piece=L.cbDO_Piece  \n" +
					"                            AND E.DO_Domaine= L.DO_Domaine \n" +
					"                            AND E.DO_Type=L.DO_Type\n" +
					"                         LEFT JOIN F_DOCREGL R \n" +
					"                            ON  R.cbDO_Piece=E.cbDO_Piece \n" +
					"                            AND R.DO_Type=E.DO_Type \n" +
					"                            AND R.DO_Domaine=E.DO_Domaine\n" +
					"                         INNER JOIN F_DEPOT D \n" +
					"                            ON D.DE_No=E.DE_No \n" +
					"                         INNER JOIN F_COMPTET C \n" +
					"                            ON C.cbCT_Num=E.cbDO_Tiers\n" +
					"                         LEFT JOIN _ReglEch_ A \n" +
					"                            ON A.DR_No=R.DR_No  \n" +
					"                         WHERE (@collab = 0 AND (E.DO_Domaine=0 \n" +
					"                            AND (E.DO_Type=6 OR E.DO_Type=7) \n" +
					"                            AND DO_Provenance IN(0,1)) OR (E.DO_Domaine=1\n" +
					"                            AND (E.DO_Type=16 OR E.DO_Type=17 OR E.DO_Type=12)) OR (@collab = 1 AND E.DO_Domaine=1 ) )  \n" +
					"                            AND ISNULL(r.dr_regle,0)=0 AND (@collab = 1 OR (@collab = 0 AND C.CT_Num = @ctNum))\n" +
					"                            AND (0 = @collab OR (@collab=1 AND CAST(E.CO_No AS NVARCHAR(50)) = @ctNum))\n" +
					"                            GROUP BY E.cbMarq,DO_Provenance,E.cbModification,DE_Intitule,E.DO_Piece,E.DO_Type,E.DO_Domaine,E.DO_Ref,E.DO_Date,E.DO_Tiers,CT_Intitule\n" +
					"                    )A\n" +
					"                    WHERE ((DO_Provenance =0 AND ttc>0) OR DO_Provenance=1) \n" +
					"                    AND     NOT (TTC=AVANCE) \n" +
					"                    ORDER BY DO_Date DESC";
    public static final String setDoModif =
            "SELECT CASE WHEN ABS(DATEDIFF(d,GETDATE(),DO_Date))>= (SELECT PR_DelaiPreAlert\n" +
            "                FROM P_PREFERENCES) THEN 1 ELSE 0 END DO_Modif\n" +
            "                FROM F_DOCENTETE\n" +
            "                WHERE cbMarq = ?";

    public static final String getLigneFactureTransfert =
            "SELECT    idSec = 0,M.cbMarq,M.DO_Piece,AR_Ref,DL_Design\n" +
					"                            ,DL_Qte,DL_PrixUnitaire,DL_CMUP,DL_Taxe1,DL_Taxe2\n" +
					"                            ,DL_Taxe3,DL_MontantTTC,DL_MontantHT,DL_Ligne\n" +
					"                            ,DL_Remise = CASE WHEN DL_Remise01REM_Type=0 THEN '' \n" +
					"                                WHEN DL_Remise01REM_Type=1 THEN cast(cast(DL_Remise01REM_Valeur as numeric(9,2)) as varchar(10))+'%' \n" +
					"                             ELSE cast(cast(DL_Remise01REM_Valeur as numeric(9,2)) as varchar(10))+'U' END   \n" +
					"                    FROM F_DOCENTETE E\n" +
					"                    INNER JOIN F_DOCLIGNE M \n" +
					"                        ON E.DO_Domaine = M.DO_Domaine \n" +
					"                        AND E.DO_Type = M.DO_Type \n" +
					"                        AND E.cbDO_Piece = M.cbDO_Piece  \n" +
					"                    WHERE E.cbMarq = ?\n" +
					"                    ORDER BY M.cbMarq";

    public static final String listeEntree = "" +
            "DECLARE @doTiers as VARCHAR(30) = ?\n" +
            "                DECLARE @dateDeb as DATE = ?\n" +
            "                DECLARE @dateFin as DATE = ?\n" +
            "                \n" +
            "                SELECT PROT_User,DO_Imprim,CASE WHEN ABS(DATEDIFF(d,GETDATE(),E.DO_Date))>= (select PR_DelaiPreAlert\n" +
            " from P_PREFERENCES) THEN 1 ELSE 0 END DO_Modif,E.cbModification,E.cbMarq,E.DO_Type,ISNULL(MAX(E.N_CatCompta),1) N_CatCompta,E.DO_Domaine,E.DO_Piece,E.DO_Ref,CAST(CAST(E.DO_Date AS DATE) AS VARCHAR(10)) AS DO_Date,E.DO_Tiers as CT_Num, ISNULL(SUM(L.DL_MontantTTC),0) AS ttc,\n" +
            "                 MAX(DE_Intitule) DE_Intitule,'' CT_Intitule,ISNULL(MAX(latitude),0) as latitude,ISNULL(MAX(longitude),0) as longitude,0 as avance\n" +
            "                 ,'' as statut\n" +
            "                 FROM F_DOCENTETE E\n" +
            "                 INNER JOIN F_DEPOT C on C.DE_No=E.DO_Tiers\n" +
            "                 LEFT JOIN F_DOCLIGNE L on E.DO_Piece=L.DO_Piece AND E.DO_Domaine= L.DO_Domaine AND E.DO_Type=L.DO_Type\n" +
            "                 LEFT JOIN F_PROTECTIONCIAL P ON E.cbCreateur = CAST(P.PROT_No AS VARCHAR(5))\n" +
            "                WHERE E.DO_Domaine=2 AND E.DO_Type=20 AND ('0'=@doTiers OR E.DO_Tiers =@doTiers)\n" +
            "                 AND CAST(E.DO_Date as DATE) BETWEEN @dateDeb AND @dateFin\n" +
            "                 GROUP BY PROT_User,DO_Imprim,E.cbModification,E.cbMarq,E.DO_Type,E.DO_Domaine,E.DO_Piece,E.DO_Ref,E.DO_Date,E.DO_Tiers\n" +
            "                    ORDER BY E.cbMarq";

    public static final String listeTransfertDetail =
			"DECLARE @doTiers AS INT = ?\n" +
			"                    DECLARE @dateDeb AS NVARCHAR(20) = ? \n" +
			"                    DECLARE @dateFin AS NVARCHAR(20) = ?\n" +
			"                    SELECT *\n" +
			"                    FROM(\n" +
			"                    SELECT PROT_User,'' DL_PieceBL,DO_Imprim,CASE WHEN ABS(DATEDIFF(d,GETDATE(),E.DO_Date))>= (select PR_DelaiPreAlert\n" +
			"from P_PREFERENCES) THEN 1 ELSE 0 END DO_Modif,E.cbMarq,E.DO_Type,E.DO_Domaine,E.DO_Piece,E.cbDO_Piece,E.DO_Ref,CAST(CAST(E.DO_Date AS DATE) AS VARCHAR(10)) AS DO_Date,E.DO_Tiers as CT_Num,E.DE_No,DE_Intitule\n" +
			"                    ,ISNULL(SUM(L.DL_Qte * DL_CMUP),0) AS ttc\n" +
			"                    FROM F_DOCENTETE E\n" +
			"                    LEFT JOIN F_DOCLIGNE L \n" +
			"                        ON  E.cbDO_Piece=L.cbDO_Piece \n" +
			"                        AND E.DO_Domaine= L.DO_Domaine \n" +
			"                        AND E.DO_Type=L.DO_Type\n" +
			"                    INNER JOIN F_DEPOT DE \n" +
			"                        ON  DE.DE_No=E.DE_No \n" +
			"                    LEFT JOIN F_PROTECTIONCIAL P \n" +
			"                        ON  E.cbCreateur = CAST(P.PROT_No AS VARCHAR(5))\n" +
			"                    WHERE   E.DO_Domaine=4 \n" +
			"                    AND     E.DO_Type=41 \n" +
			"                    AND     ('0'=@doTiers OR E.DE_No =@doTiers) \n" +
			"                    AND     CAST(E.DO_Date as DATE) BETWEEN @dateDeb AND @dateFin\n" +
			"                    GROUP BY PROT_User,DO_Imprim,E.cbMarq,E.DO_Type,E.DO_Domaine,E.DO_Piece,E.cbDO_Piece,E.DO_Ref,E.DO_Date,E.DO_Tiers,E.DE_No,DE_Intitule)A\n" +
			"                    LEFT JOIN(\n" +
			"                    SELECT E.DO_Type AS DO_Type_dest,E.DO_Domaine AS DO_Domaine_dest,E.DO_Piece AS DO_Piece_dest,E.cbDO_Piece AS cbDO_Piece_dest,E.DO_Ref AS DO_Ref_dest,\n" +
			"                    CAST(CAST(E.DO_Date AS DATE) AS VARCHAR(10)) AS DO_Date_dest,E.DO_Tiers as CT_Num_dest,E.DE_No AS DE_No_dest,DE_Intitule AS DE_Intitule_dest\n" +
			"                    ,ISNULL(SUM(L.DL_Qte * DL_CMUP),0) AS ttc_dest\n" +
			"                    FROM F_DOCENTETE E\n" +
			"                    LEFT JOIN F_DOCLIGNE L \n" +
			"                        on  E.cbDO_Piece=L.cbDO_Piece \n" +
			"                        AND E.DO_Domaine= L.DO_Domaine \n" +
			"                        AND E.DO_Type=L.DO_Type\n" +
			"                    INNER JOIN F_DEPOT DE \n" +
			"                        ON  DE.DE_No=E.DE_No \n" +
			"                    WHERE   E.DO_Domaine=4 \n" +
			"                    AND     E.DO_Type=40\n" +
			"                    GROUP BY E.DO_Type,E.DO_Domaine,E.DO_Piece,E.cbDO_Piece,E.DO_Ref,E.DO_Date,E.DO_Tiers,E.DE_No,DE_Intitule) B ON A.cbDO_PIECE=B.cbDO_Piece_dest";
    public static final String listeSortie =
            "DECLARE @doTiers as VARCHAR(30) = ?\n" +
            "                DECLARE @dateDeb as DATE = ?\n" +
            "                DECLARE @dateFin as DATE = ?\n" +
            "SELECT PROT_User,DO_Imprim,CASE WHEN ABS(DATEDIFF(d,GETDATE(),E.DO_Date))>= (select PR_DelaiPreAlert\n" +
                    "from P_PREFERENCES) THEN 1 ELSE 0 END DO_Modif,E.cbModification,E.cbMarq,E.DO_Type,ISNULL(MAX(E.N_CatCompta),1) N_CatCompta,E.DO_Domaine,E.DO_Piece,E.DO_Ref,CAST(CAST(E.DO_Date AS DATE) AS VARCHAR(10)) AS DO_Date,E.DO_Tiers as CT_Num, ISNULL(SUM(L.DL_MontantTTC),0) AS ttc,\n" +
                    "                MAX(DE_Intitule) DE_Intitule,'' CT_Intitule,0 AS avance,ISNULL(MAX(latitude),0) as latitude,ISNULL(MAX(longitude),0) as longitude\n" +
                    "                 ,'' as statut\n" +
                    "                 FROM F_DOCENTETE E\n" +
                    "                 INNER JOIN F_DEPOT C on C.DE_No=E.DO_Tiers\n" +
                    "                 LEFT JOIN  F_DOCLIGNE L on E.DO_Piece=L.DO_Piece AND E.DO_Domaine= L.DO_Domaine AND E.DO_Type=L.DO_Type\n" +
                    "                 LEFT JOIN F_PROTECTIONCIAL P ON E.cbCreateur = CAST(P.PROT_No AS VARCHAR(5))\n" +
                    "                WHERE E.DO_Domaine=2 AND E.DO_Type=21 AND ('0'=@doTiers OR E.DO_Tiers =@doTiers)\n" +
                    "                 AND CAST(E.DO_Date as DATE) BETWEEN @dateDeb AND @dateFin\n" +
                    "                 GROUP BY PROT_User,DO_Imprim,E.cbModification,E.cbMarq,E.DO_Type,E.DO_Domaine,E.DO_Piece,E.DO_Ref,E.DO_Date,E.DO_Tiers\n" +
                    "                    ORDER BY E.cbMarq ";

	public static final String listeTransfertConfirmation =
	"DECLARE @doTiers NVARCHAR(50);\n" +
	"                    DECLARE @dateDeb NVARCHAR(50); \n" +
	"                    DECLARE @dateFin NVARCHAR(50);\n" +
	"                    DECLARE @typeFac NVARCHAR(50);\n" +
	"                    DECLARE @doDomaine INT; \n" +
	"                    DECLARE @doType INT;\n" +
	"                    DECLARE @admin INT;\n" +
	"                    DECLARE @PROT_No INT;\n" +
	"                    SET @doTiers = ?;\n" +
	"                    SET @dateDeb = ?; \n" +
	"                    SET @dateFin = ?;\n" +
	"                    SET @doDomaine = ?; \n" +
	"                    SET @doType = ?;\n" +
	"                    SET @PROT_No = ?;\n" +
	"                    SET @typeFac = ?;\n" +
	"                    SET NOCOUNT ON;\n" +
	"                    CREATE TABLE #TMPDEPOT (DE_No INT);\n" +
	"                    SELECT @admin = CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No = @PROT_No \n" +
	"                    IF (@admin=0)\n" +
	"                    BEGIN \n" +
	"                        INSERT INTO #TMPDEPOT\n" +
	"                        SELECT\tA.DE_No \n" +
	"                        FROM\tF_DEPOT A\n" +
	"                        LEFT JOIN Z_DEPOTUSER D \n" +
	"                            ON  A.DE_No=D.DE_No\n" +
	"                        WHERE\t(1 = (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No=@PROT_No) OR D.PROT_No =@PROT_No)\n" +
	"                        AND     (@doTiers='0' OR @doTiers=A.DE_No)\n" +
	"                        AND     IsPrincipal = 1\n" +
	"                        GROUP BY A.DE_No\n" +
	"                    END\n" +
	"                    ELSE \n" +
	"                    BEGIN\n" +
	"                        INSERT\tINTO #TMPDEPOT \n" +
	"                        SELECT  DE_No \n" +
	"                        FROM    F_DEPOT \n" +
	"                        WHERE   (@doTiers='0' OR @doTiers=DE_No)\n" +
	"                    END ;\n" +
	"                    \n" +
	"                    SELECT PROT_User\n" +
	"                        ,DL_PieceBL = '' \n" +
	"                        ,DO_Imprim\n" +
	"                        ,DO_Modif = CASE WHEN ABS(DATEDIFF(d,GETDATE(),E.DO_Date))>= (select PR_DelaiPreAlert\n" +
	"from P_PREFERENCES) THEN 1 ELSE 0 END \n" +
	"                        ,E.cbModification,E.cbMarq,E.DO_Type,E.DO_Domaine,E.DO_Piece,E.DO_Ref\n" +
	"                        ,N_CatCompta = ISNULL(MAX(E.N_CatCompta),1) \n" +
	"                        ,DO_Date = CAST(CAST(E.DO_Date AS DATE) AS VARCHAR(10))\n" +
	"                        ,CT_Num = E.DO_Coord02\n" +
	"                        ,ttc = ISNULL(SUM(L.DL_MontantTTC),0)\n" +
	"                        ,CT_Intitule = MAX(DS.DE_Intitule)\n" +
	"                        ,DE_Intitule_dest = MAX(DS.DE_Intitule)\n" +
	"                        ,DE_Intitule = MAX(DE.DE_Intitule)\n" +
	"                        ,avance = 0\n" +
	"                        ,latitude = MAX(latitude) \n" +
	"                        ,longitude = MAX(longitude) \n" +
	"                        ,statut = ''\n" +
	"                 FROM  F_DOCENTETE E \n" +
	"                 LEFT JOIN F_DOCLIGNE L \n" +
	"                     on E.cbDO_Piece=L.cbDO_Piece \n" +
	"                     AND E.DO_Domaine= L.DO_Domaine \n" +
	"                    AND E.DO_Type=L.DO_Type\n" +
	"                 INNER JOIN F_DEPOT DE \n" +
	"                     ON DE.DE_No=E.DE_No \n" +
	"                 INNER JOIN F_DEPOT DS \n" +
	"                     ON DS.DE_No=E.DO_Coord02\n" +
	"                 /*INNER JOIN (SELECT cbMarqEntete FROM Z_LIGNE_CONFIRMATION GROUP BY cbMarqEntete) LC \n" +
	"                     ON LC.cbMarqEntete =E.cbMarq*/\n" +
	"                 LEFT JOIN F_PROTECTIONCIAL P \n" +
	"                     ON E.cbCreateur = CAST(P.PROT_No AS VARCHAR(5))\n" +
	"                WHERE   \n" +
	"                ((E.DE_No  IN (SELECT DE_No FROM #TMPDEPOT) AND @typeFac ='Transfert_confirmation')  \n" +
	"                    OR (E.DO_Coord02 IN (SELECT DE_No FROM #TMPDEPOT)  AND @typeFac ='Transfert_valid_confirmation'))\n" +
	"                AND     CAST(E.DO_Date as DATE) BETWEEN @dateDeb AND @dateFin\n" +
	"                AND     E.DO_Domaine = @doDomaine\n" +
	"                AND     E.DO_Type = @doType\n" +
	"                GROUP BY PROT_User,DO_Imprim,E.cbModification,E.cbMarq,E.DO_Type,E.DO_Domaine,E.DO_Piece,E.DO_Ref,E.DO_Date,E.DO_Coord02\n" +
	"                ORDER BY E.cbMarq ";
    public static final String listeTransfert =
            "   DECLARE @doTiers as VARCHAR(30) = ?\n" +
			"                    DECLARE @dateDeb as DATE = ?\n" +
			"                    DECLARE @dateFin as DATE = ?\n" +
			"        SELECT    PROT_User,'' DL_PieceBL,DO_Imprim\n" +
			"                            ,CASE WHEN ABS(DATEDIFF(d,GETDATE(),E.DO_Date))>= (select PR_DelaiPreAlert from P_PREFERENCES) THEN 1 ELSE 0 END DO_Modif\n" +
			"                            ,E.cbModification,E.cbMarq,E.DO_Type,E.DO_Domaine,E.DO_Piece,E.DO_Ref\n" +
			"                            ,ISNULL(MAX(E.N_CatCompta),1) N_CatCompta,CAST(CAST(E.DO_Date AS DATE) AS VARCHAR(10)) AS DO_Date,E.DO_Tiers as CT_Num, ISNULL(SUM(L.DL_MontantTTC),0) AS ttc\n" +
			"                            ,MAX(DS.DE_Intitule) AS CT_Intitule,MAX(DS.DE_Intitule) AS DE_Intitule_dest,MAX(DE.DE_Intitule) AS DE_Intitule,0 as avance,MAX(latitude) latitude,MAX(longitude) longitude,'' as statut\n" +
			"                 FROM       F_DOCENTETE E \n" +
			"                 LEFT JOIN  F_DOCLIGNE L \n" +
			"                    ON  E.cbDO_Piece=L.cbDO_Piece \n" +
			"                    AND E.DO_Domaine= L.DO_Domaine \n" +
			"                    AND E.DO_Type=L.DO_Type\n" +
			"                 INNER JOIN F_DEPOT DE \n" +
			"                     ON DE.DE_No=E.DE_No \n" +
			"                 INNER JOIN F_DEPOT DS \n" +
			"                     ON DS.DE_No=E.DO_Tiers\n" +
			"                 LEFT JOIN F_PROTECTIONCIAL P \n" +
			"                     ON E.cbCreateur = CAST(P.PROT_No AS VARCHAR(5))\n" +
			"                WHERE   ('0' = @doTiers OR (E.DE_No =@doTiers OR E.DO_Tiers =@doTiers))\n" +
			"                AND     E.cbMarq NOT IN (SELECT cbMarqEntete FROM Z_LIGNE_CONFIRMATION GROUP BY cbMarqEntete)\n" +
			"                AND     E.DO_Domaine=2 \n" +
			"                AND     E.DO_Type=23 \n" +
			"                AND     CAST(E.DO_Date as DATE) BETWEEN @dateDeb AND @dateFin\n" +
			"                GROUP BY PROT_User,DO_Imprim,E.cbModification,E.cbMarq,E.DO_Type,E.DO_Domaine,E.DO_Piece,E.DO_Ref,E.DO_Date,E.DO_Tiers\n" +
			"                ORDER BY E.cbMarq";

    public static final String getListeFacture = "" +
			"DECLARE @protNo AS INT\n" +
			"                            ,@doProvenance AS INT\n" +
			"                            ,@deNo AS INT\n" +
			"                            ,@dateDeb AS NVARCHAR(20)\n" +
			"                            ,@dateFin AS NVARCHAR(20)\n" +
			"                            ,@client AS NVARCHAR(50)\n" +
			"                            ,@doDomaine AS INT\n" +
			"                            ,@doType AS INT;\n" +
			"                    SET @protNo = ?;\n" +
			"                    SET @doProvenance = ? ;\n" +
			"                    SET @deNo  = ?;\n" +
			"                    SET @dateDeb  = ?;\n" +
			"                    SET @dateFin  = ?;\n" +
			"                    SET @client = ?;\n" +
			"                    SET @doDomaine  = ?;\n" +
			"                    SET @doType = ?;\n" +
			"        \n" +
			"                    WITH _Depot_ AS (\n" +
			"                        SELECT DE_No,PROT_No\n" +
			"                        FROM(\n" +
			"                            SELECT  fDe.DE_No,fPro.PROT_No\n" +
			"                            FROM    F_DEPOT fDe,F_PROTECTIONCIAL fPro\n" +
			"                            WHERE\t(fPro.PROT_Administrator =1) OR (PROT_Right=1)\n" +
			"                            GROUP BY fDe.DE_No,fPro.PROT_No\n" +
			"                            UNION\n" +
			"                            SELECT  DE_No,Prot_No\n" +
			"                            FROM    Z_DEPOTUSER\n" +
			"                            WHERE   IsPrincipal=1\n" +
			"                        ) Uni\n" +
			"                        WHERE PROT_No = @protNo\n" +
			"                        GROUP BY DE_No,PROT_No\n" +
			"                    ) \n" +
			"                    ,_DocLigne_ AS (\n" +
			"                        SELECT  cbDO_Piece,DO_Type,DO_Domaine,DO_Ref,DO_Date,CT_Num,SUM(DL_MontantTTC) DL_MontantTTC,DL_PieceBL,DE_No \n" +
			"                        FROM    F_DOCLIGNE L \n" +
			"                        GROUP BY cbDO_Piece,DO_Type,DO_Domaine,DO_Ref,DO_Date,CT_Num,DL_PieceBL,DE_No \n" +
			"                    )\n" +
			"                    ,_ReglEch_ AS (\n" +
			"                                    SELECT  DR_No, SUM(RC_MONTANT) avance \n" +
			"                                    FROM    F_REGLECH R \n" +
			"                                    INNER JOIN F_Creglement Re \n" +
			"                                        ON  Re.RG_No=R.RG_No \n" +
			"                                    GROUP BY DR_No)\n" +
			"                    ,_DocRegl_ AS (  SELECT  DO_Domaine,DO_Type,cbDO_Piece,SUM(avance)avance\n" +
			"                                    FROM(   SELECT  DO_Domaine,DO_Type,cbDO_Piece,avance\n" +
			"                                            FROM    F_DOCREGL Re\n" +
			"                                            LEFT JOIN _ReglEch_ A \n" +
			"                                                ON A.DR_No = Re.DR_No)A\n" +
			"                                    GROUP BY DO_Domaine,DO_Type,cbDO_Piece)\n" +
			"                    SELECT  PROT_User,DO_Imprim,DL_PieceBL\n" +
			"                            ,DO_Modif = CASE WHEN ABS(DATEDIFF(d,GETDATE(),E.DO_Date))>= (select PR_DelaiPreAlert FROM P_PREFERENCES) THEN 1 ELSE 0 END \n" +
			"                            ,E.cbModification,E.cbMarq,E.DO_Type,E.DO_Domaine,DE_Intitule,E.DO_Piece\n" +
			"                            ,E.DO_Ref,DO_Date = CAST(CAST(E.DO_Date AS DATE) AS VARCHAR(10)),E.DO_Tiers as CT_Num,CT_Intitule\n" +
			"                            ,ttc = (ISNULL(ROUND(SUM(L.DL_MontantTTC),0),0))\n" +
			"                            ,N_CatCompta = ISNULL(MAX(CASE WHEN E.N_CatCompta=0 THEN (C.N_CatCompta) ELSE (E.N_CatCompta) END),'0') \n" +
			"                            ,latitude = ISNULL(MAX(latitude),0)\n" +
			"                            ,longitude = ISNULL(MAX(longitude),0)\n" +
			"                            ,avance = ISNULL(MAX(avance),0)\n" +
			"                            ,CASE WHEN (SUM(L.DL_MontantTTC)>=0 AND SUM(avance) IS NULL \n" +
			"                                            OR sum(avance)<SUM(L.DL_MontantTTC)) \n" +
			"                                            OR (SUM(L.DL_MontantTTC)<0 AND SUM(avance) IS NULL OR sum(avance)>SUM(L.DL_MontantTTC)) OR  (ISNULL(SUM(L.DL_MontantTTC),0)=0 AND ISNULL(SUM(avance),0)=0 ) OR  (SUM(L.DL_MontantTTC) IS NULL AND SUM(avance) IS NULL) THEN 'crédit' ELSE 'comptant' END AS statut\n" +
			"                    FROM F_DOCENTETE E \n" +
			"                    LEFT JOIN _DocLigne_ L \n" +
			"                        ON  E.cbDO_Piece=L.cbDO_Piece  \n" +
			"                        AND E.DO_Domaine= L.DO_Domaine \n" +
			"                        AND E.DO_Type=L.DO_Type\n" +
			"                    LEFT JOIN _DocRegl_ R \n" +
			"                        ON  R.cbDO_Piece=E.cbDO_Piece \n" +
			"                        AND E.DO_Domaine= R.DO_Domaine \n" +
			"                        AND E.DO_Type=R.DO_Type \n" +
			"                    INNER JOIN F_DEPOT D \n" +
			"                        ON  D.DE_No=E.DE_No \n" +
			"                    INNER JOIN F_COMPTET C \n" +
			"                        ON  C.CT_Num=E.DO_Tiers\n" +
			"                    LEFT JOIN F_PROTECTIONCIAL P \n" +
			"                        ON  E.cbCreateur = CAST(P.PROT_No AS VARCHAR(5))\n" +
			"                    WHERE ((@doProvenance=0 AND E.DO_Provenance NOT IN (1,2,4)) OR (@doProvenance<>0 AND E.DO_Provenance = @doProvenance))\n" +
			"                    AND E.DO_Domaine=@doDomaine AND ((@doType = 67 AND E.DO_Type IN (6,7)) OR E.DO_Type=@doType OR (@doType = 1617 AND E.DO_Type IN (16,17)) ) \n" +
			"                    AND ((0=@deNo AND L.DE_No IN (SELECT DE_No FROM  _Depot_)) OR L.DE_No =@deNo)  \n" +
			"                    AND ('0'=@client OR E.DO_Tiers =@client) \n" +
			"                    AND CAST(E.DO_Date as DATE) >= @dateDeb AND CAST(E.DO_Date as DATE) <= @dateFin\n" +
			"                    GROUP BY PROT_User,DO_Imprim,DL_PieceBL,E.cbModification,E.cbMarq,E.DO_Type,E.DO_Domaine,DE_Intitule,E.DO_Piece,E.DO_Ref,E.DO_Date,E.DO_Tiers,CT_Intitule\n" +
			"                    ORDER BY E.cbMarq ";

    public static final String PR_DelaiPreAlert //
            =   "SELECT PR_DelaiPreAlert "
            +"FROM P_PREFERENCES";

    public static final String getLigneMajAnalytique =
			"DECLARE @cbMarq AS INT = ?;\n" +
					"                    WITH _Taxe_ AS (\n" +
					"                        SELECT\tT.TA_Code\n" +
					"                                ,T.cbTA_Code\n" +
					"                                ,T.CG_Num\n" +
					"                                ,C.CG_Tiers\n" +
					"                                ,T.TA_Provenance\n" +
					"                        FROM F_TAXE T\n" +
					"                        LEFT JOIN F_COMPTEG C \n" +
					"                            ON T.cbCG_Num = C.cbCG_Num\n" +
					"                    )\n" +
					"                    ,_InfoTaxe_ AS (\n" +
					"                        SELECT  COMPTEG_ARTICLE = ISNULL(ACP_ComptaCPT_CompteG,FCP_ComptaCPT_CompteG) \n" +
					"                            ,Cg.CG_Tiers CG_TiersArticle,CodeTaxe1 = TU.TA_Code,CG_NumTaxe1 = TU.CG_Num\n" +
					"                            ,CG_Tiers1 = TU.CG_Tiers,CodeTaxe2 = TD.TA_Code,CG_NumTaxe2 = TD.CG_Num,CG_Tiers2 = TD.CG_Tiers \n" +
					"                            ,CodeTaxe3 = TT.TA_Code,CG_NumTaxe3 = TT.CG_Num,CG_Tiers3 = TT.CG_Tiers\n" +
					"                            ,TA_Provenance1 = TU.TA_Provenance\n" +
					"                            ,TA_Provenance2 = TD.TA_Provenance\n" +
					"                            ,TA_Provenance3 = TT.TA_Provenance\n" +
					"                            ,Art.AR_Ref\n" +
					"                            ,FCP_Champ\n" +
					"                            ,FCP_Type\n" +
					"                        FROM    F_ARTICLE Art \n" +
					"                        LEFT JOIN F_FAMCOMPTA F \n" +
					"                            ON  Art.cbFA_CodeFamille = F.cbFA_CodeFamille  \n" +
					"                        LEFT JOIN F_ARTCOMPTA A \n" +
					"                            ON  A.cbAR_Ref = Art.cbAR_Ref \n" +
					"                            AND ISNULL(ACP_Champ,FCP_Champ) =FCP_Champ \n" +
					"                            AND ISNULL(ACP_Type,FCP_Type)=FCP_Type \n" +
					"                        LEFT JOIN F_COMPTEG Cg \n" +
					"                            ON  Cg.CG_Num = ISNULL(ACP_ComptaCPT_CompteG,FCP_ComptaCPT_CompteG)\n" +
					"                        LEFT JOIN _Taxe_ TU \n" +
					"                            ON TU.cbTA_Code = (CASE WHEN ISNULL(FCP_ComptaCPT_Taxe1,'')  <> ISNULL(ACP_ComptaCPT_Taxe1,'')AND ACP_ComptaCPT_Taxe1 IS NOT NULL THEN\n" +
					"                                                ACP_ComptaCPT_Taxe1 ELSE FCP_ComptaCPT_Taxe1 END)\n" +
					"                        LEFT JOIN _Taxe_ TD \n" +
					"                            ON TD.cbTA_Code = (CASE WHEN ISNULL(FCP_ComptaCPT_Taxe2,'')  <> ISNULL(ACP_ComptaCPT_Taxe2,'')  AND ACP_ComptaCPT_Taxe2 IS NOT NULL THEN \n" +
					"                                                    ACP_ComptaCPT_Taxe2 ELSE FCP_ComptaCPT_Taxe2 END) \n" +
					"                        LEFT JOIN _Taxe_ TT \n" +
					"                            ON TT.cbTA_Code = (CASE WHEN ISNULL(FCP_ComptaCPT_Taxe3,'')  <> ISNULL(ACP_ComptaCPT_Taxe3,'')  AND ACP_ComptaCPT_Taxe3 IS NOT NULL THEN \n" +
					"                                        ACP_ComptaCPT_Taxe3 ELSE FCP_ComptaCPT_Taxe3 END) \n" +
					"                    )\n" +
					"                    ,_Analytique_ AS (\n" +
					"                        SELECT\tA.CbMarq_Ligne,A_Intitule,A.N_Analytique\n" +
					"                                ,EA_Ligne,A.CA_Num,CA_Intitule,EA_Montant\n" +
					"                                ,EA_Quantite,A.cbMarq\n" +
					"                        FROM\tZ_LIGNE_COMPTEA A\n" +
					"                        LEFT JOIN F_COMPTEA B \n" +
					"                            ON\tA.CA_Num = B.CA_Num \n" +
					"                        LEFT JOIN P_Analytique PA \n" +
					"                            ON\tPA.cbIndice = A.N_Analytique\n" +
					"                    )\n" +
					"                    \n" +
					"                    SELECT\tnomFichier =''\n" +
					"                            ,JO_Num =  (SELECT JO_Num  \n" +
					"                                        FROM P_SOUCHEACHAT \n" +
					"                                        WHERE S_Intitule<>'' AND S_Valide=1 AND cbIndice-1 = docE.DO_Souche)\n" +
					"                            ,Annee_Exercice = CAST(YEAR(docE.DO_Date) AS NVARCHAR(10)) + RIGHT('0'+ CAST(MONTH(docE.DO_Date) AS NVARCHAR(10)),2)\n" +
					"                            ,CA_Num = ana.CA_Num\n" +
					"                            ,CG_Num = ISNULL(infT.COMPTEG_ARTICLE,'')\n" +
					"                            ,A_Intitule = ana.A_Intitule\n" +
					"                            ,A_Qte = ana.EA_Quantite\n" +
					"                            ,A_Montant = ana.EA_Montant\n" +
					"                            ,EC_No = ISNULL(docL.cbMarq,'')\n" +
					"                            ,N_Analytique = ana.N_Analytique                            \n" +
					"                    FROM\tF_DOCENTETE docE\n" +
					"                    LEFT JOIN F_DOCLIGNE docL\n" +
					"                        ON\tdocL.DO_Domaine = docE.DO_Domaine\n" +
					"                        AND\tdocL.DO_Type = docE.DO_Type\n" +
					"                        AND\tdocL.DO_Piece = docE.DO_Piece\n" +
					"                    LEFT JOIN F_DOCREGL docR\n" +
					"                        ON\tdocR.DO_Domaine = docE.DO_Domaine\n" +
					"                        AND\tdocR.DO_Type = docE.DO_Type\n" +
					"                        AND\tdocR.DO_Piece = docE.DO_Piece\n" +
					"                    LEFT JOIN F_COMPTET cptT\n" +
					"                        ON\tdocE.DO_Tiers = cptT.CT_Num\n" +
					"                    LEFT JOIN _InfoTaxe_ infT\n" +
					"                        ON\tinfT.FCP_Champ=docE.N_CatCompta \n" +
					"                        AND infT.FCP_Type=docE.DO_Domaine\n" +
					"                        AND infT.AR_Ref=docL.AR_Ref\n" +
					"                    LEFT JOIN _Analytique_ ana\n" +
					"                        ON\tana.CbMarq_Ligne = docL.cbMarq\n" +
					"                    WHERE\tdocE.cbMarq = @cbMarq";
    public static final String getReglementByFacture = "" +
			"DECLARE @cbMarq INT = ?;\n" +
			"\n" +
			"SELECT A.RG_No\n" +
			"FROM F_CREGLEMENT A\n" +
			"INNER JOIN (SELECT  DO_Domaine,DO_Type,DO_Piece,RG_No\n" +
			"            FROM    F_REGLECH\n" +
			"            GROUP BY DO_Domaine,DO_Type,DO_Piece,RG_No)B ON A.RG_No=B.RG_No\n" +
			"INNER JOIN (SELECT\tDO_Domaine,DO_Type,DO_Piece,cbMarq\n" +
			"\t\t\tFROM\tF_DOCENTETE) E\n" +
			"\tON\tB.DO_Domaine = E.DO_Domaine\n" +
			"\tAND B.DO_Type = E.DO_Type\n" +
			"\tAND B.DO_Piece = E.DO_Piece\n" +
			"WHERE\tE.cbMarq = @cbMarq";

    public static final String getEnteteTable //
            =   "DECLARE @doDomaine INT = ?\n" +
			"        DECLARE @doccurent_type INT = ?\n" +
			"        DECLARE @doType INT = ?\n" +
			"        DECLARE @doSouche INT = ?\n" +
			"\t\tDECLARE @doPiece VARCHAR(20)\n" +
			"\t\tDECLARE @letterEntete VARCHAR(20)\n" +
			"\t\tDECLARE @numberEntete VARCHAR(20)\n" +
			"\t\tDECLARE @nb INT = 0;\n" +
			"\n" +
			"\t\tSELECT @doPiece = ISNULL((SELECT DC_Piece\n" +
			"        from F_DOCCURRENTPIECE D\n" +
			"        WHERE DC_Domaine=@doDomaine AND DC_Souche=@doSouche AND DC_IdCol=@doccurent_type),0)\n" +
			"        SELECT\t@nb= COUNT(1)\n" +
			"\t\t\t\t,@doPiece = CASE WHEN @doType = 30 THEN MAX(DO_Piece) \n" +
			"\t\t\t\t\t\t\t\tWHEN @doType <> 30 AND @doPiece ='' THEN MAX(DO_Piece)  \n" +
			"\t\t\t\t\t\t\t\tWHEN @doType <> 30 AND TRY_CAST(@doPiece AS int) IS NOT NULL THEN MAX(DO_Piece) ELSE @doPiece END\n" +
			"        FROM    F_DOCENTETE \n" +
			"        WHERE\t(CASE WHEN @doType <>30 AND DO_Piece = @doPiece AND @doPiece <> '' THEN 1\n" +
			"\t\t\t\t\t\tWHEN @doType <>30 AND @doPiece = '' THEN 1\n" +
			"\t\t\t\t\t\tWHEN @doType <>30 AND TRY_CAST(@doPiece AS int) IS NOT NULL THEN 1\n" +
			"\t\t\t\t\tWHEN @doType = 30 THEN 1 END) = 1\n" +
			"\t\tAND (CASE WHEN @doDomaine = 2 AND DO_Domaine IN (2,4) THEN 1 \n" +
			"                        WHEN @doDomaine <> 2 AND DO_Domaine=@doDomaine THEN 1 END) = 1\n" +
			"        AND     (CASE WHEN @doType=6 AND DO_Type IN(6,7) THEN 1\n" +
			"                        WHEN @doType=16 AND DO_Type IN(16,17) THEN 1\n" +
			"                        WHEN DO_Type NOT IN (16,6) AND @doType=DO_Type THEN 1 END) = 1\n" +
			"\t\t; \n" +
			"\n" +
			"\t\tIF @nb = 0 \n" +
			"\t\t\tSELECT DO_Piece = ISNULL(@doPiece,1)\n" +
			"\t\tELSE \n" +
			"\t\tBEGIN \n" +
			"\t\t\tSELECT\t@numberEntete = SUBSTRING(@doPiece, PATINDEX('%[0-9]%', @doPiece), LEN(@doPiece))\n" +
			"\t\t\t\t\t,@letterEntete = SUBSTRING(@doPiece, 0, (LEN(@doPiece) - LEN(@numberEntete))+1)\n" +
			"\t\t\tSELECT\t@letterEntete = MAX(DO_Piece)\n" +
			"\t\t\tFROM    F_DOCENTETE \n" +
			"\t\t\tWHERE\tDO_Piece LIKE CONCAT(@letterEntete,'%')\n" +
			"\t\t\tAND (CASE WHEN @doDomaine = 2 AND DO_Domaine IN (2,4) THEN 1 \n" +
			"\t\t\t\t\t\t\tWHEN @doDomaine <> 2 AND DO_Domaine=@doDomaine THEN 1 END) = 1\n" +
			"\t\t\tAND     (CASE WHEN @doType=6 AND DO_Type IN(6,7) THEN 1\n" +
			"\t\t\t\t\t\t\tWHEN @doType=16 AND DO_Type IN(16,17) THEN 1\n" +
			"\t\t\t\t\t\t\tWHEN DO_Type NOT IN (16,6) AND @doType=DO_Type THEN 1 END) = 1;\n" +
			"\t\t\tSELECT\t@numberEntete = SUBSTRING(@letterEntete, PATINDEX('%[0-9]%', @letterEntete), LEN(@letterEntete))+1\n" +
			"\t\t\t\t\t,@letterEntete = SUBSTRING(@letterEntete, 0, (LEN(@letterEntete) - LEN(@numberEntete))+1)\n" +
			"\t\t\tSELECT DO_Piece = CONCAT(@letterEntete,@numberEntete)\n" +
			"\t\tEND";

    public static final String getEnteteByDOPiece
    =" DECLARE @do_type INT = ? " +
            " DECLARE @do_domaine INT = ? " +
            " DECLARE @char VARCHAR(35) = ?\n" +
            " DECLARE @num int = ?\n"+
            " \n" +
			"SELECT\t*,CONVERT(char(10), CAST(DO_Date AS DATE),126) AS DO_DateC \n" +
			"FROM\tF_DOCENTETE\n" +
			"WHERE\t(LEN(@char)<>0 AND LEFT(DO_Piece,LEN(@char))=@char AND TRY_CAST(RIGHT(DO_Piece,LEN(DO_Piece)-LEN(@char)) AS INT)= @num) \t\t\t\t \n" +
			"AND \tDO_Domaine=@do_domaine \t\t\t\t \n" +
			"AND\t\tCASE WHEN @do_type=6 AND (DO_Type IN(6,7)) THEN 1  \n" +
			"\t\t\tWHEN @do_type=16 AND (DO_Type IN(16,17)) THEN 1\n" +
			"\t\t\tWHEN DO_Type NOT IN (16,6) AND @do_type=DO_Type THEN 1 END=1";

    public static final String calculDateEcheance
    ="declare @date as date\n" +
            "        declare @date_ech as date\n" +
            "        declare @nbjour as int\n" +
            "        declare @datecivil as date\n" +
            "        declare @day as int\n" +
            "        declare @val as int\n" +
            "        declare @condition as int = ? \n" +
            "        set @val = ? \n" +
            "        set @nbjour = ? \n" +
            "        set @date_ech = ? \n" +
            "        set @date = (select dateadd(day,@nbjour,@date_ech))\n" +
            "        set @datecivil = (select dateadd(month,(@nbjour/30),@date_ech))\n" +
            "        set @day = CASE WHEN @val=0 THEN 1 ELSE @val END\n" +
            "        SELECT CASE WHEN @condition = 0 THEN JourNet WHEN @condition = 1 THEN FinMoisCivil WHEN @condition = 2 THEN FinMois END dateEchCalcule \n" +
            "        FROM ( " +
            "        SELECT\n" +
            "                (SELECT CAST(CASE WHEN @val=0 THEN @date ELSE DATEADD(day,@day-1,CAST(YEAR(@date) AS VARCHAR(4))+'-'+RIGHT(CAST(MONTH(@date)+1 AS VARCHAR(2)),2)+'-01') END AS DATE)AS DATE) AS JourNet,\n" +
            "        (SELECT CASE WHEN @val = 0 THEN EOMONTH(DATEADD(day,@day,EOMONTH(@datecivil)))\n" +
            "        ELSE DATEADD(day,@day,EOMONTH(@datecivil)) END) FinMoisCivil,\n" +
            "            (SELECT CASE WHEN @val = 0 THEN CAST(EOMONTH(@date) AS VARCHAR(10))\n" +
            "        ELSE DATEADD(day,@day,EOMONTH(@date)) END) FinMois)A";

    public static final String getEnteteTicketByDOPiece
    = "SELECT ISNULL(Max(DO_Piece),0) DO_Piece \n"+
            "                FROM(\n"+
            "                        SELECT CAST(DO_Piece AS INT) AS DO_Piece\n"+
            "                        FROM F_DOCENTETE\n"+
            "                        WHERE DO_Domaine=? AND DO_Type = ?\n"+
            "                        UNION\n"+
            "                        SELECT TA_Piece AS DO_Piece\n"+
            "                        FROM F_TICKETARCHIVE)A";

    public static final String listeLigne = "SELECT D.cbMarq\n" +
            "                    FROM F_DOCENTETE E" +
            " INNER JOIN F_DOCLIGNE  D ON E.cbDO_Piece = D.cbDO_Piece AND E.DO_Domaine = D.DO_Domaine AND E.DO_Type = D.DO_Type \n" +
            "                WHERE E.cbMarq = ? " +
			" ORDER BY DL_Ligne ";

    public static final String journeeCloture=
			"DECLARE @date DATE " +
					"       ,@caNo INT; " +
					"SET @date = ? ; " +
					"SET @caNo = ? ; " +
			" SELECT  Nb = count(*) " +
			" FROM    F_DOCENTETE " +
			" WHERE   DO_Cloture=1 " +
			" AND     DO_Date=@date " +
			" AND     CA_No = @caNo";
    public static final String getCbCreateurName =
            "                            SELECT Prot_User \n" +
                    "                    FROM F_DOCENTETE A \n" +
                    "                    INNER JOIN F_PROTECTIONCIAL P ON A.cbCreateur = CAST(P.PROT_No AS VARCHAR(5)) \n" +
                    "                    WHERE A.cbMarq = ?";

    public static final String drRegle ="    SELECT DR_Regle \n" +
                        "                    FROM F_DOCENTETE E  \n" +
                        "                    INNER JOIN F_DOCREGL D ON E.DO_Piece = D.DO_Piece AND E.DO_Domaine = D.DO_Domaine AND E.DO_Type = D.DO_Type \n" +
                        "                    WHERE E.cbMarq = ? ";

    public static final String AvanceDoPiece ="SELECT ROUND(ISNULL(SUM(RC_Montant),0),2) avance_regle\n" +
            "                    FROM F_DOCENTETE E                    \n" +
            "                    LEFT JOIN F_REGLECH D ON E.DO_Piece = D.DO_Piece AND E.DO_Domaine = D.DO_Domaine AND E.DO_Type = D.DO_Type\n" +
            "                    WHERE E.cbMarq = ?";

    public static final String montantRegle ="SELECT ROUND(ISNULL(SUM(DL_MontantTTC),0),2) montantRegle\n" +
            "                    FROM F_DOCENTETE E                    \n" +
            "                    LEFT JOIN F_DOCLIGNE D ON E.DO_Piece = D.DO_Piece AND E.DO_Domaine = D.DO_Domaine AND E.DO_Type = D.DO_Type\n" +
            "                    WHERE E.cbMarq = ?";

    public static final String insertFDocEntete =
            "BEGIN \n" +
                    "SET DATEFORMAT dmy;\n" +
					"DECLARE @DO_Domaine AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Type AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Date AS DATE = ?;\n" +
					"DECLARE @DO_Ref AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Tiers AS NVARCHAR(50) = ?;\n" +
					"DECLARE @CO_No AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Period AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Devise AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Cours AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DE_No AS NVARCHAR(50) = ?;\n" +
					"DECLARE @LI_No AS NVARCHAR(50) = ?;\n" +
					"DECLARE @CT_NumPayeur AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Expedit AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_NbFacture AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_BLFact AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TxEscompte AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Reliquat AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Imprim AS NVARCHAR(50) = ?;\n" +
					"DECLARE @CA_Num AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Coord01 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Coord02 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Coord03 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Coord04 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Souche AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_DateLivr AS DATE = ?;\n" +
					"DECLARE @DO_Tarif AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Colisage AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TypeColis AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Transaction AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Langue AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Regime AS NVARCHAR(50) = ?;\n" +
					"DECLARE @N_CatCompta AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Ventile AS NVARCHAR(50) = ?;\n" +
					"DECLARE @AB_No AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_DebutAbo AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_FinAbo AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_DebutPeriod AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_FinPeriod AS NVARCHAR(50) = ?;\n" +
					"DECLARE @CG_Num AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Statut AS NVARCHAR(50) = ?;\n" +
					"DECLARE @CA_No AS NVARCHAR(50) = ?;\n" +
					"DECLARE @CO_NoCaissier AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Transfere AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Cloture AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_NoWeb AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Attente AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Provenance AS NVARCHAR(50) = ?;\n" +
					"DECLARE @CA_NumIFRS AS NVARCHAR(50) = ?;\n" +
					"DECLARE @MR_No AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TypeFrais AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_ValFrais AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TypeLigneFrais AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TypeFranco AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_ValFranco AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TypeLigneFranco AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Taxe1 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TypeTaux1 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TypeTaxe1 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Taxe2 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TypeTaux2 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TypeTaxe2 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Taxe3 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TypeTaux3 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TypeTaxe3 AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_MajCpta AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Motif AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Contact AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_FactureElec AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_TypeTransac AS NVARCHAR(50) = ?;\n" +
					"DECLARE @CbProt AS NVARCHAR(50) = ?;\n" +
					"DECLARE @CbCreateur AS NVARCHAR(50) = ?;\n" +
					"DECLARE @CbFlag AS NVARCHAR(50) = ?;\n" +
					"DECLARE @VEHICULE AS NVARCHAR(50) = ?;\n" +
					"DECLARE @CHAUFFEUR AS NVARCHAR(50) = ?;\n" +
					"DECLARE @Longitude AS NVARCHAR(50) = ?;\n" +
					"DECLARE @Latitude AS NVARCHAR(50) = ?;\n" +
					"DECLARE @DO_Piece AS NVARCHAR(50) = ?;" +
					"                SET NOCOUNT ON;\n" +
                    "                INSERT INTO [dbo].[F_DOCENTETE]\n" +
                    "                ([DO_Domaine], [DO_Type], [DO_Date], [DO_Ref]\n" +
                    "                , [DO_Tiers], [CO_No], [DO_Period], [DO_Devise]\n" +
                    "                , [DO_Cours], [DE_No], [LI_No]\n" +
                    "                , [CT_NumPayeur], [DO_Expedit], [DO_NbFacture], [DO_BLFact]\n" +
                    "                , [DO_TxEscompte], [DO_Reliquat], [DO_Imprim], [CA_Num]\n" +
                    "                , [DO_Coord01], [DO_Coord02], [DO_Coord03], [DO_Coord04]\n" +
                    "                , [DO_Souche], [DO_DateLivr], [DO_Condition], [DO_Tarif]\n" +
                    "                , [DO_Colisage], [DO_TypeColis], [DO_Transaction], [DO_Langue]\n" +
                    "                , [DO_Ecart], [DO_Regime], [N_CatCompta], [DO_Ventile]\n" +
                    "                , [AB_No], [DO_DebutAbo], [DO_FinAbo], [DO_DebutPeriod]\n" +
                    "                , [DO_FinPeriod], [CG_Num], [DO_Statut], [DO_Heure]\n" +
                    "                , [CA_No], [CO_NoCaissier]\n" +
                    "                , [DO_Transfere], [DO_Cloture], [DO_NoWeb], [DO_Attente]\n" +
                    "                , [DO_Provenance], [CA_NumIFRS], [MR_No], [DO_TypeFrais]\n" +
                    "                , [DO_ValFrais], [DO_TypeLigneFrais], [DO_TypeFranco], [DO_ValFranco]\n" +
                    "                    , [DO_TypeLigneFranco], [DO_Taxe1], [DO_TypeTaux1], [DO_TypeTaxe1]\n" +
                    "                    , [DO_Taxe2], [DO_TypeTaux2], [DO_TypeTaxe2], [DO_Taxe3]\n" +
                    "                    , [DO_TypeTaux3], [DO_TypeTaxe3], [DO_MajCpta], [DO_Motif]\n" +
                    "                        , [CT_NumCentrale], [DO_Contact], [DO_FactureElec], [DO_TypeTransac]\n" +
                    "                        , [cbProt], [cbCreateur], [cbModification], [cbReplication]\n" +
                    "                        , [cbFlag], [VEHICULE], [CHAUFFEUR]\n" +
                    "                        , [longitude], [latitude],[DO_Piece])\n" +
                    "                        VALUES\n" +
                    "                        (/*DO_Domaine*/@DO_Domaine,/*DO_Type*/@DO_Type,/*DO_Date*/@DO_Date,/*DO_Ref*/@DO_Ref\n" +
                    "                            ,/*DO_Tiers*/@DO_Tiers,/*CO_No*/@CO_No,/*DO_Period*/@DO_Period,/*DO_Devise*/@DO_Devise\n" +
                    "                            ,/*DO_Cours*/@DO_Cours,/*DE_No*/@DE_No,/*LI_No*/ @LI_No\n" +
                    "                        ,/*CT_NumPayeur*/@CT_NumPayeur   \n" +
                    "                        ,/*DO_Expedit*/@DO_Expedit,/*DO_NbFacture*/@DO_NbFacture,/*DO_BLFact*/@DO_BLFact\n" +
                    "                        ,/*DO_TxEscompte*/@DO_TxEscompte,/*DO_Reliquat*/@DO_Reliquat,/*DO_Imprim*/@DO_Imprim,/*CA_Num*/@CA_Num\n" +
                    "                        ,/*DO_Coord01*/@DO_Coord01,/*DO_Coord02*/@DO_Coord02,/*DO_Coord03*/@DO_Coord03,/*DO_Coord04*/@DO_Coord04\n" +
                    "                        ,/*DO_Souche*/@DO_Souche,/*DO_DateLivr*/@DO_DateLivr,/*DO_Condition*/1,/*DO_Tarif*/@DO_Tarif\n" +
                    "                        ,/*DO_Colisage*/@DO_Colisage,/*DO_TypeColis*/@DO_TypeColis,/*DO_Transaction*/@DO_Transaction,/*DO_Langue*/@DO_Langue\n" +
                    "                        ,/*DO_Ecart*/0,/*DO_Regime*/@DO_Regime,/*N_CatCompta*/ @N_CatCompta ,/*DO_Ventile*/@DO_Ventile\n" +
                    "                        ,/*AB_No*/@AB_No,/*DO_DebutAbo*/@DO_DebutAbo,/*DO_FinAbo*/@DO_FinAbo,/*DO_DebutPeriod*/@DO_DebutPeriod\n" +
                    "                        ,/*DO_FinPeriod*/@DO_FinPeriod,/*CG_Num*/@CG_Num\n" +
                    "\t\t\t\t\t\t,/*DO_Statut*/@DO_Statut,/*DO_Heure*/'000' + CAST(DATEPART(HOUR, GETDATE()) as VARCHAR(2)) + CAST(DATEPART(MINUTE, GETDATE()) as VARCHAR(2)) + CAST(DATEPART(SECOND, GETDATE()) as VARCHAR(2))\n" +
                    "                        ,/*CA_No*/@CA_No,/*CO_NoCaissier*/@CO_NoCaissier,/*DO_Transfere*/@DO_Transfere\n" +
                    "                        ,/*DO_Cloture*/@DO_Cloture,/*DO_NoWeb*/@DO_NoWeb,/*DO_Attente*/@DO_Attente,/*DO_Provenance*/@DO_Provenance\n" +
                    "                        ,/*CA_NumIFRS*/@CA_NumIFRS,/*MR_No*/@MR_No,/*DO_TypeFrais*/@DO_TypeFrais,/*DO_ValFrais*/@DO_ValFrais\n" +
                    "                        ,/*DO_TypeLigneFrais*/@DO_TypeLigneFrais,/*DO_TypeFranco*/@DO_TypeFranco,/*DO_ValFranco*/@DO_ValFranco,/*DO_TypeLigneFranco*/@DO_TypeLigneFranco\n" +
                    "                        ,/*DO_Taxe1*/@DO_Taxe1,/*DO_TypeTaux1*/@DO_TypeTaux1,/*DO_TypeTaxe1*/@DO_TypeTaxe1,/*DO_Taxe2*/@DO_Taxe2\n" +
                    "                        ,/*DO_TypeTaux2*/@DO_TypeTaux2,/*DO_TypeTaxe2*/@DO_TypeTaxe2,/*DO_Taxe3*/@DO_Taxe3,/*DO_TypeTaux3*/@DO_TypeTaux3\n" +
                    "                        ,/*DO_TypeTaxe3*/@DO_TypeTaxe3,/*DO_MajCpta*/@DO_MajCpta,/*DO_Motif*/@DO_Motif,/*CT_NumCentrale*/NULL\n" +
                    "                        ,/*DO_Contact*/@DO_Contact,/*DO_FactureElec*/@DO_FactureElec,/*DO_TypeTransac*/@DO_TypeTransac,/*cbProt*/@cbProt\n" +
                    "                        ,/*cbCreateur*/@cbCreateur,/*cbModification*/GETDATE(),/*cbReplication*/0,/*cbFlag*/@cbFlag\n" +
                    "                        ,/*VEHICULE*/@VEHICULE,/*CHAUFFEUR*/@CHAUFFEUR,/*longitude*/@longitude,/*latitude*/@latitude,/*DO_Piece*/@DO_Piece );\n" +
                    "                        select @@IDENTITY as cbMarq\n" +
                    "\t\t\t\t\t\tEND";

	public static final String getDateEcgetTiersheance =
			"SELECT CAST(ISNULL(DATEADD(DAY,ER_NbJour,?),?) AS DATE) date_ech\n"+
					"                    FROM F_COMPTET C\n"+
					"                    LEFT JOIN F_MODELER M ON C.MR_No = M.MR_No\n"+
					"                    LEFT JOIN (SELECT * FROM F_EMODELER EM WHERE N_Reglement=1) EM ON EM.MR_No = M.MR_No\n"+
					"                    WHERE CT_Num=?";

	public static final String saisie_comptable = "DECLARE @cbMarq AS INT;\n" +
			"DECLARE @pLigneNeg INT;\n" +
			"SELECT @pLigneNeg = P_LigneNeg FROM P_PARAMETRECIAL;\n" +
			"\n" +
			"SET @cbMarq = ?;\n" +
			"\n" +
			"\n" +
			"WITH _MontantRegle_ AS (\n" +
			"\tSELECT  ROUND(ISNULL(SUM(DL_MontantTTC),0),2) montantRegle,e.cbMarq\n" +
			"\tFROM    F_DOCENTETE E                    \n" +
			"\tLEFT JOIN F_DOCLIGNE D \n" +
			"\t\tON  E.cbDO_Piece = D.cbDO_Piece\t\t\n" +
			"\t\tAND E.DO_Domaine = D.DO_Domaine \n" +
			"\t\tAND E.DO_Type = D.DO_Type\n" +
			"\tWHERE   E.cbMarq = @cbMarq\n" +
			"\tGROUP BY e.cbMarq\n" +
			")\n" +
			",_Avance_ AS (\n" +
			"SELECT    ROUND(ISNULL(SUM(RC_Montant),0),2) avance_regle,e.cbMarq\n" +
			"FROM    F_DOCENTETE E                    \n" +
			"LEFT JOIN F_REGLECH D \n" +
			"    ON  E.cbDO_Piece = D.cbDO_Piece \n" +
			"    AND E.DO_Domaine = D.DO_Domaine \n" +
			"    AND E.DO_Type = D.DO_Type\n" +
			"WHERE E.cbMarq = @cbMarq\n" +
			"GROUP BY e.cbMarq\n" +
			")\n" +
			",_Taxe_ AS (\n" +
			"    SELECT\tT.TA_Code\n" +
			"            ,T.cbTA_Code\n" +
			"            ,T.CG_Num\n" +
			"            ,C.CG_Tiers\n" +
			"\t\t\t,T.TA_Provenance\n" +
			"    FROM F_TAXE T\n" +
			"    LEFT JOIN F_COMPTEG C \n" +
			"        ON T.cbCG_Num = C.cbCG_Num\n" +
			")\n" +
			",_InfoTaxe_ AS (\n" +
			"\tSELECT  COMPTEG_ARTICLE = ISNULL(ACP_ComptaCPT_CompteG,FCP_ComptaCPT_CompteG) \n" +
			"        ,Cg.CG_Tiers CG_TiersArticle,CodeTaxe1 = TU.TA_Code,CG_NumTaxe1 = TU.CG_Num\n" +
			"        ,CG_Tiers1 = TU.CG_Tiers,CodeTaxe2 = TD.TA_Code,CG_NumTaxe2 = TD.CG_Num,CG_Tiers2 = TD.CG_Tiers \n" +
			"        ,CodeTaxe3 = TT.TA_Code,CG_NumTaxe3 = TT.CG_Num,CG_Tiers3 = TT.CG_Tiers\n" +
			"\t\t,TA_Provenance1 = TU.TA_Provenance\n" +
			"\t\t,TA_Provenance2 = TD.TA_Provenance\n" +
			"\t\t,TA_Provenance3 = TT.TA_Provenance\n" +
			"\t\t,Art.AR_Ref\n" +
			"\t\t,FCP_Champ\n" +
			"\t\t,FCP_Type\n" +
			"\tFROM    F_ARTICLE Art \n" +
			"\tLEFT JOIN F_FAMCOMPTA F \n" +
			"\t\tON  Art.cbFA_CodeFamille = F.cbFA_CodeFamille  \n" +
			"\tLEFT JOIN F_ARTCOMPTA A \n" +
			"\t\tON  A.cbAR_Ref = Art.cbAR_Ref \n" +
			"\t\tAND ISNULL(ACP_Champ,FCP_Champ) =FCP_Champ \n" +
			"\t\tAND ISNULL(ACP_Type,FCP_Type)=FCP_Type \n" +
			"\tLEFT JOIN F_COMPTEG Cg \n" +
			"\t\tON  Cg.CG_Num = ISNULL(ACP_ComptaCPT_CompteG,FCP_ComptaCPT_CompteG)\n" +
			"\tLEFT JOIN _Taxe_ TU \n" +
			"\t\tON TU.cbTA_Code = (CASE WHEN ISNULL(FCP_ComptaCPT_Taxe1,'')  <> ISNULL(ACP_ComptaCPT_Taxe1,'')AND ACP_ComptaCPT_Taxe1 IS NOT NULL THEN\n" +
			"\t\t\t\t\t\t\tACP_ComptaCPT_Taxe1 ELSE FCP_ComptaCPT_Taxe1 END)\n" +
			"\tLEFT JOIN _Taxe_ TD \n" +
			"\t\tON TD.cbTA_Code = (CASE WHEN ISNULL(FCP_ComptaCPT_Taxe2,'')  <> ISNULL(ACP_ComptaCPT_Taxe2,'')  AND ACP_ComptaCPT_Taxe2 IS NOT NULL THEN \n" +
			"\t\t\t\t\t\t\t\tACP_ComptaCPT_Taxe2 ELSE FCP_ComptaCPT_Taxe2 END) \n" +
			"\tLEFT JOIN _Taxe_ TT \n" +
			"\t\tON TT.cbTA_Code = (CASE WHEN ISNULL(FCP_ComptaCPT_Taxe3,'')  <> ISNULL(ACP_ComptaCPT_Taxe3,'')  AND ACP_ComptaCPT_Taxe3 IS NOT NULL THEN \n" +
			"\t\t\t\t\tACP_ComptaCPT_Taxe3 ELSE FCP_ComptaCPT_Taxe3 END) \n" +
			")\n" +
			",_docEntete_ AS (\n" +
			"\tSELECT\tnomFichier =''\n" +
			"\t\t\t,JO_Num = CASE WHEN docE.DO_Domaine = 0 THEN \n" +
			"\t\t\t\t\t\t\t\t(SELECT JO_Num \n" +
			"\t\t\t\t\t\t\t\tFROM P_SOUCHEVENTE \n" +
			"\t\t\t\t\t\t\t\tWHERE S_Intitule<>'' AND S_Valide=1 AND cbIndice-1 = docE.DO_Souche)\n" +
			"\t\t\t\t\t\t\tELSE \n" +
			"\t\t\t\t\t\t\t\t(SELECT JO_Num  \n" +
			"\t\t\t\t\t\t\t\tFROM P_SOUCHEACHAT \n" +
			"\t\t\t\t\t\t\t\tWHERE S_Intitule<>'' AND S_Valide=1 AND cbIndice-1 = docE.DO_Souche)\n" +
			"\t\t\t\t\t    END\n" +
			"\t\t\t,docE.DO_Date\n" +
			"\t\t\t,Annee_Exercice = CAST(YEAR(docE.DO_Date) AS NVARCHAR(10)) + RIGHT('0'+ CAST(MONTH(docE.DO_Date) AS NVARCHAR(10)),2)\n" +
			"\t\t\t,EC_Jour = DAY(docE.DO_Date)\n" +
			"\t\t\t,EC_RefPiece = docE.DO_Piece\n" +
			"\t\t\t,EC_Reference = docE.DO_Ref\n" +
			"\t\t\t,CG_Num  = ISNULL(cptT.CG_NumPrinc,'')\n" +
			"\t\t\t,EC_Sens =\tCASE WHEN docE.DO_Domaine = 0 AND cptT.CT_Num IS NULL THEN \n" +
			"\t\t\t\t\t\t\t\t\tCASE WHEN docE.DO_Provenance IN (2) AND @pLigneNeg = 1 THEN 0 ELSE 1 END\n" +
			"\t\t\t\t\t\t\t WHEN docE.DO_Domaine = 1 AND cptT.CT_Num IS NOT NULL THEN \n" +
			"\t\t\t\t\t\t\t\t\tCASE WHEN DO_Provenance IN (2) AND @pLigneNeg = 1 THEN 0 ELSE 1 END\n" +
			"\t\t\t\t\t\tELSE \n" +
			"\t\t\t\t\t\t\tCASE WHEN DO_Provenance IN (2) AND @pLigneNeg = 1 THEN 1 ELSE 0 END\n" +
			"\t\t\t\t\t\tEND\n" +
			"\t\t\t,EC_StatusRegle = CASE WHEN abs(mor.montantRegle - ava.avance_regle)<5 THEN 2 ELSE 0 END\n" +
			"\t\t\t,EC_MontantRegle = CASE WHEN ava.avance_regle<>0 THEN ava.avance_regle ELSE 0 END\n" +
			"\t\t\t,MontantRegle = CASE WHEN DO_Provenance IN (1,2) AND @pLigneNeg = 0 THEN \n" +
			"\t\t\t\t\t\t\t\t\t-mor.MontantRegle\n" +
			"\t\t\t\t\t\t\t\t\tELSE mor.MontantRegle END\n" +
			"\t\t\t,TA_Provenance = 0\n" +
			"\t\t\t,CG_NumCont= ISNULL(infT.COMPTEG_ARTICLE,'')\n" +
			"\t\t\t,CT_Num = docE.DO_Tiers\n" +
			"\t\t\t,CT_NumCont = ''\n" +
			"\t\t\t,EC_Intitule = 'fact '+compt.CT_Intitule\n" +
			"\t\t\t,N_Reglement = 1\n" +
			"\t\t\t,EC_Echeance = docR.DR_Date\n" +
			"\t\t\t,TA_Code = ''\n" +
			"\t\t\t,CbMarq = @cbMarq\n" +
			"\t\t\t,position = ROW_NUMBER() OVER (PARTITION BY docE.DO_Piece ORDER BY infT.COMPTEG_ARTICLE DESC)\n" +
			"\t\tFROM F_DOCENTETE docE\n" +
			"\t\tLEFT JOIN F_COMPTET compt\n" +
			"\t\t    ON  compt.CT_Num = docE.DO_Tiers\n" +
			"\t\tLEFT JOIN F_DOCREGL docR\n" +
			"\t\t\tON\tdocR.DO_Domaine = docE.DO_Domaine\n" +
			"\t\t\tAND\tdocR.DO_Type = docE.DO_Type\n" +
			"\t\t\tAND\tdocR.cbDO_Piece = docE.cbDO_Piece\n" +
			"\t\tLEFT JOIN F_DOCLIGNE docL\n" +
			"\t\t\tON\tdocL.DO_Domaine = docE.DO_Domaine\n" +
			"\t\t\tAND\tdocL.DO_Type = docE.DO_Type\n" +
			"\t\t\tAND\tdocL.cbDO_Piece = docE.cbDO_Piece\n" +
			"\t\tLEFT JOIN _InfoTaxe_ infT\n" +
			"\t\t\tON\tinfT.FCP_Champ=docE.N_CatCompta \n" +
			"\t\t\tAND infT.FCP_Type=docE.DO_Domaine\n" +
			"\t\t\tAND infT.AR_Ref=docL.AR_Ref\n" +
			"\t\tLEFT JOIN _Avance_ ava\n" +
			"\t\t\tON ava.cbMarq = docE.cbMarq\n" +
			"\t\tLEFT JOIN _MontantRegle_ mor\n" +
			"\t\t\tON mor.cbMarq = docE.cbMarq\n" +
			"\t\tLEFT JOIN F_COMPTET cptT\n" +
			"\t\t\tON\tdocE.cbDO_Tiers = cptT.cbCT_Num\n" +
			"\t\tWHERE docE.cbMarq = @cbMarq\n" +
			")\n" +
			", _Lettrage_ AS (\n" +
			"\tSELECT  lettrage = ISNULL(lettrage,'A')\n" +
			"\tFROM (\n" +
			"\t\tSELECT  lettrage = CHAR(ASCII(MAX(EC_Lettrage))+1)\n" +
			"\t\tFROM    F_ECRITUREC A\n" +
			"\t\tWHERE   EC_Lettre=1\n" +
			"\t\tAND\t\tCT_Num = (SELECT MAX(CT_Num) FROM _docEntete_ )\n" +
			"\t\tAND\t\tCG_Num = (SELECT MAX(CG_Num) FROM _docEntete_ ) \n" +
			"\t\tAND     CAST(DATEADD(DAY,A.EC_Jour-1,A.JM_Date) AS DATE) = (SELECT MAX(DO_Date) FROM _docEntete_) \n" +
			"\t) A\n" +
			")\n" +
			",_docLigne_ AS (\n" +
			"\tSELECT\tnomFichier =''\n" +
			"\t\t\t,JO_Num = CASE WHEN docE.DO_Domaine = 0 THEN \n" +
			"\t\t\t\t\t\t\t\t(SELECT JO_Num \n" +
			"\t\t\t\t\t\t\t\tFROM P_SOUCHEVENTE \n" +
			"\t\t\t\t\t\t\t\tWHERE S_Intitule<>'' AND S_Valide=1 AND cbIndice-1 = docE.DO_Souche)\n" +
			"\t\t\t\t\t\t\tELSE \n" +
			"\t\t\t\t\t\t\t\t(SELECT JO_Num  \n" +
			"\t\t\t\t\t\t\t\tFROM P_SOUCHEACHAT \n" +
			"\t\t\t\t\t\t\t\tWHERE S_Intitule<>'' AND S_Valide=1 AND cbIndice-1 = docE.DO_Souche)\n" +
			"\t\t\t\t\t    END\n" +
			"\t\t\t,docE.DO_Date\n" +
			"\t\t\t,Annee_Exercice = CAST(YEAR(docE.DO_Date) AS NVARCHAR(10)) + RIGHT('0'+ CAST(MONTH(docE.DO_Date) AS NVARCHAR(10)),2)\n" +
			"\t\t\t,EC_Jour = DAY(docE.DO_Date)\n" +
			"\t\t\t,EC_RefPiece = docE.DO_Piece\n" +
			"\t\t\t,EC_Reference = docE.DO_Ref\n" +
			"\t\t\t,CG_Num=ISNULL(infT.COMPTEG_ARTICLE,'')\n" +
			"\t\t\t,EC_Sens =\tCASE WHEN docE.DO_Domaine = 0 THEN \n" +
			"\t\t\t\t\t\t\tCASE WHEN docE.DO_Provenance IN (2) AND @pLigneNeg = 1 THEN 0 ELSE 1 END\n" +
			"\t\t\t\t\t\tELSE \n" +
			"\t\t\t\t\t\t\tCASE WHEN DO_Provenance IN (2) AND @pLigneNeg = 1 THEN 1 ELSE 0 END\n" +
			"\t\t\t\t\t\tEND\n" +
			"\t\t\t,EC_StatusRegle = 0\n" +
			"\t\t\t,EC_MontantRegle = 0\n" +
			"\t\t\t,MontantRegle = (CASE WHEN docE.DO_Provenance IN (1,2) AND @pLigneNeg = 0 THEN -1 ELSE 1 END) * docL.DL_MontantHT \n" +
			"\t\t\t,docL.DL_MontantTTC\n" +
			"\t\t\t,TA_Provenance = ISNULL(infT.TA_Provenance1+1,0)\n" +
			"\t\t\t,CG_NumCont  =  ISNULL(cptT.CG_NumPrinc,'')\n" +
			"\t\t\t,CT_Num = ''\n" +
			"\t\t\t,CT_NumCont = ISNULL(docE.DO_Tiers,'')\n" +
			"\t\t\t,EC_Intitule = 'fact '+compt.CT_Intitule\n" +
			"\t\t\t,N_Reglement = 0\n" +
			"\t\t\t,EC_Echeance = ''\n" +
			"\t\t\t,TA_Code = ISNULL(infT.CodeTaxe1,'')\n" +
			"\t\t\t,cptT.CT_Lettrage\n" +
			"\t\t\t,docL.AR_Ref\n" +
			"\t\t\t,docE.N_CatCompta\n" +
			"\t\t\t,docE.DO_Domaine\n" +
			"\t\t\t,docE.DO_Type\n" +
			"\t\t\t,docE.DO_Piece\n" +
			"\t\t\t,docE.cbDO_Piece\n" +
			"\t\t\t,CbMarq = @cbMarq\n" +
			"\t\t\t,docE.DO_Provenance\n" +
			"\t\t\t,CASE WHEN docL.DL_TypeTaux1=0 THEN docL.DL_MontantHT*(docL.DL_Taxe1/100) ELSE CASE WHEN docL.DL_TypeTaux1=1 THEN docL.DL_Taxe1*docL.DL_Qte ELSE docL.DL_Taxe1 END END MT_Taxe1\n" +
			"            ,CASE WHEN docL.DL_TypeTaux2=0 THEN docL.DL_MontantHT*(docL.DL_Taxe2/100) ELSE CASE WHEN docL.DL_TypeTaux2=1 THEN docL.DL_Taxe2*docL.DL_Qte ELSE docL.DL_Taxe2 END END MT_Taxe2\n" +
			"            ,CASE WHEN docL.DL_TypeTaux3=0 THEN docL.DL_MontantHT*(docL.DL_Taxe3/100) ELSE CASE WHEN docL.DL_TypeTaux3=1 THEN docL.DL_Taxe3*docL.DL_Qte ELSE docL.DL_Taxe3 END END MT_Taxe3\n" +
			"                            \n" +
			"\t\tFROM F_DOCENTETE docE\n" +
			"\t\tLEFT JOIN F_COMPTET compt\n" +
			"\t\t    ON  compt.CT_Num = docE.DO_Tiers\n" +
			"\t\tLEFT JOIN F_DOCLIGNE docL\n" +
			"\t\t\tON\tdocL.DO_Domaine = docE.DO_Domaine\n" +
			"\t\t\tAND\tdocL.DO_Type = docE.DO_Type\n" +
			"\t\t\tAND\tdocL.cbDO_Piece = docE.cbDO_Piece\n" +
			"\t\tLEFT JOIN _Avance_ ava\n" +
			"\t\t\tON ava.cbMarq = docE.cbMarq\n" +
			"\t\tLEFT JOIN _MontantRegle_ mor\n" +
			"\t\t\tON mor.cbMarq = docE.cbMarq\n" +
			"\t\tLEFT JOIN F_COMPTET cptT\n" +
			"\t\t\tON\tdocE.DO_Tiers = cptT.CT_Num\n" +
			"\t\tLEFT JOIN _InfoTaxe_ infT\n" +
			"\t\t\tON\tinfT.FCP_Champ=docE.N_CatCompta \n" +
			"\t\t\tAND infT.FCP_Type=docE.DO_Domaine\n" +
			"\t\t\tAND infT.AR_Ref=docL.AR_Ref\n" +
			"\t\tWHERE docE.cbMarq = @cbMarq\n" +
			")\n" +
			",_Ligne_ AS (\n" +
			"\tSELECT\tnomFichier,JO_Num,DO_Date,Annee_Exercice,EC_Jour,EC_RefPiece\n" +
			"\t\t\t,EC_Reference,CG_Num,EC_Sens,EC_StatusRegle,EC_MontantRegle\n" +
			"\t\t\t,MontantRegle = CASE WHEN DR_Montant = 0 THEN MontantRegle - DR_MontantTotal ELSE DR_Montant END\n" +
			"\t\t\t,DL_MontantTTC = CASE WHEN DR_Montant = 0 THEN DL_MontantTTC - DR_MontantTotal ELSE DR_Montant END\n" +
			"\t\t\t,TA_Provenance\n" +
			"\t\t\t,CG_NumCont,CT_Num,CT_NumCont,EC_Intitule,docL.N_Reglement\n" +
			"\t\t\t,EC_Echeance,TA_Code,CT_Lettrage,N_CatCompta,docL.DO_Domaine\n" +
			"\t\t\t,docL.DO_Type,docL.DO_Piece,docL.cbDO_Piece,docL.CbMarq\n" +
			"\t\t\t,docR.DR_Montant\n" +
			"\t\t\t,docR.DR_MontantTotal,docL.DO_Provenance\n" +
			"\tFROM\t(SELECT nomFichier,JO_Num,DO_Date,Annee_Exercice,EC_Jour,EC_RefPiece\n" +
			"\t\t\t,EC_Reference,CG_Num,EC_Sens,EC_StatusRegle,EC_MontantRegle\n" +
			"\t\t\t,MontantRegle = SUM (MontantRegle),DL_MontantTTC = SUM(DL_MontantTTC),TA_Provenance\n" +
			"\t\t\t,CG_NumCont,CT_Num,CT_NumCont,EC_Intitule,docL.N_Reglement\n" +
			"\t\t\t,EC_Echeance,TA_Code,CT_Lettrage,N_CatCompta,docL.DO_Domaine\n" +
			"\t\t\t,docL.DO_Type,docL.DO_Piece,docL.cbDO_Piece,docL.CbMarq,docL.DO_Provenance\n" +
			"\t\t\t\n" +
			"\t\t\tFROM _docLigne_ docL\n" +
			"\t\t\tGROUP BY nomFichier,JO_Num,DO_Date,Annee_Exercice,EC_Jour,EC_RefPiece\n" +
			"\t\t\t,EC_Reference,CG_Num,EC_Sens,EC_StatusRegle,EC_MontantRegle\n" +
			"\t\t\t,TA_Provenance\n" +
			"\t\t\t,CG_NumCont,CT_Num,CT_NumCont,EC_Intitule,docL.N_Reglement\n" +
			"\t\t\t,EC_Echeance,TA_Code,CT_Lettrage,N_CatCompta,docL.DO_Domaine,docL.DO_Provenance\n" +
			"\t\t\t,docL.DO_Type,docL.DO_Piece,docL.cbDO_Piece,docL.CbMarq) docL\n" +
			"\tLEFT JOIN (SELECT DO_Domaine,DO_Type,cbDO_Piece,DR_Date,DR_Montant,DR_MontantTotal = SUM(DR_Montant) OVER (PARTITION BY DO_Domaine,DO_Type,cbDO_Piece)\n" +
			"\t\t\t\tFROM F_DOCREGL \n" +
			"\t\t\t\tGROUP BY DO_Domaine,DO_Type,DR_Date,cbDO_Piece,DR_Montant) docR\n" +
			"\t\tON\tdocR.DO_Domaine = docL.DO_Domaine\n" +
			"\t\tAND\tdocR.DO_Type = docL.DO_Type\n" +
			"\t\tAND\tdocR.cbDO_Piece = docL.cbDO_Piece\n" +
			")\n" +
			"\n" +
			",_PivotInfoTaxe_ AS (\n" +
			"\tSELECT COMPTEG_ARTICLE, Taxe, CodeTaxe,\n" +
			"\t\t\t\tCG_TiersArticle, CG_Tiers1\n" +
			"\t\t\t\t,CG_Tiers2,CG_Tiers3,CG_NumTaxe1,CG_NumTaxe2,CG_NumTaxe3,TA_Provenance1\n" +
			"\t\t\t\t,TA_Provenance2,TA_Provenance3,AR_Ref,FCP_Champ,FCP_Type\n" +
			"\tFROM   \n" +
			"\t   (SELECT\tCOMPTEG_ARTICLE, CG_TiersArticle, CodeTaxe1,CodeTaxe2, CodeTaxe3, CG_Tiers1\n" +
			"\t\t\t\t,CG_Tiers2,CG_Tiers3,CG_NumTaxe1,CG_NumTaxe2,CG_NumTaxe3,TA_Provenance1\n" +
			"\t\t\t\t,TA_Provenance2,TA_Provenance3,AR_Ref,FCP_Champ,FCP_Type\n" +
			"\t   FROM _InfoTaxe_) p  \n" +
			"\tUNPIVOT  \n" +
			"\t   (CodeTaxe FOR Taxe IN   \n" +
			"\t\t  (CodeTaxe1, CodeTaxe2, CodeTaxe3)  \n" +
			"\t)AS unpvt  \n" +
			")\n" +
			"\n" +
			",_LigneTaxe_ AS (\n" +
			"\n" +
			"\t\tSELECT nomFichier\n" +
			"\t\t\t\t,JO_Num\n" +
			"\t\t\t\t,DO_Date\n" +
			"\t\t\t\t,Annee_Exercice\n" +
			"\t\t\t\t,EC_Jour\n" +
			"\t\t\t\t,EC_RefPiece\n" +
			"\t\t\t\t,EC_Reference\n" +
			"\t\t\t\t,CG_Num\n" +
			"\t\t\t\t,EC_Sens\n" +
			"\t\t\t\t,EC_Lettrage \n" +
			"\t\t\t\t,EC_StatusRegle \n" +
			"\t\t\t\t,EC_MontantRegle\n" +
			"\t\t\t\t,MontantRegle \n" +
			"\t\t\t\t,TA_Provenance\n" +
			"\t\t\t\t,CG_NumCont\n" +
			"\t\t\t\t,CT_Num\n" +
			"\t\t\t\t,CT_NumCont\n" +
			"\t\t\t\t,EC_Intitule\n" +
			"\t\t\t\t,N_Reglement\n" +
			"\t\t\t\t,EC_Echeance\n" +
			"\t\t\t\t,TA_Code\n" +
			"\t\t\t\t,EC_MontantCredit = ROUND(SUM(EC_MontantCredit),0)\n" +
			"\t\t\t\t,EC_MontantDebit = ROUND(SUM(EC_MontantDebit),0)\n" +
			"\t\t\t\t,CbMarq\n" +
			"\t\tFROM (\n" +
			"\t\tSELECT nomFichier =''\n" +
			"\t\t\t\t,JO_Num\n" +
			"\t\t\t\t,DO_Date\n" +
			"\t\t\t\t,Annee_Exercice\n" +
			"\t\t\t\t,EC_Jour\n" +
			"\t\t\t\t,EC_RefPiece\n" +
			"\t\t\t\t,EC_Reference\n" +
			"\t\t\t\t,CG_Num = ISNULL(CASE WHEN Taxe ='CodeTaxe1' THEN infT.CG_NumTaxe1\n" +
			"\t\t\t\t\t\t\t\t\t\t\tWHEN Taxe ='CodeTaxe2' THEN infT.CG_NumTaxe2\n" +
			"\t\t\t\t\t\t\t\t\t\t\tWHEN Taxe ='CodeTaxe3' THEN infT.CG_NumTaxe3\n" +
			"\t\t\t\t\t\t\t\t\t\t\tEND,'')\n" +
			"\t\t\t\t,EC_Sens\n" +
			"\t\t\t\t,EC_Lettrage = ''\n" +
			"\t\t\t\t,EC_StatusRegle = 0\n" +
			"\t\t\t\t,EC_MontantRegle = 0\n" +
			"\t\t\t\t,MontantRegle = 0\n" +
			"\t\t\t\t,TA_Provenance = ISNULL(CASE WHEN Taxe ='CodeTaxe1' THEN infT.TA_Provenance1+1\n" +
			"\t\t\t\t\t\t\t\t\t\t\tWHEN Taxe ='CodeTaxe2' THEN infT.TA_Provenance2+1\n" +
			"\t\t\t\t\t\t\t\t\t\t\tWHEN Taxe ='CodeTaxe3' THEN infT.TA_Provenance3+1\n" +
			"\t\t\t\t\t\t\t\t\t\t\tEND,0)\n" +
			"\t\t\t\t,CG_NumCont \n" +
			"\t\t\t\t,CT_Num\n" +
			"\t\t\t\t,CT_NumCont\n" +
			"\t\t\t\t,EC_Intitule\n" +
			"\t\t\t\t,N_Reglement = 0\n" +
			"\t\t\t\t,EC_Echeance = ''\n" +
			"\t\t\t\t,TA_Code = CodeTaxe\n" +
			"\t\t\t\t,EC_MontantCredit = (CASE WHEN docL.DO_Provenance IN (1,2) AND @pLigneNeg = 0 THEN -1 ELSE 1 END) * ROUND(CASE WHEN EC_Sens = 0 THEN \n" +
			"\t\t\t\t\t\t\t\t\t\tISNULL(CASE WHEN Taxe ='CodeTaxe1' THEN MT_Taxe1\n" +
			"\t\t\t\t\t\t\t\t\t\t\tWHEN Taxe ='CodeTaxe2' THEN MT_Taxe2\n" +
			"\t\t\t\t\t\t\t\t\t\t\tWHEN Taxe ='CodeTaxe3' THEN MT_Taxe3\n" +
			"\t\t\t\t\t\t\t\t\t\t\tEND,0)\n" +
			"\t\t\t\t\t\t\t\t\t\t\t ELSE 0 END,2)\n" +
			"\t\t\t\t,EC_MontantDebit =  (CASE WHEN docL.DO_Provenance IN (1,2) AND @pLigneNeg = 0 THEN -1 ELSE 1 END) * ROUND(CASE WHEN EC_Sens = 1 THEN \n" +
			"\t\t\t\t\t\t\t\t\t\tISNULL(CASE WHEN Taxe ='CodeTaxe1' THEN MT_Taxe1\n" +
			"\t\t\t\t\t\t\t\t\t\t\tWHEN Taxe ='CodeTaxe2' THEN MT_Taxe2\n" +
			"\t\t\t\t\t\t\t\t\t\t\tWHEN Taxe ='CodeTaxe3' THEN MT_Taxe3\n" +
			"\t\t\t\t\t\t\t\t\t\t\tEND,0)\n" +
			"\t\t\t\t\t\t\t\t\t\t\t ELSE 0 END,2)\n" +
			"\t\t\t\t,CbMarq = ''\n" +
			"\t\tFROM _docLigne_ docL\n" +
			"\t\tINNER JOIN _PivotInfoTaxe_ infT\n" +
			"\t\t\tON\tinfT.FCP_Champ=docL.N_CatCompta \n" +
			"\t\t\tAND infT.FCP_Type=docL.DO_Domaine\n" +
			"\t\t\tAND infT.AR_Ref=docL.AR_Ref\n" +
			"\t\t\t)A\n" +
			"\t\t\tGROUP BY \n" +
			"\t\t\t nomFichier\n" +
			"\t\t\t\t,JO_Num\n" +
			"\t\t\t\t,DO_Date\n" +
			"\t\t\t\t,Annee_Exercice\n" +
			"\t\t\t\t,EC_Jour\n" +
			"\t\t\t\t,EC_RefPiece\n" +
			"\t\t\t\t,EC_Reference\n" +
			"\t\t\t\t,CG_Num\n" +
			"\t\t\t\t,EC_Sens\n" +
			"\t\t\t\t,EC_Lettrage \n" +
			"\t\t\t\t,EC_StatusRegle \n" +
			"\t\t\t\t,EC_MontantRegle\n" +
			"\t\t\t\t,MontantRegle \n" +
			"\t\t\t\t,TA_Provenance\n" +
			"\t\t\t\t,CG_NumCont\n" +
			"\t\t\t\t,CT_Num\n" +
			"\t\t\t\t,CT_NumCont\n" +
			"\t\t\t\t,EC_Intitule\n" +
			"\t\t\t\t,N_Reglement\n" +
			"\t\t\t\t,EC_Echeance\n" +
			"\t\t\t\t,TA_Code\n" +
			"\t\t\t\t,CbMarq\n" +
			")\n" +
			", _LettrageLigne_ AS (\n" +
			"\tSELECT  lettrage = ISNULL(lettrage,'A')\n" +
			"\tFROM (\n" +
			"\t\tSELECT  lettrage = CHAR(ASCII(MAX(EC_Lettrage))+1)\n" +
			"\t\tFROM    F_ECRITUREC A\n" +
			"\t\tWHERE   EC_Lettre=1\n" +
			"\t\tAND\t\tCT_Num = (SELECT TOP 1 CT_Num FROM _docLigne_ )\n" +
			"\t\tAND\t\tCG_Num = (SELECT TOP 1 CG_Num FROM _docLigne_ ) \n" +
			"\t\tAND     CAST(DATEADD(DAY,A.EC_Jour-1,A.JM_Date) AS DATE) = (SELECT TOP 1 DO_Date FROM _docLigne_) \n" +
			"\t) A\n" +
			")\n" +
			",_ResultCpta_ AS (\n" +
			"\t\n" +
			"\tSELECT\tnomFichier\n" +
			"\t\t\t,JO_Num\n" +
			"\t\t\t,Annee_Exercice\n" +
			"\t\t\t,EC_Jour\n" +
			"\t\t\t,EC_RefPiece\n" +
			"\t\t\t,EC_Reference\n" +
			"\t\t\t,CG_Num\n" +
			"\t\t\t,TA_Provenance\n" +
			"\t\t\t,EC_StatusRegle\n" +
			"\t\t\t,EC_MontantRegle\n" +
			"\t\t\t,EC_Sens\n" +
			"\t\t\t,EC_Lettrage = ISNULL((SELECT lettrage FROM _Lettrage_),'')\n" +
			"\t\t\t,CG_NumCont\n" +
			"\t\t\t,CT_Num\n" +
			"\t\t\t,CT_NumCont\n" +
			"\t\t\t,EC_Intitule\n" +
			"\t\t\t,N_Reglement\n" +
			"\t\t\t,EC_Echeance\n" +
			"\t\t\t,EC_MontantCredit = CASE WHEN EC_Sens = 0 THEN (MontantRegle) ELSE 0 END\n" +
			"\t\t\t,EC_MontantDebit = CASE WHEN EC_Sens = 1 THEN (MontantRegle) ELSE 0 END\n" +
			"\t\t\t,TA_Code =''\n" +
			"\tFROM\t_docEntete_\n" +
			"\tWHERE position = 1\n" +
			"\tUNION ALL\n" +
			"\tSELECT\tnomFichier\n" +
			"\t\t\t,JO_Num\n" +
			"\t\t\t,Annee_Exercice\n" +
			"\t\t\t,EC_Jour\n" +
			"\t\t\t,EC_RefPiece\n" +
			"\t\t\t,EC_Reference\n" +
			"\t\t\t,CG_Num\n" +
			"\t\t\t,TA_Provenance\n" +
			"\t\t\t,EC_StatusRegle\n" +
			"\t\t\t,EC_MontantRegle\n" +
			"\t\t\t,EC_Sens\n" +
			"\t\t\t,EC_Lettrage\n" +
			"\t\t\t,CG_NumCont\n" +
			"\t\t\t,CT_Num\n" +
			"\t\t\t,CT_NumCont\n" +
			"\t\t\t,EC_Intitule\n" +
			"\t\t\t,N_Reglement\n" +
			"\t\t\t,EC_Echeance\n" +
			"\t\t\t,EC_MontantCredit\n" +
			"\t\t\t,EC_MontantDebit\n" +
			"\t\t\t,TA_Code\n" +
			"\tFROM\t_LigneTaxe_\n" +
			"\n" +
			"\tUNION ALL\n" +
			"\tSELECT\tnomFichier\n" +
			"\t\t\t,JO_Num\n" +
			"\t\t\t,Annee_Exercice\n" +
			"\t\t\t,EC_Jour\n" +
			"\t\t\t,EC_RefPiece\n" +
			"\t\t\t,EC_Reference\n" +
			"\t\t\t,CG_Num\n" +
			"\t\t\t,TA_Provenance\n" +
			"\t\t\t,EC_StatusRegle\n" +
			"\t\t\t,EC_MontantRegle\n" +
			"\t\t\t,EC_Sens\n" +
			"\t\t\t,EC_Lettrage = ISNULL((SELECT lettrage FROM _LettrageLigne_),'')\n" +
			"\t\t\t,CG_NumCont\n" +
			"\t\t\t,CT_Num\n" +
			"\t\t\t,CT_NumCont\n" +
			"\t\t\t,EC_Intitule\n" +
			"\t\t\t,N_Reglement\n" +
			"\t\t\t,EC_Echeance\n" +
			"\t\t\t,EC_MontantCredit = CASE WHEN EC_Sens = 0 THEN (MontantRegle) ELSE 0 END\n" +
			"\t\t\t,EC_MontantDebit = CASE WHEN EC_Sens = 1 THEN (MontantRegle) ELSE 0 END\n" +
			"\t\t\t,TA_Code\n" +
			"\tFROM _Ligne_\n" +
			")\n" +
			"SELECT\tnomFichier\n" +
			"\t\t,JO_Num\n" +
			"\t\t,Annee_Exercice\n" +
			"        ,EC_Jour\n" +
			"\t\t,EC_RefPiece\n" +
			"\t\t,EC_Reference\n" +
			"\t\t,CG_Num\n" +
			"        ,TA_Provenance\n" +
			"\t\t,EC_StatusRegle\n" +
			"\t\t,EC_MontantRegle\n" +
			"\t\t,EC_Sens\n" +
			"\t\t,EC_Lettrage\n" +
			"\t\t,CG_NumCont\n" +
			"        ,CT_Num\n" +
			"\t\t,CT_NumCont\n" +
			"\t\t,EC_Intitule\n" +
			"\t\t,N_Reglement\n" +
			"\t\t,EC_Echeance\n" +
			"\t\t,EC_MontantCredit\n" +
			"        ,EC_MontantDebit\n" +
			"\t\t,EC_Montant = EC_MontantCredit + EC_MontantDebit\n" +
			"\t\t,TA_Code\n" +
			"FROM _ResultCpta_\t";

	public static final String deleteEntete =
			"\n" +
					"BEGIN \n" +
					"\tDECLARE @DO_Domaine INT \n" +
					"\t,@DO_Type INT\n" +
					"\t,@DO_Piece VARCHAR(10)\n" +
					"\t,@cbMarq INT = ?;\n" +
					"\tSELECT @DO_Domaine =DO_Domaine, @DO_Type = DO_Type, @DO_Piece=DO_Piece\n" +
					"\tFROM F_DOCENTETE \n" +
					"\tWHERE cbMarq = @cbMarq; \n" +
					"\n" +
					"\tDELETE FROM F_DOCREGL\n" +
					"\tWHERE\tDO_Domaine = @DO_Domaine\n" +
					"\tAND\t\tDO_Type = @DO_Type\n" +
					"\tAND\t\tDO_Piece = @DO_Piece;\n" +
					"\n" +
					"\tDELETE FROM F_DOCENTETE \n" +
					"\tWHERE\tcbMarq = @cbMarq; \n" +
					"END" ;

	public static final String getListeFactureMajComptable =
			"DECLARE @doDomaine INT = 0\n" +
					"                            ,@doType INT =  0\n" +
					"                            ,@dateDeb DATE = ?\n" +
					"                            ,@dateFin DATE = ?\n" +
					"                            ,@doPieceDeb VARCHAR(20) = ?\n" +
					"                            ,@doPieceFin VARCHAR(20) = ?\n" +
					"                            ,@doSouche INT = ?\n" +
					"                            ,@catCompta INT = ?\n" +
					"                            ,@caisse INT = ?\n" +
					"                            ,@typeTransfert INT = ?\n" +
					"                            ,@etatPiece INT = ?\n" +
					"                            \n" +
					"                    SELECT @doDomaine = CASE WHEN @typeTransfert = 2 THEN 1 ELSE 0 END\n" +
					"                    SELECT @doType = CASE WHEN @typeTransfert = 2 THEN \n" +
					"                                        CASE WHEN @etatPiece = 1 THEN 17 ELSE 16 END \n" +
					"                                    ELSE \n" +
					"                                        CASE WHEN @etatPiece = 1 THEN 7 ELSE 6 END\n" +
					"                    SELECT @doDomaine = CASE WHEN @typeTransfert = 2 THEN 1 ELSE 0 END\n" +
					"                            \n" +
					"                      SELECT cbMarq,DO_Domaine,DO_Type,DO_Piece \n" +
					"                      FROM F_DOCENTETE\n" +
					"                      WHERE DO_Domaine=@doDomaine AND DO_Type=@doType\n" +
					"                      AND (@dateDeb='' OR DO_Date>=@dateDeb)\n" +
					"                      AND (@dateFin='' OR DO_Date<=@dateFin)\n" +
					"                      AND (@doPieceDeb='' OR DO_Piece>=@doPieceDeb)\n" +
					"                      AND (@doPieceFin='' OR DO_Piece<=@doPieceFin)\n" +
					"                      AND (@doSouche='-1' OR DO_Souche=@doSouche)\n" +
					"                      AND (@catCompta='0' OR N_CatCompta>=@catCompta)\n" +
					"                      AND (@caisse='0' OR CA_No =@caisse)";

	public static final String resteARegler =
            "DECLARE @avance FLOAT = ?\n" +
					"DECLARE @cbMarq INT = ?;    \n" +
					"\n" +
					"WITH _Ligne_ AS (\n" +
					"\tSELECT\tent.DO_Domaine\n" +
					"\t\t\t,ent.DO_Type\n" +
					"\t\t\t,ent.cbDO_Piece\n" +
					"\t\t\t,DL_MontantTTC = SUM(DL_MontantTTC) \n" +
					"    FROM\tF_DOCENTETE ent\n" +
					"\tLEFT JOIN F_DOCLIGNE lig\n" +
					"\t\tON\tlig.DO_Domaine=ent.DO_Domaine \n" +
					"\t\tAND lig.DO_Type=ent.DO_Type \n" +
					"\t\tAND lig.cbDO_Piece=ent.cbDO_Piece\n" +
					"\tWHERE\tent.cbMarq = @cbMarq\n" +
					"    GROUP BY\tent.DO_Domaine\n" +
					"\t\t\t\t,ent.DO_Type\n" +
					"\t\t\t\t,ent.cbDO_Piece\n" +
					")\n" +
					", _ReglEch_ AS (\n" +
					"\tSELECT\tent.DO_Domaine\n" +
					"\t\t\t,ent.DO_Type\n" +
					"\t\t\t,ent.cbDO_Piece\n" +
					"\t\t\t,RC_Montant = SUM(RC_Montant) \n" +
					"    FROM\tF_DOCENTETE ent\n" +
					"\tLEFT JOIN F_REGLECH lig\n" +
					"\t\tON\tlig.DO_Domaine=ent.DO_Domaine \n" +
					"\t\tAND lig.DO_Type=ent.DO_Type \n" +
					"\t\tAND lig.cbDO_Piece=ent.cbDO_Piece\n" +
					"\tWHERE\tent.cbMarq = @cbMarq\n" +
					"    GROUP BY\tent.DO_Domaine\n" +
					"\t\t\t\t,ent.DO_Type\n" +
					"\t\t\t\t,ent.cbDO_Piece\n" +
					")\n" +
					"\n" +
					"SELECT CASE WHEN MTT_A_REGLER>=0 THEN \n" +
					"\t\t\tCASE WHEN  MTT_A_REGLER>=@avance THEN 1 ELSE 0 END ELSE \n" +
					"\t\t\t\tCASE WHEN  MTT_A_REGLER<=@avance THEN 1 ELSE 0 END END AS VAL\n" +
					"FROM(\n" +
					"\tSELECT MTT_A_REGLER = ROUND(SUM(D.DL_MontantTTC) - ISNULL(SUM(R.RC_Montant),0),2) \n" +
					"\tFROM _Ligne_ D\n" +
					"\tLEFT JOIN _ReglEch_ R \n" +
					"\t\tON\tD.DO_Domaine=R.DO_Domaine \n" +
					"\t\tAND D.DO_Type=R.DO_Type \n" +
					"\t\tAND D.cbDO_Piece=R.cbDO_Piece\n" +
					"\t)A";

    public static final String getDateEcgetTiersheanceSelect = "SELECT CAST(ISNULL(DATEADD(DAY,ER_NbJour,?),?) AS DATE) date_ech\n"+
            "                    FROM F_MODELER M \n"+
            "                    LEFT JOIN F_EMODELER EM ON EM.MR_No = M.MR_No\n"+
            "                    WHERE M.MR_No=? AND N_Reglement=?";
    public static final String  getZFactReglSuppr =
            "SELECT 1 valeur FROM [Z_FACT_REGL_SUPPR] WHERE DO_Domaine=? AND DO_Piece=? AND DO_Type=?";

    public static final String testCorrectLigneA =
			"SELECT CASE WHEN SUM(DL_MontantHT) = SUM(EA_Montant) AND SUM(EA_Quantite) = SUM(DL_Qte) THEN 1 ELSE 0 END AS VALID\n" +
			"                    FROM(\n" +
			"                    SELECT  B.CbMarq_Ligne\n" +
			"                            ,N_Analytique\n" +
			"                            ,DL_MontantHT = MAX(DL_MontantHT)\n" +
			"                            ,EA_Montant = SUM(ISNULL(EA_Montant,0))\n" +
			"                            ,EA_Quantite = SUM(ISNULL(EA_Quantite,0))\n" +
			"                            ,DL_Qte = MAX(DL_Qte)\n" +
			"                    FROM    F_DOCENTETE fdoc   \n" +
			"                    LEFT JOIN F_DOCLIGNE A\n" +
			"                        ON  fdoc.DO_Domaine = A.DO_Domaine\n" +
			"                        AND fdoc.DO_Type = A.DO_Type\n" +
			"                        AND fdoc.cbDO_Piece = A.cbDO_Piece\n" +
			"                    LEFT JOIN Z_LIGNE_COMPTEA B \n" +
			"                        ON A.cbMarq = B.CbMarq_Ligne\n" +
			"                    WHERE   fdoc.cbMarq= ?\n" +
			"                    GROUP BY B.CbMarq_Ligne,N_Analytique)A";

	public static final String  getLigneFacture =
            "	SELECT DL_PUDevise,CA_Num,DL_TTC, DL_PUTTC,DL_MvtStock,CT_Num,cbMarq,DL_TypeTaux1,DL_TypeTaux2,DL_TypeTaux3,cbCreateur,DL_NoColis "+
			"			,CASE WHEN DL_TypeTaux1=0 THEN DL_MontantHT*(DL_Taxe1/100)    "+
			"					WHEN DL_TypeTaux1=1 THEN DL_Taxe1*DL_Qte  "+
			"						ELSE DL_Taxe1 END  MT_Taxe1 "+
			"			,CASE WHEN DL_TypeTaux2=0 THEN DL_MontantHT*(DL_Taxe2/100)    "+
			"					WHEN DL_TypeTaux2=1 THEN DL_Taxe2*DL_Qte  "+
			"						ELSE DL_Taxe2 END  MT_Taxe2 "+
			"			,CASE WHEN DL_TypeTaux3=0 THEN DL_MontantHT*(DL_Taxe3/100)    "+
			"					WHEN DL_TypeTaux3=1 THEN DL_Taxe3*DL_Qte  "+
			"						ELSE DL_Taxe3 END  MT_Taxe3 "+
			"			,DL_MontantHT,DO_Piece, "+
			"			AR_Ref,DE_No,DL_CMUP AS AR_PrixAch,DL_Design,DL_Qte,DL_PrixUnitaire,DL_CMUP,DL_Taxe1,DL_Taxe2,DL_Taxe3,DL_MontantTTC,DL_Ligne,DL_Remise01REM_Valeur,DL_Remise01REM_Type "+
			"			,CASE WHEN DL_Remise01REM_Type=0 THEN ''   "+
			"					WHEN DL_Remise01REM_Type=1 THEN cast(cast(DL_Remise01REM_Valeur as numeric(9,2)) as varchar(10))+'%'  "+
			"						ELSE cast(cast(DL_Remise01REM_Valeur as numeric(9,2)) as varchar(10))+'U' END DL_Remise "+
			"			,DL_PrixUnitaire -(CASE WHEN DL_Remise01REM_Type= 0 THEN DL_PrixUnitaire "+
			"									WHEN DL_Remise01REM_Type=1 THEN  DL_PrixUnitaire * DL_Remise01REM_Valeur / 100 "+
			"										WHEN DL_Remise01REM_Type=2 THEN DL_Remise01REM_Valeur  "+
			"											ELSE 0 END) DL_PrixUnitaire_Rem "+
			"			,DL_PUTTC -(CASE WHEN DL_Remise01REM_Type= 0 THEN DL_PUTTC "+
			"								WHEN DL_Remise01REM_Type=1 THEN  DL_PrixUnitaire * DL_Remise01REM_Valeur / 100 "+
			"									WHEN DL_Remise01REM_Type=2 THEN DL_Remise01REM_Valeur  "+
			"										ELSE 0 END) DL_PUTTC_Rem "+
			"			,DL_PrixUnitaire -(CASE WHEN DL_Remise01REM_Type= 0 THEN 0 "+
			"										WHEN DL_Remise01REM_Type=1 THEN  DL_PrixUnitaire * DL_Remise01REM_Valeur / 100 "+
			"											WHEN DL_Remise01REM_Type=2 THEN DL_Remise01REM_Valeur  "+
			"												ELSE 0 END) DL_PrixUnitaire_Rem0 "+
			"			,DL_PUTTC -(CASE WHEN DL_Remise01REM_Type= 0 THEN 0 "+
			"								WHEN DL_Remise01REM_Type=1 THEN  DL_PrixUnitaire * DL_Remise01REM_Valeur / 100 "+
			"									WHEN DL_Remise01REM_Type=2 THEN DL_Remise01REM_Valeur ELSE 0 END) DL_PUTTC_Rem0 "+
			"		FROM 	F_DOCLIGNE   "+
			"		WHERE 	DO_Piece = ?  "+
			"		AND 	DO_Domaine= ?  "+
			"		AND 	DO_Type = ? "+
			"		ORDER BY cbMarq";

}

