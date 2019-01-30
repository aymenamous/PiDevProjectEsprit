package Dao;

import Entity.Idee;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;





/**
 * Created by alaa on 07/02/16.
 */
public class SolutionDao implements Idao<Solution> {

    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;

    public SolutionDao() {
        connection=DataSource.getInstance().getConnection();
    }
    
    @Override
    public void add(Solution solution) {
        String req = "INSERT INTO solution" +
                "(remuneration,tache,confirm_owner,confirm_solver,id_projet,id_membre,comp) " +
                "Values(?,?,?,?,?,?,?)";
        try{
            pst=connection.prepareStatement(req);
            
            pst.setDouble(1,solution.getRemuneration());
            pst.setString(2,solution.getTache());
            pst.setInt(3,solution.getConfirm_owner());
            pst.setInt(4,solution.getConfirm_solver());
            pst.setInt(5,solution.getProjet().getId());
            pst.setInt(6,solution.getMembre().getId());
            pst.setString(7,solution.getCompetence());
            pst.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteById(int id) {
        String req = "delete from solution where id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateAllById(Solution solution) {
        String query = "UPDATE solution SET remuneration=?, tache=?, confirm_owner=?, confirm_solver=?, id_projet=?, comp=? WHERE id=?";
        try {
            pst = connection.prepareStatement(query);
            
            pst.setDouble(1,solution.getRemuneration());
            pst.setString(2,solution.getTache());
            pst.setInt(3,solution.getConfirm_owner());
            pst.setInt(4,solution.getConfirm_solver());
            pst.setInt(5,solution.getProjet().getId());
            
            pst.setString(6,solution.getCompetence());
            pst.setInt(7,solution.getId());
            System.out.println(pst);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SolutionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Solution> displayAll() {
        String req = "select * from solution";
        MembreDao mdao = new MembreDao();
        IdeeDAO idao = new IdeeDAO();
        List<Solution> lSol = new ArrayList<>();
        try {
            pst = connection.prepareStatement(req);
            rs = pst.executeQuery();
            while(rs.next()){
                Membre m = mdao.displayById(rs.getInt("id_membre"));
                Idee i = idao.displayById(rs.getInt("id_projet"));
                Solution s = new Solution(rs.getInt("id"),i,m,
                        rs.getDouble("remuneration"),
                        rs.getString("tache"),
                        rs.getInt("confirm_owner"),
                        rs.getInt("confirm_solver"),
                        rs.getString("competence"));
                lSol.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lSol;
    }

    @Override
    public List<Solution> displayByAll(Solution solution) {
        return null;
    }

    @Override
    public Solution displayById(int id) {
        String req = "select * from solution where id = ?";
        MembreDao mdao = new MembreDao();
        IdeeDAO idao = new IdeeDAO();
        Solution s = null ;
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while(rs.next()){
                Membre m = new Membre();
                Idee i = new Idee();
                m = mdao.displayById(rs.getInt("id_membre"));
                i = idao.displayById(rs.getInt("id_projet"));
                s = new Solution(rs.getInt("id"),i,m,
                        rs.getDouble("remuneration"),
                        rs.getString("tache"),
                        rs.getInt("confirm_owner"),
                        rs.getInt("confirm_solver"),
                        rs.getString("competence"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    
    public List<Solution> displayByIdMembre(int id) {
        String req = "select * from solution where id_membre=?";
        IdeeDAO idao = new IdeeDAO();
        MembreDao mdao = new MembreDao();
        List<Solution> lSol = new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while(rs.next()){
                Membre m = new Membre();
                Idee i = new Idee();
                i = idao.displayById(rs.getInt("id_projet"));
                m = mdao.displayById(rs.getInt("id_membre"));
                Solution s = new Solution(rs.getInt("id"),i,m,
                        rs.getDouble("remuneration"),
                        rs.getString("tache"),
                        rs.getInt("confirm_owner"),
                        rs.getInt("confirm_solver"),
                        rs.getString("comp"));
                lSol.add(s);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lSol;
    }
    
    public List<Solution> displayByIdee(Idee idee){
        IdeeDAO idao = new IdeeDAO();
        MembreDao mdao = new MembreDao();
        String req = "select * from solution where id_projet=?";
        List<Solution> lSol = new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, idee.getId());
            rs=pst.executeQuery();
            while(rs.next()){
                Membre m = new Membre();
                Idee i = new Idee();
                i = idao.displayById(rs.getInt("id_projet"));
                m = mdao.displayById(rs.getInt("id_membre"));
                Solution s = new Solution(rs.getInt("id"),i,m,
                        rs.getDouble("remuneration"),
                        rs.getString("tache"),
                        rs.getInt("confirm_owner"),
                        rs.getInt("confirm_solver"),
                        rs.getString("comp"));
                lSol.add(s);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lSol;
    }
    public List<Solution> displayByAvailableIdee(Idee idee){
        IdeeDAO idao = new IdeeDAO();
        MembreDao mdao = new MembreDao();
        String req = "select * from solution where id_projet=? and (id_membre is null OR id_membre=0)";
        List<Solution> lSol = new ArrayList<>();
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, idee.getId());
            rs=pst.executeQuery();
            while(rs.next()){
                Membre m = new Membre();
                Idee i = new Idee();
                i = idao.displayById(rs.getInt("id_projet"));
                m = mdao.displayById(rs.getInt("id_membre"));
                System.out.println("[SolutionDAO]");
                System.out.println(rs.getInt("id_membre"));
                Solution s = new Solution(rs.getInt("id"),i,m,
                        rs.getDouble("remuneration"),
                        rs.getString("tache"),
                        rs.getInt("confirm_owner"),
                        rs.getInt("confirm_solver"),
                        rs.getString("comp"));
                lSol.add(s);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lSol;
    }
    
    
    public int addAndGetLastId(Solution s){
        String req = "INSERT INTO solution" +
                "(remuneration,tache,confirm_owner,confirm_solver,id_projet,comp) " +
                "Values(?,?,?,?,?,?)";
        try{
            pst=connection.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
            
            pst.setDouble(1,s.getRemuneration());
            pst.setString(2,s.getTache());
            pst.setInt(3,s.getConfirm_owner());
            pst.setInt(4,s.getConfirm_solver());
            pst.setInt(5,s.getProjet().getId());
            pst.setString(6,s.getCompetence());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            rs.first();
            return rs.getInt(1);
        }
        catch(SQLException ex){
            Logger.getLogger(MembreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
    public void updateSolver(Solution s){
        String req ="UPDATE solution set id_membre = ? where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, s.getMembre().getId());
            pst.setInt(2, s.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SolutionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean userCanDelete(int id_projet,int id_membre){
        String req="SELECT count(*) as nb "
                + "FROM `solution` s "
                + "JOIN projet p on s.id_projet=p.id "
                + "JOIN membre m on m.id = p.id_membre "
                + "WHERE p.type='Idee' AND p.id_membre=? AND p.id = ?";
        
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1, id_membre);
            pst.setInt(2, id_projet);
            rs = pst.executeQuery();
            rs.next();
            int nb = rs.getInt("nb");
            if(nb > 0){
                return true;
            }
            else return false;
            
        } catch (SQLException ex) {
            Logger.getLogger(SolutionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
}
