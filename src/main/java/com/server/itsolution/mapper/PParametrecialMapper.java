package com.server.itsolution.mapper;

import com.server.itsolution.entities.PParametrecial;
import com.server.itsolution.entities.PPreferences;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PParametrecialMapper extends ObjectMapper {

    public static final String BASE_SQL
            =   " Select    P_CreditCaisse,P_DebitCaisse,P_ChpAnal,P_ChpAnalDoc,P_Conversion,P_Indispo\n" +
            "               ,P_Echeances,P_StockSaisie,P_PeriodEncours,P_GestionPlanning,P_ReportPrixRev,cbMarq" +
                " FROM      P_PARAMETRECIAL";

    public PParametrecial mapRow(ResultSet rs, int rowNum) throws SQLException {
        PParametrecial pParametrecial = new PParametrecial();
        pParametrecial.setP_GestionPlanning(rs.getInt("P_GestionPlanning"));
        pParametrecial.setP_ReportPrixRev(rs.getInt("P_ReportPrixRev"));
        pParametrecial.setCbMarq(rs.getInt("cbMarq"));
        pParametrecial.setP_ChpAnal(rs.getInt("P_ChpAnal"));
        pParametrecial.setP_ChpAnalDoc(rs.getInt("P_ChpAnalDoc"));
        pParametrecial.setP_Conversion(rs.getInt("P_Conversion"));
        pParametrecial.setP_CreditCaisse(rs.getInt("P_CreditCaisse"));
        pParametrecial.setP_DebitCaisse(rs.getInt("P_DebitCaisse"));
        pParametrecial.setP_Echeances(rs.getInt("P_Echeances"));
        pParametrecial.setP_Indispo(rs.getInt("P_Indispo"));
        pParametrecial.setP_StockSaisie(rs.getInt("P_StockSaisie"));
        pParametrecial.setP_PeriodEncours(rs.getInt("P_PeriodEncours"));
        return pParametrecial;
    }
}