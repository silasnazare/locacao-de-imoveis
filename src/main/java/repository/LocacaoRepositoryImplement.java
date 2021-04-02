package repository;

import model.Locacao;

import javax.persistence.EntityManager;

public class LocacaoRepositoryImplement implements LocacaoRepository {
    private EntityManager manager;
    private DAO<Locacao> locacaoDAO;

    public LocacaoRepositoryImplement(EntityManager manager) {
        this.manager = manager;
        this.locacaoDAO = new DAO<Locacao>(manager);
    }

    @Override
    public Locacao buscaPorId(Integer id) {
        return locacaoDAO.buscaPorId(Locacao.class, id);
    }

    @Override
    public Locacao criaOuAtualiza(Locacao locacao) {
        return locacaoDAO.criaOuAtualiza(locacao);
    }

    @Override
    public void remove(Locacao locacao) {
        locacaoDAO.remove(locacao);
    }
}