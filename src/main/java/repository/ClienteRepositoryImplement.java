package repository;

import model.Cliente;

import javax.persistence.EntityManager;

public class ClienteRepositoryImplement implements ClienteRepository {
    private EntityManager manager;

    public ClienteRepositoryImplement(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Cliente buscaPorNome(String nome) {
        return manager.createQuery("select c from Cliente c where c.nome = :pNome", Cliente.class).setParameter("pNome", nome).getSingleResult();
    }

    @Override
    public void salva(Cliente cliente) {
        manager.persist(cliente);
    }

    @Override
    public void atualiza(Cliente cliente) {
        manager.merge(cliente);
    }

    @Override
    public void exclui(Cliente cliente) {
        manager.remove(cliente);
    }
}
