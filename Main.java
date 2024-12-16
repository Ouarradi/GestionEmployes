package app;
import Controller.EmplpyeController;
import DAO.EmployeeDAOIMpl;
import Model.EmployeModel;
import View.EmployeView;
import java.sql.Connection;
import DAO.Connexion;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        
        try {
            // Initialisation de la connexion à la base de données
            connection = Connexion.getConnexion();

            if (connection == null) {
                throw new RuntimeException("La connexion à la base de données a échoué.");
            }

            // Initialisation des composants MVC
            EmployeView view = new EmployeView();
            EmployeeDAOIMpl dao = new EmployeeDAOIMpl(connection);
            EmployeModel model = new EmployeModel(dao);

            // Création et liaison du contrôleur
            new EmplpyeController(view, model);

            // Rendre la vue visible
            view.setVisible(true);

        } catch (Exception e) {
            // Gestion des erreurs avec affichage du message
            System.err.println("Erreur lors de l'initialisation : " + e.getMessage());
            e.printStackTrace();

        } finally {
            // Fermeture de la connexion si nécessaire
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
                }
            }
        }
    }
}
