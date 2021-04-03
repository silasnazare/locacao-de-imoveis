package repository;

import model.Aluguel;

import java.time.LocalDate;
import java.util.List;

public interface AluguelRepository {
    Aluguel buscaPorId(Integer id);
    Aluguel criaOuAtualiza(Aluguel aluguel);
    void remove(Aluguel aluguel);
    List<Aluguel> buscaAlugueisPagosPorNomeDoCliente(String nome);
    List<Aluguel> buscaAlugueisEmAtraso(String nome);
    double verificaValorDoAluguel(Aluguel aluguel, double valor);
    double calculaMulta(Aluguel aluguel);
}
