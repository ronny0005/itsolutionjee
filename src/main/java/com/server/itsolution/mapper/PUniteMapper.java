package com.server.itsolution.mapper;

import org.json.JSONException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PUniteMapper extends ObjectMapper {


    public static final String BASE_SQL //
            =   " SELECT U_Intitule, U_Correspondance, U_NbUnite,U_UniteTemps\n" +
            " FROM P_Unite " +
            " WHERE U_Intitule <> '' ";

    public static final String getUniteByIndice //
            =   " SELECT U_Intitule, U_Correspondance, U_NbUnite,U_UniteTemps\n" +
                " FROM P_Unite" +
                " WHERE cbIndice = ? ";

}