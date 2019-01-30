/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import crowdrisemobile.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author Yosra
 */
public class Statistique extends Form implements CommandListener, Runnable {

    private MIDlet m;

    int r2014, r2015, r2016 = 0;
    Command cmdBack = new Command("Retour", Command.BACK, 0);
    Command stat = new Command("stat", Command.SCREEN, 0);
    Command suivant =new Command("suivant", Command.SCREEN, 0);
    Command retour =new Command("Retour", Command.BACK, 0);
    Display disp;
    HttpConnection ht;
    HttpConnection ht1, ht2, ht3;
    DataInputStream ds, ds2;
    StringBuffer sb;
    DataInputStream ds1;
    StringBuffer sb1, sb2;
    String reponse;
    int reponseInt;
    String[] str = {"pie chart","histogramme"};
    ChoiceGroup cg = new ChoiceGroup("Statistiques des reclamations", ChoiceGroup.EXCLUSIVE, str, null);
    Form f=new Form("Statistique");
    

    public Statistique() {
        super("Statistique");
        append(cg);

        f.append("Statistique");
        addCommand(suivant);
        addCommand(retour);
        setCommandListener(this);
         
           
        

    }

    public void commandAction(Command c, Displayable d) {

        if (c == suivant && cg.isSelected(0)) {
            run();
            Midlet.md.disp.setCurrent(new canvPie());
        }
        
        else if(c==suivant && cg.isSelected(1))
        {
            run();
            Midlet.md.disp.setCurrent(new canvHisto());
        }
        
            else if (c==retour)
                Midlet.menuPrincipal.show();

    }

    public class canvPie extends Canvas implements CommandListener {

        int h = getHeight();
        int w = getWidth();
        Command cmdBack = new Command("Retour", Command.BACK, 0);  

        public canvPie() {
            this.setCommandListener(this);
          this.addCommand(cmdBack);
        }

        protected void paint(Graphics g) {

            g.setColor(255, 255, 255);
            g.fillRect(0, 0, w, h);

            int pourcen2016= r2016, pourcen2015 = r2015, pourcen2014 = r2014;
            int total= (int) (r2014+r2015+r2016);
            
             g.setColor(0, 0, 0);
             g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            g.drawString("les Statistiques ", 10, 0, Graphics.TOP|Graphics.LEFT);

            g.setColor(253, 52, 52);  // rouge
            g.fillRect(20, 45, 12, 12);
            g.setColor(0, 0, 0);
            g.drawString("2016 " + "(" + Integer.toString(pourcen2016) + "%)", 40, 57, Graphics.BASELINE | Graphics.LEFT);

            g.setColor(253, 185, 49);
            g.fillRect(20, 72, 12, 12);
            g.setColor(0, 0, 0);
            g.drawString("2015" + "(" + Integer.toString(pourcen2015) + "%)", 40, 82, Graphics.BASELINE | Graphics.LEFT);

            g.setColor(170, 103, 178);  // violet         
            g.fillRect(20,100, 12, 12);
            g.setColor(0, 0, 0);
            g.drawString("2014" + "(" + Integer.toString(pourcen2014) + "%)", 40, 110, Graphics.BASELINE | Graphics.LEFT);
            int rayon = 70;
            int diametre = 2 * rayon;
            int xCentre = w / 2;
            int yCentre = 2* h / 3;

            int angleStart = 90;

            int angle1 = 0;
            int angle2  = 0;
            int angle3 = 0;

            int  angleA = (int) ((360 * pourcen2016) / 100);
            int angleB = (int) ((360 * pourcen2014) / 100);
            int angleC = (int) ((360 * pourcen2015) / 100);
            
            angle1=((100*angleA)/total);
            angle2=((100*angleB)/total);
            angle3=((100*angleC)/total);
            
            
            g.setColor(253, 52, 52);  
            g.fillArc(xCentre - rayon, yCentre - rayon, diametre, diametre, 0, -360);

            g.setColor(253, 52, 52);  // rouge
            g.fillArc(xCentre - rayon, yCentre - rayon, diametre, diametre, angleStart, -angle1);
            angleStart -= angle1;

            g.setColor(170, 103, 178);  // violet

            g.fillArc(xCentre - rayon, yCentre - rayon, diametre, diametre, angleStart, -angle2);
            angleStart -= angle2;

            g.setColor(253, 185, 49);  // orange
            g.fillArc(xCentre - rayon, yCentre - rayon, diametre, diametre, angleStart,-angle3);

            g.setColor(255, 255, 255);

        }

