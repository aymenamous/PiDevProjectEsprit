/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author Yosra
 */
public class Reclamation {

    int id;
    Membre id_membre;
    Date date;
    String sujet;
    String reclamation;
    //String nom;

    public Reclamation() {
    }
    
   

    public Reclamation(int id, Membre id_membre, Date date, String sujet, String reclamation) {
        this.id = id;
        this.id_membre = id_membre;
        this.date = date;
        this.sujet = sujet;
        this.reclamation = reclamation;
    }

    



   
 

    public Reclamation(Membre id_membre, Date date, String sujet, String reclamation) {
        this.id_membre = id_membre;
        this.date = date;
        this.sujet = sujet;
        this.reclamation = reclamation;
       
    }

    
    public Reclamation(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Membre getId_membre() {
        return id_membre;
    }

    public String getReclamation() {
        return reclamation;
    }

    public void setReclamation(String reclamation) {
        this.reclamation = reclamation;
    }

    public void setId_membre(Membre id_membre) {
        this.id_membre = id_membre;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_membre=" + id_membre + ", date=" + date + ", sujet=" + sujet + ", reclamation=" + reclamation + '}';
    }
    
    public String  getNom()
    {
        return id_membre.getNom();
    }

    
    public int getIdM()
    {
        return  id_membre.getId();
    }
    
    public String getPrenom()
    {
        return id_membre.getPrenom();
    }
}
