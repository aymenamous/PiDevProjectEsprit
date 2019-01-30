/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Admin;
import Entity.Membre;
import Entity.Reclamation;
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
 * @author Yosra
 */
public class ReclamationDao implements Idao<Reclamation> {

    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public ReclamationDao() {
        connection = DataSource.getInstance().getConnection();
        
    }
    
    @Override
    public void add(Reclamation t) {
        String req = "INSERT INTO `reclamation`( `id_membre`, `date`, `sujet`, `reclamationt`) VALUES  (?,?,?,?) ";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, t.getId_membre().getId());
            pst.setDate(2, t.getDate());
            pst.setString(3, t.getSujet());
            pst.setString(4, t.getReclamation());         
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void deleteById(int id) {
        System.out.println("Deleting id : " + id);
        
        String req = "delete from reclamation  where id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void Delete(Reclamation k) throws SQLException {
  String req="delete from reclamation where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, k.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void updateAllById(Reclamation t) {
        
        String requete = "update reclamation set reclamationt=?  where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, t.getReclamation());
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }        
        
    }
    
    @Override
    public List<Reclamation> displayAll() {
        
        String req = "select * from reclamation";
        List<Reclamation> reclamations = new ArrayList<>();
        try {
            pst = connection.prepareStatement(req);
            rs = pst.executeQuery();
                        
            while (rs.next()) {
                Reclamation reclamation = new Reclamation();
                 Membre m=new Membre();
                reclamation.setId(rs.getInt("id"));
              //  reclamation.setId_membre(new Membre((rs.getInt("id_membre"))));
                reclamation.setId_membre(new Membre(rs.getInt("id_membre")));
               // reclamation.setId_membre(new Membre(rs.getString("nom")));
                reclamation.setDate(rs.getDate("date"));
                reclamation.setSujet(rs.getString("sujet"));
                reclamation.setReclamation(rs.getString("reclamationt"));
                reclamations.add(reclamation);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reclamations;        
        
    }
    
    @Override
    public List<Reclamation> displayByAll(Reclamation t) {
        
        List<Reclamation> reclamations = new ArrayList<>();
        
        String req = "select * from reclamation where id=?";
        try {
            pst = connection.prepareStatement(req);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setId(rs.getInt("id"));
                rec.setReclamation(rs.getString("reclamationt"));
                reclamations.add(rec);
                
            }
            return reclamations;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement  " + ex.getMessage());
            return null;
        }
        
    }
    
    @Override
    public Reclamation displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Reclamation> displayByIdMembre(int id) {
        
        List<Reclamation> reclamations = new ArrayList<>();
        
        String req = "select * from reclamation where id_membre=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setId(rs.getInt("id"));
                rec.setReclamation(rs.getString("reclamationt"));
                reclamations.add(rec);
                
            }
            return reclamations;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement  " + ex.getMessage());
            return null;
        }
        
    }
    
    
    

    public List<Reclamation> RechercherByAll(Reclamation t) {
        String req = "select * from reclamation where sujet like ? ";
        List<Reclamation> reclamations = new ArrayList<>();
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1,t.getSujet()+ "%");
           
            rs = pst.executeQuery();
            while (rs.next()) {
           Reclamation reclamation = new Reclamation();
                reclamation.setId(rs.getInt("id"));
                reclamation.setId_membre(new Membre((rs.getInt("id_membre"))));
                reclamation.setDate(rs.getDate("date"));
                reclamation.setSujet(rs.getString("sujet"));
                reclamation.setReclamation(rs.getString("reclamationt"));
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reclamations;
    }
}
