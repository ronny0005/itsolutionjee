package com.server.itsolution.mapper;

import com.server.itsolution.entities.FComptet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FComptetMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select   CT_Num,CT_Intitule,CT_Type,CG_NumPrinc,CT_Qualite,CT_Classement\n" +
            "    ,CT_Contact,CT_Adresse,CT_Complement,CT_CodePostal,CT_Ville,CT_CodeRegion\n" +
            "    ,CT_Pays,CT_Raccourci,BT_Num,N_Devise,CT_Ape,CT_Identifiant,CT_Siret,CT_Statistique01\n" +
            "    ,CT_Statistique02,CT_Statistique03,CT_Statistique04,CT_Statistique05,CT_Statistique06,CT_Statistique07\n" +
            "    ,CT_Statistique08,CT_Statistique09,CT_Statistique10,CT_Commentaire,CT_Encours,CT_Assurance,CT_NumPayeur\n" +
            "    ,N_Risque,CO_No,N_CatTarif,CT_Taux01,CT_Taux02,CT_Taux03,CT_Taux04,N_CatCompta,N_Period\n" +
            "    ,CT_Facture,CT_BLFact,CT_Langue,CT_Edi1,CT_Edi2,CT_Edi3,N_Expedition,N_Condition,CT_DateCreate\n" +
            "    ,CT_Saut,CT_Lettrage,CT_ValidEch,CT_Sommeil,DE_No,CT_ControlEnc,CT_NotRappel,N_Analytique,CA_Num\n" +
            "    ,CT_Telephone,CT_Telecopie,CT_EMail,CT_Site,CT_Coface,CT_Surveillance,CT_SvDateCreate,CT_SvFormeJuri,CT_SvEffectif\n" +
            "    ,CT_SvCA,CT_SvResultat,CT_SvIncident,CT_SvDateIncid,CT_SvPrivil,CT_SvRegul,CT_SvCotation,CT_SvDateMaj,CT_SvObjetMaj,CT_SvDateBilan\n" +
            "    ,CT_SvNbMoisBilan,N_AnalytiqueIFRS,CA_NumIFRS,CT_PrioriteLivr,CT_LivrPartielle\n" +
            "    ,MR_No,CT_NotPenal,EB_No,CT_NumCentrale,CT_DateFermeDebut,CT_DateFermeFin,CT_FactureElec,CT_TypeNIF,CT_RepresentInt\n" +
            "    ,CT_RepresentNIF,cbMarq,cbCreateur,cbModification From F_COMPTET";

    public static final String getDepotClient =
			"SELECT C.*,CASE WHEN ?=DE_No THEN 1 ELSE 0 END Valide_Depot\n" +
			"                    FROM Z_CODECLIENT C\n" +
			"                    LEFT JOIN Z_DEPOTCLIENT D ON C.CodeClient=D.CodeClient WHERE CT_Type=?";
    public static final String getFComptet //
            =   BASE_SQL+" WHERE CT_Num = ? AND (-1 = ? OR CT_Type = ?) ";

    public static final String modifClientUpdateCANum =
			"DECLARE @caNum VARCHAR(50) = ?\n" +
			"                            ,@ctNum VARCHAR(50) = ?;\n" +
			"                    UPDATE  [F_COMPTET] \n" +
			"                        SET  CA_Num=(CASE WHEN @caNum = '' THEN NULL ELSE @caNum END),cbModification=GETDATE()\n" +
			"                            ,N_Analytique=(CASE WHEN @caNum='' THEN NULL ELSE (SELECT N_Analytique FROM F_COMPTEA WHERE CA_Num=@caNum) END)\n" +
			"                    WHERE   CT_Num=@ctNum";

    public static final String getTiersByNumIntitule
            = " DECLARE @ctType INT = ?;" +
			" DECLARE @valeur NVARCHAR(150) = ?;" +
			" DECLARE @all INT = ?;" +
			" IF @all = 1 BEGIN \n"+
			" SELECT TOP 10 CT_Num\n" +
			"               ,CT_Intitule\n" +
			"               ,CO_No\n" +
			"               ,N_CatCompta\n" +
			"               ,N_CatTarif\n" +
			" FROM 			F_COMPTET\n" +
			" WHERE 		(@ctType IN (0,1) AND CT_Type = @ctType) " +
			" AND 			CONCAT(CONCAT(CT_Num,' - '),CT_Intitule) LIKE CONCAT(CONCAT('%',@valeur),'%')" +
			" UNION " +
			"SELECT *\n" +
			" FROM (SELECT 	CT_Num = '0' " +
			"				,CT_Intitule = CASE WHEN @ctType=0 THEN 'TOUT LES CLIENTS' " +
			"									 WHEN @ctType=1 THEN 'TOUT LES FOURNISSEURS'\n" +
			" 										WHEN @ctType=2 THEN 'TOUT LES COLLABORATEURS' \n" +
			"											WHEN @ctType=-1 THEN '' END " +
			"				,CO_No = 0" +
			"				,N_CatCompta = 0" +
			"				,N_CatTarif = 0)A\n" +
			" WHERE CONCAT(CONCAT(CT_Num,' - '),CT_Intitule) LIKE CONCAT(CONCAT('%',@valeur),'%')\n" +
			"END ELSE \n" +
			" BEGIN  \n" +
			" SELECT TOP 10 CT_Num\n" +
			"               ,CT_Intitule\n" +
			"               ,CO_No\n" +
			"               ,N_CatCompta\n" +
			"               ,N_CatTarif\n" +
			" FROM 			F_COMPTET\n" +
			" WHERE 		(@ctType =-1 OR (@ctType IN (0,1) AND CT_Type = @ctType)) " +
			" AND 			CONCAT(CONCAT(CT_Num,' - '),CT_Intitule) LIKE CONCAT(CONCAT('%',@valeur),'%')" +
			" END ";

    public static final String getMontantAutorise =
			"DECLARE @CT_Num AS VARCHAR(50) \n" +
					"                        DECLARE @Montant AS FLOAT \n" +
					"                        \n" +
					"                        SET @CT_Num = ?\n" +
					"                        SET @Montant = ?\n" +
					"                        ;\n" +
					"                        WITH _DocRegl_ AS (\n" +
					"                            SELECT\tDR_No, SUM(RC_MONTANT) avance \n" +
					"                            FROM\tF_REGLECH R \n" +
					"                            INNER JOIN F_Creglement Re \n" +
					"                                on Re.RG_No=R.RG_No \n" +
					"                            WHERE Re.cbCT_NumPayeur= @CT_Num\n" +
					"                            GROUP BY DR_No\n" +
					"                        )\n" +
					"                        , _DocEntete_ AS (\n" +
					"                            SELECT\tR.DR_No\n" +
					"                                    ,DO_Tiers\n" +
					"                                    ,E.DO_Domaine\n" +
					"                                    ,E.DO_Type\n" +
					"                                    ,DO_Provenance\n" +
					"                                    ,SUM(ISNULL(avance,0)) avance\n" +
					"                                    ,SUM(ISNULL(DL_MontantTTC,0)) DL_MontantTTC \n" +
					"                            FROM\tF_DOCENTETE E \n" +
					"                            LEFT JOIN\tF_DOCLIGNE L \n" +
					"                                ON\tE.DO_Piece=L.DO_Piece \n" +
					"                                AND E.DO_Domaine= L.DO_Domaine \n" +
					"                                AND E.DO_Type=L.DO_Type  \n" +
					"                            LEFT JOIN F_DOCREGL R \n" +
					"                                ON\tR.DO_Piece=E.DO_Piece \n" +
					"                                AND R.DO_Type=E.DO_Type \n" +
					"                                AND R.DO_Domaine=E.DO_Domaine\n" +
					"                            LEFT JOIN _DocRegl_ dregl\n" +
					"                                ON\tR.DR_No = dregl.DR_No\n" +
					"                            WHERE E.cbDO_Tiers= @CT_Num\n" +
					"                            AND\t\tISNULL(r.dr_regle,0)=0 \n" +
					"                            GROUP BY R.DR_No\n" +
					"                                    ,E.DO_Tiers\n" +
					"                                    ,E.DO_Provenance\n" +
					"                                    ,E.DO_Domaine\n" +
					"                                    ,E.DO_Type\n" +
					"                        )\n" +
					"                        \n" +
					"                        , _RequeteStart_ AS (\n" +
					"                            SELECT\tCT_ControlEnc\n" +
					"                                    ,CT_Encours\n" +
					"                                    ,DO_Provenance\n" +
					"                                    ,DL_MontantTTC\n" +
					"                                    ,avance\n" +
					"                                    ,CT_Num\n" +
					"                                    ,DO_Domaine\n" +
					"                                    ,DO_Type\n" +
					"                            FROM _DocEntete_ E\n" +
					"                            INNER JOIN F_COMPTET C \n" +
					"                                ON C.CT_Num=E.DO_Tiers \n" +
					"                        )\n" +
					"                        , _Requete_ AS (\n" +
					"                            SELECT\tMAX(CT_ControlEnc)CT_ControlEnc\n" +
					"                                    ,MAX(CT_Encours) CT_Encours\n" +
					"                                    ,DO_Provenance\n" +
					"                                    ,ROUND(SUM(DL_MontantTTC),0) AS ttc\n" +
					"                                    ,ISNULL(sum(avance),0) AS avance \n" +
					"                            FROM\t_RequeteStart_\n" +
					"                            WHERE ((DO_Domaine=0 AND (DO_Type=6 OR DO_Type=7) AND DO_Provenance IN(0,1)) \n" +
					"                                        OR (DO_Domaine=1 AND (DO_Type=16 OR DO_Type=17 OR DO_Type=12)) ) \n" +
					"                            GROUP BY DO_Provenance\n" +
					"                        )\n" +
					"                        SELECT CT_Encours ,TTC ,CT_Encours-TTC MontantDette \n" +
					"                                ,CASE WHEN CT_ControlEnc=0 AND CT_Encours !=0 THEN CT_Encours-@Montant-TTC \n" +
					"                                        WHEN (CT_ControlEnc=0 AND CT_Encours =0) THEN 1 \n" +
					"                                            WHEN CT_ControlEnc!=0 THEN 1 END MontantAutorise \n" +
					"                        FROM(\tSELECT\tSUM(TTC)TTC,MAX(CT_Encours)CT_Encours,MAX(CT_ControlEnc)CT_ControlEnc \n" +
					"                                FROM _Requete_ A \n" +
					"                        WHERE ((DO_Provenance =0 AND ttc>0) OR DO_Provenance=1) AND NOT (TTC=AVANCE) )A";
    public static  final String getFLivraisonByCTNum
            =   "SELECT ISNULL((SELECT Max(LI_No) FROM F_LIVRAISON WHERE CT_Num =?),0) AS LI_No";

    public static final String insertFComptetg
			=    "DECLARE @ctNum VARCHAR(50) = ?\n" +
			"                            ,@cgNum VARCHAR(50) = ?\n" +
			"                        INSERT INTO [F_COMPTETG]\n" +
			"                           ([CT_Num],[CG_Num],[cbProt]\n" +
			"                           ,[cbCreateur],[cbModification]\n" +
			"                           ,[cbReplication],[cbFlag])\n" +
			"                     VALUES\n" +
			"                           (/*CT_Num*/@ctNum,/*CG_Num*/@cgNum,/*cbProt*/0\n" +
			"                           ,/*cbCreateur*/'AND',/*cbModification*/CAST(GETDATE() AS DATE)\n" +
			"                           ,/*cbReplication*/0,/*cbFlag*/0)";

    public static final String insertFLivraison =
			"DECLARE @ctNum VARCHAR(50) = ?\n" +
					"                        ,@liIntitule VARCHAR(50) = ?\n" +
					"                        ,@liAdresse VARCHAR(50) = ?\n" +
					"                        ,@liComplement VARCHAR(50) = ?\n" +
					"                        ,@liCodePostal VARCHAR(50) = ?\n" +
					"                        ,@liVille VARCHAR(50) = ?\n" +
					"                        ,@liCodeRegion VARCHAR(50) = ?\n" +
					"                        ,@liPays VARCHAR(50) = ?\n" +
					"                        ,@liContact VARCHAR(50) = ?\n" +
					"                        ,@nExpedition VARCHAR(50) = ?\n" +
					"                        ,@nCondition VARCHAR(50) = ?\n" +
					"                        ,@liTelecopie VARCHAR(50) = ?\n" +
					"                        ,@liEmail VARCHAR(50) = ?\n" +
					"                        ,@liTelephone VARCHAR(50) = ?;\n" +
					"\n" +
					"INSERT INTO [F_LIVRAISON]\n" +
					"                    ([LI_No],[CT_Num],[LI_Intitule],[LI_Adresse]\n" +
					"\t\t   ,[LI_Complement],[LI_CodePostal],[LI_Ville],[LI_CodeRegion],[LI_Pays],[LI_Contact]\n" +
					"                    ,[N_Expedition],[N_Condition],[LI_Principal],[LI_Telephone]\n" +
					"                    ,[LI_Telecopie],[LI_EMail],[cbProt],[cbCreateur],[cbModification],[cbReplication],[cbFlag])\n" +
					"              VALUES\n" +
					"                    (/*LI_No*/ISNULL((SELECT Max(LI_No) FROM [F_LIVRAISON]),0)+1,/*CT_Num*/LEFT(@ctNum,17)\n" +
					"                    ,/*LI_Intitule*/LEFT(@liIntitule,35),/*LI_Adresse*/LEFT(@liAdresse,35)\n" +
					"                    ,/*LI_Complement*/LEFT(@liComplement,35),/*LI_CodePostal*/LEFT(@liCodePostal,9)\n" +
					"                    ,/*LI_Ville*/LEFT(@liVille,35),/*LI_CodeRegion*/LEFT(@liCodeRegion,25)\n" +
					"                    ,/*LI_Pays*/LEFT(@liPays,35),/*LI_Contact*/LEFT(@liContact,35)\n" +
					"                    ,/*N_Expedition*/@nExpedition,/*N_Condition*/@nCondition\n" +
					"                    ,/*LI_Principal*/1,/*LI_Telephone, varchar(21),*/LEFT(@liTelephone,21)\n" +
					"                    ,/*LI_Telecopie*/LEFT(@liTelecopie,21),/*LI_EMail*/LEFT(@liEmail,69)\n" +
					"                    ,/*cbProt*/0,/*cbCreateur*/'AND',/*cbModification*/CAST(GETDATE() AS DATE)\n" +
					"                    ,/*cbReplication*/0,/*cbFlag*/0)";

	public static final String getOptionModeleReglementByMRNo =
			"SELECT E.*,R_Intitule "+
					"FROM F_EMODELER E "+
					"INNER JOIN P_REGLEMENT P ON E.N_Reglement = P.R_Code "+
					"WHERE MR_No = ?";

	public static final String getModeleReglementCount =
			"SELECT COUNT(*) Nb,MAX(cbModification) cbModification\n" +
					"                FROM F_MODELER";

	public static final String allTiersMax =
			"SELECT COUNT(*) Nb,MAX(cbModification) cbModification " +
			"                FROM F_COMPTET " +
					"WHERE CT_Type=0 AND CT_Sommeil=0";

	public static final String allClientsSelect =
			"SELECT 'Tout' CT_Intitule,'0' CT_Num"+
        "        UNION "+
        "        SELECT C.CT_Intitule,CT_Num "+
        "        FROM F_COMPTET C  "+
        "        LEFT JOIN P_CATTARIF P ON P.cbIndice = C.N_CatTarif  "+
        "         LEFT JOIN (select  row_number() over (order by u.subject) as idcompta, u.LibCatCompta  "+
        "         from P_CATCOMPTA  "+
        "         unpivot  "+
        "               (  "+
        "                LibCatCompta  "+
		" for subject in (CA_ComptaVen01, CA_ComptaVen02, CA_ComptaVen03, CA_ComptaVen04, CA_ComptaVen05, CA_ComptaVen06, CA_ComptaVen07, CA_ComptaVen08, CA_ComptaVen09, CA_ComptaVen10, CA_ComptaVen11, CA_ComptaVen12, "+
		" CA_ComptaVen13, CA_ComptaVen14, CA_ComptaVen15, CA_ComptaVen16, CA_ComptaVen17, CA_ComptaVen18, CA_ComptaVen19, CA_ComptaVen20, CA_ComptaVen21, CA_ComptaVen22) "+
        "         ) u) M ON M.idcompta = C.N_CatCompta WHERE CT_Type=0 "+
        "         ORDER BY CT_Num";

    public static final String allClientShort =
            "SELECT cbModification,CT_Sommeil,C.CT_Intitule,CT_Num,CG_NumPrinc,N_CatTarif,N_CatCompta,P.CT_Intitule AS LibCatTarif,LibCatCompta,\n" +
                    "                CT_Type,CO_No,CT_Adresse,CG_NumPrinc,CT_Telephone,CT_CodeRegion,CT_Ville,CT_Siret,CT_Identifiant,MR_No,DE_No,CA_Num\n" +
                    "                FROM F_COMPTET C \n" +
                    "                LEFT JOIN P_CATTARIF P ON P.cbIndice = C.N_CatTarif \n" +
                    "                 LEFT JOIN (select  row_number() over (order by u.subject) as idcompta, u.LibCatCompta \n" +
                    "                 from P_CATCOMPTA \n" +
                    "                 unpivot \n" +
                    "                       ( \n" +
                    "                        LibCatCompta \n" +
                    "                 for subject in (CA_ComptaVen01, CA_ComptaVen02, CA_ComptaVen03, CA_ComptaVen04, CA_ComptaVen05, CA_ComptaVen06, CA_ComptaVen07, CA_ComptaVen08, CA_ComptaVen09, CA_ComptaVen10, CA_ComptaVen11, CA_ComptaVen12, CA_ComptaVen13, CA_ComptaVen14, CA_ComptaVen15, CA_ComptaVen16, CA_ComptaVen17, CA_ComptaVen18, CA_ComptaVen19, CA_ComptaVen20, CA_ComptaVen21, CA_ComptaVen22)\n" +
                    "                 ) u) M ON M.idcompta = C.N_CatCompta \n" +
                    "                 WHERE CT_Type=0 \n" +
                    "                 AND (-1=? OR CT_Sommeil=?)\n" +
                    "                 ORDER BY CT_Num";

    public static final String allFournisseurShort =
            "SELECT cbModification,CT_Sommeil,C.CT_Intitule,CT_Num,CG_NumPrinc,N_CatTarif,N_CatCompta,P.CT_Intitule AS LibCatTarif,LibCatCompta,\n" +
                    "                CT_Type,CO_No,CT_Adresse,CG_NumPrinc,CT_Telephone,CT_CodeRegion,CT_Ville,CT_Siret,CT_Identifiant,MR_No,DE_No,CA_Num\n" +
                    "                FROM F_COMPTET C\n" +
                    "                LEFT JOIN P_CATTARIF P ON P.cbIndice = C.N_CatTarif \n" +
                    "                 LEFT JOIN (select  row_number() over (order by u.subject) as idcompta, u.LibCatCompta \n" +
                    "                 from P_CATCOMPTA \n" +
                    "                 unpivot \n" +
                    "                      ( \n" +
                    "                        LibCatCompta \n" +
                    "                        for subject in (CA_ComptaAch01, CA_ComptaAch02, CA_ComptaAch03, CA_ComptaAch04, CA_ComptaAch05, CA_ComptaAch06, CA_ComptaAch07, CA_ComptaAch08, CA_ComptaAch09, CA_ComptaAch10, CA_ComptaAch11, CA_ComptaAch12, CA_ComptaAch13, CA_ComptaAch14, CA_ComptaAch15, CA_ComptaAch16, CA_ComptaAch17, CA_ComptaAch18, CA_ComptaAch19, CA_ComptaAch20, CA_ComptaAch21, CA_ComptaAch22) \n" +
                    "                 ) u) M ON M.idcompta = C.N_CatCompta " +
                    "                WHERE CT_Type=1\n" +
                    "                 AND (-1=? OR CT_Sommeil=?)\n" +
                    "                 ORDER BY CT_Intitule";
	
	public static final String getCodeAuto = 
					" DECLARE @type AS VARCHAR(50) = ? "+
					" SELECT T_Racine "+
                    " FROM  P_TIERS "+
                    " WHERE (@type='Client' AND T_Val01T_Intitule='Cl.') "+
                    " OR   (@type='Fournisseur' AND T_Val01T_Intitule='Fr.') "+
                    " OR   (@type='Salarie' AND T_Val01T_Intitule='Sal.')";

	public static final String tiersByCTIntitule	
				= "  SELECT cbMarq "+
                  "  FROM  F_COMPTET "+
                 "   WHERE CT_Intitule=?";
				 
	 public static final String TiersDoublons
        =	"	SELECT CT_Intitule "+
            "    FROM ( "+
            "    SELECT CT_Intitule,COUNT(CT_Num) Nb "+
            "    FROM F_COMPTET "+
            "    GROUP BY CT_Intitule)A "+
            "    WHERE Nb>1";
	
	public static final String remplacementTiers =
	"			BEGIN "+ 
            "    SET NOCOUNT ON; "+ 
            "    DECLARE @nouveau as VARCHAR(200) "+ 
            "    DECLARE @ancien as VARCHAR(200) "+ 
            "    DECLARE @LI_No as INT "+ 
            "    DECLARE @LI_NoOld as INT "+ 
            "    set @nouveau =? "+ 
            "    set @ancien =? "+ 
            "                     "+ 
            "    SELECT @LI_NoOld=LI_No "+ 
            "    FROM F_LIVRAISON "+ 
            "    WHERE CT_Num=@ancien "+    
            " "+    
            "    SELECT @LI_No=LI_No "+ 
            "    FROM F_LIVRAISON "+ 
            "    WHERE CT_Num=@nouveau "+ 
            "      "+ 
            "    update F_CREGLEMENT set CT_NumPayeur=@nouveau WHERE CT_NumPayeur=@ancien "+ 
            "    update F_CREGLEMENT set CT_NumPayeurOrig=@nouveau WHERE CT_NumPayeurOrig=@ancien "+ 
            "    update F_DOCENTETE set LI_No=@LI_No WHERE LI_No=@LI_NoOld "+ 
            "    update F_DOCENTETE set CT_NumPayeur=@nouveau WHERE CT_NumPayeur=@ancien "+ 
            "    update F_DOCENTETE set DO_Tiers=@nouveau WHERE DO_Tiers=@ancien "+ 
            "    UPDATE F_DOCLIGNE set CT_Num=@nouveau WHERE CT_Num=@ancien "+ 
            "    UPDATE F_TICKETARCHIVE set CT_Num=@nouveau WHERE CT_Num=@ancien "+ 
            "    UPDATE F_ECRITUREC set CT_Num=@nouveau WHERE CT_Num=@ancien "+ 
            "    UPDATE F_ECRITUREC set CT_NumCont=@nouveau WHERE CT_NumCont=@ancien "+ 
            "    DELETE FROM F_REGLEMENTT WHERE CT_Num=@ancien "+ 
            "    DELETE FROM F_LIVRAISON WHERE CT_Num=@ancien "+ 
            "    DELETE FROM F_COMPTETG WHERE CT_Num=@ancien "+ 
            "    DELETE FROM F_COMPTETMEDIA WHERE CT_Num=@ancien "+ 
            "    DELETE FROM F_COMPTETMODELE WHERE CT_Num=@ancien "+ 
            "    DELETE FROM F_COMPTETRAPPEL WHERE CT_Num=@ancien    "+ 
            "    UPDATE F_COMPTET SET CT_Sommeil=1 WHERE CT_Num=@ancien  "+  
            " END";
			
	
    public static final String allFournisseurSelect =
			"SELECT 'Tout' CT_Intitule,'0' CT_Num "+
			"		UNION "+
            "        SELECT C.CT_Intitule,CT_Num "+
            "        FROM F_COMPTET C "+
            "        LEFT JOIN P_CATTARIF P ON P.cbIndice = C.N_CatTarif  "+
            "        LEFT JOIN (select  row_number() over (order by u.subject) as idcompta, u.LibCatCompta  "+
            "        FROM P_CATCOMPTA  "+
            "        UNPIVOT "+
            "              (  "+
            "                LibCatCompta  "+
            "                for subject in (CA_ComptaAch01, CA_ComptaAch02, CA_ComptaAch03, CA_ComptaAch04, CA_ComptaAch05, CA_ComptaAch06, CA_ComptaAch07, CA_ComptaAch08, CA_ComptaAch09, CA_ComptaAch10, CA_ComptaAch11, CA_ComptaAch12, CA_ComptaAch13, CA_ComptaAch14, CA_ComptaAch15, CA_ComptaAch16, CA_ComptaAch17, CA_ComptaAch18, CA_ComptaAch19, CA_ComptaAch20, CA_ComptaAch21, CA_ComptaAch22) "+
            "         ) u) M ON M.idcompta = C.N_CatCompta WHERE CT_Type=1 "+
            "         ORDER BY CT_Intitule";
	public static final String createClientMin = 
					"DECLARE @CT_Num AS NVARCHAR(50) = ? "+
					"	,@CT_Intitule AS NVARCHAR(50) = ? "+
					"	,@CT_Type AS INT  = ? "+
					"	,@CG_NumPrinc AS NVARCHAR(50) = ? "+
					"	,@CT_Adresse AS NVARCHAR(50) = ? "+
					"	,@CT_CodePostal AS NVARCHAR(50) = ? "+
					"	,@CT_Ville AS NVARCHAR(50) = ? "+
					"	,@CT_CodeRegion AS NVARCHAR(50) = ? "+
					"	,@CT_Identifiant AS NVARCHAR(50) = ? "+
					"	,@CT_Siret AS NVARCHAR(50) = ? "+
					"	,@CO_No AS INT = ? "+
					"	,@N_CatTarif AS INT = ? "+
					"	,@N_CatCompta AS INT = ? "+
					"	,@DE_No AS INT = ? "+
					"	,@CA_Num AS NVARCHAR(50) = ? "+
					"	,@CT_Telephone AS NVARCHAR(50) = ? "+
					"	,@MR_No AS INT = ? "+
					"	,@cbCreateur AS NVARCHAR(50) = ? "+
					" "+
					"		INSERT INTO [F_COMPTET]  "+
					"	 ([CT_Num],[CT_Intitule],[CT_Type],[CG_NumPrinc],[CT_Qualite],[CT_Classement]  "+
					"	 ,[CT_Contact],[CT_Adresse],[CT_Complement],[CT_CodePostal],[CT_Ville],[CT_CodeRegion]  "+
					"	 ,[CT_Pays],[CT_Raccourci],[BT_Num],[N_Devise],[CT_Ape],[CT_Identifiant],[CT_Siret],[CT_Statistique01]  "+
					"	 ,[CT_Statistique02],[CT_Statistique03],[CT_Statistique04],[CT_Statistique05],[CT_Statistique06],[CT_Statistique07] "+ 
					"	 ,[CT_Statistique08],[CT_Statistique09],[CT_Statistique10],[CT_Commentaire],[CT_Encours],[CT_Assurance]  "+
					"	 ,[CT_NumPayeur],[N_Risque],[CO_No],[N_CatTarif],[CT_Taux01]  "+
					"	 ,[CT_Taux02],[CT_Taux03],[CT_Taux04],[N_CatCompta],[N_Period],[CT_Facture] "+ 
					"	 ,[CT_BLFact],[CT_Langue],[CT_Edi1],[CT_Edi2],[CT_Edi3],[N_Expedition] "+
					"	 ,[N_Condition],[CT_DateCreate],[CT_Saut],[CT_Lettrage],[CT_ValidEch],[CT_Sommeil]  "+
					"	 ,[DE_No],[CT_ControlEnc],[CT_NotRappel],[N_Analytique] "+
					"	 ,[CA_Num],[CT_Telephone],[CT_Telecopie],[CT_EMail],[CT_Site],[CT_Coface]  "+
					"	 ,[CT_Surveillance],[CT_SvDateCreate],[CT_SvFormeJuri],[CT_SvEffectif],[CT_SvCA],[CT_SvResultat]  "+
					"	 ,[CT_SvIncident],[CT_SvDateIncid],[CT_SvPrivil],[CT_SvRegul],[CT_SvCotation],[CT_SvDateMaj] "+ 
					"	 ,[CT_SvObjetMaj],[CT_SvDateBilan],[CT_SvNbMoisBilan],[N_AnalytiqueIFRS],[CA_NumIFRS]  "+
					"	 ,[CT_PrioriteLivr],[CT_LivrPartielle],[MR_No],[CT_NotPenal],[EB_No]  "+
					"	 ,[CT_NumCentrale],[CT_DateFermeDebut],[CT_DateFermeFin],[CT_FactureElec],[CT_TypeNIF]  "+
					"	 ,[CT_RepresentInt],[CT_RepresentNIF],[cbProt],[cbCreateur],[cbModification],[cbReplication]  "+
					"	 ,[cbFlag])  "+
					"	 VALUES   "+
					"		(/*CT_Num*/LEFT(@CT_Num,17),/*CT_Intitule*/LEFT(@CT_Intitule,35),/*CT_Type,*/@CT_Type "+
					"		,/*CG_NumPrinc*/(SELECT CASE WHEN @CG_NumPrinc='' THEN NULL ELSE @CG_NumPrinc END)  "+
					"		,/*CT_Qualite*/'',/*CT_Classement*/LEFT(@CT_Intitule,17),/*CT_Contact*/'' "+
					"		,/*CT_Adresse*/LEFT(@CT_Adresse,35),/*CT_Complement*/'' "+
					"		,/*CT_CodePostal*/LEFT(@CT_CodePostal,9),/*CT_Ville*/LEFT(@CT_Ville,35) "+
					"		,/*CT_CodeRegion*/LEFT(@CT_CodeRegion,9),/*CT_Pays*/'',/*CT_Raccourci*/'' "+
					"		,/*BT_Num*/0,/*N_Devise*/0 ,/*CT_Ape*/'',/*CT_Identifiant*/LEFT(@CT_Identifiant,25) "+
					"		,/*CT_Siret*/LEFT(@CT_Siret,15),/*CT_Statistique01*/'',/*CT_Statistique02*/'' "+
					"		,/*CT_Statistique03*/'',/*CT_Statistique04*/'',/*CT_Statistique05*/'',/*CT_Statistique06*/'' "+
					"		,/*CT_Statistique07*/'',/*CT_Statistique08*/'',/*CT_Statistique09*/'',/*CT_Statistique10*/'' "+
					"		,/*CT_Commentaire*/'',/*CT_Encours*/0,/*CT_Assurance*/0,/*CT_NumPayeur*/LEFT(@CT_Num,17) "+
					"		,/*N_Risque*/1,/*CO_No*/(CASE WHEN @CO_No='' OR @CO_No='0' THEN NULL ELSE @CO_No END) "+
					"		,/*N_CatTarif*/@N_CatTarif,/*CT_Taux01*/0,/*CT_Taux02*/0,/*CT_Taux03*/0  "+
					"		,/*CT_Taux04*/0,/*N_CatCompta*/@N_CatCompta,/*N_Period*/1,/*CT_Facture*/1  "+
					"		,/*CT_BLFact*/0,/*CT_Langue*/0,/*CT_Edi1*/'',/*CT_Edi2*/'' "+
					"		,/*CT_Edi3*/'',/*N_Expedition*/1,/*N_Condition*/1,/*CT_DateCreate*/GETDATE() "+
					"		,/*CT_Saut*/1,/*CT_Lettrage*/1,/*CT_ValidEch*/0,/*CT_Sommeil*/0  "+
					"		,/*DE_No*/(SELECT CASE WHEN @DE_No='0' THEN NULL ELSE @DE_No END) "+
					"		,/*CT_ControlEnc*/0,/*CT_NotRappel*/0,/*N_Analytique*/(CASE WHEN @CA_Num='' THEN NULL ELSE (SELECT N_Analytique FROM F_COMPTEA WHERE CA_Num=@CA_Num) END) "+
					"		,/*CA_Num*/(CASE WHEN @CA_Num='' THEN NULL ELSE @CA_Num END) "+
					"		,/*CT_Telephone*/LEFT(@CT_Telephone,21),/*CT_Telecopie*/''  "+
					"		,/*CT_EMail*/'',/*CT_Site*/'',/*CT_Coface*/'',/*CT_Surveillance*/0 "+
					"		,/*CT_SvDateCreate*/'1900-01-01',/*CT_SvFormeJuri*/'',/*CT_SvEffectif*/'',/*CT_SvCA*/0 "+
					"		,/*CT_SvResultat*/0,/*CT_SvIncident*/0,/*CT_SvDateIncid*/'1900-01-01',/*CT_SvPrivil*/0 "+
					"		,/*CT_SvRegul*/'',/*CT_SvCotation*/'',/*CT_SvDateMaj*/'1900-01-01',/*CT_SvObjetMaj*/'' "+
					"		,/*CT_SvDateBilan*/'1900-01-01',/*CT_SvNbMoisBilan*/0,/*N_AnalytiqueIFRS*/0 "+
					"		,/*CA_NumIFRS*/NULL,/*CT_PrioriteLivr*/0,/*CT_LivrPartielle*/0,/*MR_No*/@MR_No "+
					"		,/*CT_NotPenal*/0,/*EB_No*/0 "+
					"		,/*CT_NumCentrale*/NULL,/*CT_DateFermeDebut*/'1900-01-01',/*CT_DateFermeFin*/'1900-01-01',/*CT_FactureElec*/0  "+
					"		,/*CT_TypeNIF*/0,/*CT_RepresentInt*/'',/*CT_RepresentNIF*/'',/*cbProt*/0 "+
					"		,/*cbCreateur*/@cbCreateur,/*cbModification*/GETDATE(),/*cbReplication*/0,/*cbFlag*/0)";
}

