package DAO;

import Model.Employe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAOIMpl implements EmployeeDAOI {
    private Connection conn;
    private Connexion mysqlConnexion;

    public EmployeeDAOIMpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addEmployee(Employe employe) {
        // Étape 1: Obtenir les IDs correspondants pour role et poste
        String roleSQL = "SELECT id FROM Role WHERE nom_role = ?";
        String posteSQL = "SELECT id FROM Poste WHERE nom_poste = ?";

        int roleId = 0;
        int posteId = 0;

        try {
            // Récupérer l'ID du rôle
            try (PreparedStatement roleStmt = conn.prepareStatement(roleSQL)) {
                roleStmt.setString(1, employe.getRole().name()); // 'ADMIN' ou 'EMPLOYE'
                ResultSet rsRole = roleStmt.executeQuery();
                if (rsRole.next()) {
                    roleId = rsRole.getInt("id");
                }
            }

            // Récupérer l'ID du poste
            try (PreparedStatement posteStmt = conn.prepareStatement(posteSQL)) {
                posteStmt.setString(1, employe.getPoste().name()); // 'Ingénieur', 'Développeur', etc.
                ResultSet rsPoste = posteStmt.executeQuery();
                if (rsPoste.next()) {
                    posteId = rsPoste.getInt("id");
                }
            }

            // Étape 2: Insérer les données dans la table Employee
            String sql = "INSERT INTO Employee (nom, prenom, email, telephone, salaire, role_id, poste_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, employe.getNom());
                stmt.setString(2, employe.getPrenom());
                stmt.setString(3, employe.getEmail());
                stmt.setString(4, employe.getTelephone());
                stmt.setDouble(5, employe.getSalaire());
                stmt.setInt(6, roleId);   // Utiliser l'ID récupéré pour role
                stmt.setInt(7, posteId);  // Utiliser l'ID récupéré pour poste
                stmt.executeUpdate(); // Exécuter l'insertion
                System.out.println("Employé ajouté avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'employé : " + e.getMessage());
        }
    }

    @Override
    public void update(Employe employe, int id) {
        // Étape 1: Obtenir les IDs correspondants pour role et poste
        String roleSQL = "SELECT id FROM Role WHERE nom_role = ?";
        String posteSQL = "SELECT id FROM Poste WHERE nom_poste = ?";

        int roleId = 0;
        int posteId = 0;

        try {
            // Récupérer l'ID du rôle
            try (PreparedStatement roleStmt = conn.prepareStatement(roleSQL)) {
                roleStmt.setString(1, employe.getRole().name()); // 'ADMIN' ou 'EMPLOYE'
                ResultSet rsRole = roleStmt.executeQuery();
                if (rsRole.next()) {
                    roleId = rsRole.getInt("id");
                }
            }

            // Récupérer l'ID du poste
            try (PreparedStatement posteStmt = conn.prepareStatement(posteSQL)) {
                posteStmt.setString(1, employe.getPoste().name()); // 'Ingénieur', 'Développeur', etc.
                ResultSet rsPoste = posteStmt.executeQuery();
                if (rsPoste.next()) {
                    posteId = rsPoste.getInt("id");
                }
            }

            // Étape 2: Vérifier si l'employé existe déjà avec l'ID donné
            String checkEmployeeSQL = "SELECT * FROM Employee WHERE id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkEmployeeSQL)) {
                checkStmt.setInt(1, id);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    // Étape 3: Mettre à jour les informations de l'employé
                    String updateSQL = "UPDATE Employee SET nom = ?, prenom = ?, email = ?, telephone = ?, salaire = ?, role_id = ?, poste_id = ? WHERE id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
                        updateStmt.setString(1, employe.getNom());
                        updateStmt.setString(2, employe.getPrenom());
                        updateStmt.setString(3, employe.getEmail());
                        updateStmt.setString(4, employe.getTelephone());
                        updateStmt.setDouble(5, employe.getSalaire());
                        updateStmt.setInt(6, roleId);   // Utiliser l'ID récupéré pour role
                        updateStmt.setInt(7, posteId);  // Utiliser l'ID récupéré pour poste
                        updateStmt.setInt(8, id);       // L'ID de l'employé à mettre à jour
                        updateStmt.executeUpdate();    // Exécuter la mise à jour
                        System.out.println("Employé mis à jour avec succès !");
                    }
                } else {
                    System.err.println("Aucun employé trouvé avec l'ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'employé : " + e.getMessage());
        }
    }

}
