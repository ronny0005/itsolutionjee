package com.server.itsolution.dao;

import com.server.itsolution.entities.FArtStock;
import com.server.itsolution.entities.LogFile;
import com.server.itsolution.mapper.FArtStockMapper;
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
public class FArtStockDAO extends JdbcDaoSupport {

    @Autowired
    public FArtStockDAO(DataSource dataSource) {
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

    public List<Object> isStock() {
        String sql = FArtStockMapper.BASE_SQL;
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> isStockDENo(double dlQte,int deNo,String arRef) {
        String sql = FArtStockMapper.isStockDENo;
        params = new Object[]{dlQte,deNo, arRef};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }



    public List<Object> isStockJSON(String arRef, int deNo) {
        String sql = FArtStockMapper.artStock;
        params = new Object[]{deNo, arRef};
        List<Object> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public List<Object> articleWithStock(int deNo) {
        String sql = FArtStockMapper.articleWithStock;
        params = new Object[]{deNo};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }

    public Object articleWithStockCount(int deNo) {
        String sql = FArtStockMapper.articleWithStockCount;
        params = new Object[]{deNo};
        return (this.getJdbcTemplate().query(sql,params, mapper)).get(0);
    }

    public Object getArticleWithStock(int deNo) {
        String sql = FArtStockMapper.getArticleWithStock;
        params = new Object[]{deNo};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }
    public Object articleWithStockMax(int deNo) {
        String sql = FArtStockMapper.articleWithStockMax;
        params = new Object[]{deNo};
        return (this.getJdbcTemplate().query(sql,params, mapper)).get(0);
    }

    public FArtStock isStock(String arRef, int deNo) {
        String sql = FArtStockMapper.artStock;
        params = new Object[]{deNo,arRef};
        SqlRowSet sqlRowSet = this.getJdbcTemplate().queryForRowSet(sql, params);
        fArtStock = new FArtStock();
        while (sqlRowSet.next()) {
            fArtStock.setAS_MontSto(sqlRowSet.getDouble("AS_MontSto"));
            fArtStock.setAS_Principal(sqlRowSet.getInt("AS_Principal"));
            fArtStock.setAS_QteCom(sqlRowSet.getDouble("AS_QteCom"));
            fArtStock.setAS_QteMaxi(sqlRowSet.getDouble("AS_QteMaxi"));
            fArtStock.setAS_QteMini(sqlRowSet.getDouble("AS_QteMini"));
            fArtStock.setAS_QteRes(sqlRowSet.getDouble("AS_QteRes"));
            fArtStock.setAS_QteSto(sqlRowSet.getDouble("AS_QteSto"));
            fArtStock.setDE_No(sqlRowSet.getInt("DE_No"));
            fArtStock.setAR_Ref(sqlRowSet.getString("AR_Ref"));
            fArtStock.setCbMarq(sqlRowSet.getInt("cbMarq"));
        }
        return fArtStock;
    }


    public void insertF_ArtStock(String arRef, int deNo,double montant, double qte,String cbCreateur) {
        String sql = FArtStockMapper.insertF_ArtStock;
        ArrayList<Object> parames = new ArrayList<Object>();
        parames.add(arRef);
        parames.add(deNo);
        parames.add(montant);
        parames.add(qte);
        parames.add(cbCreateur);
        this.getJdbcTemplate().update(sql, parames.toArray());
    }

    public void updateFArtstock(String arRef, int deNo,double montant, double qte,String action,String protNo) {

        FArtStock fArtStock = this.isStock(arRef,deNo);
        LogFileDAO logFileDAO = new LogFileDAO(this.getDataSource());
        logFileDAO.writeStock(action,arRef,deNo,fArtStock,protNo);

        String sql = FArtStockMapper.updateArtStock;
        params = new Object[]{arRef, deNo,montant,qte};
        this.getJdbcTemplate().update(sql, params);

        FArtStock fArtStockAfter = this.isStock(arRef,deNo);
        logFileDAO.writeStock(action,arRef,deNo,fArtStockAfter,protNo);

    }

    public void updateArtStockQteRes(String arRef, int deNo, double qte,String protNo) {

        FArtStock fArtStock = this.isStock(arRef,deNo);
        //LogFileDAO logFileDAO = new LogFileDAO(this.getDataSource());
        //logFileDAO.writeStock(action,arRef,deNo,fArtStock,protNo);

        String sql = FArtStockMapper.updateArtStockQteRes;
        params = new Object[]{qte, deNo,arRef};
        this.getJdbcTemplate().update(sql, params);

        FArtStock fArtStockAfter = this.isStock(arRef,deNo);
        //logFileDAO.writeStock(action,arRef,deNo,fArtStockAfter,protNo);

    }

    public void setASQteMaxiArtStock(String arRef,int deNo){
        String sql = FArtStockMapper.setASQteMaxiArtStock;
        params = new Object[]{arRef, deNo};
        this.getJdbcTemplate().update(sql, params);
    }

    public void updateFArtstockReel(String arRef, int deNo,double qte) {

        String sql = FArtStockMapper.updateArtStockReel;
        params = new Object[]{qte,deNo,arRef};
        this.getJdbcTemplate().update(sql, params);
    }

}
