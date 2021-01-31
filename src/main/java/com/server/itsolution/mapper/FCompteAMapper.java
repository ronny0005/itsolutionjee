package com.server.itsolution.mapper;

public class FCompteAMapper extends ObjectMapper {

    public static final String BASE_SQL = "select  N_Analytique, CA_Type,CA_Report,N_Analyse,CA_Saut,CA_Sommeil,CA_Domaine,cbMarq,\n" +
                                                "       CA_Vente,CA_Achat,\n" +
                                                "       CA_Num,CA_Intitule,CA_Classement,CA_Raccourci,CA_DateCreate " +
                                                "       FROM F_CompteA ";
    public static final String affaire = BASE_SQL + " WHERE N_Analytique=1 AND CA_Type=0 AND (?=-1 OR CA_Sommeil=?)";

    public static final String getCANum = BASE_SQL + " WHERE CA_Num=?";

    public static final String getAll =
            "";
    public static final String getLigneCompteA =
            "SELECT A.CbMarq_Ligne,A_Intitule,A.N_Analytique,EA_Ligne,A.CA_Num,CA_Intitule,EA_Montant,EA_Quantite,A.cbMarq\n" +
            "                FROM Z_LIGNE_COMPTEA A\n" +
            "                LEFT JOIN F_COMPTEA B \n" +
            "                    ON A.CA_Num = B.CA_Num \n" +
            "                LEFT JOIN P_Analytique PA \n" +
            "                    ON PA.cbIndice = A.N_Analytique\n" +
            "                WHERE CbMarq_Ligne=?\n" +
            "                ORDER BY CbMarq_Ligne,EA_Ligne";
}