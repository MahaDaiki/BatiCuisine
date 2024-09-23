package repositories.implementations;

import database.DatabaseConnection;
import entities.Devis;
import repositories.interfaces.DevisRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
