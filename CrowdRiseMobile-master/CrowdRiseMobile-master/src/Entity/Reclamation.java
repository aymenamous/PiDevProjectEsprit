/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;



/**
 *
 * @author Yosra
 */
public class Reclamation {

    int id;
    Membre membre;
    String date;
    String reclamation;
    String sujet;
    //String nom;

    public Reclamation() {
    }
    
   

    public Reclamation(int id, Membre membre, String date, String reclamation,String sujet) {
        this.id = id;
        this.membre = membre;
        this.date = date;
        this.reclamation = reclamation;
        this.sujet = sujet;
   
    }



    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    
    public Reclamation(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public String getReclamation() {
        return reclamation;
    }

    public void setReclamation(String reclamation) {
        this.reclamation = reclamation;
    }

 
    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_membre=" + membre + ", date=" + date + ", sujet=" + sujet + ", reclamation=" + reclamation + '}';
    }
    
    public String  getNom()
    {
        return membre.getNom();
    }

    
    public int getIdM()
    {
        return  membre.getId();
    }
    
    public String getPrenom()
    {
        return membre.getPrenom();
    }
}
