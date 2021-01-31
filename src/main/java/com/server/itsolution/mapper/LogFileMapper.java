package com.server.itsolution.mapper;

public class LogFileMapper extends ObjectMapper {

    public static final String BASE_SQL //
        =   "INSERT INTO Z_LogInfo " +
            "VALUES (/*Action*/ ?,/*Type*/ ?,/*DoType*/ ?,/*DoEntete*/ ?,/*DeNo*/ ?,/*DoDomaine*/ ?,/*ArRef*/ ?,/*Qte*/ ?," +
                    "/*Prix*/ ?,/*Remise*/ ?,/*Montant*/ ?,/*Date*/ GETDATE(),/*UserName*/ ?,/*CbMarq*/ ?,/*Table*/ ?,/*CbCreateur*/ ?)";

}
