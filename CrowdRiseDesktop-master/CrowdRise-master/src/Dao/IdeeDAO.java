package Dao;

import Entity.Idee;
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

public class IdeeDAO implements Idao<Idee>{
    
    private Connection connection;

    public IdeeDAO() {
        connection = DataSource.getInstance().getConnection();
    }
    
        @Override
    public void add(Idee idee) {
         try {
            String req = "insert into projet (nom,debut,fin,description,type,date,remuneration_totale,imageIdee,id_membre,statut) "
                    + "values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, idee.getNom());
            ps.setDate(2, idee.getDebut());
            ps.setDate(3, idee.getFin());
            ps.setString(4, idee.getDescription());
            ps.setString(5, "idee");
            ps.setDate(6, idee.getDate());
            ps.setDouble(7, idee.getRemuneration_totale());
            ps.setString(8, idee.getImage());
            ps.setInt(9, idee.getMembre().getId());
            ps.setInt(10, 0);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IdeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void deleteById(int id) {
        String requete = "delete from projet where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Idée supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void updateAllById(Idee idee) {
        String requete = "update projet set nom=?, debut=?, fin=?, description=?, remuneration_totale=?, imageIdee=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, idee.getNom());
            ps.setDate(2, idee.getDebut());
            ps.setDate(3, idee.getFin());
            ps.setString(4, idee.getDescription());
            ps.setDouble(5, idee.getRemuneration_totale());
            ps.setString(6, idee.getImage());
            ps.setInt(7, idee.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
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

    @Override
    public List<Idee> displayAll() {
        List<Idee> idees = new ArrayList<>();
        String requete = "select * from projet where statut=? and type='idee'";
        try {
           PreparedStatement statement = connection
                    .prepareStatement(requete);
            statement.setInt(1, 1);
            MembreDao membredao = new MembreDao();
            ResultSet resultat = statement.executeQuery();
            
            while (resultat.next()) {
                Idee idee = new Idee();
                idee.setId(resultat.getInt(1));
                idee.setNom(resultat.getString(2));
                idee.setDebut(resultat.getDate(3));
                idee.setFin(resultat.getDate(4));
                idee.setMembre(membredao.displayById(resultat.getInt(5)));
                idee.setDescription(resultat.getString(7));
                idee.setDate(resultat.getDate(8));
                idee.setRemuneration_totale(resultat.getDouble(9)); 
                idee.setImage(resultat.getString(12));
                idees.add(idee);
            }
            return idees;
            
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des idées " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Idee> displayByAll(Idee idee) {
       return null ;
    } 

    @Override
    public Idee displayById(int id) {
         String requete = "select * from projet where id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            Idee idee = new Idee();
            MembreDao membredao = new MembreDao();
            while (resultat.next()) {
                idee.setId(resultat.getInt(1));
                idee.setNom(resultat.getString(2));
                idee.setDebut(resultat.getDate(3));
                idee.setFin(resultat.getDate(4));
                idee.setMembre(membredao.displayById(resultat.getInt(5)));
                idee.setDescription(resultat.getString(7));
                idee.setDate(resultat.getDate(8));
                idee.setRemuneration_totale(resultat.getDouble(9)); 
                idee.setImage(resultat.getString(12));
                
            }
            System.out.println("Succes");
            return idee;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
    
    public List<Idee> displayByIdMembre(int id) {
        List<Idee> idees = new ArrayList<>();
        MembreDao membredao = new MembreDao();
        String requete = "select * from projet where id_membre=? and type='idee' and statut=1 ";
        try {
            PreparedStatement statement = connection
                    .prepareStatement(requete);
            statement.setInt(1, id);
            
            ResultSet resultat = statement.executeQuery();

            while (resultat.next()) {
                Idee idee = new Idee();
                idee.setId(resultat.getInt(1));
                idee.setNom(resultat.getString(2));
                idee.setDebut(resultat.getDate(3));
                idee.setFin(resultat.getDate(4));
                idee.setMembre(membredao.displayById(resultat.getInt(5)));
                idee.setDescription(resultat.getString(7));
                idee.setDate(resultat.getDate(8));
                idee.setRemuneration_totale(resultat.getDouble(9)); 
                idee.setImage(resultat.getString(12));
                idees.add(idee);
            }
            return idees;
            
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des idées " + ex.getMessage());
            return null;
        }
    }
    
    public List<Idee> displayListEnAttente(){
        
        List<Idee> idees = new ArrayList<>();
        String requete = "select * from projet where statut=? and type='idee' ";
        try {
            PreparedStatement statement = connection
                    .prepareStatement(requete);
            statement.setInt(1, 0);
            MembreDao membredao = new MembreDao();
            ResultSet resultat = statement.executeQuery();

            while (resultat.next()) {
                Idee idee = new Idee();
                idee.setId(resultat.getInt(1));
                idee.setNom(resultat.getString(2));
                idee.setDebut(resultat.getDate(3));
                idee.setFin(resultat.getDate(4));
                idee.setMembre(membredao.displayById(resultat.getInt(5)));
                idee.setDescription(resultat.getString(7));
                idee.setDate(resultat.getDate(8));
                idee.setRemuneration_totale(resultat.getDouble(9)); 
                idee.setImage(resultat.getString(12));
                idees.add(idee);
            }
            return idees;

        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des idées " + ex.getMessage());
            return null;
        }
    }
    
    public int addAndgetLastInsertedIdee(Idee idee){
         try {
            String req = "insert into projet (nom,debut,fin,description,type,date,remuneration_totale,imageIdee,id_membre,statut) "
                    + "values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, idee.getNom());
            ps.setDate(2, idee.getDebut());
            ps.setDate(3, idee.getFin());
            ps.setString(4, idee.getDescription());
            ps.setString(5, "idee");
            ps.setDate(6, idee.getDate());
            ps.setDouble(7, idee.getRemuneration_totale());
            ps.setString(8, idee.getImage());
            ps.setInt(9, idee.getMembre().getId());
            ps.setInt(10, 0);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.first();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(IdeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0;
    }

    
}
