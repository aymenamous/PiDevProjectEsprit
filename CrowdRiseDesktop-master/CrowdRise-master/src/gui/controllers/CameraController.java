/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.AdminDao;
import Dao.MembreDao;
import crowdrise.main;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class CameraController implements Initializable {

    @FXML
    Button button;
    @FXML
    ImageView currentFrame;
    private ScheduledExecutorService timer;
    private VideoCapture capture = new VideoCapture();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startCamera();
    }

    public Image mat2Image(Mat frame) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", frame, buffer);
        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }

    public Image grabFrame() {
        Image imageToShow = null;
        Mat frame = new Mat();
        if (this.capture.isOpened()) {
            try {
                this.capture.read(frame);
                if (!frame.empty()) {
                    imageToShow = mat2Image(frame);
                }
            } catch (Exception e) {
                System.err.println("Exception during the image elaboration: " + e);
            }
        }
        return imageToShow;
    }

    public void startCamera() {
        
            this.capture.open(0);
            if (this.capture.isOpened()) {
                

                Runnable frameGrabber = new Runnable() {

                    @Override
                    public void run() {
                        Image imageToShow = grabFrame();
                        currentFrame.setImage(imageToShow);
                    }
                };

                this.timer = Executors.newSingleThreadScheduledExecutor();
                this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);

                this.button.setText("Capturer");
            } else {
                System.err.println("Impossible to open the camera connection...");
            }
        }
    @FXML
    public void takePicture(ActionEvent event)
    {
            try {
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }
            this.capture.release();
            File f1 = new File("photo");
            File file;
            int i = 0;
            do {
                i++;
                file = new File(f1.getAbsolutePath(), i + ".png");
                if (!file.exists()) {
                    i = -1;
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(currentFrame.getImage(), null), "png", file);
                        String path = "file:" + file.getAbsolutePath();
                        path = path.replace("\\", "/");
                        if (main.getMembre().getId()!=0)
                        {
                            MembreDao mdao=new MembreDao();
                            main.getMembre().setPhoto(path);
                            mdao.updateAllById(main.getMembre());
                            Image img = new Image(path, 154, 145, false, true);
                            ProfilController.imv.setImage(img);
                        }
                        else
                        {
                            AdminDao adao=new AdminDao();
                            main.getAdmin().setPhoto(path);
                            adao.updateAllById(main.getAdmin());
                            Image img = new Image(path, 154, 145, false, true);
                            ProfilAdminController.imv.setImage(img);
                        }
                        
                        
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } while (i != -1);
            this.currentFrame.setImage(null);
            main.camStage.close();
    }
    
    public void fermer(ActionEvent event)
    {
        try {
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }
        this.capture.release();
        main.camStage.close();
    }
}

