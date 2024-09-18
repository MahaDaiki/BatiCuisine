package repositories.implementations;

import database.DatabaseConnection;
import entities.Client;
import repositories.interfaces.ClientRepository;
import utils.InputValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class ClientRepositoryImpl implements ClientRepository {
    final Connection connection = DatabaseConnection.getInstance().getConnection();
    final InputValidator validator = InputValidator.getInstance();
    @Override
    public void add(Client client) {
        if (!validator.validatePhoneNumber(client.getTelephone())) {
            System.out.println("Invalid phone number: Must be exactly 10 digits.");
            return;
        }
        String sql ="Insert into clients (nom, adresse, telephone, est_professionnel) values (?,?,?,?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, client.getNom());
            ps.setString(2,client.getAdresse());
            ps.setString(3,client.getTelephone());
            ps.setBoolean(4, client.isEst_professionnel());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to insert client: " + e.getMessage());
        }

    }
}
