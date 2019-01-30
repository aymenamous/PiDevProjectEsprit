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
public class Competence {
    private int id;
    private String nom_Competence;
    private Membre membre;
    public static final String DESIGNER = "Designer" ;
    public static final String DEVOLOPER = "Devloppeur" ;
    public static final String INTERGRATOR = "Integrateur" ;




    public Competence() {
        id=0;
        nom_Competence="";
        membre =new Membre();
    }
       
    

    public Competence(int id, String nom_Competence, Membre membre) {
        this.id = id;
        this.nom_Competence = nom_Competence;
        this.membre = membre;
        
        
    }

    public Competence(String nom_Competence, Membre membre) {
        this.nom_Competence = nom_Competence;
        this.membre = membre;
        
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnom_Competence() {
        return nom_Competence;
    }

    public void setnom_Competence(String nom_Competence) {
        this.nom_Competence = nom_Competence;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }  

    @Override
    public String toString() {
        return "Competence{" + "id=" + id + ", nom_Competence=" + nom_Competence + ", membre=" + membre + '}';
    }

    
 
    
}
