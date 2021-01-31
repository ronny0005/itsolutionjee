package com.server.itsolution.ressource;

import com.server.itsolution.dao.EtatDAO;
import com.server.itsolution.dao.FFamilleDAO;
import com.server.itsolution.entities.FormatText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/etat")
public class EtatRessource {

    @Autowired
    EtatDAO etatDAO;

    @GetMapping(value = "/menuCaParDepot&protNo={protNo}")
    public List<Object> menuCaParDepot(@PathVariable int protNo) {
        return etatDAO.menuCaParDepot(protNo);
    }

    @GetMapping(value = "/createReport&param={param}&filter={filter}")
    public List<Object> createReport(@PathVariable String param,@PathVariable String filter) {
        return etatDAO.createReport(FormatText.getString(param),FormatText.getString(filter));
    }

    @GetMapping(value = "/invPreparatoire&depot={depot}&datedeb={datedeb}&type={type}")
    public List<Object> invPreparatoire(@PathVariable int depot,@PathVariable String datedeb,@PathVariable int type) {
        return etatDAO.invPreparatoire(depot,datedeb,type);
    }

    @GetMapping(value = "/equationStkVendeur&datedeb={datedeb}&datefin={datefin}&depot={depot}")
    public List<Object> equationStkVendeur(@PathVariable String datedeb,@PathVariable String datefin,@PathVariable int depot){
        return etatDAO.equationStkVendeur(datedeb,datefin,depot);
    }



        @GetMapping(value = "/statArticleParAgence&depot={depot}&datedeb={datedeb}&datefin={datefin}&famille={famille}&articledebut={articledebut}&articlefin={articlefin}&doType={doType}&siteMarchand={siteMarchand}&id={id}")
    public List<Object> statArticleParAgence(@PathVariable int depot,@PathVariable String datedeb,@PathVariable String datefin,@PathVariable String famille,@PathVariable String articledebut,@PathVariable String articlefin,@PathVariable int doType,@PathVariable int siteMarchand,@PathVariable int id){
        return etatDAO.statArticleParAgence(depot,datedeb,datefin,famille,articledebut,articlefin,doType,siteMarchand,id);
    }

    @GetMapping(value = "/etatCaisse&caNo={caNo}&datedeb={datedeb}&datefin={datefin}&modeReglement={modeReglement}&typeReglement={typeReglement}&protNo={protNo}")
    public List<Object> etatCaisse(@PathVariable int caNo,@PathVariable String datedeb,@PathVariable String datefin,@PathVariable int modeReglement,@PathVariable int typeReglement,@PathVariable int protNo) {
        return etatDAO.etatCaisse(caNo,datedeb,datefin,modeReglement,typeReglement,protNo);
    }

    @GetMapping(value = "/statClientParAgence&depot={depot}&datedeb={datedeb}&datefin={datefin}&doType={doType}&id={id}")
    public List<Object> statClientParAgence(@PathVariable int depot,@PathVariable String datedeb,@PathVariable String datefin,@PathVariable int doType,@PathVariable int id){
        return etatDAO.statClientParAgence(depot,datedeb,datefin,doType,id);
    }

    @GetMapping(value = "/statMouvementStock&depot={depot}&datedeb={datedeb}&datefin={datefin}&articledebut={articledebut}&articlefin={articlefin}")
    public List<Object> statMouvementStock(@PathVariable int depot,@PathVariable String datedeb,@PathVariable String datefin,@PathVariable String articledebut,@PathVariable String articlefin) {
        return etatDAO.statMouvementStock(depot,datedeb,datefin,articledebut,articlefin);
    }

    @GetMapping(value = "/top10Vente&period={period}")
    public List<Object> top10Vente(@PathVariable String period) {
        return etatDAO.top10Vente(period);
    }

    @GetMapping(value = "/statCaisseDuJour&protNo={protNo}")
    public List<Object> statCaisseDuJour(@PathVariable int protNo){
        return etatDAO.statCaisseDuJour(protNo);
    }

    @GetMapping(value = "/detteDuJour&protNo={protNo}")
    public List<Object> detteDuJour(@PathVariable int protNo){
        return etatDAO.detteDuJour(protNo);
    }

}

