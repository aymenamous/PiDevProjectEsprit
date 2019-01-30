/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.plaf.Border;
import java.io.IOException;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.StringItem;
import crowdrisemobile.Midlet;
import javax.microedition.lcdui.List;

/**
 *
 * @author Amine Triki
 */
public class MenuPricipal extends Form {

    public MenuPricipal(String title) {
        super(title);
        Container cn=new Container();
        GridLayout l= new GridLayout(3, 4);
        
        Image listProbsIcon=null;
        Image mesProbsIcon=null;
        Image mesIdeesIcon=null;
        Image reclamationIcon=null;
        Image signalisationIcon=null;
        Image statIcon=null;
        Image validCFIcon=null;
        Image validPbIcom=null;
        Image validIdeeIcon=null;
        Image listIdeeIcon=null;
        Image ProfilIcon=null;
        Image competIcon=null;
        Image financeIcon=null;
        try {
            listProbsIcon=Image.createImage("/Ressources/ListProblemes.png");
            mesProbsIcon=Image.createImage("/Ressources/MesProblemes.png");
            mesIdeesIcon=Image.createImage("/Ressources/MesIdees.png");
            reclamationIcon=Image.createImage("/Ressources/reclamation.png");
            signalisationIcon=Image.createImage("/Ressources/signalisation.png");
            statIcon=Image.createImage("/Ressources/stat.png");
            validCFIcon=Image.createImage("/Ressources/validationCF.png");
            validPbIcom=Image.createImage("/Ressources/validationPblm.png");
            validIdeeIcon=Image.createImage("/Ressources/ValiderIdee.png");
            listIdeeIcon=Image.createImage("/Ressources/listIdee.png");
            ProfilIcon=Image.createImage("/Ressources/profile.png");
            financeIcon=Image.createImage("/Ressources/finance.png");
            competIcon=Image.createImage("/Ressources/competence.png");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        Image imgBackground = null;
        try {
            imgBackground= Image.createImage("/Gui/Background.jpg");
        } catch (IOException ex) {
            ex.printStackTrace();
        }     
        this.setBgImage(imgBackground);
        
        Image img = null;
        try {
            img= Image.createImage("/Gui/anim.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                
        Image competeenceAdmin = null;
        try {
            competeenceAdmin= Image.createImage("/Gui/anim.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }  
        
        cn.setLayout(l);
        if (Midlet.membre.getId()!=0)
        {
            Button ProfilMembtn=createButton("Mon Profil", ProfilIcon, new Profil());
            Button listProbsBtn= createButton("Liste des problèmes", listProbsIcon, new ListProblemes(null));
            Button MesProbsBtn=createButton("Mes problèmes", mesProbsIcon, new MesProblemes(null));
            Button ListIdees= createButton("Les Idees", listIdeeIcon, new ListIdeesCanvas(javax.microedition.lcdui.Display.getDisplay(Midlet.md)));
            Button ListMesIdees= createButton("Mes Idees", mesIdeesIcon, new ListMesIdeesCanvas(javax.microedition.lcdui.Display.getDisplay(Midlet.md)));            
            Button menuProjetCf=createButton("Projet CrowdFunding", financeIcon, new MenuProjetCF("Projet CrowdFunding", List.IMPLICIT));
            Button competMembre =createButton("Competences", competIcon, new CompetenceList("Compétence", List.MULTIPLE, title));
            cn.addComponent(0,MesProbsBtn);
            cn.addComponent(1,listProbsBtn);
            cn.addComponent(2,ListIdees);
            cn.addComponent(3,ListMesIdees);
            cn.addComponent(4, ProfilMembtn);
            cn.addComponent(5, menuProjetCf);
            cn.addComponent(6,competMembre);
        }else
        {
            Button ProfilAdmbtn= createButton("Mon Profil", ProfilIcon, new ProfilAdmin());
            Button listProbsBtn= createButton("Liste des problèmes", listProbsIcon, new ListProblemes(null));
            Button listeMembresBtn=createButton("Liste des membres",img,new MembresBannir_Activer("Liste des Membres", List.IMPLICIT, javax.microedition.lcdui.Display.getDisplay(Midlet.md)));        
            Button activerCfBtn=createButton("Activer projet cf", validCFIcon, new ValidationCF());
            Button activerIdBtn=createButton("Activer Idée", validIdeeIcon, new ValiderIdee());
            Button activerProbBtn=createButton("Activer Problème", validPbIcom, new ValidationProbleme());
            Button listReclamation=createButton("Liste des réclamations", reclamationIcon, new ListReclamation());
            Button listSignalisation=createButton("Liste des signalisations", signalisationIcon, new ListSignalisation());
            Button stats=createButton("Statistiques des réclamations", statIcon, new Statistique());
            Button competenceB=createButton ("Ajout Compétence",competeenceAdmin, new AdminInterface("Gestion des compétences"));
            Button ListIdeesAdmin= createButton("Les Idees", listIdeeIcon, new ListIdeeAdminCanvas(javax.microedition.lcdui.Display.getDisplay(Midlet.md)));
            Button menuProjetCf=createButton("Projet CrowdFunding", financeIcon, new MenuProjetCF("Projet CrowdFunding", List.IMPLICIT));
            cn.addComponent(0,stats);
            cn.addComponent(1,ProfilAdmbtn);
            cn.addComponent(2, menuProjetCf);
            cn.addComponent(3,competenceB);
            cn.addComponent(4, listProbsBtn);            
            cn.addComponent(5,ListIdeesAdmin);
            cn.addComponent(6,activerCfBtn);
            cn.addComponent(7,activerIdBtn);
            cn.addComponent(8,activerProbBtn);
            cn.addComponent(9, listeMembresBtn);
            cn.addComponent(10,listReclamation);
            cn.addComponent(11,listSignalisation);
            
            
            
        }
        
        this.addComponent(cn); 
        
        final Command abtUsCmd = new Command("About us") { //just pour comprendre le concept
            public void actionPerformed(ActionEvent evt) {
                javax.microedition.lcdui.Form frm = new javax.microedition.lcdui.Form("About Us");
                StringItem item = new StringItem(null, "CrowdriseⓇ-2015-2016");
                frm.append(item);

                final javax.microedition.lcdui.Command cmd = new javax.microedition.lcdui.Command("Back", javax.microedition.lcdui.Command.BACK, 0);
                CommandListener cmdLis = new CommandListener() {

                    public void commandAction(javax.microedition.lcdui.Command c, Displayable d) {
                        if (c == cmd) {
                            Display.init(Midlet.md);
                            show(); // Show the LWUIT form again
                        }
                    }

                    
                };

                frm.setCommandListener(cmdLis);
                frm.addCommand(cmd);

                javax.microedition.lcdui.Display.getDisplay(Midlet.md).setCurrent(frm); // show the LCDUI Form
            }
        };
        this.addCommand(abtUsCmd);
        
    }

    private Button createButton(String titre, Image img, final Displayable instance) {
        
        Button b= new Button(titre, img ); 
        b.getStyle().setBgTransparency(0);
        b.getSelectedStyle().setBgTransparency(0);
        b.getSelectedStyle().setBorder(Border.createRoundBorder(20, 20), true);        
        b.setTextPosition(2);
        b.getUnselectedStyle().setBorder(null, false);
        
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                    javax.microedition.lcdui.Display.getDisplay(Midlet.md).setCurrent(instance); // show the LCDUI Form   
            }
        });
        
        return b;
       
    }
    
    
}
