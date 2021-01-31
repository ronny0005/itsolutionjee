package com.server.itsolution.mapper;

public class ZDepotUserMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select DE_No,Prot_No,IsPrincipal From Z_DEPOTUSER";

    public static final String getDepotPrincipal =
            "SELECT\tA.DE_No\n" +
                    "                            ,DE_Intitule\n" +
                    "                            ,IsPrincipal = ISNULL(D.IsPrincipal,0)\n" +
                    "                    FROM\tF_DEPOT A\n" +
                    "                    LEFT JOIN Z_DEPOTUSER D \n" +
                    "                        ON A.DE_No=D.DE_No\n" +
                    "                    WHERE\tIsPrincipal = 1\n" +
                    "                    AND     PROT_No = ?\n" +
                    "                    GROUP BY A.DE_No\n" +
                    "                             ,DE_Intitule\n" +
                    "                             ,IsPrincipal";

    public static final String getDepotUserCount
            ="BEGIN \n" +
            "                    DECLARE @admin INT\n" +
            "                    DECLARE @ProtNo INT\n" +
            "                    SET @ProtNo = ?\n" +
            "                    \n" +
            "                    SELECT @admin = CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No = @ProtNo \n" +
            "                    \n" +
            "                    IF (@admin=0)\n" +
            "                    BEGIN \n" +
            "                       SELECT count(*) Nb FROM (\n "+
            "                        SELECT\tA.DE_No,DE_Intitule,ISNULL(D.IsPrincipal,0)IsPrincipal\n" +
            "                        FROM\tF_DEPOT A\n" +
            "                        LEFT JOIN Z_DEPOTUSER D ON A.DE_No=D.DE_No\n" +
            "                        WHERE\t(1 = (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No=@ProtNo) OR D.PROT_No =@ProtNo)\n" +
            "                        AND IsPrincipal = 1\n" +
            "                        GROUP BY A.DE_No,DE_Intitule,IsPrincipal ) A\n" +
            "                    END\n" +
            "                    ELSE \n" +
            "                    BEGIN \n" +
            "                        SELECT count(*) Nb FROM (SELECT DE_No,DE_Intitule, 1 as IsPrincipal\n" +
            "                        FROM F_DEPOT)A \n" +
            "                    END \n" +
            "                    END ";

    public static final String getDepotUser
            ="BEGIN \n" +
            "                    DECLARE @admin INT\n" +
            "                    DECLARE @ProtNo INT\n" +
            "                    SET @ProtNo = ?\n" +
            "                    \n" +
            "                    SELECT @admin = CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No = @ProtNo \n" +
            "                    \n" +
            "                    IF (@admin=0)\n" +
            "                    BEGIN \n" +
            "                        SELECT\tA.DE_No,DE_Intitule,ISNULL(D.IsPrincipal,0)IsPrincipal\n" +
            "                        FROM\tF_DEPOT A\n" +
            "                        LEFT JOIN Z_DEPOTUSER D ON A.DE_No=D.DE_No\n" +
            "                        WHERE\t(1 = (SELECT CASE WHEN PROT_Administrator=1 OR PROT_Right=1 THEN 1 ELSE 0 END FROM F_PROTECTIONCIAL WHERE PROT_No=@ProtNo) OR D.PROT_No =@ProtNo)\n" +
            "                        AND IsPrincipal = 1\n" +
            "                        GROUP BY A.DE_No,DE_Intitule,IsPrincipal\n" +
            "                    END\n" +
            "                    ELSE \n" +
            "                    BEGIN \n" +
            "                        SELECT DE_No,DE_Intitule, 1 as IsPrincipal\n" +
            "                        FROM F_DEPOT \n" +
            "                    END \n" +
            "                    END ";

    public static final String getPrincipalDepot
            ="SELECT * FROM Z_DEPOTUSER WHERE Prot_No=? AND IsPrincipal=1";

    public static final String supprDepotUser
            ="DELETE FROM Z_DEPOTUSER WHERE Prot_No=?";

    public static final String insertDepotUser
            ="INSERT INTO Z_DEPOTUSER VALUES(?,?,0)";

    public static final String setDepotUser
            =" UPDATE Z_DEPOTUSER " +
             "   SET IsPrincipal=1 " +
             " WHERE DE_No=? " +
             " AND   prot_no=? ;";
}



