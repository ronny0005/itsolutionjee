package com.server.itsolution.entities;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public interface FormatText {
    public static String getMontant(Double value){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        NumberFormat goodNumberFormat1 = new DecimalFormat("#,##0.00#", symbols);
        Locale locale = new Locale("fr", "CM");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return goodNumberFormat1.format(value);
    }

    public static String getString(String value){
        try {
            String pourcent = value.replace(",,,,","%");
            String back = pourcent.replace(",,,","/");
            String urlDecode = URLDecoder.decode(back,"UTF-8");
            pourcent = urlDecode.replace(",,,,","%");
            back = pourcent.replace(",,,","/");
            String plus = back.replace("+"," ");
           urlDecode = URLDecoder.decode(plus,"UTF-8");
            return urlDecode;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
            return "";
        }
        return null;
    }

    public static Calendar getDateYYYYMMDD(String value){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(value));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }
}
