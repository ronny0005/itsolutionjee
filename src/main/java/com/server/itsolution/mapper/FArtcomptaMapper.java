package com.server.itsolution.mapper;

public class FArtcomptaMapper extends ObjectMapper {

    public static final String BASE_SQL =
        "SELECT\t[AR_Ref],[ACP_Type],[ACP_Champ],[ACP_ComptaCPT_CompteG],[ACP_ComptaCPT_CompteA]\n" +
		"\t\t,[ACP_ComptaCPT_Taxe1],[ACP_ComptaCPT_Taxe2],[ACP_ComptaCPT_Taxe3],[ACP_TypeFacture]\n" +
		"\t\t,[cbProt],[cbMarq],[cbCreateur],[cbModification],[cbReplication],[cbFlag]\n" +
		"FROM\t [F_ARTCOMPTA]";

    public static final String fartcomptacount =
			"SELECT cbModification = max(cbModification),count(*) Nb FROM ("+BASE_SQL+")A";
}