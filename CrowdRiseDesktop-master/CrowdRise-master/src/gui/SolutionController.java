/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Dao.IdeeDAO;
import Dao.ListeCompetencesDao;
import Dao.MembreDao;
import Dao.SolutionDao;
import Entity.Competence;
import Entity.CompetenceEnum;
import Entity.Idee;
import Entity.Membre;
import Entity.Solution;
import gui.controllers.ConsulterIdeeController;
import gui.controllers.IdeeAddController;
import gui.controllers.UpdateIdeeController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author chocho
 */
public class SolutionController implements Initializable {

    @FXML
    private TextArea taDescription;
    @FXML
    private Button addCompetence1;
    @FXML
    private AnchorPane LeftAP;
    @FXML
    private AnchorPane rightAP;
    @FXML
    private VBox SolutionContainer;
    @FXML
    private Pane swapPane;
    @FXML
    private Button Ds;
    @FXML
    private Button Dv;
    @FXML
    private ImageView AC1IV;
    @FXML
    private SplitPane splitPane;
    @FXML
    private Text text;
    @FXML
    private Button saveBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Slider slider;
    @FXML
    private Label sliderLabel;
    @FXML 
    private Label statutTache;
    @FXML
    private Button delteBtn;
    @FXML
    private ComboBox<String> compCombo;
    @FXML
    private CheckBox cbConfOwner;
    
    
    private Pane currentSolPane;
    private Solution currentSol = new Solution();

    private ObservableList<Solution> data;
    
    
    private SolutionDao sdao = new SolutionDao();
    private final double solHeight = 50;
    private String DVText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit,"
            + " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
            + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo"
            + " consequat.";
    private String DSText = "sed quia non numquam eius modi tempora incidunt ut labore "
            + "et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, "
            + "quis nostrum exercitationem ullam corporis suscipit laboriosam.";
    private String comp;
    private IdeeDAO idao;
    private MembreDao mdao = new MembreDao();
    private float budget;
    private int IdReferer;
    private static Idee idee;
    private ListeCompetencesDao lcdao = new ListeCompetencesDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        lockSplitPane();
        statutTache.setVisible(false);
        SolutionContainer = new VBox(5f);
        
        List<String> compList = new ArrayList<>(lcdao.displayAll());
        compCombo.getItems().addAll(0, compList);
        
        data = FXCollections.observableArrayList();
        
        if(crowdrise.main.referer.equals(crowdrise.main.url_idee_add)){
            IdReferer = 0;
            idee = IdeeAddController.idee;
        }
        if(crowdrise.main.referer.equals(crowdrise.main.url_consulter_idee)){
            idee = ConsulterIdeeController.idee;
            IdReferer = 1;
            saveBtn.setDisable(true);
        }
        if(crowdrise.main.referer.equals(crowdrise.main.url_update_idee)){
            idee = UpdateIdeeController.idee;
            IdReferer = 2;
            saveBtn.setDisable(false);
        }
        intiData();
        
        LeftAP.getChildren().add(SolutionContainer);
        

