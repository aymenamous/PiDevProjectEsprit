/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author Amine Triki
 */
public class ProjetCF extends Projet {
    double budget_actuel;
    double budget_final;
    //private List<Financements> Financements;
    private String image;

    public ProjetCF() {
    }

    public ProjetCF(double budget_actuel, double budget_final, int id, String nom, Date debut, Date fin, String description, Date date, Membre membre, String image) {
        super(id, nom, debut, fin, description, date, membre);
        this.budget_actuel = budget_actuel;
        this.budget_final = budget_final;
        this.image=image;
    }

    public ProjetCF(double budget_actuel, double budget_final, String nom, Date debut, Date fin, String description, Date date, String image) {
        super(nom, debut, fin, description, date);
        this.budget_actuel = budget_actuel;
        this.budget_final = budget_final;
        this.image=image;
    }

     
    
    
    public ProjetCF(double budget_actuel, double budget_final, String nom, Date debut, Date fin, String description, Date date, Membre membre, String image) {
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

    @Override
    public String toString() {
        return "ProjetCF{" +"nom="+super.getNom()+", debut="+super.getDebut()+
                ", fin="+super.getFin()+", description="+super.getDescription()
                +", date="+super.getDate()
                +", budget_actuel=" + budget_actuel + ", budget_final=" + budget_final + "}\n";
    }
    
    
}
