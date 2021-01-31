package com.server.itsolution.mapper;

import com.server.itsolution.entities.FModeleR;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FModeleRMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select   MR_No\n" +
            "            , MR_Intitule\n" +
            "            , cbMarq,cbModification FROM F_ModeleR ";
}