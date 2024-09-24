package repositories.interfaces;


import entities.Client;

import java.util.Optional;

public interface ClientRepositoryInt {
   void add(Client client);
   Optional<Client> findClientByName(String name);


}
