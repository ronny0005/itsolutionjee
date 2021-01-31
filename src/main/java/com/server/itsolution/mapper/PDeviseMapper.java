package com.server.itsolution.mapper;

public class PDeviseMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "SELECT D_Intitule\n" +
            "      ,D_Format\n" +
            "      ,D_Cours\n" +
            "      ,D_CoursP\n" +
            "      ,D_Monnaie\n" +
            "      ,D_SousMonnaie\n" +
            "      ,D_CodeISO\n" +
            "      ,D_Sigle\n" +
            "      ,D_Mode\n" +
            "      ,N_DeviseCot\n" +
            "      ,D_CoursClot\n" +
            "      ,D_AncDate\n" +
            "      ,D_AncCours\n" +
            "      ,D_AncMode\n" +
            "      ,N_DeviseAncCot\n" +
            "      ,D_CodeRemise\n" +
            "      ,D_Euro\n" +
            "      ,D_CodeISONum\n" +
            "      ,cbIndice\n" +
            "      ,cbMarq\n" +
            "  FROM CMI.dbo.P_DEVISE";


}