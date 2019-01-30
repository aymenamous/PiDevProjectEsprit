/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.FinancementDao;
import Dao.MembreDao;
import Dao.ProjetCFDao;
import Dao.VoteDao;
import Entity.Financement;
import Entity.Membre;
import Entity.Projet;
import crowdrise.IntegerTextField;
import Entity.ProjetCF;
import Entity.vote;
import com.jfoenix.controls.JFXButton;
import crowdrise.main;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import com.jfoenix.controls.JFXNodesList;
import de.jensd.fx.fontawesome.AwesomeIcon;
import de.jensd.fx.fontawesome.Icon;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
/**
 * FXML Controller class
 *
 * @author Amine Triki
 */
public class ConsulterProjetCFController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    AnchorPane anchorpane;
    
    @FXML
    AnchorPane AnchComment;
    
    @FXML
    private JFXNodesList lstNode;
    
    @FXML 
    private Label Debut;

    @FXML
    private Label Fin;

    @FXML
    private Label Datemise;

    @FXML
    private Label userName;

    

    @FXML
    private Label BudgetAct;

    @FXML
    private Label BudgetFin;

    @FXML
    private ProgressIndicator prgressIndic;

    @FXML
    private ImageView ImageMembre;

    @FXML
    private TextFlow Desc;

    @FXML
    private Text txt;

    @FXML
    private ImageView imageD;

    @FXML
    List<vote> listevote;
 

    boolean reverse;

    @FXML AnchorPane pk;
    
    @FXML
    ProgressBar pb;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final Stage dialog = new Stage(StageStyle.TRANSPARENT);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(app_stage);
        ToggleGroup x = new ToggleGroup();

        VBox vbox = new VBox();

        RadioButton sans_retour = new RadioButton("Financer sans retour");
        sans_retour.setUserData("sans retour");
        sans_retour.setToggleGroup(x);

        RadioButton cadeau = new RadioButton("Financer avec un petit cadeau");
        cadeau.setUserData("cadeau");
        cadeau.setToggleGroup(x);

        RadioButton produit = new RadioButton("Financer en contre partie le produit");
        produit.setUserData("produit");
        produit.setToggleGroup(x);
        TextField txt = new IntegerTextField();
        HBox hbx = new HBox();

        Button Confirmer = new Button("Confirmer");
        Confirmer.defaultButtonProperty().setValue(Boolean.TRUE);
        Confirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                java.util.Date daa= new java.util.Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");        
                java.sql.Date sDate1 = new java.sql.Date(daa.getTime());        
                Financement f= new Financement(x.getSelectedToggle().getUserData().toString(), sDate1, main.getMembre(), ListProjetsCFController.ProjetCF2, Double.parseDouble(txt.getText()));
                FinancementDao fdao= new FinancementDao();
                fdao.add(f);
                ProjetCFDao pdao= new ProjetCFDao();
                Membre mbr=ListProjetsCFController.ProjetCF2.getMembre();                
                mbr.setCr(mbr.getCr()+Integer.parseInt(txt.getText()));
                MembreDao mbrD=new MembreDao();
                mbrD.updateAllById(mbr);
                mbr=main.getMembre();
                mbr.setCr(mbr.getCr()-Integer.parseInt(txt.getText()));
                mbrD.updateAllById(mbr);
                ListProjetsCFController.ProjetCF2.setBudget_actuel(ListProjetsCFController.ProjetCF2.getBudget_actuel()+Double.parseDouble(txt.getText()));
                pdao.updateAllById(ListProjetsCFController.ProjetCF2);
                app_stage.getScene().getRoot().setEffect(null);
                dialog.close();
                main.initBase(anchorpane, main.url_conslt_CF);
            }
        });

        Button Cancel = new Button("Cancel");
        Cancel.cancelButtonProperty().setValue(Boolean.TRUE);
        Cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                app_stage.getScene().getRoot().setEffect(null);
                dialog.close();
            }
        });

        hbx.setSpacing(20);
        hbx.getChildren().addAll(Confirmer, Cancel);

        

        FadeTransition ft = new FadeTransition(Duration.millis(1), txt);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();

        vbox.getChildren().addAll(sans_retour, cadeau, produit, txt, hbx);
        vbox.getStyleClass().addAll("modal-dialog", "modal-dialog:pressed");

        Scene sc = new Scene(vbox);
        sc.setFill(null);
        dialog.setScene(sc);

        dialog.getScene().getStylesheets().add(getClass().getResource("modal-dialog.css").toExternalForm());

        final Node root1 = dialog.getScene().getRoot();

        x.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (x.getSelectedToggle() != null) {
                    System.out.println(x.getSelectedToggle().getUserData().toString());
                    FadeTransition ft = new FadeTransition(Duration.millis(3000), txt);
                    ft.setFromValue(0.0);
                    ft.setToValue(1.0);
                    ft.play();
                    txt.setPromptText("Saisir votre montant");
                }
            }
        });

        // allow the dialog to be dragged around.
        final Delta dragDelta = new Delta();
        root1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = dialog.getX() - mouseEvent.getScreenX();
                dragDelta.y = dialog.getY() - mouseEvent.getScreenY();
            }
        });
        root1.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialog.setX(mouseEvent.getScreenX() + dragDelta.x);
                dialog.setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });
        app_stage.getScene().getRoot().setEffect(new BoxBlur());
        dialog.show();

    }

    

    class Delta {

        double x, y;
    }

    @FXML
    public void onclickImage(MouseEvent even) {
        if (!reverse) {
            like();
            checkMembreVote();
            
            

        } else {
            dislike();
            checkMembreVote();
            
            
        }

    }

    @FXML
    void checkMembreVote() {
        VoteDao vdao = new VoteDao(); 
       java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    //  vote v = new vote(date, new Membre(2), new Projet(1));
      //selectionner le membre connectÃ© et le projet 
      vote v=new vote(date,main.getMembre(),ListProjetsCFController.getProjetCF2());
        int existe = vdao.ExisteMembre(main.getMembre().getId());
        int existeP=vdao.ExisteProjet(ListProjetsCFController.getProjetCF2().getId());
        System.out.println(ListProjetsCFController.ProjetCF2.getId());

        System.out.println("existeee"+existe);
        //l'id de la personne connectÃ©e 
        //    if (existe == main.getMembre().getId()) {
        if (existe !=0 && existeP!=0) {
            Image imgD = new Image(getClass().getResource("/gui/utility/like.png").toExternalForm());
            imageD.setImage(imgD);
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.setAutoReverse(true);
            KeyValue kvUp2 = new KeyValue(imageD.rotateProperty(), 180);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(500), kvUp2);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            reverse=true;

        } else {
            Image imgD = new Image(getClass().getResource("/gui/utility/like.png").toExternalForm());
            imageD.setImage(imgD);
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.setAutoReverse(true);
            KeyValue kvUp2 = new KeyValue(imageD.rotateProperty(), 0);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(700), kvUp2);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            reverse=false;
            
        }

    }

    void like() {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        System.out.println(ListProjetsCFController.getProjetCF2().getId());
        vote v = new vote(date, new Membre(main.getMembre().getId()),new Projet(ListProjetsCFController.getProjetCF2().getId()));
        VoteDao vdao = new VoteDao();
        vdao.add(v);
        
        pb.setProgress(0.2);
        int countTotal=vdao.countTotal();
        int count=vdao.count(ListProjetsCFController.ProjetCF2);
        System.out.println("count"+count);
         System.out.println("count"+count);

        
        
    }

    void dislike() {

        VoteDao vdao = new VoteDao();
        vdao.deleteByMembreProjet(ListProjetsCFController.getProjetCF2(), main.getMembre());
                int count=vdao.count(ListProjetsCFController.ProjetCF2);

        System.out.println("total"+count);
       pb.setProgress(count*0.2);

        
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        Submenu();
        ProjetCF pCF = ListProjetsCFController.ProjetCF2;
        loadComment(pCF.getId());
        BudgetAct.setText("" + pCF.getBudget_actuel());
        BudgetFin.setText("" + pCF.getBudget_final());
        Datemise.setText("" + pCF.getDate());
        Debut.setText("" + pCF.getDebut());
        Fin.setText("" + pCF.getFin());
        txt.setText(pCF.getDescription());
        //System.out.println("rfzdsfz "+pCF.getMembre().getPhoto());
        try {
            ImageMembre.setImage(new Image(getClass().getResourceAsStream(pCF.getMembre().getPhoto()), 102, 102, false, true));
        } catch (Exception e) {
            System.out.println("Impossible de charger l'image");
            ImageMembre.setImage(new Image(getClass().getResourceAsStream("/ressources/profile.jpg"), 102, 102, false, true));
        }
        
        
        
        
        //ImageMembre.setImage(img);
        
                     
        prgressIndic.setProgress(pCF.getBudget_actuel() / pCF.getBudget_final());
        
        
        checkMembreVote();
        VoteDao vdao=new VoteDao();
         int countTotal=vdao.countTotal();
        int count=vdao.count(ListProjetsCFController.ProjetCF2);
        System.out.println("count"+count);
                System.out.println("countT"+countTotal);

        if(countTotal==0)
            pb.setProgress(0);
        else 
        if(countTotal==2*count)
            pb.setProgress(0.5);
        else 
           pb.setProgress(count*0.2);
      
        
        
        
        
      

    }
    
 
    private void Submenu() {
        try {
			JFXButton submenubt = new JFXButton();
                        Icon i= new Icon(AwesomeIcon.GEARS,"1em",";","error");			
			submenubt.setGraphic(i);
                        submenubt.setTooltip(new Tooltip("Submenu"));
			submenubt.setButtonType(JFXButton.ButtonType.RAISED);
			submenubt.getStyleClass().add("animated-option-button");
			
			JFXButton ModifBt = new JFXButton();                        
                        ModifBt.setGraphic(new Icon(AwesomeIcon.EDIT,"1em",";","error"));
			ModifBt.setTooltip(new Tooltip("Modifier"));
			ModifBt.setButtonType(JFXButton.ButtonType.RAISED);
			ModifBt.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
                        if(!ListProjetsCFController.toogleValue){
                            ModifBt.setVisible(false);
                        }
                        ModifBt.setOnAction((event)->{
                            main.initBase(anchorpane, main.url_modif_CF);
                        });
			
			JFXButton SuppBt = new JFXButton();
                        SuppBt.setTooltip(new Tooltip("Supprimer"));
                        SuppBt.setGraphic(new Icon(AwesomeIcon.REMOVE,"1em",";","error"));
			SuppBt.setButtonType(JFXButton.ButtonType.RAISED);
			SuppBt.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
                        if(!ListProjetsCFController.toogleValue){
                            SuppBt.setVisible(false);
                        }
                        SuppBt.setOnAction((event)->{                            
                            ProjetCFDao PDao= new ProjetCFDao();
                            PDao.deleteById(ListProjetsCFController.ProjetCF2.getId());
                            main.initBase(anchorpane, main.url_liste_projet_cf);
                        });
                        
                        JFXButton AjoutBt = new JFXButton();                        		
			AjoutBt.setGraphic(new Icon(AwesomeIcon.PLUS,"1em",";","error"));	
                        AjoutBt.setTooltip(new Tooltip("Ajouter"));
			AjoutBt.setButtonType(JFXButton.ButtonType.RAISED);
			AjoutBt.getStyleClass().add("animated-option-button");
                        AjoutBt.setOnAction((event)->{
                            main.initBase(anchorpane, main.url_ajout_CF);
                        });	
                        
			lstNode.setSpacing(10);
			lstNode.addAnimatedNode(submenubt, (expanded)->{ return new ArrayList<KeyValue>(){{ add(new KeyValue(i.rotateProperty(), expanded? 360:0 , Interpolator.EASE_BOTH));}};});
			if(ListProjetsCFController.toogleValue){
                            lstNode.addAnimatedNode(ModifBt);
                            lstNode.addAnimatedNode(SuppBt);
                        }   
                        lstNode.addAnimatedNode(AjoutBt);
			lstNode.setRotate(180);
			
			
			
			
			
			//scene.getStylesheets().add(ConsulterProjetCFController.class.getResource("jfoenix-components.css").toExternalForm());
			

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void loadComment(int id) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/gui/views/Commentaire.fxml"));
            AnchorPane content;
            
            CommentaireController.id=id;
            content = loader.load();
            content.setPrefWidth(400);
            content.setMaxWidth(300);
            pk.getChildren().add(content);
            pk.setPrefHeight(content.getPrefHeight());
        } catch (IOException ex) {
            Logger.getLogger(ConsulterIdeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
