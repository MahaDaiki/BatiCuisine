package services.implementations;

import entities.Client;
import repositories.implementations.ClientRepositoryImpl;
import repositories.interfaces.ClientRepositoryInt;
import services.interfaces.ClientService;

import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    final ClientRepositoryInt clientRepositoryInt ;
    public ClientServiceImpl(ClientRepositoryInt clientRepository) {
        this.clientRepositoryInt = clientRepository;
    }
    @Override
    public void addClient(Client client) {
        clientRepositoryInt.add(client);
    }

    @Override
    public Optional<Client> findClientByName(String name) {
        return clientRepositoryInt.findClientByName(name);
    }
}
