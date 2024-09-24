package services.implementations;

import entities.Materiaux;
import repositories.implementations.MateriauxRepositoryImpl;
import repositories.interfaces.MateriauxRepository;
import services.interfaces.MateriauxService;

import java.util.List;
import java.util.Optional;

public class MateriauxServiceImpl implements MateriauxService {

MateriauxRepository materiauxRepository ;
    public MateriauxServiceImpl(MateriauxRepository materiauxRepository) {
        this.materiauxRepository = materiauxRepository;
    }
    @Override
    public void addMateriaux(Materiaux materiaux, int projectId) {
        materiauxRepository.addMateriaux(materiaux,projectId);
    }

    @Override
    public double calculateTotalMaterialCost(int projetId) {
        return materiauxRepository.calculateTotalMaterialCost(projetId);
    }

    @Override
    public Optional<List<Materiaux>> getMateriauxByProjetId(Integer projetId) {
        return materiauxRepository.getMateriauxByProjetId(projetId);
    }
}