        //Handling Events
        
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sliderLabel.setText(String.format("%.2f", newValue));
            }

        });

            
        saveBtn.setOnAction(e -> {
            updateDB();
            resetInterface();
        });
        
        editBtn.setOnAction(e->{
            Solution s;
            comp = compCombo.getValue();
            s = new Solution(0,idee,null,Double.parseDouble(sliderLabel.getText().replaceAll(",", ".")),taDescription.getText(),0,0,comp);
            System.out.println("[SolutionController]\n"
                    + s);
            int index;
            index = SolutionContainer.getChildren().indexOf(currentSolPane);
            s.setId(data.get(index).getId());
            data.set(index, s);
            SolutionContainer.getChildren().remove(currentSolPane);
            SolutionContainer.getChildren().add(index, createSolutionBtn(s));
            editBtn.setDisable(true);
            resetInterface();
            updateSolution(s);
        });
        
        delteBtn.setOnAction(e->{
            
            int index = SolutionContainer.getChildren().indexOf(currentSolPane);
            SolutionContainer.getChildren().remove(currentSolPane);
            sdao.deleteById(data.get(index).getId());
            data.remove(index);
            resetInterface();
            
        });
        backBtn.setOnAction(e->{
            crowdrise.main.afficher(crowdrise.main.referer, "Consulter idee", false);
        });
        
        cbConfOwner.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(cbConfOwner.isSelected()){
                    currentSol.setConfirm_owner(1);
                    sdao.updateAllById(currentSol);
                    resetInterface();
                    currentSol.getMembre().setCr((int) (currentSol.getRemuneration() + currentSol.getMembre().getCr()));
                    mdao.updateAllById(currentSol.getMembre());
                }
            }
        });
    }

    private Animation hideSwapPane() {

        Animation collapsePanel = new Transition() {
            {
                setCycleDuration(Duration.millis(500));
            }

            @Override
            protected void interpolate(double fraction) {
                swapPane.setTranslateY(0 * (1 - fraction));
            }
        };

        return collapsePanel;
    }

    private Animation showSwapPane() {

        Animation collapsePanel = new Transition() {
            {
                setCycleDuration(Duration.millis(500));
            }

            @Override
            protected void interpolate(double fraction) {
                swapPane.setTranslateY(-120 * (fraction));
            }
        };

        return collapsePanel;
    }


    public void intiData() {
        SolutionContainer.getChildren().clear();
        
        List<Solution> ls = new ArrayList<>(sdao.displayByIdee(idee));
        data.addAll(ls);
            
        if(IdReferer == 0){
            editBtn.setDisable(true);
        }
        if(IdReferer == 1){
            editBtn.setDisable(true);
        }
        
        for (Solution s : data) {
            Pane tmp = createSolutionBtn(s);
            VBox.setMargin(tmp, new Insets(5));
            SolutionContainer.getChildren().add(tmp);
        }
        delteBtn.setVisible(false);
        
    }
    public void updateData(Solution s) {
        
        data.add(s);
        Pane tmp = createSolutionBtn(s);
        VBox.setMargin(tmp, new Insets(5));
        SolutionContainer.getChildren().add(tmp);
        
    }
    

    public Pane createSolutionBtn(Solution S) {

        Pane p = new Pane();
        p.setPrefSize(190, solHeight);
        p.setStyle("-fx-background-color : white;\n"
                + "-fx-border-width : 2px;\n"
                + "-fx-border-color : #3498DB;");
        //Handle Mouse Event
        p.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                taDescription.setText(S.getTache());
                compCombo.setValue(S.getCompetence());
                slider.setValue(S.getRemuneration());
                rightAP.setDisable(false);
                editBtn.setDisable(false);
                saveBtn.setDisable(true);
                currentSolPane = p ;
                currentSol = S;
                delteBtn.setVisible(true);
                statutTache.setVisible(false);
                cbConfOwner.setDisable(true);
                System.out.println(S.getMembre().getId());
                if(S.getMembre().getId() != 0){
                    if(S.getConfirm_solver() == 1){
                        statutTache.setText("Le solver a confirmé la Tâche!");
                        statutTache.setStyle("-fx-text-fill:green");
                        statutTache.setVisible(true);
                        if(S.getConfirm_owner() == 1){
                            rightAP.setDisable(true);
                        }else{
                            cbConfOwner.setDisable(false);
                        }
                        
                    }
                    else{
                        statutTache.setText("Cette tache est en cours de réalisation");
                        statutTache.setStyle("-fx-text-fill:#e86d6d");
                        statutTache.setVisible(true);
                    }
                    
                }
                
            }

        });
        String ch = S.getTache();
        if (ch.length() > 20) {
            ch = S.getTache().substring(0, 13) + "...";
        } else {
            ch = S.getTache();
        }

        Label tache = new Label(ch);
        tache.setPadding(new Insets(10));
        tache.setLayoutY(7);
        
        Circle indicator = new Circle(180, 27, 5);
        if (S.getConfirm_owner() * S.getConfirm_solver() == 0) {
            indicator.setFill(Color.LIGHTSALMON);
        } else {
            indicator.setFill(Color.LIGHTGREEN);
        }
        

        p.getChildren().addAll(tache, indicator);
        return p;
    }

    public void lockSplitPane() {
        LeftAP.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.25));
        rightAP.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.70));
    }

    public void updateDB() {
        Solution s;
        s = new Solution(idee,crowdrise.main.getMembre(),Double.parseDouble(sliderLabel.getText().replaceAll(",", ".")),taDescription.getText(),0,0,compCombo.getValue());
        int i = sdao.addAndGetLastId(s);
        
        s.setId(i);
        updateData(s);
    }
    public void resetInterface(){
        taDescription.setText("");
        compCombo.setValue("C++");
        slider.setValue(0f);
        delteBtn.setVisible(false);
        saveBtn.setDisable(false);
        statutTache.setVisible(false);
        cbConfOwner.setDisable(true);
    }
    public void updateSolution(Solution s){
        sdao.updateAllById(s);
    }

}
