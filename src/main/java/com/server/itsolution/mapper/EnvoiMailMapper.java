package com.server.itsolution.mapper;

public class EnvoiMailMapper extends ObjectMapper {

    public static final String getCollaborateurEnvoiMail //
            =   "SELECT CO_No,CO_Nom,CO_Prenom,CO_EMail,CO_Telephone,PROT_User\n" +
            "                FROM [Z_LiaisonEnvoiMailUser] A\n" +
            "                INNER JOIN F_PROTECTIONCIAL B \n" +
            "                    ON A.PROT_No=B.PROT_No\n" +
            "                INNER JOIN F_COLLABORATEUR C \n" +
            "                    ON C.CO_Nom=B.PROT_User\n" +
            "                INNER JOIN [dbo].[Z_TypeEnvoiMail] D \n" +
            "                    ON D.TE_No=A.TE_No\n" +
            "                WHERE TE_Intitule=?";

    public static final String getCollaborateurEnvoiSMS
            = " SELECT CO_No,CO_Nom,CO_Prenom,CO_EMail,CO_Telephone,PROT_User\n" +
            "                FROM [Z_LiaisonEnvoiSMSUser] A\n" +
            "                INNER JOIN F_PROTECTIONCIAL B \n" +
            "                    ON A.PROT_No=B.PROT_No\n" +
            "                INNER JOIN F_COLLABORATEUR C \n" +
            "                    ON C.CO_Nom=B.PROT_User\n" +
            "                INNER JOIN [dbo].[Z_TypeEnvoiMail] D \n" +
            "                    ON D.TE_No=A.TE_No\n" +
            "                WHERE TE_Intitule=?";

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
}
