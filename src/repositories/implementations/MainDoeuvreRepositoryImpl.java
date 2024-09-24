package repositories.implementations;

import database.DatabaseConnection;
import entities.MainDoeuvre;
import repositories.interfaces.MainDoeuvreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MainDoeuvreRepositoryImpl implements MainDoeuvreRepository {
    final Connection connection = DatabaseConnection.getInstance().getConnection();
    @Override
    public void addMainDoeuvre(MainDoeuvre mainDoeuvre, int projectId) {

            String sql = "INSERT INTO main_doeuvre (nom,  taux_TVA, taux_horaire, heures_travail, productivite_ouvrier, projet_id, type_composant) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, mainDoeuvre.getNom());
                pstmt.setDouble(2, mainDoeuvre.getTauxTVA());
                pstmt.setDouble(3, mainDoeuvre.getTauxHoraire());
                pstmt.setDouble(4, mainDoeuvre.getHeuresTravail());
                pstmt.setDouble(5, mainDoeuvre.getProductiviteOuvrier());
                pstmt.setInt(6, projectId);
                pstmt.setObject(7, mainDoeuvre.getTypeComposant().name(), java.sql.Types.OTHER);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Failed to add Main d'Œuvre: " + e.getMessage());
            }
        }

    @Override
    public double calculeMaiOuvreTotal(int projetId) {
        String sql = "SELECT (taux_horaire * heures_travail * productivite_ouvrier) * taux_TVA AS total_cost " +
                "FROM main_doeuvre WHERE projet_id = ?";
        double totalCost = 0.0;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, projetId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                totalCost += rs.getDouble("total_cost");
            }
        } catch (SQLException e) {
            System.err.println("Failed to calculate total labor cost: " + e.getMessage());
        }

        return totalCost;
    }

    @Override
    public Optional<List<MainDoeuvre>> getMainDoeuvreByProjetId(Integer projetId) {
        List<MainDoeuvre> mainDoeuvreList = new ArrayList<>();
        String query = "SELECT * FROM main_doeuvre WHERE projet_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, projetId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Integer composantId = rs.getInt("composant_id");
                    String nom = rs.getString("nom");
                    Double tauxTVA = rs.getDouble("taux_TVA");
                    Double tauxHoraire = rs.getObject("taux_horaire", Double.class);
                    Double heuresTravail = rs.getObject("heures_travail", Double.class);
                    Double productiviteOuvrier = rs.getObject("productivite_ouvrier", Double.class);

                    // Assign default values if necessary
                    tauxHoraire = (tauxHoraire != null) ? tauxHoraire : 0.0;
                    heuresTravail = (heuresTravail != null) ? heuresTravail : 0.0;
                    productiviteOuvrier = (productiviteOuvrier != null) ? productiviteOuvrier : 1.0;

                    MainDoeuvre mainDoeuvre = new MainDoeuvre(composantId, nom, tauxTVA, tauxHoraire, heuresTravail, productiviteOuvrier, projetId);
                    mainDoeuvreList.add(mainDoeuvre);
                }
            }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                return mainDoeuvreList.isEmpty() ? Optional.empty() : Optional.of(mainDoeuvreList);
    }


}
