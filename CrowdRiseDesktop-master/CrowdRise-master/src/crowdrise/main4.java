/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crowdrise;

import Dao.ProjetCFDao;
import Entity.Membre;
import Entity.ProjetCF;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Amine Triki
 */
public class main4 {
    
    public static void main(String[] args) throws ParseException {
        Date daa= new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(sdf.format(daa));
        java.sql.Date sDate1 = new java.sql.Date(daa.getTime());
        System.out.println(sDate1);
        String date_s = "2011-01-18"; 
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd"); 
        Date date =dt.parse(date_s);   
        java.sql.Date sDate = new java.sql.Date(date.getTime());        
        //System.out.println(dt.format(date));
        System.out.println(sDate);
        
        //ProjetCF pcf=new ProjetCF(125, 150, "ProjetGood", sDate, sDate, "Amine Amine", sDate1, new Membre(2));
        //ProjetCFDao pcfD=new ProjetCFDao();
        
        //pcfD.add(pcf);
        
        
        //System.out.println(pcfD.displayAll());
        //pcfD.updateAllById(pcf);
        //System.out.println(pcfD.displayById(2));
    }
    
}
