package repository;

import model.Imovel;

import javax.persistence.EntityManager;
import java.util.List;

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

    @Override
    public List<Imovel> imoveisDisponiveisPorEndereco(String tipo, String endereco) {
        return manager.createQuery("select i from Imovel i where i.ativo = false and i.tipo = :tipo and i.endereco = :endereco", Imovel.class)
               .setParameter("tipo", tipo).setParameter("endereco", endereco).getResultList();
    }

    @Override
    public List<Imovel> imoveisDisponiveisPorPreco(double valorSugerido) {
        return manager.createQuery("select i from Imovel i where i.valorSugerido <= :valorSugerido", Imovel.class)
                .setParameter("valorSugerido", valorSugerido).getResultList();
    }
}
