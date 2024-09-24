package services.implementations;

import entities.Devis;
import repositories.implementations.DevisRepositoryImpl;
import repositories.interfaces.DevisRepository;
import services.interfaces.DevisService;

public class DevisServiceImpl implements DevisService {
    final DevisRepository devisRepository = new DevisRepositoryImpl();
    @Override
    public void addDevis(Devis devis) {
        devisRepository.addDevis(devis);
    }

    @Override
    public Devis getDevisByProjetId(int projetId) {
        return devisRepository.getDevisByProjetId(projetId);
    }
}
