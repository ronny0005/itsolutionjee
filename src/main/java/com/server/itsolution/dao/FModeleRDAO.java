package com.server.itsolution.dao;

import com.server.itsolution.entities.FEModeleR;
import com.server.itsolution.entities.FModeleR;
import com.server.itsolution.mapper.FEModeleRMapper;
import com.server.itsolution.mapper.FModeleRMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class FModeleRDAO extends JdbcDaoSupport {

    @Autowired
    public FModeleRDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public FModeleRMapper mapper = new FModeleRMapper();

    public List<Object> getAll() {
        String sql = FModeleRMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql,mapper);
        return list;
    }

    public List<Object> getMrNo(int mrNo){
        String sql = FModeleRMapper.BASE_SQL+" WHERE MR_No = ? ";
        return this.getJdbcTemplate().query(sql, new Object[] { mrNo },mapper);
    }

    public List<Object> getDateEcgetTiersheanceSelectSage(int MR_No,int N_Reglement, String date){
        String sql = FModeleRMapper.getDateEcgetTiersheanceSelectSage;
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(date);
        params.add(MR_No);
        params.add(N_Reglement);
        return  this.getJdbcTemplate().query(sql, params.toArray(),mapper);
    }


    public FModeleR findByMRNo(int MR_No){

        String sql = FEModeleRMapper.BASE_SQL+" WHERE MR_No = ? ";
        FModeleR fModeleR= (FModeleR) getJdbcTemplate().queryForObject(sql, new Object[] { MR_No }, mapper);
        return fModeleR;
    }

}
