package repository;

import model.Imovel;

import java.util.List;

public interface ImovelRepository {
    Imovel buscaPorId(Integer id);
    Imovel criaOuAtualiza(Imovel imovel);
    void remove(Imovel imovel);
    List<Imovel> imoveisDisponiveisPorEndereco(String tipo, String endereco);
    List<Imovel> imoveisDisponiveisPorPreco(double valorSugerido);
}