package com.server.itsolution.mapper;

public class FProtectioncialMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   " SELECT    PROT_User,PROT_Pwd, PROT_Description, PROT_Right, PROT_No, PROT_Email, PROT_UserProfil" +
                "           , PROT_Administrator, PROT_DatePwd, PROT_DateCreate" +
                "           ,PROT_LastLoginDate, PROT_LastLoginTime, PROT_PwdStatus,cbCreateur,cbModification " +
                " FROM      F_PROTECTIONCIAL ";

    public static final String insert =
                    "        DECLARE @ProtUser AS NVARCHAR(50) = ?\n" +
                    "        DECLARE @ProtPwd AS NVARCHAR(50) = ?\n" +
                    "        DECLARE @ProtDescription AS NVARCHAR(50) = ?\n" +
                    "        DECLARE @ProtRight AS INT = ?\n" +
                    "        DECLARE @ProtEmail AS NVARCHAR(50) = ?\n" +
                    "        DECLARE @ProtUserProfil AS NVARCHAR(50) = ?\n" +
                    "        DECLARE @ProtPwdStatus AS INT = ?;\n" +
                    "		 DECLARE @generated_keys table([cbMarq] int);" +
                    "		 DECLARE @protNoResult int;" +
                    "        \n" +
                    "        IF NOT EXISTS (SELECT 1 FROM F_PROTECTIONCIAL WHERE PROT_User = @ProtUser)\n" +
                    "        BEGIN \n" +
                    "            INSERT INTO F_PROTECTIONCPTA\n" +
                    "           ([PROT_User]           ,[PROT_Pwd]\n" +
                    "           ,[PROT_Description]           ,[PROT_Right]\n" +
                    "           ,[PROT_No]           ,[PROT_EMail]\n" +
                    "           ,[PROT_UserProfil]           ,[PROT_Administrator]\n" +
                    "           ,[PROT_DatePwd]           ,[PROT_DateCreate]\n" +
                    "           ,[PROT_LastLoginDate]           ,[PROT_LastLoginTime]\n" +
                    "           ,[PROT_PwdStatus]           ,[cbProt]\n" +
                    "           ,[cbCreateur]           ,[cbModification]\n" +
                    "           ,[cbReplication]           ,[cbFlag])\n" +
                    "     VALUES\n" +
                    "           (@ProtUser,@ProtPwd\n" +
                    "           , @ProtDescription,@ProtRight,(SELECT ISNULL((SELECT MAX(PROT_No) FROM F_PROTECTIONCIAL),0)+1)\n" +
                    "           ,@ProtEmail,@ProtUserProfil,0,GETDATE(),GETDATE()\n" +
                    "           ,'1900-01-01',000000000,@ProtPwdStatus,0\n" +
                    "           ,'COLU',GETDATE(),0,0);\n" +
                    "        INSERT INTO [F_PROTECTIONCIAL]\n" +
                    "           ([PROT_User],[PROT_Pwd]\n" +
                    "           ,[PROT_Description]           ,[PROT_Right]\n" +
                    "           ,[PROT_No]           ,[PROT_EMail]\n" +
                    "           ,[PROT_UserProfil]           ,[PROT_Administrator]\n" +
                    "           ,[PROT_DatePwd]           ,[PROT_DateCreate]\n" +
                    "           ,[PROT_LastLoginDate]           ,[PROT_LastLoginTime]\n" +
                    "           ,[PROT_PwdStatus]           ,[cbProt]\n" +
                    "           ,[cbCreateur]           ,[cbModification]\n" +
                    "           ,[cbReplication]           ,[cbFlag])\n" +
                    "     OUTPUT INSERTED.cbMarq INTO @generated_keys VALUES\n" +
                    "           (@ProtUser,@ProtPwd\n" +
                    "           , @ProtDescription,@ProtRight,(SELECT ISNULL((SELECT MAX(PROT_No) FROM F_PROTECTIONCIAL),0)+1)\n" +
                    "           ,@ProtEmail,@ProtUserProfil,0,GETDATE(),GETDATE()\n" +
                    "           ,'1900-01-01',000000000,@ProtPwdStatus,0\n" +
                    "           ,'COLU',GETDATE(),0,0)  \n" +
                    "           SELECT @protNoResult = PROT_No FROM F_PROTECTIONCIAL WHERE cbMarq=(SELECT cbMarq FROM @generated_keys) \n" +
                    "           END\n" +
                    "           ELSE SELECT @protNoResult = -1  \n" +
                    "           SELECT @protNoResult AS PROT_No" +
                    "       ;\n";

    public static final String getParametre =
            "DECLARE @protNo INT = ?\n" +
            "                SELECT  P.PROT_No,PROT_Right,PROT_Administrator,PROT_User,PROT_UserProfil,ISNULL(CA_Souche,1)CA_Souche,ISNULL(DE_No,1)DE_No,ISNULL(CA_No,1)CA_No,ISNULL(CO_No,1)CO_No,ISNULL(CO_NoCaissier,0) CO_NoCaissier\n" +
            "                FROM    F_PROTECTIONCIAL P\n" +
            "                LEFT JOIN (SELECT C.CO_No,CA.CA_No,DE_No,CA_Souche,PROT_No,CO_Nom,CA.CO_NoCaissier\n" +
            "                            FROM F_COLLABORATEUR C \n" +
            "                            LEFT JOIN F_CAISSECAISSIER CC \n" +
            "                                ON C.CO_No= CC.CO_No\n" +
            "                            LEFT JOIN F_CAISSE CA \n" +
            "                                ON CA.CA_No = CC.CA_No) A \n" +
            "                    ON  A.CO_Nom=P.PROT_User\n" +
            "                WHERE   P.Prot_No=@protNo";

    public static final String getDepotUser =
            "DECLARE @admin INT\n" +
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
            "                    END ";

    public static final String getUserList =
            "WITH _Profil_ AS (\n" +
                    "\tSELECT\tPROT_No\n" +
                    "\t\t\t,PROT_User\n" +
                    "\tFROM\tF_PROTECTIONCIAL\n" +
                    "\tWHERE\tPROT_UserProfil=0\n" +
                    ")\n" +
                    "SELECT\t\tfpr.PROT_No\n" +
                    "\t\t\t, fpr.PROT_User\n" +
                    "\t\t\t, PROT_Description\n" +
                    "\t\t\t,  PROT_Email\n" +
                    "\t\t\t, PROT_DateCreate = CAST(PROT_DateCreate AS DATE)\n" +
                    "\t\t\t, PROT_LastLoginDate = CAST(PROT_LastLoginDate AS DATE)\n" +
                    "\t\t\t, cbModification = CAST(cbModification AS DATE)\n" +
                    "\t\t\t,profil = ISNULL(pro.PROT_User,'Pas de profil associé')\n" +
                    "\t\t\t,Groupe = CASE WHEN PROT_Right = 1 THEN 'Administrateur'\n" +
                    "\t\t\t\t\tWHEN PROT_Right = 2 THEN 'Utilisateur'\n" +
                    "\t\t\t\t\tELSE 'Pas de groupe associé' END\n" +
                    "FROM      F_PROTECTIONCIAL fpr\n" +
                    "LEFT JOIN _Profil_ Pro\n" +
                    "\tON fpr.PROT_UserProfil = Pro.PROT_No";

    public static final String UpdateLastLogin =
            "UPDATE F_PROTECTIONCPTA "+
                    " SET [PROT_LastLoginDate] =GETDATE(),cbModification=GETDATE() WHERE PROT_No=?;"+
            "UPDATE F_PROTECTIONCIAL "+
                    " SET [PROT_LastLoginDate] =GETDATE(),cbModification=GETDATE() WHERE PROT_No=?;";

    public static final String getSoucheDepotGrpSouche =
            "                    DECLARE @prot_no as INT = ?\n" +
            "                    DECLARE @type as VARCHAR(50) = ?\n" +
                "                    SELECT cbIndice,S_Intitule,ISNULL(MAX(IsPrincipal),0)IsPrincipal\n" +
                "                    FROM(\n" +
                "                    SELECT  Prot_No,D.DE_No,IsPrincipal,\n" +
                "                    CASE WHEN @type IN ('Vente','Devis','BonLivraison','VenteRetour','Avoir') THEN CA_SoucheVente ELSE \n" +
                "                            CASE WHEN @type IN('Achat','AchatRetour','AchatPreparationCommande','PreparationCommande') THEN CA_SoucheAchat ELSE CA_SoucheStock END END AS cbIndice,\n" +
                "                    CASE WHEN @type IN ('Vente','Devis','BonLivraison','VenteRetour','Avoir') THEN V.S_Intitule ELSE \n" +
                "                            CASE WHEN @type IN ('Achat','AchatRetour','AchatPreparationCommande','PreparationCommande')  THEN A.S_Intitule ELSE St.S_Intitule END END AS S_Intitule,S.CA_Num,CA_Intitule\n" +
                "                    FROM Z_DEPOTUSER D \n" +
                "                    LEFT JOIN Z_DEPOTSOUCHE S ON D.DE_No=S.DE_No\n" +
                "                    LEFT JOIN P_SOUCHEVENTE V ON V.cbIndice-1 = CA_SoucheVente AND V.S_Valide=1\n" +
                "                    LEFT JOIN P_SOUCHEACHAT A ON A.cbIndice-1 = CA_SoucheAchat AND A.S_Valide=1\n" +
                "                    LEFT JOIN P_SOUCHEINTERNE St ON St.cbIndice-1 = CA_SoucheStock AND St.S_Valide=1\n" +
                "                    LEFT JOIN F_COMPTEA Af ON Af.CA_Num = S.CA_Num\n" +
                "                    WHERE Prot_No=@protNo)A\n" +
                "                        WHERE cbIndice is not null\n" +
                "                    GROUP BY cbIndice,S_Intitule";

    public static final String getSoucheDepotCaisse =
            " DECLARE @souche AS INT = ?\n" +
            " DECLARE @depot AS INT = ?\n" +
                    " DECLARE @ca_no AS INT = ?\n" +
                    " DECLARE @ca_num AS VARCHAR(50) = ?\n" +
                    " DECLARE @type AS VARCHAR(50) = ?\n" +
                    " SELECT DE_No,CA_No,CA_Souche,CA_Num,CA_CatTarif\n" +
                    "                FROM (SELECT D.DE_No,ISNULL(CA_No,0)CA_No,\n" +
                    "                    ISNULL(CASE WHEN (@type='Vente') OR (@type='Devis') OR (@type='VenteRetour') OR (@type='BonLivraison') THEN CA_SoucheVente ELSE \n" +
                    "                                CASE WHEN @type='Achat' OR @type='AchatRetour' OR @type='AchatPreparationCommande'  THEN CA_SoucheAchat ELSE CA_SoucheStock END END,0) CA_Souche,\n" +
                    "                    ISNULL(CA_Num,'')CA_Num\n" +
                    "                    ,ISNULL(CA_CatTarif,1)CA_CatTarif\n" +
                    "                    FROM Z_DEPOTUSER D\n" +
                    "                    LEFT JOIN Z_DEPOTCAISSE C ON C.DE_No=D.DE_No\n" +
                    "                    LEFT JOIN Z_DEPOTSOUCHE S ON S.DE_No=D.DE_No\n" +
                    "                    LEFT JOIN Z_DEPOT_DETAIL DD ON DD.DE_No=D.DE_No)A\n" +
                    "                    WHERE ('-1'=@souche OR CA_Souche =@souche)\n" +
                    "                    AND ('0'=@depot OR DE_No =@depot)\n" +
                    "                    AND ('0'=@ca_no OR CA_No =@ca_no)\n" +
                    "                    AND (''=@ca_num OR CA_Num =@ca_num)\n" +
                    "                GROUP BY DE_No,CA_No,CA_Souche,CA_Num,CA_CatTarif";
    ;

    public static final String getAllProfils =
                    "SELECT PROT_No,PROT_DateCreate = CAST(PROT_DateCreate AS DATE),cbModification = CAST(cbModification AS DATE),PROT_User\n" +
                    " FROM F_PROTECTIONCIAL\n" +
                    " WHERE PROT_UserProfil=0\n" +
                    " AND PROT_Right=2";

    public static final String createUser =
            "BEGIN \n" +
                    "        DECLARE @ProtUser AS NVARCHAR(50) = ?\n" +
                    "        DECLARE @ProtPwd AS NVARCHAR(50) = ?\n" +
                    "        DECLARE @ProtDescription AS NVARCHAR(50) = ?\n" +
                    "        DECLARE @ProtRight AS INT = ?\n" +
                    "        DECLARE @ProtEmail AS NVARCHAR(50) = ?\n" +
                    "        DECLARE @ProtUserProfil AS NVARCHAR(50) = ?\n" +
                    "        DECLARE @ProtPwdStatus AS INT = ?;\n" +
                    "        \n" +
                    "        SET NOCOUNT ON;\n" +
                    "        IF NOT EXISTS (SELECT 1 FROM F_PROTECTIONCIAL WHERE PROT_User = '{$this->PROT_User}')\n" +
                    "        BEGIN \n" +
                    "            INSERT INTO F_PROTECTIONCPTA\n" +
                    "           ([PROT_User]           ,[PROT_Pwd]\n" +
                    "           ,[PROT_Description]           ,[PROT_Right]\n" +
                    "           ,[PROT_No]           ,[PROT_EMail]\n" +
                    "           ,[PROT_UserProfil]           ,[PROT_Administrator]\n" +
                    "           ,[PROT_DatePwd]           ,[PROT_DateCreate]\n" +
                    "           ,[PROT_LastLoginDate]           ,[PROT_LastLoginTime]\n" +
                    "           ,[PROT_PwdStatus]           ,[cbProt]\n" +
                    "           ,[cbCreateur]           ,[cbModification]\n" +
                    "           ,[cbReplication]           ,[cbFlag])\n" +
                    "     VALUES\n" +
                    "           (@ProtUser,@ProtPwd\n" +
                    "           , @ProtDescription,@ProtRight,(SELECT ISNULL((SELECT MAX(PROT_No) FROM F_PROTECTIONCPTA),0)+1)\n" +
                    "           ,@ProtEmail,@ProtUserProfil,0,GETDATE(),GETDATE()\n" +
                    "           ,'1900-01-01',000000000,@ProtPwdStatus,0\n" +
                    "           ,'COLU',GETDATE(),0,0);\n" +
                    "        INSERT INTO [F_PROTECTIONCIAL]\n" +
                    "           ([PROT_User],[PROT_Pwd]\n" +
                    "           ,[PROT_Description]           ,[PROT_Right]\n" +
                    "           ,[PROT_No]           ,[PROT_EMail]\n" +
                    "           ,[PROT_UserProfil]           ,[PROT_Administrator]\n" +
                    "           ,[PROT_DatePwd]           ,[PROT_DateCreate]\n" +
                    "           ,[PROT_LastLoginDate]           ,[PROT_LastLoginTime]\n" +
                    "           ,[PROT_PwdStatus]           ,[cbProt]\n" +
                    "           ,[cbCreateur]           ,[cbModification]\n" +
                    "           ,[cbReplication]           ,[cbFlag])\n" +
                    "     VALUES\n" +
                    "           (@ProtUser,@ProtPwd\n" +
                    "           , @ProtDescription,@ProtRight,(SELECT ISNULL((SELECT MAX(PROT_No) FROM F_PROTECTIONCIAL),0)+1)\n" +
                    "           ,@ProtEmail,@ProtUserProfil,0,GETDATE(),GETDATE()\n" +
                    "           ,'1900-01-01',000000000,@ProtPwdStatus,0\n" +
                    "           ,'COLU',GETDATE(),0,0)  \n" +
                    "           SELECT @@IDENTITY cbMarq\n" +
                    "           END\n" +
                    "           ELSE SELECT -1 cbMarq\n" +
                    "       END";
    public static final String getSoucheDepotGrpAffaire=
            " DECLARE @type AS VARCHAR(50) = ?\n" +
            " DECLARE @prot_no AS INT = ?\n" +
            " DECLARE @sommeil AS INT = ?\n" +
            " SELECT CA_Num,CA_Intitule,ISNULL(MAX(IsPrincipal),0)IsPrincipal\n" +
            " FROM(\n" +
            "       SELECT  Prot_No,D.DE_No,IsPrincipal,\n" +
            "       CASE WHEN '@type'='Vente' THEN CA_SoucheVente ELSE\n" +
            "               CASE WHEN '@type'='Achat' THEN CA_SoucheAchat ELSE CA_SoucheStock END END AS cbIndice,\n" +
            "       CASE WHEN '@type'='Vente' THEN V.S_Intitule ELSE\n" +
            "               CASE WHEN '@type'='Achat' THEN A.S_Intitule ELSE St.S_Intitule END END AS S_Intitule,S.CA_Num,CA_Intitule\n" +
            "               FROM Z_DEPOTUSER D\n" +
            "               LEFT JOIN Z_DEPOTSOUCHE S ON D.DE_No=S.DE_No\n" +
            "               LEFT JOIN P_SOUCHEVENTE V ON V.cbIndice-1 = CA_SoucheVente AND V.S_Valide=1\n" +
            "               LEFT JOIN P_SOUCHEACHAT A ON A.cbIndice-1 = CA_SoucheAchat AND A.S_Valide=1\n" +
            "               LEFT JOIN P_SOUCHEINTERNE St ON St.cbIndice-1 = CA_SoucheStock AND St.S_Valide=1\n" +
            "               LEFT JOIN F_COMPTEA Af ON Af.CA_Num = S.CA_Num\n" +
            "                         WHERE Prot_No=@prot_no AND (@sommeil=-1 OR CA_Sommeil=@sommeil))A\n" +
    " WHERE CA_Intitule is not null AND IsPrincipal=1\n" +
    " GROUP BY CA_Num,CA_Intitule";

    public static final String getUtilisateurAdminMain
            =   "select *,CASE WHEN userName='' THEN ProfilName ELSE userName END Prot_User\n" +
            "                from (\n" +
            "                SELECT ROW_NUMBER() OVER (ORDER BY A.PROT_No,A.PROT_User)position,A.PROT_No,B.PROT_No PROT_No_User,A.PROT_User ProfilName,B.Prot_User as userName --PROT_No,Prot_User \n" +
            "                FROM F_Protectioncial A\n" +
            "                LEFT JOIN F_PROTECTIONCIAL B ON A.PROT_No=B.PROT_UserProfil\n" +
            "                WHERE A.PROT_UserProfil=0\n" +
            "                AND B.Prot_User is not null\n" +
            "                ) A\n" +
            "                order by 2,1";
    public static final String alerteDocumentCatComptaTaxe
            ="SELECT\tfde.DO_Domaine,fde.DO_Type,fde.DO_Piece,fde.cbMarq\n" +
            "                    FROM\tF_DOCENTETE fde\n" +
            "                    INNER JOIN F_DOCLIGNE fdl\n" +
            "                        ON\tfde.DO_Type = fdl.DO_Type\n" +
            "                        AND fde.DO_Domaine = fdl.DO_Domaine\n" +
            "                        AND fde.DO_Piece = fdl.DO_Piece\n" +
            "                    WHERE\tN_CatCompta=2\n" +
            "                    AND\t\tDL_Taxe1<>0";

    public static final String Connexion
            = "DECLARE @user NVARCHAR(50) = ?\n" +
            "        DECLARE @mdp NVARCHAR(50) = ?\n" +
            "        DECLARE @protNo INT = ?   \n" +
            "        SELECT\tcbMarq\n" +
            "\t\t,PROT_User\n" +
            "\t\t,PROT_Pwd\n" +
            "\t\t,Prot_No\n" +
            "\t\t,PROT_PwdStatus\n" +
            "\t\t,ProfilName\n" +
            "        ,ProtectAdmin\n" +
            "\t\t,PROT_UserProfil\n" +
            "\t\t,CASE WHEN PROT_Administrator =1 OR PROT_Right=1 THEN 1 ELSE 0 END PROT_Administrator\n" +
            "\t\t,PROT_Description\n" +
            "\t\t,PROT_Email\n" +
            "\t\t,PROT_Right \n" +
            "        ,ISNULL([33068],0) PROT_CBCREATEUR\n" +
            "\t\t,ISNULL([33541],0) PROT_CLIENT\n" +
            ",ISNULL([33537],0) PROT_FAMILLE\n" +
            ",ISNULL([12132],0) PROT_OUVERTURE_TOUTE_LES_CAISSES\n" +
            ",ISNULL([33538],0) PROT_ARTICLE\n" +
            ",ISNULL([34051],0) PROT_DOCUMENT_STOCK\n" +
            ",ISNULL([34049],0) PROT_DOCUMENT_VENTE\n" +
            ",ISNULL([6150],0) PROT_DOCUMENT_VENTE_FACTURE\n" +
            ",ISNULL([6145],0) PROT_DOCUMENT_VENTE_DEVIS\n" +
            ",ISNULL([34050],0) PROT_DOCUMENT_ACHAT\n" +
            ",ISNULL([6404],0) PROT_DOCUMENT_ACHAT_RETOUR\n" +
            ",ISNULL([8193],0) PROT_DOCUMENT_ENTREE\n" +
            ",ISNULL([8194],0) PROT_DOCUMENT_SORTIE\n" +
            ",ISNULL([34056],0) PROT_DOCUMENT_REGLEMENT\t\n" +
            ",ISNULL([6147],0) PROT_DOCUMENT_VENTE_BLIVRAISON\n" +
            ",ISNULL([34089],0) PROT_SAISIE_INVENTAIRE\n" +
            ",ISNULL([6148],0) PROT_DOCUMENT_VENTE_RETOUR\n" +
            ",ISNULL([6149],0) PROT_DOCUMENT_VENTE_AVOIR\n" +
            ",ISNULL([33547],0) PROT_DEPOT\n" +
            ",ISNULL([33542],0) PROT_FOURNISSEUR\n" +
            ",ISNULL([33546],0) PROT_COLLABORATEUR\n" +
            ",ISNULL([34056],0) PROT_SAISIE_REGLEMENT \n" +
            ",ISNULL([30081],0) PROT_SAISIE_REGLEMENT_FOURNISSEUR\n" +
            ",ISNULL([12125],0) PROT_PX_ACHAT\n" +
            ",ISNULL([12126],0) PROT_PX_REVIENT\n" +
            ",ISNULL([12119],0) PROT_DATE_COMPTOIR\n" +
            ",ISNULL([12116],0) PROT_DATE_VENTE\n" +
            ",ISNULL([12117],0) PROT_DATE_ACHAT\n" +
            ",ISNULL([12118],0) PROT_DATE_STOCK\n" +
            ",ISNULL([34563],0) PROT_MVT_CAISSE\n" +
            ",ISNULL([12129],0) PROT_QTE_NEGATIVE\n" +
            ",ISNULL([12124],0) PROT_DATE_RGLT\n" +
            ",ISNULL([9985],0) PROT_RISQUE_CLIENT\n" +
            ",ISNULL([5122],0) PROT_CATCOMPTA -- traitement liste client complément\n" +
            ",ISNULL([8195],0) PROT_DEPRECIATION_STOCK\n" +
            ",ISNULL([12136],0) PROT_CTRL_TT_CAISSE\n" +
            ",ISNULL([12137],0) PROT_AFFICHAGE_VAL_CAISSE\n" +
            ",ISNULL([4868],0) PROT_INFOLIBRE_ARTICLE\n" +
            ",ISNULL([12121],0) PROT_DATE_MVT_CAISSE\n" +
            ",ISNULL([6401],0) PROT_DOCUMENT_ACHAT_PREPARATION_COMMANDE\n" +
            ",ISNULL([6406],0) PROT_DOCUMENT_ACHAT_FACTURE\n" +
            ",ISNULL([12128],0) PROT_MODIF_SUPPR_COMPTOIR\n" +
            ",ISNULL([11009],0) PROT_AVANT_IMPRESSION\n" +
            ",ISNULL([11010],0) PROT_APRES_IMPRESSION\n" +
            ",ISNULL([11011],0) PROT_TICKET_APRES_IMPRESSION\n" +
            ",ISNULL([34067],0) PROT_GENERATION_RGLT_CLIENT\n" +
            ",ISNULL([12306],0) PROT_DOCUMENT_INTERNE_2\n" +
            ",ISNULL([12134],0) PROT_MODIFICATION_CLIENT\n" +
            ",ISNULL([36678],0) PROT_ETAT_INVENTAIRE_PREP\n" +
            ",ISNULL([36677],0) PROT_ETAT_LIVRE_INV\n" +
            ",ISNULL([36672],0) PROT_ETAT_STAT_ARTICLE_PAR_ART\n" +
            ",ISNULL([36673],0) PROT_ETAT_STAT_ARTICLE_PAR_FAM\n" +
            ",ISNULL([36674],0) PROT_ETAT_STAT_ARTICLE_PALMARES\n" +
            ",ISNULL([34316],0) PROT_ETAT_MVT_STOCK\n" +
            ",ISNULL([36645],0) PROT_ETAT_CLT_PAR_FAM_ART\n" +
            ",ISNULL([36646],0) PROT_ETAT_CLT_PAR_ARTICLE\n" +
            ",ISNULL([36647],0) PROT_ETAT_PALMARES_CLT\n" +
            ",ISNULL([36661],0) PROT_ETAT_STAT_FRS_FAM_ART\n" +
            ",ISNULL([36662],0) PROT_ETAT_STAT_FRS\n" +
            ",ISNULL([12133],0) PROT_GEN_ECART_REGLEMENT\n" +
            ",ISNULL([36736],0) PROT_ETAT_STAT_CAISSE_ARTICLE\n" +
            ",ISNULL([36737],0) PROT_ETAT_STAT_CAISSE_FAM_ARTICLE\n" +
            ",ISNULL([36738],0) PROT_ETAT_CAISSE_MODE_RGLT\n" +
            ",ISNULL([36356],0) PROT_ETAT_RELEVE_CPTE_CLIENT\n" +
            ",ISNULL([36688],0) PROT_ETAT_STAT_COLLAB_PAR_TIERS\n" +
            ",ISNULL([36689],0) PROT_ETAT_STAT_COLLAB_PAR_ARTICLE\n" +
            ",ISNULL([36690],0) PROT_ETAT_STAT_COLLAB_PAR_FAMILLE\n" +
            ",ISNULL([34306],0) PROT_ETAT_STAT_ACHAT_ANALYTIQUE\n" +
            ",ISNULL([36357],0) PROT_ETAT_RELEVE_ECH_CLIENT\n" +
            ",ISNULL([36358],0) PROT_ETAT_RELEVE_ECH_FRS\n" +
            ",ISNULL([34562],0) PROT_VENTE_COMPTOIR\n" +
            ",ISNULL([12130],0) PROT_SAISIE_PX_VENTE_REMISE\n" +
            ",ISNULL([5126],0) PROT_TARIFICATION_CLIENT\n" +
            ",ISNULL([34095],0) PROT_CLOTURE_CAISSE\n" +
            ",ISNULL([34817],0) PROT_PLAN_COMPTABLE\n" +
            ",ISNULL([34818],0) PROT_PLAN_ANALYTIQUE\n" +
            ",ISNULL([34819],0) PROT_TAUX_TAXE\n" +
            ",ISNULL([34820],0) PROT_CODE_JOURNAUX\n" +
            ",ISNULL([34821],0) PROT_LISTE_BANQUE\n" +
            ",ISNULL([34822],0) PROT_LISTE_MODELE_REGLEMENT\n" +
            ",ISNULL([34319],0) PROT_REAPPROVISIONNEMENT\n" +
            ",ISNULL([12309],0) PROT_DOCUMENT_INTERNE_5\n" +
            ",ISNULL([6146],0) PROT_DOCUMENT_VENTE_BONCOMMANDE\n" +
            ",ISNULL([8196],0) PROT_VIREMENT_DEPOT\n" +
            "FROM(\n" +
            "        SELECT\tP.cbMarq\n" +
            "                ,PROT_User\n" +
            "                ,PROT_Pwd\n" +
            "                ,P.Prot_No\n" +
            "                ,Prot_UserProfil\n" +
            "                ,P.PROT_PwdStatus\n" +
            "                ,P.Prot_Email\n" +
            "                ,ProtectAdmin = ISNULL(zpu.ProtectAdmin,0)\n" +
            "                ,ISNULL(ProfilName,PROT_User)ProfilName\n" +
            "                ,PROT_Administrator\n" +
            "                ,PROT_Description\n" +
            "                ,(CASE WHEN PROT_Description='SUPERVISEUR' OR PROT_Description='RAF' THEN 1 ELSE PROT_Right END) PROT_Right \n" +
            "                ,ISNULL(CASE WHEN C.EPROT_Cmd IS NULL THEN B.EPROT_Right ELSE C.EPROT_Right END,0)EPROT_Right\n" +
            "                ,B.EPROT_Cmd\n" +
            "        FROM    F_PROTECTIONCIAL P\n" +
            "        LEFT JOIN ( SELECT  Prot_No AS ProtUserProfilP\n" +
            "                            ,PROT_User AS ProfilName \n" +
            "                    FROM    F_PROTECTIONCIAL) Profil \n" +
            "            ON Prot_UserProfil = ProtUserProfilP \n" +
            "        LEFT JOIN (SELECT   PROT_No\n" +
            "                            ,EPROT_Right\n" +
            "                            ,Libelle_Cmd,A.EPROT_Cmd\n" +
            "                    FROM    F_EPROTECTIONCIAL A \n" +
            "                    LEFT JOIN LIB_CMD B \n" +
            "                        ON  B.PROT_Cmd = A.EPROT_Cmd\n" +
            "                    ) B \n" +
            "            ON B.PROT_No = ProtUserProfilP\n" +
            "        LEFT JOIN (SELECT PROT_No,EPROT_Right,Libelle_Cmd,A.EPROT_Cmd\n" +
            "                    FROM F_EPROTECTIONCIAL A \n" +
            "                    LEFT JOIN LIB_CMD B \n" +
            "                        ON B.PROT_Cmd = A.EPROT_Cmd) C\n" +
            "            ON C.PROT_No = P.PROT_No\n" +
            "        LEFT JOIN Z_ProtUser zpu            \n" +
            "            ON zpu.PROT_No = P.PROT_No\n" +
            "        WHERE (PROT_Right<>0 OR (PROT_Right=0 AND b.EPROT_Cmd IN (\t'33541','33537','33538','34051','34049'\n" +
            "                                ,'6150','6145','34050','8193','8194','34067','12306','8196'\n" +
            "                                ,'36678','36677','36672','36673','36674','6404','12132'\n" +
            "                                ,'12133','36736','36737','36738','36357','36358','5122','34819','6146'\n" +
            "                                ,'34316','36645','36646','36647','34562','12130','34095','34817','34822','34319'\n" +
            "                                ,'34056','6147','34089','6148','6149','33547','33542','33546','34818','34820','12309'\n" +
            "                                ,'30081','12125','12126','12119','12116','12117','12118','34563','12134','5126','34821'\n" +
            "                                ,'12129','12124','9985','8195','12136','12137','4868','12121','6401','6406','12128','11009','11010','11011'\n" +
            "                                ,'36356','36688','36689','36690','36661','36662','34306','33068')))\n" +
            "        )A\n" +
            "        \n" +
            "        PIVOT(\n" +
            "        SUM(EPROT_RIGHT)\n" +
            "        FOR EPROT_Cmd IN (\t[33541],[33537],[33538],[34051],[34049],[6150],[6145],[34050],[8193],[8194]\n" +
            "                            ,[34056],[6147],[34089],[6148],[6149],[33547],[33542],[33546],[34067],[12306]\n" +
            "                            ,[30081],[12125],[12126],[12119],[12116],[12117],[12118],[34563],[12134]\n" +
            "                            ,[36678],[36677],[36672],[36673],[36674],[34562],[6404],[12132],[6146]\n" +
            "                            ,[12133],[36736],[36737],[36738],[36357],[36358],[34095],[34817],[34822],[8196]\n" +
            "                            ,[36356],[36688],[36689],[36690],[34306],[12130],[33068],[5122],[34820],[34821]\n" +
            "                            ,[34316],[36645],[36646],[36647],[36661],[36662],[5126],[34818],[34819],[34319],[12309]\n" +
            "                            ,[12129],[12124],[9985],[8195],[12136],[12137],[4868],[12121],[6401],[6406],[12128],[11009],[11010],[11011]))AS PIVOTTABLE\n" +
            "                        WHERE (@protNo=0 AND PROT_User=@user AND PROT_Pwd=@mdp) OR (@protNo!=0 AND PROT_No=@protNo) "
            ;

    public static final String getCollaborateurEnvoiMail=
            "SELECT  CO_No,CO_Nom\n" +
            "                        ,CO_Prenom,CO_EMail\n" +
            "                        ,CO_Telephone,PROT_User\n" +
            "                FROM    [Z_LiaisonEnvoiMailUser] A\n" +
            "                INNER JOIN F_PROTECTIONCIAL B \n" +
            "                    ON  A.PROT_No=B.PROT_No\n" +
            "                INNER JOIN F_COLLABORATEUR C \n" +
            "                    ON  C.CO_Nom=B.PROT_User\n" +
            "                INNER JOIN [dbo].[Z_TypeEnvoiMail] D \n" +
            "                    ON  D.TE_No=A.TE_No\n" +
            "                WHERE   TE_Intitule=?";

    public static final String getCollaborateurEnvoiSMS=
            "SELECT  CO_No,CO_Nom,CO_Prenom\n" +
            "                        ,CO_EMail,CO_Telephone\n" +
            "                        ,PROT_User\n" +
            "                FROM    [Z_LiaisonEnvoiSMSUser] A\n" +
            "                INNER JOIN F_PROTECTIONCIAL B \n" +
            "                    ON  A.PROT_No=B.PROT_No\n" +
            "                INNER JOIN F_COLLABORATEUR C \n" +
            "                    ON  C.CO_Nom=B.PROT_User\n" +
            "                INNER JOIN [dbo].[Z_TypeEnvoiMail] D \n" +
            "                    ON D.TE_No=A.TE_No\n" +
            "                WHERE TE_Intitule=?";
    public static final String getCoNo=
    "SELECT CO_No\n"+
            "                FROM F_PROTECTIONCIAL P \n"+
            "                INNER JOIN F_COLLABORATEUR C ON P.PROT_User = C.CO_Nom\n"+
            "                WHERE CO_Nom = ?";

    public static final String connexionProctectionByProtNo =
            "SELECT PROT_User, PROT_Pwd" +
                    " FROM F_PROTECTIONCIAL" +
                    "        WHERE PROT_No = ?";

    public static final String allProfil =
            "SELECT PROT_User,PROT_No \n" +
            "            FROM F_PROTECTIONCIAL\n" +
            "            WHERE PROT_UserProfil=0\n" +
            "            AND PROT_Right=2";

    public static final String getInfoRAFControleur =
    "SELECT CO_No,CO_Nom,CO_Prenom,CO_EMail,CO_Telephone,PROT_User\n"+
            "                FROM ( SELECT P.* FROM F_PROTECTIONCIAL P \n"+
            "                LEFT JOIN (SELECT PROT_No FROM F_PROTECTIONCIAL WHERE PROT_User='RAF') A ON A.PROT_No=P.PROT_UserProfil \n"+
            "                WHERE A.PROT_No IS NOT NULL \n"+
            "                ) A \n"+
            "                INNER JOIN F_COLLABORATEUR C ON C.CO_Nom = A.PROT_User";
}