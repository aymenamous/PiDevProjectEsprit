/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;



/**
 *
 * @author H4DH
 */
public class Problem 
{
    protected int id;
    protected String date;
    protected String description;
    protected Membre membre;
    protected String titre;
    protected int etat=0;
    
    public Problem ()
    {}
    
      public Problem(int id, String date, String description, Membre membre, String titre) 
    {
        this.id = id;
        this.date = date;
        this.description = description;
        this.membre = membre;
        this.titre = titre;
        
    }
        public Problem(String date, String description, Membre membre, String titre) 
    {
        this.date = date;
        this.description = description;
        this.membre = membre;
        this.titre = titre;
    }

    public Problem(int id, String date, String description, Membre membre, String titre,int etat) 
    {
        this.id = id;
        this.date = date;
        this.description = description;
        this.membre = membre;
        this.titre = titre;
        this.etat= etat;
    }

    public Problem(String date, String description, Membre membre, String titre,int etat) 
    {
        this.date = date;
        this.description = description;
        this.membre = membre;
        this.titre = titre;
        this.etat=etat;
    }

    public Problem(int id) {
        this.id = id;
    }
    

    public int getId() 
    {
        return id;
    }
    
    public void setId(int id) 
    {
        this.id = id;
    }

    public String getDate() 
    {
        return date;
    }
    
    public void setDate(String date) 
    {
        this.date = date;
    }

    public String getDescription() 
    {
        return description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    
    
    public String getTitre() 
    {
        return titre;
    }

    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    

    
    public String toString() 
    {
        return "Problem{" + "id=" + id + ", date=" + date + ", description=" + description + ", Membre=" + membre + ", titre=" + titre + '}';
    }
    

    
       
}
