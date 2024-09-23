package repositories.implementations;

import database.DatabaseConnection;
import entities.Materiaux;
import repositories.interfaces.MateriauxRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MateriauxRepositoryImpl implements MateriauxRepository {
    final Connection connection = DatabaseConnection.getInstance().getConnection();
    @Override
    public void addMateriaux(Materiaux materiaux, int projectId) {
        String sql = "INSERT INTO materiaux (nom, cout_unitaire, quantite, taux_TVA, coefficient_qualite, cout_transport, projet_id, type_composant) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, materiaux.getNom());
            pstmt.setDouble(2, materiaux.getCoutUnitaire());
            pstmt.setDouble(3, materiaux.getQuantite());
            pstmt.setDouble(4, materiaux.getTauxTVA());
            pstmt.setDouble(5, materiaux.getCoefficientQualite());
            pstmt.setDouble(6, materiaux.getCoutTransport());
            pstmt.setInt(7, projectId);
            pstmt.setObject(8, materiaux.getTypeComposant().name(), java.sql.Types.OTHER);

            pstmt.executeUpdate();
            System.out.println("Matériaux ajouté avec succès !");

        } catch (SQLException e) {
            System.err.println("Failed to add Matériaux: " + e.getMessage());
        }
        }

    @Override
    public double calculateTotalMaterialCost(int projetId) {

        String sql = "SELECT (cout_unitaire * quantite * coefficient_qualite + cout_transport) * taux_TVA AS total_cost " +
                "FROM materiaux WHERE projet_id = ?";
        double totalCost = 0.0;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, projetId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                totalCost += rs.getDouble("total_cost");
            }
        } catch (SQLException e) {
            System.err.println("Failed to calculate total material cost: " + e.getMessage());
        }

        return totalCost;
    }
}

