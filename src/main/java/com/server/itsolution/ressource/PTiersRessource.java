package com.server.itsolution.ressource;

import com.server.itsolution.dao.FDepotDAO;
import com.server.itsolution.dao.PTiersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/ptiers")
public class PTiersRessource {

    @Autowired
    PTiersDAO pTiersDAO;

    @GetMapping(value = "/tVal01TIntitule={tVal01TIntitule}")
    public List<Object> selectDefautCompte(@PathVariable String tVal01TIntitule) {
        return pTiersDAO.selectDefautCompte(tVal01TIntitule);
    }
}

