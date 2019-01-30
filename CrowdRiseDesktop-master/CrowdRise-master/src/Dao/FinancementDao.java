/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Entity.Financement;
import Entity.Membre;
import Entity.Projet;
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
public class FinancementDao implements Idao<Financement> {

    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;

    public FinancementDao() {
        this.connection = DataSource.getInstance().getConnection();;
    }
    
    @Override
    public void add(Financement t) {
        String req="insert into financement (moyen,date,somme,id_membre,id_projet) values (?,?,?,?,?)";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, t.getMoyen());
            pst.setDate(2, t.getDate());
            pst.setDouble(3, t.getSomme());
            pst.setInt(4, t.getMembre().getId());
            pst.setInt(5, t.getProjet().getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FinancementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String req="delete from financement where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FinancementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAllById(Financement t) {
        String req="update financement set moyen=?,date=?,somme=? where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, t.getMoyen());
            pst.setDate(2, t.getDate());
            pst.setDouble(3, t.getSomme());
            pst.setInt(4, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FinancementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateMoyen(Financement t)
    {
        String req="update financement set moyen=? where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, t.getMoyen());
            pst.setInt(2, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FinancementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDate(Financement t)
    {
        String req="update financement set date=? where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setDate(1, t.getDate());
            pst.setInt(2, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FinancementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateSomme(Financement t)
    {
        String req="update financement set somme=? where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setDouble(1, t.getSomme());
            pst.setInt(2, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FinancementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateMembre(Financement t)
    {
        String req="update financement set id_membre=? where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, t.getMembre().getId());
            pst.setInt(2, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FinancementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateProjet(Financement t)
    {
        String req="update financement set id_projet=? where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, t.getProjet().getId());
            pst.setInt(2, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FinancementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Financement> displayAll() {
        String req="select * from financement";
        List<Financement> financements=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next())
            {
                financements.add(new Financement(rs.getInt("id"), rs.getString("moyen"), rs.getDate("date"), new Membre(rs.getInt("id_membre")), new Projet(rs.getInt("id_projet")), rs.getDouble("somme")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FinancementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return financements;
    }

    @Override
    public List<Financement> displayByAll(Financement t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Financement displayById(int id) {
        String req="select * from financement where id=?";
        Financement financement=new Financement(0);
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next())
            {
                financement=new Financement(rs.getInt("id"), rs.getString("moyen"), rs.getDate("date"), new Membre(rs.getInt("id_membre")), new Projet(rs.getInt("id_projet")), rs.getDouble("somme"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FinancementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return financement;
    }
    
    public List<Financement> displayByIdMembre(int id) {
        String req="select * from financement where id_membre=?";
        List<Financement> financements=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next())
            {
                financements.add(new Financement(rs.getInt("id"), rs.getString("moyen"), rs.getDate("date"), new Membre(rs.getInt("id_membre")), new Projet(rs.getInt("id_projet")), rs.getDouble("somme")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FinancementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return financements;
    }
    
}
