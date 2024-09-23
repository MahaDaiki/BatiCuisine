package repositories.interfaces;

import entities.Materiaux;

public interface MateriauxRepository {
     void addMateriaux(Materiaux materiaux, int projectId);
      double calculateTotalMaterialCost(int projetId);
}
