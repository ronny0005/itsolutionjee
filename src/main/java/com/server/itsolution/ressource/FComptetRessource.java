package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.dao.FComptetDAO;
import com.server.itsolution.entities.FormatText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fcomptet")
public class FComptetRessource {

    @Autowired
    FComptetDAO fComptetDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fComptetDAO.getAll();
        return list;
    }
	
    @GetMapping(value = "/allClientsSelect")
    public List<Object> allClientsSelect() {
        List<Object> list = fComptetDAO.allClientsSelect();
        return list;
    }
	
    @GetMapping(value = "/TiersDoublons")
    public List<Object> TiersDoublons() {
        List<Object> list = fComptetDAO.TiersDoublons();
        return list;
    }
	
	
    @GetMapping(value = "/queryListeClient&ctType={ctType}&ctSommeil={ctSommeil}&searchString={searchString}&orderBy={orderBy}&orderType={orderType}&start={start}&length={length}")
    public List<Object> queryListeClient(@PathVariable int ctType,@PathVariable int ctSommeil,@PathVariable String searchString,@PathVariable String orderBy,@PathVariable String orderType,@PathVariable String start,@PathVariable String length) {
        List<Object> list = fComptetDAO.queryListeClient(ctType,ctSommeil,searchString,orderBy,orderType,start,length);
        return list;
    }

    @GetMapping(value = "/getDepotClient&deNo={deNo}&ctType={ctType}")
    public List<Object> getDepotClient(@PathVariable int deNo,@PathVariable int ctType) {
        return fComptetDAO.getDepotClient(deNo,ctType);
    }

    @GetMapping(value = "/remplacementTiers&ctNumNouveau={ctNumNouveau}&ctNumAncien={ctNumAncien}")
    public List<Object> remplacementTiers(@PathVariable String ctNumNouveau,@PathVariable String ctNumAncien) {
        List<Object> list = fComptetDAO.remplacementTiers(ctNumNouveau,ctNumAncien);
        return list;
    }



	
    @GetMapping(value = "/allFournisseurSelect")
    public List<Object> allFournisseurSelect() {
        List<Object> list = fComptetDAO.allFournisseurSelect();
        return list;
    }

    @GetMapping(value = "/modifClientUpdateCANum&ctNum={ctNum}&caNum={caNum}")
    public void modifClientUpdateCANum(@PathVariable String ctNum,@PathVariable String caNum) {
        fComptetDAO.modifClientUpdateCANum(ctNum,caNum);
    }

    @GetMapping(value = "/majMRNo&ctNum={ctNum}&mrNo={mrNo}")
    public void majMRNo(@PathVariable String ctNum,@PathVariable int mrNo) {
        fComptetDAO.majMRNo(ctNum,mrNo);
    }

    @GetMapping(value = "/allFournisseurSelect&type={type}")
    public List<Object> getCodeAuto(@PathVariable String type) {
        List<Object> list = fComptetDAO.getCodeAuto(type);
        return list;
    }
	
    @GetMapping(value = "/getFLivraisonByCTNum&ctNum={ctNum}")
    public int getFLivraisonByCTNum(@PathVariable String ctNum) {
        return fComptetDAO.getFLivraisonByCTNum(ctNum);
    }
	
    @GetMapping(value = "/tiersByCTIntitule&ctIntitule={ctIntitule}")
    public List<Object> tiersByCTIntitule(@PathVariable String ctIntitule) {
        List<Object> list = fComptetDAO.tiersByCTIntitule(FormatText.getString(ctIntitule));
        return list;
    }
	
	
    @GetMapping(value = "/createClientMin&ctNum={ctNum}&ctIntitule={ctIntitule}&ctType={ctType}&cgNumPrinc={cgNumPrinc}&ctAdresse={ctAdresse}&ctCodePostal={ctCodePostal}&ctVille={ctVille}&ctCodeRegion={ctCodeRegion}&ctIdentifiant={ctIdentifiant}&ctSiret={ctSiret}&coNo={coNo}&nCatTarif={nCatTarif}&nCatCompta={nCatCompta}&deNo={deNo}&caNum={caNum}&ctTelephone={ctTelephone}&mrNo={mrNo}&cbCreateur={cbCreateur}")
    public void createClientMin(@PathVariable String ctNum,@PathVariable String ctIntitule,@PathVariable String ctType,@PathVariable String cgNumPrinc,@PathVariable String ctAdresse,@PathVariable String ctCodePostal,@PathVariable String ctVille,@PathVariable String ctCodeRegion,@PathVariable String ctIdentifiant,@PathVariable String ctSiret,@PathVariable int coNo
								,@PathVariable int nCatTarif,@PathVariable int nCatCompta,@PathVariable int deNo,@PathVariable String caNum,@PathVariable String ctTelephone,@PathVariable int mrNo,@PathVariable String cbCreateur) {
        fComptetDAO.createClientMin(FormatText.getString(ctNum),FormatText.getString(ctIntitule),ctType,FormatText.getString(cgNumPrinc),FormatText.getString(ctAdresse),ctCodePostal,FormatText.getString(ctVille),FormatText.getString(ctCodeRegion),FormatText.getString(ctIdentifiant),FormatText.getString(ctSiret),coNo,nCatTarif,nCatCompta,deNo,FormatText.getString(caNum),FormatText.getString(ctTelephone),mrNo,FormatText.getString(cbCreateur));
        }


    @GetMapping(value = "/ctNum={tiers}")
    public List<Object> getTiers(@PathVariable String tiers) {
        return fComptetDAO.getF_ComptetJSON(tiers);
    }

    @GetMapping(value = "/allClientShort&sommeil={sommeil}")
    public Object allClientShort(@PathVariable int sommeil) {
        List<Object> list = fComptetDAO.allClientShort(sommeil);
        return list;
    }

    @GetMapping(value = "/allFournisseurShort&sommeil={sommeil}")
    public Object allFournisseurShort(@PathVariable int sommeil) {
        List<Object> list = fComptetDAO.allFournisseurShort(sommeil);
        return list;
    }

    @GetMapping(value = "/allTiersMax")
    public Object allTiersMax() {
        return fComptetDAO.allTiersMax();
    }

    @GetMapping(value = "/getModeleReglementCount")
    public Object getModeleReglementCount() {
        return fComptetDAO.getModeleReglementCount();
    }

    @GetMapping(value = "/getTiersByNumIntitule&typeFacture={typeFacture}&term={term}&all={all}")
    public Object getTiersByNumIntituleListe(@PathVariable String typeFacture,@PathVariable String term,@PathVariable int all) {
        List<Object> list = fComptetDAO.getTiersByNumIntituleListe(term,typeFacture,all);
        return list;
    }


	
    @GetMapping(value = "/rafraichir_listeClient&typeFacture={typeFacture}")
    public List<Object> rafraichir_listeClient(@PathVariable String typeFacture) {
        return fComptetDAO.rafraichir_listeClient(typeFacture);
    }

    @GetMapping(value = "/getTiersByNumIntituleSearch&intitule={intitule}&type={type}&ctSommeil={ctSommeil}")
    public List<Object> getTiersByNumIntituleSearch(@PathVariable String intitule,@PathVariable int type,@PathVariable int ctSommeil) {
        return fComptetDAO.getTiersByNumIntituleSearch(intitule,type,ctSommeil);
    }

    @GetMapping(value = "/maj/nom={nom}&valeur={valeur}&cbMarq={cbMarq}&cbCreateur={cbCreateur}")
    public void getEnteteDocument(@PathVariable String nom, @PathVariable String valeur, @PathVariable BigDecimal cbMarq, @PathVariable String cbCreateur) {
        fComptetDAO.maj(nom, FormatText.getString(valeur),cbMarq,cbCreateur);
    }


}

