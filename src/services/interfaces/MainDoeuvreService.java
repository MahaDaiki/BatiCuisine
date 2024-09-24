package services.interfaces;

import entities.MainDoeuvre;

import java.util.List;
import java.util.Optional;

public interface MainDoeuvreService {
    void addMainDoeuvre(MainDoeuvre mainDoeuvre , int projectId);

    double calculeMaiOuvreTotal(int projetId);
    Optional<List<MainDoeuvre>> getMainDoeuvreByProjetId(Integer projetId);

}
