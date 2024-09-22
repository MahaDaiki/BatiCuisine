package repositories.interfaces;


import entities.Client;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ClientRepository {
   void add(Client client);
   Optional<Client> findClientByName(String name);
   List<Client> getAllClientsWithProjets(int clientId);

}
