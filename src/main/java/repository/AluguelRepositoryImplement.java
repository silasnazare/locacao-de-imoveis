package repository;

import model.Aluguel;

import javax.persistence.EntityManager;

public class AluguelRepositoryImplement implements AluguelRepository {
    private EntityManager manager;
    private DAO<Aluguel> aluguelDAO;

    public AluguelRepositoryImplement(EntityManager manager) {
        this.manager = manager;
        this.aluguelDAO = new DAO<Aluguel>(manager);
    }

    @Override
    public Aluguel buscaPorId(Integer id) {
        return aluguelDAO.buscaPorId(Aluguel.class, id);
    }

    @Override
    public Aluguel criaOuAtualiza(Aluguel aluguel) {
        return aluguelDAO.criaOuAtualiza(aluguel);
    }

    @Override
    public void remove(Aluguel aluguel) {
        aluguelDAO.remove(aluguel);
    }
}
