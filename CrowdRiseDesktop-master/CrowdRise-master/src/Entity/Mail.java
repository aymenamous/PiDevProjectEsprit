/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Yosra
 */
public class Mail {
     
    private String mailAddresseEnvoi ;
    private String pwd ;
    private String mailAddressRecep ; 
    private String mailContenu ;
    private String mailSujet ;

    public String getMailAddresseEnvoi() {
        return mailAddresseEnvoi;
    }

    public void setMailAddresseEnvoi(String mailAddresseEnvoi) {
        this.mailAddresseEnvoi = mailAddresseEnvoi;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMailAddressRecep() {
        return mailAddressRecep;
    }

    public void setMailAddressRecep(String mailAddressRecep) {
        this.mailAddressRecep = mailAddressRecep;
    }

    public String getMailContenu() {
        return mailContenu;
    }

    public void setMailContenu(String mailContenu) {
        this.mailContenu = mailContenu;
    }

    public String getMailSujet() {
        return mailSujet;
    }

    public void setMailSujet(String mailSujet) {
        this.mailSujet = mailSujet;
    }


    
    
}
