/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import crowdrisemobile.Midlet;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.*;

/**
 *
 * @author H4DH
 */
public class Spinner2 extends Canvas 
{

    int h = getHeight();
    int w = getWidth();
    String ext = ".png";
    Image img;
    Image[] imgArray;
    int index = 0;
    RepeatMe tt;
     Timer tm = new Timer();

    public Spinner2() throws IOException 
    {
        imgArray = new Image[15];
        for (int i = 0; i <= 14; i++) {
            img = Image.createImage("/Gui/spinner/" + i + ext);
            imgArray[i] = img;
        }
        tt = new RepeatMe(this);
        tm.schedule(tt, 0, 40);
    }

    protected void paint(Graphics g) {

        g.setColor(255,255,255);
        g.fillRect(0, 0, w, h);
        g.setColor(255, 0, 0);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        g.drawString("Ajout En Cours",  (w / 2), h / 4, Graphics.HCENTER | Graphics.BASELINE);
        g.drawImage(imgArray[index], w / 2, h / 2, Graphics.HCENTER | Graphics.VCENTER);
    }

    class RepeatMe extends TimerTask {

        Spinner2 g;
        public int count;
        
        RepeatMe(Spinner2 g) {
            this.g = g;
        }

        public void run() {
            
            count++;
            if(count  == 30)
            {
                Midlet.md.disp.setCurrent(new AdminInterface("Compétence"));
            }
            else {
            if (index < 14) {
                index++;
            } else {
                index = 0;
            }
            g.repaint();
        }
        }
    }

}
