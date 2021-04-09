package service;

import model.Cliente;
import model.Imovel;
import model.Locacao;
import repository.LocacaoRepository;

import java.time.LocalDate;

public class LocacaoService {
    private LocacaoRepository locacaoRepository;

    public void setLocacaoRepository(LocacaoRepository locacaoRepository) {
        this.locacaoRepository = locacaoRepository;
    }

    public Locacao alugarImovel(Cliente cliente, Imovel imovel, double valorDoAluguel) {
        if (cliente == null || imovel == null) {
            throw new IllegalArgumentException("Dados Inválidos. Tente novamente!");
        }
        Locacao locacao = new Locacao();
        locacao.setImovel(imovel);
        locacao.setCliente(cliente);
        locacao.setValorDoAluguel(valorDoAluguel);
        locacao.setMulta(0.33);
        locacao.setDataDoVencimento(LocalDate.now().plusDays(30));
        locacao.setInicioDoContrato(LocalDate.now());
        locacao.setFinalDoContrato(LocalDate.now().plusYears(1));
        locacao.setObservacoes("...");

        locacaoRepository.criaOuAtualiza(locacao);

        return locacao;
    }
}
