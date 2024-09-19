package services.implementations;

import entities.Client;
import repositories.implementations.ClientRepositoryImpl;
import repositories.interfaces.ClientRepository;
import services.interfaces.ClientService;

import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    final ClientRepository clientRepository = new ClientRepositoryImpl();
    @Override
    public void addClient(Client client) {
        clientRepository.add(client);
    }

    @Override
    public Optional<Client> findClientByName(String name) {
        return clientRepository.findClientByName(name);
    }
}
