package com.server.itsolution.dao;

import com.server.itsolution.entities.FDocEntete;
import com.server.itsolution.entities.FReglEch;
import com.server.itsolution.mapper.FDocReglMapper;
import com.server.itsolution.mapper.FReglEchMapper;
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
public class FReglEchDAO extends JdbcDaoSupport {

    @Autowired
    public FReglEchDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FReglEchMapper mapper = new FReglEchMapper();

    public List<Object> getAll() {
        String sql = FReglEchMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public void addReglEch(FReglEch fReglEch) {
        String sql = FReglEchMapper.addReglEch;
        params = new Object[]{fReglEch.getRG_No(),fReglEch.getDR_No(),fReglEch.getDO_Domaine(),fReglEch.getDO_Type(),fReglEch.getDO_Piece(),fReglEch.getRcMontant(),fReglEch.getCbCreateur()
        ,fReglEch.getRG_TypeReg()};
        this.getJdbcTemplate().update(sql, params);
    }

}

