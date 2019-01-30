/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crowdrise;

import Dao.CommentaireDao;
import Dao.ReclamationDao;
import Dao.VoteDao;
import Entity.Commentaire;
import Entity.Membre;
import Entity.Projet;
import Entity.Reclamation;
import Entity.vote;
import java.awt.Insets;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.text.html.ListView;

public class main2 extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;

        initCommenter();

    }

    public static void initCommenter() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/gui/ListCommenSignale.fxml"));
            AnchorPane authentification = loader.load();
            Scene sc = new Scene(authentification);
            stage.setScene(sc);
            stage.show();
            stage.setTitle("Commenter");
            stage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
       
          launch(args);
        
          
    }

}
