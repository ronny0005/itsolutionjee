package com.server.itsolution.dao;

import com.server.itsolution.entities.FArticle;
import com.server.itsolution.entities.FContactD;
import com.server.itsolution.mapper.FContactDMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class FContactDDAO extends JdbcDaoSupport {

    @Autowired
    public FContactDDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FContactDMapper mapper = new FContactDMapper();

    public List<Object> getAll() {
        String sql = FContactDMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
    public FContactD getContactD(int cdNo) {
        String sql = FContactDMapper.getContactD;
        params = new Object[]{cdNo};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        FContactD fContactD = new FContactD();
        while(sqlRowSet.next()) {
            fContactD.setCD_Adresse(sqlRowSet.getString("CD_Adresse"));
            fContactD.setCD_Civilite(sqlRowSet.getString("CD_Civilite"));
            fContactD.setCD_CodePostal(sqlRowSet.getString("CD_CodePostal"));
            fContactD.setCD_Complement(sqlRowSet.getString("CD_Complement"));
            fContactD.setCD_EMail(sqlRowSet.getString("CD_EMail"));
            fContactD.setCD_Fonction(sqlRowSet.getString("CD_Fonction"));
            fContactD.setCD_No(sqlRowSet.getInt("CD_No"));
            fContactD.setCD_Nom(sqlRowSet.getString("CD_Nom"));
            fContactD.setCD_Prenom(sqlRowSet.getString("CD_Prenom"));
            fContactD.setCD_Telecopie(sqlRowSet.getString("CD_Telecopie"));
            fContactD.setCD_Telephone(sqlRowSet.getString("CD_Telephone"));
            fContactD.setCD_TelPortable(sqlRowSet.getString("CD_TelPortable"));
            fContactD.setCD_Ville(sqlRowSet.getString("CD_Ville"));
        }
        return fContactD;
    }

    public void sendMessage(String tel,String msg){

    }
}
