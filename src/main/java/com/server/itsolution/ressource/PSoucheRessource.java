package com.server.itsolution.ressource;

import com.server.itsolution.dao.PCatComptaDAO;
import com.server.itsolution.dao.PSoucheDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/psouche")
public class PSoucheRessource {

    @Autowired
    PSoucheDAO pSoucheDAO;

    @GetMapping(value = "/soucheVente")
    public List<Object> soucheVente() {
        return pSoucheDAO.psoucheVente();
    }

    @GetMapping(value = "/soucheAchat")
    public List<Object> soucheAchat() {
        return pSoucheDAO.psoucheAchat();
    }

    @GetMapping(value = "/soucheInterne")
    public List<Object> soucheInterne() {
        return  pSoucheDAO.psoucheInterne();
    }
    @GetMapping(value = "/getPSoucheVente/cbIndice={cbIndice}")
    public List<Object> getPSoucheVente(@PathVariable int cbIndice) {
        return pSoucheDAO.getPSoucheVente(cbIndice);
    }

    @GetMapping(value = "/getPSoucheAchat/cbIndice={cbIndice}")
    public List<Object> getPSoucheAchat(@PathVariable int cbIndice) {
        return pSoucheDAO.getPSoucheAchat(cbIndice);
    }

    @GetMapping(value = "/getPSoucheInterne/cbIndice={cbIndice}")
    public List<Object> getPSoucheInterne(@PathVariable int cbIndice) {
        return pSoucheDAO.getPSoucheInterne(cbIndice);
    }
}

