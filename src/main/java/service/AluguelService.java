package service;

import model.Aluguel;
import model.Cliente;
import model.Locacao;
import repository.AluguelRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AluguelService {
    private AluguelRepository aluguelRepository;
    private EmailService emailService;

    public Aluguel gerarAluguel(Locacao locacao, double valorPago, LocalDate dataDePagamento) {
        if (locacao == null) {
            throw new RuntimeException("Dados Inválidos. Tente Novamente!");
        }
        Aluguel aluguel = new Aluguel();
        aluguel.setLocacao(locacao);
        aluguel.setValorPago(valorPago);
        aluguel.setDataDePagamento(dataDePagamento);
        aluguel.setObservacoes("...");

        this.enviaComprovanteDePagamentoPorEmail(locacao.getCliente());

        aluguelRepository.criaOuAtualiza(aluguel);

        return aluguel;
    }

    public double verificaValorDoAluguel(Aluguel aluguel, double valor) {
        if (valor < aluguel.getLocacao().getValorDoAluguel()) {
            throw new IllegalArgumentException("Valor abaixo do sugerido. Tente outro valor");
        }
        else {
            aluguel.setValorPago(valor);
        }
        return aluguel.getValorPago();
    }

    public double calculaMulta(Aluguel aluguel) {
        long diasDeAtraso = aluguel.getLocacao().getDataDoVencimento().until(aluguel.getDataDePagamento(), ChronoUnit.DAYS);
        if (diasDeAtraso <= 0)
            throw new IllegalArgumentException("Data inválida! Tente novamente!");
        double valorTotal = aluguel.getLocacao().getValorDoAluguel() + (aluguel.getLocacao().getValorDoAluguel() * (aluguel.getLocacao().getMulta() * diasDeAtraso));
        if (valorTotal > (aluguel.getLocacao().getValorDoAluguel() * 1.8)) {
            aluguel.setValorPago((aluguel.getLocacao().getValorDoAluguel() * 1.8));
        }
        else{
            aluguel.setValorPago(valorTotal);
        }
        return aluguel.getValorPago();
    }

    public void notificaClientesComAluguelEmAtraso() {
        List<Aluguel> alugueisEmAtraso = aluguelRepository.buscaTodosOsAlugueisEmAtraso();
        alugueisEmAtraso.forEach(aluguel -> emailService.notificaCliente(aluguel.getLocacao().getCliente()));
    }

    private void enviaComprovanteDePagamentoPorEmail(Cliente cliente) {
        emailService.notificaCliente(cliente);
    }
}
