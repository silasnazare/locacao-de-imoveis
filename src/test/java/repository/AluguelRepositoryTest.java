package repository;

import builder.AluguelBuilder;
import builder.ClienteBuilder;
import builder.ImovelBuilder;
import builder.LocacaoBuilder;
import model.Aluguel;
import model.Cliente;
import model.Imovel;
import model.Locacao;
import org.junit.jupiter.api.*;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

class AluguelRepositoryTest {
    private EntityManager manager;
    private static EntityManagerFactory factory;
    private AluguelRepository alugueis;
    private LocacaoRepository locacoes;
    private ClienteRepository clientes;
    private ImovelRepository imoveis;

    @BeforeAll
    static void start() {
        factory = Persistence.createEntityManagerFactory("br.edu.ifma.es2_lab3-locacaodeimoveis-integracao_1.0-SNAPSHOTPU_test");
    }

    @BeforeEach
    void setUp() {
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
        alugueis = new AluguelRepositoryImplement(manager);
        locacoes = new LocacaoRepositoryImplement(manager);
        clientes = new ClienteRepositoryImplement(manager);
        imoveis = new ImovelRepositoryImplement(manager);
    }

    @AfterEach
    void tearDown() {
        manager.getTransaction().rollback();
    }

    @AfterAll
    static void end() {
        factory.close();
    }

    @Test
    void buscaPorId() {
        Aluguel aluguel = AluguelBuilder.umAluguel().comValorPagoDe(1000).constroi();
        aluguel.setId(100000);
        alugueis.criaOuAtualiza(aluguel);
        manager.flush();
        manager.clear();

        assertThat(100000, is(equalTo(aluguel.getId())));
    }

    @Test
    void buscaAlugueisPagosPorNomeDoCliente() {
        Cliente silas = ClienteBuilder.umCliente().comNome("Silas").constroi();
        Imovel casaCentro = ImovelBuilder.umImovel().doTipo("casa").noEndereco("Centro").constroi();
        Locacao locacao = LocacaoBuilder.umaLocacao().deUmImovel(casaCentro).paraUmCliente(silas).constroi();
        Aluguel janeiro = AluguelBuilder.umAluguel().paraALocacao(locacao).comValorPagoDe(949.90).constroi();
        Aluguel fevereiro = AluguelBuilder.umAluguel().paraALocacao(locacao).comValorPagoDe(1149.90).constroi();
        clientes.criaOuAtualiza(silas);
        imoveis.criaOuAtualiza(casaCentro);
        locacoes.criaOuAtualiza(locacao);
        alugueis.criaOuAtualiza(janeiro);
        alugueis.criaOuAtualiza(fevereiro);
        manager.clear();

        List<Aluguel> listaAlugueis;
        listaAlugueis = alugueis.buscaAlugueisPagosPorNomeDoCliente("Silas");

        Assertions.assertEquals(2, listaAlugueis.size());
    }

    @Test
    void buscaAlugueisEmAtrasoPorNome() {
        Cliente silas = ClienteBuilder.umCliente().comNome("Silas").constroi();
        Imovel casaCentro = ImovelBuilder.umImovel().doTipo("casa").noEndereco("Centro").constroi();
        Locacao locacao = LocacaoBuilder.umaLocacao().deUmImovel(casaCentro).paraUmCliente(silas).constroi();
        Aluguel janeiro = AluguelBuilder.umAluguel().paraALocacao(locacao).comValorPagoDe(949.90).constroi();
        Aluguel fevereiro = AluguelBuilder.umAluguel().paraALocacao(locacao).comValorPagoDe(1149.90).pagoComAtraso().constroi();
        clientes.criaOuAtualiza(silas);
        imoveis.criaOuAtualiza(casaCentro);
        locacoes.criaOuAtualiza(locacao);
        alugueis.criaOuAtualiza(janeiro);
        alugueis.criaOuAtualiza(fevereiro);
        manager.clear();

        List<Aluguel> listaAlugueis = alugueis.buscaAlugueisEmAtrasoPorNomeDoCliente("Silas");

        Assertions.assertEquals(1, listaAlugueis.size());
    }

    /*@Test
    void vericaValorDoAluguel() {
        Locacao locacao = LocacaoBuilder.umaLocacao().noValorDe(1000).constroi();
        Aluguel janeiro = AluguelBuilder.umAluguel().paraALocacao(locacao).constroi();

        locacoes.criaOuAtualiza(locacao);
        alugueis.criaOuAtualiza(janeiro);
        manager.clear();

        double valorPago = alugueis.verificaValorDoAluguel(janeiro, 1000);

        Assertions.assertEquals(1000, valorPago);
    }*/

    /*@Test
    void calculaMulta() {
        Locacao locacao = LocacaoBuilder.umaLocacao().noValorDe(1000).comVencimentoEm(LocalDate.now()).comMultaDe(0.33).constroi();
        Aluguel janeiro = AluguelBuilder.umAluguel().paraALocacao(locacao).comPagamentoEm(LocalDate.of(2021, 4, 4)).constroi();

        locacoes.criaOuAtualiza(locacao);
        alugueis.criaOuAtualiza(janeiro);
        manager.clear();

        double multa = alugueis.calculaMulta(janeiro);

        Assertions.assertEquals(1660, multa);
    }*/
}