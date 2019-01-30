/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import utils.DataSource;
import java.util.logging.Level;

/**
 *
 * @author Aymen
 */
public class ListeCompetencesDao implements Idao<String> {
    
    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;

    public ListeCompetencesDao() {
        connection=DataSource.getInstance().getConnection();
    }

    @Override
    public void add(String t) {
       String req="insert into nom_competence (nom) values (?)";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, t);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ListeCompetencesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void delete(String t)
    {
        String req="delete from nom_competence where nom=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, t);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ListeCompetencesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAllById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> displayAll() {
        List<String> liste=new ArrayList<>();
        String req="select * from nom_competence";
        try {
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next())
            {
                liste.add(rs.getString("nom"));
                System.out.println(rs.getString("nom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    @Override
    public List<String> displayByAll(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String display(String t)
    {
        String req="select * from nom_competence where nom=?";
        String s="";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1, t);
            rs=pst.executeQuery();
            while(rs.next())
            {
                s=rs.getString("nom");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
}
