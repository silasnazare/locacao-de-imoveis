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
        return clienteDAO.buscaPorNome(Cliente.class, nome);

    }

    @Override
    public Cliente salvaOuAtualiza(Cliente cliente) {
        return clienteDAO.salvaOuAtualiza(cliente);
    }


    @Override
    public void remove(Cliente cliente) {
        clienteDAO.remove(cliente);
    }
}
