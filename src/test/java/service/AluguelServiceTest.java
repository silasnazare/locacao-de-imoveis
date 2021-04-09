package service;

import builder.AluguelBuilder;
import builder.ClienteBuilder;
import builder.ImovelBuilder;
import builder.LocacaoBuilder;
import model.Aluguel;
import model.Cliente;
import model.Imovel;
import model.Locacao;
import org.hibernate.query.criteria.internal.expression.SimpleCaseExpression;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import repository.AluguelRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AluguelServiceTest {
    private AluguelService aluguelService;
    private AluguelRepository aluguelRepository;
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        aluguelService = new AluguelService();
        aluguelRepository = Mockito.mock(AluguelRepository.class);
        emailService = Mockito.mock(EmailService.class);

        aluguelService.setAluguelRepository(aluguelRepository);
        aluguelService.setEmailService(emailService);
    }

    @Test
    void gerarAluguel() {
        Cliente cliente = ClienteBuilder.umCliente().constroi();
        Imovel imovel = ImovelBuilder.umImovel().constroi();
        Locacao locacao = LocacaoBuilder.umaLocacao().deUmImovel(imovel).paraUmCliente(cliente).constroi();

        Aluguel aluguel = aluguelService.gerarAluguel(locacao, 1000.0, LocalDate.now());

        assertThat(aluguel.getValorPago(), is(equalTo(1000.0)));
        assertThat(aluguel.getDataDePagamento(), is(equalTo(LocalDate.now())));

        Mockito.verify(aluguelRepository).criaOuAtualiza(aluguel);
    }

    @Test
    void notificaClientesComAluguelEmAtraso() {
        Cliente c1 = ClienteBuilder.umCliente().comNome("c1").constroi();
        Cliente c2 = ClienteBuilder.umCliente().comNome("c2").constroi();
        Cliente c3 = ClienteBuilder.umCliente().comNome("c3").constroi();
        Imovel i1 = ImovelBuilder.umImovel().constroi();
        Imovel i2 = ImovelBuilder.umImovel().constroi();
        Imovel i3 = ImovelBuilder.umImovel().constroi();
        Locacao l1 = LocacaoBuilder.umaLocacao().deUmImovel(i1).paraUmCliente(c1).constroi();
        Locacao l2 = LocacaoBuilder.umaLocacao().deUmImovel(i2).paraUmCliente(c2).constroi();
        Locacao l3 = LocacaoBuilder.umaLocacao().deUmImovel(i3).paraUmCliente(c3).constroi();
        Aluguel a1 = AluguelBuilder.umAluguel().paraALocacao(l1).comValorPagoDe(499.90).constroi();
        Aluguel a2 = AluguelBuilder.umAluguel().paraALocacao(l2).comValorPagoDe(599.90).constroi();

        List<Aluguel> alugueisAtrasados = Arrays.asList(a1, a2);
        when(aluguelRepository.buscaTodosOsAlugueisEmAtraso()).thenReturn(alugueisAtrasados);

        aluguelService.notificaClientesComAluguelEmAtraso();

        verify(emailService, times(1)).notificaCliente(c1);
        verify(emailService, times(1)).notificaCliente(c2);
        verify(emailService, never()).notificaCliente(c3);

        verifyNoMoreInteractions(emailService);
    }
}