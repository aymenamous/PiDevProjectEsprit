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
public class vote {
    
    int id;
    Date date;
    Membre membre;
    Projet projet;

    public vote(int id, Date date, Membre membre, Projet projet) {
        this.id = id;
        this.date = date;
        this.membre = membre;
        this.projet = projet;
    }

    public vote(Date date, Membre membre, Projet projet) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    @Override
    public String toString() {
        return "vote{" + "id=" + id + ", date=" + date + ", membre=" + membre + ", projet=" + projet + '}';
    }
    

    
    
    
    
    
    
    
    
    
    
}
