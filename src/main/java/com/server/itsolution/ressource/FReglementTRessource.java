package com.server.itsolution.ressource;

import com.server.itsolution.dao.FArtStockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/freglementt")
public class FReglementTRessource {

    @Autowired
    FArtStockDAO fArtStockDAO;


}

