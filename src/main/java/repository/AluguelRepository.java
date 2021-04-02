package repository;

import model.Aluguel;

import java.util.List;

public interface AluguelRepository {
    Aluguel buscaPorId(Integer id);
    Aluguel criaOuAtualiza(Aluguel aluguel);
    void remove(Aluguel aluguel);
    List<Aluguel> buscaAlugueisPagosPorNomeDoCliente(String nome);
    List<Aluguel> buscaAlugueisEmAtraso(String nome);
}
