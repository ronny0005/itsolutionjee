package com.server.itsolution.dao;

import com.server.itsolution.entities.FArticle;
import com.server.itsolution.entities.PUnite;
import com.server.itsolution.mapper.PCatTarifMapper;
import com.server.itsolution.mapper.PUniteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class PUniteDAO extends JdbcDaoSupport {

    @Autowired
    public PUniteDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public PUniteMapper mapper = new PUniteMapper();

    public List<Object> getAll() {
        String sql = PUniteMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public PUnite getUniteByIndice(int cbIndice) {
        String sql = PUniteMapper.getUniteByIndice;
        params = new Object[]{cbIndice};
        PUnite pUnite = new PUnite();
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        while(sqlRowSet.next()) {
            pUnite.setU_Intitule(sqlRowSet.getString("U_Intitule"));
            pUnite.setU_Correspondance(sqlRowSet.getInt("U_Correspondance"));
            pUnite.setU_NbUnite(sqlRowSet.getInt("U_NbUnite"));
            pUnite.setU_UniteTemps(sqlRowSet.getInt("U_UniteTemps"));
        }
            return pUnite;

    }

}
