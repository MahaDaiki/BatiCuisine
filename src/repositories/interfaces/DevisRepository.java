package repositories.interfaces;

import entities.Devis;

import java.util.List;

public interface DevisRepository {
     void addDevis(Devis devis);
     Devis getDevisByProjetId(int projetId);
}
