package com.server.itsolution.mapper;

import com.server.itsolution.entities.FModeleR;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FModeleRMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select   MR_No\n" +
            "            , MR_Intitule\n" +
            "            , cbMarq,cbModification FROM F_ModeleR ";


    public static final String getDateEcgetTiersheanceSelectSage //
            =   "DECLARE @date NVARCHAR(20) = ?\n" +
            "                    DECLARE @mrNo INT = ?\n" +
            "                    DECLARE @nReglement INT = ?;\n" +
            "                    SELECT  REPLACE(CONVERT(VARCHAR(8), CAST(ISNULL(DATEADD(DAY,ER_NbJour,@date),@date) AS DATE), 5),'-','') date_ech\n" +
            "                    FROM F_MODELER M \n" +
            "                    LEFT JOIN F_EMODELER EM ON EM.MR_No = M.MR_No\n" +
            "                    WHERE M.MR_No=@mrNo AND N_Reglement=@nReglement";
}