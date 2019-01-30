/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Idee;
import Entity.Projet;
import crowdrisemobile.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.TimeBase;

/**
 *
 * @author Aymen
 */
public class Vote extends Canvas implements Runnable, CommandListener {

    int w, h, votes, xtitre, ytitre, allVotes;
    Projet projet;
    boolean existe;
    long initialTime;
    long currentTime;
    Graphics g;
    Thread th;
    boolean start;
    int i;
    Command retour;
    int selector = 0;
    Image like,dislike;
    
    public Vote(Projet p) {
        h = getHeight();
        w = getWidth();
        projet = p;
        xtitre = 100;
        ytitre = 30;
        th = new Thread(this);
        start = false;
        i = 0;
        retour = new Command("Retour", Command.BACK, 0);
        addCommand(retour);
        setCommandListener(this);
        try {
            like = Image.createImage("/Ressources/like.png");
            dislike = Image.createImage("/Ressources/dislike.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    protected void paint(Graphics g) {

        if (!start) {

            g.setColor(255, 255, 255);
            g.fillRect(0, 0, w, h);
            votes = getVotesProjet();
            allVotes = getAllVotes();
            if (allVotes ==0) {allVotes = -1;}
            drawTitre(g);
            drawButton(g);
            th = new Thread(this);
            th.start();
            start = true;
            drawCircle(g);
        } else {
            drawCircle(g);
        }

    }

    public void drawTitre(Graphics g) {

        //contour titre
        g.setColor(147, 7, 3);
        g.fillRect(0, 0, xtitre + 2, ytitre + 3);
        g.fillArc(xtitre - 25 - 2, -ytitre - 2, 50 + 4, ytitre * 2 + 4, 270, 90);

        //background titre
        g.setColor(221, 57, 53);
        g.fillRect(0, 0, xtitre, ytitre + 1);
        g.fillArc(xtitre - 25, -ytitre, 50, ytitre * 2, 270, 90);

        //String
        g.setColor(255, 255, 255);
        String v;
        if (allVotes == -1)
        { 
            v = "0";
        }else
        {
            v = allVotes+"";
        };
        g.drawString("Votes: " + v, 5, ytitre / 2 + 5, Graphics.BASELINE | Graphics.LEFT);
        g.setColor(100,149,237);
        g.drawString(votes+"", w/2 + 80, h/2 +5, Graphics.BASELINE | Graphics.LEFT);
        g.setColor(221, 57, 53);
        g.drawString(getAllVotes()-votes+"", w/2 -80, h/2 +5, Graphics.BASELINE | Graphics.LEFT);
        g.drawImage(like, w/2 + 55, h/2, Graphics.VCENTER|Graphics.HCENTER);
        g.drawImage(dislike, w/2 - 55, h/2, Graphics.VCENTER|Graphics.HCENTER);
        

    }

    public void drawButton(Graphics g) {

        if (getVoteMembre() == -1) {
            existe = false;
            //countour button
            g.setColor(128, 128, 128);
            if (selector == 0)
            {
                g.fillRect(w / 2 - 50, h / 2 -92, 100, 40);
            }
            else {
                g.fillRect(w / 2 - 50, h / 2 + 45, 100, 40);
            }
            

            //Boutton
            g.setColor(169, 169, 169);
            g.fillRect(w / 2 - 48, h / 2 + 47, 96, 36);
            g.fillRect(w / 2 - 48, h / 2 -90, 96, 36);
            
        } else {
            existe = true;
            if (getVoteMembre()==1)
            {
                //countour button
                if (selector == 0){
                     g.setColor(25,25,112);
                    g.fillRect(w / 2 - 50, h / 2 -92, 100, 40);
                }else{
                    g.setColor(128, 128, 128);
                    g.fillRect(w / 2 - 50, h / 2 + 45, 100, 40);
                }
                
               

                //Boutton
                g.setColor(169, 169, 169);
                g.fillRect(w / 2 - 48, h / 2 + 47, 96, 36);
                g.setColor(100,149,237);
                g.fillRect(w / 2 - 48, h / 2 -90, 96, 36);
            }else{
                //countour button
                if (selector ==0){
                    
                g.setColor(128, 128, 128);
                g.fillRect(w / 2 - 50, h / 2 -92, 100, 40);
                }else {
                    g.setColor(147, 7, 3);
                g.fillRect(w / 2 - 50, h / 2 + 45, 100, 40);
                }

                //Boutton
                g.setColor(221, 57, 53);
                g.fillRect(w / 2 - 48, h / 2 + 47, 96, 36);
                g.setColor(169, 169, 169);
                g.fillRect(w / 2 - 48, h / 2 -90, 96, 36);
            }
        }

        g.setColor(255, 255, 255);
        g.drawString("J'aime ", w / 2, (h / 2 - 72) + 5, Graphics.BASELINE | Graphics.HCENTER);
        g.drawString("Je n'aime pas", w / 2, (h / 2 + 65) + 5, Graphics.BASELINE | Graphics.HCENTER);
    }

    public void drawCircle(Graphics g) {

        g.setColor(255, 255, 255);
        g.fillArc(w / 2 - 35, h / 2 - 35, 80, 80, 0, 360);
        //cerlce milieu contour
        g.setColor(169, 169, 169);
        g.fillArc(w / 2 - 35, h / 2 - 35, 70, 70, 0, 360);

        //remplissage %
        
        g.setColor(100,149,237);
        g.fillArc(w / 2 - 35, h / 2 - 35, 70, 70, 90, -i);
        if (allVotes!=-1 && allVotes!=votes)
        {
            g.setColor(147, 7, 3);
            g.fillArc(w / 2 - 35, h / 2 - 35, 70, 70, 90, 360-i);
        }
        //cercle blanc milieu
        g.setColor(255, 255, 255);
        g.fillArc(w / 2 - 21, h / 2 - 21, 42, 42, 0, 360);

        //String
        g.setColor(0, 0, 0);
        g.drawString(i * 100 / 360 + "%", w / 2, h / 2 + 5, Graphics.HCENTER | Graphics.BASELINE);
    }

    public int getVotesProjet() {
        try {
            HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/vote/count_likes_projet.php?id=" + projet.getId());
            DataInputStream data = con.openDataInputStream();
            StringBuffer str = new StringBuffer();
            int c;
            while ((c = data.read()) != -1) {
                str.append((char) c);
            }
            con.close();
            data.close();
            return Integer.parseInt(str.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getAllVotes() {
        try {
            HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/vote/count_vote_projet.php?id=" + projet.getId());
            DataInputStream data = con.openDataInputStream();
            StringBuffer str = new StringBuffer();

            int c;
            while ((c = data.read()) != -1) {
                str.append((char) c);
            }
            con.close();
            data.close();
            return Integer.parseInt(str.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getVoteMembre() {
        try {
            HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/vote/count_vote_projet_membre.php?id=" + projet.getId() + "&id1=" + Midlet.membre.getId());
            DataInputStream data = con.openDataInputStream();
            StringBuffer str = new StringBuffer();
            int c;
            while ((c = data.read()) != -1) {
                str.append((char) c);
            }
            con.close();
            data.close();
            return Integer.parseInt(str.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public void run() {
        System.out.println("***************"+i);
        System.out.println("***************"+votes);
        System.out.println("***************"+allVotes);
        initialTime = System.currentTimeMillis();
        if (i == 0 || i < (votes * 360 / allVotes)) {
            do {
                currentTime = System.currentTimeMillis();
                if (currentTime - initialTime > 5) {
                    i++;
                    repaint();
                    initialTime = System.currentTimeMillis();
                }

            } while (i < (votes * 360 / allVotes));
        } else {
            do {
                currentTime = System.currentTimeMillis();
                if (currentTime - initialTime > 5) {
                    i--;
                    repaint();
                    initialTime = System.currentTimeMillis();
                }

            } while (i > (votes * 360 / allVotes));
        }

        //th.interrupt();
    }

    protected void keyPressed(int keyCode) {
        super.keyPressed(keyCode); //To change body of generated methods, choose Tools | Templates.
        if (getGameAction(keyCode) == FIRE && !th.isAlive()) {
            if (existe) {
                deleteVote();
            } else {
                if (selector == 0)
                {
                    addVote("1");
                }else{
                    addVote("0");
                }
                
            }
            start = false;
            repaint();

        }
        else if (getGameAction(keyCode) == UP)
        {
            selector= 0;
            start = false;
            repaint();
        }
        else if (getGameAction(keyCode) == DOWN)
        {
            selector= 1;
            start = false;
            repaint();
        }
    }

    public void addVote(String avis) {
        try {
            HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/vote/create_vote.php?id_projet=" + projet.getId() + "&id_membre=" + Midlet.membre.getId()+"&avis="+avis);
            DataInputStream data = con.openDataInputStream();
            con.close();
            data.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteVote() {
        try {
            HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/vote/delete_vote.php?id_projet=" + projet.getId() + "&id_membre=" + Midlet.membre.getId());
            DataInputStream data = con.openDataInputStream();
            con.close();
            data.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c == retour) {
            if (Midlet.currentPosition.equals("listIdées") || Midlet.currentPosition.equals("listMesIdées") || Midlet.currentPosition.equals("listIdéesAdmin")) {
                ConsulterIdeeForm cif = new ConsulterIdeeForm(Midlet.md.disp, (Idee) projet);
                Midlet.md.disp.setCurrent(cif);
            }
        }
    }

}
