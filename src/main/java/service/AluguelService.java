package service;

import model.Aluguel;
import model.Locacao;
import repository.AluguelRepository;

import java.time.LocalDate;
import java.util.List;

public class AluguelService {
    private AluguelRepository aluguelRepository;
    private EmailService emailService;

    public void setAluguelRepository(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public Aluguel gerarAluguel(Locacao locacao, double valorPago, LocalDate dataDePagamento) {
        if (locacao == null) {
            throw new IllegalArgumentException("Dados Inválidos. Tente novamente!");
        }
        Aluguel aluguel = new Aluguel();
        aluguel.setLocacao(locacao);
        aluguel.setValorPago(valorPago);
        aluguel.setDataDePagamento(dataDePagamento);
        aluguel.setObservacoes("...");

        aluguelRepository.criaOuAtualiza(aluguel);

        return aluguel;
    }

    public void notificaClientesComAluguelEmAtraso() {
        List<Aluguel> alugueisEmAtraso = aluguelRepository.buscaTodosOsAlugueisEmAtraso();
        alugueisEmAtraso.forEach(aluguel -> emailService.notificaCliente(aluguel.getLocacao().getCliente()));
    }
}
