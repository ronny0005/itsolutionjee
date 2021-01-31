package com.server.itsolution.ressource;

import com.server.itsolution.dao.FArtClientDAO;
import com.server.itsolution.dao.FArtStockDAO;
import com.server.itsolution.entities.FArtClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fartclient")
public class FArtClientRessource {

    @Autowired
    FArtClientDAO fArtClientDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fArtClientDAO.getAll();
        return list;
    }

    @GetMapping(value = "/updateFArtClient&arRef={arRef}&acCategorie={acCategorie}&acPrixTTC={acPrixTTC}&acPrixVen={acPrixVen}&acCoef={acCoef}")
    public void updateFArtClient(@PathVariable String arRef,@PathVariable int acCategorie,@PathVariable double acPrixTTC,@PathVariable double acPrixVen,@PathVariable double acCoef) {
        fArtClientDAO.updateFArtClient(arRef,acCategorie,acPrixTTC,acPrixVen,acCoef);
    }

    @GetMapping(value = "/getF_ArtclientCount")
    public Object getF_ArtclientCount() {
        return fArtClientDAO.getF_ArtclientCount();
    }


}

