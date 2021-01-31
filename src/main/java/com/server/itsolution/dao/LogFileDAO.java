package com.server.itsolution.dao;

import com.server.itsolution.entities.FArtStock;
import com.server.itsolution.entities.FArticle;
import com.server.itsolution.entities.LogFile;
import com.server.itsolution.entities.Message;
import com.server.itsolution.mapper.FArticleMapper;
import com.server.itsolution.mapper.LogFileMapper;
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
public class LogFileDAO extends JdbcDaoSupport {

    @Autowired
    public LogFileDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[]{};
    public LogFileMapper mapper = new LogFileMapper();

    public void insertLog(LogFile logFile){
        String sqlquery ="INSERT INTO Z_LogInfo "+
        "VALUES (/*Action*/ '"+logFile.getAction()+"',/*Type*/ '"+logFile.getType()+"',/*DoType*/ "+logFile.getDoType() +
                ",/*DoEntete*/ '"+logFile.getDoEntete()+"',/*DeNo*/ "+logFile.getDeNo()+",/*DoDomaine*/ "+logFile.getDoDomaine()+"" +
                ",/*ArRef*/ '"+logFile.getArRef()+"',/*Qte*/ "+logFile.getQte()+",/*Prix*/ "+logFile.getPrix()+"" +
                ",/*Remise*/ "+logFile.getRemise()+",/*Montant*/ "+logFile.getMontant()+",/*Date*/ GETDATE(),/*UserName*/ '"+logFile.getUserName()+"'" +
                ",/*CbMarq*/ "+logFile.getCbMarq()+",/*Table*/ '"+logFile.getAction()+"',/*CbCreateur*/ '"+logFile.getCbCreateur()+"',/*dateDocument*/ null)";
        this.getJdbcTemplate().execute(sqlquery);
    }

    public void writeStock(String type, String arRef, int deNo, FArtStock fArtStock,String cbCreateur) {
        LogFile logFile = new LogFile();
        logFile.writeStock(type,arRef,deNo,fArtStock.getAS_QteSto(),fArtStock.getAS_MontSto(),String.valueOf(fArtStock.getCbMarq()),"F_ARTSTOCK",cbCreateur);
        insertLog(logFile);
    }

    public void writeFacture(String action,int doType,String doEntete,int doDomaine,String arRef,double qte,double prix,double remise,double montant,String cbMarq,String table,String cbCreateur){
        LogFile logFile = new LogFile();
        logFile.writeFacture(action,doType,doEntete,doDomaine,arRef,qte,prix,remise,montant,cbMarq,table,cbCreateur);
        insertLog(logFile);
    }

    public void writeReglement(String action,float montant,String rgNo,String rgPiece,String cbMarq,String table,String cbCreateur){
        LogFile logFile = new LogFile();
        logFile.writeReglement(action,montant,rgNo,rgPiece,cbMarq,table,cbCreateur);
        insertLog(logFile);
    }

}
