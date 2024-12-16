
package Controller;

import Model.EmployeModel;
import View.EmployeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EmplpyeController{

    private EmployeView view;
    private EmployeModel model;


    public  EmplpyeController(EmployeView view, EmployeModel model) {
        this.view = view;
        this.model = model;

        // Add ActionListener to buttons using the getter methods
        this.view.getAjouterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        // You can add listeners for other buttons like Modifier, Supprimer, etc., as needed.
    }

    private void addEmployee() {
        // Retrieve input values from the view
        String nom = view.getNom();
        String prenom = view.getPrenom();
        String email = view.getEmail();
        String telephone = view.getTelephone();
        double salaire;

        // Validate if the fields are empty
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty()) {
            view.afficherMessageErreur("Tous les champs doivent être remplis.");
            return;
        }

        // Validate the salary input
        try {
            salaire = view.getSalaire();
        } catch (NumberFormatException e) {
            view.afficherMessageErreur("Le salaire doit être un nombre valide.");
            return;
        }

        // Validate email format
        if (!email.contains("@") || !email.contains(".")) {
            view.afficherMessageErreur("L'email est invalide.");
            return;
        }

        // Validate telephone format (simple check, you can refine it)
        if (telephone.length() < 10) {
            view.afficherMessageErreur("Le numéro de téléphone est invalide.");
            return;
        }

        // Convert String role and poste from JComboBox to Enum values
        String roleString = view.getRole();  // Role as String from JComboBox
        String posteString = view.getPoste();  // Poste as String from JComboBox

        // Assuming that Role and Poste enums have a valueOf method to convert string to enum
        Model.Employe.Role role = Model.Employe.Role.valueOf(roleString);
        Model.Employe.Poste poste = Model.Employe.Poste.valueOf(posteString);

        // Call the model to add the employee
        boolean ajoutReussi = model.addEmployee(nom, prenom, email, telephone, salaire, role, poste);

        // Show success or error message based on the result
        if (ajoutReussi) {
            view.afficherMessageSucces("Employé ajouté avec succès !");
            // Optionally, clear the fields after successful addition
        } else {
            view.afficherMessageErreur("Échec de l'ajout de l'employé. Vérifiez les données.");
        }
    }
}
