package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_employes";
    private static final String USER = "root";
    private static final String Password ="";
    static Connection conn;
public static Connection getConnexion(){
    try {
        conn = DriverManager.getConnection(URL, USER, Password); // Connexion
        System.out.println("Connexion réussie");
    } catch (Exception ex) {
        System.err.println("Erreur : " + ex); // Affiche l'erreur en cas d'échec
    }
    return conn;
}
    public void close() {

        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connexion fermée");
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la fermeture de la connexion : " + ex);
        }
    }
}

