/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author H4DH
 */
public class Problem 
{
    protected int id;
    protected Date date;
    protected String description;
    protected int id_membre;
    protected String titre;
    protected int etat=0;
    
    public Problem ()
    {}
    
      public Problem(int id, Date date, String description, int id_membre, String titre) 
    {
        this.id = id;
        this.date = date;
        this.description = description;
        this.id_membre = id_membre;
        this.titre = titre;
        
    }
        public Problem(Date date, String description, int id_membre, String titre) 
    {
        this.date = date;
        this.description = description;
        this.id_membre = id_membre;
        this.titre = titre;
    }

    public Problem(int id, Date date, String description, int id_membre, String titre,int etat) 
    {
        this.id = id;
        this.date = date;
        this.description = description;
        this.id_membre = id_membre;
        this.titre = titre;
        this.etat= etat;
    }

    public Problem(Date date, String description, int id_membre, String titre,int etat) 
    {
        this.date = date;
        this.description = description;
        this.id_membre = id_membre;
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

    public Date getDate() 
    {
        return date;
    }
    
    public void setDate(Date date) 
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

    public int getId_membre()
    {
        return id_membre;
    }
    
    public void setId_membre(int id_membre) 
    {
        this.id_membre = id_membre;
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
    
    

    @Override
    public String toString() 
    {
        return "Problem{" + "id=" + id + ", date=" + date + ", description=" + description + ", id_membre=" + id_membre + ", titre=" + titre + '}';
    }
    

    
       
}
