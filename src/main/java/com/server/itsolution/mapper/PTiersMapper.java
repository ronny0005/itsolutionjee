package com.server.itsolution.mapper;

public class PTiersMapper extends ObjectMapper {

	public static final String Parameter = "DECLARE @type NVARCHAR(20) = ?;";

	public static final String BASE_SQL
            =   "SELECT [T_Principal]\n" +
			"      ,[T_Val01T_Intitule]\n" +
			"      ,[T_Val01T_TCompte]\n" +
			"      ,[T_Val01T_Compte]\n" +
			"      ,[T_Val02T_Intitule]\n" +
			"      ,[T_Val02T_TCompte]\n" +
			"      ,[T_Val02T_Compte]\n" +
			"      ,[T_Val03T_Intitule]\n" +
			"      ,[T_Val03T_TCompte]\n" +
			"      ,[T_Val03T_Compte]\n" +
			"      ,[T_Val04T_Intitule]\n" +
			"      ,[T_Val04T_TCompte]\n" +
			"      ,[T_Val04T_Compte]\n" +
			"      ,[T_Val05T_Intitule]\n" +
			"      ,[T_Val05T_TCompte]\n" +
			"      ,[T_Val05T_Compte]\n" +
			"      ,[T_Val06T_Intitule]\n" +
			"      ,[T_Val06T_TCompte]\n" +
			"      ,[T_Val06T_Compte]\n" +
			"      ,[T_Val07T_Intitule]\n" +
			"      ,[T_Val07T_TCompte]\n" +
			"      ,[T_Val07T_Compte]\n" +
			"      ,[T_Val08T_Intitule]\n" +
			"      ,[T_Val08T_TCompte]\n" +
			"      ,[T_Val08T_Compte]\n" +
			"      ,[T_Val09T_Intitule]\n" +
			"      ,[T_Val09T_TCompte]\n" +
			"      ,[T_Val09T_Compte]\n" +
			"      ,[T_Val10T_Intitule]\n" +
			"      ,[T_Val10T_TCompte]\n" +
			"      ,[T_Val10T_Compte]\n" +
			"      ,[T_Numerotation]\n" +
			"      ,[T_Lg]\n" +
			"      ,[T_Racine]\n" +
			"      ,[cbIndice]\n" +
			"      ,[cbMarq]\n" +
			"  FROM [P_TIERS]";

	public static final String GetPTiersByType = Parameter + BASE_SQL +
		"WHERE ('Fr.'=@type AND T_Val01T_Intitule=@type) "+
			"OR ('Cl.'=@type AND T_Val01T_Intitule=@type) "+
			"OR ('Sal.'=@type AND T_Val01T_Intitule=@type)";
}