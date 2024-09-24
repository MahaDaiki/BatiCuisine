package repositories.implementations;

import database.DatabaseConnection;
import entities.Client;
import entities.Projet;
import enums.EtatProjet;
import repositories.interfaces.ProjetRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
        String sql = "UPDATE projets SET marge_beneficiaire = ? , cout_total = ? WHERE projet_id = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, projet.getMargeBeneficiaire());
            pstmt.setDouble(2, projet.getCoutTotal());
            pstmt.setInt(3, projet_id);
            pstmt.executeUpdate();
            System.out.println("Success !");
        } catch (SQLException e) {
            System.err.println("Fail: " + e.getMessage());
        }
    }

    @Override
    public void updateProjetStatus(Projet projet, int projet_id) {
        String sql = " UPDATE projets SET etat_projet = CAST(? AS etat_projet) WHERE projet_id = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, projet.getEtat_projet().name());
            pstmt.setInt(2, projet_id);
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

    @Override
    public HashMap<String, Object> getProjectAndClientDetails(int projetId) {
        String sql = "SELECT p.projet_id, p.nom_projet, p.marge_beneficiaire, " +
                "p.cout_total, p.etat_projet, p.surface, " +
                "c.client_id, c.nom AS client_nom, " +
                "c.adresse AS client_adresse, c.telephone AS client_telephone, " +
                "c.est_professionnel " +
                "FROM projets p " +
                "LEFT JOIN clients c ON p.client_id = c.client_id " +
                "WHERE p.projet_id = ?";

        HashMap<String, Object> result = new HashMap<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, projetId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Projet projet = new Projet(
                        rs.getInt("projet_id"),
                        rs.getString("nom_projet"),
                        rs.getDouble("marge_beneficiaire"),
                        rs.getDouble("cout_total"),
                        EtatProjet.valueOf(rs.getString("etat_projet")),
                        rs.getDouble("surface"),
                        null
                );

                Client client = new Client(
                        rs.getInt("client_id"),
                        rs.getString("client_nom"),
                        rs.getString("client_adresse"),
                        rs.getString("client_telephone"),
                        rs.getBoolean("est_professionnel")
                );

                result.put("projet", projet);
                result.put("client", client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Retrieval Failed: " + e.getMessage());
        }
        return result;
    }


}
