package com.server.itsolution.dao;

import com.server.itsolution.entities.FCReglement;
import com.server.itsolution.entities.FCaisse;
import com.server.itsolution.entities.FDocEntete;
import com.server.itsolution.entities.FDocRegl;
import com.server.itsolution.mapper.FCaisseMapper;
import com.server.itsolution.mapper.FDocReglMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class FDocReglDAO extends JdbcDaoSupport {

    @Autowired
    public FDocReglDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FDocReglMapper mapper = new FDocReglMapper();

    public List<Object> getAll() {
        String sql = FDocReglMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public void ajoutDocRegl(FDocRegl fDocRegl) {
        String sql = FDocReglMapper.InsertFDocRegl;
        params = new Object[]{fDocRegl.getDO_Domaine(),fDocRegl.getDO_Type(),fDocRegl.getDO_Piece(),fDocRegl.getDR_Date() ,fDocRegl.getDR_Regle(),fDocRegl.getN_Reglement(),fDocRegl.getCbCreateur()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql,params); // Not update, you're returning a ResultSet.
        while (sqlRowSet.next()) {
            fDocRegl.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
        }
    }

    public List<Object> getDocReglByDO_Piece(BigDecimal cbMarqEntete) {
        String sql = FDocReglMapper.getDocReglByDO_PieceDRNo;
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
        params = new Object[]{fDocEntete.getDO_Domaine(),fDocEntete.getDO_Type(),fDocEntete.getDO_Piece()};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public int getDocReglByDO_PieceDRNo(BigDecimal cbMarqEntete) {
        String sql = FDocReglMapper.getDocReglByDO_PieceDRNo;
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
        params = new Object[]{fDocEntete.getDO_Piece(),fDocEntete.getDO_Domaine(),fDocEntete.getDO_Type()};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            return sqlRowSet.getInt("DR_No");
        }
        return 0;
    }

    public FDocRegl getDocRegl(BigDecimal cbMarq) {
        String sql = FDocReglMapper.getDocRegl;
        FDocRegl fDocRegl = new FDocRegl();
        params = new Object[]{cbMarq};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            fDocRegl.setDR_Date(sqlRowSet.getString("DR_Date"));
            fDocRegl.setN_Reglement(sqlRowSet.getInt("N_Reglement"));
            fDocRegl.setDR_Regle(sqlRowSet.getInt("DR_Regle"));
            fDocRegl.setDO_Domaine(sqlRowSet.getInt("DO_Domaine"));
            fDocRegl.setDO_Piece(sqlRowSet.getString("DO_Piece"));
            fDocRegl.setDO_Type(sqlRowSet.getInt("DO_Type"));
            fDocRegl.setDR_Equil(sqlRowSet.getInt("DR_Equil"));
            fDocRegl.setDR_Libelle(sqlRowSet.getString("DR_Libelle"));
            fDocRegl.setDR_Montant(sqlRowSet.getFloat("DR_Montant"));
            fDocRegl.setDR_MontantDev(sqlRowSet.getFloat("DR_MontantDev"));
            fDocRegl.setDR_No(sqlRowSet.getInt("DR_No"));
            fDocRegl.setDR_Pourcent(sqlRowSet.getFloat("DR_Pourcent"));
            fDocRegl.setDR_TypeRegl(sqlRowSet.getInt("DR_TypeRegl"));
            fDocRegl.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
        }
        return fDocRegl;
    }

    public void updateDrRegle(int drNo){
        String sql = FDocReglMapper.updateDrRegle;
        params = new Object[]{drNo};
        this.getJdbcTemplate().update(sql,params);
    }

    public void UpdateDrRegleByRgNo(BigDecimal cbMarqEntete,BigDecimal rgNo){
        String sql = FDocReglMapper.UpdateDrRegleByRgNo;
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
        params = new Object[]{rgNo,fDocEntete.getDO_Piece(),fDocEntete.getDO_Domaine(),fDocEntete.getDO_Type()};
        this.getJdbcTemplate().update(sql,params);
    }

    public void deleteReglEchByRgNo(BigDecimal cbMarqEntete,BigDecimal rgNo){
        String sql = FDocReglMapper.deleteReglEchByRgNo;
        FDocEnteteDAO fDocEnteteDAO = new FDocEnteteDAO(this.getDataSource());
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarqEntete);
        params = new Object[]{rgNo,fDocEntete.getDO_Piece(),fDocEntete.getDO_Domaine(),fDocEntete.getDO_Type()};
        this.getJdbcTemplate().update(sql,params);
    }

    public void isDocReglFull(BigDecimal cbMarqEntete) {
        String sql = "BEGIN \n" +
                "\tDECLARE @cbMarq AS INT = "+cbMarqEntete+"\n" +
                "\t,@val AS INT;\n" +
                "\t\n" +
                "\tWITH _Ligne_ AS (\n" +
                "\t\tSELECT FD.DO_Type,FD.DO_Domaine,FD.cbDO_Piece,SUM(DL_MontantTTC) DL_MontantTTC\n" +
                "\t\t\tFROM F_DOCLIGNE C \n" +
                "\t\t\tINNER JOIN F_DOCENTETE FD \n" +
                "\t\t\t\tON \tFD.cbDO_Piece = C.cbDO_Piece\n" +
                "\t\t\t\tAND FD.DO_Domaine = C.DO_Domaine\n" +
                "\t\t\t\tAND FD.DO_Type = C.DO_Type\n" +
                "\t\t\tWHERE FD.cbMarq = @cbMarq\n" +
                "\t\t\tGROUP BY FD.DO_Type,FD.DO_Domaine,FD.cbDO_Piece\n" +
                "\t)       \n" +
                "\t,_ReglEch_ AS (\n" +
                "\t\tSELECT \tDO_Type,DO_Domaine,cbDO_Piece\n" +
                "\t\t\t\t,RC_Montant = SUM(RC_Montant)\n" +
                "\t\tFROM \tF_REGLECH \n" +
                "\t\tGROUP BY DO_Type,DO_Domaine,cbDO_Piece\n" +
                "\t)\n" +
                "\n" +
                "\tSELECT @val = CASE WHEN ISNULL(RC_Montant,0) >= A.DL_MontantTTC THEN 1 ELSE 0 END\n" +
                "\tFROM    _Ligne_ A\n" +
                "\tLEFT JOIN _ReglEch_ D \n" +
                "\t\tON  D.cbDO_Piece=A.cbDO_Piece \n" +
                "\t\tAND D.DO_Domaine = A.DO_Domaine \n" +
                "\t\tAND A.DO_Type = D.DO_Type\n" +
                "\t;\n" +
                "\tIF @val = 1 \n" +
                "\t\tUPDATE \tF_DOCREGL \n" +
                "\t\t\tSET DR_Regle = 1 \n" +
                "\t\tFROM \tF_DOCENTETE\n" +
                "\t\tWHERE \tF_DOCENTETE.DO_Domaine = F_DOCREGL.DO_Domaine\n" +
                "\t\tAND \tF_DOCENTETE.DO_Type = F_DOCREGL.DO_Type\n" +
                "\t\tAND \tF_DOCENTETE.cbDO_Piece = F_DOCREGL.cbDO_Piece\n" +
                "\t\tAND \tF_DOCENTETE.cbMarq = @cbMarq\n" +
                "\t\t\n" +
                "END";
        this.getJdbcTemplate().execute(sql);
    }
}

