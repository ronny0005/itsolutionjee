package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.dao.FComptegDAO;
import com.server.itsolution.entities.FormatText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fcompteg")
public class FCompteGRessource {

    @Autowired
    FComptegDAO fComptegDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fComptegDAO.getAll();
        return list;
    }
    @GetMapping(value = "/getComptegCount")
    public Object getComptegCount() {
        return fComptegDAO.getComptegCount();
    }

    @GetMapping(value = "/getComptegByType&cgType={cgType}")
    public List<Object> getComptegByType(@PathVariable int cgType) {
        return fComptegDAO.getComptegByType(cgType);
    }

    @GetMapping(value = "/cgNum={cgNum}")
    public List<Object> getCGNum(@PathVariable String cgNum) {
        return fComptegDAO.getCGNum(cgNum);
    }

    @GetMapping(value = "/allSearch&intitule={intitule}&top={top}")
    public List<Object> allSearch(@PathVariable String intitule,@PathVariable int top) {
            return fComptegDAO.allSearch(FormatText.getString(intitule),top);
    }

    }

