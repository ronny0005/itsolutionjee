package com.server.itsolution.ressource;

import com.server.itsolution.dao.FArtcomptaDAO;
import com.server.itsolution.dao.FFamcomptaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fartcompta")
public class FArtcomptaRessource {

    @Autowired
    FArtcomptaDAO fArtcomptaDAO;

    @GetMapping(value = "/all")
    public Object all() {
        return fArtcomptaDAO.all();
    }

    @GetMapping(value = "/fartcomptacount")
    public Object fartcomptacount() {
        return fArtcomptaDAO.fartcomptacount();
    }

}

