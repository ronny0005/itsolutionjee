package com.server.itsolution.mapper;

public class PReglementMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "SELECT R_Code,R_Intitule,cbIndice \n" +
            "    FROM   P_REGLEMENT P\n" +
            "    WHERE  R_Intitule<>''";

    public static final String getPReglement
            =    BASE_SQL+ " AND cbMarq = ?";

    public static final String listeTypeReglementCount
            = " SELECT COUNT(*) Nb \n" +
              " FROM P_REGLEMENT P\n" +
              " WHERE R_Intitule<>''";
}