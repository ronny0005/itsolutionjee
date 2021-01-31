package com.server.itsolution.mapper;

public class ZCalendarUserMapper extends ObjectMapper {

    public static final String BASE_SQL
            =   "Select PROT_No,ID_JourDebut,ID_JourFin,ID_HeureDebut,ID_MinDebut,ID_HeureFin,ID_MinFin FROM Z_CALENDAR_USER ";

    public static final String getCalendarUser
            =   BASE_SQL + " WHERE PROT_No = ?";

    public static final String getCalendarUserConnexion
            =   "SELECT CASE WHEN CAST(? AS TIME) BETWEEN HeureDebut AND HeureFin THEN 1 ELSE 0 END as canConnect\n" +
            "                    FROM (SELECT PROT_No \n" +
            "\t\t\t\t\t\t\t\t,Id_JourDebut\t\n" +
            "\t\t\t\t\t\t\t\t,ID_JourFin\n" +
            "\t\t\t\t\t\t\t\t,CAST(CONCAT(CONCAT(RIGHT(CONCAT('00',ID_HeureDebut),2),':'),RIGHT(CONCAT('00',ID_MinDebut),2)) AS TIME) AS HeureDebut\n" +
            "                                ,CASE WHEN CAST(CONCAT(CONCAT(RIGHT(CONCAT('00',ID_HeureFin),2),':'),RIGHT(CONCAT('00',ID_MinFin),2)) AS TIME)='00:00' THEN '23:59' ELSE CAST(CONCAT(CONCAT(RIGHT(CONCAT('00',ID_HeureFin),2),':'),RIGHT(CONCAT('00',ID_MinFin),2)) AS TIME) END AS HeureFin\n" +
            "\t\t\t\t\t\t\tFROM Z_CALENDAR_USER)A\n" +
            "                    WHERE PROT_No = $PROT_No AND Id_JourDebut=?";


}