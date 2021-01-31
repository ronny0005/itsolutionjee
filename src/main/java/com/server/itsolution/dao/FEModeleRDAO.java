package com.server.itsolution.dao;

import com.server.itsolution.entities.FEModeleR;
import com.server.itsolution.entities.FModeleR;
import com.server.itsolution.mapper.FCaisseMapper;
import com.server.itsolution.mapper.FEModeleRMapper;
import com.server.itsolution.mapper.FModeleRMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class FEModeleRDAO extends JdbcDaoSupport {

    @Autowired
    public FEModeleRDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FEModeleRMapper mapper = new FEModeleRMapper();

    public List<Object> getAll() {
        String sql = FModeleRMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql,mapper);
        return list;
    }

    public FEModeleR findByMRNo(int MR_No){

        String sql = FEModeleRMapper.BASE_SQL+" WHERE MR_No = ? ";

        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, new Object[] { MR_No });
        FEModeleR feModeleR = new FEModeleR();
        while(sqlRowSet.next()) {
            feModeleR.setCbMarq(sqlRowSet.getBigDecimal("cbMarq"));
            feModeleR.setER_Condition(sqlRowSet.getInt("ER_Condition"));
            feModeleR.setER_JourTb01(sqlRowSet.getInt("ER_JourTb01"));
            feModeleR.setER_JourTb02(sqlRowSet.getInt("ER_JourTb02"));
            feModeleR.setER_JourTb03(sqlRowSet.getInt("ER_JourTb03"));
            feModeleR.setER_JourTb04(sqlRowSet.getInt("ER_JourTb04"));
            feModeleR.setER_JourTb05(sqlRowSet.getInt("ER_JourTb05"));
            feModeleR.setER_JourTb06(sqlRowSet.getInt("ER_JourTb06"));
            feModeleR.setER_NbJour(sqlRowSet.getInt("ER_NbJour"));
            feModeleR.setER_TRepart(sqlRowSet.getInt("ER_TRepart"));
            feModeleR.setER_VRepart(sqlRowSet.getFloat("ER_VRepart"));
        }
        return feModeleR;
    }

}
