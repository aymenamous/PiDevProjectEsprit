/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Vector;



/**
 *
 * @author Aymen
 */
public class Membre {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String adresse;
    private String telephone;
    private String photo;
    private int cr;
    private double nbr_solved;
    private boolean statut;
    private Vector projets;
    private Vector commentaires;
    private Vector votes;
    private Vector problems;
    private Vector solutions;
    private Vector financements;
    private Vector competences;
    private Vector reclamations;
    private Vector signalisations;

    public Membre() {
    }

    public Membre(String nom, String prenom, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.adresse = "";
        this.telephone = "";
        this.cr = 0;
        this.nbr_solved = 0;
        projets = new Vector();
        commentaires = new Vector();
        votes = new Vector();
        problems = new Vector();
        solutions = new Vector();
        financements = new Vector();
        competences = new Vector();
        reclamations = new Vector();
        signalisations = new Vector();
    }
    
    public Membre(int id, String nom, String prenom, String email, String mdp, String adresse, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public Membre(int id, String nom, String prenom, String email, String mdp, String adresse, String telephone, int cr, double nbr_solved, String photo, boolean statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.adresse = adresse;
        this.telephone = telephone;
        this.cr = cr;
        this.nbr_solved = nbr_solved;
        this.photo = photo;
        this.statut = statut;
        projets = new Vector();
        commentaires = new Vector();
        votes = new Vector();
        problems = new Vector();
        solutions = new Vector();
        financements = new Vector();
        competences = new Vector();
        reclamations = new Vector();
        signalisations = new Vector();
    }

    public Membre(int id) {
        this.id = id;
        this.nom = "";
        this.prenom = "";
        this.email = "";
        this.mdp = "";
        this.adresse = "";
        this.telephone = "";
        this.cr = 0;
        this.nbr_solved = 0;
        projets = new Vector();
        commentaires = new Vector();
        votes = new Vector();
        problems = new Vector();
        solutions = new Vector();
        financements = new Vector();
        competences = new Vector();
        reclamations = new Vector();
        signalisations = new Vector();
    }

    public Membre(String nom) {
        this.nom = nom;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getCr() {
        return cr;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }

    public double getNbr_solved() {
        return nbr_solved;
        
    }

    public void setNbr_solved(double nbr_solved) {
        this.nbr_solved = nbr_solved;
    }

    public Vector getProjets() {
        return projets;
    }

    public void setProjets(Vector projets) {
        this.projets = projets;
    }

    public Vector getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Vector commentaires) {
        this.commentaires = commentaires;
    }

    public Vector getProblems() {
        return problems;
    }

    public void setProblems(Vector problems) {
        this.problems = problems;
    }

    public Vector getVotes() {
        return votes;
    }

    public void setVotes(Vector votes) {
        this.votes = votes;
    }

    public Vector getSolutions() {
        return solutions;
    }

    public void setSolutions(Vector solutions) {
        this.solutions = solutions;
    }

    public Vector getFinancements() {
        return financements;
    }

    public void setFinancements(Vector financements) {
        this.financements = financements;
    }

    public Vector getCompetences() {
        return competences;
    }

    public void setCompetences(Vector competences) {
        this.competences = competences;
    }

    public Vector getReclamations() {
        return reclamations;
    }

    public void setReclamations(Vector reclamations) {
        this.reclamations = reclamations;
    }

    public Vector getSignalisations() {
        return signalisations;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setSignalisations(Vector signalisations) {
        this.signalisations = signalisations;
    }

    public String toString() {
        return "Membre{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", mdp=" + mdp + ", adresse=" + adresse + ", telephone=" + telephone + ", photo=" + photo + ", cr=" + cr + ", nbr_solved=" + nbr_solved + ", statut=" + statut + ", projets=" + projets + ", commentaires=" + commentaires + ", votes=" + votes + ", problems=" + problems + ", solutions=" + solutions + ", financements=" + financements + ", competences=" + competences + ", reclamations=" + reclamations + ", signalisations=" + signalisations + '}';
    }

    public void fillListes() {
        
    }

}
