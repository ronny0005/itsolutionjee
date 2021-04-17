package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCReglementDAO;
import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.dao.FDocEnteteDAO;
import com.server.itsolution.entities.FDocEntete;
import com.server.itsolution.entities.FormatText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.Format;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fcreglement")
public class FCReglementRessource {

    @Autowired
    FCReglementDAO fcReglementDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fcReglementDAO.getAll();
        return list;
    }

    @GetMapping(value = "/rgNo={rgNo}")
    public List<Object> getReglement(@PathVariable BigDecimal rgNo) {
        List<Object> list = fcReglementDAO.getReglement(rgNo);
        return list;
    }

    @GetMapping(value = "/remboursementRglt/rgNo={rgNo}&date={date}&montant={montant}")
    public void remboursementRglt(@PathVariable BigDecimal rgNo,@PathVariable String date,@PathVariable double montant)
    {
        fcReglementDAO.remboursementRglt(rgNo,date,montant);
    }

    @GetMapping(value = "/setDO_Modif&rgNo={rgNo}")
    public int setDO_Modif(@PathVariable BigDecimal rgNo) {
        return fcReglementDAO.setDO_Modif(rgNo);
    }

    @GetMapping(value = "/getReglementByClientFacture&cbMarq={cbMarq}")
    public List<Object> getReglementByClientFacture(@PathVariable BigDecimal cbMarq) {
        List<Object> list = fcReglementDAO.getReglementByClientFacture(cbMarq);
        return list;
    }

    @GetMapping(value = "/getMajAnalytique&dateDeb={dateDeb}&dateFin={dateFin}&caNum={caNum}&statut={statut}")
    public List<Object> getMajAnalytique(@PathVariable String dateDeb,@PathVariable String dateFin,@PathVariable String caNum,@PathVariable int statut) {
        List<Object> list = fcReglementDAO.getMajAnalytique(dateDeb,dateFin,caNum,statut);
        return list;
    }
	
    @GetMapping(value = "/setMajAnalytique&dateDeb={dateDeb}&dateFin={dateFin}&caNum={caNum}&cbCreateur={cbCreateur}")
    public void getMajAnalytique(@PathVariable String dateDeb,@PathVariable String dateFin,@PathVariable String caNum,@PathVariable String cbCreateur) {
        fcReglementDAO.setMajAnalytique(dateDeb,dateFin,caNum,cbCreateur);
    }
	
    @GetMapping(value = "/supprRgltAssocie&rgNo={rgNo}")
    public void supprRgltAssocie(@PathVariable BigDecimal rgNo) {
        fcReglementDAO.supprRgltAssocie(rgNo);
    }
	
    @GetMapping(value = "/supprReglement&rgNo={rgNo}")
    public void supprReglement(@PathVariable BigDecimal rgNo) {
        fcReglementDAO.supprReglement(rgNo);
    }

    @GetMapping(value = "/supprReglementTiers&mvtCaisse={mvtCaisse}&rgNo={rgNo}&protNo={protNo}")
    public void supprReglementTiers(@PathVariable boolean mvtCaisse,@PathVariable BigDecimal rgNo,@PathVariable int protNo){
        fcReglementDAO.supprReglementTiers(mvtCaisse,rgNo,protNo);
    }

    @GetMapping(value = "/insertF_ReglementVrstBancaire&rgNo={rgNo}&rgNoCache={rgNoCache}")
    public void insertF_ReglementVrstBancaire(@PathVariable BigDecimal rgNo,@PathVariable BigDecimal rgNoCache) {
        fcReglementDAO.insertF_ReglementVrstBancaire(rgNo,rgNoCache);
    }
	
	
    @GetMapping(value = "/deleteF_ReglementVrstBancaire&rgNo={rgNo}")
    public void deleteF_ReglementVrstBancaire(@PathVariable BigDecimal rgNo) {
        fcReglementDAO.deleteF_ReglementVrstBancaire(rgNo);
    }


    @GetMapping(value = "/insertMvtCaisse&rgMontant={rgMontant}&protNo={protNo}&caNum={caNum}&libelle={libelle}&rgTypeReg={rgTypeReg}&caNo={caNo}&cgNumBanque={cgNumBanque}&isModif={isModif}&rgDate={rgDate}&joNum={joNum}&caNoDest={caNoDest}&cgAnalytique={cgAnalytique}&rgTyperegModif={rgTyperegModif}&journalRec={journalRec}&rgNoDest={rgNoDest}")
    public void insertMvtCaisse(@PathVariable float rgMontant,@PathVariable int protNo,@PathVariable String caNum,@PathVariable String libelle,@PathVariable int rgTypeReg,@PathVariable int caNo
            ,@PathVariable String cgNumBanque,@PathVariable int isModif,@PathVariable String rgDate,@PathVariable String joNum,@PathVariable int caNoDest,@PathVariable int cgAnalytique
            ,@PathVariable int rgTyperegModif,@PathVariable  String journalRec,@PathVariable BigDecimal rgNoDest) {
            fcReglementDAO.insertMvtCaisse(rgMontant,protNo,caNum,FormatText.getString(libelle),rgTypeReg,caNo
                ,cgNumBanque,isModif,rgDate,joNum,caNoDest,cgAnalytique
                ,rgTyperegModif,journalRec,rgNoDest);
    }

    @GetMapping(value = "/modifReglementTiers&protNo={protNo}&coNo={coNo}&bonCaisse={bonCaisse}&rgNo={rgNo}&rgLibelle={rgLibelle}&montant={montant}&rgDate={rgDate}&joNum={joNum}&ctNum={ctNum}")
    public void modifReglementTiers(@PathVariable int protNo,@PathVariable String coNo,@PathVariable int bonCaisse,@PathVariable BigDecimal rgNo,@PathVariable String rgLibelle,@PathVariable float montant,@PathVariable String rgDate,@PathVariable String joNum,@PathVariable String ctNum){
        fcReglementDAO.modifReglementTiers(protNo,coNo,bonCaisse,rgNo,rgLibelle,montant,rgDate, FormatText.getString(joNum),FormatText.getString(ctNum));
    }

    @GetMapping(value = "/addEcheance&protNo={protNo}&rgNo={rgNo}&typeRegl={typeRegl}&cbMarqEntete={cbMarqEntete}&montant={montant}")
    public void addEcheance(@PathVariable int protNo,@PathVariable BigDecimal rgNo,@PathVariable String typeRegl,@PathVariable BigDecimal cbMarqEntete,@PathVariable float montant) {
        fcReglementDAO.addEcheance(protNo,rgNo,typeRegl,cbMarqEntete,montant);
    }

    @GetMapping(value = "/updateImpute&rgNo={rgNo}")
    public void updateImpute(@PathVariable BigDecimal rgNo) {
        fcReglementDAO.updateImpute(rgNo);
    }

    @GetMapping(value = "/journeeCloture&date={date}&caNo={caNo}")
    public int journeeCloture(@PathVariable String date,@PathVariable int caNo) {
        return fcReglementDAO.journeeCloture(date,caNo);
    }
    @GetMapping(value = "/insertCaNum&rgNo={rgNo}&caNum={caNum}")
    public void insertCaNum(@PathVariable BigDecimal rgNo,@PathVariable String caNum) {
        fcReglementDAO.insertCaNum(rgNo,caNum);
    }

    @GetMapping(value = "/insertZ_RGLT_BONDECAISSE&rgNo={rgNo}&rgNoLier={rgNoLier}")
    public void insertZ_RGLT_BONDECAISSE(@PathVariable BigDecimal rgNo,@PathVariable BigDecimal rgNoLier) {
        fcReglementDAO.insertZ_RGLT_BONDECAISSE(rgNo,rgNoLier);
    }
	
    @GetMapping(value = "/getFactureRGNo&rgNo={rgNo}")
    public List<Object> getFactureRGNo(@PathVariable BigDecimal rgNo) {
        return fcReglementDAO.getFactureRGNo(rgNo);
    }

    @GetMapping(value = "/addReglement&protNo={protNo}&joNum={joNum}&rgNoLier={rgNoLier}&ctNum={ctNum}&caNo={caNo}&bonCaisse={bonCaisse}&libelle={libelle}&caissier={caissier}&date={date}&modeReglementRec={modeReglementRec}&montant={montant}&impute={impute}&rgType={rgType}&afficheData={afficheData}&typeRegl={typeRegl}")
    public Object addReglement(@PathVariable int protNo,@PathVariable String joNum,@PathVariable int rgNoLier,@PathVariable String ctNum
                            ,@PathVariable int caNo,@PathVariable int bonCaisse ,@PathVariable String libelle ,@PathVariable String caissier
            ,@PathVariable String date,@PathVariable String modeReglementRec,@PathVariable double montant,@PathVariable int impute
            ,@PathVariable int rgType,@PathVariable boolean afficheData,@PathVariable String typeRegl)
    {
        try {
            return fcReglementDAO.addReglement( protNo, URLDecoder.decode(joNum, String.valueOf(StandardCharsets.UTF_8)),  rgNoLier, URLDecoder.decode(ctNum, String.valueOf(StandardCharsets.UTF_8)) ,  caNo,  bonCaisse , URLDecoder.decode(libelle, String.valueOf(StandardCharsets.UTF_8)) , caissier
                                                , date,modeReglementRec, montant,  impute,rgType, afficheData, typeRegl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/listeReglementCaisse&dateDeb={dateDeb}&dateFin={dateFin}&caNo={caNo}&type={type}&protNo={protNo}")
    public List<Object> listeReglementCaisse(@PathVariable String dateDeb,@PathVariable String dateFin,@PathVariable int caNo,@PathVariable int type,@PathVariable int protNo){
        return fcReglementDAO.listeReglementCaisse(dateDeb,dateFin,caNo,type,protNo);
    }

    @GetMapping(value = "/getReglementByClient&dateDeb={dateDeb}&dateFin={dateFin}&rgImpute={rgImpute}&ctNum={ctNum}&collab={collab}&nReglement={nReglement}&caNo={caNo}&coNoCaissier={coNoCaissier}&rgType={rgType}&protNo={protNo}")
    public List<Object> getReglementByClient(@PathVariable String dateDeb,@PathVariable String dateFin,@PathVariable int rgImpute,@PathVariable String ctNum,@PathVariable int collab,@PathVariable int nReglement,@PathVariable int caNo,@PathVariable int coNoCaissier,@PathVariable int rgType,@PathVariable int protNo){
        return fcReglementDAO.getReglementByClient(dateDeb,dateFin,rgImpute,ctNum,collab,nReglement,caNo,coNoCaissier,rgType,protNo);
    }

	@GetMapping(value = "/regle&cbMarq={cbMarq}&typeFacture={typeFacture}&protNo={protNo}&valideRegle={valideRegle}&valideRegltImprime={valideRegltImprime}&mttAvance={mttAvance}&montantTotal={montantTotal}&modeReglement={modeReglement}&dateRglt={dateRglt}&libRglt={libRglt}")
    public String regle(  @PathVariable BigDecimal cbMarq,@PathVariable String typeFacture,@PathVariable int protNo,@PathVariable int valideRegle
                        ,@PathVariable int valideRegltImprime,@PathVariable float mttAvance,@PathVariable float montantTotal,@PathVariable int modeReglement
                        ,@PathVariable String dateRglt,@PathVariable String libRglt) {
            libRglt = FormatText.getString(libRglt);
            mttAvance = Float.parseFloat(FormatText.getString(String.valueOf(mttAvance)));
            montantTotal = Float.parseFloat(FormatText.getString(String.valueOf(montantTotal)));
        return fcReglementDAO.regle(cbMarq,typeFacture,protNo,valideRegle,valideRegltImprime,mttAvance,montantTotal,modeReglement,dateRglt,libRglt);
    }
}

