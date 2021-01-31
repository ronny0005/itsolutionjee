package com.server.itsolution.ressource;

import com.server.itsolution.dao.FProtectioncialDAO;
import com.server.itsolution.dao.PParametrecialDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/pparametrecial")
public class PParametrecialRessource {

    @Autowired
    PParametrecialDAO pParametrecialDAO;

    @GetMapping(value = "/getPParametrecial")
    public Object getPParametrecial() {
        return pParametrecialDAO.getObject();
    }


}

