package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCollaborateurDAO;
import com.server.itsolution.entities.FormatText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fcollaborateur")
public class FCollaborateurRessource {

    @Autowired
    FCollaborateurDAO fCollaborateurDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        return  fCollaborateurDAO.getAll();
    }

    @GetMapping(value = "/allVendeur")
    public Object allVendeur() {
        return  fCollaborateurDAO.allVendeur();
    }

    @GetMapping(value = "/getCollaborateurCount")
    public Object getCollaborateurCount() {
        return  fCollaborateurDAO.getCollaborateurCount();
    }


    @GetMapping(value = "/{coNo}")
    public List<Object> getAll(@PathVariable int coNo) {
        List<Object> list = fCollaborateurDAO.getCollaborateur(coNo);
        return list;
    }

    @GetMapping(value = "/getCaissierByCaisse&caNo={caNo}")
    public List<Object> getCaissierByCaisse(@PathVariable int caNo) {
        return fCollaborateurDAO.getCaissierByCaisse(caNo);
    }

    @GetMapping(value = "/allCaissier")
    public List<Object> allCaissier() {
        List<Object> list = fCollaborateurDAO.allCaissier();
        return list;
    }

    @GetMapping(value = "/allAcheteur")
    public List<Object> allAcheteur() {
        List<Object> list = fCollaborateurDAO.allAcheteur();
        return list;
    }
    @GetMapping(value = "/delete&cbMarq={cbMarq}")
    public void delete(@PathVariable BigInteger cbMarq) {
        fCollaborateurDAO.delete(cbMarq);
    }


    @GetMapping(value = "/insertCollaborateur/coNom={coNom}&coPrenom={coPrenom}&codePostal={codePostal}&coFonction={coFonction}&coAdresse={coAdresse}&coComplement={coComplement}&coVille={coVille}&coCodeRegion={coCodeRegion}&coPays={coPays}&coService={coService}&coVendeur={coVendeur}&coCaissier={coCaissier}&coAcheteur={coAcheteur}&coTelephone={coTelephone}&coTelecopie={coTelecopie}&coEmail={coEmail}&coReceptionnaire={coReceptionnaire}&coChargeRecouvr={coChargeRecouvr}&cbCreateur={cbCreateur}")
    public Object insertCollaborateur (@PathVariable String coNom,@PathVariable String coPrenom,@PathVariable String codePostal,String coFonction,@PathVariable String coAdresse,@PathVariable String coComplement
									,@PathVariable String coVille,@PathVariable String coCodeRegion,@PathVariable String coPays,@PathVariable String coService
										,@PathVariable int coVendeur,@PathVariable int coCaissier, @PathVariable int coAcheteur,@PathVariable  String coTelephone,@PathVariable String coTelecopie,@PathVariable String coEmail
										,@PathVariable int coReceptionnaire,@PathVariable int coChargeRecouvr,@PathVariable String cbCreateur) {
            return fCollaborateurDAO.insertCollaborateur (FormatText.getString(coNom)
                    ,FormatText.getString(coPrenom)
                    ,codePostal,FormatText.getString(coFonction),FormatText.getString(coAdresse),FormatText.getString(coComplement),FormatText.getString(coVille),FormatText.getString(coCodeRegion),FormatText.getString(coPays),coService
                                            ,coVendeur,coCaissier,coAcheteur,FormatText.getString(coTelephone),FormatText.getString(coTelecopie),FormatText.getString(coEmail),coReceptionnaire,coChargeRecouvr,cbCreateur);
    }
	
    @GetMapping(value = "/modifCollaborateur/coNom={coNom}&coPrenom={coPrenom}&codePostal={codePostal}&coFonction={coFonction}&coAdresse={coAdresse}&coComplement={coComplement}&coVille={coVille}&coCodeRegion={coCodeRegion}&coPays={coPays}&coService={coService}&coVendeur={coVendeur}&coCaissier={coCaissier}&coAcheteur={coAcheteur}&coTelephone={coTelephone}&coTelecopie={coTelecopie}&coEmail={coEmail}&coReceptionnaire={coReceptionnaire}&coChargeRecouvr={coChargeRecouvr}&cbCreateur={cbCreateur}&coNo={coNo}")
    public void modifCollaborateur (@PathVariable String coNom,@PathVariable String coPrenom,@PathVariable String codePostal,@PathVariable String coFonction,@PathVariable String coAdresse,@PathVariable String coComplement
									,@PathVariable String coVille,@PathVariable String coCodeRegion,@PathVariable String coPays,@PathVariable String coService
										,@PathVariable int coVendeur,@PathVariable int coCaissier, @PathVariable int coAcheteur,@PathVariable  String coTelephone,@PathVariable String coTelecopie,@PathVariable String coEmail
										,@PathVariable int coReceptionnaire,@PathVariable int coChargeRecouvr,@PathVariable String cbCreateur,@PathVariable int coNo) {
        fCollaborateurDAO.modifCollaborateur (FormatText.getString(coNom),FormatText.getString(coPrenom),codePostal,FormatText.getString(coFonction),FormatText.getString(coAdresse),FormatText.getString(coComplement),FormatText.getString(coVille),FormatText.getString(coCodeRegion),FormatText.getString(coPays),coService
										,coVendeur,coCaissier,coAcheteur,coTelephone,coTelecopie,coEmail,coReceptionnaire,coChargeRecouvr,cbCreateur,coNo);
        
    }


    @GetMapping(value = "/maj/nom={nom}&valeur={valeur}&cbMarq={cbMarq}&cbCreateur={cbCreateur}")
    public void maj(@PathVariable String nom,@PathVariable String valeur,@PathVariable BigDecimal cbMarq,@PathVariable String cbCreateur) {
        fCollaborateurDAO.maj(nom, FormatText.getString(valeur),cbMarq,cbCreateur);
    }

}

