package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeView extends JFrame {
    // Déclaration des panels
    JPanel pan1 = new JPanel();
    JPanel pan2 = new JPanel();
    JPanel pan4 = new JPanel();
    JPanel pan5 = new JPanel();

    // Boutons
    JButton ajouter = new JButton("Ajouter");
    JButton modifier = new JButton("Modifier");
    JButton supprimer = new JButton("Supprimer");
    JButton afficher = new JButton("Afficher");

    // Labels
    JLabel nomLabel = new JLabel("Nom:");
    JLabel prenomLabel = new JLabel("Prénom:");
    JLabel emailLabel = new JLabel("Email:");
    JLabel telephoneLabel = new JLabel("Téléphone:");
    JLabel salaireLabel = new JLabel("Salaire:");
    JLabel roleLabel = new JLabel("Rôle:");
    JLabel posteLabel = new JLabel("Poste:");

    // Champs de texte
    JTextField nomField = new JTextField(20);
    JTextField prenomField = new JTextField(20);
    JTextField emailField = new JTextField(20);
    JTextField telephoneField = new JTextField(20);
    JTextField salaireField = new JTextField(20);

    // Modèle et table
    private DefaultTableModel tableModel;
    private JTable table;

    // Comboboxes
    JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Admin", "Employé"});
    JComboBox<String> posteComboBox = new JComboBox<>(new String[]{"Ingénieur", "Développeur", "Technicien"});

    public EmployeView() {
        // Configuration de la fenêtre principale
        setTitle("Gestion des Employés");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuration du panel principal
        pan1.setLayout(new BorderLayout());
        pan1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacement

        // Panel contenant les champs de saisie
        pan2.setLayout(new GridLayout(7, 2, 5, 5));
        pan2.add(nomLabel);
        pan2.add(nomField);
        pan2.add(prenomLabel);
        pan2.add(prenomField);
        pan2.add(emailLabel);
        pan2.add(emailField);
        pan2.add(telephoneLabel);
        pan2.add(telephoneField);
        pan2.add(salaireLabel);
        pan2.add(salaireField);
        pan2.add(roleLabel);
        pan2.add(roleComboBox);
        pan2.add(posteLabel);
        pan2.add(posteComboBox);

        // Panel pour les boutons
        pan4.setLayout(new GridLayout(1, 4, 10, 10));
        pan4.add(ajouter);
        pan4.add(modifier);
        pan4.add(supprimer);
        pan4.add(afficher);

        // Table pour afficher les données
        tableModel = new DefaultTableModel(new Object[]{"Nom", "Prénom", "Email", "Téléphone", "Salaire", "Rôle", "Poste"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Ajout des composants au panel principal
        pan5.add(pan4);
        pan1.add(pan2, BorderLayout.NORTH);
        pan1.add(pan5, BorderLayout.SOUTH);
        pan1.add(scrollPane, BorderLayout.CENTER);

        add(pan1);

        // Gestion des actions des boutons
        ajouter.addActionListener(e -> ajouterLigne());
        modifier.addActionListener(e -> modifierLigne());
        supprimer.addActionListener(e -> supprimerLigne());
        afficher.addActionListener(e -> afficherToutesLesLignes());

        // Rendre la fenêtre visible
        setVisible(true);
    }

    // Méthode pour ajouter une ligne
    private void ajouterLigne() {
        try {
            String nom = getNom();
            String prenom = getPrenom();
            String email = getEmail();
            String telephone = getTelephone();
            double salaire = getSalaire();
            String role = getRole();
            String poste = getPoste();

            // Ajouter les données au tableau
            tableModel.addRow(new Object[]{nom, prenom, email, telephone, salaire, role, poste});
            afficherMessageSucces("Ligne ajoutée avec succès !");
        } catch (Exception e) {
            afficherMessageErreur("Erreur d'ajout : " + e.getMessage());
        }
    }

    // Méthode pour modifier une ligne sélectionnée
    private void modifierLigne() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.setValueAt(getNom(), selectedRow, 0);
            tableModel.setValueAt(getPrenom(), selectedRow, 1);
            tableModel.setValueAt(getEmail(), selectedRow, 2);
            tableModel.setValueAt(getTelephone(), selectedRow, 3);
            tableModel.setValueAt(getSalaire(), selectedRow, 4);
            tableModel.setValueAt(getRole(), selectedRow, 5);
            tableModel.setValueAt(getPoste(), selectedRow, 6);

            afficherMessageSucces("Ligne modifiée avec succès !");
        } else {
            afficherMessageErreur("Veuillez sélectionner une ligne à modifier.");
        }
    }

    // Méthode pour supprimer une ligne sélectionnée
    private void supprimerLigne() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            afficherMessageSucces("Ligne supprimée avec succès !");
        } else {
            afficherMessageErreur("Veuillez sélectionner une ligne à supprimer.");
        }
    }

    // Méthode pour afficher toutes les lignes (inutile ici)
    private void afficherToutesLesLignes() {
        afficherMessageSucces("Toutes les données sont déjà affichées.");
    }

    // Getters pour récupérer les champs
    public String getNom() { return nomField.getText(); }
    public String getPrenom() { return prenomField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getTelephone() { return telephoneField.getText(); }
    public double getSalaire() { return Double.parseDouble(salaireField.getText()); }
    public String getRole() { return (String) roleComboBox.getSelectedItem(); }
    public String getPoste() { return (String) posteComboBox.getSelectedItem(); }

    // Méthode pour afficher un message d'erreur
    public void afficherMessageErreur(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    // Méthode pour afficher un message de succès
    public void afficherMessageSucces(String message) {
        JOptionPane.showMessageDialog(this, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

    // Méthode main pour lancer l'interface graphique
    public static void main(String[] args) {
        new EmployeView();
        
    }

	public JButton getAjouterButton() {
	    return ajouter;
	}
}