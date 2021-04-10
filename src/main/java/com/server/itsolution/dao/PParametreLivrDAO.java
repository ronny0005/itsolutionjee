package com.server.itsolution.dao;

import com.server.itsolution.entities.PParametreLivr;
import com.server.itsolution.mapper.FDepotMapper;
import com.server.itsolution.mapper.PParametreLivrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class PParametreLivrDAO extends JdbcDaoSupport {

    @Autowired
    public PParametreLivrDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public PParametreLivrMapper mapper = new PParametreLivrMapper();

    public List<Object> getAll() {
        String sql = PParametreLivrMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Object getpParametreLivr(int cbMarq) {
        String sql = PParametreLivrMapper.getPParametreLivr;
        params = new Object[] {cbMarq};
        return this.getJdbcTemplate().query(sql, params,mapper);
    }

    public PParametreLivr getpParametreLivrObject(int cbMarq) {
        String sql = PParametreLivrMapper.BASE_SQL;
        params = new Object[] {cbMarq};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql);
        PParametreLivr pParametreLivr = new PParametreLivr();
        while(sqlRowSet.next()) {
            pParametreLivr.setPL_TypeDuree(sqlRowSet.getInt("PL_TypeDuree"));
            pParametreLivr.setPL_Generation(sqlRowSet.getInt("PL_Generation"));
            pParametreLivr.setPL_Priorite1(sqlRowSet.getInt("PL_Priorite1"));
            pParametreLivr.setPL_Priorite2(sqlRowSet.getInt("PL_Priorite2"));
            pParametreLivr.setPL_Priorite3(sqlRowSet.getInt("PL_Priorite3"));
            pParametreLivr.setPL_Quantite(sqlRowSet.getInt("PL_Quantite"));
            pParametreLivr.setPL_Reliquat(sqlRowSet.getInt("PL_Reliquat"));
            pParametreLivr.setPL_Statut(sqlRowSet.getInt("PL_Statut"));
            pParametreLivr.setCbMarq(sqlRowSet.getInt("CbMarq"));
        }
        return pParametreLivr;
    }

}

