package com.server.itsolution.ressource;

import com.server.itsolution.dao.FArtStockDAO;
import com.server.itsolution.dao.FFamcomptaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/ffamcompta")
public class FFamcomptaRessource {

    @Autowired
    FFamcomptaDAO fFamcomptaDAO;

    @GetMapping(value = "/all")
    public Object all() {
        return fFamcomptaDAO.all();
    }

    @GetMapping(value = "/ffamcomptacount")
    public Object ffamcomptacount() {
        return fFamcomptaDAO.ffamcomptacount();
    }

    @GetMapping(value = "/getLibTaxePied&fcpType={fcpType}&fcpChamp={fcpChamp}")
    public Object getLibTaxePied(@PathVariable int fcpType,@PathVariable int fcpChamp) {
        return fFamcomptaDAO.getLibTaxePied(fcpType, fcpChamp);
    }

}

