package repository;

import model.Imovel;
import model.Locacao;

import javax.persistence.EntityManager;
import java.util.List;

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

    @Override
    public List<Imovel> imoveisDisponiveisPorEndereco(String endereco) {
        return manager.createQuery("select i from Imovel i join Locacao l on l.imovel.id = i.id where l.ativo = false and i.endereco = :endereco", Imovel.class)
                .setParameter("endereco", endereco).getResultList();
    }
}