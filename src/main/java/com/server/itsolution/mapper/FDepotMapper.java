package com.server.itsolution.mapper;

public class FDepotMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select     A.DE_No,DE_Intitule,DE_Adresse,DE_Complement,DE_CodePostal,DE_Ville,DE_Contact,DE_Principal "+
            "               ,DE_CatCompta,DE_Region,DE_Pays,DE_EMail,DE_Code,DE_Telephone,DE_Telecopie,DE_Replication "+
            "               ,DP_NoDefaut,A.cbMarq,cbModification,CA_CatTarif,ISNULL(CA_No,0) CA_No\n" +
            "                        ,ISNULL(CA_SoucheVente,-1)CA_SoucheVente\n" +
            "                        ,ISNULL(CA_SoucheStock,-1)CA_SoucheStock\n" +
            "                        ,ISNULL(CA_SoucheAchat,-1)CA_SoucheAchat\n" +
            "                        ,CA_Num  " +
            "   From        F_DEPOT A " +
            "   LEFT JOIN   Z_DEPOT_DETAIL B " +
            "       ON  A.DE_No=B.DE_No " +
            "   LEFT JOIN Z_DEPOTSOUCHE Z \n" +
            "       ON  Z.DE_No=A.DE_No \n" +
            "   LEFT JOIN Z_DEPOTCAISSE C \n" +
            "       ON  C.DE_No=A.DE_No ";

    public static final String getDepot = BASE_SQL + " WHERE A.DE_No = ?";

    public static final String supprDepotClient =
            "DELETE FROM Z_DEPOTCLIENT WHERE DE_No=?";

    public static final String insertDepot =
            "BEGIN \n" +
                    "SET NOCOUNT ON;\n" +
                    "DECLARE @DE_Intitule NVARCHAR(50) = ?;\n" +
                    "DECLARE @DE_Adresse NVARCHAR(50) = ?;\n" +
                    "DECLARE @DE_Complement NVARCHAR(50) = ?;\n" +
                    "DECLARE @DE_CodePostal NVARCHAR(50) = ?;\n" +
                    "DECLARE @DE_Ville NVARCHAR(50) = ?;\n" +
                    "DECLARE @DE_Contact NVARCHAR(50) = ?;\n" +
                    "DECLARE @DE_Region NVARCHAR(50) = ?;\n" +
                    "DECLARE @DE_Pays NVARCHAR(50) = ?;\n" +
                    "DECLARE @DE_EMail NVARCHAR(50) = ?;\n" +
                    "DECLARE @DE_Telephone NVARCHAR(50) = ?;\n" +
                    "DECLARE @DE_Telecopie NVARCHAR(50) = ?;\n" +
                    "DECLARE @cbCreateur NVARCHAR(50) = ?;\n" +
                    "DECLARE @DE_No AS INT;\n" +
                    "DECLARE @DP_No AS INT;\n" +
                    "DECLARE @generated_keys table([cbMarq] int);\n" +
                    "\n" +
                    "SET @DE_No = (SELECT ISNULL((select MAX(DE_No)+1 from f_depot),1));\n" +
                    "\n" +
                    "INSERT INTO [dbo].[F_DEPOT]\n" +
                    "([DE_No],[DE_Intitule],[DE_Adresse],[DE_Complement],[DE_CodePostal],[DE_Ville]\n" +
                    ",[DE_Contact],[DE_Principal],[DE_CatCompta],[DE_Region],[DE_Pays],[DE_EMail]\n" +
                    ",[DE_Code],[DE_Telephone],[DE_Telecopie],[DE_Replication],[DP_NoDefaut]\n" +
                    ",[cbProt],[cbCreateur],[cbModification],[cbReplication],[cbFlag])\n" +
                    "OUTPUT INSERTED.cbMarq INTO @generated_keys \n" +
                    "VALUES\n" +
                    "(/*DE_No*/@DE_No,/*DE_Intitule*/@DE_Intitule,/*DE_Adresse*/@DE_Adresse\n" +
                    ",/*DE_Complement*/@DE_Complement,/*DE_CodePostal*/@DE_CodePostal,/*DE_Ville*/@DE_Ville\n" +
                    ",/*DE_Contact*/@DE_Contact,/*DE_Principal*/0,/*DE_CatCompta*/1\n" +
                    ",/*DE_Region*/@DE_Region,/*DE_Pays*/@DE_Pays,/*DE_EMail*/@DE_EMail\n" +
                    ",/*DE_Code*/NULL,/*DE_Telephone*/@DE_Telephone,/*DE_Telecopie*/@DE_Telecopie\n" +
                    ",/*DE_Replication*/0,/*DP_NoDefaut*/NULL\n" +
                    ",/*cbProt*/0,/*cbCreateur*/@cbCreateur,/*cbModification*/CAST(GETDATE() AS DATE)\n" +
                    ",/*cbReplication*/0,/*cbFlag*/0);\n" +
                    "\n" +
                    "SET @DP_No = (SELECT ISNULL((SELECT MAX(DP_No)+1 FROM F_DEPOTEMPL),1));\n" +
                    "\n" +
                    "INSERT INTO [dbo].[F_DEPOTEMPL]\n" +
                    "           ([DE_No],[DP_No],[DP_Code],[DP_Intitule]\n" +
                    "           ,[DP_Zone],[DP_Type],[cbProt],[cbCreateur]\n" +
                    "           ,[cbModification],[cbReplication],[cbFlag])\n" +
                    "            VALUES\n" +
                    "           (/*DE_No*/@DE_No,/*DP_No*/@DP_No,/*DP_Code*/'DEFAUT',/*DP_Intitule*/'Défaut',/*DP_Zone*/0\n" +
                    "           ,/*DP_Type*/0,/*cbProt*/0,/*cbCreateur*/@cbCreateur,/*cbModification*/CAST(GETDATE() AS DATE),/*cbReplication*/0,/*cbFlag*/0);\n" +
                    "    \n" +
                    "            UPDATE F_DEPOT SET  DP_NoDefaut = @DP_No\n" +
                    "                                ,cbModification = GETDATE()\n" +
                    "             WHERE DE_No = @DE_No\n" +
                    "\n" +
                    "\tSELECT\tcbMarq\n" +
                    "\t\t\t,DE_No = @DE_No \n" +
                    "\tFROM\t@generated_keys;\n" +
                    "END ";
    public static final String insertDepotClient =
            "INSERT INTO Z_DEPOTCLIENT VALUES (?,?)";
    public static final String insertDepotCaisse =
            "BEGIN \n" +
            "                        DECLARE @deNo INT = ?\n" +
            "                                ,@caNo INT = ?;   \n" +
            "                        IF( SELECT TOP 1 1 FROM Z_DEPOTCAISSE WHERE DE_No=@deNo) = 1 \n" +
            "                            UPDATE Z_DEPOTCAISSE SET CA_No=@caNo WHERE DE_No=@deNo;\n" +
            "                        ELSE \n" +
            "                            INSERT INTO Z_DEPOTCAISSE VALUES (@deNo,@caNo);\n" +
            "                    END";

    public static final String deleteDepot =
            "   DECLARE @DE_No INT = ? " +
            "   UPDATE F_DEPOT SET DP_NoDefaut = 0 ,cbModification=GETDATE() WHERE DE_No=@DE_No; \n" +
            "            DELETE FROM Z_DEPOT_DETAIL WHERE DE_No=@DE_No;\n" +
            "            DELETE FROM F_DEPOTEMPL WHERE DE_No=@DE_No\n"+
            "            DELETE FROM F_DEPOT WHERE DE_No=@DE_No;\n";

    public static final String insertDepotSouche =
            "DECLARE @deNo INT = ?\n" +
            "                            ,@caSoucheVente INT = ?\n" +
            "                            ,@caSoucheAchat INT = ?\n" +
            "                            ,@caSoucheStock INT = ?\n" +
            "                            ,@caNum VARCHAR(20) = ?;\n" +
            "                    IF (SELECT TOP 1 1 FROM Z_DEPOTSOUCHE WHERE DE_No=@deNo) = 1\n" +
            "                        UPDATE Z_DEPOTSOUCHE \n" +
            "                                SET CA_SoucheVente=@caSoucheVente\n" +
            "                                    ,CA_SoucheAchat=@caSoucheAchat\n" +
            "                                    ,CA_SoucheStock=@caSoucheStock\n" +
            "                                    ,CA_Num=@caNum \n" +
            "                        WHERE DE_No=@deNo;\n" +
            "                    ELSE\n" +
            "                        INSERT INTO Z_DEPOTSOUCHE VALUES (@deNo,@caSoucheVente,@caSoucheAchat,@caSoucheStock,@caNum);";

    public static final String majCatTarif =
            "BEGIN \n" +
                    "                    SET NOCOUNT ON;\n" +
                    " DECLARE @deNo INT = ?" +
                    "           ,@caCatTarif INT = ?; " +
                    "                    IF NOT EXISTS(SELECT 1 FROM Z_DEPOT_DETAIL WHERE DE_No =@deNo) \n" +
                    "                        INSERT INTO Z_DEPOT_DETAIL VALUES(@deNo,@caCatTarif)\n" +
                    "                    ELSE \n" +
                    "                        UPDATE Z_DEPOT_DETAIL SET CA_CatTarif = @caCatTarif WHERE DE_No=@deNo\n" +
                    "                  END";

    public static final String depotShortDetail =
            " SELECT DE_No,DE_Intitule,DE_Ville,DE_CodePostal,cbModification,1 as IsPrincipal "+
                    " FROM F_DEPOT";

    public static final String getDepotByIntitule =
            "                DECLARE @intitule AS VARCHAR(50) =? "+
            "                DECLARE @depotExclude AS INT =? "+
            "                SELECT  DE_No\n"+
            "                        ,DE_Intitule\n"+
            "                        ,DE_Intitule as value\n"+
            "                FROM F_DEPOT\n"+
            "                WHERE CONCAT(CONCAT(DE_No,' - '),DE_Intitule) LIKE CONCAT(CONCAT('%',@intitule),'%')\n"+
            "                AND (@depotExclude = -1 OR DE_No <> @depotExclude)";

    public static final String setCatTarif =
            "SELECT CA_CatTarif\n" +
            "                  FROM Z_DEPOT_DETAIL\n" +
            "                  WHERE DE_No=?";

    public static final String getDepotUserSearch =
            "DECLARE @protNo as INT = ?  \n" +
                    "                    DECLARE @depotExclude as INT = ?\n" +
                    "                    DECLARE @searchTerm as VARCHAR(50) = ?\n" +
                    "                    DECLARE @principal as INT = ?\n" +
                    "                    SELECT * FROM(\n" +
                    "                    SELECT  A.DE_No\n" +
                    "                            ,DE_Intitule\n" +
                    "                            ,DE_Intitule as value\n" +
                    "                            ,IsPrincipal\n" +
                    "                    FROM    F_DEPOT A\n" +
                    "                    INNER JOIN Z_DEPOTUSER B ON A.DE_No = B.DE_No\n" +
                    "                    WHERE   Prot_No=@protNo\n" +
                    "                    AND (@depotExclude=-1 OR A.DE_No<>@depotExclude)\n" +
                    "                    AND DE_Intitule like CONCAT(CONCAT('%',@searchTerm),'%')\n" +
                    "                    GROUP BY A.DE_No\n" +
                    "                            ,DE_Intitule\n" +
                    "                            ,IsPrincipal) A WHERE @principal=-1 OR IsPrincipal=@principal";

    public static final String getDepotUser =
            "SELECT  A.DE_No\n" +
                    "        ,DE_Intitule\n" +
                    "        ,IsPrincipal\n" +
                    "FROM    F_DEPOT A\n" +
                    "INNER JOIN Z_DEPOTUSER B \n" +
                    "   ON  A.DE_No = B.DE_No\n" +
                    "WHERE      Prot_No=?\n" +
                    "GROUP BY   A.DE_No\n" +
                    "           ,DE_Intitule\n" +
                    "           ,IsPrincipal";

    public static final String depotCount=
            "select count(*)Nb,max(cbModification)cbModification from F_DEPOT";
    public static final String getDepotUserPrincipal =
            "SELECT  A.DE_No\n" +
            "                            ,DE_Intitule\n" +
            "                            ,IsPrincipal\n" +
            "                    FROM    F_DEPOT A\n" +
            "                    INNER JOIN (SELECT *\n" +
            "                                FROM Z_DEPOTUSER \n" +
            "                                WHERE IsPrincipal = 1) B ON A.DE_No = B.DE_No\n" +
            "                    WHERE   Prot_No=? \n" +
            "                    GROUP BY A.DE_No\n" +
            "                            ,DE_Intitule\n" +
            "                            ,IsPrincipal";

}
