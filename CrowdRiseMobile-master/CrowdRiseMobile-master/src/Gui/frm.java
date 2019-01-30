/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import crowdrisemobile.Midlet;
import java.io.IOException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;

import javax.microedition.lcdui.Item;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.GUIControl;

/**
 *
 * @author Amine Triki
 */
public class frm extends Form implements CommandListener {

    Player player = null;

    private Command SeConnect = new Command("Se Connecter", Command.SCREEN, 1);

    private Command exitCommand = new Command("Exit", Command.EXIT, 1);

    private Alert alert = new Alert("Error");

    private Image img;

    private boolean error = false;

    public frm(String title) {
        super(title);
        this.addCommand(SeConnect);
        this.addCommand(exitCommand);
        this.setCommandListener(this);
        try {
            img=Image.createImage(getClass().getResourceAsStream("/Gui/crowdrise_1.PNG"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            
            ImageItem imag = new ImageItem(null, img, ImageItem.LAYOUT_CENTER, null);
            imag.setPreferredSize(100, 50);
            
            this.append(imag);
            loadPlayer();

            GUIControl guiControl = (GUIControl) player.getControl("javax.microedition.media.control.GUIControl");

            if (guiControl == null) {
                throw new Exception("No GUIControl!!");
            }

            Item videoItem = (Item) guiControl.initDisplayMode(GUIControl.USE_GUI_PRIMITIVE, null);
            videoItem.setLabel("Crowdrise Project");
            videoItem.setPreferredSize(400, 200);

            this.append(videoItem);

            player.start();

        } catch (Exception e) {
            error(e);
        }

    }

    private void loadPlayer() throws Exception {

        player = Manager.createPlayer(getClass().getResourceAsStream("/Ressources/amine.mpg"), "video/mpeg");

        player.realize();
    }

    private void error(Exception e) {
        alert.setString(e.getMessage());
        alert.setTimeout(Alert.FOREVER);
        Midlet.md.disp.setCurrent(alert);

        e.printStackTrace();
        error = true;
    }

    public void commandAction(Command c, Displayable d) {
        if(c==SeConnect){
            try {
                Midlet.md.disp.setCurrent(new MembreLogin("Login"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else{
            Midlet.md.notifyDestroyed();
        }
    }

}
