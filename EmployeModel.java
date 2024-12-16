package Model;
import DAO.EmployeeDAOIMpl;
public class EmployeModel
{
    private EmployeeDAOIMpl Dao;
    public EmployeModel(EmployeeDAOIMpl Dao){
        this.Dao = Dao;
    }
    public boolean addEmployee(String nom, String prenom, String email,String telephone,double salaire , Employe.Role role, Employe.Poste poste){
        if(salaire <= 0){
            System.out.println("erreur : salaire doit etre > 0");
            return false;
        }
        if( email == null || !email.contains("@")){

            System.out.println("Email invalid");
            return false;
        }
        Employe nouveauEmploye = new Employe(nom, prenom, email,telephone, salaire , role,poste);
        Dao.addEmployee(nouveauEmploye);
        return true;
    }

}
