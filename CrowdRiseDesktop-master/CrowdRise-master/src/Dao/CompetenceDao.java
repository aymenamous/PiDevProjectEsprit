/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Entity.Membre;
import Entity.Competence;
import Entity.Solution;
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
 * @author Amine Triki
 */
public class CompetenceDao implements Idao<Competence> {
    
    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;

    public CompetenceDao() {
        connection=DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Competence t) {
        String req="insert into competence (nom,id_membre) values (?,?)";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, t.getnom_Competence());
            pst.setInt(2, t.getMembre().getId());     
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CompetenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public void deleteById(int id) {
        String req="delete from competence where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CompetenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAllById(Competence t) {
        String requete = "update competence set nom=?, id_membre=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, t.getnom_Competence());
            ps.setInt(2, t.getMembre().getId());
            ps.setInt(3, t.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }    }

    @Override
    public List<Competence> displayAll() {
        String req="select * from competence";
        List<Competence> competences=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next())
            {
                competences.add(new Competence(rs.getInt("id"), rs.getString("nom"), new Membre(rs.getInt("id_membre"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return competences;    
    }

    @Override
    public List<Competence> displayByAll(Competence t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Competence displayByNameMembre(Competence competence)
    {
        String req="select * from competence where nom=? and id_membre=?";
        Competence c=new Competence();
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, competence.getnom_Competence());
            pst.setInt(2, competence.getMembre().getId());
            rs=pst.executeQuery();
            while (rs.next())
            {
                c.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public Competence displayById(int id) {
        String req="select * from Competence where id=?";
        Competence competence=new Competence();
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next())
            {
                competence = new Competence(rs.getInt("id"), rs.getString("nom"), new Membre(rs.getInt("id_membre")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return competence;
    }
    
    public List<Competence> displayByIdMembre(int id)
    {
        List<Competence> competences=new ArrayList<>();
        String req="select * from Competence where id_membre=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next())
            {
                Competence competence=new Competence(rs.getInt("id"), rs.getString("nom"), new Membre(rs.getInt("id_membre")));
                competences.add(competence);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return competences;
    }

    
    
}
