package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.dao.FDocEnteteDAO;
import com.server.itsolution.entities.FDocEntete;
import com.server.itsolution.entities.FEcritureA;
import com.server.itsolution.entities.FormatText;
import com.server.itsolution.entities.SaisieAnalytique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fdocentete")
public class FDocEnteteRessource {

    @Autowired
    FDocEnteteDAO fDocEnteteDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fDocEnteteDAO.getAll();
        fDocEnteteDAO.getDelai();
        return null;
    }

    @GetMapping(value = "/document&cbMarq={cbMarq}")
    public Object getFDocEntete(@PathVariable BigDecimal cbMarq) {
        return fDocEnteteDAO.getFDocEnteteJSon(cbMarq);
    }

    @GetMapping(value = "/transBLFacture&cbMarq={cbMarq}&conserv={conserv}&typeTrans={typeTrans}&reference={reference}&date={date}&protNo={protNo}")
    public Object transBLFacture(@PathVariable BigDecimal cbMarq,@PathVariable int conserv,@PathVariable int typeTrans,@PathVariable String reference,@PathVariable String date,@PathVariable int protNo)
    {
        return fDocEnteteDAO.transBLFacture(cbMarq,conserv,typeTrans,reference,date,protNo);
    }

    @GetMapping(value = "/setDoModif&cbMarq={cbMarq}")
    public int setDoModif(@PathVariable BigDecimal cbMarq) {
        return fDocEnteteDAO.setDoModif(cbMarq);
    }

    @GetMapping(value = "/journeeCloture&date={date}&caNo={caNo}")
    public int journeeCloture(@PathVariable String date,@PathVariable int caNo) {
        return fDocEnteteDAO.journeeCloture(date,caNo);
    }

    @GetMapping(value = "/getDateEcgetTiersheance&ctNum={ctNum}&date={date}")
    public List<Object> getDateEcgetTiersheance(@PathVariable String ctNum,@PathVariable String date) {
        return fDocEnteteDAO.getDateEcgetTiersheance(ctNum,date);
    }

    @GetMapping(value = "/resteARegler&cbMarq={cbMarq}&avance={avance}")
    public List<Object> resteARegler(@PathVariable BigDecimal cbMarq ,@PathVariable double avance) {
        return fDocEnteteDAO.resteARegler(cbMarq,avance);
    }

    @GetMapping(value = "/getDateEcgetTiersheanceSelect&mrNo={mrNo}&nReglement={nReglement}&date={date}")
    public List<Object> getDateEcgetTiersheanceSelect(@PathVariable int mrNo,@PathVariable int nReglement,@PathVariable String date) {
        return fDocEnteteDAO.getDateEcgetTiersheanceSelect(nReglement,date,mrNo);
    }


    @GetMapping(value = "/avanceDoPiece&cbMarq={cbMarq}")
    public double avanceDoPiece(@PathVariable BigDecimal cbMarq) {
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarq);
        return fDocEntete.getAvance();
    }
    @GetMapping(value = "/montantRegle&cbMarq={cbMarq}")
    public double montantRegle(@PathVariable BigDecimal cbMarq) {
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarq);
        return fDocEntete.getTtc();
    }
    @GetMapping(value = "/drRegle&cbMarq={cbMarq}")
    public int drRegle(@PathVariable BigDecimal cbMarq) {
        return fDocEnteteDAO.getdrRegle(cbMarq);
    }

    @GetMapping(value = "/listeLigneFacture&cbMarq={cbMarq}")
    public List<Object> listeLigneFacture(@PathVariable BigDecimal cbMarq) {
        FDocEntete fDocEntete = fDocEnteteDAO.getFDocEntete(cbMarq);
        return fDocEnteteDAO.getListeLigne(fDocEntete);
    }

    @GetMapping(value = "/getZFactReglSuppr&cbMarq={cbMarq}")
    public int getZFactReglSuppr(@PathVariable BigDecimal cbMarq) {
        return fDocEnteteDAO.getZFactReglSuppr(cbMarq);
    }

    @GetMapping(value = "/getPiedPage&cbMarq={cbMarq}&typeFacture={typeFacture}&catCompta={catCompta}&catTarif={catTarif}")
    public String getPiedPage(@PathVariable BigDecimal cbMarq,@PathVariable String typeFacture,@PathVariable int catCompta,@PathVariable int catTarif) {
        return fDocEnteteDAO.getPiedPage(cbMarq,typeFacture,catCompta,catTarif);
    }


    @GetMapping(value = "/statutVente&type={type}")
    public List<Object> statutVente(@PathVariable String type) {
        return fDocEnteteDAO.statutVente(type);
    }

    @GetMapping(value = "/statutAchat&type={type}")
    public List<Object> statutAchat(@PathVariable String type) {
        return fDocEnteteDAO.statutAchat(type);
    }

    @GetMapping(value = "/getCbCreateurName&cbMarq={cbMarq}")
    public String getCbCreateurName(@PathVariable BigDecimal cbMarq) {
        return fDocEnteteDAO.getCbCreateurName(cbMarq);
    }

    @GetMapping(value = "/getEnteteDocument&typeFac={typeFac}&doSouche={doSouche}")
    public String getEnteteDocument(@PathVariable String typeFac,@PathVariable int doSouche) {
        return fDocEnteteDAO.getEnteteDocument(typeFac,doSouche);
    }

    @GetMapping(value = "/maj/nom={nom}&valeur={valeur}&cbMarq={cbMarq}&cbCreateur={cbCreateur}")
    public void getEnteteDocument(@PathVariable String nom,@PathVariable String valeur,@PathVariable BigDecimal cbMarq,@PathVariable String cbCreateur) {
        fDocEnteteDAO.maj(nom, FormatText.getString(valeur),cbMarq,cbCreateur);
    }

    @GetMapping(value = "/majEnteteComptable&doType={doType}&doDomaine={doDomaine}&doPiece={doPiece}&doTypeCible={doTypeCible}")
    public void majEnteteComptable(@PathVariable int doType,@PathVariable int doDomaine,@PathVariable String doPiece,@PathVariable int doTypeCible) {
        fDocEnteteDAO.majEnteteComptable(doType,doDomaine,doPiece,doTypeCible);
    }

    @GetMapping(value = "/ajoutEntete&protNo={protNo}&doPiece={doPiece}&typeFacture={typeFacture}&doDate={doDate}&doSouche={doSouche}&caNum={caNum}&ctNum={ctNum}&machineName={machineName}&doCoord01={doCoord01}&doCoord02={doCoord02}&doCoord03={doCoord03}&doCoord04={doCoord04}&doStatut={doStatut}&catTarif={catTarif}&catCompta={catCompta}&deNo={deNo}&caNo={caNo}&coNo={coNo}&reference={reference}&longitude={longitude}&latitude={latitude}")
    public Object ajoutEntete(@PathVariable int protNo,@PathVariable String doPiece,@PathVariable String typeFacture,@PathVariable String doDate,@PathVariable int doSouche,
                              @PathVariable String caNum,@PathVariable String ctNum,@PathVariable String machineName,@PathVariable String doCoord01,
                              @PathVariable String doCoord02,@PathVariable String doCoord03,@PathVariable String doCoord04,
                              @PathVariable int doStatut,@PathVariable int catTarif,@PathVariable int catCompta,@PathVariable int deNo,
                              @PathVariable int caNo,@PathVariable int coNo,@PathVariable String reference,@PathVariable double longitude,@PathVariable double latitude) {

        if(typeFacture.equals("Transfert_detail"))
            return fDocEnteteDAO.ajoutEnteteTrsftDetail(FormatText.getString(doPiece),FormatText.getString(typeFacture),latitude,longitude,FormatText.getString(reference),doDate,deNo,coNo,ctNum,protNo);
        return fDocEnteteDAO.ajoutEntete(protNo,FormatText.getString(doPiece),FormatText.getString(typeFacture),doDate,doSouche,"",FormatText.getString(caNum),FormatText.getString(ctNum),
                FormatText.getString(machineName),doCoord01,doCoord02,doCoord03,doCoord04,doStatut,catTarif,catCompta,deNo,caNo,coNo,FormatText.getString(reference),longitude,latitude);

    }


    @GetMapping(value = "/getLigneTransfertDetail&doPiece={doPiece}")
    public Object getLigneTransfertDetail(@PathVariable String doPiece) {
        return fDocEnteteDAO.getLigneTransfertDetail(doPiece);
    }

    @GetMapping(value = "/enteteTransfertDetail&cbMarq={cbMarq}")
    public Object enteteTransfertDetail(@PathVariable BigDecimal cbMarq) {
        return fDocEnteteDAO.enteteTransfertDetail(cbMarq);
    }

    @GetMapping(value = "/testCorrectLigneA&cbMarq={cbMarq}")
    public List<Object> testCorrectLigneA(@PathVariable BigDecimal cbMarq){
        return fDocEnteteDAO.testCorrectLigneA(cbMarq);
    }

    @GetMapping(value = "/saisieComptable&cbMarq={cbMarq}&trans={trans}")
    public ArrayList<SaisieAnalytique> saisieComptable(@PathVariable BigDecimal cbMarq,@PathVariable int trans) {
        return fDocEnteteDAO.saisieComptable(cbMarq,trans);
    }

    @GetMapping(value = "/saisieComptableCaisse&cbMarq={cbMarq}&trans={trans}")
    ArrayList<SaisieAnalytique> saisieComptableCaisse(@PathVariable BigDecimal cbMarq,@PathVariable int trans){
        return fDocEnteteDAO.saisieComptableCaisse(cbMarq,trans);
    }

    @GetMapping(value = "/saisieCompteAnal&cbMarq={cbMarq}&insert={insert}")
    ArrayList<FEcritureA> saisieCompteAnal(@PathVariable BigDecimal cbMarq,@PathVariable int insert){
        return fDocEnteteDAO.saisieCompteAnal(cbMarq,insert);
    }

    @GetMapping(value = "/getEnteteTable&typeFac={typeFac}&doSouche={doSouche}")
    public String getEnteteTable(@PathVariable String typeFac,@PathVariable int doSouche) {
        fDocEnteteDAO.getEnteteTable(typeFac,doSouche);
        return null;
    }

    @GetMapping(value = "/getListeFacture&doProvenance={doProvenance}&doType={doType}&doDomaine={doDomaine}&deNo={deNo}&dateDeb={dateDeb}&dateFin={dateFin}&client={client}&protNo={protNo}")
    public Object getListeFacture(@PathVariable int doProvenance,@PathVariable int doType,@PathVariable int doDomaine,@PathVariable int deNo,@PathVariable String dateDeb
            ,@PathVariable String dateFin,@PathVariable String client,@PathVariable int protNo) {
        List<Object> list = fDocEnteteDAO.getListeFacture(doProvenance,doType,doDomaine,deNo,dateDeb,dateFin,client,protNo);
        return list;
    }

    @GetMapping(value = "/getlisteEntree&client={client}&dateDeb={dateDeb}&dateFin={dateFin}")
    public Object getlisteEntree(@PathVariable String dateDeb,@PathVariable String dateFin,@PathVariable String client) {
        List<Object> list = fDocEnteteDAO.getlisteEntree(client,dateDeb,dateFin);
        return list;
    }

    @GetMapping(value = "/getlisteSortie&client={client}&dateDeb={dateDeb}&dateFin={dateFin}")
    public Object getlisteSortie(@PathVariable String dateDeb,@PathVariable String dateFin,@PathVariable String client) {
        List<Object> list = fDocEnteteDAO.getlisteSortie(client,dateDeb,dateFin);
        return list;
    }

    @GetMapping(value = "/getlisteTransfert&client={client}&dateDeb={dateDeb}&dateFin={dateFin}")
    public Object getlisteTransfert(@PathVariable String dateDeb,@PathVariable String dateFin,@PathVariable String client) {
        List<Object> list = fDocEnteteDAO.getlisteTransfert(client,dateDeb,dateFin);
        return list;
    }

    @GetMapping(value = "/getlisteTransfertConfirmation&dateDeb={dateDeb}&dateFin={dateFin}&doDomaine={doDomaine}&doType={doType}&protNo={protNo}&typeFac={typeFac}")
    public Object listeTransfertConfirmation(@PathVariable String dateDeb,@PathVariable String dateFin,@PathVariable int doDomaine,@PathVariable int doType,@PathVariable int protNo,@PathVariable String typeFac){
        List<Object> list = fDocEnteteDAO.listeTransfertConfirmation(dateDeb,dateFin,doDomaine,doType,protNo,typeFac);
        return list;
    }

    @GetMapping(value = "/getlisteTransfertDetail&client={client}&dateDeb={dateDeb}&dateFin={dateFin}")
    public Object getlisteTransfertDetail(@PathVariable String dateDeb,@PathVariable String dateFin,@PathVariable String client) {
        List<Object> list = fDocEnteteDAO.getlisteTransfertDetail(client,dateDeb,dateFin);
        return list;
    }


    @GetMapping(value = "/suppr_facture&cbMarq={cbMarq}&typeFacture={typeFacture}&protNo={protNo}")
    public void suppr_facture(@PathVariable BigDecimal cbMarq,@PathVariable String typeFacture,@PathVariable String protNo){
        fDocEnteteDAO.suppr_facture(cbMarq,typeFacture,protNo);
    }

    @GetMapping(value = "/getLigneFactureTransfert&cbMarq={cbMarq}")
    public Object getLigneFactureTransfert(@PathVariable BigDecimal cbMarq) {
        List<Object> list = fDocEnteteDAO.getLigneFactureTransfert(cbMarq);
        return list;
    }

    @GetMapping(value = "/doImprim&cbMarq={cbMarq}")
    public void doImprim(@PathVariable BigDecimal cbMarq) {
        fDocEnteteDAO.doImprim(cbMarq);
    }

    @GetMapping(value = "/isVisu&cbMarq={cbMarq}&protNo={protNo}&typeFacture={typeFacture}")
    public int isVisu(@PathVariable BigDecimal cbMarq,@PathVariable int protNo,@PathVariable String typeFacture) {
        return fDocEnteteDAO.isVisu(cbMarq,protNo,typeFacture);
    }

    @GetMapping(value = "/isModif&cbMarq={cbMarq}&protNo={protNo}&typeFacture={typeFacture}")
    public int isModif(@PathVariable BigDecimal cbMarq,@PathVariable int protNo,@PathVariable String typeFacture) {
        return fDocEnteteDAO.isModif(cbMarq,protNo,typeFacture);
    }

    @GetMapping(value = "/majCollaborateur&coNo={coNo}&cbMarq={cbMarq}")
    public void majCollaborateur(@PathVariable String coNo,@PathVariable BigDecimal cbMarq) {
        fDocEnteteDAO.majCollaborateur(coNo,cbMarq);
    }

    @GetMapping(value = "/majAffaire&caNum={caNum}&cbMarq={cbMarq}")
    public void majAffaire(@PathVariable String caNum,@PathVariable BigDecimal cbMarq) {
        fDocEnteteDAO.majAffaire(caNum,cbMarq);
    }

    @GetMapping(value = "/getFactureCORecouvrement&collab={collab}&ctNum={ctNum}")
    public List<Object> getFactureCORecouvrement(@PathVariable int collab,@PathVariable String ctNum) {
        return fDocEnteteDAO.getFactureCORecouvrement(collab,ctNum);
    }

    @GetMapping(value = "/regle&cbMarq={cbMarq}&typeFacture={typeFacture}&protNo={protNo}&valideRegle={valideRegle}&valideRegltImprime={valideRegltImprime}&montantAvance={montantAvance}&modeReglement={modeReglement}&dateReglt={dateReglt}&libReglt={libReglt}&dateEch={dateEch}")
    public int regle(  @PathVariable BigDecimal cbMarq,@PathVariable String typeFacture,@PathVariable int protNo
            ,@PathVariable int valideRegle,@PathVariable int valideRegltImprime,@PathVariable double montantAvance
            ,@PathVariable int modeReglement,@PathVariable String dateReglt,@PathVariable String libReglt,@PathVariable String dateEch
    )
    {
        fDocEnteteDAO.regle(cbMarq,FormatText.getString(typeFacture),protNo,valideRegle,valideRegltImprime,montantAvance,modeReglement,dateReglt, FormatText.getString(libReglt),dateEch);
        return 1;
    }

    @GetMapping(value = "/AjoutMvtStock&typeFacture={typeFacture}&latitude={latitude}&longitude={longitude}&doRef={doRef}&caNum={caNum}&doTiers={doTiers}&doDate={doDate}&deNo={deNo}&protNo={protNo}")
    public Object AjoutMvtStock(@PathVariable String typeFacture,@PathVariable double latitude,@PathVariable double longitude
            ,@PathVariable String doRef,@PathVariable String caNum,@PathVariable String doTiers
            ,@PathVariable String doDate,@PathVariable int deNo,@PathVariable int protNo){
        return fDocEnteteDAO.AjoutMvtStock(typeFacture,latitude,longitude, FormatText.getString( doRef),caNum,doTiers,doDate,deNo,protNo);
    }

    @GetMapping(value = "/getLigneFacture&cbMarq={cbMarq}")
    public List<Object> getLigneFacture(@PathVariable BigDecimal cbMarq){
        return fDocEnteteDAO.getLigneFacture(cbMarq);
    }

    @GetMapping(value = "/removeFacRglt&cbMarqEntete={cbMarqEntete}&rgNo={rgNo}")
    public void removeFacRglt(@PathVariable BigDecimal cbMarqEntete,@PathVariable BigDecimal rgNo) {
        fDocEnteteDAO.removeFacRglt(cbMarqEntete,rgNo);
    }

    @GetMapping(value = "/getLigneTransfert&cbMarq={cbMarq}")
    public List<Object> getLigneTransfert(@PathVariable BigDecimal cbMarq){
        return fDocEnteteDAO.getLigneTransfert(cbMarq);
    }


}

