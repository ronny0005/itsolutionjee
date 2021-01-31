package com.server.itsolution.mapper;

public class PCatTarifMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select   CT_Intitule\n" +
            "    ,CT_PrixTTC\n" +
            "    ,cbIndice\n" +
            "    ,cbMarq From P_CatTarif where CT_Intitule <>''";

    public static final String getpCatTarif
            =   "   SELECT  CT_Intitule\n" +
            "               ,CT_PrixTTC\n" +
            "               ,cbIndice\n" +
            "               ,cbMarq " +
            "       FROM    P_CatTarif " +
            "       WHERE   cbMarq = ?";

    public static final String typeArticle
            =   "SELECT CT_PrixTTC = MAX(CT_PrixTTC)  " +
            "FROM   P_CATTARIF";
    public static final String getTarifCount
            =   "SELECT Count(*) Nb FROM P_CATTARIF where CT_Intitule <>''";
}