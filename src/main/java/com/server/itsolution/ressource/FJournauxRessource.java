package com.server.itsolution.ressource;

import com.server.itsolution.dao.FDepotDAO;
import com.server.itsolution.dao.FJournauxDAO;
import com.server.itsolution.entities.FJournaux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fjournaux")
public class FJournauxRessource {

    @Autowired
    FJournauxDAO fJournauxDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        return fJournauxDAO.getAll();
    }

    @GetMapping(value = "/{joNum}")
    public List<Object> getAll(@PathVariable String joNum) {
        return  fJournauxDAO.getJournal(joNum);
    }

    @GetMapping(value = "/getJournaux&joSommeil={joSommeil}")
    public List<Object> getJournaux(@PathVariable int joSommeil) {
        return fJournauxDAO.getJournaux(joSommeil);
    }

    @GetMapping(value = "/getJournauxReglement&joSommeil={joSommeil}")
    public List<Object> getJournauxReglement(@PathVariable int joSommeil) {
        return fJournauxDAO.getJournauxReglement(joSommeil);
    }

    @GetMapping(value = "/getJournauxCount&joSommeil={joSommeil}")
    public List<Object> getJournauxCount(@PathVariable int joSommeil) {
        return fJournauxDAO.getJournauxCount(joSommeil);
    }

    @GetMapping(value = "/getJournauxSaufTotaux")
    public List<Object> getJournauxSaufTotaux() {
        return fJournauxDAO.getJournauxSaufTotaux();
    }

    @GetMapping(value = "/getECPiece&joNum={joNum}&jmDate={jmDate}")
    public List<Object> getECPiece(@PathVariable String joNum,@PathVariable String jmDate){
        return     fJournauxDAO.getECPiece(joNum,jmDate);
    }

    @GetMapping(value = "/getJournauxType/{joType}/{joSommeil}")
    public List<Object> getJournauxType(@PathVariable int joType,@PathVariable int joSommeil) {
        return fJournauxDAO.getJournauxType(joType,joSommeil);
    }

    @GetMapping(value = "/getJournauxSaisieSelect/ouvert={ouvert}&mois={mois}&joNum={journal}")
    public List<Object> getJournauxSaisieSelect(@PathVariable int ouvert,@PathVariable int mois,@PathVariable String journal) {
        return     fJournauxDAO.getJournauxSaisieSelect(ouvert,mois,journal);
    }

    @GetMapping(value = "/getJournauxSaisie/ouvert={ouvert}&nomMois={nomMois}&joNum={joNum}&annee={annee}")
    public List<Object> getJournauxSaisie(@PathVariable int ouvert,@PathVariable String nomMois,@PathVariable String joNum,@PathVariable int annee) {
           return     fJournauxDAO.getJournauxSaisie(ouvert,nomMois,joNum,annee);
    }

    @GetMapping(value = "/calculSoldeLettrage/listCbMarq={listCbMarq}")
    public List<Object> calculSoldeLettrage(@PathVariable String listCbMarq) {
        return     fJournauxDAO.calculSoldeLettrage(listCbMarq);
    }

    @GetMapping(value = "/headerSaisiJournal/anneeExercice={anneeExercice}&joNum={joNum}&position={position}")
    public List<Object> headerSaisiJournal(@PathVariable int anneeExercice,@PathVariable String joNum,@PathVariable int position) {
        return     fJournauxDAO.headerSaisiJournal(anneeExercice,joNum,position);
    }

    @GetMapping(value = "/getSaisieJournalExercice/joNum={joNum}&mois={mois}&annee={annee}&ctNum={ctNum}&dateDebut={dateDebut}&dateFin={dateFin}&lettrage={lettrage}&cgNum={cgNum}")
    public List<Object> getSaisieJournalExercice(@PathVariable String joNum,@PathVariable int mois,@PathVariable int annee,@PathVariable String ctNum,@PathVariable String dateDebut,@PathVariable String dateFin,@PathVariable int lettrage,@PathVariable String cgNum) {
        return     fJournauxDAO.getSaisieJournalExercice(joNum,mois,annee,ctNum,dateDebut,dateFin,lettrage,cgNum);
    }

    @GetMapping(value = "/getTotalJournal/joNum={joNum}&mois={mois}&annee={annee}&sens={sens}&ctNum={ctNum}&dateDebut={dateDebut}&dateFin={dateFin}&lettrage={lettrage}&cgNum={cgNum}")
    public List<Object> getTotalJournal(@PathVariable String joNum,@PathVariable int mois,@PathVariable int annee,@PathVariable int sens,@PathVariable String ctNum,@PathVariable String dateDebut,@PathVariable String dateFin,@PathVariable int lettrage,@PathVariable String cgNum) {
        return     fJournauxDAO.getTotalJournal(joNum,mois,annee,sens,ctNum,dateDebut,dateFin,lettrage,cgNum);
    }

    @GetMapping(value = "/getJournalLastDate/joNum={joNum}&mois={mois}&annee={annee}")
        public List<Object> getJournalLastDate(@PathVariable String joNum,@PathVariable int mois,@PathVariable int annee) {
        return     fJournauxDAO.getJournalLastDate(joNum,mois,annee);
    }

    @GetMapping(value = "/getJournalPiece/joNum={joNum}&mois={mois}&annee={annee}")
    public List<Object> getJournalPiece(@PathVariable String joNum,@PathVariable int mois,@PathVariable int annee) {
        return     fJournauxDAO.getJournalPiece(joNum,mois,annee);
    }

    @GetMapping(value = "/getLettrage/ctNum={ctNum}&dateDebut={dateDebut}&dateFin={dateFin}&cgNum={cgNum}")
    public List<Object> getLettrage(@PathVariable String ctNum,@PathVariable String cgNum,@PathVariable String dateDebut ,@PathVariable String dateFin){
        return     fJournauxDAO.getLettrage(ctNum,dateDebut,dateFin,cgNum);
//        return fJournauxDAO.getLettrage("POLARIS","2020-01-01","2020-12-31","");

    }

    @GetMapping(value = "/pointerEcriture/annuler={annuler}&listCbMarq={listCbMarq}&ecLettrage={ecLettrage}")
    public List<Object> pointerEcriture(@PathVariable int annuler,@PathVariable String listCbMarq,@PathVariable String ecLettrage) {
        return     fJournauxDAO.pointerEcriture(annuler,listCbMarq,ecLettrage);
    }

}

