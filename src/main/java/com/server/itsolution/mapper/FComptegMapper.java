package com.server.itsolution.mapper;

public class FComptegMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select   CG_Num,CG_Type,CG_Intitule,CG_Classement\n" +
            "    ,N_Nature,CG_Report,CR_Num\n" +
            "    ,CG_Raccourci,CG_Saut,CG_Regroup\n" +
            "    ,CG_Analytique,CG_Echeance,CG_Quantite,CG_Lettrage\n" +
            "    ,CG_Tiers,CG_DateCreate\n" +
            "    ,CG_Devise,N_Devise,TA_Code,CG_Sommeil\n" +
            "    ,cbProt,cbMarq,cbCreateur,cbModification\n" +
            "    ,cbReplication,cbFlag From F_COMPTEG";

    public static final String getComptegByType =
            "SELECT  CG_Num,CG_Intitule\n" +
                    "        ,TA_Code,cbModification \n" +
                    "FROM    F_COMPTEG \n" +
                    "WHERE   CG_Type= ? \n" +
                    "ORDER BY CG_Num";

    public static final String getCGNum = BASE_SQL +
            " WHERE   CG_Num= ? \n";

    public static final String getComptegCount =
            " SELECT COUNT(*) Nb,MAX(cbModification) cbModification " +
            " FROM F_COMPTEG " +
            " WHERE CG_Type=0 " +
            " ORDER BY cbModification DESC\n";


}