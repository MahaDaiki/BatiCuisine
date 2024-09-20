package repositories.implementations;

import database.DatabaseConnection;
import entities.Projet;
import enums.EtatProjet;
import repositories.interfaces.ProjetRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetRepositoryImpl implements ProjetRepository {
    final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public void addProjet(Projet projet, int client_id) {
        String sql = "INSERT INTO projets (nom_projet ,marge_beneficiaire, cout_total, etat_projet, surface, client_id ) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, projet.getNomProjet());

            if (projet.getMargeBeneficiaire() != null) {
                pstmt.setDouble(2, projet.getMargeBeneficiaire());
            } else {
                pstmt.setNull(2, Types.DOUBLE);
            }

            if (projet.getCoutTotal() != null) {
                pstmt.setDouble(3, projet.getCoutTotal());
            } else {
                pstmt.setNull(3, Types.DOUBLE);
            }
            pstmt.setObject(4, projet.getEtat_projet().name(), java.sql.Types.OTHER);
            pstmt.setDouble(5, projet.getSurface());
            pstmt.setInt(6, projet.getClientId());

            pstmt.executeUpdate();
            System.out.println("Projet added successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to add projet: " + e.getMessage());
        }

    }

    @Override
    public List<Projet> getAllProjets() {
        String sql = "SELECT * FROM projets";
        List<Projet> projets = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Projet projet = new Projet(
                        rs.getInt("projet_id"),
                        rs.getString("nom_projet"),
                        rs.getDouble("marge_beneficiaire"),
                        rs.getDouble("cout_total"),
                        EtatProjet.valueOf(rs.getString("etat_projet")),
                        rs.getDouble("surface"),
                        rs.getInt("client_id")
                );
                projets.add(projet);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving projets: " + e.getMessage());
        }

        return projets;
    }

    @Override
    public void updateProjet(Projet projet, int projet_id) {
        String sql = "UPDATE projets SET marge_benefinicaire = ? , cout_total = ? WHERE projet_id = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, projet.getMargeBeneficiaire());
            pstmt.setDouble(2, projet.getCoutTotal());
            pstmt.executeUpdate();
            System.out.println("Success !");
        } catch (SQLException e) {
            System.err.println("Fail: " + e.getMessage());
        }
    }

    @Override
    public void updateProjetStatus(Projet projet, int projet_id) {
        String sql = " UPDATE projets SET etat_projet = ? WHERE projet_id = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, projet.getEtat_projet().name());
            pstmt.executeUpdate();
            System.out.println(" ETAT DU PROJET MODIFIE!");
        } catch (SQLException e) {
            System.err.println("Fail: " + e.getMessage());
        }
    }
    @Override
    public int getLastInsertedProjetId() {
        String sql = "SELECT currval(pg_get_serial_sequence('projets', 'projet_id'))";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Failed to get last inserted projet ID: " + e.getMessage());
        }
        return -1;
    }

}
