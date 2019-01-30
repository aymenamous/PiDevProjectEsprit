/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;

import Entity.Idee;
import Entity.Solution;
import Handler.SolutionHandler;
import crowdrisemobile.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author userpc
 */
public class ConsulterIdeeForm extends Form implements CommandListener,Runnable  {
    
    public static Idee idee;
    Solution[] solutions;
    int[] sol_id;
    ChoiceGroup cg = new ChoiceGroup("les taches :", ChoiceGroup.MULTIPLE);
    ChoiceGroup cg2 = new ChoiceGroup("les taches :", ChoiceGroup.POPUP);
    Command cmdAccepter = new Command("Accepter", Command.SCREEN, 0);
    Command cmdSMS = new Command("Envoyer SMS", Command.SCREEN, 0);
    Command cmdBack = new Command("Retour", Command.SCREEN, 0);
    Command cmdOui = new Command("oui", Command.SCREEN, 0);
    Command cmdNon = new Command("non", Command.SCREEN, 0);
    Command cmdSupp = new Command("Supprimer", Command.SCREEN, 0);
    Command cmdComm = new Command("Commentaire", Command.SCREEN, 0);
    Command cmdVote = new Command("Vote", Command.SCREEN, 0);
    Alert alert1 = new Alert("taches selectionnées", "vous etes sure de prendre ces taches ?", null, AlertType.CONFIRMATION);
    
    Display disp;
    HttpConnection hi;
    DataInputStream dis;
    StringBuffer sb ;
    int j=0;
    String url="http://localhost/crowdrise/solution/insert_solution.php?"; 
    
    public ConsulterIdeeForm(Display d,Idee idee) {
        super("Consulter Idée");
        this.idee = idee;
        run();
        append(new StringItem("titre idée :", idee.getNom()));
        append(new StringItem("description :", idee.getDescription()));
        append(new StringItem("date debut :", idee.getDebut()));
        append(new StringItem("date fin :", idee.getFin()));
        addCommand(cmdBack);
        if (Midlet.currentPosition.equals("listIdées")) {
            append(cg);
            addCommand(cmdAccepter);
            addCommand(cmdSMS);
            addCommand(cmdVote);
        }else{
            addCommand(cmdSupp);
            append(cg2);
        }
        addCommand(cmdComm);
        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            if (Midlet.currentPosition.equals("listMesIdées")) {
                if (ListIdeeRechCanvas.from.equals("listMesIdées")){
                    disp.setCurrent(new ListIdeeRechCanvas(disp));
                }else{
                    disp.setCurrent(new ListMesIdeesCanvas(disp)); 
                }
               
            }else if (Midlet.currentPosition.equals("listIdées")){
                if (ListIdeeRechCanvas.from.equals("listIdées")){
                    disp.setCurrent(new ListIdeeRechCanvas(disp));
                }else{
                    disp.setCurrent(new ListIdeesCanvas(disp));
                } 
            }else if (Midlet.currentPosition.equals("listIdéesAdmin")){
                if (ListIdeeRechCanvas.from.equals("listIdéesAdmin")){
                    disp.setCurrent(new ListIdeeRechCanvas(disp));
                }else{
                    disp.setCurrent(new ListIdeeAdminCanvas(disp)); 
                }
            }
        }else
        if (c == cmdAccepter) {
            boolean isSelected = false;
            for (int i = 0; i < solutions.length; i++) {
                if (cg.isSelected(i)) {
                    isSelected = true;
                }
            }
            if (isSelected==false) {
                    Alert alert = new Alert(null);
                    alert.setTimeout(5000);
                    alert.setType(AlertType.ERROR);
                    alert.setString("veuillez selectionner au moins une tache à faire !");
                    alert.setTitle("Erreur");
                    disp.setCurrent(alert);
            }else
                sol_id = new int[solutions.length];
                
            for (int i = 0; i < solutions.length; i++) {
                
                if (cg.isSelected(i)) {
                    sol_id[j]=solutions[i].getId();
                    j++;
                    alert1.addCommand(cmdOui);
                    alert1.addCommand(cmdNon);
                    alert1.setCommandListener(this);
                    disp.setCurrent(alert1);
                }
            }
        }else
        if (c==cmdOui) {
                    insertSolution(sol_id);
                    
                }else
        if (c==cmdSupp) {
            System.out.println("HERE SUPP");
            supprimer(idee);
        }else
            if (c==cmdComm) {
            ListCommentaires lc=new ListCommentaires(idee, null);
            Midlet.md.disp.setCurrent(lc);
        }else 
            if(c==cmdSMS){
              disp.setCurrent(new SMSForm(disp));
        }else 
            if(c==cmdVote){
             Vote v=new Vote(idee);
             disp.setCurrent(v);
        }
       
    }

    public void run() {
         try {
            
            SolutionHandler solsHandler = new SolutionHandler();

            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection hc;
             if (Midlet.currentPosition.equals("listMesIdées")) {
                hc = (HttpConnection) Connector.open("http://localhost/crowdrise/solution/select_solution.php?projet="+idee.getId()+"&membre="+Midlet.membre.getId()); 
             }else{
                hc = (HttpConnection) Connector.open("http://localhost/crowdrise/solution/select_all_solution.php?projet="+idee.getId());
             }
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, solsHandler);
            // display the result
            solutions = solsHandler.getSol();

            if (solutions.length > 0) {
                this.deleteAll();
                for (int i = 0; i < solutions.length; i++) {
                    cg.append(solutions[i].getTache()+" "+solutions[i].getRemuneration()+" $", null);
                    cg2.append(solutions[i].getTache()+" "+solutions[i].getRemuneration()+" $", null);
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertSolution(int[] tab_id){
         try {
             for (int i = 0; i < tab_id.length; i++) {
                 //getCurrentMembre
                 System.out.println("tache "+tab_id[i]);
       hi=(HttpConnection)Connector.open(url+"tache_id="+tab_id[i]+"&membre="+Midlet.membre.getId());
       dis=hi.openDataInputStream(); 
       int ascii;
       sb =new  StringBuffer();
       while( (ascii=dis.read()) != -1 ){
           
           sb.append((char)ascii);
           
       }
             }  
       if(sb.toString().equals("successfully added")){
           Alert a= new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
           a.setTimeout(3000);
           disp.setCurrent(a,(new ConsulterIdeeForm(disp, idee)));
       }else{
           Alert a= new Alert("Erreur", "Erreur dans l'ajout !", null, AlertType.ERROR);
           a.setTimeout(3000);
           disp.setCurrent(a);
       }
       
      
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void supprimer(Idee idee){
        try {
            hi=(HttpConnection)Connector.open("http://localhost/crowdrise/idees/supprime_idee.php?id="+idee.getId());
            dis=hi.openDataInputStream();
           Alert a= new Alert("Information", "idee supprimer !", null, AlertType.CONFIRMATION);
           a.setTimeout(3000);
           if (Midlet.currentPosition.equals("listMesIdées")) {
               disp.setCurrent(a,new ListMesIdeesCanvas(disp));
           }else if (Midlet.currentPosition.equals("listIdées")){
               disp.setCurrent(a,new ListIdeesCanvas(disp));
           }else if (Midlet.currentPosition.equals("listIdéesAdmin")){
               disp.setCurrent(a,new ListIdeeAdminCanvas(disp));
           }
           
        }
            catch (IOException ex) {
            ex.printStackTrace();
        }
               
       
    }
}
