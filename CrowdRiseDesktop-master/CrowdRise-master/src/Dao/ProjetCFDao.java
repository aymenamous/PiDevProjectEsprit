/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Entity.Membre;
import Entity.ProjetCF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author Amine Triki
 */
public class ProjetCFDao implements Idao<ProjetCF>{
    
    private Connection connection;

    public ProjetCFDao() {
        connection = DataSource.getInstance().getConnection();
    }   
    

    @Override
    public void add(ProjetCF projetCF) {
        try {
            String req = "insert into projet (nom,debut,fin,description,type,date,budget_actuel,budget_finale,id_membre,statut,imageIdee) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, projetCF.getNom());
            ps.setDate(2, projetCF.getDebut());
            ps.setDate(3, projetCF.getFin());            
            ps.setString(4, projetCF.getDescription());
            ps.setString(5, "ProjetCF");
            ps.setDate(6, projetCF.getDate());
            ps.setDouble(7, projetCF.getBudget_actuel());
            ps.setDouble(8, projetCF.getBudget_final());
            ps.setInt(9, projetCF.getMembre().getId());
            ps.setInt(10, 0);
            ps.setString(11, projetCF.getImage());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProjetCFDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String requete = "delete from projet where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("ProjetCF supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }    
    }

    @Override
    public void updateAllById(ProjetCF projetCF) {
        String requete = "update projet set nom=?, debut=?, fin=?, description=?, budget_actuel=?, budget_finale=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, projetCF.getNom());
            ps.setDate(2, projetCF.getDebut());
            ps.setDate(3, projetCF.getFin());            
            ps.setString(4, projetCF.getDescription());
            ps.setDouble(5, projetCF.getBudget_actuel());
            ps.setDouble(6, projetCF.getBudget_final());
            ps.setInt(7, projetCF.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }    }

    @Override
    public List<ProjetCF> displayAll() {
        List<ProjetCF> projetsCF = new ArrayList<>();
        MembreDao membredao = new MembreDao();
        String requete = "select * from projet where type='ProjetCF' and statut=1";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                ProjetCF projetCF = new ProjetCF();
                projetCF.setId(resultat.getInt(1));
                projetCF.setNom(resultat.getString(2));
                projetCF.setDebut(resultat.getDate(3));
                projetCF.setFin(resultat.getDate(4));               
                projetCF.setDescription(resultat.getString(7));
                projetCF.setDate(resultat.getDate(8));
                projetCF.setBudget_actuel(resultat.getDouble(10)); 
                projetCF.setBudget_final(resultat.getDouble(11));
                projetCF.setMembre(membredao.displayById(resultat.getInt(5)));
                projetCF.setImage(resultat.getString(12));
                projetsCF.add(projetCF);
            }
            return projetsCF;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des projet CrowdFunding " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<ProjetCF> displayByAll(ProjetCF t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProjetCF displayById(int id) {
        String requete = "select * from projet where id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            ProjetCF projetCF = new ProjetCF();
            while (resultat.next()) {

                projetCF.setId(resultat.getInt(1));
                projetCF.setNom(resultat.getString(2));
                projetCF.setDebut(resultat.getDate(3));
                projetCF.setFin(resultat.getDate(4));
               
                projetCF.setDescription(resultat.getString(7));
                projetCF.setDate(resultat.getDate(8));
                projetCF.setBudget_actuel(resultat.getDouble(10)); 
                projetCF.setBudget_final(resultat.getDouble(11));
            }
            System.out.println("Succes");
            return projetCF;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
    
    public List<ProjetCF> displayByIdMembre(int id) {
        List<ProjetCF> projetsCF = new ArrayList<>();
        String requete = "select * from projet where type='ProjetCF' and id_membre=?";
        try {
            PreparedStatement statement = connection.prepareStatement(requete);
            statement.setInt(1, id);
            ResultSet resultat = statement.executeQuery();

            while (resultat.next()) {
                ProjetCF projetCF = new ProjetCF();
                projetCF.setId(resultat.getInt(1));
                projetCF.setNom(resultat.getString(2));
                projetCF.setDebut(resultat.getDate(3));
                projetCF.setFin(resultat.getDate(4));               
                projetCF.setDescription(resultat.getString(7));
                projetCF.setDate(resultat.getDate(8));
                projetCF.setBudget_actuel(resultat.getDouble(10)); 
                projetCF.setBudget_final(resultat.getDouble(11));
                projetCF.setImage(resultat.getString(12)); 
                projetsCF.add(projetCF);
            }
            return projetsCF;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des projet CrowdFunding " + ex.getMessage());
            return null;
        }
    }
    
    public List<ProjetCF> displayByYearMonth(int year, int Month) {
        List<ProjetCF> projetsCF = new ArrayList<>();
        String requete = "select * from projet where type='ProjetCF' and extract(year from date)=? and extract(month from date)=?";
        try {
            PreparedStatement statement = connection.prepareStatement(requete);
            statement.setInt(1, year);
            statement.setInt(2, Month);
            ResultSet resultat = statement.executeQuery();

            while (resultat.next()) {
                ProjetCF projetCF = new ProjetCF();
                projetCF.setId(resultat.getInt(1));
                projetCF.setNom(resultat.getString(2));
                projetCF.setDebut(resultat.getDate(3));
                projetCF.setFin(resultat.getDate(4));               
                projetCF.setDescription(resultat.getString(7));
                projetCF.setDate(resultat.getDate(8));
                projetCF.setBudget_actuel(resultat.getDouble(10)); 
                projetCF.setBudget_final(resultat.getDouble(11)); 
                projetsCF.add(projetCF);
            }
            return projetsCF;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des projet CrowdFunding " + ex.getMessage());
            return null;
        }
    }
    
    public List<ProjetCF> displayListEnAttente(){
        
        List<ProjetCF> PCFs = new ArrayList<>();
        String requete = "select * from projet where statut=? and type='ProjetCF' ";
        try {
            PreparedStatement statement = connection
                    .prepareStatement(requete);
            statement.setInt(1, 0);
            MembreDao membredao = new MembreDao();
            ResultSet resultat = statement.executeQuery();

            while (resultat.next()) {
                ProjetCF ProjCF = new ProjetCF();
                ProjCF.setId(resultat.getInt(1));
                ProjCF.setNom(resultat.getString(2));
                ProjCF.setDebut(resultat.getDate(3));
                ProjCF.setFin(resultat.getDate(4));
                ProjCF.setMembre(membredao.displayById(resultat.getInt(5)));
                ProjCF.setDescription(resultat.getString(7));
                ProjCF.setDate(resultat.getDate(8));
                ProjCF.setBudget_actuel(resultat.getDouble(10)); 
                ProjCF.setBudget_final(resultat.getDouble(11));  
                ProjCF.setImage(resultat.getString(12));
                PCFs.add(ProjCF);
            }
            return PCFs;

        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Projets CF en attente " + ex.getMessage());
            return null;
        }
        
        
    }
    
    public void updateStatutById(int id,int statut){
        String requete = "update projet set statut=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, statut);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
}
