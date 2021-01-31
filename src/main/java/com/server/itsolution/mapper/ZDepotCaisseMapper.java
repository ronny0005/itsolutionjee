package com.server.itsolution.mapper;

public class ZDepotCaisseMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select DE_No,CA_No From Z_DEPOTCAISSE";

    public static final String getDepotCaisseSelect =
            "SELECT     C.DE_No" +
            "           ,DE_Intitule" +
            "           ,Valide_Caisse = CASE WHEN ?=CA_No THEN 1 ELSE 0 END \n" +
            "FROM       F_DEPOT C\n" +
            "LEFT JOIN  Z_DEPOTCAISSE D " +
            "   ON C.DE_No=D.DE_No";
}