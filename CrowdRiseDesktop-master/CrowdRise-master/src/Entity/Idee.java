package Entity;

import java.sql.Date;
import java.util.List;


public class Idee extends Projet {
    private double remuneration_totale;
    private List<Solution> solutions;
    private String image;

    public Idee() {
    }

    public Idee(double remuneration_totale, String nom, Date debut, Date fin, String description, Date date) {
        super(nom, debut, fin,description, date);
        this.remuneration_totale = remuneration_totale;
    }

    public Idee(int id) {
        super(id);
    }
    //new Idee(20, liste2.get(i).getNom(), null, null, liste2.get(i).getDescription(), liste2.get(i).getDate());

    public Idee(double remuneration_totale, String image, String nom, Date debut, Date fin, String description, Date date,Membre membre) {
        super(nom, debut, fin,description, date,membre);
        this.remuneration_totale = remuneration_totale;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRemuneration_totale() {
        return remuneration_totale;
    }

    public void setRemuneration_totale(double remuneration_totale) {
        this.remuneration_totale = remuneration_totale;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    @Override
    public String toString() {
        
        return super.toString()+" Idee{" + "remuneration_totale=" + remuneration_totale + ", image=" + image + '}';
    }    
}
