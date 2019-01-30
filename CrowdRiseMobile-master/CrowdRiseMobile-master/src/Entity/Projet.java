package Entity;

import java.util.Vector;




public class Projet {
    private int id;
    private String nom;
    private String debut;
    private String fin;
    //private Float duree;
    private String description;
    private String date;
    private Membre membre;
    private Vector commentaires ;
    private Vector votes;

    public Projet() {
    }

    public Projet(int id) {
        this.id = id;
    }

    public Projet(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Projet(int id, String nom, String debut, String fin, String description, String date, Membre membre) {
        this.id = id;
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.description = description;
        this.date = date;
        this.membre = membre;
    }

    public Projet(String nom) {
        this.nom = nom;
    }
    
    

    public Projet(String nom, String debut, String fin, String description, String date) {
        
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.description = description;
        this.date = date;
    }
    
    

    public Projet(String nom, String debut, String fin, String description, String date, Membre membre) {
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.description = description;
        this.date = date;
        this.membre=membre;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Vector getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Vector commentaires) {
        this.commentaires = commentaires;
    }

    public Vector getVotes() {
        return votes;
    }

    public void setVotes(Vector votes) {
        this.votes = votes;
    }
    
    

    
    public String toString() {
        return "Projet{" + "id=" + id + ", nom=" + nom + ", debut=" + debut + ", fin=" + fin + ", description=" + description + ", date=" + date + ", membre=" + membre + '}';
    }
    
    
}
