/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Membre;
import Handler.MembreHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author userpc
 */
public class SMSForm extends Form implements CommandListener, Runnable {

    Display disp;
    private TextField toWhom;
    private TextField message;
    private Alert alert;
    private Command send, exit;
    MessageConnection clientConn;
    StringBuffer sb;
    public static Membre[] membre;

    public SMSForm(Display d) {
        super("Envoyer SMS");
        toWhom = new TextField("To", "", 10, TextField.PHONENUMBER);
        message = new TextField("Message", "", 600, TextField.ANY);
        send = new Command("Envoyer", Command.SCREEN, 0);
        exit = new Command("Retour", Command.SCREEN, 0);
        toWhom.setConstraints(TextField.UNEDITABLE);
        try {
            MembreHandler membreHandler = new MembreHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            System.out.println("here id idée = "+ConsulterIdeeForm.idee.getId());
            HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/sms/select_tel_membre.php?projet="+ConsulterIdeeForm.idee.getId());
            DataInputStream data = con.openDataInputStream();

            parser.parse(data, membreHandler);
            membre = new Membre[membreHandler.getMembrevect().size()];
            membreHandler.getMembrevect().copyInto(membre);
            if (membre.length > 0) {
                deleteAll();
                toWhom.setString(membre[0].getTelephone());
            }else{
                toWhom.setString("pas de numero de telefone");
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
        append(toWhom);
        append(message);
        addCommand(exit);
        addCommand(send);
        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == exit) {
            disp.setCurrent(new ConsulterIdeeForm(disp, ConsulterIdeeForm.idee));
        }
        if (c == send) {
            Thread th = new Thread(this);
            th.start();
        }
    }

    public void run() {
        String mno = toWhom.getString();
        String msg = message.getString();
        if (mno.equals("")) {
            alert = new Alert("Alert");
            alert.setType(AlertType.ERROR);
            alert.setString("Enter Mobile Number!!!");
            alert.setTimeout(2000);
            disp.setCurrent(alert);
        } else {
            try {
                clientConn = (MessageConnection) Connector.open("sms://" + mno);
            } catch (Exception e) {
                System.out.println(e);
                /*alert = new Alert("Alert");
                 alert.setType(AlertType.ERROR);
                 alert.setString("Unable to connect to Station because of network problem");
                 alert.setTimeout(2000);
                 disp.setCurrent(alert);*/
            }
            try {
                TextMessage textmessage = (TextMessage) clientConn.newMessage(MessageConnection.TEXT_MESSAGE);
                textmessage.setAddress("sms://" + mno);
                textmessage.setPayloadText(msg);
                clientConn.send(textmessage);
                Alert alert = new Alert(null);
                alert.setTimeout(5000);
                alert.setType(AlertType.CONFIRMATION);
                alert.setString("Message envoyé !");
                alert.setTitle("Succes");
                disp.setCurrent(alert);
            } catch (Exception e) {
                /*Alert alert=new Alert("Alert","",null,AlertType.INFO);
                 alert.setTimeout(Alert.FOREVER);
                 alert.setString("Unable to send");
                 disp.setCurrent(alert);*/
            }
        }
    }

}
