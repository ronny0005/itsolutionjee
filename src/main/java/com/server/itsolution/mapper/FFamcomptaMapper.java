package com.server.itsolution.mapper;

public class FFamcomptaMapper extends ObjectMapper {

    public static final String BASE_SQL =
        "SELECT\t[FA_CodeFamille],[FCP_Type],[FCP_Champ],[FCP_ComptaCPT_CompteG]\n" +
		"\t\t,[FCP_ComptaCPT_CompteA],[FCP_ComptaCPT_Taxe1]\n" +
		"\t\t,[FCP_ComptaCPT_Taxe2],[FCP_ComptaCPT_Taxe3]\n" +
		"\t\t,[FCP_TypeFacture],[cbProt],[cbMarq],[cbCreateur]\n" +
		"\t\t,[cbModification],[cbReplication],[cbFlag]\n" +
		"FROM\t[F_FAMCOMPTA]";

	public static final String ffamcomptacount =
			"SELECT cbModification = max(cbModification),count(*) Nb FROM ("+BASE_SQL+")A";

	public static final String     getLibTaxePied=
			" SELECT  LIB1 = TU.TA_Intitule\n" +
			"                        ,LIB2 = TD.TA_Intitule\n" +
			"\t\t\t\t,LIB3 = TT.TA_Intitule\n" +
			"\t\t\t\t,TA_Code1 = TU.TA_Code\n" +
			"\t\t\t\t,TA_Code2 = TD.TA_Code\n" +
			"\t\t\t\t,TA_Code3 = TT.TA_Code\n" +
			"\t\tFROM F_FAMCOMPTA FA\n" +
			"\t\tLEFT JOIN DBO.F_TAXE TU\n" +
			"\t\tON  TU.TA_Code = FA.FCP_ComptaCPT_Taxe1\n" +
			"\t\tLEFT JOIN DBO.F_TAXE TD\n" +
			"\t\tON  TD.TA_Code = FA.FCP_ComptaCPT_Taxe2\n" +
			"\t\tLEFT JOIN DBO.F_TAXE TT\n" +
			"\t\tON  TT.TA_Code = FA.FCP_ComptaCPT_Taxe3\n" +
			"\t\tWHERE   FCP_Type=?\n" +
			"\t\tAND     FCP_Champ=?\n" +
			"\t\tGROUP BY TU.TA_Intitule,TD.TA_Intitule,TT.TA_Intitule,TU.TA_Code,TD.TA_Code,TT.TA_Code";

}