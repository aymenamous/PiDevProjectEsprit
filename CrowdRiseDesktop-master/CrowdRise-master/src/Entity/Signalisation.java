/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author Aymen
 */


public class Signalisation {
    private int id;
    private Membre membre;
    private Commentaire commentaire;
    private String raison;
    private Date date;

    public Signalisation() {
    }

    public Signalisation(int id,Membre membre, Commentaire commentaire, String raison, Date date) {
        this.id=id;
        this.membre = membre;
        this.commentaire = commentaire;
        this.raison = raison;
        this.date = date;
    }

    public Signalisation(Membre membre, Commentaire commentaire, String raison, Date date) {
        this.membre = membre;
        this.commentaire = commentaire;
        this.raison = raison;
        this.date = date;
    }

    public Signalisation(int id) {
        this.id = id;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
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

    @Override
    public String toString() {
        return "Signalisation{" + "id=" + id + ", membre=" + membre + ", commentaire=" + commentaire + ", raison=" + raison + ", date=" + date + '}';
    }

    
    
    
    
}
