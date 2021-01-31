package com.server.itsolution.dao;

import com.server.itsolution.entities.FArtStock;
import com.server.itsolution.entities.FArticle;
import com.server.itsolution.entities.FTaxe;
import com.server.itsolution.mapper.FArtClientMapper;
import com.server.itsolution.mapper.FArtStockMapper;
import com.server.itsolution.mapper.FComptegMapper;
import com.server.itsolution.mapper.FTaxeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
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
public class FTaxeDAO extends JdbcDaoSupport {

    @Autowired
    public FTaxeDAO(DataSource dataSource) {
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
    public FTaxeMapper mapper = new FTaxeMapper();
    public FTaxe fTaxe = new FTaxe();


    public List<Object> getAll() {
        String sql = FTaxeMapper.BASE_SQL;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public List<Object> getTaCode(String cgNum) {
        String sql = FTaxeMapper.getTaCode;
        params = new Object[] {cgNum};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public Object getF_TaxeCount() {
        String sql = FTaxeMapper.getF_TaxeCount;
        return (this.getJdbcTemplate().query(sql, mapper)).get(0);
    }

    public List<Object> allSearch(String intitule,int top){
        String valeurSaisie =intitule.replace(" ","%");
        String value = "";
        if(top!=0)
            value = "TOP "+top;
        String sql =  "  SELECT  "+value+" TA_Code "+
                "          ,TA_Intitule "+
                "          ,TA_Code as id "+
                "          ,TA_Type "+
                "                    ,CONCAT(CONCAT(TA_Code,' - '),TA_Intitule) as text "+
                "                   ,CONCAT(CONCAT(TA_Code,' - '),TA_Intitule) as value "+
                "  FROM  F_Taxe "+
                "  WHERE   CONCAT(CONCAT(TA_Code,' - '),TA_Intitule) LIKE CONCAT(CONCAT('%','"+valeurSaisie+"'),'%') ";
        return this.getJdbcTemplate().query(sql, mapper);
    }


}
