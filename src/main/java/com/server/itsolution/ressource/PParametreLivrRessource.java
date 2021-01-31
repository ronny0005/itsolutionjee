package com.server.itsolution.ressource;

import com.server.itsolution.dao.FDepotDAO;
import com.server.itsolution.dao.PParametreLivrDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/pparametreLivr")
public class PParametreLivrRessource {

    @Autowired
    PParametreLivrDAO pParametreLivrDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = pParametreLivrDAO.getAll();
        return list;
    }

    @GetMapping(value = "/{cbMarq}")
    public Object getCaisse(@PathVariable int cbMarq) {
        Object list = pParametreLivrDAO.getpParametreLivr(cbMarq);
        return list;
    }
}

