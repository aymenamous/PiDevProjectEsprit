/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;



/**
 *
 * @author Aymen
 */
public class Financement {
    int id;
    String moyen;
    String date;
    Membre membre;
    Projet projet;
    double somme;

    public Financement(int id, String moyen, String date, Membre membre, Projet projet, double somme) {
        this.id = id;
        this.moyen = moyen;
        this.date = date;
        this.membre = membre;
        this.projet = projet;
        this.somme = somme;
    }

    public Financement(String moyen, String date, Membre membre, Projet projet, double somme) {
        this.moyen = moyen;
        this.date = date;
        this.membre = membre;
        this.projet = projet;
        this.somme = somme;
    }

    public Financement(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoyen() {
        return moyen;
    }

    public void setMoyen(String moyen) {
        this.moyen = moyen;
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

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }

    
    public String toString() {
        return "Financement{" + "id=" + id + ", moyen=" + moyen + ", date=" + date + ", membre=" + membre + ", projet=" + projet + ", somme=" + somme + '}';
    }
    
    
    
    
}
