package repositories.implementations;

import database.DatabaseConnection;
import entities.Client;
import repositories.interfaces.ClientRepository;
import utils.InputValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            ps.setBoolean(4, client.isEst_professionnel());
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
}


