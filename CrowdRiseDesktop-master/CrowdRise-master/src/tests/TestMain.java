/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Dao.IdeeDAO;
import Dao.ProblemDAO;
import Entity.Idee;
import Entity.Problem;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author userpc
 */
public class TestMain {

    public static void main(String[] args) {
        //Here Test Your DAO
        ProblemDAO idao = new ProblemDAO();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        for (int i = 0; i < 50; i++) {
            Problem p = new Problem(20,date , "Lorem Upsem Description text of the problem", 1, "TITRE ");
            System.out.println("HERE");
            idao.add(p);
        }
         
         
    }
    
}
