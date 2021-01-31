package com.server.itsolution.ressource;

import com.server.itsolution.dao.FDepotDAO;
import com.server.itsolution.entities.FormatText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fdepot")
public class FDepotRessource {

    @Autowired
    FDepotDAO fDepotDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fDepotDAO.getAll();
        return list;
    }
    @GetMapping(value = "/deNo={deNo}")
    public List<Object> getFDepot(@PathVariable int deNo) {
        List<Object> list = fDepotDAO.getFDepot(deNo);
        return list;
    }


    @GetMapping(value = "/maj/nom={nom}&valeur={valeur}&cbMarq={cbMarq}&cbCreateur={cbCreateur}")
    public void maj(@PathVariable String nom, @PathVariable String valeur, @PathVariable BigDecimal cbMarq, @PathVariable String cbCreateur) {
        fDepotDAO.maj(nom, FormatText.getString(valeur),cbMarq,cbCreateur);
    }

    @GetMapping(value = "/majCatTarif&deNo={deNo}&catTarif={catTarif}")
    public void majCatTarif(@PathVariable int deNo,@PathVariable int catTarif){
        fDepotDAO.majCatTarif(deNo,catTarif);
    }

    @GetMapping(value = "/insertDepotClient&deNo={deNo}&value={value}")
    public void  insertDepotClient (@PathVariable int deNo,@PathVariable String value) {
        fDepotDAO.insertDepotClient(deNo,value);
    }

    @GetMapping(value = "/insertDepot&deIntitule={deIntitule}&deAdresse={deAdresse}&deComplement={deComplement}&deCodePostal={deCodePostal}&deVille={deVille}&deContact={deContact}&deRegion={deRegion}&dePays={dePays}&deEmail={deEmail}&deTelephone={deTelephone}&deTelecopie={deTelecopie}&protNo={protNo}&caSoucheVente={caSoucheVente}&caSoucheAchat={caSoucheAchat}&caSoucheInterne={caSoucheInterne}&affaire={affaire}&caisse={caisse}&codeClient={codeClient}")
    public Object insertDepot(@PathVariable String deIntitule,@PathVariable String deAdresse,@PathVariable String deComplement,@PathVariable String deCodePostal
            ,@PathVariable String deVille,@PathVariable String deContact,@PathVariable String deRegion,@PathVariable String dePays,@PathVariable String deEmail
            ,@PathVariable String deTelephone,@PathVariable String deTelecopie,@PathVariable String protNo,@PathVariable int caSoucheVente,@PathVariable int caSoucheAchat
            ,@PathVariable int caSoucheInterne,@PathVariable String affaire,@PathVariable int caisse,@PathVariable String codeClient){
        return fDepotDAO.insertDepot(FormatText.getString(deIntitule),FormatText.getString(deAdresse),FormatText.getString(deComplement),FormatText.getString(deCodePostal)
                                ,FormatText.getString(deVille),FormatText.getString(deContact),FormatText.getString(deRegion),FormatText.getString(dePays),FormatText.getString(deEmail)
                                ,FormatText.getString(deTelephone),FormatText.getString(deTelecopie)
                                ,protNo,caSoucheVente,caSoucheAchat,caSoucheInterne,FormatText.getString(affaire),caisse,codeClient);
    }

    @GetMapping(value = "/insertDepotSouche&deNo={deNo}&caNum={caNum}&caSoucheVente={caSoucheVente}&caSoucheAchat={caSoucheAchat}&caSoucheStock={caSoucheStock}")
    public void insertDepotSouche (@PathVariable int deNo,@PathVariable String caNum,@PathVariable int caSoucheVente,@PathVariable int caSoucheAchat,@PathVariable int caSoucheStock) {
        fDepotDAO.insertDepotSouche(deNo,caNum,caSoucheVente,caSoucheAchat,caSoucheStock);
    }

    @GetMapping(value = "/insertDepotCaisse&deNo={deNo}&caNo={caNo}")
    public void insertDepotCaisse (@PathVariable int deNo,@PathVariable int caNo) {
        fDepotDAO.insertDepotCaisse (deNo,caNo);
    }

    @GetMapping(value = "/getDepotUser&protNo={protNo}")
    public List<Object> getDepotUser(@PathVariable int protNo){
        return fDepotDAO.getDepotUser(protNo);
    }

    @GetMapping(value = "/getDepotUserPrincipal&protNo={protNo}")
    public List<Object> getDepotUserPrincipal(@PathVariable int protNo){
        return fDepotDAO.getDepotUserPrincipal(protNo);
    }

    @GetMapping(value = "/deleteDepot&deNo={deNo}")
    public void deleteDepot (@PathVariable int deNo) {
        fDepotDAO.deleteDepot (deNo);
    }

    @GetMapping(value = "/supprDepotClient&deNo={deNo}")
    public void  supprDepotClient (@PathVariable int deNo){
        fDepotDAO.supprDepotClient(deNo);
    }

    @GetMapping(value = "/depotShortDetail")
    public List<Object> depotShortDetail() {
        return fDepotDAO.depotShortDetail();
    }

    @GetMapping(value = "/depotCount")
    public Object depotCount() {
        return fDepotDAO.depotCount();
    }


    @GetMapping(value = "/getDepotByIntitule&deNo={deNo}&intitule={intitule}")
    public List<Object> getDepotByIntitule(@PathVariable int deNo,@PathVariable String intitule) {
        return fDepotDAO.getDepotByIntitule(intitule,deNo);
    }

    @GetMapping(value = "/setCatTarif&deNo={deNo}")
    public String setCatTarif(@PathVariable int deNo) {
        return fDepotDAO.setCatTarif(deNo);
    }


    @GetMapping(value = "/getDepotUserSearch&protNo={protNo}&depotExclude={depotExclude}&searchTerm={searchTerm}&principal={principal}")
    public List<Object> getDepotUserSearch(@PathVariable int protNo,@PathVariable int depotExclude,@PathVariable String searchTerm,@PathVariable int principal) {
        return fDepotDAO.getDepotUserSearch(protNo,depotExclude,searchTerm,principal);
    }


}

