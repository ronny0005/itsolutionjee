package com.server.itsolution.dao;

import com.server.itsolution.entities.FArticle;
import com.server.itsolution.entities.FJournaux;
import com.server.itsolution.mapper.FDepotMapper;
import com.server.itsolution.mapper.FJournauxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class FJournauxDAO extends JdbcDaoSupport {

    @Autowired
    public FJournauxDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FJournauxMapper mapper = new FJournauxMapper();

    public List<Object> getAll() {
        String sql = FJournauxMapper.BASE_SQL;
        return this.getJdbcTemplate().query(sql, mapper);
    }


    public List<Object> getJournal(String joNum) {
        String sql = FJournauxMapper.getJournal;
        params = new Object[]{joNum};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public FJournaux getJournalObject(String joNum) {
        String sql = FJournauxMapper.getJournal;
        params = new Object[]{joNum};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FJournaux fJournaux = new FJournaux();
        while(sqlRowSet.next()) {
            fJournaux.setJO_Num(sqlRowSet.getString("JO_Num"));
            fJournaux.setJO_Intitule(sqlRowSet.getString("JO_Intitule"));
            fJournaux.setCG_Num(sqlRowSet.getString("CG_Num"));
            fJournaux.setCbCreateur(sqlRowSet.getString("cbCreateur"));
            fJournaux.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
            fJournaux.setJO_Contrepartie(sqlRowSet.getInt("JO_Contrepartie"));
            fJournaux.setJO_IFRS(sqlRowSet.getInt("JO_IFRS"));
            fJournaux.setJO_NotCalcTot(sqlRowSet.getInt("JO_NotCalcTot"));
            fJournaux.setJO_NumPiece(sqlRowSet.getInt("JO_NumPiece"));
            fJournaux.setJO_Rappro(sqlRowSet.getInt("JO_Rappro"));
            fJournaux.setJO_SaisAnal(sqlRowSet.getInt("JO_SaisAnal"));
        }
        return fJournaux;
    }

    public List<Object> getJournaux(int joSommeil) {
        String sql = FJournauxMapper.getJournaux;
        params = new Object[]{joSommeil};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }
    public List<Object> getJournauxReglement(int joSommeil) {
        String sql = FJournauxMapper.getJournauxReglement;
        params = new Object[]{joSommeil};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getECPiece(String joNum,String jmDate) {
        String sql = FJournauxMapper.getECPiece;
        params = new Object[]{joNum,jmDate};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getJournauxCount(int joSommeil) {
        String sql = FJournauxMapper.getJournauxCount;
        params = new Object[]{joSommeil};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getJournauxSaufTotaux() {
        String sql = FJournauxMapper.getJournauxSaufTotaux;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public List<Object> getJournauxType(int joType,int joSommeil){
        String sql = FJournauxMapper.getJournauxType;
        params = new Object[]{joType,joSommeil};
        return this.getJdbcTemplate().query(sql, params,mapper);
    }

    public List<Object> getJournauxSaisieSelect(int ouvert,int mois,String journal) {
        String sql = FJournauxMapper.getJournauxSaisieSelect;
        params = new Object[]{ouvert,mois,journal};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> headerSaisiJournal(int anneeExercice,String journal,int position) {
        String sql = FJournauxMapper.headerSaisiJournal;
        params = new Object[]{anneeExercice,journal,position};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getJournauxSaisie(int ouvert,String nomMois,String joNum,int annee) {
        String sql = FJournauxMapper.getJournauxSaisie;
        params = new Object[]{ouvert,nomMois,joNum,annee};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> calculSoldeLettrage(String listCbMarq) {
        if(listCbMarq=="")
            listCbMarq = "0";
        String sql  = "SELECT EC_MontantCredit = CASE WHEN EC_MontantCredit <= EC_MontantDebit THEN 0 ELSE EC_MontantCredit-EC_MontantDebit END\n" +
                "                , EC_MontantDebit = CASE WHEN EC_MontantDebit <= EC_MontantCredit THEN 0 ELSE EC_MontantDebit-EC_MontantCredit END\n" +
                "        FROM (SELECT  EC_MontantCredit = SUM(CASE WHEN EC_Sens=1 THEN EC_Montant ELSE 0 END)\n" +
                "                ,EC_MontantDebit = SUM(CASE WHEN EC_Sens=0 THEN EC_Montant ELSE 0 END)\n" +
                "                FROM    F_ECRITUREC A\n" +
                "                WHERE cbMarq IN ("+listCbMarq+"))A";
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public List<Object> getSaisieJournalExercice(String joNum,int mois,int annee,String ctNum,String dateDebut,String dateFin,int lettrage,String cgNum) {
        String sql = FJournauxMapper.getSaisieJournalExercice;
        params = new Object[]{joNum,mois,annee,ctNum,dateDebut,dateFin,lettrage,cgNum};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getTotalJournal(String joNum,int mois,int annee,int sens,String ctNum,String dateDebut,String dateFin,int lettrage,String cgNum) {
        String sql = FJournauxMapper.getTotalJournal;
        params = new Object[]{joNum,mois,annee,sens,ctNum,cgNum,dateDebut,dateFin,lettrage};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getJournalLastDate(String joNum,int mois,int annee) {
        String sql = FJournauxMapper.getJournalLastDate;
        params = new Object[]{joNum,mois,annee};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getJournalPiece(String joNum,int mois,int annee) {
        String sql = FJournauxMapper.getJournalPiece;
        params = new Object[]{joNum,mois,annee};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getLettrage(String ctNum,String dateDebut,String dateFin,String cgNum) {
        String sql = FJournauxMapper.getLettrage;
        params = new Object[]{ctNum,cgNum,dateDebut,dateFin};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> pointerEcriture(int annuler,String listCbMarq,String ecLettrage) {
        if(listCbMarq=="")
            listCbMarq = "0";
        String sql  =   "BEGIN\n" +
                        "                        SET NOCOUNT ON;\n" +
                        "                        DECLARE @amount FLOAT;\n" +
                        "                        DECLARE @pointage INT;\n" +
                        "                        DECLARE @result INT;\n" +
                        "                        DECLARE @annuler INT = "+annuler+";\n" +
                        "                        DECLARE @ecLettrage VARCHAR(50) = '"+ecLettrage+"';\n" +
                        "                        SELECT  @amount = SUM(CASE WHEN EC_Sens = 1 THEN EC_Montant ELSE - EC_Montant END) \n" +
                        "                               ,@pointage = SUM(EC_Lettre)" +
                        "                        FROM    F_ECRITUREC\n" +
                        "                        WHERE   cbMarq IN ("+listCbMarq+")\n" +
                        "                        \n" +
                        "                        IF  (@annuler = 1 AND @amount=0 AND @pointage = 0) OR (@annuler = 0 AND @amount=0 AND @pointage <> 0)\n" +
                        "                            BEGIN \n" +
                        "                                UPDATE F_ECRITUREC SET EC_Lettre = @annuler\n" +
                        "                                                    ,EC_Lettrage = @ecLettrage \n" +
                        "                                WHERE cbMarq IN ("+listCbMarq+")\n" +
                        "                                SELECT @result = 1\n" +
                        "                            END \n" +
                        "                        ELSE \n" +
                        "                            BEGIN \n" +
                        "                               IF @pointage = 0" +
                        "                                SELECT @result = 0\n\n" +
                        "\t\t\t\t\t\t\t\t            ELSE \n" +
                        "\t\t\t\t\t\t\t\t\t              SELECT @result = 2" +
                        "                            END\n" +
                        "                            SELECT Result = @result \n" +
                        "                    END";
        return this.getJdbcTemplate().query(sql, mapper);
    }

}

