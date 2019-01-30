/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crowdrise;

import Dao.CommentaireDao;
import Dao.FinancementDao;
import Dao.MembreDao;
import Dao.ReclamationDao;
import Dao.SolutionDao;
import Entity.Admin;
import Entity.Commentaire;
import Entity.Membre;
import Entity.Projet;
import Entity.Reclamation;
import Entity.Solution;
import com.jfoenix.controls.JFXDecorator;
import gui.controllers.AuthentificationController;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Mixer;
import org.opencv.core.Core;

/**
 *
 * @author Aymen
 */
public class main extends Application {

    public static Stage stage;
    public static Stage camStage;
    private static Membre membre;
    private static Admin admin;
    public static main mainApp;
    public static String url_competence;
    public static String url_authentification;
    public static String url_inscription;
    public static String url_liste_membre;
    public static String url_profil;
    public static String url_profil_admin;
    public static String url_base_admin;
    public static String url_base;
    public static String url_accueil;
    public static String url_accueil_admin;
    public static String url_liste_projet_cf;
    public  static String  url_commentaire;
    public  static String  url_reclamation;
    public static String url_comm_signale;

    public static String url_liste_idees;
    public static String url_liste_mes_idees;
    public static String url_idee_add;
    public static String url_update_idee;
    public static String url_consulter_idee;
    public static String url_probleme;
    public static String url_side_menu;
    public static String url_list_reclamation;
    public static String url_Mail;
    public static String url_solution;
    public static String url_admin_probleme;
    //used to get the url of the previous window for some tests. 
    public static String referer;
    public static String url_ListeIdeeAdmin;
    public static String url_ajout_CF;
    public static String url_Base;
    public static String url_main_home_page;
    public static String url_modif_CF;
    public static String url_conslt_CF;
    public static String url_my_sol;
    public static Mixer mixer;
    public static Clip clip;
    public static String url_chart;
    public static String url_taches;

    public main() {
        mainApp = this;
        url_competence = "/gui/views/Competence.fxml";
        url_authentification = "/gui/views/Authentification.fxml";
        url_inscription = "/gui/views/Inscription.fxml";
        url_liste_membre = "/gui/views/ListeMembres.fxml";
        url_profil = "/gui/views/Profil.fxml";
        url_profil_admin = "/gui/views/ProfilAdmin.fxml";
        url_base_admin="/gui/views/BaseAdmin.fxml";
        url_base="/gui/views/Base.fxml";
        url_accueil="/gui/views/Accueil.fxml";
        url_accueil_admin="/gui/views/Accueil.fxml";
        url_liste_projet_cf="/gui/views/ListProjetsCF.fxml";
        url_liste_idees="/gui/views/ListeIdees.fxml";
        url_liste_mes_idees="/gui/views/ListeMesIdees.fxml";
        url_idee_add="/gui/views/IdeeAdd.fxml";
        url_update_idee="/gui/views/UpdateIdee.fxml";
        url_consulter_idee="/gui/views/ConsulterIdee.fxml";
        url_probleme= "/gui/views/Probleme.fxml";
        url_side_menu="/gui/views/SideMenu.fxml";
        url_commentaire="/gui/views/commentaire.fxml";
        url_reclamation="/gui/views/Reclamation.fxml";
         url_list_reclamation="/gui/views/ListReclamation.fxml";
        url_solution= "/gui/Solution.fxml";
        url_ListeIdeeAdmin="/gui/views/ListeIdeesAdmin.fxml";
        url_Base="/gui/views/Base.fxml";
        url_ajout_CF="/gui/views/AjoutProjetCF.fxml";
        url_admin_probleme="/gui/views/AdminListeProbleme.fxml";
        url_main_home_page ="/gui/main.fxml";
        url_modif_CF="/gui/views/ModifProjetCF.fxml";
        url_conslt_CF="/gui/views/ConsulterProjetCF.fxml";
        url_comm_signale="/gui/views/ListCommenSignale.fxml";
        url_Mail="/gui/views/MailApi.fxml";
        url_my_sol = "/gui/mySolution.fxml";
        url_chart="/gui/views/Charts.fxml";
        url_taches="/gui/choisirSolution.fxml";
        
    }

    @Override
    public void start(Stage primaryStage) {
        
        //afficher(url_competence, "Gestion compétence",true);
        afficher(url_authentification, "Se connecter", true);
        /*Media m = new Media(Paths.get("C:/Users/hadhe/Downloads/muscc.mp3").toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(m);
        mediaPlayer.play();*/
        
       
    } 
    
   

    public static void afficher(String url, String title, boolean resizable) {
        try {
            if (stage!=null)
            {
                stage.close();
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource(url));
            AnchorPane content = loader.load();            
            //Scene sc = new Scene(content);
            stage=new Stage();
            Scene sc = new Scene(new JFXDecorator(stage, content));
            
            sc.getStylesheets().add("/ressources/jfoenix-main-demo.css");
            
            stage.setScene(sc);
            stage.setTitle(title);
            stage.setResizable(resizable);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void afficherCam() {
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/gui/views/Camera.fxml"));
            AnchorPane content = loader.load();            
            camStage=new Stage();
            camStage.initStyle(StageStyle.TRANSPARENT);
            Scene sc = new Scene(content);
            sc.getStylesheets().add("/ressources/jfoenix-main-demo.css");
            camStage.setScene(sc);
            camStage.show();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void initBase(AnchorPane base, String url) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource(url));
        try {
            base.getChildren().clear();
            AnchorPane content = loader.load();
            base.getChildren().add(content);   
            AnchorPane.setTopAnchor(content,0.0);
            AnchorPane.setBottomAnchor(content,0.0);
            AnchorPane.setLeftAnchor(content,0.0);
            AnchorPane.setRightAnchor(content,0.0);
            
            
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void initAjoutSolution(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/gui/Solution.fxml"));
            AnchorPane listeM =  loader.load();
            Scene sc=new Scene(listeM);
            stage.setScene(sc);
            stage.show();
            stage.setTitle("Ajouter les tâches");
            stage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Membre getMembre() {
        return membre;
    }

    public static void setMembre(Membre membre) {
        main.membre = membre;
    }

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin admin) {
        main.admin = admin;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
      
        
      
    }

}
