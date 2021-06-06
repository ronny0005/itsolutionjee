package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.dao.FDocLigneDAO;
import com.server.itsolution.dao.FProtectioncialDAO;
import com.server.itsolution.entities.FDocLigne;
import com.server.itsolution.entities.FormatText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fdocligne")
public class FDocLigneRessource {

    @Autowired
    FDocLigneDAO fDocLigneDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fDocLigneDAO.getAll();
        return list;
    }
    @GetMapping(value = "/cbMarq={cbMarq}")
    public Object getFDocligneJSON(@PathVariable BigDecimal cbMarq) {
        return fDocLigneDAO.getFDocligneJSON(cbMarq);
    }
    @GetMapping(value = "/initRemise&cbMarq={cbMarq}")
    public Object initRemise(@PathVariable BigDecimal cbMarq) {
        FDocLigne fDocLigne = fDocLigneDAO.getFDocLigne(cbMarq);
        return fDocLigneDAO.initRemise(fDocLigne);
    }

    @GetMapping(value = "/getPrixClientHT&arRef={arRef}&catCompta={catCompta}&catTarif={catTarif}&prix={prix}&rem={rem}&qte={qte}&fournisseur={fournisseur}")
    public Object getPrixClientHT(@PathVariable String arRef,@PathVariable int catCompta,@PathVariable int catTarif,@PathVariable double prix,@PathVariable double rem,
                                  @PathVariable double qte,@PathVariable int fournisseur) {
        return fDocLigneDAO.getPrixClientHTJSON(arRef,catCompta,catTarif,prix,rem,qte,fournisseur);
    }

    @GetMapping(value = "/ajoutLigneTransfert&qte={qte}&prix={prix}&typeFacture={typeFacture}&cbMarq={cbMarq}&cbMarqEntete={cbMarqEntete}&protNo={protNo}&acte={acte}&arRef={arRef}&machineName={machineName}")
    public Object ajoutLigneTransfert(@PathVariable double qte,@PathVariable double prix,@PathVariable String typeFacture,@PathVariable BigDecimal cbMarq,@PathVariable BigDecimal cbMarqEntete,@PathVariable int protNo,@PathVariable String acte,@PathVariable String arRef,@PathVariable String machineName)
    {
        return fDocLigneDAO.ajoutLigneTransfert(qte,prix,typeFacture,cbMarq,cbMarqEntete,protNo,acte,FormatText.getString(arRef),FormatText.getString(machineName));
    }

    @GetMapping(value = "/ajoutLigneTransfertDetail&qte={qte}&prix={prix}&qteDest={qteDest}&prixDest={prixDest}&cbMarq={cbMarq}&cbMarqEntete={cbMarqEntete}&protNo={protNo}&acte={acte}&arRef={arRef}&arRefDest={arRefDest}&machineName={machineName}")
    public Object ajoutLigneTransfertDetail(@PathVariable double qte,@PathVariable double prix,@PathVariable double qteDest,@PathVariable double prixDest,@PathVariable BigDecimal cbMarq,@PathVariable BigDecimal cbMarqEntete,@PathVariable int protNo,@PathVariable String acte,@PathVariable String arRef,@PathVariable String arRefDest,@PathVariable String machineName)
    {
        return fDocLigneDAO.ajoutLigneTransfertDetail(qte,prix,qteDest,prixDest,cbMarqEntete,protNo,acte,FormatText.getString(arRef),FormatText.getString(arRefDest),FormatText.getString(machineName));
    }

    @GetMapping(value = "/getcbCreateurName&cbMarq={cbMarq}")
    public String getCbCreateurName(@PathVariable BigDecimal cbMarq) {
        return fDocLigneDAO.getCbCreateurName(cbMarq);
    }

    @GetMapping(value = "/getLigneFacture&cbMarq={cbMarq}")
    public List<Object> getLigneFacture(@PathVariable BigDecimal cbMarq) {
        return fDocLigneDAO.getLigneFacture(cbMarq);
    }
    @GetMapping(value = "/getCbMarqEntete&cbMarq={cbMarq}")
    public BigDecimal getCbMarqEntete(@PathVariable BigDecimal cbMarq) {
        return fDocLigneDAO.getCbMarqEntete(cbMarq);
    }

    @GetMapping(value = "/verifSupprAjout&cbMarq={cbMarq}")
    public Object verifSupprAjout(@PathVariable BigDecimal cbMarq) {
        return fDocLigneDAO.verifSupprAjout(cbMarq);
    }

    @GetMapping(value = "/maj/nom={nom}&valeur={valeur}&cbMarq={cbMarq}&cbCreateur={cbCreateur}")
    public void majLigne(@PathVariable String nom,@PathVariable String valeur,@PathVariable BigDecimal cbMarq,@PathVariable String cbCreateur) {
        fDocLigneDAO.maj(nom, FormatText.getString(valeur),cbMarq,cbCreateur);
    }


    @GetMapping(value = "/delete&cbMarq={cbMarq}")
    public void deleteLigne(@PathVariable BigDecimal cbMarq) {
        fDocLigneDAO.deleteLigne(cbMarq);
    }

    @GetMapping(value = "getPrixClientAch&arRef={arRef}&catCompta={catCompta}&catTarif={catTarif}&ctNum={ctNum}")
    public List<Object> getPrixClientAch(@PathVariable String arRef,@PathVariable int catCompta,@PathVariable int catTarif,@PathVariable String ctNum){
        return fDocLigneDAO.getPrixClientAch(arRef,catCompta,catTarif,ctNum);
    }

    //http://localhost:8083/rest/fdocligne/ajoutLigne/0/20/1/LGR-34217/112156/Vente/0/1500/%20/%20/ajout/%20/
    @GetMapping(value = "/ajoutLigne&cbMarq={cbMarq}&protNo={protNo}&dlQte={dlQte}&arRef={arRef}&cbMarqEntete={cbMarqEntete}&typeFacture={typeFacture}&catTarif={catTarif}&dlPrix={dlPrix}&dlRemise={dlRemise}&machineName={machineName}&acte={acte}&entete_prev={entete_prev}&depotLigne={depotLigne}")
    public Object ajoutLigne(@PathVariable BigDecimal cbMarq, @PathVariable int protNo, @PathVariable double dlQte, @PathVariable String arRef, @PathVariable BigDecimal cbMarqEntete, @PathVariable String typeFacture,
                             @PathVariable int catTarif, @PathVariable double dlPrix, @PathVariable String dlRemise, @PathVariable String machineName, @PathVariable String acte, @PathVariable String entete_prev,@PathVariable int depotLigne) {
            return fDocLigneDAO.ajoutLigne(cbMarq,protNo,dlQte, FormatText.getString(arRef),cbMarqEntete,typeFacture,catTarif,dlPrix, FormatText.getString(dlRemise),
                    FormatText.getString(machineName),FormatText.getString(acte),FormatText.getString(entete_prev),depotLigne);
    }


    @GetMapping(value = "/supprLigneFacture&cbMarq={cbMarq}&cbMarqSec={cbMarqSec}&typeFacture={typeFacture}&protNo={protNo}")
    public Object supprLigneFacture(@PathVariable BigDecimal cbMarq,@PathVariable BigDecimal cbMarqSec,@PathVariable String typeFacture,@PathVariable int protNo){
        if(typeFacture.equals("Sortie") || typeFacture.equals("Entree"))
            return fDocLigneDAO.supprMvt(cbMarq,String.valueOf(protNo),typeFacture);
        else
        if(typeFacture.equals("Transfert"))
            return fDocLigneDAO.supprLigneTransfert(cbMarq,cbMarqSec,typeFacture,protNo);
        else if(typeFacture.equals("Transfert_detail"))
            return fDocLigneDAO.supprTransfertDetail(cbMarq,cbMarqSec,String.valueOf(protNo),typeFacture);
        else
            return fDocLigneDAO.supprLigneFacture(cbMarq,typeFacture,protNo);
    }

}

