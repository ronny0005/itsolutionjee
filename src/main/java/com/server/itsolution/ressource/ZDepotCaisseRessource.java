package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.dao.ZDepotCaisseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/zdepotcaisse")
public class ZDepotCaisseRessource {

    @Autowired
    ZDepotCaisseDAO zDepotCaisseDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = zDepotCaisseDAO.getAll();
        return list;
    }

    @GetMapping(value = "/getDepotCaisseSelect&caNo={caNo}")
    public List<Object> getDepotCaisseSelect(@PathVariable int caNo) {
        return zDepotCaisseDAO.getDepotCaisseSelect(caNo);
    }


}

