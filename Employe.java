package Model;

public class Employe {

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private double salaire;
    private Role role;
    private Poste poste;


    // Constructeur
    public Employe(String nom, String prenom,String email, String telephone, double salaire ,Role role, Poste poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.salaire = salaire;
        this.role = role;
        this.poste = poste;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public Role getRole() {
        return role;
    }
    public double getSalaire(){
        return salaire;
    }
    public void setSalaire(double salaire){
        this.salaire = salaire;

    }

    public Poste getPoste() {
        return poste;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    // Enums pour Role et Poste
    public enum Role {
        Admin,
        Employé,
    }

    public enum Poste {
        Ingénieur,
        Développeur,
        Technicien,
        Manager,
    }
}
