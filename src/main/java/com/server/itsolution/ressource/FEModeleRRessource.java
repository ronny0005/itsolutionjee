package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.dao.FEModeleRDAO;
import com.server.itsolution.dao.FModeleRDAO;
import com.server.itsolution.entities.FEModeleR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/FEModeleR")
public class FEModeleRRessource {

    @Autowired
    FEModeleRDAO feModeleRDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = feModeleRDAO.getAll();
        return list;
    }
}

