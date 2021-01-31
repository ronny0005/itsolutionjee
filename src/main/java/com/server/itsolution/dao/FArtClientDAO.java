package com.server.itsolution.dao;

import com.server.itsolution.entities.FArtClient;
import com.server.itsolution.entities.FArtStock;
import com.server.itsolution.entities.FArticle;
import com.server.itsolution.mapper.FArtClientMapper;
import com.server.itsolution.mapper.FArtStockMapper;
import com.server.itsolution.mapper.FArticleMapper;
import com.server.itsolution.mapper.FDepotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class FArtClientDAO extends JdbcDaoSupport {

    @Autowired
    public FArtClientDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public static int mapParams(PreparedStatement ps, Object... args) throws SQLException {
        int i = 1;
        for (Object arg : args) {
            if (arg instanceof Date) {
                ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Long) {
                ps.setLong(i++, (Long) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (double) arg);
            } else {
                ps.setString(i++, (String) arg);
            }
        }
        return 0;
    }

    public Object[] params = new Object[]{};
    public FArtStockMapper mapper = new FArtStockMapper();
    public FArtStock fArtStock = new FArtStock();

    public List<Object> selectFArtClient(String arRef,int acCategorie) {
        String sql = FArtClientMapper.selectFArtClient;
        params = new Object[] {arRef,acCategorie};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Object> getAll() {
        String sql = FArtClientMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    public Object getF_ArtclientCount() {
        String sql = FArtClientMapper.getF_ArtclientCount;
        return (this.getJdbcTemplate().query(sql, mapper)).get(0);
    }

    public void updateFArtClient(String arRef,int acCategorie,double acPrixTTC,double acPrixVen,double acCoef){
        if(selectFArtClient(arRef,acCategorie).size()>0)
        {
            modifFArtClient(arRef,acCategorie,acPrixTTC,acPrixVen,acCoef);
        }
        else{
            insertFArtClient(arRef,acCategorie,acPrixTTC,acPrixVen,acCoef);
        }
    }

    public void modifFArtClient(String arRef, int acCategorie,double acPrixTTC,double acPrixVen,double acCoef) {
        FArticleDAO fArticleDAO = new FArticleDAO(this.getDataSource());
        FArticle fArticle = fArticleDAO.getF_Article(arRef);
        String sql = FArtClientMapper.modifFArtClient;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(fArticle.getAR_Ref());
        parames.add(acCategorie);
        parames.add(acPrixTTC);
        parames.add(acPrixVen);
        parames.add(acCoef);
        parames.add(fArticle.getCbCreateur());
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public void insertFArtClient(String arRef, int acCategorie,double acPrixTTC,double acPrixVen,double acCoef){
        FArticleDAO fArticleDAO = new FArticleDAO(this.getDataSource());
        FArticle fArticle = fArticleDAO.getF_Article(arRef);
        String sql = FArtClientMapper.insertFArtClient;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(fArticle.getAR_Ref());
        parames.add(acCategorie);
        parames.add(acPrixTTC);
        parames.add(acPrixVen);
        parames.add(acCoef);
        parames.add(fArticle.getCbCreateur());
        this.getJdbcTemplate().update(sql, parames.toArray());
    }


}
