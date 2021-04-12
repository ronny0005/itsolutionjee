package com.server.itsolution.ressource;

import com.server.itsolution.dao.FCaisseDAO;
import com.server.itsolution.dao.FProtectioncialDAO;
import com.server.itsolution.dao.PParametrecialDAO;
import com.server.itsolution.entities.FCollaborateur;
import com.server.itsolution.entities.FProtectioncial;
import com.server.itsolution.entities.FormatText;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Formattable;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/fprotectioncial")
public class FProtectioncialRessource {

    @Autowired
    FProtectioncialDAO fProtectioncialDAO;
    PParametrecialDAO pParametrecialDAO;

    @GetMapping(value = "/all")
    public List<Object> getAll() {
        List<Object> list = fProtectioncialDAO.getAll();
        return list;
    }

    @GetMapping(value = "/user={username}&mdp={mdp}")
    public Object getUser(@PathVariable String username,@PathVariable String mdp) {
        return fProtectioncialDAO.getUser(FormatText.getString(username),FormatText.getString(mdp),0);
    }

    @GetMapping(value = "/barreMenu&protNo={protNo}&module={module}&action={action}&type={type}&position={position}")
    public Object barreMenu(@PathVariable int protNo,@PathVariable int module,@PathVariable int action,@PathVariable String type,@PathVariable String position) {
        return fProtectioncialDAO.barreMenu(protNo,action,module,type,position);
    }

