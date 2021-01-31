package com.server.itsolution.mapper;

public class FContactDMapper extends ObjectMapper {

    public static final String BASE_SQL //
            =   "Select   CD_Nom,CD_Prenom,N_Service,CD_Fonction\n" +
            "    ,CD_Telephone,CD_TelPortable,CD_Telecopie,CD_EMail\n" +
            "    ,CD_Civilite,N_Contact,CD_Adresse,CD_Complement\n" +
            "    ,CD_CodePostal,CD_Ville,CD_No,cbProt\n" +
            "    ,cbCreateur,cbModification,cbReplication,cbFlag,cbMarq From F_CONTACTD";

    public static final String getContactD //
            =   BASE_SQL+ " WHERE CD_No = ?";
}