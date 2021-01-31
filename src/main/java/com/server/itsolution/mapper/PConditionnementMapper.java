package com.server.itsolution.mapper;

public class PConditionnementMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "SELECT\tP_Conditionnement\n " +
            " ,cbIndice\n " +
            " ,cbMarq\n " +
            " FROM P_CONDITIONNEMENT\n " +
            " WHERE P_Conditionnement<>'' ";

    public static final String getpConditionnement
            =   BASE_SQL +
            " AND cbMarq = ?";


    public static final String getConditionnementMax
            =" SELECT  COUNT(*) Nb \n" +
            "                FROM    (SELECT 'Aucun' AS P_Conditionnement,0 AS cbIndice \n" +
            "                        UNION \n" +
            "                        SELECT  P_Conditionnement,cbIndice \n" +
            "                        FROM    P_CONDITIONNEMENT \n" +
            "                        WHERE   P_Conditionnement<>'')A ";

    public static final String getPrixConditionnement
            ="DECLARE @arRef NVARCHAR(50) = ?\n" +
            "                    SELECT  cbIndice\n" +
            "                        ,AC_Categorie\n" +
            "                        ,CT_Intitule\n" +
            "                        ,AC_PrixTTC as AC_PrixTTCExist\n" +
            "                        ,AC_Coef\n" +
            "                        ,ISNULL(AC_PrixTTC,(SELECT AR_PrixTTC FROM F_ARTICLE WHERE AR_Ref=@arRef)) AC_PrixTTC\n" +
            "                        ,CASE WHEN AC_PrixVen IS NULL OR AC_PrixVen=0 THEN (SELECT AR_PrixVen FROM F_ARTICLE WHERE AR_Ref=@arRef) ELSE AC_PrixVen END AC_PrixVen \n" +
            "                    FROM P_CATTARIF C \n" +
            "                    LEFT JOIN (SELECT AR_Ref,AC_Categorie,AC_Coef,AC_PrixVen,AC_PrixTTC FROM F_ARTCLIENT WHERE ar_ref=@arRef) A ON C.cbIndice=AC_Categorie\n" +
            "                    where CT_Intitule <>''";
}