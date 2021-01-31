package com.server.itsolution.dao;

import com.server.itsolution.mapper.EnvoiMailMapper;
import com.server.itsolution.mapper.FDepotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class EnvoiMailDAO extends JdbcDaoSupport {

    @Autowired
    public EnvoiMailDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public EnvoiMailMapper mapper = new EnvoiMailMapper();

    public void getCollaborateurEnvoiMail(String intitule) {
        String sql = EnvoiMailMapper.getCollaborateurEnvoiMail;
        params = new Object[]{intitule};
        this.getJdbcTemplate().query(sql, params,mapper);
    }

    public void getCollaborateurEnvoiSMS(String intitule) {
        String sql = EnvoiMailMapper.getCollaborateurEnvoiSMS;
        params = new Object[]{intitule};
        this.getJdbcTemplate().query(sql, params,mapper);
    }


}

