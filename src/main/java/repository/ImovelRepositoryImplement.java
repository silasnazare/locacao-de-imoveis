package repository;

import model.Cliente;
import model.Imovel;

import javax.persistence.EntityManager;

public class ImovelRepositoryImplement implements ImovelRepository {
    private EntityManager manager;
    private DAO<Imovel> imovelDAO;

    public ImovelRepositoryImplement(EntityManager manager) {
        this.manager = manager;
        this.imovelDAO = new DAO<Imovel>(manager);
    }

    @Override
    public Imovel buscaPorId(Integer id) {
        return imovelDAO.buscaPorId(Imovel.class, id);
    }

    @Override
    public Imovel criaOuAtualiza(Imovel imovel) {
        return imovelDAO.criaOuAtualiza(imovel);
    }

    @Override
    public void remove(Imovel imovel) {
        imovelDAO.remove(imovel);
    }
}
