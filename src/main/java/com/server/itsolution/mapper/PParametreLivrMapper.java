package com.server.itsolution.mapper;

public class PParametreLivrMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "SELECT [PL_Priorite1]\n" +
            "      ,[PL_Priorite2]\n" +
            "      ,[PL_Priorite3]\n" +
            "      ,[PL_Duree]\n" +
            "      ,[PL_TypeDuree]\n" +
            "      ,[PL_Reliquat]\n" +
            "      ,[PL_Quantite]\n" +
            "      ,[PL_Generation]\n" +
            "      ,[PL_Statut]\n" +
            "      ,[cbMarq]\n" +
            "  FROM [P_PARAMETRELIVR]";


    public static final String getPParametreLivr
            =   BASE_SQL + " WHERE cbMarq = ?";
}