    @PostMapping(value = "/login")
    public Object login(@RequestBody String data) {
        JSONObject tasse = new JSONObject();
        try {
            tasse = new JSONObject(data);

            Object result = fProtectioncialDAO.getUser(FormatText.getString(tasse.getString("user")),FormatText.getString(tasse.getString("pwd")),0);
            if(result == null )
                return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
                else
                    return result;
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getUserList")
    public Object getUserList() {
        return fProtectioncialDAO.getUserList();
    }

    @GetMapping(value = "/connexion&protUser={protUser}&pwd={pwd}&jour={jour}&heure={heure}")
    public Object connexion(@PathVariable String protUser,@PathVariable String pwd,@PathVariable int jour,@PathVariable String heure){
        return fProtectioncialDAO.connexion(protUser,pwd,jour,heure);
    }


    @GetMapping(value = "/getDepotUser&protNo={protNo}")
    public Object getDepotUser(@PathVariable int protNo) {
        return fProtectioncialDAO.getDepotUser(protNo);
    }
    @GetMapping(value = "/getParametre&protNo={protNo}")
    public Object getParametre(@PathVariable int protNo) {
        return fProtectioncialDAO.getParametre(protNo);
    }

    @GetMapping(value = "/IssecuriteAdmin&protNo={protNo}&deNo={deNo}")
    public Object IssecuriteAdmin(@PathVariable int protNo ,@PathVariable int deNo){
        return fProtectioncialDAO.getParametre(protNo);
    }

    @GetMapping(value = "/allProfil")
    public Object allProfil(){
        return fProtectioncialDAO.allProfil();
    }

    @GetMapping(value = "/updateLastLogin&protNo={protNo}")
    public void updateLastLogin(@PathVariable int protNo) {
        fProtectioncialDAO.updateLastLogin(protNo);
    }

    @GetMapping(value = "/connexionProctectionByProtNo&protNo={protNo}")
    public Object connexionProctectionByProtNo(@PathVariable int protNo) {
        return fProtectioncialDAO.connexionProctectionByProtNoJSON(protNo);
    }

    @GetMapping(value = "/ajoutUser&username={username}&description={description}&password={password}&email={email}&protRight={protRight}&protUserProfil={protUserProfil}&protPwdStatus={protPwdStatus}&securiteAdmin={securiteAdmin}&protNo={protNo}&depot={depot}")
    public Object ajoutUser(@PathVariable String username,@PathVariable String description,@PathVariable String password
            ,@PathVariable String email,@PathVariable int protRight,@PathVariable int protUserProfil,@PathVariable int protPwdStatus
            ,@PathVariable int securiteAdmin,@PathVariable int protNo,@PathVariable String depot){
         return fProtectioncialDAO.ajoutUser(username,description,password,email,protRight,protUserProfil
                 ,protPwdStatus,securiteAdmin,protNo,depot);
        
    }

    @GetMapping(value = "/ajoutDepotUser&protNo={protNo}&depot={depot}")
    public void ajoutDepotUser(@PathVariable int protNo,@PathVariable String depot) {
        fProtectioncialDAO.ajoutDepotUser(depot,protNo);
    }

    @GetMapping(value = "/setDepotUser&protNo={protNo}&depot={depot}")
    public void setDepotUser(@PathVariable int protNo,@PathVariable String depot) {
        fProtectioncialDAO.setDepotUser(depot,protNo);
    }

    @GetMapping(value = "/getSoucheDepotGrpSouche&protNo={protNo}&type={type}")
    public Object getSoucheDepotGrpSouche(@PathVariable int protNo,@PathVariable String type) {
        Object list = fProtectioncialDAO.getSoucheDepotGrpSouche(protNo,type);
        return list;
    }

    @GetMapping(value = "/getUtilisateurAdminMain")
    public Object getUtilisateurAdminMain() {
        return fProtectioncialDAO.getUtilisateurAdminMain();
    }

    @GetMapping(value = "/createUser/protUser={protUser}&protPwd={protPwd}&protDescription={protDescription}&protRight={protRight}&protEmail={protEmail}&protUserProfil={protUserProfil}&ProtPwdStatus={ProtPwdStatus}")
    public Object createUser(@PathVariable String protUser,@PathVariable String protPwd,@PathVariable String protDescription,@PathVariable int protRight,@PathVariable String protEmail,@PathVariable String protUserProfil,@PathVariable int ProtPwdStatus) {
        return fProtectioncialDAO.createUser(protUser,protPwd,protDescription,protRight,protEmail,protUserProfil,ProtPwdStatus);
    }

    @GetMapping(value = "/maj/nom={nom}&valeur={valeur}&cbMarq={cbMarq}&cbCreateur={cbCreateur}")
    public void maj(@PathVariable String nom, @PathVariable String valeur, @PathVariable BigDecimal cbMarq, @PathVariable String cbCreateur) {
        fProtectioncialDAO.maj(nom, FormatText.getString(valeur),cbMarq,cbCreateur);
    }

    @GetMapping(value = "/getAllProfils")
    public Object getAllProfils() {
        return fProtectioncialDAO.getAllProfils();
    }

    @GetMapping(value = "/alerteDocumentCatComptaTaxe")
    public List<Object> alerteDocumentCatComptaTaxe() {
        return fProtectioncialDAO.alerteDocumentCatComptaTaxe();
    }

    @GetMapping(value = "/getSoucheDepotGrpAffaire&protNo={protNo}&type={type}&sommeil={sommeil}")
    public List<Object> getSoucheDepotGrpAffaire(@PathVariable int protNo,@PathVariable String type,@PathVariable int sommeil) {
        return fProtectioncialDAO.getSoucheDepotGrpAffaire(protNo,type,sommeil);
    }

    @GetMapping(value = "/getInfoRAFControleur")
    public ArrayList<FCollaborateur> getInfoRAFControleur() {
        return fProtectioncialDAO.getInfoRAFControleur();
    }

    @GetMapping(value = "/getCoNo&protNo={protNo}")
    public int getInfoRAFControleur(@PathVariable int protNo) {
        return fProtectioncialDAO.getCoNo(protNo);
    }


    @GetMapping(value = "/getSoucheDepotCaisse&protNo={protNo}&type={type}&doSouche={souche}&caNo={caNo}&deNo={deNo}&caNum={caNum}")
    public Object getSoucheDepotCaisse(@PathVariable int protNo,@PathVariable String type,@PathVariable int souche
            ,@PathVariable int caNo,@PathVariable int deNo,@PathVariable String caNum) {
        Object list = fProtectioncialDAO.getSoucheDepotCaisse(type,souche,caNo,deNo,caNum);
        return list;
    }



}

