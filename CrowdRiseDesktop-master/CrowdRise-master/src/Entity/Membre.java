/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Dao.CommentaireDao;
import Dao.CompetenceDao;
import Dao.FinancementDao;
import Dao.IdeeDAO;
import Dao.ProblemDAO;
import Dao.ProjetCFDao;
import Dao.ReclamationDao;
import Dao.SignalisationDao;
import Dao.SolutionDao;
import Dao.VoteDao;
import java.util.ArrayList;
import java.util.List;

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
    private List<Projet> projets;
    private List<Commentaire> commentaires;
    private List<vote> votes;
    private List<Problem> problems;
    private List<Solution> solutions;
    private List<Financement> financements;
    private List<Competence> competences;
    private List<Reclamation> reclamations;
    private List<Signalisation> signalisations;

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
        projets = new ArrayList<>();
        commentaires = new ArrayList<>();
        votes = new ArrayList<>();
        problems = new ArrayList<>();
        solutions = new ArrayList<>();
        financements = new ArrayList<>();
        competences = new ArrayList<>();
        reclamations = new ArrayList<>();
        signalisations = new ArrayList<>();
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
        projets = new ArrayList<>();
        commentaires = new ArrayList<>();
        votes = new ArrayList<>();
        problems = new ArrayList<>();
        solutions = new ArrayList<>();
        financements = new ArrayList<>();
        competences = new ArrayList<>();
        reclamations = new ArrayList<>();
        signalisations = new ArrayList<>();
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
        projets = new ArrayList<>();
        commentaires = new ArrayList<>();
        votes = new ArrayList<>();
        problems = new ArrayList<>();
        solutions = new ArrayList<>();
        financements = new ArrayList<>();
        competences = new ArrayList<>();
        reclamations = new ArrayList<>();
        signalisations = new ArrayList<>();
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

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public List<vote> getVotes() {
        return votes;
    }

    public void setVotes(List<vote> votes) {
        this.votes = votes;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public List<Financement> getFinancements() {
        return financements;
    }

    public void setFinancements(List<Financement> financements) {
        this.financements = financements;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }

    public List<Signalisation> getSignalisations() {
        return signalisations;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setSignalisations(List<Signalisation> signalisations) {
        this.signalisations = signalisations;
    }

    @Override
    public String toString() {
        return "Membre{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", mdp=" + mdp + ", adresse=" + adresse + ", telephone=" + telephone + ", photo=" + photo + ", cr=" + cr + ", nbr_solved=" + nbr_solved + ", statut=" + statut + ", projets=" + projets + ", commentaires=" + commentaires + ", votes=" + votes + ", problems=" + problems + ", solutions=" + solutions + ", financements=" + financements + ", competences=" + competences + ", reclamations=" + reclamations + ", signalisations=" + signalisations + '}';
    }

    public void fillListes() {
        CompetenceDao compdao = new CompetenceDao();
        competences = new ArrayList<>(compdao.displayByIdMembre(id));
        /*ProjetCFDao projdao = new ProjetCFDao();
        IdeeDAO idao = new IdeeDAO();
        CommentaireDao comdao = new CommentaireDao();
        VoteDao votedao = new VoteDao();
        ProblemDAO probdao = new ProblemDAO();
        SolutionDao soldao = new SolutionDao();
        FinancementDao findao = new FinancementDao();
        ReclamationDao recdao = new ReclamationDao();
        SignalisationDao signdao = new SignalisationDao();
        
        projets = new ArrayList<>(projdao.displayByIdMembre(id));
        projets.addAll(idao.displayByIdMembre(id));
        commentaires = new ArrayList<>(comdao.displayByIdMembre(id));
        votes = new ArrayList<>(votedao.displayByIdMembre(id));
        problems = new ArrayList<>(probdao.displayByIdMembre(id));
        solutions = new ArrayList<>(soldao.displayByIdMembre(id));
        financements = new ArrayList<>(findao.displayByIdMembre(id));
        
        reclamations = new ArrayList<>(recdao.displayByIdMembre(id));
        signalisations = new ArrayList<>(signdao.displayByIdMembre(id));*/
    }

}
