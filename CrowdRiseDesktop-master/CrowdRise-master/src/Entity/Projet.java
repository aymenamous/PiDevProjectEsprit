package Entity;

import java.sql.Date;
import java.util.List;


public class Projet {
    private int id;
    private String nom;
    private Date debut;
    private Date fin;
    //private Float duree;
    private String description;
    private Date date;
    private Membre membre;
    private List<Commentaire> commentaires ;
    private List<vote> votes;

    public Projet() {
    }

    public Projet(int id) {
        this.id = id;
    }

    public Projet(int id, String nom, Date debut, Date fin, String description, Date date, Membre membre) {
        this.id = id;
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.description = description;
        this.date = date;
        this.membre = membre;
    }
    
    

    public Projet(String nom, Date debut, Date fin, String description, Date date) {
        
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.description = description;
        this.date = date;
    }
    
    

    public Projet(String nom, Date debut, Date fin, String description, Date date, Membre membre) {
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

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public List<vote> getVotes() {
        return votes;
    }

    public void setVotes(List<vote> votes) {
        this.votes = votes;
    }
    
    

    @Override
    public String toString() {
        return "Projet{" + "id=" + id + ", nom=" + nom + ", debut=" + debut + ", fin=" + fin + ", description=" + description + ", date=" + date + ", membre=" + membre + '}';
    }
    
    
}