        public void commandAction(Command c, Displayable d) {

          if(c==cmdBack)
          {
              Midlet.md.disp.setCurrent(new Statistique());
          }
        }

    }

      public class canvHisto extends Canvas implements CommandListener 
    {

        int w = getWidth();
        int h = getHeight();
        Command cmdBack = new Command("Retour", Command.BACK, 0);  
        
        public canvHisto()
        {
          this.setCommandListener(this);
          this.addCommand(cmdBack);
        }

        
        public void paint(Graphics g)
        {
            g.setColor(0, 0, 0);
            g.fillRect(0, 0, w, h);
            
            g.setColor(255,255,255);
            g.drawLine(10, 0, 10, h);  // vertical line
            g.drawLine(w-10, 0, w-10, h);  // vertical line
            g.drawLine(0, h-40, w, h-40); // horizontal line
            
            g.setColor(207,207,207);
            g.setStrokeStyle(Graphics.DOTTED);
            g.drawLine(0, h-85, w, h-85);
            g.drawLine(0, h-130, w, h-130);
            g.drawLine(0, h-175, w, h-175);
            g.drawLine(0, h-220, w, h-220);
            
            g.setStrokeStyle(Graphics.SOLID);
            g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
            g.setColor(124, 181, 236);  // bleu ciel
            g.drawString("10", 1, h-85, Graphics.BASELINE|Graphics.LEFT);
            g.drawString("20", 1, h-130, Graphics.BASELINE|Graphics.LEFT);
            g.drawString("30", 1, h-175, Graphics.BASELINE|Graphics.LEFT);
            g.drawString("40", 1, h-220, Graphics.BASELINE|Graphics.LEFT);
            
         
            
            g.setColor(255, 255, 255);
            
                for(int i=70,j=4;i<w-10;i+=(w-20)/4,j++)
            {
                g.fillRect(i, h-42, 4, 4);
                g.drawString("201"+Integer.toString(j), i-3, h-25, Graphics.BASELINE|Graphics.LEFT);
            }
                
                
           int v=15*(int)r2014;
           int s=15*(int)r2015;
           int p=15*(int)r2016;
           g.setColor(255, 181, 236);  // bleu ciel
           g.fillRect(70-15, h-v,30, v-40);
            
           g.fillRect(130-15, h-s ,30, s-40);
           g.fillRect(190-15, h-p, 30, p-40);
            
            
            
            g.setColor(255, 255, 255);
            g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            g.drawString("Statistique des reclamations", w/2, 15, Graphics.BASELINE|Graphics.HCENTER);
            
            
        }

        public void commandAction(Command c, Displayable d) {
          if(c==cmdBack)
          {
              Midlet.md.disp.setCurrent(new Statistique());
          }
        }
       
    }
    public void run() {

        int ch;
        sb = new StringBuffer("");
        sb1 = new StringBuffer("");
        sb2 = new StringBuffer("");
        try {
            ht = (HttpConnection) Connector.open("http://localhost/crowdrise/statistique/statRec.php");
            ht1 = (HttpConnection) Connector.open("http://localhost/crowdrise/statistique/stat2015.php");
            ht2 = (HttpConnection) Connector.open("http://localhost/crowdrise/statistique/stat2014.php");
            ds = ht.openDataInputStream();
            ds1 = ht1.openDataInputStream();
            ds2 = ht2.openDataInputStream();
            while ((ch = ds.read()) != -1) {
                sb.append((char) ch);
            }

            reponse = sb.toString().trim();
            System.out.println("rep" + reponse);
            r2016 = Integer.parseInt(reponse);
            while ((ch = ds1.read()) != -1) {
                sb1.append((char) ch);
            }

            String rep1 = sb1.toString().trim();
            r2015 = Integer.parseInt(rep1);
            sb1 = new StringBuffer();

            while ((ch = ds2.read()) != -1) {
                sb2.append((char) ch);
            }
            String rep2 = sb2.toString().trim();
            r2014 = Integer.parseInt(rep2);
            sb2 = new StringBuffer();
//                ht.close();
//                ds.close();
//                
//                ht1.close();
//                ds1.close();
//                
//                ht2.close();
//                ds2.close();
        }
        
    
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
