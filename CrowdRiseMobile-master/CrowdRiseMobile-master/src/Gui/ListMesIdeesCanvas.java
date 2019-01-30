/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Idee;
import Handler.IdeesHandler;
import crowdrisemobile.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author userpc
 */
public class ListMesIdeesCanvas extends Canvas implements CommandListener,Runnable{
    
    Display disp;
    Idee[] idee;
    Idee[] ideeList;
    int w = getWidth();
    int h = getHeight();
    public static int pos = 1;
    int h1;
    int x=0;
    int indice=0;
    int y;
    public static int currentIndice;
    public static int currentx;
    //Command cmd = new Command("next", Command.SCREEN, 0);
    Command cmdBack = new Command("Retour", Command.SCREEN, 0);
    Command cmdRech = new Command("Recherche", Command.SCREEN, 0);
    
    public ListMesIdeesCanvas(Display d) {
       this.disp=d;
       Thread th = new Thread(this);
       th.start();
       if (currentIndice!=0) {
            indice=currentIndice;
        }
        if (currentx!=0) {
            x=currentx;
        }
       run();
    }
    
    
    protected void paint(Graphics g) {
        
        try {
            g.setColor(0, 0, 0);
            g.fillRect(0, 0, w, h);
            //g.setColor(0, 0, 0);
            //g.drawRect(0, 0, w, 95);
            g.setColor(126,184,105);
            
            Image img =Image.createImage("/Gui/idee.jpg");
            g.drawImage(img, 0, 0, Graphics.TOP|Graphics.LEFT);
            
            
            y=idee.length;
            
            if (y>=5) {
               if(pos==1){
                g.fillRect(0, img.getHeight()+0, w, 40);
            }else if(pos==2){
                g.fillRect(0, img.getHeight()+40, w, 40);
            }else if(pos==3){
                g.fillRect(0, img.getHeight()+80, w, 40);
            }else if(pos==4){
                g.fillRect(0, img.getHeight()+120, w, 40);
            }else if(pos==5){
                g.fillRect(0, img.getHeight()+160, w, 40);
            } 
            }else if (y<5) {
               if(pos==1 && pos<=y){
                g.fillRect(0, img.getHeight()+0, w, 40);
            }else if(pos==2 && pos<=y){
                g.fillRect(0, img.getHeight()+40, w, 40);
            }else if(pos==3 && pos<=y){
                g.fillRect(0, img.getHeight()+80, w, 40);
            }else if(pos==4 && pos<=y){
                g.fillRect(0, img.getHeight()+120, w, 40);
            }/*else if(pos==5 && pos<=y){
                g.fillRect(0, img.getHeight()+160, w, 40);
            } */
            }
            h1=img.getHeight()+15;
            g.setColor(255, 255, 255);
            if (y>=5) {
              for (int i = x; i < x+5; i++) {
                g.drawString(idee[i].getNom(), 10, h1, 0);
                h1=h1+40;
            }  
            }else{
                for (int i = x; i < y; i++) {
                g.drawString(idee[i].getNom(), 10, h1, 0);
                h1=h1+40;
            }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    }
    
     protected void keyPressed(int keyCode) {

        switch (getGameAction(keyCode)) {
            case (UP):
               
                if (pos==5) {
                   pos = 4;
                   indice--;
                   repaint();
                }else
                if (pos==4) {
                   pos = 3;
                   indice--;
                   repaint();
                }else
                if (pos==3) {
                   pos = 2;
                   indice--;
                   repaint();
                }else
                if (pos==2) {
                   pos = 1;
                   indice--;
                   repaint();
                }else
                if (pos==1) {
                    if (x>0) {
                     x=x-1;
                     indice--;
                     repaint();
                    }
                }
                break;
            case (DOWN):
               if (pos==1 && pos<y) {
                   pos = 2;
                   indice++;
                   repaint();
                }else
                if (pos==2 && pos<y) {
                   pos = 3;
                   indice++;
                   repaint();
                }else
                    if (pos==3 && pos<y) {
                   pos = 4;
                   indice++;
                   repaint();
                }else
                if (pos==4 && pos<y) {
                   pos = 5;
                   indice++;
                   repaint();
                }else
                if (pos==5) {
                    if (x<idee.length-5) {
                        x=x+1;
                        indice++;
                        repaint();
                    }
                }
                break;
            case (FIRE):
                Midlet.currentPosition="listMesIdées";
                currentIndice = indice;
                currentx=x;
                ConsulterIdeeForm form = new ConsulterIdeeForm(disp,idee[indice]);
                disp.setCurrent(form);
                break;
        
        }
        super.keyPressed(keyCode);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            Midlet.menuPrincipal.show();
        }
        
        if (c==cmdRech) {
            RechercheIdeeForm form = new RechercheIdeeForm(disp);
            disp.setCurrent(form);
        }
    }

    public void run() {
        try {
            addCommand(cmdBack);
            addCommand(cmdRech);
            setCommandListener(this);
            IdeesHandler ideesHandler = new IdeesHandler();

            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/crowdrise/idees/select_mes_idees.php?membre="+Midlet.membre.getId());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, ideesHandler);
            // display the result
            idee = ideesHandler.getIdee();
            
            hc.close();
            dis.close();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
}
