package com.server.itsolution.mapper;

public class FDocReglMapper extends ObjectMapper {

    public static final String BASE_SQL
            =   "Select     DR_No, DO_Domaine, DO_Type,DO_Piece, DR_TypeRegl,DR_Date,DR_Libelle,DR_Pourcent,DR_Montant,DR_MontantDev,DR_Equil,EC_No," +
            "               DR_Regle, N_Reglement, cbProt, cbMarq, cbCreateur, cbModification,cbReplication,cbFlag From F_DOCREGL";
    public static final String InsertFDocRegl
            =
            " SET DATEFORMAT dmy;" +
            " DECLARE @generated_keys table([cbMarq] int);" +
            " INSERT INTO [dbo].[F_DOCREGL] \n" +
            "                ([DR_No],[DO_Domaine],[DO_Type],[DO_Piece],[DR_TypeRegl],[DR_Date],[DR_Libelle],[DR_Pourcent] \n" +
            "                ,[DR_Montant],[DR_MontantDev],[DR_Equil],[EC_No],[cbEC_No],[DR_Regle] \n" +
            "                ,[N_Reglement],[cbProt],[cbCreateur],[cbModification],[cbReplication],[cbFlag]) \n" +
            "                VALUES \n" +
            "                (/*DR_No*/ISNULL((SELECT MAX(DR_No) FROM F_DOCREGL),0)+1,/*DO_Domaine*/?\n" +
            "                ,/*DO_Type*/?,/*DO_Piece*/?,/*DR_TypeRegl*/2,/*DR_Date*/CAST(? AS DATE)  \n" +
            "                ,/*DR_Libelle*/'',/*DR_Pourcent*/0,/*DR_Montant*/0,/*DR_MontantDev*/0 \n" +
            "                ,/*DR_Equil*/1,/*EC_No, */0,/*cbEC_No, */0,/*DR_Regle*/?\n" +
            "                ,/*N_Reglement*/?,/*cbProt*/0,/*cbCreateur*/?,/*cbModification*/GETDATE() \n" +
            "                ,/*cbReplication*/0,/*cbFlag*/0);" +
            " SELECT cbMarq FROM @generated_keys;";

    public static final String getDocReglByDO_Piece =
            BASE_SQL + " WHERE DO_Piece=? AND DO_Domaine=? AND DO_Type=?";
    public static final String getDocReglByDO_PieceDRNo =
            BASE_SQL + " WHERE DO_Piece=? AND DO_Domaine=? AND DO_Type=?";
    public static final String getDocRegl =
            BASE_SQL + " WHERE cbMarq = ?";

    public static final String updateDrRegle =
            "UPDATE F_DOCREGL SET Dr_Regle = 1,cbModification=GETDATE() WHERE Dr_No = ?";

    public static final String isDocReglFull =
            "WITH _Ligne_ AS (\n" +
                    "                    SELECT FD.DO_Type,FD.DO_Domaine,FD.cbDO_Piece,SUM(DL_MontantTTC) DL_MontantTTC\n" +
                    "                        FROM F_DOCLIGNE C \n" +
                    "                        INNER JOIN F_DOCENTETE FD \n" +
                    "                            ON FD.cbDO_Piece = C.cbDO_Piece\n" +
                    "                            AND FD.DO_Domaine = C.DO_Domaine\n" +
                    "                            AND FD.DO_Type = C.DO_Type\n" +
                    "                        WHERE FD.cbMarq =?\n" +
                    "                        GROUP BY FD.DO_Type,FD.DO_Domaine,FD.cbDO_Piece\n" +
                    "                )       \n" +
                    "                ,_ReglEch_ AS (\n" +
                    "                    SELECT DO_Type,DO_Domaine,cbDO_Piece, SUM(RC_Montant)RC_Montant\n" +
                    "                    FROM F_REGLECH \n" +
                    "                    GROUP BY DO_Type,DO_Domaine,cbDO_Piece\n" +
                    "                )\n" +
                    "\n" +
                    "                SELECT CASE WHEN RC_Montant >= DL_MontantTTC THEN 1 ELSE 0 END AS VAL\n" +
                    "                FROM(SELECT  A.DL_MontantTTC\n" +
                    "                            ,RC_Montant = ISNULL(RC_Montant,0)  \n" +
                    "                     FROM    _Ligne_ A\n" +
                    "                     LEFT JOIN _ReglEch_ D \n" +
                    "                        ON  D.cbDO_Piece=A.cbDO_Piece \n" +
                    "                        AND D.DO_Domaine = A.DO_Domaine \n" +
                    "                        AND A.DO_Type = D.DO_Type\n" +
                    "                ) A";

    public static final String UpdateDrRegleByRgNo=
                    " DECLARE @rgNo AS INT = ?" +
                    " DECLARE @doPiece AS VARCHAR(50) = ? " +
                    " DECLARE @doDomaine AS INT = ? " +
                    " DECLARE @doType AS INT = ? " +
                    " UPDATE F_DOCREGL SET DR_Regle = \n" +
                    "                     (SELECT CASE WHEN DR_Regle= 1 THEN 0 ELSE DR_Regle END\n" +
                    "                      FROM F_DOCREGL A\n" +
                    "                      INNER JOIN F_REGLECH B " +
                    "                           ON A.DR_No=B.DR_No\n" +
                    "                      WHERE RG_No=@rgNo\n" +
                    "                      AND A.DO_Piece=@doPiece \n" +
                    "                      AND A.DO_Domaine=@doDomaine \n" +
                    "                      AND A.DO_Type=@doType)" +
                    " FROM F_REGLECH \n" +
                    " WHERE F_DOCREGL.DR_No=F_REGLECH.DR_No \n" +
                    " AND RG_No=@rgNo \n" +
                    " AND F_DOCREGL.DO_Piece=@doPiece \n" +
                    " AND F_DOCREGL.DO_Domaine=@doDomaine \n" +
                    " AND F_DOCREGL.DO_Type=@doType;";

    public static final String updateDateRegle =
            "UPDATE F_DOCREGL SET Dr_date = ?,cbModification=GETDATE() WHERE Dr_No = ?";

    public static final String deleteReglEchByRgNo =
            " DECLARE @rgNo AS INT = ?" +
            " DECLARE @doPiece AS VARCHAR(50) = ? " +
            " DECLARE @doDomaine AS INT = ? " +
            " DECLARE @doType AS INT = ? " +
            " DELETE FROM F_REGLECH\n" +
            "                    WHERE   RG_No=@rgNo \n" +
            "                    AND     DO_Piece=@doPiece \n" +
            "                    AND     DO_Domaine=@doDomaine \n" +
            "                    AND     DO_Type=@doType";


}