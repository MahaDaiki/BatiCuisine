package services.implementations;

import entities.Projet;
import repositories.implementations.ProjetRepositoryImpl;
import repositories.interfaces.ProjetRepository;
import services.interfaces.MainDoeuvreService;
import services.interfaces.MateriauxService;
import services.interfaces.ProjetService;

import java.util.HashMap;
import java.util.List;

public class ProjetServiceImpl implements ProjetService {
final ProjetRepository projetRepository;


    public ProjetServiceImpl(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    @Override
    public void addProjet(Projet projet, int client_id) {
        projetRepository.addProjet(projet, client_id);
    }

    @Override
    public List<Projet> getAllProjets() {
        return projetRepository.getAllProjets();
    }

    @Override
    public void updateProjet(Projet projet, int projet_id) {


        projetRepository.updateProjet(projet, projet_id);
    }

    @Override
    public void updateProjetStatus(Projet projet, int projet_id) {
        projetRepository.updateProjetStatus(projet, projet_id);
    }

    @Override
    public int getLastInsertedProjetId() {
        return projetRepository.getLastInsertedProjetId();
    }

    @Override
    public HashMap<String, Object> getProjectAndClientDetails(int projetId) {
        return projetRepository.getProjectAndClientDetails(projetId);
    }
}
