package services.interfaces;

import entities.Materiaux;

import java.util.List;
import java.util.Optional;

public interface MateriauxService {
    void addMateriaux(Materiaux materiaux, int projectId);
    public double calculateTotalMaterialCost(int projetId);
    Optional<List<Materiaux>> getMateriauxByProjetId(Integer projetId);

}
