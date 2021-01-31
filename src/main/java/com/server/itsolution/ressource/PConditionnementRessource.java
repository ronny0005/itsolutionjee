package com.server.itsolution.ressource;

import com.server.itsolution.dao.PCatTarifDAO;
import com.server.itsolution.dao.PConditionnementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/pconditionnement")
public class PConditionnementRessource {

    @Autowired
    PConditionnementDAO pConditionnementDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = pConditionnementDAO.getAll();
        return list;
    }

    @GetMapping(value = "/cbMarq={cbMarq}")
    public List<Object> getPConditionnement(@PathVariable int cbMarq) {
        List<Object> list = pConditionnementDAO.getpConditionnement(cbMarq);
        return list;
    }

    @GetMapping(value = "/getConditionnementMax")
    public Object getConditionnementMax() {
        return pConditionnementDAO.getConditionnementMax();
    }

    @GetMapping(value = "/getPrixConditionnement&arRef={arRef}")
    public List<Object> getPrixConditionnement(@PathVariable String arRef) {
        List<Object> list = pConditionnementDAO.getPrixConditionnement(arRef);
        return list;
    }

}

