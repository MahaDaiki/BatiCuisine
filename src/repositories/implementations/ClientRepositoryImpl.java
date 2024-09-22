package repositories.implementations;

import database.DatabaseConnection;
import entities.Client;
import entities.Projet;
import enums.EtatProjet;
import repositories.interfaces.ClientRepository;
import utils.InputValidator;

import java.sql.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {
    final Connection connection = DatabaseConnection.getInstance().getConnection();
    final InputValidator validator = InputValidator.getInstance();

    @Override
    public void add(Client client) {
        if (!validator.validatePhoneNumber(client.getTelephone())) {
            System.out.println("Invalid phone number: Must be exactly 10 digits.");
            return;
        }
        String sql = "Insert into clients (nom, adresse, telephone, est_professionnel) values (?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getAdresse());
            ps.setString(3, client.getTelephone());
            ps.setBoolean(4, client.getEst_professionnel());
            ps.executeUpdate();
            System.out.println("Client ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Add Failed: " + e.getMessage());
        }

    }

    @Override
    public Optional<Client> findClientByName(String name) {
        String sql = "select * from clients where nom = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Client client = new Client(
                        rs.getInt("Client_id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("est_professionnel")
                );
                return Optional.of(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Search Failed: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Client> getAllClientsWithProjets(int clientId) {

            String sql = "SELECT c.client_id, c.client_name, c.client_address, c.client_phone, c.client_email, " +
                    "p.projet_id, p.nom_projet, p.marge_beneficiaire, p.cout_total, p.etat_projet, p.surface " +
                    "FROM clients c " +
                    "INNER JOIN projets p ON c.client_id = p.client_id"+
                     "WHERE c.client_id = ?" ;

            List<Client> clients = new ArrayList<>();

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {

                    Client client = new Client(
                            rs.getInt("Client_id"),
                            rs.getString("nom"),
                            rs.getString("adresse"),
                            rs.getString("telephone"),
                            rs.getBoolean("est_professionnel")
                    );


                    Projet projet = new Projet(
                            rs.getInt("projet_id"),
                            rs.getString("nom_projet"),
                            rs.getDouble("marge_beneficiaire"),
                            rs.getDouble("cout_total"),
                            EtatProjet.valueOf(rs.getString("etat_projet")),
                            rs.getDouble("surface"),
                            rs.getInt("client_id")
                    );

                    clients.add(client);
                }
            } catch (SQLException e) {
                System.err.println("Error retrieving clients with projets: " + e.getMessage());
            }

            return clients;
        }




}


