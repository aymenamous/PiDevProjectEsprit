/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Dao.ProjetCFDao;
import Dao.SolutionDao;
import Entity.Membre;
import Entity.Solution;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author Giliwawa
 */
public class MySolutionController implements Initializable {
    
    private ProjetCFDao prdao = new ProjetCFDao();
    private SolutionDao sdao = new SolutionDao();
    
    
    @FXML GridPane GP ;
    
    List<Solution> data = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Pane tmp = new Pane();
        int col = 0,row = 0;
        
        data = sdao.displayByIdMembre(crowdrise.main.getMembre().getId());
        System.out.println(data);
        for(int i = 0 ; i < data.size();i++ ){
            System.out.println("("+row+","+col+") ("+((i+1)% 3)+")("+(i+1)+")");
            tmp = createCard(data.get(i));
            GP.add(tmp, col, row);
            if((i+1) % 3 == 0){
                col = 0;
                row ++;
            }
            else{
                col++;
            }
            
        }
    }    
    
    public Pane createCard(Solution s){
        boolean cbPrevState = false;
        Pane p = new Pane();
        p.setPrefSize(100, 150);
        p.setStyle("-fx-background-color : #BADDF0;\n"
                + "-fx-border-width : 2px;\n"
                + "-fx-border-color : #3498DB;");
        
        String container ="";
        
        container = s.getProjet().getNom();
        Text Title = new Text(container);
        Title.setLayoutY(20);
        Title.setLayoutX(10);
        Title.setFont(Font.font("Lucida Grande", FontWeight.BOLD, 16));
        
        container = s.getTache();
        
        Text desc = new Text(container);
        desc.setWrappingWidth(130);
        desc.setLayoutX(10);
        desc.setLayoutY(50);
        
        CheckBox done = new CheckBox("Done");
        
        done.setLayoutX(100);
        done.setLayoutY(155);
        cbPrevState = 1 == s.getConfirm_solver();
        done.setSelected(cbPrevState);
        
        //event handling for child nodes
        done.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(done.isSelected()){
                    done.setStyle("-fx-text-fill: green");
                    s.setConfirm_solver(1);
                }else{
                    done.setStyle("-fx-text-fill: black");
                    s.setConfirm_solver(0);
                }
                System.out.println("$$$$$" + s.getConfirm_solver());
                sdao.updateAllById(s);
            }
        });
//        done.setOnAction(e -> {
//            if(done.isSelected()){
//                s.setConfirm_solver(1);
//            }else{
//                s.setConfirm_solver(0);
//            }
//            
//            
//        });
        
        p.getChildren().addAll(Title,desc,done);
        
        
        return p;
    }
}
