package com.server.itsolution.ressource;

import com.server.itsolution.dao.PCatTarifDAO;
import com.server.itsolution.dao.PUniteDAO;
import com.server.itsolution.entities.PUnite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/punite")
public class PUniteRessource {

    @Autowired
    PUniteDAO pUniteDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = pUniteDAO.getAll();
        return list;
    }
}

