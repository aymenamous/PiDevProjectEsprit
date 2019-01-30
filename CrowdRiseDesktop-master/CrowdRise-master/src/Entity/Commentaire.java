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
public class Commentaire {
    
    int id;
    Projet projet;
    Membre membre;
    String text_commentaire;

    public Commentaire() {
    
    
    }

    public Commentaire(int id, String text_commentaire) {
        this.id = id;
        this.text_commentaire = text_commentaire;
    }

    public Commentaire(int id) {
        this.id = id;
    }

    public Commentaire(String text_commentaire) {
        this.text_commentaire = text_commentaire;
    }

   
    public Commentaire(Projet projet, Membre membre,String text_commentaire) {
        this.projet = projet;
        this.membre = membre;
        this.text_commentaire = text_commentaire;
    }
    
    

    public Commentaire(int id, Projet projet, Membre membre, String text_commentaire) {
        this.id = id;
        this.projet = projet;
        this.membre = membre;
        this.text_commentaire = text_commentaire;
    }

    public int getId() {
        return id;
    }
    
   

    public void setId(int id) {
        this.id = id;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public String getText_commentaire() {
        return text_commentaire;
    }

    public void setText_commentaire(String text_commentaire) {
        this.text_commentaire = text_commentaire;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", projet=" + projet + ", membre=" + membre + ", text_commentaire=" + text_commentaire + '}';
    }

    
}
