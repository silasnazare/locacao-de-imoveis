package repository;

import model.Cliente;

public interface ClienteRepository {
    Cliente buscaPorNome(String nome);
    void salva(Cliente cliente);
    void atualiza(Cliente cliente);
    void exclui(Cliente cliente);
}
