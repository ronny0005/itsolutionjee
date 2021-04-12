package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.dao.ZCalendarUserDAO;
import com.server.itsolution.entities.ZCalendarUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/zCalendarUser")
public class ZCalendarUserRessource {

    @Autowired
    ZCalendarUserDAO zCalendarUserDAO;


    @GetMapping(value = "/canConnect/{protNo}/{jour}/{heure}")
    public Object canConnect(@PathVariable int protNo, @PathVariable int jour,@PathVariable String heure) {
        int canConnect= zCalendarUserDAO.canConnect(protNo,jour,heure);
        return canConnect;
    }
    @GetMapping(value = "/isCalendarUser/{protNo}")
    public Object isCalendarUser(@PathVariable int protNo) {
        int canConnect= zCalendarUserDAO.isCalendarUser(protNo);
        return canConnect;
    }
}

