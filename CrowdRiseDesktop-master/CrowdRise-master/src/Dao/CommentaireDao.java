/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Commentaire;
import Entity.Membre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
import Entity.Projet;
import Entity.Signalisation;
import java.util.ArrayList;
import javax.smartcardio.CommandAPDU;

/**
 *
 * @author Yosra
 */
public class CommentaireDao implements Idao<Commentaire>{
    
          private Connection connection;
          private PreparedStatement pst;
          private ResultSet rs;

    
         public CommentaireDao()
  {
        connection = DataSource.getInstance().getConnection();

  }

    @Override
    public void add(Commentaire t) {
     String req="INSERT INTO commentaire(id_projet,id_membre,text_commentaire) VALUES (?,?,?) ";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1,t.getProjet().getId());
            pst.setInt(2,t.getMembre().getId());
            pst.setString(3,t.getText_commentaire());      
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteById(int id) {

        String req="delete from commentaire where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    
            public void Delete(Commentaire k) throws SQLException {
        String req="delete from commentaire where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, k.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAllById(Commentaire c) {
         String requete = "update commentaire set text_commentaire=?  where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
             ps.setString(1,c.getText_commentaire());

             ps.setInt(2,c.getId());
            
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }   
        
            }

    @Override
    public List<Commentaire> displayAll() {
        String req="select * from commentaire";
        List<Commentaire> commentaires=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next())
            {
               Commentaire commentaire = new Commentaire();
               commentaire.setId(rs.getInt("id"));
               commentaire.setText_commentaire(rs.getString("text_commentaire")); 
               commentaire.setMembre(new Membre(rs.getInt("id_membre")));
               commentaire.setProjet(new Projet(rs.getInt("id_projet")));
               commentaires.add(commentaire);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentaires; 
    
    }

    @Override
    public List<Commentaire> displayByAll(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commentaire displayById(int id) {
          Commentaire commentaire = new Commentaire();
        String requete = "select * from commentaire where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                commentaire.setId(resultat.getInt(1));
                commentaire.setText_commentaire(resultat.getString(2));
                commentaire.setProjet(new Projet(resultat.getInt("id_projet")));
                commentaire.setMembre(new Membre(resultat.getInt("id_membre")));
            }
            return commentaire;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche " + ex.getMessage());
            return null;
        }
    }
    
    public List<Commentaire> displayByIdMembre(int id) {
        List<Commentaire> commentaires=new ArrayList<>();
        String requete = "select * from commentaire where id_membre=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Commentaire commentaire=new Commentaire();
                commentaire.setId(resultat.getInt(1));
                commentaire.setText_commentaire(resultat.getString(2));
                commentaire.setProjet(new Projet(resultat.getInt("id_projet")));
                commentaire.setMembre(new Membre(resultat.getInt("id_membre")));
                commentaires.add(commentaire);
            }
            return commentaires;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche " + ex.getMessage());
            return commentaires;
        }
    }
    
    
       public List<Commentaire> CommentaireSignale(int id) {
        List<Commentaire> commentaires=new ArrayList<>();
        String requete = "select * from commentaire where id = (select id_commentaire from signalisation where id=?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Commentaire commentaire=new Commentaire();
                Signalisation s =new Signalisation();
                s.setId(resultat.getInt(1));
                commentaire.setId(resultat.getInt(1));
                commentaire.setText_commentaire(resultat.getString("text_commentaire"));
             
//                commentaire.setProjet(new Projet(resultat.getInt("id_projet")));
//                commentaire.setMembre(new Membre(resultat.getInt("id_membre")));
                commentaires.add(commentaire);
            }
            return commentaires;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche " + ex.getMessage());
            return commentaires;
        }
    }
    
    public List<Commentaire> RechercherByAll(Commentaire t) {
        String req = "select * from  commentaire where text_commentaire like ? ";
        List<Commentaire> Commentaires = new ArrayList<>();
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1,t.getText_commentaire()+ "%");
           
            rs = pst.executeQuery();
            while (rs.next()) {
           Commentaire r= new Commentaire();
                r.setId(rs.getInt("id"));
               r.setText_commentaire(rs.getString("text_commentaire"));
                Commentaires.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Commentaires;
    }

    public List<Commentaire> displayByIdProjet(int id) {
        List<Commentaire> commentaires=new ArrayList<>();
        String requete = "select * from commentaire where id_projet=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Commentaire commentaire=new Commentaire();
                commentaire.setId(resultat.getInt("id"));
                commentaire.setText_commentaire(resultat.getString("text_commentaire"));
                commentaire.setProjet(new Projet(resultat.getInt("id_projet")));
                commentaire.setMembre(new Membre(resultat.getInt("id_membre")));
                commentaires.add(commentaire);
            }
            return commentaires;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche " + ex.getMessage());
            return commentaires;
        }
    }
        }


