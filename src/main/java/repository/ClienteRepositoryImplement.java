package repository;

import model.Cliente;

import javax.persistence.EntityManager;

public class ClienteRepositoryImplement implements ClienteRepository {
    private EntityManager manager;
    private DAO<Cliente> clienteDAO;

    public ClienteRepositoryImplement(EntityManager manager) {
        this.manager = manager;
        this.clienteDAO = new DAO<Cliente>(manager);
    }

    @Override
    public Cliente buscaPorId(Integer id) {
        return clienteDAO.buscaPorId(Cliente.class, id);
    }

    @Override
    public Cliente buscaPorNome(String nome) {
        return manager.createQuery("select c from Cliente c where c.nome = :pNome", Cliente.class)
                .setParameter("pNome", nome).getSingleResult();
    }

    @Override
    public Cliente criaOuAtualiza(Cliente cliente) {
        return clienteDAO.criaOuAtualiza(cliente);
    }

    @Override
    public void remove(Cliente cliente) {
        clienteDAO.remove(cliente);
    }
}
