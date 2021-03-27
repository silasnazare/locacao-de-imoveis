package repository;

import model.Aluguel;

public interface AluguelRepository {
    Aluguel buscaPorId(Integer id);
    Aluguel criaOuAtualiza(Aluguel aluguel);
    void remove(Aluguel aluguel);
}
