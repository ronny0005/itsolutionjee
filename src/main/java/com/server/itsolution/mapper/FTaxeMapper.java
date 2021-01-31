package com.server.itsolution.mapper;

public class FTaxeMapper extends ObjectMapper {

    public static final String BASE_SQL =
        "SELECT [TA_Intitule],[TA_TTaux],[TA_Taux],[TA_Type],[CG_Num]\n" +
				"\t\t,[TA_No],[TA_Code],[TA_NP],[TA_Sens]\n" +
				"\t\t,[TA_Provenance],[TA_Regroup],[TA_Assujet],[TA_GrilleBase]\n" +
				"\t\t,[TA_GrilleTaxe],[cbProt],[cbMarq],[cbCreateur]\n" +
				"\t\t,[cbModification],[cbReplication],[cbFlag]" +
				"  FROM [F_TAXE]";

	public static final String getTaCode = BASE_SQL +
			" WHERE   CG_Num= ? \n";

	public static final String getF_TaxeCount =
		"SELECT count(*) Nb" +
		"		,max(cbModification) cbModification  \n" +
		"FROM 	F_Taxe \n";


}