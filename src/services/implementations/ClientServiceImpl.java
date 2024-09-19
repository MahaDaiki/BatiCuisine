package services.implementations;

import entities.Client;
import repositories.implementations.ClientRepositoryImpl;
import repositories.interfaces.ClientRepository;
import services.interfaces.ClientService;

public class ClientServiceImpl implements ClientService {

    final ClientRepository clientRepository = new ClientRepositoryImpl();
    @Override
    public void addClient(Client client) {
        clientRepository.add(client);
    }
}
