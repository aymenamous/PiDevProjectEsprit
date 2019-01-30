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
public class vote {
    
    int id;
    String date;
    Membre membre;
    Projet projet;

    public vote(int id, String date, Membre membre, Projet projet) {
        this.id = id;
        this.date = date;
        this.membre = membre;
        this.projet = projet;
    }

    public vote(String date, Membre membre, Projet projet) {
        this.date = date;
        this.membre = membre;
        this.projet = projet;
    }

   
    public vote(int id) {
        this.id = id;
    }

    public vote() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    
    public String toString() {
        return "vote{" + "id=" + id + ", date=" + date + ", membre=" + membre + ", projet=" + projet + '}';
    }
    

    
    
    
    
    
    
    
    
    
    
}
