package repositories.interfaces;

import entities.MainDoeuvre;

public interface MainDoeuvreRepository {
    void addMainDoeuvre(MainDoeuvre mainDoeuvre , int projectId);
    double calculeMaiOuvreTotal(int projetId);

}
