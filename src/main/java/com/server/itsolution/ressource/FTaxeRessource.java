package com.server.itsolution.ressource;

import com.server.itsolution.dao.FTaxeDAO;
import com.server.itsolution.entities.FormatText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/ftaxe")
public class FTaxeRessource {

    @Autowired
    FTaxeDAO fTaxeDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fTaxeDAO.getAll();
        return list;
    }
    @GetMapping(value = "/taCode={taCode}")
    public List<Object> getCGNum(@PathVariable String taCode) {
        return fTaxeDAO.getTaCode(taCode);
    }

    @GetMapping(value = "/getF_TaxeCount")
    public Object getF_TaxeCount() {
        return fTaxeDAO.getF_TaxeCount();
    }

    @GetMapping(value = "/allSearch&intitule={intitule}&top={top}")
    public List<Object> allSearch(@PathVariable String intitule, @PathVariable int top) {
        return fTaxeDAO.allSearch(FormatText.getString(intitule),top);
    }
}

