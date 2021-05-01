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

    public static final String getForProtNoCaisseDepot
            ="DECLARE @protNo INT = ?\n" +
            "                    IF (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE Prot_No=@protNo) = 1 \n" +
            "                    SELECT  CA_No = ISNULL(CA.CA_No,0)\n" +
            "                            ,IsPrincipal = 1\n" +
            "                            ,CA_Intitule = ISNULL(CA_Intitule,'')\n" +
            "                           ,C.DE_No\n" +
            "                    FROM 	F_CAISSE CA\n" +
            "                    INNER JOIN Z_DEPOTCAISSE C \n" +
            "                        ON CA.CA_No=C.CA_No\n" +
            "                    INNER JOIN F_DEPOT D \n" +
            "                        ON C.DE_No=D.DE_No\n" +
            "                    INNER JOIN F_COMPTET CT \n" +
            "                        ON CT.CT_Num = CA.CT_Num\n" +
            "                    GROUP BY CA.CA_No\n" +
            "                            ,CA_Intitule\n" +
            "                           ,C.DE_No;\n" +
            "                    \n" +
            "                    ELSE \n" +
            "                  \n" +
            "                    SELECT\tCA_No = ISNULL(fca.CA_No,0) \n" +
            "                            ,IsPrincipal = MAX(CASE WHEN fco.CO_No IS NOT NULL THEN 2\n" +
            "                                WHEN zdu.IsPrincipal = 1 THEN 1 ELSE 0 END)\n" +
            "                            ,CA_Intitule = ISNULL(fca.CA_Intitule,'')\n" +
            "                           ,zde.DE_No\n" +
            "                    FROM F_CAISSE fca\n" +
            "                    LEFT JOIN Z_DEPOTCAISSE zde \n" +
            "                        ON fca.CA_No=zde.CA_No\n" +
            "                    LEFT JOIN Z_DEPOTUSER zdu \n" +
            "                        ON zde.DE_No=zdu.DE_No\n" +
            "                    LEFT JOIN F_COMPTET fct\n" +
            "                        ON fct.CT_Num = fca.CT_Num\n" +
            "                    LEFT JOIN F_PROTECTIONCIAL fpr\n" +
            "                        ON fpr.PROT_No = zdu.Prot_No\n" +
            "                    LEFT JOIN F_COLLajoutLigneABORATEUR fco \n" +
            "                        ON fco.CO_Nom = fpr.PROT_User\n" +
            "                        AND fco.CO_No = fca.CO_NoCaissier\n" +
            "                    WHERE\tzdu.Prot_No=@protNo\n" +
            "                    \n" +
            "                    GROUP BY fca.CA_No\n" +
            "                            ,fca.CA_Intitule\n" +
        "                               ,zde.DE_No;";
}