package com.server.itsolution.ressource;

import com.server.itsolution.dao.FArticleDAO;
import com.server.itsolution.entities.FormatText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.Format;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/farticle")
public class FArticleRessource {

    @Autowired
    FArticleDAO fArticleDAO;

    @GetMapping(value = "/all&intitule={intitule}&top={top}&sommeil={sommeil}&arPublie={arPublie}&rechEtat={rechEtat}")
    public List<Object> getAll(@PathVariable String intitule, @PathVariable int top, @PathVariable int sommeil, @PathVariable int arPublie,@PathVariable String rechEtat) {
        List<Object> list = fArticleDAO.getAll(intitule, top, sommeil, arPublie,rechEtat);
        return list;
    }

    @GetMapping(value = "/getF_ArticleJSON&arRef={arRef}")
    public List<Object> getF_ArticleJSON(@PathVariable String arRef) {
        List<Object> list = fArticleDAO.getF_ArticleJSON(FormatText.getString(arRef));
        return list;
    }

    @GetMapping(value = "/insertFArtCompta/cbMarq={cbMarq}&arRef={arRef}&acpType={acpType}&acpChamp={acpChamp}&val={val}&champ={champ}&acpTypeFacture={acpTypeFacture}&protNo={protNo}")
    public void insertFArtCompta(@PathVariable BigDecimal cbMarq,@PathVariable String arRef,@PathVariable int acpType,@PathVariable int acpChamp,@PathVariable String val,@PathVariable String champ,@PathVariable int acpTypeFacture,@PathVariable String protNo) {
        if(cbMarq.equals(BigDecimal.ZERO)) {
            fArticleDAO.insertFArtCompta(arRef,acpType,acpChamp,val,champ,acpTypeFacture,protNo);
        }
        else
            fArticleDAO.majFArtCompta(cbMarq,val,champ);
    }

    @GetMapping(value = "/getShortList")
    public List<Object> getShortList() {
        List<Object> list = fArticleDAO.getShortList();
        return list;
    }

    @GetMapping(value = "/getArticleAndDepot&arRef={arRef}")
    public List<Object> getArticleAndDepot(@PathVariable String arRef) {
        List<Object> list = fArticleDAO.getArticleAndDepot(arRef);
        return list;
    }

    @GetMapping(value = "/getCatComptaByArRef&arRef={arRef}&acpChamp={acpChamp}&acpType={acpType}")
    public List<Object> getCatComptaByArRef(@PathVariable String arRef, @PathVariable int acpChamp, @PathVariable int acpType) {
        return fArticleDAO.getCatComptaByArRef(arRef, acpChamp, acpType);
    }

    @GetMapping(value = "/detailConditionnement&arRef={arRef}&tcRefCf={tcRefCf}")
    public List<Object> detailConditionnement(@PathVariable String arRef, @PathVariable String tcRefCf) {
        List<Object> list = fArticleDAO.detailConditionnement(arRef, tcRefCf);
        return list;
    }

    @GetMapping(value = "/articleDoublons")
    public List<Object> articleDoublons() {
        List<Object> list = fArticleDAO.articleDoublons();
        return list;
    }

    @GetMapping(value = "/updateF_ArtStockBorne&asQteMini={asQteMini}&asQteMaxi={asQteMaxi}&cbCreateur={cbCreateur}&arRef={arRef}&deNo={deNo}")
    public void updateF_ArtStockBorne(@PathVariable double asQteMini, @PathVariable double asQteMaxi, @PathVariable String cbCreateur, @PathVariable String arRef, @PathVariable int deNo) {
        fArticleDAO.updateF_ArtStockBorne(asQteMini, asQteMaxi, cbCreateur, arRef, deNo);
    }

    @GetMapping(value = "/majRefArticle&newArRef={newArRef}&arRef={arRef}")
    public void majRefArticle(@PathVariable String newArRef, @PathVariable String arRef) {
        fArticleDAO.majRefArticle(newArRef, arRef);
    }

    @GetMapping(value = "/ajout_article&reference={reference}&designation={designation}&pxAchat={pxAchat}&faCodeFamille={faCodeFamille}&condition={condition}&pxHt={pxHt}&pxMin={pxMin}&pxMax={pxMax}&qteGros={qteGros}&arPrixTTC={arPrixTTC}&clNo1={clNo1}&clNo2={clNo2}&clNo3={clNo3}&clNo4={clNo4}&cbCreateur={cbCreateur}")
    public Object ajout_article(@PathVariable String reference, @PathVariable String designation
            , @PathVariable double pxAchat, @PathVariable String faCodeFamille, @PathVariable int condition, @PathVariable double pxHt, @PathVariable double pxMin, @PathVariable double pxMax
            , @PathVariable double qteGros, @PathVariable double arPrixTTC, @PathVariable int clNo1, @PathVariable int clNo2, @PathVariable int clNo3, @PathVariable int clNo4, @PathVariable String cbCreateur) {
       return fArticleDAO.ajout_article(FormatText.getString(reference), FormatText.getString(designation), pxAchat,FormatText.getString(faCodeFamille), condition, pxHt, pxMin, pxMax, qteGros, arPrixTTC, clNo1, clNo2, clNo3, clNo4, cbCreateur);
    }

    @GetMapping(value = "/maj/nom={nom}&valeur={valeur}&cbMarq={cbMarq}&cbCreateur={cbCreateur}")
    public void maj(@PathVariable String nom,@PathVariable String valeur,@PathVariable BigDecimal cbMarq,@PathVariable String cbCreateur) {
            fArticleDAO.maj(nom,FormatText.getString(valeur),cbMarq,cbCreateur);
    }

