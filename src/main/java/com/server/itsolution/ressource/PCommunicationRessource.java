package com.server.itsolution.ressource;

import com.server.itsolution.dao.PCommunicationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/pcommunication")
public class PCommunicationRessource {

    @Autowired
    PCommunicationDAO pCommunicationDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = pCommunicationDAO.getAll();
        return list;
    }
}

