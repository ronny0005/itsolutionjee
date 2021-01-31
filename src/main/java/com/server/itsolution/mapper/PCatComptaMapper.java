package com.server.itsolution.mapper;

public class PCatComptaMapper extends ObjectMapper {

    public static final String catComptaAchat = "select  row_number() over (order by u.subject) as idcompta,u.marks "+
            " from P_CATCOMPTA "+
            " unpivot "+
            "     ( "+
            "              marks "+
            "                     for subject in (CA_ComptaAch01, CA_ComptaAch02,CA_ComptaAch03,CA_ComptaAch04,CA_ComptaAch05,CA_ComptaAch06,CA_ComptaAch07,CA_ComptaAch08,CA_ComptaAch09,CA_ComptaAch10,CA_ComptaAch11,CA_ComptaAch12,CA_ComptaAch13,CA_ComptaAch14,CA_ComptaAch15,CA_ComptaAch16,CA_ComptaAch17,CA_ComptaAch18,CA_ComptaAch19,CA_ComptaAch20,CA_ComptaAch21,CA_ComptaAch22) "+
            "                    ) u WHERE marks<>''";

    public static final String catComptaVente = "select  row_number() over (order by u.subject) as idcompta,u.marks "+
   " from P_CATCOMPTA "+
   " unpivot "+
  "          ( "+
 "                   marks "+
"              for subject in (CA_ComptaVen01, CA_ComptaVen02,CA_ComptaVen03,CA_ComptaVen04,CA_ComptaVen05,CA_ComptaVen06,CA_ComptaVen07,CA_ComptaVen08,CA_ComptaVen09,CA_ComptaVen10,CA_ComptaVen11,CA_ComptaVen12,CA_ComptaVen13,CA_ComptaVen14,CA_ComptaVen15,CA_ComptaVen16,CA_ComptaVen17,CA_ComptaVen18,CA_ComptaVen19,CA_ComptaVen20,CA_ComptaVen21,CA_ComptaVen22) "+
    "        ) u "+
    " WHERE marks<>''";

    public static final String BASE_SQL //
            =   "select  row_number() over (order by u.subject) as idcompta,u.marks,1 AS Type "+
"            from P_CATCOMPTA "+
 "   unpivot "+
  "          ( "+
   "                 marks "+
    "              for subject in (CA_ComptaAch01, CA_ComptaAch02,CA_ComptaAch03,CA_ComptaAch04,CA_ComptaAch05,CA_ComptaAch06,CA_ComptaAch07,CA_ComptaAch08,CA_ComptaAch09,CA_ComptaAch10,CA_ComptaAch11,CA_ComptaAch12,CA_ComptaAch13,CA_ComptaAch14,CA_ComptaAch15,CA_ComptaAch16,CA_ComptaAch17,CA_ComptaAch18,CA_ComptaAch19,CA_ComptaAch20,CA_ComptaAch21,CA_ComptaAch22) "+
     "           ) u WHERE marks<>'' "+
    " union "+
    " select  row_number() over (order by u.subject) as idcompta,u.marks,0 AS Type "+
    " from P_CATCOMPTA "+
    " unpivot "+
     "       ( "+
     "               marks "+
     "             for subject in (CA_ComptaVen01, CA_ComptaVen02,CA_ComptaVen03,CA_ComptaVen04,CA_ComptaVen05,CA_ComptaVen06,CA_ComptaVen07,CA_ComptaVen08,CA_ComptaVen09,CA_ComptaVen10,CA_ComptaVen11,CA_ComptaVen12,CA_ComptaVen13,CA_ComptaVen14,CA_ComptaVen15,CA_ComptaVen16,CA_ComptaVen17,CA_ComptaVen18,CA_ComptaVen19,CA_ComptaVen20,CA_ComptaVen21,CA_ComptaVen22) "+
     "           ) u "+
    " WHERE marks<>''";

    public static final String allCount = "SELECT COUNT(*) Nb FROM("+BASE_SQL+")A";


}