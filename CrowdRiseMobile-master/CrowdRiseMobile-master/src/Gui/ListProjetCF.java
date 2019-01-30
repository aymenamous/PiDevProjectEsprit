/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Commentaire;
import Entity.ProjetCF;
import Handler.CommentaireHandler;
import Handler.ProjetCFHandler;
import crowdrisemobile.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.control.ToneControl;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Giliwawaa
 */
public class ListProjetCF extends Canvas implements Runnable, CommandListener {

    int selector = 0;

    String url = "";
    ProjetCF[] projets;
    String Error;
    String Description = "";
    boolean isError = false;
    
    int thumbX = 0;
    int thumbY = 0;
    int w = getWidth();
    int h = getHeight();
    boolean slideUp = false;
    Timer tm;
    Animation anim;//timerTask
    public static final int MEMBRE = 0;
    public static final int ADMIN = 1;
    int memberType;
    
    
    Command retour = new Command("Retour", Command.BACK, 0);
    Command delete = new Command("Supprimer", Command.SCREEN, 0);
    Command commenter = new Command("commenter", Command.SCREEN, 1);
    Command voter = new Command("Voter", Command.SCREEN, 2);
    Command financer = new Command("Financer", Command.SCREEN, 3);
    
    public ListProjetCF(int listType) {
        
        if(listType == 0){
            url = "http://localhost/CrowdRise/projetcf/select_ProjetCf.php";
            memberType = 0;
        }
        if(listType == 1){
            url = "http://localhost/CrowdRise/projetcf/select_ProjetCf_id.php?id_membre="+Midlet.membre.getId();
            memberType = 1;
        }
        run();
        tm = new Timer();
        anim = new Animation(this);
        tm.scheduleAtFixedRate(anim, 0, 10);
        this.addCommand(retour);
        this.addCommand(delete);
        this.addCommand(commenter);
        this.addCommand(voter);
        this.addCommand(financer);
        this.setCommandListener(this);
    }

    public ListProjetCF(String keyword) {
        url = "http://localhost/CrowdRise/projetcf/select_ProjetCf_recherche.php?keyword="+keyword;
        run();
        tm = new Timer();
        anim = new Animation(this);
        tm.scheduleAtFixedRate(anim, 0, 10);
        this.addCommand(retour);
        this.setCommandListener(this);
    }
    

    protected void paint(Graphics g) {
        if (projets[selector] != null) {

            try {
                ProjetCF currentP = projets[selector];
                //System.out.println(currentP);
                Image thumbnail;
                thumbnail = Image.createImage("/"+currentP.getImage());
                thumbnail = Image.createImage(thumbnail, 0, 0, w, h, 0);
                Description = wrapText(currentP.getDescription());
                System.out.println(Description);
                g.setColor(255, 255, 255);
                g.fillRect(0, 0, w, h);
                g.setColor(0, 0, 0);
                g.drawString("Description:", 0, 0, g.TOP | g.LEFT);
                g.drawString(Description, 5, 20, g.TOP | g.LEFT);
                g.drawString("Budget Actuel", 5, 189, g.TOP | g.LEFT);
                g.drawString(currentP.getBudget_actuel()+"/"+currentP.getBudget_final(), 5, 200+10, g.TOP | g.LEFT);
                g.drawImage(thumbnail, thumbX, thumbY, g.TOP | g.LEFT);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else {
            System.out.println("null");
        }

    }

    public void run() {
        try {
            ProjetCFHandler pcfh = new ProjetCFHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection con = (HttpConnection) Connector.open(url);
            DataInputStream data = con.openDataInputStream();
            parser.parse(data, pcfh);
            projets = new ProjetCF[pcfh.getProjetCF().length];
            projets = pcfh.getProjetCF();
            if (projets.length < 0) {
                Error = "No projects\nto display";
                isError = true;
            } else {
                isError = false;
                selector = 0;
            }
            con.close();
            data.close();

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        repaint();
    }

    public void commandAction(Command c, Displayable d) {
        if(c == retour){
            Midlet.md.disp.setCurrent(new MenuProjetCF("Menu", List.IMPLICIT));
        }
        if(c == delete){
            url = "http://localhost/crowdriseJ2ME/deleteProjetCf.php?id="+projets[selector].getId();
            try {
                HttpConnection con = (HttpConnection) Connector.open(url);
                Midlet.md.disp.setCurrent(new ListProjetCF(memberType));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(c == commenter ){
            
        }
        if(c == voter){
            
        }
        if(c == financer){
            
        }
        
    }

    public String wrapText(String text) {
        if (text.length() > 30) {
            for (int i = 0; i < text.length(); i++) {
                if (i % 30 == 0) {
                    text = text.substring(0, i) + "\n" + text.substring(i + 1, text.length());
                }
            }
        }
        return text;
    }

    protected void keyPressed(int keyCode) {
        try {
            Manager.playTone(ToneControl.C4, 4000 , 100);
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
        switch (getGameAction(keyCode)) {
            case FIRE:
                slideUp = !slideUp;
                anim.start = true;
                anim.count = 0;
            break;
            case RIGHT:
                if(selector < projets.length-1){
                    selector ++;
                    thumbX = 0;
                    thumbY = 0 ;
                    System.out.println("Selector: "+selector);
                    repaint();
                }
            break;
            case LEFT:
                if(selector > 0){
                    selector --;
                    thumbX = 0;
                    thumbY = 0;
                    repaint();
                }
                
        }

        super.keyPressed(keyCode); //To change body of generated methods, choose Tools | Templates.
    }

    public class Animation extends TimerTask {

        private ListProjetCF canvas;
        public boolean start = false;
        public int count = 0;

        public Animation(ListProjetCF canvas) {
            this.canvas = canvas;
        }

        public void run() {
            if (start) {
                count++;
                if (canvas.slideUp) {
                    if (canvas.thumbY > -h) {
                        canvas.thumbY -= count * (count/3);  
                    }
                    else
                        start = false;
                } else {
                    if (canvas.thumbY < 0) {
                        canvas.thumbY += count * (count/3);
                    }
                    else
                        start = false;
                }
                canvas.repaint();
                
            }

        }

    }

}
