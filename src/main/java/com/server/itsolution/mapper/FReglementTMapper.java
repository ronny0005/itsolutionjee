package com.server.itsolution.mapper;

public class FReglementTMapper extends ObjectMapper {

    public static final String insert =
        "DECLARE @ctNum VARCHAR(50) = ?\n" +
				"                    ,@rtCondition INT = ?\n" +
				"                    ,@rtNbJour INT = ?\n" +
				"                    ,@rtJourTb01 INT = ?\n" +
				"                    ,@rtTRepart INT = ?\n" +
				"                    ,@rtVRepart FLOAT = ?;\n" +
				"            \n" +
				"            IF (SELECT TOP 1 1 FROM [F_REGLEMENTT] WHERE CT_Num = @ctNum) = 1 \n" +
				"                UPDATE F_REGLEMENTT \n" +
				"                    SET RT_Condition = @rtCondition\n" +
				"                        ,RT_NbJour = @rtNbJour\n" +
				"                        ,RT_JourTb01 = @rtJourTb01\n" +
				"                        ,RT_TRepart = @rtTRepart\n" +
				"                        ,RT_VRepart = @rtVRepart\n" +
				"                WHERE CT_Num = @ctNum\n" +
				"            ELSE\n" +
				"                INSERT INTO [dbo].[F_REGLEMENTT]\n" +
				"                   ([CT_Num],[N_Reglement],[RT_Condition],[RT_NbJour]\n" +
				"                   ,[RT_JourTb01],[RT_JourTb02],[RT_JourTb03],[RT_JourTb04]\n" +
				"                   ,[RT_JourTb05],[RT_JourTb06],[RT_TRepart],[RT_VRepart]\n" +
				"                   ,[cbProt],[cbCreateur],[cbModification],[cbReplication],[cbFlag])\n" +
				"                VALUES\n" +
				"                   (/*CT_Num*/@ctNum,/*N_Reglement*/1\n" +
				"                   ,/*RT_Condition*/@rtCondition,/*RT_NbJour*/@rtNbJour\n" +
				"                   ,/*RT_JourTb01*/@rtJourTb01,/*RT_JourTb02*/0\n" +
				"                   ,/*RT_JourTb03*/0,/*RT_JourTb04*/0\n" +
				"                   ,/*RT_JourTb05*/0,/*RT_JourTb06*/0\n" +
				"                   ,/*RT_TRepart*/@rtTRepart,/*RT_VRepart*/@rtVRepart\n" +
				"                   ,/*cbProt*/0,/*cbCreateur*/'AND'\n" +
				"                   ,/*cbModification*/CAST(GETDATE() AS DATE),/*cbReplication*/0,/*cbFlag*/0)";


}