package repository;

import model.Imovel;

public interface ImovelRepository {
    Imovel buscaPorId(Integer id);
    Imovel criaOuAtualiza(Imovel imovel);
    void remove(Imovel imovel);
}