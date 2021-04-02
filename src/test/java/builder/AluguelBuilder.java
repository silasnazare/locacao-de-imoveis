package builder;

import model.Aluguel;
import model.Locacao;

import java.time.LocalDate;

public class AluguelBuilder {
    private Aluguel aluguel;
    private Locacao locacao;

    public AluguelBuilder() { }

    public static AluguelBuilder umAluguel() {
        AluguelBuilder aluguelBuilder = new AluguelBuilder();
        aluguelBuilder.aluguel = new Aluguel();
        aluguelBuilder.aluguel.setObservacoes("...");
        return aluguelBuilder;
    }

    public AluguelBuilder comValorPagoDe(double valorPago) {
        aluguel.setValorPago(valorPago);
        return this;
    }

    public AluguelBuilder paraALocacao(Locacao locacao) {
        aluguel.setLocacao(locacao);
        return this;
    }

    public AluguelBuilder pagoComAtraso() {
        aluguel.setDataDePagamento(LocalDate.now().minusDays(10));
        return this;
    }

    public Aluguel constroi() {
        return aluguel;
    }
}
