package repositories.implementations;

import database.DatabaseConnection;
import entities.Devis;
import repositories.interfaces.DevisRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class DevisRepositoryImpl implements DevisRepository {
    final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public void addDevis(Devis devis) {
        String sql = "INSERT INTO devis (montant_estime, date_emission, date_validite, accepte, projet_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, devis.getMontantEstime());
            pstmt.setDate(2, java.sql.Date.valueOf(devis.getDateEmission()));
            pstmt.setDate(3, java.sql.Date.valueOf(devis.getDateValidite()));
            pstmt.setBoolean(4, devis.getAccepte());
            pstmt.setInt(5, devis.getProjetId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Devis ajouté avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du devis: " + e.getMessage());
        }
    }

    @Override
    public Devis getDevisByProjetId(int projetId) {
        String sql = "SELECT * FROM devis WHERE projet_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, projetId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Integer devisId = rs.getInt("devis_id");
                Double montantEstime = rs.getDouble("montant_estime");

                LocalDate dateEmission = rs.getDate("date_emission") != null ?
                        rs.getDate("date_emission").toLocalDate() : null;
                LocalDate dateValidite = rs.getDate("date_validite") != null ?
                        rs.getDate("date_validite").toLocalDate() : null;

                Boolean accepte = rs.getBoolean("accepte");
                Integer projet_id = rs.getInt("projet_id");

                return new Devis(devisId, montantEstime, dateEmission, dateValidite, accepte, projet_id);
            } else {
                System.out.println("Aucun devis trouvé pour le projet_id : " + projetId);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du devis : " + e.getMessage());
        }
        return null;
    }

}
