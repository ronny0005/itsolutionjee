package com.server.itsolution.ressource;

import com.server.itsolution.dao.FDocEnteteDAO;
import com.server.itsolution.dao.FDocReglDAO;
import com.server.itsolution.entities.FDocEntete;
import com.server.itsolution.entities.FEcritureA;
import com.server.itsolution.entities.FormatText;
import com.server.itsolution.entities.SaisieAnalytique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fdocregl")
public class FDocReglRessource {

    @Autowired
    FDocReglDAO fDocReglDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        return fDocReglDAO.getAll();
    }

    @GetMapping(value = "/getDocRegl&cbMarqEntete={cbMarq}")
    public Object getFDocEntete(@PathVariable BigDecimal cbMarq) {
        return fDocReglDAO.getDocReglByDO_Piece(cbMarq);
    }

}

