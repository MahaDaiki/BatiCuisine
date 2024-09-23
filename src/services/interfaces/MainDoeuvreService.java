package services.interfaces;

import entities.MainDoeuvre;

public interface MainDoeuvreService {
    void addMainDoeuvre(MainDoeuvre mainDoeuvre , int projectId);

    double calculeMaiOuvreTotal(int projetId);
}
