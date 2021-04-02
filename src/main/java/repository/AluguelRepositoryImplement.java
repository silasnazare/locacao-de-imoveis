package repository;

import model.Aluguel;
import model.Locacao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

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

    @Override
    public List<Aluguel> buscaAlugueisPagosPorNomeDoCliente(String nome) {
        String jpql = "select a from Aluguel a join a.locacao l join l.cliente c where c.nome = :nome";
        TypedQuery<Aluguel> aluguelTypedQuery = manager.createQuery(jpql, Aluguel.class);
        List<Aluguel> aluguelList = aluguelTypedQuery.setParameter("nome", nome).getResultList();
        return aluguelList;
    }

    @Override
    public List<Aluguel> buscaAlugueisEmAtraso(String nome) {
        String jpql = "select a from Aluguel a join a.locacao l join l.cliente c where c.nome = :nome and a.dataDePagamento < :hoje";
        TypedQuery<Aluguel> aluguelTypedQuery = manager.createQuery(jpql, Aluguel.class);
        List<Aluguel> aluguelList = aluguelTypedQuery.setParameter("nome", nome).setParameter("hoje", LocalDate.now()).getResultList();
        return aluguelList;
    }
}
