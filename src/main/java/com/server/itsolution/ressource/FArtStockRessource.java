package com.server.itsolution.ressource;

import com.server.itsolution.dao.FArtStockDAO;
import com.server.itsolution.dao.FArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fartstock")
public class FArtStockRessource {

    @Autowired
    FArtStockDAO fArtStockDAO;

    @GetMapping(value = "/isStockJSON&arRef={arRef}&deNo={deNo}")
    public List<Object> isStockJSON(@PathVariable String arRef,@PathVariable int deNo) {
        List<Object> list = fArtStockDAO.isStockJSON(arRef,deNo);
        return list;
    }

    @GetMapping(value = "/articleWithStockCount&deNo={deNo}")
    public Object articleWithStockCount(@PathVariable int deNo) {
        return fArtStockDAO.articleWithStockCount(deNo);
    }

    @GetMapping(value = "/getArticleWithStock&deNo={deNo}")
    public Object getArticleWithStock(@PathVariable int deNo) {
        return fArtStockDAO.getArticleWithStock(deNo);
    }

    @GetMapping(value = "/articleWithStockMax&deNo={deNo}")
    public Object articleWithStockMax(@PathVariable int deNo) {
        return fArtStockDAO.articleWithStockMax(deNo);
    }

    @GetMapping(value = "/articleWithStock&deNo={deNo}")
    public List<Object> articleWithStock(@PathVariable int deNo) {
        return fArtStockDAO.articleWithStock(deNo);

    }

    @GetMapping(value = "/isStockDENo&dlQte={dlQte}&deNo={deNo}&arRef={arRef}")
    public List<Object> isStockDENo(@PathVariable float dlQte,@PathVariable int deNo,@PathVariable String arRef) {
        return fArtStockDAO.isStockDENo(dlQte,deNo,arRef);
    }
    @GetMapping(value = "/updateArtStock&arRef={arRef}&deNo={deNo}&montant={montant}&qte={qte}&action={action}&protNo={protNo}")
    public void updateArtStock(@PathVariable String arRef,@PathVariable int deNo,@PathVariable float montant,@PathVariable float qte,@PathVariable String action,@PathVariable String protNo) {
            fArtStockDAO.updateFArtstock(arRef,deNo,montant,qte,action,protNo);
    }
	
    @GetMapping(value = "/insertF_ArtStock&arRef={arRef}&deNo={deNo}&montant={montant}&qte={qte}&cbCreateur={cbCreateur}")
    public void insertF_ArtStock(@PathVariable String arRef,@PathVariable int deNo,@PathVariable float montant,@PathVariable float qte,@PathVariable String cbCreateur) {
        fArtStockDAO.insertF_ArtStock(arRef,deNo,montant,qte,cbCreateur);
    }

    @GetMapping(value = "/setASQteMaxiArtStock&arRef={arRef}&deNo={deNo}")
    public void setASQteMaxiArtStock(@PathVariable String arRef,@PathVariable int deNo) {
        fArtStockDAO.setASQteMaxiArtStock(arRef,deNo);
    }


}

