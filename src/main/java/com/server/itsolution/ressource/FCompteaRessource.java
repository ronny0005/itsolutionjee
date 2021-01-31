package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCompteADAO;
import com.server.itsolution.dao.FComptetDAO;
import com.server.itsolution.entities.FCompteA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fcomptea")
public class FCompteaRessource {

    @Autowired
    FCompteADAO fCompteADAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fCompteADAO.getAll();
        return list;
    }

    @GetMapping(value = "/affaire&sommeil={sommeil}")
    public List<Object> affaire(@PathVariable int sommeil) {
        return  fCompteADAO.affaire(sommeil);
    }

    @GetMapping(value = "/caNum={caNum}")
    public List<Object> getCANum(@PathVariable String caNum) {
        return  fCompteADAO.getCANum(caNum);
    }

    @GetMapping(value = "/allSeach/intitule={intitule}&top={top}")
    public List<Object> getAll(@PathVariable String intitule, @PathVariable int top) {
        List<Object> list = fCompteADAO.getAllSeach(intitule, top);
        return list;
    }



}

