package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.entities.FormatText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fcaisse")
public class FCaisseRessource {

    @Autowired
    FCaisseDAO fCaisseDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fCaisseDAO.getAll();
        return list;
    }

    @GetMapping(value = "/caisseCount")
    public Object caisseCount() {
        return fCaisseDAO.caisseCount();
    }

    @GetMapping(value = "/getCaisseDepot/{protNo}")
    public List<Object> getCaisseDepot(@PathVariable int protNo) {
        List<Object> list = fCaisseDAO.getCaisseDepot(protNo);
        return list;
    }

    @GetMapping(value = "/getCaisseByCA_No&caNo={caNo}")
    public List<Object> getCaisseByCA_No(@PathVariable int caNo) {
        return fCaisseDAO.getCaisseByCA_No(caNo);
    }

    @GetMapping(value = "/getCaisseDepotCount/{protNo}")
    public Object getCaisseDepotCount(@PathVariable int protNo) {
        return fCaisseDAO.getCaisseDepotCount(protNo);
    }

    @GetMapping(value = "/clotureCaisse/dateCloture={dateCloture}&caisseDebut={caisseDebut}&caisseFin={caisseFin}&protNo={protNo}&typeCloture={typeCloture}")
    public void clotureCaisse(@PathVariable String dateCloture,@PathVariable int caisseDebut,@PathVariable int caisseFin,@PathVariable int protNo,@PathVariable int typeCloture) {
        fCaisseDAO.clotureCaisse(dateCloture,caisseDebut,caisseFin,protNo,typeCloture);
    }


    @GetMapping(value = "/getCaNum&caNo={caNo}")
    public Object getCaNum(@PathVariable int caNo){
        return fCaisseDAO.getCaNum(caNo);
    }

    @GetMapping(value = "/{caNo}")
    public Object getCaisse(@PathVariable int caNo) {
        Object list = fCaisseDAO.getF_CaisseJSON(caNo);
        return list;
    }

    @GetMapping(value = "/insertCaisse/caIntitule={caIntitule}&coNoCaissier={coNoCaissier}&joNum={joNum}&cbCreateur={cbCreateur}&codeDepot={codeDepot}")
    public void insertCaisse(@PathVariable String caIntitule,@PathVariable int coNoCaissier,@PathVariable String joNum,@PathVariable String cbCreateur,@PathVariable String codeDepot)  {
        fCaisseDAO.insertCaisse(FormatText.getString(caIntitule),coNoCaissier,FormatText.getString(joNum),FormatText.getString(cbCreateur),codeDepot) ;
    }

    @GetMapping(value = "/insertDepotCaisse/caNo={caNo}&deNo={deNo}")
    public void insertDepotCaisse(@PathVariable int caNo,@PathVariable int deNo)  {
        fCaisseDAO.insertDepotCaisse(caNo,deNo) ;
    }

    @GetMapping(value = "/deleteCaisse/caNo={caNo}")
    public void deleteCaisse(@PathVariable int caNo)  {
        fCaisseDAO.deleteCaisse(caNo); ;
    }

    @GetMapping(value = "/supprDepotCaisse&caNo={caNo}")
    public void supprDepotCaisse(@PathVariable int caNo)  {
        fCaisseDAO.supprDepotCaisse(caNo) ;
    }

    @GetMapping(value = "/maj/nom={nom}&valeur={valeur}&cbMarq={cbMarq}&cbCreateur={cbCreateur}")
    public void maj(@PathVariable String nom, @PathVariable String valeur, @PathVariable BigDecimal cbMarq, @PathVariable String cbCreateur) {
        fCaisseDAO.maj(nom, FormatText.getString(valeur),cbMarq,cbCreateur);
    }
	


	@GetMapping(value = "/listeCaisseShort")
    public Object listeCaisseShort() {
        Object list = fCaisseDAO.listeCaisseShort();
        return list;
    }
	
    @GetMapping(value = "/getCaissierByCaisse&caNo={caNo}")
    public Object getCaissierByCaisse(@PathVariable int caNo) {
        Object list = fCaisseDAO.getCaissierByCaisse(caNo);
        return list;
    }
	
	
	


}

