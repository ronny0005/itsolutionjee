package com.server.itsolution.dao;

import com.server.itsolution.entities.FReglEch;
import com.server.itsolution.mapper.EtatMapper;
import com.server.itsolution.mapper.FReglEchMapper;
import com.server.itsolution.mapper.ZDepotUserMapper;
import com.server.itsolution.ressource.EtatRessource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class EtatDAO extends JdbcDaoSupport {

    @Autowired
    public EtatDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public Object[] params = new Object[] {};
    public EtatMapper mapper = new EtatMapper();

    public List<Object> menuCaParDepot(int protNo) {
        String sql = EtatMapper.menuCaParDepot;
        params = new Object[]{protNo};
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public boolean fArticle(String[] params){
        for(int i=0;i<params.length;i++){
            if(params[i].equals("Reference article"))
                return true;

            if(params[i].equals("Designation article"))
                return true;
            if(params[i].equals("Prix de vente"))
                return true;

            if(params[i].equals("Prix d'achat"))
                return true;
        }
        return false;
    }

    public boolean isMesure(String[] params){
        if(mesureLigne(params))
            return true;
        if(mesureReglement(params))
            return true;

        return false;
    }

    public List<Object> top10Vente(String period,int time,int protNo){
        String sql = EtatMapper.top10Vente;
        params = new Object[]{period,time,protNo};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }

    public List<Object> statCaisseDuJour(int protNo){
        String sql = EtatMapper.statCaisseDuJour;
        params = new Object[]{protNo};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }

    public List<Object> statMouvementStock(int depot,String datedeb,String datefin,String articledebut,String articlefin){
        String sql = EtatMapper.statMouvementStock;
        params = new Object[]{depot,datedeb,datefin,articledebut,articlefin};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }

    public List<Object> equationStkVendeur(String datedeb,String datefin,int depot){
        String sql = EtatMapper.equationStkVendeur;
        params = new Object[]{datedeb,datefin,depot};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }

    public List<Object> invPreparatoire(int depot,String datedeb,int type){
        String sql="";
        if(type==0) {
            sql = EtatMapper.getPreparatoireCumul;
            params = new Object[]{depot};
        }else{
            sql = EtatMapper.getetatpreparatoire;
            params = new Object[]{depot,datedeb};
        }
        return this.getJdbcTemplate().query(sql,params, mapper);
    }


    public List<Object> statArticleParAgence(int depot, String datedeb, String datefin, String famille,String articledebut,String articlefin,int do_type,int siteMarchand,int id){
        String sql = EtatMapper.statArticleParAgence;
        params = new Object[]{depot,datedeb,datefin,famille,articledebut,articlefin,do_type,siteMarchand,id};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }

    public List<Object> etatCaisse(int caNo,String datedeb,String datefin,int modeReglement,int typeReglement,int protNo){
        String sql = EtatMapper.etatCaisse;
        params = new Object[]{datedeb,datefin,caNo,modeReglement,typeReglement,protNo};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }

    public List<Object> statClientParAgence(int depot,String datedeb,String datefin,int doType,int id){
        String sql = EtatMapper.statClientParAgence;
        params = new Object[]{depot,datedeb,datefin,doType,id};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }



    public List<Object> detteDuJour(int protNo){
        String sql = EtatMapper.detteDuJour;
        params = new Object[]{protNo};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }

    public List<Object> detteDuMois(int protNo,int period){
        String sql = EtatMapper.detteDuMois;
        params = new Object[]{protNo,period};
        return this.getJdbcTemplate().query(sql,params, mapper);
    }



    public boolean mesureLigne(String[] params){
        for(int i=0;i<params.length;i++){
            if(params[i].equals("CA HT")) {
                return true;
            }
            if(params[i].equals("Marge")) {
                return true;
            }
            if(params[i].equals("CA TTC")) {
                return true;
            }
            if(params[i].equals("Qte Vendues")) {
                return true;
            }
            if(params[i].equals("Prix de revient")) {
                return true;
            }
        }
        return false;
    }

    public boolean mesureReglement(String[] params){
        for(int i=0;i<params.length;i++){
            if(params[i].equals("Montant regle")) {
                return true;
            }
        }
        return false;
    }

    public String linkQuery(boolean fArticle,boolean isMesure,boolean isMesureLigne, boolean isMesureReglement){
        String from="";

        if(isMesure){
            if(!isMesureReglement && isMesureLigne)
                from = " FROM _MesureDocLigne_ mdl ";
         //   if(isMesureReglement && !isMesureLigne)
         //       from = " FROM _Reglement_ rgl ";
            if(isMesureReglement && isMesureLigne)
                from = from+" FROM _MesureDocLigne_ mdl    " +
                        "LEFT JOIN F_DOCREGL fdregl  ON  fdregl.DO_Domaine = mdl.DO_Domaine" +
                        "                                   AND fdregl.DO_Type = mdl.DO_Type \n" +
                        "                                   AND fdregl.DO_Piece = mdl.DO_Piece  " +
                        "   LEFT JOIN F_REGLECH fregl  ON  fregl.DR_No = fdregl.DR_No " +
                        "   LEFT JOIN _Reglement_ rgl  ON  fregl.RG_No = rgl.RG_No ";
        }

        if(fArticle){
            if(isMesure) {
                from = from + " LEFT JOIN F_ARTICLE fart ON fart.AR_Ref = mdl.AR_Ref ";
            }else{
                from = from + " FROM F_ARTICLE fart ";
            }
        }

            return from;
    }

    public String selectQuery (String[] params ){
        String select = "SELECT ";
        ArrayList<String> selectList = new ArrayList<String>();
        for(String value : params){
            if(value.equals("CA HT"))
                selectList.add("mdl.CAHTNet");
            if(value.equals("Marge"))
                selectList.add("mdl.Marge");
            if(value.equals("CA TTC"))
                selectList.add("mdl.CATTCNet");
            if(value.equals("Qte Vendues"))
                selectList.add("mdl.QteVendues");
            if(value.equals("Prix de revient"))
                selectList.add("mdl.PrxRevientU");
            //Article
            if(value.equals("Reference article"))
                selectList.add("fart.AR_Ref");
            if(value.equals("Prix d'achat"))
                selectList.add("fart.AR_PrixAch");
            if(value.equals("Prix de vente"))
                selectList.add("fart.AR_PrixVen");
            if(value.equals("Designation article"))
                selectList.add("fart.AR_Design");
            //Comptet
            if(value.equals("Code client"))
                selectList.add("fcomptt.CT_Num");
            if(value.equals("Nom client"))
                selectList.add("fcomptt.CT_Intitule");
        }
        String data = " ";
        for(String result : selectList)
            data = data + result +",";
        return select.concat(data.substring(0,data.length()-1));
    }

    public String paramQuery(){
        return EtatMapper.paramQuery;
    }
    public List<Object> createReport(String param,String filter){
        String[] params = param.split(",");
        String[] filters = filter.split(";");
        for(int i=0;i<params.length;i++){
            params[i] = params[i].trim();
        }
        boolean isMesure = isMesure(params);
        boolean isMesureLigne = false;
        boolean isMesureReglement = false;
        boolean isFArticle = fArticle(params);
        String sql ="";
        if(isMesure){
            sql = "WITH ";
            isMesureLigne = mesureLigne(params);
            if(isMesureLigne)
                sql = sql + EtatMapper.MesureDocLigne;
            isMesureReglement = mesureReglement(params);
            if(isMesureReglement) {
                if(isMesureLigne)
                    sql = sql +",";
                sql = sql + EtatMapper.MesureReglement;
            }
        }
        String from  = linkQuery(isFArticle,isMesure,isMesureLigne,isMesureReglement);
        String select = selectQuery (params);
        sql = sql +" "+ select +" "+ from;
      //  sql = article+ " SELECT * FROM _Article_";
        return this.getJdbcTemplate().query(sql, mapper);
    }
}

