/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Entity.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import utils.DataSource;

/**
 *
 * @author Aymen
 */
public class AdminDao implements Idao<Admin> {

    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;

    public AdminDao() {
        connection=DataSource.getInstance().getConnection();
    }
    
    @Override
    public void add(Admin t) {
        String req="insert into admin (nom,prenom,email,mdp) values(?,?,?,?) ";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMdp());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void deleteById(int id) {
        String req="delete from admin where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAllById(Admin t) {
        String req="update admin set nom=? , prenom=? , email=? , mdp=?, photo=? where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMdp());
            pst.setString(5, t.getPhoto());
            pst.setInt(6, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public List<Admin> displayAll() {
        String req="select * from admin";
        List<Admin> membres=new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next())
            {
                membres.add(new Admin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"),rs.getString("photo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return membres;
    }

    @Override
    public List<Admin> displayByAll(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Admin displayById(int id) {
        String req="select * from admin where id=?";
        Admin admin=new Admin(0);
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next())
            {
                admin=new Admin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"),rs.getString("photo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }
    
    public Admin displayByEmailMdp(String email, String mdp)
    {
        String req="select * from admin where email=? and mdp=?";
        Admin admin=new Admin(0);
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, email);
            pst.setString(2, mdp);
            rs=pst.executeQuery();
            while (rs.next())
            {
                admin=new Admin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"),rs.getString("photo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }
    
    public Admin displayByEmail(String email)
    {
        String req="select * from admin where email=? ";
        Admin admin=new Admin(0);
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, email);
            rs=pst.executeQuery();
            while (rs.next())
            {
                admin=new Admin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"),rs.getString("photo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }
}