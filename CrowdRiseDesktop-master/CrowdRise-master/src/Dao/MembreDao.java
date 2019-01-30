/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Commentaire;
import Entity.Competence;
import Entity.Financement;
import java.util.List;
import Entity.Membre;
import Entity.Problem;
import Entity.Projet;
import Entity.ProjetCF;
import Entity.Reclamation;
import Entity.Signalisation;
import Entity.Solution;
import Entity.vote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author Aymen
 */
public class MembreDao implements Idao<Membre> {

    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;

    public MembreDao() {
        connection = DataSource.getInstance().getConnection();
    }

    public void add(Membre t) {
        String req = "insert into membre (nom,prenom,email,password,adresse,telephone,cr,nbr_solved,photo,statut,username,enabled,username_canonical) values(?,?,?,?,?,?,?,?,'/ressources/profil.png','1',?,1,?) ";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMdp());
            pst.setString(5, t.getAdresse());
            pst.setString(6, t.getTelephone());
            pst.setInt(7, t.getCr());
            pst.setDouble(8, t.getNbr_solved());
            pst.setString(9, t.getNom()+t.getPrenom());
            pst.setString(10, t.getNom()+t.getPrenom());
            pst.executeUpdate();
            System.out.println("done");
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String req = "delete from membre where id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAllById(Membre t) {
        String req = "update membre set nom=?, prenom=? ,  email=? ,  password=? ,  adresse=? ,  telephone=? ,  cr=? ,  nbr_solved=?, photo=?, statut=?, username=?, username_canonical=? where id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMdp());
            pst.setString(5, t.getAdresse());
            pst.setString(6, t.getTelephone());
            pst.setInt(7, t.getCr());
            pst.setDouble(8, t.getNbr_solved());
            pst.setString(9, t.getPhoto());
            pst.setBoolean(10, t.isStatut());
            pst.setString(11, t.getNom()+t.getPrenom());
            pst.setString(12, t.getNom()+t.getPrenom());
            pst.setInt(13, t.getId());
           
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Membre> displayAll() {
        String req = "select * from membre";
        List<Membre> membres = new ArrayList<>();
        try {
            pst = connection.prepareStatement(req);
            rs = pst.executeQuery();
            while (rs.next()) {
                Membre membre = new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getString("adresse"), rs.getString("telephone"), rs.getInt("cr"), rs.getDouble("nbr_solved"), rs.getString("photo"), rs.getBoolean("statut"));
                membres.add(membre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return membres;
    }

    @Override
    public List<Membre> displayByAll(Membre t) {
        String req = "select * from membre where nom like ? and prenom like ? and email like ? ";
        List<Membre> membres = new ArrayList<>();
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, t.getNom() + "%");
            pst.setString(2, t.getPrenom() + "%");
            pst.setString(3, t.getEmail() + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                Membre membre = new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getString("adresse"), rs.getString("telephone"), rs.getInt("cr"), rs.getDouble("nbr_solved"), rs.getString("photo"), rs.getBoolean("statut"));
                membres.add(membre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return membres;
    }

    @Override
    public Membre displayById(int id) {
        String req = "select * from membre where id=?";
        Membre membre = new Membre(0);
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                membre = new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getString("adresse"), rs.getString("telephone"), rs.getInt("cr"), rs.getDouble("nbr_solved"), rs.getString("photo"), rs.getBoolean("statut"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return membre;
    }

    public Membre displayByEmailMdp(String email, String mdp) {
        String req = "select * from membre where email=? and password=?";
        Membre membre = new Membre(0);
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, email);
            pst.setString(2, mdp);
            rs = pst.executeQuery();
            while (rs.next()) {
                membre = new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getString("adresse"), rs.getString("telephone"), rs.getInt("cr"), rs.getDouble("nbr_solved"), rs.getString("photo"), rs.getBoolean("statut"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return membre;
    }

    public Membre displayByEmail(String email)
    {
        String req="select * from membre where email=? ";
        Membre membre=new Membre(0);
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, email);
            rs=pst.executeQuery();
            while (rs.next())
            {
                membre=new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"),rs.getString("adresse"), rs.getString("telephone"), rs.getInt("cr"), rs.getDouble("nbr_solved"),rs.getString("photo"),rs.getBoolean("statut"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return membre;
    }
}
