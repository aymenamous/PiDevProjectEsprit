/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;



/**
 *
 * @author Amine Triki
 */
public class ProjetCF extends Projet {
    double budget_actuel;
    double budget_final;
    //private List<Financements> Financements;
    private String image;

    public ProjetCF(int id, String nom) {
        super(id, nom);
    }

    
    public ProjetCF() {
    }

    public ProjetCF(double budget_actuel, double budget_final, int id, String nom, String debut, String fin, String description, String date, Membre membre, String image) {
        super(id, nom, debut, fin, description, date, membre);
        this.budget_actuel = budget_actuel;
        this.budget_final = budget_final;
        this.image=image;
    }

    public ProjetCF(double budget_actuel, double budget_final, String nom, String debut, String fin, String description, String date, String image) {
        super(nom, debut, fin, description, date);
        this.budget_actuel = budget_actuel;
        this.budget_final = budget_final;
        this.image=image;
    }

     
    
    
    public ProjetCF(double budget_actuel, double budget_final, String nom, String debut, String fin, String description, String date, Membre membre, String image) {
        super(nom, debut, fin, description, date,membre);
        this.budget_actuel = budget_actuel;
        this.budget_final = budget_final;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
    public double getBudget_actuel() {
        return budget_actuel;
    }

    public double getBudget_final() {
        return budget_final;
    }

    public void setBudget_actuel(double budget_actuel) {
        this.budget_actuel = budget_actuel;
    }

    public void setBudget_final(double budget_final) {
        this.budget_final = budget_final;
    }

    public String toString() {
        return super.toString()+ "ProjetCF{" + "budget_actuel=" + budget_actuel + ", budget_final=" + budget_final + ", image=" + image + '}'; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

    
    
    
}
