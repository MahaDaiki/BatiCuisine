package services.interfaces;

import entities.Projet;

import java.util.List;

public interface ProjetService {
    void addProjet(Projet projet, int client_id);
    List<Projet> getAllProjets();
    void updateProjet(Projet projet, int projet_id);
    void updateProjetStatus(Projet projet, int projet_id);
    int getLastInsertedProjetId();
}