    @GetMapping(value = "/getStockDepot&arRef={arRef}&deNo={deNo}")
    public List<Object> getStockDepot(@PathVariable String arRef, @PathVariable int deNo) {
        return fArticleDAO.getStockDepot(arRef, deNo);
    }

    @GetMapping(value = "/getArticleByRefDesignation&deNo={deNo}&term={term}&typeFacture={typeFacture}&rechEtat={rechEtat}")
    public List<Object> getArticleByRefDesignation(@PathVariable int deNo, @PathVariable String term, @PathVariable String typeFacture,@PathVariable String rechEtat) {
        return fArticleDAO.getArticleByRefDesignation(deNo, term, typeFacture,rechEtat);
    }

    @GetMapping(value = "/getArtFournisseur&arRef={arRef}")
    public List<Object> getArtFournisseur(@PathVariable String arRef) {
        return fArticleDAO.getArtFournisseur(arRef);
    }

    @GetMapping(value = "/getPxMinMaxCatCompta&arRef={arRef}&acCategorie={acCategorie}")
    public List<Object> getPxMinMaxCatCompta(@PathVariable String arRef,@PathVariable int acCategorie) {
        return fArticleDAO.getPxMinMaxCatCompta(arRef,acCategorie);
    }

    @GetMapping(value = "/getArtFournisseurSelect&cbMarq={cbMarq}")
    public List<Object> getArtFournisseurSelect(@PathVariable BigDecimal cbMarq) {
        return fArticleDAO.getArtFournisseurSelect(cbMarq);
    }

    @GetMapping(value = "/getArticleByIntitule&arIntitule={arIntitule}")
    public List<Object> getArticleByIntitule(@PathVariable String arIntitule) {
        return fArticleDAO.getArticleByIntitule(arIntitule);
    }

    @GetMapping(value = "/queryListeArticle&flagPxAchat={flagPxAchat}&flagPxRevient={flagPxRevient}&arSommeil={arSommeil}&prixFlag={prixFlag}&stockFlag={stockFlag}&searchString={searchString}&orderBy={orderBy}&orderType={orderType}&start={start}&length={length}")
    public List<Object> queryListeArticle(@PathVariable int flagPxAchat,@PathVariable int flagPxRevient,@PathVariable int arSommeil,@PathVariable int prixFlag,@PathVariable int stockFlag,@PathVariable String searchString,@PathVariable String orderBy,@PathVariable String orderType,@PathVariable String start,@PathVariable String length) {
        return fArticleDAO.queryListeArticle(flagPxAchat,flagPxRevient,arSommeil,prixFlag,stockFlag,searchString,orderBy,orderType,start,length);
    }

    @GetMapping(value = "/isArticleLigne&arRef={arRef}")
    public List<Object> isArticleLigne(@PathVariable String arRef) {
        List<Object> list = fArticleDAO.isArticleLigne(arRef);
        return list;
    }

    @GetMapping(value = "/deleteArticle&cbMarq={cbMarq}")
    public int deleteArticle(@PathVariable int cbMarq) {
        return fArticleDAO.deleteArticle(cbMarq);
    }

    @GetMapping(value = "/allSearch&arPublie={arPublie}&sommeil={sommeil}&valeurSaisie={valeurSaisie}")
    public List<Object> allSearch(@PathVariable int arPublie,@PathVariable int sommeil,@PathVariable String valeurSaisie) {
            return fArticleDAO.allSearch(arPublie,sommeil, FormatText.getString(valeurSaisie));
    }


    @GetMapping(value = "/getAllArticleDispoByArRef&deNo={deNo}&codeFamille={codeFamille}&valeur={valeur}&rechEtat={rechEtat}")
    public List<Object> getAllArticleDispoByArRef(@PathVariable int deNo, @PathVariable String codeFamille,@PathVariable String valeur,@PathVariable String rechEtat) {
        List<Object> list = fArticleDAO.getAllArticleDispoByArRef(deNo,codeFamille,valeur,rechEtat);
        return list;
    }

    @GetMapping(value = "/getTaxeArticle&fcpChamp={fcpChamp}&fcpType={fcpType}&arRef={arRef}")
    public List<Object> getTaxeArticle(@PathVariable int fcpChamp,@PathVariable int fcpType,@PathVariable String arRef){
        return fArticleDAO.getTaxeArticle(fcpChamp,fcpType,arRef);
    }

    @GetMapping(value = "/stockMinDepasse&deNo={deNo}&arRef={arRef}")
    public List<Object> getAllArticleDispoByArRef(@PathVariable int deNo, @PathVariable String arRef) {
        List<Object> list = fArticleDAO.stockMinDepasse(deNo,arRef);
        return list;
    }

    @GetMapping(value = "/getArtFournisseurByTiers&ctNum={ctNum}")
    public int getArtFournisseurByTiers(@PathVariable String ctNum) {
        return fArticleDAO.getArtFournisseurByTiers(ctNum);
    }


    @GetMapping(value = "/getPrixClient&arRef={arRef}&catCompta={catCompta}&catTarif={catTarif}")
    public List<Object> getPrixClient(@PathVariable String arRef,@PathVariable int catCompta,@PathVariable int catTarif){
        List<Object> list = fArticleDAO.getPrixClient(arRef,catCompta,catTarif);
        return list;
    }

    @GetMapping(value = "/listeArticleSource&deNo={deNo}&type={type}&rechEtat={rechEtat}")
    public List<Object> listeArticleSource(@PathVariable int deNo,@PathVariable String type,@PathVariable String rechEtat){
        return fArticleDAO.listeArticleSource(deNo,type,rechEtat);
    }


}

