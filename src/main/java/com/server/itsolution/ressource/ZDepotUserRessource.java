package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.dao.ZDepotUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/zdepotuser")
public class ZDepotUserRessource {

    @Autowired
    ZDepotUserDAO zDepotUserDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = zDepotUserDAO.getAll();
        return list;
    }
    @GetMapping(value = "/user&protNo={protNo}")
    public Object getDepotUser(@PathVariable int protNo) {
        List<Object> list = zDepotUserDAO.getDepotUser(protNo);
        return list;
    }
    @GetMapping(value = "/usercount&protNo={protNo}")
    public Object getDepotUserCount(@PathVariable int protNo) {
        return zDepotUserDAO.getDepotUserCount(protNo);
    }

    @GetMapping(value = "/getPrincipalDepot&protNo={protNo}")
    public Object getPrincipalDepot(@PathVariable int protNo) {
        return zDepotUserDAO.getPrincipalDepot(protNo);
    }
}

