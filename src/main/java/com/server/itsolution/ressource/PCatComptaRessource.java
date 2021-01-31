package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.dao.PCatComptaDAO;
import com.server.itsolution.entities.PCatCompta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/pcatcompta")
public class PCatComptaRessource {

    @Autowired
    PCatComptaDAO pCatComptaDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = pCatComptaDAO.getAll();
        return list;
    }
    @GetMapping(value = "/getAllCount")
    public Object getAllCount() {
        return pCatComptaDAO.getAllCount();
    }

    @GetMapping(value = "/getCatComptaAchat")
    public List<Object> getCatComptaAchat() {
        List<Object> list = pCatComptaDAO.getCatComptaAchat();
        return list;
    }

    @GetMapping(value = "/getCatComptaVente")
    public List<Object> getCatComptaVente() {
        List<Object> list = pCatComptaDAO.getCatComptaVente();
        return list;
    }
}

