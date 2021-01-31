package com.server.itsolution.mapper;

public class FArtClientMapper extends ObjectMapper {

    public static final String BASE_SQL =
        "SELECT [AR_Ref],[AC_Categorie],[AC_PrixVen]\n" +
				"      ,[AC_Coef],[AC_PrixTTC],[AC_Arrondi],[AC_QteMont]\n" +
				"      ,[EG_Champ],[AC_PrixDev],[AC_Devise],[CT_Num]\n" +
				"      ,[AC_Remise],[AC_Calcul],[AC_TypeRem],[AC_RefClient]\n" +
				"      ,[AC_CoefNouv],[AC_PrixVenNouv],[AC_PrixDevNouv],[AC_RemiseNouv]\n" +
				"      ,[AC_DateApplication],[cbProt],[cbMarq],[cbCreateur]\n" +
				"      ,[cbModification],[cbReplication],[cbFlag]\n" +
				"  FROM [F_ARTCLIENT]";

    public static final String selectFArtClient =
			BASE_SQL + " WHERE AR_Ref=? AND AC_Categorie=?";

    public static final String getF_ArtclientCount =
			"SELECT count(*)Nb,max(cbModification)cbModification  \n" +
					"            FROM F_ARTCLIENT";
	public static String insertFArtClient =
			" DECLARE @arRef VARCHAR (150) = ? "+
					" , @acCategorie VARCHAR (150) = ? "+
					" , @acPrixTTC VARCHAR (150) = ? "+
					" , @acPrixVen VARCHAR (150) = ? "+
					" , @acCoef VARCHAR (150) = ? "+
					" , @cbCreateur VARCHAR (150) = ? "+
					"INSERT INTO [dbo].[F_ARTCLIENT]\n" +
					"                            ([AR_Ref],[AC_Categorie],[AC_PrixVen],[AC_Coef]\n" +
					"                            ,[AC_PrixTTC],[AC_Arrondi],[AC_QteMont],[EG_Champ]\n" +
					"                            ,[AC_PrixDev],[AC_Devise],[CT_Num],[AC_Remise]\n" +
					"                            ,[AC_Calcul],[AC_TypeRem],[AC_RefClient],[AC_CoefNouv]\n" +
					"                            ,[AC_PrixVenNouv],[AC_PrixDevNouv],[AC_RemiseNouv],[AC_DateApplication]\n" +
					"                            ,[cbProt],[cbCreateur],[cbModification],[cbReplication],[cbFlag])\n" +
					"                      VALUES\n" +
					"                            (/*AR_Ref, varchar(19),*/@arRef,/*AC_Categorie*/@acCategorie,/*AC_PrixVen*/@acPrixVen,/*AC_Coef*/@acCoef\n" +
					"                            ,/*AC_PrixTTC*/@acPrixTTC,/*AC_Arrondi*/0,/*AC_QteMont*/0,/*EG_Champ*/0\n" +
					"                            ,/*AC_PrixDev*/0,/*AC_Devise*/0,/*CT_Num*/NULL,/*AC_Remise*/0\n" +
					"                            ,/*AC_Calcul*/0,/*AC_TypeRem*/0,/*AC_RefClient*/'',/*AC_CoefNouv*/0\n" +
					"                            ,/*AC_PrixVenNouv*/0,/*AC_PrixDevNouv*/0,/*AC_RemiseNouv*/0,/*AC_DateApplication*/'1900-01-01'\n" +
					"                            ,/*cbProt*/0,/*cbCreateur, char(4),*/@cbCreateur,/*cbModification*/GETDATE(),/*cbReplication*/0,/*cbFlag*/0)";

	public static String modifFArtClient =
			" DECLARE @arRef VARCHAR (150) = ? "+
					" , @acCategorie VARCHAR (150) = ? "+
					" , @acPrixTTC VARCHAR (150) = ? "+
					" , @acPrixVen VARCHAR (150) = ? "+
					" , @acCoef VARCHAR (150) = ? "+
					" , @cbCreateur VARCHAR (150) = ?; "+
			" UPDATE F_ARTCLIENT SET AC_PrixVen = @acPrixVen " +
					", AC_PrixTTC = @acPrixTTC " +
					", AC_Coef = @acCoef \n" +
					", cbCreateur = @cbCreateur \n" +
					" WHERE AR_Ref= @arRef " +
					" AND   AC_Categorie= @acCategorie";
}