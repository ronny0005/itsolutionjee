package com.server.itsolution.ressource;

import com.server.itsolution.dao.PPreferencesDAO;
import com.server.itsolution.dao.ZDepotUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/ppreference")
public class PPreferenceRessource {

    @Autowired
    PPreferencesDAO pPreferencesDAO;

    @GetMapping(value = "/info")
    public Object getpreference() {
        return pPreferencesDAO.getObject();
    }
}

