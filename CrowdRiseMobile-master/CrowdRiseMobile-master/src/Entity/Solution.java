package Entity;



/**
 * Created by alaa on 07/02/16.
 */
public class Solution {

    /*private int id;
    private Idee projet;
    private Membre membre;
    private double remuneration;
    private String tache;
    private int confirm_owner;
    private int confirm_solver;*/
    

    private int id;
    private Idee projet ;
    private Membre membre;
    private Double remuneration;
    private String tache;
    private Integer confirm_owner;
    private Integer confirm_solver;
    private String competence;



    public Solution() {
    }
    

    public int getId() {
        return id;
    }

    public Double getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Double remuneration) {
        this.remuneration = remuneration;
    }
    
    
    /*public Solution(int id, Idee projet, Membre membre, double remuneration, String tache, int confirm_owner, int confirm_solver, String competence) {
    this.id = id;
    this.projet = new SimpleObjectProperty<>(projet);
    this.membre = new SimpleObjectProperty<>(membre);
    this.remuneration = new SimpleDoubleProperty(remuneration) ;
    this.tache = new SimpleStringProperty(tache) ;
    this.confirm_owner = new SimpleIntegerProperty(confirm_owner);
    this.confirm_solver = new SimpleIntegerProperty(confirm_solver);
    this.competence = new SimpleStringProperty(competence);
    }
    public Solution(Idee projet, Membre membre, double remuneration, String tache, int confirm_owner, int confirm_solver, String competence) {
    this.projet = new SimpleObjectProperty<>(projet);
    this.membre = new SimpleObjectProperty<>(membre);
    this.remuneration = new SimpleDoubleProperty(remuneration) ;
    this.tache = new SimpleStringProperty(tache) ;
    this.confirm_owner = new SimpleIntegerProperty(confirm_owner);
    this.confirm_solver = new SimpleIntegerProperty(confirm_solver);
    this.competence = new SimpleStringProperty(competence);
    }
    public final void setProjet(Idee value){
    projet.set(value);
    }
    public final Idee getProjet(){
    return projet.get();
    }
    public final ObjectProperty projetProperty(){
    return projet;
    }
    public final void setMembre(Membre value){
    membre.set(value);
    }
    public final Membre getMembre(){
    return membre.get();
    }
    public final ObjectProperty membreProperty(){
    return membre;
    }
    @Override
    public String toString() {
    return "Solution{" +
    "id=" + id +
    ", projet=" + projet +
    ", membre=" + membre +
    ", remuneration=" + remuneration +
    ", tache='" + tache + '\'' +
    ", confirm_owner=" + confirm_owner +
    ", confirm_solver=" + confirm_solver +
    '}';
    }
    public int getId() {
    return id;
    }
    public void setId(int id) {
    this.id = id;
    }
    public final void setRemuneration(Double value) {
    remuneration.set(value);
    }
    public final Double getRemuneration() {
    return remuneration.get();
    }
    public final DoubleProperty remunerationProperty() {
    return remuneration;
    }
    public final void setTache(String value) {
    tache.set(value);
    }
    public final String getTache() {
    return tache.get();
    }
    public final StringProperty tacheProperty() {
    return tache;
    }
    public final void setConfirm_owner(Integer value) {
    confirm_owner.set(value);
    }
    public final Integer getConfirm_owner() {
    return confirm_owner.get();
    }
    public final IntegerProperty confirm_ownerProperty() {
    return confirm_owner;
    }
    public final void setConfirm_solver(Integer value) {
    confirm_solver.set(value);
    }
    public final Integer getConfirm_solver() {
    return confirm_solver.get();
    }
    public final IntegerProperty confirm_solverProperty() {
    return confirm_solver;
    }
    public final void setCompetence(String value) {
    competence.set(value);
    }
    public final String getCompetence() {
    return competence.get();
    }
    public final StringProperty competenceProperty() {
    return competence;
    }*/
    public void setId(int id) {
        this.id = id;
    }

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }
    
}
