package com.server.itsolution.ressource;

import com.server.itsolution.dao.FFamilleDAO;
import com.server.itsolution.entities.FormatText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/ffamille")
public class FFamilleRessource {

    @Autowired
    FFamilleDAO fFamilleDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fFamilleDAO.getAll();
        return list;
    }

    @GetMapping(value = "/faCodeFamille={faCodeFamille}")
    public List<Object> getF_ArticleJSON(@PathVariable String faCodeFamille) {
        List<Object> list = fFamilleDAO.getFFamille(faCodeFamille);
        return list;
    }

    @GetMapping(value = "/getShortList")
    public List<Object> getShortList() {
        List<Object> list = fFamilleDAO.getShortList();
        return list;
    }

    @GetMapping(value = "/getFamilleCount")
    public Object getFamilleCount() {
        return fFamilleDAO.getFamilleCount();
    }

    @GetMapping(value = "/getNextArticleByFam&faCodeFamille={faCodeFamille}")
    public List<Object> getNextArticleByFam(@PathVariable String faCodeFamille) {
        List<Object> list = fFamilleDAO.getNextArticleByFam(faCodeFamille);
        return list;
    }

    @GetMapping(value = "/insertFamille&code={code}&intitule={intitule}&clNo1={clNo1}&clNo2={clNo2}&clNo3={clNo3}&clNo4={clNo4}&protNo={protNo}")
    public void insertFamille(@PathVariable String code,@PathVariable String intitule,@PathVariable int clNo1,@PathVariable int clNo2,@PathVariable int clNo3,@PathVariable int clNo4,@PathVariable int protNo) {
        fFamilleDAO.insertFamille(FormatText.getString(code),FormatText.getString(intitule),clNo1,clNo2,clNo3, clNo4,protNo);
    }

    @GetMapping(value = "/getCatComptaByCodeFamille&faCodeFamille={faCodeFamille}&fcpType={fcpType}&fcpChamp={fcpChamp}")
    public List<Object> getCatComptaByCodeFamille(@PathVariable String faCodeFamille,@PathVariable int fcpType,@PathVariable int fcpChamp) {
        List<Object> list = fFamilleDAO.getCatComptaByCodeFamille(faCodeFamille,fcpType,fcpChamp);
        return list;
    }

    @GetMapping(value = "/maj/nom={nom}&valeur={valeur}&cbMarq={cbMarq}&cbCreateur={cbCreateur}")
    public void getEnteteDocument(@PathVariable String nom, @PathVariable String valeur, @PathVariable BigDecimal cbMarq, @PathVariable String cbCreateur) {
        fFamilleDAO.maj(nom, FormatText.getString(valeur),cbMarq,cbCreateur);
    }

    @GetMapping(value = "/supprFamille&codeFamille={codeFamille}")
    public void supprFamille(@PathVariable String codeFamille){
        fFamilleDAO.supprFamille(codeFamille);
    }

}

