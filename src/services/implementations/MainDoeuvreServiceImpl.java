package services.implementations;

import entities.MainDoeuvre;
import repositories.implementations.MainDoeuvreRepositoryImpl;
import repositories.interfaces.MainDoeuvreRepository;
import services.interfaces.MainDoeuvreService;

public class MainDoeuvreServiceImpl  implements MainDoeuvreService {

    final MainDoeuvreRepository mainDoeuvreRepository = new MainDoeuvreRepositoryImpl();

    @Override
    public void addMainDoeuvre(MainDoeuvre mainDoeuvre, int projectId) {
        mainDoeuvreRepository.addMainDoeuvre(mainDoeuvre, projectId);
    }

    @Override
    public double calculeMaiOuvreTotal(int projetId) {
        return mainDoeuvreRepository.calculeMaiOuvreTotal(projetId);
    }


}
