package repositories.implementations;

import database.DatabaseConnection;
import entities.Materiaux;
import repositories.interfaces.MateriauxRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<List<Materiaux>> getMateriauxByProjetId(Integer projetId) {
        List<Materiaux> materiauxList = new ArrayList<>();
        String query = "SELECT * FROM materiaux WHERE projet_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, projetId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Integer composantId = rs.getInt("composant_id");
                    String nom = rs.getString("nom");
                    Double tauxTVA = rs.getDouble("taux_TVA");
                    Double coutUnitaire = rs.getDouble("cout_unitaire");
                    Double quantite = rs.getDouble("quantite");
                    Double coefficientQualite = rs.getObject("coefficient_qualite", Double.class);
                    Double coutTransport = rs.getObject("cout_transport", Double.class);


                    coefficientQualite = (coefficientQualite != null) ? coefficientQualite : 1.0;
                    coutTransport = (coutTransport != null) ? coutTransport : 0.0;

                    Materiaux materiaux = new Materiaux(composantId, nom, coutUnitaire, quantite, tauxTVA, projetId, coefficientQualite, coutTransport);
                    materiauxList.add(materiaux);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return materiauxList.isEmpty() ? Optional.empty() : Optional.of(materiauxList);
    }
}

