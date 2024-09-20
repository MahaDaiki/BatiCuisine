package repositories.implementations;

import database.DatabaseConnection;
import entities.MainDoeuvre;
import repositories.interfaces.MainDoeuvreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainDoeuvreRepositoryImpl implements MainDoeuvreRepository {
    final Connection connection = DatabaseConnection.getInstance().getConnection();
    @Override
    public void addMainDoeuvre(MainDoeuvre mainDoeuvre, int projectId) {
            double heuresTravail = mainDoeuvre.getHeuresTravail();
            double coutUnitaire = mainDoeuvre.getCoutUnitaire();

            double tauxHoraire = calculateTauxHoraire(coutUnitaire, heuresTravail);
            double coutTotal = tauxHoraire * heuresTravail * mainDoeuvre.getProductiviteOuvrier();

            String sql = "INSERT INTO main_doeuvre (nom, cout_unitaire, quantite, taux_TVA, taux_horaire, heures_travail, productivite_ouvrier, projet_id, type_composant) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, mainDoeuvre.getNom());
                pstmt.setDouble(2, coutUnitaire);
                pstmt.setDouble(3, mainDoeuvre.getQuantite());
                pstmt.setDouble(4, mainDoeuvre.getTauxTVA());
                pstmt.setDouble(5, tauxHoraire);
                pstmt.setDouble(6, heuresTravail);
                pstmt.setDouble(7, mainDoeuvre.getProductiviteOuvrier());
                pstmt.setInt(8, projectId);
                pstmt.setObject(9, mainDoeuvre.getTypeComposant().name(), java.sql.Types.OTHER);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Failed to add Main d'Œuvre: " + e.getMessage());
            }
        }

        public double calculateTauxHoraire(double coutTotal, double heuresTravaille) {
            if (heuresTravaille == 0) {
                throw new IllegalArgumentException("Heures de travail ne peut pas être zéro.");
            }
            return coutTotal / heuresTravaille;
        }
}
