/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Entity.Commentaire;
import Entity.Membre;
import Entity.Signalisation;
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
 * @author Aymen
 */
public class SignalisationDao implements Idao<Signalisation>{

    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;

    public SignalisationDao() {
        connection=DataSource.getInstance().getConnection();
    }
    @Override
    public void add(Signalisation t) {
        String req="insert into signalisation (id_membre,id_commentaire,raison,date) values(?,?,?,?) ";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, t.getMembre().getId());
            pst.setInt(2, t.getCommentaire().getId());
            pst.setString(3, t.getRaison());
            pst.setDate(4, t.getDate());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String req="delete from signalisation where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAllById(Signalisation t) {
        String req="update signalisation set raison=?, date=? where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, t.getRaison());
            pst.setDate(2, t.getDate());
            pst.setInt(3, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SignalisationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Signalisation> displayAll() {
        String req="select * from signalisation";
        List<Signalisation> signalisations=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next())
            {
                signalisations.add(new Signalisation(rs.getInt("id"), new Membre(rs.getInt("id_membre")), new Commentaire(rs.getInt("id_commentaire")), rs.getString("raison"), rs.getDate("date")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return signalisations;
    }

    @Override
    public List<Signalisation> displayByAll(Signalisation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Signalisation displayById(int id) {
        String req="select * from signalisation where id=?";
        Signalisation signalisation=new Signalisation(0);
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next())
            {
                signalisation=new Signalisation(rs.getInt("id"), new Membre(rs.getInt("id_membre")), new Commentaire(rs.getInt("id_commentaire")), rs.getString("raison"), rs.getDate("date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return signalisation;
    }
    
    public List<Signalisation> displayByIdMembre(int id) {
        String req="select * from signalisation where id_membre=?";
        List<Signalisation> signalisations=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next())
            {
                signalisations.add(new Signalisation(rs.getInt("id"), new Membre(rs.getInt("id_membre")), new Commentaire(rs.getInt("id_commentaire")), rs.getString("raison"), rs.getDate("date")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return signalisations;
    }
    
}
