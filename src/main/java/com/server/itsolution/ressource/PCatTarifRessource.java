package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.dao.PCatTarifDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/pcattarif")
public class PCatTarifRessource {

    @Autowired
    PCatTarifDAO pCatTarifDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = pCatTarifDAO.getAll();
        return list;
    }
    @GetMapping(value = "/getTarifCount")
    public Object getTarifCount() {
        return pCatTarifDAO.getTarifCount();
    }

    @GetMapping(value = "/cbMarq={cbMarq}")
    public List<Object> getPCatTarif(@PathVariable int cbMarq) {
        List<Object> list = pCatTarifDAO.getpCatTarif(cbMarq);
        return list;
    }



}

