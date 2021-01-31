package com.server.itsolution.mapper;

public class PSoucheMapper extends ObjectMapper {

    public static final String psoucheVente = "SELECT S_Intitule,cbIndice-1 as cbIndice,JO_Num FROM P_SOUCHEVENTE WHERE S_Intitule<>'' AND S_Valide=1";
    public static final String getPSoucheVente = psoucheVente + " AND cbIndice= ? ";

    public static final String psoucheAchat = "SELECT S_Intitule,(cbIndice-1) as cbIndice,JO_Num  FROM P_SOUCHEACHAT WHERE S_Intitule<>'' AND S_Valide=1";
    public static final String getPSoucheAchat = psoucheAchat + " AND cbIndice= ? ";

    public static final String psoucheInterne = "SELECT S_Intitule,(cbIndice-1) as cbIndice,'' JO_Num  FROM P_SOUCHEINTERNE WHERE S_Intitule<>'' AND S_Valide=1";
    public static final String getPSoucheInterne = psoucheAchat + " AND cbIndice= ? ";



}