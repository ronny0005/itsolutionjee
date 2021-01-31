package com.server.itsolution.dao;

import com.server.itsolution.entities.FArticle;
import com.server.itsolution.entities.Message;
import com.server.itsolution.entities.PTiers;
import com.server.itsolution.mapper.FArticleMapper;
import com.server.itsolution.mapper.PTiersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PTiersDAO extends JdbcDaoSupport {

    @Autowired
    public PTiersDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[]{};
    public PTiersMapper mapper = new PTiersMapper();
    public PTiers pTiers = new PTiers();


    public List<Object> selectDefautCompte(String valeur){
        String sql = PTiersMapper.GetPTiersByType;
        params = new Object[] {valeur};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

}
