package services.interfaces;

import entities.Devis;

public interface DevisService {
    void addDevis(Devis devis);
     Devis getDevisByProjetId(int projetId);
}
