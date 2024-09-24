package services.implementations;

import entities.MainDoeuvre;
import repositories.implementations.MainDoeuvreRepositoryImpl;
import repositories.interfaces.MainDoeuvreRepository;
import services.interfaces.MainDoeuvreService;

import java.util.List;
import java.util.Optional;

public class MainDoeuvreServiceImpl  implements MainDoeuvreService {

    final MainDoeuvreRepository mainDoeuvreRepository;

    public MainDoeuvreServiceImpl(MainDoeuvreRepository mainDoeuvreRepository) {
        this.mainDoeuvreRepository = mainDoeuvreRepository;
    }

    @Override
    public void addMainDoeuvre(MainDoeuvre mainDoeuvre, int projectId) {
        mainDoeuvreRepository.addMainDoeuvre(mainDoeuvre, projectId);
    }

    @Override
    public double calculeMaiOuvreTotal(int projetId) {
        return mainDoeuvreRepository.calculeMaiOuvreTotal(projetId);
    }

    @Override
    public Optional<List<MainDoeuvre>> getMainDoeuvreByProjetId(Integer projetId) {
        return mainDoeuvreRepository.getMainDoeuvreByProjetId(projetId);
    }


}
