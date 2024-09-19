package services.interfaces;

import entities.Client;

import java.util.Optional;

public interface ClientService {
    void addClient(Client client);
    Optional<Client> findClientByName(String name);
}
