package com.server.itsolution.ressource;

import com.server.itsolution.dao.PCatTarifDAO;
import com.server.itsolution.dao.PDeviseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/pdevise")
public class PDeviseRessource {

    @Autowired
    PDeviseDAO pDeviseDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = pDeviseDAO.getAll();
        return list;
    }

}

