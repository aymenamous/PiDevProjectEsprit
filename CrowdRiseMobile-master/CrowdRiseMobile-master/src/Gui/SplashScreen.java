/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package Gui;
import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;


public class SplashScreen extends Canvas  {
    private Image mImage;

    public SplashScreen(){
      try{
        mImage = Image.createImage("/Gui/Capture.PNG");
         }
        catch(IOException e){
            e.printStackTrace();
        }
    }
      public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        //set background color to overdraw what ever was previously displayed
        g.setColor(255,255,255);
        g.fillRect(0,0, width, height);
        g.drawImage(mImage, width / 2, height / 2,
                Graphics.HCENTER | Graphics.VCENTER);
    }
}
