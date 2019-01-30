/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Membre;
import Entity.Projet;
import Entity.vote;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author Yosra
 */
public class VoteDao implements Idao<vote>{
    
      private Connection connection;
          private PreparedStatement pst;
          private ResultSet rs;



    
         public VoteDao()
  {
        connection = DataSource.getInstance().getConnection();

  }

    @Override
    public void add(vote t) {
        String req="INSERT INTO voting(date,id_membre,id_projet) VALUES (?,?,?) ";
        try {
            pst=connection.prepareStatement(req);
            pst.setDate(1, t.getDate());
            pst.setInt(2, t.getMembre().getId());
            pst.setInt(3,t.getProjet().getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteById(int id) {

     String req="delete from voting where id_membre=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    
    public void deleteByMembreProjet(Projet p, Membre m) {

     String req="delete from voting where id_membre=? and id_projet=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, m.getId());
            pst.setInt(2, p.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @Override
    public void updateAllById(vote t) {
  String requete = "update voting set date=? where id_membre=?";
        try {
            pst=connection.prepareStatement(requete);
            pst.setDate(1, t.getDate());
            pst.setInt(2, t.getId());
            pst.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }   
        
    
    
    }


    @Override
    public List<vote> displayAll() {
   String req="select * from voting";
        List<vote> votes=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next())
            {
               
              vote v= new vote();
               v.setId(rs.getInt("id"));
               v.setDate(rs.getDate("date"));
               v.setMembre(new Membre(rs.getInt("id_membre")));
               v.setProjet(new Projet(rs.getInt("id_projet")));
               votes.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votes; 
        }

    @Override
    public List<vote> displayByAll(vote t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public vote displayById(int id) {
        String req="select * from voting where id=?";
        vote v= new vote();
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next())
            {
               v.setId(rs.getInt("id"));
               v.setDate(rs.getDate("date"));
               v.setMembre(new Membre(rs.getInt("id_membre")));
               v.setProjet(new Projet(rs.getInt("id_projet")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
    public List<vote> displayByIdMembre(int id) {
   String req="select * from voting where id_membre=?";
        List<vote> votes=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next())
            {
               
              vote v= new vote();
               v.setId(rs.getInt("id"));
               v.setDate(rs.getDate("date"));
               v.setMembre(new Membre(rs.getInt("id_membre")));
               v.setProjet(new Projet(rs.getInt("id_projet")));
               votes.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votes; 
        }
    
        public List<vote> displayByIdProjet(int id) {
        String req="select * from voting where id_projet=?";
        List<vote> votes=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next())
            {
               
              vote v= new vote();
               v.setId(rs.getInt("id"));
               v.setDate(rs.getDate("date"));
               v.setMembre(new Membre(rs.getInt("id_membre")));
               v.setProjet(new Projet(rs.getInt("id_projet")));
               votes.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votes; 
        }

   
    public int ExisteMembre(int id)
    {
         int i=0;
        String req = "select id_membre from voting where id_membre=?";

        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {

               i=(Integer)rs.getInt("id_membre");

            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
    
   
    public int ExisteProjet(int id)
    {
         int i=0;
        String req = "select id_projet from voting where id_projet=?";

        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {

               i=(Integer)rs.getInt("id_projet");

            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
    
    public int count(Projet id_projet)
    {int rowcount=0;
         String req = "select count(id) as nb from voting where id_projet=?";

        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,id_projet.getId());
            rs = pst.executeQuery();
         while(rs.next())
         {
                          rowcount=rs.getInt("nb");

         }
            
          
        } catch (SQLException ex) {
            Logger.getLogger(VoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowcount;
    }
            
       public int countTotal()
    {int rowcount=0;
         String req = "select count(id) as nb from voting";

        try {
            pst = connection.prepareStatement(req);
            rs = pst.executeQuery();
         while(rs.next())
         {
                          rowcount=rs.getInt("nb");

         }
            
          
        } catch (SQLException ex) {
            Logger.getLogger(VoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowcount;
    }
}
