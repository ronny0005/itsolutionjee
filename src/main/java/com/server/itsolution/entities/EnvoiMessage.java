package com.server.itsolution.entities;

import com.server.itsolution.dao.EnvoiMailDAO;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public interface EnvoiMessage {


    public static void envoiMsgMvtSortie(double rgMontant, String libelle, String caIntitule, int protNo, String rgDate){
        String msg = "SORTIE D'UN MONTANT DE " + FormatText.getMontant(rgMontant) + " POUR " + libelle + " DANS LA CAISSE " + caIntitule + " SAISI PAR " + protNo + " LE " + rgDate;
    }


}
