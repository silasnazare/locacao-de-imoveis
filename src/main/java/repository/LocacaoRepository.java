package repository;

import model.Locacao;

public interface LocacaoRepository {
    Locacao buscaPorId(Integer id);
    Locacao criaOuAtualiza(Locacao locacao);
    void remove(Locacao locacao);
}
