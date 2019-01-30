/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crowdrisemobile;

import Entity.Admin;
import Entity.Membre;
import Gui.MembreLogin;
import Gui.MenuPricipal;
import Gui.SplashScreen;
import Gui.frm;
import java.io.IOException;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author Aymen
 */
public class Midlet extends MIDlet {

    public Display disp;
    public static Midlet md;
    public static Membre membre;
    public static Admin admin;
    public static String currentPosition = "";
    public static MenuPricipal menuPrincipal;

    public Midlet() {
        md = this;
        disp = Display.getDisplay(this);
        com.sun.lwuit.Display.init(this);
        membre = new Membre(0);
        admin = new Admin(0);

    }

    public void startApp() {
        System.out.println("Amine");
        SplashScreen s = new SplashScreen();
        disp.setCurrent(s);
        try {
            Thread.sleep(1500);//set for 1,5 seconds
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        
        try {
        
        disp.setCurrent(new MembreLogin("Login"));
        
        
        } catch (IOException ex) {
        ex.printStackTrace();
        }
        
        
        //disp.setCurrent(new frm("Accueil"));
        

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
