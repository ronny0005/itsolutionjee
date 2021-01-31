package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCatalogueDAO;
import com.server.itsolution.dao.PConditionnementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fcatalogue")
public class FCatalogueRessource {

    @Autowired
    FCatalogueDAO fCatalogueDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fCatalogueDAO.getAll();
        return list;
    }

    @GetMapping(value = "/clNo={clNo}")
    public List<Object> getCatalogueByCL(@PathVariable int clNo) {
        List<Object> list = fCatalogueDAO.getCatalogueByCL(clNo);
        return list;
    }

    @GetMapping(value = "/getCatalogue&clNiveau={clNiveau}")
    public List<Object> getCatalogue(@PathVariable int clNiveau) {
        List<Object> list = fCatalogueDAO.getCatalogue(clNiveau);
        return list;
    }

    @GetMapping(value = "/getCatalogueByCL&clNiveau={clNiveau}&clNoParent={clNoParent}")
    public List<Object> getCatalogueByCL(@PathVariable int clNiveau,@PathVariable int clNoParent) {
        List<Object> list = fCatalogueDAO.getCatalogueChildren(clNiveau,clNoParent);
        return list;
    }

    @GetMapping(value = "/getLastCatalogue")
    public List<Object> getLastCatalogue() {
        List<Object> list = fCatalogueDAO.getLastCatalogue();
        return list;
    }

    @GetMapping(value = "/insertFCatalogue&clIntitule={clIntitule}&clNoParent={clNoParent}&clNiveau={clNiveau}&cbCreateur={cbCreateur}")
    public List<Object> insertFCatalogue(@PathVariable String clIntitule,@PathVariable int clNoParent,@PathVariable int clNiveau,@PathVariable String cbCreateur) {
        List<Object> list = fCatalogueDAO.insertFCatalogue(clIntitule,clNoParent,clNiveau,cbCreateur);
        return list;
    }

    @GetMapping(value = "/deleteFCatalogue&clNo={clNo}")
    public List<Object> deleteFCatalogue(@PathVariable int clNo) {
        List<Object> list = fCatalogueDAO.deleteFCatalogue(clNo);
        return list;
    }


}

