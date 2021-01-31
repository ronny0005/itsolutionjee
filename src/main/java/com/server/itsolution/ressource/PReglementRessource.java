package com.server.itsolution.ressource;

import com.server.itsolution.dao.FArticleDAO;
import com.server.itsolution.dao.PReglementDAO;
import com.server.itsolution.entities.FDocLigne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/preglement")
public class PReglementRessource {

    @Autowired
    PReglementDAO pReglementDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = pReglementDAO.getAll();
        return list;
    }
    @GetMapping(value = "/listeTypeReglementCount")
    public Object listeTypeReglementCount() {
        return pReglementDAO.listeTypeReglementCount();
    }

    @GetMapping(value = "/cbMarq={cbMarq}")
    public Object getPReglement(@PathVariable BigDecimal cbMarq) {
        return pReglementDAO.getPReglement(cbMarq);
    }
}

