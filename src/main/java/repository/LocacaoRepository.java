package repository;

import model.Imovel;
import model.Locacao;

import java.util.List;

public interface LocacaoRepository {
    Locacao buscaPorId(Integer id);
    Locacao criaOuAtualiza(Locacao locacao);
    void remove(Locacao locacao);
    List<Imovel> imoveisDisponiveisPorEndereco(String endereco);
}