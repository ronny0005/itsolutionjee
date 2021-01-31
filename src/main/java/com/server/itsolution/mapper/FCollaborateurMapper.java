package com.server.itsolution.mapper;

public class FCollaborateurMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "SELECT CO_No,CO_Nom,CO_Prenom,CO_Fonction,CO_Adresse\n" +
            "    ,CO_Complement,CO_CodePostal,CO_Ville,CO_CodeRegion\n" +
            "    ,CO_Pays,CO_Service,CO_Vendeur,CO_Caissier,cbCreateur,cbModification \n" +
            "    ,CO_DateCreation,CO_Acheteur,CO_Telephone,CO_Telecopie\n" +
            "    ,CO_EMail,CO_Receptionnaire,PROT_No,CO_TelPortable,CO_ChargeRecouvr,cbMarq FROM F_Collaborateur ";

	public static final String BASE_SQL_Short //
			=   "SELECT CO.CO_No,CO.CO_Nom From F_COLLABORATEUR CO";
	public static final String getCollaborateurCount //
			=   "SELECT COUNT(*) Nb, MAX(cbModification)cbModification " +
			"                FROM f_collaborateur";

    public static final String getCollaborateur = BASE_SQL + " WHERE CO_No = ? ";

    public static final String allCaissier = BASE_SQL_Short + " WHERE CO_Caissier=1";

    public static final String allAcheteur = BASE_SQL_Short + " WHERE CO_Acheteur=1";

    public static final String allVendeur = BASE_SQL_Short + " WHERE CO_Vendeur=1";

    public static final String getCaissierByCaisse = "DECLARE @caNo INT = ?;" +BASE_SQL_Short + " LEFT JOIN F_CAISSECAISSIER CA " +
																	  "		ON CO.CO_No = CA.CO_No\n" +
														"                WHERE CO_Caissier=1 AND (0 = @caNo OR CA_No = @caNo)\n" +
														"                GROUP BY CO.CO_No,CO_Nom";

    public static final String getCaissierByIntitule =
			"DECLARE @value AS NVARCHAR(50) = ?; " +
					"SELECT TOP 10   CO_Nom CT_Intitule\n" +
					"                                ,CT_Num = CO_No " +
					"								 ,label = CO_Nom" +
					"								 ,N_CatCompta = 0" +
					"								 ,value = CO_No" +
				"									 ,N_CatTarif = 0 \n" +
					"                FROM F_COLLABORATEUR\n" +
					"                WHERE CO_Caissier=1\n" +
					"                AND CONCAT(CONCAT(CO_No,' - '),CO_Nom) LIKE CONCAT('%',@value,'%')";

    public static final String delete = "DELETE F_COLLABORATEUR WHERE cbMarq = ? ";
	public static final String insertCollaborateur
	
			="DECLARE @CO_Nom AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_Prenom AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_Fonction AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_Adresse AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_Complement AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_Ville AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_CodeRegion AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_Pays AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_Service AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_Vendeur AS INT = ? \n" +
			"\t\t, @CO_Caissier AS INT = ? \n" +
			"\t\t, @CO_Acheteur AS INT = ? \n" +
			"\t\t, @CO_Telephone AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_Telecopie AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_EMail AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_Receptionnaire AS INT = ? \n" +
			"\t\t, @CO_ChargeRecouvr AS INT = ? \n" +
			"\t\t, @cbCreateur AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_CodePostal AS VARCHAR(50) = ? \n" +
			"\t\t, @CO_No AS INT = ISNULL((SELECT MAX(CO_No)CO_No FROM F_COLLABORATEUR),0)+1 \n"+
			"\t\t \n" +
			"IF (SELECT count(*) FROM F_COLLABORATEUR WHERE CO_Nom=@CO_Nom) = 0 \n" +
			"BEGIN \n" +
			"\tINSERT INTO [dbo].[F_COLLABORATEUR] \n" +
			"\t([CO_No],[CO_Nom],[CO_Prenom],[CO_Fonction],[CO_Adresse] \n" +
			"\t,[CO_Complement],[CO_CodePostal],[CO_Ville],[CO_CodeRegion] \n" +
			"\t,[CO_Pays],[CO_Service],[CO_Vendeur],[CO_Caissier] \n" +
			"\t,[CO_DateCreation],[CO_Acheteur],[CO_Telephone],[CO_Telecopie] \n" +
			"\t,[CO_EMail],[CO_Receptionnaire],[PROT_No],[cbPROT_No] \n" +
			"\t,[CO_TelPortable],[CO_ChargeRecouvr],[cbProt],[cbCreateur] \n" +
			"\t,[cbModification],[cbReplication],[cbFlag]) \n" +
			"\t VALUES \n" +
			"\t\t   (/*CO_No*/ISNULL((SELECT MAX(CO_No)CO_No FROM F_COLLABORATEUR),0)+1\n" +
			"\t\t   ,/*CO_Nom*/@CO_Nom,/*CO_Prenom*/@CO_Prenom \n" +
			"\t\t   ,/*CO_Fonction*/@CO_Fonction,/*CO_Adresse*/@CO_Adresse \n" +
			"\t\t   ,/*CO_Complement*/@CO_Complement,/*CO_CodePostal*/@CO_CodePostal \n" +
			"\t\t   ,/*CO_Ville*/@CO_Ville,/*CO_CodeRegion*/@CO_CodeRegion \n" +
			"\t\t   ,/*CO_Pays*/@CO_Pays,/*CO_Service*/@CO_Service \n" +
			"\t\t   ,/*CO_Vendeur*/@CO_Vendeur,/*CO_Caissier*/@CO_Caissier \n" +
			"\t\t   ,/*CO_DateCreation*/GETDATE(),/*CO_Acheteur*/@CO_Acheteur \n" +
			"\t\t   ,/*CO_Telephone*/@CO_Telephone,/*CO_Telecopie*/@CO_Telecopie \n" +
			"\t\t  ,/*CO_EMail*/@CO_EMail,/*CO_Receptionnaire*/@CO_Receptionnaire \n" +
			"\t\t  ,/*PROT_No*/0,/*cbPROT_No*/NULL \n" +
			"\t\t  ,/*CO_TelPortable*/'',/*CO_ChargeRecouvr*/@CO_ChargeRecouvr \n" +
			"\t\t  ,/*cbProt*/0,/*cbCreateur*/@cbCreateur \n" +
			"\t\t  ,/*cbModification*/GETDATE(),/*cbReplication*/0,/*cbFlag*/0);\n" +
			"\tSELECT \tcbMarq = @@IDENTITY\n" +
			"			,CO_No = @CO_No\n" +
			"\t\t\t,Message = CONCAT('Le collaborateur ',@CO_Nom,' à bien été crée !') \n" +
			"END\n" +
			"ELSE \n" +
			"\tSELECT \tcbMarq = 0\n" +
			"			,CO_No = @CO_No\n" +
			"\t\t\t,Message =  CONCAT('Le collaborateur ',@CO_Nom,' existe déjà !')\n";
	
	public static final String modifCollaborateur
			="		DECLARE @CO_Nom AS VARCHAR(50) = ? "+
			"		, @CO_Prenom AS VARCHAR(50) = ? "+
			"		, @CO_Fonction AS VARCHAR(50) = ? "+
			"		, @CO_Adresse AS VARCHAR(50) = ? "+
			"		, @CO_Complement AS VARCHAR(50) = ? "+
			"		, @CO_Ville AS VARCHAR(50) = ? "+
			"		, @CO_CodeRegion AS VARCHAR(50) = ? "+
			"		, @CO_Pays AS VARCHAR(50) = ? "+
			"		, @CO_Service AS VARCHAR(50) = ? "+
			"		, @CO_Vendeur AS INT = ? "+
			"		, @CO_Caissier AS INT = ? "+
			"		, @CO_Acheteur AS INT = ? "+
			"		, @CO_Telephone AS VARCHAR(50) = ? "+
			"		, @CO_Telecopie AS VARCHAR(50) = ? "+
			"		, @CO_EMail AS VARCHAR(50) = ? "+
			"		, @CO_Receptionnaire AS INT = ? "+
			"		, @CO_ChargeRecouvr AS INT = ? "+
			"		, @cbCreateur AS VARCHAR(50) = ? "+
			"		, @CO_CodePostal AS VARCHAR(50) = ? "+
			"		, @CO_No AS INT = ? "+
			"				 "+
			"	UPDATE [dbo].[F_COLLABORATEUR] "+
            "    SET [CO_Nom] = @CO_Nom,[CO_Prenom] = @CO_Prenom,[CO_Fonction] = @CO_Fonction,[CO_Adresse] = @CO_Adresse "+
            "       ,[CO_Complement] = @CO_Complement,[CO_CodePostal] = @CO_CodePostal,[CO_Ville] = @CO_Ville,[CO_CodeRegion] = @CO_CodeRegion "+
            "       ,[CO_Pays] = @CO_Pays,[CO_Service] = @CO_Service,[CO_Vendeur] = @CO_Vendeur,[CO_Caissier] = @CO_Caissier "+
            "       ,[CO_Acheteur] = @CO_Acheteur,[CO_Telephone] = @CO_Telephone "+
            "       ,[CO_Telecopie] = @CO_Telecopie,[CO_EMail] = @CO_EMail,[CO_Receptionnaire] = @CO_Receptionnaire "+
            "       ,[CO_ChargeRecouvr] = @CO_ChargeRecouvr "+
            "       ,[cbModification] = GETDATE() "+
			"	   ,[cbCreateur] = @cbCreateur "+
            " WHERE [CO_No] = @CO_No "+
            ";";
}