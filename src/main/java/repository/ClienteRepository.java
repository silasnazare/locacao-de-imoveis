package repository;

import model.Cliente;

public interface ClienteRepository {
    Cliente buscaPorId(Integer id);
    Cliente buscaPorNome(String nome);
    Cliente salvaOuAtualiza(Cliente cliente);
    void remove(Cliente cliente);
}
