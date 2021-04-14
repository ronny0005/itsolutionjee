package com.server.itsolution.mapper;

public class FReglEchMapper extends ObjectMapper {

    public static final String BASE_SQL
            =   "Select     RG_No,DR_No, DO_Domaine, DO_Type,DO_Piece, RC_Montant, RG_TypeReg " +
            "               FROM F_REGLECH";


    public static final String addReglEch
            = "BEGIN\n" +
            "                    SET DATEFORMAT dmy;" +
            "                    SET NOCOUNT ON;\n" +
            "                    DECLARE @RG_No INT = ?\n" +
            "                            ,@DR_No INT = ?\n" +
            "                            ,@DO_Domaine INT = ? \n" +
            "                            ,@DO_Type INT = ? \n" +
            "                            ,@DO_Piece NVARCHAR(20) = ? \n" +
            "                            ,@RC_Montant FLOAT = ?\n" +
            "                            ,@cbCreateur NVARCHAR(10) = ?\n" +
            "                            ,@RG_TypeReg INT = ?\n" +
            "                            ,@cbProt INT = 0\n" +
            "                            ,@cbReplication INT = 0\n" +
            "                            ,@cbFlag INT = 0\n" +
            "                            ,@count INT = 0\n" +
            "                            ,@rcMontant FLOAT\n" +
            "                            ,@dlMontantTTC FLOAT\n" +
            "                            ,@doProvenance INT" +
            "                            ,@RG_Montant AS FLOAT\n" +
            "                            ,@RC_MontantInsert FLOAT = 0;" +
            "                            SET @RC_MontantInsert = @RC_Montant;\n"+
            "                            SET @RC_Montant = ROUND(@RC_Montant,0);\n"+
            "                            \n" +
            "                            SELECT @doProvenance = DO_Provenance\n" +
            "                            FROM F_DOCENTETE\t\t\t \n" +
            "                            WHERE DO_Type=@DO_Type \n" +
            "                            AND DO_Domaine = @DO_Domaine \n" +
            "                            AND DO_Piece = @DO_Piece;\n" +
            "                            \n" +
            "                            SELECT @RG_Montant = ROUND(ISNULL(RG_Montant,0)-ISNULL(RC_Montant,0),0)\n" +
            "                            FROM F_CREGLEMENT cre\n" +
            "                            LEFT JOIN F_REGLECH reg\n" +
            "                               ON cre.RG_No = reg.RG_No\n" +
            "                            WHERE cre.RG_No=@RG_No\n" +
            "                            \n" +
            "                            SELECT @dlMontantTTC = ROUND(SUM(DL_MontantTTC) ,0) \n" +
            "                            FROM F_DOCLIGNE \n" +
            "                            WHERE DO_Type=@DO_Type \n" +
            "                            AND DO_Domaine = @DO_Domaine \n" +
            "                            AND DO_Piece = @DO_Piece\n" +
            "                            \n" +
            "                            SELECT @rcMontant = SUM(RC_Montant) \n" +
            "                                   ,@count = ISNULL(SUM(CASE WHEN RG_No = @RG_No THEN 1 ELSE 0 END),0) \n" +
            "                            FROM   F_REGLECH \n" +
            "                            WHERE  DO_Type=@DO_Type \n" +
            "                            AND    DO_Domaine = @DO_Domaine \n" +
            "                            AND    DO_Piece = @DO_Piece\n" +
            "                            \n" +
            "                            IF  (@doProvenance<>1 AND @RG_Montant>0 AND @RG_Montant >=@RC_Montant AND @RC_Montant <= @dlMontantTTC)\n" +
            "                                   OR (@doProvenance = 1 AND ABS(@RG_Montant)>0 AND ABS(@RG_Montant) >=ABS(@RC_Montant) AND ABS(@RC_Montant) <= ABS(@dlMontantTTC))\n" +
            "                               IF @count=0\n" +
            "                                    INSERT INTO [dbo].[F_REGLECH] \n" +
            "                                        ([RG_No],[DR_No],[DO_Domaine],[DO_Type],[DO_Piece],[RC_Montant],[RG_TypeReg],[cbProt] \n" +
            "                                        ,[cbCreateur],[cbModification],[cbReplication],[cbFlag]) \n" +
            "                                        VALUES \n" +
            "                                           (/*RG_No*/@RG_No,/*DR_No*/@DR_No,/*DO_Domaine*/@DO_Domaine,/*DO_Type*/@DO_Type\n" +
            "                                           ,/*DO_Piece*/@DO_Piece,/*RC_Montant*/@RC_MontantInsert,/*RG_TypeReg*/@RG_TypeReg,/*cbProt*/@cbProt \n" +
            "                                          ,/*cbCreateur*/@cbCreateur,/*cbModification*/GETDATE(),/*cbReplication*/@cbReplication,/*cbFlag*/@cbFlag);\n" +
            "                                ELSE\n" +
            "                                    UPDATE\tF_REGLECH \n" +
            "                                        SET RC_Montant = @RC_MontantInsert\n" +
            "                                    WHERE\tDO_Type=@DO_Type \n" +
            "                                    AND\t\tDO_Domaine = @DO_Domaine \n" +
            "                                    AND\t\tDO_Piece = @DO_Piece\n" +
            "                                    \n" +
            "                    \n" +
            "                END;";
}