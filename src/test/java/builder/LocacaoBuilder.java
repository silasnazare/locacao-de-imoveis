package builder;

import model.Cliente;
import model.Imovel;
import model.Locacao;

public class LocacaoBuilder {
    private Locacao locacao;

    public LocacaoBuilder() { }

    public static LocacaoBuilder umaLocacao() {
        LocacaoBuilder locacaoBuilder = new LocacaoBuilder();
        locacaoBuilder.locacao = new Locacao();
        locacaoBuilder.locacao.setObservacoes("...");
        return locacaoBuilder;
    }

    public LocacaoBuilder deUmImovel(Imovel imovel) {
        locacao.setImovel(imovel);
        return this;
    }

    public LocacaoBuilder paraUmCliente(Cliente cliente) {
        locacao.setCliente(cliente);
        return this;
    }

    public LocacaoBuilder noValorDe(double valorDoAluguel) {
        locacao.setValorDoAluguel(valorDoAluguel);
        return this;
    }

    public Locacao constroi() {
        return locacao;
    }
}
