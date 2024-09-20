package repositories.interfaces;

import entities.MainDoeuvre;

public interface MainDoeuvreRepository {
    void addMainDoeuvre(MainDoeuvre mainDoeuvre , int projectId);
    double calculateTauxHoraire(double couTotal, double heuresTravaille);

}
