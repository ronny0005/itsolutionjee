package com.server.itsolution.ressource;

import com.server.itsolution.dao.FModeleRDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fmodeler")
public class FModeleRRessource {

    @Autowired
    FModeleRDAO fModeleRDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fModeleRDAO.getAll();
        return list;
    }

    @GetMapping(value = "/mrNo={mrNo}")
    public List<Object> getMrNo(@PathVariable int mrNo) {
        List<Object> list = fModeleRDAO.getMrNo(mrNo);
        return list;
    }

    @GetMapping(value = "/getDateEcgetTiersheanceSelectSage&mrNo={mrNo}&nReglement={nReglement}&date={date}")
    public List<Object> getDateEcgetTiersheanceSelectSage(@PathVariable int mrNo,@PathVariable int nReglement,@PathVariable String date) {
        return fModeleRDAO.getDateEcgetTiersheanceSelectSage(mrNo,nReglement,date);
    }
}

