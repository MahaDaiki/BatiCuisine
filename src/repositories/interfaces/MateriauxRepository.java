package repositories.interfaces;

import entities.Materiaux;

import java.util.List;
import java.util.Optional;

public interface MateriauxRepository {
     void addMateriaux(Materiaux materiaux, int projectId);
      double calculateTotalMaterialCost(int projetId);
    Optional<List<Materiaux>> getMateriauxByProjetId(Integer projetId);
}
