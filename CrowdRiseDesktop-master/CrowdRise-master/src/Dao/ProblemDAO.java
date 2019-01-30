    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Entity.Problem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author H4DH
 */

public class ProblemDAO implements Idao<Problem> 
{
    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;
    
     public ProblemDAO() {
        connection=DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Problem t) 
    {
        try 
        {
            String req="INSERT INTO `probleme`(`date`, `description`, `id_membre`, `titre`,`etat`) VALUES (curdate(),?,?,?,?)" ;
            pst=connection.prepareStatement(req);
            pst.setString(1, t.getDescription());
            pst.setInt(   2, t.getId_membre());
            pst.setString(3, t.getTitre());
            pst.setInt(4,t.getEtat());
            pst.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public void deleteById(int id) 
    {
        String req="DELETE FROM `probleme` WHERE `id`=?";
        try 
        {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
       @Override
    public void updateAllById(Problem t) 
    {
          String requete = "update probleme set date=?, description=?, id_membre=?, titre=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setDate(1, t.getDate());
            ps.setString(2, t.getDescription());
            ps.setInt(3, t.getId_membre());
            ps.setString(4, t.getTitre());
            ps.setInt(5, t.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public List<Problem> displayAll() 
    {
         String req="SELECT * FROM `probleme`";
        List<Problem> problems=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next())
            {
                
                problems.add(new Problem(rs.getInt("id"), rs.getDate("date"),rs.getString("description"),rs.getInt("id_membre"),rs.getString("titre"),rs.getInt("etat")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return problems;  
    }
    
   
    public List<Problem> displayAllByStatut() 
    {
         String req="SELECT * FROM `probleme` where etat=0" ;
        List<Problem> problems=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next())
            {
                
                problems.add(new Problem(rs.getInt("id"), rs.getDate("date"),rs.getString("description"),rs.getInt("id_membre"),rs.getString("titre"),rs.getInt("etat")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return problems;  
    }
    
        
    public void updateStatutById(int id,int etat){
        String requete = "update probleme set etat=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, etat);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public Problem displayById(int id) 
    {
        String req ="SELECT * FROM `probleme` where id=?";
        Problem problem=new Problem(0);
        try 
        {    
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while (rs.next())
            {
                if(rs.getInt("etat")==1)
                problem=new Problem(rs.getInt("id"), rs.getDate("date"),rs.getString("description"),rs.getInt("id_membre"),rs.getString("titre"));
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return problem; 
    }
    
    public List<Problem> displayByIdMembre(int id) 
    {
         String req="SELECT * FROM `probleme` where id_membre=?";
        List<Problem> problems=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next())
            {
                if(rs.getInt("etat")==1)
                problems.add(new Problem(rs.getInt("id"), rs.getDate("date"),rs.getString("description"),rs.getInt("id_membre"),rs.getString("titre"),rs.getInt("etat")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return problems;  
    }
    
    public void updateDate(Problem t)
    {
        String req = "UPDATE `probleme` set `date`=? where id=?";
        try 
        {
            pst=connection.prepareStatement(req);
            pst.setDate(1, t.getDate());
            pst.setInt(2, t.getId());
            pst.executeUpdate();
        }
         catch (SQLException ex)
        {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        public void updateDescription(Problem t)
    {
        String req = "UPDATE `probleme` set `description`=? where id=?";
        try 
        {
            pst=connection.prepareStatement(req);
            pst.setString(1, t.getDescription());
            pst.setInt(2, t.getId());
            pst.executeUpdate();
        }
         catch (SQLException ex)
        {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
    public void updateTitre(Problem t)
    {
        String req = "UPDATE `probleme` set `titre`=? where id=?";
        try 
        {
            pst=connection.prepareStatement(req);
            pst.setString(1, t.getTitre());
            pst.setInt(2, t.getId());
            pst.executeUpdate();
        }
         catch (SQLException ex)
        {
            Logger.getLogger(ProblemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /* à quoi sert cette méthode? */ 
    
    @Override
    public List<Problem> displayByAll(Problem t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateetat(Problem t) {
         String requete = "update probleme set etat=1 where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
       
    }
    
}
       


 
