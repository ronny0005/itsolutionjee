package com.server.itsolution.mapper;

public class FCatalogueMapper extends ObjectMapper {

    public static final String BASE_SQL
            =   "SELECT CL_No" +
            "           ,CL_Intitule,CL_Code,CL_Stock,CL_NoParent,CL_Niveau,cbMarq,cbCreateur,cbFlag,cbReplication " +
                " FROM F_CATALOGUE ";
    public static final String getCatalogueByCL = BASE_SQL +" WHERE CL_No=? ";

    public static final String getCatalogue = BASE_SQL +" WHERE CL_Niveau=? ";

    public static final String getCatalogueChildren = BASE_SQL +" WHERE CL_Niveau=? AND CL_NoParent=? ";

    public static final String getLastCatalogue = "SELECT top 1 CL_No,CL_Intitule FROM F_CATALOGUE ORDER by Cbmarq desc";

    public static final String insertFCatalogue
        ="INSERT INTO [dbo].[F_CATALOGUE] "+
        " ([CL_No],[CL_Intitule],[CL_Code],[CL_Stock] "+
        "    ,[CL_NoParent],[CL_Niveau],[cbProt] "+
        "    ,[cbCreateur],[cbModification],[cbReplication],[cbFlag]) "+
        " VALUES "+
        "        (/*CL_No*/(SELECT ISNULL(MAX(CL_No),0)+1 FROM F_CATALOGUE),/*CL_Intitule*/?,/*CL_Code*/'',/*CL_Stock*/0 "+
        " ,/*CL_NoParent*/?,/*CL_Niveau*/?,/*cbProt*/0 "+
        "        ,/*cbCreateur*/?,/*cbModification*/GETDATE(),/*cbReplication*/0,/*cbFlag*/0)";

    public static final String  updateFCatalogue =
         "UPDATE F_CATALOGUE SET CL_Intitule=?,cbCreateur=?,cbModification=GETDATE() WHERE CL_No=?";

    public static final String  deleteFCatalogue =
            "DELETE FROM F_CATALOGUE WHERE CL_No=?";

}