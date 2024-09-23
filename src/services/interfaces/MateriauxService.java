package services.interfaces;

import entities.Materiaux;

public interface MateriauxService {
    void addMateriaux(Materiaux materiaux, int projectId);
    public double calculateTotalMaterialCost(int projetId);
}
