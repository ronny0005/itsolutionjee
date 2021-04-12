package com.server.itsolution.mapper;

public class FEModeleRMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select   MR_No\n" +
            "            , N_Reglement\n" +
            "            , ER_Condition\n" +
            "            , ER_NbJour\n" +
            "            , ER_JourTb01\n" +
            "            , ER_JourTb02\n" +
            "            , ER_JourTb03\n" +
            "            , ER_JourTb04\n" +
            "            , ER_JourTb05\n" +
            "            , ER_JourTb06\n" +
            "            , ER_TRepart\n" +
            "            , ER_VRepart\n" +
            "            , cbModification\n" +
            "            , cbMarq FROM F_EModeleR ";

/*
    public FModeleR mapRow(ResultSet rs, int rowNum) throws SQLException {
        FModeleR fModeleR= new FModeleR();
        fModeleR.setCbMarq(rs.getInt("cbMarq"));
        fModeleR.setER_Condition(rs.getInt("ER_Condition"));
        fModeleR.setER_JourTb01(rs.getInt("ER_JourTb01"));
        fModeleR.setER_JourTb02(rs.getInt("ER_JourTb02"));
        fModeleR.setER_JourTb03(rs.getInt("ER_JourTb03"));
        fModeleR.setER_JourTb04(rs.getInt("ER_JourTb04"));
        fModeleR.setER_JourTb05(rs.getInt("ER_JourTb05"));
        fModeleR.setER_JourTb06(rs.getInt("ER_JourTb06"));
        fModeleR.setER_NbJour(rs.getInt("ER_NbJour"));
        fModeleR.setER_TRepart(rs.getInt("ER_TRepart"));
        fModeleR.setER_VRepart(rs.getInt("ER_VRepart"));
        fModeleR.setMR_No(rs.getInt("MR_No"));
        fModeleR.setN_Reglement(rs.getInt("N_Reglement"));
        return fModeleR;
    }
*/
}