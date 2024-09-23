package services.implementations;

import entities.Materiaux;
import repositories.implementations.MateriauxRepositoryImpl;
import repositories.interfaces.MateriauxRepository;
import services.interfaces.MateriauxService;

public class MateriauxServiceImpl implements MateriauxService {

MateriauxRepository materiauxRepository = new MateriauxRepositoryImpl();
    @Override
    public void addMateriaux(Materiaux materiaux, int projectId) {
        materiauxRepository.addMateriaux(materiaux,projectId);
    }

    @Override
    public double calculateTotalMaterialCost(int projetId) {
        return materiauxRepository.calculateTotalMaterialCost(projetId);
    }
}
